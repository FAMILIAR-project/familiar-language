package be.ac.fundp.info.TVLParser.exceptions;

public class TypeIDNotCorrespondToARecordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3031892887673931193L;

	public TypeIDNotCorrespondToARecordException() {
		super();
	}

	public TypeIDNotCorrespondToARecordException(String message) {
		super(message);
	}

	public TypeIDNotCorrespondToARecordException(Exception source) {
		super(source);
	}
}
