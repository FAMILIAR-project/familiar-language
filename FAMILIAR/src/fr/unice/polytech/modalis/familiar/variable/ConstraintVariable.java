package fr.unice.polytech.modalis.familiar.variable;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.parser.NameSpace;
import gsd.synthesis.Expression;

public class ConstraintVariable extends VariableImpl {
	
	private Expression<String> _constraint ;

 
	public ConstraintVariable(Expression<String> constraint,
			String name, NameSpace ns) {
		_constraint = constraint ; 
		this.name = name ;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	public ConstraintVariable(Expression<String> expr, String name) {
		this(expr, name, NSFactory.mkEmpty());
	}

	
	

	@Override
	public RType getRType() {
		return RType.CONSTRAINT ; 
	}

	@Override
	public Variable copy() {
		return new ConstraintVariable(_constraint, name);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof ConstraintVariable) {
			ConstraintVariable cv = (ConstraintVariable) vari;
			setConstraint(cv.getConstraint());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");
		
	}

	@Override
	public String getSpecificValue() {
		return _constraint.toString() ;  
	}
	
	public Expression<String> getConstraint() {
		return _constraint;
	}

	public void setConstraint(Expression<String> constraint) {
		_constraint = constraint;
	}
	
}
