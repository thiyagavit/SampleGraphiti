package samplegraphitinonemf.diagram;

import model.generated.HierarchyDBType;

import org.eclipse.core.resources.IFile;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DefaultPersistencyBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;

public class MyDiagramBehavior extends DiagramBehavior {
  private HierarchyDBType hierarchyDB;
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

  /**
   * @return the flow that is open in this editor instance; Can be null if no Flow is opened yet somehow.
   */
  public HierarchyDBType getHierarchyDB() {
    if (hierarchyDB == null) {
      try {
        Diagram diagram = getDiagramTypeProvider().getDiagram();
        hierarchyDB = HierarchyRepository.getHierarchyDBForDiagram(diagram);
      } catch (NullPointerException e) {
        // ignore, means somehow the flow is not linked yet
      }
    }
    return hierarchyDB;
  }
  
  public IFile getDiagramFile() {
    IDiagramEditorInput input = getInput();
    if(input instanceof DiagramEditorInput) {
      return (IFile) ((DiagramEditorInput)input).getAdapter(IFile.class);
    } else {
      return null;
    }
  }

}
