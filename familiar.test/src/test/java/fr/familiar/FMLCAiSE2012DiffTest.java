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

package fr.familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.sat4j.specs.TimeoutException;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.experimental.FGroup;
import fr.familiar.fm.FMLSynthetizer;
import fr.familiar.fm.FMLUtils;
import fr.familiar.fm.SimpleExtendedEdge;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.operations.CliquesComputation;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.operations.Mode;
import fr.familiar.variable.CliquesOperationFactory;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;

/**
 * @author macher
 *
 */
public class FMLCAiSE2012DiffTest extends FMLTest {
	
	
	private String _fm1 = "applet : mustOverride [destroy] [stop] ; " +
	"mustOverride: (paint|start|init)+ ; " +
	"destroy -> init ; stop -> init ; " +
	"" ; 
	
	
	
	private String _fm2 = "applet : [mustOverride] [init] ; " +
	"init : [destroy] [stop] ; mustOverride: (paint|start)+ ; " +
	"!mustOverride -> init ; " +
	"" +
	"" ; 
	
	
	
	/**
	 * FIXME the test does not pass if executed with others (but works individually)
	 * 
	 */
	
	/**
	 * MODELS'11: Vision Paper: Make a Difference! (Semantically) Uli Fahrenberg, Axel Legay, and Andrzej Wasowski
	 * The two example feature models and an over-approximation of their "quotient"
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testWasowski() throws Exception {
		
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		
		
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		
		
		
		_shell.parse("fm21Bis = " + "merge diff { fm1 fm2 } ");
		FeatureModelVariable fmv21Bis = getFMVariable("fm21Bis");
		
		System.err.println("fmv21Bis=" + fmv21Bis); 
		double n21D = fmv21Bis.counting() ; 
		System.err.println("fmv21Bis (counting)=" + n21D); 
		
		assertEquals(4, n21D, 0);
				
				
		
		System.err.println("fmv1=" + fmv1);
		double n1 = fmv1.counting() ; 
		System.err.println("fmv1 (counting)=" + n1);
		Set<Set<String>> s1 = FMLUtils.setConfigToSetStr(fmv1.configs()) ; 
		System.err.println("[[fmv1]]=" + s1);
		System.err.println();
		
		
		System.err.println("fmv2=" + fmv2);
		double n2 = fmv2.counting() ; 
		System.err.println("fmv2 (counting)=" + n2);
		Set<Set<String>> s2 = FMLUtils.setConfigToSetStr(fmv2.configs()) ; 
		System.err.println("[[fmv2]]=" + s2);
		System.err.println();
	
		_shell.setVerbose(true);
		FeatureModelVariable fmv12U = fmv1.merge(fmv2, Mode.StrictUnion);
		
		System.err.println("fmv12U=" + fmv12U);
		double n12U = fmv12U.counting() ; 
		System.err.println("fmv12U (counting)=" + n12U);
		System.err.println("[[fmv12U]]=" + FMLUtils.setConfigToSetStr(fmv12U.configs()));
		System.err.println();
		
		FeatureModelVariable fmv12I = fmv1.merge(fmv2, Mode.Intersection);
		System.err.println("fmv12I=" + fmv12I);
		double n12I = fmv12I.counting();
		System.err.println("fmv12I (counting)=" + n12I);
		System.err.println("[[fmv12I]]=" + FMLUtils.setConfigToSetStr(fmv12I.configs()));
		System.err.println();
		
		
		double nUnionExpected = n1 + n2 - n12I ; 
		assertEquals(nUnionExpected, n12U, 0);
		
		
		FeatureModelVariable fmv12 = fmv1.mergeDiff(fmv2);
		System.err.println("fmv12=" + fmv12);
		double n12D = fmv12.counting() ; 
		System.err.println("fmv12 (counting)=" + n12D);
		System.err.println();
		
		double n12Total = n12D + n21D + n12I ; // union (f, g) = diff (f, g) + diff (g, f) + intersection (f, g)
		assertEquals(n12Total, n12U, 0);
		
		
		FeatureModelVariable fmv12H = new FMLSynthetizer(_builder).synthetize(FMLMergerBDD.diff(fmv1.getFormula(), fmv2.getFormula(), _builder), fmv2.getFm()) ; // VI
		System.err.println("fmv12H (hierarchy) =" + fmv12H);
		System.err.println("fmv12H (counting)=" + fmv12H.counting());
		System.err.println();
		
		_shell.setVerbose(true);
		FeatureModelVariable fmv21 = fmv2.mergeDiff(fmv1);
		_shell.setVerbose(false);
		System.err.println("fmv21=" + fmv21);
		System.err.println("fmv21 (counting)=" + fmv21.counting());
		System.err.println("[[fmv21]]=" + FMLUtils.setConfigToSetStr(fmv21.configs()));
		System.err.println();
		
		
		System.err.println("#fm1=" + fmv1.counting());
		System.err.println("#fm2=" + fmv2.counting());
		
		
		System.err.println("" + _shell.getHistory()); 
		
	}

	
	
	
	@Test
	public void testWasowski2() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		System.err.println("\n******** diff report: fm1 vs fm2 \n");
		
		
		
		analyzeAll(fmv1, fmv2, _builder);

		
		/*
		System.err.println("\n******** diff report: fm2 vs fm1 \n");
		
		analyzeAll(fmv2, fmv1, _builder);
		System.err.println("[[fm2 \\ fm1]]=" + 
				FMLSlicerUtilityTest.prettyConfigs(fmv1.mergeDiff(fmv2))); */
		
	
	}
	
	/**
	 * the test does not pass if executed with others (but works individually)
	 * 
	 */
	
	/**
	 * 
	 * Aligning feature models (2)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testWasowski5() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		FeatureModelVariable fmv2Bis = new FMLSynthetizer(_builder).synthetize(fmv2.getFormula(), fmv1.getHierarchy()); 
		System.err.println("#fmv2Bis=" + fmv2Bis.counting()); 
		System.err.println("fmv2Bis=" + fmv2Bis); 
		assertTrue(fmv2.counting() > fmv2Bis.counting()); // what does it mean? 
		
		
		
		FeatureModelVariable myFmv2SemiAutomated = FM ("myFm1", "applet : [mustOverride] [init] ; init : [destroy] [stop] ; " +
				"mustOverride: (paint|start)+ [initP] ; " +
				"destroy -> init ; stop -> init ; init <-> initP ; " +
				"" ) ; 
		
		myFmv2SemiAutomated.setBuilder(_builder) ;
		FeatureModelVariable myFmv2SemiAutomatedSliced = myFmv2SemiAutomated.slice(SliceMode.EXCLUDING, "init");

		Set<String> ft = myFmv2SemiAutomated.features().names() ;
		ft.remove("init");
		
		Set<Set<String>> sliceConfigs = FMLUtils.setConfigToSetStr(myFmv2SemiAutomated.configs()) ;
		Set<Set<String>> s3 = new HashSet<Set<String>>();
		for (Set<String> conf : sliceConfigs) {
			s3.add(Sets.intersection(conf, ft)); 
		}
		
		int n3 = s3.size();
		System.err.println("#s3=" + n3);
		double n2ASliced = myFmv2SemiAutomatedSliced.counting() ; 
		System.err.println("#myFmv1SemiAutomatedSliced=" + n2ASliced); 
		
		assertEquals(n3, n2ASliced, 0);
		
		System.err.println("myFmv1SemiAutomatedSliced=" + myFmv2SemiAutomatedSliced); 
		
		myFmv2SemiAutomatedSliced.renameFeature("initP", "init");
		
		System.err.println("#not in fm2:" + fmv2.mergeDiff(myFmv2SemiAutomatedSliced).counting());
		System.err.println("not in fm2:" + FMLUtils.setConfigToSetStr(fmv2.mergeDiff(myFmv2SemiAutomatedSliced).configs()));
		
		System.err.println("" + FMLUtils.setConfigToSetStr(myFmv2SemiAutomatedSliced.mergeDiff(fmv2).configs()));
		
		System.err.println("#fm2=" + fmv2.counting());
		System.err.println("fm2=" + fmv2);
		System.err.println("[[fm2]]=" + FMLUtils.setConfigToSetStr(fmv2.configs()));
		
	}
	
	/**
	 * the test does not pass if executed with others (but works individually)
	 */
	
	/**
	 * 
	 * Aligning feature models
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testWasowski4() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		FeatureModelVariable fmv1Bis = new FMLSynthetizer(_builder).synthetize(fmv1.getFormula(), fmv2.getHierarchy()); 
		System.err.println("#fmv1Bis=" + fmv1Bis.counting()); 
		System.err.println("fmv1Bis=" + fmv1Bis); 
		assertEquals(fmv1.counting(), fmv1Bis.counting(), 0); 
		
	
		FeatureModelVariable myFmv1 = FM ("myFm1", "applet : mustOverride [init] ; init : [destroy] [stop] ; " +
											"mustOverride: (paint|start)+ ; " +
											"destroy -> init ; stop -> init ; " +
											"" ) ; 
	
		System.err.println("#myFmv1=" + myFmv1.counting()); 
		
		
		FeatureModelVariable myFmv1Bis = FM ("myFm1Bis", "applet : mustOverride [init] ; init : [destroy] [stop] ; " +
				"mustOverride: (paint|start)+ ; " +
				//"destroy -> init ; stop -> init ; " + // not usefull!
				"" ) ; 

		System.err.println("#myFmv1Bis=" + myFmv1Bis.counting()); 
		
		
		FeatureModelVariable myFmv1SemiAutomated = FM ("myFm1", "applet : mustOverride [initP] ; initP : [destroy] [stop] ; " +
				"mustOverride: (paint|start|init)+ ; " +
				"destroy -> init ; stop -> init ; init <-> initP ; " +
				"" ) ; 
	
		myFmv1SemiAutomated.setBuilder(_builder); 
		FeatureModelVariable myFmv1SemiAutomatedSliced = myFmv1SemiAutomated.slice(SliceMode.EXCLUDING, "init");

		Set<String> ft = myFmv1SemiAutomated.features().names() ;
		ft.remove("init");
		
		Set<Set<String>> sliceConfigs = FMLUtils.setConfigToSetStr(myFmv1SemiAutomated.configs()) ;
		Set<Set<String>> s3 = new HashSet<Set<String>>();
		for (Set<String> conf : sliceConfigs) {
			s3.add(Sets.intersection(conf, ft)); 
		}
		
		int n3 = s3.size();
		System.err.println("#s3=" + n3);
		double n1ASliced = myFmv1SemiAutomatedSliced.counting() ; 
		System.err.println("#myFmv1SemiAutomatedSliced=" + n1ASliced); 
		
		assertEquals(n3, n1ASliced, 0);
		
		System.err.println("myFmv1SemiAutomatedSliced=" + myFmv1SemiAutomatedSliced); 
		
		myFmv1SemiAutomatedSliced.renameFeature("initP", "init"); // FIXME
		System.err.println("domain: " + myFmv1SemiAutomatedSliced.getFormula().getDomain());
		_shell.setVerbose(true);
		System.err.println("" + fmv1.mergeDiff(myFmv1SemiAutomatedSliced).counting());
		System.err.println("" + FMLUtils.setConfigToSetStr(myFmv1SemiAutomatedSliced.mergeDiff(fmv1).configs()));
		
	}
	
	/**
	 * Quotient-based method
	 * @throws Exception
	 */
	@Test
	public void testWasowski3() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		System.err.println("q12:" + fmv1.quotient(fmv2));
		System.err.println("q21:" + fmv2.quotient(fmv1));
		
	
	}
	
	@Test
	public void testSlicingAndDifferencing() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		String[] slicingCriterion = new String[] { "applet", "paint", "start", "stop", "init" }; //"mustOverride"}; //  
		
		
		fmv1.setBuilder(_builder); 
		FeatureModelVariable fmv1S = fmv1.slice(SliceMode.INCLUDING, slicingCriterion);
		fmv2.setBuilder(_builder); 
		FeatureModelVariable fmv2S = fmv2.slice(SliceMode.INCLUDING, slicingCriterion);
		
		System.err.println("fmv1S=" + fmv1S);
		System.err.println("fmv2S=" + fmv2S);
		
		System.err.println("#fmv1S=" + fmv1S.counting());
		System.err.println("#fmv2S=" + fmv2S.counting());
		
		analyzeAll(fmv1S, fmv2S, _builder);
		
		
		
	}
	
	
	/**
	 * Quotient-based method
	 * @throws Exception
	 */
	@Test
	public void testWasowskiToGeneralization() throws Exception {
		
	
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1);
		FeatureModelVariable fmv2 = FM ("fm2", _fm2);
		
		
		FeatureModelVariable fmvI12 = fmv2.merge(fmv1, Mode.Intersection);
		System.err.println("fmvI12=" + fmvI12);
		
		assertEquals(Comparison.ARBITRARY, fmv1.compare(fmv2));
		
		FeatureModelVariable fmvDiff12 = fmv1.mergeDiff(fmv2);		
		System.err.println("#fmvDiff12=" + fmvDiff12.counting());
		System.err.println("fmvDiff12=" + fmvDiff12); // applet: init mustOverride [stop] [destroy] ;
		
		
		
		
		
		FeatureModelVariable fmv2Complemented = fmvDiff12.merge(fmv2, Mode.StrictUnion); // fmv2.merge(fmvDiff12, Mode.StrictUnion);
		
		//System.err.println("#fmv2=" + fmv2.counting());
		System.err.println("#fmv2Complemented=" + fmv2Complemented.counting());
		System.err.println("fmv2Complemented=" + fmv2Complemented);
		
		String complemented2 = "applet: (init|mustOverride)+ ;" +  
		"init: [stop] [destroy] ; " + 
		"mustOverride: [start] [paint] ; " + 
		"destroy | stop | start | init | paint | !mustOverride ; ";
		//"destroy | stop | start | init | paint | !mustOverride;" ; 
		
		FeatureModelVariable fmvComplemented2 = FM ("complemented2", complemented2) ;
		System.err.println("\n" + fmvComplemented2.counting (CountingStrategy.BDD_FML) + "\n");

		assertEquals(Comparison.REFACTORING, fmv2Complemented.compare(fmvComplemented2));
		
		assertEquals(Comparison.GENERALIZATION, fmv2Complemented.compareBDD(fmv2, _builder));
		
		
		FeatureModelVariable fmvDiff21 = fmv2.mergeDiff(fmv1);		
		System.err.println("#fmvDiff21=" + fmvDiff21.counting());
		System.err.println("fmvDiff21=" + fmvDiff21);
		
		FeatureModelVariable fmv2BisComplemented = fmv1.merge(fmvDiff21, Mode.StrictUnion);
		
		System.err.println("#fmv2=" + fmv2.counting());
		System.err.println("#fmv2Complemented=" + fmv2BisComplemented.counting());
		System.err.println("fmv2Complemented=" + fmv2BisComplemented);
		assertEquals(Comparison.GENERALIZATION, fmv2BisComplemented.compareBDD(fmv1, _builder));
	
	}
	
	
	@Ignore
	@Test
	public void testRE07() throws Exception {


			// _shell.setVerbose(true);

			/*
			 * Metzger et al. 2007, RE'07 Disambiguating the ..... Figure 3, Section
			 * 6
			 */

			// input FMs + constraints

			String softwareVariability = "fmSoftware = FM (PBX : F1 [F2] [F3] F4 F5; "
					+ "F1: F6 F7; "
					+ "									F2 : [F9] [F10] F8; "
					+ "									F3 : [F11] [F12];"
					+ "									F4 : F13 F14 F15;"
					+ "									F15 : F19 F20 [F21] ;"
					+ "									F5 : F16 F17 F18;"
					+ "									F18 : F22 [F23] [F24];"
					+ "									F21 requires F2; F24 requires F3 ; )";
			_shell.parse(softwareVariability + "\n");
			FeatureModelVariable fmSoftware = getFMVariable("fmSoftware");

			String PlVariability = "fmPL = FM (PBXPL : VP1 [VP2] [VP3]; "
					+ "VP1: (V11|V12|V13); " + "VP2: (V22|V21); "
					+ "VP3: V31 [V32]; "
					+ "VP2 -> !V11 ; VP3 -> !V11 ; VP3 -> !V12 ; )";
			_shell.parse(PlVariability + "\n");
			FeatureModelVariable fmPL = getFMVariable("fmPL");

			System.err.println("size fmPL.*=" + fmPL.features().size());
			System.err.println("size fmSoftware.*=" + fmSoftware.features().size());

			System.err.println("[[F]]");
			System.err.println("fmSoftware=" + fmSoftware);
			System.err.println("#fmSoftware=" + fmSoftware.counting());
			System.err.println("common(fmSoftware)=" + fmSoftware.cores());
			System.err.println("dead(fmSoftware)=" + fmSoftware.deads());

			System.err.println("==========");

			System.err.println("[[O]]");
			System.err.println("fmPL=" + fmPL);
			System.err.println("#fmPL=" + fmPL.counting());
			System.err.println("common(fmPL)=" + fmPL.cores());
			System.err.println("dead(fmPL)=" + fmPL.deads());

			System.err.println("==========");
			String xLink = "xLink = constraints (F9 <-> V22; F10 <-> V22; F8 <-> (V21|V11); F11 <-> V31; F12 <-> V32; F21 <-> (V12|V13); F23 <-> V13; F24 <-> V13; )";
			_shell.parse(xLink + "\n");

			_shell.parse("gFM = aggregate { fmPL fmSoftware } withMapping xLink\n");

			FeatureModelVariable gFM = getFMVariable("gFM");
			System.err.println("gFM=" + gFM);
			System.err.println("#gFM=" + gFM.counting());
			System.err.println("common(gFM)=" + gFM.cores());
			System.err.println("dead(gFM)=" + gFM.deads());
			System.err.println("CTCR gfm=" + gFM.CTCR());

			System.err.println("==========");
			
			gFM.setBuilder(_builder);
			FeatureModelVariable fmPLp = gFM.slice(SliceMode.INCLUDING, fmPL.features().names());
			System.err.println("fmPLp=" + fmPLp);
			System.err.println("#fmPLp=" + fmPLp.counting());
			
			FeatureModelVariable fmSoftwarep = gFM.slice(SliceMode.INCLUDING, fmSoftware.features().names());
			System.err.println("fmSoftwarep=" + fmSoftwarep);
			System.err.println("#fmSoftwarep=" + fmSoftwarep.counting());
			System.err.println();
			System.err.println("==========");
			System.err.println("==========");
			System.err.println();
			
			analyzeAll(fmSoftware, fmSoftwarep, _builder);
			
			analyzeAll(fmPL, fmPLp, _builder);
			
			
		
	}
	
	




	public static void analyzeAll(FeatureModelVariable fmv1,	FeatureModelVariable fmv2, BDDBuilder<String> builder) throws TimeoutException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		System.err.println("BIG / BEG");
		analyzeImplicationExclusion(fmv1, fmv2, builder);
		
		System.err.println();
		
		System.err.println("Syntactic diff hierarchy");
		analyzeDiffHierarchy(fmv1, fmv2, builder);
		System.err.println();
		
		System.err.println("Diff AS");
		analyzeDiffAtomicSets(fmv1, fmv2, builder);
		System.err.println();
		
		System.err.println("syntactic GROUPS"); 
		Set<FGroup> csGroups1 = fmv1.getGroups();
		Set<FGroup> csGroups2 = fmv2.getGroups();
		for (FGroup fGroup1 : csGroups1) {
			boolean found = false ; 
			for (FGroup fGroup2 : csGroups2) {		
				if (fGroup1.equals(fGroup2)) {
					found = true ;
					break ; 
				}
			}
			if (!found) {
				System.err.println("fGroup=" + fGroup1);
			}
		}
		
		
		System.err.println();
		
		System.err.println("CLIQUES");
		analyzeCliques(fmv1, fmv2);
		
		System.err.println();
		
		System.err.println("QUOTIENT"); 
		System.err.println(fmv1.quotient(fmv2));
		
		System.err.println("GROUPS\n"); 
		Set<FGroup> cGroups1 = fmv1.computeGroups();
		Set<FGroup> cGroups2 = fmv2.computeGroups();

		for (FGroup fGroup1 : cGroups1) {
			boolean found = false ; 
			for (FGroup fGroup2 : cGroups2) {		
				if (fGroup1.equals(fGroup2)) {
					found = true ;
					break ; 
				}
			}
			if (!found) {
				System.err.println("fGroup (computed)=" + fGroup1);
			}
		}
		System.err.println();
		System.err.println("DIFF=" + fmv1.mergeDiff(fmv2)); 
		
		System.err.println("experimental: cliques (on the diff)=" + fmv1.mergeDiff(fmv2).cliques().names()); 
		
		
	}
	
	private static void analyzeDiffAtomicSets(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2, BDDBuilder<String> builder) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		SetVariable as1 = fmv1.atomicSet() ;
		SetVariable as2 = fmv2.atomicSet() ;
		
		for (Variable v1 : as1.getVars()) {
			SetVariable at1 = ((SetVariable) v1);
			Set<Variable> vars1 = at1.getVars() ;
			Set<String> strs1 = new HashSet<String>() ; 
			for (Variable v11 : vars1) {
				strs1.add(v11.getValue()); 
			}
			boolean found = false;
			for (Variable v2 : as2.getVars()) {
				SetVariable at2 = ((SetVariable) v2);
				Set<Variable> vars2 = at2.getVars() ;
				Set<String> strs2 = new HashSet<String>() ; 
				for (Variable v22 : vars2) {
					strs2.add(v22.getValue()); 
				}
				if (strs1.equals(strs2)) {
					found = true ; 
					break ; 
				}
			}
			if (!found) {
				System.err.println("at=" + at1.names());
			}
		} 
		
	}




	private static void analyzeDiffHierarchy(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2, BDDBuilder<String> builder) {
		
		ImplicationGraph<String> impl1 = fmv1.getImplicationGraphFromPCEdges(builder);
		ImplicationGraph<String> impl2 = fmv2.getImplicationGraphFromPCEdges(builder);
		
		Set<SimpleExtendedEdge<String>> dI = ImplicationGraphUtil.diffEdges(impl1, impl2);
		printSetEdges(dI, 5);
		
	}




	public static void analyzeCliques(FeatureModelVariable fmv1, FeatureModelVariable fmv2) {
		Collection<Set<String>> cl1 = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		Collection<Set<String>> cl2 = CliquesOperationFactory.mkCliques(fmv2, CliquesComputation.SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		
		
		System.err.println("common cliques: " + org.apache.commons.collections15.CollectionUtils.intersection(cl1, cl2));
		System.err.println("cl1 -- c2 => " + org.apache.commons.collections15.CollectionUtils.subtract(cl1, cl2));
		
	}
	
	
	
	public static void analyzeImplicationExclusion(FeatureModelVariable fmv1,	FeatureModelVariable fmv2, BDDBuilder<String> builder) {
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(builder);
		ImplicationGraph<String> impl2 = fmv2.computeImplicationGraph(builder);
		
		
		//ImplicationGraphUtil.commonEdges(impl1, impl2);
		Set<SimpleExtendedEdge<String>> dI = ImplicationGraphUtil.diffEdges(impl1, impl2);
		System.err.println("\nBIG");
		printSetEdges(dI, 5);
		
		ExclusionGraph<String> excl1 = fmv1.computeExclusionGraph(builder);
		ExclusionGraph<String> excl2 = fmv2.computeExclusionGraph(builder);
		Set<SimpleExtendedEdge<String>> dE = ImplicationGraphUtil.diffExclEdges(excl1, excl2);
		System.err.println("\nBEG");
		printSetEdges(dE, 5);
		
	}




	public static void printSetEdges(Set<SimpleExtendedEdge<String>> cedges, int PRINTLN_EVERY) {
	int nc = 0;
	int nelement = 0;
	for (SimpleExtendedEdge<String> ce1 : cedges) {
		if (nelement++ == PRINTLN_EVERY) {
			nelement = 0;
			System.err.println();
			System.err.print("" + nc + ".." + (nc + PRINTLN_EVERY) + "=");
		}
		nc++;
		System.err.print(ce1.toString() + ";");
	}
	
}
	
	
	
}
