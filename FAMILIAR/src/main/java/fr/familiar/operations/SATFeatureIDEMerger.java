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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

import splar.core.constraints.CNFFormula;
import splar.core.fm.FeatureTreeNode;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class SATFeatureIDEMerger extends SATMerger {

	private ISolver _rsolver = null;

	private Map<Integer, Object> _intToVar = new HashMap<Integer, Object>();

	public SATFeatureIDEMerger() {

	}

	@Override
	public boolean mergeFM(List<FeatureModelVariable> olfms, Mode mode) {

		// TODO -- no side effect (clone first!)

		if (mode == Mode.Diff)
			return mergeDiff(olfms);

		/******** negated variables **********/

		// first pass: collect all features in each FM

		Set<String> allFeatures = new HashSet<String>();
		for (FeatureModelVariable fmv : olfms) {
			Set<String> features = fmv.getFm().getDiagram().features();
			allFeatures.addAll(features);
		}

		// second pass: for each FM fmm, negate features that are in other FMs
		// but not in fmm
		for (FeatureModelVariable fmv : olfms) {
			FeatureModel<String> fm = fmv.getFm();
			Set<String> features = fmv.getFm().getDiagram().features();
			Set<String> diff = new HashSet<String>(allFeatures);
			diff.removeAll(features);

			for (String featNotIncluded : diff) {
				Expression<String> nf = new Expression<String>(featNotIncluded);
				nf = nf.not();

				// optional feature of the root
				FeatureNode<String> ftSource = new FeatureNode<String>(
						featNotIncluded);
				fm.getDiagram().addVertex(ftSource);
				String rootName = fmv.root().name();
				FeatureNode<String> ftTarget = fm.getDiagram().findVertex(
						rootName);
				fm.getDiagram().addEdge(ftSource, ftTarget,
						FeatureEdge.HIERARCHY);
				// end

				fm.addConstraint(nf);
			}

			assert (fmv.countingSATSPLOT() == fmv.counting (CountingStrategy.BDD_FML));

		}

		// we impose a mapping var to int
		// it is calculated here

		// we first collect IDs of SPLOT

		Set<String> allSPLOTFeatures = new HashSet<String>();
		for (FeatureModelVariable fmv : olfms) {
			Collection<FeatureTreeNode> ftNodes = fmv.toSPLOT().getNodes();
			for (FeatureTreeNode featureTreeNode : ftNodes) {
				allSPLOTFeatures.add(featureTreeNode.getID());
			}
		}
		System.err.println("allSPLOTFeatures=" + allSPLOTFeatures);
		Map<String, Integer> imposedIntToVar = new HashMap<String, Integer>();
		int id = 1;
		for (String ft : allSPLOTFeatures) {
			imposedIntToVar.put(ft, new Integer(id++));
		}

		/****** merging process *********/
		if (mode == Mode.Intersection) {
			// false | A = A

			// add clauses of isolver to rsolver ;

			try {

				/*
				 * Map<String, Integer> ft2Var = new HashMap<String, Integer>();
				 * int i = 1; ft2Var.put(FeatureGraphFactory.DEFAULT_TOP_STRING,
				 * i++); ft2Var.put(FeatureGraphFactory.DEFAULT_BOTTOM_STRING,
				 * i++); for (String ft : allFeatures) { ft2Var.put(ft, i++); }
				 * SATTransformer<String> satTransf = new
				 * SATTransformer<String>(ft2Var);
				 */
				CNFFormula2SAT satTransf = new CNFFormula2SAT(imposedIntToVar);

				_intToVar = new HashMap<Integer, Object>();
				Set<String> keys = imposedIntToVar.keySet();
				for (String key : keys) {
					Integer keyID = imposedIntToVar.get(key);
					String actualKeyName = key;
					for (FeatureModelVariable fmv : olfms) {
						Collection<FeatureTreeNode> ftNodes = fmv.toSPLOT()
								.getNodes();
						for (FeatureTreeNode featureTreeNode : ftNodes) {
							if (featureTreeNode.getID().equals(key))
								actualKeyName = featureTreeNode.getName();
						}
					}
					_intToVar.put(keyID, actualKeyName);
				}

				_rsolver = SolverFactory.instance().createSolverByName(
						"MiniSAT");
				_rsolver.newVar(imposedIntToVar.size() + 1);

				for (FeatureModelVariable fmv : olfms) {

					// FMReasoningWithSAT satReasoner =
					// fmv.getSPLOTSATReasoner(imposedIntToVar) ;
					assert (fmv.countingSATSPLOT() == fmv.counting (CountingStrategy.BDD_SPLOT));
					CNFFormula cnf = fmv.toSPLOT().FM2CNF();
					IVec<IVecInt> clauses = satTransf.mkClauses(cnf);

					// IVec<IVecInt> clauses = satTransf.mkClauses(fmv.getFm());
					Iterator<IVecInt> it = clauses.iterator();
					while (it.hasNext()) {
						IVecInt clause = it.next();
						_rsolver.addClause(clause);

					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		} else if (mode == Mode.StrictUnion) {
			// true & A = A
			FMLShell.getInstance().printTODO("Intersection SAT merging");

			// actually difficult due to negation of clauses!
			// i.e., CNF is required
			// so fm1 v fm2 should be transformed in ~ (~fm1 ^ ~fm2)
			// fm1 v fm2 v fm3 ....

			return false;
		}

		return true;

	}

	private Map<Integer, Object> tr(Map<String, Integer> imposedIntToVar) {
		Map<Integer, Object> rmap = new HashMap<Integer, Object>();
		Set<String> keys = imposedIntToVar.keySet();
		for (String key : keys) {
			rmap.put(imposedIntToVar.get(key), key);
		}
		return rmap;
	}

	private boolean mergeDiff(List<FeatureModelVariable> olfms) {
		FMLShell.getInstance().printTODO("Diff SAT merging");
		return false;
	}

	public ISolver getSolver() {
		return _rsolver;
	}

	public Map<Integer, Object> getIntToVar() {
		return _intToVar;
	}

}
