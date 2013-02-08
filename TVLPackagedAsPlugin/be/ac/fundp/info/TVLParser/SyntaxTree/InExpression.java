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

public class InExpression implements BooleanExpression {
	
	Expression expression;
	SetExpression setExpression;
	
	public InExpression(Expression e1, SetExpression e2) {
		this.expression = e1;
		this.setExpression = e2;
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @return the setExpression
	 */
	public SetExpression getSetExpression() {
		return setExpression;
	}

	@Override
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException {
		if (((expression.getType() == Expression.REAL) && (this.setExpression.getType() == Expression.REAL)) || ((expression.getType() == Expression.INT) && (this.setExpression.getType() == Expression.INT)) || ((expression.getType() == Expression.REAL) && (setExpression.getType() == Expression.INT))) {
			AttributeSymbol numericalAttribute = (AttributeSymbol) ((LongIDExpression)this.expression).getSymbol();
			if (numericalAttribute.hasASetExpressionSymbol()) {
				if (numericalAttribute.getSetExpressionSymbol().containsSetExpression(setExpression))
					return Expression.BOOL;
				else
					throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is not valid. The values domain of "+numericalAttribute.getID()+" doesn't include the set expression "+this.setExpression.toString());
			}
			else return Expression.BOOL;
		}
		else {
			if ((this.expression.getType() == Expression.ENUM) && (this.setExpression.getType() == Expression.ENUM)) {
				LongIDExpression longIDExpression = (LongIDExpression) this.expression;
				if (longIDExpression.getSymbol() != null) {
					AttributeSymbol enumAttribute = (AttributeSymbol) longIDExpression.getSymbol();
					if (enumAttribute.getSetExpressionSymbol().containsSetExpression(this.setExpression)) {
						return Expression.BOOL;
					}
					else {
						throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The values domain of the attribute "+this.expression.toString()+" doesn't include "+this.setExpression.expressionList.toString()+".");
					}
				}
				else
					throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The attribute "+this.expression.toString()+" cannot be found.");
			}
			else {
				throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The type "+Util.toStringExpressionType(this.expression.getType())+" of the left paramater ( "+this.expression.toString()+" ) is different from the type "+Util.toStringExpressionType(this.setExpression.getType())+" of the set expression ( "+this.setExpression.toString()+" ).");
			}
		}	
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.expression.toString());
		sb.append(" in ");
		sb.append(this.setExpression.toString());
		return sb.toString();
	}
	
	public Expression toNormalForm() {
		return new InExpression(this.expression.toNormalForm(), this.setExpression.toNormalForm());
	}
	
	@Override
	public BooleanExpression toSimplifiedForm() {
		LongIDExpression longIDExpression = (LongIDExpression) this.expression;
		AttributeSymbol enumAttribute = (AttributeSymbol) longIDExpression.symbol;
		Object[] enumValues =  (Object[]) enumAttribute.getBooleanAttributes().keySet().toArray();
		BooleanExpression simplifiedExpression = null;
		int i =0;
		while (i <= enumAttribute.getNumberOfBooleanAttributes() -1) {
			int j = 0;
			boolean foundValue = false;
			while (j <= this.setExpression.getExpressionList().getExpressions().size()-1) {
				if (enumValues[i].toString().equals(((LongIDExpression)this.setExpression.getExpressionList().getExpressions().get(j)).getLongID())) {
					foundValue = true;
					break;
				}
				j++;
			}
			if (foundValue == false) {
				if (simplifiedExpression == null) {
					simplifiedExpression = new NotExpression(new LongIDExpression(longIDExpression.getNormalizedLongID()+"."+enumAttribute.getBooleanAttribute(enumValues[i].toString()).getID(), null));
				}
				else {
					simplifiedExpression =  new AndExpression(simplifiedExpression, new NotExpression(new LongIDExpression(longIDExpression.getNormalizedLongID()+"."+enumAttribute.getBooleanAttribute(enumValues[i].toString()).getID(), null)));
				}
			}
			i++;
		}
		//TODO Optimiser ce passage...il va falloir gérer le cas où une booleanexpression peut être nulle partout...
		if (simplifiedExpression == null) {
			simplifiedExpression = new LongIDExpression(longIDExpression.getNormalizedLongID()+"."+enumAttribute.getBooleanAttribute(enumValues[0].toString()).getID(), null);
			i = 1;
			while (i <= enumAttribute.getNumberOfBooleanAttributes() -1) {
				simplifiedExpression = new OrExpression(simplifiedExpression, new LongIDExpression(longIDExpression.getNormalizedLongID()+"."+enumAttribute.getBooleanAttribute(enumValues[i].toString()).getID(), null));
				i++;
			}
			return simplifiedExpression;
		}
		else {
			return simplifiedExpression;
		}
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
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

	

}
