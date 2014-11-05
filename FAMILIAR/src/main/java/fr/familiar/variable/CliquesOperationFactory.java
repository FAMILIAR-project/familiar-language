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
package fr.familiar.variable;

import java.util.Collection;
import java.util.Set;

import fr.familiar.operations.CliquesComputation;
import fr.familiar.operations.CliquesOperation;
import fr.familiar.operations.CliquesOperationBDD;
import fr.familiar.operations.CliquesOperationSATWithBDDSatisfiabilityChecking;

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
