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
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.EnumSetExpressionSymbol;

public class LowerExpression implements BooleanExpression {

	Expression expression1, expression2;

	public LowerExpression(Expression e1, Expression e2) {
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

	public void checkNumericalComparison() throws IllegalExpressionException, NumberFormatException,
			SetExpressionMemberViolatingAttributeTypeException, SymbolNotFoundException, AmbiguousReferenceException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SetExpressionMemberOutOfBoundException,
			ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException {
		if ((this.expression1 instanceof LongIDExpression) && (this.expression2 instanceof LongIDExpression)) {
			LongIDExpression longIDExpression1 = (LongIDExpression) this.expression1;
			LongIDExpression longIDExpression2 = (LongIDExpression) this.expression2;
			AttributeSymbol numericalAttribute1 = (AttributeSymbol) longIDExpression1.getSymbol();
			AttributeSymbol numericalAttribute2 = (AttributeSymbol) longIDExpression2.getSymbol();
			if ((numericalAttribute1.hasASetExpressionSymbol()) && (numericalAttribute2.hasASetExpressionSymbol())) {
				numericalAttribute1.getSetExpressionSymbol().print();
				numericalAttribute2.getSetExpressionSymbol().print();
				if (!((numericalAttribute1.getSetExpressionSymbol().containsSetExpressionSymbol(numericalAttribute2
						.getSetExpressionSymbol())) && (numericalAttribute2.getSetExpressionSymbol()
						.containsSetExpressionSymbol(numericalAttribute1.getSetExpressionSymbol())))) {
					throw new IllegalExpressionException("Type error : the expression " + this.toString() + " is not valid. "
							+ numericalAttribute1.getID() + " and " + numericalAttribute2.getID() + " haven't the same values domain");
				}
			}
		} else if (this.expression1 instanceof LongIDExpression) {
			LongIDExpression longIDExpression1 = (LongIDExpression) this.expression1;
			AttributeSymbol numericalAttribute1 = (AttributeSymbol) longIDExpression1.getSymbol();
			if (numericalAttribute1.hasASetExpressionSymbol()) {
				if (!(numericalAttribute1.getSetExpressionSymbol().containsExpression(expression2))) {
					throw new IllegalExpressionException("Type error : the expression " + this.toString()
							+ " is not valid. The values domain of " + numericalAttribute1.getID() + " doesn't include "
							+ this.expression2.toString());
				}
			}
		} else if (this.expression2 instanceof LongIDExpression) {
			LongIDExpression longIDExpression2 = (LongIDExpression) this.expression2;
			AttributeSymbol numericalAttribute2 = (AttributeSymbol) longIDExpression2.getSymbol();
			if (numericalAttribute2.hasASetExpressionSymbol()) {
				if (!(numericalAttribute2.getSetExpressionSymbol().containsExpression(expression1))) {
					throw new IllegalExpressionException("Type error : the expression " + this.toString()
							+ " is not valid. The values domain of " + numericalAttribute2.getID() + " doesn't include "
							+ this.expression1.toString());
				}
			}
		} else
			throw new IllegalExpressionException("Type error : the expression " + this.toString()
					+ " is not valid, you cannot compare two numerical values.");
	}

	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException,
			SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException {
		if (this.expression1.getType() == Expression.INT) {
			if (this.expression2.getType() == Expression.INT) {
				this.checkNumericalComparison();
				return Expression.BOOL;
			} else {
				if (this.getExpression2().getType() == Expression.REAL) {
					this.checkNumericalComparison();
					return Expression.BOOL;
				} else {
					throw new IllegalExpressionException("Type error : the expression " + this.toString()
							+ " is invalid. The type of the right paramater ( " + this.expression2.toString()
							+ " ) of a lower expression must be real or int. Currently,  its type is "
							+ Util.toStringExpressionType(this.expression2.getType()) + ".");
				}
			}
		} else {
			if (this.expression1.getType() == Expression.REAL) {
				if (this.expression2.getType() == Expression.INT) {
					this.checkNumericalComparison();
					return Expression.BOOL;
				} else {
					if (this.getExpression2().getType() == Expression.REAL) {
						this.checkNumericalComparison();
						return Expression.BOOL;
					} else {
						throw new IllegalExpressionException("Type error : the expression " + this.toString()
								+ " is invalid. The type of the right paramater ( " + this.expression2.toString()
								+ " ) of a lower expression must be real or int. Currently,  its type is "
								+ Util.toStringExpressionType(this.expression2.getType()) + ".");
					}
				}
			} else {
				if ((this.getExpression1().getType() == Expression.ENUM) && (this.getExpression2().getType() == Expression.ENUM)) {
					LongIDExpression longIDExpression1 = (LongIDExpression) this.expression1;
					LongIDExpression longIDExpression2 = (LongIDExpression) this.expression2;
					if (longIDExpression1.getSymbol() != null) {
						if (longIDExpression2.getSymbol() != null) {
							AttributeSymbol enumAttribute1 = (AttributeSymbol) longIDExpression1.getSymbol();
							AttributeSymbol enumAttribute2 = (AttributeSymbol) longIDExpression2.getSymbol();
							if ((enumAttribute1.getType() == Expression.USER_CREATED)
									&& (enumAttribute2.getType() == Expression.USER_CREATED)) {
								if (enumAttribute1.getUserType().equals(enumAttribute2.getUserType()))
									return Expression.BOOL;
								else {
									if (((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol())
											.hasTheSameEnumValuesDomain(((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol())
													.getContainedValues())) {
										return Expression.BOOL;
									} else {
										throw new IllegalExpressionException("Type error : the expression " + this.toString()
												+ " is invalid. The values domain of the left paramater ( " + this.expression1.toString()
												+ " ) is different from the values domain of the right parameter ( "
												+ this.expression2.toString() + " ).");
									}
								}
							} else {
								if ((((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol())
										.hasTheSameEnumValuesDomain(((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol())
												.getContainedValues()))) {
									return Expression.BOOL;
								} else
									throw new IllegalExpressionException("Type error : the expression " + this.toString()
											+ " is invalid. The values domain of the left paramater ( " + this.expression1.toString()
											+ " ) is different from the values domain of the right parameter ( "
											+ this.expression2.toString() + " ).");
							}
						} else {
							AttributeSymbol enumAttribute1 = (AttributeSymbol) longIDExpression1.getSymbol();
							if (enumAttribute1.getSetExpressionSymbol().containsExpression(longIDExpression2)) {
								return Expression.BOOL;
							} else {
								throw new IllegalExpressionException("Type error : the expression " + this.toString()
										+ " is invalid. The values domain of the attribute ( " + this.expression1.toString()
										+ " ) doesn't contain the enum value ( " + this.expression2.toString() + " ).");
							}
						}
					} else {
						if (longIDExpression2.getSymbol() != null) {
							AttributeSymbol enumAttribute2 = (AttributeSymbol) longIDExpression2.getSymbol();
							if (enumAttribute2.getSetExpressionSymbol().containsExpression(longIDExpression1)) {
								return Expression.BOOL;
							} else {
								throw new IllegalExpressionException("Type error : the expression " + this.toString()
										+ " is invalid. The values domain of the attribute ( " + this.expression1.toString()
										+ " ) doesn't contain the enum value ( " + this.expression2.toString() + " ).");
							}
						} else {
							throw new IllegalExpressionException("Type error : the expression " + this.toString()
									+ " is invalid. You cannot compare two enum values (" + this.expression1.toString() + ", "
									+ this.expression2.toString() + " ).");
						}
					}
				} else {
					throw new IllegalExpressionException("Type error : the expression " + this.toString()
							+ " is invalid. The type of the left paramater ( " + this.expression1.toString()
							+ " ) of a greataer or equal expression must be real or int. Currently,  its type is "
							+ Util.toStringExpressionType(this.expression1.getType()) + ".");
				}
			}
		}
	}

	public String toString() {
		return this.expression1.toString() + " < " + this.expression2.toString();
	}

	public Expression toNormalForm() {
		return new LowerExpression(this.expression1.toNormalForm(), this.expression2.toNormalForm());
	}

	@Override
	public BooleanExpression distributeDisjunctions() {
		return this;
	}

	@Override
	public BooleanExpression distributeNegations() {
		return this;
	}

	@Override
	public BooleanExpression removeArrows() {
		return this;
	}

	@Override
	public BooleanExpression toSimplifiedForm() {
		LongIDExpression londIDExpression1 = ((LongIDExpression) this.expression1);
		LongIDExpression londIDExpression2 = ((LongIDExpression) this.expression2);
		if ((londIDExpression1.getSymbol() != null) && (londIDExpression2.getSymbol() != null)) {
			AttributeSymbol enumAttribute1 = (AttributeSymbol) londIDExpression1.getSymbol();
			AttributeSymbol enumAttribute2 = (AttributeSymbol) londIDExpression2.getSymbol();
			int i = 0;
			BooleanExpression simplifiedExpression = new ImpliesExpression(new LongIDExpression(londIDExpression2.getNormalizedLongID()
					+ "."
					+ enumAttribute2.getBooleanAttribute(
							((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i).toString())
							.getID(), null), new FalseExpression());
			i++;
			while (i <= enumAttribute1.getNumberOfBooleanAttributes() - 1) {
				if (i == 1) {
					simplifiedExpression = new AndExpression(simplifiedExpression, new ImpliesExpression(new LongIDExpression(
							londIDExpression2.getNormalizedLongID()
									+ "."
									+ enumAttribute2.getBooleanAttribute(
											((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
													.toString()).getID(), null), new LongIDExpression(
							londIDExpression1.getNormalizedLongID()
									+ "."
									+ enumAttribute1.getBooleanAttribute(
											((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues()
													.get(i - 1).toString()).getID(), null)));
				} else {
					int j = 0;
					OrExpression orExpression = new OrExpression(new LongIDExpression(londIDExpression1.getNormalizedLongID()
							+ "."
							+ enumAttribute1.getBooleanAttribute(
									((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues().get(j)
											.toString()).getID(), null), new LongIDExpression(londIDExpression1.getNormalizedLongID()
							+ "."
							+ enumAttribute1.getBooleanAttribute(
									((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues().get(j + 1)
											.toString()).getID(), null));
					j = j + 2;
					while (j < i) {
						orExpression = new OrExpression(orExpression, new LongIDExpression(londIDExpression1.getNormalizedLongID()
								+ "."
								+ enumAttribute1.getBooleanAttribute(
										((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues().get(j)
												.toString()).getID(), null));
						j++;
					}
					simplifiedExpression = new AndExpression(simplifiedExpression, new ImpliesExpression(new LongIDExpression(
							londIDExpression2.getNormalizedLongID()
									+ "."
									+ enumAttribute2.getBooleanAttribute(
											((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
													.toString()).getID(), null), orExpression));
				}
				i++;
			}
			return simplifiedExpression;
		} else {
			if (londIDExpression1.getSymbol() != null) {
				String enumValue = ((LongIDExpression) this.expression2).getLongID();
				AttributeSymbol enumAttribute1 = (AttributeSymbol) londIDExpression1.getSymbol();
				int i = 0;
				while (!(enumValue.equals(((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues().get(i)
						.toString()))) {
					i++;
				}
				if (i == 0) {
					return new FalseExpression();
				} else {
					BooleanExpression simplifiedExpression = new NotExpression(new LongIDExpression(londIDExpression1.getNormalizedLongID()
							+ "."
							+ enumAttribute1.getBooleanAttribute(
									((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues().get(i)
											.toString()).getID(), null));
					while (i <= enumAttribute1.getNumberOfBooleanAttributes() - 1) {
						simplifiedExpression = new AndExpression(simplifiedExpression, new NotExpression(new LongIDExpression(
								londIDExpression1.getNormalizedLongID()
										+ "."
										+ enumAttribute1.getBooleanAttribute(
												((EnumSetExpressionSymbol) enumAttribute1.getSetExpressionSymbol()).getContainedValues()
														.get(i).toString()).getID(), null)));
						i++;
					}
					return simplifiedExpression;
				}
			} else {
				String enumValue = ((LongIDExpression) this.expression1).getLongID();
				AttributeSymbol enumAttribute2 = (AttributeSymbol) londIDExpression2.getSymbol();
				int i = 0;
				BooleanExpression simplifiedExpression = null;
				while (!(enumValue.equals(((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
						.toString()))) {
					if (simplifiedExpression == null) {
						simplifiedExpression = new NotExpression(new LongIDExpression(londIDExpression2.getNormalizedLongID()
								+ "."
								+ enumAttribute2.getBooleanAttribute(
										((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
												.toString()).getID(), null));
					} else {
						simplifiedExpression = new AndExpression(simplifiedExpression, new NotExpression(new LongIDExpression(
								londIDExpression2.getNormalizedLongID()
										+ "."
										+ enumAttribute2.getBooleanAttribute(
												((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues()
														.get(i).toString()).getID(), null)));
					}
					i++;
				}
				if (i == enumAttribute2.getNumberOfBooleanAttributes() - 1)
					return new FalseExpression();
				else if (i == 0)
					return new NotExpression(new LongIDExpression(londIDExpression2.getNormalizedLongID()
							+ "."
							+ enumAttribute2.getBooleanAttribute(
									((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
											.toString()).getID(), null));
				else
					return new AndExpression(simplifiedExpression, new NotExpression(new LongIDExpression(
							londIDExpression2.getNormalizedLongID()
									+ "."
									+ enumAttribute2.getBooleanAttribute(
											((EnumSetExpressionSymbol) enumAttribute2.getSetExpressionSymbol()).getContainedValues().get(i)
													.toString()).getID(), null)));
			}
		}
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
