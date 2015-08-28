package samplegraphitinonemf.diagram;

import model.service.HierarchyService;

//import org.eclipse.core.resources.IFile;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.services.Graphiti;

public class SampleGraphitiNonEMFDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public final static String ID = "SampleGraphitiNonEMFDiagram_TypeProvider";

	public final static String DIAGRAM_KEY_PROPERTY = "diagramKey";
	
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
	    String dbKey = Graphiti.getPeService().getPropertyValue(diagram, SampleGraphitiNonEMFDiagramTypeProvider.DIAGRAM_KEY_PROPERTY);
	    HierarchyService.getInstance().getOrCreateNewHierarchyDB(dbKey);
	    independenceSolver.setDiagramKey(dbKey);
	    
	   /* if (hierarchyDb == null && diagramBehavior != null) {
	      try {
	        //IFile dei = ((MyDiagramBehavior)diagramBehavior).getDiagramFile();
	        hierarchyDb = new ObjectFactory().createHierarchyDBType();
	        HierarchyRepository.registerDiagramAndHierarchyDB(diagram, hierarchyDb);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }*/
	    //independenceSolver.setTopLevel(hierarchyDb);
	  }
	  
	  @Override
	  public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
	    return true;
	  }
}
