package fr.familiar.test.heuristics;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.experimental.FGroup;
import fr.familiar.gui.synthesis.KeyValue;
import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.familiar.operations.heuristics.metrics.FMEditDistanceMetric;
import fr.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.operations.heuristics.metrics.RefactoringEditDistance;
import fr.familiar.operations.heuristics.metrics.ZhangEditDistance;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class ASE2013KSynthesisTest extends KSynthesisTest {

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

		for (Heuristic metric : metrics) {
			double sumNbOfFeaturesInTopN = 0;
			int nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1; 

			for (int i=0; i<nbIterations; i++) {
				double nbOfFeaturesInTopNIter = 0;
				
				for (FeatureModelVariable fm : fms) {
				
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(
							fm, metric, new ArrayList<Heuristic>(), 
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

		for (Heuristic metric : metrics) {
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
							fm, null, new ArrayList<Heuristic>(), 
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

	private void testOptimumBranching(List<FeatureModelVariable> fms) {
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		for (Heuristic metric : metrics) {
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

					List<Heuristic> complementaryHeuristics = new ArrayList<Heuristic>();
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
					writeFMToFile(OUTPUT_FOLDER, metric, fm.getCompleteIdentifier(), computedFM);
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

	
	
	private void testFMClusters(List<FeatureModelVariable> fms) {
		// Distance metrics
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		// Init clusters
		HashMap<Heuristic, List<FeatureModelVariable>> fmClusters = new HashMap<Heuristic, List<FeatureModelVariable>>();
		for (Heuristic metric : metrics) {
			fmClusters.put(metric, new ArrayList<FeatureModelVariable>());
		}
		
		// Init common edges with ground truth
		HashMap<FeatureModelVariable, List<String>> commonEdgesWithGroundTruth = new HashMap<FeatureModelVariable, List<String>>();
		
		// Select best metric for each fm
		for (FeatureModelVariable fm : fms) {

			Heuristic bestMetric = null;
			double bestMetricDistance = -1;
			List<String> bestCommonEdges = null;
			
			for (Heuristic metric : metrics) {

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
		for (Heuristic metric : fmClusters.keySet()) {
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

	

	private void testInteractiveSynthesis(List<FeatureModelVariable> fms) {
		FMEditDistanceMetric editDistance = new RefactoringEditDistance();

		for (Heuristic metric : metrics) {
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
		testBIGDegree(getPCMFeatureModels());
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
		testTopN(getPCMFeatureModels(), 2);
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
		testClusters(getPCMFeatureModels());
	}

	@Ignore
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
		testOptimumBranching(getPCMFeatureModels());
	}
	
	@Ignore
	@Test
	public void testVariCell() {
		List<FeatureModelVariable> variCellFeatureModels = getPCMFeatureModels();
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



	@Ignore
	@Test
	public void testCompareFASEandFAMILIAR() {
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (Heuristic metric : metrics) {
			System.out.println(metric);
			
			int nbFaseAndFamiliar = 0;
			int nbFaseOnly = 0;
			int nbFamiliarOnly = 0;
			int nbEdges = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
//				System.out.println(fm.getCompleteIdentifier());
				
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					String faseRoot = faseFM.root().getFtName();
					
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, -1);
 					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
 					writeFMToFile(OUTPUT_FOLDER, metric, fm.getCompleteIdentifier(), familiarFM);
 					String familiarRoot = familiarFM.root().getFtName();
					
					
					
					
					if (!familiarRoot.equals(faseRoot)) {
						System.out.println("\"" + fm.getCompleteIdentifier().substring(fm.getCompleteIdentifier().indexOf("_") +1) + ".xml\",");
//						System.out.println("fase : " + faseRoot);
//						System.out.println("familiar : " + familiarRoot);
//						System.out.println();
					}
					
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);

					Set<String> faseAndFamiliarCommonEdges = new HashSet<String>(faseCommonEdges);
					faseAndFamiliarCommonEdges.retainAll(familiarCommonEdges);

					faseCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					familiarCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					
					nbEdges += fm.features().size();
					nbFaseAndFamiliar += faseAndFamiliarCommonEdges.size();
					nbFaseOnly += faseCommonEdges.size();
					nbFamiliarOnly += familiarCommonEdges.size();
					
					if (!faseCommonEdges.isEmpty() || !familiarCommonEdges.isEmpty()) {
						System.out.println("\"" + fm.getCompleteIdentifier().substring(fm.getCompleteIdentifier().indexOf("_") +1) + ".xml\",");
						System.out.println(faseCommonEdges.size());
						System.out.println(familiarCommonEdges.size());
					}
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

// FIXME : broken with heuristic plugins
//	@Ignore
//	@Test
//	public void testCompareReductionAndPathLength() {
//
//		int nbReductionAndPathLength = 0;
//		int nbReductionOnly = 0;
//		int nbPathLengthOnly = 0;
//		int nbEdges = 0;
//
//		for (FeatureModelVariable fm : getSPLOTFeatureModels()) {
//			InteractiveFMSynthesizer synthesizerReduction = new InteractiveFMSynthesizer(fm, reductionMetric, null, null, 0);
//			FeatureModelVariable reductionFM = synthesizerReduction.computeCompleteFeatureModel();
//			List<String> reductionCommonEdges = getCommonEdges(fm, reductionFM);				
//
//
//			InteractiveFMSynthesizer synthesizerPathLength = new InteractiveFMSynthesizer(fm, pathLength, null, null, 0);
//			FeatureModelVariable pathLengthFM = synthesizerPathLength.computeCompleteFeatureModel();
//			List<String> pathLengthCommonEdges = getCommonEdges(fm, pathLengthFM);
//
//			Set<String> reductionAndPathLengthCommonEdges = new HashSet<String>(reductionCommonEdges);
//			reductionAndPathLengthCommonEdges.retainAll(pathLengthCommonEdges);
//
//			reductionCommonEdges.removeAll(reductionAndPathLengthCommonEdges);
//			pathLengthCommonEdges.removeAll(reductionAndPathLengthCommonEdges);
//
//			System.out.println(pathLengthCommonEdges);
//			
//			nbEdges += fm.features().size();
//			nbReductionAndPathLength += reductionAndPathLengthCommonEdges.size();
//			nbReductionOnly += reductionCommonEdges.size();
//			nbPathLengthOnly += pathLengthCommonEdges.size();
//
//		}
//
//		System.out.println("Nb of edges : " + nbEdges);
//		System.out.println("Reduction and PathLength: " + nbReductionAndPathLength);
//		System.out.println("Reduction only : " + nbReductionOnly);
//		System.out.println("PathLength only : " + nbPathLengthOnly);
//		System.out.println();	
//		
//	}
	
	private double computeOntologicalScore(FeatureModelVariable fm, Heuristic metric) {

		Set<String> features = fm.getFm().features();
		ImplicationGraph<String> big = fm.computeImplicationGraph();
		Set<FGroup> xorGroups = fm.computeXorGroups();
		Set<FGroup> orGroups = fm.computeOrGroups();

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
//				double similarity = metric.similarity(big, xorGroups, orGroups, feature, parent);
				double similarity = metric.similarity(feature, parent);
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

		for (Heuristic metric : metrics) {
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
		
		for (Heuristic metric : metrics) {
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
	
	@Ignore
	@Test
	public void testCheckFASEFeatureModels() {
		System.out.println("Checking FASE FMs");
		for (FeatureModelVariable fm : getFASEFeatureModels()) {
			FeatureModelVariable referenceFM = findReferenceFM(fm, getSPLOTFeatureModels());
			FeatureGraph<String> fmDiagram = fm.getFm().getDiagram();
			FeatureGraph<String> referenceDiagram = referenceFM.getFm().getDiagram();
			if (fmDiagram.vertices().size() != referenceDiagram.vertices().size()) {
				System.out.println(fm.getCompleteIdentifier());
			}
		}
	}
	
	@Ignore
	@Test
	public void testPerfectExample() {
		System.out.println("Optimum branching of a perfect example");
		testOptimumBranching(getPerfectExample());
		
		FeatureModelVariable example = getPerfectExample().iterator().next();
		InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(example, directedPathLength, null, null, 0);
		for (KeyValue<String, List<String>> parentCandidates : synthesizer.getParentCandidates()) {
			System.out.println(parentCandidates.getKey());
			for (String parentCandidate : parentCandidates.getValue()) {
				System.out.println(parentCandidate + " : " + directedPathLength.similarity(parentCandidates.getKey(), parentCandidate));
			}
			System.out.println();
			
		}
	}
	
	@Ignore
	@Test
	public void testToto() {
		System.out.println("Toto test !");
		FeatureModelVariable faseFM = getFASEExample().iterator().next();
		writeFMToFile(OUTPUT_FOLDER, null, faseFM.getCompleteIdentifier(), faseFM);
		testOptimumBranching(getFASEExample());
	}
	
	@Ignore
	@Test
	public void testTata() throws Exception {
		FeatureModelVariable faseExample = FM ("fase_counter_example", 
				"A: [B] [C] E ; \n" +
				"(C -> B); \n" +
				""
				);
		
		writeFMToFile(OUTPUT_FOLDER, null, faseExample.getCompleteIdentifier(), faseExample);
		List<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		fms.add(faseExample);
		testOptimumBranching(fms);
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

