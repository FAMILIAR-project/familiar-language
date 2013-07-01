package be.ac.fundp.info.TVLParser.exceptions;

public class BadCardinalityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -780137742284028378L;

	public BadCardinalityException() {
		super();
	}

	public BadCardinalityException(String message) {
		super(message);
	}

	public BadCardinalityException(Exception source) {
		super(source);
	}
}
