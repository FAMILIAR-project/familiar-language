package be.ac.fundp.info.TVLParser.exceptions;

public class ManyAttributesWithSameIDException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1498714447900562091L;

	public ManyAttributesWithSameIDException() {
		super();
	}

	public ManyAttributesWithSameIDException(String message) {
		super(message);
	}

	public ManyAttributesWithSameIDException(Exception source) {
		super(source);
	}
	
}
