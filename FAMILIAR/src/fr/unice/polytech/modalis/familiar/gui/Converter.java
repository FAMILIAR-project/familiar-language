/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)
 * project (http://familiar-project.github.com/).
 *
 * Copyright (C) 2011 - 2013
 *     University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *     Colorado State University, Computer Science Department
 *     
 * Author: Aleksandar Jaksic
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.collections15.Closure;

import prefuse.data.Node;
import prefuse.data.Tree;
import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class Converter {
	// A simple, fast, and thread-safe singleton implementation.
	public final static Converter INSTANCE = new Converter();

	public static final String NAME = "name";
	public static final String SOLITARY = "solitary";
	public static final String GROUP = "group";
	public static final String CONSTRAINTS = "     CONSTRAINTS:     ";
	
	public static final int NOTAVAILABLE = -1;
	public static final int MANDATORY = 0;
	public static final int OPTIONAL = 1;
	public static final int CONSTRAINT = 32;

	public static int gID = 0;
	private static int counter = 0;
	
	private StringBuilder sb = null;

	private Converter() {
	}
	
	public String generateRootName() {
		return "myFM" + ++counter;
	}
	
	private Tree generateEmptyTree() {
		Tree t = new Tree();
		t.addColumn(NAME, String.class);
		t.addColumn(SOLITARY, int.class, NOTAVAILABLE);
		t.addColumn(GROUP, int.class, NOTAVAILABLE);
		return t;
	}
	
    public FeatureModelVariable getStartupFMV() {
		FeatureModelVariable fmv = buildStartupDisplayFromFML();
		if (null == fmv) {
			Tree t = buildStartupDisplayFromPrefuseTree(null);
			fmv = prefuseTree2Fmv(t, null);
		} 
		return fmv;
	}
	
	public Tree buildStartupDisplayFromPrefuseTree(String fmRootName) {
		Tree t = generateEmptyTree();
		Node root = t.addRoot();
		root.setString(NAME, null == fmRootName ? generateRootName() : fmRootName);
		return t;
	}
	
	private FeatureModelVariable buildStartupDisplayFromFML() {
		FeatureModel<String> fm = null;
		try {
//			fm = FMBuilder.getInternalFM(FMLTest.FM_LAPTOP);
			fm = FMBuilder.getInternalFM(
					" Wiki: Hosting License Storage [\"Programming Language\"] ; \n" + 
					"Hosting: (\"Hosted Service\"|Local) ; \n" + 
					"License: (\"Proprietary License\"|\"Open Source\") ; \n" + 
					"Storage: (PostgreSQL|MySQL) ; \n" + 
					"\"Programming Language\": (Java|PHP) ; \n" + 
					"\"Hosted Service\": [Domain] ; \n" + 
					"(\"Proprietary License\" -> !\"Programming Language\");\n" + 
					"(Local -> !\"Proprietary Licence\");\n" + 
					"(PostgreSQL <-> \"Proprietary License\");" +
					"PostgreSQL -> Domain ;" +
					//"\"Open Source\" -> MySQL ; " +
					//"PHP -> MySQL ; " +
					//"Java -> MySQL ; " +
					//"\"Proprietary Licence\" -> !MySQL ; " +  
					"" +
					"" +
					""
					) ;
		} catch (Exception e) {
			System.err.println("Error when parsing internal FM: " + e.getMessage());
			return null;
		}
		return createNewFMV(fm, null);
	}
	
	public Tree fmv2PrefuseTree(FeatureModelVariable fmv) {
		Tree t = null;
		try {
			FeatureModel<String> fm = fmv.getFm();
			FeatureGraph<String> fg = fm.getDiagram();
			
			t = generateEmptyTree();
			String rootName = fmv.root().name();
			
			Map<String, Node> nameToNode = new HashMap<String, Node>();
			Node root = t.addRoot();
			root.setString(NAME, rootName);
			nameToNode.put(rootName, root);
	
			convertFeatureGraph2PrefuseTree(fg, t, nameToNode);
			
			// Add Constraints
			Set<Expression<String>> constraints = fm.getConstraints();
			if (null != constraints && constraints.size() > 0) {
				Node groupC = t.addNode();
				groupC.setString(NAME, CONSTRAINTS);
				groupC.setInt(GROUP, new Integer(CONSTRAINT));
				t.addChildEdge(root, groupC);
				Node parent = null;
				for (Expression<String> expression : constraints) {
					Node node = t.addNode();
					node.setString(NAME, expression.toString());
					node.setInt(SOLITARY, new Integer(CONSTRAINT));
					if (null != parent) {
						t.addChildEdge(parent, node);
					} else {
						t.addChildEdge(groupC, node);
					}
					parent = node;
				}
			}
		} catch (Exception e) {
//			FamiliarConsole.INSTANCE.setMessage("Error: In fmv2PrefuseTree: " +
//				e.getMessage());
			return null;
		}
		return t;
	}
	
	private String processNode(Node node, Stack<Node> stack) {
		// Solitaire Feature
		if (NOTAVAILABLE != node.getInt(SOLITARY)) {
			if (OPTIONAL == node.getInt(SOLITARY)) {
				return "[" + node.getString(NAME) + "]" + " ";
			} else if (MANDATORY == node.getInt(SOLITARY)) {
				return node.getString(NAME) + " ";
			} 
		}
		// Feature Group
		else if (NOTAVAILABLE != node.getInt(GROUP)) {
			String group = "";
			Node child=node.getFirstChild(); 
			while (child!=null) {
				stack.push(child);
				group += child.getString(NAME);
				child=child.getNextSibling();
				if (null != child) {
					group += "|";
				}
		    }
			if (FeatureEdge.XOR == node.getInt(GROUP)) {
				return ("(" + group + ")");
			} else if (FeatureEdge.OR == node.getInt(GROUP)) {
				return ("(" + group + ")+");
			}
		}
		return "";
	}
	
	private void traverseNodes(Node root) {  
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (node.getChildCount() > 0 && NOTAVAILABLE == node.getInt(GROUP)
					&& CONSTRAINT != node.getInt(SOLITARY)) {
				sb.append(node.getString(NAME) + " : ");
				Node child=node.getFirstChild(); 
				while (child!=null) {
					stack.push(child);
					String interopName = processNode(child, stack);
					sb.append(interopName + " ");
					child=child.getNextSibling();
			    }
				sb.append(";");
			} 
		}
	}
	
	private void traverseConstraints(Node root) { 
		if (root.getChildCount() <= 0) return;
		Node child=root.getFirstChild();
		if (null == child) return;
		
		Node constr = null;
		// Find a constraint group now
		while (null != child) {
			if (CONSTRAINT == child.getInt(GROUP) && child.getChildCount() > 0) {
				constr = child.getFirstChild(); 
				while (null != constr) {
					sb.append(constr.getString(NAME) + " ");
					constr = constr.getFirstChild(); 
					if (null != constr) {
						sb.append("; ");
					}
			    }
				sb.append(";");
				return;
			}
			child=child.getNextSibling();
		}
	}
	
	public String Tree2String(Tree t) {
		Node root = null;
		try {
			root = t.getRoot();
		} catch (Exception e) {
			return null;
		}
				
		if (null == root || !root.isValid()) {
			return null;
		}
		if (null == root.getFirstChild()) {
			return "FM ( " + root.getString(NAME) + " ; )";
		}
		sb = new StringBuilder();
		sb.append("FM ( ");
		traverseNodes(root);
		traverseConstraints(root);
		sb.append(" )");
		return sb.toString();
	}
	
	public FeatureModel<String> getInternalFM(String fmlText) {
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(fmlText);
		} catch (Exception e) {
			FamiliarConsole.INSTANCE.setMessage("Error: Parsing internal FM " + e.getMessage());
			return null;
		}
		return fm;
	}
	
	// fmName is defines a name of a FMV.
	// By default, both FM name as well as root name are the same, but they can also be different 
	// For example: laptop = new FM (Toshiba... 
	public FeatureModelVariable prefuseTree2Fmv(Tree t, String fmName) {
		String fmlText = Tree2String(t); 
		if (null == fmlText) {
			return null;
		}
		return createNewFMV(getInternalFM(fmlText), fmName);
	}

	public Tree convertFeatureGraph2PrefuseTree(final FeatureGraph<String> g, 
			final Tree t, 
			final Map<String, Node> nameToNode) {

		final Queue<FeatureNode<String>> rest = new LinkedList<FeatureNode<String>>();
		
		Closure<FeatureNode<String>> processNode = new Closure<FeatureNode<String>>() {

			public void execute(FeatureNode<String> v) {
				Collection<FeatureNode<String>> children = g.children(v);
				if (children.size() == 0) {
					return;
				}
				rest.addAll(children);

				Node nv = null;
				if (v.equals(g.getTopVertex())) {
					return;
				} else {
					String ftName = v.getFeature();
					nv = addOrCreateNode(t, ftName);
				}

				// Process Groups
				for (FeatureEdge e : g.incomingEdges(v)) {
					Set<FeatureNode<String>> sources = g.getSources(e);
					if (sources.size() == 1) {
						// Not a group, skip it
						continue;
					}
					// Create a group
					Node ngroup = null;

					switch (e.getType()) {
					case FeatureEdge.XOR:
						ngroup = addOrCreateNode(t, "XOR" + gID++);
						ngroup.setInt(GROUP, new Integer(FeatureEdge.XOR));
						break;
					case FeatureEdge.MUTEX:
						ngroup = addOrCreateNode(t, "MUTEX" + gID++);
						ngroup.setInt(GROUP, new Integer(FeatureEdge.MUTEX));
						break;
					case FeatureEdge.OR:
						ngroup = addOrCreateNode(t, "OR" + gID++);
						ngroup.setInt(GROUP, new Integer(FeatureEdge.OR));
						break;
					}
					if (ngroup != null) {
						// Insert new group
						t.addChildEdge(nv, ngroup);
						for (FeatureNode<String> m : g.getSources(e)) {
							Node nc = addOrCreateNode(t, m.getFeature());
							// Then add all children to the group
							t.addChildEdge(ngroup, nc);
						}
					}
					children.removeAll(sources);
				}
				// Process remaining children
				for (FeatureNode<String> child : children) {
					if (g.findEdge(child, v, FeatureEdge.MANDATORY) == null) {
						Node nchild = addOrCreateNode(t, child.getFeature());
						t.addChildEdge(nv, nchild);
						nchild.setInt(SOLITARY, new Integer(OPTIONAL));
					} else {
						Node nchild = addOrCreateNode(t, child.getFeature());
						t.addChildEdge(nv, nchild);
						nchild.setInt(SOLITARY, new Integer(MANDATORY));
					}
				}
			}

			private Node addOrCreateNode(Tree t, String ftName) {
				Node node = null;
				boolean alreadyExists = nameToNode.containsKey(ftName);
				if (!alreadyExists) {
					node = t.addNode();
					node.setString(NAME, ftName);
					nameToNode.put(ftName, node);
				} else {
					node = nameToNode.get(ftName);
				}
				return node;
			}
		};

		rest.add(g.getTopVertex());
		while (!rest.isEmpty()) {
			processNode.execute(rest.poll());
		}
		return t;
	}
	
	public FeatureModelVariable createNewFMV(FeatureModel<String> fm, String fmName) {
		String rootName = null;
		if (null == fmName) {
			try {
				rootName = getRootName(fm);
			} catch (Exception e) {
				return null;
			}
		}
		return new FeatureModelVariable(null == fmName ? rootName : fmName, fm);
	}
	
	private String getRootName(FeatureModel<String> fm) {
		FeatureGraph<String> fg = fm.getDiagram();
		return fg.children(fg.getTopVertex()).iterator().next().toString();
	}
} // end of class Converter
