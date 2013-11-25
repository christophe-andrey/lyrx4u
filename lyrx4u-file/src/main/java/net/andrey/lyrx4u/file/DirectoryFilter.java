package net.andrey.lyrx4u.file;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryFilter implements DirectoryStream.Filter<Path> {

	

	@Override
	public boolean accept(Path path) {
		return Files.isDirectory(path);
	}

}
