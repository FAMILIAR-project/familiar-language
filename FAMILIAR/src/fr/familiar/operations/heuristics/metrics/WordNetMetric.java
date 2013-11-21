package fr.familiar.operations.heuristics.metrics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;
import fr.familiar.operations.heuristics.ConfigurableHeuristicPlugin;
import fr.familiar.operations.heuristics.SimpleHeuristic;

public abstract class WordNetMetric extends SimpleHeuristic implements ConfigurableHeuristicPlugin {

	private Dictionary dictionary;
	private Synset context;

	@Override
	public boolean init(File configFile) {
		if (configFile.exists()) {
			try {
				dictionary = Dictionary.getInstance(new FileInputStream(configFile.getAbsolutePath()));
			} catch (JWNLException e) {
				e.printStackTrace();
				return false;
			}	
			 catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				}
		}
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
	

	@Override
//	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
	public double similarity(String child, String parent) {
		List<String> wordsF1 = new ArrayList<String>();
//		if (lookup(featureName1) != null) {
//			wordsF1.add(featureName1);
//		} else {
			String preprocessedF1 = child.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2").toLowerCase();
			wordsF1.addAll(Arrays.asList(preprocessedF1.split("\\s|\\p{Punct}")));
//		}

		List<String> wordsF2 = new ArrayList<String>();
//		if (lookup(featureName2) != null) {
//			wordsF2.add(featureName2);
//		} else {
			String preprocessedF2 = parent.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2").toLowerCase();
			wordsF2.addAll(Arrays.asList(preprocessedF2.split("\\s|\\p{Punct}")));
//		}

		double nbOfZeros = 0;
		double sumF1 = 0;
		for (String word1 : wordsF1) {
			double max = 0;
			for (String word2 : wordsF2) {
				double sim = context == null ? maxWordSimilarity(word1, word2) : contextWordSimilarity(word1, word2);
				if (sim > max) {
					max = sim;
				}
			}
//			if (max == 0) {
//				nbOfZeros++;
//			}
			sumF1 += max;
		}

		double sumF2 = 0;
		for (String word2 : wordsF2) {
			double max = 0;
			for (String word1 : wordsF1) {
				double sim = context == null ? maxWordSimilarity(word1, word2) : contextWordSimilarity(word1, word2);
				if (sim > max) {
					max = sim;
				}
			}
//			if (max == 0) {
//				nbOfZeros++;
//			}
			sumF2 += max;
		}

		double nbOfWords = (wordsF1.size() + wordsF2.size() - nbOfZeros);
		double score = (sumF1 + sumF2) / nbOfWords;
		
		return nbOfWords != 0 ? score : 0;
	}

	/**
	 * Compute the similarity of two words
	 * based on the maximum similarity of two of their synsets
	 * @param word1
	 * @param word2
	 * @return
	 */
	public double maxWordSimilarity(String word1, String word2) {
		double maxScore = 0;

		IndexWord indexWord1 = lookup(word1);
		IndexWord indexWord2 = lookup(word2);

		if (indexWord1 != null && indexWord2 != null) {
			// Select maximum score
			for (Synset synsetWord1 : indexWord1.getSenses()) {
				for (Synset synsetWord2 : indexWord2.getSenses()) {
					double score = synsetSimilarity(synsetWord1, synsetWord2);
					if (score > maxScore) {
						maxScore = score;
					}
				}
			}	
		}

		return maxScore;
	}
	
	/**
	 * Compute the similarity of two words
	 * based on the best synsets compared to the context
	 * @param word1
	 * @param word2
	 * @return
	 */
	public double contextWordSimilarity(String word1, String word2) {
		double bestScore = 0;

		IndexWord indexWord1 = lookup(word1);
		IndexWord indexWord2 = lookup(word2);

		if (indexWord1 != null && indexWord2 != null) {
			// Select best synset for word 1
			Synset bestSynset1 = null;
			double scoreBestSynset1 = 0;
			for (Synset synset : indexWord1.getSenses()) {
				double score = synsetSimilarity(synset, context);
				if (score > scoreBestSynset1) {
					scoreBestSynset1 = score;
					bestSynset1 = synset;
				}
			}	
			// Select best synset for word 2	
			Synset bestSynset2 = null;
			double scoreBestSynset2 = 0;
			for (Synset synset : indexWord2.getSenses()) {
				double score = synsetSimilarity(synset, context);
				if (score > scoreBestSynset2) {
					scoreBestSynset2 = score;
					bestSynset2 = synset;
				}
			}
			// Compute score
			bestScore = synsetSimilarity(bestSynset1, bestSynset2);
		}
		
		return bestScore;
	}

	/**
	 * Compute the similarity of two synsets
	 * @param synset1
	 * @param synset2
	 * @return
	 */
	public abstract double synsetSimilarity(Synset synset1, Synset synset2);

	/**
	 * Lookup the word in the dictionnary
	 * @param word
	 * @return
	 */
	public IndexWord lookup(String word) {
		IndexWord index = null;
		try {
			index = dictionary.lookupIndexWord(POS.NOUN, word);
			if (index == null) {
				index = dictionary.lookupIndexWord(POS.VERB, word);
			}
			if (index == null) {
				index = dictionary.lookupIndexWord(POS.ADJECTIVE, word);
			}
			if (index == null) {
				index = dictionary.lookupIndexWord(POS.ADVERB, word);
			}
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		return index;
	}

	public Synset getContext() {
		return context;
	}

	public void setContext(Synset context) {
		this.context = context;
	}

}
