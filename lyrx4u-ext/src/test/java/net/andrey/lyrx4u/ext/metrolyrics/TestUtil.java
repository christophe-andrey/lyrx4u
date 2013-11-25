package net.andrey.lyrx4u.ext.metrolyrics;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class TestUtil {

	public static String readFile(String path, Class<?> clazz) throws IOException {
		StringWriter sw = new StringWriter();
		IOUtils.copy(clazz.getResourceAsStream(path), sw);
		String json = sw.toString();
		return json;
	}

}
