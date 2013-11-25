package net.andrey.lyrx4u.client.rcp.views.file;

import java.net.URL;

import net.andrey.lyrx4u.file.AbstractFile;
import net.andrey.lyrx4u.file.Directory;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class TreeViewLabelProvider extends LabelProvider {

	private static final Image FOLDER = getImage("folder.gif");
	private static final Image FILE = getImage("file.gif");

	// Helper Method to load the images
	public static Image getImage(String file) {
		Bundle bundle = FrameworkUtil.getBundle(TreeViewLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
	}

	@Override
	public String getText(Object element) {
		AbstractFile f = (AbstractFile) element;
		return f.getFileName();
	}

	@Override
	public Image getImage(Object element) {
		AbstractFile f = (AbstractFile) element;
		if (f instanceof Directory) {
			return FOLDER;
		} else
			return FILE;
	}
}
