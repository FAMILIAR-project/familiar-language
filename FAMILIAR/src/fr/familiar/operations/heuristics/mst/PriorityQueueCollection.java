package fr.familiar.operations.heuristics.mst;

import java.util.HashMap;
import java.util.PriorityQueue;

public class PriorityQueueCollection {

	private HashMap<Integer, PriorityQueue<DirectedWeightedEdge>> queues;
	private DirectedWeightedEdge dummyEdge;
	
	public PriorityQueueCollection(DirectedWeightedEdge dummyEdge) {
		queues = new HashMap<Integer, PriorityQueue<DirectedWeightedEdge>>();
		this.dummyEdge = dummyEdge;
	}
	
	
	/**
	 * Create a queue
	 * @param name
	 */
	public void create(Integer name) {
		queues.put(name, new PriorityQueue<DirectedWeightedEdge>());
	}
	
	/**
	 * Put an element in the queue
	 * @param queue
	 * @param element
	 */
	public void put(Integer queue, DirectedWeightedEdge element) {
		queues.get(queue).add(element);
	}
	
	/**
	 * Adds the elements of queueB to queueA
	 * @param queueA
	 * @param queueB
	 */
	public void union(Integer queueA, Integer queueB) {
		queues.get(queueA).addAll(queues.get(queueB));
	}
	
	/**
	 * Returns the largest element in queue, deleting this element from the queue.
	 * If the queue is empty, returns a dummy element
	 * @param queue
	 * @return
	 */
	public DirectedWeightedEdge max(Integer queue) {
		DirectedWeightedEdge max = queues.get(queue).poll();
		if (max == null) {
			return dummyEdge;
		} 
		return max;
	}
	
	/**
	 * Adds a constant to the value of all elements in the queue
	 * @param queue
	 * @param value
	 */
	public void add(Integer queue, double value) {
		for (DirectedWeightedEdge edge : queues.get(queue)) {
			edge.setWeight(edge.getWeight() + value);
		}
	}
	
}
