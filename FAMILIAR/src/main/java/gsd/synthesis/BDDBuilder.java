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

import gsd.graph.DepthFirstEdgeIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

import org.apache.commons.collections15.BidiMap;
import org.apache.commons.collections15.bidimap.DualHashBidiMap;
import org.apache.commons.collections15.iterators.LoopingIterator;

/**
 * Treats all features as a single universe even if they belong to different
 * feature models.
 *
 * @author Steven She <shshe@uwaterloo.ca>
 */
public class BDDBuilder<T extends Comparable<T>> {

	protected static BDDFactory _factory;
	// Can't start at 0 since machinery treats negatives as
	// negations
	private int i = 1;

	private BidiMap<T, Integer> _featToVar = new DualHashBidiMap<T, Integer>();
	private Logger logger = Logger.getLogger(BDDBuilder.class.getName());
	private final FeatureGraphFactory<T> _fgf;

	public BDDBuilder(FeatureGraphFactory<T> fgf) {
		_fgf = fgf;
		initFactory(10000000, 1000000, 1000);
	}

	public BDDBuilder(int nodes, int cache, int var, FeatureGraphFactory<T> fgf) {
		_fgf = fgf;
		initFactory(nodes, cache, var);
	}

	private void initFactory(int nodes, int cache, int var) {
		if (_factory == null) {
			_factory = BDDFactory.init("java", nodes, cache);
			_factory.setVarNum(var);
			//_factory.setError(1);
		}
	}

	public BDDBuilder(BDDBuilder<T> builder) {
		_featToVar = new DualHashBidiMap<T, Integer>(builder.getFeatureMap());
		_factory = builder.getFactory();
		_fgf = builder.getFeatureGraphFactory();
	}

	public boolean contains(T o) {
		return _featToVar.containsKey(o);
	}

	public BDD get(T o) {
		assert o != null;

		if (o.equals(_fgf.getTopFeature()))
			return _factory.one();
		else if (o.equals(_fgf.getBottomFeature()))
			return _factory.zero();

		int var = _featToVar.containsKey(o) ? _featToVar.get(o) : add(o);
		
		return _factory.ithVar(var);
	}

	public BDD nget(T o) {
		assert o != null;

		if (o.equals(_fgf.getTopFeature()))
			return _factory.zero();
		else if (o.equals(_fgf.getBottomFeature()))
			return _factory.one();

		int var = _featToVar.containsKey(o) ? _featToVar.get(o) : add(o);
		return _factory.nithVar(var);
	}

	public BDD mkSet(Collection<T> set) {
		if (set.contains(_fgf.getBottomFeature()))
			return zero();
		return getFactory().makeSet(vars(set));
	}

	private BDD mkOrNodes(Collection<FeatureNode<T>> set) {
		BDD result = _factory.zero();
		for (FeatureNode<T> v : set) {
			for (T f : v.features()) {
				result.orWith(get(f));
			}
		}
		return result;
	}

	public BDD mkConjunction(FeatureNode<T> v) {
		if (v.isTop())
			return _factory.one();
		else if (v.isBottom())
			return _factory.zero();

		return mkSet(v.features());
	}

	public BDD mkDisjunction(FeatureNode<T> v) {
		BDD result = _factory.zero();
		for (T f : v.features()) {
			result.orWith(get(f));
		}
		return result;
	}

	private BDD mkFeatureNodeNot(FeatureNode<T> v) {
		BDD and = mkSet(v.features());
		BDD result = and.not();
		and.free();
		return result;
	}


	public int variable(T o) {
		if (o.equals(_fgf.getTopFeature()))
			throw new IllegalArgumentException("cannot obtain variable for top!");
		if (o.equals(_fgf.getBottomFeature()))
			throw new IllegalArgumentException("cannot obtain variable for bottom!");

		return _featToVar.containsKey(o) ? _featToVar.get(o) : add(o);
	}

	public T feature(int i) {
		return _featToVar.inverseBidiMap().get(i);
	}

	public BDDFactory getFactory() {
		return _factory;
	}

	public FeatureGraphFactory<T> getFeatureGraphFactory() {
		return _fgf;
	}

	public Map<T, Integer> getFeatureMap() {
		return _featToVar;
	}

