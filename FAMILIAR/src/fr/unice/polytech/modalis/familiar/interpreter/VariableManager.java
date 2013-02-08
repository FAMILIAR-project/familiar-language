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
package fr.unice.polytech.modalis.familiar.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import fr.unice.polytech.modalis.familiar.parser.NameSpace;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.familiar.variable.VariableIdentifier;

/**
 * This class manages all variables.
 * 
 * @author Mathieu Acher
 * 
 */
public class VariableManager {
	
	
	private static Logger _LOGGER = Logger.getLogger(VariableManager.class);


	private Map<VariableIdentifier, Variable> _varMapping;

	/**
	 * optional: name to the variable manager (DEBUG)
	 */
	private String envName;

	public VariableManager() {
		this("");
	}

	private VariableManager(String envName) {
		_varMapping = new HashMap<VariableIdentifier, Variable>();
		this.setEnvName(envName);
	}

	/**
	 * @param identifier
	 * @returna variable associated to the given identifier
	 */
	public Variable getVariable(String identifier) {

		List<VariableIdentifier> gkeys = new ArrayList<VariableIdentifier>();

		VariableIdentifier vid = new VariableIdentifier(identifier);
		Set<VariableIdentifier> keys = _varMapping.keySet();
		for (VariableIdentifier key : keys) {
			if (key.same(vid)) {
				gkeys.add(key);

			}

		}

		if (gkeys.size() == 0)
			return null;

		if (gkeys.size() == 1) {// no ambiguity
			VariableIdentifier gk = gkeys.get(0);
			return _varMapping.get(gk);
		}
		for (VariableIdentifier gk : gkeys) {
			if (gk.sameNameWithNS(vid))
				return _varMapping.get(gk);
		}

		for (VariableIdentifier gk : gkeys) {
			if (gk.sameName(vid))
				return _varMapping.get(gk);
		}

		return null;
	}

	/**
	 * This method adds a variable (records).
	 * 
	 * @param varName
	 *            name of the variable
	 * 
	 */
	public void addVariable(Variable varValue) {
		if (varValue.getIdentifier() == null)
			// TODO: fatal error?
			System.err.println("Variable has no name.");
		else
			_varMapping.put(varValue.getVid(), varValue);
	}

	/**
	 * This method removes a variable.
	 * 
	 * @param varName
	 *            name of the variable
	 * 
	 */
	@Deprecated
	public Variable removeVariable(String varName) {
		return _varMapping.remove(new VariableIdentifier(varName));
	}

	public void removeVariable(VariableIdentifier vid) {

		Set<VariableIdentifier> vids = _varMapping.keySet();
		VariableIdentifier vid2remove = null;
		for (VariableIdentifier vi : vids) {
			if (vi.equals(vid)) {
				vid2remove = vi;
				break;
			}
		}
		if (vid2remove == null) {
			_LOGGER.debug(
					"Unable to find identifier " + vid);
			return;
		}

		Variable v = _varMapping.remove(vid2remove);
		if (v == null) {
			_LOGGER.debug(
					"Unable to remove variable " + vid);
			return;
		}

		// System.out.println("variable removed: " + vid);
	}

	// TODO
	public void replaceVar(Variable varValue) {
		_varMapping.put(varValue.getVid(), varValue);

	}

	public List<Variable> getVariables() {
		List<Variable> r = new ArrayList<Variable>();
		Collection<Variable> vars = _varMapping.values();
		for (Variable var : vars) {
			r.add(var);
		}
		return r;
	}

	public boolean hasVariable(String var) {
		return getVariable(var) != null;

	}

	public boolean hasVariable(VariableIdentifier vid) { // can be ambiguous
		return hasVariable(vid.getName());
		/*
		 * Set<VariableIdentifier> keys = varMapping.keySet(); for
		 * (VariableIdentifier key : keys) { if (key.same(vid)) return true; }
		 * return false;
		 */

	}

	public boolean hasVariableWithNS(String strFM, NameSpace ns) { // only
		// strict (namespace is required)
		VariableIdentifier vid = new VariableIdentifier(strFM);
		Set<VariableIdentifier> keys = _varMapping.keySet();
		for (VariableIdentifier key : keys) {
			if (key.sameNameWithNS(vid))
				return true;
		}
		return false;
	}

	public boolean hasVariableWithNS(VariableIdentifier vid) { // only
		// strict (namespace is required)
		return hasVariableWithNS(vid.getName(), vid.getNs());

	}

	public boolean hasAmbigousVariable(String var) {

		List<VariableIdentifier> gkeys = getCandidateVariable(var);
		VariableIdentifier vid = new VariableIdentifier(var);

		if (gkeys.size() == 0) // no variable, no ambiguity
			return false;

		if (gkeys.size() == 1) // no ambiguity, only one variable
			return false;

		// System.out.println("Gkeys: " + gkeys);
		for (VariableIdentifier gk : gkeys) {
			if (gk.sameNameWithNS(vid))
				return false;
		}

		// no namespace used

		/*
		 * for (VariableIdentifier gk : gkeys) { if(gk.sameName(vid)) return
		 * false ; }
		 */

		return true;
	}

	public List<VariableIdentifier> getCandidateVariable(String var) {

		List<VariableIdentifier> gkeys = new ArrayList<VariableIdentifier>();

		VariableIdentifier vid = new VariableIdentifier(var);
		Set<VariableIdentifier> keys = _varMapping.keySet();
		for (VariableIdentifier key : keys) {
			if (key.same(vid))
				gkeys.add(key);
		}

		return gkeys;
	}

	public static VariableManager mkEnvironment() {
		return new VariableManager();
	}

	public static VariableManager mkEnvironment(String envName) {
		return new VariableManager(envName);
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvName() {
		return envName;
	}

	public void addEnvironment(VariableManager variableManager) {

		List<Variable> currentEnvVars = getVariables();
		List<Variable> newEnvVars = variableManager.getVariables();

		for (Variable af : newEnvVars) {
			boolean exist = false;
			for (Variable bf : currentEnvVars) {
				if (af.equals(bf)) {
					exist = true;

				}
			}

			if (!exist) {
				_LOGGER.warn("\t\t\t#####@@@ already exists in the env: "
								+ af.getVid());

			}
		}

		_varMapping.putAll(variableManager.getEnv());

	}

	private Map<? extends VariableIdentifier, ? extends Variable> getEnv() {
		return _varMapping;
	}

	public void removeVariable(Variable bf) {
		_varMapping.remove(bf);

	}

	/**
	 * delete all variables
	 */
	public void clear() {
		_varMapping.clear();
	}

}
