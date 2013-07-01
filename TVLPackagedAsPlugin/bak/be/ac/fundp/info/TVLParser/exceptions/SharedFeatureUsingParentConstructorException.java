package be.ac.fundp.info.TVLParser.exceptions;

public class SharedFeatureUsingParentConstructorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -183097635149452241L;

	public SharedFeatureUsingParentConstructorException() {
		super();
	}

	public SharedFeatureUsingParentConstructorException(String message) {
		super(message);
	}

	public SharedFeatureUsingParentConstructorException(Exception source) {
		super(source);
	}
}
