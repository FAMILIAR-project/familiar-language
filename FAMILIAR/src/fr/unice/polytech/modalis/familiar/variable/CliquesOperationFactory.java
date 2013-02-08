package fr.unice.polytech.modalis.familiar.variable;

import java.util.Collection;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.operations.CliquesComputation;
import fr.unice.polytech.modalis.familiar.operations.CliquesOperation;
import fr.unice.polytech.modalis.familiar.operations.CliquesOperationBDD;
import fr.unice.polytech.modalis.familiar.operations.CliquesOperationSATWithBDDSatisfiabilityChecking;

/**
 * @author macher1
 *
 */
public class CliquesOperationFactory {

	public static Collection<Set<String>> mkCliques(FeatureModelVariable fmv,	CliquesComputation cliquesComputationStrategy) {
		
		CliquesOperation cliquesOp = null ; 
		
		if (cliquesComputationStrategy == CliquesComputation.SAT)
			cliquesOp = new CliquesOperationSAT(fmv) ;
		else if (cliquesComputationStrategy == CliquesComputation.BDD)
			cliquesOp = new CliquesOperationBDD(fmv) ;
		else if (cliquesComputationStrategy == CliquesComputation.SAT_WITH_BDD_SATISFIABILITY_CHECKS)
			cliquesOp = new CliquesOperationSATWithBDDSatisfiabilityChecking(fmv) ;
		if (cliquesOp == null)
			return null ; 
		return cliquesOp.cliques() ; 
	}
	
	
	
}
