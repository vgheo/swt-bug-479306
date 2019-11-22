package viewercomparatorissue.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class SampleView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "viewercomparatorissue.views.SampleView";
	
	public ComboViewer myViewer;
	private IObservableList myChoices = new WritableList();
	private List<String> myItems = new ArrayList<String>();
	
	/**
	 * The constructor.
	 */
	public SampleView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMycombo = new Label(composite, SWT.NONE);
		lblMycombo.setText("MyCombo");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		createItems();
		myViewer = new ComboViewer(combo);
		myViewer.setContentProvider(new ObservableListContentProvider());
		myViewer.setLabelProvider(new LabelProvider());
		myViewer.getCombo().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
					updateValues();
			}
		});
		myViewer.setInput(myChoices);

		myViewer.setComparator(new ViewerComparator());
		updateValues();
	}
	
	private void updateValues(){
		myChoices.clear();
		Iterables.addAll(myChoices,myItems);
	}

	private List<String> createItems() {
			
			myItems.add( "sand");
			myItems.add( "band");
			myItems.add( "sbnd");
			myItems.add( "aand");
		return myItems;
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
