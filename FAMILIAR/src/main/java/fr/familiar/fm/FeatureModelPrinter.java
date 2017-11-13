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
package fr.familiar.fm;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraphFactory;

public class FeatureModelPrinter {

	private FeatureModelVariable _fmv = null;

	public FeatureModelPrinter(FeatureModelVariable fmv) {
		this._fmv = fmv;
	}

	public String pureSyntacticalRepresentation() {

		FeatureGraphFactory<String> gf = FeatureGraphFactory.mkStringFactory();
		FMLInternalFeatureModelSerializer<String> ser = new FMLInternalFeatureModelSerializer<String>(
				gf, true);

		return ser.toString(_fmv.getFm());
	}

	public String toString() {

		String strFM = pureSyntacticalRepresentation();
		//Formula<String> formula = _fmv.getFormula();
		String res = strFM ; //formula.getBDD().isZero() ? "False" : strFM;

		return res;

	}

	public String pureSyntacticalRepresentationWithoutConstraint() {
		FeatureGraphFactory<String> gf = FeatureGraphFactory.mkStringFactory();
		FMLInternalFeatureModelSerializer<String> ser = new FMLInternalFeatureModelSerializer<String>(
				gf, false);

		return ser.toStringWithoutConstraint(_fmv.getFm());
	}

}
