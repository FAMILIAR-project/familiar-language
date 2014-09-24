/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.editing.ModelComparator;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter;
import fr.familiar.FMLTest;
import fr.familiar.experimental.featureide.FMComparator;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher FeatureIDE converter
 */

@RunWith(value = Parameterized.class)
public class FMLConverterFeatureIDETest extends FMLTest {

	protected String _fm;
	protected String _expected;

	public FMLConverterFeatureIDETest(String fm, String expected) {
		_fm = fm;
		_expected = expected;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B C [D] ; (D -> !C); )",
						"A : [D] B C :: _A;\n%%\n\nD implies not C ;" },
				{ "FM (ZZZ: [B] [C] [E] ; (E -> C); )",
						"ZZZ : [B] [E] [C] :: _ZZZ;\n%%\n\nE implies C ;" },
				{ "FM (ZZZ: [B] [C] [E] ; B : I J [K] ; (E -> C); )",
						"ZZZ : [B] [E] [C] :: _ZZZ;\nB : J I [K] :: _B;\n%%\n\nE implies C ;" },
				{
						"FM (ZZZ: B [C] [E] ; B : I J [K] ; C : [X] Y; (E -> C); )",
						"ZZZ : B [E] [C] :: _ZZZ;\nB : J I [K] :: _B;\nC : [X] Y :: _C;\n%%\n\nE implies C ;" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )",
						"A : B+ [E] C :: _A;\nB : H | G | F ;" },
				{ "FM (A: B C [E] ; C: (F|G|H)+ ; B : (I|J) ; (F -> !E); )",
						"A : B [E] C+ :: _A;\nB : J | I ;\nC : H | G | F ;\n%%\n\nF implies not E ;" },
				// { "FM (A: B C [E] [F] ; (F -> !E); (F | !A); )",
				// "A : B [E] C [F] :: _A;\n%%\n\nF implies not E ;\nF or not A ;"
				// }, // propositional constraints
				// { "FM (A: B C [E] ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				// { "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				// {
				// "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )"
				// },
				{
						"FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )",
						"A : D* X [B] Y Z C* :: _A;\nD : J | I | H | K ;\nC : E | G | F ;" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )",
						"A : [D] X [B] Y Z [C] :: _A;\nD : J | I | H | K ;\nC : E | G | F ;" },

				{
						"FM (fm4: A D G ; A : B C; D : E F ; G : (H|I|J)+ ; (E -> H) ; (F -> !I) ; )",
						"fm4 : A D G+ :: _fm4;\nA : B C :: _A;\nD : E F :: _D;\nG : J | I | H ;\n%%\n\nE implies H ;\nF implies not I ;" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )",
						"A_ : A+ :: _A ;\nA : B | E | C ;\nC : H | G | F ;\n%%\n\nF and not E ;" },
				{
						FMLTest.FM_LAPTOPbis,
						"Laptop : Screen [Warranty] CG Battery RAM HDD CPU Connectivity :: _Laptop;\n"
								+ "Screen : s15 | s12 | s17 ;\n"
								+ "Warranty : year3theft | year2theft ;\n"
								+ "CG : Integrated | Standalone ;\n"
								+ "Battery : cells6 | cells9 ;\n"
								+ "RAM : Corsair4Go1030 | Kingstom4Go666 | Kingstom2Go666 ;\n"
								+ "HDD : WD160Go5400tr | WD500Go ;\n"
								+ "CPU : LowTDP | HighTDP ;\n"
								+ "Connectivity : [Bluetooth] Wifi :: _Connectivity;\n"
								+ "Integrated : GMA :: _Integrated;\n"
								+ "Standalone : Nvidia9400M | Nvidia8400M | Nvdia8600M | Nvidia3670 ;\n"
								+ "WD500Go : S7200tr | S5400tr ;\n"
								+ "LowTDP : Atom270 | AtomZ320 ;\n"
								+ "HighTDP : Core2T6600 | Core2P7350 ;\n"
								+ "Wifi : N | ABG ;\n" + "%%\n\n"
								+ "LowTDP implies not Standalone ;\n"
								+ "Standalone implies HighTDP ;" }, };
		return Arrays.asList(data);
	}

	@Test
	public void testConverter1() throws Exception {
		_shell.setVerbose(true);
		_shell.parse("fm1 =" + _fm);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		String representation = new FMLtoFeatureIDE(fm1).transformToText();
		assertMyStringEquals(_expected.trim(), representation.trim());

	}

	/**
	 * TODO: to improve
	 * http://stackoverflow.com/questions/2131997/checking-if-two-strings-are-permutations-of-each-other
	 * @param str1
	 * @param str2
	 */
	private void assertMyStringEquals(String str1, String str2) {
		if(str1.length() != str2.length())
			assertTrue(str1 + " vs " + str2, false);

		char[] a1 = str1.toCharArray() ; 
		Arrays.sort(a1) ;
		char[] a2 = str2.toCharArray() ; 
		Arrays.sort(a2);
		int len = a1.length;

			for(int i = 0; i < len; i++)
			  if(a2[i] != a1[i])
			    assertTrue(str1 + " vs " + str2, false);

			assertTrue(true);
		
	}

	@Test
	public void testConverter2() throws Exception {
		_shell.parse("fm1 =" + _fm);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		String representation = new FMLtoFeatureIDE(fm1).transformToText();

		FeatureModel fmide = parseFMIDEfromString(_expected);
		assertNotNull(fmide);
		FeatureModel fmactual = parseFMIDEfromString(representation);
		assertNotNull(fmactual);

		ModelComparator comparator = new ModelComparator(20000);
		Comparison comparison = FMComparator.convert(comparator.compare(fmide, fmactual));
		assertEquals(Comparison.REFACTORING, comparison);

	}

	/**
	 * this time: toFeatureIDE() test
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConverter3() throws Exception {
		_shell.parse("fm1 =" + _fm);

		FeatureModelVariable fm1 = getFMVariable("fm1");

		FeatureModel fmide = parseFMIDEfromString(_expected);
		assertNotNull(fmide);
		System.err.println("fmide=" + fmide);
		System.err.println("fmide=" + new GuidslWriter(fmide).writeToString());
		FeatureModel fmactual = new FMLtoFeatureIDE(fm1).convert();
		assertNotNull(fmactual);
		System.err.println("fmactual=" + fmactual);
		System.err.println("fmactual=" + new GuidslWriter(fmactual).writeToString());
		
		Collection<Feature> ftsA = fmide.getFeatures() ; // fmactual.getFeatures() ;
		for (Feature ftA : ftsA) {
			System.err.println("ft=" + ftA.getName() + " " + ftA.isAbstract());
		}
		System.err.println("\n\n");
		Collection<Feature> ftsB = fmactual.getFeatures() ;
		for (Feature ftB : ftsB) {
			System.err.println("ft=" + ftB.getName() + " " + ftB.isAbstract());
		}
		
		ModelComparator comparator = new ModelComparator(60000);
		Comparison comparison = FMComparator.convert(comparator.compare(fmide, fmactual));
		assertEquals(Comparison.REFACTORING, comparison);

	}

	protected FeatureModel parseFMIDEfromString(String str) throws Exception {
		FeatureModel fmide = new FeatureModel();
		GuidslReader reader1 = new GuidslReader(fmide);
		reader1.readFromString(str);
		return fmide;
	}

}
