// FIXME
package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.SliceMode;

import splar.core.constraints.BooleanVariable;
import splar.core.constraints.PropositionalFormula;
import splar.core.fm.FeatureTreeNode;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

import com.google.common.collect.Sets;

import fr.familiar.experimental.MappingCorrespondence;
import fr.familiar.experimental.featureide.FMMatcher;
import fr.familiar.experimental.featureide.StringSimilarity;
import fr.familiar.fm.FMLBDDReader;
import fr.familiar.fm.SimpleExtendedEdge;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.fm.converter.FormulaBDDSerializer;
import fr.familiar.fm.converter.S2T2Converter;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.AllConfigsBDD;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.EGBuilder;
import fr.familiar.operations.FMLExpressionUtil;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.parser.FMBuilder;
import fr.familiar.test.FMLSlicerUtilityTest;
import fr.familiar.test.FMLTest;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelLazyVariable;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;


/**
 * @author macher
 * costly
 *
 */
@Ignore
public class AdamArchTest extends FMLTest {
	
	

	private static final String SPLOT_FLA_LOCATION = "output/splotFla";
	private static final String ORIGINAL_FLA_LOCATION = "output/oFla";
	private static final String DIFF_FLA_LOCATION = "output/diffFla";
	private static final String DIFF_FLA_DOMAIN_LOCATION = "output/diffFlaDomain";
	
	
	private static final int N_CONSTRAINT = 10;
	
	public static final int N_CONSTRAINT_MAX = 158 ; // 1.0905190307165527E8
	
	private int _current_nbCst;

	
	
	// what I have done so far, based on the Grafle file
	// FraSCAti => MMFraSCAti (since names have to be unique in FML at the moment)
	// tinfi_oo / tinfi_ooMembraneFactory (for the same reason)
	// OSGI / OSGIMembrane
	   public static String _fmMerleSpecification = "FM (FraSCAti : SCAParser AssemblyFactory ComponentFactory ; " +
			"SCAParser : Metamodel ; Metamodel : SCA [MMFraSCAti] [Tuscany] ; " +
			"AssemblyFactory : Implementation Interface Binding PropertyType ; " +
							"Implementation : [OSGiImplementation] [Script] [Fractal] [Spring] [BPEL] ; " + 
							"OSGiImplementation : " + "(Felix|Equinox) ; " + 
							"Interface : [WSDL] [native] ; " +
							"Binding : [http] [jsonrpc] [rest] [rmi] [ws] [UPnP] ; " +
							"PropertyType : jaxb ;" + 
			"ComponentFactory : MembraneFactory MembraneGeneration ; MembraneFactory : tinfi_ooMembraneFactory [julia] [OSGiMembrane] ; " +
			"MembraneGeneration : JavaCompiler MembraneGenerator ; " +
			"JavaCompiler: (JDK6|JDT); " +
			//"MembraneGenerator: [tinfi_oo] [OSGI] ; " + // original specification: so you authorize no generator? ie., you're less restrictive than the 150 ArchiFM!
			"MembraneGenerator: (tinfi_oo|OSGI)+ ; " + 
			") ";
	
	
		private String _fmMerleSpecificationWithConstraints = "FM (FraSCAti : SCAParser AssemblyFactory ComponentFactory ; " +
		"SCAParser : Metamodel ; Metamodel : SCA [MMFraSCAti] [Tuscany] ; " +
		"AssemblyFactory : Implementation Interface Binding PropertyType ; " +
						"Implementation : [OSGiImplementation] [Script] [Fractal] [Spring] [BPEL] ; " + 
						"OSGiImplementation : " + "(Felix|Equinox) ; " + 
						"Interface : [WSDL] [native] ; " +
						"Binding : [http] [jsonrpc] [rest] [rmi] [ws] [UPnP] ; " +
						"PropertyType : [jaxb] ;" + 
		"ComponentFactory : MembraneFactory MembraneGeneration ; MembraneFactory : tinfi_ooMembraneFactory [julia] [OSGiMembrane] ; " +
		"MembraneGeneration : JavaCompiler MembraneGenerator ; " +
		"JavaCompiler: (JDK6|JDT); " +
		"MembraneGenerator: [tinfi_oo] [OSGI] ; " + // original specification: so you authorize no generator? ie., you're less restrictive than the 150 ArchiFM!
		//"MembraneGenerator: (tinfi_oo|OSGI)+ ; " + 
		"http -> Tuscany ; " +
		"jsonrpc -> MMFraSCAti ; " +
		"rest -> MMFraSCAti ; " +
		"rmi -> MMFraSCAti ; " +
		"Fractal -> MMFraSCAti ; " +
		"Script -> MMFraSCAti ; " +
		"OSGiImplementation -> MMFraSCAti ; " +
		
		
		") ";
		
		private String _fmMerleSpecificationWithConstraintsEnhanced = "FM (FraSCAti : SCAParser AssemblyFactory ComponentFactory ; " +
		"SCAParser : Metamodel ; Metamodel : SCA [MMFraSCAti] [Tuscany] ; " +
		"AssemblyFactory : Implementation Interface Binding PropertyType ; " +
						"Implementation : [OSGiImplementation] [Script] [Fractal] [Spring] [BPEL] ; " + 
						"OSGiImplementation : " + "(Felix|Equinox) ; " + 
						"Interface : [WSDL] [native] ; " +
						"Binding : [http] [jsonrpc] [rest] [rmi] [ws] [UPnP] ; " +
						"PropertyType : [jaxb] ;" + 
		"ComponentFactory : [MembraneFactory] [MembraneGeneration] ; MembraneFactory : [tinfi_ooMembraneFactory] [julia] [OSGiMembrane] ; " +
		"MembraneGeneration : [JavaCompiler] [MembraneGenerator] ; " +
		"JavaCompiler: (JDK6|JDT); " +
		"MembraneGenerator: [tinfi_oo] [OSGI] ; " + // original specification: so you authorize no generator? ie., you're less restrictive than the 150 ArchiFM!
		//"MembraneGenerator: (tinfi_oo|OSGI)+ ; " + 
		"http -> Tuscany ; " +
		"jsonrpc -> MMFraSCAti ; " +
		"rest -> MMFraSCAti ; " +
		"rmi -> MMFraSCAti ; " +
		"Fractal -> MMFraSCAti ; " +
		"Script -> MMFraSCAti ; " +
		"OSGiImplementation -> MMFraSCAti ; " +
		
		
		") ";
		
		
		
		private String _fmMerleSpecificationWithConstraintsBis = "FM (FraSCAti : SCAParser AssemblyFactory ComponentFactory ; " +
		"SCAParser : Metamodel ; Metamodel : SCA (MMFraSCAti|Tuscany)+ ; " +
		"AssemblyFactory : Implementation Interface Binding PropertyType ; " +
						"Implementation : (OSGiImplementation|Script|Fractal|Spring|BPEL)+ ; " + 
						"OSGiImplementation : " + "(Felix|Equinox) ; " + 
						"Interface : (WSDL|native) ; " +
						"Binding : (http|jsonrpc|rest|rmi|ws|UPnP)+ ; " +
						"PropertyType : jaxb ;" + 
		"ComponentFactory : MembraneFactory MembraneGeneration ; MembraneFactory : tinfi_ooMembraneFactory (julia|OSGiMembrane)+ ; " +
		"MembraneGeneration : JavaCompiler MembraneGenerator ; " +
		"JavaCompiler: (JDK6|JDT); " +
		//"MembraneGenerator: [tinfi_oo] [OSGI] ; " + // original specification: so you authorize no generator? ie., you're less restrictive than the 150 ArchiFM!
		"MembraneGenerator: (tinfi_oo|OSGI)+ ; " + 
		"http -> Tuscany ; " +
		"jsonrpc -> MMFraSCAti ; " +
		"rest -> MMFraSCAti ; " +
		"rmi -> MMFraSCAti ; " +
		"Fractal -> MMFraSCAti ; " +
		"Script -> MMFraSCAti ; " +
		"OSGiImplementation -> MMFraSCAti ; " +
		
		
		") ";
	
	/*  - for N_CONSTRAINT=60
	 *  #fmFull=4.6892083838976E14
		#fmvSliced=9.56301312E8
		
		- for N_CONSTRAINT=10
		#fmFull=1.2475871687741748E20
		#fmvSliced=1.0200547328E10
		
		- for N_CONSTRAINT=100
		#fmFull=1.1983417344E12
		#fmvSliced=2.045952E7
			
			
		- for N_CONSTRAINT=157
		#fmFull=1.35352832E8
		#fmvSliced=951552.0
		
		- for N_CONSTRAINT=158 (full)
		#fmFull=9.0040832E7
		#fmvSliced=936576.0
		
		- for N_CONSTRAINT=130
		#fmFull=1.013219328E9
		#fmvSliced=1534464.0
	 * 
	 */
	
