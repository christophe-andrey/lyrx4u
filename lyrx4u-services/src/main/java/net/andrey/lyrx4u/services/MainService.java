package net.andrey.lyrx4u.services;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

import net.andrey.lyrx4u.file.Repository;
import net.andrey.lyrx4u.lyrics.domain.Artist;
import net.andrey.lyrx4u.lyrics.domain.Statistics;

public interface MainService {

	/**
	 * Warning: This method loads all lyrics. That may be huge. <br>
	 * TODO: Make it lazy!
	 * @param artist optional
	 * @param album optional
	 * @param title optional
	 * @return Matches indexed by artist, album and title (in this order). 
	 */
	List<Artist> searchSongs(String artist, String albumTitle, String songTitle);
	
	Repository openRepository(Path rootFile) throws FileNotFoundException;
	
	Statistics getLibraryStatistics();
	
	JobProgress startImportingLyricsFromRepoToLibrary();
	JobProgress startUpdatingRepoFromExternalSource();
	
}
