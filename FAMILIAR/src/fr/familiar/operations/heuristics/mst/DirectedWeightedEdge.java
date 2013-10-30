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
