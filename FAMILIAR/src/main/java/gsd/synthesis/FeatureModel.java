/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.synthesis;

import static gsd.synthesis.ExpressionType.FEATURE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.collections15.CollectionUtils;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

/**
 * A class containing a FeatureGraph describing the feature diagram and a set of
 * extra constraints. Implies and Excludes edges are described in a directed and
 * undirected graph respectively.
 *
 * @author Steven She (shshe@uwaterloo.ca)
 */
public class FeatureModel<T> implements Cloneable {

	Logger logger = Logger.getLogger(FeatureModel.class.getName());
	private FeatureGraph<T> _diagram;

	/**
	 * Set of extra constraints that are not binary.
	 */
	private final Set<Expression<T>> _constraints = new HashSet<Expression<T>>();

	/**
	 * Digraph to represent implies (requires) edges.
	 */
	private SimpleDirectedGraph<T, Requires<T>> _implG
		= new SimpleDirectedGraph<T, Requires<T>>(new EdgeFactory<T, Requires<T>>() {
			public Requires<T> createEdge(T source, T target) {
				return new Requires<T>(source, target);
			}
	});

	/**
	 * Undirected graph to represent excludes edges.
	 */
	private UndirectedGraph<T, Excludes<T>> _exG
		= new SimpleGraph<T, Excludes<T>>(new EdgeFactory<T, Excludes<T>>() {
			public Excludes<T> createEdge(T source, T target) {
				return new Excludes<T>(source, target);
			}
		});

	public FeatureModel(FeatureGraph<T> g) {
		_diagram = g;
	}

	public FeatureModel<T> clone() {
		FeatureModel<T> fm = new FeatureModel<T>(_diagram.clone());
		fm.addAllConstraints(_constraints);
		return fm;
	}

	public Set<T> features() {
		Set<T> result = _diagram.features();
		for (Expression<T> c : _constraints) {
			features(c, result);
		}
		return result;
	}

	private void features(Expression<T> expr, Set<T> features) {
		if (expr == null)
			return;
		features(expr.getLeft(), features);
		features(expr.getRight(), features);
		if (expr.getType() == FEATURE) {
			features.add(expr.getFeature());
		}
	}

	/**
	 * Structurally (syntactically) compare two FeatureModels. To compare
	 * semantically, use the BDD representation.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FeatureModel) {
			FeatureModel<?> other = (FeatureModel<?>) obj;

			return getDiagram().equals(other.getDiagram())
				&& getConstraints().equals(other.getConstraints());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getDiagram().hashCode() + getConstraints().hashCode();
	}

	@Override
	public String toString() {
        return new FeatureModelSerializer(FeatureGraphFactory.mkStringFactory(), true).toString(this);
	}


	public FeatureGraph<T> getDiagram() {
		return _diagram;
	}

	public void addAllConstraints(Collection<? extends Expression<T>> es) {
		for (Expression<T> e : es)
			addConstraint(e);
	}

	public boolean addConstraint(Expression<T> e) {
		
		
		
		if (e == null)
			throw new IllegalArgumentException("expression cannot be null!");

		if (_constraints.contains(e)) {
			return false;
		}

		BinaryExpression<T> bin = BinaryExpression.mkCanonicalBinaryExpression(e);
		if (bin == null) {
			_constraints.add(e);
		}
		else if (bin.isRequires()) {
			Requires<T> req = (Requires<T>) bin;
			if (_implG.containsEdge(req) || req.getAntecedent().equals(req.getConsequent())) {
			//	System.out.println("duplicate constraint: " + req);
				return false;
			}

			_implG.addVertex(req.getAntecedent());
			_implG.addVertex(req.getConsequent());
			_implG.addEdge(req.getAntecedent(), req.getConsequent(), req);
		}
		else {
			Excludes<T> ex = (Excludes<T>) bin;
			if (_exG.containsEdge(ex))
				return false;

			_exG.addVertex(ex.getAntecedent());
			_exG.addVertex(ex.getConsequent());
			_exG.addEdge(ex.getAntecedent(), ex.getConsequent(), ex);
		}
		return true;
	}

	public boolean removeConstraint(Expression<T> e) {
		if (!_constraints.contains(e))
			return false;

		BinaryExpression<T> bin = BinaryExpression.mkCanonicalBinaryExpression(e);
		if (bin == null) {
			_constraints.remove(e);
		}
		else if (bin.isRequires()) {
			Requires<T> req = (Requires<T>) bin;
			_implG.removeEdge(req.getAntecedent(), req.getConsequent());
		}
		else {
			Excludes<T> ex = (Excludes<T>) bin;
			_exG.removeEdge(ex.getAntecedent(), ex.getConsequent());
		}
		return true;
	}

	public Set<Expression<T>> getConstraints() {
		Set<Expression<T>> result = new HashSet<Expression<T>>(_constraints);
		result.addAll(_implG.edgeSet());
		result.addAll(_exG.edgeSet());
		return Collections.unmodifiableSet(result);
	}
	
	/**
	 * AM: remove all constraints from the feature models (TODO: removeConstraint is buggy)
	 * @return if the removal has been consistently performed
	 */

