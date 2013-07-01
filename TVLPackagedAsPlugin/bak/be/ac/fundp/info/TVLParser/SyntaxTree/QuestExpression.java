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

public class QuestExpression implements BooleanExpression {
	
	Expression expression1, expression2, expression3;
	
	public QuestExpression(Expression e1, Expression e2, Expression e3) {
		this.expression1 = Util.removeParentheses(e1);
		this.expression2 = Util.removeParentheses(e2);
		this.expression3 = Util.removeParentheses(e3);
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

	/**
	 * @return the expression3
	 */
	public Expression getExpression3() {
		return expression3;
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (!(this.expression1.getType() == Expression.BOOL))
			throw new IllegalExpressionException("Type error : The expression "+this.toString()+" isn't valid. The type of the first parameter ( "+this.expression1.toString()+" ) is "+Util.toStringExpressionType(this.expression1.getType())+" and not bool.");
		if (this.expression2.getType() == Expression.BOOL) {
			if (this.expression3.getType() == Expression.BOOL) 
				return Expression.BOOL;
			else
				throw new IllegalExpressionException("Type error : The expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression2.getType())+" of the second parameter ( "+this.expression2.toString()+" ) is different from the type "+Util.toStringExpressionType(this.expression3.getType())+" of the second parameter ( "+this.expression3.toString()+" ).");
		}
		else {
			if (this.expression2.getType() == Expression.INT) {
				if (this.expression3.getType() == Expression.INT) 
					return Expression.INT;
				else
					throw new IllegalExpressionException("Type error : The expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression2.getType())+" of the second parameter ( "+this.expression2.toString()+" ) is different from the type "+Util.toStringExpressionType(this.expression3.getType())+" of the second parameter ( "+this.expression3.toString()+" ).");
			}
			else {
				if (this.expression2.getType() == Expression.REAL) {
					if ((this.expression3.getType() == Expression.REAL) || (this.expression3.getType() == Expression.INT))
						return Expression.REAL;
					else
						throw new IllegalExpressionException("Type error : The expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression2.getType())+" of the second parameter ( "+this.expression2.toString()+" ) is different from the type "+Util.toStringExpressionType(this.expression3.getType())+" of the second parameter ( "+this.expression3.toString()+" ).");
				}
				else {
					if (this.expression2.getType() == Expression.ENUM) {
						if (this.expression3.getType() == Expression.ENUM) 
							return Expression.ENUM;
						else
							throw new IllegalExpressionException("Type error : The expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression2.getType())+" of the second parameter ( "+this.expression2.toString()+" ) is different from the type "+Util.toStringExpressionType(this.expression3.getType())+" of the second parameter ( "+this.expression3.toString()+" ).");
					}
					else {
						throw new IllegalExpressionException("Type error : The expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression2.getType())+" of the second parameter ( "+this.expression2.toString()+" ) is different from the type "+Util.toStringExpressionType(this.expression3.getType())+" of the second parameter ( "+this.expression3.toString()+" ).");
					}	
				}
			}
		}
	}

	public String toString() {
		return this.expression1.toString()+" ? "+this.expression2.toString()+" : "+this.expression3.toString();
	}
	
	public Expression toNormalForm() {
		return new QuestExpression(this.expression1.toNormalForm(), this.expression2.toNormalForm(), this.expression3.toNormalForm());
	}
	
	public BooleanExpression toSimplifiedForm() {
		return new OrExpression(new AndExpression(((BooleanExpression)this.expression1).toSimplifiedForm(),((BooleanExpression)this.expression2).toSimplifiedForm()), 
									new AndExpression(new NotExpression(((BooleanExpression)this.expression1).toSimplifiedForm()),((BooleanExpression)this.expression3).toSimplifiedForm()));
		//return new OrExpression(new ParenthesesExpression(new AndExpression(this.expression1, this.expression2)), new ParenthesesExpression(new AndExpression(new NotExpression(this.expression1), this.expression3)));
	}
	
	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression removeArrows() {
		return this;
	}
	
	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression distributeDisjunctions() {
		return this;
	}

	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
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