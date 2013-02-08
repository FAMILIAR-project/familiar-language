
package org.xtext.example.mydsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class FMLStandaloneSetup extends FMLStandaloneSetupGenerated{

	public static void doSetup() {
		new FMLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

