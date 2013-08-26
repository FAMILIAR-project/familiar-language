package fr.unice.polytech.modalis.familiar.test.heuristics;


import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.extjwnl.dictionary.Dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.modalis.familiar.gui.synthesis.KeyValue;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.DirectedPathLengthMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FMEditDistanceMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.LatentSemanticMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.PathLengthMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RefactoringEditDistance;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.TransitiveReductionMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerDB;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WuPalmerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ZhangEditDistance;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.TransitiveReduction;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FGBuilder;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class ASE2013KSynthesisTest extends FMLTest {

	private static final String SPLOT_FOLDER = "inputFML/splot-models-2012-08-07";
	private static final String VARICELL_FMS_FOLDER = "inputFML/wikipedia-comparison-tables";
	private static final String FASE_FOLDER = "inputFML/FASE13-generated-models";
	private static final String MODIFIED_SPLOT_FOLDER = "inputFML/splot-modified";
	

	private static final String WORDNET_DB = "/udd/gbecan/Documents/workspaces/workspace/Heuristics/resources/wordnet_properties.xml";
	private static final String WIKIPEDIA_DB = "/local/wikipedia/WikipediaMiner/db_wikipedia/wikipedia-template.xml";
	private static final String WIKTIONARY_DB = "/local/wikipedia/WikipediaMiner/db_wiktionary/wikipedia-template.xml";
	private static final String LSA_DB = "C:\\db_wikipedia\\wikipedia-template.xml";

	private static final String OUTPUT_FOLDER = "output/generated-with-heuristics/";

	private static int RANDOM_ITERATIONS = 1;

	private static WikipediaMinerDB wikipediaDB;
	private static WikipediaMinerDB wiktionaryDB;
	
	private static List<FeatureSimilarityMetric> metrics;
	private static WikipediaMinerMetric wikiMetric;
	private static WikipediaMinerMetric wiktionaryMetric;
	private static FeatureSimilarityMetric pathLength;
	private static FeatureSimilarityMetric levenshtein;
	private static FeatureSimilarityMetric smithWaterman;
	private static TransitiveReductionMetric reductionMetric;
	
	private static HashMap<FeatureSimilarityMetric, Double> clusteringThresholds;

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
	private static FeatureSimilarityMetric wup;
	private static DirectedPathLengthMetric directedPathLength;
	

	
	// ----- SET UP TEST -----
	
	
	
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

		// Random metric
		FeatureSimilarityMetric random = new RandomMetric();
		metrics.add(random);
		clusteringThresholds.put(random, 0.15);

		// Simmetrics metrics
		smithWaterman = new SimmetricsMetric(MetricName.SIMMETRICS_SMITHWATERMAN);
		metrics.add(smithWaterman);
		clusteringThresholds.put(smithWaterman, 0.6);

		levenshtein = new SimmetricsMetric(MetricName.SIMMETRICS_LEVENSHTEIN);
		metrics.add(levenshtein);
		clusteringThresholds.put(levenshtein, 0.7);

		// WordNet metrics
		if (new File(WORDNET_DB).exists()) {
			Dictionary wordNetDictionary = Dictionary.getInstance(new FileInputStream(WORDNET_DB));
			wup = new WuPalmerMetric(wordNetDictionary);
			metrics.add(wup);
			clusteringThresholds.put(wup, 0.2);

			pathLength = new PathLengthMetric(wordNetDictionary); 
			metrics.add(pathLength);
			clusteringThresholds.put(pathLength, 0.5);
			
			directedPathLength = new DirectedPathLengthMetric(wordNetDictionary);
			metrics.add(directedPathLength);
			clusteringThresholds.put(directedPathLength, 0.5);
		}

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

//		// LSA metric
//		if (wikipediaDB.isLoaded()) {
//			LatentSemanticMetric lsa = new LatentSemanticMetric(wikipediaDB);
//			metrics.add(lsa);
//			clusteringThresholds.put(lsa, 0.5);
//		}
		
//		// Transitive reduction metric
//		reductionMetric = new TransitiveReductionMetric();
//		metrics.add(reductionMetric);
//		clusteringThresholds.put(reductionMetric, 0.5);

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
					return name.endsWith(extension) && (excludeFiles == null || !excludeFiles.contains(name));
				}
			});

			// Load feature models
			for (File file : files) {
				String fmIdentifier = prefix +"_" + file.getName().replaceAll("-", "_");
				fmIdentifier = fmIdentifier.substring(0, fmIdentifier.lastIndexOf("."));
				String command = fmIdentifier + " = FM(\"" + file.getPath() + "\")";
				FeatureModelVariable fm = (FeatureModelVariable) _shell.parse(command);
				if (fm == null) {
					System.out.println("Error with " + file.getPath());
				} else {
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
		if (variCellFMs == null) {
			variCellFMs = loadFeatureModelFolder(VARICELL_FMS_FOLDER, ".fmlbdd", null, "varicellFM");
			System.out.println(variCellFMs.size() + " FMs loaded from VariCell");
		}

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
	
	private List<FeatureModelVariable> getFASEFeatureModels() {
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
					"Software: \"Operating System\" Application Compiler Debugger; \n" +
					"\"Operating System\": Unix Windows; \n" +
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
	
	// ----- TESTS -----
	
	private void testBIGDegree(List<FeatureModelVariable> fms) {
		ImplicationGraphMetrics graphMetrics = new ImplicationGraphMetrics();
		List<Integer> minOutDegrees = new ArrayList<Integer>();
		List<Integer> maxOutDegrees = new ArrayList<Integer>();
		List<Double> meanOutDegrees = new ArrayList<Double>();
		double sumDegrees = 0;
		double nbFeatures = 0;

		for (FeatureModelVariable fm : fms) {
			ImplicationGraph<String> implicationGraph = fm.computeImplicationGraph();

			for (String vertex : implicationGraph.vertices()) {
				sumDegrees += implicationGraph.outDegreeOf(vertex);
				nbFeatures++;
			}

			int minOutdegree = graphMetrics.minOutdegree(implicationGraph);
			minOutDegrees.add(minOutdegree);
			int maxOutdegree = graphMetrics.maxOutdegree(implicationGraph);
			maxOutDegrees.add(maxOutdegree);
			double meanOutdegree = graphMetrics.meanOutdegree(implicationGraph);
			meanOutDegrees.add(meanOutdegree);
		} 

		Collections.sort(minOutDegrees);
		System.out.println("min : " + minOutDegrees);
		Collections.sort(maxOutDegrees);
		System.out.println("max : " + maxOutDegrees);
		Collections.sort(meanOutDegrees);
		System.out.println("mean : " + meanOutDegrees);
		System.out.println("mean degree : " + sumDegrees/nbFeatures);
	}

	private void testTopN(List<FeatureModelVariable> fms, int n) {

		int nbOfFeatures = 0;

		for (FeatureModelVariable fm : fms) {
			nbOfFeatures += fm.features().size()-1; // Avoid the root feature
		}
		System.out.println("Nb of features : " + nbOfFeatures);

		for (FeatureSimilarityMetric metric : metrics) {
			double sumNbOfFeaturesInTopN = 0;
			int nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1; 

			for (int i=0; i<nbIterations; i++) {
				double nbOfFeaturesInTopNIter = 0;
				
				for (FeatureModelVariable fm : fms) {
				
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(
							fm, metric, new ArrayList<FeatureSimilarityMetric>(), 
							null, 0);

					List<KeyValue<String,List<String>>> parentCandidateLists = synthesizer.getParentCandidates();
					FeatureGraph<String> hierarchy = fm.getFm().getDiagram();

					for (KeyValue<String, List<String>> parentCandidates : parentCandidateLists) {
						String feature = parentCandidates.getKey();
						List<String> parentCandidatesList = parentCandidates.getValue();
						FeatureNode<String> parent = hierarchy.parents(hierarchy.findVertex(feature)).iterator().next();
						if (!parent.isTop()) {
							int indexOfParent= parentCandidatesList.indexOf(parent.getFeature());

							if (indexOfParent < n) {
								nbOfFeaturesInTopNIter++;
							}
						}
					}
				}

				sumNbOfFeaturesInTopN += nbOfFeaturesInTopNIter;
			}
			double nbOfFeaturesInTopN = sumNbOfFeaturesInTopN / nbIterations;
			System.out.println(metric + " : " + nbOfFeaturesInTopN + " (" + nbOfFeaturesInTopN / ((double) nbOfFeatures) + ")");
		}

	}

	private void testClusters(List<FeatureModelVariable> fms) {

		for (FeatureSimilarityMetric metric : metrics) {
			double threshold = clusteringThresholds.get(metric);

			int totalNbOfFeatures = 0;
			double totalNbClusters = 0;
			int totalNbCorrectClusters = 0;
			int totalNbUndirectlyCorrectClusters = 0;
			int totalNbFeaturesInACluster = 0;
			int totalNbFeaturesInACorrectCluster = 0;
			int totalNbFeaturesInAnUndirectlyCorrectCluster = 0;
			double sumProportionFeaturesInACluster = 0;
			double sumProportionFeaturesInACorrectCluster = 0;
			double sumProportionFeaturesInAnUCorrectCluster = 0;


			double nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1;
			for (int i=0; i<nbIterations; i++) {


				for (FeatureModelVariable fm : fms) {

					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(
							fm, null, new ArrayList<FeatureSimilarityMetric>(), 
							metric, threshold);

					Set<Set<String>> clusters = synthesizer.getSimilarityClusters();
					FeatureGraph<String> hierarchy = fm.getFm().getDiagram();

					int nbFeatures = fm.features().size();
					int nbClusters = 0;
					int nbCorrectClusters = 0;
					int nbUndirectlyCorrectClusters = 0;
					int nbFeaturesInACluster = 0;
					int nbFeaturesInACorrectCluster = 0;
					int nbFeaturesInAnUndirectlyCorrectCluster = 0;

					List<Set<String>> siblingsList = convertInSetOfString(hierarchy.getSiblingSetsInBFS());
					for (Set<String> cluster : clusters) {
						if (cluster.size() > 1) {
							// Check that the cluster is correct
							for (Set<String> siblings : siblingsList) {
								// All the features are siblings
								if (siblings.containsAll(cluster)) {
									nbCorrectClusters++;
									nbFeaturesInACorrectCluster += cluster.size();
									break;
								} 

								boolean undirectlyCorrect = false;
								for (String possibleParent : cluster) {
									// Compute the cluster without a possible parent
									Set<String> reducedCluster = new HashSet<String>(cluster);
									reducedCluster.remove(possibleParent);

									// The features of the reduced cluster are siblings and the chosen parent is correct
									undirectlyCorrect = siblings.containsAll(reducedCluster)
											&& hierarchy.containsEdge(
													hierarchy.findVertex(reducedCluster.iterator().next()), 
													hierarchy.findVertex(possibleParent), 
													FeatureEdge.HIERARCHY);
									if (undirectlyCorrect) {
										break;
									}
								}

								if (undirectlyCorrect) {
									nbUndirectlyCorrectClusters++;
									nbFeaturesInAnUndirectlyCorrectCluster += cluster.size();
									break;
								}

							}

							nbClusters++;
							nbFeaturesInACluster += cluster.size();
						}
					}

					totalNbOfFeatures += nbFeatures;
					totalNbClusters += nbClusters;
					totalNbCorrectClusters += nbCorrectClusters;
					totalNbUndirectlyCorrectClusters += nbUndirectlyCorrectClusters;
					totalNbFeaturesInACluster += nbFeaturesInACluster;
					totalNbFeaturesInACorrectCluster += nbFeaturesInACorrectCluster;
					totalNbFeaturesInAnUndirectlyCorrectCluster += nbFeaturesInAnUndirectlyCorrectCluster;
					sumProportionFeaturesInACluster += nbFeaturesInACluster / ((double) nbFeatures);
					sumProportionFeaturesInACorrectCluster += nbFeaturesInACorrectCluster / ((double) nbFeatures);
					sumProportionFeaturesInAnUCorrectCluster += nbFeaturesInAnUndirectlyCorrectCluster / ((double) nbFeatures);
				}
			}
			System.out.println(metric);
			System.out.println("clusters : " + (totalNbClusters / nbIterations));
			System.out.println("mean cluster size : " + (totalNbFeaturesInACluster / ((double) totalNbClusters)));
			System.out.println("correct clusters : " + (totalNbCorrectClusters / nbIterations));
			System.out.println("undirectly correct clusters : " + (totalNbUndirectlyCorrectClusters / nbIterations));
			System.out.println("features in a cluster : " + (totalNbFeaturesInACluster / nbIterations));
			System.out.println("features in a correct cluster : " + (totalNbFeaturesInACorrectCluster / nbIterations));
			System.out.println("features in a undirectly correct cluster : " + (totalNbFeaturesInAnUndirectlyCorrectCluster / nbIterations));
			System.out.println("features in both type of correct clusters : " + ((totalNbFeaturesInACorrectCluster + totalNbFeaturesInAnUndirectlyCorrectCluster) / nbIterations));
			System.out.println("features in a cluster (average) : " + (sumProportionFeaturesInACluster / fms.size() / nbIterations));
			System.out.println("features in a correct cluster (average) : " + (sumProportionFeaturesInACorrectCluster / fms.size() / nbIterations));
			System.out.println("features in a undirectly correct cluster (average) : " + (sumProportionFeaturesInAnUCorrectCluster / fms.size() / nbIterations));

			System.out.println("correct clusters / cluster : " + (totalNbCorrectClusters + totalNbUndirectlyCorrectClusters) / totalNbClusters);
			System.out.println();
		}

	}

	/**
	 * Convert a list of siblings represented by feature nodes in 
	 * a list of siblings represented by strings
	 * @param siblingSetsInBFS
	 * @return
	 */
	private List<Set<String>> convertInSetOfString(List<Set<FeatureNode<String>>> siblingSetsInBFS) {
		List<Set<String>> siblingsList = new ArrayList<Set<String>>();

		for (Set<FeatureNode<String>> featureNodesiblings : siblingSetsInBFS) {
			Set<String> siblings = new HashSet<String>();
			for (FeatureNode<String> feature : featureNodesiblings) {
				siblings.add(feature.getFeature());
			}
			siblingsList.add(siblings);
		}

		return siblingsList;
	}


	private void testOptimumBranching(List<FeatureModelVariable> fms) {
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		for (FeatureSimilarityMetric metric : metrics) {
			double sumCommonEdgesDistance = 0;
			double sumZhangDistance = 0;
			double sumRefactoringDistance = 0;
			double sumCommonEdgesGlobalDistance = 0;
			double totalNbEdges = 0;
			
			int[] distribution = new int[11];
			
			double nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1;
			for (int i=0; i<nbIterations; i++) {
				for (FeatureModelVariable fm : fms) {

					List<FeatureSimilarityMetric> complementaryHeuristics = new ArrayList<FeatureSimilarityMetric>();
//					complementaryHeuristics.add(pathLength);
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, complementaryHeuristics, null, 0);
					FeatureModelVariable computedFM = synthesizer.computeCompleteFeatureModel();

					// Compute edit distances
					double nbCommonEdges = commonEdgesMetric.commonEdges(computedFM, fm);
//					double nbEdges = computedFM.getFm().getDiagram().edges().size();
					double nbEdges = fm.features().size();
					
					sumCommonEdgesGlobalDistance += nbCommonEdges;
					totalNbEdges += nbEdges;
					double commonEdgesDistance =  nbCommonEdges / nbEdges;
					sumCommonEdgesDistance += commonEdgesDistance;
					distribution[(int) (commonEdgesDistance*10)]++;
					
					double zhangDistance = (zhangDistanceMetric.editDistance(computedFM, fm) / ((double) fm.features().size()));
					sumZhangDistance += zhangDistance;
					double refactoringDistance = (refactoringDistanceMetric.editDistance(computedFM, fm) / ((double) fm.features().size()));
					sumRefactoringDistance += refactoringDistance;
					
					// Write the generated FM in the ouput folder
					File computedFMFile = new File(OUTPUT_FOLDER + fm.getIdentifier() + "_" + metric.toString().replaceAll(" ", "_") + ".xml");
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
			}
			System.out.println(metric);
			System.out.println("common edges for all fms : " + sumCommonEdgesGlobalDistance / totalNbEdges / nbIterations);
			System.out.println("common edges by fm : " + sumCommonEdgesDistance / fms.size() / nbIterations);
			System.out.println("zhang distance : " + sumZhangDistance / fms.size() / nbIterations);
			System.out.println("refactoring distance : " + sumRefactoringDistance / fms.size() / nbIterations);
			System.out.println("distribution : ");
			int sum = 0; 
			for (int i =0; i<11; i++) {
				sum += distribution[i];
				System.out.println(i + " : " + distribution[i] + "\t" + sum);
			}
			System.out.println();
		}

	}
	
	private void testFMClusters(List<FeatureModelVariable> fms) {
		// Distance metrics
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		// Init clusters
		HashMap<FeatureSimilarityMetric, List<FeatureModelVariable>> fmClusters = new HashMap<FeatureSimilarityMetric, List<FeatureModelVariable>>();
		for (FeatureSimilarityMetric metric : metrics) {
			fmClusters.put(metric, new ArrayList<FeatureModelVariable>());
		}
		
		// Init common edges with ground truth
		HashMap<FeatureModelVariable, List<String>> commonEdgesWithGroundTruth = new HashMap<FeatureModelVariable, List<String>>();
		
		// Select best metric for each fm
		for (FeatureModelVariable fm : fms) {

			FeatureSimilarityMetric bestMetric = null;
			double bestMetricDistance = -1;
			List<String> bestCommonEdges = null;
			
			for (FeatureSimilarityMetric metric : metrics) {

				InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
				FeatureModelVariable computedFM = synthesizer.computeCompleteFeatureModel();

				double zhangDistance = (zhangDistanceMetric.editDistance(computedFM, fm) / ((double) fm.features().size()));
				
				double currentMetricDistance = zhangDistance;
				
				if (bestMetric == null || currentMetricDistance < bestMetricDistance) {
					bestMetric = metric;
					bestMetricDistance = currentMetricDistance;
					bestCommonEdges = getCommonEdges(fm, computedFM);
				}

			}
			
			if (bestMetric != null) {
				fmClusters.get(bestMetric).add(fm);
				commonEdgesWithGroundTruth.put(fm, bestCommonEdges);
			}

		}
		
		// Print clusters
		for (FeatureSimilarityMetric metric : fmClusters.keySet()) {
			System.out.println("****** " + metric);
			for (FeatureModelVariable fm : fmClusters.get(metric)) {
				System.out.println(fm.getCompleteIdentifier());
				for (String commonEdge : commonEdgesWithGroundTruth.get(fm)) {
					System.out.println("\t" + commonEdge);
				}
			}
			System.out.println();
		}

	}
	
	private List<String> getCommonEdges(FeatureModelVariable fm1, FeatureModelVariable fm2) {
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
	

	private void testInteractiveSynthesis(List<FeatureModelVariable> fms) {
		FMEditDistanceMetric editDistance = new RefactoringEditDistance();

		for (FeatureSimilarityMetric metric : metrics) {
			System.out.println(metric);
			for (FeatureModelVariable fm : fms) {
				FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
				InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
				FeatureModelVariable computedFM = synthesizer.computeCompleteFeatureModel();

				int steps = 0; 

				// Compute a list of features ordered by number of parent candidates
				List<KeyValue<String, List<String>>> parentCandidates = synthesizer.getParentCandidates();
				List<String> features = new ArrayList<String>();
				for (KeyValue<String, List<String>> parentCandidate : parentCandidates) {
					features.add(parentCandidate.getKey());
				}
				Iterator<String> it = features.iterator();

				while (editDistance.editDistance(computedFM, fm) > 0 && it.hasNext()) {

					if (steps == 0) {
						// Choose the root
						String root = hierarchy.children(hierarchy.getTopVertex()).iterator().next().getFeature();
						synthesizer.setRoot(root);
					} else {
						// Choose a feature's parent
						String child = it.next();
						String parent = hierarchy.parents(hierarchy.findVertex(child)).iterator().next().getFeature();
						synthesizer.selectParent(child, parent);	
					}

					steps++;
					computedFM = synthesizer.computeCompleteFeatureModel();
				}

				System.out.println(fm.getCompleteIdentifier() + " " + steps);
			}	
		}
	}


	@After
	public void prettyPrint() {
		System.out.println("-----");
	}

	@Ignore
	@Test
	public void testBIGDegreeSPLOT() {
		System.out.println("BIG SPLOT");
		testBIGDegree(getSPLOTFeatureModels());
	}

	@Ignore
	@Test
	public void testBIGDegreeVariCell() {
		System.out.println("BIG Varicell");
		testBIGDegree(getVariCellFeatureModels());
	}

	@Ignore
	@Test
	public void testTop2SPLOT() {
		System.out.println("Top 2 SPLOT");
		testTopN(getSPLOTFeatureModels(), 2);
	}

	@Ignore
	@Test
	public void testTop2VariCell() {
		System.out.println("Top 2 VariCell");
		testTopN(getVariCellFeatureModels(), 2);
	}

	@Ignore
	@Test
	public void testClustersSPLOT() {
		System.out.println("Clusters SPLOT");
		testClusters(getSPLOTFeatureModels());
	}

	
	@Ignore
	@Test
	public void testClustersVariCell() {
		System.out.println("Clusters VariCell");
		testClusters(getVariCellFeatureModels());
	}

	@Test
	public void testOptimumBranchingSPLOT() {
		System.out.println("Optimum branching SPLOT");
		testOptimumBranching(getSPLOTFeatureModels());
	}

	@Ignore
	@Test
	public void testOptimumBranchingModifiedSPLOT() {
		System.out.println("Optimum branching modified SPLOT");
		testOptimumBranching(getModifiedSPLOTFeatureModels());
	}
	
	@Ignore
	@Test
	public void testOptimumBranchingVariCell() {
		System.out.println("Optimum branching VariCell");
		testOptimumBranching(getVariCellFeatureModels());
	}
	
	@Ignore
	@Test
	public void testVariCell() {
		List<FeatureModelVariable> variCellFeatureModels = getVariCellFeatureModels();
		testBIGDegree(variCellFeatureModels);
		testTopN(variCellFeatureModels, 2);
		testClusters(variCellFeatureModels);
		testOptimumBranching(variCellFeatureModels);
	}

	@Ignore
	@Test
	public void testRunningExample() throws Exception {
		
		System.out.println("BIG degree");
		testBIGDegree(getRunningExample());
		System.out.println("-----");
		
		System.out.println("TOP 1");
		testTopN(getRunningExample(), 1);
		System.out.println("-----");
		
		System.out.println("TOP 2");
		testTopN(getRunningExample(), 2);
		System.out.println("-----");
		
		System.out.println("Clusters");
		testClusters(getRunningExample());
		System.out.println("-----");
		
		System.out.println("Optimum branching");
		testOptimumBranching(getRunningExample());
		
		
	}
	
	@Ignore
	@Test
	public void testFMClustersSPLOT() {
		System.out.println("Feature model clusters according to their best heuristic");
		testFMClusters(getSPLOTFeatureModels());
	}
	
	@Ignore
	@Test
	public void testFASEAlgorithm() {
		System.out.println("FASE'13 FMs edit distance");
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		double sumCommonEdgesDistance = 0;
		double sumZhangDistance = 0;
		double sumRefactoringDistance = 0;
		double sumCommonEdgesGlobalDistance = 0;
		double totalNbEdges = 0;

		List<FeatureModelVariable> fms = getFASEFeatureModels();
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		int[] distribution = new int[11];

		for (FeatureModelVariable fm : fms) {

			FeatureModelVariable referenceFM = findReferenceFM(fm, referenceFMs);

			double nbCommonEdges = commonEdgesMetric.commonEdges(referenceFM, fm);
//			double nbEdges = computedFM.getFm().getDiagram().edges().size();
			double nbEdges = fm.features().size();
			
			sumCommonEdgesGlobalDistance += nbCommonEdges;
			totalNbEdges += nbEdges;
			double commonEdgesDistance =  nbCommonEdges / nbEdges;
			sumCommonEdgesDistance += commonEdgesDistance;
			distribution[(int) (commonEdgesDistance*10)]++;
			
			double zhangDistance = (zhangDistanceMetric.editDistance(referenceFM, fm) / ((double) fm.features().size()));
			sumZhangDistance += zhangDistance;
			double refactoringDistance = (refactoringDistanceMetric.editDistance(referenceFM, fm) / ((double) fm.features().size()));
			sumRefactoringDistance += refactoringDistance;
		}

		System.out.println("FASE algorithm");
		System.out.println("common edges for all fms : " + sumCommonEdgesGlobalDistance / totalNbEdges);
		System.out.println("common edges by fm : " + sumCommonEdgesDistance / fms.size());
		System.out.println("zhang distance : " + sumZhangDistance / fms.size());
		System.out.println("refactoring distance : " + sumRefactoringDistance / fms.size());
		System.out.println("distribution : ");
		int sum = 0; 
		for (int i =0; i<11; i++) {
			sum += distribution[i];
			System.out.println(i + " : " + distribution[i] + "\t" + sum);
		}
		System.out.println();

	}

	private FeatureModelVariable findReferenceFM(FeatureModelVariable fm, List<FeatureModelVariable> referenceFMs) {
		String name = fm.getCompleteIdentifier();
		name = name.substring(name.indexOf("_")+1);
		
		for (FeatureModelVariable referenceFM : referenceFMs) {
			if (referenceFM.getCompleteIdentifier().endsWith(name)) {
				return referenceFM;
			}
		}
		
		return null;
	}

	@Ignore
	@Test
	public void testCompareFASEandFAMILIAR() {
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (FeatureSimilarityMetric metric : metrics) {
			System.out.println(metric);
			
			int nbFaseAndFamiliar = 0;
			int nbFaseOnly = 0;
			int nbFamiliarOnly = 0;
			int nbEdges = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);

					Set<String> faseAndFamiliarCommonEdges = new HashSet<String>(faseCommonEdges);
					faseAndFamiliarCommonEdges.retainAll(familiarCommonEdges);

					faseCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					familiarCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					
					nbEdges += fm.features().size();
					nbFaseAndFamiliar += faseAndFamiliarCommonEdges.size();
					nbFaseOnly += faseCommonEdges.size();
					nbFamiliarOnly += familiarCommonEdges.size();
					
//					System.out.println("FASE and FAMILIAR");
//					System.out.println(faseAndFamiliarCommonEdges.size());
//					
//					System.out.println("FASE only");
//					System.out.println(faseCommonEdges.size());
//					
//					System.out.println("FAMILIAR only");
//					System.out.println(familiarCommonEdges.size());
//					
//					System.out.println();
				}
				
			}
			
			System.out.println("Nb of edges : " + nbEdges);
			System.out.println("FASE and FAMILIAR : " + nbFaseAndFamiliar);
			System.out.println("FASE only : " + nbFaseOnly);
			System.out.println("FAMILIAR only : " + nbFamiliarOnly);
			System.out.println();	
		}
		
		
	}
	
	@Ignore
	@Test
	public void testCompareReductionAndPathLength() {

		int nbReductionAndPathLength = 0;
		int nbReductionOnly = 0;
		int nbPathLengthOnly = 0;
		int nbEdges = 0;

		for (FeatureModelVariable fm : getSPLOTFeatureModels()) {
			InteractiveFMSynthesizer synthesizerReduction = new InteractiveFMSynthesizer(fm, reductionMetric, null, null, 0);
			FeatureModelVariable reductionFM = synthesizerReduction.computeCompleteFeatureModel();
			List<String> reductionCommonEdges = getCommonEdges(fm, reductionFM);				


			InteractiveFMSynthesizer synthesizerPathLength = new InteractiveFMSynthesizer(fm, pathLength, null, null, 0);
			FeatureModelVariable pathLengthFM = synthesizerPathLength.computeCompleteFeatureModel();
			List<String> pathLengthCommonEdges = getCommonEdges(fm, pathLengthFM);

			Set<String> reductionAndPathLengthCommonEdges = new HashSet<String>(reductionCommonEdges);
			reductionAndPathLengthCommonEdges.retainAll(pathLengthCommonEdges);

			reductionCommonEdges.removeAll(reductionAndPathLengthCommonEdges);
			pathLengthCommonEdges.removeAll(reductionAndPathLengthCommonEdges);

			System.out.println(pathLengthCommonEdges);
			
			nbEdges += fm.features().size();
			nbReductionAndPathLength += reductionAndPathLengthCommonEdges.size();
			nbReductionOnly += reductionCommonEdges.size();
			nbPathLengthOnly += pathLengthCommonEdges.size();

		}

		System.out.println("Nb of edges : " + nbEdges);
		System.out.println("Reduction and PathLength: " + nbReductionAndPathLength);
		System.out.println("Reduction only : " + nbReductionOnly);
		System.out.println("PathLength only : " + nbPathLengthOnly);
		System.out.println();	
		
	}
	
	private double computeOntologicalScore(FeatureModelVariable fm, FeatureSimilarityMetric metric) {

		Set<String> features = fm.getFm().features();
		ImplicationGraph<String> big = fm.computeImplicationGraph();

		// FIRST TRY
//		double nbRecognizedFeatures = 0;
//		for (String feature : features) {
//			if (metric.similarity(big, feature, feature) != 0) {
//				nbRecognizedFeatures += 1;
//			}
//		}
//
//		double score = nbRecognizedFeatures / features.size();
		
		// SECOND TRY
//		double sumSimilarity = 0;
//		for (String feature : features) {
//			sumSimilarity += metric.similarity(big, feature, feature);
//		}
//		double score = sumSimilarity / features.size();
		
		// THIRD TRY
		double sumSimilarity = 0;
		FeatureGraph<String> diagram = fm.getFm().getDiagram();
		for (FeatureEdge edge: diagram.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY) {
				String feature = diagram.getSource(edge).getFeature();
				String parent = diagram.getTarget(edge).getFeature();
				double similarity = metric.similarity(big, feature, parent);
				if (similarity > 0.7) {
					sumSimilarity += 1;
				}
			}
		}
		double score = sumSimilarity / features.size();
		
		return score;

	}
	
	/**
	 * Rank FM according to the common edges and display ontological score
	 */
	@Ignore
	@Test
	public void testRankSPLOTFeatureModels() {
		List<FeatureModelVariable> fms = getSPLOTFeatureModels();

		for (FeatureSimilarityMetric metric : metrics) {
			List<OptimumBranchingResult> results = new ArrayList<OptimumBranchingResult>();
			
			for (FeatureModelVariable fm : fms) {
				OptimumBranchingResult result = new OptimumBranchingResult();
				result.setReferenceFM(fm);
				result.setMetric(metric);
				result.setOntologicalScore(computeOntologicalScore(fm, metric));
				
				InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
				FeatureModelVariable computedFM = synthesizer.computeCompleteFeatureModel();
				result.setSynthesizedFM(computedFM);
				result.setCommonEdges(getCommonEdges(fm, computedFM));
				
				results.add(result);
			}	
			
			Collections.sort(results);
			for (OptimumBranchingResult result : results) {
				System.out.println(result);
				System.out.println();
			}

			System.out.println("\n\n\n");
		}
		
	}

	/**
	 * Competition between FASE and FAMILIAR on each FM
	 */
	@Ignore
	@Test
	public void testFASEvsFAMILIAR() {
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (FeatureSimilarityMetric metric : metrics) {
			System.out.println(metric);
			int faseWins = 0;
			int familiarWins = 0;
			int draw = 0;
			int sumGap = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
//				System.out.print(fm.getIdentifier() + " : ");
				
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);
					
					int gap = faseCommonEdges.size() - familiarCommonEdges.size();
					

					if (gap > 0) {
						faseWins++;
						sumGap += gap;
					} else if (gap < 0) {
						familiarWins++;
					} else {
						draw++;
					}
				}
				
			}
			
			System.out.println("fase : " + faseWins);
			System.out.println("familiar : " + familiarWins);
			System.out.println("draw : " + draw);
			System.out.println("gap : " + sumGap / ((double) faseWins));
			System.out.println();
		}
	}
	
	@Test
	public void testPerfectExample() {
		System.out.println("Optimum branching of a perfect example");
		testOptimumBranching(getPerfectExample());
		
//		FeatureModelVariable example = getPerfectExample().iterator().next();
//		InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(example, pathLength, null, null, 0);
//		for (KeyValue<String, List<String>> parentCandidates : synthesizer.getParentCandidates()) {
//			System.out.println(parentCandidates.getKey());
//			for (String parentCandidate : parentCandidates.getValue()) {
//				System.out.println(parentCandidate + " : " + pathLength.similarity(null, parentCandidates.getKey(), parentCandidate));
//			}
//			System.out.println();
//			
//		}
	}
	
	@Ignore
	@Test
	public void testToto() {
		System.out.println("Toto test !");
		FeatureModelVariable runningExample = getRunningExample().iterator().next();
		ImplicationGraph<Set<String>> reducedGraph = runningExample.computeImplicationGraph().reduceCliques();
		TransitiveReduction.INSTANCE.reduce(reducedGraph);
//		System.out.println(reducedGraph);
		
		Expression<String> constraint = new Expression<String>(
				ExpressionType.IMPLIES,
				new Expression<String>("Storage"),
				new Expression<String>("Hosting"));
		runningExample.addConstraint(constraint);
		System.out.println(runningExample.getAllConstraints());
	}
	
