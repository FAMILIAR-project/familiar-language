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

public class BooleanVariable extends VariableImpl {

	private boolean v;

	public BooleanVariable(String name, boolean v, NameSpace ns) {
		this.name = name;
		this.v = v;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	public BooleanVariable(String name, boolean v) {
		this(name, v, NSFactory.mkEmpty());
	}

	@Override
	public String getSpecificValue() {
		return v ? "true" : "false";
	}

	@Override
	public RType getRType() {
		return RType.BOOLEAN;
	}

	@Override
	public Variable copy() {
		return new BooleanVariable(name, v, ns);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof BooleanVariable) {
			BooleanVariable bw = (BooleanVariable) vari;
			setTrue(bw.isTrue());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/**
	 * @return the v
	 */
	public boolean isTrue() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setTrue(boolean v) {
		this.v = v;
	}

}
