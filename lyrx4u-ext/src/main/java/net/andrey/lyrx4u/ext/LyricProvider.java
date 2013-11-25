package net.andrey.lyrx4u.ext;

public interface LyricProvider {
	/**
	 * @param artist
	 * @param songTitle
	 * @return Lyric. Null if not found
	 */
	String getLyric(String artist, String songTitle);
}
