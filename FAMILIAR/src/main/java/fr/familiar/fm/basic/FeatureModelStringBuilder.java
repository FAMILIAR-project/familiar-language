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
package fr.familiar.fm.basic;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Mutexgroup;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;

import fr.familiar.fm.FeatureModelVisitor;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.CNFtoExpression;
import fr.familiar.utils.ToStringBuilder;
import gsd.synthesis.Expression;

public class FeatureModelStringBuilder extends FeatureModelVisitor {

	private boolean _inFMCALC;

	public FeatureModelStringBuilder(FeatureModel fm) {
		this(fm, false);
	}

	/**
	 * @param fm
	 *            the feature model to process
	 * @param inFMCALC
	 *            whether or not the convertion is in FMCALC
	 */
	public FeatureModelStringBuilder(FeatureModel fm, boolean inFMCALC) {
		super(fm);
		_inFMCALC = inFMCALC;
	}

	@Override
	public String treatFeatureModel(FeatureModel fm) {
		StringBuilder sb = new StringBuilder();
		sb.append("FM (");

		List<Production> prods = fm.getFeatures();
		for (Production prod : prods) {
			sb.append(treatProd(prod) + "");
		}

		EList<CNF> constraints = fm.getExpr();
		for (CNF constraint : constraints) {
			sb.append(treatConstraint(constraint));
		}

		sb.append(")");
		return sb.toString();
	}

	@Override
	public String treatConstraint(CNF constraint) {

		CNFtoExpression converter = new CNFtoExpression(constraint);
		Expression<String> expr = converter.convert();
		return expr.toString() + "; ";
	}

	@Override
	public String treatProd(Production prod) {
		StringBuilder sb = new StringBuilder();
		sb.append(prod.getName() + ": ");
		List<Child> childs = prod.getFeatures();
		for (Iterator<Child> iterator = childs.iterator(); iterator.hasNext();) {
			Child child = (Child) iterator.next();
			sb.append(treatChild(child) + " ");

		}
		sb.append("; ");
		return sb.toString();
	}

	@Override
	public String treatChild(Child c) {

		String r = "";

		if (c instanceof Xorgroup) {
			Xorgroup g = (Xorgroup) c;
			EList<String> features = g.getFeatures();
			r = new ToStringBuilder("|", "(", ")").toString(features);

		} else if (c instanceof Orgroup) {
			Orgroup g = (Orgroup) c;
			EList<String> features = g.getFeatures();
			r = new ToStringBuilder("|", "(", ")").toString(features);
			r += "+";
		}

		else if (c instanceof Mutexgroup) {
			Mutexgroup g = (Mutexgroup) c;
			EList<String> features = g.getFeatures();
			r = new ToStringBuilder("|", "(", ")").toString(features);
			r += "?";
		}

		else if (c instanceof Optional) {
			Optional o = (Optional) c;
			if (_inFMCALC)
				r += o.getName() + "?";
			else
				r += "[" + o.getName() + "]";

		} else if (c instanceof Mandatory) {
			Mandatory m = (Mandatory) c;
			r += m.getName() + "";

		} else {
			// should never be reached
			FMLShell.getInstance().setError(
					"Unexpected error, unknown child feature: " + c);
			return null;
		}

		return r;

	}

	@Override
	public String toString() {
		return treatFeatureModel(fm);
	}

}
