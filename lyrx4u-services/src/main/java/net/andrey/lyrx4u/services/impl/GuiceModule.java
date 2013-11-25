package net.andrey.lyrx4u.services.impl;

import net.andrey.lyrx4u.ext.LyricProvider;
import net.andrey.lyrx4u.ext.metrolyrics.MetrolyricsProvider;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;
import net.andrey.lyrx4u.lyrics.impl.InMemoryHashtableLibrary;
import net.andrey.lyrx4u.services.MainService;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MainService.class).to(MainServiceImpl.class);
		bind(LyricsLibrary.class).to(InMemoryHashtableLibrary.class);
		bind(LyricProvider.class).to(MetrolyricsProvider.class);

	}

}
