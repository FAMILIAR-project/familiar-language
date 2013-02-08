package be.ac.fundp.info.TVLParser.SyntaxTree;

public class TrueExpression implements BooleanExpression{
	
	public TrueExpression() {
		
	}
	
	public int getType() {
		return Expression.BOOL;
	}
	
	public boolean check() {
		return true;
	}
	
	public String toString() {
		return "true";
	}
	
	public Expression toNormalForm() {
		return new TrueExpression();
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
