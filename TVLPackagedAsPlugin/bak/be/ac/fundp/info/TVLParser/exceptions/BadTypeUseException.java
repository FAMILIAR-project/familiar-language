package be.ac.fundp.info.TVLParser.exceptions;

public class BadTypeUseException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6135905807615788727L;

	public BadTypeUseException() {
		super();
	}

	public BadTypeUseException(String message) {
		super(message);
	}

	public BadTypeUseException(Exception source) {
		super(source);
	}

}
