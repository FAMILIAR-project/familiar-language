package fr.familiar.operations;

import fr.familiar.experimental.FGroup;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author macher
 * 
 * Knowledge used by the synthesis algorithm 
 *  * hierarchy 
 *  * feature groups
 *  * constraints
 * 
 * 
 * a set of getter/setter methods
 */
public class KnowledgeSynthesis {
	
	private FeatureGraph<String> _hierarchy = null ; 
	
	private Set<FGroup> _groups = new HashSet<FGroup>(); 
	
	private Set<Expression<String>> _constraints = new HashSet<Expression<String>>(); 
	
	
	public KnowledgeSynthesis() {
		// no knowledge
	}
	
	public KnowledgeSynthesis(FeatureGraph<String> hierarchy, Set<FGroup> groups, Set<Expression<String>> constraints) {
		_hierarchy = hierarchy ; 
		_groups = groups ; 
		_constraints = constraints ; 
	}
	
	public KnowledgeSynthesis(FeatureGraph<String> hierarchy, Set<FGroup> groups) {
		this (hierarchy, groups, new HashSet<Expression<String>>()) ;  
		
	}
	
	public KnowledgeSynthesis(FeatureGraph<String> hierarchy) {
		this (hierarchy,  new HashSet<FGroup>(), new HashSet<Expression<String>>()) ;  
		
	}
	
	/**
	 * @return true if the specification is consistent (e.g., features are not part of more than one feature group)
	 */
	public boolean isConsistent() {
		
		Set<FeatureNode<String>> membersOfGroup = new HashSet<FeatureNode<String>>() ;
		for (FGroup fgroup : _groups) {
			Set<FeatureNode<String>> children = fgroup.getSources() ; 
			if (!membersOfGroup.addAll(children)) // source already part of another feature group
				return false ; 
		}
		return true ; 
	}
	
	/**
	 * @return true if the hierarchy is specified
	 */
	public boolean isHierarchySpecified() {
		return _hierarchy != null ; 
	}


	/**
	 * @return the _hierarchy
	 */
	public FeatureGraph<String> getHierarchy() {
		return _hierarchy;
	}


	/**
	 * @param _hierarchy the _hierarchy to set
	 */
	public void setHierarchy(FeatureGraph<String> hierarchy) {
		this._hierarchy = hierarchy;
	}
	
	/**
	 * @param _hierarchy the _hierarchy to set
	 */
	public void setHierarchy(FeatureModel<String> hierarchy) {
		this._hierarchy = hierarchy.getDiagram() ;
	}


	/**
	 * @return the _groups
	 */
	public Set<FGroup> getGroups() {
		return _groups;
	}


	/**
	 * @param _groups the _groups to set
	 */
	public void setGroups(Set<FGroup> groups) {
		this._groups = groups;
	}


	/**
	 * @return the _constraints
	 */
	public Set<Expression<String>> getConstraints() {
		return _constraints;
	}


	/**
	 * @param _constraints the _constraints to set
	 */
	public void setConstraints(Set<Expression<String>> constraints) {
		this._constraints = constraints;
	}

	/** 
	 * 
	 * all features of KST should be in the "domain" of the formula / FM
	 * coherent w.r.t. the targeted feature model
	 * @param fmToSynthesis
	 * @return
	 */
	public KSTReport ckeckKSTCoherence(FeatureModelVariable fmToSynthesis) {
		if (isHierarchySpecified()) {
			Set<String> ftsH = (Set<String>) _hierarchy.features() ; 
			Set<String> ftsFM = fmToSynthesis.features().names() ;
			if(!ftsFM.containsAll(ftsH))
				return new KSTReport("Features specified in the KST do not exist in the feature model: " + Sets.difference(ftsH, ftsFM));
		}
		
		return new KSTReport() ; 
	}

}
