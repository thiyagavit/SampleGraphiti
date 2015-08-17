package samplegraphiti.features.connections;

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
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import sample.Hierarchy;
import sample.Person;
import sample.SamplePackage;

public class CreateHierarchyConnectionFeature extends AbstractCreateConnectionFeature {

	public CreateHierarchyConnectionFeature(IFeatureProvider fp) {
		super(fp, "Hierarchy", "Create Reporting Hierarchy");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Object srcObject = getBusinessObjectForPictogramElement(
				context.getSourcePictogramElement());
		Object targetObject = getBusinessObjectForPictogramElement(
				context.getTargetPictogramElement());
		return srcObject instanceof Person 
				&& targetObject instanceof Person && srcObject != targetObject;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Person srcObject = (Person) getBusinessObjectForPictogramElement(
				context.getSourcePictogramElement());
		Person targetObject = (Person) getBusinessObjectForPictogramElement(
				context.getTargetPictogramElement());
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                context.getTargetAnchor());
		Hierarchy hierarchy = SamplePackage.eINSTANCE.getSampleFactory().createHierarchy();
		hierarchy.setManager(srcObject);
		hierarchy.eResource();
		hierarchy.setReportee(targetObject);
        addContext.setNewObject(hierarchy);
		
        try {
			saveToModelFile(hierarchy, getDiagram());
		} catch (CoreException | IOException e) {
			e.printStackTrace();
		}
        return (Connection) getFeatureProvider().addIfPossible(addContext);
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

	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Object srcObject = getBusinessObjectForPictogramElement(
				context.getSourceAnchor().getParent());
		return srcObject instanceof Person;
	}

}
