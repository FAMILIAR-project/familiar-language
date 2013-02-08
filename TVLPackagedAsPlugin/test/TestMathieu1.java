package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Test;
import org.sat4j.specs.ContradictionException;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class TestMathieu1 {

	@Test
	public void test() throws Exception {
		TVLParser parser = new TVLParser(new File("test/audi.tvl"));
		 FeatureSymbol root = parser.getRoot();
		Assert.assertNotNull(root);
		
				System.err.println("root=" + root.getID());
				FeatureModel fm = new FeatureModel(root) ;
				fm.include("AudiCar", true); // ModelLine
				
	//		Assert.assertTrue(	fm.isIncluded("AudiCar", false));
			
		}
	}


