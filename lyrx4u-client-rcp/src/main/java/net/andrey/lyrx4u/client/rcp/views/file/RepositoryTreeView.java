package net.andrey.lyrx4u.client.rcp.views.file;

import javax.annotation.PostConstruct;

import net.andrey.lyrx4u.client.rcp.views.AbstractTreeView;

import org.eclipse.swt.widgets.Composite;

public class RepositoryTreeView extends AbstractTreeView {

	@PostConstruct
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getViewer().setContentProvider(new TreeModelProvider());
		getViewer().setLabelProvider(new TreeViewLabelProvider());
		// provide the input to the ContentProvider
		// getViewer().setInput(new TodoMockModel());

	}
}