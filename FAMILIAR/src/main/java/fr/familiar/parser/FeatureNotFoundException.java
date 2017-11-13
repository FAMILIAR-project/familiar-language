/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.parser;

import fr.familiar.variable.FeatureModelVariable;

public class FeatureNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7068812298698326598L;
	private String feature;
	private FeatureModelVariable fmw;

	public FeatureNotFoundException(String feature) {
		this(feature, null);
	}

	public FeatureNotFoundException(String feature, FeatureModelVariable fmw) {
		this.feature = feature;
		this.fmw = fmw;
	}

	@Override
	public String toString() {
		String inFM = (fmw == null) ? "" : "" + "in " + fmw.getIdentifier();
		return "feature not found: " + this.feature + " " + inFM;
	}

}