//	@Ignore
//	@Test
//	public void testLSA() throws IOException {
//		System.out.println("toto");
//		if (!SingularValueDecompositionLibC.isSVDLIBCavailable())
//            return;
//
//		System.out.println("tata");
//        String[] docArr = new String[] {
//            "shipment of gold damaged in a fire",
//            "delivery of silver arrived in a silver truck",
//            "shipment of gold arrived in a truck"
//        };
//        
//        
//        List<String> docs = Arrays.asList(docArr);
//        int numDocs = docs.size();
//        LatentSemanticAnalysis lsa =
//            new LatentSemanticAnalysis(true, 2, new NoTransform(),
//                                       new SingularValueDecompositionLibC(),
//                                       false, new StringBasisMapping());
//        for (String doc : docs)
//            lsa.processDocument(new BufferedReader(new StringReader(doc)));
//        lsa.processSpace(System.getProperties());
//        
//        String query = "gold silver truck";
//        
//        DoubleVector projected = lsa.project(new StringDocument(query));
//        assertEquals(2, projected.length());
//        assertEquals(0.2140, Math.abs(projected.get(0)), 0.001);
//        assertEquals(0.1821, Math.abs(projected.get(1)), 0.001);
//        System.out.println("Projected: " + projected);
//	}
	
}

