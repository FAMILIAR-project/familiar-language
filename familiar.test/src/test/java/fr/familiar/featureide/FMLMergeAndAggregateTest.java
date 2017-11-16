/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Or;
import org.xtext.example.mydsl.fml.SliceMode;

import com.google.common.collect.Sets;

import de.ovgu.featureide.fm.core.editing.NodeCreator;
import fr.familiar.FMLSlicerUtilityTest;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.parser.FMLMergerWithConstraints;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Excludes;

@RunWith(Parameterized.class)
public class FMLMergeAndAggregateTest extends FMLSlicerUtilityTest {

	protected static final String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
	protected static final String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
	protected static final String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

	protected static final String fmsupp3b = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; )";

	protected static final String fmExpectedSupp = "FM (MedicalImage : Anonymized MRI Header DICOM ; MRI : T1 T2 ; )";

	protected List<FeatureModelVariable> _fmvPossibleHierarchies = new ArrayList<FeatureModelVariable>();

	/**
	 * possible hierarchy expected of the resulting, merged FM (more than one is
	 * possible)
	 */
	protected List<String> _possibleHierarchies;

	/**
	 * List of feature models specification
	 */
	protected List<String> _lfms = new ArrayList<String>();

	/**
	 * List of feature models variable (once parsed in FML)
	 */
	protected List<FeatureModelVariable> _lfmvs = new ArrayList<FeatureModelVariable>();

	protected static int t = 0;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_shell.setCountingStrategy(CountingStrategy.BDD_SPLOT); // TODO: should work
															// well with FML or
															// SAT

		int i = 0;
		for (String fm : _lfms) {
			String fmIdentifier = "fm" + i++;
			_shell.parse(fmIdentifier + " = " + fm + " \n");
			_lfmvs.add(getFMVariable(fmIdentifier));
		}

		int j = 0;
		String fmHierarchyID = "hierarchyExpected";
		for (String possibleHierarchy : _possibleHierarchies)
			_fmvPossibleHierarchies.add(FM(fmHierarchyID + "" + j++,
					possibleHierarchy));

	}

	public FMLMergeAndAggregateTest(List<String> lfms, List<String> possibleHierarchies)
			throws Exception {
		
		_lfms = lfms;
		_possibleHierarchies = possibleHierarchies;
	}

	@Parameters
	public static Collection<Object[]> data() {

		return Arrays
				.asList(new Object[][] {
						
						{
							Arrays.asList(new String[] {
									"FM (A : B C ; ) ",
									"FM (A : E ; )" }),
							Arrays.asList(new String[] { "FM (A : B C E ; )" }) },
							
						{
								Arrays.asList(new String[] {
										"FM (A : (B|C|D)+; ) ",
										"FM (A : (B|D|E) ; )",
										"FM (A : (B|D)+; )",
										"FM (A : (B|D|E|C) ; )",
										"FM (A : [B] [D] [E] ; )" }),
								Arrays.asList(new String[] { "FM (A : B C D E ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : (B|C); )", "FM (A : (B|C); )",
										"FM (A : [B] [C]; B | C ; !B | !C ; )" }),
								Arrays.asList(new String[] { "FM (A : B C ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : (B|C); )", "FM (A : (B|C); )",
										"FM (A : [B] [C]; B | C ; )" }),
								Arrays.asList(new String[] { "FM (A : B C ; )" }) },

						// spanning tree is not unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B [C] ;)\n",
										"FM (A : B ; B : [C]; )\n" }),
								Arrays.asList(new String[] { "FM (A : B C ; )",
										"FM (A : B ; B : C ; )" }) },

						// spanning tree is not unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n" }),
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : F ; )",
										"FM (A : B ; B : C; C : E; E : F ; )" }) },

						// spanning tree is unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },
						// spanning tree is unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : B C [D]; D : (E|F|G); C : (I|J)+; )",
										"FM (A : B C [D]; D : (E|F|G)+; C : (I|J) ; )\n",
										"FM (A : B C [D]; D : (E|F|G)+; C : (I|J)+ ; )\n", }),
								Arrays.asList(new String[] { "FM (A : B C D; D : E F G; C : I J ; )" }) },
								
						{
								Arrays.asList(new String[] { fmsupp1, fmsupp2,
										fmsupp3b }),
								Arrays.asList(new String[] { fmExpectedSupp }) },

						{
								Arrays.asList(new String[] { fmsupp1, fmsupp2,
										fmsupp3 }),
								Arrays.asList(new String[] { fmExpectedSupp }) },

						{
								Arrays.asList(new String[] { "FM (A : B; ) ",
										"FM (A : C ; )", "FM (A : D ; )", }),
								Arrays.asList(new String[] { "FM (A : B C D ; )" }) },
								
						{
									Arrays.asList(new String[] { "FM (A : I [B]; ) ",
											"FM (A : [C] I [J] ; )", "FM (A : [D] I ; )", }),
									Arrays.asList(new String[] { "FM (A : I ; )" }) },
								
						{
									Arrays.asList(mkInputFMs(15, 
											new String[] { "FM (A : (B|C|D)+; ) ",
											"FM (A : (B|D|E) ; )",
											"FM (A : (B|D)+; )",
											"FM (A : (B|D)+; )",
											"FM (A : (B|D|E|C) ; )", }									
									)),
									Arrays.asList(new String[] { "FM (A : B C D E ; )" }) },
									
									
						/*	scale		{
										Arrays.asList(mkInputFMs(100, 
												new String[] { "FM (A : (B|C|D)+; ) ",
												"FM (A : (B|D|E) ; )",
												"FM (A : (B|D)+; )",
												"FM (A : (B|D)+; )",
												"FM (A : (B|D|E|C) ; )", }									
										)),
										Arrays.asList(new String[] { "FM (A : B C D E ; )" }) },*/
							
									
						/* scale	{
									Arrays.asList(mkInputFMs(200, 
											new String[] { "FM (A : (B|C|D)+; ) ",
											"FM (A : (B|D|E) ; )",
											"FM (A : (B|D)+; )",
											"FM (A : (B|G)+; )",
											"FM (A : (B|D|E|C) ; )", }									
									)),
									Arrays.asList(new String[] { "FM (A : B C D E ; )" }) }, */
									
							/* scale		{
										Arrays.asList(mkInputFMs(200, 
												new String[] { "FM (A : (B|C|D)+ ; D : (I|J)+ ; )",
												"FM (A : (B|D|E) ; D : [I] ; E : [J] ; I -> E ;  )",
												"FM (A : (B|D)+; D : J ; )",
												"FM (A : (B|G)+ [I] ; )",
												"FM (A : (B|D|E|C) ; D : (I|J) ; )", }									
										)),
										Arrays.asList(new String[] { "FM (A : B C D E G ; D : I J ; )" }) },*/
									
									
							/* scale {
										Arrays.asList(mkInputFMs(400, 
												new String[] { "FM (A : (B|C|D)+ ; D : (I|J)+ ; )",
												"FM (A : (B|D|E) ; D : [I] ; E : [J] ; I -> E ;  )",
												"FM (A : (B|D)+; D : J ; )",
												"FM (A : (B|G)+ [I] ; )",
												"FM (A : (B|D|E|C) ; D : (I|J) ; )", }									
										)),
										Arrays.asList(new String[] { "FM (A : B C D E G ; D : I J ; )" }) },*/
									
									
									
						/*	{
										Arrays.asList(mkInputFMs(450,  // not scaling with SAT-based merging
												new String[] { "FM (A : (B|C|D)+; ) ",
												"FM (A : (B|D|E) ; )",
												"FM (A : (B|D)+; )",
												"FM (A : (B|G)+; )",
												"FM (A : (B|D|E|C) ; )", }									
										)),
										Arrays.asList(new String[] { "FM (A : B C D E G ; )" }) },*/
									
							/* scale {
									Arrays.asList(mkInputFMs(75, 
											new String[] { "FM (A : (B|C|D)+ ; D : (I|J)+ ; )",
											"FM (A : (B|D|E) ; D : [I] ; E : [J] ; I -> E ;  )",
											"FM (A : (B|D)+; D : J ; )",
											"FM (A : (B|G)+ [I] ; )",
											"FM (A : (B|D|E|C) ; D : (I|J) ; )", }									
									)),
									Arrays.asList(new String[] { "FM (A : B C D E G ; D : I J ; )" }) },*/
									
									
							/*scale {
										Arrays.asList(mkInputFMs(75, 
												new String[] { "FM (A : (B|C|D)+ ; D : (I|J)+ ; )",
												"FM (A : (B|D|E) ; D : [I] ; E : M N O [J] ; I -> E ;  )",
												"FM (A : (B|D)+; D : J P Q R; )",
												"FM (A : (B|G)+ [I] S ; )",
												"FM (A : (B|D|E|C) ; D : (I|J) T U V W; )", }									
										)),
										Arrays.asList(new String[] { "FM (A : B C D E G S ; E : M N O ; D : P Q R I J T U V W ; )" }) },*/
									
						/* scale {
									Arrays.asList(mkInputFMs(275, 
											new String[] { "FM (A : (B|C|D)+ ; D : (I|J)+ ; )",
											"FM (A : (B|D|E) ; D : [I] ; E : M N O [J] ; I -> E ;  )",
											"FM (A : (B|D)+; D : J P Q R; )",
											"FM (A : (B|G)+ [I] S ; )",
											"FM (A : (B|D|E|C) ; D : (I|J) T U V W; )", }									
									)),
									Arrays.asList(new String[] { "FM (A : B C D E G S ; E : M N O ; D : P Q R I J T U V W ; )" }) },*/

				});
	}

	private static String[] mkInputFMs(int nbReplication, String[] toReplicate) {
	
		List<String> lr = new ArrayList<String>() ; 
		int j = 0 ; 
		for (int i = 0 ; ; ) {
			lr.add(toReplicate[i]); 
			if (i == (toReplicate.length - 1)) { // one replication
				i = 0 ; 
				j++ ; 
			}
			else {
				i++ ; 
			}
			if (j == (nbReplication - 1))
				break ; 
		}
		String[] r = new String[lr.size()]; 
		return lr.toArray(r) ; 
	}

	@Test
	public void testMergeUnion() throws Exception {

		// expected merged FM

		_shell.parse("expectedFM = merge sunion fm*");
		
		// SAT-based merging
		SetVariable fms = getSetVariable("fm*") ;
		Node n = new Literal(NodeCreator.varFalse); 
		Set<String> fts = new HashSet<String>();
		for (Variable v : fms.getVars()) {
			FeatureModelVariable fmv = (FeatureModelVariable) v ; 
			fts.addAll(fmv.features().names());
		}
		
		
		for (Variable v : fms.getVars()) {
			FeatureModelVariable fmv = (FeatureModelVariable) v ;
			Node fmvNode = new SATFMLFormula(fmv).getNode() ;
			Set<String> fmvFts = fmv.features().names() ;
			Set<String> toNegates = Sets.difference(fts, fmvFts);
			Node nots = new Literal(NodeCreator.varTrue);
			for (String toNegate : toNegates) {
				nots = new And(nots, new Literal(toNegate, false));
			}
			
			fmvNode = new And(fmvNode, nots); 
			n = new Or(n, fmvNode).toCNF(); 
		}
		
		
		
		ImplicationGraph<String> ig = new SATFMLFormula(n.toCNF()).computeImplicationGraph(fts);
		ImplicationGraphUtil.debugImplicationGraph(ig);
		
		
		
		
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");
		
		
		assertTrue(ImplicationGraphUtil. eq (ig, 
											expectedFM.computeImplicationGraph()));

		System.err.println("expectedFM=" + expectedFM);
		//double countExpected = expectedFM.counting();
		//System.err.println("#expectedFM=" + countExpected);

		
/*
		_shell.setVerbose(true) ; 
		FeatureModelVariable fmvMerged = new FMLMergerWithConstraints(_lfmvs).union();
		_shell.setVerbose(false) ; 
		System.err.println("size fmMerged.* = " + fmvMerged.features().size());
		System.err.println("fmMerged = " + fmvMerged.getSpecificValue());
		//System.err.println("#fmMerged=" + fmvMerged.counting());

		//Set<Variable> configs = fmvMerged.configs();
		//System.err.println("[fmMerged]=" + setConfigToSetStr(configs));

		Set<String> ftsToSlice = new HashSet<String>();
		for (FeatureModelVariable featureModelVariable : _lfmvs) {
			ftsToSlice.addAll(featureModelVariable.getFm().features());
		}

		Set<String> allFts = fmvMerged.getFm().features();
		allFts.removeAll(ftsToSlice);
		
		fmvMerged.setBuilder(_builder);
		FeatureModelVariable slicedFmv = fmvMerged.slice(SliceMode.EXCLUDING, allFts) ;
		System.err.println("slicedFmv=" + slicedFmv);
		System.err.println(""); 
		System.err.println(""); */
		
		
		
	// time consuming	
		//assertEquals(Comparison.REFACTORING, slicedFmv.compareBDD(expectedFM, _builder)); 
		
		//SATFormula flaMerged = fmvMerged.sliceSAT(SliceMode.INCLUDING, slicedFmv.features().names());
	
		
		/*
		SATFMLFormula flaMerged = new SATFMLFormula(fmvMerged) ;
		FeatureModelVariable slicedSATFla = new FeatureModelVariableSATFormula("", flaMerged).slice(SliceMode.EXCLUDING, allFts);
		slicedSATFla.computeImplicationGraph() ; 
		
		ImplicationGraph<String> implGMerged = 
				//slicedSATFla.computeImplicationGraph() ;
				flaMerged.computeImplicationGraph (Sets.difference(fmvMerged.getFm().features(), allFts)) ;
		ImplicationGraphUtil.debugImplicationGraph(implGMerged);
		System.err.println("\n\n\n===============\n\n\n");
		
		ImplicationGraph<String> implMergedSATSliced = slicedSATFla.computeImplicationGraph() ; 
		ImplicationGraphUtil.debugImplicationGraph(implMergedSATSliced);
		
		assertTrue(ImplicationGraphUtil. eq (implMergedSATSliced, 
											implGMerged ))
											;
		ExclusionGraph<String> exclGMerged = flaMerged.computeExclusionGraph(Sets.difference(fmvMerged.getFm().features(), allFts)) ;
		Set<Excludes<String>> edgesE = exclGMerged.edgeSet() ; 
		for (Excludes<String> edgeE : edgesE) {
			String s = edgeE.getAntecedent() ; 
			String t = edgeE.getConsequent() ;
			System.err.println("" + s + " excludes " + t);
		}*/
		
	
		
		
		
		//SATFormula slicedFla2 = fmvMerged.sliceSAT(SliceMode.EXCLUDING, allFts);
		/*SATFMLFormula slicedFla1 = new SATFMLFormula(slicedFmv) ;
		System.err.println("(2)" + slicedFla2.getDomain()) ; // + "\n" + slicedFla2.getNode());
		System.err.println("(1)" + slicedFla1.getDomain()) ; // + "\n" + slicedFla1.getNode());
		assertEquals(Comparison.REFACTORING, slicedFla2.compare(slicedFla1)) ;*/

	}
	
	@Ignore
	@Test
	public void testMergeIntersection() throws Exception {

		// expected merged FM

		_shell.parse("expectedFM = merge intersection fm*");
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");

		System.err.println("expectedFM=" + expectedFM);
		//double countExpected = expectedFM.counting();
		//System.err.println("#expectedFM=" + countExpected);

		_shell.setVerbose(true) ; 
		
		
		//System.err.println("fmMerged=" + fmMerged);
		FeatureModelVariable fmvMerged = new FMLMergerWithConstraints(_lfmvs).intersection() ;
		_shell.setVerbose(false) ; 
		System.err.println("size fmMerged.* = " + fmvMerged.features().size());
		//System.err.println("#fmMerged=" + fmvMerged.counting());

		//Set<Variable> configs = fmvMerged.configs();
		//System.err.println("[fmMerged]=" + setConfigToSetStr(configs));

		Set<String> ftsToSlice = new HashSet<String>();
		for (FeatureModelVariable featureModelVariable : _lfmvs) {
			ftsToSlice.addAll(featureModelVariable.getFm().features());
		}

		Set<String> allFts = fmvMerged.getFm().features();
		allFts.removeAll(ftsToSlice);
		
		boolean satisfiable = fmvMerged.isValid() ; 
		if (satisfiable) {
			fmvMerged.setBuilder(_builder);  
		FeatureModelVariable slicedFmv = fmvMerged.slice(SliceMode.EXCLUDING, allFts) ;
		assertEquals(Comparison.REFACTORING, slicedFmv.compareBDD(expectedFM, _builder)); 
		System.err.println("slicedFmv=" + slicedFmv);
		}
		//SATFormula flaMerged = fmvMerged.sliceSAT(SliceMode.INCLUDING, slicedFmv.features().names());
	
		SATFMLFormula flaMerged = new SATFMLFormula(fmvMerged) ;
		if (!satisfiable) {
			assertFalse (flaMerged.isValid());
		}
		//System.err.println("deads " + flaMerged.deads(flaMerged.getDomain()));
		ImplicationGraph<String> implGMerged = flaMerged.computeImplicationGraph
										(Sets.difference(fmvMerged.getFm().features(), allFts)) ; 
		Set<SimpleEdge> edgesI = implGMerged.edgeSet() ;
		for (SimpleEdge edgeI : edgesI) {
			String s = implGMerged.getEdgeSource(edgeI);
			String t = implGMerged.getTarget(edgeI) ; 
			System.err.println("" + s + " => " + t);
		}
		
		ExclusionGraph<String> exclGMerged = flaMerged.computeExclusionGraph(Sets.difference(fmvMerged.getFm().features(), allFts)) ;
		Set<Excludes<String>> edgesE = exclGMerged.edgeSet() ; 
		for (Excludes<String> edgeE : edgesE) {
			String s = edgeE.getAntecedent() ; 
			String t = edgeE.getConsequent() ;
			System.err.println("" + s + " excludes " + t);
		}
		
		
		
		
		//SATFormula slicedFla2 = fmvMerged.sliceSAT(SliceMode.EXCLUDING, allFts);
		/*SATFMLFormula slicedFla1 = new SATFMLFormula(slicedFmv) ;
		System.err.println("(2)" + slicedFla2.getDomain()) ; // + "\n" + slicedFla2.getNode());
		System.err.println("(1)" + slicedFla1.getDomain()) ; // + "\n" + slicedFla1.getNode());
		assertEquals(Comparison.REFACTORING, slicedFla2.compare(slicedFla1)) ;*/

	}

	

}
