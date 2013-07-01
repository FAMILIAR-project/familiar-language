package be.ac.fundp.info.TVLParser.exceptions;

public class IllegalIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3255550772611609621L;

	public IllegalIDException() {
		super();
	}

	public IllegalIDException(String message) {
		super(message);
	}

	public IllegalIDException(Exception source) {
		super(source);
	}
}

