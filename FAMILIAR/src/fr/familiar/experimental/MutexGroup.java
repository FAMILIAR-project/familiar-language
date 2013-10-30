package fr.familiar.experimental;

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

import java.util.Set;

import net.sf.javabdd.BDD;

public class MutexGroup extends FGroup {

	public MutexGroup (Set<FeatureNode<String>> source, FeatureNode<String> target) {
		_sources = source;
		_target = target;
		_edgeType = FeatureEdge.MUTEX ;
	}

	

	private BDD mkDisjunction(Set<FeatureNode<String>> fnodes, BDDBuilder<String> builder) {
		BDD result = builder.zero();
		for (FeatureNode<String> f : fnodes) {
			result.orWith(builder.get(f.getFeature()));
		}
		return result;

	}

	
	
	/**
	 * an Xor-group is a special kind of Mutex-group
	 * 
	 * @param fla
	 * @return
	 */
	public boolean isXorGroup(Formula<String> fla, BDDBuilder<String> builder) {
		BDD parent = builder.get(_target.getFeature());
		BDD children = mkDisjunction(_sources, builder);

		BDD entail = parent.imp(children);

		BDD oBDD = fla.getBDD().id();
		if (oBDD.and(entail.not()).isZero()) {
			return true;
		}

		return false;

	}
	
	@Override
	public Expression<String> toExpression() {
		assert (_sources.size() > 1) ; 
		return new Expression<String>(ExpressionType.AND, 
				ExpressionUtility.atMost1(_sources),
				ExpressionUtility.mkImplicationHierarchies(_sources, _target)) ; 
	}
 

}
