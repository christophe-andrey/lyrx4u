package net.andrey.lyrx4u.lyrics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SONG_IDENTIFICATION")
public class SongId {
	public SongId() {
	}

	public SongId(IdKind kind, String id, Song song) {
		this.id = id;
		this.kind = kind;
		this.song = song;
	}

	@Column(name = "ID_VALUE", nullable = false, length=60)
	private String id;
	
	@Column(name = "KIND", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private IdKind kind;
	
	@Id
	@Column(name="SONG_IDENTIFICATION_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long entityId;
	
	@ManyToOne
	@JoinColumn(name = "SONG_ID", nullable = false, referencedColumnName="SONG_ID")
	private Song song;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final IdKind getKind() {
		return kind;
	}

	public final void setKind(IdKind kind) {
		this.kind = kind;
	}

	public final Song getSong() {
		return song;
	}

	public final void setSong(Song song) {
		this.song = song;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
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
		SongId other = (SongId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kind != other.kind)
			return false;
		return true;
	}

}
