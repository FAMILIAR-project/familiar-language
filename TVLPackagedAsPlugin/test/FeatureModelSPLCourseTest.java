package test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class FeatureModelSPLCourseTest {

	@Test
	public void testAudiUnsatisfiable() throws Exception {
		
		TVLParser parser = new TVLParser(new File("test/audi.tvl"));
		FeatureSymbol root = parser.getRoot();
		Assert.assertNotNull(root);
		FeatureModel fm = new FeatureModel(root) ;
		Assert.assertFalse(fm.hasAtLeastOneSolution()); // unsatisfiable! (over constrained)
	}
	
	@Test
	public void testSkodaPerformanceConfiguration() throws Exception {
		
		TVLParser parser = new TVLParser(new File("test/skoda.tvl"));
		FeatureSymbol root = parser.getRoot();
		Assert.assertNotNull(root);
		FeatureModel fm = new FeatureModel(root) ;
		Assert.assertTrue(fm.hasAtLeastOneSolution()); // no problem
		
		fm.include("Yeti_E", false);
		
	}
}
