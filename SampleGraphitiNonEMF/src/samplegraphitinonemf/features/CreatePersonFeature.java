package samplegraphitinonemf.features;

import model.generated.PersonType;
import model.service.HierarchyService;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;

import samplegraphitinonemf.diagram.SampleGraphitiNonEMFDiagramTypeProvider;

public class CreatePersonFeature extends AbstractCreateFeature {

	private static final String FEATURE_NAME = "Person";

	private static final String TITLE = "Create Person";

	private static final String USER_QUESTION = "Enter name of new Person";

	private String diagramKey;
	
	public CreatePersonFeature(IFeatureProvider fp) {
		super(fp, FEATURE_NAME, "create a new person");
		
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {

		// ask user for Person name
		String personName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
		if (personName == null || personName.trim().length() == 0) {
			return EMPTY;
		}
		
		if(diagramKey == null) {
			diagramKey = Graphiti.getPeService().getPropertyValue(getDiagram(), SampleGraphitiNonEMFDiagramTypeProvider.DIAGRAM_KEY_PROPERTY);	
		}
		
		PersonType person = HierarchyService.getInstance().createPerson(diagramKey, personName);		
		
		// do the add
		addGraphicalRepresentation(context, person);

		// return newly created business object(s)
		return new Object[] { person };
	}

}
