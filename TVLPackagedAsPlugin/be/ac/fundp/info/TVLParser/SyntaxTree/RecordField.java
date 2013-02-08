package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.Util.Util;

public class RecordField {
	String ID;
	int type;
	String userType;
	SetExpression setExpression;
	
	public RecordField() {	
	}
	
	public RecordField(SimpleType simpleType) {
		this.userType = null;
		this.ID = simpleType.getID();
		this.type = simpleType.getType();
		this.setExpression = simpleType.setExpression;
	}
	
	public String toString() {
		if (this.type == Expression.USER_CREATED) {
			return "    "+this.userType+" "+this.ID+";\n";
		}
		else {
			if (this.setExpression != null) {
				return "    "+Util.toStringExpressionType(type)+" "+this.ID+" in "+this.setExpression.toString()+";\n";
			}
			else {
				return "    "+Util.toStringExpressionType(type)+" "+this.ID+";\n";
			}
		}
	}
	
	public RecordField(String type, String ID) {
		this.userType = type;
		this.ID = ID;
		this.type = Expression.USER_CREATED;
		this.setExpression = null;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
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
	 * @return the setExpression
	 */
	public SetExpression getSetExpression() {
		return setExpression;
	}

	public Boolean hasASetExpression() {
		if (this.setExpression == null)
			return false;
		else
			return true;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

}
