package samplegraphitinonemf.wizards;

import model.service.HierarchyService;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;

import samplegraphitinonemf.diagram.SampleGraphitiNonEMFDiagramTypeProvider;

public class SampleGraphitiWizard extends GraphitiNewWizard {

	public SampleGraphitiWizard() {
		super("SampleGraphitiNonEMF.wizard", "pdml", "SampleGraphitiNonEMFDiagramType", "SampleGraphitiNonEMFDiagram_TypeProvider",
        "samplegraphitinonemf.diagram.editor.graphiti.MyDiagramEditor");
	}


	@Override
	protected void configureDiagram(Diagram diagram, String diagramName) {
    	HierarchyService.getInstance().getOrCreateNewHierarchyDB(diagramName);
    	Graphiti.getPeService().setPropertyValue(diagram, 
    			SampleGraphitiNonEMFDiagramTypeProvider.DIAGRAM_KEY_PROPERTY, diagramName);
    }

}
