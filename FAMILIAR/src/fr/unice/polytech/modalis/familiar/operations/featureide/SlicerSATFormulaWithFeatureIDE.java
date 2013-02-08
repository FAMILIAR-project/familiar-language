package fr.unice.polytech.modalis.familiar.operations.featureide;

import java.util.Collection;

import org.prop4j.Node;
import org.xtext.example.mydsl.fML.SliceMode;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.editing.NodeCreator;
import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * without FeatureIDE stuff 
 * @author macher1
 *
 */
public class SlicerSATFormulaWithFeatureIDE extends SlicerSATFormula {

	public SlicerSATFormulaWithFeatureIDE() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public FMLFormula sliceFormula (FeatureModelVariable fmv, Collection<String> fts, SliceMode mode) {
				
		FeatureModel fmToSliceFeatureIDE = new FMLtoFeatureIDE(fmv).convert();
		Node node = NodeCreator.createNodes(fmToSliceFeatureIDE) ; 
		FMLFormula rFla = runSliceFormulaSAT(fmv, node,
				fmToSliceFeatureIDE.getFeatureNames(), fts, mode);
		return rFla ;
	}
	
	

}
