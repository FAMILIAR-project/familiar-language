package be.ac.fundp.info.TVLParser.symbolTables;

import java.util.Vector;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.IntExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.RealExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ZeroExpression;
import be.ac.fundp.info.TVLParser.Util.Util;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.EnumIntervalSetExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.IllogicalIntervalSetExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;

public class IntervalSetExpressionSymbol implements SetExpressionSymbol {
	private String max, min, attributeID;
	private int type;
	
	public IntervalSetExpressionSymbol copy() {
		IntervalSetExpressionSymbol clonedIntervalSetExpressionSymbol =  new IntervalSetExpressionSymbol();
		clonedIntervalSetExpressionSymbol.min = this.min;
		clonedIntervalSetExpressionSymbol.max = this.max;
		clonedIntervalSetExpressionSymbol.type = this.type;
		return clonedIntervalSetExpressionSymbol;
	}
	
	public IntervalSetExpressionSymbol() {
		
	}
	
	public IntervalSetExpressionSymbol(int type, String min, String max, String attributeID) throws EnumIntervalSetExpressionException, IllogicalIntervalSetExpressionException {
		if ((!(type == Expression.REAL)) && (!(type == Expression.INT)))
			throw new EnumIntervalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the type of the interval [ "+min+".."+max+" ] is "+Util.toStringExpressionType(type)+" and not int or real.");
		
		this.type = type;
		this.attributeID = attributeID;
		
		if (min.equals("*")) {
			if (this.type == Expression.REAL) {
				// Solution temporaire
				//Float minFloat =  new Float(Float.MIN_VALUE);
				//this.min = minFloat.toString();
				Integer minInteger =  new Integer(Integer.MIN_VALUE);
				this.min = minInteger.toString();
			}
			else {
				Integer minInteger =  new Integer(Integer.MIN_VALUE);
				this.min = minInteger.toString();
			}
		}
		else {
			this.min = min;
		}
		if (max.equals("*")) {
			if (this.type == Expression.REAL) {
				// Solution temporaire
				//Float maxFloat =  new Float(Float.MAX_VALUE);
				//this.max = maxFloat.toString();
				Integer maxInteger =  new Integer(Integer.MAX_VALUE);
				this.max = maxInteger.toString();
			}
			else {
				Integer maxInteger =  new Integer(Integer.MAX_VALUE);
				this.max = maxInteger.toString();
			}
		}
		else {
			this.max = max;
		}
		this.check();
	}
	
	public String getMax() {
		return max;
	}

	public String getMin() {
		return min;
	}
	
	public void print() {
		System.out.println("	[ "+this.min+".."+this.max+" ]");
	}
	
	public String toString() {
		return "[ "+this.min+".."+this.max+" ]";
	}
	
	public boolean check() throws IllogicalIntervalSetExpressionException {
		try {
			if (this.type == Expression.REAL) {
				Float minFloat = Float.parseFloat(min);
				Float maxFloat = Float.parseFloat(max);
				if (minFloat > maxFloat)
					throw new IllogicalIntervalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the interval [ "+minFloat.toString()+".."+maxFloat.toString()+" ] is not valid, the lower bound must be smaller or equals to the upper bound.");
				else
					return true;
			}
			else {
				int minInteger = Integer.parseInt(min);
				int maxInteger = Integer.parseInt(max);
				if (minInteger > maxInteger) {
					throw new IllogicalIntervalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the interval [ "+minInteger+".."+maxInteger+" ] is not valid, the lower bound must be smaller or equals to the upper bound.");
				}
				else
					return true;
			}
		}
		catch (NumberFormatException e) {
			throw new IllogicalIntervalSetExpressionException("Type error : In the (sub-)attribute/type "+attributeID+", the interval [ "+this.min+".."+this.max+" ] is not valid. It contains a string or the interval type is int and it contains a real.");		
		}
	}
	
	public boolean containsExpression(Expression expression) throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException {
		try {
			if (this.type == Expression.REAL) {
				Float minFloat = Float.parseFloat(min);
				Float maxFloat = Float.parseFloat(max);
				if  ( (!(expression.getType() == Expression.REAL)) && (!(expression.getType() == Expression.INT)) ) {
					return false;
				}
				else {
					if ( (expression instanceof RealExpression) || (expression instanceof IntExpression)) {
						float floatExpr = Float.parseFloat(expression.toString());
						if ( ( floatExpr < minFloat) || (floatExpr > maxFloat) )
							return false;
						else
							return true;
					}
					else
						return true;
				}
			}
			else {
				Integer minInteger = Integer.parseInt(min);
				Integer maxInteger = Integer.parseInt(max);
				if  (!(expression.getType() == Expression.INT)) {
					return false;
				}
				else {
					if (expression instanceof IntExpression || expression instanceof ZeroExpression) {
						Integer integerExpr = Integer.parseInt(expression.toString());
						if ( ( integerExpr < minInteger) || (integerExpr > maxInteger) )
							return false;
						else
							return true;
					}
					else
						return true;
				}
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean containsSetExpression(SetExpression setExpression) throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, NumberFormatException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException {
		int setExpressionType = setExpression.getType();
		if (setExpressionType == Expression.ENUM)
			return false;
		if (setExpression.hasAnExpressionList()) {
			Vector<Expression> expressions = setExpression.getExpressionList().getExpressions();
			if (this.type == Expression.REAL) {
				int i = 0;
				while (i <= expressions.size()-1) {
					if (!this.containsExpression(expressions.get(i)))
						return false;
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
		else {
			if ((this.containsExpression(new LongIDExpression(setExpression.getMinExpression(),null))) && (this.containsExpression(new LongIDExpression(setExpression.getMaxExpression(),null))))
				return true;
			else
				return false;
		}	
	}

	@Override
	public boolean isAnEnumSetExpressionSymbol() {
		return false;
	}
	
	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}

	@Override
	public boolean containsSetExpressionSymbol(SetExpressionSymbol setExpressionSymbol)
			throws SetExpressionMemberViolatingAttributeTypeException,
			IllegalExpressionException, SymbolNotFoundException,
			AmbiguousReferenceException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException,
			SetExpressionMemberOutOfBoundException,
			ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException,
			SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, NumberFormatException,
			IDEnumValuesConflictException {
		int setExpressionType = setExpressionSymbol.getType();
		if (setExpressionType == Expression.ENUM)
			return false;
		if (setExpressionSymbol.isAnEnumSetExpressionSymbol()) {
			Vector<Expression> expressions = ((EnumSetExpressionSymbol) setExpressionSymbol).getContainedValues();
			if (this.type == Expression.REAL) {
				int i = 0;
				while (i <= expressions.size()-1) {
					if (!this.containsExpression(expressions.get(i)))
						return false;
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
		else {
			if (setExpressionType == Expression.INT) {
				if ((this.containsExpression(new IntExpression(((IntervalSetExpressionSymbol) setExpressionSymbol).getMin()))) && (this.containsExpression(new IntExpression(((IntervalSetExpressionSymbol) setExpressionSymbol).getMax()))))
					return true;
				else
					return false;
			}
			else {
				if ((this.containsExpression(new RealExpression(((IntervalSetExpressionSymbol) setExpressionSymbol).getMin()))) && (this.containsExpression(new RealExpression(((IntervalSetExpressionSymbol) setExpressionSymbol).getMax()))))
					return true;
				else
					return false;
			}
			
		}	
	}

	@Override
	public int getType() {
		return this.type;
	}
}
