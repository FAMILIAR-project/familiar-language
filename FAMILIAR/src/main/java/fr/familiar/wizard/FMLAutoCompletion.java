/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

/**
 * 
 */
package fr.familiar.wizard;

import org.eclipse.xtext.ui.editor.contentassist.AbstractContentProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.antlr.ParserBasedContentAssistContextFactory;
import org.junit.Test;
import org.xtext.example.mydsl.fml.FamiliarScript;
//import org.xtext.example.mydsl.ui.contentassist.AbstractFMLProposalProvider;
//import org.xtext.example.mydsl.ui.contentassist.FMLProposalProvider;

import fr.familiar.parser.FMLParsingHelper;
/**
 * Failiure (not that easy to reuse Xtext facilities in a non Eclipse setting wrt discussions with Xtext guys)
 * @author macher1
 *
 */
@Deprecated
public class FMLAutoCompletion {
	
	
	
	@Test
	public void testReader1() throws Exception {
		
		FMLParsingHelper parseHelper = new FMLParsingHelper();
		FamiliarScript model = parseHelper.parse("a = 3");
		
		System.err.println("FML model: " + model);
		System.err.println("model.getCmds()=" + model.getCmds());
		
		//AbstractFMLProposalProvider pr ;  
		
		//FMLProposalProvider cpr;
		
		ParserBasedContentAssistContextFactory pcf = new ParserBasedContentAssistContextFactory(); 
		
		ContentAssistContext context = null ;
		ICompletionProposalAcceptor acceptor = null ; 
		//new AbstractContentProposalProvider.DefaultContentAssistProcessorSwitch(context, acceptor);
		
	}

}
