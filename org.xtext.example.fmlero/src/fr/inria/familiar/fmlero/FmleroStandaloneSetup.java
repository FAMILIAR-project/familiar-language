
package fr.inria.familiar.fmlero;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class FmleroStandaloneSetup extends FmleroStandaloneSetupGenerated{

	public static void doSetup() {
		new FmleroStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

