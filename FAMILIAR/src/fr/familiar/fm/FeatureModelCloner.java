package fr.familiar.fm;

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;

import java.util.Set;

public class FeatureModelCloner {

	public static FeatureModel<String> clone(FeatureModel<String> fm) {
		FeatureGraph<String> fg = fm.getDiagram().clone() ; 
		FeatureModel<String> r = new FeatureModel<String>(fg);
		Set<Expression<String>> csts = fm.getConstraints() ;
		for (Expression<String> cst : csts) {
			Expression<String> cstClone = ExpressionUtility.clone(cst);
			r.addConstraint(cstClone);
		}
		return r;
	}

}
