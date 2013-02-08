package be.ac.fundp.info.TVLParser.exceptions;

public class ManyConstantsWithSameIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2619270965315404402L;

	public ManyConstantsWithSameIDException() {
		super();
	}

	public ManyConstantsWithSameIDException(String message) {
		super(message);
	}

	public ManyConstantsWithSameIDException(Exception source) {
		super(source);
	}
}
