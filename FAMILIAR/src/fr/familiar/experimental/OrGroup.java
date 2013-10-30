package fr.familiar.experimental;

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

import java.util.Set;

public class OrGroup extends FGroup {

	public OrGroup(Set<FeatureNode<String>> source, FeatureNode<String> target) {
		_sources = source ; 
		_target = target ; 
		_edgeType = FeatureEdge.OR ;
	}

	@Override
	public Expression<String> toExpression() {
		return new Expression<String>(ExpressionType.AND,
				ExpressionUtility.atLeast1(_sources, _target), // at least one
				ExpressionUtility.mkImplicationHierarchies(_sources, _target)); 
	}

	

}
