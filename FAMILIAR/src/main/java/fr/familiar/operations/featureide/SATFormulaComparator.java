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

package fr.familiar.operations.featureide;

import java.util.LinkedList;
import java.util.Set;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.SatSolver;
import org.sat4j.specs.TimeoutException;

import com.google.common.collect.Sets;

import fr.familiar.variable.Comparison;


/**
 * Compares two formulas based on a satisfiability solver. The result is a
 * classification of the edit that transforms one model into the second model.
 * 
 * based on Thomas Thuem algorithm (ICSE'09)
 */
public class SATFormulaComparator {

	private long timeout;

	public SATFormulaComparator(long timeout) {
		this.timeout = timeout ;
	}
	public Comparison compare(SATFormula newModel, SATFormula oldModel) {

		Comparison result = null ;
		
		try {
			Set<String> addedFeatures = calculateAddedFeatures(oldModel, newModel);
			Set<String> deletedFeatures = calculateAddedFeatures(newModel, oldModel);
			
			Node oldRoot = createFalseStatementForConcreteVariables(addedFeatures,
					oldModel.getNode());
			Node newRoot = createFalseStatementForConcreteVariables(deletedFeatures,
					newModel.getNode());

			Node oldRootUpdated = removeIdenticalNodes(oldRoot, newRoot);
			Node newRootUpdated = removeIdenticalNodes(newRoot, oldRoot);
			
			
			boolean implies = implies(oldRoot, newRootUpdated);
			
			boolean isImplied = implies(newRoot, oldRootUpdated);
			
			if (implies)
				if (isImplied)
					result = Comparison.REFACTORING;
				else
					result = Comparison.GENERALIZATION;
			else if (isImplied)
				result = Comparison.SPECIALIZATION;
			else
				result = Comparison.ARBITRARY;
		} catch (OutOfMemoryError e) {
			result = null ;
		} catch (TimeoutException e) {
			result = null ;
		} catch (Exception e) {
			e.printStackTrace();
			result = null ;
		}
		return result;
	}

	private Set<String> calculateAddedFeatures(SATFormula oldModel,
			SATFormula newModel) {
		Set<String> addedFeatures = Sets.difference(newModel.getDomain(), oldModel.getDomain());
		return addedFeatures;
	}

	

	private Node createFalseStatementForConcreteVariables(
			Set<String> addedFeatures, Node node) {
		if (addedFeatures.isEmpty())
			return node;
		LinkedList<Node> children = new LinkedList<Node>();
		for (Object var : addedFeatures)
			children.add(new Literal((String) var, false));
		return new And(node, new And(children));
	}

	/**
	 * Removes all child nodes that are contained in the reference node.
	 * 
	 * @param node
	 *            the node to copy and remove from
	 * @param referenceNode
	 *            node that specifies what do remove
	 * @return a copy of the node where some child nodes are not existent
	 */
	private Node removeIdenticalNodes(Node node, Node referenceNode) {
		LinkedList<Node> updatedNodes = new LinkedList<Node>();
		Node[] children = node.getChildren() ;
		if (children == null || children.length == 0)
			return null ; 
			
			
		for (Node child : children)
			if (!containedIn(child, referenceNode.getChildren()))
				updatedNodes.add(child);
		return updatedNodes.isEmpty() ? null : new And(updatedNodes);
	}

	public boolean implies(Node a, Node b)
			throws TimeoutException {
		if (b == null)
			return true;
		Node node = new And(a.clone(), new Not(b.clone()));
		SatSolver solver = new SatSolver(node, timeout);
		boolean valid = !solver.isSatisfiable();
		return valid;

	}

	private boolean containedIn(Node node, Node[] nodes) {
		for (Node child : nodes)
			if (node.equals(child))
				return true;
		return false;
	}




}

