package samplegraphitinonemf.properties;

import org.eclipse.graphiti.internal.ExternalPictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

@SuppressWarnings("restriction")
public class PersonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		String propValue = Graphiti.getPeService().getPropertyValue(pe,  ExternalPictogramLink.KEY_INDEPENDENT_PROPERTY);
		return propValue != null;
	}



}
