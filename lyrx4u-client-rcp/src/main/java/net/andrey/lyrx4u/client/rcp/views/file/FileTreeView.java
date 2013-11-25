package net.andrey.lyrx4u.client.rcp.views.file;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class FileTreeView extends Composite {
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public Object[] getChildren(Object parentElement) {
			return new Object[] { "item_0", "item_1", "item_2" };
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}
	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FileTreeView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		TreeViewer treeViewer = new TreeViewer(this, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new TreeContentProvider());

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
