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
package fr.familiar.fm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Child;
import org.xtext.example.mydsl.fml.FeatureModel;
import org.xtext.example.mydsl.fml.Mandatory;
import org.xtext.example.mydsl.fml.Optional;
import org.xtext.example.mydsl.fml.Orgroup;
import org.xtext.example.mydsl.fml.Production;
import org.xtext.example.mydsl.fml.Xorgroup;

/**
 * @author mathieuacher
 * 
 */
public class FeatureModelChecker {

	private FeatureModel _fm;
	private List<String> _errors = new ArrayList<String>();

	public FeatureModelChecker(FeatureModel fm) {
		_fm = fm;
	}

	public boolean isSyntacticallyValid() {

		/*
		 * case 1: only root!
		 */
		String rootName = _fm.getRoot() ;
		if (rootName != null)
			return true ; 
		
		// check productions
		EList<Production> productions = _fm.getFeatures();
		Set<String> parentFeatures = new HashSet<String>();

		assert (productions.size() > 0);
		Production firstProduction = productions.iterator().next();
		String root = firstProduction.getName(); // parent name of the first
													// production

		for (Production production : productions) {
			String name = production.getName();
			if (!parentFeatures.add(name)) {
				setUnvalid("Parent feature " + name + " already exists!");
				return false;
			}
		}

		Set<String> childFeatures = new HashSet<String>();
		for (Production production : productions) {
			EList<Child> children = production.getFeatures();
			for (Child c : children) {

				// check that each child feature does not occur twice
				if (c instanceof Xorgroup) {
					Xorgroup g = (Xorgroup) c;
					EList<String> fts = g.getFeatures();
					if (!checkGroup(fts))
						return false;

					for (String ft : fts) {
						if (childFeatures.contains(ft)) {
							setUnvalid("Child feature " + ft
									+ " in Xor-group already exists!");
							return false;
						}
						childFeatures.add(ft);
					}

				} else if (c instanceof Orgroup) {
					Orgroup g = (Orgroup) c;
					EList<String> fts = g.getFeatures();
					if (!checkGroup(fts))
						return false;

					for (String ft : fts) {
						if (childFeatures.contains(ft)) {
							setUnvalid("Child feature " + ft
									+ " in Or-group already exists!");
							return false;
						}
						childFeatures.add(ft);
					}

				}

				else if (c instanceof Optional) {
					Optional o = (Optional) c;
					String name = o.getName();
					if (childFeatures.contains(name)) {
						setUnvalid("Child feature " + name + " already exists!");
						return false;
					}
					childFeatures.add(name);

				} else if (c instanceof Mandatory) {
					Mandatory m = (Mandatory) c;
					String name = m.getName();
					if (childFeatures.contains(name)) {
						setUnvalid("Child feature " + name + " already exists!");
						return false;
					}
					childFeatures.add(name);

				} else {
					// TODO / error?
				}

			}

		}

		if (childFeatures.contains(root)) {
			setUnvalid("Root feature " + root + " cannot be a child feature!");
			return false;
		}

		return true;
	}

	private boolean checkGroup(EList<String> fts) {

		Set<String> s = new HashSet<String>();
		for (String ft : fts) {
			if (!s.add(ft)) {
				setUnvalid("Child feature " + ft
						+ " already exists in the group!");
				return false;
			}
		}

		return true;

	}

	private void setUnvalid(String string) {
		_errors.add(string);
	}

	public List<String> getErrors() {
		return _errors;
	}

}
