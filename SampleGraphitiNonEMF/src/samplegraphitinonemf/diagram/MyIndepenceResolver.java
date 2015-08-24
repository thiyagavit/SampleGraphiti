package samplegraphitinonemf.diagram;

import java.util.HashMap;
import java.util.Map;

import model.generated.PersonType;

import org.eclipse.graphiti.features.impl.IIndependenceSolver;

public class MyIndepenceResolver implements IIndependenceSolver {

	private static Map<String, Object> objectMap = new HashMap<String, Object>();
	
	private Object topLevel;
	
	public Object getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Object topLevel) {
		this.topLevel = topLevel;
	}

	@Override
	public String getKeyForBusinessObject(Object bo) {
		String result = null;
		if(bo != null) {
			
			if(bo instanceof PersonType) {
				//For now person will have unique name assumed :)
				result = ((PersonType) bo).getName();
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
		return objectMap.get(key);
	}

	
}
