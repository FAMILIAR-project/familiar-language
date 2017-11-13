package fr.familiar;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;

public class FeatureModelLoaderTest extends FMLTest {
	
	
	@Test
	public void testSPLOTLoadFMs() throws Exception {
		
		 List<FeatureModelVariable> fmvs = new FeatureModelLoader(_shell, _builder).getAllSPLOTFeatureModels(); //getSPLOTFeatureModels();
		 assertTrue(fmvs.size() > 0);
		 
		 List<Integer> fmSizes = new ArrayList<Integer>(); 
		 for (FeatureModelVariable fmv : fmvs) {
			 // workaround: new BDD env. needed (otherwise 1.0 all time, since the BDD factory does not reset properly)
			 fmv.setBuilder(new BDDBuilder<String>(_builder.getFeatureGraphFactory()));
			 int s = fmv.features().size();
			 fmSizes.add(s);
			 double c = fmv.counting();
			 System.err.println("" + fmv.getIdentifier() + " = " + " #" + c);	
			 if (c == 1.0) {				 	
				 // break;
			 }
		 }
		 Collections.sort(fmSizes);
		 System.err.println("" + fmSizes);
		 
		 FeatureModelVariable fm = (FeatureModelVariable) _shell.parse("fm1 = FM (A: [B] [C]; C : (D|E|F); )");
		 System.err.println("#" + fm.counting());
		 
		 
		 FeatureModelVariable fm2 = (FeatureModelVariable) _shell.parse("fm2 = FM ("
		 		+ "\"Monitor Engine\": \"Monitor Engine performance\" \"Monitor Fuel Consumption\" ; \n" + 
		 		"\"Monitor Engine performance\": \"Monitor exhaust levels and temperature\" \"Monitor temperatures\" \"Monitor RPM\" ; \n" + 
		 		"\"Monitor Fuel Consumption\": Measures Methods [temperature] ; \n" + 
		 		"\"Monitor temperatures\": engine [coolant] transmission oil ; \n" + 
		 		"Measures: [\"1 per km\"] [\"gallon per mile\"] ; \n" + 
		 		"Methods: [\"Based on type of driving\"] [\"Based on driving\"] [\"Based on distance\"] ; \n" + 
		 		"temperature: [\"F2\"] [\"F1\"] ;"
		 		+ ")");
		 System.err.println("#" + fm2.counting());
	}

}
