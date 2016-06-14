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

import java.util.Collection;

import org.xtext.example.mydsl.fml.SliceMode;

import fr.familiar.fm.FMLFormula;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public abstract class SlicerFormula {
	
	/**
	 * @param oformula
	 *            original formula to be filtered
	 * @param features
	 *            set of features to be quantified away
	 * @param sliceMode
	 *            including or exclusive slicing criterion strategy
	 * @return a "filtered" formula (see TR for formal description)
	 */
	public abstract FMLFormula sliceFormula(FeatureModelVariable fmv,
			Collection<String> features, SliceMode sliceMode) ;

}
