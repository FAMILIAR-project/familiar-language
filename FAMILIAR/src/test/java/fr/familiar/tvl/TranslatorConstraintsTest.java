/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.tvl;

import org.junit.Test;

/**
 * 
 * @author Charles Vanbeneden
 * 
 */
public class TranslatorConstraintsTest extends TranslatorTest {

	// simple constraint test - Or
	@Test
	public void TranslatorConstraintsTest1() throws Exception {
		String input = "root A { group someOf{ B, C, D } C || D;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest1 :: Simple or contraint test - " + _translator.getFAMILIARFMLOutput());
	}

	// simple constraint test - or + not
	@Test
	public void TranslatorConstraintsTest2() throws Exception {
		String input = "root A { group someOf{ B, C, D } !C || !D;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest2 :: Simple constraint test - or + not - " + _translator.getFAMILIARFMLOutput());
	}

	// simple constraint test - and
	@Test
	public void TranslatorConstraintsTest3() throws Exception {
		String input = "root A { group someOf{ B, C, D } B && C;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest3 :: Simple constraint test - and - " + _translator.getFAMILIARFMLOutput());
	}

	// simple constraint test - and + not
	@Test
	public void TranslatorConstraintsTest4() throws Exception {
		String input = "root A { group someOf{ B, C, D } B && !C;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest4 :: Simple constraint test - and + not - " + _translator.getFAMILIARFMLOutput());
	}

	// simple constraint test - implies
	@Test
	public void TranslatorConstraintsTest5() throws Exception {
		String input = "root A { group someOf{ B, C, D } B->C;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest5 :: Simple constraint test - implies - " + _translator.getFAMILIARFMLOutput());
	}

	// and + not + or (xor relation)
	@Test
	public void TranslatorConstraintsTest6() throws Exception {
		String input = "root A { group someOf{ B, C, D } (B || !C) && (!B || C);}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest6 :: Simple constraint test - and + not + or - " + _translator.getFAMILIARFMLOutput());
	}

	// not simplification (xor relation)
	@Test
	public void TranslatorConstraintsTest7() throws Exception {
		String input = "root A { group someOf{ B, C, D } (!(B || !C) || !(!B || C));}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest7 :: Simple constraint test - not simplification - "
				+ _translator.getFAMILIARFMLOutput());
	}

	// true - false
	@Test
	public void TranslatorConstraintsTest8() throws Exception {
		String input = "root A { group someOf{ B, C, D } B && true || C && false;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest8 :: Simple constraint test - true + false - " + _translator.getFAMILIARFMLOutput());
	}

	// Double constraint test - double implies
	@Test
	public void TranslatorConstraintsTest9() throws Exception {
		String input = "root A { group someOf{ B, C, D } B->C; C-> B;}";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest9 :: Double constraint test - double implies - " + _translator.getFAMILIARFMLOutput());
	}

	// Double constraint test - double implies
	@Test
	public void TranslatorConstraintsTest10() throws Exception {
		String input = "root A { group someOf{ B, C, D } (B && C); C || D; B -> !D; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest10 :: Triple constraint test - All basic types - "
				+ _translator.getFAMILIARFMLOutput());
	}

	// Unsatisfaisable constraint test
	@Test
	public void TranslatorConstraintsTest11() throws Exception {
		String input = "root A { group someOf{ B, C, D } !B; !C; !D; !B->!D; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest11 :: Unsatisfaisable constraint test - " + _translator.getFAMILIARFMLOutput());
	}

	// Constraint on sub-features
	@Test
	public void TranslatorConstraintsTest12() throws Exception {
		String input = "root A { group someOf{ B { group oneOf { E, F } }, C, D } C -> E; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest12 :: Constraint on sub-features - " + _translator.getFAMILIARFMLOutput());
	}

	// Constraint on boolean attributes
	@Test
	public void TranslatorConstraintsTest13() throws Exception {
		String input = "root A { bool e; bool f; group oneOf{ B, C, D } e -> B; e -> !f; f-> !e; f -> D; e == true; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest13 :: Constraint on boolean attributes - " + _translator.getFAMILIARFMLOutput());
	}

	// Parent reference
	@Test
	public void TranslatorConstraintsTest14() throws Exception {
		String input = "root A { group someOf{ B { group allOf { E { E -> parent.parent.C; }, F} }, C, D } }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest14 :: Parent reference test - " + _translator.getFAMILIARFMLOutput());
	}

	// Constraint on boolean attributes groups, subgroups, boolean attribute,
	// basic constraints - H selected -> D and !C
	@Test
	public void TranslatorConstraintsTest15() throws Exception {
		String input = "root A { bool e; group someOf{ B { bool g; group oneOf { H, F } H -> g; g -> parent.e; }, C, D } e -> D; D -> !C; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest15 :: Constraint on boolean attributes - " + _translator.getFAMILIARFMLOutput());
	}

	// Constraint with 'this' accessor
	@Test
	public void TranslatorConstraintsTest16() throws Exception {
		String input = "root Car { this.mainColor != this.optionnalColor; this.mainColor -> Nissan; group oneof { Nissan, Ford } bool mainColor; bool optionnalColor; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest16 :: Constraint with 'this' accessor - " + _translator.getFAMILIARFMLOutput());
	}

	// Constraint with 'root', 'parent', 'path' accessor
	@Test
	public void TranslatorConstraintsTest17() throws Exception {
		String input = "root Car { group someOf{ A,  B { group someOf { E, F } root.A || parent.B -> root.D && root.B.E && parent.B.F; }, C, D } }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest17 :: Constraint with 'root', 'parent', 'path' accessor - "
				+ _translator.getFAMILIARFMLOutput());
	}

	// Constraint with 'this' accessor
	@Test
	public void TranslatorConstraintsTest18() throws Exception {
		String input = "root A { group someOf{ G,  B { group someOf { E, F } this.E || this.F; }, C, D } this.C && this.D; }";
		compareProductionsTVLVersusFAMILIARFromTVLInput(input);
		System.out.println("TranslatorConstraintsTest18 :: Constraint with 'this' accessor - " + _translator.getFAMILIARFMLOutput());
	}

}
