package fr.familiar.experimental.featureide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.prop4j.And;
import org.prop4j.AtLeast;
import org.prop4j.AtMost;
import org.prop4j.Choose;
import org.prop4j.Equals;
import org.prop4j.Implies;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.NodeWriter;
import org.prop4j.Not;
import org.prop4j.Or;

import fr.familiar.utils.FileSerializer;

public class ConverterCNF {

	private static String _BINARY_SBSAT_LOCATION = "/Users/mathieuacher/Downloads/sbsat-2.7b/src/sbsat";

	private static String filename = "bddFoo.ite";

	private static ProcessBuilder pb = new ProcessBuilder(
			_BINARY_SBSAT_LOCATION, "-c", "-All", "0", "-In", "0", filename);

	private Node _n;

	private Map<String, Integer> _varIDs = new HashMap<String, Integer>();

	private int _id;

	public ConverterCNF(Node n) {
		_n = n;
	}

	public Node convert() {

		String canonicalForm = _toString(_n);
		String header = "p bdd " + _id + " 1\n";
		String comprehensive = header + canonicalForm;
		// System.err.println("============ N=\n" + comprehensive);

		try {
			String output = execute(comprehensive);
			// System.err.println("============ ouput=\n" + output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private String execute(String comprehensive) throws IOException,
			InterruptedException {

		// serialize first
		FileSerializer.write(filename, comprehensive);

		Process pr = pb.start();

		BufferedReader input = new BufferedReader(new InputStreamReader(
				pr.getInputStream()));

		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = input.readLine()) != null) {
			sb.append(line + "\n");
		}

		/*
		 * BufferedReader error = new BufferedReader(new
		 * InputStreamReader(pr.getErrorStream())); String lineError = "";
		 * while((lineError = error.readLine()) != null) {
		 * System.err.println("###### error: " + lineError); }
		 */

		int exitVal = pr.waitFor();

		pr.destroy();

		return sb.toString();

	}

	private String _toString(Node n) {

		return nodeToString(n, NodeWriter.textualSymbols, false, null);

	}

	/**
	 * Converts the given node into a specified textual representation.
	 * 
	 * @param node
	 *            a propositional node to convert
	 * @param symbols
	 *            array containing strings for: not, and, or, implies, iff,
	 *            seperator, choose, atleast and atmost
	 * @param optionalBrackets
	 *            a flag identifying if not necessary brackets will be added
	 * @param parent
	 *            the class of the node's parent or null if not available
	 * @return the textual representation
	 */
	protected String nodeToString(Node node, String[] symbols,
			boolean optionalBrackets, Class<? extends Node> parent) {
		if (node instanceof Literal)
			return (((Literal) node).positive ? "" : "-") + ""
					+ toInt(((Literal) node).var);
		if (node instanceof Not)
			return "not "
					+ "("
					+ nodeToString(node.getChildren()[0], symbols,
							optionalBrackets, node.getClass()) + ")";
		return multipleNodeToString(node, symbols, optionalBrackets, parent);
	}

	private int toInt(Object var) {

		String s = var.toString();

		if (_varIDs.containsKey(var)) {
			return _varIDs.get(var);
		} else {
			_id++;
			_varIDs.put(s, _id);
			return _id;
		}

	}

	/**
	 * Converts a node having multiple children into a specified textual
	 * representation.
	 * 
	 * @param node
	 *            a propositional node to convert
	 * @param symbols
	 *            array containing strings for: not, and, or, implies, iff,
	 *            seperator, choose, atleast and atmost
	 * @param optionalBrackets
	 *            a flag identifying if not necessary brackets will be added
	 * @param parent
	 *            the class of the node's parent or null if not available
	 * @return the textual representation
	 */
	protected String multipleNodeToString(Node node, String[] symbols,
			boolean optionalBrackets, Class<? extends Node> parent) {
		Node[] children = node.getChildren();
		if (children.length < 1)
			return "???";
		if (children.length == 1)
			return nodeToString(children[0], symbols, optionalBrackets, parent);

		String separator = getSeparator(node, symbols);
		String s = separator + "(";
		for (int i = 0; i < children.length; i++) {
			Node child = children[i];
			s += nodeToString(child, symbols, optionalBrackets, node.getClass());
			if (i != (children.length - 1))
				s += ",";

		}
		s += ")";
		// s = s.substring(separator.length());

		String prefix = "";
		if (node instanceof Choose)
			prefix = symbols[6] + ((Choose) node).n;
		else if (node instanceof AtLeast)
			prefix = symbols[7] + ((AtLeast) node).min;
		else if (node instanceof AtMost)
			prefix = symbols[8] + ((AtMost) node).max;

		int orderParent = order(parent);
		int orderChild = order(node.getClass());
		optionalBrackets = optionalBrackets || prefix.length() > 0
				|| orderParent > orderChild;
		optionalBrackets |= orderParent == orderChild
				&& orderParent == order(Implies.class);
		// s = optionalBrackets ? "(" + s + ")" : s;

		return prefix + s;
	}

	/**
	 * Assigns a number to every type of node. That And has a higher order than
	 * Or means that (A and B or C) is equal to ((A and B) or C).
	 * 
	 * @param nodeClass
	 *            type of node
	 * @return the order assigned to the type of node
	 */
	protected static int order(Class<? extends Node> nodeClass) {
		if (nodeClass == null)
			return 0;
		if (nodeClass.equals(AtMost.class) || nodeClass.equals(AtLeast.class)
				|| nodeClass.equals(Choose.class))
			return 1;
		if (nodeClass.equals(Equals.class))
			return 2;
		if (nodeClass.equals(Implies.class))
			return 3;
		if (nodeClass.equals(Or.class))
			return 4;
		if (nodeClass.equals(And.class))
			return 5;
		if (nodeClass.equals(Not.class))
			return 6;
		throw new RuntimeException("Unknown subtype from org.prop4j.Node \""
				+ nodeClass + "\"!");
	}

	/**
	 * Retrieves the separating char between different child nodes.
	 * 
	 * @param node
	 *            a node with child nodes
	 * @param symbols
	 *            a textual representation
	 * @return the separating string
	 */
	protected static String getSeparator(Node node, String[] symbols) {
		if (node instanceof And)
			return "and ";
		if (node instanceof Or)
			return "or ";
		if (node instanceof Implies)
			return "imp ";
		if (node instanceof Equals)
			return "same ";
		if (node instanceof Choose)
			return symbols[5];
		if (node instanceof AtLeast)
			return symbols[5];
		if (node instanceof AtMost)
			return symbols[5];
		throw new RuntimeException("Unknown subtype from org.prop4j.Node \""
				+ node + "\"!");
	}

}
