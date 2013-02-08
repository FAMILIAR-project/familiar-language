package be.ac.fundp.info.TVLParser.exceptions;

public class IllegalStructTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2466056227489088880L;

	public IllegalStructTypeException() {
		super();
	}

	public IllegalStructTypeException(String message) {
		super(message);
	}

	public IllegalStructTypeException(Exception source) {
		super(source);
	}
}
