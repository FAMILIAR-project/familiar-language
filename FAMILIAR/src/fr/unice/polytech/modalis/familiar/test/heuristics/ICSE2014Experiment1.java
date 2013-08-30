package fr.unice.polytech.modalis.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.gui.synthesis.KeyValue;
import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FMEditDistanceMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RefactoringEditDistance;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ZhangEditDistance;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * Experiment 1: our heuristics vs a random heuristic
 * @author gbecan
 *
 */
public class ICSE2014Experiment1 extends KSynthesisTest {

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
							null, -1);

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
//					System.out.println(fm.getCompleteIdentifier());

					List<FeatureSimilarityMetric> complementaryHeuristics = new ArrayList<FeatureSimilarityMetric>();
//					complementaryHeuristics.add(pathLength);
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, complementaryHeuristics, null, -1);
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
					writeFMToFile(metric, fm, computedFM);
				}
			}
			System.out.println(metric);
			System.out.println("common edges for all fms : " + sumCommonEdgesGlobalDistance / totalNbEdges);
			System.out.println("common edges by fm : " + sumCommonEdgesDistance / fms.size() / nbIterations);
			System.out.println("zhang distance : " + sumZhangDistance / fms.size() / nbIterations);
			System.out.println("refactoring distance : " + sumRefactoringDistance / fms.size() / nbIterations);
			System.out.println("distribution : ");
			int sum = 0; 
			for (int i =0; i<11; i++) {
				sum += distribution[i] / nbIterations;
				System.out.println(i + " : " + (int) (distribution[i] / nbIterations) + "\t" + sum);
			}
			System.out.println();
		}

	}
	
	@Test
	public void testBIGDegreeSPLOT() {
		System.out.println("BIG SPLOT");
		testBIGDegree(getSPLOTFeatureModels());
	}

	@Test
	public void testBIGDegreeVariCell() {
		System.out.println("BIG Varicell");
		testBIGDegree(getVariCellFeatureModels());
	}

	@Test
	public void testTop2SPLOT() {
		System.out.println("Top 2 SPLOT");
		testTopN(getSPLOTFeatureModels(), 2);
	}

	@Test
	public void testTop2VariCell() {
		System.out.println("Top 2 VariCell");
		testTopN(getVariCellFeatureModels(), 2);
	}

	@Test
	public void testClustersSPLOT() {
		System.out.println("Clusters SPLOT");
		testClusters(getSPLOTFeatureModels());
	}

	
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

	@Test
	public void testOptimumBranchingVariCell() {
		System.out.println("Optimum branching VariCell");
		testOptimumBranching(getVariCellFeatureModels());
	}
	
	@Test
	public void testVariCell() {
		List<FeatureModelVariable> variCellFeatureModels = getVariCellFeatureModels();
		testBIGDegree(variCellFeatureModels);
		testTopN(variCellFeatureModels, 2);
		testClusters(variCellFeatureModels);
		testOptimumBranching(variCellFeatureModels);
	}

}
