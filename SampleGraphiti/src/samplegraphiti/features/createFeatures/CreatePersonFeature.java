package samplegraphiti.features.createFeatures;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import sample.Person;
import sample.SamplePackage;

public class CreatePersonFeature extends AbstractCreateFeature {

	private static final String FEATURE_NAME = "Person";

	private static final String TITLE = "Create Person";

	private static final String USER_QUESTION = "Enter name of new Person";

	public CreatePersonFeature(IFeatureProvider fp) {
		super(fp, FEATURE_NAME, "create a new person");
		// TODO Auto-generated constructor stub
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
		Person person = SamplePackage.eINSTANCE.getSampleFactory().createPerson();
		/*
		 * // Add model element to resource. // We add the model element to the
		 * resource of the diagram for // simplicity's sake. Normally, a
		 * customer would use its own // model persistence layer for storing the
		 * business model separately.
		 * getDiagram().eResource().getContents().add(newClass);
		 */
		person.setName(personName);

		try {
			saveToModelFile(person, getDiagram());
		} catch (CoreException | IOException e) {
			e.printStackTrace();
		}

		// do the add
		addGraphicalRepresentation(context, person);

		// return newly created business object(s)
		return new Object[] { person };
	}

	public static void saveToModelFile(final EObject obj, final Diagram d)
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

	}

}
