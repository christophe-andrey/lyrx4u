package net.andrey.lyrx4u.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class FileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Path p = Paths.get("Users", "admin", "Downloads");
		System.out.println(p.isAbsolute());
		System.out.println(p.getFileName().toString());
		System.out.println(p.getFileSystem());
		System.out.println(p.getName(0).toString());


	}

}