	/**
	 * Carlos example from ECSA'10 conference
	 * @throws Exception
	 */
	@Test
	public void testFM() throws Exception {
		_shell.parse("fm1 = FM ( Application : Catalog Notification [Location] Payment ; " +
				"Catalog : Filtered; Filtered : (ByDiscount|ByWeather|ByLocation) ; " +
				"Notification : (SMS|Call) ; " +
				"Location : (Wifi|GPS)+ ; " +
				"Payment : (CreditCard|Discount)+ ; )");
		assertEquals(72, getFMVariable("fm1").counting(), 0);
		_shell.parse("map fm1 with constraints (ByLocation -> Location ; )");
		assertEquals(66, getFMVariable("fm1").counting(), 0);
	}
	
	@Test
	public void testFMFull() throws Exception {
		FeatureModelVariable fmFull = getFMFull();
		
		System.err.println("fmFull=" + fmFull.getSyntacticalRepresentation());
		
	}
	
	
		
	
	/**
	 * rather a playground here
	 * @throws Exception
	 */
	@Test
	public void testS2T2toFML1() throws Exception {
		
		//FeatureModelVariable fmPlugin = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/PluginFeatureModel.fmprimitives"));
		FeatureModelVariable fmArchi = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		
		//statsFM(fmPlugin);
		//statsFM(fmArchi);
		
		FeatureModelVariable fmFullWithoutCst = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
		fmFullWithoutCst.getFm().removeAllConstraints();
		
		statsFM(fmFullWithoutCst);
			
		
		FeatureModelVariable fmFull = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
		gsd.synthesis.FeatureModel<String> fmInternalFull = fmFull.getFm() ;
		Set<Expression<String>> csts = fmInternalFull.getConstraints();
		Set<String> fts = fmInternalFull.features(); 
		for (Expression<String> expr : csts) {
			Set<String> atomicExprs = ExpressionUtil.getAllFeatures(expr);
			for (String atomicExpr : atomicExprs) {
				assertTrue("atomicExpr=" + atomicExpr, fts.contains(atomicExpr));
			}
		}
		
		int idCst = 156 ; 
		Expression<String> cst = getCst(idCst, csts);
		
		System.out.println("cst (" + idCst + ") = " + cst);
		
		System.err.println("====== FULL without some constraints =======");
		FeatureModelVariable fmFullOnlyNFirstCst = getFullOnlyNFirstCst(8); // #157 works but not #158 (max: #159)
		statsFM(fmFullOnlyNFirstCst); 
		
		//int idRemovedCst = 79; // 3 or 56 or 79 does not work / try with 159 158
		//FeatureModelVariable fmFullWithout = getFullWithoutICst(idRemovedCst);
		//statsFM(fmFullWithout); 
		/*
		System.err.println("\n\n========= FLA FULL SPLOT ====");
		Formula<String> fmFullFla = fmFull.getSPLOTFormulaAligned(_builder) ;
		System.err.println("\n\n========= FLA FULL some cst SPLOT ====");
		Formula<String> fmFullSLOTFla = fmFullWithoutSomeCst.getSPLOTFormulaAligned(_builder);
		assertFormulaEquals(fmFullFla, fmFullSLOTFla);
		*/
		
		//	statsFM(fmFull); 
		
		
		// check full in SPLOT and FML
		/*System.err.println("====== FML formula =======");
		Formula<String> fullFla = fmFull.getFormula() ;
		System.err.println("\n\n====== SPLOT formula =======");
		Formula<String> splotFullFla = fmFull.getSPLOTFormulaAligned(_builder);
		System.err.println("====== comparing formula =======");
		assertFormulaEquals(fullFla, splotFullFla);*/
		
		
		/*
		System.err.println("====== SPLOT =======");
		System.err.println("(splot) #full=" + fmFull.countingBDDSPLOT());
		System.err.println("====== slicing ========") ;
		Set<String> fts = setVariabletoString(fmArchi.features());
		Formula<String> slicedArchSPLOT = runSlicingSPLOT(fmFull, fts, SliceMode.INCLUDING) ; //fmFull.getFormula();
		double slicedArchCountingSPLOT = countingFormula(slicedArchSPLOT);
		System.err.println("(splot) #slicedArchCounting=" + slicedArchCountingSPLOT);
		
		
		
		System.err.println("\n\n====== FML =======");
		System.err.println("(fml) #full=" + fmFull.countingBDD());
		System.err.println("====== slicing ========") ;
		Formula<String> slicedArch = runSlicing(fmFull, fts, SliceMode.INCLUDING) ; //fmFull.getFormula();
		double slicedArchCounting = countingFormula(slicedArch);
		System.err.println("#slicedArchCounting=" + slicedArchCounting);
		
		assertFormulaEquals(slicedArchSPLOT, slicedArch);
		
		assertEquals(slicedArchCountingSPLOT, slicedArchCounting, 0);
		*/ 
		
		
		
		
		//statsFM(fmFull);

		
	}


