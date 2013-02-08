/**
 * 
 */
package fr.unice.polytech.modalis.familiar.fm;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.FormulaAnalyzer;
import fr.unice.polytech.modalis.familiar.operations.KSynthesisBDD;
import fr.unice.polytech.modalis.familiar.operations.KnowledgeSynthesis;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
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
