/**
 * 
 */
package fr.familiar.test;

import org.eclipse.xtext.ui.editor.contentassist.AbstractContentProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.antlr.ParserBasedContentAssistContextFactory;
import org.junit.Test;
import org.xtext.example.mydsl.fML.FamiliarScript;
import org.xtext.example.mydsl.ui.contentassist.AbstractFMLProposalProvider;
import org.xtext.example.mydsl.ui.contentassist.FMLProposalProvider;

import fr.familiar.parser.FMLParsingHelper;
/**
 * @author macher1
 *
 */
public class FMLAutoCompletion {
	
	
	
	@Test
	public void testReader1() throws Exception {
		
		FMLParsingHelper parseHelper = new FMLParsingHelper();
		FamiliarScript model = parseHelper.parse("a = 3");
		
		System.err.println("FML model: " + model);
		System.err.println("model.getCmds()=" + model.getCmds());
		
		AbstractFMLProposalProvider pr ;  
		
		FMLProposalProvider cpr;
		
		ParserBasedContentAssistContextFactory pcf = new ParserBasedContentAssistContextFactory(); 
		
		ContentAssistContext context = null ;
		ICompletionProposalAcceptor acceptor = null ; 
		new AbstractContentProposalProvider.DefaultContentAssistProcessorSwitch(context, acceptor);
		
	}

}
