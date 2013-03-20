package be.ac.fundp.info.TVLParser.exceptions;

public class ExpressionOutOfBoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2369931599583358791L;

	public ExpressionOutOfBoundException() {
		super();
	}

	public ExpressionOutOfBoundException(String message) {
		super(message);
	}

	public ExpressionOutOfBoundException(Exception source) {
		super(source);
	}
}
