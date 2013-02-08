package be.ac.fundp.info.TVLParser.SyntaxTree;

public class IntExpression implements Expression {

	int value;
	
	public IntExpression(String value) {
		this.value = Integer.parseInt(value);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String toString() {
		Integer integer = new Integer(this.value);
		return integer.toString();
	}
	
	public int getType() {
		return Expression.INT;
	}
	
	public boolean check() {
		return true;
	}
	
	public Expression toNormalForm() {
		return new IntExpression(String.valueOf(this.value));
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
	
}
