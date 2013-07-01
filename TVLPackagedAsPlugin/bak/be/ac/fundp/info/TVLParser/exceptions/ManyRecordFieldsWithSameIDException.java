package be.ac.fundp.info.TVLParser.exceptions;

public class ManyRecordFieldsWithSameIDException extends Exception {
	
	/**
	 *  Exception j�t�e quand un record poss�de plusieurs champs avec le m�me ID.
	 */
	private static final long serialVersionUID = -1340462682531932086L;

	public ManyRecordFieldsWithSameIDException() {
		super();
	}

	public ManyRecordFieldsWithSameIDException(String message) {
		super(message);
	}

	public ManyRecordFieldsWithSameIDException(Exception source) {
		super(source);
	}

}
