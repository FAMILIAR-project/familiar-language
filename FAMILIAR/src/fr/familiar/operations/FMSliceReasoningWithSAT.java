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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;
import org.xtext.example.mydsl.fML.SliceMode;

import splar.core.constraints.CNFClause;
import splar.core.constraints.CNFLiteral;
import splar.core.constraints.PropositionalFormula;
import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModel;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.SolitaireFeature;
import splar.plugins.reasoners.sat.sat4j.FMReasoningWithSAT;

public class FMSliceReasoningWithSAT extends FMReasoningWithSAT {

	private Collection<String> _fts;
	private SliceMode _mode;

	public FMSliceReasoningWithSAT(String solverName, FeatureModel fmSplot,
			int sATtimeout, Collection<String> fts, SliceMode mode) {
		super(solverName, fmSplot, sATtimeout);
		_fts = fts;
		_mode = mode;
	}

	@Override
	protected void addSolverClauses(ISolver solver) throws Exception {
		addHierarchySolverClauses(solver);
		// add extra constraints
		for (PropositionalFormula formula : featureModel.getConstraints()) {
			for (CNFClause clause : formula.toCNFClauses()) {
				IVecInt vectInt = new VecInt(clause.countLiterals());
				for (CNFLiteral literal : clause.getLiterals()) {
					int signal = literal.isPositive() ? 1 : -1;
					int varID = getVariableIndex(literal.getVariable().getID());
					vectInt.push(signal * varID);
				}
				solver.addClause(vectInt);
				// System.out.println("EC: " + vectInt);
			}
		}
	}

