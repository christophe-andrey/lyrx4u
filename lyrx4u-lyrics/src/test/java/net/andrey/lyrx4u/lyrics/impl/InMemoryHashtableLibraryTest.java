package net.andrey.lyrx4u.lyrics.impl;

import net.andrey.lyrx4u.lyrics.facade.AbstractLibraryTest;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;

public final  class InMemoryHashtableLibraryTest extends AbstractLibraryTest {

	@Override
	protected LyricsLibrary createTestObject() {
		return new InMemoryHashtableLibrary();
	}

}
