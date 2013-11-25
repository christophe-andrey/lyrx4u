package net.andrey.lyrx4u.lyrics.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.andrey.lyrx4u.lyrics.domain.IdKind;
import net.andrey.lyrx4u.lyrics.domain.Song;
import net.andrey.lyrx4u.lyrics.domain.SongId;
import net.andrey.lyrx4u.lyrics.domain.Statistics;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;

public class InMemoryHashtableLibrary implements LyricsLibrary {

	private HashMap<IdKind, HashMap<String, Song>> db;

	public InMemoryHashtableLibrary() {
		db = new HashMap<>();
		for (IdKind k : IdKind.values())
			db.put(k, new HashMap<String, Song>());
	}

	@Override
	public Song getSong(IdKind kind, String id) {
		return db.get(kind).get(id);
	}

	@Override
	public void addSong(Collection<SongId> ids) {
		boolean alreadyPresent = false;
		for (SongId id : ids) {
			if (getSong(id.getKind(), id.getId()) == null) {
				db.get(id.getKind()).put(id.getId(), id.getSong());
			} else
				alreadyPresent = true;
		}
		if (!alreadyPresent){
			songCount++;
			ids.iterator().next().getSong().setId((long)songSeqId++);
		
		}
	}

	private int songCount;
	private int songSeqId;

	@Override
	public List<Song> searchSongs(String artist, String albumTitle,
			String songTitle) {
		Set<Song> matches = new HashSet<>();
		for (HashMap<String, Song> songMap : db.values())
			for (Song s : songMap.values()) {
				if (!matches.contains(s)) {
					if ((artist == null || s.getArtist().contains(artist))
							&& (albumTitle == null || s.getAlbum().contains(
									albumTitle))
							&& (songTitle == null || s.getTitle().contains(
									songTitle))
							)
						matches.add(s);
				}
			}

		// Sort songs:
		List<Song> list = new ArrayList<>(matches);
		Comparator<Song> c = new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				int c1 = o1.getArtist().compareTo(o2.getArtist());
				int c2 = o1.getAlbum().compareTo(o2.getAlbum());
				int c3 = o1.getTitle().compareTo(o2.getTitle());
				if (c1 == 0)
					if (c2 == 0)
						return c3;
					else
						return c2;
				else
					return c1;
			}
		};
		Collections.sort(list, c);
		return list;
	}

	@Override
	public Statistics getStatistics() {
		return new Statistics(songCount);
	}
}
