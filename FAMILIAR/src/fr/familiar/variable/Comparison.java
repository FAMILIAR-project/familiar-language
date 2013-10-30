/**
 * 
 */
package fr.familiar.variable;

/**
 * We rely on the terminology used in Thuem et al., ICSE'09
 * @author macher1
 *
 */
public enum Comparison {
	
	SPECIALIZATION("SPECIALIZATION"), 
	GENERALIZATION("GENERALIZATION"), 
	REFACTORING("REFACTORING"), 
	ARBITRARY("ARBITRARY");
	
	private Comparison(final String in) {
        this.cmpString = in;
    }
	
	@Override
	public String toString() {
		return cmpString;
	}
	
	private final String cmpString;
}
