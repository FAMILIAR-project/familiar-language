/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.operations;

/**
 * @author macher
 *
 * Given a set of configurations, s
 * we want to synthesise the following:
 * FM = FD ^ cst
 * 
 * How to compute, encode, represent and manage the evolution of cst is not trivial...
 * So we propose different strategies
 */
public enum FDOverApproximationStrategy {
	
	
	/**
	 * we compute the logical representation of cst and encode it as a BDD, 
	 * but without rendering it as a set of readable constraints 
	 */
	LAZY_WITH_DIFF_FLA,
	
	
	/**
	 * we compute the logical representation of cst and encode it as a BDD, and we render it as a set of constraints 
	 * it is not efficient on a large scale
	 * it also leads to a huge set of disjunctive clauses (readability?) 
	 */
	BRUTE_FORCE_ENUMERATION, 
	
	
	/**
	 * we do not compute any logical representation
	 * we consider that FD and the set of configurations represented by FM is sufficient
	 */
	SYNCHRONIZED_FLA,

}
