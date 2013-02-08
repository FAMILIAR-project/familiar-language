package be.ac.fundp.info.TVLParser.exceptions;

public class BadUseOfReservedWordException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8990227563207973046L;

	public BadUseOfReservedWordException() {
		super();
	}

	public BadUseOfReservedWordException(String message) {
		super(message);
	}

	public BadUseOfReservedWordException(Exception source) {
		super(source);
	}

}
