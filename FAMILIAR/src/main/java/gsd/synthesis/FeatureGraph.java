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

import gsd.graph.BasicGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.multimap.MultiHashMap;

/**
 * Use @{link FeatureGraphFactory} to create a FeatureGraph.
 *
 * @author shshe
 *
 * @param <T>
 */
public class FeatureGraph<T> implements Cloneable,
	BasicGraph<FeatureNode<T>, FeatureEdge> {

Map<FeatureNode<T>, Collection<FeatureEdge>> _vertices = new HashMap<FeatureNode<T>, Collection<FeatureEdge>> ();

MultiMap<FeatureEdge, FeatureNode<T>> _sources = new MultiHashMap<FeatureEdge, FeatureNode<T>> ();

Map<FeatureEdge, FeatureNode<T>> _target = new HashMap<FeatureEdge, FeatureNode<T>> ();

Map<T, FeatureNode<T>> _features = new HashMap<T, FeatureNode<T>> ();

public static final boolean CHECKS_ENABLED = false;

private FeatureNode<T> _top = null;
private FeatureNode<T> _bottom = null;

private final T _topFeat;
private final T _bottomFeat;


public static FeatureGraph<String>
		mkStringFeatureGraph() {
	return FeatureGraphFactory.mkStringFactory().mkTop();
}

public FeatureGraphFactory<T>
		getFeatureGraphFactory() {
	return new FeatureGraphFactory<T>(_topFeat, _bottomFeat);
}

protected FeatureGraph(T topFeat, T bottomFeat) {

	_topFeat = topFeat;
	_bottomFeat = bottomFeat;
	_top = new FeatureNode<T> (_topFeat, FeatureNode.TOP);
	_bottom = new FeatureNode<T> (_bottomFeat, FeatureNode.BOTTOM);
	addVertex (_top);
	addVertex (_bottom);
}



public FeatureNode<T> getTopVertex() {

	return _top;
}



public FeatureNode<T> getBottomVertex() {

	return _bottom;
}



public boolean addVertex(FeatureNode<T> v) {

	if (v == null)
		throw new IllegalArgumentException ("vertex cannot be null!");

	if (_vertices.containsKey (v))
		return false;

	for (T f : v.features ())
		if (_features.containsKey (f))
			throw new IllegalArgumentException (
				"feature "
					+ f
					+ " already exists in graph as a different node!");

	_vertices.put (v, new ArrayList<FeatureEdge> ());

	for (T feature : v.features ())
		_features.put (feature, v);

	return true;
}



/**
 * TODO maybe this belongs somewhere else? ie. FeatureDiagram?
 */
public FeatureNode<T> addFreeVariable(T f) {

	FeatureNode<T> v = new FeatureNode<T> (f, FeatureNode.FREE);
	this.addVertex (v);
	this.addEdge (v, _top, FeatureEdge.HIERARCHY);
	return v;
}



public void removeAllVertices(Set<FeatureNode<T>> vertices) {

	for (FeatureNode<T> v : vertices) {
		removeVertex (v);
	}
}



public boolean removeVertex(FeatureNode<T> v) {

	if (v.equals (_top))
		throw new IllegalArgumentException ("cannot remove top node!");
	else if (v.equals (_bottom))
		throw new IllegalArgumentException (
			"cannot remove bottom node!");

	if (!_vertices.containsKey (v))
		return false;

	if (!leaves ().contains (v))
		throw new IllegalArgumentException (v + " must be a leaf!");

	for (FeatureEdge e : outgoingEdges (v)) {
		removeEdge (e);
	}

	for (T f : v.features ())
		_features.remove (f);

	checkEdgeConsistency ();
	_vertices.remove (v);
	return true;
}



public FeatureNode<T> replaceVertex(FeatureNode<T> v, FeatureNode<T> w) {

	checkVertexExists (v);

	Collection<EdgeContainer<T>> edges = new ArrayList<EdgeContainer<T>> ();

	for (FeatureEdge e : incomingEdges (v)) {
		edges.add (new EdgeContainer<T> (getSources (e), w,
			e.getType ()));
		removeOnlyEdge (e);
	}

	for (FeatureEdge e : outgoingEdges (v)) {
		Set<FeatureNode<T>> sources = getSources (e);
		sources.remove (v);
		sources.add (w);
		edges.add (new EdgeContainer<T> (sources, getTarget (e),
			e.getType ()));
		removeOnlyEdge (e);
	}

	removeVertex (v);
	addVertex (w);
	addAllEdges (edges);

	return w;
}



protected void addAllEdges(Collection<EdgeContainer<T>> edges) {

	for (EdgeContainer<T> e : edges)
		addEdge (e.getSources (), e.getTarget (), e.getType ());
}



@SuppressWarnings("unchecked")
public boolean addEdge(FeatureNode<T> source, FeatureNode<T> target, int type) {

	return addEdge (Arrays.asList (source), target, type);
}



@Override
public FeatureEdge addEdge(FeatureNode<T> v1, FeatureNode<T> v2) {

	throw new UnsupportedOperationException ();
}



public boolean addEdge(Collection<FeatureNode<T>> sources,
	FeatureNode<T> target, int type) {

	if (sources.size () == 0)
		throw new IllegalArgumentException (
			"Sources cannot be empty collection!");
	if (sources.contains (target))
		throw new IllegalArgumentException (
			"Source cannot contain the target!");

	checkEdgeConsistency ();
	checkVertexExists (target);

	FeatureEdge e = new FeatureEdge (type);

	if (findEdge (sources, target, type) != null)
		return false;

	_target.put (e, target);

	for (FeatureNode<T> m : sources) {
		checkVertexExists (m);

		_vertices.get (m).add (e);
		_sources.put (e, m);
	}

	_vertices.get (target).add (e);

	checkEdgeConsistency ();
	checkVertexConsistency ();
	return true;
}



public void removeAllEdges(Collection<FeatureEdge> edges) {

	List<FeatureEdge> sorted = new ArrayList<FeatureEdge> (edges);
	// Reverse order such that hierarchy edges are last
	Collections.sort (sorted, new Comparator<FeatureEdge> () {
		public int compare(FeatureEdge e1, FeatureEdge e2) {

			return e1.getType () < e2.getType () ? 1
				: e1.getType () == e2.getType () ? 0 : -1;
		}
	});
	for (FeatureEdge e : sorted) {
		removeEdge (e);
	}
}



public boolean removeEdge(FeatureEdge e) {

	checkEdgeExists (e);

	// Check groups that are invalidated by the removal of a hierarchy edge
	if (e.getType () == FeatureEdge.HIERARCHY) {
		for (FeatureEdge over : overlappingEdges (e))
			removeEdge (over);
	}

	removeOnlyEdge (e);

	checkEdgeConsistency ();
	checkVertexConsistency ();
	return true;
}



/**
 * Removes the edge without checking for overlapping cardinality edges.
 */
protected void removeOnlyEdge(FeatureEdge e) {

	for (FeatureNode<T> v : getSources (e)) {
		_vertices.get (v).remove (e);
	}

	_vertices.get (getTarget (e)).remove (e);
	_sources.remove (e);
	_target.remove (e);
}



public Set<FeatureNode<T>> ancestors(FeatureNode<T> v) {

	final Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	ancestors_rec (v, result);
	return result;
}



private void ancestors_rec(FeatureNode<T> v, Set<FeatureNode<T>> result) {

	if (result.containsAll (parents (v)))
		return;

	result.addAll (parents (v));
	for (FeatureNode<T> c : parents (v)) {
		ancestors_rec (c, result);
	}
}



public Set<FeatureNode<T>> descendants(FeatureNode<T> v) {

	final Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	descendants_rec (v, result);
	return result;
}



private void descendants_rec(FeatureNode<T> v, Set<FeatureNode<T>> result) {

	result.addAll (children (v));
	for (FeatureNode<T> c : children (v)) {
		descendants_rec (c, result);
	}
}



public Set<FeatureNode<T>> parents(FeatureNode<T> v) {

	Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	for (FeatureEdge e : outgoingEdges (v, FeatureEdge.HIERARCHY)) {
		result.add (getTarget (e));
	}
	return result;
}



/**
 * Finds a hierarchy edge from source to target. Needed to implement the
 * {@link BasicGraph} interface.
 *
 * @deprecated Use {@link #findEdge(FeatureNode, FeatureNode, int)} instead.
 */
public FeatureEdge findEdge(FeatureNode<T> source, FeatureNode<T> target) {

	return findEdge (source, target, FeatureEdge.HIERARCHY);
}



public boolean containsEdge(FeatureNode<T> source, FeatureNode<T> target,
	int type) {

	return findEdge (source, target, type) != null;
}



@SuppressWarnings("unchecked")
public FeatureEdge findEdge(FeatureNode<T> source, FeatureNode<T> target,
	int type) {

	return findEdge (Arrays.asList (source), target, type);
}



/**
 * Finds an edge equivalent to FeatureEdge e in FeatureGraph g, in this graph.
 */
public FeatureEdge findEdge(FeatureEdge e, FeatureGraph<T> g) {

	return findEdge (g.getSources (e), g.getTarget (e), e.getType ());
}



public FeatureEdge findEdge(FeatureEdge e, int type) {

	return findEdge (getSources (e), getTarget (e), type);
}



public FeatureEdge findEdge(final Collection<FeatureNode<T>> sources,
	final FeatureNode<T> target, final int type) {

	checkVertexConsistency ();

	final HashSet<FeatureNode<T>> sourcesSet = new HashSet<FeatureNode<T>> (
		sources);

	return CollectionUtils.find (_vertices.get (target),
		new Predicate<FeatureEdge> () {
			public boolean evaluate(FeatureEdge e) {

				return getSources (e).equals (sourcesSet)
					&& type == e.getType ();
			}
		});
}



public Collection<FeatureEdge> findEdges(
	final Collection<FeatureNode<T>> sources, final FeatureNode<T> target) {

	return CollectionUtils.select (_vertices.get (target),
		new Predicate<FeatureEdge> () {

			public boolean evaluate(FeatureEdge e) {

				return getSources (e).equals (sources);
			}

		});
}



public Collection<FeatureEdge> findEdges(
	final Collection<FeatureNode<T>> sources, final FeatureNode<T> target,
	final int type) {

	return new HashSet<FeatureEdge> (CollectionUtils.select (
		_vertices.get (target), new Predicate<FeatureEdge> () {

			public boolean evaluate(FeatureEdge e) {

				return (e.getType () & type) == e.getType ();
			}

		}));
}



public Collection<FeatureEdge> incomingEdges(FeatureNode<T> v) {

	checkVertexExists (v);

	Collection<FeatureEdge> result = new ArrayList<FeatureEdge> ();
	for (FeatureEdge e : _vertices.get (v)) {
		if (_target.get (e).equals (v))
			result.add (e);
	}
	return result;
}



public Collection<FeatureEdge> incomingEdges(FeatureNode<T> v, final int type) {

	return CollectionUtils.select (incomingEdges (v),
		new Predicate<FeatureEdge> () {
			public boolean evaluate(FeatureEdge e) {

				return (e.getType () & type) == e.getType ();
			}
		});
}



public Set<FeatureNode<T>> children(FeatureNode<T> v) {

	Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	for (FeatureEdge e : incomingEdges (v)) {
		result.addAll (getSources (e));
	}
	return result;
}



/**
 * @return a list (ordered collection of sibling sets in the breadth first search traversal
 *         order
 */
public List<Set<FeatureNode<T>>> getSiblingSetsInBFS() {

	LinkedList<Set<FeatureNode<T>>> result = new LinkedList<Set<FeatureNode<T>>> ();
	LinkedList<FeatureNode<T>> workList = new LinkedList<FeatureNode<T>> ();
	workList.addLast (this._top);
	Set<FeatureNode<T>> singleton = new HashSet<FeatureNode<T>> ();
	singleton.add (this._top);
	result.addLast (singleton);

	while (!workList.isEmpty ()) {

		FeatureNode<T> root = workList.removeFirst ();
		if (this.children(root).isEmpty ())
			continue;
		result.addLast (this.children (root));
		workList.addAll (this.children (root));
	}
	return result;
}



public Collection<FeatureEdge> outgoingEdges(FeatureNode<T> v) {

	checkVertexExists (v);

	Collection<FeatureEdge> result = new ArrayList<FeatureEdge> ();
	for (FeatureEdge e : _vertices.get (v)) {
		if (_sources.get (e).contains (v))
			result.add (e);
	}
	return result;
}



public Collection<FeatureEdge> outgoingEdges(FeatureNode<T> v, final int type) {

	return CollectionUtils.select (outgoingEdges (v),
		new Predicate<FeatureEdge> () {
			public boolean evaluate(FeatureEdge e) {

				return (e.getType () & type) == e.getType ();
			}
		});
}



protected Set<FeatureEdge> overlappingEdges(FeatureEdge e) {

	assert e.getType () == FeatureEdge.HIERARCHY;
	checkEdgeExists (e);

	Set<FeatureEdge> result = new HashSet<FeatureEdge> ();

	FeatureNode<T> source = getSource (e);
	FeatureNode<T> target = getTarget (e);

	for (FeatureEdge out : outgoingEdges (source)) {
		if (out != e && getTarget (out) == target)
			result.add (out);
	}

	return result;
}



public Collection<FeatureEdge> incidentEdges(FeatureNode<T> v) {

	return new ArrayList<FeatureEdge> (_vertices.get (v));
}



public FeatureNode<T> getSource(FeatureEdge e) {

	if (e.getType () != FeatureEdge.HIERARCHY
		&& e.getType () != FeatureEdge.FROZEN
		&& e.getType () != FeatureEdge.MANDATORY
		&& e.getType () != FeatureEdge.DEAD
		&& e.getType () != FeatureEdge.MARKED) {
		throw new IllegalArgumentException (
			"Edge must be a hierarchy edge, use getSources(e) instead!");
	}
	checkEdgeExists (e);
	assert _sources.get (e).size () == 1;
	return _sources.get (e).iterator ().next ();
}



public Set<FeatureNode<T>> getSources(FeatureEdge e) {

	checkEdgeExists (e);
	return new HashSet<FeatureNode<T>> (_sources.get (e));
}



public FeatureNode<T> getTarget(FeatureEdge e) {

	checkEdgeExists (e);
	return _target.get (e);
}



public Set<FeatureNode<T>> getSourceVertices(Collection<FeatureEdge> es) {

	Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	for (FeatureEdge e : es) {
		result.addAll (getSources (e));
	}
	return result;
}



public Set<FeatureNode<T>> getTargetVertices(Collection<FeatureEdge> es) {

	Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>> ();
	for (FeatureEdge e : es) {
		result.add (getTarget (e));
	}
	return result;
}



/**
 * @return true if the graph only contains the synthetic root, false otherwise
 */
public boolean isTop() {

	return incomingEdges (_top).size () == 0;
}



@SuppressWarnings("unchecked")
public boolean isBottom() {

	return findEdge (Arrays.asList (_bottom), _top, FeatureEdge.MANDATORY) != null;
}



public FeatureNode<T> findVertex(T feature) {

	if (feature.equals (_topFeat))
		return getTopVertex ();
	else if (feature.equals (_bottomFeat))
		return getBottomVertex ();

	if (!_features.containsKey (feature)) {
		throw new IllegalArgumentException (feature
			+ " does not exist in graph!");
	}
	return _features.get (feature);
}



/**
 * Returns the leaves in the graph, excluding the bottom node.
 *
 * @return
 */
public Set<FeatureNode<T>> leaves() {

	Set<FeatureNode<T>> cand = new HashSet<FeatureNode<T>> (vertices ());
	for (FeatureEdge e : edges ()) {
		cand.remove (getTarget (e));
	}
	cand.remove (getBottomVertex ());
	return cand;
}



public Set<FeatureEdge> edges() {

	return Collections.unmodifiableSet (_target.keySet ());
}



public Set<FeatureNode<T>> vertices() {

	return Collections.unmodifiableSet (_vertices.keySet ());
}



public Set<FeatureEdge> selectGroupEdges() {

	return new HashSet<FeatureEdge> (CollectionUtils.select (edges (),
		new Predicate<FeatureEdge> () {

			public boolean evaluate(FeatureEdge e) {

				return (e.getType () & FeatureEdge.GROUPS) > 0;
			}

		}));
}



public Set<FeatureEdge> selectCardinalityEdges() {

	return new HashSet<FeatureEdge> (CollectionUtils.select (edges (),
		new Predicate<FeatureEdge> () {

			public boolean evaluate(FeatureEdge e) {

				return e.getType () != FeatureEdge.HIERARCHY;
			}

		}));
}



/**
 * @param type
 *                is a bitmask. You may specify several edge types by using the
 *                OR | operation. For example: selectEdges(
 *                FeatureEdge.HIERARCHY | FeatureEdge.MANDATORY );
 * @return
 */
public Set<FeatureEdge> selectEdges(final int type) {

	return new HashSet<FeatureEdge> (CollectionUtils.select (edges (),
		new Predicate<FeatureEdge> () {

			public boolean evaluate(FeatureEdge e) {

				return (e.getType () & type) == e.getType ();
			}

		}));
}



public Set<FeatureNode<T>> selectAndGroups() {

	return new HashSet<FeatureNode<T>> (CollectionUtils.select (
		vertices (), new Predicate<FeatureNode<T>> () {
			public boolean evaluate(FeatureNode<T> v) {

				return v.features ().size () > 1;
			}
		}));
}



public Set<T> features() {

	Set<T> result = new HashSet<T> ();
	for (FeatureNode<T> v : vertices ()) {
		if (!v.isTop () && !v.isBottom ())
			result.addAll (v.features ());
	}
	return result;
}



@Override
public FeatureGraph<T> clone() {

	FeatureGraph<T> result = new FeatureGraph<T> (_topFeat, _bottomFeat);

	for (FeatureNode<T> v : vertices ()) {
		result.addVertex (v);
	}

	for (FeatureEdge e : edges ()) {
		result.addEdge (getSources (e), getTarget (e), e.getType ());
	}
	return result;
}



@Override
public int hashCode() {

	return _sources.hashCode () * 13 + _target.hashCode () * 67
		- _vertices.hashCode () * 3;
}



@SuppressWarnings("unchecked")
@Override
public boolean equals(Object o) {

	if (o instanceof FeatureGraph) {
		final FeatureGraph<T> other = (FeatureGraph<T>) o;

		if (edges ().size () != other.edges ().size ())
			return false;

		int matches = CollectionUtils.countMatches (edges (),
			new Predicate<FeatureEdge> () {

				public boolean evaluate(FeatureEdge e) {

					return other.findEdge (getSources (e),
						getTarget (e), e.getType ()) != null;
				}

			});

		return matches == edges ().size ()
			&& vertices ().equals (other.vertices ());
	}
	return false;
}



@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (FeatureNode<T> v : vertices()) {
        sb.append(v + "; ");
    }

    sb.append("\n");

    for(FeatureEdge e : edges()) {
        sb.append(edgeString(e) + "; ");
    }
    
    return sb.toString();
}


