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

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fML.IntegerExpr;
import org.xtext.example.mydsl.fML.impl.IntLiteralImpl;
import org.xtext.example.mydsl.fML.impl.IntegerOperationImpl;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

public class IntegerExpressionEval {

	private IntegerExpr cmd;

	/****** environment ********/
	private FMLCommandInterpreter an;

	public IntegerExpressionEval(IntegerExpr cmd, FMLCommandInterpreter an) {
		this.cmd = cmd;
		this.an = an;
	}

	public int eval() {
		return evalExpression(this.cmd);
	}

	private int evalExpression(IntegerExpr icmd) {

		if (icmd instanceof IntLiteralImpl) {

			IntLiteralImpl intcmd = (IntLiteralImpl) icmd;

			// TODO
			int val = intcmd.getValue();

			String value = null; // should be actually an integer (other are
									// supported differently)

			// TODO: currently we have problems with the grammar
			try {
				int v = Integer.parseInt(value);
				return v;
			} catch (NumberFormatException e) {
				// FMShell.getInstance().printDebugMessage("unable to parse value: "
				// + value + " (necessary a variable)");
			}

			// necessary a variable
			try {
				Variable var = an.getVariable(value);

				if (var instanceof RefVariable) {
					RefVariable refVar = (RefVariable) var;
					var = refVar.getValueReference();
				}

				if (var instanceof IntegerVariable) {
					return ((IntegerVariable) var).getV();
				}

				FMLShell.getInstance().setError(
						"the variable is not an integer: " + value);
				return -1;

			} catch (VariableNotExistingException e1) {

			} catch (VariableAmbigousConflictException e1) {

			}

			FMLShell.getInstance().setError(
					"integer expected: unable to parse value/command or retrieve variable "
							+ value);
			return -1;
		}

		else if (icmd instanceof IntegerOperationImpl) {
			IntegerOperationImpl oicmd = (IntegerOperationImpl) icmd;

			// TODO
			EObject leftExpr = oicmd.getLeft();
			int ileft = evalExpression(null);

			// TODO
			EObject rightExpr = oicmd.getLeft();
			int iright = evalExpression(null);

			// FMShell.getInstance().printDebugMessage("left: " + ileft +
			// " right: " + iright);

			String op = oicmd.getOp();
			if (op.equals("+"))
				return ileft + iright;
			else if (op.equals("-"))
				return ileft - iright;
			else if (op.equals("*"))
				return ileft * iright;
			else {
				FMLShell.getInstance().setError(
						"unable to parse integer value: operator " + op
								+ " unknown");
				return -1;
			}

		}

		else {
			FMLShell.getInstance().setError("unable to parse integer value ");
			return -1;
		}

	}

}
