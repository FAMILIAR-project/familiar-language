package be.ac.fundp.info.TVLParser.symbolTables;

import java.util.HashMap;
import java.util.Map;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.Util.IDGenerator;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.ExtensionDomainException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;

public class AttributeSymbol extends Symbol implements TypeSymbol, Cloneable {
	
	int type, trueType, DIMACS_ID;
	String id, userType;
	
	Map<String, AttributeSymbol> enumValueToBooleanAttribute; // Only used with enum attributes for the boolean form 
	
	String nonNormalizedID; //Only used during the normalization process
	
	Expression ifInExpression, ifOutExpression;
	SetExpression ifInSetExpression, ifOutSetExpression;
	
	SetExpressionSymbol setExpressionSymbol;
	Expression expression;
	
	public Map<String, AttributeSymbol> getBooleanAttributes() {
		return this.enumValueToBooleanAttribute;
	}
	
	public void initializeEnumValueToBooleanAttribute() {
		this.enumValueToBooleanAttribute = new HashMap<String, AttributeSymbol>();
	}
	
	public int getNumberOfBooleanAttributes() {
		return this.enumValueToBooleanAttribute.size();
	}
	
	public void addBooleanAttribute(String enumValue, AttributeSymbol booleanAttribute) {
		this.enumValueToBooleanAttribute.put(enumValue, booleanAttribute);
	}
	
	public AttributeSymbol getBooleanAttribute(String enumValue) {
		return this.enumValueToBooleanAttribute.get(enumValue);
	}
	
	public AttributeSymbol clone() {
		AttributeSymbol clonedAttributeSymbol = new AttributeSymbol(this.userType, this.trueType, this.id);
		clonedAttributeSymbol.nonNormalizedID = this.nonNormalizedID;
		clonedAttributeSymbol.type = this.type;
		clonedAttributeSymbol.ifInExpression = this.ifInExpression;
		clonedAttributeSymbol.ifOutExpression = this.ifOutExpression;
		clonedAttributeSymbol.ifInSetExpression = this.ifInSetExpression;
		clonedAttributeSymbol.ifOutSetExpression = this.ifOutSetExpression;
		clonedAttributeSymbol.expression = this.expression;
		if (this.hasASetExpressionSymbol()) {
			clonedAttributeSymbol.setExpressionSymbol = this.setExpressionSymbol.copy();
		}
		else {
			clonedAttributeSymbol.setExpressionSymbol = null;
		}
		return clonedAttributeSymbol;
	}
	
	// Utilisé pour les champs des records dans les types
	public AttributeSymbol(String userType, int trueType, String id) {
		this.id = id;
		this.nonNormalizedID = id;
		this.type = Expression.USER_CREATED;
		this.userType = userType;
		this.ifInExpression = null;
		this.ifOutExpression = null;
		this.ifInSetExpression = null;
		this.ifOutSetExpression = null;
		this.setExpressionSymbol = null;
		this.expression = null;
		this.trueType = trueType;
		this.DIMACS_ID =  IDGenerator.getInstance().getNewID(this);
	}
	
	public AttributeSymbol(String userType, int trueType, String id, SetExpressionSymbol setExpressionSymbol) {
		this.id = id;
		this.nonNormalizedID = id;
		this.type = Expression.USER_CREATED;
		this.userType = userType;
		this.ifInExpression = null;
		this.ifOutExpression = null;
		this.ifInSetExpression = null;
		this.ifOutSetExpression = null;
		this.setExpressionSymbol = setExpressionSymbol;
		this.expression = null;
		this.trueType = trueType;
		this.DIMACS_ID =  IDGenerator.getInstance().getNewID(this);
	}
	
	public AttributeSymbol(int Type, String ID, SetExpressionSymbol setExpressionSymbol) {
		this.id = ID;
		this.nonNormalizedID = ID;
		this.type = Type;
		this.setExpressionSymbol = setExpressionSymbol;
		this.userType = null;
		this.ifInExpression = null;
		this.ifOutExpression = null;
		this.ifInSetExpression = null;
		this.ifOutSetExpression = null;
		this.expression = null;
		this.trueType = type;
		this.DIMACS_ID =  IDGenerator.getInstance().getNewID(this);
	}
	
