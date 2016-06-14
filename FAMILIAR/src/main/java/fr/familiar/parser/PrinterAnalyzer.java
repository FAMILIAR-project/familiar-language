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

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.LArgs;
import org.xtext.example.mydsl.fml.PrinterUtility;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher print and println utility functions manages a set of
 *         variables or strings see: examples/testing/print/printstr.fmm
 *         examples/testing/print/printwrong.fmm
 */
public class PrinterAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public PrinterAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public PrinterAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.ft.parser.AbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.VOID; // TODO: in case of an undeclared variable?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.ft.parser.AbstractCommandAnalyzer#parse()
	 */
	@Override
	public void eval() {
		assert (_command instanceof PrinterUtility);
		FMLShell.getInstance().printDebugMessage("printing... ");
		PrinterUtility printerCmd = (PrinterUtility) _command;
		String op = printerCmd.getOp();

		String toDisplay = "";

		LArgs arg = printerCmd.getArg();
		EList<Command> args = arg.getArgs();

		for (Command argToDisplay : args) {

			FMLShell.getInstance().printDebugMessage(
					"evaluating arg to display: " + argToDisplay);
			Variable variable = _environment.parse(argToDisplay, null);
			if (variable instanceof RefVariable)
				variable = ((RefVariable) variable).getValueReference();
			toDisplay += variable.getValue() + "";
			/*
			 * toDisplay += "(" + variable.getType() + ") " + variable.getVid()
			 * + " = " + variable.getValue() + ""; toDisplay += " ";
			 */

		}

		if (op.equals("println"))
			toDisplay += "\n";

		if (op.equals("println"))
			toDisplay += "\n";

		FMLShell.getInstance().printDisplay(toDisplay);

	}

}
