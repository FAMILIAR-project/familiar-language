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

package fr.familiar.regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.familiar.FMLCoresTest;
import fr.familiar.FMLCountingTest;
import fr.familiar.FMLDeadsTest;
import fr.familiar.FMLExtractTest;
import fr.familiar.FMLIntegerTest;
import fr.familiar.FMLMergeOperationsTest;
import fr.familiar.FMLMergeOperatorTest;
import fr.familiar.FMLUnmapTest;
import fr.familiar.VideoSurveillanceICECCSTest;
import fr.familiar.VideoSurveillanceREFSQTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		// AdamArchTest.class,
		// BDDOperationsTest.class,
		// FDConverterTest.class,
		//FMLMergeSliceTest.class,
		//FMLAggregateTest.class,
		//FMLCoresTest.class,
		// FMLBasicInterpreterTest.class,
		// FMLCleanupTest.class,
		// FMLConfiguration2Test.class,
		//FMLConfigurationTest.class,
		// FMLConverterFeatureIDETest.class,
		// FMLConverterTest.class,
		
		//FMLCounting2Test.class,
		//FMLCountingConfigsTest.class,
		FMLDeadsTest.class,
		FMLCountingTest.class,
		// FMLCustomizedTestSuite.class,
		
		FMLCoresTest.class,
		// FMLExpressionTest.class,
		FMLExtractTest.class,
		
		// FMLFMBuilderTest.class,
		// FMLFeatureOperationsTest.class,
		// FMLFeatures.class,
		FMLIntegerTest.class,
		
		// FMLIsValid2Test.class,
		// FMLIsValidTest.class,
		FMLMergeOperationsTest.class, FMLMergeOperatorTest.class,
		FMLMedicalImageSliceTest.class, FMLMiscTest.class,
		// FMLQualitySPLTest.class,
		// FMLReaderWriterTest.class,
		// FMLRefactoring2Test.class,
		// FMLRefactoringTest.class,
		// FMLRemoveFeatureTest.class,
		// FMLRenameFeatureTest.class,
		// FMLRendererTest.class,
		// FMLScriptTest.class,
//FMLSlicerTest.class,
		// FMLSlicerUtilityTest.class,
		// FMLStringTest.class,
		// FMLTest.class,
		// FMLTutorialTest.class,
		FMLUnmapTest.class,
		// FMLVisualization2Test.class,
		// FMLVisualizationTest.class,
		// FeatureNodeUtilsTest.class,
		VideoSurveillanceICECCSTest.class, VideoSurveillanceREFSQTest.class, })
public class FMLCustomizedTestSuite {

}
