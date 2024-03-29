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

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

import java.util.Set;

/**
 * @author macher
 * KSynthesis
 */
@Deprecated
public class FMLSynthetizer {

	private BDDBuilder<String> _builder;
	
	
	public FMLSynthetizer(BDDBuilder<String> builder) {
		
		_builder = builder ;
		
	}

	/**
	 * assume the hierarchy is cleaned or otherwise we try to clean based on the analysis of features
	 *
	 * TODO use formula domain instead?
	 * @param fla
	 * @param hierarchy
	 * @return
	 */
	public FeatureModelVariable synthetize(Formula<String> fla, FeatureModel<String> hierarchy) {

	
		 // to avoid side effect on hierarchy!
		FeatureModel<String> lHierarchy = hierarchy.clone() ; 
		
		/****** we have obtained the formula *****/
		if (fla.isZero()) { // false
			FeatureModelVariable fmR = FeatureModelVariable.mkFalse(fla, lHierarchy);
			return fmR ; 
		}
		
		Set<String> deads = new FormulaAnalyzer<String>(fla, _builder).computeDeadFeatures();
		
				
		// first, we eliminate dead features (synthesis algorithm precondition)
		fla = new FormulaAnalyzer<String>(fla,	_builder).removeDeadFeatures();

		// at this step, the formula represents *exactly* the set of configurations expected
		Formula<String> originalFlaMerged = fla.clone();
		
		/*** hierarchy ****/
		FMLMergerBDD.removeDeadFeaturesFromHierarchy(lHierarchy, deads);
		
						
		/*** now we actually use the synthesis algorithm ****/
		KSynthesisBDD builder = new KSynthesisBDD(fla, new KnowledgeSynthesis(lHierarchy.getDiagram()), _builder);
		FeatureModel<String> fgRender = builder.build().getFm(); 
		
		FeatureModelVariable fmR = new FeatureModelVariable(null, fgRender, fla);
		
		/*
		originalFlaMerged = new FormulaAnalyzer<String>(originalFlaMerged,
				FMLCommandInterpreter.getBuilder()).removeDeadFeatures();
				*/
		//fmR = MergeAnalyzer.complement(originalFlaMerged, fmR, _builder);
		FMLShell.getInstance().printDebugMessage("Lazy...");
		fmR = FMLMergerBDD.complementLazy(originalFlaMerged, fmR, _builder);
		
		return fmR ; 
		
		
	}

}
