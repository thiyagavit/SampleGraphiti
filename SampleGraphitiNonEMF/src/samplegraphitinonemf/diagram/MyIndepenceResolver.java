package samplegraphitinonemf.diagram;

import java.util.HashMap;
import java.util.Map;

import model.generated.PersonType;
import model.service.HierarchyService;

import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.impl.IIndependenceSolver;

public class MyIndepenceResolver implements IIndependenceSolver {

	private static Map<String, Object> objectMap = new HashMap<String, Object>();
	
	private String diagramKey;
	
	public String getDiagramKey() {
		return diagramKey;
	}

	public void setDiagramKey(String diagramKey) {
		this.diagramKey = diagramKey;
	}

	@Override
	public String getKeyForBusinessObject(Object bo) {
		String result = null;
		if(bo != null) {
			
			if(bo instanceof PersonType) {
				//For now person will have unique name assumed :)
				result = ((PersonType) bo).getName();
				
				Assert.isNotNull(this.diagramKey);
				HierarchyService.getInstance().getPerson(getDiagramKey(), result);
				return result;
			} 
			
			if(result == null) {
				result = String.valueOf(bo.hashCode());	
			}
			
			if(!objectMap.containsKey(result)) {
				objectMap.put(result, bo);
			}				
		}
		return result;
	}

	@Override
	public Object getBusinessObjectForKey(String key) {
		Assert.isNotNull(this.diagramKey);
		Object obj = HierarchyService.getInstance().getPerson(getDiagramKey(), key);
		
		if(obj == null) {
			obj = objectMap.get(key);
		}
		return obj;
	}

	
}
