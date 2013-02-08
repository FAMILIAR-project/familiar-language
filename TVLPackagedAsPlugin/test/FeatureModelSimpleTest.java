package test;

import org.junit.Assert;
import org.junit.Test;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

/**
 * An entry point for any TVL API user: how to parse a TVL model? how to configure a TVL model?
 * @author macher
 *
 */
public class FeatureModelSimpleTest {
	
	@Test
	public void testParseAndConfigure() throws Exception {
		
		// PARSER
		TVLParser parser = new TVLParser("root Vehicle { " +
				"group oneof {" +
				"	Car {" +
				"		group oneof {" +
				"			Ford," +
				"			Ferrari" +
				"		}" +
					"}," +
				"" +
				"	Truck {" +
				"		group oneof {" +
				"			Ford," +
							"Peugeot " +
						"}" +
				"	}" +
				"}" +
			"}"
				);
		FeatureSymbol root = parser.getRoot();
		Assert.assertNotNull(root);
		System.err.println("root=" + root.getID());
		System.err.println("children of root=" + root.getChildrenFeatures());
		System.err.println("min .. max =" + root.getMinFeatureCardinality() + " " + root.getMaxFeatureCardinality());
		// ...
		
		
		// SOLVER/reasoner
		FeatureModel fm = new FeatureModel(root) ;
		System.err.println("" + fm.isSelectable("Car", false)); 
		System.err.println("" + fm.include("Car", false)); 
		Assert.assertTrue(fm.isIncluded("Car", false));
		
		
	}

}
