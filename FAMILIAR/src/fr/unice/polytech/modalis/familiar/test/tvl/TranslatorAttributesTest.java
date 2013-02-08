package fr.unice.polytech.modalis.familiar.test.tvl;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;

import fr.unice.polytech.modalis.familiar.fm.converter.tvl.TVLTranslator;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * 
 * @author Charles Vanbeneden
 * 
 */
public class TranslatorAttributesTest extends TranslatorTest {

	// Boolean attributes test - basic test
	@Test
	public void TranslatorAttributesTest1() throws Exception {
		String input = "root A { bool b; group allOf { C, D } }";
		_translator = new TVLTranslator(input);
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod = productions.next();
		assertTrue(prod.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod.getFeatures().iterator();
		assertTrue("Feature A_Boolean_b is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_b") == 0);
		assertTrue("Feature D is wrong.", ((Mandatory) childrens.next()).getName().compareTo("D") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Mandatory) childrens.next()).getName().compareTo("C") == 0);
		System.out.println("TranslatorAttributesTest1 :: Boolean attributes test - " + _translator.getFAMILIARFMLOutput());
	}

	// Boolean attributes test - other type of attributes
	@Test
	public void TranslatorAttributesTest2() throws Exception {
		String input = "root A { bool b; bool e; int f; group allOf { C, D } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorAttributesTest2 :: Boolean attributes test - " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod = productions.next();
		assertTrue(prod.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod.getFeatures().iterator();
		assertTrue("Feature A_Boolean_e is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_e") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_b") == 0);
		assertTrue("Feature D is wrong.", ((Mandatory) childrens.next()).getName().compareTo("D") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Mandatory) childrens.next()).getName().compareTo("C") == 0);
	}

	// Boolean attributes test - XOR sub-group.
	@Test
	public void TranslatorAttributesTest3() throws Exception {
		String input = "root A { bool b; bool e; int f; group oneOf { C, D } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorAttributesTest3 :: Boolean attributes test - " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod = productions.next();
		assertTrue(prod.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod.getFeatures().iterator();
		assertTrue("Feature A_Boolean_e is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_e") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_b") == 0);
		String subgroup = ((Mandatory) childrens.next()).getName();
		assertTrue("Feature Temp_Feature0 is wrong.", subgroup.contains("Temp_Feature"));
		prod = productions.next();
		assertTrue("Sub-prod Temp_Feature0 is wrong.", subgroup.compareTo(prod.getName()) == 0);
		Child childXor = ((Xorgroup) prod.getFeatures().iterator().next());
		Iterator<String> iterator3 = ((Xorgroup) childXor).getFeatures().iterator();
		assertTrue("Feature D is wrong.", iterator3.next().compareTo("D") == 0);
		assertTrue("Feature C is wrong.", iterator3.next().compareTo("C") == 0);
	}

	// Boolean attributes test - OR sub-group.
	@Test
	public void TranslatorAttributesTest4() throws Exception {
		String input = "root A { bool b; bool e; int f; group someOf { C, D } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorAttributesTest4 :: Boolean attributes test - " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod = productions.next();
		assertTrue(prod.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod.getFeatures().iterator();
		assertTrue("Feature A_Boolean_e is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_e") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_b") == 0);
		String subgroup = ((Mandatory) childrens.next()).getName();
		assertTrue("Feature Temp_Feature0 is wrong.", subgroup.contains("Temp_Feature"));
		prod = productions.next();
		assertTrue("Sub-prod Temp_Feature0 is wrong.", subgroup.compareTo(prod.getName()) == 0);
		Child childXor = ((Orgroup) prod.getFeatures().iterator().next());
		Iterator<String> iterator3 = ((Orgroup) childXor).getFeatures().iterator();
		assertTrue("Feature D is wrong.", iterator3.next().compareTo("D") == 0);
		assertTrue("Feature C is wrong.", iterator3.next().compareTo("C") == 0);
	}

	// Boolean attributes test - special cardinality sub-group.
	@Test
	public void TranslatorAttributesTest5() throws Exception {
		String input = "root A { bool b; bool e; int f; group [0..1] { C, D } }";
		_translator = new TVLTranslator(input);
		System.out.println("TranslatorAttributesTest5 :: Boolean attributes test - " + _translator.getFAMILIARFMLOutput());
		Iterator<Production> productions = _translator.getFAMILIARFeatureModel().getFeatures().iterator();
		Production prod = productions.next();
		assertTrue(prod.getName().compareTo("A") == 0);
		Iterator<Child> childrens = prod.getFeatures().iterator();
		assertTrue("Feature A_Boolean_e is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_e") == 0);
		assertTrue("Feature A_Boolean_b is wrong.", ((Optional) childrens.next()).getName().compareTo("A_Boolean_b") == 0);
		String subgroup = ((Mandatory) childrens.next()).getName();
		assertTrue("Feature Temp_Feature0 is wrong.", subgroup.contains("Temp_Feature"));
		prod = productions.next();
		assertTrue("Sub-prod Temp_Feature0 is wrong.", subgroup.compareTo(prod.getName()) == 0);
		Iterator<Child> iterator3 = prod.getFeatures().iterator();
		assertTrue("Feature D is wrong.", ((Optional) iterator3.next()).getName().compareTo("D") == 0);
		assertTrue("Feature C is wrong.", ((Optional) iterator3.next()).getName().compareTo("C") == 0);
		assertTrue("Feature Temp_Var_8 is wrong.", ((Optional) iterator3.next()).getName().compareTo("Temp_Var_8") == 0);
	}

	// Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 2
	// bool attributes
	@Test
	public void TranslatorAttributesTest6() throws Exception {
		String inputTVL = "root A { bool b; bool c; group [2..4] { B, C, D, E, F}}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out
				.println("TranslatorAttributesTest6 :: Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 2 bool attributes");
	}

	// Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 2
	// bool attributes - 1 int attribute
	@Test
	public void TranslatorAttributesTest7() throws Exception {
		String inputTVL = "root A { bool b; bool c; int d; group [2..4] { B, C, D, E, F}}";
		_translator = new TVLTranslator(inputTVL);
		String inputFAMILIAR = _translator.getFAMILIARFMLOutput();
		_shell.parse("fm = " + inputFAMILIAR);
		FeatureModelVariable fmVariable = getFMVariable("fm");
		assertTrue(_translator.hasWarnings());
		assertTrue(fmVariable.configs().size() > 0);
		System.out
				.println("TranslatorAttributesTest7 :: Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 2 bool attributes - 1 ignored int attribute");
	}

	// Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 4
	// bool attributes - sub attributes
	@Test
	public void TranslatorAttributesTest8() throws Exception {
		String inputTVL = "root A { bool b; bool c; group [2..4] { B {bool d;}, C, D, E{bool x;}, F}}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out
				.println("TranslatorAttributesTest8 :: Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 4 bool attributes - sub attributes");
	}

	// Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 4
	// bool attributes - 1 int attribute - sub attributes
	@Test
	public void TranslatorAttributesTest9() throws Exception {
		String inputTVL = "root A { bool b; bool c; group [2..4] { B {bool d; int f;}, C, D, E{bool x;}, F}}";
		_translator = new TVLTranslator(inputTVL);
		String inputFAMILIAR = _translator.getFAMILIARFMLOutput();
		_shell.parse("fm = " + inputFAMILIAR);
		FeatureModelVariable fmVariable = getFMVariable("fm");
		assertTrue(_translator.hasWarnings());
		assertTrue(fmVariable.configs().size() > 0);
		System.out
				.println("TranslatorAttributesTest9 :: Comparing TVL and FAMILIAR productions of 2..4 group - 5 children - 4 bool attributes - 1 int attribute - sub attributes");
	}

	// test9Constraints test from TVL. Function on int.
	@Test
	public void TranslatorAttributesTest10() throws Exception {
		String inputTVL = "root Foo{ bool a; bool b; group allOf{ C, D }}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesTest10 :: Test production bool.");
	}

	@Test
	public void TranslatorAttributesTest11() throws Exception {
		String inputTVL = "root Foo{ bool a; bool b; group allOf{ C, D } a -> b;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesTest11 :: Test production bool + constraint - " + _translator.getFAMILIARFMLOutput());
	}

	@Test
	public void TranslatorAttributesTest12() throws Exception {
		String inputTVL = "root Foo{ bool a; bool b; bool e; group allOf{ C, D } a -> b;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesTest12 :: Test production bool + constraint.");
	}

}
