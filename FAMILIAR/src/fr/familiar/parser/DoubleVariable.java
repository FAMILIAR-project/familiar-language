package fr.familiar.parser;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;

public class DoubleVariable extends IntegerVariable {

	private double _doubleValue;

	public DoubleVariable(String name, double v) {
		this(name, v, NSFactory.mkEmpty());
	}

	public DoubleVariable(String name, double v, NameSpace ns) {
		super(name, (int) v, ns);
		this.name = name;
		_doubleValue = v;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	@Override
	public RType getRType() {
		return RType.DOUBLE;
	}

	@Override
	public Variable copy() {
		return new DoubleVariable(name, _doubleValue, ns);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof DoubleVariable) {
			DoubleVariable iw = (DoubleVariable) vari;
			setDouble(iw.getDouble());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	public void setDouble(double value) {
		_doubleValue = value;

	}

	public double getDouble() {
		return _doubleValue;
	}

	@Override
	public String getSpecificValue() {
		return "" + _doubleValue;
	}

}
