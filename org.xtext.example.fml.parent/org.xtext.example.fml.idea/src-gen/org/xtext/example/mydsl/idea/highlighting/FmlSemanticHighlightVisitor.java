/*
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;
import org.xtext.example.mydsl.idea.lang.FmlLanguage;

public class FmlSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public FmlSemanticHighlightVisitor() {
		FmlLanguage.INSTANCE.injectMembers(this);
	}
}
