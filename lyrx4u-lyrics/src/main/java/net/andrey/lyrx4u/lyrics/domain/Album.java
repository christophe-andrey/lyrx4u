package net.andrey.lyrx4u.lyrics.domain;

import java.util.List;

public class Album implements Comparable<Album>{
	public Album(List<Song> songs) {
		this.songs = songs;
	}

	private final List<Song> songs;

	public final List<Song> getSongs() {
		return songs;
	}
	
	public String getTitle(){
		return songs.get(0).getAlbum();
	}

	@Override
	public int compareTo(Album o) {
		return getTitle().compareTo(o.getTitle());
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
