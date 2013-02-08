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
package fr.unice.polytech.modalis.familiar.parser;

import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ConstraintExpr;
import org.xtext.example.mydsl.fML.FMCommand;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class ConstraintExprAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public ConstraintExprAnalyzer(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ConstraintExprAnalyzer(Command cmd2, NameSpace ns, String var,
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
		FMLShell.getInstance().printDebugMessage("constraint expression");
		assert (getCmd() instanceof ConstraintExpr);
		ConstraintExpr cstCmd = (ConstraintExpr) getCmd();

		FMCommand fmCmd = cstCmd.getFm();
		FMLShell.getInstance().printDebugMessage("withFM=" + fmCmd);
		if (fmCmd == null) {
			SetVariable cv = ConstraintsBuilder.createFromCNF(
					cstCmd.getConstraints(), _assigner); 
			setVariable(cv);
		} else {
			FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
			Set<Expression<String>> csts = fmv.getFm().getConstraints() ; 
			
			SetVariable cv = ConstraintsBuilder.createFromCNF(csts, _assigner) ; 
			setVariable(cv);
		}

	}

	/*
	 * (non-Javadoc)
	 * TODO: set of constraints
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return RType.SET;
	}

}
