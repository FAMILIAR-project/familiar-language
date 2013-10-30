package fr.familiar.experimental;

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

import java.util.Set;

public class XorGroup extends FGroup {

	public XorGroup(Set<FeatureNode<String>> source, FeatureNode<String> target) {
		_sources = source ;
		_target = target ;
		_edgeType = FeatureEdge.XOR ;
	}

	@Override
	public Expression<String> toExpression() {
		return new Expression<String>(ExpressionType.AND,
				new Expression<String>(ExpressionType.AND, 
						ExpressionUtility.mkImplicationHierarchies(_sources, _target),
						ExpressionUtility.atLeast1(_sources, _target)), 
				ExpressionUtility.atMost1(_sources)); 
	}

}
