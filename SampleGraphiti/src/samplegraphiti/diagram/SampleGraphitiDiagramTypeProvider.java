package samplegraphiti.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

public class SampleGraphitiDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public SampleGraphitiDiagramTypeProvider() {
		super();
		setFeatureProvider(new SampleGraphitiFeatureProvider(this));
	}
}
