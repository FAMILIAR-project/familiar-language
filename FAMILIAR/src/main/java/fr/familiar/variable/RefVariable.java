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

public class RefVariable extends VariableImpl {

	Variable v;

	@Deprecated
	public RefVariable(Variable v) {
		this(v, v.getVid());
	}

	public RefVariable(Variable v, VariableIdentifier vid) {
		setVid(vid);
		this.v = v;
	}

	@Override
	public void setVid(VariableIdentifier vid) {
		super.setVid(vid);
		this.name = vid.getName();
		this.ns = vid.getNs();
	}

	@Override
	public RType getRType() {
		return v.getRType();
	}

	@Override
	public String getType() {
		return super.getType() + " (ref) to: " + getValueReference().getVid();
	}

	@Override
	public String getSpecificValue() {
		return v.getValue();
	}

	public Variable getReference() {
		return v;
	}

	@Override
	public Variable copy() {
		return new RefVariable(v, vid);
	}

	public void setReference(Variable var2Save) {
		// TODO: control that the var2Save is not a reference whose reference is
		// 'this'
		this.v = var2Save;
		if (var2Save instanceof RefVariable) {
			FMLShell.getInstance().printWarning("Itself a reference!");
		}
	}

	public void setValue(Variable vari) {
		// TODO: check?
		v.setValue(vari);
	}

	public Variable getValueReference() { // reference to a reference to a
											// reference (dangerous!)
		if (v instanceof RefVariable) {
			return ((RefVariable) v).getValueReference();
		}
		return v;

	}

}
