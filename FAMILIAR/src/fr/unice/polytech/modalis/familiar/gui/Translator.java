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

import fr.unice.polytech.modalis.familiar.fm.FeatureModelCloner;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import prefuse.data.Node;

public class Translator extends Observable {
	// A simple, fast, and thread-safe singleton implementation.
	public final static Translator INSTANCE = new Translator();
	
	private Translator() {
	}
	
	private boolean containsFeature(FeatureGraph<String> fg, String feature) {
		FeatureNode<String> fn = null;
		try {
			fn = fg.findVertex(feature);
		} catch (Exception e) {
			return false;
		}
		return null != fn;
	}
	
	// Verify if FM has well-formed rules. For example, a constraint referring to
	// a feature that is not part of the feature model. 
	private boolean isConstraintValid(FeatureModelVariable fmv, Expression<String> constr) {
		FeatureModel<String> validateFm = null;
		try {
			FeatureModelVariable fmv2 = (FeatureModelVariable) fmv.copy();
			fmv2.removeAllConstraints();
			fmv2.getFm().addConstraint(constr);
			validateFm = FeatureModelCloner.clone(fmv2.getFm());

			Set<Expression<String>> constraints = validateFm.getConstraints();
			for (Expression<String> expression : constraints) {
				Set<String> features = ExpressionUtil.getAllFeatures(expression);
				for (String feature : features) {
					if (!containsFeature(fmv2.getFm().getDiagram(), feature)) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void changedFmv(FeatureModelVariable fmv) {
		if (null == fmv) {
			// Nothing to change since this is not FeatureModelVariable; it's probably ConfigurationVariable
			return; 
		}
		setChanged();
		notifyObservers(null == fmv ? FamiliarConsole.INSTANCE.getLoadedFMV() : fmv);
	}
	
	public FeatureModelVariable renameFeature(String oldFeatureName, String newFeatureName) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (newFeatureName.isEmpty() || null == fmv) {
			return null;
		}
		
		boolean result = fmv.renameFeature(oldFeatureName, newFeatureName);
		if (false == result || !fmv.isValid()) {
			return null;
		}
		return fmv;
	}
	
	/**
	 * insert a new feature (optional or mandatory) into an existing Prefuse tree
	 * @param t
	 * @param parentName
	 * @param newFeatureName
	 * @param groupType
	 * @return
	 */
	public FeatureModelVariable addChildFeature(String parentName, String newFeatureName, int groupType) {
		
		assert (groupType == FeatureEdge.MANDATORY || groupType == FeatureEdge.HIERARCHY);
		
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		
		try {
			FeatureModel<String> fm = fmv.getFm();
			if (null == fm) {
				return null;
			}
			FeatureGraph<String> fg = fm.getDiagram();
			if (null == fg || containsFeature(fg, newFeatureName)) {
				return null;
			}
			FeatureNode<String> parentNode = fg.findVertex(parentName);
			if (null == parentNode) {
				return null;
			}
			FeatureNode<String> newChildNode = new FeatureNode<String>(newFeatureName);
			fg.addVertex(newChildNode);
			fg.addEdge(newChildNode, parentNode, FeatureEdge.HIERARCHY); // optional
			if (groupType == FeatureEdge.MANDATORY)
				fg.addEdge(newChildNode, parentNode, FeatureEdge.MANDATORY);
			
			if (!fmv.isValid()) return null;
		} catch (Exception e) {
			FamiliarConsole.INSTANCE.setMessage("Exception adding child feature: " + e.getMessage());
			return null;
		}
		return fmv;
	}
	
	public FeatureModelVariable addNewGroup(String parentName, 
			String f1, String f2, int groupType) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		
		try {
			FeatureModel<String> fm = fmv.getFm();
			if (null == fm) {
				return null;
			}
			FeatureGraph<String> fg = fm.getDiagram();
			if (null == fg || containsFeature(fg, f1) || containsFeature(fg, f2)) {
				return null;
			}
			FeatureNode<String> parentNode = fg.findVertex(parentName);
			if (null == parentNode) {
				return null;
			}
			FeatureNode<String> newChildNode1 = new FeatureNode<String>(f1);
			FeatureNode<String> newChildNode2 = new FeatureNode<String>(f2);
			
			Collection<FeatureNode<String>> group = new HashSet<FeatureNode<String>>();
			group.add(newChildNode1);
			fg.addVertex(newChildNode1);
			fg.addEdge(newChildNode1, parentNode, FeatureEdge.HIERARCHY);
			
			group.add(newChildNode2);
			fg.addVertex(newChildNode2);
			fg.addEdge(newChildNode2, parentNode, FeatureEdge.HIERARCHY);
			
			fg.addEdge(group, parentNode, groupType);
			
			if (!fmv.isValid()) return null;
		} catch (Exception e) {
			FamiliarConsole.INSTANCE.setMessage("Exception adding new group: " + e.getMessage());
			return null;
		}
		return fmv;
	}
	
	public FeatureModelVariable addGroupedFeature(String parentName, 
			String newFeatureName, int groupType) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		} 
		
		try {
			FeatureModel<String> fm = fmv.getFm();
			if (null == fm) {
				return null;
			}
			FeatureGraph<String> fg = fm.getDiagram();
			if (null == fg || containsFeature(fg, newFeatureName)) {
				return null;
			}
			FeatureNode<String> parentNode = fg.findVertex(parentName);
			if (null == parentNode) {
				return null;
			}
			FeatureNode<String> newChildNode = new FeatureNode<String>(newFeatureName);
			Collection<FeatureEdge> edgesType = fg.incomingEdges(parentNode, groupType);
			if (null == edgesType) {
				return null;
			}
			Set<FeatureNode<String>> group = fg.getSources(edgesType.iterator().next());
			if (null == group) {
				return null;
			}
			FeatureEdge fe = fg.findEdge(group, parentNode, groupType);
			if (null == fe) {
				return null;
			}
			fg.addVertex(newChildNode);
			group.add(newChildNode);
			fg.removeEdge(fe);
			fg.addEdge(group, parentNode, groupType);
			
			if (!fmv.isValid()) return null;
		} catch (Exception e) {
			FamiliarConsole.INSTANCE.setMessage("Exception adding grouped feature: " + e.getMessage());
			return null;
		}
		return fmv;
	}
	
