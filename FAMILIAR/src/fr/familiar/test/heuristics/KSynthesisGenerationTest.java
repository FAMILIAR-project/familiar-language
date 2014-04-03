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
