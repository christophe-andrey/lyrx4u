/**
 * 
 */
package net.andrey.lyrx4u.file;

import java.nio.file.Path;

/**
 * @author admin
 * 
 */
public abstract class AbstractFile implements Comparable<AbstractFile> {
	public AbstractFile(Path file) {
		this.file = file;
	}

	protected final Path file;

	@Override
	public int compareTo(AbstractFile o) {
		return getFileName().compareTo(o.getFileName());
	}

	public final String getFileName() {
		return file.getFileName().toString();
	}

	@Override
	public String toString() {
		return getFileName();
	}
}
