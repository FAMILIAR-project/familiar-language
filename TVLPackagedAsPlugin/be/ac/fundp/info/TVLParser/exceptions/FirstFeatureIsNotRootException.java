package be.ac.fundp.info.TVLParser.exceptions;

public class FirstFeatureIsNotRootException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6473727835690916463L;

	public FirstFeatureIsNotRootException() {
		super();
	}

	public FirstFeatureIsNotRootException(String message) {
		super(message);
	}

	public FirstFeatureIsNotRootException(Exception source) {
		super(source);
	}
}
