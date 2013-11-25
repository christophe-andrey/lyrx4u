package net.andrey.lyrx4u.file;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class Mp3FileNameFilter implements  DirectoryStream.Filter<Path> {



	@Override
	public boolean accept(Path path) {
		return path.getFileName().toString().toLowerCase().endsWith(".mp3");
	}

}
