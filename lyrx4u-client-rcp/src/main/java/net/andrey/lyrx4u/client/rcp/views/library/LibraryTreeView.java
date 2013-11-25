package net.andrey.lyrx4u.client.rcp.views.library;

import javax.annotation.PostConstruct;

import net.andrey.lyrx4u.client.rcp.views.AbstractTreeView;

import org.eclipse.swt.widgets.Composite;

public class LibraryTreeView extends AbstractTreeView {

	@PostConstruct
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getViewer().setContentProvider(new TreeModelProvider());
		getViewer().setLabelProvider(new TreeViewLabelProvider());
		// provide the input to the ContentProvider
		// getViewer().setInput(new TodoMockModel());

	}
}