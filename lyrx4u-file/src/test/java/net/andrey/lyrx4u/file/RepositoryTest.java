package net.andrey.lyrx4u.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDirRepo() throws URISyntaxException, CannotReadException,
			IOException, TagException, ReadOnlyFileException,
			InvalidAudioFrameException {
		Repository repo = createDirRepo();
		Directory root = (Directory) repo.getRoot();
		Assert.assertNotNull(root);
		Assert.assertTrue(root.getSongFiles().isEmpty());
		Assert.assertEquals(1, root.getSubdirs().size());
		Directory subdir = root.getSubdirs().get(0);
		Assert.assertTrue(subdir.getSubdirs().isEmpty());
		List<SongFile> songs = subdir.getSongFiles();
		Assert.assertEquals(4, songs.size());
		for (SongFile s : songs) {
			String lyrics = s.readLyrics();
			Assert.assertEquals(!s.getFileName().contains("dummy lyrics"),
					lyrics == null);
			SongProperties props = s.readSongProperties();
			Assert.assertNotNull(props);
		}

	}

	@Test
	public void testFileRepo() throws URISyntaxException, CannotReadException,
			IOException, TagException, ReadOnlyFileException,
			InvalidAudioFrameException {
		Repository repo = createFileRepo();
		SongFile song = (SongFile) repo.getRoot();
		Assert.assertNotNull(song);
		String lyrics = song.readLyrics();
		Assert.assertEquals("hello", lyrics);
		SongProperties props = song.readSongProperties();
		Assert.assertTrue(props.album.length() > 0);
		Assert.assertNull(props.amazonId);
		Assert.assertTrue(props.artist.length() > 0);
		Assert.assertTrue(props.discoGsReleaseId.length() > 0);
		Assert.assertTrue(props.lyrics.length() > 0);
		Assert.assertTrue(props.musicBrainzId.length() > 0);
		Assert.assertTrue(props.title.length() > 0);
		Assert.assertEquals("hello", props.lyrics);
	}

	@Test
	public void testSaveLyrics() throws URISyntaxException,
			CannotReadException, IOException, TagException,
			ReadOnlyFileException, InvalidAudioFrameException,
			CannotWriteException {
		// TODO: Copy dummy file, read it and write its lyrics. Then delete it.
		Repository repo = createRepo("/some_subdir/Dummy.mp3");
		Path songFile = repo.getRoot().file;
		Path testFile = Paths.get(songFile.getParent().toString(),
				"deleteme.mp3");
		try {
			Files.copy(songFile, testFile);
			repo = createRepo("/some_subdir/deleteme.mp3");
			SongFile song = (SongFile) repo.getRoot();
			Assert.assertNotNull(song);
			String lyrics = song.readLyrics();
			Assert.assertNull(lyrics);
			song.saveLyrics("bingo");
			lyrics = song.readLyrics();
			Assert.assertEquals("bingo", lyrics);
		} finally {
			Files.deleteIfExists(testFile);
		}
	}

	private Repository createDirRepo() throws URISyntaxException,
			FileNotFoundException {
		return createRepo("");

	}

	private Repository createFileRepo() throws URISyntaxException,
			FileNotFoundException {
		return createRepo("/some_subdir/Best Coast - The Only Place - dummy lyrics musicbrainz id.mp3");
	}

	private Repository createRepo(String pathRelativeToTestPackage)
			throws URISyntaxException, FileNotFoundException {
		URL url = this.getClass().getResource(
				"/mp3" + pathRelativeToTestPackage);
		Path root = Paths.get(url.toURI());
		Repository dirRepo = new Repository(root);
		return dirRepo;
	}

}
