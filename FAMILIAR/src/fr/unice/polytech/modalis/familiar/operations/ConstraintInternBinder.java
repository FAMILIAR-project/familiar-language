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
package fr.unice.polytech.modalis.familiar.operations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.parser.MyExpressionParser;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;

public class ConstraintInternBinder {
	
	
	private static Logger _LOGGER = Logger.getLogger(ConstraintInternBinder.class);


	private List<FeatureModelVariable> _lv;
	private Expression<String> _cst;

	public ConstraintInternBinder(Expression<String> constraint,
			List<FeatureModelVariable> lv) {
		_cst = constraint;
		_lv = lv;
	}

	public ConstraintInternBinder(Expression<String> constraint) {
		this(constraint, null);
	}

	private FeatureModelVariable whichFM(Expression<String> cnf) {

		assert (cnf != null);
		if (cnf == null)
			return null;

		// TODO
		int c = 0;
		for (FeatureModelVariable fmw : _lv) {
			Set<String> features = fmw.getFm().features();
			for (String fture : features) {
				if (containsFeature(fture, cnf))
					c++;
			}

		}

		if (c > 2) {
			FMLShell.getInstance().setError(
					"Ambigous expression " + cnf + " (occurence:" + c + ")");
			return null;
		}

		for (FeatureModelVariable fmw : _lv) {
			Set<String> features = fmw.getFm().features();
			for (String fture : features) {
				if (containsFeature(fture, cnf))
					return fmw;
			}

		}

		return null;
	}

	/**
	 * @param fture
	 *            the feature name that is supposed to occur in the expression
	 * @param cnf
	 *            the expression to process
	 * @return true if there is an occurence of fture in cnf
	 */
	private boolean containsFeature(String fture, Expression<String> cnf) {
		if (cnf.getType() == ExpressionType.FEATURE)
			return fture.equals(cnf.getFeature());

		if (cnf.getType() == ExpressionType.NOT)
			return fture.equals(cnf.getFeature());

		// iff, implies, and, : binary
		return containsFeature(fture, cnf.getLeft())
				|| containsFeature(fture, cnf.getRight());

	}

	public void bindInternally() {

		assert (_lv != null);
		assert (_lv.size() >= 1);
		FeatureModelVariable fmw = whichFM(_cst);
		if (fmw == null) {
			FMLShell.getInstance().setError(
					"cannot bind constraint to any feature model");
			return;
		}
		fmw.getFm().addConstraint(_cst);

	}
	
	public boolean remove(FeatureModelVariable fmTarget) {

		boolean isIn = checkConstraintisInFM(_cst, fmTarget);
		if (isIn) {
			return fmTarget.removeConstraint(_cst);
		}	
		return false ; 
	}

	public boolean bind(FeatureModelVariable fmTarget) {

		boolean isIn = checkConstraintisInFM(_cst, fmTarget);
		if (isIn) {
			fmTarget.getFm().addConstraint(_cst);
			return true;
		} else {
			
			_LOGGER.debug(
					" cnf=" + _cst + " is not in!");
			Set<String> featuresNotIn = featuresNotInCNF(_cst, fmTarget);
			for (String ftNotIn : featuresNotIn) {
				_LOGGER.debug("ftNotIn=" + ftNotIn);
				Expression<String> nexpr = MyExpressionParser.parseString("!"
						+ ftNotIn + "");
				// Expression<String> nexpr = new
				// Expression<String>(ExpressionType.NOT, ftNotIn, null);
				//
				_LOGGER.debug("nexpr=" + nexpr);
				try {
					fmTarget.getFm().addConstraint(nexpr);
				} catch (Exception e) { // weird
					_LOGGER.debug(
							"nexpr=" + nexpr + " already there!");
				}
			}
			fmTarget.getFm().addConstraint(_cst);
		}
		return false;
	}

	private Set<String> featuresNotInCNF(Expression<String> cnf,
			FeatureModelVariable fmTarget) {

		Set<String> features = fmTarget.getFm().features();
		Set<String> featuresOfCNF = new HashSet<String>();
		findFeatures(cnf, featuresOfCNF);
		return Sets.difference(featuresOfCNF, features);
	}

	private boolean checkConstraintisInFM(Expression<String> cnf,
			FeatureModelVariable fmTarget) {
		Set<String> features = fmTarget.getFm().features();
		Set<String> featuresOfCNF = new HashSet<String>();
		findFeatures(cnf, featuresOfCNF);
		return features.containsAll(featuresOfCNF);
	}

	private void findFeatures(Expression<String> expr, Set<String> acc) {
		if (expr == null)
			return;
		if (expr.getType() == ExpressionType.FEATURE) {
			String ft = expr.getFeature();
			if (ft != null)
				acc.add(ft);
			return;
		}

		// iff, implies, and, not : binary
		findFeatures(expr.getLeft(), acc);
		findFeatures(expr.getRight(), acc);

	}

}
