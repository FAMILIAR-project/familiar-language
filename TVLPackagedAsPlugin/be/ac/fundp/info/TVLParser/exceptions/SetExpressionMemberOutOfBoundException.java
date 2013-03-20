package be.ac.fundp.info.TVLParser.exceptions;

public class SetExpressionMemberOutOfBoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1780953155118466211L;

	public SetExpressionMemberOutOfBoundException() {
		super();
	}

	public SetExpressionMemberOutOfBoundException(String message) {
		super(message);
	}

	public SetExpressionMemberOutOfBoundException(Exception source) {
		super(source);
	}
}
