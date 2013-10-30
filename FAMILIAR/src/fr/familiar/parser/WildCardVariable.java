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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureNode;

public class WildCardVariable {

	public static String WILD_CARD = "*";

	private FMLCommandInterpreter _interpreter;
	private String _identifier;

	public WildCardVariable(FMLCommandInterpreter commandInterpreter,
			String identifier) {
		this._interpreter = commandInterpreter;
		this._identifier = identifier;
	}

	public SetVariable parse() throws VariableNotExistingException {

		FMLShell.getInstance().printDebugMessage("parsing wild card");
		if (_identifier.equals(WILD_CARD)) { // current NS
			Set<Variable> vars = new HashSet<Variable>(
					_interpreter.getVariables());
			return new SetVariable(vars, _identifier); // TODO 'varName'
		}
		// NEW: not necessary a namespace!
		// fm1.A.*
		// or event A.* (A is a non ambigous feature)
		// TODO: currently, only fm1.A* is supported
		// could be
		try {
			FeatureModelVariable fmv = _interpreter
					.retrieveFeatureModel(_identifier);

			int base = 0;
			if (FMLCommandInterpreter.isExplicitFeatureName(_identifier))
				base = _identifier.indexOf(FeatureVariable.SEPARATOR) + 1;
			String actualFeatureName = _identifier.substring(base);
			FMLShell.getInstance().printDebugMessage(
					"(* extraction) actualFeatureName=" + actualFeatureName);

			// hack (qualified or not)
			// fm1.(B.A) is qualified
			// A is not
			if (FMLCommandInterpreter.isQualifiedFtName(actualFeatureName)) {
				FMLShell.getInstance().printDebugMessage(
						"(* extraction) indeed a qualified feature name");
				int lastIndexSeparator = actualFeatureName
						.lastIndexOf(FeatureVariable.SEPARATOR);
				String realFeatureName = actualFeatureName
						.substring(lastIndexSeparator + 1); // last element
				FMLShell.getInstance().printDebugMessage(
						"(* extraction) real feature name=" + realFeatureName);
				if (realFeatureName.equals(WildCardVariable.WILD_CARD)) {

					StringTokenizer tokenizer = new StringTokenizer(
							actualFeatureName, FeatureVariable.SEPARATOR);
					int nSeparator = tokenizer.countTokens();
					int nToken = 0;
					String parentFt = null; // last but one before *, e.g.,
											// fm1.A.B.* => B
					while (tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
						if (++nToken == (nSeparator - 1))
							parentFt = token;
					}
					if (parentFt == null) {
						FMLShell.getInstance().printError(
								"Unable to process and extract: "
										+ actualFeatureName);
					}

					// checking that the parent does exist
					try {
						FeatureNode<String> fn = fmv.getFm().getDiagram()
								.findVertex(parentFt);
						if (fmv.getFm().getDiagram().outgoingEdges(fn).size() == 0)
							throw new FeatureNotFoundException(
									actualFeatureName, fmv);
						FMLShell.getInstance().printDebugMessage(
								"parent feature actually there: "
										+ fn.getFeature());

						FeatureVariable ftParentVariable = new FeatureVariable(
								parentFt, fmv);
						SetVariable descs = ftParentVariable.descendants();
						return descs;

					} catch (Exception e) {
						throw new FeatureNotFoundException(actualFeatureName,
								fmv);
					}

				}
			}

		} catch (FeatureNotFoundException e) {
			FMLShell.getInstance().printDebugMessage(
					"does not involve a feature model: " + _identifier + " e="
							+ e);
		} catch (FeatureAmbigousException e) {
			FMLShell.getInstance().printDebugMessage(
					"(ambigous) does not involve a feature model: "
							+ _identifier + " e=" + e);
		}

		// something before
		String namespace = extractNameSpace();
		String strvariable = null;
		if (namespace == null) {
			FMLShell.getInstance().printDebugMessage(
					"not directly on a namespace");
			strvariable = extractVariable();
			FMLShell.getInstance().printDebugMessage(
					"variable * " + strvariable);
			if (_identifier.indexOf(".") != -1)
				namespace = _identifier.substring(0,
						_identifier.indexOf(strvariable) - 1);
			else
				namespace = null;

		} else {

			FMLShell.getInstance().printDebugMessage("namespace: " + namespace);
			// can be a feature model and * refer to features!!
			List<Variable> vars = _interpreter.getVariables();
			Set<Variable> varsR = new HashSet<Variable>();
			for (Variable v : vars) {
				FMLShell.getInstance().printDebugMessage(
						"Searching variable (for *) v="
								+ v.getIdentifier());

				if (v instanceof RefVariable) {
					// TODO (BUG)
					v = ((RefVariable) v).getValueReference();
					FMLShell.getInstance().printDebugMessage(
							"(found!) ref variable name=" 
									+ v.getIdentifier());
				}

				if (v instanceof FeatureModelVariable) {

					// TODO: uses directly fmw.features()
					if (v.getIdentifier().equals(namespace)) {
						FMLShell.getInstance().printDebugMessage(
								"NS is an FM " + v.getIdentifier());
						FeatureModelVariable fmw = (FeatureModelVariable) v;
						return fmw.features() ;
						/*
						FeatureGraph<String> fgraph = fmw.getFm().getDiagram();
						Set<FeatureNode<String>> vtx = fgraph.vertices();

						for (FeatureNode<String> fn : vtx) {
							if (!fn.isBottom() && !fn.isTop() && !fn.isDead())
								varsR.add(new FeatureVariable("", fn
										.getFeature(), fmw));

						}
						SetVariable sv = new SetVariable(varsR, _identifier);
						FMLShell.getInstance().printDebugMessage(
								"sfeatures=" + sv);
						return sv;
						*/
					}

					if (v.getNS().getName().equals(namespace)) {
						FMLShell.getInstance().printDebugMessage(
								"in the namespace " + v.getIdentifier());
						try {
							// TODO
							Variable candidate = _interpreter.getVariable(v
									.getNS().getName()
									+ "."
									+ v.getIdentifier());
							varsR.add(candidate);
						} catch (VariableAmbigousConflictException e) {
							FMLShell.getInstance().setError(
									"Conflict (should not happen)");
						} catch (VariableNotExistingException e) {
							FMLShell.getInstance().setError(
									"Ambigous (should not happen)");
						}

					}

				}

			}

			return new SetVariable(varsR, _identifier);

		}

		Set<Variable> vars = new HashSet<Variable>();
		if (strvariable != null) {
			List<Variable> allvars = _interpreter.getVariables();
			for (Variable v : allvars) {
				if (v.getIdentifier().startsWith(strvariable)) { // old version:
																	// contains
					try {
						Variable candidate = _interpreter.getVariable(v
								.getIdentifier());
						vars.add(candidate);
					} catch (VariableAmbigousConflictException e) {

					} catch (VariableNotExistingException e) {

					}

				}

			}

		} else {
			List<Variable> lvars = _interpreter.getVariables(NSFactory
					.mkNS(namespace));
			if (lvars.size() == 0)
				throw new VariableNotExistingException(_identifier);
			vars = new HashSet<Variable>(lvars);
		}
		return new SetVariable(vars, _identifier); // TODO 'varName'

	}

	private String extractNameSpace() {

		assert (_identifier.length() > 1);

		int wildPosition = _identifier.indexOf(WILD_CARD);
		char beforeWild = _identifier.charAt(wildPosition - 1);
		if (beforeWild == '.')
			return _identifier.substring(0, _identifier.lastIndexOf("."));

		return null;

	}

	private String extractVariable() {

		assert (_identifier.length() > 1);

		int wildPosition = _identifier.indexOf(WILD_CARD);
		int dotPosition = _identifier.lastIndexOf(".");
		if (dotPosition == -1)
			return _identifier.substring(0, wildPosition);
		// else

		return _identifier.substring(dotPosition + 1, wildPosition);

	}

}
