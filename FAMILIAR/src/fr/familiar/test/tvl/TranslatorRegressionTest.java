package fr.familiar.test.tvl;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.familiar.fm.converter.tvl.TVLTranslator;
import fr.familiar.variable.FeatureModelVariable;

/**
 * 
 * @author Charles Vanbeneden
 * 
 */

public class TranslatorRegressionTest extends TranslatorTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	// Multiple & same feature names
	@Test
	public void testNamesRegression1() throws Exception {
		String input = "root A { group allOf { B, C { group allOf { B, D }}, D } }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("testNamesRegression1 :: Multiple same feature names : " + _translator.getFAMILIARFMLOutput());
	}

	// Comparing TVL and FAMILIAR productions of reserved name feature model
	// test
	@Test
	public void testNamesRegression2() throws Exception {
		String input = "root Temp_Var_2 { group allOf{ B, Temp_Var_5 } }";
		System.out.println("testNamesRegression2 :: Comparing TVL and FAMILIAR productions of reserved name feature model test");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// TVL BUG : Testing a single root feature model
	@Test
	public void translatorTestRegression1() throws Exception {
		String input = "root A {}";
		System.out.println("translatorTestRegression1 :: Testing a single root feature model");
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
	}

	// Simple enum test.
	@Test
	public void TranslatorAttributesRegression1() throws Exception {
		String inputTVL = " root A { bool b; bool c; test y; test z; group allOf { B, C, D, E, F}} enum test in {test1, test2};";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesRegression1 :: Simple enum test.");
	}

	// test1EnumBooleanForm test from TVL. Enum + 'this' accessor
	@Test
	public void TranslatorAttributesRegression2() throws Exception {
		String inputTVL = "enum color in {Red, Blue, White}; root Car { this.mainColor != this.optionnalColor; group oneof { Nissan, Ford } color mainColor; color optionnalColor; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesRegression2 :: test1EnumBooleanForm test from TVL. Enum + 'this' accessor");
	}

	// test1Constraints test from TVL. All constraint with all attribute type.
	@Test
	public void TranslatorAttributesRegression3() throws Exception {
		String inputTVL = "root Car {group oneof { Focus { bool rally; } } enum brand in {Ford, Nissan}; enum color in {blue, red, yellow, white, black}; int doorsNumber; int maxSpeed; real price; real size; bool sold; bool used; } root Car { sold && used; sold || used; sold -> used; sold <-> used; sold <- used; !(sold); !(sold == used); sold != used; doorsNumber == maxSpeed; price == size; maxSpeed == size; true; false; doorsNumber <= maxSpeed; price <= size; maxSpeed <= size; doorsNumber < maxSpeed; price < size; maxSpeed < size; doorsNumber >= maxSpeed; price >= size; maxSpeed >= size; doorsNumber > maxSpeed; price > size; maxSpeed > size; brand in {Nissan, Ford}; maxSpeed in [180..250]; price in {15000, 17000, 19000.50}; this.Focus requires Car; root.Focus excludes Car; used ? true : false; and (sold, used, this.Focus.rally); or (sold, true); xor (false, used); and (selectedchildren.rally); or (selectedchildren.rally); xor (selectedchildren.rally); and (children.rally); or (children.rally); xor (children.rally);}";
		_translator = new TVLTranslator(inputTVL);
		String inputFAMILIAR = _translator.getFAMILIARFMLOutput();
		_shell.parse("fm = " + inputFAMILIAR);
		FeatureModelVariable fmVariable = getFMVariable("fm");
		assertTrue(_translator.hasWarnings());
		assertTrue("Size = " + fmVariable.configs().size(), fmVariable.configs().size() > 0);
		System.out.println("TranslatorAttributesRegression3 :: test1Constraints test from TVL. All constraint with all attribute type.");
	}

	// TVL problem : Int not supported ! test9Constraints test from TVL. Function on int.
	@Test
	public void TranslatorAttributesTest12() throws Exception {
		String inputTVL = "root Car { int doorsNumber; real price; group oneof { Ferrari } max(doorsNumber); }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(inputTVL);
		System.out.println("TranslatorAttributesTest12 :: test9Constraints test from TVL. Function on int.");
	}

	@After
	public void tearDown() {
		super.tearDown();
	}
}