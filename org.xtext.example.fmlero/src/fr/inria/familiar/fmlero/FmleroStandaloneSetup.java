
package org.xtext.example.mydsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class FmleroStandaloneSetup extends FmleroStandaloneSetupGenerated{

	public static void doSetup() {
		new FmleroStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

