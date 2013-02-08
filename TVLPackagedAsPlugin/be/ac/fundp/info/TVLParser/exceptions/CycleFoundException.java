package be.ac.fundp.info.TVLParser.exceptions;

public class CycleFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6020429092279578556L;

	public CycleFoundException() {
		super();
	}

	public CycleFoundException(String message) {
		super(message);
	}

	public CycleFoundException(Exception source) {
		super(source);
	}

}
