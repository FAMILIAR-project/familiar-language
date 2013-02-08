package be.ac.fundp.info.TVLParser.exceptions;

public class SymbolNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4117085217471790790L;

	public SymbolNotFoundException() {
		super();
	}

	public SymbolNotFoundException(String message) {
		super(message);
	}

	public SymbolNotFoundException(Exception source) {
		super(source);
	}

}
