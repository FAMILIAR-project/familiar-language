/**
 * 
 */
package fr.familiar.operations;

import java.util.Collection;

import org.xtext.example.mydsl.fML.SliceMode;

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
