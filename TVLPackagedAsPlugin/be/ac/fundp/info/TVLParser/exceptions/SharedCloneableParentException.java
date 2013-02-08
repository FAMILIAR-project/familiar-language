package be.ac.fundp.info.TVLParser.exceptions;

public class SharedCloneableParentException extends Exception{

	private static final long serialVersionUID = -3203982994399019043L;

	public SharedCloneableParentException() {
		super();
	}

	public SharedCloneableParentException(String message) {
		super(message);
	}

	public SharedCloneableParentException(Exception source) {
		super(source);
	}
}