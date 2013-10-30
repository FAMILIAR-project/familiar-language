/**
 * 
 */
package fr.familiar.operations;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.util.Collection;

import org.xtext.example.mydsl.fML.SliceMode;

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
