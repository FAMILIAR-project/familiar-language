/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.synthesis;

/*import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.TreeAdaptor;

import gsd.synthesis.FmLexer;
import gsd.synthesis.FmParser;
import gsd.synthesis.FmTreeParser;

import java.io.IOException;
import java.util.logging.Logger;*/

/**
 *
 * Uses the ANTLR grammar to return a Feature Model consisting of String objects.
 *
 * @author Steven She <shshe@uwaterloo.ca>
 *
 */
public class FeatureModelParser {

	
//	static Logger logger = Logger.getLogger("fm.FeatureModelParser");
//
//	/**
//	 * A simple adaptor that stores the tokens of the tree
//	 */
//	static final TreeAdaptor adaptor = new CommonTreeAdaptor() {
//		@Override
//		public Object create(Token token) {
//			return new CommonTree(token);
//		}
//
//
//		/**
//		 * Disable error node creation.
//		 */
//		public Object errorNode(TokenStream input, Token start, Token stop,
//				RecognitionException e)
//		{
//			return null;
//		}
//
//	};
//
//
//	public static FeatureModel<String> parseString(String text) {
//		ANTLRStringStream input = new ANTLRStringStream(text);
//		try {
//			return parse(input);
//		}
//		catch (IOException e) {
//			//This should never happen since we're using string stream
//			return null;
//		}
//	}
//	public static FeatureModel<String> parseFile(String filename) throws IOException {
//		ANTLRFileStream fs = new ANTLRFileStream(filename);
//		return parse(fs);
//	}
//
//	protected static FeatureModel<String> parse(CharStream stream) throws IOException {
//		FmLexer lex = new FmLexer(stream);
//		TokenRewriteStream tokens = new TokenRewriteStream(lex);
//		FmParser grammar = new FmParser(tokens);
//		grammar.setTreeAdaptor(adaptor);
//
//		try {
//			FmParser.input_return input = grammar.input();
//			CommonTree ast = (CommonTree) input.getTree();
//
//			CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
//			FmTreeParser walker = new FmTreeParser(nodes);
//			return walker.input();
//		}
//		catch (RecognitionException exception) {
//			logger.warning("Recognition Exception: " + exception);
//			return null;
//		}
//	}
	

}