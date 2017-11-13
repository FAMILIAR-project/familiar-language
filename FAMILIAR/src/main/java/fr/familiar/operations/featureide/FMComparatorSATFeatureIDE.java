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

package fr.familiar.operations.featureide;

import org.apache.log4j.Logger;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter;
import fr.familiar.experimental.featureide.FMComparator;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;

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
