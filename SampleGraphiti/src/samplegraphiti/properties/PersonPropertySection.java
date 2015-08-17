package samplegraphiti.properties;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import sample.Person;

public class PersonPropertySection extends GFPropertySection 
									implements	ITabbedPropertyConstants {

	   private Text nameText;
	   
	    @Override
	    public void createControls(Composite parent,
	        TabbedPropertySheetPage tabbedPropertySheetPage) {
	        super.createControls(parent, tabbedPropertySheetPage);
	 
	        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
	        Composite composite = factory.createFlatFormComposite(parent);
	        FormData data;
	 
	        nameText = factory.createText(composite, "");
	        data = new FormData();
	        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
	        data.right = new FormAttachment(100, 0);
	        data.top = new FormAttachment(0, VSPACE);
	        nameText.setLayoutData(data);
	 
	        CLabel valueLabel = factory.createCLabel(composite, "Name:");
	        data = new FormData();
	        data.left = new FormAttachment(0, 0);
	        data.right = new FormAttachment(nameText, -HSPACE);
	        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
	        valueLabel.setLayoutData(data);
	        
	        nameText.addModifyListener(new ModifyListener() {

	        	@Override
	        	public void modifyText(ModifyEvent e) {
	        		String value = nameText.getText();
	        		if (value == null) {
	        			value = "";//$NON-NLS-1$
	        		}
	        		PictogramElement pe = getSelectedPictogramElement();
	        		if (pe != null) {
	        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        			// the filter assured, that it is a EClass
	        			if (bo == null)
	        				return;
	        			String name = ((Person) bo).getName();
	        			if (value.equals(name))
	        				return;
	        		}
	        		final String typedValue = value;
	        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
	        				
	        			@Override
	        			public void execute(IContext context) {
	        				PictogramElement pe = getSelectedPictogramElement();
	        				if (pe != null) {
	        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        					// the filter assured, that it is a EClass
	        					if (bo == null)
	        						return;
	        					Person eClass = (Person) bo;
	        					eClass.setName(typedValue);
	        				}
	        			}
	        			
	        			@Override
	        			public boolean canExecute(IContext context) {
	        				return true;
	        			}
	        		};
	        		CustomContext context = new CustomContext();
	        		execute(feature, context);
	        	}
	        });
	    }
	 
	    @Override
	    public void refresh() {
	        PictogramElement pe = getSelectedPictogramElement();
	        if (pe != null) {
	            Object bo = Graphiti.getLinkService()
	                 .getBusinessObjectForLinkedPictogramElement(pe);
	            // the filter assured, that it is a Person
	            if (bo == null)
	                return;
	            String name = ((Person) bo).getName();
	            nameText.setText(name == null ? "" : name);
	        }
	    }
}
