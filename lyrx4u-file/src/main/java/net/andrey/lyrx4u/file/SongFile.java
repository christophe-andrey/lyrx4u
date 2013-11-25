package net.andrey.lyrx4u.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.StringTokenizer;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public final class SongFile extends AbstractFile {

	SongFile(Path file) {
		super(file);
	}

	/**
	 * Naive implementation based on the file name extension
	 * 
	 * @param rootFile
	 * @return
	 */
	public static boolean isSongFile(Path file) {
		return new Mp3FileNameFilter().accept(file);
	}

	public String readLyrics() throws CannotReadException, IOException,
			TagException, ReadOnlyFileException, InvalidAudioFrameException {
		return readSongProperties().lyrics;
	}

	public SongProperties readSongProperties() throws CannotReadException,
			IOException, TagException, ReadOnlyFileException,
			InvalidAudioFrameException {
		SongProperties p = new SongProperties();
		AudioFile f = AudioFileIO.read(file.toFile());
		Tag tag = f.getTag();

		p.artist = getValue(tag, FieldKey.ARTIST);
		p.album = getValue(tag, FieldKey.ALBUM);
		p.title = getValue(tag, FieldKey.TITLE);
		String track = getValue(tag, FieldKey.TRACK);
		if (track != null)
			p.track = new Integer(track);
		p.lyrics = getValue(tag, FieldKey.LYRICS);
		p.musicBrainzId = getValue(tag, FieldKey.MUSICBRAINZ_RELEASEID);
		p.amazonId = getValue(tag, FieldKey.AMAZON_ID);
		p.discoGsReleaseId = getValue(tag, FieldKey.URL_DISCOGS_RELEASE_SITE);
		if (p.discoGsReleaseId != null)
			p.discoGsReleaseId = discoGsUrlToId(p.discoGsReleaseId);
		return p;
	}

	private static String getValue(Tag tag, FieldKey key) {
		String s = tag.getFirst(key);
		return s.length() == 0 ? null : s;
	}

	public void saveLyrics(String newLyrics) throws CannotReadException,
			IOException, TagException, ReadOnlyFileException,
			InvalidAudioFrameException, CannotWriteException {
		AudioFile f = AudioFileIO.read(file.toFile());
		Tag tag = f.getTag();
		tag.setField(FieldKey.LYRICS, newLyrics);
		f.commit();
	}

	/**
	 * Extracts the DiscoGS Release ID from its URL.
	 * 
	 * @param url
	 *            For example http://www.discogs.com/release/3552057
	 * @return For example 3552057
	 */
	private static String discoGsUrlToId(String url) {
		StringTokenizer st = new StringTokenizer(url);
		String lastToken = null;
		while (st.hasMoreTokens())
			lastToken = st.nextToken();
		return lastToken;
	}

}
