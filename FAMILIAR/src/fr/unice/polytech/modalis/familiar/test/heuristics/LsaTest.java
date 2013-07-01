package fr.unice.polytech.modalis.familiar.test.heuristics;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import edu.ucla.sspace.lsa.LatentSemanticAnalysis;



public class LsaTest {
	 private Collection<BufferedReader> documents ;
	 private String[] features= {"drank","foobar","beverage","foobar","sodas","cold","number","cheap","tastes","case" };
	@Test
	public void testImplicationGraphMetrics() throws IOException {
		

		 documents = new ArrayList<BufferedReader>();
	    String[] articles= {"He drank the foobar at the game.","Foobar is the number three beverage.","A case of foobar is cheap compared to other sodas.","Foobar tastes better when cold."};
	    
	    for(String article : articles){
	    InputStream is_article = new ByteArrayInputStream(article.getBytes());
	    System.out.println(is_article.toString());
		BufferedReader br_article = new BufferedReader(new InputStreamReader(is_article));
		 System.out.println(br_article.toString());
		documents.add(br_article);
	    }
	
			try {
				LatentSemanticAnalysis algo= new LatentSemanticAnalysis();
				 for (BufferedReader document : documents) {
				        algo.processDocument(document);
				    }
				 
				  algo.processSpace(System.getProperties());
				  
				  for (String word : algo.getWords()) {
				        System.out.printf("%s maps to %s%n", word, algo.getVector(word).toString());
				    }
				  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	}

