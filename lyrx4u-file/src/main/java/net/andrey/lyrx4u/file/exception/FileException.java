package net.andrey.lyrx4u.file.exception;

import java.io.IOException;

public class FileException extends RuntimeException {
	public FileException(IOException ioe) {
		super(ioe);
	}
}
