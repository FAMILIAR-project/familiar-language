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
import org.xtext.example.mydsl.fml.UnMap;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;

/**
 * @author mathieuacher
 * 
 */
public class UnMapConstraint extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public UnMapConstraint(Command cmd2, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public UnMapConstraint(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (getCmd() instanceof UnMap);

		UnMap unmap = (UnMap) getCmd();
		FeatureModelVariable fmv = _environment.parseFMCommand(unmap.getFm(), null, null);

		boolean b = fmv.getFm().removeAllConstraints();
		assert (b);
		/*
		 * removeConstraint is buggy! Set<Expression<String>> constraints =
		 * fmv.getFm().getConstraints() ;
		 * FMLShell.getInstance().printDebugMessage("UnMapping constraints=" +
		 * constraints + " to fmv=" + fmv); // TODO: remove constraints from the
		 * feature model for (Expression<String> cst : constraints) { if ( !
		 * (fmv
		 * .getFm().removeConstraint(ExpressionParser.parseString(cst.toString()
		 * + ";"))))
		 * FMLShell.getInstance().printDebugMessage("unmap problems with cst=" +
		 * cst); }
		 */

		FMLShell.getInstance().printDebugMessage(
				"resulting fmv=" + fmv.getFm().toString());

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
		return RType.VOID;
	}

}
