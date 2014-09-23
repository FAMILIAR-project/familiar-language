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

package gsd.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.alg.TransitiveClosure;
import org.jgrapht.graph.SimpleDirectedGraph;

public class ImplicationGraph<V> extends SimpleDirectedGraph<V, SimpleEdge>
        implements Cloneable, BasicGraph<V, SimpleEdge> {

    public ImplicationGraph() {
        super(SimpleEdge.class);
    }

    @Override
    public int hashCode() {
        int code = vertices().hashCode() * -2 + edgeSet().hashCode() * 3;
        return code;
    }

    @Override
    public Collection<SimpleEdge> edges() {
        return edgeSet();
    }

    @Override
    public Collection<V> vertices() {
        return vertexSet();
    }

    @Override
    public Set<V> children(V v) {
        Set<V> result = new HashSet<V>();
        for (SimpleEdge e : super.incomingEdgesOf(v)) {
            result.add(getEdgeSource(e));
        }
        return result;
    }

    @Override
    public SimpleEdge findEdge(V v1, V v2) {
        return getEdge(v1, v2);
    }

    @Override
    public V getSource(SimpleEdge e) {
        return getEdgeSource(e);
    }

    @Override
    public V getTarget(SimpleEdge e) {
        return getEdgeTarget(e);
    }

    @Override
    public Collection<SimpleEdge> incomingEdges(V v) {
        return incomingEdgesOf(v);
    }

    @Override
    public Collection<SimpleEdge> outgoingEdges(V v) {
        return outgoingEdgesOf(v);
    }

    @Override
    public Set<V> parents(V v) {
        Set<V> result = new HashSet<V>();
        for (SimpleEdge e : super.outgoingEdgesOf(v)) {
            result.add(getEdgeTarget(e));
        }
        return result;
    }

    public Set<V> ancestors(V v) {
        final Set<V> result = new HashSet<V>();
        ancestors_rec(v, result);
        result.remove(v);
        return result;
    }

    public Set<V> descendants (V v) {
        final Set<V> result = new HashSet<V>();
        descendants_rec(v, result);
        result.remove(v);
        return result;
    }

    private void ancestors_rec(V v, Set<V> result) {
        if (result.containsAll(parents(v)))
            return;

        result.addAll(parents(v));

        for (V p : parents(v)) {
            ancestors_rec(p, result);
        }
    }

    private void descendants_rec(V v, Set<V> result) {
        if (result.containsAll(children(v)))
            return;

        result.addAll(children (v));

        for (V p : children(v)) {
            descendants_rec(p, result);
        }
    }

    @Override
    public String toString() {
        return new GraphvizGraph<V, SimpleEdge>(this).toString();
    }

    @Override
    public ImplicationGraph<V> clone() {
        ImplicationGraph<V> result = new ImplicationGraph<V>();
        for (V v : vertices()) {
            result.addVertex(v);
        }
        for (SimpleEdge e : edges()) {
            V source = getSource(e);
            V target = getTarget(e);
            result.addEdge(source, target, e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (o instanceof ImplicationGraph<?>) {
            ImplicationGraph<Object> other = (ImplicationGraph<Object>) o;
            boolean verticesEqual = new HashSet<V>(this.vertices())
                    .equals(new HashSet<Object>(other.vertices()));

            if (!verticesEqual || this.edgeSet().size() != other.edgeSet().size()) {
                return false;
            }

            for (SimpleEdge e1 : this.edges())
                if (other.findEdge(this.getSource(e1), this.getTarget(e1)) == null) {
                    return false;
                }
            return true;
        }
        return false;
    }


    public List<SimpleEdge> topologicalEdges() {
        ArrayList<SimpleEdge> result = new ArrayList<SimpleEdge>();
        for (V root : roots()) {
            topologicalEdges_Internal(root, new LinkedList<V>(), result);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public ImplicationGraph<Set<V>> reduceCliques() {
        long time = System.currentTimeMillis();
        ImplicationGraph<Set<V>> result = new ImplicationGraph<Set<V>>();
        ImplicationGraph<V> closed = clone();
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(closed);

        List<Set<V>> cliques = DirectedCliqueFinder.INSTANCE.findAll(closed);
        Set<V> cliqueVs = new HashSet<V>();

        Map<V, Set<V>> vertexSetMap = new HashMap<V, Set<V>>();

        for (Set<V> clique : cliques) {
            result.addVertex(clique);
            cliqueVs.addAll(clique);
            for (V u : clique)
                vertexSetMap.put(u, clique);
        }

        for (V v : closed.vertices())
            if (!cliqueVs.contains(v)) {
                Set<V> set = new HashSet<V>(Arrays.asList(v));
                result.addVertex(set);
                vertexSetMap.put(v, set);
            }

        List<SimpleEdge> remainingEdges = new ArrayList<SimpleEdge>(edges());
        for (Set<V> clique : cliques) {
            for (V u : clique) {
                for (SimpleEdge e : closed.incomingEdges(u)) {
                    V source = closed.getSource(e);
                    if (!clique.contains(source))
                        result.addEdge(vertexSetMap.get(source), clique);
                    remainingEdges.remove(e);
                }

                for (SimpleEdge e : closed.outgoingEdges(u)) {
                    V target = closed.getTarget(e);
                    if (!clique.contains(target))
                        result.addEdge(clique, vertexSetMap.get(target));
                    remainingEdges.remove(e);
                }
            }
        }

        //Add the remaining hierarchy edges
        for (SimpleEdge e : remainingEdges)
            result.addEdge(vertexSetMap.get(closed.getSource(e)),
                    vertexSetMap.get(closed.getTarget(e)));

        return result;
    }

    private void topologicalEdges_Internal(V v, Queue<V> q,
                                           List<SimpleEdge> result) {
        q.addAll(getPredecessors(v));
        result.addAll(outgoingEdges(v));

        if (!q.isEmpty())
            topologicalEdges_Internal(q.poll(), q, result);
    }

    public Set<V> roots() {
        Set<V> result = new HashSet<V>();
        for (V v : vertices()) {
            if (getSuccessors(v).size() == 0)
                result.add(v);
        }
        return result;
    }

    public void printEdges() {
        for (SimpleEdge e : edges()) {
            System.out.println(edgeString(e));
        }
    }

    public String edgeString(SimpleEdge e) {
        return getSource(e) + "->" + getTarget(e);
    }

    /**
     * Confusing since parents / successors are reversed from standard
     * definitions. This definition is based on conversion to Feature Models.
     */
    public Set<V> getSuccessors(V v) {
        return parents(v);
    }

    /**
     * Confusing since parents / successors are reversed from standard
     * definitions. This definition is based on conversion to Feature Models.
     */
    public Set<V> getPredecessors(V v) {
        return children(v);
    }


}
