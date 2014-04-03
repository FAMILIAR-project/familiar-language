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
