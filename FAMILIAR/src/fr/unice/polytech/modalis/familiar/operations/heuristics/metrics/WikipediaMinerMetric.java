package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.io.File;

import org.wikipedia.miner.comparison.ArticleComparer;
import org.wikipedia.miner.model.Article;
import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.WikipediaConfiguration;

public class WikipediaMinerMetric implements FeatureSimilarityMetric {

	private final String wikipediaDB;
	private Wikipedia wikipedia;
	private ArticleComparer comparer;


	/**
	 * 
	 * @param wikipediaDB : path to the database template file (wikipedia-template.xml)
	 */
	public WikipediaMinerMetric(String wikipediaDB) {
		this.wikipediaDB = wikipediaDB;
	}

	public void loadDatabase() throws Exception {
		WikipediaConfiguration conf = new WikipediaConfiguration(
				new File(wikipediaDB));
		wikipedia = new Wikipedia(conf, false);	
		comparer = new ArticleComparer(wikipedia);
	}

	
	public void closeDatabase() {
		if (wikipedia != null) {
			wikipedia.close();	
		}
	}
	
	@Override
	public String toString() {
		return "Wikipedia Miner metric (" + wikipedia.getConfig().getDatabaseDirectory().getName() + ")";
	}

	@Override
	public double similarity(String featureName1, String featureName2) {
		double relatedness = 0;

		// Preprocessing
		String f1 = featureName1.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2");
		String f2 = featureName2.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2");

		if (wikipedia != null) {
			Article article1 = wikipedia.getMostLikelyArticle(f1, null);
			Article article2 = wikipedia.getMostLikelyArticle(f2, null);
			
			// Compute the similarity of the whole feature name if possible, 
			// split the feature in words otherwise
			if (article1 == null || article2 == null) {
				relatedness = computeSimilarityBetweenWords(f1, f2);
			} else {
				try {
					relatedness = comparer.getRelatedness(article1, article2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


		}

		return relatedness;

	}


	private double computeSimilarityBetweenWords(String f1, String f2) {
		String[] wordsF1 = f1.split("\\s|\\p{Punct}");
		String[] wordsF2 = f2.split("\\s|\\p{Punct}");

		double nbOfZeros = 0;
		double sumF1 = 0;
		for (String word1 : wordsF1) {
			double max = 0;
			for (String word2 : wordsF2) {
				double sim = computeRelatedness(word1, word2);
				if (sim > max) {
					max = sim;
				}
			}
			sumF1 += max;
		}

		double sumF2 = 0;
		for (String word2 : wordsF2) {
			double max = 0;
			for (String word1 : wordsF1) {
				double sim = computeRelatedness(word1, word2);
				if (sim > max) {
					max = sim;
				}
			}
			sumF2 += max;
		}

		double nbOfWords = (wordsF1.length + wordsF2.length - nbOfZeros);
		double score = (sumF1 + sumF2) / nbOfWords;

		return nbOfWords != 0 ? score : 0;
	}

	private double computeRelatedness(String word1, String word2) {

		double relatedness = 0;

		if (wikipedia != null) {
			Article article1 = wikipedia.getMostLikelyArticle(word1, null);
			Article article2 = wikipedia.getMostLikelyArticle(word2, null);
			if (article1 != null && article2 != null) {
				try {
					relatedness = comparer.getRelatedness(article1, article2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


		return relatedness;
	}

}

