package net.andrey.lyrx4u.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.andrey.lyrx4u.file.exception.FileException;

public class Directory extends AbstractFile {

	Directory(Path file) {
		super(file);
	}

	public List<Directory> getSubdirs()  {
		List<Directory> list = new ArrayList<>();
		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(file, new DirectoryFilter());
			for (Path f : stream) {
				list.add(new Directory(f));
			}
		} catch (IOException e) {
			throw new FileException(e);
		}
		Collections.sort(list);
		return list;

	}

	public List<SongFile> getSongFiles()  {
		List<SongFile> list = new ArrayList<>();
		try {
			for (Path f :  Files.newDirectoryStream(file, new Mp3FileNameFilter())) {
				list.add(new SongFile(f));
			}
		} catch (IOException e) {
			throw new FileException(e);
		}
		Collections.sort(list);
		return list;

	}

}
