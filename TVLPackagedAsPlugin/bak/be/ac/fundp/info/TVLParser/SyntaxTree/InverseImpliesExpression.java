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

public class InverseImpliesExpression implements BooleanExpression {

	Expression expression1, expression2;

	public InverseImpliesExpression(Expression e1, Expression e2) {
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
		if (this.expression1.getType() == Expression.BOOL) {
			if (this.expression2.getType() == Expression.BOOL) {
				return Expression.BOOL;
			} else {
				throw new IllegalExpressionException("Type error : the expression " + this.toString()
						+ " is invalid. The type of the right paramater ( " + this.expression2.toString()
						+ " ) of an and expression must be bool. Currently,  its type is "
						+ Util.toStringExpressionType(this.expression2.getType()) + ".");
			}
		} else {
			throw new IllegalExpressionException("Type error : the expression " + this.toString()
					+ " is invalid. The type of the left paramater ( " + this.expression1.toString()
					+ " ) of an and expression must be bool. Currently,  its type is "
					+ Util.toStringExpressionType(this.expression1.getType()) + ".");
		}
	}

	public String toString() {
		return this.expression1.toString() + " <- " + this.expression2.toString();
	}

	public Expression toNormalForm() {
		return new InverseImpliesExpression(this.expression1.toNormalForm(), this.expression2.toNormalForm());
	}

	public BooleanExpression toSimplifiedForm() {
		return new OrExpression(new NotExpression(((BooleanExpression) this.expression2).toSimplifiedForm()),
				((BooleanExpression) this.expression1).toSimplifiedForm());
	}

	public BooleanExpression removeArrows() {
		return new OrExpression(new NotExpression(((BooleanExpression) this.expression2).removeArrows()),
				((BooleanExpression) this.expression1).removeArrows());
	}

	/**
	 * This method is normally never used because the toSimplifiedForm() method
	 * removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression distributeDisjunctions() {
		return this;
	}

	/**
	 * This method is normally never used because the toSimplifiedForm() method
	 * removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression distributeNegations() {
		return this;
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
