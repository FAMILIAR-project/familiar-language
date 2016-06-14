/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.util.Collection;

import org.xtext.example.mydsl.fml.SliceMode;

/**
 * @author macher1
 *
 */
public class FMSlicerBDDWithSPLOT extends FMSlicerBDD {

	/**
	 * @param builder
	 */
	public FMSlicerBDDWithSPLOT(BDDBuilder<String> builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param fm
	 * @param features
	 * @param slideMode
	 * @return
	 */
	@Override
	public Formula<String> sliceFormula(FeatureModelVariable fm,
			Collection<String> features, SliceMode sliceMode) {
		Formula<String> oformula = fm.getSPLOTFormulaAligned(_builder);
		_LOGGER.debug("original formula computed");
		Formula<String> filteredFormula = new SlicerBDDFormula(_builder).sliceFormula(
				oformula, features, sliceMode);
		return filteredFormula;
	}

}
