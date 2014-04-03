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

package fr.familiar.test.heuristics;

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

