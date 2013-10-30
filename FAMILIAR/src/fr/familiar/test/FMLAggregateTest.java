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
package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.parser.AggregatorAnalyzer;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.VariabilityOperatorVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class FMLAggregateTest extends FMLTest {

	@Test
	public void testAggregate1() throws Exception {

		// _shell.parse("fm0 = FM (A : [B] [C] [D] [E]; ) ");
		_shell.parse("fm1 = FM (A0 : (B0|C0|D0)+; ) ");
		_shell.parse("fm2 = FM (A1 : (B1|D1|E1) ; )");
		_shell.parse("fm0 = FM (A2 : (B2|D2)+; ) ");
		_shell.parse("fm3 = FM (A3 : (B3|D3|E3|C3)+ ; )");

		_shell.parse("aggFM = aggregate fm*");

		FeatureModelVariable aggFM = getFMVariable("aggFM");
		System.err.println("aggFM=" + aggFM);

	}

	@Test
	public void withoutConstraints() throws Exception {

		_shell.parse("fm1 = FM (A : B C; )" + "fm2 = FM (D : E F; )"
				+ "fm3 = FM (G : (H|I|J)+ ; )"
				+ "fm4 = aggregate { fm1 fm2 fm3 }" + "");

		FeatureModelVariable fmv = getFMVariable("fm4");
		FeatureVariable ft = fmv.root();

		assertNotNull(ft);
		assertTrue(ft.name().equals("fm4")); // root of the aggregated FM should
												// be the name of the variable

	}

	@Test
	public void unamedAggregatedFM() throws Exception {

		_shell.parse("fm1 = FM (A : B C; )" + "fm2 = FM (D : E F; )"
				+ "fm3 = FM (G : (H|I|J)+ ; )"
				+ "ft = root aggregate { fm1 fm2 fm3 }" + "");
		Variable v = _environment.getVariable("ft");
		assertNotNull(v);
		assertTrue(v instanceof FeatureVariable);

		FeatureVariable ft = (FeatureVariable) v;

		assertTrue(ft.name().equals(AggregatorAnalyzer.DEFAULT_FAKEROOT_NAME)); // root
																		// of
																		// the
																		// aggregated
																		// FM
																		// has a
																		// default
																		// name

	}

	@Test
	public void testSpecificationConstraints() throws Exception {

		_shell.parse("cst1 = constraints (E -> H ; F -> !I ; )");
		Variable v = _environment.getVariable("cst1");
		assertNotNull(v);
		assertTrue(v instanceof SetVariable);

		SetVariable cv = (SetVariable) v;

		Set<Variable> constraints = cv.getVars() ;
		assertNotNull(constraints);
		assertTrue(constraints.size() == 2);
		for (Variable cst : constraints) {
			assertTrue(cst instanceof ConstraintVariable);
		}

	}

	@Test
	public void withConstraints() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C; )" + "fm2 = FM (D : E F; )"
				+ "fm3 = FM (G : (H|I|J)+ ; )"
				+ "cst1 = constraints (E -> H; F -> !I; )"
				+ "fm4 = aggregate { fm1 fm2 fm3 } withMapping cst1\n"
				+ "c = convert fm4 into featureide");
		Variable v = _environment.getVariable("fm4");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v;
		FeatureVariable ft = fmv.root();

		assertNotNull(ft);
		assertTrue(ft.name().equals("fm4")); // root of the aggregated FM should
												// be the name of the variable

		assertNotNull(fmv.getFm());
		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 2);

	}

	@Test
	public void testMap1() throws Exception {

		_shell.parse("fm1 = FM (A : B C; )" + "fm2 = FM (D : [E] F; )"
				+ "fm3 = aggregate { fm1 fm2 } \n"
				+ "map fm3 with constraints (D -> !B; )\n");

		FeatureModelVariable fmv = getFMVariable("fm3");
		FeatureVariable ft = fmv.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("fm3"));
		assertNotNull(fmv.getFm());
		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);

		// not valid

	}

	@Test
	public void testMap2() throws Exception {

		_shell.parse("fm1 = FM (A : (B|C) ; )" + "fm2 = FM (D : [E] F; )"
				+ "fm3 = FM (H : [I] J; J : (K|L|M)+ ; )"
				+ "fm4 = aggregate { fm1 fm2 fm3 } \n"
				+ "map fm4 with constraints (D -> !B; I -> C; K | I; )\n");

		FeatureModelVariable fmv = getFMVariable("fm4");
		FeatureVariable ft = fmv.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("fm4"));
		assertNotNull(fmv.getFm());
		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 3);

		// not valid

	}

	@Test
	public void testMap3() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints (!B | !C ;)\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(false, fm1.isValid());

	}

	@Test
	public void testMap4() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints (!B -> !C ;)\n"); // TODO: failed

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(true, fm1.isValid());

	}

	@Test
	public void testMap6() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; (!B -> !C) ; )\n"); // no problem!

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(true, fm1.isValid());

	}

	/**
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testMap7() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((!B -> !C) ;)\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(2, fm1.counting(), 0); // failed!
		assertEquals(true, fm1.counting() > 0); // failed!
		assertEquals(true, fm1.isValid());

	}

	/**
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testMap7bis() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; (!B -> !C) ;)\n"); // same without
																	// map style

		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(2, fm1.counting(), 0);
		assertEquals(true, fm1.counting() > 0);
		assertEquals(true, fm1.isValid());

	}

	/**
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testMap7bis2() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((B | !C) ;)\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(2, fm1.counting(), 0); // surprisingly it works!
		assertEquals(true, fm1.counting() > 0); // surprisingly it works!
		assertEquals(true, fm1.isValid());

	}

	/**
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testAggregate7() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "fm2 = aggregate { fm1 } withMapping constraints ((!B -> !C) ;)\n");

		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureVariable ft = fm2.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("fm2"));
		assertNotNull(fm2.getFm());
		Set<Expression<String>> constraints = fm2.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(2, fm2.counting(), 0);
		assertEquals(true, fm2.counting() > 0);
		assertEquals(true, fm2.isValid());

	}

	@Test
	public void testMap8() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((!B | !C) ;)\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureVariable ft = fm1.root();
		assertNotNull(ft);
		assertTrue(ft.name().equals("A"));
		assertNotNull(fm1.getFm());
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(0, fm1.counting(), 0); // non valid
		assertEquals(true, fm1.counting() == 0); // non valid
		assertEquals(false, fm1.isValid());

	}

	@Ignore
	@Test
	public void testMapFailed1() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "b = map fm1 with constraints ((!E | !C) ;)\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(1, constraints.size());
		_shell.setCountingStrategy(CountingStrategy.BDD_FML);
		_shell.setCountingStrategy(CountingStrategy.BDD_SPLOT); // fail during the
															// conversion
		assertEquals(2, fm1.counting(), 0); // valid

		BooleanVariable b = getBooleanVariable("b");
		assertFalse("map has detected some constraints unrelated to features",
				b.isTrue());

	}

	@Test
	public void testAddConstraint1() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "b = addConstraint constraint (!B | !C) to fm1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 1);
		assertEquals(0, fm1.counting(), 0); // non valid
		assertFalse("fm1 is no longer valid", fm1.isValid());

		BooleanVariable b = getBooleanVariable("b");
		assertTrue("addConstraint actually appliers", b.isTrue());

	}

	@Test
	public void withInternalConstraints1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B [C] [X] ; X : (Y|Z) ; C -> !Z; )"
				+ "fm2 = FM (D : E [F] [U]; F -> U ; )"
				+ "fm3 = FM (G : (H|I|J)+ ; )"
				+ "cst1 = constraints (E -> H; F -> !I; )"
				+ "fm4 = aggregate { fm1 fm2 fm3 } withMapping cst1\n"
				+ "c = convert fm4 into featureide");
		Variable v = _environment.getVariable("fm4");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v;
		FeatureVariable ft = fmv.root();

		assertNotNull(ft);
		assertTrue(ft.name().equals("fm4")); // root of the aggregated FM should
												// be the name of the variable

		assertNotNull(fmv.getFm());
		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 4); // 1 + 1 + 2

	}

	@Test
	public void withInternalConstraints2() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B [C] [X] ; X : (Y|Z) ; C -> !Z; )"
				+ "fm2 = FM (D : E [F] [U]; F -> U ; )\n"
				+ "fm3 = FM (G : (H|I|J)+ ; )\n"
				+ "cst1 = constraints (E -> H; F -> !I; )\n"
				+ "fm4 = aggregate { fm1 fm2 fm3 }\n" + "map fm4 with cst1\n"
				+ "c = convert fm4 into featureide");
		Variable v = _environment.getVariable("fm4");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v;
		FeatureVariable ft = fmv.root();

		assertNotNull(ft);
		assertTrue(ft.name().equals("fm4")); // root of the aggregated FM should
												// be the name of the variable

		assertNotNull(fmv.getFm());
		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertNotNull(constraints);
		assertEquals(constraints.size(), 4); // 1 + 1 + 2

	}

	@Test
	public void cleanup1() throws Exception {

		_shell.parse("fm1 = FM (A : B [C] [D] ; D : (E|F) ; C -> !E; )\n"
				+ "fm2 = FM (I : J [K] L ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n"
				+ "cst = constraints (J implies C ; )\n"
				+ "fm4 = aggregate fm* withMapping cst\n");

		FeatureModelVariable fm4 = getFMVariable("fm4");

		Set<String> fts = new HashSet<String>();
		StringTokenizer tok = new StringTokenizer(
				"A B C D E F I J K L M N O P fm4");
		while (tok.hasMoreTokens()) {
			fts.add(tok.nextToken());
		}

		assertSetEquals(setVariabletoString(fm4.features()), fts);

		_shell.parse("cleanup fm4\n");

		fm4 = getFMVariable("fm4");

		Set<String> fts2 = new HashSet<String>();
		StringTokenizer tok2 = new StringTokenizer(
				"A B C D F I J K L M N O P fm4");
		while (tok2.hasMoreTokens()) {
			fts2.add(tok2.nextToken());
		}

		assertSetEquals(setVariabletoString(fm4.features()), fts2);
		assertFalse("E no longer here!", setVariabletoString(fm4.features())
				.contains("E"));

		_shell.parse("op = operator fm4.F");

		VariabilityOperatorVariable op = getVOPVariable("op");
		assertEquals(op.getFek(), FeatureEdgeKind.MANDATORY);

		_shell.parse("rfm4 = root fm4\n"
				+ "assert (renameFeature rfm4 as \"MySPL\")");

		assertEquals(fm4.root().getFtName(), "MySPL");

		_shell.parse("featureIDEfm4 = convert fm4 into featureide\n");
		_shell.parse("println featureIDEfm4");

	}

	@Test
	public void aggregateUnvalid1() throws Exception {
		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C D; )\n"
				+ "fm2 = FM (E : (F|G); )"
				+ "fm3 = aggregate { fm1 fm2 } withMapping constraints (B -> F; C -> G;)\n"
				+ "assert (not isValid fm3)\n"
				+ "//assert (size configs fm3 eq 0)");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		assertEquals(0, fm3.counting(), 0);

		assertEquals(0, fm3.configs().size());

	}
	
	@Test
	public void testAggregate2() throws Exception {
		
		_shell.parse("fm1 = FM (A : B [C] [D] ; D : (E|F) ; C -> !E; )\n" + 
				"fm2 = FM (I : J [K] L ; )\n" + 
				"fm3 = FM (M : (N|O|P)+ ; )\n" + 
				"cst = constraints (J implies C ; )\n" );
		
		_shell.setVerbose(true);
		// sometimes it fails in standalone case
		_shell.parse("fm4 = aggregate fm* withMapping cst // equivalent to aggregate { fm1 fm2 fm3 }") ;
		
		FeatureModelVariable fmv4 = getFMVariable("fm4");
		System.err.println("fmv4=" + fmv4);
		
		
	}
	
	
	@Test
	public void aggregateModularWay() throws Exception {
		
		_shell.setVerbose(true);
		_shell.parse("vsar = FM (VSApplicationRequirement: Scene Sort ;\n" + 
				"                                     Scene:  LightingConditions;\n" + 
				"                                     Sort: (Person|Vehicle)+ ;\n" + 
				"                                     LightingConditions: (Indoors|Outdoors) [LightingNoise] (ArtificialLight|NaturalLight) TimeOfDay ;\n" + 
				"                                     TimeOfDay: (Night|Day) ;\n" + 
				"                                     LightingNoise : (Flashes|Shadows|HeadLight) ; )\n" + 
				"\n" + 
				"//serialize vsar into featureide\n" + 
				"\n" + 
				"setMandatory vsar.LightingNoise\n" + 
				"removeFeature vsar.Shadows\n" + 
				"setMandatory vsar.Outdoors\n" + 
				"removeFeature vsar.Indoors\n" + 
				"removeFeature vsar.Vehicle\n" + 
				"setMandatory vsar.Person\n" + 
				"\n" + 
				"\n" + 
				"cleanup vsar\n" + 
				"\n" + 
				"\n" + 
				"pcf = FM (VSPlatform: Segmentation Classification [LightingAnalyses];\n" + 
				"                 Segmentation:  KernelFunction;\n" + 
				"                 KernelFunction : AColor KModel ;\n" + 
				"                 AColor : (Color|Grey) ;\n" + 
				"                 KModel : (Edge|Region) ;\n" + 
				"                 Classification : [Contour] [Density] [Model] ;\n" + 
				"                 Density : HighDensity ;\n" + 
				"                 LightingAnalyses : [HeadLightDetect] ;\n" + 
				"                 Model : [Omega] Math ;\n" + 
				"                 Math : (ThreeD|Paral|Ellipse);\n" + 
				"                 Edge -> !Density ;)\n" + 
				"\n" + 
				"//serialize pcf into SPLOT\n" + 
				"\n" + 
				"\n" + 
				"prules = constraints ( LightingNoise -> (Edge and LightingAnalyses) ; (Flashes or HeadLight) -> Contour ; Person -> Omega ; )");
		
		
		// the supposed to be source of error
		_shell.parse("glFM = aggregate { vsar pcf } withMapping prules");
		
		FeatureModelVariable glFM = getFMVariable("glFM") ;
		System.err.println("glFM=" + glFM);
		System.err.println("#glFM=" + glFM.counting());
		glFM.cleanup() ; 
	
		System.err.println("\n\nglFM (cleaned up)=" + glFM);
		System.err.println("#glFM=" + glFM.counting());
	}
	

	// TODO: check when features are shared btw FMs (error handling)

}
