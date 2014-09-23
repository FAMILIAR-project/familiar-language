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

package fr.familiar.featureide;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.familiar.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

/**
 * Testing facilities for iterating over FMs of SPLOT
 * @author macher1
 *
 */
public class FMLSPLOTTest extends FMLTest {
	
	final protected List<String> excludeFiles = Arrays.asList(new String[] {
			//"stack_fm.xml", 
			//"movies_app_fm.xml", 
			//"arcade_game_pl_fm.xml", // FeatureIDE failure
			 //two features with the same name (parent)
			"model_20100830_561967343.xml", 
			"model_20100927_1382418986.xml", 
			"model_20101112_864228137.xml", 
			"model_20110406_353034837.xml", 
			"model_20110407_1283128166.xml", 
			"model_20110712_1373520081.xml", 
			"model_20101115_2000504462.xml",
			"model_20120130_333619036.xml",
			"model_20120328_573707444.xml",
			"model_20120328_663906927.xml",
			"model_20120418_828883554.xml",
			"model_20120424_1703152596.xml",
			
			"REAL-FM-4.xml",			
			
			// group with one element
			"model_20101120_2091447559.xml", 
			"model_20101123_920943759.xml", 
			"model_20110301_216655728.xml", 
			"model_20110207_906076676.xml", 
			"model_20110323_789959080.xml", 
			"model_20110330_919429247.xml", 
			"model_20110507_674768061.xml", 
			"model_20110704_328391695.xml", 
			"model_20110826_1574631601.xml",
			"model_20110925_62365838.xml",
			"model_20120111_1667229430.xml",
			"model_20120205_24117969.xml",
			"model_20120328_288933955.xml",
			"model_20120801_1784537083.xml",
			
			"model_20110216_608697455.xml",
			"model_20110926_400852996.xml", // same feature names
			"smart_home_fm.xml", // same feature names
			"model_20120725_1460954667.xml",
			
			"model_20110516_1331478109.xml"
			
			
			
	});

	
	protected final static String SPLOT_FOLDER = "inputFML/splot-models-2012-08-07";
	
	
	protected List<FeatureModelVariable> collectSPLOTFMs() {
		File splotFolder = new File(SPLOT_FOLDER);
		File[] splotFiles = splotFolder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml") && !excludeFiles.contains(name);
				
			}
		});
		
		List<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>(); 
		int i = 0;  
		for (File splotFile : splotFiles) {
			i++ ; 	
			String fileName = splotFile.getName() ; 
			
			String fmName = fileName.replace("-", "");
			//System.err.println("fm" + i + ": " + fmName);
			String mkFmlInput = "fm_" + i + "_" + fmName + "" + " = " + "FM (" + "\"" +  fileName + "\"" + ")\n\n" ;
			//System.err.println("" + mkFmlInput) ;
			Variable v = null ;
			//try {
				v = _shell.parse(mkFmlInput);
			//}
			//catch (Exception e) {
			//	System.err.println("DOES NOT COMPILE") ; 
			//	continue ; 
			//}
			 
			assertTrue(v instanceof FeatureModelVariable);
			if (v != null) {
				fms.add((FeatureModelVariable)v);
				
				/*System.err.println(v);
				System.err.println("=====");*/
			}
			
		}
		
		return fms ; 
	}

}
