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
package fr.familiar.parser;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.VariableNull;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher VariableNull : 'isNull' var=FMCALC_ID ; determines if a
 *         variable is null see: examples/testing/shell/isnull.fmm
 * 
 */
public class VariableNullAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public VariableNullAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public VariableNullAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

	@Override
	public void eval() {
		assert (_command instanceof VariableNull);
		VariableNull vnullCmd = (VariableNull) _command;

		// TODO: rewrite
		Variable v = null;
		try {
			v = _environment.getVariable(vnullCmd.getVar());
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError(
					"variable does not exist: tries to use 'isExisting' first) "
							+ e.toString());
			return;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError(
					"(ambigous) variable does not exist: tries to use 'isExisting' first)"
							+ e.toString());
			return;
		}

		assert (v != null);

		boolean isNull = false;

		if (v.isNull()) { // domain specific?!
			isNull = true;
		}

		setVariable(new BooleanVariable(_assigner, isNull));

	}

}
