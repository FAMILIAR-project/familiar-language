package be.ac.fundp.info.TVLParser.exceptions;

public class ExpressionTypeViolatingAttributeTypeException extends Exception {
	// pourrait être utilisé à la place de SetExpressionMemberViolating...
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1856882322287741254L;

	public ExpressionTypeViolatingAttributeTypeException() {
		super();
	}

	public ExpressionTypeViolatingAttributeTypeException(String message) {
		super(message);
	}

	public ExpressionTypeViolatingAttributeTypeException(Exception source) {
		super(source);
	}
}
