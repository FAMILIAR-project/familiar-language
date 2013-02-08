package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.ContradictionException;

import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;

public class FeatureModelTestFullText {

	//private String testFilesPath = "/home/rm/workspace/TVLParser/test/";
//	private TVLParser Parser = null; 
	FeatureModel fm = null;
	
	@Before
	public void setUp() throws Exception {
				fm = new FeatureModel("root Vehicle { 	group oneof { 	Car { group oneof {	Ford, Ferrari } }, 	Truck { group oneof { Ford, Peugeot	} } } }",true);
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println(fm.getSatCalls() + " sat calls") ;
	//	Parser = null;
		fm = null;
	}

	@Test
	public void testFeatureModel() throws Exception {
		assertNotNull(fm);
		fm.include("Vehicle.Car.Ford", true);
		System.out.println(fm.toString());
	}

}
