package fr.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.map.HashedMap;
import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * Experiment 2: our heuristics vs a random heuristic
 * @author gbecan
 *
 */
public class ICSE2014Experiment2 extends KSynthesisTest {

	private final static int N_START = 2;
	private final static int N_END = 5;


	// Tests SPLOT
	@Ignore
	@Test
	public void testStatsSPLOT() {
		System.out.println("Stats SPLOT");
		testStatsFMs(getSPLOTFeatureModelsForFASE());
	}

	@Ignore
	@Test
	public void testTop2SPLOT() {
		System.out.println("Top SPLOT");
		testTopN(getSPLOTFeatureModelsForFASE(), N_START, N_END, false);
	}


	@Ignore
	@Test
	public void testTop2SPLOTReduced() {
		System.out.println("Top SPLOT (BIG reduced)");
		testTopN(getSPLOTFeatureModelsForFASE(), N_START, N_END, true);
	}

	@Test
	public void testClustersSPLOT() {
		System.out.println("Clusters SPLOT");
		testClusters(getSPLOTFeatureModelsForFASE(), false); 
	}
	
	@Ignore
	@Test
	public void testClustersSPLOTReduced() {
		System.out.println("Clusters SPLOT (BIG reduced)");
		testClusters(getSPLOTFeatureModelsForFASE(), true); 
	}


	// Test PCMs

	//	@Test
	//	public void testPCM() {
	//		List<FeatureModelVariable> pcmFeatureModels = getPCMFeatureModels();
	//		System.out.println("Stats PCM");
	//		testStatsFMs(pcmFeatureModels);
	//		prettyPrint();
	//		
	//		System.out.println("Top PCM");
	//		testTopN(pcmFeatureModels, 2, 5, false);
	//		prettyPrint();
	//		
	//		System.out.println("Top PCM (BIG reduced)");
	//		testTopN(getPCMFeatureModels(), 2, 5, true);
	//		prettyPrint();
	//		
	//		System.out.println("Clusters PCM");
	//		testClusters(pcmFeatureModels);
	//	}

	@Ignore
	@Test
	public void testStatsPCM() {
		System.out.println("BIG PCM");
		testStatsFMs(getPCMFeatureModels());
	}

	@Ignore
	@Test
	public void testTop2PCM() {
		System.out.println("Top PCM");
		testTopN(getPCMFeatureModels(), N_START, N_END, false);
	}

	@Ignore
	@Test
	public void testTop2PCMReduced() {
		System.out.println("Top PCM (BIG reduced)");
		testTopN(getPCMFeatureModels(), N_START, N_END, true);
	}

	@Ignore
	@Test
	public void testClustersPCM() {
		System.out.println("Clusters PCM");
		testClusters(getPCMFeatureModels(), false);
	}
	
	@Ignore
	@Test
	public void testClustersPCMReduced() {
		System.out.println("Clusters PCM (BIG Reduced)");
		testClusters(getPCMFeatureModels(), true);
	}


	// Auxiliary functions for tests

	private void testStatsFMs(List<FeatureModelVariable> fms) {
		ImplicationGraphMetrics graphMetrics = new ImplicationGraphMetrics();

		List<Integer> fmSize = new ArrayList<Integer>();
		List<Integer> minOutDegrees = new ArrayList<Integer>();
		List<Integer> maxOutDegrees = new ArrayList<Integer>();
		List<Double> meanOutDegrees = new ArrayList<Double>();
		int sumDegrees = 0;
		int nbFeatures = 0;
		int sumDepth = 0;


		for (FeatureModelVariable fm : fms) {
			fmSize.add(fm.features().size());
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
			
			sumDepth += getDepth(fm);
		} 

		System.out.println("Nb of features : " + nbFeatures);
		System.out.println("Nb of parents : " + (nbFeatures - fms.size()));
		Collections.sort(fmSize);
		System.out.println("size : " + fmSize);
		System.out.println("mean size : " + nbFeatures / ((double) fms.size()));
		Collections.sort(minOutDegrees);
		System.out.println("min : " + minOutDegrees);
		Collections.sort(maxOutDegrees);
		System.out.println("max : " + maxOutDegrees);
		Collections.sort(meanOutDegrees);
		System.out.println("mean : " + meanOutDegrees);
		System.out.println("mean degree : " + sumDegrees/ ((double) nbFeatures));
		
		double meanDepth = sumDepth / ((double) fms.size());
		System.out.println("mean depth of hierachies : " + meanDepth);
	}

