package be.ac.fundp.info.TVLParser.exceptions;

public class BadGroupCardinalityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -780137742284028378L;

	public BadGroupCardinalityException() {
		super();
	}

	public BadGroupCardinalityException(String message) {
		super(message);
	}

	public BadGroupCardinalityException(Exception source) {
		super(source);
	}
}
