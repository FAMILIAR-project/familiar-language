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

package fr.familiar.operations;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;

import java.util.Set;

/**
 * @author macher1
 *
 * based on the metrics proposed in
 * Assessing the maintainability of software product line feature models using structural metrics
   (Ebrahim Bagheri and Dragan Gasevic), Software Quality Journal
   
   A good exercice for evaluating the conciness of the API and 
   the expressivness of the FAMILIAR language (completeness of pre-defined operators and language constructs to implement the metrics)
 *
 */
public class StructuralMetricsFM {

	protected FeatureModelVariable _fmv;

	/**
	 * 
	 */
	public StructuralMetricsFM(FeatureModelVariable fmv) {
		_fmv = fmv ; 
	}
	
	/*
	 * Size measures
	 */
	
	
	/** 
	 * 
	 * the total number of features that are present in a feature model. This includes both leaf and parent features. NF counts all of the nodes in the feature model tree
	 * @return NF aka number of features
	 */
	public int nbFeatures() {
		return _fmv.features().size() ; 
	}
	
	/** 
	 *
	 * The number of features that are first direct descendants of the feature model root. In other words, the number of nodes in depth one of the tree
	 * @return NTop, aka number of children features of the root
	 */
	public int nbTopFeatures() {
		return _fmv.root().children().size() ; 
	}
	
	/**
	 * The number of features with no children or further specializations. These correspond with the leafs of the feature model tree
	 * @return NLeaf, aka number of leaves in the tree
	 */
	public int nbLeafFeatures() {
		return _fmv.leaves().size() ; 
	}
	
	/*
	 * Structural complexity measures
	 */
	
	/**
	 * The number of distinct cycles that can be found in the feature model. 
	 * Since feature models are in the form of trees, no cycles can exist in a feature model; 
	 * however, integrity constraints between the available features can cause cycles. 
	 * It is simple to show that the number of distinct cycles and hence cyclomatic complexity of a feature model is equivalent to the number of integrity constraints of a feature model. 
	 * This is due to the tree-like (cycleless graph) structure of feature models
	 * @return CC, aka number of cycles (introduced by constraints)
	 */
	public int nbCyclomatic() {
		return _fmv.getAllConstraints().size() ; 
	}
	
	/**
	 * The ratio of the number of unique features involved in the feature model integrity constraint over all of the number of features in the feature model. 
	 * This measure represents the degree of involvement of features in the definition of the integrity constraints
	 * @return CTC aka CTCR in Mendonca et al.
	 */
	public double nbCTC() {
		return _fmv.CTCR() ;
	}
	
	/**
	 * The average branching factor of the parent features in the feature model. 
	 * In other words, the average number of children of the nodes in the feature model tree
	 * @return RoV
	 */
	public int ratioOfVariability() {
		// TODO
		return - 1; 
	}
	
	/**
	 * The ratio of the number of edges over the number of features in a feature model. 
	 * In graph theory, the coefficient of connectivity represents how well the graph components are connected
	 * @return aka CoC
	 */
	public double CoC() {
		Set<FeatureEdge> treeEdges = _fmv.getFm().getDiagram().selectEdges(FeatureEdge.HIERARCHY);
		double nEdges = (treeEdges.size() - 1) + nbCyclomatic() ;
		//return nEdges ;
		return (double) (nEdges / nbFeatures()); 
	}
	
	/**
	 * This is the ratio of the number of optional features over all of the available features in the feature model. 
	 * The rationale behind this is that the more optional features exist in the feature model, the more choices are available for the designers to choose from while configuring the feature model
	 * @return aka FoC
	 */
	public double FoC() {
		double nOptionals = _fmv.getOptionals().size() ;
		return (double) (nOptionals / nbFeatures()) ; 
	}
	
	/**
	 * The number of all possible and valid configurations that can be derived from the feature model in the face of its integrity constraints and tree structure
	 * @return aka NVC
	 */
	public double nbValidConfigurations() {
		return _fmv.counting() ; 
	}
	
	/**
	 * The length of the longest path from the feature model root to leaf features in the feature model
	 * @return
	 */
	public int depthOfTree() {
		return _fmv.depth() ; 
	}
	
	@Override
	public String toString() {
		return "NF " + nbFeatures() + "\n"
				+ "NTop " + nbTopFeatures() + "\n"
				+ "NLeaf " + nbLeafFeatures() + "\n"
				+ "CC " + nbCyclomatic() + "\n"
				+ "CTC " + nbCTC() + "\n"
				+ "RoV " + ratioOfVariability() + "\n"
				+ "CoC " + CoC() + "\n" 
				+ "FoC " + FoC() + "\n" 
				+ "NVC " + nbValidConfigurations() + "\n"
				+ "DT " + depthOfTree() ;
	}
	
	

}
