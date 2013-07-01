package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

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


public class ExpressionList {

	Vector<Expression> expressions;

	public ExpressionList(Expression e1) {
		this.expressions = new Vector<Expression>();
		this.expressions.add(e1);
	}
	
	public ExpressionList(Expression e1, ExpressionList e2) {
		this.expressions = e2.getExpressions();
		this.expressions.add(e1);
		//this.expressions.add(0, e1);
	}

	/**
	 * @return the expressionList
	 */
	public Vector<Expression> getExpressions() {
		return expressions;
	}
	
	public String toString() {
	/*	String list = this.expressions.get(0).toString();
		int i = 1;
		while ( i <= this.expressions.size()-1) {
			list = list.concat(", "+this.expressions.get(i).toString());
			i++;
		}
		return list; */
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(Expression e : expressions){
			if(!first) {
				sb.append(",");
				first = false;
			}
			sb.append(e.toString());
		}
		return sb.toString();
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		int currentType = 0;
		int i = 0;
		while (i <= this.expressions.size()-1) {
			if (i == 0) {
				currentType = expressions.get(i).getType();			}
			else {
				if (currentType != expressions.get(i).getType())
					if ((currentType == Expression.REAL) && (expressions.get(i).getType() == Expression.INT)) {
						currentType = Expression.REAL;
					}
					else {
						if ((currentType == Expression.INT) && (expressions.get(i).getType() == Expression.REAL)) {
							currentType = Expression.REAL;
						}
						else {
							throw new IllegalExpressionException();
						}
					}
			}
			i++;
		}
		return currentType;
	}
}
