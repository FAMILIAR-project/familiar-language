package be.ac.fundp.info.TVLParser.exceptions;

public class RecordFieldNotDefinedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7994761000049888010L;

	public RecordFieldNotDefinedException() {
		super();
	}

	public RecordFieldNotDefinedException(String message) {
		super(message);
	}

	public RecordFieldNotDefinedException(Exception source) {
		super(source);
	}

}
