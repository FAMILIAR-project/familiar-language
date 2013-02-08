package be.ac.fundp.info.TVLParser.SyntaxTree;


public class ZeroExpression implements Expression {
	
	public ZeroExpression(){
		
	}

	@Override
	public int getType() {
		return Expression.INT;
	}
	
	public String toString() {
		return "0";
	}
	
	public Expression toNormalForm() {
		return new ZeroExpression();
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
