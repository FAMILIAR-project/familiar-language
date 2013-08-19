package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import edu.ucla.sspace.basis.StringBasisMapping;
import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.lsa.LatentSemanticAnalysis;
import edu.ucla.sspace.matrix.NoTransform;
import edu.ucla.sspace.matrix.factorization.SingularValueDecompositionLibC;
import edu.ucla.sspace.text.StringDocument;
import edu.ucla.sspace.vector.DoubleVector;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wikipedia.miner.model.Article;
import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.WikipediaConfiguration;

public class ComputeLSA {

	private ImplicationGraph<String> wbig;
	private WikipediaMinerMetric wiki_metric;
	private Collection<BufferedReader> documents;
	private LatentSemanticAnalysis lsa;
	
	private Map<SimpleEdge, Double> similarityMap = new HashMap<SimpleEdge, Double>();
	private Wikipedia wikipedia;
	
	public ComputeLSA(ImplicationGraph<String> big, WikipediaMinerDB wikipediaDB) throws Exception {
		this.wbig = big;
		wikipedia = wikipediaDB.getWikipedia();
		documents = new ArrayList<BufferedReader>();
	}
	public void seekWikiArticles(ImplicationGraph<String> big){
		Collection<SimpleEdge> ed = big.edges();

		double simillarity = 0;
		int simIndex = -1;
		for (String featureName : big.vertices()) {
			String feature = featureName.replaceAll("(\\p{Lower})(\\p{Upper})",
					"$1 $2");
			if (wikipedia != null) {
				Article article = wikipedia.getArticleByTitle(feature);
				if (article != null) {
					String page = article.getMarkup();
					// System.out.println(page);
					// convert String into InputStream
					if (page != null) {
						InputStream is = new ByteArrayInputStream(page.getBytes());
						// read it with BufferedReader
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						documents.add(br);
					}
				}
			}
		}
	}
	public void computeProject (ImplicationGraph<String> big){
		seekWikiArticles(big);
		try {
			  int numDocs = documents.size();
		       lsa = new LatentSemanticAnalysis(true, numDocs, new NoTransform(), 
		                                       new SingularValueDecompositionLibC(),
		                                       false, new StringBasisMapping());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			String query = "gold silver truck";
	        
	        DoubleVector projected = lsa.project(new StringDocument(query));
//	        assertEquals(2, projected.length());
//	        assertEquals(0.2140, Math.abs(projected.get(0)), 0.001);
//	        assertEquals(0.1821, Math.abs(projected.get(1)), 0.001);
	        System.out.println("Projected: " + projected);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		  private Matrix documentSpace;
//		  private Matrix sigma;
//		  private transient WeakReference<Matrix> UtimesSigmaInvRef;
//		  private Matrix U;
//		  private final SingularValueDecomposition reducer;
//		  private final Transform transform;
//		  private final int dimensions;
//		  private final boolean retainDocumentSpace;
	}
	public void cosinusSimilarity(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					double sim = Similarity.getSimilarity(
							Similarity.SimType.COSINE, lsa.getVector(word),
							targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void correlationSimilarity(ImplicationGraph<String> big) {
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.correlation(lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void spearmanRankCorrelation(ImplicationGraph<String> big) {
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.spearmanRankCorrelationCoefficient(lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void euclideanDistance(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.euclideanDistance(lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goodmanKruskalGamma(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.goodmanKruskalGamma((DoubleVector) lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void jaccardIndex(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.jaccardIndex((DoubleVector) lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void kendallsTau(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.kendallsTau( lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void klDivergence(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.klDivergence( lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void linSimilarity(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.linSimilarity( lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tanimotoSimilarity(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		seekWikiArticles(big);
		try {
			lsa = new LatentSemanticAnalysis(documents.size());
			for (BufferedReader document : documents) {
				lsa.processDocument(document);
			}
			lsa.processSpace(System.getProperties());
			for (String t_word : lsa.getWords()) {
				// System.out.printf("%s maps to %s%n", t_word,
				// lsa.getVector(t_word).toString());
				DoubleVector targetWord = (DoubleVector) lsa.getVector(t_word);
				for (String word : getRemainingWords(t_word)) {
					 double sim = Similarity.tanimotoCoefficient( lsa.getVector(word),targetWord);
					SimpleEdge cle = wbig.getEdge(word, t_word);
					similarityMap.put(cle, sim);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<String> getRemainingWords(String word) {
		List<String> remainingWords = new ArrayList<String>();
		for (String w : lsa.getWords()) {
			if (!w.equals(word)) {
				remainingWords.add(w);
			}
		}
		return remainingWords;
	}
	public ImplicationGraph<String> getWbig() {
		return wbig;
	}
	public WikipediaMinerMetric getWiki_metric() {
		return wiki_metric;
	}
	public Collection<BufferedReader> getDocuments() {
		return documents;
	}
	public LatentSemanticAnalysis getLsa() {
		return lsa;
	}
	public Map<SimpleEdge, Double> getSimilarityMap() {
		// TODO Auto-generated method stub
		return similarityMap;
	}
	

}
