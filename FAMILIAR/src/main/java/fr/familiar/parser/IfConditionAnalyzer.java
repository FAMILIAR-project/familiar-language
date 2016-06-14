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

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComplexCommand;
import org.xtext.example.mydsl.fml.IfCondition;
import org.xtext.example.mydsl.fml.ScriptCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

public class IfConditionAnalyzer extends FMLAbstractCommandAnalyzer {

	IfConditionAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		assert (getCmd() instanceof IfCondition);
	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {
		assert (getCmd() instanceof IfCondition);

		IfCondition ifCmd = (IfCondition) getCmd();

		ComplexCommand bExpr = ifCmd.getBexpr();

		Variable varB = _environment.parse(bExpr, null);
		if (varB instanceof RefVariable)
			varB = ((RefVariable) varB).getValueReference();

		if (!(varB instanceof BooleanVariable)) {
			FMLShell.getInstance().setError(
					"boolean type expected but " + varB + " found");
			return;
		}

		BooleanVariable boolVar = (BooleanVariable) varB;

		// BooleanExpressionEval bep = new BooleanExpressionEval(bExpr,
		// getAn());
		boolean b = boolVar.isTrue();

		if (b) {
			EList<ScriptCommand> commands = ifCmd.getThen();
			for (ScriptCommand scmd : commands) {
				_environment.parse(scmd);
			}

		} else {
			EList<ScriptCommand> commands = ifCmd.getElse();
			for (ScriptCommand scmd : commands) {
				_environment.parse(scmd);
			}
		}

	}

}
