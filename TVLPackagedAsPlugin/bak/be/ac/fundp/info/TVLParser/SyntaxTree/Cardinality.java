package be.ac.fundp.info.TVLParser.SyntaxTree;

public class Cardinality {
	
	String min, max;
	
	public Cardinality(String min, String max) {
		this.min = min;
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}
}
