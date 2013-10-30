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
package fr.familiar.fm.featureide;

import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;

import java.util.Set;

/**
 * @author mathieuacher
 * 
 */
public class FeatureIDEConverterUtils {

	protected void addMutualConstraint(FeatureNode<String> sr,
			Set<FeatureNode<String>> sources, FeatureModel<String> lfm) {

		String strSr = convertFeatureNode(sr);
		Expression<String> lcstr = new Expression<String>(strSr).not();
		for (FeatureNode<String> fn : sources) {
			String ostr = convertFeatureNode(fn);
			if (fn != sr) {
				Expression<String> rcstr = new Expression<String>(ostr).not();
				Expression<String> cstr = new Expression<String>(
						ExpressionType.OR, lcstr, rcstr);
				lfm.addConstraint(cstr);
			}
		}

	}



	protected String convertFeatureNode(FeatureNode<String> v) {
		String strFT = "";
		if (v.getType() == FeatureType.AND_GROUP) {
			strFT = v.toString(); // (String)
			// FeatureIDEConverter.this.groupToId.get(v)
			strFT = strFT.replace("&", ""); // TODO: add subfeatures
		} else {
			strFT = v.getFeature(); // v.isTop() ? "Root" : v.getFeature();
			if (strFT.trim().equals("1"))
				strFT = "RootTop";
		}
		// strFT = strFT.replace("1", "RootTop");
		return strFT;
	}

}
