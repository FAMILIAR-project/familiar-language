package be.ac.fundp.info.TVLParser.SyntaxTree;


public class AttributeConditionnal {
	
	boolean ifIn;
	boolean ifOut;
	Expression expression1, expression2;
	SetExpression setExpression1, setExpression2;
	
	public String toString() {
		String text = new String("");
		if (this.ifIn && this.ifOut) {
			if (this.expression1 != null) {
				text = text.concat("ifin: is "+this.expression1.toString()+", ");
			}
			else {
				text = text.concat("ifin: is "+this.setExpression1.toString()+", ");
			}
			if (this.expression2 != null) {
				text = text.concat("ifin: is "+this.expression2.toString()+", ");
			}
			else {
				text = text.concat("ifin: is "+this.setExpression2.toString()+", ");
			}
		}
		else {
			if (this.ifIn) {
				if (this.expression1 != null) {
					text = text.concat("ifin: is "+this.expression1.toString());
				}
				else {
					text = text.concat("ifin: is "+this.setExpression1.toString());
				}
			}
			if (this.ifOut) {
				if (this.expression2 != null) {
					text = text.concat("ifin: is "+this.expression2.toString());
				}
				else {
					text = text.concat("ifin: is "+this.setExpression2.toString());
				}
			}
		}	
		return text;
	}
	
	/**
	 * @return the ifIn
	 */
	public boolean isIfIn() {
		return ifIn;
	}

	/**
	 * @return the ifOut
	 */
	public boolean isIfOut() {
		return ifOut;
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

	/**
	 * @return the setExpression1
	 */
	public SetExpression getSetExpression1() {
		return setExpression1;
	}

	/**
	 * @return the setExpression2
	 */
	public SetExpression getSetExpression2() {
		return setExpression2;
	}

	
	
	public AttributeConditionnal(Expression e1, Expression e2) {
		this.expression1 = e1;
		this.expression2 = e2;
		this.setExpression1 = null;
		this.setExpression2 = null;
		this.ifIn = true;
		this.ifOut = true;
	}
	
	public AttributeConditionnal(Expression e1, SetExpression e2) {
		this.expression1 = e1;
		this.setExpression2 = e2;
		this.expression2 = null;
		this.setExpression1 = null;
		this.ifIn = true;
		this.ifOut = true;
	}

	public AttributeConditionnal(SetExpression e1, Expression e2) {
		this.setExpression1 = e1;
		this.setExpression2 = null;
		this.expression2 = e2;
		this.expression1 = null;
		this.ifIn = true;
		this.ifOut = true;
	}

	public AttributeConditionnal(SetExpression e1, SetExpression e2) {
		this.expression1 = null;
		this.expression2 = null;
		this.setExpression1 = e1;
		this.setExpression2 = e2;
		this.ifIn = true;
		this.ifOut = true;
	}
	
	public AttributeConditionnal(boolean ifIn, Expression e1) {
		if (ifIn) {
			this.ifIn = true;
			this.ifOut = false;
			this.expression1 = e1;
			this.expression2 = null;
		}
		else {
			this.ifIn = false;
			this.ifOut = true;
			this.expression1 = null;
			this.expression2 = e1;
		}
		this.setExpression1 = null;
		this.setExpression2 = null;
	}
	
	public AttributeConditionnal(boolean ifIn, SetExpression e1) {
		if (ifIn) {
			this.ifIn = true;
			this.ifOut = false;
			this.setExpression1 = e1;
			this.setExpression2 = null;
		}
		else {
			this.ifIn = false;
			this.ifOut = true;
			this.setExpression1 = null;
			this.setExpression2 = e1;
		}
		this.expression1 = null;
		this.expression2 = null;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
