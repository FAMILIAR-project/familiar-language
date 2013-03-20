package be.ac.fundp.info.TVLParser.symbolTables;

import java.util.Vector;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ExpressionList;
import be.ac.fundp.info.TVLParser.SyntaxTree.IntExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.RealExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ZeroExpression;
import be.ac.fundp.info.TVLParser.Util.Util;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeSetExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.IllogicalSetExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.ManyIdenticalSetExpressionMembersException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;

public class EnumSetExpressionSymbol implements SetExpressionSymbol {
		
	private Vector<Expression> containedValues = new Vector<Expression>();
	
	private int type;
	
	private String attributeID;
	
	public void setExpressions(Vector<Expression> newExpressions) {
		this.containedValues = newExpressions;
	}
	
	public EnumSetExpressionSymbol copy() {
		EnumSetExpressionSymbol copiedEnumSetExpressionSymbol = new EnumSetExpressionSymbol();
		copiedEnumSetExpressionSymbol.attributeID = this.attributeID;
		copiedEnumSetExpressionSymbol.type = this.type;
		/////////////////////////////////////////////////////////////
		copiedEnumSetExpressionSymbol.containedValues = this.containedValues;
		return copiedEnumSetExpressionSymbol;
	}
	
	public EnumSetExpressionSymbol() {
		
	}
	
