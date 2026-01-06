package org.xtext.example.mydsl;

public class FmlStandaloneSetup extends FmlStandaloneSetupGenerated {
    public static void doSetup() {
        new FmlStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
