/**
 * 
 */
package fr.familiar.operations;

import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;

import java.util.Set;


/**
 * @author macher1
 *
 */
public class IGBuilderDomain extends IGBuilder {
	
	
	  public static <T extends Comparable<T>> ImplicationGraph<T> build (Formula<String> formula, BDDBuilder<String> bddBuilder, Set<String> fts) {
		  return (ImplicationGraph<T>) build(formula.getBDD(), bddBuilder, fts);
	  }

}