	/**
	 * Extend the number of variables in the BDDFactory, but only when necessary.
	 * @return the old varNum
	 */
	private int extVarNum(int num) {
		if (_factory.varNum() - i + 1 >= num) {
			return _factory.varNum();
		}
		return _factory.setVarNum(_factory.varNum() + 1000);
	}

	/**
	 * Returns a formula representing true.
	 */
	public BDD one() {
		return _factory.one();
	}

	public Formula<T> oneF() {
		return new Formula<T>(_factory.one(), domain(), this);
	}

	public Formula<T> zeroF() {
		return new Formula<T>(_factory.zero(), Collections.<T>emptySet(), this);
	}


	/**
	 * Returns a formula representing false.
	 * @return
	 */
	public BDD zero() {
		return _factory.zero();
	}

	public int add(T o) {
		assert o != null;
		assert !_featToVar.containsKey(o);

		if (o.equals(_fgf.getTopFeature()))
			throw new IllegalArgumentException(o + " is the same as the top feature!");
		else if (o.equals(_fgf.getBottomFeature()))
			throw new IllegalArgumentException(o + " is the same as the bottom feature!");

		int var = i++;
		extVarNum(1);

		_featToVar.put(o, var);
		return var;
	}

	public int remove(T o) {
		return _featToVar.remove(o);
	}

	/**
	 * Resets the feature to var mapping and the BDDFactory.
	 * FIXME there seems to be a bug with supportSize in BuDDY and JavaBDD not resetting.
	 */
	public void reset() {
		i=1;
		_featToVar = new DualHashBidiMap<T, Integer>();
		//FIXME Workaround for bug with supportSize variable in JFactory
		int newVarNum = _factory.varNum() + 1;
		_factory.reset();
		_factory.setVarNum(newVarNum);
	}

	public int[] vars(Collection<T> features) {
		ArrayList<T> list = new ArrayList<T>(features);
		Iterator<T> iter = list.iterator();

		//Check against top and bottom features
		while (iter.hasNext()) {
			T next = iter.next();
			if (next.equals(_fgf.getTopFeature()))
				iter.remove();
			else if (next.equals(_fgf.getBottomFeature()))
				return new int[0];
		}

		int[] result = new int[list.size()];
		iter = list.iterator();
		for (int i=0; i<result.length; i++) {
			result[i] = variable(iter.next());
		}
		return result;
	}


	public Set<T> support(BDD formula) {
		Set<T> result = new HashSet<T>();
		BDD supp = formula.support();

		if (supp.isOne()) {
			supp.free();
			return result;
		}

		int[] set = supp.scanSet();
		for (int i=0; i<set.length; i++) {
			result.add(feature(set[i]));
		}
		supp.free();
		return result;
	}

