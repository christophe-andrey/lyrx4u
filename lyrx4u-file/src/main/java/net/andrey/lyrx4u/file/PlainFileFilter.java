package net.andrey.lyrx4u.file;

import java.io.File;
import java.io.FileFilter;

public class PlainFileFilter implements FileFilter {

	

	@Override
	public boolean accept(File pathname) {
		return pathname.isFile();
	}

}
