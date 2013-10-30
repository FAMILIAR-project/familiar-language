package fr.familiar.fm;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.Closure;

import prefuse.data.Node;
import prefuse.data.Tree;
import prefuse.data.io.DataIOException;
import prefuse.data.io.TreeMLWriter;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class FMLConverter2TreePrefuse {

	private static final String NAME = "name";

	private static final String OPTIONAL = "optional";

	private static final String GROUP_VARIABILITY = "group";

	public static int gID = 0;

	public FMLConverter2TreePrefuse() {

	}

	public Tree toTreePrefuse(FeatureModelVariable fmv) {

		// Node root = tree.addRoot() ;
		// root.setString(NAME, fmv.root().name());

		FeatureModel<String> fm = fmv.getFm();

		// graph (actually a tree)
		FeatureGraph<String> fg = fm.getDiagram();

		// set root
		Tree tree = new Tree();

		tree.addColumn(NAME, String.class);
		tree.addColumn(OPTIONAL, int.class, -1);
		tree.addColumn(GROUP_VARIABILITY, int.class, -1);

		String rootName = fmv.root().name();
		Node nv = tree.addRoot();
		nv.setString(NAME, rootName);
		Map<String, Node> nameToNode = new HashMap<String, Node>();
		nameToNode.put(rootName, nv);
		process(fg, tree, nameToNode);

		// constraints
		Set<Expression<String>> csts = fm.getConstraints();
		// TODO

		return tree;
	}

	public void serialize(FeatureModelVariable fmv, File file) {

		Tree t = toTreePrefuse(fmv);

		TreeMLWriter writer = new TreeMLWriter();
		try {
			writer.writeGraph(t, file);
		} catch (DataIOException e) {
			FMLShell.getInstance().printError(
					"Unable to serialize the FM in TreeML format " + e);
			e.printStackTrace();
			return;
		}

	}

	public Tree process(final FeatureGraph<String> g, final Tree tree,
			final Map<String, Node> nameToNode) {

		final Queue<FeatureNode<String>> rest = new LinkedList<FeatureNode<String>>();

		Closure<FeatureNode<String>> processNode = new Closure<FeatureNode<String>>() {

			public void execute(FeatureNode<String> v) {
				Collection<FeatureNode<String>> children = g.children(v);
				if (children.size() == 0)
					return;

				rest.addAll(children);

				Node nv = null;
				if (v.equals(g.getTopVertex()))
					return;

				else {

					String ftName = v.getFeature();
					nv = addOrCreateNode(tree, ftName);
				}

				System.err.println("nv=" + nv + " children=" + children + " v="
						+ v);

				// Process Groups

				for (FeatureEdge e : g.incomingEdges(v)) {
					Set<FeatureNode<String>> sources = g.getSources(e);

					if (sources.size() == 1)
						continue;

					Node ngroup = null;

					switch (e.getType()) {

					case FeatureEdge.XOR:
						ngroup = addOrCreateNode(tree, "XOR" + gID++);
						ngroup.setInt(GROUP_VARIABILITY, new Integer(
								FeatureEdge.XOR));
						break;
					case FeatureEdge.MUTEX:
						ngroup = addOrCreateNode(tree, "MUTEX" + gID++);
						ngroup.setInt(GROUP_VARIABILITY, new Integer(
								FeatureEdge.MUTEX));
						break;
					case FeatureEdge.OR:
						ngroup = addOrCreateNode(tree, "OR" + gID++);
						ngroup.setInt(GROUP_VARIABILITY, new Integer(
								FeatureEdge.OR));
						break;
					}

					if (ngroup != null) {
						tree.addChildEdge(nv, ngroup);

						for (FeatureNode<String> m : g.getSources(e)) {

							Node nc = addOrCreateNode(tree, m.getFeature());
							tree.addChildEdge(ngroup, nc);

						}
					}

					children.removeAll(sources);
				}

				System.err.println("opt/mand=" + children);
				// Process remaining children
				for (FeatureNode<String> child : children) {
					if (g.findEdge(child, v, FeatureEdge.MANDATORY) == null) {

						Node nchild = addOrCreateNode(tree, child.getFeature());
						tree.addChildEdge(nv, nchild);
						nchild.setInt(OPTIONAL, new Integer(1));

					} else {
						Node nchild = addOrCreateNode(tree, child.getFeature());
						tree.addChildEdge(nv, nchild);
						nchild.setInt(OPTIONAL, new Integer(0));
					}
				}

			}

			private Node addOrCreateNode(Tree tree, String ftName) {
				Node node = null;

				boolean alreadyExists = nameToNode.containsKey(ftName);

				if (!alreadyExists) {
					node = tree.addNode();
					node.setString(NAME, ftName);
					nameToNode.put(ftName, node);
				} else
					node = nameToNode.get(ftName);

				return node;
			}
		};

		rest.add(g.getTopVertex());
		while (!rest.isEmpty())
			processNode.execute(rest.poll());

		return tree;

	}

}
