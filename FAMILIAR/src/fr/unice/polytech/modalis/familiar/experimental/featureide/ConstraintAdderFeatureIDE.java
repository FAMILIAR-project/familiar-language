package fr.unice.polytech.modalis.familiar.experimental.featureide;

import java.util.Set;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.editing.ModelComparator;
import fr.unice.polytech.modalis.familiar.experimental.ConstraintAdder;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

public class ConstraintAdderFeatureIDE extends ConstraintAdder {

	private de.ovgu.featureide.fm.core.FeatureModel _oModel ;	

	public ConstraintAdderFeatureIDE(FeatureModel<String> fm, 
								Set<Expression<String>> biimplies, 
								ImplicationGraph<String> impl, 
								ExclusionGraph<String> excl1
								) {
		super (fm, biimplies, impl, excl1);
		_oModel = new FMLtoFeatureIDE(new FeatureModelVariable("", (FeatureModel<String>) fm)).convert() ;
		 
	}

		
	public ConstraintAdderFeatureIDE(FeatureModel<String> fm) {
		super(fm);
		_oModel = new FMLtoFeatureIDE(new FeatureModelVariable("", (FeatureModel<String>) fm)).convert() ;
		 
	}


	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {

		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		de.ovgu.featureide.fm.core.FeatureModel dirtyModel = _oModel.clone() ;
		Node exprNode = exprFla.getNode() ;
		dirtyModel.addPropositionalNode(exprNode);
		ModelComparator comparator = new ModelComparator(SATFMLFormula.SAT_TIMEOUT);
		Comparison comparison = FMComparator.convert(comparator.compare (_oModel, dirtyModel));
		boolean b = comparison == Comparison.REFACTORING ; 
		if (!b) {
			_oModel.addPropositionalNode(exprNode);
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
	
	}
	
	

}
