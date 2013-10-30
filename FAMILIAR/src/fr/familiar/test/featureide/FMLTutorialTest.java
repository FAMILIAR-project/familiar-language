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
package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.ComparisonOperator;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.OpSelection;

import com.google.common.collect.Sets;

import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.parser.VariableComparator;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class FMLTutorialTest extends FMLTest {

	/**
	 * Introduction to basic types
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay1() throws Exception {

		_shell.parse("gc1 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n"
				+ "gc2 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n"
				+ "b1 = gc1 eq gc2 // b1 is true\n"
				+ "b2 = gc1 == gc2 // b2 is false\n"
				+ "str = \"v10\" // str records the value \"v10\" \n"
				+ "fmSet = { gc1 gc2 }  // fmSet records a reference to a set");

		BooleanVariable b1 = getBooleanVariable("b1");
		assertTrue("b1 is true", b1.isTrue());
		BooleanVariable b2 = getBooleanVariable("b2");
		assertFalse("b2 is false", b2.isTrue());

		StringVariable str = getStringVariable("str");
		assertTrue("str records the value \"v10\"", str.getValue()
				.equals("v10"));

		SetVariable fmSet = getSetVariable("fmSet");
		assertTrue("Two elements in fmSet", fmSet.size() == 2);
		Set<Variable> svars = fmSet.getVars();
		assertTrue("Two elements in fmSet", svars.size() == 2);
		for (Variable v : svars) {
			assertTrue(v instanceof FeatureModelVariable);
			FeatureModelVariable fmv = (FeatureModelVariable) v;
			assertTrue(fmv == getFMVariable("gc1")
					|| fmv == getFMVariable("gc2"));
		}

	}

	/**
	 * Some accessors
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay2() throws Exception {

		_shell.parse("gc3 = FM ( GraphicCard: DirectX Speed Bus Multi; \n"
				+ "                                 DirectX: (v11 | v10dot1) ; Speed: n1000 ; Bus: n256 ; )\n"
				+ "f1 = parent v11 // f1 refers to feature named 'DirectX' in gc3\n"
				+ "f2 = root gc3 // f2 refers to feature named 'GraphicCard' in gc3\n"
				+ "s1 = name f2 // s1 is a string \"GraphicCard\"\n"
				+ "fs = children f1 // fs refers to the set of features named 'v11' and 'v10dot1' in gc3\n"
				+ "nfs = size fs // 2");

		FeatureVariable f1 = getFeatureVariable("f1");
		assertTrue("f1 refers to feature named 'DirectX' in gc3", f1
				.getFtName().equals("DirectX"));
		FeatureVariable f2 = getFeatureVariable("f2");
		assertTrue("f2 refers to feature named 'GraphicCard' in gc3", f2
				.getFtName().equals("GraphicCard"));
		assertEquals(f2.getFtName(), getFMVariable("gc3").root().getFtName());
		assertTrue("f2 is the root of gc3", f2.getFeatureModel().root().getFtName()
				.equals(getFMVariable("gc3").root().getFtName()));

		// assertTrue("f2 is the root of gc3", f2 ==
		// getFMVariable("gc3").root()); // difficult to provide a reference
		// (eventually TODO)

		assertTrue("feature model of f1 is gc3",
				f1.getFeatureModel() == getFMVariable("gc3"));
		assertTrue("feature model of f2 is gc3",
				f2.getFeatureModel() == getFMVariable("gc3"));

		StringVariable s1 = getStringVariable("s1");
		assertEquals(s1.getVal(), "GraphicCard");
		assertTrue("s1 is actually the name of feature f2",
				s1.getVal().equals(f2.getFtName()));

		SetVariable fs = getSetVariable("fs");
		assertTrue(
				"fs refers to the set of features named 'v11' and 'v10dot1' in gc3",
				fs.size() == 2);

		assertNotNull("v11 exists", _environment.getVariable("v11"));
		assertNotNull("gc3.v11 exists", _environment.getVariable("gc3.v11"));

		for (Variable v : fs.getVars()) {
			assertTrue(v instanceof FeatureVariable);
			FeatureVariable fv = (FeatureVariable) v;
			assertTrue(fv.getFtName().equals(
					getFeatureVariable("v11").getFtName())
					|| fv.getFtName().equals(
							getFeatureVariable("v10dot1").getFtName()));
		}

		assertTrue("size fs // 2", getIntegerVariable("nfs").getV() == 2);

	}

	/**
	 * Basic operations (side-effects, aligning terms)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay3() throws Exception {

		_shell.parse("gc3 = FM ( GraphicCard: DirectX Speed Bus Multi; \n"
				+ "DirectX: (v11 | v10dot1) ; Speed: n1000 ; Bus: n256 ; ) "
				+ "						oldFeature = parent n256 // oldFeature refers to 'Bus' feature of gc3\n"
				+ "	            		newName = strConcat \"Memory\" name oldFeature\n"
				+ "	            		b1 = renameFeature oldFeature as newName // aligning terms of gc3 with gc1 and gc2\n"
				+ "	                    assert (b1 eq true) // assert (b1) is equivalent\n"
				+ "	                    assert (b1)" + // added
				"	");

		String oldFeatureStr = getFeatureVariable("oldFeature").getFtName();
		assertTrue("oldFeature refers to 'Bus' feature of gc3",
				oldFeatureStr.equals("Bus"));
		assertEquals(getStringVariable("newName").getVal(),
				"Memory".concat(oldFeatureStr));
		FeatureModelVariable gc3 = getFMVariable("gc3");
		SetVariable sfeatures = gc3.features();

		assertFalse("gc3 has been modified (oldFeature is no longer present)",
				setVariabletoString(sfeatures).contains("Bus"));

		assertTrue(
				"gc3 has been modified (newName)",
				setVariabletoString(sfeatures).contains(
						getStringVariable("newName").getVal()));
		assertTrue("gc3 has been modified (newName)",
				setVariabletoString(sfeatures).contains("MemoryBus"));

		FeatureVariable newFT = getFeatureVariable("gc3.MemoryBus");
		assertEquals("MemoryBus", newFT.getFtName());

	}

	/**
	 * Some operations on configurations
	 * FIXME SAT4J versions
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testLdPDay4() throws Exception {

		_shell.parse("gc1 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n");

		FeatureModelVariable gc1 = getFMVariable("gc1");
		System.err.println("" + gc1.convert(FMFormat.FSPLOT));

		// note this is the very first example of mixin internal DSLs with
		// external DSLs. (alternately)
		
		ConfigurationVariable conf0 = ConfigurationVariableFactory.INSTANCE.mkSPLOT(gc1, "conf0");
		assertTrue(conf0.applySelection("Multi", OpSelection.SELECT));
		assertTrue(conf0.applySelection("Multi", OpSelection.DESELECT));
		assertTrue(conf0.applySelection("Multi", OpSelection.UNSELECT)); 
		
		System.err.println("conf0=" + conf0.getSpecificValue());
		
		assertFalse("Multi is neither selected... ", conf0.getSelected()
				.contains("Multi"));
		assertFalse("Multi is ... nor deselected", conf0.getDeselected()
				.contains("Multi"));
		assertTrue("Multi is neither selected nor deselected, so unselected",
				conf0.getUnselected().contains("Multi"));

		
		_shell.parse("conf1 = configuration gc1 // create a configuration of gc1\n"
				+ "b1 = select Multi in conf1 // feature Multi of gc1 is selected\n"
				+ "b2 = deselect Multi in conf1 // override the previous selection\n"
				+ "b3 = unselect Multi in conf1 // now, Multi is neither selected nor deselected");


		ConfigurationVariable conf1 = getConfigurationVariable("conf1");

		assertTrue("conf1 is actually a configuration of gc1",
				conf1.getFmv() == gc1);

		BooleanVariable b1 = getBooleanVariable("b1");
		BooleanVariable b2 = getBooleanVariable("b2");
		BooleanVariable b3 = getBooleanVariable("b3");
		

		assertTrue("Multi has been selected", b1.isTrue());
		assertTrue("Multi has been deselected", b2.isTrue());
		assertTrue("Multi has been unselected", b3.isTrue());
		
		
		assertFalse("Multi is neither selected... ", conf1.getSelected()
				.contains("Multi"));
		assertFalse("Multi is ... nor deselected", conf1.getDeselected()
				.contains("Multi"));
		assertTrue("Multi is neither selected nor deselected, so unselected",
				conf1.getUnselected().contains("Multi"));
				

	}

	/**
	 * compare, copy, isValid, counting
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay5() throws Exception {

		_shell.parse("gc1 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n"
				+ "gc2 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n");

		_shell.parse("conf1 = configuration gc1 // create a configuration of gc1\n"
				+ "b1 = select Multi in conf1 // feature Multi of gc1 is selected\n"
				+ "b2 = deselect Multi in conf1 // override the previous selection\n"
				+ "b3 = unselect Multi in conf1 // now, Multi is neither selected nor deselected");

		_shell.parse("conf2 = copy conf1 \n//assert (conf2 eq conf1) and assert (not (conf1 == conf2))\n");

		ConfigurationVariable conf1 = getConfigurationVariable("conf1");
		ConfigurationVariable conf2 = getConfigurationVariable("conf2");
		assertTrue("(conf1 eq conf2)", new VariableComparator(conf1, conf2,
				ComparisonOperator.EQUAL).eval());
		assertFalse("not (conf1 == conf2)", new VariableComparator(conf1,
				conf2, ComparisonOperator.REF_EQUAL).eval());

		_shell.parse("nb = counting gc1 // number of valid configurations\n"
				+ "b1 = isValid conf1\n"
				+ "b2 = (selectedF conf1) eq (selectedF conf2) // true\n"
				+ "cmp1 = compare gc1 gc2 // refactoring");

		assertTrue("Same sets of selected features", getBooleanVariable("b2")
				.isTrue());
		assertTrue("b1 is true", getBooleanVariable("b1").isTrue());
		assertEquals("REFACTORING", getStringVariable("cmp1").getVal());

	}

	/**
	 * Insert operation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay6() throws Exception {

		_shell.parse("base = FM (Keyboard: [ControlCD] Wireless Wiring [International] ; Wiring: (USB|PS2); )\n"
				+ "aspect1 = FM ( Lang : [US] European [Chinese]; )\n"
				+ "insert aspect1 into International with mand  // 'base' is modified\n"
				+ "removeVariable aspect1 // no longer need 'aspect1' variable\n"
				+ "fInt = parent Lang // feature International is now in 'base' FM\n"
				+ "assert (name fInt eq \"International\") // check it\n"
				+ "// check also that feature Lang in 'base' FM has still three child features\n"
				+ "assert (size children Lang eq 3) ");

		try {
			Variable aspect1 = getFMVariable("aspect1");
		} catch (Exception e) {
			assertNotNull(e);
		}

		assertTrue(
				"fInt = parent Lang // feature International is now in 'base' FM\n",
				getFeatureVariable("fInt").getFtName().equals("International"));

		_shell.parse("clangs = children Lang\n");

		SetVariable clangs = getSetVariable("clangs");
		assertEquals(3, clangs.size());
		Set<String> expected = new HashSet<String>();
		expected.add("US");
		expected.add("European");
		expected.add("Chinese");

		Set<String> clangsFTs = new HashSet<String>();
		for (Variable v : clangs.getVars()) {
			assertTrue(v instanceof FeatureVariable);
			FeatureVariable ft = (FeatureVariable) v;
			clangsFTs.add(ft.getFtName());
		}

		assertEquals(expected, clangsFTs);

	}

	@Test
	public void testLdPDay7() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("gc4 = FM ( GraphicCard: DirectX Speed Bus [Multi]; \n"
				+ "                            DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; Bus: n128; )\n"
				+ "gc5 = FM ( GraphicCard: DirectX Speed Bus [Vertex]; \n"
				+ "                            DirectX: (v10dot1 | v10 | v9) ; Speed: n800 ; Bus: (n64 | n128); )\n");

		_shell.parse("gc_inter = merge intersection { gc4 gc5 } \n"
				+ "gc_inter_expected = FM ( GraphicCard: DirectX Speed Bus ; \n"
				+ "                                   DirectX: (v10dot1 | v10) ; Speed: n800 ; Bus: n128 ; )\n"
		// +
		// "assert (gc_inter eq gc_inter_expected) // same set of configurations and hierarchy"
		);

		FeatureModelVariable gc_inter = getFMVariable("gc_inter");
		FeatureModelVariable gc_inter_expected = getFMVariable("gc_inter_expected");

		assertEquals(gc_inter_expected.counting(), gc_inter.counting(), 0);
		assertFMsEqual(gc_inter_expected.getFm(), gc_inter.getFm());

		System.err.println("gc_inter=" + gc_inter.getFm().toString());
		System.err.println("gc_inter to FeatureIDE=" + new FMLtoFeatureIDE(gc_inter).convert());

		_shell.parse("gc_sunion = merge sunion { gc4 gc5 }\n"
				+ "n_sunion = counting gc_sunion // number of valid configurations\n"
				+ ""
		// "n_expected = (counting gc4 + counting gc5) - counting gc_inter \n" +
		// "assert (n_sunion eq n_expected)"
		);

		FeatureModelVariable gc4 = getFMVariable("gc4");
		FeatureModelVariable gc5 = getFMVariable("gc5");
		FeatureModelVariable gc_sunion = getFMVariable("gc_sunion");
		

		double nexpected = (gc4.counting() + gc5.counting())
				- gc_inter_expected.counting();

		assertEquals(nexpected, gc_sunion.counting(), 0);

	}

	/**
	 * Aggregate operation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay8() throws Exception {
		_shell.parse("fm1 = FM ( A : B H [C] ; H: (E|F) ; )\n"
				+ "fm2 = FM ( R : S T [U] ; T : (W|Y)+ ; )\n"
				+ "fm3 = aggregate { fm1 fm2 } \n"
				+ "cst1 = constraints (C -> W; U -> W; U -> !(E | C) ; )\n"
				+ "map fm3 with cst1\n"
				+ "fm4  = aggregate { fm1 fm2 } withMapping cst1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		Set<String> ftfm3 = new HashSet<String>();
		ftfm3.add("fm3");
		Set<String> fts3Expected = Sets.union(Sets.union(
				setVariabletoString(fm1.features()),
				setVariabletoString(fm2.features())), ftfm3);

		assertEquals(fts3Expected, setVariabletoString(fm3.features()));

		FeatureVariable fm3root = fm3.root();
		assertEquals("fm3", fm3root.getFtName());

		FeatureModelVariable fm4 = getFMVariable("fm4");

		Set<String> ftfm4 = new HashSet<String>();
		ftfm4.add("fm4");
		Set<String> fts4Expected = Sets.union(Sets.union(
				setVariabletoString(fm1.features()),
				setVariabletoString(fm2.features())), ftfm4);
		assertTrue("fm4 features contain fm1 and fm2 features",
				fts4Expected.equals(setVariabletoString(fm4.features())));

		_shell.parse("unmap fm4");

		assertEquals(0, getFMVariable("fm4").getFm().getConstraints().size());

	}

	/**
	 * Namespace management
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLdPDay9() throws Exception {

		_shell.parse("gc1 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n"
				+ "gc2 = FM ( GraphicCard: DirectX Speed MemoryBus [Multi]; \n"
				+ "                       DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; MemoryBus: n128 ; )\n");

		_shell.parse("gc3 = FM ( GraphicCard: DirectX Speed Bus Multi; \n"
				+ "                                 DirectX: (v11 | v10dot1) ; Speed: n1000 ; Bus: n256 ; )\n");

		_shell.parse("sdirect = children gc1.DirectX // explicit notation needed: DirectX exists also in gc2 and gc3\n"
				+ "GCgc2 = gc2.GraphicCard // explicit notation needed: GraphicCard exists also in gc1 and gc3\n"
				+ "pv11 = parent v11 // non ambiguous: equivalent to gc3.v11 ");

		SetVariable sdirect = getSetVariable("sdirect");
		assertEquals(2, sdirect.size());

		FeatureVariable GCgc2 = getFeatureVariable("GCgc2");
		assertTrue("GCgc2 actually belongs to gc2",
				GCgc2.getFeatureModel() == getFMVariable("gc2"));

		FeatureVariable pv11 = getFeatureVariable("pv11");
		assertTrue("pv11 actually belongs to gc3",
				pv11.getFeatureModel() == getFMVariable("gc3"));

	}

	@Test
	public void testLdPDay10() throws Exception {
		String replaceFMbyFML = "// replaceFMbyFM.fml : a parametrized script that replaces a subtree of target FM\n"
				+ "parameter target : FeatureModel\n"
				+ "parameter fmToInsert : FeatureModel // type specification is optional\n"
				+ "\n"
				+ "ft = root fmToInsert\n"
				+ "f = name ft\n"
				+ "parentF = parent target.f // save the parent of feature 'f'\n"
				+ "operatorF = operator target.f // save the operator of the feature 'f' \n"
				+ "assert (removeFeature target.f) // the feature must exist\n"
				+ "insert fmToInsert into parentF with operatorF // entity referred by target is modified\n"
				+
				// TODO
				"//hide ft f parentF operatorF // no more need temporary variables";

		// serialize the script
		File file = new File("examples/testing/script/replaceFMbyFM0.fml");

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(new StringReader(
					replaceFMbyFML));
			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str + "\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {

		}

		_shell.setVerbose(true);
		// call it

		_shell.parse("originalLaptop = FM ( Laptop: Motherboard Processor [Wifi] [GraphicCard] ; \n"
				+ "						GraphicCard: Bus [Multi] ; )\n"
				+ "newGC = FM (GraphicCard: Bus [Vertex] ; Bus: (n128 | n256);) // the new GC we want \n"
				+ "run \"replaceFMbyFM0.fml\" { originalLaptop newGC } // the two arguments are originalLaptop and newGC\n"
				+ "assert (isExisting originalLaptop.Vertex) // 'Vertex' is now in originalLaptop");

		FeatureModelVariable originalLaptop = getFMVariable("originalLaptop");
		FeatureModelVariable newGC = getFMVariable("newGC");

		Set<String> newGCFTs = setVariabletoString(newGC.features());
		assertTrue(
				"originalLaptop features contain newGC features",
				setVariabletoString(originalLaptop.features()).containsAll(
						newGCFTs));

		// System.err.println("originalLaptop=" +
		// originalLaptop.getSpecificValue());
		System.err.println("originalLaptop="
				+ originalLaptop.getFm().getDiagram().toString());

		assertTrue("'Vertex' is now in originalLaptop",
				getFeatureVariable("originalLaptop.Vertex") != null);
		assertTrue("'n128' is now in originalLaptop",
				getFeatureVariable("originalLaptop.n128") != null);
		assertTrue("'n256' is now in originalLaptop",
				getFeatureVariable("originalLaptop.n256") != null);

		try {
			_environment.getVariable("originalLaptop.Multi");
		} catch (Exception e) {
			assertTrue("'Multi' is no longer in originalLaptop", e != null);
		}

	}

}
