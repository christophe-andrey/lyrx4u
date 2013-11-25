package net.andrey.lyrx4u.lyrics.domain;

public class Statistics {

	public Statistics(long songCount) {
		this.songCount = songCount;
	}

	private final long songCount;

	public final long getSongCount() {
		return songCount;
	}
}
