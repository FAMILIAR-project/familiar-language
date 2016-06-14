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
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ConfigurationCommand;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.GDisplay;
import org.xtext.example.mydsl.fml.IdentifierExpr;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class GDisplayAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public GDisplayAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public GDisplayAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {
		assert (_command instanceof GDisplay);
		GDisplay gdisplCmd = (GDisplay) _command;

		// TODO
		EObject eovar = gdisplCmd.getVar();

		
		Variable variable = null ; 
		if (eovar instanceof IdentifierExpr) {
			variable = _environment.parse((IdentifierExpr) eovar, null);
			if (variable instanceof RefVariable) {
				FMLShell.getInstance().printDebugMessage(
						variable.getIdentifier() + " is a reference");
				variable = ((RefVariable) variable).getValueReference();
			}
		}
		else if (eovar instanceof FMCommand) {
			FMCommand fmVar = (FMCommand) eovar;
			FMLShell.getInstance().printDebugMessage(
					"evaluating FM or configuration to display: " + fmVar);

			variable = _environment.parseFMCommand(fmVar, null, null);
			
		}

		else if (eovar instanceof ConfigurationCommand) {
			// TODO: can be a configuration
			FMLShell.getInstance().printDebugMessage("gdisplay configuration");
			variable = _environment.parseConfigurationCommand((ConfigurationCommand) eovar, null);
		} else {
			FMLShell.getInstance().setError(
					"Neither a feature model nor a configuration " + eovar);
			return;
		}
		

		if (variable instanceof FeatureModelVariable) {
			FeatureModelVariable fmw = null;
			fmw = (FeatureModelVariable) variable;
			assert (fmw != null);
			fmw.gdisplay();
			return;
		} else if (variable instanceof ConfigurationVariable) {
			FMLShell.getInstance().printDebugMessage(
					"conf variable= " + variable);
			final ConfigurationVariable conf = (ConfigurationVariable) variable;
			conf.gdisplay();
			return;
		} else {
			FMLShell.getInstance()
					.setError("Unknown variable: " + variable);
			return;
		}


	}

}
