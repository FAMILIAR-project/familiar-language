/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.fm.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.collect.Sets;

import fr.familiar.operations.ExpressionUtility;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.FeatureModelVariableConstraints;
import gsd.graph.DepthFirstEdgeIterator;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FeatureModelToExpression {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureModelToExpression.class);

	public static String _TOP_VARIABLE_NAME = "SYNTETIC_ROOT_FEATURE";

	private FeatureModelVariable _fmv;

	public FeatureModelToExpression(FeatureModelVariable fmv) {
		_fmv = fmv ;
	}
	
	

	public Collection<Expression<String>> convert() {
		
		if (_fmv instanceof FeatureModelVariableConstraints) {
			FeatureModelVariableConstraints fmvCst = (FeatureModelVariableConstraints) _fmv ;
			return fmvCst.getAllConstraints() ; 
		}
		// FIXME OTHER CASES
		else { 
			//assert (_fmv.getFm() != null);
			return _convertInternalFM() ; 
		}
	}

	
	/**
	 * @see {@link #mkStructure(FeatureGraph)}
	 */
	public Expression<String> mkFeatureModel(FeatureModel<String> model) {
		Set<Expression<String>> diagram = mkStructure(model.getDiagram());
		Set<Expression<String>> csts = model.getConstraints() ; 
		Set<Expression<String>> allExprs = Sets.union(diagram, csts);
		
		return mkAnd (allExprs) ;
	}

	private Expression<String> mkAnd(Set<Expression<String>> allExprs) {
		Iterator<Expression<String>> itExpr = allExprs.iterator();
		Expression<String> r = itExpr.next() ; // first
		while (itExpr.hasNext()) {
			Expression<String> e = itExpr.next() ;
			r = r.and(e);
		}
		return r;
	}



	public Expression<String> mkTop(FeatureGraph<String> g) {
		Expression<String> result = mkTrueNode();
		for (FeatureEdge e : g.incomingEdges(g.getTopVertex())) {
			if (e.getType() == FeatureEdge.MANDATORY)
				result = new Expression<String>(ExpressionType.AND, result, mkConjunction(g.getSource(e)));
			else if (e.getType() == FeatureEdge.DEAD)
				result = new Expression<String>(ExpressionType.AND, result, mkFeatureNodeNot(g.getSource(e)));
		}
		return result;
	}
	
	public Expression<String> mkConjunction(FeatureNode<String> v) {
		if (v.isTop())
			return mkTrueNode();
		else if (v.isBottom())
			return mkFalseNode();

		return mkSet(v.features());
	}
	
	public Expression<String> mkSet(Collection<String> fts) {
		Set<Expression<String>> exprs = new HashSet<Expression<String>>()  ;
		for (String ft : fts) {
			exprs.add (new Expression<String>(ft));
		}
		return mkAnd(exprs);
	}

	/**
	 * Creates a BDD representing the structural semantics of the FeatureGraph.
	 * The root feature is not set, since it reduces the BDD. We want to
	 * maintain all structure in the FeatureGraph.
	 * 
	 */		
		
	protected Set<Expression<String>> mkStructure(FeatureGraph<String> g) {
		Set<Expression<String>> exprs = new HashSet<Expression<String>>() ; 
		if (g.vertices().size() == 0)
			return exprs;
		Set<Expression<String>> hierarchy = mkHierarchy(g);
		Set<Expression<String>> groups = mkCardinality(g);
		Set<Expression<String>> rSet = Sets.union(hierarchy, groups);
		return rSet ; 
	}

	@SuppressWarnings("unchecked")
	private Expression<String> mkMutex(Set<FeatureNode<String>> sources) {
		if (sources.size() == 1)
			return mkTrueNode();

		Set<Expression<String>> and = new HashSet<Expression<String>>();
		FeatureNode<String>[] arr = sources.toArray(new FeatureNode[0]);
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				FeatureNode<String> v1 = arr[i];
				FeatureNode<String> v2 = arr[j];
				and.add(new Expression<String>(ExpressionType.IMPLIES, mkDisjunction(v1), mkFeatureNodeNot(v2)));
			}
		}

		return mkAnd(and);
	}
	
	private Expression<String> mkFeatureNodeNot(FeatureNode<String> v) {
		return new Expression<String>(ExpressionType.NOT, v.getFeature(), null);
	}
	
	public Expression<String> mkDisjunction(FeatureNode<String> v) {
		return new Expression<String>(v.getFeature());
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
	public Set<Expression<String>>  mkHierarchy(FeatureGraph<String> g) {
		
		Set<Expression<String>> and = new HashSet<Expression<String>>() ; 
		DepthFirstEdgeIterator<String> iter = new DepthFirstEdgeIterator<String>(g);
		while (iter.hasNext()) {
			FeatureEdge e = iter.next();

			if (e.getType() == FeatureEdge.HIERARCHY) {
				FeatureNode<String> t = g.getTarget(e) ;
				Set<FeatureNode<String>> s = g.getSources(e) ; 
				Expression<String> sources = mkOrNodes(s);
				Expression<String> target = mkConjunction(t);
				and.add(new Expression<String>(ExpressionType.IMPLIES, sources, target));
			}
		}
		return and;
	}
	
	private Expression<String> mkOrNodes(Collection<FeatureNode<String>> fts) {
		
		Iterator<FeatureNode<String>> itNodes = fts.iterator() ;
		FeatureNode<String> fn0 = itNodes.next() ; // first
		Expression<String> result = new Expression<String>(fn0.getFeature());
		while (itNodes.hasNext()) {
			FeatureNode<String> fn1 = itNodes.next() ;
			Expression<String> e1 = get(fn1.getFeature());
			result = new Expression<String>(ExpressionType.OR, result, e1);
		}
		return result;
	}

	public Expression<String> get(String o) {
		return new Expression<String>(o);
	}

	public static Expression<String> mkTrueNode() {
		return new Expression<String>(_TOP_VARIABLE_NAME) ;
		//return new Expression<String>(ExpressionType.TRUE, _TOP_VARIABLE_NAME, null) ;
	}

	public Expression<String> mkFalseNode() {
		return new Expression<String>(ExpressionType.NOT, _TOP_VARIABLE_NAME, null);
	}

	private Set<Expression<String>> mkCardinality(FeatureGraph<String> g) {
		Set<Expression<String>> result = new HashSet<Expression<String>>() ;
		for (FeatureEdge e : g.edges()) {

			Set<FeatureNode<String>> sources = g.getSources(e);
			FeatureNode<String> target = g.getTarget(e);
			
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				result.add(mkMutex(sources));
				break;
			
			case FeatureEdge.MANDATORY:
				result.add(new Expression<String>(ExpressionType.IMPLIES, 
						mkConjunction(target), mkOrNodes(sources)));
				break ; 
			case FeatureEdge.XOR:
				Collection<Expression<String>> disjs = new HashSet<Expression<String>>() ;
				Expression<String> eTarget = mkConjunction(target) ;
				
				for (FeatureNode<String> source : sources) {
					disjs.add(new Expression<String>(ExpressionType.OR, 
							new Expression<String>(ExpressionType.NOT, eTarget, null), 
							get(source.getFeature())));					
				}
				result.add(ExpressionUtility.mkDisjunction(disjs));
					// Mutual Exclusions
				result.add(mkMutex(sources));
				break;
			case FeatureEdge.OR:
				result.add(new Expression<String>(ExpressionType.IMPLIES, mkConjunction(target),
						mkOrNodes(sources)));
				break;
			default:
				// Skip
				assert e.getType() == FeatureEdge.HIERARCHY
						|| e.getType() == FeatureEdge.FROZEN;
			}
		}

		return result;
	}

	private Collection<Expression<String>> _convertInternalFM() {
		
		FeatureModel<String> _fm = _fmv.getFm();		
		Collection<Expression<String>> exprs = new HashSet<Expression<String>>() ; 
		
		exprs.addAll(mkStructure(_fm.getDiagram()));
		exprs.addAll(_fm.getConstraints()); 
		exprs.add(mkTrueNode());
		return exprs ; 
		
	}


	

}
