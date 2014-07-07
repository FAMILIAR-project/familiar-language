package fr.familiar.test.heuristics;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.FeatureModelVariableSATFormula;

/**
 * Experiment 4: Randomly generated FMs 
 * @author gbecan
 *
 */
public class ICSE2014Experiment4 extends KSynthesisTest {

	private static final double PERCENTAGE_OF_IMPLIES = 0.8;
	private static final int STEP = 10;
	private static final int FMS_GENERATED_FOR_EACH_FM = 10;

	private ConstraintGenerator constraintGenerator = new ConstraintGenerator();

	@Ignore
	@Test
	public void testGenerateConstraints() {
		List<FeatureModelVariable> fms = getSPLOTFeatureModels();
		


		for (int percentageOfMax = 80; percentageOfMax <= 100; percentageOfMax += STEP) {
			String outputFolder = "inputFML/ICSE2014-constraint-generator/" + (percentageOfMax / 10) + "/";
			
			System.out.println("Generating FMs with " + percentageOfMax + "% of maximum number of constraints per feature");
			System.out.println("in folder : " + outputFolder);
			
			
			for (FeatureModelVariable fm : fms) {
				
				for (int i=0; i<FMS_GENERATED_FOR_EACH_FM; i++) {
					
					FeatureModelVariable generatedFM = new FeatureModelVariableSATFormula(
							fm.getCompleteIdentifier(), fm.getFm().clone(), new SATFMLFormula(fm)); 
					constraintGenerator.clearFM(generatedFM);
					
					int nbFeatures = fm.features().size();
					int constraintPerFeature = (int) ((percentageOfMax / 100.0) * (nbFeatures -1) * nbFeatures);
					constraintGenerator.generateConstraints(generatedFM, constraintPerFeature, PERCENTAGE_OF_IMPLIES);
					
					writeFMToFile(outputFolder, null, fm.getCompleteIdentifier() + "_" + i, generatedFM);
				}
				System.out.print(".");
			}
			System.out.println(" done");
		}
		
		
	}
	
	@Test
	public void testOptimumBranching() {
		for (int percentageOfMax = 0; percentageOfMax <= 100; percentageOfMax += STEP) {
			System.out.println("Optimum branching for " + percentageOfMax + "% of maximum constraints");
			
			String inputFolder = "inputFML/ICSE2014-constraint-generator/" + (percentageOfMax / 10) + "/";
			List<FeatureModelVariable> fms = loadFeatureModelFolder(inputFolder, ".xml", null, null, "");
			testOptimumBranching(fms, false);
			System.out.println("---------------------------------------------------------");
		}
	}



}
