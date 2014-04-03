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

package fr.familiar.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import splar.core.fm.FeatureModelException;
import splar.core.fm.XMLFeatureModel;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;
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
