/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations.featureide;

import org.apache.log4j.Logger;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter;
import fr.unice.polytech.modalis.familiar.experimental.featureide.FMComparator;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public class FMComparatorSATFeatureIDE {

	protected static Logger _LOGGER = Logger.getLogger(FMComparatorSATFeatureIDE.class) ;
	
	private FeatureModelVariable _rfmv;
	
	private FeatureModelVariable _lfmv;
	
	private boolean _refactoringBDD;


	public FMComparatorSATFeatureIDE(FeatureModelVariable lfmv,	FeatureModelVariable rfmv, boolean refactoringBDD) {
		_lfmv = lfmv ; 
		_rfmv = rfmv ; 
		_refactoringBDD = refactoringBDD ; 
	}

	public Comparison compare() {
		if (_refactoringBDD) { // checking 
			if (_lfmv.getFormula().equals(_rfmv.getFormula())
					|| _lfmv.getFm().equals(_rfmv.getFm())) {
				_LOGGER.debug("refactoring (BDD equality) ");
				return Comparison.REFACTORING;
			} 
		}

		FeatureModel lfm = new FMLtoFeatureIDE(_lfmv).convert();
		GuidslWriter lwriterFmIDE = new GuidslWriter(
				lfm.clone());

		FeatureModel rfm = new FMLtoFeatureIDE(_rfmv).convert();

		GuidslWriter rwriterFmIDE = new GuidslWriter(
				rfm.clone());

		_LOGGER.debug(
				"Left: " + lwriterFmIDE.writeToString());
		_LOGGER.debug(
				"Right: " + rwriterFmIDE.writeToString());

		FMComparator cmpFM = new FMComparator();

		Comparison rcomp = cmpFM.compare(rwriterFmIDE.writeToString(),
				lwriterFmIDE.writeToString()); // change it!?
		_LOGGER.debug("rcomp=" + rcomp.name());

		return rcomp;
		
	}

}
