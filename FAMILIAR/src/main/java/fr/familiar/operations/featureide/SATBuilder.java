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

import fr.familiar.experimental.FeatureNodeUtil;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.FeatureModelVariableConstraints;
import fr.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.graph.DepthFirstEdgeIterator;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.prop4j.And;
import org.prop4j.AtMost;
import org.prop4j.Equals;
import org.prop4j.Implies;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.editing.NodeCreator;

/**
 * Inspired from BDDBuilder
 * @author macher
 *
 */
public class SATBuilder {

	private int i = 1;

	private Map<String, Integer> _ftsToVar = new HashMap<String, Integer>();

	private final FeatureGraphFactory<String> _fgf = FeatureGraphFactory
			.mkStringFactory();

	public SATBuilder() {

	}

	public boolean contains(String o) {
		return _ftsToVar.containsKey(o);
	}

	public Node get(String o) {
		assert o != null;

		if (o.equals(_fgf.getTopFeature()))
			return mkTrueNode();
		else if (o.equals(_fgf.getBottomFeature()))
			return mkFalseNode();

		int var = _ftsToVar.containsKey(o) ? _ftsToVar.get(o) : add(o);
		return new Literal(o);
	}

	public Node nget(String o) {
		assert o != null;

		if (o.equals(_fgf.getTopFeature()))
			return mkFalseNode();
		else if (o.equals(_fgf.getBottomFeature()))
			return mkTrueNode();

		int var = _ftsToVar.containsKey(o) ? _ftsToVar.get(o) : add(o);
		return new Literal(o, false);
	}

	public Node mkSet(Collection<String> set) {
		return new And(set);
	}

	private Node mkOrNodes(Collection<FeatureNode<String>> set) {
		Node result = mkFalseNode();
		for (FeatureNode<String> v : set) {
			for (String f : v.features()) {
				result = new Or(result, get(f));
			}
		}
		return result;
	}

	public Node mkConjunction(FeatureNode<String> v) {
		if (v.isTop())
			return mkTrueNode();
		else if (v.isBottom())
			return mkFalseNode();

		return mkSet(v.features());
	}

	public Node mkDisjunction(FeatureNode<String> v) {
		Node result = mkFalseNode();
		for (String f : v.features()) {
			result = new Or(result, get(f));
		}
		return result;
	}

	private Node mkFeatureNodeNot(FeatureNode<String> v) {
		Node and = mkSet(v.features());
		Node result = new Not(and);
		return result;
	}

	public int variable(String o) {

		return _ftsToVar.containsKey(o) ? _ftsToVar.get(o) : add(o);
	}

	public String feature(int i) {
		Set<String> keys = _ftsToVar.keySet();
		for (String key : keys) {
			if (i == _ftsToVar.get(key))
				return key ; 
		}
		return null ; 
	}

	public Map<String, Integer> getFeatureMap() {
		return _ftsToVar;
	}

	public int add(String o) {
		assert o != null;
		assert !_ftsToVar.containsKey(o);
		int var = i++;

		_ftsToVar.put(o, var);
		return var;
	}

	public int remove(String o) {
		return _ftsToVar.remove(o);
	}

	public int[] vars(Collection<String> features) {
		ArrayList<String> list = new ArrayList<String>(features);
		Iterator<String> iter = list.iterator();

		int[] result = new int[list.size()];
		iter = list.iterator();
		for (int i = 0; i < result.length; i++) {
			result[i] = variable(iter.next());
		}
		return result;
	}

	public Node mkExpression(Expression<String> expr) {
		if (expr == null)
			return null;

		Node left = mkExpression(expr.getLeft());
		Node right = mkExpression(expr.getRight());
		switch (expr.getType()) {
		case TRUE:
			return mkTrueNode();
		case FALSE:
			return mkFalseNode();
		case AND:
			return new And(left, right);
		case OR:
			return new Or(left, right);
		case IMPLIES:
			return new Implies(left, right);
		case IFF:
			return new Equals(left, right); // new And (new Implies (left,
											// right), new Implies (right,
											// left)) ; //
		case NOT:
			Node result = new Not(left);
			return result;
		case FEATURE:
			// AM: buggy in !B -> !C with map // TODO does not resolve the
			// problem
			if (expr.getFeature() == null)
				return null;
			return get(expr.getFeature());
		default:
			throw new UnsupportedOperationException();
		}
	}

