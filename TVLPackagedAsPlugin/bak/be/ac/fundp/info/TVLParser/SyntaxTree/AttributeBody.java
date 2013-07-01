package be.ac.fundp.info.TVLParser.SyntaxTree;

public class AttributeBody {
	Expression isExpression;
	SetExpression inSetExpression;
	AttributeConditionnal attributeConditionnal;
	
	public String toString() {
		if (this.hasAnIsExpression()) {
			return "is "+this.isExpression.toString();
		}
		else {
			if (this.hasAnInSetExpression()) {
				if (this.hasAnAttributeConditionnal()) {
					return "in "+this.inSetExpression.toString()+", "+this.attributeConditionnal.toString();
				}
				else {
					return "in "+this.inSetExpression.toString();
				}
			}
			else {
				if (this.hasAnAttributeConditionnal()) {
					return ", "+this.attributeConditionnal.toString();
				}
				else {
					return "";
				}
			}
		}
	}
	
	public AttributeBody(Expression isExpression) {
		this.isExpression = isExpression;
		this.inSetExpression = null;
		this.attributeConditionnal = null;
	}
	
	public AttributeBody(SetExpression inSetExpression) {
		this.isExpression = null;
		this.inSetExpression = inSetExpression;
		this.attributeConditionnal = null;
	}
	
	public AttributeBody(AttributeConditionnal attributeConditionnal) {
		this.isExpression = null;
		this.inSetExpression = null;
		this.attributeConditionnal = attributeConditionnal;
	}
	
	public AttributeBody(SetExpression inSetExpression, AttributeConditionnal attributeConditionnal) {
		this.isExpression = null;
		this.inSetExpression = inSetExpression;
		this.attributeConditionnal = attributeConditionnal;
	}
	
	public boolean hasAnIsExpression() {
		if (this.isExpression == null)
			return false;
		else
			return true;
	}
	
	public boolean hasAnInSetExpression() {
		if (this.inSetExpression == null)
			return false;
		else
			return true;
	}
	
	public boolean hasAnAttributeConditionnal() {
		if (this.attributeConditionnal == null)
			return false;
		else 
			return true;
	}

	/**
	 * @return the isExpression
	 */
	public Expression getIsExpression() {
		return isExpression;
	}

	/**
	 * @return the inSetExpression
	 */
	public SetExpression getInSetExpression() {
		return inSetExpression;
	}

	/**
	 * @return the attributeConditionnal
	 */
	public AttributeConditionnal getAttributeConditionnal() {
		return attributeConditionnal;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

}
