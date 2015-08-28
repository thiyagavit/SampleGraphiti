package samplegraphitinonemf.diagram;

import model.generated.HierarchyDBType;
import model.service.HierarchyService;

import org.eclipse.core.resources.IFile;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DefaultPersistencyBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;

public class MyDiagramBehavior extends DiagramBehavior {
  //private FlowChangeListener flowChangeListener;

  public MyDiagramBehavior(IDiagramContainerUI diagramContainer) {
    super(diagramContainer);
  }

  @Override
  protected DefaultPersistencyBehavior createPersistencyBehavior() {
    return new MyPersistencyBehavior(this);
  }

  @Override
  protected void registerBusinessObjectsListener() {
/*    flowChangeListener = new FlowChangeListener(getDiagramTypeProvider());
    HierarchyDBType f = getHierarchyDB();
    if (f != null) {
      f.addChangeListener(flowChangeListener);
    }*/
  }

  @Override
  protected void unregisterBusinessObjectsListener() {
/*    Flow f = getFlow();
    if (f != null) {
      f.removeChangeListener(flowChangeListener);
    }*/
  }

/*  *//**
   * @return the flow that is open in this editor instance; Can be null if no Flow is opened yet somehow.
   *//*
  public HierarchyDBType getHierarchyDB() {
      Diagram diagram = getDiagramTypeProvider().getDiagram();
      String diagramKey = Graphiti.getPeService().getPropertyValue(diagram, SampleGraphitiNonEMFDiagramTypeProvider.DIAGRAM_KEY_PROPERTY);
	  return HierarchyService.getInstance().getOrCreateNewHierarchyDB(diagramKey);
  }*/
  
  public IFile getDiagramFile() {
    IDiagramEditorInput input = getInput();
    if(input instanceof DiagramEditorInput) {
      return (IFile) ((DiagramEditorInput)input).getAdapter(IFile.class);
    } else {
      return null;
    }
  }

}
