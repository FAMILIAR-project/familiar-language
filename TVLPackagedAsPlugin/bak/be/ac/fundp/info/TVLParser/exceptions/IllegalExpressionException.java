package be.ac.fundp.info.TVLParser.exceptions;

public class IllegalExpressionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8786407375220857271L;

	public IllegalExpressionException() {
		super();
	}

	public IllegalExpressionException(String message) {
		super(message);
	}

	public IllegalExpressionException(Exception source) {
		super(source);
	}

}
