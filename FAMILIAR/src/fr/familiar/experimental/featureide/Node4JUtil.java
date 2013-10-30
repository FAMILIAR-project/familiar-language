/**
 * 
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
