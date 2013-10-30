package fr.familiar.operations.heuristics.mst;

import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class OptimumBranchingFinder<V> {

	// Contains the optimum branching
	private ImplicationGraph<Integer> h;

	// Result of the algorithm
	private ImplicationGraph<V> optimumBranching;

	// Gives the root components of G(H) which may have entering edges of positive value
	private LinkedList<Integer> roots;

	// Initialize strongly connected components
	private DisjointSetCollection sccSet;

	// Initialize weakly connected components
	private DisjointSetCollection wccSet;

	// Gives the root components of G(H) with no entering edges of positive value
	private Set<Integer> rset;

	// Gives the unique edge in H entering a strongly connected component
	private DirectedWeightedEdge[] enter;

	// Gives, for each SCC the root vertex which algorithm ROOT will select
	private Integer[] min;

	// Priority queues that keep track of the edges entering each strongly connected component
	private PriorityQueueCollection incomingEdges;

	// Forest helping in extracting the optimum branching
	private ImplicationGraph<String> forest;

	// Pointers to the leaves of the forest
	private Map<Integer, String> pointers;

	// Temporary variable helping the construction of the forest
	private HashMap<Integer, ArrayList<DirectedWeightedEdge>> cycle;

	private ArrayList<V> verticesValues;


	/**
	 * Find the optimum branching (maximum spanning tree) of a weighted implication graph
	 * @param graph : a weighted implication graph with weights greater than or equal to zero
	 * @return an implication graph representing the optimum branching 
	 */
	public ImplicationGraph<V> findOptimumBranching(WeightedImplicationGraph<V> big) {
		init(big);
		tarjanAlgorithm();

		// Compute R
		LinkedList<Integer> r = new LinkedList<Integer>();
		for (Integer vertex : rset) {
			r.add(min[vertex]);
		}

		extractOptimumBranching(r);

		return optimumBranching;
	}

	/**
	 * Initialize data structures according to Tarjan's paper
	 * @param big
	 * @param heuristic
	 */
	private void init(WeightedImplicationGraph<V> big) {
		int nbOfVertices = big.vertices().size();
		h = new ImplicationGraph<Integer>();
		optimumBranching = new ImplicationGraph<V>();
		roots = new LinkedList<Integer>();
		sccSet = new DisjointSetCollection();
		wccSet = new DisjointSetCollection();
		rset = new HashSet<Integer>();
		enter = new DirectedWeightedEdge[nbOfVertices];
		min = new Integer[nbOfVertices];
		DirectedWeightedEdge dummyEdge = new DirectedWeightedEdge(null, null, Double.NEGATIVE_INFINITY);
		incomingEdges = new PriorityQueueCollection(dummyEdge);
		forest = new ImplicationGraph<String>();
		pointers = new HashMap<Integer, String>();
		cycle = new HashMap<Integer, ArrayList<DirectedWeightedEdge>>();
		verticesValues = new ArrayList<V>();

		int index = 0;
		for (V vertex : big.vertices()) {
			verticesValues.add(vertex);
			incomingEdges.create(index);
			sccSet.create(index);
			wccSet.create(index);
			enter[index] = dummyEdge;
			roots.add(index);
			min[index] = index;
			h.addVertex(index);
			optimumBranching.addVertex(vertex);
			index++;
		}

		// Initialize priority queues of incomingEdges while inverting the implication graph
		for (SimpleEdge edge : big.edges()) {
			V source = big.getTarget(edge); // Inversion here
			V target = big.getSource(edge); // ... and here
			double weight = big.getEdgeWeight(edge);
			Integer sourceIndex = verticesValues.indexOf(source);
			Integer targetIndex = verticesValues.indexOf(target);
			incomingEdges.put(targetIndex, new DirectedWeightedEdge(sourceIndex, targetIndex, weight));
		}
	}

	/**
	 * Compute optimum branching with Tarjan's algorithm and corrections by Camerini et al.
	 */
	private void tarjanAlgorithm() {
		while (!roots.isEmpty()) {
			Integer scc = roots.pop();
			DirectedWeightedEdge maxEdge = incomingEdges.max(scc);


			if (maxEdge.getWeight() < 0) {
				rset.add(scc);

			} else if(sccSet.find(maxEdge.getSource()).equals(scc)) {
				roots.add(scc);

			} else {
				// Update the forest
				String maxEdgeName = maxEdge.getSource() + "/" + maxEdge.getTarget(); 
				forest.addVertex(maxEdgeName);

				if(cycle.get(scc) == null || cycle.get(scc).size() == 0) {
					pointers.put(maxEdge.getTarget(), maxEdgeName);
				} else {
					for (DirectedWeightedEdge edge : cycle.get(scc)) {
						String vertexName = edge.getSource() + "/" + edge.getTarget();
						forest.addEdge(vertexName, maxEdgeName);
					}
				}

				h.addEdge(maxEdge.getSource(), maxEdge.getTarget());
				Integer wFindS = wccSet.find(maxEdge.getSource());
				Integer wFindT = wccSet.find(maxEdge.getTarget());
				if (!wFindS.equals(wFindT)) {
					wccSet.union(wFindS, wFindT);
					enter[scc] =  maxEdge;
				} else {
					double val = Double.POSITIVE_INFINITY;
					Integer vertex = -1;
					DirectedWeightedEdge edge = maxEdge;

					ArrayList<DirectedWeightedEdge> sequence = new ArrayList<DirectedWeightedEdge>();
					while (edge.getSource() != null && edge.getTarget() != null) {
						if (edge.getWeight() < val) {
							val = edge.getWeight();
							vertex = sccSet.find(edge.getTarget());
						}
						sequence.add(edge);
						edge = enter[sccSet.find(edge.getSource())];
					}
					incomingEdges.add(scc, val - maxEdge.getWeight());
					if (vertex != -1) {
						min[scc] = min[vertex];						
					}

					edge = enter[sccSet.find(maxEdge.getSource())];

					while (edge.getSource() != null && edge.getTarget() != null) {
						incomingEdges.add(sccSet.find(edge.getTarget()), val - edge.getWeight());
						incomingEdges.union(scc, sccSet.find(edge.getTarget()));
						sccSet.union(scc, sccSet.find(edge.getTarget()));
						edge = enter[sccSet.find(edge.getSource())]; 
					}

					roots.add(scc);
					cycle.put(scc, sequence);
				}
			}
		}

	}

	/**
	 * Extract the optimum branching from h and his related forest (See camerini1979note for details)
	 * @param r
	 * @return
	 */
	private void extractOptimumBranching(LinkedList<Integer> r) {
		while (!r.isEmpty() || !forest.roots().isEmpty()) {
			Integer vertex;
			if (!r.isEmpty()) {
				vertex = r.pop();
			} else {
				String rootNodeName = forest.roots().iterator().next();
				Integer source = Integer.parseInt(rootNodeName.split("/")[0]);
				Integer target = Integer.parseInt(rootNodeName.split("/")[1]);
				optimumBranching.addEdge(verticesValues.get(target), verticesValues.get(source));
				vertex = target;
			}

			deletePathInForest(forest, pointers.get(vertex));
		}
	}

	/**
	 * Delete the path from the leaf to a root node in the forest
	 * @param forest
	 * @param leaf
	 * @return
	 */
	private void deletePathInForest(ImplicationGraph<String> forest, String leaf) {
		String node = leaf;
		while (node != null) {
			Set<String> parents = forest.parents(node);
			String parent = parents.iterator().hasNext() ? parents.iterator().next() : null;
			forest.removeVertex(node);
			node = parent;
		} 
	}

}
