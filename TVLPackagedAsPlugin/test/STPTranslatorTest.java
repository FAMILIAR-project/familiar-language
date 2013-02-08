package test;

import static org.junit.Assert.*;

import java.io.*;



import junit.framework.Assert;

import org.junit.Test;
import org.sat4j.specs.ContradictionException;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.Util.STPTranslator;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class STPTranslatorTest  {


	@Test
	public void testGetVariables() throws Exception {
		testFile("test/STPTranslator/variables1.tvl","/tmp/variables1.cvc");
	}

	private void testFile(String fileName, String cvcFile) throws Exception {
		// System.out.println("parsing "+fileName);
		TVLParser parser = new TVLParser(new File(fileName));
		TVLParser nfparser = new TVLParser(parser.getNormalForm());
		System.out.println("NORMALFORM=\n"+parser.getNormalForm());
		nfparser.run();
		System.out.println("parsing complete");
		FeatureSymbol root = nfparser.getFeaturesSymbolTable().getFeatureSymbol(nfparser.getFeaturesSymbolTable().getRootFeatureID()) ;
		STPTranslator stp = new STPTranslator(root,cvcFile); 
		stp.write();
	}
	
	private void testnfFile(String fileName, String cvcFile) throws Exception {
		// System.out.println("parsing "+fileName);
		TVLParser parser = new TVLParser(new File(fileName));
		
		//System.out.println("NORMALFORM=\n"+parser.getNormalForm());
		parser.run();
		//System.out.println("parsing complete");
		FeatureSymbol root = parser.getFeaturesSymbolTable().getFeatureSymbol(parser.getFeaturesSymbolTable().getRootFeatureID()) ;
		STPTranslator stp = new STPTranslator(root,cvcFile); 
		stp.write();
	}
	
	private int execute(String fileName) throws Exception {
	  System.out.println("executing "+fileName);
      String line;
      Process p = Runtime.getRuntime().exec("/usr/local/bin/stp -t "+fileName);
      BufferedReader bri = new BufferedReader
        (new InputStreamReader(p.getInputStream()));
      BufferedReader bre = new BufferedReader
        (new InputStreamReader(p.getErrorStream()));
      while ((line = bri.readLine()) != null) {
        System.out.println(line);
      }
      bri.close();
      while ((line = bre.readLine()) != null) {
        System.out.println(line);
      }
      bre.close();
      p.waitFor();
      System.out.println("Done. Return status = "+p.exitValue());
	 return p.exitValue();
	}

/*	@Test
	public void testGetVariables2() throws ContradictionException, UnsatisfiableModelException, IOException, AmbiguousReferenceException, SymbolNotFoundException, ChildrenFeatureNotFoundException {
		TVLParser parser = new TVLParser(new File("test/STPTranslator/drupa7_v2.tvl"));
		TVLParser nfparser = new TVLParser(parser.getNormalForm());
		nfparser.run();
		FeatureSymbol root = nfparser.getFeaturesSymbolTable().getFeatureSymbol(nfparser.getFeaturesSymbolTable().getRootFeatureID()) ;
		STPTranslator stp = new STPTranslator(root); 
		System.out.println(stp.write());
	}
	*/
	@Test
	public void testConstraints1() throws Exception {
		testFile("test/STPTranslator/constraints1.tvl","/tmp/constraints1.cvc");
	
	}
	
	@Test
	public void testExcludes() throws Exception {
		testFile("test/STPTranslator/excludes.tvl","/tmp/excludes.cvc");
	}
	
	private void writeFile(String tvlFile, String cvcFile) throws Exception {
		
		testFile(tvlFile,cvcFile);

	}
	
	@Test
	public void testAudi() throws Exception {
		 String tvlFile = "test/audi.tvl";
		 String cvcFile = "/tmp/audi.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}

	@Test
	public void testSkoda() throws Exception {
		 String tvlFile = "test/skoda.tvl";
		 String cvcFile = "/tmp/skoda.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testCardinalities1() throws Exception {
		String tvlFile = "test/STPTranslator/cardinalities1.tvl";
		 String cvcFile = "/tmp/cardinalities1.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testAudiPaper() throws Exception {
		String tvlFile = "/Users/rm/Dropbox/Doctorat/Papers/12-SMT-Workshop/vijay/tvl2stp/slides/audi-sample.tvl";
		 String cvcFile = "/Users/rm/Dropbox/Doctorat/Papers/12-SMT-Workshop/vijay/tvl2stp/slides/audi-sample.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testDrupal() throws Exception {
		String tvlFile = "test/drupal3.tvl";
		 String cvcFile = "/tmp/drupal3.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testEnum() throws Exception {
		String tvlFile = "test/testenum.tvl";
		 String cvcFile = "/tmp/testenum.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testcudfnf() throws Exception {
		String tvlFile = "/Users/rm/Dropbox/Doctorat/Papers/12-SMT-Workshop/vijay/tvl2stp/code/test.tvl";
		 String cvcFile = "/Users/rm/Dropbox/Doctorat/Papers/12-SMT-Workshop/vijay/tvl2stp/code/test.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	/*	@Test
	public void testSkoda() throws Exception {
		 String tvlFile = "test/skoda.tvl";
		 String cvcFile = "/tmp/skoda.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}
	
	@Test
	public void testDrupal() throws Exception {
		 String tvlFile = "test/STPTranslator/drupal.tvl";
		 String cvcFile = "/tmp/drupal.cvc";
		 writeFile(tvlFile,cvcFile);
		 Assert.assertEquals(0, execute(cvcFile));
	}*/
}
