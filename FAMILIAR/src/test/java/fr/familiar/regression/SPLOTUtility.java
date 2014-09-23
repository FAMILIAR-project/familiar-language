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

package fr.familiar.regression;

import splar.core.heuristics.FTPreOrderSortedECTraversalHeuristic;
import splar.core.heuristics.VariableOrderingHeuristic;
import splar.core.heuristics.VariableOrderingHeuristicsManager;
import splar.plugins.reasoners.bdd.javabdd.FMReasoningWithBDD;
import splar.plugins.reasoners.bdd.javabdd.ReasoningWithBDD;
import splar.plugins.reasoners.sat.sat4j.FMReasoningWithSAT;
import fr.familiar.fm.FMLFormula;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.FMLShellConfiguration;
import fr.familiar.operations.MyFMReasoningWithSAT;

public class SPLOTUtility {

	private final static String SOLVER_NAME = "MiniSAT"; // "Default" ; //

	public static ReasoningWithBDD getSPLOTBDDReasoner(
			splar.core.fm.FeatureModel featureModel) {
		// create BDD variable order heuristic
		new FTPreOrderSortedECTraversalHeuristic("Pre-CL-MinSpan",
				featureModel, FTPreOrderSortedECTraversalHeuristic.FORCE_SORT);
		VariableOrderingHeuristic heuristic = VariableOrderingHeuristicsManager
				.createHeuristicsManager().getHeuristic("Pre-CL-MinSpan");

		// BDD construction parameters
		// - Tuning this parameters can be tricky at times and may require
		// playing a bit
		// - For the purpose of this example let's assume "large enough" values
		//int bddNodeNum = 10000; // sets the initial size of the BDD table
		//int bddCacheSize = 10000; // sets the size of the BDD cache table

		// Scalability Experiments of Mendonca
		int bddNodeNum = FMLShellConfiguration.getBDDSPLOTnodes() ;
	    int bddCacheSize = FMLShellConfiguration.getBDDSPLOTcache() ; // 

		// Creates the BDD reasoner
		ReasoningWithBDD reasoner = new FMReasoningWithBDD(featureModel,
				heuristic, bddNodeNum, bddCacheSize, 60000, "pre-order");

		// Initialize the reasoner (BDD is created at this moment)
		try {
			reasoner.init();
		} catch (Exception e) {
			FMLShell.getInstance().setError(
					"Unable to init the SPLOT/SPLAR reasoner: "
							+ e.getMessage());
			return null;
		}
		return reasoner;
	}

	public static FMReasoningWithSAT getSPLOTSATReasoner(
			splar.core.fm.FeatureModel fmSplot) {
		// SAT reasoner construction parameters
		// - "MiniSAT" - name of the SAT4J solver used
		// - Timeout parameter
		int SATtimeout = (int) FMLFormula.SAT_TIMEOUT ; 
		FMReasoningWithSAT reasoner = new MyFMReasoningWithSAT(SOLVER_NAME,
				fmSplot, SATtimeout);

		// Initialize the reasoner
		try {
			reasoner.init();
		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to load the SPLOT SAT reasoner " + e);
			return null;
		}

		return reasoner;
	}

}
