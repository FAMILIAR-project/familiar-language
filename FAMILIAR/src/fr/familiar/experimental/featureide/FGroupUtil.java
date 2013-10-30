/**
 * 
 */
package fr.familiar.experimental.featureide;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;

import fr.familiar.experimental.MutexGroup;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.operations.featureide.SATFormula;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FGroupUtil {
	
	/**
	 * an Xor-group is a special kind of Mutex-group SAT version
	 * 
	 * @param mtx
	 * @param fla
	 * @return
	 */
	public static boolean isXorGroup (MutexGroup mtx, SATFormula fla) {
		
		Node parent = new Literal(mtx.getTarget().getFeature());
		
		// mkDisjunctions of sources
		Node disj = SATBuilder.mkFalseNode() ; 
		for (FeatureNode<String> fNode : mtx.getSources()) {
			disj = new Or (disj, new Literal(fNode.getFeature()));
		}
		Node entail = new Or (new Not(parent), disj);
		Node oNode = fla.getNode() ; 
		
		if (!new SATFMLFormula(new And (oNode, new Not(entail))).isValid())
			return true ; 
		

		return false;
		
		
	}

}
