/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.variable;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;

/**
 * @author mathieuacher
 * 
 */
public class IntegerVariable extends VariableImpl {

	protected int _intValue;

	public IntegerVariable(String name, int v) {
		this(name, v, NSFactory.mkEmpty());
	}

	public IntegerVariable(String name, int v, NameSpace ns) {
		this.name = name;
		this._intValue = v;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.ft.interpreter.Variable#getValue()
	 */
	@Override
	public String getSpecificValue() {
		return "" + _intValue;
	}

	@Override
	public RType getRType() {
		return RType.INTEGER;
	}

	@Override
	public Variable copy() {
		return new IntegerVariable(name, _intValue, ns);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof IntegerVariable) {
			IntegerVariable iw = (IntegerVariable) vari;
			setV(iw.getV());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/**
	 * @return the v
	 */
	public int getV() {
		return _intValue;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(int v) {
		this._intValue = v;
	}

}
