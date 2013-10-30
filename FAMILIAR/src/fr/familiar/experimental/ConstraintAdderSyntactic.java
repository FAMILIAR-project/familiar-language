package fr.familiar.experimental;

import fr.familiar.fm.converter.ExclusionGraph;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

import java.util.Set;

public class ConstraintAdderSyntactic extends ConstraintAdder {

	public ConstraintAdderSyntactic(FeatureModel<String> fm,
			Set<Expression<String>> biimplies, ImplicationGraph<String> impl,
			ExclusionGraph<String> excl1) {
		super(fm, biimplies, impl, excl1);
	}
	
	public ConstraintAdderSyntactic(FeatureModel<String> fm) {
		super(fm);
	}

	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {
		return _fm.addConstraint(expr) ; 
	}

}
