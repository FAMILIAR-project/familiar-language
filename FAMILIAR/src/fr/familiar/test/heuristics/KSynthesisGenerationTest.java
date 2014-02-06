package fr.familiar.test.heuristics;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableWithSynchronizedFormula;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;

public class KSynthesisGenerationTest extends KSynthesisTest {

	@Test
	public void testEquality() {
		List<FeatureModelVariable> fms = getSPLOTFeatureModels();
		for (FeatureModelVariable fm : fms) {
			InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm);
			FeatureModelVariable generatedFM = synthesizer.computeCompleteFeatureModel();
			assertEquals(Comparison.REFACTORING, fm.compare(generatedFM));
			
			FeatureModelVariable copyFM = new FeatureModelVariableWithSynchronizedFormula(fm.getIdentifier() + "_synthesis",
					new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()),
					fm.getFormula().clone());
			assertEquals(Comparison.REFACTORING, fm.compare(copyFM));
		}
		
	}
}