	public AttributeSymbol(int type, String ID) {
		this.id = ID;
		this.nonNormalizedID = ID;
		this.type = type;
		this.setExpressionSymbol = null;
		this.userType = null;
		this.ifInExpression = null;
		this.ifOutExpression = null;
		this.ifInSetExpression = null;
		this.ifOutSetExpression = null;
		this.expression = null;
		this.trueType = type;
		this.DIMACS_ID =  IDGenerator.getInstance().getNewID(this);
	}
	
	public String getNonNormalizedID() {
		return this.nonNormalizedID;
	}
	
	/**
	 * @return the ifInSetExpression
	 */
	public SetExpression getIfInSetExpression() {
		return ifInSetExpression;
	}

	/**
	 * @param ifInSetExpression the ifInSetExpression to set
	 */
	public void setIfInSetExpression(SetExpression ifInSetExpression) {
		this.ifInSetExpression = ifInSetExpression;
	}

	/**
	 * @return the ifOutSetExpression
	 */
	public SetExpression getIfOutSetExpression() {
		return ifOutSetExpression;
	}

	/**
	 * @param ifOutSetExpression the ifOutSetExpression to set
	 */
	public void setIfOutSetExpression(SetExpression ifOutSetExpression) {
		this.ifOutSetExpression = ifOutSetExpression;
	}

	public int getTrueType() {
		return trueType;
	}

	public void setID(String newID) {
		this.id = newID;
	}

	// Utilisé pours les attibuts des features
	/*
	public AttributeSymbol(String type, String id, SetExpressionSymbol setExpressionSymbol) {
		this.id = id;
		this.type = type;
		this.ifInExpression = null;
		this.ifOutExpression = null;
		this.setExpressionSymbol = setExpressionSymbol;
	}
	*/
	public AttributeSymbol() {
	}
	
	public boolean isARecordSymbol() {
		return false;
	}

	/**
	 * @return the ifInExpression
	 */
	public Expression getIfInExpression() {
		return ifInExpression;
	}

	/**
	 * @param ifInExpression the ifInExpression to set
	 */
	public void setIfInExpression(Expression ifInExpression) {
		this.ifInExpression = ifInExpression;
	}

	/**
	 * @return the ifOutExpression
	 */
	public Expression getIfOutExpression() {
		return ifOutExpression;
	}

	/**
	 * @param ifOutExpression the ifOutExpression to set
	 */
	public void setIfOutExpression(Expression ifOutExpression) {
		this.ifOutExpression = ifOutExpression;
	}

	/**
	 * @return the setExpressionSymbol
	 */
	public SetExpressionSymbol getSetExpressionSymbol() {
		return setExpressionSymbol;
	}

