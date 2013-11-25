package net.andrey.lyrx4u.services.impl;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.andrey.lyrx4u.ext.LyricProvider;
import net.andrey.lyrx4u.file.Repository;
import net.andrey.lyrx4u.lyrics.domain.Album;
import net.andrey.lyrx4u.lyrics.domain.Artist;
import net.andrey.lyrx4u.lyrics.domain.Song;
import net.andrey.lyrx4u.lyrics.domain.Statistics;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;
import net.andrey.lyrx4u.services.JobProgress;
import net.andrey.lyrx4u.services.MainService;

public class MainServiceImpl implements MainService {
	@Inject
	public MainServiceImpl(LyricsLibrary lib, LyricProvider prov) {
		this.lib = lib;
		this.prov = prov;
	}

	private LyricsLibrary lib;
	private LyricProvider prov;

	@Override
	public List<Artist> searchSongs(String artist, String albumTitle,
			String songTitle) {
		List<Song> songs = lib.searchSongs(artist, albumTitle, songTitle);
		Map<String, Map<String, Map<String, Song>>> map = new HashMap<String, Map<String, Map<String, Song>>>();
		for (Song s : songs) {
			Map<String, Map<String, Song>> map1 = map.get(s.getArtist());
			if (map1 == null) {
				map1 = new HashMap<String, Map<String, Song>>();
				map.put(s.getArtist(), map1);
			}
			Map<String, Song> map2 = map1.get(s.getAlbum());
			if (map2 == null) {
				map2 = new HashMap<String, Song>();
				map1.put(s.getAlbum(), map2);
			}
			if (!map2.containsKey(s.getTitle()))
				map2.put(s.getTitle(), s);
		}

		return getArtists(map);
	}

	private Artist getArtist(Map<String, Map<String, Song>> map) {
		List<Album> albi = new ArrayList<>();
		for (Map<String, Song> m : map.values())
			albi.add(getAlbum(m));
		Collections.sort(albi);
		return new Artist(albi);
	}

	private List<Artist> getArtists(
			Map<String, Map<String, Map<String, Song>>> map) {
		List<Artist> artists = new ArrayList<Artist>();

		for (Map<String, Map<String, Song>> m : map.values())
			artists.add(getArtist(m));
		Collections.sort(artists);
		return artists;
	}

	private Album getAlbum(Map<String, Song> map) {
		List<Song> songs = new ArrayList<>(map.values());
		Collections.sort(songs);
		return new Album(songs);

	}

	@Override
	public Repository openRepository(Path rootFile)
			throws FileNotFoundException {
		return new Repository(rootFile);
	}

	@Override
	public Statistics getLibraryStatistics() {
		return lib.getStatistics();
	}

	@Override
	public JobProgress startImportingLyricsFromRepoToLibrary() {
		// TODO implement me
		return new JobProgress();
	}

	@Override
	public JobProgress startUpdatingRepoFromExternalSource() {
		// TODO implement me
		return new JobProgress();
	}

}
