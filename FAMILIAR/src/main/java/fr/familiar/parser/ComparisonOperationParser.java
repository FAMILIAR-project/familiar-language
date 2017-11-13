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

import java.lang.reflect.InvocationTargetException;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComparisonOperation;
import org.xtext.example.mydsl.fml.ComparisonOperator;
import org.xtext.example.mydsl.fml.ComplexCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class ComparisonOperationParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public ComparisonOperationParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ComparisonOperationParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eval() {

		assert (_command instanceof ComparisonOperation);

		ComparisonOperation bcmd = (ComparisonOperation) _command;

		Command lcmd = bcmd.getLeft();
		Variable lVar = _environment.parse(lcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (left) : " + lVar);

		ComplexCommand rcmd = bcmd.getRight();
		FMLShell.getInstance().printDebugMessage(
				"evaluating complex command... (right: " + rcmd + ") ");
		Variable rVar = _environment.parse(rcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (right) : " + rVar);

		ComparisonOperator cmpo = bcmd.getCmpOp();

		// integers
		if ((cmpo == ComparisonOperator.GREATER_THAN)
				|| (cmpo == ComparisonOperator.LESSER_THAN)) {

			
			// TODO: DOUBLE
			if (!((lVar instanceof IntegerVariable))) {
				FMLShell.getInstance().setError(
						"integer expected (lvar) " + lVar);
				return;
			}

			if (!((rVar instanceof IntegerVariable))) {
				FMLShell.getInstance().setError(
						"integer expected (rvar) " + rVar);
				return;
			}

			assert ((lVar instanceof IntegerVariable) && (rVar instanceof IntegerVariable));

			IntegerVariable lbVar = (IntegerVariable) lVar;
			IntegerVariable rbVar = (IntegerVariable) rVar;

			int lbValue = lbVar.getV();
			int rbValue = rbVar.getV();

			if (cmpo == ComparisonOperator.GREATER_THAN) {
				setVariable(new BooleanVariable(_assigner, lbValue > rbValue));
			} else if (cmpo == ComparisonOperator.LESSER_THAN) {
				setVariable(new BooleanVariable(_assigner, lbValue < rbValue));
			}
			return;
		}

		else if ((cmpo == ComparisonOperator.EQUAL)
				|| (cmpo == ComparisonOperator.NOT_EQUAL)) {

			VariableComparator comparator = new VariableComparator(lVar, rVar,
					cmpo);
			try {
				setVariable(new BooleanVariable(_assigner, comparator.eval()));
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

		else if ((cmpo == ComparisonOperator.REF_EQUAL)
				|| (cmpo == ComparisonOperator.REF_NOT_EQUAL)) {

			if (cmpo == ComparisonOperator.REF_EQUAL) {
				setVariable(new BooleanVariable(_assigner, lVar == rVar));
			} else
				setVariable(new BooleanVariable(_assigner, lVar != rVar));

		}

		else {
			FMLShell.getInstance().setError(
					"unexpected comparison operator " + cmpo);
			return;
		}

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
		return RType.BOOLEAN;
	}

}
