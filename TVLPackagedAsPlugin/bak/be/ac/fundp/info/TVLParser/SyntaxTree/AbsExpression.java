package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.Util.Util;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;

public class AbsExpression implements Expression {
	
	Expression expression;
	
	public AbsExpression(Expression e1) {
		this.expression = Util.removeParentheses(e1);
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (this.expression.getType() == Expression.REAL)
			return Expression.REAL;
		if (this.expression.getType() == Expression.INT)
			return Expression.INT;
		throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The type of the paramater ( "+this.expression.toString()+" ) of an abs expression must be int or real. Currently,  its type is "+Util.toStringExpressionType(this.expression.getType())+".");
		// Si l'on arrive ici, c'est qu'il y a eu un problème de type
	}
	
	public String toString() {
		return "abs( "+this.expression.toString()+" )";
	}
	
	public AbsExpression toNormalForm() {
		AbsExpression normalizedExpression =  new AbsExpression(this.expression.toNormalForm());
		return normalizedExpression;
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
	
}
