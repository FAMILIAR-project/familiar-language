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
