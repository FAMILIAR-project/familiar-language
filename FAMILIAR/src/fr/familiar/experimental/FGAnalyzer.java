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

package fr.familiar.experimental;

import fr.familiar.interpreter.FMLShell;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.MapIterator;
import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.iterators.EntrySetMapIterator;
import org.apache.commons.collections15.multimap.MultiHashMap;

/**
 * @author mathieuacher Feature Graph Analyzer
 */

public class FGAnalyzer<T extends Comparable<T>> {

	private FeatureGraph<T> fg;

	public FGAnalyzer(FeatureGraph<T> fg) {
		this.fg = fg;
	}

	public MultiMap<FeatureNode<T>, FeatureEdge> findAmbiguousGrouped() {
		MultiMap<FeatureNode<T>, FeatureEdge> result = new MultiHashMap<FeatureNode<T>, FeatureEdge>();

		for (FeatureEdge e : fg.selectGroupEdges()) {
			Set<FeatureNode<T>> sources = fg.getSources(e);
			for (FeatureNode<T> s : sources) {
				result.put(s, e);
			}

		}

		Map<FeatureNode<T>, Collection<FeatureEdge>> mapr = result.map();

		MapIterator<FeatureNode<T>, Collection<FeatureEdge>> iter = new EntrySetMapIterator<FeatureNode<T>, Collection<FeatureEdge>>(
				mapr);

		while (iter.hasNext()) {
			iter.next();
			if (iter.getValue().size() != 1)
				continue;
			iter.remove();
		}

		return result;
	}

	public MultiMap<FeatureNode<T>, FeatureEdge> findAmbiguousGroupsByParent() {
		MultiMap<FeatureNode<T>, FeatureEdge> result = new MultiHashMap<FeatureNode<T>, FeatureEdge>();

		for (FeatureEdge e : fg.selectGroupEdges()) {
			result.put(fg.getTarget(e), e);
		}

		Map<FeatureNode<T>, Collection<FeatureEdge>> mapr = result.map();
		MapIterator<FeatureNode<T>, Collection<FeatureEdge>> iter = new EntrySetMapIterator<FeatureNode<T>, Collection<FeatureEdge>>(
				mapr);
		while (iter.hasNext()) {
			iter.next();
			if (iter.getValue().size() == 1)
				iter.remove();
			else if (!(areEdgesOverlapping(iter.getValue()))) {
				iter.remove();
			}
		}
		return result;
	}

	public MultiMap<FeatureNode<T>, FeatureEdge> findAmbiguousHierarchy() {
		MultiMap<FeatureNode<T>, FeatureEdge> result = new MultiHashMap<FeatureNode<T>, FeatureEdge>();

		for (FeatureEdge e : fg.selectEdges(FeatureEdge.HIERARCHY)) {
			FeatureNode<T> source = fg.getSource(e);
			result.put(source, e);
		}

		Map<FeatureNode<T>, Collection<FeatureEdge>> mapr = result.map();
		MapIterator<FeatureNode<T>, Collection<FeatureEdge>> iter = new EntrySetMapIterator<FeatureNode<T>, Collection<FeatureEdge>>(
				mapr);

		while (iter.hasNext()) {
			iter.next();
			if (iter.getValue().size() != 1)
				continue;
			iter.remove();
		}

		return result;
	}

	public Set<FeatureEdge> findHierarchyViolatingGroups() {
		Set<FeatureEdge> result = new HashSet<FeatureEdge>();
		for (FeatureEdge e : fg.selectGroupEdges()) {
			FeatureNode<T> target = fg.getTarget(e);
			for (FeatureNode<T> v : fg.getSources(e)) {
				if (fg.findEdge(v, target, FeatureEdge.HIERARCHY) == null) {
					result.add(e);
					break;
				}
			}
		}
		return result;
	}

	public boolean isGeneralized() {
		MultiMap<FeatureNode<T>, FeatureEdge> grouped = findAmbiguousGroupsByParent();
		MultiMap<FeatureNode<T>, FeatureEdge> hierarchy = findAmbiguousHierarchy();
		return ((grouped.size() > 0) || (hierarchy.size() > 0));
	}

	public List<FeatureNode<T>> sortByDepth(Collection<FeatureNode<T>> vs) {
		assert (fg.vertices().containsAll(vs));

		List<FeatureNode<T>> copy = new ArrayList<FeatureNode<T>>(vs);
		List<FeatureNode<T>> result = new LinkedList<FeatureNode<T>>();

		return result;
	}

	public boolean areEdgesOverlapping(Collection<FeatureEdge> retain) {
		HashSet<FeatureNode<T>> seen = new HashSet<FeatureNode<T>>();
		for (FeatureEdge e : retain) {
			Set<FeatureNode<T>> sources = fg.getSources(e);
			if (CollectionUtils.containsAny(seen, sources))
				return true;
			seen.addAll(sources);
		}
		return false;
	}

	public void printDebug() {

		FMLShell.getInstance().printDebugMessage(
				"DEBUG " + "Is generalized? " + isGeneralized());

		MultiMap<FeatureNode<T>, FeatureEdge> ambigousHierarchy = findAmbiguousHierarchy();
		Set<Entry<FeatureNode<T>, Collection<FeatureEdge>>> ambigousEdges = ambigousHierarchy
				.entrySet();
		Iterator<Entry<FeatureNode<T>, Collection<FeatureEdge>>> localIterator1 = ambigousEdges
				.iterator();
		Entry<FeatureNode<T>, Collection<FeatureEdge>> en;
		Iterator<FeatureEdge> localIterator2;
		FeatureEdge e;
		while (localIterator1.hasNext()) {
			en = localIterator1.next();
			System.out.println("DEBUG " + "Ambiguous hierarchy edges for: "
					+ en.getKey());
			for (localIterator2 = en.getValue().iterator(); localIterator2
					.hasNext();) {
				e = localIterator2.next();
				System.out.println("DEBUG " + fg.edgeString(e));
			}

		}

		localIterator1 = findAmbiguousGroupsByParent().entrySet().iterator();

		while (localIterator1.hasNext()) {
			en = localIterator1.next();
			System.out.println("DEBUG " + "Ambiguous group edges for: "
					+ en.getKey());
			for (localIterator2 = en.getValue().iterator(); localIterator2
					.hasNext();) {
				e = localIterator2.next();
				System.out.println("DEBUG " + fg.edgeString(e));
			}
		}

		localIterator1 = findAmbiguousGrouped().entrySet().iterator();

		while (localIterator1.hasNext()) {
			en = localIterator1.next();
			System.out.println("DEBUG " + "Ambiguous grouped for: "
					+ en.getKey());
			for (localIterator2 = en.getValue().iterator(); localIterator2
					.hasNext();) {
				e = localIterator2.next();
				System.out.println("DEBUG " + fg.edgeString(e));
			}
		}

		Set<FeatureEdge> edges = findHierarchyViolatingGroups();

		for (FeatureEdge fe : edges) {
			System.out.println("DEBUG " + "Violating groups: " + fe);
		}

	}

}