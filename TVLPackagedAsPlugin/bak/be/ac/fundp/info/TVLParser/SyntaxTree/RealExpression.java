package be.ac.fundp.info.TVLParser.SyntaxTree;

public class RealExpression implements Expression {
	
	float value;
	
	public RealExpression(String value) {
		this.value = Float.parseFloat(value);
	}
	
	public float getValue() {
		return this.value;
	}
	
	public String toString() {
		Float f = new Float(this.value);
		return f.toString();
	}
	
	public int getType() {
		return Expression.REAL;
	}
	
	public boolean check() {
		return true;
	}
	
	public Expression toNormalForm() {
		return new RealExpression(String.valueOf(this.value));
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
