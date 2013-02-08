package be.ac.fundp.info.TVLParser.exceptions;

public class FeatureIDNotBeginByAnUpperLetterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5687447033943901996L;

	public FeatureIDNotBeginByAnUpperLetterException() {
		super();
	}

	public FeatureIDNotBeginByAnUpperLetterException(String message) {
		super(message);
	}

	public FeatureIDNotBeginByAnUpperLetterException(Exception source) {
		super(source);
	}

}
