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
package fr.familiar.parser;

import org.xtext.example.mydsl.fml.Assertion;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComplexCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 *         assert command evaluates a boolean expression (eq, neq, <=, >=, etc.)
 *         exists in case the boolean expression is false see:
 *         examples/testing/assert/*
 * 
 */
public class AssertAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public AssertAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public AssertAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {
		assert (_command instanceof Assertion);

		Assertion assertCmd = (Assertion) _command;
		ComplexCommand assertion = assertCmd.getAssertion();

		Variable v = _environment.parse(assertion);
		if (!(v instanceof BooleanVariable)) {
			FMLShell.getInstance().setError("boolean expected but " + v);
			return;
		}

		BooleanVariable bv = (BooleanVariable) v;
		if (!bv.isTrue()) {
			FMLShell.getInstance().setAssertionViolation(" " + assertion.getLeft());
			return;
		}

		/*
		 * Operator op = assertCmd.getOp(); VariableComparator comparator = new
		 * VariableComparator(lvar, rvar, op); if (!comparator.assertion())
		 * FMShell
		 * .getInstance().printAssertionViolation(comparator.getAssertionError
		 * ());
		 */

	}

}
