package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import splar.core.fm.FeatureModelException;
import splar.core.fm.XMLFeatureModel;
import fr.unice.polytech.modalis.familiar.fm.converter.SPLOTtoFML;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.FeatureModel;

public class FMLSPLOT2FMConverterTest extends FMLTest{

	@Test
	public void testSPLOT2FM1() {
		String fileName = "aircraft_fm.xml";
		
		// Parse the  FM with the model to text converter
		String mkFmlInput = "fm1" + " = " + "FM (" + "\"" +  fileName + "\"" + ")\n\n" ;
		Variable v = _shell.parse(mkFmlInput);
		FeatureModelVariable fm = (FeatureModelVariable) v;
		FeatureModel<String> expectedFm = fm.getFm();
		
		
		// Parse the FM with the model to model converter
		File splotFile = FMLShell.getInstance().searchFile(fileName);
		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				splotFile.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			FMLShell.getInstance().printError(
					"Unable to load SPLOT feature model "
							+ e.getMessage());
			return;
		}
		SPLOTtoFML converter = new SPLOTtoFML();
		FeatureModel<String> computedFm = converter.convertToFeatureModel(featureModelSPLOT);
		
		// Compare the two FMs
		assertEquals(expectedFm, computedFm);
	}
}
