package samplegraphitinonemf.features;

import model.generated.PersonType;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.internal.ExternalPictogramLink;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

@SuppressWarnings("restriction")
public class UpdatePersonFeature extends AbstractUpdateFeature {

	public UpdatePersonFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
        // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof PersonType);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
        // retrieve name from pictogram model
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    pictogramName = text.getValue();
                }
            }
        }
 
        // retrieve name from business model
        String businessName = null;
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof PersonType) {
        	PersonType eClass = (PersonType) bo;
            businessName = eClass.getName();
        }
 
        // update needed, if names are different
        boolean updateNameNeeded =
            ((pictogramName == null && businessName != null) || 
                (pictogramName != null && !pictogramName.equals(businessName)));
        if (updateNameNeeded) {
            return Reason.createTrueReason("Name is out of date");
        } else {
            return Reason.createFalseReason();
        }
    }

	@Override
	public boolean update(IUpdateContext context) {
		boolean result = false;
        // retrieve name from business model
        String businessName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof PersonType) {
        	PersonType eClass = (PersonType) bo;
            businessName = eClass.getName();
        }

        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            updateIndependentpropertyForPerson(cs, businessName);
            
            for (Shape shape : cs.getChildren()) {
            	updateIndependentpropertyForPerson(shape, businessName);
            	
                if (shape.getGraphicsAlgorithm() instanceof Text) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    result = true;
                }
            }
        }
 
        return result;
    }

	
	private boolean updateIndependentpropertyForPerson(Shape shape, String newValue) {
		boolean result = false;
		String keyValue = Graphiti.getPeService().getPropertyValue(shape, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
    	
    	if(keyValue != null) {
    		Graphiti.getPeService().setPropertyValue(shape, ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY, newValue);
    		result = true;
    	}
    	return result;
	}
}
