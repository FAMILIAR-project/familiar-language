package fr.familiar.operations;

import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

import java.util.Set;

public interface IConstraintReasoner {
	
	public boolean hasRedundantConstraints() ;

	public Set<Expression<String>> computeRedundantConstraints();

	public FeatureModel<String> eliminateRedundantConstraints();

}
