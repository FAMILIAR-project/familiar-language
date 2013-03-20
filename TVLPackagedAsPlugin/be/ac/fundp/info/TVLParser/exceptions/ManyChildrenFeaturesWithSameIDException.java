package be.ac.fundp.info.TVLParser.exceptions;

public class ManyChildrenFeaturesWithSameIDException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3429025855232319104L;

	public ManyChildrenFeaturesWithSameIDException() {
		super();
	}

	public ManyChildrenFeaturesWithSameIDException(String message) {
		super(message);
	}

	public ManyChildrenFeaturesWithSameIDException(Exception source) {
		super(source);
	}
}
