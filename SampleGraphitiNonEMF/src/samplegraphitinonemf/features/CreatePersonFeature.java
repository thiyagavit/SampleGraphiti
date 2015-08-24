package samplegraphitinonemf.features;

import model.generated.HierarchyDBType;
import model.generated.ObjectFactory;
import model.generated.PersonListType;
import model.generated.PersonType;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import samplegraphitinonemf.diagram.HierarchyRepository;

public class CreatePersonFeature extends AbstractCreateFeature {

	private static final String FEATURE_NAME = "Person";

	private static final String TITLE = "Create Person";

	private static final String USER_QUESTION = "Enter name of new Person";

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

		// create EClass
		// EClass newClass = EcoreFactory.eINSTANCE.createEClass();
		PersonType person = new ObjectFactory().createPersonType();
		/*
		 * // Add model element to resource. // We add the model element to the
		 * resource of the diagram for // simplicity's sake. Normally, a
		 * customer would use its own // model persistence layer for storing the
		 * business model separately.
		 * getDiagram().eResource().getContents().add(newClass);
		 */
		person.setName(personName);
		
		HierarchyDBType hierachydb = HierarchyRepository.getHierarchyDBForDiagram(getDiagram());
		PersonListType pListType = hierachydb.getPersonList();
		
		if(pListType == null) {
			pListType = new PersonListType();
			hierachydb.setPersonList(pListType);
		}
		pListType.getPerson().add(person);

/*		try {
			saveToModelFile(person, getDiagram());
		} catch (CoreException | IOException e) {
			e.printStackTrace();
		}*/

		// do the add
		addGraphicalRepresentation(context, person);

		// return newly created business object(s)
		return new Object[] { person };
	}

/*	public static void saveToModelFile(final Object obj, final Diagram d)
			throws CoreException, IOException {
		URI uri = d.eResource().getURI();
		uri = uri.trimFragment();
		uri = uri.trimFileExtension();
		uri = uri.appendFileExtension("model");
		ResourceSet rSet = d.eResource().getResourceSet();
		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace()
				.getRoot();
		IResource file = workspaceRoot.findMember(uri.toPlatformString(true));

		if (file == null || !file.exists()) {
			Resource createResource = rSet.createResource(uri);
			createResource.save(new HashMap<String, Object>());
			createResource.setTrackingModification(true);
		}
		final Resource resource = rSet.getResource(uri, true);
		resource.getContents().add(obj);

	}*/

}
