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

public class PlusExpression implements Expression {

	Expression expression1, expression2;

	public PlusExpression(Expression e1, Expression e2) {
		this.expression1 = Util.removeParentheses(e1);
		this.expression2 = Util.removeParentheses(e2);
	}

	/**
	 * @return the expression1
	 */
	public Expression getExpression1() {
		return expression1;
	}

	/**
	 * @return the expression2
	 */
	public Expression getExpression2() {
		return expression2;
	}

	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException,
			SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException {
		if (this.expression1.getType() == Expression.INT) {
			if (this.expression2.getType() == Expression.INT) {
				return Expression.INT;
			} else {
				if (this.getExpression2().getType() == Expression.REAL) {
					return Expression.REAL;
				} else {
					throw new IllegalExpressionException("Type error : the expression " + this.toString()
							+ " is invalid. The type of the right paramater ( " + this.expression2.toString()
							+ " ) of a plus expression must be real or int. Currently,  its type is "
							+ Util.toStringExpressionType(this.expression2.getType()) + ".");
				}
			}
		} else {
			if (this.expression1.getType() == Expression.REAL) {
				if (this.expression2.getType() == Expression.INT) {
					return Expression.REAL;
				} else {
					if (this.getExpression2().getType() == Expression.REAL) {
						return Expression.REAL;
					} else {
						throw new IllegalExpressionException("Type error : the expression " + this.toString()
								+ " is invalid. The type of the right paramater ( " + this.expression2.toString()
								+ " ) of a plus expression must be real or int. Currently,  its type is "
								+ Util.toStringExpressionType(this.expression2.getType()) + ".");
					}
				}
			} else {
				throw new IllegalExpressionException("Type error : the expression " + this.toString()
						+ " is invalid. The type of the left paramater ( " + this.expression1.toString()
						+ " ) of a plus expression must be real or int. Currently,  its type is "
						+ Util.toStringExpressionType(this.expression1.getType()) + ".");
			}
		}
	}

	public String toString() {
		return this.expression1.toString() + " + " + this.expression2.toString();
	}

	public Expression toNormalForm() {
		return new PlusExpression(this.expression1.toNormalForm(), this.expression2.toNormalForm());
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}