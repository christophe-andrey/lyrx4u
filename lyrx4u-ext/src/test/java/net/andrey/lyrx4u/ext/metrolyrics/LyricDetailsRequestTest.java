package net.andrey.lyrx4u.ext.metrolyrics;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LyricDetailsRequestTest {

	private LyricDetailsRequest req;

	@Before
	public void setUp() throws Exception {
		req = new LyricDetailsRequest("Pearl Jam", "Just Breathe", 2108725950);
	}

	@Test
	public void testProcessResponseSuccess() throws IOException {
		String json = TestUtil.readFile("/metrolyrics/lyricdetails/answer.json", getClass());
		String lyric = TestUtil.readFile("/metrolyrics/lyricdetails/lyric.txt", getClass());
		req.processResponse(json);
		String computed = req.getLyric();
		Assert.assertEquals(lyric, computed);
	}

	@Test
	public void testProcessResponseFailure() throws IOException {
		String json = TestUtil.readFile("/metrolyrics/lyricdetails/answer_neg.json", getClass());
		req.processResponse(json);
		Assert.assertNull(req.getLyric());
	}

	@Test
	public void testProcessResponseFailure2() throws IOException {
		String json = TestUtil.readFile("/metrolyrics/lyricdetails/answer_invalid.json", getClass());
		req.processResponse(json);
		Assert.assertNull(req.getLyric());
	}

	@Test
	public void testProcessResponseFailure3() throws IOException {
		String json = TestUtil.readFile("/metrolyrics/lyricdetails/answer_missing_api_key.json", getClass());
		req.processResponse(json);
		Assert.assertNull(req.getLyric());
	}

	@Test
	public void testGetUrl() {
		URL url = req.getURL();
		Assert.assertEquals(
				"http://api.metrolyrics.com/v1/get/lyricdetails/artist/Pearl%20Jam/title/Just%20Breathe/X-API-KEY/1234567890123456789012345678901234567890/lyricid/2108725950/deviceid/266facd5d2f64af6fae35681e98786eb2448ba1c",
				url.toString());

	}

}
