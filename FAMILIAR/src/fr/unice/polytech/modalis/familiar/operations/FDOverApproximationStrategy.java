package fr.unice.polytech.modalis.familiar.operations;

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
