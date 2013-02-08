package fr.unice.polytech.modalis.familiar.test.tvl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;

import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModelReader;
import fr.unice.polytech.modalis.familiar.fm.basic.FeatureModelStringBuilder;
import fr.unice.polytech.modalis.familiar.fm.converter.tvl.FeatureModelInvalidException;
import fr.unice.polytech.modalis.familiar.fm.converter.tvl.TVLTranslator;

/**
 * 
 * @author Charles Vanbeneden
 * 
 */
public class TranslatorProductionTest extends TranslatorTest {

	// Basic test of 1 root with 2 mandatory children
	@Test
	public void TranslatorProductionTest1() throws Exception {
		String input = "root A { group allOf { B, C } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest1 :: " + _translator.getFAMILIARFMLOutput());
		// ouput = "FM (A: B C ; )";
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod1 = productions.next();
		// Root feature called A
		assertTrue(prod1.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod1.getFeatures().iterator();
		// Mandatory child B
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("B") == 0);
		// Mandatory child C
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("C") == 0);
		// No other children
		assertFalse(childrens.hasNext());
	}

	// Test of optional relation in child
	@Test
	public void TranslatorProductionTest2() throws Exception {
		String input = "root A { group allOf { opt B, C } }";
		// ouput = "FM (A: [B] C ; )";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest2 :: " + _translator.getFAMILIARFMLOutput());

		assertTrue(((Optional) _translator.getFAMILIARFeatureModel().getFeatures().iterator().next().getFeatures().iterator().next())
				.getName().compareTo("B") == 0);
	}

	// Test of second production
	@Test
	public void TranslatorProductionTest3() throws Exception {
		String input = "root A { group allOf { B {group allOf {D, E}}, C } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest3 :: " + _translator.getFAMILIARFMLOutput());
		// ouput = "FM (A: B C ; )";
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod1 = productions.next();
		// Root feature called A
		assertTrue(prod1.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod1.getFeatures().iterator();
		// Mandatory child B
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("B") == 0);
		// Mandatory child C
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("C") == 0);
		// No other children
		assertFalse(childrens.hasNext());
		// Second production, B sub-features
		Production prod2 = productions.next();
		// Second production B
		assertTrue(prod2.getName().compareTo("B") == 0);
		childrens = prod2.getFeatures().iterator();
		// Mandatory child D
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("D") == 0);
		// Mandatory child E
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("E") == 0);
	}

	// Test of XOR group
	@Test
	public void TranslatorProductionTest4() throws Exception {
		String input = "root A { group oneOf { B, C } }";
		// output = FM (A: (B|C) ; )
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest4 :: " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod1 = productions.next();
		Iterator<Child> child = prod1.getFeatures().iterator();
		// Getting the xorgroup construction
		Child xorgroup = child.next();
		Iterator<String> iteratorChildrens = ((Xorgroup) xorgroup).getFeatures().iterator();
		// Testing first feature of the xor construction
		assertTrue(iteratorChildrens.next().compareTo("B") == 0);
		// Testing second feature of the xor construction
		assertTrue(iteratorChildrens.next().compareTo("C") == 0);
	}

	// Basic Wrong syntax test
	@Test
	public void testFailure1() {
		String input = "root "; // syntax error
		try {
			_translator = new TVLTranslator(input);
			// Should return this exception...
		} catch (Exception e) {
			assertNotNull(e);
			System.out.println("testFailure1 :: " + e.getMessage());
		}
	}

	// Test of OR group
	@Test
	public void TranslatorProductionTest5() throws Exception {
		String input = "root A { group someOf { B, C } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest5 :: " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod1 = productions.next();
		Iterator<Child> child = prod1.getFeatures().iterator();
		// Getting the orgroup construction
		Child orgroup = child.next();
		Iterator<String> iteratorChildrens = ((Orgroup) orgroup).getFeatures().iterator();
		// Testing first feature of the or construction
		assertTrue(iteratorChildrens.next().compareTo("B") == 0);
		// Testing second feature of the or construction
		assertTrue(iteratorChildrens.next().compareTo("C") == 0);
	}

	// Construction of Xor and Or groups. Xor and Mandatory with an unique
	// feature.
	@Test
	public void TranslatorProductionTest6() throws Exception {
		String input = "root Test {group someof{" + "A{group allOf{C}}," + "B{group oneOf{D}}" + "}}";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorProductionTest6 :: " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod1 = productions.next();
		// root feature name
		assertTrue(prod1.getName().compareTo("Test") == 0);
		Iterator<Child> child = prod1.getFeatures().iterator();
		Child orgroup = child.next();
		Iterator<String> iteratorChildrens = ((Orgroup) orgroup).getFeatures().iterator();
		// First orgroup feature
		assertTrue(iteratorChildrens.next().compareTo("A") == 0);
		// Second orgroup feature
		assertTrue(iteratorChildrens.next().compareTo("B") == 0);
		// A unfolding
		Production prod2 = productions.next();
		Iterator<Child> childrens = prod2.getFeatures().iterator();
		// A's subfeature C is effectively mandatory
		assertTrue(((Mandatory) childrens.next()).getName().compareTo("C") == 0);
		Production prod3 = productions.next();
		Iterator<Child> childrens2 = prod3.getFeatures().iterator();
		assertTrue(((Mandatory) childrens2.next()).getName().compareTo("D") == 0);

	}

	@Test
	public void TranslatorProductionTest7() throws Exception {

		String input = "root A {\r\n" + "		       group allOf {\r\n" + "		                     B,\r\n" + "		                     C,\r\n"
				+ "		                     D\r\n" + "		                   }\r\n" + "		       } \r\n" + "		B {\r\n" + "		  group allOf {\r\n"
				+ "		                E,\r\n" + "		                opt F\r\n" + "		              }\r\n" + "		  } \r\n" + "		F {\r\n"
				+ "		  group allOf {\r\n" + "		                I,\r\n" + "		                opt J\r\n" + "		              }\r\n" + "		}";

		String expected = "FM (A: D B C ; B: E [F] ; F: I [J] ; )";

		_translator = new TVLTranslator(input);

		FeatureModel fm = _translator.getFAMILIARFeatureModel();
		FeatureModel fmExpected = new FMLFeatureModelReader().parseString(expected);

		assertEquals(new FeatureModelStringBuilder(fm).toString(), new FeatureModelStringBuilder(fmExpected).toString());

		/* certainly equivalent to the code above */
		String output = _translator.getFAMILIARFMLOutput();
		assertEquals(expected, output);
		System.out.println("TranslatorProductionTest7 :: " + _translator.getFAMILIARFMLOutput());
	}

	// Comparing TVL and FAMILIAR productions - Test 1 example - Mandatory
	// relation
	@Test
	public void TranslatorProductionTest8() throws Exception {
		String input = "root A { group allOf { B, C } }";
		System.out.println("TranslatorProductionTest8 :: Comparing TVL and FAMILIAR productions - Test 1 example - Mandatory relation");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 2 example - Optional
	// relation
	@Test
	public void TranslatorProductionTest9() throws Exception {
		String input = "root A { group allOf { opt B, C } }";
		System.out.println("TranslatorProductionTest9 :: Comparing TVL and FAMILIAR productions - Test 2 example - Optional relation");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 2 example - Optional
	// relation
	@Test
	public void TranslatorProductionTest10() throws Exception {
		String input = "root A { group allOf { opt B } }";
		System.out.println("TranslatorProductionTest10 :: Comparing TVL and FAMILIAR productions - Test 2 example - Optional relation");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 3 example - Second
	// production
	@Test
	public void TranslatorProductionTest11() throws Exception {
		String input = "root A { group allOf { B {group allOf {D, E}}, C } }";
		System.out.println("TranslatorProductionTest11 :: Comparing TVL and FAMILIAR productions - Test 3 example - Second production");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 4 example - XorGroup
	@Test
	public void TranslatorProductionTest12() throws Exception {
		String input = "root A { group oneOf { B, C } }";
		System.out.println("TranslatorProductionTest12 :: Comparing TVL and FAMILIAR productions - Test 4 example - XorGroup");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 6 example - OrGroup
	@Test
	public void TranslatorProductionTest13() throws Exception {
		String input = "root A { group someOf { B, C } }";
		System.out.println("TranslatorProductionTest13 :: Comparing TVL and FAMILIAR productions - Test 6 example - OrGroup");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 7 example - Construction of
	// Xor and Or groups. Xor and Mandatory with an unique feature.
	@Test
	public void TranslatorProductionTest14() throws Exception {
		String input = "root Test {group someof{" + "A{group allOf{C}}," + "B{group oneOf{D}}" + "}}";
		System.out
				.println("TranslatorProductionTest14 :: Comparing TVL and FAMILIAR productions - Test 7 example - Construction of Xor and Or groups. Xor and Mandatory with an unique feature.");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Comparing TVL and FAMILIAR productions - Test 8 example - Some relation
	// tests.
	@Test
	public void TranslatorProductionTest15() throws Exception {
		String input = "root A {\r\n" + "		       group allOf {\r\n" + "		                     B,\r\n" + "		                     C,\r\n"
				+ "		                     D\r\n" + "		                   }\r\n" + "		       } \r\n" + "		B {\r\n" + "		  group allOf {\r\n"
				+ "		                E,\r\n" + "		                opt F\r\n" + "		              }\r\n" + "		  } \r\n" + "		F {\r\n"
				+ "		  group allOf {\r\n" + "		                I,\r\n" + "		                opt J\r\n" + "		              }\r\n" + "		}";
		System.out.println("TranslatorProductionTest15 :: Comparing TVL and FAMILIAR productions - Test 8 example - Some relation tests.");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// 5 sub features - 2..4 group constraint -
	@Test
	public void TranslatorProductionTest16() throws Exception {
		String inputTVL = "root A { group [2..4] { B, C, D, E, F}}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorProductionTest16 :: Comparing TVL and FAMILIAR productions of 2..4 group - 5 children");
	}

	// 3 sub features - 0..1 group constraint - Comparing TVL and FAMILIAR
	// productions
	@Test
	public void TranslatorProductionTest17() throws Exception {
		String inputTVL = "root A { group [0..1] { B, C, D }}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorProductionTest17 :: Comparing TVL and FAMILIAR productions of 0..1 group - 3 children");
	}

	// Comparing TVL and FAMILIAR productions of 0..4 group - 5 children
	@Test
	public void TranslatorProductionTest18() throws Exception {
		String inputTVL = "root A { group [4..4] { B, C, D, E, F }}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorProductionTest18 :: Comparing TVL and FAMILIAR productions of 4..4 group - 5 children");
	}

	// Comparing TVL and FAMILIAR productions of multiple cardinality sub-groups
	@Test
	public void TranslatorProductionTest19() throws Exception {
		String inputTVL = "root A { group [0..3] { B { group [0..1] { E, F, G } }, C { group [2..3] { H, I, J } }, D } }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorProductionTest19 :: Comparing TVL and FAMILIAR productions of multiple cardinality sub-groups");
	}

	// test4Cardinality from TVL tests
	@Test
	public void TranslatorProductionTest20() throws Exception {
		String inputTVL = "root Animal { group allof { Dog { group [-1..2] { Labrador, Caniche	} }, Cat { group someof { Siamois, Birman } } }	}";
		try {
			System.out
					.print("TranslatorProductionTest20 :: test4Cardinality from TVL tests. Special cardinality with neg value. Error Message :");
			compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		} catch (FeatureModelInvalidException e) {
			System.out.println(e.getMessage());
			assertNotNull(e);
		}

	}

	// Comparing TVL and FAMILIAR productions of multiple cardinality sub-groups
	@Test
	public void TranslatorProductionTest21() throws Exception {
		compareProductionsTVLVersusFAMILIARFromTVLInput(FM_LAPTOP_TVL);
		System.out.println("TranslatorProductionTest21 :: Comparing TVL and FAMILIAR productions of Laptop Example");
	}

}
