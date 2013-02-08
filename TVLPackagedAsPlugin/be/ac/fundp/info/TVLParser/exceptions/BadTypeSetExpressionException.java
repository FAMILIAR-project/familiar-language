package be.ac.fundp.info.TVLParser.exceptions;

public class BadTypeSetExpressionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8943990893852184332L;

	public BadTypeSetExpressionException() {
		super();
	}

	public BadTypeSetExpressionException(String message) {
		super(message);
	}

	public BadTypeSetExpressionException(Exception source) {
		super(source);
	}
}