public String edgeString(FeatureEdge e) {

	return getSources (e) + "->" + getTarget (e) + ":" + e.getType ();
}



public String edgeString(Collection<FeatureEdge> es) {

	StringBuilder sb = new StringBuilder ();
	for (FeatureEdge e : es) {
		sb.append (edgeString (e)).append (", ");
	}
	if (sb.length () == 0) {
		sb.append ("<EMPTY>");
	} else {
		sb.deleteCharAt (sb.length () - 1);
		sb.deleteCharAt (sb.length () - 1);
	}
	return sb.toString ();
}



private void checkVertexConsistency() {

	if (!CHECKS_ENABLED)
		return;

	for (Entry<FeatureNode<T>, Collection<FeatureEdge>> en : _vertices.entrySet ()) {
		FeatureNode<T> v = en.getKey ();
		for (FeatureEdge e : en.getValue ()) {
			if (!getSources (e).contains (v)
				&& !getTarget (e).equals (v))
				throw new AssertionError ("Error");
		}
	}
}



private void checkVertexExists(FeatureNode<T> v) {

	if (v == null)
		throw new IllegalArgumentException ("vertex cannot be null!");

	if (!_vertices.containsKey (v) && v != getTopVertex ())
		throw new IllegalArgumentException ("vertex " + v
			+ " doesn't exist in graph!");
}



private void checkEdgeConsistency() {

	if (!CHECKS_ENABLED)
		return;

	// TODO remove this
	Set<FeatureEdge> verticeEdges = new HashSet<FeatureEdge> ();
	for (Collection<FeatureEdge> edges : _vertices.values ()) {
		verticeEdges.addAll (edges);
	}

	if (!_sources.keySet ().equals (_target.keySet ())
		|| !_sources.keySet ().equals (verticeEdges))
		throw new AssertionError (
			"FeatureEdges in sources and target not in sync!");
}



private void checkEdgeExists(FeatureEdge e) {

	if (e == null)
		throw new IllegalArgumentException ("edge cannot be null!");

	if (!_sources.containsKey (e))
		throw new IllegalArgumentException (e + " doesn't exist!");
}

}