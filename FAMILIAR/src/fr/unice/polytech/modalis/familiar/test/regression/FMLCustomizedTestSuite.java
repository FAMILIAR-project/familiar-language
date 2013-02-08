package fr.unice.polytech.modalis.familiar.test.regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.unice.polytech.modalis.familiar.test.FMLCoresTest;
import fr.unice.polytech.modalis.familiar.test.FMLCountingTest;
import fr.unice.polytech.modalis.familiar.test.FMLDeadsTest;
import fr.unice.polytech.modalis.familiar.test.FMLExtractTest;
import fr.unice.polytech.modalis.familiar.test.FMLIntegerTest;
import fr.unice.polytech.modalis.familiar.test.FMLMergeOperationsTest;
import fr.unice.polytech.modalis.familiar.test.FMLMergeOperatorTest;
import fr.unice.polytech.modalis.familiar.test.FMLUnmapTest;
import fr.unice.polytech.modalis.familiar.test.VideoSurveillanceICECCSTest;
import fr.unice.polytech.modalis.familiar.test.VideoSurveillanceREFSQTest;

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
