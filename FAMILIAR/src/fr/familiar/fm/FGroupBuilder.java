/**
 * 
 */
package fr.familiar.fm;

import java.util.Set;

import org.apache.log4j.Logger;

import fr.familiar.experimental.FGroup;
import fr.familiar.experimental.MutexGroup;
import fr.familiar.experimental.OrGroup;
import fr.familiar.experimental.XorGroup;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FGroupBuilder {

	private static Logger _LOGGER = Logger.getLogger(FGroupBuilder.class) ; 
	
	public static FGroup make(Set<FeatureNode<String>> sources,	FeatureNode<String> target, int edgeType) {
		if (edgeType == FeatureEdge.OR)
			return new OrGroup (sources, target);
		else if (edgeType == FeatureEdge.XOR)
			return new XorGroup (sources, target);
		else if (edgeType == FeatureEdge.MUTEX)
			return new MutexGroup (sources, target);
		else {
			_LOGGER.error("Unknown feature group: " + edgeType);
			return null ;
		}
	}

}
