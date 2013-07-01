package be.ac.fundp.info.TVLParser.exceptions;

public class IllegalConstraintException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9120634885099051578L;

	public IllegalConstraintException() {
		super();
	}

	public IllegalConstraintException(String message) {
		super(message);
	}

	public IllegalConstraintException(Exception source) {
		super(source);
	}

}
