package net.andrey.lyrx4u.ext.metrolyrics;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArtistSongRequestTest {

	private ArtistSongRequest req;

	@Before
	public void setUp() throws Exception {
		req = new ArtistSongRequest("usher", "burn");
	}

	@Test
	public void testProcessResponseSuccess() throws IOException {
		String json = TestUtil.readFile("/metrolyrics/artistsong/answer.json",
				getClass());
		req.processResponse(json);
		Integer computed = req.getLyricId();
		Assert.assertEquals((Integer)207805, computed);
	}

	@Test
	public void testProcessResponseFailure() throws IOException {
		String json = TestUtil.readFile(
				"/metrolyrics/artistsong/answer_not_found.json", getClass());
		req.processResponse(json);
		Integer computed = req.getLyricId();
		Assert.assertNull(computed);
	}

	@Test
	public void testGetUrl() {
		URL url = req.getURL();
		Assert.assertEquals(
				"http://api.metrolyrics.com/v1/search/artistsong/artist/usher/song/burn/X-API-KEY/1234567890123456789012345678901234567890",
				url.toString());

	}

}
