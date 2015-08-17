package samplegraphiti.wizards;

import org.eclipse.emf.ecore.EObject;

import sample.SamplePackage;

public class SampleGraphitiWizard extends GraphitiNewWizard {

	public SampleGraphitiWizard() {
		super("Sample Graphiti", "diagram", "model", "SampleGraphiti_DiagramType", "SampleGraphitiDiagram_TypeProvider",
        org.eclipse.graphiti.ui.editor.DiagramEditor.DIAGRAM_EDITOR_ID);
	}

	@Override
	protected EObject createModel(String name) {
		return SamplePackage.eINSTANCE.getSampleFactory().createPerson();		
	}

}
