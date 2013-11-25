package net.andrey.lyrx4u.client.rcp.views.library;

import java.util.List;

import net.andrey.lyrx4u.lyrics.domain.Album;
import net.andrey.lyrx4u.lyrics.domain.Artist;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeModelProvider implements ITreeContentProvider {

	private List<Artist> model;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.model = (List<Artist>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return model.toArray(new Object[0]);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Artist)
			return ((Artist)parentElement).getAlbi().toArray(new Object[0]);
		else if (parentElement instanceof Album)
			return ((Album)parentElement).getSongs().toArray(new Object[0]);
		else return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object e) {
		if( (e instanceof Artist) && !((Artist)e).getAlbi().isEmpty())
			return true;
		else return  (e instanceof Album) && !((Album)e).getSongs().isEmpty();
	}

}