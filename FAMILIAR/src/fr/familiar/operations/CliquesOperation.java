/**
 * 
 */
package fr.familiar.operations;

import java.util.Collection;
import java.util.Set;

import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public abstract class CliquesOperation {
	
	protected FeatureModelVariable _fmv ; 
	
	public CliquesOperation(FeatureModelVariable fmv) {
		_fmv = fmv ;  
	}
	
	/**
	 * cliques are set of features that have the same assignment (based on Implication graph)
	 * @return the set of cliques where a clique is a set of names (feature/variable)
	 */
	public abstract Collection<Set<String>> cliques() ; 

}
