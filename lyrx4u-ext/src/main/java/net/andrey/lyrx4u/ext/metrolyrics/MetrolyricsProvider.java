package net.andrey.lyrx4u.ext.metrolyrics;

import net.andrey.lyrx4u.ext.LyricProvider;

public class MetrolyricsProvider implements LyricProvider {

	@Override
	public String getLyric(String artist, String songTitle) {
		ArtistSongRequest req1 = new ArtistSongRequest(artist, songTitle);
		req1.execute();
		if (req1.getLyricId() != null) {
			LyricDetailsRequest req2 = new LyricDetailsRequest(artist,
					songTitle, req1.getLyricId());
			req2.execute();
			return req2.getLyric();
		} else
			return null;
	}

	/**
	 * Test program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LyricProvider prov = new MetrolyricsProvider();
		String lyric = prov.getLyric("Usher", "Burn");
		System.out.println(lyric);
	}

}
