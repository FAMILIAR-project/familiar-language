/**
 * 
 */
package fr.unice.polytech.modalis.familiar.fm.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;


import splar.core.constraints.BooleanVariable;
import splar.core.constraints.PropositionalFormula;
import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModel;
import splar.core.fm.FeatureModelException;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.SolitaireFeature;
import splar.core.fm.XMLFeatureModel;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureName;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class SPLOTtoFML {

	private static Logger _LOGGER = Logger.getLogger(SPLOTtoFML.class);


	private boolean _needsToInterop; // if false we keep original names

	private Map<String, String> _idsToNames;

	public SPLOTtoFML() {
		this(true);
	}

	public SPLOTtoFML(boolean interoperability) {
		_idsToNames = new HashMap<String, String>();
		_needsToInterop = interoperability;
	}

	/**
	 * @param featureModelSPLOT
	 * @return string-based representation of FML feature model
	 */
	@Deprecated
	public String convert(FeatureModel featureModelSPLOT) {

		StringBuilder sb = new StringBuilder();
		sb.append("FM ( ");

		Stack<FeatureTreeNode> stack = new Stack<FeatureTreeNode>();
		stack.push(featureModelSPLOT.getRoot());

		while (!stack.isEmpty()) {
			FeatureTreeNode ftNode = stack.pop();
			if (!ftNode.isLeaf() && !(ftNode instanceof FeatureGroup)) { // not
				// a
				// left
				// and
				// not
				// a
				// group
				String parentName = interoperableFtName(ftNode.getName());
				sb.append(parentName + " : ");
				for (int i = 0; i < ftNode.getChildCount(); i++) {
					FeatureTreeNode featureTreeNode = (FeatureTreeNode) ftNode
							.getChildAt(i);
					stack.push(featureTreeNode);
					String interopName = treatFt(featureTreeNode, stack);
					sb.append(interopName + " ");
				}
				sb.append(";\n");
			}
		}

		// constraints handling now
		Collection<PropositionalFormula> formulaCsts = featureModelSPLOT
				.getConstraints();
		for (PropositionalFormula formulaCst : formulaCsts) {
			String strFormula = "";
			Collection<BooleanVariable> bVars = formulaCst.getVariables();
			int nCst = 0;
			for (BooleanVariable bv : bVars) {
				if (!bv.isPositive()) {
					strFormula += "!";
				}
				String bvID = bv.getID();
				FeatureTreeNode ftCst = featureModelSPLOT.getNodeByID(bvID);
				String cstName = interoperableFtName(ftCst);
				strFormula += cstName;
				if (nCst++ != (bVars.size() - 1))
					strFormula += " | ";

			}

			sb.append(strFormula + ";" + "\n");
		}

		sb.append(" )");

		return sb.toString();

	}

	private String interoperableFtName(FeatureTreeNode ftNode) {

		String ftName = genUniqueFtName(ftNode); // ftNode.getName() ; //
		_idsToNames.put(ftNode.getID(), ftName);
		String interopName = interoperableFtName(ftName);
		return interopName;
	}

	private String genUniqueFtName(FeatureTreeNode ftNode) {

		String keyID = ftNode.getID();
		if (_idsToNames.containsKey(keyID))
			return _idsToNames.get(keyID);

		Collection<String> ftNames = _idsToNames.values();
		String ftName = ftNode.getName();

		if (ftNames.contains(ftName)) {
			for (int i = 0;; i++) {
				if (!ftNames.contains(genUniqueFt(ftName, i))) {
					String ftUniqueName = genUniqueFt(ftName, i);
					_LOGGER.debug("\t\tduplidated feature name: " + ftUniqueName);
					return ftUniqueName;
				}
			}
		} else {
			return ftName;
		}

	}

	private String interoperableFtName(String ftName) {
		if (!_needsToInterop)
			return ftName;
		String res = FeatureName.normalize(ftName);

		res = res.replace("-", "");
		res = res.replace(" ", "");
		res = res.replace(".", "DOT");
		
		res = res.replace("/", "_");
		res = res.replace("$", "USDollar");
		
		res = res.replace("0", "Zero");
		res = res.replace("1", "One");
		res = res.replace("2", "Two");
		res = res.replace("3", "Three");
		res = res.replace("4", "Four");
		res = res.replace("5", "Five");
		res = res.replace("6", "Six");
		res = res.replace("7", "Seven");
		res = res.replace("8", "Eight");
		res = res.replace("9", "Nine");
		
		res = res.replace("+", "PLUS");

		return res;
	}

	private String genUniqueFt(String ftName, int i) {
		return ftName + "_" + i;
	}

	private String treatFt(FeatureTreeNode featureTreeNode,
			Stack<FeatureTreeNode> stack) {
		String ftName = "";
		if (featureTreeNode instanceof SolitaireFeature) {
			ftName = interoperableFtName(featureTreeNode);
			SolitaireFeature sFT = (SolitaireFeature) featureTreeNode;
			if (sFT.isOptional()) {
				ftName = "[" + ftName + "]";
			}
		}

		else if (featureTreeNode instanceof FeatureGroup) {
			FeatureGroup gFT = (FeatureGroup) featureTreeNode;

			for (int i = 0; i < gFT.getChildCount(); i++) {
				FeatureTreeNode ftNode = (FeatureTreeNode) gFT.getChildAt(i);
				stack.push(ftNode);
				ftName += interoperableFtName(ftNode);
				if (i != (gFT.getChildCount() - 1))
					ftName += "|";
			}

			if (gFT.getMax() == 1 && gFT.getMin() == 1) // XOR
				ftName = "(" + ftName + ")";
			else if (gFT.getMax() == -1 && gFT.getMin() == 1) // OR
				ftName = "(" + ftName + ")+";
		}

		String interopName = interoperableString(ftName);
		return interopName;

	}

	private String interoperableString(String str) {

		if (!_needsToInterop)
			return str;
		String res = str;
		res = res.replace("-", "");
		res = res.replace(" ", "");

		return res;
	}
	
		public String convert(File splotFile) {
		
		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				splotFile.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			FMLShell.getInstance().printError(
					"Unable to load SPLOT feature model "
							+ e.getMessage());
			return null;
		}

		return convert(featureModelSPLOT);
	}

	/**
	 * Convert a SPLOT feature model to a gsd feature model
	 * @param featureModelSPLOT
	 * @return
	 */
	public gsd.synthesis.FeatureModel<String> convertToFeatureModel(FeatureModel featureModelSPLOT) {

		// Convert feature diagram
		FeatureGraph<String> featureGraph = FeatureGraph.mkStringFeatureGraph();
		convertHierarchy(featureGraph, featureModelSPLOT.getRoot());

		// Convert constraints
		gsd.synthesis.FeatureModel<String> featureModelFAMILIAR = new gsd.synthesis.FeatureModel<String>(featureGraph);
		convertConstraints(featureModelFAMILIAR, featureModelSPLOT);

		return featureModelFAMILIAR;
	}


	private void convertHierarchy(FeatureGraph<String> featureGraph, FeatureTreeNode root) {
		// Convert root node
		Stack<FeatureTreeNode> stack = new Stack<FeatureTreeNode>();
		stack.push(root);
		FeatureNode<String> rootFeature = new FeatureNode<String>(root.getDescription());
		featureGraph.addVertex(rootFeature);
		featureGraph.addEdge(rootFeature, featureGraph.getTopVertex(), FeatureEdge.HIERARCHY);
		featureGraph.addEdge(rootFeature, featureGraph.getTopVertex(), FeatureEdge.MANDATORY);

		// Convert other nodes
		while (!stack.isEmpty()) {
			FeatureTreeNode node = stack.pop();

			int type;
			String parentName;


			if (!(node instanceof FeatureGroup)) {
				parentName = node.getDescription();

				for (int i = 0; i < node.getChildCount(); i++) {
					FeatureTreeNode child = (FeatureTreeNode) node.getChildAt(i);
					FeatureNode<String> childFeature = new FeatureNode<String>(child.getDescription());

					// Add features and skip group nodes
					if (child instanceof SolitaireFeature) {
						SolitaireFeature solitaireNode = (SolitaireFeature) child;
						featureGraph.addVertex(childFeature);
						featureGraph.addEdge(childFeature, featureGraph.findVertex(parentName), FeatureEdge.HIERARCHY);
						if (!solitaireNode.isOptional()) {
							featureGraph.addEdge(childFeature, featureGraph.findVertex(parentName), FeatureEdge.MANDATORY);
						}
					}

					stack.push(child);
				}
			} else { 
				parentName = ((FeatureTreeNode) node.getParent()).getDescription();
				FeatureNode<String> parent = featureGraph.findVertex(parentName);

				// Set group type
				FeatureGroup group = (FeatureGroup) node;
				if (group.getMin() == 0 && group.getMax() == 1) {
					type = FeatureEdge.MUTEX;	
				} else if (group.getMin() == 1 && group.getMax() == 1) {
					type = FeatureEdge.XOR;
				} else {
					type = FeatureEdge.OR;
				}

				// Collect children
				ArrayList<FeatureNode<String>> children = new ArrayList<FeatureNode<String>>();
				for (int i = 0; i < node.getChildCount(); i++) {
					FeatureTreeNode child = (FeatureTreeNode) node.getChildAt(i);
					FeatureNode<String> childFeature = new FeatureNode<String>(child.getDescription());
					featureGraph.addVertex(childFeature);
					featureGraph.addEdge(childFeature, parent, FeatureEdge.HIERARCHY);
					children.add(childFeature);
					stack.push(child);
				}

				// Add group to the feature model
				featureGraph.addEdge(children, parent, type);
			}


		}
	}

	private void convertConstraints( gsd.synthesis.FeatureModel<String> featureModelFAMILIAR, FeatureModel featureModelSPLOT) {
		for (PropositionalFormula formula : featureModelSPLOT.getConstraints()){
			Collection<BooleanVariable> variables = formula.getVariables();
			Expression<String> constraint = null;
			
			for (BooleanVariable variable : variables) {
				Expression<String> variableConstraint;
				String variableName = featureModelSPLOT.getNodeByID(variable.getID()).getDescription();
				variableConstraint = new Expression<String>(variableName);
				if (!variable.isPositive()) {
					variableConstraint = variableConstraint.not();
				}
				
				if (constraint == null) {
					constraint = variableConstraint;
				} else {
					constraint = constraint.or(variableConstraint);					
				}
			}
			
			featureModelFAMILIAR.addConstraint(constraint);
		}
	}

}
