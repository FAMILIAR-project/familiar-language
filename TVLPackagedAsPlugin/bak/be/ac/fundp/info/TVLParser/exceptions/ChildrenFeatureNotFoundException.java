package be.ac.fundp.info.TVLParser.exceptions;

public class ChildrenFeatureNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8036860487576468263L;

	public ChildrenFeatureNotFoundException() {
		super();
	}

	public ChildrenFeatureNotFoundException(String message) {
		super(message);
	}

	public ChildrenFeatureNotFoundException(Exception source) {
		super(source);
	}
}
