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

package fr.familiar.experimental.featureide;

import java.util.ArrayList;
import java.util.List;

import org.prop4j.And;
import org.prop4j.Node;

/**
 * @author mathieuacher
 * 
 */
public class Node4JUtil {

	public static int countSizeOfNode(Node node) {
		if (node == null)
			return 0;
		Node[] children = node.getChildren();
		int i = 0;
		if (children == null || children.length == 0)
			return 1;
		for (Node child : children) {
			i += countSizeOfNode(child);
		}
		return i;
	}

	public static List<Node> splitConjunctions(Node p) {
		List<Node> nodes = new ArrayList<Node>();
		if (p instanceof And) {
			Node[] children = p.getChildren();
			for (int i = 0; i < children.length; i++) {
				nodes.addAll(splitConjunctions(children[i]));
			}
		} else {
			nodes.add(p);
		}

		return nodes;
	}

}
