package net.andrey.lyrx4u.services.impl;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import net.andrey.lyrx4u.file.Repository;
import net.andrey.lyrx4u.lyrics.domain.Statistics;
import net.andrey.lyrx4u.services.JobProgress;
import net.andrey.lyrx4u.services.MainService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainServiceImplTest {
	private MainService service;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new GuiceModule());
		service = injector.getInstance(MainService.class);
	}

	@Test
	public void testSearchSongs() {
		service.searchSongs(null, null, null);
	}

	@Test
	public void testOpenRepository() throws URISyntaxException,
			FileNotFoundException {
		Repository repo = service.openRepository(Paths.get(getClass()
				.getResource("/mp3").toURI()));
		Assert.assertNotNull(repo);
	}

	@Test
	public void testGetLibraryStatistics() {
		Statistics s = service.getLibraryStatistics();
		Assert.assertNotNull(s);
		Assert.assertEquals(0, s.getSongCount());
	}

	@Test
	public void testStartImportingLyricsFromRepoToLibrary() {
		JobProgress j = service.startImportingLyricsFromRepoToLibrary();
		Assert.assertNotNull(j);
	}

	@Test
	public void testStartUpdatingRepoFromExternalSource() {
		JobProgress j = service.startUpdatingRepoFromExternalSource();
		Assert.assertNotNull(j);
	}

}
