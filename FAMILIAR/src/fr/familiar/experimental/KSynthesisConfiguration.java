package fr.familiar.experimental;
/**
 * 
 */

/**
 * @author macher1
 *
 */
public abstract class KSynthesisConfiguration {
	
	/**
	 * @return true if synthesis adds implies, equals, excludes
	 */
	public abstract boolean isAddingCrossTreeConstraints() ;
	
	

	/**
	 * @return true if OR-groups are synthesized
	 */
	public abstract boolean hasOrGroupSupport() ;
}
