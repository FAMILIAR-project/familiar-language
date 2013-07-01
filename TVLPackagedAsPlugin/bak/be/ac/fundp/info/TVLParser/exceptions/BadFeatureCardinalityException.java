package be.ac.fundp.info.TVLParser.exceptions;

public class BadFeatureCardinalityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8449255865373495201L;

	public BadFeatureCardinalityException() {
		super();
	}

	public BadFeatureCardinalityException(String message) {
		super(message);
	}

	public BadFeatureCardinalityException(Exception source) {
		super(source);
	}
}
