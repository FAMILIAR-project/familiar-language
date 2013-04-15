package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.collect.Lists;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * Compute the edit distance in terms of swap and move operations 
 * move : define a new parent for a set of sibling features
 * swap : exchange two features in an reversed ancestor/descendant relation
 * 
 * @author gbecan
 */
public class RefactoringEditDistance implements FMEditDistanceMetric {

	
	@Override
	public int editDistance(FeatureModelVariable fm, FeatureModelVariable reference) {
		int distance = 0;

		FeatureGraph<String> fmHierarchy = fm.getFm().getDiagram().clone();
		FeatureGraph<String> referenceHierarchy = reference.getFm().getDiagram();

		HashMap<FeatureNode<String>, Set<FeatureNode<String>>> descendants = 
				new HashMap<FeatureNode<String>, Set<FeatureNode<String>>>();

		// Compute descendants of each feature in fm2
		for (FeatureNode<String> feature : referenceHierarchy.vertices()) {
			descendants.put(feature, referenceHierarchy.descendants(feature));
		}

		// Compute swaps
		boolean hasSwaps = true;
		while (hasSwaps) {
			// Search for possible swaps
			PriorityQueue<Swap> swaps = findSwaps(fmHierarchy, descendants);

			// Perform the largest swap
			if (!swaps.isEmpty()) {
				Swap swap = swaps.peek();
				swap.perform();
				distance++;
			} else {
				hasSwaps = false;
			}
			
		}

		// Compute moves
		List<Move> moves = performMoves(fmHierarchy, referenceHierarchy);
		for (Move move : moves) {
			move.perform();
			distance++;
		}

		return distance;
	}

	/**
	 * Find possible swaps in the hierarchy
	 * @param hierarchy
	 * @param descendants
	 * @return
	 */
	private PriorityQueue<Swap> findSwaps(FeatureGraph<String> hierarchy,
			HashMap<FeatureNode<String>, Set<FeatureNode<String>>> descendants) {

		PriorityQueue<Swap> swaps = new PriorityQueue<Swap>();
		LinkedList<FeatureNode<String>> nodesToVisit = new LinkedList<FeatureNode<String>>();
		nodesToVisit.push(hierarchy.getTopVertex());
		LinkedList<FeatureNode<String>> ancestorsInFM1 = new LinkedList<FeatureNode<String>>();

		while (!nodesToVisit.isEmpty()) {
			FeatureNode<String> feature = nodesToVisit.pop();

			if (feature != null) {
				if (!feature.isTop()) {
					// Detect swaps
					Set<FeatureNode<String>> descendantsInFM2 = descendants.get(feature);
					int distance = 1;
					for (FeatureNode<String> ancestorInFM1 : ancestorsInFM1) {
						if (descendantsInFM2.contains(ancestorInFM1)) {
							swaps.add(new Swap(hierarchy, feature, ancestorInFM1, distance));
						}
						distance++;
					}

				}

				// Handle recursivity
				nodesToVisit.push(null);
				for (FeatureNode<String> child : hierarchy.children(feature)) {
					nodesToVisit.push(child);
				}
				ancestorsInFM1.push(feature);
				
			} else {
				ancestorsInFM1.pop();
			}
		}

		return swaps;
	}
	
	/**
	 * Compute every needed moves to get the reference hierarchy from the given hierarchy
	 * @param fmHierarchy
	 * @param referenceHierarchy 
	 * @return sorted list of moves to perform
	 */
	private List<Move> performMoves(FeatureGraph<String> fmHierarchy, FeatureGraph<String> referenceHierarchy) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		LinkedList<FeatureNode<String>> nodesToVisit = new LinkedList<FeatureNode<String>>();
		nodesToVisit.add(fmHierarchy.getTopVertex());
		
		while (!nodesToVisit.isEmpty()) {
			FeatureNode<String> feature = nodesToVisit.pop();
			Set<FeatureNode<String>> children = fmHierarchy.children(feature);
			
			if (!children.isEmpty()) {
				nodesToVisit.addAll(children);
				
				// Select wrong children
				Set<FeatureNode<String>> wrongChildren = new HashSet<FeatureNode<String>>();
				FeatureNode<String> target = referenceHierarchy.findVertex(feature.getFeature());
				
				for (FeatureNode<String> child : children) {
					FeatureNode<String> source = referenceHierarchy.findVertex(child.getFeature());
					if (!referenceHierarchy.containsEdge(source, target, FeatureEdge.HIERARCHY)) {
						wrongChildren.add(child);
					}
				}
				
				// Compute groups of wrong children that are siblings in the reference hierarchy
				HashMap<FeatureNode<String>, Set<FeatureNode<String>>> wrongChildrenGroups = 
						new HashMap<FeatureNode<String>, Set<FeatureNode<String>>>();
				for (FeatureNode<String> wrongChild : wrongChildren) {
					FeatureNode<String> newParent = referenceHierarchy.parents(wrongChild).iterator().next();
					if (wrongChildrenGroups.containsKey(newParent)) {
						wrongChildrenGroups.get(newParent).add(wrongChild);
					} else {
						HashSet<FeatureNode<String>> wrongChildrenSiblings = new HashSet<FeatureNode<String>>();
						wrongChildrenSiblings.add(wrongChild);
						wrongChildrenGroups.put(newParent, wrongChildrenSiblings);
					}
				}
				
				// Create moves
				for (FeatureNode<String> newParent : wrongChildrenGroups.keySet()) {
					Set<FeatureNode<String>> childrenToMove = wrongChildrenGroups.get(newParent);
					FeatureNode<String> firstChildToMove= childrenToMove.iterator().next();
					FeatureNode<String> oldParent = fmHierarchy.parents(firstChildToMove).iterator().next();
					Move move = new Move(fmHierarchy, childrenToMove, oldParent, newParent);
					moves.add(move);
				}
			}
		}
		
		return Lists.reverse(moves); // the list is reversed because the moves must be performed from leaves to root
	}
	
}
