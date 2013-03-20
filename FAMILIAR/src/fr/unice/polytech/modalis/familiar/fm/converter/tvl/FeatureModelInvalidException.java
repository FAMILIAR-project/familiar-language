package fr.unice.polytech.modalis.familiar.fm.converter.tvl;

public class FeatureModelInvalidException extends Exception {

	private static final long serialVersionUID = 4139238698205264118L;

	public FeatureModelInvalidException() {
		super();
	}

	public FeatureModelInvalidException(String message) {
		super(message);
	}

	public FeatureModelInvalidException(Exception source) {
		super(source);
	}
}
