package be.ac.fundp.info.TVLParser.exceptions;

public class TypeNotDefinedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8227934404286511909L;

	public TypeNotDefinedException() {
		super();
	}

	public TypeNotDefinedException(String message) {
		super(message);
	}

	public TypeNotDefinedException(Exception source) {
		super(source);
	}

}
