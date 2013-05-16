/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.experimental.KSynthesisConfiguration;

/**
 * @author macher1
 *
 */
public class DefaultKSynthesisConfiguration extends KSynthesisConfiguration {
	
	private static final boolean _DEFAULT_ADDING_CROSS_TREE_CONSTRAINTS = true ; 

	@Override
	public boolean isAddingCrossTreeConstraints() {
		return _DEFAULT_ADDING_CROSS_TREE_CONSTRAINTS ; 
	}

}
