package be.ac.fundp.info.TVLParser.exceptions;

public class ParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4667476260411313806L;

	public ParsingException() {
		super();
	}

	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(Exception source) {
		super(source);
	}
	
}
