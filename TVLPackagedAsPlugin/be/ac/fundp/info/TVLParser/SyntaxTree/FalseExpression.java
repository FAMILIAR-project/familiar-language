package be.ac.fundp.info.TVLParser.SyntaxTree;

public class FalseExpression implements BooleanExpression{
	
	public FalseExpression() {
		
	}
	
	public int getType() {
		return Expression.BOOL;
	}
	
	public boolean check() {
		return true;
	}
	
	public String toString() {
		return "false";
	}
	
	public Expression toNormalForm() {
		return new FalseExpression();
	}

	@Override
	public BooleanExpression removeArrows() {
		return this;
	}

	@Override
	public BooleanExpression toSimplifiedForm() {
		return this;
	}

	@Override
	public BooleanExpression distributeDisjunctions() {
		return this;
	}

	@Override
	public BooleanExpression distributeNegations() {
		return this;
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

	
}
