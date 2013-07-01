package be.ac.fundp.info.TVLParser.exceptions;

public class UnsatisfiableModelException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9168473023317064894L;

	public UnsatisfiableModelException() {
		super();
	}

	public UnsatisfiableModelException(String message) {
		super(message);
	}

	public UnsatisfiableModelException(Exception source) {
		super(source);
	}
}
