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

import java.util.HashMap;
import java.util.Map;

import model.generated.HierarchyDBType;

import org.eclipse.graphiti.mm.pictograms.Diagram;

public class HierarchyRepository {
  
  private final static Map<Diagram, HierarchyDBType> diagramHierarchyMap = new HashMap<Diagram, HierarchyDBType>();
  private final static Map<HierarchyDBType, Diagram> hierarchyDiagramMap = new HashMap<HierarchyDBType,Diagram>();
  
  public static void registerDiagramAndHierarchyDB(Diagram d, HierarchyDBType f) {
	  diagramHierarchyMap.put(d, f);
	  hierarchyDiagramMap.put(f,d);
  }
  
  public static void unregisterDiagramAndHierarchyDB(Diagram d, HierarchyDBType f) {
	  diagramHierarchyMap.remove(d);
    hierarchyDiagramMap.remove(f);
  }
  
  public static HierarchyDBType getHierarchyDBForDiagram(Diagram d) {
    return diagramHierarchyMap.get(d);
  }
  
  public static Diagram getDiagramForHierarchyDB(HierarchyDBType f) {
    return hierarchyDiagramMap.get(f);
  }

}
