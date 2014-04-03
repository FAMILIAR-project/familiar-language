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

public class DirectedWeightedEdge implements Comparable<DirectedWeightedEdge>{

	private Integer source;
	private Integer target;
	private double weight;
	
	public DirectedWeightedEdge(Integer source, Integer target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public Integer getSource() {
		return source;
	}

	public Integer getTarget() {
		return target;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(DirectedWeightedEdge o) {
		// Inverted natural ordering
		if (weight < o.getWeight()) {
			return 1;
		} else if (weight == o.getWeight()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return source + " -> " + target + " (" + weight + ")";
	}
	
}
