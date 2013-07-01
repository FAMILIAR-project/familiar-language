package be.ac.fundp.info.TVLParser.exceptions;

public class AmbiguousReferenceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3800821054833037179L;

	public AmbiguousReferenceException() {
		super();
	}

	public AmbiguousReferenceException(String message) {
		super(message);
	}

	public AmbiguousReferenceException(Exception source) {
		super(source);
	}

}