	private int getDepth(FeatureModelVariable fm) {
		FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
		return getDepth(hierarchy, hierarchy.getTopVertex());
	}

	private int getDepth(FeatureGraph<String> hierarchy, FeatureNode<String> feature) {
		int depth = 0;
		Set<FeatureNode<String>> children = hierarchy.children(feature);
		for (FeatureNode<String> child : children) {
			depth = Math.max(getDepth(hierarchy, child), depth);
		}
		
		if (feature.isTop()) {
			return depth;
		} else {
			return 1 + depth;	
		}
		
		
	}

	private void testTopN(List<FeatureModelVariable> fms, int nStart, int nEnd, boolean reduceBIG) {


		for (Heuristic metric : metrics) {
			System.out.println(metric);

			// Init map gathering results
			Map<Integer, List<Double>> mapNbOfFeaturesInTopN = new HashedMap<Integer, List<Double>>();
			for (int n=nStart; n<=nEnd; n++) {
				mapNbOfFeaturesInTopN.put(n, new ArrayList<Double>());
			}

			// Counting features in top N
			for (FeatureModelVariable fm : fms) {

				InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, new AlwaysZeroMetric(), null, null, -1);

				// Reduce implication graph if necessary
				if (reduceBIG) {
					ImplicationGraph<String> implicationGraph = synthesizer.getImplicationGraph().clone();
					ImplicationGraph<String> reducedBIG = computeReducedBIG(fm, implicationGraph);

					for (SimpleEdge edge : implicationGraph.edges()) {
						String child = implicationGraph.getEdgeSource(edge);
						String parent = implicationGraph.getEdgeTarget(edge);
						if (!reducedBIG.containsEdge(child, parent)) {
							synthesizer.ignoreParent(child, parent);
						}
					}
				} 

				synthesizer.setParentSimilarityMetric(metric);

				int nbFeatures = fm.features().size() -1; // Avoid root
				for (int n=nStart; n<=nEnd; n++) {
					double nbOfFeaturesInTopN = countTopNParents(n, fm, synthesizer, metric);
					mapNbOfFeaturesInTopN.get(n).add(nbOfFeaturesInTopN / nbFeatures);	
				}


			}

			// Display results
			for (int n=nStart; n<=nEnd; n++) {
				List<Double> listNbFeaturesInTopN = mapNbOfFeaturesInTopN.get(n);
				double sum = 0;
				for (Double nbFeaturesInTopN : listNbFeaturesInTopN) {
					sum += nbFeaturesInTopN;
				}
				double average = sum / fms.size();

				Collections.sort(listNbFeaturesInTopN);
				double median = listNbFeaturesInTopN.get(listNbFeaturesInTopN.size()/2);

				System.out.println("Top" + n + " : " + average + " (average) " + median + " (median)");
			}
			System.out.println();

		}
	}

	private void testTopVariableN(List<FeatureModelVariable> fms) {

		int nbOfFeatures = 0;

		for (FeatureModelVariable fm : fms) {
			nbOfFeatures += fm.features().size()-1; // Avoid the root feature
		}
		System.out.println("Nb of parents : " + nbOfFeatures);

		for (Heuristic metric : metrics) {
			double sumNbOfFeaturesInTopN = 0;
			int nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1; 

			for (int i=0; i<nbIterations; i++) {
				double nbOfFeaturesInTopNIter = 0;

				for (FeatureModelVariable fm : fms) {

					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(
							fm, metric, null, 
							null, -1);

					nbOfFeaturesInTopNIter += countTopVariableNParents(fm, synthesizer);
				}

				sumNbOfFeaturesInTopN += nbOfFeaturesInTopNIter;
			}
			double nbOfFeaturesInTopN = sumNbOfFeaturesInTopN / nbIterations;
			System.out.println(metric + " : " + nbOfFeaturesInTopN + " (" + nbOfFeaturesInTopN / ((double) nbOfFeatures) + ")");
		}

	}

	private void testClusters(List<FeatureModelVariable> fms, boolean reduceBIG) {

		for (Heuristic metric : metrics) {
			System.out.println(metric);
			double threshold = clusteringThresholds.get(metric);


			List<Integer> listNbClusters = new ArrayList<Integer>();
			List<Double> listClusterSize = new ArrayList<Double>();
			List<Double> listCorrectClusters = new ArrayList<Double>();
			List<Double> listFeaturesInCorrectClusters = new ArrayList<Double>();
			List<Double> listRemovedChoices = new ArrayList<Double>();


			int nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1;
			for (int i=0; i<nbIterations; i++) {


				for (FeatureModelVariable fm : fms) {
					FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
					List<Set<String>> siblingsList = convertInSetOfString(hierarchy.getSiblingSetsInBFS());
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, new AlwaysZeroMetric(), null, null, -1);

					// Reduce implication graph if necessary
					if (reduceBIG) {
						ImplicationGraph<String> implicationGraph = synthesizer.getImplicationGraph().clone();
						ImplicationGraph<String> reducedBIG = computeReducedBIG(fm, implicationGraph);

						for (SimpleEdge edge : implicationGraph.edges()) {
							String child = implicationGraph.getEdgeSource(edge);
							String parent = implicationGraph.getEdgeTarget(edge);
							if (!reducedBIG.containsEdge(child, parent)) {
								synthesizer.ignoreParent(child, parent);
							}
						}
					} 
					
					synthesizer.setClusteringParameters(metric, threshold);
					Set<Set<String>> clusters = synthesizer.getSimilarityClusters();
					
					
					// Check clusters
					int sumClusterSize = 0;
					int sumCorrectClusters = 0;
					int sumFeaturesInCorrectClusters = 0;
					
					for (Set<String> cluster : clusters) {
						sumClusterSize += cluster.size();
						
						if (checkCluster(cluster, hierarchy, siblingsList)) {
							sumCorrectClusters++;
							sumFeaturesInCorrectClusters += cluster.size();
						}
					}
					
					// Compute metrics on clusters
					listNbClusters.add(clusters.size());


					if (clusters.size() > 0) {
						double nbClusters = clusters.size();
						listClusterSize.add(sumClusterSize / nbClusters);
						listCorrectClusters.add(sumCorrectClusters / nbClusters);	
					} else {
						listClusterSize.add(0.0);
					}
					
					double nbFeatures = fm.features().size();				
					double featuresInCorrectClusters = sumFeaturesInCorrectClusters / nbFeatures;
					listFeaturesInCorrectClusters.add(featuresInCorrectClusters);
					
					
					double removedChoices = (sumFeaturesInCorrectClusters - sumCorrectClusters) / nbFeatures;
					listRemovedChoices.add(removedChoices);
				}
			}

			// Display results
			double nbClustersAv = averageInt(listNbClusters);
			double nbClustersMed = medianInt(listNbClusters);
			System.out.println("Number of clusters: " + nbClustersAv + " (average) " + nbClustersMed + " (median)");
			
			double clusterSizeAv = averageDouble(listClusterSize);
			double clusterSizeMed = medianDouble(listClusterSize);
			System.out.println("Cluster size: " + clusterSizeAv + " (average) " + clusterSizeMed + " (median)");
			
			double correctClustersAv = averageDouble(listCorrectClusters);
			double correctClustersMed = medianDouble(listCorrectClusters);
			System.out.println("Correct clusters: " + correctClustersAv + " (average) " + correctClustersMed + " (median)");
			
			double featuresInCorrectClustersAv = averageDouble(listFeaturesInCorrectClusters);
			double featuresInCorrectClustersMed = medianDouble(listFeaturesInCorrectClusters);			
			System.out.println("Features in a correct cluster: " + featuresInCorrectClustersAv + " (average) " + featuresInCorrectClustersMed + " (median)");
			
			double removedChoicesAv = averageDouble(listRemovedChoices);
			double removedChoicesMed = medianDouble(listRemovedChoices);
			System.out.println("Removed choices: " + removedChoicesAv + " (average) " + removedChoicesMed + " (median)");
			
			System.out.println();
		}

	}
	

}
