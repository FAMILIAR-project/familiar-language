package fr.familiar.test.featureide;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.editing.NodeCreator;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

public class SATFeatureIDEFormula extends SATFormula {

	public SATFeatureIDEFormula(FeatureModelVariable fmv) {
		super(fmv);
	}

	public SATFeatureIDEFormula(Node n) {
		super(n);
	}

	@Override
	public Node mkNode(FeatureModelVariable fmv) {
		return NodeCreator.createNodes(new FMLtoFeatureIDE(fmv).convert());
	}

	@Override
	public Node mkNode(Expression<String> expr) {
		// TODO Auto-generated method stub
		return null;
	}

}