	/**
	 * check whether fmArchi150 is a specialization of fmMerle
	 * @throws Exception
	 */
	@Test
	public void testArchFM() throws Exception {
		
		FeatureModelVariable fmArchi150 = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));

		System.err.println("#fmArchi150=" + fmArchi150.counting (CountingStrategy.BDD_FML));
				
		checkMerleArchi(getFMMerle(), fmArchi150);
			
	}
	

	
	/**
	 * simply controls that fmFull *without* the "plugins" sub FM part is similar to fmArchi150
	 * similar here: same set of features, same set of "leaves"
	 * @throws Exception
	 */
	@Test
	public void testArchFMCoherent() throws Exception {
		
		// without slicing (basic extraction of fmFull *without* the "plugins" sub FM part
		FeatureModelVariable fmvFull = getFullOnlyNFirstCst(N_CONSTRAINT); 
		System.err.println("fmvFull=" +  fmvFull.getSyntacticalRepresentationWithoutCst());
		FeatureModel<String> fmExtractArchi = fmvFull.extract("FraSCAtiArchitecture");
		FeatureModelVariable fmvExtractArchi = new FeatureModelVariable("", fmExtractArchi);
		fmvExtractArchi.renameFeature("FraSCAtiArchitecture", "FraSCAti");
		System.err.println("\n\t=========== FM Archi extracted from FMFull (simple, without slicing) ========\n");
		System.err.println("fmExtractArchi=" +  fmvExtractArchi.getSyntacticalRepresentationWithoutCst());
		
		// fmArchi150
		FeatureModelVariable fmArchi150 = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		System.err.println("fmArchi150=" +  fmArchi150.getSyntacticalRepresentationWithoutCst());
		
		
		controlFts (fmvExtractArchi.features().names(), fmArchi150.features().names());
		controlFts (fmvExtractArchi.leaves().names(), fmArchi150.leaves().names());
			
	}
	
	
	/**
	 * control that the sliced FMFull (i.e., archi enforced) has the same "structure"
	 * @throws Exception
	 */
	@Test
	public void testArchFMSliceKeepHierarchy() throws Exception {
		
		FeatureModelVariable fmvArchi150 = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		System.err.println("fmArchi=" +  fmvArchi150.getSyntacticalRepresentationWithoutCst());
		
		FeatureModelVariable fmvArchiEnforced = getFM150EnforcedLazy(N_CONSTRAINT);
		System.err.println("\n\t=========== FM Archi Enforced (lazy) Done ========\n");
		System.err.println("fmArchiEnforced=" +  fmvArchiEnforced.getSyntacticalRepresentationWithoutCst());
	
		controlFts (fmvArchiEnforced.features().names(), fmvArchi150.features().names());
		controlFts (fmvArchiEnforced.leaves().names(), fmvArchi150.leaves().names());
		
		
	}
	
	
	/**
	 * check that fmArchi150 is an overapproximation of fmvArchiEnforced
	 * @throws Exception
	 */
	@Test
	public void testArchFMSpecialization() throws Exception {
		
		FeatureModelVariable fmvArchi150 = getFM150(); 
		//new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		Set<String> oCoresArchi = fmvArchi150.cores();

		System.err.println("#fmArchi150=" + fmvArchi150.counting (CountingStrategy.BDD_FML));
		
		FeatureModelVariable fmvArchiEnforced = loadFM150SerializedEnforcedLazy();
		System.err.println("#fmArchiEnforced=" + fmvArchiEnforced.counting (CountingStrategy.BDD_FML));
		System.err.println("\n\t=========== FM Archi Enforced (lazy) Done ========\n");
		Set<String> coresArchiEnforced = fmvArchi150.cores();
		
		assertEquals(Comparison.GENERALIZATION,
						fmvArchi150.compareBDD(fmvArchiEnforced, _builder));
		
		
		System.err.println("#deprecatedConfigurations=" + 
					countingFormula(fmvArchi150.
							diffFormula(fmvArchiEnforced, _builder)));
		
		System.err.println("new cores=" + Sets.difference(coresArchiEnforced, oCoresArchi));
		
		compareVariabilityOperators(fmvArchi150, fmvArchiEnforced);
		
		// #originalfm150: 1.3958643712E10
		// #deprecatedConfigurations=1.3957707136E10 for nCST=158
		/*
		 * >>> 13958643712 - 13957707136
			936576L
		 */
	}
	
	





	/**
	 * check that fmArchi150 is an overapproximation of fmvArchiEnforced
	 * @throws Exception
	 */
	@Test
	public void testArchFMSpecialization2() throws Exception {
		
		FeatureModelVariable fmvArchi150 = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		FeatureModelVariable fmvArchiEnforcedWithoutCst = getFM150EnforcedLazy(0);
		assertEquals(Comparison.REFACTORING,
				fmvArchi150.compareBDD(fmvArchiEnforcedWithoutCst, _builder));
		
		FeatureModelVariable fmvArchiEnforced = getFM150EnforcedLazy(N_CONSTRAINT); // works from >5
		System.err.println("\n\t=========== FM Archi Enforced (lazy) Done ========\n");
		System.err.println("#fmArchi=" + fmvArchiEnforced.counting (CountingStrategy.BDD_FML));
		assertEquals(Comparison.GENERALIZATION,
						fmvArchi150.compareBDD(fmvArchiEnforced, _builder));
		
		
		System.err.println("#deprecatedConfigurations=" + 
					countingFormula(fmvArchiEnforcedWithoutCst.
							diffFormula(fmvArchiEnforced, _builder)));
	
		
		// #originalfm150: 1.3958643712E10
		// #deprecatedConfigurations=1.3957707136E10 for nCST=158
		/*
		 * >>> 13958643712 - 13957707136
			936576L
		 */
	}
	
	@Test
	public void testArchFMSerializeArchiEnforcedTuned() throws Exception {
		FeatureModelVariable fmArchi = getFM150EnforcedLazy(N_CONSTRAINT);
		FileSerializer.write(getFMLBDDFileName(_current_nbCst), fmArchi.convert(FMFormat.FMLBDD));
		
	}
	
	
	private String getFMLBDDFileName(int nbCst) {
		return "output/" + "fmArchiEnforced" + nbCst + ".fmlbdd" ; 
	}




	// the entire one! work but costly (about 10 minutes)
	@Test
	public void testArchFMSerializeArchiEnforced() throws Exception {
		 
		FeatureModelVariable fmArchi = getFM150EnforcedLazy();
		FileSerializer.write("output/" + "fmArchiEnforced" + _current_nbCst + ".fmlbdd"  , fmArchi.convert(FMFormat.FMLBDD));
		FileSerializer.write("inputFML/FraSCAti/" + "fmArchiEnforced" + _current_nbCst + ".fmlbdd"  , fmArchi.convert(FMFormat.FMLBDD));
		
	}
	
	
	@Test
	public void testArchFMSerializeMerle() throws Exception {
		FeatureModelVariable fmArchi = getFMMerle();
		FileSerializer.write("output/" + "fmMerle" + ".fmlbdd", fmArchi.convert(FMFormat.FMLBDD));
		
	}
	
	private FeatureModelVariable getFMMerle() throws Exception {
		String fmMerleID = "fmMerle";
		FeatureModelVariable fmMerle = FM(fmMerleID, _fmMerleSpecification); // _fmMerleSpecification
		_shell.parse("nFTs = size " + fmMerle.getIdentifier() + ".*") ;
		assertEquals(39, getIntegerVariable("nFTs").getV()); // manual control
		return fmMerle;
	}

	
	@Test
	public void testArchFM2() throws Exception {
		FeatureModelVariable fmArchi = getFM150EnforcedLazy(); // getFM150Enforced(); // 
		System.err.println("\n\t=========== FM Archi Enforced Done ========\n");
		checkMerleArchi(getFMMerle(), fmArchi);
	}
	
	@Test
	public void testArchFM1() throws Exception {
		FeatureModelVariable fmArchi = getFM150();
		System.err.println("\n\t=========== FM Archi 150 ========\n");
		checkMerleArchi(getFMMerle(), fmArchi);
	}
	
	private FeatureModelVariable getFM150() {
		FeatureModelVariable fmArchi150 = 
			new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		return fmArchi150; 
	}


	
	@Test
	public void testFMMerleRequirements() throws Exception {
		/*
 			Bonjour Mathieu, tous,
				Connaissez-vous un moyen automatique de convertir un modèle de features au format S2T2 (fichiers .fmprimitives) vers 
				le format utilisé par le site SPLOT (http://www.splot-research.org/) ?
				En fait, je souhaiterais exporter le modèle de FraSCAti sur le site SPLOT pour utiliser leurs outils d'analyse 
				(nb features, configurations, features mortes, etc) et de visualisation/configuration.
				Je souhaiterais vérifier que FraSCAti a bien environ 1 million de configurations valides.
				Peut-être que je pourrais faire les mêmes analyses avec FAMILIAR ?
				Est-ce que FAMILIAR est téléchargeable quelque part ?
		 */
		
		
		FeatureModelVariable fmvMerle = getFMMerleWithConstraints() ; // 1191936
		_shell.parse("n = counting " + fmvMerle.getIdentifier());
		System.err.println(getIntegerVariable("n").getSpecificValue());
		
		
		_shell.parse("println \"number of features: \", nFTs");
		_shell.parse("println \"number of core features: \", size cores " + fmvMerle.getIdentifier());
		_shell.parse("println \"core features: \", cores " + fmvMerle.getIdentifier());
		_shell.parse("println \"dead features: \", deads " + fmvMerle.getIdentifier());
		_shell.parse("println \"number of configurations: \", n");

		_shell.parse("serialize " + fmvMerle.getIdentifier() + " into " + "SPLOT");
		_shell.parse("serialize " + fmvMerle.getIdentifier() + " into " + "featureide");
		_shell.parse("serialize " + fmvMerle.getIdentifier() + " into " + "S2T2");
		
		
		System.err.println(_shell.getHistory()); 
		
	}

	
	@Test
	public void testArchFMStats() throws Exception {
		//_shell.setVerbose(true);
		
		FeatureModelVariable fmPlugin = getFMPlugin();
		//System.err.println("fmPlugin=" + fmPlugin.getSyntacticalRepresentation());
		System.err.println("ctcr(fmPlugin)=" + fmPlugin.CTCR());
		System.err.println("#F(fmPlugin)=" + fmPlugin.features().size());
		
		FeatureModelVariable fm150 = getFM150();
		//System.err.println("fm150=" + fm150.getSyntacticalRepresentation());
		System.err.println("#fm150=" + fm150.counting());
		System.err.println("ctcr(fm150)=" + fm150.CTCR());		
		System.err.println("#F(fm150)=" + fm150.features().size());
		
		FeatureModelVariable fmFull = getFMFull() ; 
		//System.err.println("fmFull=" + fmFull.getSyntacticalRepresentation());
		System.err.println("ctcr(fmFull)=" + fmFull.CTCR());
		System.err.println("#F(fmFull)=" + fmFull.features().size());
		
		FeatureModelVariable fmArchi = loadFM150SerializedEnforcedLazy();
		//System.err.println("fmArchi=" + fmArchi.getSyntacticalRepresentation());
		System.err.println("#fmArchi=" + fmArchi.counting (CountingStrategy.BDD_FML));
		System.err.println("#F(fmArchi)=" + fmArchi.features().size());
		
		FeatureModelVariable fmvMerle = getFMMerleWithConstraintsEnhanced();
		//System.err.println("fmMerle=" + fmvMerle.getSyntacticalRepresentation());
		System.err.println("#fmMerle=" + fmvMerle.counting (CountingStrategy.BDD_FML));
		System.err.println("#F(fmMerle)=" + fmvMerle.features().size());
		
		
	}

	@Test
	public void testArchFM2bis() throws Exception {
		//_shell.setVerbose(true);
		
		
		
		FeatureModelVariable fmArchi = loadFM150SerializedEnforcedLazy();
		fmArchi.setIdentifier("fmArchi");
		System.err.println("\n\t=========== (load) FM Archi Enforced Done ========\n");
		System.err.println("fmArchi=" + fmArchi.getSyntacticalRepresentation());
		System.err.println("#fmArchi=" + fmArchi.counting (CountingStrategy.BDD_FML));
		FileSerializer.write("inputFML/FraSCAti/fmArchiEnforced13.fmprimitives", fmArchi.convert(FMFormat.S2T2));
		/*
		 * 
		 * diffFla (Archi -- Merle): #430272.0
		  diffFla (Merle -- Archi): #374208.0
		 * 
		 */
		//checkMerleArchi(getFMMerle(), fmArchi);
		
		/*
		 * 
		 * diffFla (Archi -- Merle): #430272.0
		   diffFla (Merle -- Archi): #133056.0
		 * 
		 */
		FeatureModelVariable fmvMerle = getFMMerleWithConstraints();
		FileSerializer.write("inputFML/FraSCAti/fmMerleWithConstraints.fmprimitives", fmvMerle.convert(FMFormat.S2T2));
		
		// 4469760.0
		
		/*
		setOptional(fmvMerle, "MembraneFactory");
		setOptional(fmvMerle, "MembraneGeneration");
		setOptional(fmvMerle, "MembraneGenerator");
		setOptional(fmvMerle, "tinfi_ooMembrane");
		setOptional(fmvMerle, "JavaCompiler");
		*/
		
		System.err.println("#fmMerle=" + fmvMerle.counting (CountingStrategy.BDD_FML));
		
		/*
		setMandatory(fmArchi, "fractal_bootstrap_class_providers"); // MembraneFactory
		setMandatory(fmArchi, "delegate_membrane_generation"); // "MembraneGeneration", ""
		setMandatory(fmArchi, "generators"); //"MembraneGenerator");
		setMandatory(fmArchi, "compiler_provider"); // "JavaCompiler");
		setMandatory(fmArchi, "tinfi_oo"); //"tinfi_ooMembrane");
		*/
		
		Comparison cmp = checkMerleArchi(fmvMerle, fmArchi);
		System.err.println("======= fmMerle is a " + cmp.name() + " of fmArchi =========");
		
		
		
	}
	

	private void setOptional(FeatureModelVariable fmv, String ftName) {
		fmv.getFeature(ftName).setOptionalStatus();
	
	}

	private void setMandatory(FeatureModelVariable fmv, String ftName) {
		
		fmv.getFeature(ftName).setMandatoryStatus();
	}

	private FeatureModelVariable getFMMerleWithConstraintsEnhanced() throws Exception {
		String fmMerleID = "fmMerle";
		FeatureModelVariable fmMerle = FM(fmMerleID, _fmMerleSpecificationWithConstraintsEnhanced); 
		_shell.parse("nFTs = size " + fmMerle.getIdentifier() + ".*") ;
		assertEquals(39, getIntegerVariable("nFTs").getV()); // manual control
		return fmMerle;
	}
	
	private FeatureModelVariable getFMMerleWithConstraints() throws Exception {
		String fmMerleID = "fmMerle";
		FeatureModelVariable fmMerle = FM(fmMerleID, _fmMerleSpecificationWithConstraints); 
		_shell.parse("nFTs = size " + fmMerle.getIdentifier() + ".*") ;
		assertEquals(39, getIntegerVariable("nFTs").getV()); // manual control
		return fmMerle;
	}




	private FeatureModelVariable loadFM150SerializedEnforcedLazy() {

		FMLBDDReader reader = new FMLBDDReader(getFMLBDDFileName(N_CONSTRAINT_MAX));
		// TODO
		// reader.getFMV(assigner);
		Map<String, Integer> map = reader.getMapBuilder();
		BDD bdd = reader.getBDD();
		Formula<String> fla = new Formula<String>(bdd, map.keySet(), _builder);
		
		
		FeatureModel<String> ifm = FMBuilder.getInternalFM("FM (" + reader.getFMRepresentation() + ")");
		assertNotNull (ifm);
		Formula<String> diffFla = FMLMergerBDD.diff(fla, _builder.mkFeatureModel(ifm), _builder);
		return new FeatureModelLazyVariable("", ifm, diffFla);
	}




	private FeatureModelVariable getFM150Enforced() throws IOException {
		
		FeatureModelVariable fmArchi = 
			new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		FeatureModelVariable fmFull = getFullOnlyNFirstCst(N_CONSTRAINT); // 158: hard
				//new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
		System.err.println("\n\t=========== begin slicing ========\n");
		
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmFull, fmArchi.features().names(), SliceMode.INCLUDING);
		System.err.println("\n\t===========  end slicing ========\n");
		return fmvSliced ; 
	}
	
	
	public FeatureModelVariable getFMPlugin() {

		return
			new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/PluginFeatureModel.fmprimitives"));
		
	}
	
	private FeatureModelVariable getFM150EnforcedLazy(int nbCst) throws IOException {
		
		_current_nbCst = nbCst ; 
		
		FeatureModelVariable fmArchi = 
			new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		FeatureModelVariable fmvFull = getFullOnlyNFirstCst(nbCst); // 158: hard but works
				//new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
	//	gsd.synthesis.FeatureModel<String> fmFullSliced = SliceAnalyzer.sliceFM(fmFull, fmArchi.features().names(), SliceMode.INCLUDING, _builder);
	//	assertNotNull(fmFullSliced);
		
		//return new FeatureModelVariable("", fmFullSliced) ;
		System.err.println("\n\t=========== begin slicing ========\n");
		
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmvFull, fmArchi.features().names(), SliceMode.INCLUDING);
		System.err.println("\n\t===========  end slicing ========\n");
		
		/*
		System.err.println("\n\t===========  some stats ========\n");
		System.err.println("#fmFull=" + fmvFull.countingBDD());
		System.err.println("#fmvSliced=" + fmvSliced.countingBDD());
		System.err.println("\n\t===========  end stats ========\n");
		*/
		return fmvSliced ; 
		
	}
	
	private FeatureModelVariable getFM150EnforcedLazy() throws IOException {
			return getFM150EnforcedLazy(N_CONSTRAINT_MAX);	
	
	}

	/**
	 * check the relationship between ofmMerleSpecification and fmArchi (e.g., 150 or enforced by FM views) 
	 * @param fmArchi
	 * @return 
	 * @throws Exception
	 */
	private Comparison checkMerleArchi(FeatureModelVariable fmMerle, FeatureModelVariable fmArchi) throws Exception {
		
		System.err.println("\n\t=========== checking Merle/Archi consistency ========\n");
	
		
		System.err.println("===== original situation between fmMerle and fmArchi ======");		
		debugFts(fmMerle.features().names(), fmArchi.features().names());
		
		
		
		// basic alignment 
		alignMerleAndArchi(fmMerle, fmArchi, _shell);
		System.err.println("\n\n\n\n\n\nHISTORY\n" + _shell.getHistory() + "\n\n\n\n\n\n"); 
		
		
		

		
		System.err.println("===== situation after the alignment of fmMerle and fmArchi ======");		
		
		System.err.println("fmMerleAligned=" + fmMerle); 
		
		debugFts(fmMerle.features().names(), fmArchi.features().names());
		
		
		
		
		
		// seems surprising at first glance... 
		assertEquals(Comparison.ARBITRARY, fmMerle.compareBDD(fmArchi, _builder));
		System.err.println("\n\t=========== compare FMmerle fmArchi -> ARBITRARY  ========\n");
		
		
		// but actually correct... 
		// since there are some disturbing features (too much information in one side)
		// here: Felix|Equinox present in Merle
				
	//	checkDiff(fmMerle, fmArchi);
		
		
		/******** we slice the non matched features in each FM **********/
		
		// (1) in Merle but not in Archi
		Set<String> diffMerle = Sets.difference(fmMerle.features().names(), fmArchi.features().names()) ;
		//FeatureModelVariable fmvSlicedMerle = SliceAnalyzer.sliceFMVlazyConstraint(fmMerle, diffMerle, SliceMode.EXCLUDING, _builder) ;
		
		Set<String> ftsToConsiderMerle = diffMerle ;
		
		/*Sets.difference(Sets.union(fmMerle.leaves().names(), 
								new HashSet<String>(Arrays.asList( new String[] { fmMerle.root().name() }))), 
								diffMerle) ;*/
		
		System.err.println("\nftsToConsiderMerle =>\n\t " + ftsToConsiderMerle);
		
		FeatureModelVariable fmvSlicedMerle = new FMSlicerBDD(_builder).sliceFM(fmMerle, ftsToConsiderMerle, SliceMode.EXCLUDING) ;
		
		
		
		
		// (2) in Archi but not in Merle
		Set<String> diffArchi = Sets.difference(fmArchi.features().names(), fmMerle.features().names()) ;
		//FeatureModelVariable fmvSlicedArchi =  SliceAnalyzer.sliceFMVlazyConstraint(fmArchi, diffArchi, SliceMode.EXCLUDING, _builder) ;
		
		Set<String> ftsToConsiderArchi = diffArchi ; 
		
		/*
		Sets.difference(Sets.union(fmArchi.leaves().names(), 
				new HashSet<String>(Arrays.asList( new String[] { fmArchi.root().name() }))), 
				diffArchi) ;
				*/
		
		FeatureModelVariable fmvSlicedArchi = new FMSlicerBDD(_builder).sliceFM(fmArchi, ftsToConsiderArchi, SliceMode.EXCLUDING) ;
		
		/*
		controlFts(fmvSlicedArchi.features().names(), fmvSlicedMerle.features().names());
		controlFts(fmvSlicedArchi.leaves().names(), fmvSlicedMerle.leaves().names());
	
		checkParentChildConformity(fmvSlicedArchi, fmvSlicedMerle);
		checkParentChildConformity(fmvSlicedMerle, fmvSlicedArchi);
		*/
		
		
		
		// actually it only works for FeatureIDE since considering only leaves
		
		System.err.println("fmSlicedMerle=" + fmvSlicedMerle.getSyntacticalRepresentationWithoutCst());
		System.err.println("fmSlicedArchi=" + fmvSlicedArchi.getSyntacticalRepresentationWithoutCst());
		
		
		// at this step, it is rather impossible to determine if
		// fmMerle is a specialization of fmArchi, even enforced! 
		
		 // the art is to determine what is "IN" and what is "OUT"
		
		// first technique: compare "cores" features
		Set<String> coresSlicedArchi = fmvSlicedArchi.cores();
		Set<String> coresSlicedMerle = fmvSlicedMerle.cores() ;
		System.err.println("cores merle -- archi =\t" + 
				Sets.difference(coresSlicedMerle, coresSlicedArchi));
		System.err.println("cores archi -- merle =\t" + 
				Sets.difference(coresSlicedArchi, coresSlicedMerle));
		
		// deads?
		Set<String> deadsSlicedArchi = fmvSlicedArchi.deads() ;
		System.err.println("deads archi =\t" + deadsSlicedArchi);
		// second technique: compare VOP
		
		
		compareVariabilityOperators(fmvSlicedArchi, fmvSlicedMerle);
		
		// third technique: implication graph
		checkImplicationGraph(fmvSlicedArchi, fmvSlicedMerle);
		
		
		// TODO
		
		// advanced diffing technique here
		System.err.println("#fmMerle=" + fmvSlicedMerle.counting (CountingStrategy.BDD_FML));
		System.err.println("#fmvSlicedArchi=" + fmvSlicedArchi.counting (CountingStrategy.BDD_FML));
		
		
		//checkDiff(fmvSlicedMerle, fmvSlicedArchi);
		
		
		
		Comparison cmpMerleArchi = fmvSlicedMerle.compareBDD(fmvSlicedArchi, _builder);
		
		assertTrue(cmpMerleArchi == Comparison.ARBITRARY); // still granularity issues (e.g., juliac)
	
		
		
		// by example here:
		// Formula<String> diffFla = fmvSlicedMerle.diffFormula(fmvSlicedArchi, _builder);
		// System.err.println("need to slice leaves -- [[merle -- archi]] " + new AllConfigsBDD(_builder).process(diffFla, 1));
		/*
		double nMerle = fmvSlicedMerle.counting() ;
		System.err.println("#fmvSlicedMerle=" + nMerle);
		double nArchi = fmvSlicedArchi.counting() ;
		System.err.println("#fmvSlicedArchi=" + nArchi);
		FeatureModelVariable[] fms = new FeatureModelVariable[] { fmvSlicedArchi, fmvSlicedMerle } ;
		Formula<String> flaMergedIntersection = MergeAnalyzer.mergeFormulas(Arrays.asList(fms), Mode.Intersection);
		double nMerged = countingFormula(flaMergedIntersection) ;
		System.err.println("#common=" + nMerged);
		System.err.println("%diff(1)=" + nMerged / nArchi);
		System.err.println("%diff(2)=" + nMerged / nMerle);
		System.err.println("%diff(3)" + nArchi / (nArchi - nMerle));
		checkDiff(fmvSlicedMerle, fmvSlicedArchi);
		*/
		
	//	FeatureModelVariable fmFullWithout = new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
	//	statsFM(fmFullWithout);
		
		
		
		// to avoid granularity issues
		// (a) slice on leaves
		// (b) slice on common features
		
		/* (a)
		Set<String> ftsAlignedArchi = Sets.union(fmvSlicedArchi.leaves().names(), new HashSet<String>(Arrays.asList( new String[] { fmvSlicedArchi.root().name() }))) ;
		Set<String> ftsAlignedMerle = Sets.union(fmvSlicedMerle.leaves().names(), new HashSet<String>(Arrays.asList( new String[] { fmvSlicedMerle.root().name() }))) ; 
		assertEquals(ftsAlignedArchi, ftsAlignedMerle);
		*/
		
		Set<String> ftsAlignedArchi = new HashSet<String>(Sets.intersection(fmvSlicedArchi.features().names(), fmvSlicedMerle.features().names())); 
		ftsAlignedArchi.add(fmvSlicedArchi.root().name());
		Set<String> ftsAlignedMerle = new HashSet<String>(ftsAlignedArchi); 
		ftsAlignedMerle.add(fmvSlicedMerle.root().name());
		
		System.err.println("ftsAlignedMerle=" + ftsAlignedMerle);
		
		
		fmvSlicedArchi = new FMSlicerBDD(_builder).sliceFM(fmArchi, ftsAlignedArchi, SliceMode.INCLUDING) ;
		fmvSlicedMerle = new FMSlicerBDD(_builder).sliceFM(fmMerle, ftsAlignedMerle, SliceMode.INCLUDING) ;
		
		
		/*
		fmvSlicedMerle.removeAllConstraints();
		fmvSlicedArchi.removeAllConstraints() ; 
		*/
		fmvSlicedMerle.addAllConstraints(fmvSlicedArchi.getAllConstraints());
		 
		
		System.err.println("fmSlicedMerle=" + fmvSlicedMerle.getSpecificValue()) ; //SyntacticalRepresentationWithoutCst());
		System.err.println("fmSlicedArchi=" + fmvSlicedArchi.getSpecificValue()); //SyntacticalRepresentationWithoutCst());
		
		/*
		System.err.println("merge diff { merle archi }=" + fmvSlicedMerle.mergeDiff(fmvSlicedArchi));
		System.err.println("merge diff { archi merle }=" + fmvSlicedArchi.mergeDiff(fmvSlicedMerle));
		*/
		//checkDiff(fmvSlicedMerle, fmvSlicedArchi);
		// works perfectly
		
		
		Comparison cmpMerleArchi2 = fmvSlicedMerle.compareBDD(fmvSlicedArchi, _builder);
		//assertEquals(cmpMerleArchi, cmpMerleArchi2);
		assertTrue(cmpMerleArchi2 == Comparison.SPECIALIZATION || 
						cmpMerleArchi2 == Comparison.GENERALIZATION);
		
		return cmpMerleArchi2 ; 
	}

	private void checkImplicationGraph(FeatureModelVariable fmv1,	FeatureModelVariable fmv2) {
		System.err.println("\n\t======= comparing implication graph ========\n");
		
		ExclusionGraph<String> excl1 = EGBuilder.build(fmv1.getFormula(), _builder);
		System.err.println("excl1=" + excl1);
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		
	
		//ImplicationGraph<Set<String>> impl1Reduced = impl1.reduceCliques();
		//FMLImplicationExclusionTest.debugImplicationGraph(impl1Reduced);
		//TransitiveReduction.INSTANCE.reduce(impl1);
		Set<SimpleEdge> edges1 = impl1.edgeSet() ;
		System.err.println("#impl1=" + edges1.size());
		
		
		
		ExclusionGraph<String> excl2 = EGBuilder.build(fmv2.getFormula(), _builder);
		System.err.println("excl2=" + excl2);
		ImplicationGraph<String> impl2 = fmv2.computeImplicationGraph(_builder);
		//TransitiveReduction.INSTANCE.reduce(impl2);
		Set<SimpleEdge> edges2 = impl2.edgeSet() ; 
		System.err.println("#impl2=" + edges2.size());
		
		int PRINTLN_EVERY = 5 ; 
		
		Set<SimpleExtendedEdge<String>> cedges = ImplicationGraphUtil.commonEdges(impl1, impl2);
		System.err.println("#commonEdges=" + cedges.size());
		printSetEdges(cedges, PRINTLN_EVERY);
		System.err.println("\n");
		
		
		Set<SimpleExtendedEdge<String>> cedges12 = ImplicationGraphUtil.diffEdges(impl1, impl2);
		System.err.println("#edges12=" + cedges12.size());
		printSetEdges(cedges12, PRINTLN_EVERY);
		System.err.println("\n");
		
		
		Set<SimpleExtendedEdge<String>> cedges21 = ImplicationGraphUtil.diffEdges(impl2, impl1);
		System.err.println("#edges21=" + cedges21.size());
		printSetEdges(cedges21, PRINTLN_EVERY);
		System.err.println("\n");
		
		assertEquals(cedges12.size(), edges1.size() - cedges.size());
		assertEquals(cedges21.size(), edges2.size() - cedges.size());
		
		
		
		System.err.println("\n======= comparing (part 2) =====\n");
		// compare implication graph (full) 
		// FIXME: redundant with previous checkings
		Set<Expression<String>> fullImpl1 = ImplicationGraphUtil.toExpressions(fmv1.computeImplicationGraph(_builder));
		Set<Expression<String>> fullImpl2 = ImplicationGraphUtil.toExpressions(fmv2.computeImplicationGraph(_builder));
	
		Set<Expression<String>> fullImpl12 = Sets.difference(fullImpl1, fullImpl2);
		System.err.println("#fullImpl1 -- fullImpl2=" + fullImpl12.size());
		System.err.println("fullImpl1 -- fullImpl2=" + fullImpl12);
		
		Set<Expression<String>> fullImpl21 = Sets.difference(fullImpl2, fullImpl1);
		System.err.println("#fullImpl2 -- fullImpl1=" + fullImpl21.size());
		System.err.println("fullImpl2 -- fullImpl1=" + fullImpl21);
	
		
		// compare feature hierarchy graph
		Set<Expression<String>> implHierarchy1 = ImplicationGraphUtil.toExpressions(fmv1.getImplicationGraphFromFeatureHierarchy(_builder));
		System.err.println("#implHierarchy1=" + implHierarchy1.size());
		Set<Expression<String>> implHierarchy2 = ImplicationGraphUtil.toExpressions(fmv1.getImplicationGraphFromFeatureHierarchy(_builder));
		System.err.println("#implHierarchy2=" + implHierarchy2.size());
		
		Set<Expression<String>> implHierarchy12 = Sets.difference(implHierarchy1, implHierarchy2);
		System.err.println("#implHierarchy1 -- implHierarchy2=" + implHierarchy12.size());
		System.err.println("implHierarchy1 -- implHierarchy2=" + implHierarchy12);
		
		Set<Expression<String>> implHierarchy21 = Sets.difference(implHierarchy2, implHierarchy1);
		System.err.println("#implHierarchy2 -- implHierarchy1=" + implHierarchy21.size());
		System.err.println("implHierarchy2 -- implHierarchy1=" + implHierarchy21);
		
		
		// compare implication edges (cross-tree constraints)
		Set<Expression<String>> implEdges1 = fmv1.computeImpliesEdge(_builder);
		System.err.println("#implEdge1=" + implEdges1.size());
		Set<Expression<String>> implEdges2 = fmv2.computeImpliesEdge(_builder);
		System.err.println("#implEdge2=" + implEdges2.size());
		
		Set<Expression<String>> implEdges12 = Sets.difference(implEdges1, implEdges2);
		System.err.println("#implEdge1 -- implEdge2=" + implEdges12.size());
		System.err.println("implEdge1 -- implEdge2=" + implEdges12);
		
		Set<Expression<String>> implEdges21 = Sets.difference(implEdges2, implEdges1);
		System.err.println("#implEdge2 -- implEdge1=" + implEdges21.size());
		System.err.println("implEdge2 -- implEdge1=" + implEdges21);
		
		System.err.println("\n======= end comparing (part 2) =====\n");
		
		System.err.println("\n\t======= end comparing implication graph ========\n");
		
		
	}




	private void printSetEdges(Set<SimpleExtendedEdge<String>> cedges, int PRINTLN_EVERY) {
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







	/**
	 * @param fmvMerle
	 * @param fmArchi
	 * 
	 * Has side effects on fmvMerle and fmvArchi
	 */
	public static void alignMerleAndArchi(FeatureModelVariable fmvMerle, FeatureModelVariable fmArchi, FMLShell shell) {
		
		
		/********** mapping (syntactic, one-to-one) **********/
		// rewriting rules (key: before/left/Merle, value: after/right/Archi)
		
		Map<String, String> manualMapping = new HashMap<String, String>() ;
		
		String mMsuffix = "sca_metamodel_";
		manualMapping.put("MMFraSCAti", mMsuffix + "frascati");
		manualMapping.put("Tuscany", mMsuffix + "tuscany");
		manualMapping.put("SCA", "sca_metamodel");
		String implSuffix = "sca_implementation_";
		manualMapping.put("BPEL", implSuffix + "bpel");
		manualMapping.put("Spring", implSuffix + "spring");
		String implFrascSuffix = "frascati_implementation_";
		manualMapping.put("Fractal", implFrascSuffix + "fractal");
		manualMapping.put("Script", implFrascSuffix + "script");
		manualMapping.put("OSGiImplementation", implFrascSuffix + "osgi");
		
		manualMapping.put("Binding", "bindings");
		String scaBindingSuffix = "sca_binding_";
		manualMapping.put("ws", scaBindingSuffix + "ws");
		manualMapping.put("UPnP", scaBindingSuffix + "upnp");
		String frascatiBindingSuffix = "frascati_binding_";
		manualMapping.put("http", frascatiBindingSuffix + "http");
		manualMapping.put("rest", frascatiBindingSuffix + "rest");
		manualMapping.put("rmi", frascatiBindingSuffix + "rmi");
		manualMapping.put("jsonrpc", frascatiBindingSuffix + "jsonrpc");
		
		String interfaceSuffix = "sca_interface_";
		manualMapping.put("WSDL", interfaceSuffix + "wsdl");
		manualMapping.put("native", interfaceSuffix + "native");
		manualMapping.put("Interface", "interfaces");
		
		manualMapping.put("OSGiMembrane", "osgi_provider");
		// dangerous
		manualMapping.put("tinfi_oo", "tinfi_oo_1");
		manualMapping.put("tinfi_ooMembraneFactory", "tinfi_oo");
	
		
		
		manualMapping.put("jaxb", "sca_property_type_jaxb");
		
		manualMapping.put("SCAParser", "sca_parser");
		manualMapping.put("Metamodel", "metamodels");
		
		manualMapping.put("AssemblyFactory", "assembly_factory") ;
		
		manualMapping.put("ComponentFactory", "component_factory") ;
		manualMapping.put("JDT", "jdt_compiler") ;
		manualMapping.put("JDK6", "jdk6_compiler") ;
		
		manualMapping.put("OSGI", "osgi") ;
		
		manualMapping.put("Implementation", "implementations") ;
		
		manualMapping.put("FraSCAti", "FraSCAti");
		manualMapping.put("julia", "julia");
				
		
		// I want to see how automated techniques can help...
		FMMatcher matcher = new FMMatcher(new StringSimilarity() {
			
			@Override
			public boolean isSimilar(String str1, String str2) {
				String lStr1 = str1.toLowerCase() ; 
				String lStr2 = str2.toLowerCase();
				Levenshtein lev = new Levenshtein();
				boolean match = lStr1.contains(lStr2) || lStr2.contains(lStr1) || lev.getUnNormalisedSimilarity(lStr1, lStr2) == 1.0;
				return match ; 
			}
		});
		MappingCorrespondence<String> mappings = matcher.computeOneToOneMappingOrder(fmvMerle, fmArchi);
		System.err.println("mappings ++\t=" + mappings.getKeys());
		System.err.println("mappings --\t=" + mappings.getNonMatchingElements());
		
		/***** control that the manual mapping is "conformed" to the automatic mapping (and vice-versa) ********/
		
		assertEquals(Sets.union(mappings.getKeys(), 
							mappings.getNonMatchingElements()).size(), fmvMerle.features().size());
		
		
		// automatic key subset of manual keys 
		Set<String> automaticKeys = mappings.getKeys() ;
		for (String automaticKey : automaticKeys) {
			Set<String> manualKeys = manualMapping.keySet() ;
			boolean found = false ; 
			String correspondenceKey = mappings.getCorrespondence(automaticKey);
			
			// the manual is actually semi-manual (same names do not have to be specified)
			if (correspondenceKey.equalsIgnoreCase(automaticKey)) {
				found = true ;
				continue ; 
			}
			for (String manualKey : manualKeys) {
				if(manualKey.equalsIgnoreCase(automaticKey)) {
						found = true;
						continue ; 
				}
			
			}
			assertTrue("automaticKey=" + automaticKey + " ->" + correspondenceKey, found) ; 
		}
		
		Set<String> missingAut = Sets.difference(manualMapping.keySet(), mappings.getKeys()) ;
		System.err.println("missing part of the automated mapping:\n");
		for (String miss : missingAut) {
			System.err.println("" + miss + " -> " + manualMapping.get(miss));
		}
		
		Set<String> missingMan = Sets.difference(mappings.getKeys(), manualMapping.keySet()) ;
		System.err.println("missing part of the manual mapping:\n");
		for (String miss : missingMan) {
			System.err.println("" + miss + " -> " + mappings.getCorrespondence(miss));
		}
		
		Set<String> nonMatchs = mappings.getNonMatchingElements() ;
		System.err.println("unable to match the following features:\n");
		for (String nonMatch : nonMatchs) {
			System.err.println("## " + nonMatch);
		}
		
		
		assertEquals(manualMapping.size(), mappings.nbMappings());
		
		
		// difficult part: knowledge is (clearly) needed or automated part can be better
		manualMapping.put("MembraneGenerator", "generators") ;
		manualMapping.put("JavaCompiler", "compiler_provider") ;
		manualMapping.put("MembraneFactory", "fractal_bootstrap_class_providers") ;
		manualMapping.put("MembraneGeneration", "delegate_membrane_generation") ;
		manualMapping.put("PropertyType", "property_types");
		
			
		Set<String> ftToRewrite = manualMapping.keySet() ;
		for (String ft : ftToRewrite) {
			String newFt = manualMapping.get(ft);
			String renamingInstruction = "renameFeature " + fmvMerle.getIdentifier() + "." + ft + " as \"" + newFt + "\"" ;
			//_shell.parse("assert (" + renamingInstruction + ")\n");
			shell.parse(renamingInstruction + "\n");
		}
		System.err.println("\n\t=========== end of alignment ========\n");
		
		
	}


	private void debugFts(Set<String> ftsArchiMerle, Set<String> ftsArchi) {
		Set<String> diffMerle = Sets.difference(ftsArchiMerle, ftsArchi) ;
		System.err.println("in Merle but not in archi: #" + diffMerle.size() + " " + diffMerle);
		Set<String> diffArchi = Sets.difference(ftsArchi, ftsArchiMerle) ;
		System.err.println("in archi but not in Merle: #" + diffArchi.size() + " " + diffArchi);
		Set<String> inter = Sets.intersection(ftsArchiMerle, ftsArchi) ;
		System.err.println("in archi and in Merle: #" + inter.size() + " " + inter);
		
	}


	private void checkDiff(FeatureModelVariable fmMerle,	FeatureModelVariable fmArchi) {
		System.err.println("ftMaps=" + _builder.getFeatureMap());
		Formula<String> flaDiffArchi = fmArchi.diffFormula(fmMerle, _builder) ;
		FormulaAnalyzer<String> flaDiffArchiAnalyzer = new FormulaAnalyzer<String>(flaDiffArchi, _builder) ; 
		//System.err.println("deads flaDiffArchi" + flaDiffArchiAnalyzer.computeDeadFeatures());
		flaDiffArchi = flaDiffArchiAnalyzer.removeDeadFeatures();
		Set<String> coresDiffArchi = new FormulaAnalyzer<String>(flaDiffArchi, _builder).computeCoreFeatures();
		System.err.println("diffFla (Archi -- Merle): #" + countingFormula(flaDiffArchi));
		
		Set<Set<String>> diffArchis = new AllConfigsBDD(_builder).process(flaDiffArchi, 3);
		for (Set<String> diffArchi : diffArchis) {
			System.err.println("\t " + diffArchi ) ; //Sets.difference(diffArchi, coresDiffArchi));
		}
		
		
		Formula<String> flaDiffMerle = fmMerle.diffFormula(fmArchi, _builder) ;
		FormulaAnalyzer<String> flaDiffMerleAnalyzer = new FormulaAnalyzer<String>(flaDiffMerle, _builder) ; 
		flaDiffMerle= flaDiffMerleAnalyzer.removeDeadFeatures();
		Set<String> coresDiffMerle = new FormulaAnalyzer<String>(flaDiffMerle, _builder).computeCoreFeatures();
		System.err.println("diffFla (Merle -- Archi): #" + countingFormula(flaDiffMerle));
		Set<Set<String>> diffMerles = new AllConfigsBDD(_builder).process(flaDiffMerle, 3);
		for (Set<String> diffMerle : diffMerles) {
			System.err.println("\t " + diffMerle) ; //Sets.difference(diffMerle, coresDiffMerle));
		}
		
		// TODO intersection
		Formula<String> flaIntersection = fmMerle.intersectionFormula(fmArchi, _builder);
		System.err.println("fla (Merle ** Archi): #" + countingFormula(flaIntersection));
		Set<Set<String>> intersectArchiMerles = new AllConfigsBDD(_builder).process(flaIntersection, 3);
		for (Set<String> intersectArchiMerle : intersectArchiMerles) {
			System.err.println("\t " + intersectArchiMerle) ; //Sets.difference(diffMerle, coresDiffMerle));
		}
		
		assertTrue(flaDiffMerle.isZero() || flaDiffArchi.isZero());
		
	}

	private void controlFts(Set<String> ftsArchiMerle, Set<String> ftsArchi) {
		debugFts(ftsArchiMerle, ftsArchi);
		assertTrue (ftsArchiMerle.equals(ftsArchi));
	}

	private Expression<String> getCst(int i, Set<Expression<String>> csts) {
		Expression<String> nCst = null ; 
		int n = 0;
		for (Expression<String> cst : csts) {
			if (n++ == i)
				nCst = cst;
		}
		return nCst ; 
		
	}

	private FeatureModelVariable getFullOnlyNFirstCst(int nbCst) {
		FeatureModelVariable fmFull = getFMFull(); 
		
		
		gsd.synthesis.FeatureModel<String> fm = fmFull.getFm() ; 
		
		Set<Expression<String>> csts = fm.getConstraints();
		int idExpr = 0 ;
		Set<Expression<String>> cstsToKeep = new HashSet<Expression<String>>();
		for (Expression<String> cst : csts) {
			if (idExpr++ < nbCst)
				cstsToKeep.add(cst);
		}
		
		fm.removeAllConstraints() ;
		
		for (Expression<String> cst : cstsToKeep) {
			if (!fm.addConstraint(cst))
				assertFalse("Unable to add constraint: " + cst, true);
		}
		fm.addFreeVariables();	
		
		return fmFull;
	}
	
	public static FeatureModelVariable getFMFull() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/FullFeatureModel.fmprimitives"));
		
	}
	
	




	private FeatureModelVariable getFullWithoutICst(int iCst) {
		FeatureModelVariable fmFull = getFMFull();
		gsd.synthesis.FeatureModel<String> fm = fmFull.getFm() ; 
		Set<Expression<String>> csts = fm.getConstraints();
		int idExpr = 0 ;
		Set<Expression<String>> cstsToKeep = new HashSet<Expression<String>>();
		for (Expression<String> cst : csts) {
			if (idExpr++ != iCst)
				cstsToKeep.add(cst);
		}
		
		fm.removeAllConstraints() ;
		
		for (Expression<String> cst : cstsToKeep) {
			if (!fm.addConstraint(cst))
				assertFalse("Unable to add constraint: " + cst, true);
		}
				
		
		return fmFull;
	}

	private void statsFM (FeatureModelVariable fm) throws IOException {
		//System.err.println(fm.getSyntacticalRepresentation());
		String strSplot = ConvertAnalyzer.convert(fm, FMFormat.FSPLOT);
		System.err.println("strSplot=" + strSplot);
		System.err.println("CTCR=" + fm.CTCR());
		System.err.println("(normal) #csts=" + fm.getFm().getConstraints().size());
		splar.core.fm.FeatureModel fmSplot = fm.toSPLOT() ;
		System.err.println("(splot) #csts=" + fmSplot.getConstraints().size());
		Collection<PropositionalFormula> csts = fmSplot.getConstraints();
		Set<String> sbvs = new HashSet<String>();
		for (PropositionalFormula propositionalFormula : csts) {
			Collection<BooleanVariable> bvars = propositionalFormula.getVariables();
			int nbv = 0;
			StringBuilder fla = new StringBuilder();
			fla.append("(");
			for (BooleanVariable bv : bvars) {
				String neg = bv.isPositive() ? "" : "!";
				FeatureTreeNode node = fmSplot.getNodeByID(bv.getID());
				String ftName = node.getName();
				String separator = ++nbv != bvars.size() ? " | " : "";
				fla.append(neg + ftName + separator);				
			}
			fla.append(")");
			sbvs.add(fla.toString() + "");
		}
		
		Set<Expression<String>> ocsts = fm.getFm().getConstraints();
		for (Expression<String> exp : ocsts) {
			Expression<String> cnf = FMLExpressionUtil.toCNF(exp);
			String ocst = cnf.toString();
			if (!sbvs.contains(ocst)) {
				assertTrue("NO ==> " + cnf, false); // fail
			}
		}
		
		Formula<String> splotFla = fm.getSPLOTFormulaAligned(_builder);
		// serialize
		FormulaBDDSerializer.save(SPLOT_FLA_LOCATION, splotFla); 
		double c2 = countingFormula(splotFla);
		System.err.println("(splot) #" + c2);
		
		Formula<String> oFla = fm.getFormula();	
		// serialize
		FormulaBDDSerializer.save(ORIGINAL_FLA_LOCATION, oFla); 
		double c1 = countingFormula(oFla);
		System.err.println("(normal) #" + c1);
		
		
		System.err.println("...diffing...");
		Formula<String> diffFla = FMLMergerBDD.diff(oFla, splotFla, _builder);
		assertNotNull(diffFla);
		FormulaBDDSerializer.save(DIFF_FLA_LOCATION, diffFla); 
		
		Set<String> diffFlaDomain = diffFla.getDomain() ;
		StringBuilder sbDiff = new StringBuilder();
		for (String diffFlaFt : diffFlaDomain) {
			sbDiff.append(diffFlaFt + "\n");
		}
		FileSerializer.write(DIFF_FLA_DOMAIN_LOCATION, sbDiff.toString());
		
		assertTrue("no difference", diffFla.isZero());
		
		System.err.println("...equality...");
		assertFormulaTunedEquals(oFla, splotFla);
		
		
		
		
		
		
		assertEquals(c1, c2, 0);
	
		//assertFormulaEquals(oFla, splotFla);
			
	}
	
	
	@Test
	public void checkBDDEquality() throws Exception {
		BDD oBDD = _builder.getFactory().load(ORIGINAL_FLA_LOCATION);
		BDD splotBDD = _builder.getFactory().load(SPLOT_FLA_LOCATION);
		assertBDDTunedEquals(oBDD, splotBDD);
	}
	
	@Test
	public void loadDiffBDD() throws Exception {

		BDD diffBDD = _builder.getFactory().load(DIFF_FLA_LOCATION);
		Set<String> diffDomain = new HashSet<String>();
		
		File fileFlaDomain = new File(DIFF_FLA_DOMAIN_LOCATION);
		BufferedReader br = new BufferedReader(new FileReader(fileFlaDomain));
		String r = null;
		while ((r = br.readLine()) != null) {
			diffDomain.add(r);
		}
			
		Formula<String> diffFla = new Formula<String>(diffBDD, diffDomain, _builder);
		Set<Set<String>> diffConfigs = new AllConfigsBDD(_builder).process(diffFla);
		System.err.println("diffConfigs=" + diffConfigs);
		
	}
	
	
	
	@Test
	public void testSerializeECSA() throws Exception {
		String OUTPUT_FOLDER = "output/ECSA/";
		
		FeatureModelVariable fm150 = getFM150();
		FileSerializer.write(OUTPUT_FOLDER + "fmArch150.fmprimitives", ConvertAnalyzer.convert(fm150, FMFormat.S2T2));
		FileSerializer.write(OUTPUT_FOLDER + "fmArch150.m", ConvertAnalyzer.convert(fm150, FMFormat.FIDE));
		FileSerializer.write(OUTPUT_FOLDER + "fmArch150.fml", ConvertAnalyzer.convert(fm150, FMFormat.FFML));
		
		FeatureModelVariable fmvMerle = getFMMerleWithConstraintsEnhanced();
		FileSerializer.write(OUTPUT_FOLDER + "fmMerle.fmprimitives", ConvertAnalyzer.convert(fmvMerle, FMFormat.S2T2));
		FileSerializer.write(OUTPUT_FOLDER + "fmMerle.m", ConvertAnalyzer.convert(fmvMerle, FMFormat.FIDE));
		
		
		FeatureModelVariable fmPlugin = getFMPlugin();
		FileSerializer.write(OUTPUT_FOLDER + "fmPlugin.fmprimitives", ConvertAnalyzer.convert(fmPlugin, FMFormat.S2T2));
		FileSerializer.write(OUTPUT_FOLDER + "fmPlugin.m", ConvertAnalyzer.convert(fmPlugin, FMFormat.FIDE));
		FileSerializer.write(OUTPUT_FOLDER + "fmPlugin.fml", ConvertAnalyzer.convert(fmPlugin, FMFormat.FFML));
				
		FeatureModelVariable fmFull = getFMFull() ; 
		FileSerializer.write(OUTPUT_FOLDER + "fmFull.fmprimitives", ConvertAnalyzer.convert(fmFull, FMFormat.S2T2));
		FileSerializer.write(OUTPUT_FOLDER + "fmFull.m", ConvertAnalyzer.convert(fmFull, FMFormat.FIDE));
		FileSerializer.write(OUTPUT_FOLDER + "fmFull.fml", ConvertAnalyzer.convert(fmFull, FMFormat.FFML));
		
		
			
		FeatureModelVariable fmArch = loadFM150SerializedEnforcedLazy();
		fmArch.cleanup();
		FileSerializer.write(OUTPUT_FOLDER + "fmArch.fmprimitives", ConvertAnalyzer.convert(fmArch, FMFormat.S2T2));
		FileSerializer.write(OUTPUT_FOLDER + "fmArch.m", ConvertAnalyzer.convert(fmArch, FMFormat.FIDE));
		
	
	}
	
	
	@Test
	public void testProjectionECSA() throws Exception {
	
		FeatureModelVariable fmArch = FM("fmArch", "FM ( Arch : Ar1 Ar2; Ar1: (Ar3|Ar4); Ar2 : (Ar5|Ar6); )");
		FeatureModelVariable fmPlugin = FM("fmPlugin", "FM ( Plugin : (Pl1|Pl2|Pl3)+ ; Pl1 -> Pl2 ; )");
		System.err.println("[[fmArch]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArch));
		
		_shell.parse("fmFull = aggregate { fmArch fmPlugin } withMapping constraints (Ar3 -> Pl1 ; Pl2 -> Ar5; )");
		FeatureModelVariable fmFull = getFMVariable("fmFull");
		assertTrue(fmFull.renameFeature("fmFull", "FtAggregation"));
		_shell.parse("fmArchEnforced = slice fmFull including fmArch.*");
		System.err.println("fmFull=" + fmFull);
		System.err.println("[[fmFull]]=" + FMLSlicerUtilityTest.prettyConfigs(fmFull));
		
		FeatureModelVariable fmArchEnforced = getFMVariable("fmArchEnforced");
		
		System.err.println("fmArchEnforced=" + fmArchEnforced);
		System.err.println("[[fmArchEnforced]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArchEnforced));
		
	}
	
	@Test
	public void testProjectionECSA1() throws Exception {
	
		FeatureModelVariable fmArch = FM("fmArch", "FM ( Arch : Ar1 Ar2; Ar1: (Ar3|Ar4); Ar2 : (Ar5|Ar6); )");
		FeatureModelVariable fmPlugin = FM("fmPlugin", "FM ( Plugins : [Pl1] [Pl2] [Pl3] ; Pl1 -> Pl2 ; )");
		System.err.println("[[fmArch]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArch));
		
		_shell.parse("fmFull = aggregate { fmArch fmPlugin } withMapping constraints (Ar3 -> Pl1 ; Pl2 -> Ar5; )");
		FeatureModelVariable fmFull = getFMVariable("fmFull");
		assertTrue(fmFull.renameFeature("fmFull", "Full"));
		_shell.parse("fmArchEnforced = slice fmFull including fmArch.*");
		System.err.println("fmFull=" + fmFull);
		System.err.println("[[fmFull]]=" + FMLSlicerUtilityTest.prettyConfigs(fmFull));
		
		FeatureModelVariable fmArchEnforced = getFMVariable("fmArchEnforced");
		
		System.err.println("fmArchEnforced=" + fmArchEnforced);
		System.err.println("[[fmArchEnforced]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArchEnforced));
		
	}
	
	@Test
	public void testProjectionECSA2() throws Exception {
	
		FeatureModelVariable fmArch = FM("fmArch", "FM ( Arch : Ar1 Ar2 [Ar7]; Ar1: (Ar3|Ar4); Ar2 : (Ar5|Ar6); (Ar3 & Ar5) -> Ar7 ; )");
		FeatureModelVariable fmPlugin = FM("fmPlugin", "FM ( Plugin : [Pl1] [Pl2] [Pl3] ; Pl1 -> Pl2 ; )");
		System.err.println("[[fmArch]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArch));
		
		_shell.parse("fmFull = aggregate { fmArch fmPlugin } withMapping constraints (Ar3 -> Pl1 ; Pl2 -> Ar5; )");
		FeatureModelVariable fmFull = getFMVariable("fmFull");
		assertTrue(fmFull.renameFeature("fmFull", "Full"));
		_shell.parse("fmArchEnforced = slice fmFull including fmArch.*");
		System.err.println("fmFull=" + fmFull);
		System.err.println("[[fmFull]]=" + FMLSlicerUtilityTest.prettyConfigs(fmFull));
		
		FeatureModelVariable fmArchEnforced = getFMVariable("fmArchEnforced");
		
		System.err.println("fmArchEnforced=" + fmArchEnforced);
		System.err.println("[[fmArchEnforced]]=" + FMLSlicerUtilityTest.prettyConfigs(fmArchEnforced));
		
	}
	
	


 }
