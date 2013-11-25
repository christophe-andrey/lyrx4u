package net.andrey.lyrx4u.lyrics.facade;

import java.util.Arrays;
import java.util.List;

import net.andrey.lyrx4u.lyrics.domain.IdKind;
import net.andrey.lyrx4u.lyrics.domain.Song;
import net.andrey.lyrx4u.lyrics.domain.SongId;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractLibraryTest {
	private LyricsLibrary lib;

	@Before
	public void setUp() throws Exception {
		lib = createTestObject();
	}

	protected abstract LyricsLibrary createTestObject();

	@Test
	public void testAdd() {
		Song s = new Song("John Doe", "His 1st album", "Doeing it", "hello", null);
		SongId id1 = new SongId(IdKind.AMAZON, "a1", s);
		SongId id2 = new SongId(IdKind.DISCOGS, "a2", s);
		SongId id3 = new SongId(IdKind.MUSICBRAINZ, "a3", s);
		s.setIds(Arrays.asList(id1, id2, id3));

		Assert.assertNull(lib.getSong(id1.getKind(), id1.getId()));
		lib.addSong(Arrays.asList(id1, id2, id3));
		Assert.assertNotNull(lib.getSong(id1.getKind(), id1.getId()));
	}

	@Test
	public void testSearch() {
		// Setup:
		Song s1 = new Song("John Doe", "His 1st album", "Doeing it", "hello", null);
		Song s2 = new Song("John One", "His 2nd album", "ReDoeing it", "hello", null);
		Song s3 = new Song("John One", "His 2nd album", "UnDoeing it", "hello", null);
		List<Song> all = Arrays.asList(s1, s2, s3);
		int i = 0;
		for (Song s : all) {
			SongId id1 = new SongId(IdKind.AMAZON, "a" + i, s);
			SongId id2 = new SongId(IdKind.DISCOGS, "d" + i, s);
			SongId id3 = new SongId(IdKind.MUSICBRAINZ, "m" + i, s);
			s.setIds(Arrays.asList(id1, id2, id3));
			lib.addSong(Arrays.asList(id1, id2, id3));
			i++;
		}
		
		// TODO: Enable me again!
//		Assert.assertEquals(3, lib.getStatistics().getSongCount());
//
//		// Search:
//		List<Song> matches = lib.searchSongs(null, null, null);
//		Assert.assertEquals(3, matches.size());
//		matches = lib.searchSongs("Do", null, null);
//		Assert.assertEquals(1, matches.size());
//		matches = lib.searchSongs(null, "2nd", null);
//		Assert.assertEquals(2, matches.size());
//		matches = lib.searchSongs(null, null, "it");
//		Assert.assertEquals(3, matches.size());
//		matches = lib.searchSongs("Do", "album", "x");
//		Assert.assertEquals(0, matches.size());

	}

}
