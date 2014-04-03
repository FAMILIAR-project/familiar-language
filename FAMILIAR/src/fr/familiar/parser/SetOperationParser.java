/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComparisonOperator;
import org.xtext.example.mydsl.fML.ComplexCommand;
import org.xtext.example.mydsl.fML.SetOperation;
import org.xtext.example.mydsl.fML.SetOperator;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class SetOperationParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public SetOperationParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public SetOperationParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
	}

	@Override
	public void eval() {

		assert (_command instanceof SetOperation);

		SetOperation bcmd = (SetOperation) _command;

		Command lcmd = bcmd.getLeft();
		Variable lVar = _environment.parse(lcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (left) : " + lVar);
		if (!(lVar instanceof SetVariable)) {
			FMLShell.getInstance().setError(
					"variable (left) " + lVar + " is not a set:"
							+ lVar.getRType());
			return;
		}

		ComplexCommand rcmd = bcmd.getRight();
		FMLShell.getInstance().printDebugMessage(
				"evaluating complex command... (right: " + rcmd + ") ");
		Variable rVar = _environment.parse(rcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (right) : " + rVar);
		if (!(rVar instanceof SetVariable)) {
			FMLShell.getInstance().setError(
					"variable (right) " + rVar + " is not a set:"
							+ rVar.getRType());
			return;
		}

		SetOperator cmpo = bcmd.getSop();

		SetVariable lsVar = (SetVariable) lVar;
		SetVariable rsVar = (SetVariable) rVar;

		Set<Variable> lsset = lsVar.getVars();
		Set<Variable> rsset = rsVar.getVars();

		// set operations ala SCALA --
		// http://www.scala-lang.org/docu/files/collections-api/collections_7.html
		if (cmpo == SetOperator.SUNION) {
			FMLShell.getInstance()
					.printDebugMessage("union (++) processing...");

			Set<Variable> vars = new HashSet<Variable>(lsset);
			safeAddAll(rsset, vars);

			setVariable(new SetVariable(vars, _assigner));
		}

		else if (cmpo == SetOperator.SDIFF) {
			FMLShell.getInstance().printDebugMessage("diff (--) processing...");
			Set<Variable> vars = new HashSet<Variable>(lsset);
			safeRemoveAll(rsset, vars);

			setVariable(new SetVariable(vars, _assigner));

		}

		else {
			FMLShell.getInstance().setError("unexpected set operator " + cmpo);
			return;
		}

	}

	private void safeRemoveAll(Set<Variable> rsset, Set<Variable> vars) {
		for (Variable rvar : rsset)
			try {
				safeRemove(rvar, vars);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	protected boolean safeRemove(Variable rvar, Set<Variable> vars)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		boolean found = false;
		Variable var2Remove = null;
		for (Variable var : vars) {
			if (new VariableComparator(var, rvar, ComparisonOperator.EQUAL)
					.eval()) {
				found = true;
				var2Remove = var;
			}
		}
		if (found)
			vars.remove(var2Remove);
		return found;

	}

	private void safeAddAll(Set<Variable> rsset, Set<Variable> vars) {
		for (Variable rvar : rsset)
			safeAdd(rvar, vars);

	}

	protected boolean safeAdd(Variable rvar, Set<Variable> vars) {
		boolean found = false;
		for (Variable var : vars) {
			try {
				if (new VariableComparator(var, rvar, ComparisonOperator.EQUAL)
						.eval()) {
					found = true;
				}
			} catch (Exception e) {
				// 
			}
		}
		if (!found)
			vars.add(rvar);

		return found;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return RType.SET;
	}

}
