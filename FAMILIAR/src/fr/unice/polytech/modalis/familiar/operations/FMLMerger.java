/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.Collection;

import fr.unice.polytech.modalis.familiar.experimental.KSynthesisConfiguration;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMergerStrategy;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public abstract class FMLMerger {
	
	protected Collection<FeatureModelVariable> _lfms ;
	
	// FIXME: all paramaters of the synthesis should be here now
	protected KSynthesisConfiguration _kSynthesisConfiguration;
	
	public static final HierarchyMergerStrategy _DEFAULT_HIERARCHY_MERGER = HierarchyMergerStrategy.MST_IMPLICATION_GRAPH; 
	
	public FMLMerger (Collection<FeatureModelVariable> lfms) {
		_lfms = lfms ;
	}
	
	public abstract FeatureModelVariable intersection() ;
	
	public abstract FeatureModelVariable union() ;

}
