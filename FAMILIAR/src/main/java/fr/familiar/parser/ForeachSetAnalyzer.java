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

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ForeachSet;
import org.xtext.example.mydsl.fml.ScriptCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;

public class ForeachSetAnalyzer extends FMLAbstractCommandAnalyzer {
	
	private static Logger _LOGGER = Logger.getLogger(ForeachSetAnalyzer.class);



	public ForeachSetAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);
		assert (getCmd() instanceof ForeachSet);
	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {
		assert (getCmd() instanceof ForeachSet);
		ForeachSet foreachCmd = (ForeachSet) getCmd();

		EList<ScriptCommand> cmds = foreachCmd.getExprs();

		String strVarSet = foreachCmd.getVar();
		Variable varSet = null;
		try {
			varSet = _environment.getVariable(strVarSet);

			if (varSet instanceof RefVariable) {
				RefVariable refVarSet = (RefVariable) varSet;
				varSet = refVarSet.getValueReference();
			}

			if (! (varSet instanceof SetVariable)) {
			   FMLShell.getInstance().setError(
						"the variable " + strVarSet
								+ " is not a SET (in the foreach loop)");
				return;
			}
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError(
					"unable to retrieve the variable " + strVarSet
							+ " in the foreach loop");
			return;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError(
					"(ambigous) unable to retrieve the variable " + strVarSet
							+ " in the foreach loop");
			return;
		}

		assert (varSet != null);
		assert (varSet instanceof SetVariable);

		String strVal = foreachCmd.getVal(); // local value in the loop

		try {
			_environment.getVariable(strVal);
			// normally the variable should not exist!
			FMLShell.getInstance().setError(
					"the variable already exists" + strVal
							+ " in the environment.");
			return;

		} catch (VariableNotExistingException e) {
			_LOGGER.debug(
					"local variable does not exist: " + true);
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance()
					.printWarning(
							"similar variable " + strVal
									+ " found in the environment.");
		}

		// foreach loop create a new variable
		// (which references one of the variable of the set)
		// add to the environment
		// execute the commands

		SetVariable setVar2Loop = (SetVariable) varSet;
		Set<Variable> vars2loop = setVar2Loop.getVars();
		_LOGGER.debug(
				vars2loop.size() + " steps expected in the loop");
		int i = 0;
		for (Variable svar : vars2loop) {
			i++;
			_LOGGER.debug(
					"in the foreach (step_" + i + ")");
			Variable localVarLoop = new RefVariable(svar,
					new VariableIdentifier(strVal));

			List<Variable> beforeVars = _environment.getVariables(); // before
																		// parsing
																		// in
																		// the
																		// foreach
			// add temporary to the environment
			_environment.addOrReplaceVariable(localVarLoop.getIdentifier(),
					localVarLoop);
			// an.addVariable(localVarLoop); // be careful
			for (ScriptCommand ecmd : cmds) {
				// execute the command
				_LOGGER.debug(
						"in the foreach (execution): " + ecmd.toString());
				_environment.parse(ecmd);
			}
			List<Variable> afterVars = _environment.getVariables(); // after
																	// parsing
																	// all
																	// iterations
																	// in the
																	// foreach
			cleaningUp(beforeVars, afterVars);

			// TODO
			_environment.removeVariable(localVarLoop.getVid()); // remove

		}

	}

	private void cleaningUp(List<Variable> beforeVars, List<Variable> afterVars) {
		for (Variable af : afterVars) {
			boolean newVar = true;
			for (Variable bf : beforeVars) {
				if (af.equals(bf)) {
					newVar = false;
				}
			}
			if (newVar) {

				if (af instanceof RefVariable) { // TODO // remove only if it is
													// be a free variable!
					_LOGGER.debug(
							"\t\t\tfree variable in the foreach (delete): "
									+ af.getVid());
					_environment.removeVariable(af.getVid());
				}
			}
		}

	}

}
