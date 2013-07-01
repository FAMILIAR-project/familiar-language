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

public class ParenthesesExpression implements BooleanExpression {

	public Expression expression;
	
	public ParenthesesExpression(Expression expression) {
		this.expression = Util.removeParentheses(expression);
	}
	
	public String toString() {
		return "( "+this.expression.toString()+" )";
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		return this.expression.getType();
	}
	
	public Expression toNormalForm() {
		return new ParenthesesExpression(this.expression.toNormalForm());
	}

	@Override
	public BooleanExpression removeArrows() {
		return new ParenthesesExpression(((BooleanExpression)this.expression).removeArrows());
	}

	@Override
	public BooleanExpression toSimplifiedForm() {
		return new ParenthesesExpression(((BooleanExpression)this.expression).toSimplifiedForm());
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public BooleanExpression distributeNegations() {
		return new ParenthesesExpression(((BooleanExpression)this.expression).distributeNegations());
	}
	
	public BooleanExpression distributeDisjunctions() {
		return ((BooleanExpression) this.expression).distributeDisjunctions();
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}	
}