	public BDD mkExpression(Expression<T> expr) {
		if (expr == null) return null;

		BDD left = mkExpression(expr.getLeft());
		BDD right = mkExpression(expr.getRight());
		switch (expr.getType()) {
		case TRUE:
			return _factory.one();
		case FALSE:
			return _factory.zero();
		case AND:
			return left.andWith(right);
		case OR:
			return left.orWith(right);
		case IMPLIES:
			return left.impWith(right);
		case IFF:
			return left.biimpWith(right);
		case NOT:
			BDD result = left.not();
			left.free();
			return result;
		case FEATURE:
			// AM: buggy in !B -> !C with map // TODO does not resolve the problem
			if (expr.getFeature() == null) return null ;
			return get(expr.getFeature());
		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * @see {@link #mkStructure(FeatureGraph)}
	 */
	public Formula<T> mkFeatureModel(FeatureModel<T> model) {
		BDD diagram = mkStructure(model.getDiagram());
		for (Expression<T> expr : model.getConstraints()) {
			diagram.andWith(mkExpression(expr));
		}
		return new Formula<T>(diagram, model.features(), this);
	}

	public BDD mkTop(FeatureGraph<T> g) {
		BDD result = _factory.one();
		for (FeatureEdge e : g.incomingEdges(g.getTopVertex())) {
			if (e.getType() == FeatureEdge.MANDATORY)
				result.andWith(mkConjunction(g.getSource(e)));
			else if (e.getType() == FeatureEdge.DEAD)
				result.andWith(mkFeatureNodeNot(g.getSource(e)));
		}
		return result;
	}

	/**
	 * Creates a BDD representing the structural semantics of the FeatureGraph.
	 * The root feature is not set, since it reduces the BDD. We want to
	 * maintain all structure in the FeatureGraph.
	 *
	 */
	protected BDD mkStructure(FeatureGraph<T> g) {
		if (g.vertices().size() == 0) return _factory.one();
		BDD result = mkHierarchy(g).getBDD();
		result.andWith(mkAndGroups(g));
		result.andWith(mkTop(g));
		//Use the hierarchy BDD to further restrict the cardinality edges
		//since they are mostly disjunctive
		mkCardinality(g, result);
		return result;
	}


	@SuppressWarnings("unchecked")
	private BDD mkMutex(Set<FeatureNode<T>> sources) {
		if (sources.size() == 1)
			return _factory.one();

		BDDQueue queue = new BDDQueue(this);

		FeatureNode<T>[] arr = sources.toArray(new FeatureNode[0]);
		for (int i=0; i<arr.length-1; i++) {
			for (int j=i+1; j<arr.length; j++) {
				FeatureNode<T> v1 = arr[i];
				FeatureNode<T> v2 = arr[j];
				queue.add(mkDisjunction(v1).impWith(mkFeatureNodeNot(v2)));
			}
		}

		return queue.getConjoinedBDD();
	}


	/**
	 * <p>
	 * Creates a BDD describing the implications "upwards" in the feature tree.
	 * These upwards implications describe child-parent implications. Mandatory
	 * and AND-Grouped features are considered optional.
	 * </p>
	 *
	 * @see {@link #mkCardinality(FeatureGraph)}
	 */
	public Formula<T> mkHierarchy(FeatureGraph<T> g) {
		logger.fine("Making BDD hierarchy edges");

		//FIXME Special check for bottom since its hierarchy is true, but we don't want that
		if (g.isBottom())
			return zeroF();

		BDDQueue queue = new BDDQueue(this);

		DepthFirstEdgeIterator<T> iter = new DepthFirstEdgeIterator<T>(g);
		while (iter.hasNext()) {
			FeatureEdge e = iter.next();

			if (e.getType() == FeatureEdge.HIERARCHY) {
				BDD sources = mkOrNodes(g.getSources(e));
				BDD target = mkConjunction(g.getTarget(e));
				queue.add(sources.impWith(target));
			}
		}
		return new Formula<T>(queue.getConjoinedBDD(), g.features(), this);
	}

	public BDD mkCardinality(FeatureGraph<T> g) {
		return mkCardinality(g, _factory.one());
	}

	private BDD mkAndGroups(FeatureGraph<T> g) {
		BDD result = _factory.one();
		//AND-Groups
		for (FeatureNode<T> v : g.vertices()) {
			if (v.features().size() > 1) {
				LoopingIterator<T> iter = new LoopingIterator<T>(v.features());
				T start = iter.next();
				T prev = start;
				do {
					T next = iter.next();
					result.andWith(get(prev).impWith(get(next)));
					prev = next;
				} while (prev != start);
			}
		}
		return result;
	}

	private BDD mkCardinality(FeatureGraph<T> g, BDD result) {
		logger.fine("Making BDD cardinality edges");

		for (FeatureEdge e : g.edges()) {

			Set<FeatureNode<T>> sources = g.getSources(e);
			FeatureNode<T> target = g.getTarget(e);
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				result.andWith(mkMutex(sources));
				break;
			case FeatureEdge.XOR:
			case FeatureEdge.MANDATORY:
				result.andWith(mkConjunction(target)
						.impWith(mkOrNodes(sources)));
				//Mutual Exclusions
				result.andWith(mkMutex(sources));
				break;
			case FeatureEdge.OR:
				result.andWith(mkConjunction(target)
						.impWith(mkOrNodes(sources)));
				break;
			case FeatureEdge.DEAD:
				for (FeatureNode<T> v : sources)
					result.andWith(mkFeatureNodeNot(v));
				break;
			default:
				//Skip
				assert e.getType() == FeatureEdge.HIERARCHY
					|| e.getType() == FeatureEdge.FROZEN;
			}
		}


		return result;
	}

	protected Set<T> domain() {
		return Collections.unmodifiableSet(_featToVar.keySet());
	}


}