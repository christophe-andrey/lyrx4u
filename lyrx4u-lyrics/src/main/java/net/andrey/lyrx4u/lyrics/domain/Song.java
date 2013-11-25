package net.andrey.lyrx4u.lyrics.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SONG")
public class Song implements Comparable<Song>{
	public Song(String artist, String album, String title, String lyric, List<SongId> ids) {
		this.artist = artist;
		this.album = album;
		this.title = title;
		this.lyric = lyric;
		this.ids= ids;
	}

	@Id
	@Column(name = "SONG_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue(generator="system-uuid")
	// @GenericGenerator(name="system-uuid", strategy = "uuid")
	private Long id;

	public Song() {
	}

	@Column(name = "LYRIC", nullable = false, length = 10000)
	private String lyric;
	@Column(name = "ARTIST", nullable = false)
	private String artist;
	@Column(name = "ALBUM", nullable = false)
	private String album;
	@Column(name = "TITLE", nullable = false)
	private String title;

	@OneToMany(mappedBy = "song")
	private List<SongId> ids;

	public final String getLyric() {
		return lyric;
	}

	public final void setLyric(String lyric) {
		this.lyric = lyric;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getArtist() {
		return artist;
	}

	public final void setArtist(String artist) {
		this.artist = artist;
	}

	public final String getAlbum() {
		return album;
	}

	public final void setAlbum(String album) {
		this.album = album;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final List<SongId> getIds() {
		return ids;
	}

	public final void setIds(List<SongId> ids) {
		this.ids = ids;
	}

	@Override
	public int compareTo(Song o) {
		return getTitle().compareTo(o.getTitle());
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