	public boolean removeAllConstraints() {
		
		_constraints.clear();
		
		//_implG.removeAllEdges(_implG.edgeSet());
		//boolean breq = true ;
		
		/*Set<Requires<T>> reqs = _implG.edgeSet() ;
		// Hint: ConcurrentModificationException may suddenly happear
		
		if (reqs.size() > 0)
			for (Requires<T> req : reqs) {
				if(_implG.removeEdge(req.getAntecedent(), req.getConsequent()) == null)
					breq = false;
			}*/
		//_implG.removeAllVertices(_implG.vertexSet());
		
		_implG = new SimpleDirectedGraph<T, Requires<T>>(new EdgeFactory<T, Requires<T>>() {
			public Requires<T> createEdge(T source, T target) {
				return new Requires<T>(source, target);
			}
			}) ;
		
		_exG
		= new SimpleGraph<T, Excludes<T>>(new EdgeFactory<T, Excludes<T>>() {
			public Excludes<T> createEdge(T source, T target) {
				return new Excludes<T>(source, target);
			}
		});
	
		
		// _exG.removeAllEdges(_exG.edgeSet());
		//boolean bexcl = true ;
		
		/*Set<Excludes<T>> excs = _exG.edgeSet() ;
		if (excs.size() > 0)
			for (Excludes<T> excl : excs) {
				if(_exG.removeEdge(excl.getAntecedent(), excl.getConsequent()) == null)
					bexcl = false;
			}
			*/
		//_exG.removeAllVertices(_exG.vertexSet());	
		
		//return breq && bexcl ;
		return true ;
	}

	public SimpleDirectedGraph<T, Requires<T>> getRequiresGraph() {
		return _implG;
	}

	public UndirectedGraph<T, Excludes<T>> getExcludesGraph() {
		return _exG;
	}

	public Set<Requires<T>> getRequires() {
		return _implG.edgeSet();
	}

	public Set<Excludes<T>> getExcludes() {
		return _exG.edgeSet();
	}

	/**
	 * Adds any variables referenced in extra constraints into the feature
	 * diagram as free variables.
	 */
	public void addFreeVariables() {
		for (Expression<T> e : _constraints) {
			Collection<T> exprFeats = ExpressionUtil.getAllFeatures(e);
			for (T f : CollectionUtils.subtract(exprFeats, _diagram.features()))
				_diagram.addFreeVariable(f);
		}

		for (T f : CollectionUtils.subtract(_implG.vertexSet(), _diagram.features()))
			_diagram.addFreeVariable(f);

		for (T f : CollectionUtils.subtract(_exG.vertexSet(), _diagram.features()))
			_diagram.addFreeVariable(f);
	}

	/**
	 * Returns the set of vertices that are connected to v through mandatory
	 * edges and hierarchy edges (including v itself)
	 */
	private Set<FeatureNode<T>> getTransitiveConnected(FeatureNode<T> v) {
		final Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>>();
		result.add(v);

		for (FeatureEdge e : _diagram.outgoingEdges(v, FeatureEdge.HIERARCHY)) {
			FeatureNode<T> target = _diagram.getTarget(e);
			result.addAll(getTransitiveConnected(target));
		}

		collectMandatoryChildren(v, result);
		return result;
	}

	private void collectMandatoryChildren(FeatureNode<T> v, Set<FeatureNode<T>> result) {
		for (FeatureEdge e : _diagram.incomingEdges(v, FeatureEdge.MANDATORY)) {
			FeatureNode<T> source = _diagram.getSource(e);
			result.add(source);
			collectMandatoryChildren(source, result);
		}
	}

	private boolean containsPath(FeatureNode<T> source, FeatureNode<T> target) {
		Set<FeatureNode<T>> ancSource = getTransitiveConnected(source);
		if (ancSource.contains(target))
			return true;
		return false;
	}
}