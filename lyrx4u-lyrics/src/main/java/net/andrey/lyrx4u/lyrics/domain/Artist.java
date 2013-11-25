package net.andrey.lyrx4u.lyrics.domain;

import java.util.List;

public class Artist implements Comparable<Artist> {
	
	public Artist(List<Album> albi) {
		this.albi = albi;
	}

	private final List<Album> albi;

	public final List<Album> getAlbi() {
		return albi;
	}
	
	public String getName(){
		return albi.get(0).getSongs().get(0).getArtist();
	}

	@Override
	public int compareTo(Artist o) {
		return getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return getName();
	}
}