	/**
	 * @param setExpressionSymbol the setExpressionSymbol to set
	 */
	public void setSetExpressionSymbol(SetExpressionSymbol setExpressionSymbol) {
		this.setExpressionSymbol = setExpressionSymbol;
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public void printAttribute(String espace) {
		String s ="";
		if (this.type == Expression.INT) {
			if (this.hasASetExpressionSymbol()) {
				s = espace+"  |      int "+this.id+" in "+this.setExpressionSymbol.toString();
			}
			else {
				if (this.expression != null) {
					s = espace+"  |      int "+this.id+" is "+this.expression;
				}
				else {
					s = espace+"  |      int "+this.id;
				}
			}
		}
		if (this.type == Expression.REAL) {
			if (this.hasASetExpressionSymbol()) {
				s = espace+"  |      real "+this.id+" in "+this.setExpressionSymbol.toString();
			}
			else {
				if (this.expression != null) {
					s = espace+"  |      real "+this.id+" is "+this.expression;
				}
				else {
					s = espace+"  |      real "+this.id;
				}
			}
		}
		if (this.type == Expression.BOOL) {
			s = espace+"  |      bool "+this.id;
		}
		if (this.type == Expression.ENUM) {
			if (this.hasASetExpressionSymbol()) {
				s = espace+"  |      enum "+this.id+" in "+this.setExpressionSymbol.toString();
			}
			else {
				if (this.expression != null) {
					s = espace+"  |      enum "+this.id+" is "+this.expression;
				}
				else {
					s = espace+"  |      enum "+this.id;
				}
			}
		}
		if (this.type == Expression.USER_CREATED) {
			String trueType = "default";
			switch (this.trueType) {
			case Expression.INT : trueType = "int"; break;
			case Expression.REAL : trueType = "real"; break;
			case Expression.ENUM : trueType = "enum"; break;
			case Expression.STRUCT : trueType = "struct"; break;
			case Expression.BOOL : trueType = "bool"; break;
			}
			if (this.hasASetExpressionSymbol()) {
				s = espace+"  |      "+this.userType+" "+trueType+" "+this.id+" in "+this.setExpressionSymbol.toString();
			}
			else {
				if (this.expression != null) {
					s = espace+"  |      "+this.userType+" "+trueType+" "+this.id+" is "+this.expression;
				}
				else {
					s = espace+"  |      "+this.userType+" "+trueType+" "+this.id;
				}
			}
		}
		if (this.ifInExpression != null) {
			s = s.concat(", ifin: "+this.ifInExpression);
		}
		if (this.ifOutExpression != null) {
			s = s.concat(", ifout: "+this.ifOutExpression);
		}
		System.out.println(s);
	}
	
	public void printAttribute() {
		System.out.println("      "+this.type+" "+this.id);
	}

	@Override
	public void print() {
		System.out.println("  "+toString());
	}
	
	public String toString() {
		String typeName = "";
		switch (this.type) {
		case Expression.INT : typeName = "int"; break;
		case Expression.REAL : typeName = "real"; break;
		case Expression.BOOL : typeName = "bool"; break;
		case Expression.ENUM : typeName = "enum"; break;
		}
		if (!(this.setExpressionSymbol == null))
			return id+" "+typeName+" in "+this.setExpressionSymbol.toString();
		else
			return id+" "+typeName;
	}
	
	public boolean hasASetExpressionSymbol() {
		if (this.setExpressionSymbol == null) 
			return false;
		else
			return true;
	}
	
	public boolean hasIfInDeclaration () {
		if ((this.ifInExpression != null) || (this.ifInSetExpression != null))
			return true;
		else
			return false;
	}
	
	public boolean hasIfOutDeclaration () {
		if ((this.ifOutExpression != null) || (this.ifOutSetExpression != null))
			return true;
		else
			return false;
	}
	
	public boolean hasExpression() {
		if (this.expression != null)
			return true;
		else
			return false;
	}
	
	public int getDIMACS_ID() {
		return this.DIMACS_ID;
	}
	
	public int resolvePrimaryType() throws BadTypeUseException{
		if (this.getType() == Expression.USER_CREATED){
			return this.trueType;
		} else if (this.getType() == Expression.STRUCT){
			throw new BadTypeUseException("This Attribute Symbol doesn't have a PrimaryType, it's a STRUCT");
		} else {
			return this.type;
		}
	}
	
	/**
	 * check if setExpression is a restriction (or same domain) of the domain defined for this, 
	 * throw an ExtensionDomainException if setExpression is an extension of the domain of this
	 * 
	 * @param setExpression
	 * 		the set to compare with the domain of this
	 * 
	 * @return true if setExpression is a restriction of the domain defined for this, false if setExpression is null
	 * 
	 * @throws ExtensionDomainException if setExpression is an extension of the domain of this
	 */
	public boolean isRestriction(SetExpressionSymbol setExpression) throws ExtensionDomainException, NumberFormatException, SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SetExpressionMemberOutOfBoundException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException{
		
		if (setExpression == null){
			return false;
		} else {
			//check if restriction (or same value)	
			if (!this.setExpressionSymbol.containsSetExpressionSymbol(setExpression)){
				throw new ExtensionDomainException("The domain of the attribute " + this.id + "contains values : " + this.setExpressionSymbol.toString() + " , it can't be extended with the values : " + setExpression.toString());
			} else {
				return true;
			}
		}
	}

}
