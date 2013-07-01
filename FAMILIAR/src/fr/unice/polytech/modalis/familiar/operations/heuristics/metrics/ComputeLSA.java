package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.lsa.LatentSemanticAnalysis;
import edu.ucla.sspace.vector.DoubleVector;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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

public class ComputeLSA {
	
	 private ImplicationGraph<String> wbig;
	 private WikipediaMinerMetric wiki_metric; 
	 private Collection<BufferedReader> documents ;
	 private  Map<SimpleEdge, Double> cosinusSimilarityMap = new HashMap<SimpleEdge, Double>();
	 private LatentSemanticAnalysis lsa;
	 
	 public ComputeLSA (ImplicationGraph<String> big) throws Exception{
		 this.wbig = big;
		 wiki_metric = new WikipediaMinerMetric("C:\\db_wikipedia\\wikipedia-template.xml");
		 wiki_metric.loadDatabase();
		 documents = new ArrayList<BufferedReader>();
	 }

	public void computeCosinusSimilarity(ImplicationGraph<String> big) {
		// TODO Auto-generated method stub
		
		Collection<SimpleEdge> ed = big.edges() ;
		Wikipedia wikipedia = wiki_metric.getWikipedia();
		double simillarity=0;
		int simIndex=-1;
		
		for (String featureName : big.vertices()) {
			
		String	feature = featureName.replaceAll("(\\p{Lower})(\\p{Upper})", "$1 $2");
		
		if (wikipedia != null) {
			Article article = wikipedia.getMostLikelyArticle(feature, null);
			
			String page = article.getMarkup();
			
			//System.out.println(page);
		
			// convert String into InputStream
			if(page!=null){
			InputStream is = new ByteArrayInputStream(page.getBytes());
		 
			// read it with BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			documents.add(br);
		 
		}
		}
		
		try {
			 lsa = new LatentSemanticAnalysis();
			 
			 for (BufferedReader document : documents) {
			 lsa.processDocument(document);
			 }
			 
			 lsa.processSpace(System.getProperties());
			  
			 for (String t_word : lsa.getWords()) {
			 
			 //System.out.printf("%s maps to %s%n", t_word, lsa.getVector(t_word).toString());
				 
			 DoubleVector targetWord=(DoubleVector) lsa.getVector(t_word);
			
			 for (String word : getRemainingWords(t_word)) {
			 double sim=Similarity.getSimilarity(Similarity.SimType.COSINE,lsa.getVector(word),targetWord); 
			 SimpleEdge cle = wbig.getEdge(word, t_word);
			 cosinusSimilarityMap.put(cle, sim);
			 }
			 
			 }
			  
			} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
	}

	public Map<SimpleEdge, Double> getCosinusSimilarityMap() {
		// TODO Auto-generated method stub
		return cosinusSimilarityMap;
	}
	
	public List<String> getRemainingWords(String word){
		
		List<String> remainingWords = new ArrayList<String>();
		
		for (String w : lsa.getWords()) {
			 if(! w.equals(word)){
				 remainingWords.add(w);
			 }	 
		 }
		
		 return remainingWords;
	}

}
