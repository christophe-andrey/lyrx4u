package net.andrey.lyrx4u.lyrics.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.andrey.lyrx4u.lyrics.TechnicalException;
import net.andrey.lyrx4u.lyrics.domain.IdKind;
import net.andrey.lyrx4u.lyrics.domain.Song;
import net.andrey.lyrx4u.lyrics.domain.SongId;
import net.andrey.lyrx4u.lyrics.domain.Statistics;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;

public class SongJpaDao implements LyricsLibrary {
	public SongJpaDao(EntityManager entityMgr) {
		this.entityMgr = entityMgr;
	}

	private EntityManager entityMgr;

	public SongJpaDao() {

	}

	@Override
	public Song getSong(IdKind kind, String id) {
		CriteriaBuilder cb = entityMgr.getCriteriaBuilder();
		CriteriaQuery<SongId> cq = cb.createQuery(SongId.class);
		Root<SongId> from = cq.from(SongId.class);
		cq.where(cb.and(cb.equal(from.get("kind"), kind),
				cb.equal(from.get("id"), id)));

		TypedQuery<SongId> q = entityMgr.createQuery(cq);
		List<SongId> matches = q.getResultList();
		Song match = null;
		if (!matches.isEmpty())
			match = matches.get(0).getSong();
		return match;
	}

	@Override
	public void addSong(Collection<SongId> ids) {
		entityMgr.getTransaction().begin();
		// Validation (to be externalized in a validator)
		if (ids == null || ids.isEmpty())
			throw new TechnicalException("Invalid object " + ids);
		Song song = ids.iterator().next().getSong();
		for (SongId id : ids)
			if (id.getSong() != song)
				throw new TechnicalException("Invalid object " + ids);
		// Persist ids pointing to that song:
		entityMgr.persist(song);
		for (SongId id : ids) {
			entityMgr.persist(id);
		}
		entityMgr.getTransaction().commit();
	}

	@Override
	public List<Song> searchSongs(String artist, String albumTitle,
			String songTitle) {
		CriteriaBuilder cb = entityMgr.getCriteriaBuilder();
		CriteriaQuery<Song> q = cb.createQuery(Song.class);
		Root<Song> song = q.from(Song.class);
		List<Predicate> conditions = new ArrayList<Predicate>();
		if (artist != null)
			conditions.add(cb.like(song.<String>get("artist"), "%" + artist + "%"));
		if (albumTitle != null)
			conditions.add(cb.like(song.<String>get("album"), "%" + albumTitle + "%"));
		if (songTitle != null)
			conditions.add(cb.like(song.<String>get("title"), "%" + songTitle + "%"));
		q.select(song);
		q.where(conditions.toArray(new Predicate[0]));
		q.orderBy(cb.asc(song.get("artist")), cb.asc(song.get("album")),
				cb.asc(song.get("title")));

		List<Song> matches = entityMgr.createQuery(q).getResultList();
		return matches;
	}

	@Override
	public Statistics getStatistics() {
		CriteriaBuilder cb = entityMgr.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<SongId> from = cq.from(SongId.class);
		CriteriaQuery<Long> select = cq.select(cb.count(from));
		TypedQuery<Long> typedQuery = entityMgr.createQuery(select);
		Long result = typedQuery.getSingleResult();
		return new Statistics(result);
	}

}
