package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.Util.Util;

public class SimpleType implements Type  {
	String ID;
	int type;
	SetExpression setExpression;
	
	public String toString() {
		if (this.setExpression != null) {
			return "    "+Util.toStringExpressionType(type)+" "+this.ID+" in "+this.setExpression.toString()+";\n";
		}
		else {
			return "    "+Util.toStringExpressionType(type)+" "+this.ID+";\n";
		}
	}

	public SimpleType(int type, String ID, SetExpression setExpression) {
		this.type = type;
		this.ID = ID;
		this.setExpression = setExpression;
	}
	
	public SimpleType(int type, String ID) {
		this.type = type;
		this.ID = ID;
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
	 * @return the setExpression
	 */
	public SetExpression getSetExpression() {
		return setExpression;
	}
	
	public boolean hasASetExpression() {
		if (this.setExpression == null)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean isARecord() {
		return false;
	}

	//Utilisé pour la construction du modèle
	@Override
	public boolean isAFeature() {
		return false;
	}

	@Override
	public boolean isAType() {
		return true;
	}

	@Override
	public boolean isAconstant() {
		return false;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
