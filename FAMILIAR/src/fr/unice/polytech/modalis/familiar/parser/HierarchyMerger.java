/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureModel;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author macher1
 *
 */
public abstract class HierarchyMerger {
	
	protected static Logger _LOGGER = Logger.getLogger(HierarchyMerger.class);
	
	
	/**
	 * given a collection of feature models compute the "merged" hierarchy
	 * different strategies are feasible (from basic to correct-by-construction and "optimized" hierarchies) 
	 * @param lfms collection of feature models
	 * @return the "merged" hierarchy 
	 */
	public abstract FeatureModel<String> build(Collection<FeatureModelVariable> lfms) ; 

}
