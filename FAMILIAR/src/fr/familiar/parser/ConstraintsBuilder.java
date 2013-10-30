package fr.familiar.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fML.CNF;

import fr.familiar.operations.CNFtoExpression;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

public class ConstraintsBuilder {
	
	public static SetVariable createFromCNF(EList<CNF> constraints,
			String var) {
		List<Expression<String>> csts = new ArrayList<Expression<String>>();
		for (CNF cnf : constraints) {
			Expression<String> expr = new CNFtoExpression(cnf).convert();
			csts.add(expr);
		}
		return createFromCNF(csts, var); 
	 
	}
	
	public static SetVariable createFromCNF(Collection<Expression<String>> csts,
			String var) {
	
		
		Set<Variable> cstVars = new HashSet<Variable>();
		for (Expression<String> cst : csts) {
			cstVars.add(new ConstraintVariable(cst, ""));
		}
		return new SetVariable(cstVars, var);
	}

}
