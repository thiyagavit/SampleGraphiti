package samplegraphitinonemf.diagram;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.swt.widgets.Display;

/**
 * Listener for BO changes in an Non-EMF model linked to a diagram.
 * 
 */
public class MyDomainModelChangeListener implements ResourceSetListener {

	private DiagramBehavior diagramBehavior;

	public MyDomainModelChangeListener(DiagramBehavior diagramBehavior) {
		setDiagramBehavior(diagramBehavior);
	}

	public NotificationFilter getFilter() {
		return NotificationFilter.NOT_TOUCH;
	}

	public boolean isAggregatePrecommitListener() {
		return false;
	}

	public boolean isPostcommitOnly() {
		return true;
	}

	public boolean isPrecommitOnly() {
		return false;
	}

	public void resourceSetChanged(ResourceSetChangeEvent event) {

		// // if there is no diagramLink, we have also no pictogramLinks -> no
		// // references to bo's -> don't handle change events
		// if (getDiagramTypeProvider() instanceof AbstractDiagramTypeProvider)
		// {
		// DiagramLink cachedDiagramLink = ((AbstractDiagramTypeProvider)
		// getDiagramTypeProvider()).getCachedDiagramLink();
		// if (cachedDiagramLink == null) {
		// return;
		// }
		// }
		// if we have no pictogramLinks -> no
		// references to bo's -> don't handle change events
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		if (diagram != null) {
			if (diagram.getPictogramLinks().size() == 0) {
				return;
			}
		}

		// Compute changed BOs.
		final Set<Object> changedBOs = new HashSet<Object>();
		List<Notification> notifications = event.getNotifications();
		for (Notification notification : notifications) {
			Object notifier = notification.getNotifier();
			changedBOs.add(notifier);
		}

		final PictogramElement[] dirtyPes = getDiagramTypeProvider().getNotificationService()
				.calculateRelatedPictogramElements(changedBOs.toArray());

		// Do nothing if no BO linked to the diagram changed.
		if (dirtyPes.length == 0) {
			return;
		}

		// Do an asynchronous update in the UI thread.
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (getDiagramTypeProvider().isAutoUpdateAtRuntime()) {
					// Bug 352109: Enable unconditional auto refresh for case 3)
					// standard refresh with saved editor
					if (getDiagramTypeProvider().isAutoUpdateAtRuntimeWhenEditorIsSaved()
							|| getDiagramTypeProvider().getDiagramBehavior().getDiagramContainer().isDirty()) {
						// The notification service takes care of not only the
						// linked BOs but also asks the diagram provider about
						// related BOs.
						getDiagramTypeProvider().getNotificationService().updatePictogramElements(dirtyPes);
					}
				} else {
					getDiagramTypeProvider().getDiagramBehavior().refresh();
				}
			}

		});

	}

	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

	private IDiagramTypeProvider getDiagramTypeProvider() {
		return getDiagramBehavior().getDiagramTypeProvider();
	}

	private DiagramBehavior getDiagramBehavior() {
		return diagramBehavior;
	}

	private void setDiagramBehavior(DiagramBehavior diagramBehavior) {
		this.diagramBehavior = diagramBehavior;
	}
}
