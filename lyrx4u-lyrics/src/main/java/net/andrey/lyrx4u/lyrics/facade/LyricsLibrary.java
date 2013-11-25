package net.andrey.lyrx4u.lyrics.facade;

import java.util.Collection;
import java.util.List;

import net.andrey.lyrx4u.lyrics.domain.IdKind;
import net.andrey.lyrx4u.lyrics.domain.Song;
import net.andrey.lyrx4u.lyrics.domain.SongId;
import net.andrey.lyrx4u.lyrics.domain.Statistics;

public interface LyricsLibrary {
	Song getSong(IdKind kind, String id);

	/**
	 * @param ids not null, not empty. All objects refer to the same Song instance
	 */
	void addSong(Collection<SongId> ids);
	
	/**
	 * @param artist Optional. Must be trimmed and non-empty
	 * @param albumTitle optional. Must be trimmed and non-empty
	 * @param songTitle optional. Must be trimmed and non-empty
	 * @return Matches. Search parameters contain by default a wildcard at their start and at their end. 
	 */
	List<Song> searchSongs(String artist, String albumTitle, String songTitle);
	
	Statistics getStatistics();
}
