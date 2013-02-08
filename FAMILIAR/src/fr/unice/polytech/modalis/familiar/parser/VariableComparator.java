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
package fr.unice.polytech.modalis.familiar.parser;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.ComparisonOperator;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelPrinter;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.VariabilityOperatorVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.FeatureModel;

public class VariableComparator {
	
	
	private static Logger _LOGGER = Logger.getLogger(VariableComparator.class);


	private Variable lvar;
	private Variable rvar;
	private ComparisonOperator op;
	private String assertionError;

	public VariableComparator(Variable lvar, Variable rvar,
			ComparisonOperator op) {
		this.lvar = lvar;
		this.rvar = rvar;
		this.op = op;
	}

	public boolean eval() throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		// reference equality
		if (op == ComparisonOperator.REF_EQUAL)
			return lvar == rvar;
		else if (op == ComparisonOperator.REF_NOT_EQUAL)
			return lvar != rvar;
		// content, type specific equality
		else {
			
			boolean v = equal();
			if (op == ComparisonOperator.EQUAL)
				return v;
			else if (op == ComparisonOperator.NOT_EQUAL)
				return !v;
			else {
				FMLShell.getInstance().setError("Unknown comparator " + op);
				return false;
			}
		}
	}

	private boolean equal() throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		if (lvar instanceof RefVariable) {
			lvar = ((RefVariable) lvar).getValueReference();
		}

		if (rvar instanceof RefVariable) {
			rvar = ((RefVariable) rvar).getValueReference();
		}

		if (lvar == null && rvar == null) // FIXME: possible case?
			return true;

		if (lvar == null || rvar == null) // FIXME: possible case?
			return false;

		if (lvar.getRType() == RType.INTEGER
				&& rvar.getRType() == RType.INTEGER) {

			int l = Integer.parseInt(lvar.getValue());
			int r = Integer.parseInt(rvar.getValue());
			// if assertion is violated
			setAssertionError("integer values : left " + l + ", right " + r);
			if (op == ComparisonOperator.LESSER_THAN)
				return l < r;
			else if (op == ComparisonOperator.GREATER_THAN)
				return l > r;
			return l == r;
		}
		
		if (lvar.getRType() == RType.DOUBLE
				&& rvar.getRType() == RType.INTEGER) {

			double l = Double.parseDouble(lvar.getValue());
			int r = Integer.parseInt(rvar.getValue());
			// if assertion is violated
			setAssertionError("integer values : left " + l + ", right " + r);
			if (op == ComparisonOperator.LESSER_THAN)
				return l < r;
			else if (op == ComparisonOperator.GREATER_THAN)
				return l > r;
			return l == r;
		}
		
		if (lvar.getRType() == RType.DOUBLE
				&& rvar.getRType() == RType.DOUBLE) {

			double l = Double.parseDouble(lvar.getValue());
			double r = Double.parseDouble(rvar.getValue());
			// if assertion is violated
			setAssertionError("integer values : left " + l + ", right " + r);
			if (op == ComparisonOperator.LESSER_THAN)
				return l < r;
			else if (op == ComparisonOperator.GREATER_THAN)
				return l > r;
			return l == r;
		}
		
		if (lvar.getRType() == RType.INTEGER
				&& rvar.getRType() == RType.DOUBLE) {

			int l = Integer.parseInt(lvar.getValue());
			double r = Double.parseDouble(rvar.getValue());
			// if assertion is violated
			setAssertionError("integer values : left " + l + ", right " + r);
			if (op == ComparisonOperator.LESSER_THAN)
				return l < r;
			else if (op == ComparisonOperator.GREATER_THAN)
				return l > r;
			return l == r;
		}

		else if (lvar.getRType() == RType.BOOLEAN
				&& rvar.getRType() == RType.BOOLEAN) {

			boolean l = Boolean.parseBoolean(lvar.getValue());
			boolean r = Boolean.parseBoolean(rvar.getValue());
			// if assertion is violated
			setAssertionError("Boolean values: left " + l + ", right " + r);
			// shell.printDebugMessage("L:" + l + " R:" + r);
			return l == r;
		}

		else if (lvar.getRType() == RType.STRING
				&& rvar.getRType() == RType.STRING) {

			String l = lvar.getValue();
			String r = rvar.getValue();
			// if assertion is violated
			setAssertionError("string or enum values : left " + l + ", right " + r);
			return l.equals(r);
		}

		/*
		 * @Deprecated else if ((lvar.getRType() == RType.STRING &&
		 * rvar.getRType() == RType.FEATURE) || (lvar.getRType() ==
		 * RType.FEATURE && rvar.getRType() == RType.STRING)) {
		 * 
		 * String l = lvar.getValue(); String r = rvar.getValue(); // if
		 * assertion is violated
		 * setAssertionError("string or feature values : left " + l + ", right "
		 * + r); //shell.printDebugMessage("L:" + l + " R:" + r); return
		 * l.equals(r) ; }
		 */

		else if (lvar.getRType() == RType.FEATURE
				&& rvar.getRType() == RType.FEATURE) {

			assert (lvar instanceof FeatureVariable);
			assert (rvar instanceof FeatureVariable);

			/*
			 * two features are equals iff they have the same name
			 * TODO can be much more complicated (we should think about this)
			 * e.g. same parent
			 * e.g. same attributes
			 * e.g. same feature model
			 */
			String l = lvar.getValue();
			String r = rvar.getValue();

			// TODO: check if they belong to the same FM?
			FeatureModelVariable lw = ((FeatureVariable) lvar).getFmw();
			FeatureModelVariable rw = ((FeatureVariable) rvar).getFmw();


			// if assertion is violated
			setAssertionError("feature values : left " + l + ", right " + r);
			// shell.printDebugMessage("L:" + l + " R:" + r);
			return l.equals(r) ; //&& lw == rw; // note the '==' for feature models
		}
		
		
		else if (lvar.getRType() == RType.FEATURE_GROUP
				&& rvar.getRType() == RType.FEATURE_GROUP) {

			assert (lvar instanceof FGroup);
			assert (rvar instanceof FGroup);

			/*
			 * two constraints are equals iff they have the same propositional "form" (syntactically) + feature equality
			 * TODO can be much more complicated (we should think about this)
			 * e.g. same feature model
			 * e.g. semantic equivalence
			 */
			FGroup lFg = (FGroup) lvar ;
			FGroup rFg = (FGroup) rvar ;

			// if assertion is violated
			setAssertionError("fgroup values : left " + lFg + ", right " + rFg);
			return lFg.equals(rFg) ; 
		}
		
		else if (lvar.getRType() == RType.CONSTRAINT
				&& rvar.getRType() == RType.CONSTRAINT) {

			assert (lvar instanceof ConstraintVariable);
			assert (rvar instanceof ConstraintVariable);

			/*
			 * two constraints are equals iff they have the same propositional "form" (syntactically) + feature equality
			 * TODO can be much more complicated (we should think about this)
			 * e.g. same feature model
			 * e.g. semantic equivalence
			 */
			ConstraintVariable lCst = (ConstraintVariable) lvar ;
			ConstraintVariable rCst = (ConstraintVariable) rvar ;

			// if assertion is violated
			setAssertionError("constraint values : left " + lCst + ", right " + rCst);
			return lCst.getConstraint().equals(rCst.getConstraint()) ; 
		}

		else if ((lvar.getRType() == RType.SET && rvar.getRType() == RType.SET)) {

			assert (lvar instanceof SetVariable);
			assert (rvar instanceof SetVariable);

			String l = lvar.getValue();
			String r = rvar.getValue();
			// if assertion is violated
			setAssertionError("SET values : left " + l + ", right " + r);

			SetVariable lset = (SetVariable) lvar;
			SetVariable rset = (SetVariable) rvar;

			// check if a variable lv in lset has an equivalent *value* in reset
			for (Variable lv : lset.getVars()) {

				boolean found = false;
				for (Variable rv : rset.getVars()) {
					VariableComparator vcmp = new VariableComparator(lv, rv,
							ComparisonOperator.EQUAL);
					boolean eq = vcmp.equal();
					if (eq)
						found = true;
				}

				if (!found)
					return false;

			}

			// and vice versa
			for (Variable rv : rset.getVars()) {

				boolean found = false;
				for (Variable lv : lset.getVars()) {
					VariableComparator vcmp = new VariableComparator(lv, rv,
							ComparisonOperator.EQUAL);
					boolean eq = vcmp.equal();
					if (eq)
						found = true;
				}

				if (!found)
					return false;

			}

			return true;

		}

		else if ((lvar.getRType() == RType.VARIABILITY_OPERATOR && rvar
				.getRType() == RType.VARIABILITY_OPERATOR)) {

			assert (lvar instanceof VariabilityOperatorVariable);
			assert (rvar instanceof VariabilityOperatorVariable);

			VariabilityOperatorVariable lvow = (VariabilityOperatorVariable) lvar;
			VariabilityOperatorVariable rvow = (VariabilityOperatorVariable) rvar;

			setAssertionError("variability operators values : left "
					+ lvow.getFek() + ", right " + rvow.getFek());

			return lvow.getFek() == rvow.getFek();

		}

		else if ((lvar.getRType() == RType.FEATURE_MODEL && rvar.getRType() == RType.FEATURE_MODEL)) {

			assert (lvar instanceof FeatureModelVariable);
			assert (rvar instanceof FeatureModelVariable);

			FeatureModelVariable lfmw = (FeatureModelVariable) lvar;
			FeatureModelVariable rfmw = (FeatureModelVariable) rvar;

			setAssertionError("feature models values : left " + lfmw
					+ ", right " + rfmw);

			FeatureModel<String> lfm = lfmw.getFm();
			FeatureModel<String> rfm = rfmw.getFm();

			_LOGGER.debug(
					"@#@# (FM) l: " + lfmw + " r: " + rfmw);

			// TODO
			
			//return Comparison.REFACTORING == lfmw.compare(rfmw);
			
			return lfm.equals(rfm)
					|| (new FeatureModelPrinter(lfmw).toString()
							.equals(new FeatureModelPrinter(rfmw).toString())); 

		}

		else if ((lvar.getRType() == RType.CONFIGURATION && rvar.getRType() == RType.CONFIGURATION)) {

			assert (lvar instanceof ConfigurationVariable);
			assert (rvar instanceof ConfigurationVariable);

			ConfigurationVariable lcv = (ConfigurationVariable) lvar;
			ConfigurationVariable rcv = (ConfigurationVariable) rvar;

			setAssertionError("configuration values : left " + lcv + ", right "
					+ rcv);

			FeatureModelVariable lfmw = lcv.getFmv();
			FeatureModelVariable rfmw = rcv.getFmv();

			_LOGGER.debug(
					"lfmw=" + lfmw + " rfmw=" + rfmw);

			if (lfmw != rfmw)
				_LOGGER.debug(
						"configurations are different since lfm=" + lfmw
								+ " is different than rfm=" + rfmw);

			/*
			 * * two variables of type configuration are equal if 1) they belong
			 * to the same feature model 2) they have the same set of selected,
			 * deselected and unselected features
			 */
			return (lfmw == rfmw)
					&& (lcv.getSelected().equals(rcv.getSelected()))
					&& (lcv.getUnselected().equals(rcv.getUnselected()));
		}

		else {
			setAssertionError("Unable to perform the assertion (types are not recognized l: "
					+ lvar.getRType() + " r: " + rvar.getRType() + ")");

			// FMShell.getInstance().printError
			// ("Unable to perform the assertion (types are not recognized l: "
			// + lvar.getRType() + " r: " + rvar.getRType() + ")");

			return false;
		}

	}

	/**
	 * @return the assertionError
	 */
	public String getAssertionError() {
		return assertionError;
	}

	/**
	 * In case there is an error, it records the nature of the supposed error
	 * 
	 * @param str
	 */
	private void setAssertionError(String str) {
		this.assertionError = str;
	}

}
