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
package fr.familiar.operations;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.parser.FeatureAmbigousException;
import fr.familiar.parser.FeatureNotFoundException;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class ConstraintBinder {

	private FMLCommandInterpreter _env;
	private Expression<String> _cnf;

	/**
	 * 
	 */
	public ConstraintBinder(Expression<String> cnf, FMLCommandInterpreter env) {
		_cnf = cnf;
		_env = env;
	}

	private FeatureModelVariable whichFM(Expression<String> cnf) {

		// TODO

		FeatureModelVariable fmw = null;
		FeatureVariable fw = null;
		String featureName = cnf.getLeft().getFeature(); // left say, dangerous
		try {

			fw = _env.retrieveFeature(featureName);
			fmw = fw.getFeatureModel();
			return fmw;
		} catch (FeatureNotFoundException e) {
			FMLShell.getInstance().printDebugMessage(
					"Unable to bind (1): " + e.toString());

			try {

				fmw = _env.retrieveFeatureModel(featureName);
				_env.extractFeaturefromFeature(featureName);
				FMLShell.getInstance().printDebugMessage(
						"ft found: " + featureName + " fm found: " + fmw);
				return fmw;
			} catch (FeatureNotFoundException e1) {
				FMLShell.getInstance().setError(
						"Unable to bind (2): " + e1.toString());
				return null;
			} catch (FeatureAmbigousException e1) {
				FMLShell.getInstance().setError(
						"(ambigous) Unable to bind (2): " + e1.toString());
				return null;
			}
		} catch (FeatureAmbigousException e) {
			FMLShell.getInstance().printDebugMessage(
					"(ambigous) Unable to bind: " + e.toString());

			try {
				fmw = _env.retrieveFeatureModel(featureName);
				_env.extractFeaturefromFeature(featureName);
				return fmw;
			} catch (FeatureNotFoundException e1) {
				FMLShell.getInstance().printWarning(
						"(amb) Unable to bind (2): " + e1.toString());
				return null;
			} catch (FeatureAmbigousException e1) {
				FMLShell.getInstance().printWarning(
						"(amb) Unable to bind (2): " + e1.toString());
				return null;
			} // ambiguity
		} // TODO: manage

	}

	public void bindInternally() {

		FeatureModelVariable fmw = whichFM(_cnf); // let say
		// TODO: control consistency
		if (fmw == null) {
			FMLShell.getInstance().setError(
					"cannot bind constraint to any feature model");
			return;
		}
		fmw.getFm().addConstraint(_cnf);

	}

}
