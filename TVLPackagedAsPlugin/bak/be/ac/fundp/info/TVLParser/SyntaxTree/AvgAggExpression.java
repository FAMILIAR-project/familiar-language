package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

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

public class AvgAggExpression implements Expression {

	ExpressionList expressionList;
	ChildrenAttributeID childrenAttributeID;

	public AvgAggExpression(ExpressionList e1) {
		this.expressionList = e1;
	}

	public AvgAggExpression(ChildrenAttributeID e1) {
		this.childrenAttributeID = e1;
	}

	/**
	 * @return the expressionList
	 */
	public ExpressionList getExpressionList() {
		return expressionList;
	}

	/**
	 * @return the childrenAttributeID
	 */
	public ChildrenAttributeID getChildrenAttributeID() {
		return childrenAttributeID;
	}

	public int getType() throws AmbiguousReferenceException, SymbolNotFoundException, IllegalExpressionException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException,
			SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException {
		if (this.expressionList == null) {
			if (this.childrenAttributeID.getType() == Expression.REAL) {
				return Expression.REAL;
			} else {
				if (this.childrenAttributeID.getType() == Expression.INT) {
					return Expression.INT;
				} else {
					throw new IllegalExpressionException(
							"Type error : the expression "
									+ this.toString()
									+ " is invalid. In an aggregate avg expression, the type of the parameter must be int or real. Currently, The type of the children attribute is "
									+ Util.toStringExpressionType(this.childrenAttributeID.getType()) + " and not int or real.");
				}
			}
		} else {
			if (this.expressionList.getType() == Expression.REAL) {
				return Expression.REAL;
			} else {
				if (this.expressionList.getType() == Expression.INT) {
					return Expression.INT;
				} else {
					throw new IllegalExpressionException(
							"Type error : the expression "
									+ this.toString()
									+ " is invalid. In an aggregate avg expression, the type of the parameter must be int or real. Currently, The type of the expression list is "
									+ Util.toStringExpressionType(this.childrenAttributeID.getType()) + " and not int or real.");
				}
			}
		}
	}

	public Expression toNormalForm() {
		if (this.childrenAttributeID != null) {
			if (this.childrenAttributeID.selectionType == ChildrenAttributeID.SELECTED_CHILDREN) {
				Vector<Object> childrenAttributesPath = this.childrenAttributeID.getChildrenAttributesPath();
				ExpressionList expressionList = new ExpressionList(new QuestExpression(new LongIDExpression(childrenAttributesPath.get(0)
						.toString(), null), new IntExpression("1"), new IntExpression("0")));
				int i = 2;
				while (i <= childrenAttributesPath.size() - 1) {
					expressionList = new ExpressionList(new QuestExpression(new LongIDExpression(childrenAttributesPath.get(i).toString(),
							null), new IntExpression("1"), new IntExpression("0")), expressionList);
					i = i + 2;
				}
				return new DivideExpression((new SumAggExpression(this.childrenAttributeID)).toNormalForm(), (new SumAggExpression(
						expressionList)));
			} else {
				return new DivideExpression((new SumAggExpression(this.childrenAttributeID)).toNormalForm(), new IntExpression(new Integer(
						this.childrenAttributeID.childrenAttributesPath.size() / 2).toString()));
			}
		} else {
			int i = 0;
			ExpressionList normalizedExpressionList = new ExpressionList(this.expressionList.getExpressions().get(i++));
			while (i <= this.expressionList.expressions.size() - 1) {
				normalizedExpressionList = new ExpressionList(this.expressionList.getExpressions().get(i).toNormalForm(),
						normalizedExpressionList);
				i++;
			}
			// return new AvgAggExpression(expressionList);
			return new DivideExpression(new SumAggExpression(normalizedExpressionList), new IntExpression(new Integer(i).toString()));
		}
	}

	public String toString() {
		if (this.expressionList != null) {
			return "avg( " + this.expressionList.toString() + " )";
		} else
			return "avg( " + this.childrenAttributeID.toString() + " )";
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}
