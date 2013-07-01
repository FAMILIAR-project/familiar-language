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
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;

public class XorAggExpression implements BooleanExpression {

	ExpressionList expressionList;
	ChildrenAttributeID childrenAttributeID;

	public XorAggExpression(ExpressionList e1) {
		this.expressionList = e1;
	}

	public XorAggExpression(ChildrenAttributeID e1) {
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

	@Override
	public int getType() throws AmbiguousReferenceException, SymbolNotFoundException, IllegalExpressionException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException,
			SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException {
		if (this.expressionList == null) {
			if (this.childrenAttributeID.getType() == Expression.BOOL)
				return Expression.BOOL;
			else
				throw new IllegalExpressionException(
						"Type error : the expression "
								+ this.toString()
								+ " is invalid. In an aggregate xor expression, the type of the parameter must be bool. Currently, The type of the children attribute is "
								+ Util.toStringExpressionType(this.childrenAttributeID.getType()) + " and not bool");
		} else {
			if (this.expressionList.getType() == Expression.BOOL)
				return Expression.BOOL;
			else
				throw new IllegalExpressionException(
						"Type error : the expression "
								+ this.toString()
								+ " is invalid. In an aggregate xor expression, the type of the parameter must be bool. Currently, The type of the expression list is "
								+ Util.toStringExpressionType(this.childrenAttributeID.getType()) + " and not bool");
		}
	}

	public String toString() {
		if (this.expressionList != null) {
			return "xor( " + this.expressionList.toString() + " )";
		} else
			return "xor( " + this.childrenAttributeID.toString() + " )";
	}

	public Expression toNormalForm() {
		if (this.childrenAttributeID != null) {
			if (this.childrenAttributeID.selectionType == ChildrenAttributeID.SELECTED_CHILDREN) {
				Vector<Object> childrenAttributesPath = this.childrenAttributeID.getChildrenAttributesPath();
				QuestExpression questExpression = new QuestExpression(new LongIDExpression(childrenAttributesPath.get(0).toString(), null),
						new LongIDExpression(childrenAttributesPath.get(0).toString() + "."
								+ ((AttributeSymbol) childrenAttributesPath.get(1)).getID(), null), new FalseExpression());
				ExpressionList expressionList = new ExpressionList(questExpression);
				int i = 2;
				while (i <= childrenAttributesPath.size() - 1) {
					questExpression = new QuestExpression(new LongIDExpression(childrenAttributesPath.get(i).toString(), null),
							new LongIDExpression(childrenAttributesPath.get(i).toString() + "."
									+ ((AttributeSymbol) childrenAttributesPath.get(i + 1)).getID(), null), new FalseExpression());
					expressionList = new ExpressionList(questExpression, expressionList);
					i = i + 2;
				}
				return new XorAggExpression(expressionList);
			} else {
				Vector<Object> childrenAttributesPath = this.childrenAttributeID.getChildrenAttributesPath();
				LongIDExpression longIDExpression = new LongIDExpression(childrenAttributesPath.get(0).toString() + "."
						+ ((AttributeSymbol) childrenAttributesPath.get(1)).getID(), null);
				ExpressionList expressionList = new ExpressionList(longIDExpression);
				int i = 2;
				while (i <= childrenAttributesPath.size() - 1) {
					longIDExpression = new LongIDExpression(childrenAttributesPath.get(i).toString() + "."
							+ ((AttributeSymbol) childrenAttributesPath.get(i + 1)).getID(), null);
					expressionList = new ExpressionList(longIDExpression, expressionList);
					i = i + 2;
				}
				return new XorAggExpression(expressionList);
			}
		} else {
			int i = 0;
			ExpressionList normalizedExpressionList = new ExpressionList(this.expressionList.getExpressions().get(i).toNormalForm());
			i = 1;
			while (i <= this.expressionList.expressions.size() - 1) {
				normalizedExpressionList = new ExpressionList(this.expressionList.getExpressions().get(i).toNormalForm(),
						normalizedExpressionList);
				i++;
			}
			return new XorAggExpression(normalizedExpressionList);
		}
	}

	public BooleanExpression toSimplifiedForm() {
		if (this.expressionList.getExpressions().size() == 1) {
			return ((BooleanExpression) this.expressionList.expressions.get(0)).toSimplifiedForm();
		} else {
			AndExpression andExpression1 = new AndExpression(
					((BooleanExpression) this.expressionList.expressions.get(0)).toSimplifiedForm(), new NotExpression(
							((BooleanExpression) this.expressionList.expressions.get(1))).toSimplifiedForm());
			AndExpression andExpression2 = new AndExpression(
					((BooleanExpression) this.expressionList.expressions.get(1)).toSimplifiedForm(), new NotExpression(
							((BooleanExpression) this.expressionList.expressions.get(0))).toSimplifiedForm());
			int i = 2;
			while (i <= this.expressionList.expressions.size() - 1) {
				andExpression1 = new AndExpression(andExpression1, new NotExpression(
						((BooleanExpression) this.expressionList.expressions.get(i))).toSimplifiedForm());
				andExpression2 = new AndExpression(andExpression2, new NotExpression(
						((BooleanExpression) this.expressionList.expressions.get(i))).toSimplifiedForm());
				i++;
			}
			OrExpression simplifiedExpression = new OrExpression(new ParenthesesExpression(andExpression1), new ParenthesesExpression(
					andExpression2));
			i = 2;
			while (i <= this.expressionList.expressions.size() - 1) {
				AndExpression andExpression = new AndExpression(
						((BooleanExpression) this.expressionList.expressions.get(i)).toSimplifiedForm(), new NotExpression(
								((BooleanExpression) this.expressionList.expressions.get(0))).toSimplifiedForm());
				int j = 1;
				while (j <= this.expressionList.expressions.size() - 1) {
					if (i != j) {
						andExpression = new AndExpression(andExpression, new NotExpression(
								((BooleanExpression) this.expressionList.expressions.get(j))).toSimplifiedForm());
					}
					j++;
				}
				simplifiedExpression = new OrExpression(simplifiedExpression, new ParenthesesExpression(andExpression));
				i++;
			}
			System.out.println("simplifiedExpression : " + simplifiedExpression.toString());
			return new ParenthesesExpression(simplifiedExpression);
		}
	}

	/**
	 * This method is normally never used because the toSimplifiedForm() method
	 * removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression removeArrows() {
		return this;
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