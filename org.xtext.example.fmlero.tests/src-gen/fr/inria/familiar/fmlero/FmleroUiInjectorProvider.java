/*
* generated by Xtext
*/
package fr.inria.familiar.fmlero;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class FmleroUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return fr.inria.familiar.fmlero.ui.internal.FmleroActivator.getInstance().getInjector("fr.inria.familiar.fmlero.Fmlero");
	}
	
}