	public FeatureModelVariable deleteGroup(Node group) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		Node child=group.getFirstChild(); 
		while (child!=null) {
			if (false == fmv.removeFeature(child.getString(Converter.NAME))) {
				return null;
			}
			child=child.getNextSibling();
	    }
		if (!fmv.isValid()) return null;
		return fmv;
	}
	
	public FeatureModelVariable newConstraint(String constrStr) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		if (1 == fmv.nbFeatures()) {
			FamiliarConsole.INSTANCE.setMessage("Error: Adding a new constraint to a FM with single feature is not allowed.");
			return null;
		}
		Expression<String> expr = new Expression<String>(constrStr);
		if (!isConstraintValid(fmv, expr)) {
			return null;
		}
		
		boolean result = fmv.getFm().addConstraint(expr);
		if (!fmv.isValid() || !result) {
			return null;
		}
		return fmv;
	}
	
	public FeatureModelVariable updateConstraint(String oldConstr, String newConstr) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		
		Expression<String> newExpr = new Expression<String>(newConstr);
		if (null == fmv || !isConstraintValid(fmv, newExpr)) {
			return null;
		}
		if (removeConstraint(fmv.getFm(), oldConstr)) {
			boolean result = fmv.getFm().addConstraint(newExpr);
			if (!fmv.isValid() || !result) {
				return null;
			}
		}
		return fmv;
	}
	
	public FeatureModelVariable deleteAllConstraints(Node group) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		boolean result = fmv.getFm().removeAllConstraints();
		if (!fmv.isValid() || !result) {
			return null;
		}
		return fmv;
	}
	
	private boolean containsConstraint(Set<Expression<String>> constraints,
			Expression<String> expr) {
		return (constraints.contains(expr) || 
				constraints.toString().contains(expr.toString()));
	}
	
	private boolean equals(Expression<String> expr1,
			Expression<String> expr2) {
		return (expr1.equals(expr2) || 
				expr1.toString().contains(expr2.toString()));
	}
	
	
	private boolean removeConstraint(FeatureModel<String> fm, String constrStr) {
		Set<Expression<String>> constraints = fm.getConstraints();
		Expression<String> removeExpr = new Expression<String>(constrStr);
		
		if (containsConstraint(constraints, removeExpr)) {
			fm.removeAllConstraints();
			for (Expression<String> expr : constraints) {
				if (!equals(expr, removeExpr)) {
					if (!fm.addConstraint(expr)) {
						FamiliarConsole.INSTANCE.setMessage(
							"Error: Unable to add a constraint back: " + expr);
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	public FeatureModelVariable deleteConstraint(String constrStr) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		
		FeatureModel<String> fm = fmv.getFm();
		if (null == fm) {
			return null;
		}
		
		if (!removeConstraint(fm, constrStr)) return null;
		
		if (!fmv.isValid()) return null;
		
		return fmv;
	}
	
	public FeatureModelVariable deleteFeature(String featureName) {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return null;
		}
		boolean featureRemoved = fmv.removeFeature(featureName);
		if (!fmv.isValid() || !featureRemoved) {
			return null;
		}
		return fmv;
	}
} // end of class Translator
