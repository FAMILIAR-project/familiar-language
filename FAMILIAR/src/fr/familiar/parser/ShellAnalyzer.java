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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.Exist;
import org.xtext.example.mydsl.fML.IsConflicting;
import org.xtext.example.mydsl.fML.OPT_LISTING;
import org.xtext.example.mydsl.fML.impl.ExistImpl;
import org.xtext.example.mydsl.fML.impl.ExitImpl;
import org.xtext.example.mydsl.fML.impl.ListingImpl;
import org.xtext.example.mydsl.fML.impl.ShellImpl;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher process shell-like commands (ls, help, copy, exist,
 *         etc.) see: examples/testing/shell/isconflicting.fmm
 *         examples/testing/shell/isexisting.fmm
 *         examples/testing/shell/isnull.fmm TODO: refactoring
 * 
 */
public class ShellAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public ShellAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		this(cmd, null, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ShellAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
		_type = RType.VOID;

	}

	@Override
	public RType getType() {
		return _type; // it depends
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.ft.parser.AbstractCommandAnalyzer#parse()
	 */
	@Override
	public void eval() {
		assert (_command instanceof ShellImpl);

		ShellImpl shellcmd = (ShellImpl) _command;
		// printInfoMessage("shell command: " + shellcmd.getCmd().toString());

		EObject rcmd = shellcmd.getCmd();

		// quit/exit
		if (rcmd instanceof ExitImpl) {
			_type = RType.VOID;
			FMLShell.getInstance().close();

		}

		// listing variables
		else if (rcmd instanceof ListingImpl) {
			// FMShell.getInstance().printInfoMessage(BDDBuilderSerializer.asString(getBuilder()));
			_type = RType.VOID;
			
			
			
			ListingImpl lsCmd = (ListingImpl) rcmd ; 
			OPT_LISTING opt = lsCmd.getOpt() ;
			
			FMLShell.getInstance().printDebugMessage("opt=" + opt);
			if (opt == OPT_LISTING.NORMAL || (opt == null)) {
				List<Variable> vars = _environment.getVariables();
				for (Variable variable : vars) {
					FMLShell.getInstance().printDisplay("(" + variable.getType() + ") " + variable.getIdentifier() + System.getProperty("line.separator"));
				}
			}			
			else if (opt == OPT_LISTING.VERBOSE) {
				_environment.printAllVariables();
			}
			else if (opt == OPT_LISTING.VALUE_ONLY) {
				List<Variable> vars = _environment.getVariables();
				for (Variable variable : vars) {
					FMLShell.getInstance().printDisplay("(" + variable.getType() + ") " + variable.getIdentifier() + " = " + variable.getValue() + System.getProperty("line.separator"));
				}
			}
			else {
				FMLShell.getInstance().printError("Unable to understand the option associated to the listing command " + opt);
				return ;
			}

		}

		
		else if (rcmd instanceof Exist) {
			_type = RType.BOOLEAN;
			ExistImpl exist = (ExistImpl) rcmd;

			String strvar = exist.getVar();
			BooleanVariable bw = null;
			try {
				_environment.getVariable(strvar);

				// it can be a feature!

			} catch (VariableNotExistingException e) {
				FMLShell.getInstance().printDebugMessage(
						"Not a variable... Maybe a feature?");

				try {
					if (_environment.retrieveFeatureModel(strvar) != null)
						bw = new BooleanVariable(_assigner, true); // find!
					else
						bw = new BooleanVariable(_assigner, false);
				} catch (FeatureNotFoundException e1) {
					FMLShell.getInstance().printWarning(
							"Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, false);
				} catch (FeatureAmbigousException e1) {
					FMLShell.getInstance().printWarning(
							"(ambigous) Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, false);
				}

			} catch (VariableAmbigousConflictException e) {
				FMLShell.getInstance().printWarning(
						"Ambigous variable" + e.toString());
				FMLShell.getInstance().printDebugMessage(
						"Not a variable... Maybe a feature?");

				try {
					if (_environment.retrieveFeatureModel(strvar) != null)
						bw = new BooleanVariable(_assigner, true); // find!
					else
						bw = new BooleanVariable(_assigner, false);
				} catch (FeatureNotFoundException e1) {
					FMLShell.getInstance().printWarning(
							"Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, false);
				} catch (FeatureAmbigousException e1) {
					FMLShell.getInstance().printWarning(
							"(ambigous) Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, false);
				}

			}

			// we found it!
			if (bw == null) {
				FMLShell.getInstance().printInfoMessage("true");
				bw = new BooleanVariable(_assigner, true);
			}
			setVariable(bw);
			return;
		}

		else if (rcmd instanceof IsConflicting) {
			_type = RType.BOOLEAN;
			FMLShell.getInstance().printDebugMessage("isConflicting");
			IsConflicting isconflict = (IsConflicting) rcmd;

			String strvar = isconflict.getVar();
			BooleanVariable bw = null;
			try {
				_environment.getVariable(strvar);
				bw = new BooleanVariable(_assigner, false);

			} catch (VariableNotExistingException e) {
				FMLShell.getInstance().printDebugMessage(
						"Not a variable... Maybe a feature?");

				try {
					if (_environment.retrieveFeatureModel(strvar) != null)
						bw = new BooleanVariable(_assigner, false); // find!
				} catch (FeatureNotFoundException e1) {
					FMLShell.getInstance().printWarning(
							"Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, false);
				} catch (FeatureAmbigousException e1) {
					FMLShell.getInstance().printWarning(
							"(ambigous) Unable to find feature by name (2): "
									+ e1.toString());
					bw = new BooleanVariable(_assigner, true);
				}

			} catch (VariableAmbigousConflictException e) {
				FMLShell.getInstance().printWarning(
						"Ambigous variable" + e.toString());
				FMLShell.getInstance().printDebugMessage(
						"Not a variable... Maybe a feature?");

				bw = new BooleanVariable(_assigner, true);
				try {
					if (_environment.retrieveFeatureModel(strvar) != null)
						bw = new BooleanVariable(_assigner, false); // only this
																	// can
																	// disambiguate
				} catch (FeatureNotFoundException e1) {
					FMLShell.getInstance().printWarning(
							"Unable to find feature by name (2): "
									+ e1.toString());

				} catch (FeatureAmbigousException e1) {
					FMLShell.getInstance().printWarning(
							"(ambigous) Unable to find feature by name (2): "
									+ e1.toString());

				}

			}

			// we found it!
			if (bw == null) {
				FMLShell.getInstance().printInfoMessage("Found: true");
				bw = new BooleanVariable(_assigner, true);
			}
			setVariable(bw);
			return;
		}

		else
			FMLShell.getInstance().printTODO();

	}

}
