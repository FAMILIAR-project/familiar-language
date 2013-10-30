/**
 * 
 */
package fr.familiar.parser;

import fr.familiar.operations.Mode;
import gsd.synthesis.Formula;

/**
 * @author macher1
 *
 */
public class HierarchyMergerFactory {

	public static HierarchyMerger mkMerger(HierarchyMergerStrategy hierarchyMergerStrategy, Mode m, Formula<String> fla) {
		if (hierarchyMergerStrategy == HierarchyMergerStrategy.MST)
			return new HierarchyMergerWithMST(); 
		else if (hierarchyMergerStrategy == HierarchyMergerStrategy.IMPLICATION_GRAPH)
			return new HierarchyMergerWithImpl(m); 				
		else if (hierarchyMergerStrategy == HierarchyMergerStrategy.MST_IMPLICATION_GRAPH)
			return new HierarchyMergerWithMSTIG(fla) ;					
		else if (hierarchyMergerStrategy == HierarchyMergerStrategy.FLAT)
			return new HierarchyMergerFlat() ; 
		else
			return new HierarchyMergerBasic() ; 
		
	}	
	

}
