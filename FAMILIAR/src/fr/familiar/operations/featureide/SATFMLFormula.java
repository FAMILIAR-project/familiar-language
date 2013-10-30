/**
 * 
 */
package fr.familiar.operations.featureide;

import org.prop4j.Node;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class SATFMLFormula extends SATFormula {

	public SATFMLFormula(FeatureModelVariable fmv) {
		super(fmv);
	}

	public SATFMLFormula(Node n) {
		super(n);
	}

	public SATFMLFormula(Expression<String> expr) {
		super (expr);
	}

	@Override
	public Node mkNode(FeatureModelVariable fmv) {
		return new SATBuilder().mkNode(fmv);
	}
	
	@Override
	public Node mkNode(Expression<String> expr) {
		return new SATBuilder().mkExpression(expr);
	}

	

	

	

}