	public EnumSetExpressionSymbol(int type, ExpressionList expressions, String attributeID, FeaturesSymbolTable featuresSymbolTable) throws IllogicalSetExpressionException, ManyIdenticalSetExpressionMembersException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, BadTypeSetExpressionException, SharedFeatureUsingParentSelectorException, IDEnumValuesConflictException, ChildrenFeatureNotFoundException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		this.attributeID = attributeID;
		this.type = type;
		this.checkContainedValues(expressions.getExpressions());
		featuresSymbolTable.checkIDEnumValuesConflict(this);	
	}
	
	public EnumSetExpressionSymbol(int type, ExpressionList expressions) throws IllogicalSetExpressionException, ManyIdenticalSetExpressionMembersException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, BadTypeSetExpressionException, SharedFeatureUsingParentSelectorException, IDEnumValuesConflictException, ChildrenFeatureNotFoundException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		this.attributeID = null;
		this.type = type;
		this.checkContainedValues(expressions.getExpressions());
	}
	
	public void print() {
		int i = 0;
		String s = "	{ "+containedValues.get(i).toString();
		i = 1;
		while (i <= this.containedValues.size()-1) {
			s = s.concat(", "+containedValues.get(i).toString());
			i++;
		}
		s = s.concat(" }");
		System.out.println(s);
	}
	
	public String toString() {
		int i = containedValues.size()-2;
		String s = "{ "+containedValues.get(containedValues.size()-1).toString();
		while (i >= 0) {
			s = s.concat(", "+containedValues.get(i).toString());
			i--;
		}
		s = s.concat(" }");
		return s;
	}
	
	public boolean checkContainedValues(Vector<Expression> expressions) throws IllogicalSetExpressionException, ManyIdenticalSetExpressionMembersException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, BadTypeSetExpressionException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (this.type == Expression.INT) {
			int i = 0;
			while (i <= expressions.size()-1) {
				if (expressions.get(i).getType() != Expression.INT)
					throw new IllogicalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the declared type of the set expression  is int but it contains a "+Util.toStringExpressionType(expressions.get(i).getType())+".");
				else {
					if (expressions.get(i) instanceof IntExpression || expressions.get(i) instanceof ZeroExpression) {
						if (this.containsExpression(expressions.get(i)))
							throw new ManyIdenticalSetExpressionMembersException("Type error : In the (sub-)attribute/type "+attributeID+", the set expression contains many identical values ( "+expressions.get(i).toString()+" ).");
						else
							this.containedValues.add(expressions.get(i));
					}
					else {
						throw new BadTypeSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the set expression is invalid. In a type declaration, a set expression may only contain simple values (integer, real or enum)");
					}
				}
				i++;	
			}
			return true;
		}
		if (this.type == Expression.REAL) {
			int i = 0;
			while (i <= expressions.size()-1) {
				if ((expressions.get(i).getType() != Expression.REAL) && (expressions.get(i).getType() != Expression.INT))
					throw new IllogicalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the declared type of the set expression is int real it contains a "+Util.toStringExpressionType(expressions.get(i).getType())+".");
				else {
					if ((expressions.get(i) instanceof RealExpression) || (expressions.get(i) instanceof IntExpression) || (expressions.get(i) instanceof ZeroExpression)) {
						if (this.containsExpression(expressions.get(i)))
							throw new ManyIdenticalSetExpressionMembersException("Type error : In the (sub-)attribute/type "+attributeID+", the set expression contains many identical values ( "+expressions.get(i).toString()+" ).");
						else
							this.containedValues.add(expressions.get(i));
					}
					else {
						throw new BadTypeSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the set expression is invalid. In a type declaration, a set expression may only contain simple values (integer, real or enum).");
					}
				}
				i++;	
			}
			return true;
		}
		else {
			int i = 0;
			while (i <= expressions.size()-1) {
				if (expressions.get(i).getType() != Expression.ENUM)
					throw new IllogicalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the declared type of the set expression "+this.toString()+" is enum but it contains a "+Util.toStringExpressionType(expressions.get(i).getType())+".");
				else {
					if (this.containsExpression(expressions.get(i)))
						throw new ManyIdenticalSetExpressionMembersException("Type error : In the (sub-)attribute/type "+attributeID+", the set expression "+this.toString()+" contains many identical values ( "+expressions.get(i).toString()+" ).");
					else
						this.containedValues.add(expressions.get(i));
				}
				i++;	
			}
			return true;
		}
	}

	/**
	 * @return the content
	 */
	public Vector<Expression> getContainedValues() {
		return containedValues;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	@Override
	public boolean containsExpression(Expression expression) throws SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (this.type == Expression.REAL) {
			if ((expression.getType() != Expression.REAL) && (expression.getType() != Expression.INT)) {
				return false;
			}
			else {
				if ((expression instanceof RealExpression) || (expression instanceof IntExpression) || (expression instanceof ZeroExpression)) {
					int i = 0;
					String expressionString = expression.toString();
					while (i <= this.containedValues.size()-1) {
						if (Float.parseFloat(this.containedValues.get(i).toString()) == Float.parseFloat(expressionString)) {
							System.out.println("Je me casse !");
							return true;
						}
						i++;
					}
					return false;
				}
				else
					return true;
			}
		}
		else {
			if (this.type == Expression.INT) {
				if (expression.getType() != Expression.INT){
					return false;	
				}
				else {
					if (expression instanceof IntExpression || (expression instanceof ZeroExpression)) {
						int i = 0;
						String expressionString = expression.toString();
						while (i <= this.containedValues.size()-1) {
							if (Integer.parseInt(this.containedValues.get(i).toString()) == Integer.parseInt(expressionString))
								return true;
							i++;
						}
						return false;
					}
					else
						return true;
				}
			}
			else {
				if (expression.getType() != Expression.ENUM) {
					return false;
				}
				else {
					int i = 0;
					String expressionString = expression.toString();
					while (i <= this.containedValues.size()-1) {
						if (this.containedValues.get(i).toString().equals(expressionString))
							return true;
						i++;
					}
					return false;
				}
			}
		}
	}
	/**
	 * Only used with <, <=, >, >= and with enum expressions.
	 */
	public boolean hasTheSameEnumValuesDomain(Vector<Expression> expressions) {
		int i = 0;
		if (this.containedValues.size() != expressions.size())
			return false;
		while (i <= this.containedValues.size()-1) {
			if (!(((LongIDExpression) this.containedValues.get(i)).getLongID().equals(((LongIDExpression) expressions.get(i)).getLongID())))
				return false;
			i++;
		}
		return true;
	}
	
	public boolean containsSetExpressionSymbol(SetExpressionSymbol setExpressionSymbol) throws NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (setExpressionSymbol.isAnEnumSetExpressionSymbol()) {
			Vector<Expression> expressions = ((EnumSetExpressionSymbol) setExpressionSymbol).getContainedValues();
			if (this.type == Expression.REAL) {
				int i = 0;
				while (i <= expressions.size()-1) {
					if  (!this.containsExpression(expressions.get(i)))
							return false;
					i++;
				}
				return true;
			}
			else {
				if (this.type == Expression.INT) {
					int i = 0;
					while (i <= expressions.size()-1) {
						if (!this.containsExpression(expressions.get(i)))
							return false;
						i++;
					}
					return true;
				}
				else {
					int i = 0;
					while (i <= expressions.size()-1) {
						if (!this.containsExpression(expressions.get(i)))
							return false;
						i++;
					}
					return true;
				}
			}
		}
		else {
			if (this.type == Expression.ENUM)
				return false;
			else {
				int setExpressionType = setExpressionSymbol.getType();
				if (this.type == Expression.INT) {
					if (setExpressionType == Expression.INT) {
						int i = Integer.parseInt(((IntervalSetExpressionSymbol)setExpressionSymbol).getMin());
						int max = Integer.parseInt(((IntervalSetExpressionSymbol)setExpressionSymbol).getMax());
						while (i <= max) {
							if (!this.containsExpression(new IntExpression(new Integer(i).toString())))
								return false;
							i++;
						}
						return true;
					}
					else
						return false;
				}
				else {
					if (setExpressionType == Expression.REAL)
						return true;
					else
						return false;
				}
			}
		}
	}
	
	public boolean containsSetExpression(SetExpression setExpression) throws NumberFormatException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (setExpression.hasAnExpressionList()) {
			Vector<Expression> expressions = setExpression.getExpressionList().getExpressions();
			if (this.type == Expression.REAL) {
				int i = 0;
				while (i <= expressions.size()-1) {
					if  (!this.containsExpression(expressions.get(i)))
							return false;
					i++;
				}
				return true;
			}
			else {
				if (this.type == Expression.INT) {
					int i = 0;
					while (i <= expressions.size()-1) {
						if (!this.containsExpression(expressions.get(i)))
							return false;
						i++;
					}
					return true;
				}
				else {
					int i = 0;
					while (i <= expressions.size()-1) {
						if (!this.containsExpression(expressions.get(i)))
							return false;
						i++;
					}
					return true;
				}
			}
		}
		else {
			if (this.type == Expression.ENUM)
				return false;
			else {
				int setExpressionType = setExpression.getType();
				if (this.type == Expression.INT) {
					if (setExpressionType == Expression.INT) {
						int i = Integer.parseInt(setExpression.getMinExpression());
						int max = Integer.parseInt(setExpression.getMaxExpression());
						while (i <= max) {
							if (!this.containsExpression(new IntExpression(new Integer(i).toString())))
								return false;
							i++;
						}
						return true;
					}
					else
						return false;
				}
				else {
					if (setExpressionType == Expression.REAL)
						return true;
					else
						return false;
				}
			}
		}
	}

	@Override
	public boolean isAnEnumSetExpressionSymbol() {
		return true;
	}
	
	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}
}
