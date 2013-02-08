package fr.unice.polytech.modalis.familiar.operations;

/**
 * Implemented as an exception-like class
 * @author macher1
 *
 */
public class KSTReport {
	
	protected boolean _coherence ; 
	
	protected String _debugMsg = null ; 
	
	public KSTReport() {
		_coherence = true ; 
	}
	
	public KSTReport(String msg) {
		_coherence = false ;
		_debugMsg = msg ; 
	}
	
	public boolean isCoherent() {
		return _coherence ; 
	}
	
	public String getMessage() {
		return _debugMsg ; 
	}

}