	public static HashMap<Object, Node> calculateReplacingMap(
			FeatureModelVariable fmv, Set<String> abstractFts) {
		HashMap<Object, Node> map = new HashMap<Object, Node>();
		for (String abstractFt : abstractFts) {
			Node replacing = calculateReplacing(abstractFt, fmv);
			replacing = NodeCreator.replaceAbstractVariables(replacing, map,
					true);
			updateMap(map, abstractFt, replacing);
		}

		return map;
	}

	public static HashMap<Object, Node> _calculateReplacingMap(
			FeatureModelVariable fmv, Set<String> abstractFts) {
		HashMap<Object, Node> map = new HashMap<Object, Node>();
		de.ovgu.featureide.fm.core.FeatureModel fmIDE = new FMLtoFeatureIDE(fmv).convert();
		for (String abstractFt : abstractFts) {
			Feature feature = fmIDE.getFeature(abstractFt);
			String var = getVariable(feature, fmIDE);
			Node replacing = calculateReplacing(var, fmIDE);
			replacing = NodeCreator.replaceAbstractVariables(replacing, map,
					true);
			updateMap(map, var, replacing);
		}

		return map;
	}

	private static Node calculateReplacing(String var, FeatureModelVariable fmv) {
		FeatureNode<String> feature = getFeature(var, fmv);
		return calculateReplacing(fmv, feature);
	}

	private static FeatureNode<String> getFeature(Object var,
			FeatureModelVariable fmv) {
		return fmv.getFm().getDiagram().findVertex((String) var);
	}

	private static Node calculateReplacing(Object var,
			de.ovgu.featureide.fm.core.FeatureModel featureModel) {
		Feature feature = getFeature(var, featureModel);
		return calculateReplacing(featureModel, feature);
	}

	private static Node calculateReplacing(FeatureModelVariable fmv,
			FeatureNode<String> feature) {

		de.ovgu.featureide.fm.core.FeatureModel featureModel = new FMLtoFeatureIDE(fmv).convert();
		if (!FeatureNodeUtil.hasChildren(feature)) {
			FeatureNode<String> parent = FeatureNodeUtil.getParent(feature);
			if (parent == null || FeatureNodeUtil.isAbstract(parent))
				return null;
			if ((FeatureNodeUtil.isAnd(parent) && FeatureNodeUtil
					.isMandatorySet(feature))
					|| (!FeatureNodeUtil.isAnd(parent) && FeatureNodeUtil
							.getChildrenCount(parent) == 1))
				return new Literal(featureModel.getOldName(parent.getFeature()));
			return null;
		}
		if (FeatureNodeUtil.isAnd(feature)) {
			for (FeatureNode<String> child : FeatureNodeUtil
					.getChildren(feature))
				if (FeatureNodeUtil.isMandatorySet(child)
						&& FeatureNodeUtil.isConcrete(child))
					return new Literal(featureModel.getOldName(child
							.getFeature()));
			for (FeatureNode<String> child : FeatureNodeUtil
					.getChildren(feature))
				if (FeatureNodeUtil.isMandatorySet(child))
					return new Literal(featureModel.getOldName(child
							.getFeature()));
			return null;
		}
		LinkedList<Node> children = new LinkedList<Node>();
		for (FeatureNode<String> child : FeatureNodeUtil.getChildren(feature)) {
			String var2 = featureModel.getOldName(child.getFeature());
			children.add(new Literal(var2));
		}
		if (children.size() == 1)
			return children.getFirst();
		return new Or(children);
	}

	private static Node calculateReplacing(
			de.ovgu.featureide.fm.core.FeatureModel featureModel,
			Feature feature) {
		if (!feature.hasChildren()) {
			Feature parent = feature.getParent();
			if (parent == null || parent.isAbstract())
				return null;
			if ((parent.isAnd() && feature.isMandatorySet())
					|| (!parent.isAnd() && parent.getChildrenCount() == 1))
				return new Literal(featureModel.getOldName(parent.getName()));
			return null;
		}
		if (feature.isAnd()) {
			for (Feature child : feature.getChildren())
				if (child.isMandatorySet() && child.isConcrete())
					return new Literal(featureModel.getOldName(child.getName()));
			for (Feature child : feature.getChildren())
				if (child.isMandatorySet())
					return new Literal(featureModel.getOldName(child.getName()));
			return null;
		}
		LinkedList<Node> children = new LinkedList<Node>();
		for (Feature child : feature.getChildren()) {
			String var2 = featureModel.getOldName(child.getName());
			children.add(new Literal(var2));
		}
		if (children.size() == 1)
			return children.getFirst();
		return new Or(children);
	}

	private static Feature getFeature(Object var,
			de.ovgu.featureide.fm.core.FeatureModel featureModel) {
		String currentName = featureModel.getNewName((String) var);
		return featureModel.getFeature(currentName);
	}

