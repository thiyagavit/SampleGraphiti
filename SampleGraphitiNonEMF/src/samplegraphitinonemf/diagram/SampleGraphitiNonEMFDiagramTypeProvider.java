package samplegraphitinonemf.diagram;

import model.generated.HierarchyDBType;
import model.generated.ObjectFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramBehavior;

public class SampleGraphitiNonEMFDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public final static String ID = "SampleGraphitiNonEMF.SampleGraphitiNonEMFDiagramTypeProvider";
	
	private MyIndepenceResolver independenceSolver;
	
	public MyIndepenceResolver getIndependenceSolver() {
		return independenceSolver;
	}

	public SampleGraphitiNonEMFDiagramTypeProvider() {
		super();
		independenceSolver = new MyIndepenceResolver();
		setFeatureProvider(new SampleGraphitiNonEMFFeatureProvider(this));		
	}
	
	  @Override
	  public void init(Diagram diagram, IDiagramBehavior diagramBehavior) {
	    super.init(diagram, diagramBehavior);
	    HierarchyDBType hierarchyDb = HierarchyRepository.getHierarchyDBForDiagram(diagram);

	    if (hierarchyDb == null && diagramBehavior != null) {
	      try {
	        IFile dei = ((MyDiagramBehavior)diagramBehavior).getDiagramFile();
	        hierarchyDb = new ObjectFactory().createHierarchyDBType();
	        HierarchyRepository.registerDiagramAndHierarchyDB(diagram, hierarchyDb);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	    independenceSolver.setTopLevel(hierarchyDb);
	  }
	  
	  @Override
	  public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
	    return true;
	  }
}
