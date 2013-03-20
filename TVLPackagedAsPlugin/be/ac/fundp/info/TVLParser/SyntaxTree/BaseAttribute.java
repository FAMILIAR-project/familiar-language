package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.Util.Util;

public class BaseAttribute extends Attribute {
	int type;
	String ID;
	String userType = null;
	AttributeBody attributeBody;
	
	public BaseAttribute(int type, String ID, AttributeBody attributeBody) {
		this.type = type;
		this.ID = ID;
		this.attributeBody = attributeBody;
	}
	
	public BaseAttribute(String type, String ID, AttributeBody attributeBody) {
		this.type = Expression.USER_CREATED;
		this.ID = ID;
		this.userType = type;
		this.attributeBody = attributeBody;
	}
	
	public String toString() {
		if (this.type == Expression.USER_CREATED) {
			if (this.attributeBody != null) {
				return this.userType+" "+this.ID+" "+this.attributeBody.toString()+";";
			}
			else {
				return this.userType+" "+this.ID+";";
			}
		}
		else {
			if (this.attributeBody != null) {
				return Util.toStringExpressionType(type)+" "+this.ID+" "+this.attributeBody.toString()+";";
			}
			else {
				return Util.toStringExpressionType(type)+" "+this.ID+";";
			}
		}
	}
	
	public boolean isABaseAttribute() {
		return true;
	}
	
	public int getType() {
		return type;
	}

	public String getID() {
		return ID;
	}

	public String getUserType() {
		return userType;
	}

	public AttributeBody getAttributeBody() {
		return attributeBody;
	}
	
	//Utilisé pour la construction d'une feature
	public boolean isAData() {
		return false;
	}
	
	public boolean isAnAttribute() {
		return true;
	}
	
	public boolean isAConstraint() {
		return false;
	}
	
	public boolean isAFeatureGroup() {
		return false;
	}
	
	public boolean hasAnAttributeBody() {
		if (this.attributeBody != null) 
			return true;
		else
			return false;
	}
	
}
