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

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComplexCommand;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class NotBooleanParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public NotBooleanParser(Command cmd2, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public NotBooleanParser(Command cmd2, String var, NameSpace ns,
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
		assert (_command instanceof ComplexCommand);
		ComplexCommand compCmd = (ComplexCommand) _command;

		Variable v = _environment.parse(compCmd, null);
		if (!(v instanceof BooleanVariable)) {
			FMLShell.getInstance().setError("boolean expected but " + v);
			return;
		}
		BooleanVariable bv = (BooleanVariable) v;
		FMLShell.getInstance().printDebugMessage(
				"NOT: negate on " + bv + " [" + bv.getValue() + "]");
		boolean bValue = !bv.isTrue();
		BooleanVariable nbv = new BooleanVariable(_assigner, bValue);
		setVariable(nbv);

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
