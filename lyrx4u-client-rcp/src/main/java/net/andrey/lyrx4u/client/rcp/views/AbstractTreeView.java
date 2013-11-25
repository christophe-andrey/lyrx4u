package net.andrey.lyrx4u.client.rcp.views;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractTreeView {

	//  public static final String ID = "de.vogella.jface.treeviewer.view";
	  private TreeViewer viewer;

	@Focus
	  public void setFocus() {
	    viewer.getControl().setFocus();
	  }

	protected final TreeViewer getViewer() {
		return viewer;
	}

	  protected void createPartControl(Composite parent) {
	    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	    
	
	    // add a doubleclicklistener
	    viewer.addDoubleClickListener(new IDoubleClickListener() {
	
	      @Override
	      public void doubleClick(DoubleClickEvent event) {
	        TreeViewer viewer = (TreeViewer) event.getViewer();
	        IStructuredSelection thisSelection = (IStructuredSelection) event
	            .getSelection();
	        Object selectedNode = thisSelection.getFirstElement();
	        viewer.setExpandedState(selectedNode,
	            !viewer.getExpandedState(selectedNode));
	      }
	    });
	
	   
	
	  }

}
