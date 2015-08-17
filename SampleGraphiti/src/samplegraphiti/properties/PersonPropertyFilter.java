package samplegraphiti.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import sample.Person;

public class PersonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
        EObject eObject =
                Graphiti.getLinkService()
                .getBusinessObjectForLinkedPictogramElement(pe);
        if (eObject instanceof Person) {
            return true;
        }
		return false;
	}



}
