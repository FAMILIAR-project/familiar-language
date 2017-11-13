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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.LoadGeneric;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableManager;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.ScriptVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;

/**
 * @author mathieuacher
 * 
 */
public class RunParser extends FMLAbstractCommandAnalyzer {

	public RunParser(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {

		assert (LoadGeneric.class.isInstance(getCmd()));

		parseLoad((LoadGeneric) getCmd());

	}

	// Uniform load
	private void parseLoad(LoadGeneric loadCmd) {

		// load file and execute all commands
		String filename = loadCmd.getStream();
		filename = filename.replace("\"", "");
		FMLShell.getInstance().printDebugMessage(
				"loading script file " + filename);

		/*** map arguments to parameters ********/

		List<Variable> paramsVar = new ArrayList<Variable>();
		EList<String> params = loadCmd.getParams();

		if (params != null && params.size() > 0) {
			// retrieve variable identifier

			for (String param : params) {
				Variable v =  _environment.parseCommand(param, null, null); 
				paramsVar.add(v);
			}
				
		}
		
		
		FMLShell.getInstance().printDebugMessage("" + paramsVar) ; 
				
		String namespace = "";

		// if (hasVar()) {
		if (loadCmd.getNs() != null) {
			namespace = loadCmd.getNs(); // getVar();
			FMLShell.getInstance().printDebugMessage(
					"with namespace: " + namespace);
		}

		// check if it already exists!

		NameSpace newNS = NSFactory.mkNS(namespace);
		
		/****** Is it a script variable? ********/
		ScriptVariable sv = checkScriptVariable(filename);
		if (sv == null)
			FMLShell.getInstance().printDebugMessage(
					"Unable to find the script variable " + filename);
		else {
			FMLShell.getInstance().printDebugMessage(
					"Execute the script variable " + sv);
			VariableManager vars = sv.run(namespace, paramsVar);
			_environment.checkEnvironmentIntegrity(loadCmd);
			_environment.addOrReplaceEnv(vars);
			FMLShell.getInstance().setRunnableMode(false); // end
			_environment.updateVariableView();
			return;

		}

		/***** Loading file *******/

		// searching recursively for a file 'filename' which is in the shell
		// path (directory tree)
		File file = FMLShell.getInstance().searchFile(filename);

		// checking if file exists
		if (file == null || !file.isFile() || !file.exists()) {

			FMLShell.getInstance().setError("File " + filename + " not found.");
			return;
		}

		FMLShell.getInstance().setRunnableMode(true);
		BufferedReader br;
		try {
			List<Variable> beforeVars = _environment.getVariables(); // before
																		// parsing
																		// the
																		// new
																		// file
			br = new BufferedReader(new FileReader(file));
			String strcmd = "";

			FMLCommandInterpreter newEnv = new FMLCommandInterpreter(ns);
			
			String sbuffer = "";
			while ((strcmd = br.readLine()) != null) {
				/**** Parse all commands ******/
				if (FMLShell.LINE_BY_LINE)
					newEnv.parseCommand(strcmd, newNS, paramsVar);
				else
					sbuffer += strcmd + "\n";
			}
			if (!FMLShell.LINE_BY_LINE)
				newEnv.parseCommand(sbuffer, newNS, paramsVar);

			// remove hidden variables (first)

			// TODO
			newEnv.hiddenVariables();

			/****** set the namespace *********/
			// shell.getVariables()
			FMLShell.getInstance().printDebugMessage(
					"######\t\t AFTER THE RUN (newenv updating) \t\t######");
			FMLShell.getInstance().printDebugMessage(
					"using namespace: " + namespace);
			List<Variable> afterVars = newEnv.getVariables(); // after parsing
																// the new file
			for (Variable aftv : afterVars) {
				FMLShell.getInstance().printDebugMessage(
						"after: " + aftv.getIdentifier() + "");
			}
			for (Variable bftv : beforeVars) {
				FMLShell.getInstance().printDebugMessage(
						"before: " + bftv.getIdentifier() + "");
			}

			for (Variable af : afterVars) {
				boolean alreadyExist = false;
				for (Variable bf : beforeVars) {
					if (af.equals(bf)) {
						alreadyExist = true;
						if ((af instanceof RefVariable)
								&& (bf instanceof RefVariable)) {
							// same parameter (HACK)
							FMLShell.getInstance().printDebugMessage(
									"@@@@ Unifying references @@@@@");
							// Variable realAF = ((RefVariable)
							// af).getValueReference();
							// Variable realBF = ((RefVariable)
							// bf).getValueReference();
							newEnv.removeVariable(bf);

						}
					}
				}

				if (!alreadyExist) {
					FMLShell.getInstance().printDebugMessage(
							"new variable: " + af.getVid());
					if (af instanceof RefVariable) { // can be a parameter!
						RefVariable raf = (RefVariable) af;
						VariableIdentifier vid2Remove = new VariableIdentifier(
								raf.getIdentifier(), NSFactory.concatenateNS(
										NSFactory.mkEmpty(), raf.getNS()));
						FMLShell.getInstance().printDebugMessage(
								"cleaning environment (parameter): "
										+ vid2Remove + " -> "
										+ raf.getReference().getIdentifier());
						newEnv.removeVariable(vid2Remove);

					} else {
						FMLShell.getInstance().printDebugMessage(
								"setting namespace... ");
						af.setNS(NSFactory.concatenateNS(newNS, af.getNS()));

					}
				}
			}

			// an.getEnvironment().addEnvironment(newEnv.getEnvironment()); //
			// replace?!
			// an.printAllVariables();
			_environment.checkEnvironmentIntegrity(loadCmd);
			_environment.addOrReplaceEnv(newEnv.getEnvironment()); // TODO
			// associate reference values to parameters!

			FMLShell.getInstance().setRunnableMode(false); // end
			_environment.updateVariableView();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ScriptVariable checkScriptVariable(String filename) {

		try {
			Variable v = _environment.getVariable(filename);
			if (v instanceof RefVariable)
				v = ((RefVariable) v).getValueReference();
			if (!(v instanceof ScriptVariable))
				return null;
			else
				return (ScriptVariable) v;
		} catch (VariableNotExistingException e) {
			return null;
		} catch (VariableAmbigousConflictException e) {
			return null;
		}

	}

}
