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

import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;

/**
 * @author mathieuacher A skeleton feature model visitor
 * 
 */
public class FeatureModelVisitorSkeleton extends FeatureModelVisitor {

	/**
	 * 
	 */
	public FeatureModelVisitorSkeleton(FeatureModel fm) {
		super(fm);
	}

	@Override
	public String treatFeatureModel(FeatureModel fm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String treatProd(Production prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String treatChild(Child c) {
		if (c instanceof Xorgroup) {
			Xorgroup g = (Xorgroup) c;
			// TODO
		} else if (c instanceof Orgroup) {
			Orgroup g = (Orgroup) c;
			// TODO
		}

		else if (c instanceof Optional) {
			Optional o = (Optional) c;
			// TODO

		} else if (c instanceof Mandatory) {
			Mandatory m = (Mandatory) c;
			// TODO

		} else {
			// TODO / error?
		}

		return null;
	}

	@Override
	public String treatConstraint(CNF constraint) {
		// TODO Auto-generated method stub
		return null;
	}

}
