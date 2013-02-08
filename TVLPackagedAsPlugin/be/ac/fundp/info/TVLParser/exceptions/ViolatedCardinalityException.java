package be.ac.fundp.info.TVLParser.exceptions;

public class ViolatedCardinalityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2585663803839034548L;

	public ViolatedCardinalityException() {
		super();
	}

	public ViolatedCardinalityException(String message) {
		super(message);
	}

	public ViolatedCardinalityException(Exception source) {
		super(source);
	}
}
