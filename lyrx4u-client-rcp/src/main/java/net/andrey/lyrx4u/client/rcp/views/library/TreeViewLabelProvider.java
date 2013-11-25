package net.andrey.lyrx4u.client.rcp.views.library;

import net.andrey.lyrx4u.lyrics.domain.Album;
import net.andrey.lyrx4u.lyrics.domain.Artist;
import net.andrey.lyrx4u.lyrics.domain.Song;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TreeViewLabelProvider extends LabelProvider {

	private static final Image ALBUM = net.andrey.lyrx4u.client.rcp.views.file.TreeViewLabelProvider
			.getImage("album.gif");
	private static final Image ARTIST = net.andrey.lyrx4u.client.rcp.views.file.TreeViewLabelProvider
			.getImage("artist.gif");
	private static final Image SONG = net.andrey.lyrx4u.client.rcp.views.file.TreeViewLabelProvider
			.getImage("song.gif");

	@Override
	public String getText(Object element) {
		return element.toString();
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Artist) {
			return ARTIST;
		} else if (element instanceof Album) {
			return ALBUM;
		}
		if (element instanceof Song) {
			return SONG;
		} else
			return null;
	}
}
