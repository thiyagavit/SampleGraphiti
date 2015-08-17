package samplegraphiti.diagram;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import sample.Hierarchy;
import sample.Person;
import samplegraphiti.features.AddEClassFeature;
import samplegraphiti.features.LayoutDomainObjectFeature;
import samplegraphiti.features.connections.AddHierarchyConnectionFeature;
import samplegraphiti.features.connections.CreateHierarchyConnectionFeature;
import samplegraphiti.features.createFeatures.AddPersonFeature;
import samplegraphiti.features.createFeatures.CreatePersonFeature;
import samplegraphiti.features.createFeatures.UpdatePersonFeature;


public class SampleGraphitiFeatureProvider extends DefaultFeatureProvider {

	public SampleGraphitiFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		//return new ICreateFeature[] {new CreateEClassFeature(this)};
		return new ICreateFeature[] {new CreatePersonFeature(this)};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {new CreateHierarchyConnectionFeature(this)};
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		// TODO: check for right domain object instances below
		if (context.getNewObject() instanceof Person /* && context.getNewObject() instanceof <DomainObject> */) {
			return new AddPersonFeature(this);
		} else if (context.getNewObject() instanceof Hierarchy /* && context.getNewObject() instanceof <DomainObject> */) {
			return new AddHierarchyConnectionFeature(this);
		} else if (context.getNewObject() instanceof EClass /* && context.getNewObject() instanceof <DomainObject> */) {
			return new AddEClassFeature(this);
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
	   PictogramElement pictogramElement = context.getPictogramElement();
	   if (pictogramElement instanceof ContainerShape) {
	       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
	       if (bo instanceof Person) {
	           return new UpdatePersonFeature(this);
	       }
	   }
	   return super.getUpdateFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		// TODO: check for right domain object instances below
		if (context.getPictogramElement() instanceof ContainerShape /* && getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof <DomainObject> */) {
			return  new LayoutDomainObjectFeature(this);
		}
	
		return super.getLayoutFeature(context);
	}
}
