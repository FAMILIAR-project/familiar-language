package fr.familiar.test.regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.familiar.test.FMLCoresTest;
import fr.familiar.test.FMLCountingTest;
import fr.familiar.test.FMLDeadsTest;
import fr.familiar.test.FMLExtractTest;
import fr.familiar.test.FMLIntegerTest;
import fr.familiar.test.FMLMergeOperationsTest;
import fr.familiar.test.FMLMergeOperatorTest;
import fr.familiar.test.FMLUnmapTest;
import fr.familiar.test.VideoSurveillanceICECCSTest;
import fr.familiar.test.VideoSurveillanceREFSQTest;

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
