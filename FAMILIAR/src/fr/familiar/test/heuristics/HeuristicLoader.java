package fr.familiar.test.heuristics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.metrics.LevenshteinMetric;
import fr.familiar.operations.heuristics.metrics.PathLengthMetric;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.operations.heuristics.metrics.SmithWatermanMetric;
import fr.familiar.operations.heuristics.metrics.TransitiveReductionMetric;
import fr.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.familiar.operations.heuristics.metrics.WuPalmerMetric;

public class HeuristicLoader {

	private static final String WORDNET_DB = "wordnet_properties.xml";
	private static final String WIKIPEDIA_DB = "/mnt/windows/Users/gbecan/Documents/db_wikipedia/wikipedia-template.xml";
	private static final String WIKTIONARY_DB = "/mnt/windows/Users/gbecan/Documents/db_wiktionary/wikipedia-template.xml";
	
	private ArrayList<Heuristic> metrics;
	private HashMap<Heuristic, Double> clusteringThresholds;
	
	private SmithWatermanMetric smithWaterman;
	private LevenshteinMetric levenshtein;
	private WuPalmerMetric wup;
	private PathLengthMetric pathLength;
	private WikipediaMinerMetric wikiMetric;
	private WikipediaMinerMetric wiktionaryMetric;
	private TransitiveReductionMetric reductionMetric;

	public List<Heuristic> loadHeuristics() {
		// Set up metrics
				metrics = new ArrayList<Heuristic>();
				clusteringThresholds = new HashMap<Heuristic, Double>();

//				// Random metric
//				Heuristic random = new RandomMetric();
//				metrics.add(random);
//				clusteringThresholds.put(random, 0.15);

				// Simmetrics metrics
				smithWaterman = new SmithWatermanMetric();
				metrics.add(smithWaterman);
				clusteringThresholds.put(smithWaterman, 0.6);

//				levenshtein = new LevenshteinMetric();
//				metrics.add(levenshtein);
//				clusteringThresholds.put(levenshtein, 0.7);

				// WordNet metrics
				if (new File(WORDNET_DB).exists()) {
//					wup = new WuPalmerMetric();
//					wup.init(new File(WORDNET_DB));
//					metrics.add(wup);
//					clusteringThresholds.put(wup, 0.2);
//
//					pathLength = new PathLengthMetric();
//					pathLength.init(new File(WORDNET_DB));
//					metrics.add(pathLength);
//					clusteringThresholds.put(pathLength, 0.5);
					
//					directedPathLength = new DirectedPathLengthMetric(wordNetDictionary);
//					metrics.add(directedPathLength);
//					clusteringThresholds.put(directedPathLength, 0.5);
				}

//				// WikipediaMiner metrics
//					wikiMetric = new WikipediaMinerMetric();
//					wikiMetric.init(new File(WIKIPEDIA_DB));
//					metrics.add(wikiMetric);
//					clusteringThresholds.put(wikiMetric, 0.5);	
//				
//					wiktionaryMetric = new WikipediaMinerMetric();
//					wiktionaryMetric.init(new File(WIKTIONARY_DB));
//					metrics.add(wiktionaryMetric);
//					clusteringThresholds.put(wiktionaryMetric, 0.5);	
//
//				// Transitive reduction metric
//				reductionMetric = new TransitiveReductionMetric();
//				metrics.add(reductionMetric);
//				clusteringThresholds.put(reductionMetric, 0.5);
				
//				HybridMetric hybrid = new HybridMetric(wikiMetric);
//				metrics.add(hybrid);
//				clusteringThresholds.put(hybrid, 0.5);
				
				return metrics;
	}
	
	public void closeHeuristics() {
		if (wikiMetric != null) {
			wikiMetric.stop();	
		}
		if (wiktionaryMetric != null) {
			wiktionaryMetric.stop();
		}
	}
	
	public HashMap<Heuristic, Double> getDefaultClusteringThresholds() {
		return clusteringThresholds;
	}
}