	/**
	 * Replaces all occurrences of the given variable in values of the map.
	 */
	private static void updateMap(HashMap<Object, Node> map, Object var,
			Node replacing) {
		for (Object key : map.keySet()) {
			Node value = map.get(key);
			HashMap<Object, Node> tempMap = new HashMap<Object, Node>();
			tempMap.put(var, replacing);
			value = NodeCreator.replaceAbstractVariables(value, tempMap, true);
			map.put(key, value);
		}
		map.put(var, replacing);
	}

	public static String getVariable(Feature feature,
			de.ovgu.featureide.fm.core.FeatureModel featureModel) {
		return featureModel.getOldName(feature.getName());
	}

	public static Node replaceAbstractFeature(Node diagram,
			HashMap<Object, Node> replacingMap) {

		And and = new And(diagram);
		and = (And) NodeCreator.replaceAbstractVariables(and, replacingMap,
				false);
		// and = NodeCreator.eliminateAbstractVariables(and, replacingMap,
		// fmIDE);

		return and;
	}

	/**
	 * @see {@link #mkStructure(FeatureGraph)}
	 */
	public Node mkFeatureModel(FeatureModel<String> model) {
		Node diagram = mkStructure(model.getDiagram());
		for (Expression<String> expr : model.getConstraints()) {
			diagram = new And(diagram, mkExpression(expr));
		}

		return diagram;
	}

	public Node mkTop(FeatureGraph<String> g) {
		Node result = mkTrueNode();
		for (FeatureEdge e : g.incomingEdges(g.getTopVertex())) {
			if (e.getType() == FeatureEdge.MANDATORY)
				result = new And(result, mkConjunction(g.getSource(e)));
			else if (e.getType() == FeatureEdge.DEAD)
				result = new And(result, mkFeatureNodeNot(g.getSource(e)));
		}
		return result;
	}

	/**
	 * Creates a BDD representing the structural semantics of the FeatureGraph.
	 * The root feature is not set, since it reduces the BDD. We want to
	 * maintain all structure in the FeatureGraph.
	 * 
	 */
	protected Node mkStructure(FeatureGraph<String> g) {
		if (g.vertices().size() == 0)
			return mkTrueNode();
		Node hierarchy = mkHierarchy(g);
		Node andGroups = mkAndGroups(g);
		Node hierarchyWithAndGroup = new And(hierarchy, andGroups);
		Node withRoot = new And(hierarchyWithAndGroup, mkTop(g));
		return mkCardinality(g, withRoot);
	}

