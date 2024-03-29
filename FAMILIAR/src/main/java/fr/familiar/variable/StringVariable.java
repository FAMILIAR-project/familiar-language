/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.variable ; 

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;

public class StringVariable extends VariableImpl {

	private String val;

	public StringVariable(String name, Comparison cmp) {
		this(name, cmp.name(), NSFactory.mkEmpty());
	}

	public StringVariable(String name, Comparison rcomp, NameSpace ns) {
		this(name, rcomp.name(), ns);
	}

	public StringVariable(String name, String val) {
		this(name, val, NSFactory.mkEmpty());
	}

	public StringVariable(String var, String val, NameSpace ns) {
		this.name = var;
		this.val = val;
		this.ns = ns;
		this.vid = new VariableIdentifier(var, ns);
	}

	@Override
	public RType getRType() {
		return RType.STRING;
	}

	@Override
	public String getSpecificValue() {
		return val;
	}

	@Override
	public Variable copy() {
		return new StringVariable(name, val, ns);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof StringVariable) {
			StringVariable sw = (StringVariable) vari;
			setVal(sw.getVal());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/**
	 * @return the val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val           
	 */
	public void setVal(String val) {
		this.val = val;
	}
}