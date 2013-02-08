package be.ac.fundp.info.TVLParser.exceptions;

public class ManyTypesWithSameIDException extends Exception {

	/**
	 *  Exception jet�e quand au moins deux types poss�dent le m�me ID.
	 */
	private static final long serialVersionUID = -6278466633869079756L;

	public ManyTypesWithSameIDException() {
		super();
	}

	public ManyTypesWithSameIDException(String message) {
		super(message);
	}

	public ManyTypesWithSameIDException(Exception source) {
		super(source);
	}
}
