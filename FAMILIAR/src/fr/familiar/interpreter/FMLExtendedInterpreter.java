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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.parser.VariableAmbigousConflictException;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableImpl;

/**
 * @author mathieuacher FAMILIAR interpreter designed so that external
 *         applications can easily call FAMILIAR scripts in a programmative way.
 *         Currently not public -- that is a trap to FML framework ;-)
 */

public class FMLExtendedInterpreter {

	protected final FMLShell _shell;
	protected final FMLCommandInterpreter _environment;
	protected boolean hasBeenParsed;

	public FMLExtendedInterpreter() {
		if (FMLShell.getInstance() == null)
			_shell = FMLShell.instantiateStandalone(System.in);
		else
			_shell = FMLShell.getInstance();
		_environment = _shell.getCurrentEnv();
		hasBeenParsed = false;
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
	public void eval(String instr) throws FMLFatalError, FMLAssertionError {
		_shell.parse(instr);

		if (_shell.hasFatalErrors())
			throw new FMLFatalError(_shell.getFatalErrors());

		if (_shell.hasAssertionErrors()) {
			hasBeenParsed = true; // considering that assertion does not break
									// the parsing process
			throw new FMLAssertionError(_shell.getAssertionErrors());
		}

	}

	/**
	 * @param id
	 *            identifier of a variable in the environment
	 * @return variable 'id' in the environment
	 * @throws VariableNotExistingException
	 *             in case there is no variable associated to the identifier
	 * @throws VariableAmbigousConflictException
	 *             in case there is no explicit identifier (ambiguity)
	 */
	public Variable getVariable(String id) throws VariableNotExistingException,
			VariableAmbigousConflictException {

		assert (hasBeenParsed);

		try {
			VariableImpl v = (VariableImpl) _environment.getVariable(id);
			return v;
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

		assert (hasBeenParsed);

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

	/**
	 * @param id
	 * @return a variable of type feature model whose identifier is id in the
	 *         current environment
	 * @throws VariableAmbigousConflictException
	 * @throws VariableNotExistingException
	 */
	public FeatureModelVariable getFMVariable(String id)
			throws VariableNotExistingException,
			VariableAmbigousConflictException {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);
		return (FeatureModelVariable) v;

	}

	/**
	 * @param id
	 * @return a variable of type set whose identifier is id in the current
	 *         environment
	 * @throws VariableAmbigousConflictException
	 * @throws VariableNotExistingException
	 */
	public SetVariable getSetVariable(String id)
			throws VariableNotExistingException,
			VariableAmbigousConflictException {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof SetVariable);
		return (SetVariable) v;

	}

	public FMLShell getShell() {
		return _shell;
	}

}
