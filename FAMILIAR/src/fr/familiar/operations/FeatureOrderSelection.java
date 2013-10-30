/**
 * 
 */
package fr.familiar.operations;

import java.util.Collection;
import java.util.List;

import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public abstract class FeatureOrderSelection {
	
	
	
	protected FeatureModelVariable _fmv;
	
	protected Collection<String> _fts;

	public FeatureOrderSelection(FeatureModelVariable fmv, Collection<String> fts) {
		_fmv = fmv ; 
		_fts = fts ; 
	}
	
	public abstract List<String> compute() ; 

}
