/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.idea.completion;

import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;
import org.xtext.example.mydsl.idea.completion.AbstractFmlCompletionContributor;
import org.xtext.example.mydsl.idea.lang.FmlLanguage;

@SuppressWarnings("all")
public class FmlCompletionContributor extends AbstractFmlCompletionContributor {
  public FmlCompletionContributor() {
    this(FmlLanguage.INSTANCE);
  }
  
  public FmlCompletionContributor(final AbstractXtextLanguage lang) {
    super(lang);
  }
}
