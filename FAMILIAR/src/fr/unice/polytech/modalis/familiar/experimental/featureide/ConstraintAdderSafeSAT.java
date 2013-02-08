package fr.unice.polytech.modalis.familiar.experimental.featureide;

import java.util.Set;

import org.prop4j.And;
import org.prop4j.Not;

import fr.unice.polytech.modalis.familiar.experimental.ConstraintAdder;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

public class ConstraintAdderSafeSAT extends ConstraintAdder {

	private SATFMLFormula _fmFla;


	public ConstraintAdderSafeSAT(FeatureModel<String> fm,
			Set<Expression<String>> biimplies, ImplicationGraph<String> impl,
			ExclusionGraph<String> excl1) {
		super(fm, biimplies, impl, excl1);
		
		_fmFla = new SATFMLFormula(new FeatureModelVariable("", _fm));
	}

	
	public ConstraintAdderSafeSAT(FeatureModel<String> fm) {
		super(fm);
		_fmFla = new SATFMLFormula(new FeatureModelVariable("", _fm));
	}


	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {

	

		SATFMLFormula exprFla = new SATFMLFormula(expr);
		And newNode = new And(_fmFla.getNode().clone(), new Not(exprFla.getNode()));
		SATFMLFormula newFla = new SATFMLFormula(newNode);
		boolean b = newFla.isValid() ; 
		if (b) {
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
		/*
		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		And newNode = new And(_fmFla.getNode().clone(), exprFla.getNode());
		SATFMLFormula newFla = new SATFMLFormula(newNode);
		
		
		Comparison comparison = newFla.compare(_fmFla);
		boolean b = comparison == Comparison.REFACTORING ; 
		if (!b) {
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
		*/

	}
	
	
	
	

}
