/* Copyright 2013 - iSencia Belgium NV

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package samplegraphitinonemf.diagram;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.graphiti.features.impl.IIndependenceSolver;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.ui.part.IPageSite;

public class MyDiagramEditor extends DiagramEditor {

	public final static String EDITOR_ID = "samplegraphitinonemf.diagram.editor.graphiti.MyDiagramEditor";

	public MyDiagramEditor() {
		super();

	}

	@Override
	public DefaultEditDomain getEditDomain() {
		DefaultEditDomain editDomain = super.getEditDomain();

		if (editDomain == null) {
			setEditDomain(new DefaultEditDomain(this));
		}
		return super.getEditDomain();
	}

	@Override
	protected DiagramBehavior createDiagramBehavior() {
		return new MyDiagramBehavior(this);
	}

	@Override
	public String getContributorId() {
		return "SampleGraphitiNonEMFDiagramType.PropertyContributor";	
	}

	public IIndependenceSolver getIndependenceSolver() {
		return ((SampleGraphitiNonEMFDiagramTypeProvider) getDiagramTypeProvider())
				.getIndependenceSolver();
	}

	protected CustomPalettePage createPalettePage() {
		return new CustomPalettePage(getPaletteViewerProvider()) {
			public void init(IPageSite pageSite) {
			}
		};
	}

}
