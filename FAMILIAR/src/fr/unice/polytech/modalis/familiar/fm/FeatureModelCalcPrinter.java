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
package fr.unice.polytech.modalis.familiar.fm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;

@Deprecated
// @see FeatureModelStringBuilder.java
public class FeatureModelCalcPrinter extends FeatureModelVisitor {

	public FeatureModelCalcPrinter(FeatureModel fm) {
		super(fm);
	}

	Set<String> strs = new HashSet<String>();

	public String toString() {
		return treatFeatureModel(fm);
	}

	public String treatFeatureModel(FeatureModel fm) {

		String r = "";

		List<Production> prods = fm.getFeatures();

		for (Production prod : prods) {
			r += treatProd(prod);
		}

		return r;

	}

	public String treatProd(Production prod) {
		String r = "";
		r += prod.getName() + ": ";
		List<Child> childs = prod.getFeatures();
		for (Iterator<Child> iterator = childs.iterator(); iterator.hasNext();) {
			Child child = (Child) iterator.next();
			r += treatChild(child);

		}
		r += "; ";
		return r;

	}

	public String treatChild(Child c) {

		String r = "";

		if (c instanceof Xorgroup) {
			Xorgroup g = (Xorgroup) c;
			r += g.getFeatures() + " ";
		} else if (c instanceof Orgroup) {
			Orgroup g = (Orgroup) c;
			r += g.getFeatures() + "+ ";
		}
		// else if (c instanceof Andgroup) {
		// Andgroup g = (Andgroup) c;
		// r += g.getFeatures();
		// }

		else if (c instanceof Optional) {
			Optional o = (Optional) c;
			// r += "[" + o.getName() + "] ";
			r += "" + o.getName() + "? ";

		} else if (c instanceof Mandatory) {
			Mandatory m = (Mandatory) c;
			r += m.getName() + " ";

		} else {
			System.err.println("Error?");
			// TODO / error?
		}

		return r;

	}

	public Set<String> getStrs() {
		return strs;
	}

	@Override
	public String treatConstraint(CNF constraint) {

		StringBuilder sb = new StringBuilder();

		// TODO

		// ....

		return sb.toString();
	}

}
