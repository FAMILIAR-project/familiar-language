/*
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.idea;

import com.intellij.openapi.extensions.ExtensionFactory;
import org.xtext.example.mydsl.idea.lang.FmlLanguage;

public class FmlExtensionFactory implements ExtensionFactory {
	@Override
	public Object createInstance(String factoryArgument, String implementationClass) {
		Class<?> clazz;
		try {
			clazz = Class.forName(implementationClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Couldn't load "+implementationClass, e);
		}
		return FmlLanguage.INSTANCE.<Object> getInstance(clazz);
	}
}