	@SuppressWarnings("unchecked")
	private Node mkMutex(Set<FeatureNode<String>> sources) {
		if (sources.size() == 1)
			return mkTrueNode();

		Set<Node> and = new HashSet<Node>();
		FeatureNode<String>[] arr = sources.toArray(new FeatureNode[0]);
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				FeatureNode<String> v1 = arr[i];
				FeatureNode<String> v2 = arr[j];
				and.add(new Implies(mkDisjunction(v1), mkFeatureNodeNot(v2)));
			}
		}

		return new And(and);
	}

	/**
	 * <p>
	 * Creates a Node describing the implications "upwards" in the feature tree.
	 * These upwards implications describe child-parent implications. Mandatory
	 * and AND-Grouped features are considered optional.
	 * </p>
	 * 
	 * @see {@link #mkCardinality(FeatureGraph)}
	 */
	public Node mkHierarchy(FeatureGraph<String> g) {
		
		Set<Node> and = new HashSet<Node>();
		DepthFirstEdgeIterator<String> iter = new DepthFirstEdgeIterator<String>(
				g);
		while (iter.hasNext()) {
			FeatureEdge e = iter.next();

			if (e.getType() == FeatureEdge.HIERARCHY) {
				Node sources = mkOrNodes(g.getSources(e));
				Node target = mkConjunction(g.getTarget(e));
				and.add(new Implies(sources, target));
			}
		}
		return new And(and);
	}

	public Node mkCardinality(FeatureGraph<String> g) {
		return mkCardinality(g, mkTrueNode());
	}

	private Node mkAndGroups(FeatureGraph<String> g) {
		Node result = mkTrueNode();
		// AND-Groups
		for (FeatureNode<String> v : g.vertices()) {
			if (v.features().size() > 1) {
				Iterator<String> iter = v.features().iterator();
				String start = iter.next();
				String prev = start;
				do {
					String next = iter.next();
					result = new And(result, new Implies(get(prev), get(next)));
					prev = next;
				} while (prev != start);
			}
		}
		return result;
	}

	private static final Literal _TRUE_NODE = new Literal(NodeCreator.varTrue,
			true);
	private static final Literal _FALSE_NODE = new Literal(
			NodeCreator.varFalse, true);

	public static Literal mkTrueNode() {
		_TRUE_NODE.positive = true; // very strange :(
		return _TRUE_NODE;
	}

	public static Literal mkFalseNode() {
		_FALSE_NODE.positive = true;
		return _FALSE_NODE;
	}

	private Node mkCardinality(FeatureGraph<String> g, Node result) {
		
		for (FeatureEdge e : g.edges()) {

			Set<FeatureNode<String>> sources = g.getSources(e);
			FeatureNode<String> target = g.getTarget(e);
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				result = new And(result, mkMutex(sources));
				break;
			case FeatureEdge.XOR:
			case FeatureEdge.MANDATORY:
				result = new And(result, new Implies(mkConjunction(target),
						mkOrNodes(sources)));
				// Mutual Exclusions
				result = new And(result, mkMutex(sources));
				break;
			case FeatureEdge.OR:
				result = new And(result, new Implies(mkConjunction(target),
						mkOrNodes(sources)));
				break;
			case FeatureEdge.DEAD:
				for (FeatureNode<String> v : sources)
					result = new And(result, mkFeatureNodeNot(v));
				break;
			default:
				// Skip
				assert e.getType() == FeatureEdge.HIERARCHY
						|| e.getType() == FeatureEdge.FROZEN;
			}
		}

		return result;
	}

	protected Set<String> domain() {
		return Collections.unmodifiableSet(_ftsToVar.keySet());
	}

	public Node mkNode(FeatureModelVariable fmv) {
		
		
		// FIXME rather WEIRD
		if (fmv instanceof FeatureModelVariableConstraints) {
			FeatureModelVariableConstraints csts = (FeatureModelVariableConstraints) fmv ;
			return csts.getSATFormula().getNode() ; 
		}
		
		if (fmv instanceof FeatureModelVariableSATFormula) {
			FeatureModelVariableSATFormula satFla = (FeatureModelVariableSATFormula) fmv ;
			return satFla.getSATFormula().getNode() ; 
		}
		
		Node n = mkFeatureModel(fmv.getFm());
		n = new And(n, mkTrueNode(), new Not(mkFalseNode()));
		n = simplify(n);
		//n = simplify(n.toCNF());
		return n;
	}

	public static Node simplify(Node node) {
		return _simplify(node);
	}

	public static Node _simplify0(Node node) {
		if (node instanceof Literal) {
			Literal lit = (Literal) node;
			if (lit.var.equals(NodeCreator.varFalse) && !lit.positive)
				return new Literal(NodeCreator.varTrue);
			if (lit.var.equals(NodeCreator.varTrue) && !lit.positive)
				return new Literal(NodeCreator.varFalse);
			return lit;
		}
		Node[] children = node.getChildren();
		int removeChildren = 0;
		for (int i = 0; i < children.length; i++) {
			Node child = _simplify0(children[i]);
			if (child instanceof Literal) {
				Literal lit = (Literal) child;
				// we assume that litTrue and litFalse can only occur positive
				if (lit.var.equals(NodeCreator.varTrue)) {
					/*
					 * if (node instanceof Not) return new
					 * Literal(NodeCreator.varFalse); if (node instanceof And) {
					 * removeChildren++; child = null; } if (node instanceof Or)
					 * return lit;
					 */
					if (node instanceof Implies) {
						if (i == 0)
							return children[1];
						// else
						// return lit;
					}
					/*
					 * if (node instanceof Equals) { if (i == 0) return
					 * children[1]; else return children[0]; } if (node
					 * instanceof AtMost) { AtMost atmost = (AtMost) node; if
					 * (atmost.max < 1) return new
					 * Literal(NodeCreator.varFalse); Node[] newChildren = new
					 * Node[children.length - 1]; for (int j = 0; j < i; j++)
					 * newChildren[j] = children[j]; for (int j = i + 1; j <
					 * children.length; j++) newChildren[j - 1] = children[j];
					 * if (atmost.max > 1) return _simplify0(new
					 * AtMost(atmost.max - 1, newChildren)); for (int j = 0; j <
					 * newChildren.length; j++) { Node newChild =
					 * newChildren[j]; if (newChild instanceof Literal)
					 * ((Literal) newChild).positive = !((Literal)
					 * newChild).positive; else newChildren[j] = new
					 * Not(newChild); } return _simplify0(new And(newChildren));
					 * }
					 */
				} else if (lit.var.equals(NodeCreator.varFalse)) {
					/*
					 * if (node instanceof Not) return new
					 * Literal(NodeCreator.varTrue); if (node instanceof And)
					 * return lit; if (node instanceof Or) { removeChildren++;
					 * child = null; }
					 */

					if (node instanceof Implies) {
						if (i == 0) {
							return new Literal(NodeCreator.varTrue);
						} else
							return _simplify0(new Not(children[0]));
					}
					/*
					 * if (node instanceof Equals) { if (i == 0) return
					 * _simplify0(new Not(children[1])); else return
					 * _simplify0(new Not(children[0])); }
					 */
				}
			}
			children[i] = child;
		}

		if (removeChildren == 0)
			return node;
		/*
		 * if (children.length - removeChildren == 0) { if (node instanceof And)
		 * return new Literal(NodeCreator.varTrue); if (node instanceof Or)
		 * return new Literal(NodeCreator.varFalse); }
		 */
		Node[] newChildren = new Node[children.length - removeChildren];
		int i = 0;
		for (Node child : children)
			if (child != null)
				newChildren[i++] = child;
		node.setChildren(newChildren);

		return node;
	}

	public static Node _simplify(Node node) {
		if (node instanceof Literal) {
			Literal lit = (Literal) node;
			if (lit.var.equals(NodeCreator.varFalse) && !lit.positive)
				return new Literal(NodeCreator.varTrue);
			if (lit.var.equals(NodeCreator.varTrue) && !lit.positive)
				return new Literal(NodeCreator.varFalse);
			return lit;
		}
		Node[] children = node.getChildren();
		int removeChildren = 0;
		for (int i = 0; i < children.length; i++) {
			Node child = _simplify(children[i]);
			if (child instanceof Literal) {
				Literal lit = (Literal) child;
				// we assume that litTrue and litFalse can only occur positive
				if (lit.var.equals(NodeCreator.varTrue)) {
					if (node instanceof Not)
						return new Literal(NodeCreator.varFalse);
					if (node instanceof And) {
						removeChildren++;
						child = null;
					}
					if (node instanceof Or)
						return lit;
					if (node instanceof Implies) {
						if (i == 0)
							return children[1];
						else
							return lit;
					}
					if (node instanceof Equals) {
						if (i == 0)
							return children[1];
						else
							return children[0];
					}
					if (node instanceof AtMost) {
						AtMost atmost = (AtMost) node;
						if (atmost.max < 1)
							return new Literal(NodeCreator.varFalse);
						Node[] newChildren = new Node[children.length - 1];
						for (int j = 0; j < i; j++)
							newChildren[j] = children[j];
						for (int j = i + 1; j < children.length; j++)
							newChildren[j - 1] = children[j];
						if (atmost.max > 1)
							return _simplify(new AtMost(atmost.max - 1,
									newChildren));
						for (int j = 0; j < newChildren.length; j++) {
							Node newChild = newChildren[j];
							if (newChild instanceof Literal)
								((Literal) newChild).positive = !((Literal) newChild).positive;
							else
								newChildren[j] = new Not(newChild);
						}
						return _simplify(new And(newChildren));
					}
				} else if (lit.var.equals(NodeCreator.varFalse)) {
					if (node instanceof Not)
						return new Literal(NodeCreator.varTrue);
					if (node instanceof And)
						return lit;
					if (node instanceof Or) {
						removeChildren++;
						child = null;
					}
					if (node instanceof Implies) {
						if (i == 0)
							return new Literal(NodeCreator.varTrue);
						else
							return _simplify(new Not(children[0]));
					}
					if (node instanceof Equals) {
						if (i == 0)
							return _simplify(new Not(children[1]));
						else
							return _simplify(new Not(children[0]));
					}
				}
			}
			children[i] = child;
		}
		if (removeChildren == 0)
			return node;
		if (children.length - removeChildren == 0) {
			if (node instanceof And)
				return new Literal(NodeCreator.varTrue);
			if (node instanceof Or)
				return new Literal(NodeCreator.varFalse);
		}
		Node[] newChildren = new Node[children.length - removeChildren];
		int i = 0;
		for (Node child : children)
			if (child != null)
				newChildren[i++] = child;
		node.setChildren(newChildren);
		return node;
	}

	/**
	 * @param n
	 * @param ftsToRemove
	 *            simplify wrt the ft (others are *not* modified)
	 */
	public static Node simplify(Node n, Collection<String> ftsToRemove) {
		// return n ;
		// return _simplify(n);
		return _simplify0(n);

	}

}
