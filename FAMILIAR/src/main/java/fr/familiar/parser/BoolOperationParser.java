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

import org.xtext.example.mydsl.fml.BOOL_Operator;
import org.xtext.example.mydsl.fml.BoolOperation;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComplexCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class BoolOperationParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public BoolOperationParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public BoolOperationParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);

	}

	@Override
	public void eval() {

		assert (_command instanceof BoolOperation);
		BoolOperation bcmd = (BoolOperation) _command;

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

		if (!((lVar instanceof BooleanVariable))) {
			FMLShell.getInstance().setError("boolean expected (lvar) " + lVar);
			return;
		}

		if (!((rVar instanceof BooleanVariable))) {
			FMLShell.getInstance().setError("boolean expected (rvar) " + rVar);
			return;
		}

		assert ((lVar instanceof BooleanVariable) && (rVar instanceof BooleanVariable));

		BooleanVariable lbVar = (BooleanVariable) lVar;
		BooleanVariable rbVar = (BooleanVariable) rVar;

		boolean lbValue = lbVar.isTrue();
		boolean rbValue = rbVar.isTrue();

		BOOL_Operator bop = bcmd.getOp();

		boolean res = false;
		if (bop == BOOL_Operator.BOOL_AND) {
			res = lbValue && rbValue;

		} else if (bop == BOOL_Operator.BOOL_OR) {
			res = lbValue || rbValue;

		} else {
			FMLShell.getInstance().setError("unexpected boolean operator");
			return;
		}

		setVariable(new BooleanVariable(_assigner, res));

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
