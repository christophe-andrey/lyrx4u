package net.andrey.lyrx4u.client.rcp.views.file;

import java.util.ArrayList;
import java.util.List;

import net.andrey.lyrx4u.file.AbstractFile;
import net.andrey.lyrx4u.file.Directory;
import net.andrey.lyrx4u.file.Repository;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeModelProvider implements ITreeContentProvider {

	private Repository model;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.model = (Repository) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return new Object[]{model.getRoot()};
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Directory node = (Directory) parentElement;
		List<AbstractFile> children = new ArrayList<AbstractFile>();
		children.addAll(node.getSubdirs());
		children.addAll(node.getSongFiles());
		return children.toArray(new Object[0]);
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		AbstractFile node = (AbstractFile) element;
		return node instanceof Directory;
	}

}