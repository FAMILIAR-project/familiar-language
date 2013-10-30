/**
 * 
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
