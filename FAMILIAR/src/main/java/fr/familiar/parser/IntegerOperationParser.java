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

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.IntegerCommand;
import org.xtext.example.mydsl.fml.IntegerOperation;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class IntegerOperationParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public IntegerOperationParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public IntegerOperationParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eval() {

		assert (_command instanceof IntegerOperation);
		IntegerOperation icmd = (IntegerOperation) _command;
		String cmpo = icmd.getOp();

		Command lcmd = icmd.getLeft();
		Variable lVar = _environment.parse(lcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (left) : " + lVar);

		IntegerCommand rcmd = icmd.getRight();
		FMLShell.getInstance().printDebugMessage(
				"evaluating complex command... (right: " + rcmd + ") ");
		Variable rVar = _environment.parseIntegerCmd(rcmd, null);
		FMLShell.getInstance().printDebugMessage(
				"binary FML command (right) : " + rVar);
		
		if (lVar instanceof RefVariable) {
			lVar = ((RefVariable) lVar).getValueReference() ; 
		}
		
		if (rVar instanceof RefVariable) {
			rVar = ((RefVariable) rVar).getValueReference() ; 
		}

		if (!((lVar instanceof IntegerVariable))) {
			FMLShell.getInstance().setError("integer expected (lvar) " + lVar);
			return;
		}

		if (!((rVar instanceof IntegerVariable))) {
			FMLShell.getInstance().setError("integer expected (rvar) " + rVar);
			return;
		}

		assert ((lVar instanceof IntegerVariable) && (rVar instanceof IntegerVariable));

		IntegerVariable lbVar = (IntegerVariable) lVar;
		IntegerVariable rbVar = (IntegerVariable) rVar;

		int lbValue = lbVar.getV();
		int rbValue = rbVar.getV();

		if (cmpo.equals("+")) {
			setVariable(new IntegerVariable(_assigner, lbValue + rbValue));
		} else if (cmpo.equals("-")) {
			setVariable(new IntegerVariable(_assigner, lbValue - rbValue));
		} else if (cmpo.equals("mult")) { // TODO
			setVariable(new IntegerVariable(_assigner, lbValue * rbValue));
		} else if (cmpo.equals("/")) {
			// TODO: double!
			setVariable(new IntegerVariable(_assigner, lbValue / rbValue));
		} else if (cmpo.equals("^")) { // pow
			setVariable(new IntegerVariable(_assigner,
					(int) java.lang.Math.pow(
							Double.parseDouble(Integer.toString(lbValue)),
							Double.parseDouble(Integer.toString(rbValue)))));
		} else {
			FMLShell.getInstance().setError("unexpected integer operator");
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
		return RType.INTEGER;
	}

}
