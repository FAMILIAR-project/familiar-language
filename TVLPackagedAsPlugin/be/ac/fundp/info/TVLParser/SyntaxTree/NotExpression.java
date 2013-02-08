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

public class NotExpression implements BooleanExpression {
	
	Expression expression;
	
	public NotExpression(Expression e1) {
		this.expression = Util.removeParentheses(e1);
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (this.expression.getType() == Expression.BOOL) {
			return Expression.BOOL;
		}
		throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The type of the paramater ( "+this.expression.toString()+" ) of a not expression must be bool. Currently,  its type is "+Util.toStringExpressionType(this.expression.getType())+".");
	}
	
	public String toString() {
		return "! "+this.expression.toString();
	}
	
	public Expression toNormalForm() {
		return new NotExpression(this.expression.toNormalForm());
	}

	@Override
	public BooleanExpression removeArrows() {
		return new NotExpression(((BooleanExpression)this.expression).removeArrows());
	}

	@Override
	public BooleanExpression toSimplifiedForm() {
		return new NotExpression(((BooleanExpression)this.expression).toSimplifiedForm());
	}
	
	public BooleanExpression distributeNegations() {
		while (this.expression instanceof ParenthesesExpression) {
			this.expression = ((ParenthesesExpression) this.expression).getExpression();
		}
		if (this.expression instanceof LongIDExpression) {
			return new NotExpression(this.expression);
		}
		else {
			if (this.expression instanceof NotExpression) {
				return ((BooleanExpression)((NotExpression) this.expression).getExpression()).distributeNegations();
			}
			else {
				BooleanExpression expression1;
				BooleanExpression expression2;
				if (this.expression instanceof OrExpression) {
					expression1 = (BooleanExpression) ((OrExpression) expression).getExpression1();
					expression2 = (BooleanExpression) ((OrExpression) expression).getExpression2();
					return new AndExpression((new NotExpression(expression1)).distributeNegations(), (new NotExpression(expression2)).distributeNegations());
				}
				else {
					if (this.expression instanceof AndExpression) {
						expression1 = (BooleanExpression) ((AndExpression) expression).getExpression1();
						expression2 = (BooleanExpression) ((AndExpression) expression).getExpression2();
						return new OrExpression((new NotExpression(expression1)).distributeNegations(), (new NotExpression(expression2)).distributeNegations());
					}
					else {
						if (this.expression instanceof TrueExpression) {
							return new FalseExpression();
						}
						else {
							return new TrueExpression();
						}
					}
				}
			}
		}
	}
	

	public BooleanExpression distributeDisjunctions() {
		//return new NotExpression(((BooleanExpression)this.expression).distributeDisjunctions());
		return this;
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}
