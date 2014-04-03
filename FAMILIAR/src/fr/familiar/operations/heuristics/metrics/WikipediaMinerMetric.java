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

package fr.familiar.operations.heuristics.metrics;

import java.io.File;

import org.wikipedia.miner.comparison.ArticleComparer;
import org.wikipedia.miner.model.Article;
import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.WikipediaConfiguration;

import fr.familiar.operations.heuristics.ConfigurableHeuristicPlugin;
import fr.familiar.operations.heuristics.SimpleHeuristic;

public class WikipediaMinerMetric extends SimpleHeuristic implements ConfigurableHeuristicPlugin {

	private Wikipedia wikipedia;
	private ArticleComparer comparer;


	@Override
	public String toString() {
		return "Wikipedia Miner metric (" + wikipedia.getConfig().getDatabaseDirectory().getName() + ")";
	}

	@Override
//	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
	public double similarity(String child, String parent) {
		double relatedness = 0;

		// Preprocessing
		String f1 = child.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2");
		String f2 = parent.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2");

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

	public Wikipedia getWikipedia() {
		return wikipedia;
	}

//	@Override
//	public boolean isXorGroupRequired() {
//		return false;
//	}
//
//	@Override
//	public boolean isOrGroupRequired() {
//		return false;
//	}

	@Override
	public String getName() {
		return "Wikipedia Miner";
	}

	
	@Override
	public boolean init(File configFile) {
		try {
			WikipediaConfiguration conf = new WikipediaConfiguration(configFile);
			this.wikipedia = new Wikipedia(conf, false);
			this.comparer = new ArticleComparer(this.wikipedia);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		if (this.wikipedia != null) {
			this.wikipedia.close();
		}
		return true;
	}




}

