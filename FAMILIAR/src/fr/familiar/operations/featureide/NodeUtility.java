/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations.featureide;

import fr.familiar.operations.FeatureIDEUtils;
import fr.familiar.parser.MyExpressionParser;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureGraphFactory;

import org.apache.log4j.Logger;
import org.prop4j.And;
import org.prop4j.Implies;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;

public class NodeUtility {
	
	protected static Logger _LOGGER = Logger.getLogger(NodeUtility.class) ;
	
	public static Node toNode(Expression<String> e) {
		return toNode(e, "") ;
	}
	
	public static Node toNode(Expression<String> e, String rootName) {
		Object oNode = convertExpressionToNode(e, rootName);
		if ((oNode != null) && (oNode instanceof Node)) 
			return (Node) oNode;
		return null ; 
	}
	
	// FeatureIDE has no true/false
	// idea: root is the true value! ;-)
	private static Object convertExpressionToNode(Expression<String> e,
			String rootName) {

		if (e == null)
			return null;
	
		/*FMLShell.getInstance().printDebugMessage(
				"e=" + e.toString() + " e.type=" + e.getType() + " e.feature="
						+ e.getFeature());*/

		if (e.getType() == ExpressionType.FEATURE) {
			if (e.getFeature().equals(FeatureGraphFactory.DEFAULT_TOP_STRING)) {

				return new Literal(rootName);
			}
			if (e.getFeature()
					.equals(FeatureGraphFactory.DEFAULT_BOTTOM_STRING)) {
				// FeatureIDE has no true/false
				// idea: !root is the false value! ;-)
				return new Literal(rootName, false);
			}
			// return null;
			// NO: return e.getFeature();
			return new Literal(e.getFeature());

		} else if (e.getType() == ExpressionType.NOT) {
			Object nd = convertExpressionToNode(e.getLeft(), rootName);
			//FMLShell.getInstance().printDebugMessage("NOT " + nd);
			return new Not(nd);

		} else if (e.getType() == ExpressionType.IMPLIES) {
			Object oleft = convertExpressionToNode(e.getLeft(), rootName);
			Object oright = convertExpressionToNode(e.getRight(), rootName);
			if (oleft == null || oright == null)
				return null;
			return new Implies(oleft, oright);

		} else if (e.getType() == ExpressionType.OR) {
			return new Or(convertExpressionToNode(e.getLeft(), rootName),
					convertExpressionToNode(e.getRight(), rootName));

		} else if (e.getType() == ExpressionType.AND) {

			Object oleft = convertExpressionToNode(e.getLeft(), rootName);
			Object oright = convertExpressionToNode(e.getRight(), rootName);

			return new And(oleft, oright);

		}

		else if (e.getType() == ExpressionType.IFF) {
			return new And(new Implies(convertExpressionToNode(e.getRight(),
					rootName), convertExpressionToNode(e.getLeft(), rootName)),
					new Implies(convertExpressionToNode(e.getLeft(), rootName),
							convertExpressionToNode(e.getRight(), rootName)));

		}

		else if (e.getType() == ExpressionType.TRUE) {
			// FeatureIDE has no true/false
			// idea: root is the true value! ;-)
			return new Literal(rootName);
		}

		else if (e.getType() == ExpressionType.FALSE) {
			// FeatureIDE has no true/false
			// idea: !root is the false value! ;-)
			return new Literal(rootName, false);
		}

		else { // strange, no?
			_LOGGER.warn ("STRANGE CASE");
			Object left = convertExpressionToNode(e.getLeft(), rootName);
			Object right = convertExpressionToNode(e.getRight(), rootName);
			if (left == null || right == null)
				return null;
			return new Implies(left, right);

		}

	}

	public static Expression<String> mkExpression(Node conj) {
		return MyExpressionParser.parseString(conj.toString(FeatureIDEUtils._textualSymbols));
	}

}
