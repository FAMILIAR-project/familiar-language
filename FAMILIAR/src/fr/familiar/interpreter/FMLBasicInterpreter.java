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
package fr.familiar.interpreter;

import java.util.ArrayList;
import java.util.List;

import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.parser.VariableAmbigousConflictException;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableImpl;

/**
 * @author mathieuacher FAMILIAR interpreter designed so that external
 *         applications can easily call FAMILIAR scripts and get the results
 *         (string-based representation) in a programmative way.
 */

public class FMLBasicInterpreter {

	private final FMLShell _shell;
	private final FMLCommandInterpreter _environment;
	private boolean hasBeenCorrectlyParsed;

	public FMLBasicInterpreter() {
		_shell = FMLShell.instantiateStandalone(System.in);
		_environment = _shell.getCurrentEnv();
		hasBeenCorrectlyParsed = false;
	}
	
	public FMLBasicInterpreter(FMLShell shell) {
		_shell = shell ; 
		_environment = _shell.getCurrentEnv();
		hasBeenCorrectlyParsed = false;
	}

	/**
	 * @param filename
	 * @throws FMLFatalError
	 * @throws FMLAssertionError
	 */
	public void evalFile(String filename) throws FMLFatalError,
			FMLAssertionError {
		// TODO
	}

	/**
	 * @param instr
	 *            the FAMILIAR expressions to eval
	 * @throws FMLFatalError
	 *             in case there is a fatal error during the evaluation
	 * @throws FMLAssertionError
	 *             in case there is an assertion (using assert in FAMILIAR)
	 *             error during the evaluation
	 */
	public Variable eval(String instr) throws FMLFatalError, FMLAssertionError {

		Variable v = _shell.parse(instr);
		
		
		
		if (hasFatalErrors()) {
			hasBeenCorrectlyParsed = false;
			throw new FMLFatalError(_shell.getFatalErrors());

		}

		if (_shell.hasAssertionErrors()) {
			hasBeenCorrectlyParsed = true; // we consider that FML assertion
											// violation does not break the
											// parsing process
			throw new FMLAssertionError(_shell.getAssertionErrors());
		}
		hasBeenCorrectlyParsed = true;
		return v ; 

	}

	/**
	 * @param id
	 *            identifier of a variable in the environment
	 * @return a string-based representation of the value of variable 'id' in
	 *         the environment
	 * @throws VariableNotExistingException
	 *             in case there is no variable associated to the identifier
	 * @throws VariableAmbigousConflictException
	 *             in case there is no explicit identifier (ambiguity)
	 */
	public String exprToString(String id) throws VariableNotExistingException,
			VariableAmbigousConflictException {

		assert (hasBeenCorrectlyParsed);

		try {
			VariableImpl v = (VariableImpl) _environment.getVariable(id);
			return v.getValue();
		} catch (VariableNotExistingException e) {
			throw e;
		} catch (VariableAmbigousConflictException e) {
			throw e;
		}

	}

	/**
	 * Note that fm1.A are not considered as "active" variables and are not
	 * returned
	 * 
	 * @return A list of identifiers, where each element is a variable
	 *         identifier in the environment
	 */
	public List<String> getAllIdentifiers() {

		assert (hasBeenCorrectlyParsed);

		List<String> ls = new ArrayList<String>();
		List<Variable> vars = _environment.getVariables();

		for (Variable v : vars) {
			ls.add(((VariableImpl) v).getCompleteIdentifier());
		}

		return ls;
	}

	/**
	 * Basically delete all variables in the environment
	 */
	public void reset() {
		_shell.reset();
		assert (getAllIdentifiers().size() == 0);
	}

	public boolean hasFatalErrors() {
		return _shell.hasFatalErrors();
	}

	public boolean isFeatureModel(String id) {
		assert (hasBeenCorrectlyParsed);

		try {
			VariableImpl v = (VariableImpl) _environment.getVariable(id);
			if (v instanceof FeatureModelVariable)
				return true;
			else
				return false;
		} catch (VariableNotExistingException e) {
			return false;
		} catch (VariableAmbigousConflictException e) {
			return false;
		}

	}

}
