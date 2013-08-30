package fr.unice.polytech.modalis.familiar.test.heuristics;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.DirectedPathLengthMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.TransitiveReductionMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerDB;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public abstract class KSynthesisTest extends FMLTest {

	
	private static final String SPLOT_FOLDER = "inputFML/splot-models-2012-08-07";
	private static final String VARICELL_FMS_FOLDER = "inputFML/wikipedia-comparison-tables";
	private static final String FASE_FOLDER = "inputFML/FASE13-generated-models";
	private static final String MODIFIED_SPLOT_FOLDER = "inputFML/splot-modified";
	

	private static final String WORDNET_DB = "/udd/gbecan/Documents/workspaces/workspace/Heuristics/resources/wordnet_properties.xml";
	private static final String WIKIPEDIA_DB = "/local/wikipedia/WikipediaMiner/db_wikipedia/wikipedia-template.xml";
	private static final String WIKTIONARY_DB = "/local/wikipedia/WikipediaMiner/db_wiktionary/wikipedia-template.xml";
	private static final String LSA_DB = "C:\\db_wikipedia\\wikipedia-template.xml";

	private static final String OUTPUT_FOLDER = "output/generated-with-heuristics/";

	protected static int RANDOM_ITERATIONS = 1;

	private static WikipediaMinerDB wikipediaDB;
	private static WikipediaMinerDB wiktionaryDB;
	
	public static List<FeatureSimilarityMetric> metrics;
	protected static WikipediaMinerMetric wikiMetric;
	protected static WikipediaMinerMetric wiktionaryMetric;
	protected static FeatureSimilarityMetric pathLength;
	protected static FeatureSimilarityMetric levenshtein;
	protected static FeatureSimilarityMetric smithWaterman;
	protected static TransitiveReductionMetric reductionMetric;
	protected static FeatureSimilarityMetric wup;
	protected static DirectedPathLengthMetric directedPathLength;
	
	protected static HashMap<FeatureSimilarityMetric, Double> clusteringThresholds;

	private static List<FeatureModelVariable> splotFMs;
	private static List<FeatureModelVariable> variCellFMs;
	private static List<FeatureModelVariable> faseFMs;
	private static List<FeatureModelVariable> modifiedSplotFMs;
	
	private static final List<String> splotExcludeFiles = Arrays.asList(new String[] {
			// two features with the same name
			"model_20110926_400852996.xml",
			"model_20101112_864228137.xml",
			"model_20110406_353034837.xml",
			"model_20110216_608697455.xml",
			"smart_home_fm.xml",

			"model_20120418_828883554.xml",
			"model_20120408_404695779.xml",
			"model_20091205_755658379.xml",
			"model_20110712_1373520081.xml",
			"model_20111220_1450733948.xml",
			"model_20120422_2100930135.xml",
			"model_20110222_2078967171.xml",
			"model_20110826_1574631601.xml",
			"model_20101208_1056693380.xml",
			"model_20101117_2128796258.xml",
			"model_20100308_1355834007.xml",
			"model_20111029_1654942500.xml",
			"model_20120710_919309758.xml",
			"model_20120130_333619036.xml",
			"model_20110725_452580960.xml",
			"model_20110330_919429247.xml",
			"model_20120328_1412454948.xml",
			"REAL-FM-10.xml",
			"model_20100829_787258873.xml",
			"model_20100326_770562654.xml",
			"model_20091225_1547989376.xml",
			"model_20110310_1849309646.xml",
			"model_20120201_1444061231.xml",
			"model_20100106_985255768.xml",
			"model_20110704_328391695.xml",
			"REAL-FM-17.xml",
			"model_20100831_1378118837.xml",
			"model_20120529_2101702978.xml",
			"model_20120425_1998125226.xml",
			"model_20101115_2000504462.xml",
			"model_20100730_1366577700.xml",
			"model_20101020_1809093990.xml",
			"model_20120717_770767926.xml",
			"model_20100415_947132043.xml",
			"model_20110407_1283128166.xml",
			"model_20120328_288933955.xml",
			"REAL-FM-1.xml",
			"model_20100325_298677687.xml",
			"model_20100927_1382418986.xml",
			"model_20120510_220834497.xml",
			"model_20101116_1022976130.xml",
			"model_20110715_945252556.xml",
			"model_20110406_656545830.xml",
			"model_20120328_1814590478.xml",
			"REAL-FM-11.xml",
			"model_20120328_794090001.xml",
			"model_20120328_1227899142.xml",
			"model_20110919_127780100.xml",
			"model_20100415_240595643.xml",
			"REAL-FM-16.xml",
			"model_20120205_24117969.xml",
			"model_20120111_1667229430.xml",
			"model_20100830_561967343.xml",
			"model_20120424_1703152596.xml",
			"model_20110323_789959080.xml",

			// cannot compute the implication graph
			"model_20110301_216655728.xml",
			"model_20120725_1460954667.xml",
			"model_20110516_1331478109.xml",
			"REAL-FM-4.xml", // too big

			// not in english
			"model_20120328_573707444.xml",
			"model_20110826_1555954181.xml",
			"model_20110826_252647654.xml",
			"model_20110926_608554224.xml",
			"model_20120419_1397354511.xml",
			"model_20100416_811483774.xml",
			"model_20120422_1648870831.xml",
			"model_20120201_899753062.xml",
			"model_20111031_386778867.xml",
			"model_20120328_1357539597.xml",
			"model_20111220_1184087779.xml",
			"model_20100607_746327867.xml",
			"model_20120229_1103715736.xml",
			"model_20110401_868452735.xml",
			"model_20120328_663906927.xml",
			"model_20110329_10104623.xml",
			"model_20120328_523540818.xml",
			"model_20120201_865979127.xml",
			"model_20120515_1862354569.xml",
			"model_20110925_62365838.xml",
			"model_20120122_258977494.xml",
			"model_20110826_819334011.xml",
			"model_20100904_330653656.xml",
			"model_20120609_84195762.xml",
			"model_20110826_1180494500.xml",
			"model_20120705_77954373.xml",
			"model_20110826_1744501275.xml",
			"model_20120328_1667195252.xml",
			"model_20110809_1799855518.xml",
			"model_20120513_373795895.xml",
			"model_20101117_1571856147.xml",
			"model_20120328_361613983.xml",
			"model_20110718_866950306.xml",
			"model_20120423_1625073325.xml",
			"model_20091219_494647199.xml",
			"model_20110507_674768061.xml",
			"model_20120512_712347860.xml",
			"model_20120328_1974817485.xml",
			"model_20100321_1548012375.xml",
			"model_20120113_1803352101.xml",
			"model_20101109_1698100628.xml",
			"model_20111111_1026730216.xml",
			"model_20120423_1196408273.xml",
			"model_20120328_593032745.xml",
			"model_20110823_553386338.xml",
			"model_20110130_639381749.xml",

			// useless feature names (like F1, F2 or A, B, C)
			"model_20120701_1498696792.xml",
			"model_20120718_813832704.xml",
			"model_20111027_1380540076.xml",
			"model_20111129_1932950448.xml",
			"model_20120314_1438691117.xml",
			"model_20111027_966072474.xml",
			"model_20101123_920943759.xml",
			"model_20120701_899073101.xml",
			"model_20110704_555218409.xml",
			"model_20110216_1027109687.xml",
			"model_20101124_661702924.xml",
			"model_20110519_503436691.xml",
			"model_20120412_1314362396.xml",
			"model_20120801_1784537083.xml",
			"model_20110915_1159959623.xml",
			
			// FASE'13
			
			// too long (more than 900 000 products)
			"model_20111201_1252018702.xml",
			"model_20120522_87989914.xml",
			"model_20091009_1552375070.xml",
			"arcade_game_pl_fm.xml",
			"model_20111201_372659565.xml",
			"model_20111130_1614470655.xml",
			"model_20110116_381192414.xml",
			"REAL-FM-3.xml",
			"model_20100904_226530663.xml",
			"model_20120329_788560154.xml",
			"REAL-FM-18.xml",
			"model_20101024_1938793748.xml",
			
			// parsing error
			"model_20110527_1847306763.xml",
			"model_20110207_1623721536.xml",
			
			// missing features in the result
			"DELL-LAPTOP-NOTEBOOK-FM.xml",
			
			
			""
	});
	
	private static final List<String> varicellExcludeFiles = Arrays.asList(new String[] {
			"Comparison_of_AMD_CPU_microarchitectures.fmlbdd",
			"Comparison_of_antivirus_software.fmlbdd",
			"Comparison_of_application_virtual_machines.fmlbdd",
			"Comparison_of_audio_synthesis_environments.fmlbdd",
			"Comparison_of_defragmentation_software.fmlbdd",
			"Comparison_of_DEX_software.fmlbdd",
			"Comparison_of_distributed_file_systems.fmlbdd",
			"Comparison_of_free_software_eCommerce_web_application_frameworks.fmlbdd",
//			"Comparison_of_HTML_editors.fmlbdd",
			"Comparison_of_Java_virtual_machines.fmlbdd",
			"Comparison_of_photo_gallery_software.fmlbdd",
			"Comparison_of_PSA_systems.fmlbdd",
			"Comparison_of_Skype_recorders.fmlbdd",
			"Comparison_of_SSH_clients.fmlbdd",
			"Comparison_of_Toyota_hybrids.fmlbdd",
			"Comparison_of_US_and_Chinese_Military_Armed_Forces.fmlbdd",
			"Comparison_of_XML_editors.fmlbdd",
			""
			
	});
	
	@AfterClass
	public static void tearDownMetrics() {
		if (wikipediaDB != null) {
			wikipediaDB.closeDatabase();	
		}
		if (wiktionaryDB != null) {
			wiktionaryDB.closeDatabase();
		}
	}
	
	@BeforeClass
	public static void setUpMetrics() throws Exception {
		// Set up databases
		if (new File(WIKIPEDIA_DB).exists()) {
			wikipediaDB = new WikipediaMinerDB(WIKIPEDIA_DB);
			wikipediaDB.loadDatabase(); // Must be closed
		}
		
		if (new File(WIKTIONARY_DB).exists()) {
			wiktionaryDB = new WikipediaMinerDB(WIKTIONARY_DB);
			wiktionaryDB.loadDatabase(); // Must be closed
		}
		
		
		// Set up metrics
		metrics = new ArrayList<FeatureSimilarityMetric>();
		clusteringThresholds = new HashMap<FeatureSimilarityMetric, Double>();

//		// Random metric
//		FeatureSimilarityMetric random = new RandomMetric();
//		metrics.add(random);
//		clusteringThresholds.put(random, 0.15);
//
//		// Simmetrics metrics
//		smithWaterman = new SimmetricsMetric(MetricName.SIMMETRICS_SMITHWATERMAN);
//		metrics.add(smithWaterman);
//		clusteringThresholds.put(smithWaterman, 0.6);
//
//		levenshtein = new SimmetricsMetric(MetricName.SIMMETRICS_LEVENSHTEIN);
//		metrics.add(levenshtein);
//		clusteringThresholds.put(levenshtein, 0.7);
//
//		// WordNet metrics
//		if (new File(WORDNET_DB).exists()) {
//			Dictionary wordNetDictionary = Dictionary.getInstance(new FileInputStream(WORDNET_DB));
//			wup = new WuPalmerMetric(wordNetDictionary);
//			metrics.add(wup);
//			clusteringThresholds.put(wup, 0.2);
//
//			pathLength = new PathLengthMetric(wordNetDictionary); 
//			metrics.add(pathLength);
//			clusteringThresholds.put(pathLength, 0.5);
//			
//			directedPathLength = new DirectedPathLengthMetric(wordNetDictionary);
//			metrics.add(directedPathLength);
//			clusteringThresholds.put(directedPathLength, 0.5);
//		}
//
//		// WikipediaMiner metrics
//		if (wikipediaDB.isLoaded()) {
//			wikiMetric = new WikipediaMinerMetric(wikipediaDB);
//			metrics.add(wikiMetric);
//			clusteringThresholds.put(wikiMetric, 0.5);	
//		}
//		
//		if (wiktionaryDB.isLoaded()) {
//			wiktionaryMetric = new WikipediaMinerMetric(wiktionaryDB);
//			metrics.add(wiktionaryMetric);
//			clusteringThresholds.put(wiktionaryMetric, 0.5);	
//		}
//
//		// LSA metric
//		if (wikipediaDB.isLoaded()) {
//			LatentSemanticMetric lsa = new LatentSemanticMetric(wikipediaDB);
//			metrics.add(lsa);
//			clusteringThresholds.put(lsa, 0.5);
//		}
		
		// Transitive reduction metric
		reductionMetric = new TransitiveReductionMetric();
		metrics.add(reductionMetric);
		clusteringThresholds.put(reductionMetric, 0.5);
		
	}

	@After
	public void prettyPrint() {
		System.out.println("-----");
	}
	
	// ----- GET FEATURE MODELS -----

	/**
	 * Load all feature models from a specified folder
	 * @param folder
	 * @param extension : file extension
	 * @param excludeFiles
	 * @param prefix : prefix of FMs' identifier
	 * @return
	 */
	public List<FeatureModelVariable> loadFeatureModelFolder(
			String folder, final String extension, final List<String> excludeFiles, String prefix) {
		
		if (_shell == null) {
			_shell = FMLShell.instantiateStandalone(null);
		}
		

		ArrayList<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		
		File fmsFolder = new File(folder);

		if (fmsFolder.exists() && fmsFolder.isDirectory()) {
			
			// Filter files
			File[] files = fmsFolder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(extension) && (excludeFiles == null || !excludeFiles.contains(name));// && name.equals("model_20120110_1256867454.xml");
				}
			});

			// Load feature models
			for (File file : files) {
				String fmIdentifier = prefix +"_" + file.getName();
				fmIdentifier = fmIdentifier.substring(0, fmIdentifier.lastIndexOf("."));
				String command = fmIdentifier.replaceAll("-", "_") + " = FM(\"" + file.getPath() + "\")";
				FeatureModelVariable fm = (FeatureModelVariable) _shell.parse(command);

				if (fm == null) {
					System.out.println("Error with " + file.getPath());
				} else {
					fm.setIdentifier(fmIdentifier);
					fms.add(fm);	
				}
				
			}
		}
		
		return fms;
	}
	
	public List<FeatureModelVariable> getSPLOTFeatureModels()  {
		if (splotFMs == null) {
			splotFMs = loadFeatureModelFolder(SPLOT_FOLDER, ".xml", splotExcludeFiles, "splotFM");
			System.out.println(splotFMs.size() + " FMs loaded from SPLOT");
		}

		return splotFMs;
	}


	public List<FeatureModelVariable> getModifiedSPLOTFeatureModels() {
		if (modifiedSplotFMs == null) {
			modifiedSplotFMs = loadFeatureModelFolder(MODIFIED_SPLOT_FOLDER, ".xml", null, "modified");
			System.out.println(modifiedSplotFMs.size() + " FMs loaded from modified SPLOT");
		}

		return modifiedSplotFMs;
	}
	
	public List<FeatureModelVariable> getVariCellFeatureModels() {
//		if (variCellFMs == null) {
			variCellFMs = loadFeatureModelFolder(VARICELL_FMS_FOLDER, ".fmlbdd", varicellExcludeFiles, "varicellFM");
			System.out.println(variCellFMs.size() + " FMs loaded from VariCell");
//		}

		return variCellFMs;
	}
	
	
	public List<FeatureModelVariable> getRunningExample() {
		
		if (_shell == null) {
			_shell = FMLShell.instantiateStandalone(null);
			_environment = _shell.getCurrentEnv();
			_builder = FMLCommandInterpreter.getBuilder();
		}
		
		ArrayList<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		
		try {
			
			FeatureModelVariable runningExample = FM ("fm1bis", 
					" Wiki: Hosting Licence Storage [\"Programming Language\"] ; \n" + 
					"Hosting: (\"Hosted Service\"|Local) ; \n" + 
					"Licence: (\"Proprietary Licence\"|\"Open Source\") ; \n" + 
					"Storage: (PostgreSQL|MySQL) ; \n" + 
					"\"Programming Language\": (Java|PHP) ; \n" + 
					"\"Hosted Service\": [Domain] ; \n" + 
					"(\"Proprietary Licence\" -> !\"Programming Language\");\n" + 
					"(Local -> !\"Proprietary Licence\");\n" + 
					"(PostgreSQL <-> \"Proprietary Licence\");" +
					"PostgreSQL -> Domain ;" +
					//"\"Open Source\" -> MySQL ; " +
					//"PHP -> MySQL ; " +
					//"Java -> MySQL ; " +
					//"\"Proprietary Licence\" -> !MySQL ; " +  
					"" +
					"" +
					""
					);
			fms.add(runningExample);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fms;
	}
	
	public List<FeatureModelVariable> getFASEFeatureModels() {
		if (faseFMs == null) {
			faseFMs = loadFeatureModelFolder(FASE_FOLDER, ".xml", splotExcludeFiles, "faseFM");
			System.out.println(faseFMs.size() + " FMs loaded from FASE");
		}
		
		return faseFMs;
	}
	
	public List<FeatureModelVariable> getPerfectExample() {

		if (_shell == null) {
			_shell = FMLShell.instantiateStandalone(null);
			_environment = _shell.getCurrentEnv();
			_builder = FMLCommandInterpreter.getBuilder();
		}

		ArrayList<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();

		try {

			FeatureModelVariable runningExample = FM ("software",
//					"Software: \"Operating System\" Application Compiler Debugger; \n" +
//							"\"Operating System\": Unix Windows; \n" +
//							"Application: \"Web browser\" Applet \"Word processor\"; \n" +
//							"Compiler: \"C compiler\" \"Fortran compiler\"; \n" +
//							""
					"Software: \"Operating System\" Program; \n" +
					"\"Operating System\": Unix Windows; \n" +
					"Program: Compiler Application Debugger; \n" +
					"Application: \"Web browser\" Applet \"Word processor\"; \n" +
					"Compiler: \"C compiler\" \"Fortran compiler\"; \n" +
					""
					);
			fms.add(runningExample);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fms;
	}
	
	public List<FeatureModelVariable> getFASEExample() {
		
		if (_shell == null) {
			_shell = FMLShell.instantiateStandalone(null);
			_environment = _shell.getCurrentEnv();
			_builder = FMLCommandInterpreter.getBuilder();
		}
		
		ArrayList<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		
		try {
			
			FeatureModelVariable faseExample = FM ("fase_example", 
					"\"Cell Phone\": Games Display \"Accu Cell\" [\"Wireless\"] ; \n" +
					"Wireless: (Infrared | Bluetooth)+; \n" +
					"\"Accu Cell\": (Strong | Medium | Weak); \n" +
					"Games: (\"Multi Player\" | \"Single Player\")+; \n" +
					"\"Single Player\": \"Artificial Opponent\"; \n" +
					"(\"Multi Player\" -> Wireless); \n" +
					"(\"Multi Player\" -> !Weak); \n" +
					"(Bluetooth -> Strong); \n" +
					""
					);
			fms.add(faseExample);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fms;
	}
	
	/**
	 * Save the fm in a SPLOT file
	 * @param metric
	 * @param fm
	 * @param computedFM
	 */
	public void writeFMToFile(FeatureSimilarityMetric metric, FeatureModelVariable fm, FeatureModelVariable computedFM) {
		File computedFMFile ;
		if (metric == null) {
			computedFMFile = new File(OUTPUT_FOLDER + fm.getIdentifier() + ".xml");
		} else {
			computedFMFile = new File(OUTPUT_FOLDER + fm.getIdentifier() + "_" + metric.toString().replaceAll(" ", "_") + ".xml");	
		}
		
		try {
			PrintStream standardOutput = System.out;
			PrintStream fileOutput = new PrintStream(computedFMFile);
			System.setOut(fileOutput); // Hack for this stupid dumpXML function
			computedFM.toSPLOT().dumpXML();
			System.setOut(standardOutput);
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Find the corresponding FM in the list of reference FMs
	 * @param fm
	 * @param referenceFMs
	 * @return
	 */
	public FeatureModelVariable findReferenceFM(FeatureModelVariable fm, List<FeatureModelVariable> referenceFMs) {
		String name = fm.getCompleteIdentifier();
		name = name.substring(name.indexOf("_")+1);
		
		for (FeatureModelVariable referenceFM : referenceFMs) {
			if (referenceFM.getCompleteIdentifier().endsWith(name)) {
				return referenceFM;
			}
		}
		
		return null;
	}
	
	
	/**
	 * Get common edges of fm1 and fm2
	 * @param fm1
	 * @param fm2
	 * @return list of common edges between fm1 and fm2
	 */
	public List<String> getCommonEdges(FeatureModelVariable fm1, FeatureModelVariable fm2) {
		List<String> commonEdges = new ArrayList<String>();
		
		FeatureGraph<String> hierarchy1 = fm1.getFm().getDiagram();
		FeatureGraph<String> hierarchy2 = fm2.getFm().getDiagram();

		for (FeatureEdge edge : hierarchy1.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY) {
				String source1 = hierarchy1.getSource(edge).getFeature();
				String target1 = hierarchy1.getTarget(edge).getFeature();
				FeatureNode<String> source2 = hierarchy2.findVertex(source1);
				FeatureNode<String> target2 = hierarchy2.findVertex(target1);
				if (hierarchy2.containsEdge(source2, target2, FeatureEdge.HIERARCHY)) {
					commonEdges.add(source1 + " -> " + target1);// + " : " + metric.similarity(fm1.computeImplicationGraph(), source1, target1));
				}
			}
		}
		
		return commonEdges;
	}
}
