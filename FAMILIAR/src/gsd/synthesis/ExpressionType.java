/**
 *
 */
package gsd.synthesis;

public enum ExpressionType {
	AND("&"), OR("|"), NOT("!"), IMPLIES("->"), IFF("<->"), FEATURE,
	TRUE(FeatureGraphFactory.DEFAULT_TOP_STRING),
	FALSE(FeatureGraphFactory.DEFAULT_BOTTOM_STRING);

	private ExpressionType() {
		this.symbol = null;
	}

	private ExpressionType(String symbol) {
		this.symbol = symbol;
	}

	private String symbol = null;

	public String toString() {
		return symbol == null ? super.toString() : symbol;
	}
}