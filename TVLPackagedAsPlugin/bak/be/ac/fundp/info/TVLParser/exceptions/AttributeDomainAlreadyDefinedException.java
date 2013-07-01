package be.ac.fundp.info.TVLParser.exceptions;

public class AttributeDomainAlreadyDefinedException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1174835792756605131L;

	public AttributeDomainAlreadyDefinedException() {
		super();
	}

	public AttributeDomainAlreadyDefinedException(String message) {
		super(message);
	}

	public AttributeDomainAlreadyDefinedException(Exception source) {
		super(source);
	}
}
