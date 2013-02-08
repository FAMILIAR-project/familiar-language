package be.ac.fundp.info.TVLParser.SyntaxTree;

public class SubAttribute extends Attribute {
	
	String ID;
	int type;
	AttributeBody attributeBody;
	
	public SubAttribute(String ID, AttributeBody attributeBody) {
		this.ID = ID;
		this.attributeBody = attributeBody;
		this.type = Expression.STRUCT_FIELD;
	}
	
	public String toString(String whiteSpace) {
		return whiteSpace+this.ID+" "+this.attributeBody.toString()+";\n";
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
	 * @return the attributeBody
	 */
	public AttributeBody getAttributeBody() {
		return attributeBody;
	}
	
	public boolean hasAnAttributeBody() {
		if (this.attributeBody != null)
			return true;
		else
			return false;
	}
}
