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
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;


public class SetExpression {
	
	ExpressionList expressionList;
	String minExpression, maxExpression;
	
	FeaturesSymbolTable featuresSymbolTable;
	
	public SetExpression(ExpressionList e1, FeaturesSymbolTable featuresSymbolTable) {
		this.expressionList = e1;
		this.featuresSymbolTable = featuresSymbolTable;
		this.maxExpression = null;
		this.minExpression = null;
	}
	
	public SetExpression(String e1, String e2, FeaturesSymbolTable featuresSymbolTable) {
		this.minExpression = e1;
		this.maxExpression = e2;
		this.featuresSymbolTable = featuresSymbolTable;
		this.expressionList = null;
	}
	
	public boolean hasAnExpressionList() {
		if (this.expressionList != null)
			return true;
		else
			return false;
	}

	/**
	 * @return the expressionList
	 */
	public ExpressionList getExpressionList() {
		return expressionList;
	}

	/**
	 * @return the minExpression
	 */
	public String getMinExpression() {
		return minExpression;
	}

	/**
	 * @return the maxExpression
	 */
	public String getMaxExpression() {
		return maxExpression;
	}
	
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (this.expressionList == null) {
			if (this.minExpression.equals("*")) {
				if (this.maxExpression.equals("*")) {
					return Expression.INT;
				}
				else {
					try {
						Integer.parseInt(this.maxExpression);
						return Expression.INT;
					}
					catch (NumberFormatException e) {
						return Expression.REAL;
					}
				}
			}
			else {
				if (this.maxExpression.equals("*")) {
					try {
						Integer.parseInt(this.minExpression);
						return Expression.INT;
					}
					catch (NumberFormatException e) {
						return Expression.REAL;
					}
				}
				else {
					try {
						int minInt = Integer.parseInt(this.minExpression);
						try {
							int maxInt = Integer.parseInt(this.maxExpression);
							if (minInt <= maxInt)
								return Expression.INT;
							else
								throw new IllegalExpressionException("Error : the interval [ "+this.minExpression+".."+this.maxExpression+" ] is not valid, the lower bound must be smaller or equals to the upper bound.");
						}
						catch (NumberFormatException e) {
							float maxFloat = Float.parseFloat(maxExpression);
							if (minInt <= maxFloat)
								return Expression.REAL;
							else
								throw new IllegalExpressionException("Error : the interval [ "+this.minExpression+".."+this.maxExpression+" ] is not valid, the lower bound must be smaller or equals to the upper bound.");
						}
					}
					catch (NumberFormatException e) {
						float minFloat = Float.parseFloat(minExpression);
						float maxFloat = Float.parseFloat(maxExpression);
						if (minFloat <= maxFloat)
							return Expression.REAL;
						else
							throw new IllegalExpressionException("Error : the interval [ "+this.minExpression+".."+this.maxExpression+" ] is not valid, the lower bound must be smaller or equals to the upper bound.");
					}
				}
			}
		}
		else {
			int currentType = this.expressionList.getExpressions().get(0).getType();
			int i = 1;
			while (i <= this.expressionList.getExpressions().size()-1) {
				if (currentType != this.expressionList.getExpressions().get(i).getType()) {
					if ((currentType == Expression.REAL) && (this.expressionList.getExpressions().get(i).getType() == Expression.INT)) {
						currentType = Expression.REAL;
					}
					else {
						if ((currentType == Expression.INT) && (this.expressionList.getExpressions().get(i).getType() == Expression.REAL)) {
							currentType = Expression.REAL;
						}
						else {
							if ((this.featuresSymbolTable.containsSymbol(this.expressionList.getExpressions().get(i).toString())) && (Util.isAFeatureID(this.expressionList.getExpressions().get(i).toString()))) {
								throw new IDEnumValuesConflictException("Type error : the enum value "+this.expressionList.getExpressions().get(i).toString()+" of the setx expression "+this.expressionList.toString()+" corresponds to a feature ID, a constant ID or a type ID.");
							}
							else {
								throw new IllegalExpressionException("Error : the expression { "+this.expressionList.toString()+" } is not valid, it contains "+Util.toStringExpressionType(this.expressionList.getExpressions().get(i).getType())+" and "+Util.toStringExpressionType(currentType)+" values.");
							}
						}	
					}
				}
				i++;
			}
			return currentType;
		}
	}
	
	public SetExpression toNormalForm() {
		if (this.hasAnExpressionList()) {
			Expression normalizedExpression = expressionList.getExpressions().get(0).toNormalForm();
			ExpressionList expressionList = new ExpressionList(normalizedExpression);
			int i = 1;
			while (i <= this.expressionList.expressions.size()-1) {
				normalizedExpression = this.expressionList.expressions.get(i).toNormalForm();
				expressionList = new ExpressionList(normalizedExpression, expressionList);
				i++;
			}
			return new SetExpression(expressionList, null);
		}
		else {
			return new SetExpression(this.minExpression, this.maxExpression, null);
		}
	}
	
	public String toString() {
		if (this.hasAnExpressionList())
			return "{ "+this.expressionList.toString()+" }";
		else
			return "[ "+this.minExpression+".."+this.maxExpression+" ]";
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
