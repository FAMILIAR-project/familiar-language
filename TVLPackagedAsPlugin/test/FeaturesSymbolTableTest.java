package test;


import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.TestCase;

import org.junit.Test;

import be.ac.fundp.info.TVLParser.TVLParser;

public class FeaturesSymbolTableTest extends TestCase {
	
	//String testFilesPath = "/Users/phe/Documents/workspace/TVLParser/test/"; 
	String testFilesPath = "test/";
	@Test
	public void testOSL() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("root.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test1IDConflicts() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1IDConflicts.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IDEnumValuesConflictException"));
	}
	
	@Test
	public void test1Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test2Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllogicalIntervalSetExpressionException"));
	}
	
	@Test
	public void test3Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test3Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllogicalIntervalSetExpressionException"));
	}
	
	@Test
	public void test4Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test4Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("BadTypeSetExpressionException"));
	}
	
	@Test
	public void test5Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test5Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("TypeNotDefinedException"));
	}
	
	@Test
	public void test6Types() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test6Types.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllogicalSetExpressionException"));
	}
	
	@Test
	public void test1Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test2Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test3Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test3Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test4Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test4Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test5Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test5Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test6Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test6Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test7Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test7Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test8Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test8Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test9Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test9Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test10Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test10Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	@Test
	public void test11Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test11Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	@Test
	public void test12Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test12Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test13Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test13Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test14Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test14Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test15Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test15Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test16Constraints() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test16Constraints.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test1SharedFeatures() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1SharedFeatures.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test2SharedFeatures() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2SharedFeatures.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test1Cardinality() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1Cardinality.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test2Cardinality() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2Cardinality.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("ViolatedCardinalityException"));
	}
	
	@Test
	public void test3Cardinality() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test3Cardinality.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("BadCardinalityException"));
	}
	
	@Test
	public void test4Cardinality() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test4Cardinality.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("BadCardinalityException"));
	}
	
	@Test
	public void test5Cardinality() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test5Cardinality.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("BadCardinalityException"));
	}
	
	@Test
	public void test1LongID() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1LongID.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.isValid());
	}
	
	@Test
	public void test2LongID() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2LongID.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test3LongID() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test3LongID.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test4LongID() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test4LongID.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test5LongID() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test5LongID.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("IllegalConstraintException"));
	}
	
	@Test
	public void test1DetectCycle() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test1Cycle.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("CycleFoundException"));
	}
	
	@Test
	public void test2DetectCycle() throws FileNotFoundException {
		String filePath = this.testFilesPath.concat("test2Cycle.tvl");
		TVLParser parser = new TVLParser(new File(filePath));
		assertTrue(parser.getTypeError().getClass().toString().contains("CycleFoundException"));
	}

}
