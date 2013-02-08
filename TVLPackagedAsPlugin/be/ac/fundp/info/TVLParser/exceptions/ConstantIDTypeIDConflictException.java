package be.ac.fundp.info.TVLParser.exceptions;

public class ConstantIDTypeIDConflictException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8627996182893957176L;

	public ConstantIDTypeIDConflictException() {
		super();
	}

	public ConstantIDTypeIDConflictException(String message) {
		super(message);
	}

	public ConstantIDTypeIDConflictException(Exception source) {
		super(source);
	}

}
