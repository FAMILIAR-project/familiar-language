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

/**
 * @author macher1
 *
 */
public class FMLMergerBDDSPLOT extends FMLMergerBDD {

	/**
	 * @param lfvms
	 */
	public FMLMergerBDDSPLOT(Collection<FeatureModelVariable> lfvms) {
		super(lfvms);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lfvms
	 * @param synchronizedFla
	 */
	public FMLMergerBDDSPLOT(Collection<FeatureModelVariable> lfvms,
			FDOverApproximationStrategy synchronizedFla) {
		super(lfvms, synchronizedFla);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lfvms
	 * @param builder
	 */
	public FMLMergerBDDSPLOT(Collection<FeatureModelVariable> lfvms,
			BDDBuilder<String> builder) {
		super(lfvms, builder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lfvms
	 * @param synchronizedFla
	 * @param builder
	 */
	public FMLMergerBDDSPLOT(Collection<FeatureModelVariable> lfvms,
			FDOverApproximationStrategy synchronizedFla,
			BDDBuilder<String> builder) {
		super(lfvms, synchronizedFla, builder);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param lfms
	 *            list of FMs
	 * @param mode
	 *            sunion, intersection, etc.
	 * @return new formula based on the mode and formulas associated to each FM
	 *         of lfms (SPLOT facilities)
	 */
	@Override
	public Formula<String> calculateFormula(Mode mode) {
		BDDMerger merger = new BDDMerger (_builder);
		Formula<String> fmerged = merger.mergeFMSPLOT(_lfms, mode);
		return fmerged;
	}

	

}
