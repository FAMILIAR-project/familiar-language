/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
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
package fr.familiar.fm.basic;

import java.util.Set;

import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;

/**
 * @author mathieuacher
 * 
 */
public interface FMLFeatureModel {

	public abstract FeatureVariable root();

	/** aka satisfiability or "non void" 
	 * @return true if the feature model represents at least one valid configuration
	 */
	public abstract boolean isValid();

	public abstract SetVariable features();


	public abstract boolean setOptional(FeatureVariable ft);

	public abstract boolean setAlternative(FeatureVariable ft);

	public abstract boolean setOr(FeatureVariable ft);

	/**
	 * is time consuming 
	 * @return the number of valid configurations 
	 */
	public abstract double counting();
	
	/**
	 *  core features
	 * @return the set of core features of a feature model
	 */
	public abstract Set<String> cores() ;
	
	/**
	 * @return the set of "dead" features, i.e., features that do not appear in
	 *         any configuration of a feature model
	 */
	public abstract Set<String> deads() ; 

	/**
	 * We define cross-tree constraint ratio (CTCR) as the ratio of the number
	 * of features in the cross-tree constraints to the number of features in
	 * the feature tree.
	 * 
	 * @return CTCR of the feature model
	 */
	public abstract double CTCR();
	
	
	/**
	 * @return the binary implication graph 
	 */
	public abstract ImplicationGraph<String> computeImplicationGraph() ;
	
	
	
	public abstract FeatureModelVariable slice(SliceMode sliceMode, String... fts) ; 
	public abstract FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) ;
	
	
	public abstract FeatureModelVariable ksynthesis (KnowledgeSynthesis kn) ;

	void setFeatureAttribute(FeatureVariable ft, String attributeID, Variable rVar);

	boolean setMandatory(FeatureVariable ft);
	
	
	

}
