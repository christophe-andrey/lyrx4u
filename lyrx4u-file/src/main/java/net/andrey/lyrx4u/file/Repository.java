package net.andrey.lyrx4u.file;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Repository {
	private final AbstractFile root;

	public Repository(Path rootFile) throws FileNotFoundException {
		if (!Files.exists(rootFile))
			throw new FileNotFoundException();
		else if (Files.isDirectory(rootFile))
			root = new Directory(rootFile);
		else if (Files.isRegularFile(rootFile) && SongFile.isSongFile(rootFile))
			root = new SongFile(rootFile);
		else
			root = null;
	}

	/**
	 * @return A Directory instance if the root of this repository is a
	 *         directory, a SongFile instance if it is a song, null otherwise
	 *         (if the root is no file or no music file).
	 */
	public final AbstractFile getRoot() {
		return root;
	}

}