	private void addHierarchySolverClauses(ISolver solver) throws Exception {
		// release all data attached to feature model nodes
		featureModel.resetNodesAttachedData();

		solver.newVar(featureModel.countNodes());

		// count features (variables)
		int countFeatures = 1;

		// root is always TRUE
		IVecInt vectInt = new VecInt(1);
		vectInt.push(countFeatures);
		solver.addClause(vectInt);
		// System.out.println(vectInt);

		FeatureTreeNode rootNode = featureModel.getRoot();
		rootNode.attachData(new Integer(countFeatures));

		Vector<FeatureTreeNode> nodes = new Vector<FeatureTreeNode>();
		nodes.add(rootNode);

		// Perform a Breadht First Traversal of the feature tree
		while (nodes.size() > 0) {

			// consumes first node
			FeatureTreeNode curNode = nodes.firstElement();
			nodes.remove(curNode);

			if (curNode != null) {
				int parentVarID = 0;
				int count = curNode.getChildCount();
				if (count > 0) {
					for (int i = 0; i < count; i++) {
						FeatureTreeNode childNode = ((FeatureTreeNode) curNode
								.getChildAt(i));
						int childVarID = 0;
						// It's a solitaire feature
						if (childNode instanceof SolitaireFeature) {
							parentVarID = (Integer) curNode.getAttachedData();
							childVarID = ++countFeatures;
							childNode.attachData(childVarID);
							nodes.add(childNode);

							SolitaireFeature solitaireNode = (SolitaireFeature) childNode;
							// if node is optional relation is
							// "child implies parent"
							vectInt = new VecInt(2);
							vectInt.push(parentVarID);
							vectInt.push(-childVarID);
							solver.addClause(vectInt);
							// System.out.println(vectInt);
							// if mandatory, "parent also implies child"
							if (!solitaireNode.isOptional()) {
								vectInt = new VecInt(2);
								vectInt.push(-parentVarID);
								vectInt.push(childVarID);
								solver.addClause(vectInt);
								// System.out.println(vectInt);
							}
						}
						// It's a feature group
						else if (childNode instanceof FeatureGroup) {
							FeatureGroup fgNode = (FeatureGroup) childNode;
							parentVarID = (Integer) ((FeatureTreeNode) fgNode
									.getParent()).getAttachedData();
							int countGroupedNodes = fgNode.getChildCount();

							// (not P or g1 or g2 or ... or gn)
							vectInt = new VecInt(countGroupedNodes + 1);
							vectInt.push(-parentVarID);

							// (not g1 or P) (not g2 or P) ... (not gn or P)
							IVecInt vectIntGrpOR;

							for (int j = 0; j < countGroupedNodes; j++) {
								FeatureTreeNode groupedNode = (FeatureTreeNode) fgNode
										.getChildAt(j);
								childVarID = ++countFeatures;
								groupedNode.attachData(childVarID);
								nodes.add(groupedNode);
								vectInt.push(childVarID);
								vectIntGrpOR = new VecInt(2);
								vectIntGrpOR.push(parentVarID);
								vectIntGrpOR.push(-childVarID);
								solver.addClause(vectIntGrpOR);
								// System.out.println(vectIntGrpOR);

							}
							solver.addClause(vectInt);
							// System.out.println(vectInt);

							// if it's an exclusive-OR group
							// (not g1 or not g2) (not g1 or not g3) (not g2 or
							// not g3)
							int min = fgNode.getMin();
							int max = fgNode.getMax();
							max = (max == -1 ? countGroupedNodes : max);
							if (min == 1 && max == 1) {
								List<List<Integer>> combinations = new ArrayList<List<Integer>>();
								computeCombinations(combinations,
										countGroupedNodes, 2);
								IVecInt vectIntGrpXOR;
								for (List<Integer> combination : combinations) {
									vectIntGrpXOR = new VecInt(2);
									for (Integer index : combination) {
										vectIntGrpXOR
												.push(-(countFeatures
														- countGroupedNodes + 1 + index));
									}
									solver.addClause(vectIntGrpXOR);
									// System.out.println(vectIntGrpXOR);
								}
							}
							// Implemented on May 6, 2009
							// min > 1 || max < countGroupedNodes
							else if (min > 1 || max < countGroupedNodes) {
								// step 1: from 1 to MIN-1
								// step 2: from MAX+1 to countGroupedNodes
								int startIndex = 0;
								int endIndex = min - 1;
								List<List<Integer>> combinations = new ArrayList<List<Integer>>();
								while (true) {
									// System.out.println("startIndex=" +
									// startIndex +"  endIndex=" + endIndex);
									for (int idx = startIndex; idx < endIndex; idx++) {
										computeCombinations(combinations,
												countGroupedNodes, idx + 1);
										IVecInt vectIntGrp;
										// System.out.println("combination size: "
										// + combinations.size());
										for (List<Integer> combination : combinations) {
											vectIntGrp = new VecInt(
													countGroupedNodes);
											// add negated variables
											// System.out.print(">> ");
											for (Integer index : combination) {
												// System.out.print(-(countFeatures-countGroupedNodes+1+index)
												// + ",");
												vectIntGrp
														.push(-(countFeatures
																- countGroupedNodes
																+ 1 + index));
											}
											// add positive variables
											for (int posIndex = 0; posIndex < countGroupedNodes; posIndex++) {
												if (!combination
														.contains(posIndex)) {
													// System.out.print(countFeatures-countGroupedNodes+1+posIndex
													// + ",");
													vectIntGrp
															.push(countFeatures
																	- countGroupedNodes
																	+ 1
																	+ posIndex);
												}
											}
											// System.out.println("");
											solver.addClause(vectIntGrp);
											// System.out.println(vectIntGrpXOR);
										}
									}
									if (endIndex == countGroupedNodes)
										break;
									startIndex = max;
									endIndex = countGroupedNodes;
								}
							}
							// else (Inclusive-OR grouped) <- addressed by
							// default
						} else {
							// System.out.println("Error: Other type of node!");
						}
					}
				}
			}
		}

		updateVariableMappings();

		// add unit clauses for instantiated variables
		for (FeatureTreeNode node : featureModel.getInstantiatedNodes()) {
			// System.out.println(node + ":" + node.getValue());
			if (!(node instanceof FeatureGroup)) {
				IVecInt vInt = new VecInt(1);
				int index = getVariableIndex(node.getID());
				vInt.push(node.getValue() == 1 ? index : -index);
				solver.addClause(vInt);
				// System.out.println("Adding unit clause for " + node.getID());
			}
		}

	}

}
