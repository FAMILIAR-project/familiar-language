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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;
import org.jgrapht.graph.DefaultEdge;

import fr.familiar.experimental.BasicDirectedGraph;
import fr.familiar.experimental.BreadthFirstIterator;
import fr.familiar.experimental.BreadthFirstVertexIterator;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FeatureOrderSelectionByDepthAndSiblings extends
		FeatureOrderSelection {

	/**
	 * @param fmv
	 * @param fts
	 */
	public FeatureOrderSelectionByDepthAndSiblings(FeatureModelVariable fmv,
			Collection<String> fts) {
		super(fmv, fts);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.test.FeatureOrderSelection#compute()
	 */
	@Override
	public List<String> compute() {
		return sortFtsByDepthAndSiblings();
	}
	
	private List<String> sortFtsByDepthAndSiblings() {

		List<String> ftsOrdered = sortByDepthAndSiblings(_fmv.getHierarchy().getDiagram());
		if (ftsOrdered.isEmpty())
			return new ArrayList<String>(ftsOrdered);
		Set<String> ftsToRemove = new HashSet<String>();
		for (String ft : ftsOrdered) {
			if (!_fts.contains(ft))
				ftsToRemove.add(ft);
		}

		ftsOrdered.removeAll(ftsToRemove);

		return ftsOrdered;
	}

	private List<String> sortByDepthAndSiblings(FeatureGraph<String> g) {
		List<FeatureNode<String>> fns = _sortByDepthAndSiblings(g);
		List<String> fts = new ArrayList<String>();
		for (FeatureNode<String> fn : fns) {
			fts.add(fn.getFeature());
		}
		return fts;
	}

	private List<FeatureNode<String>> _sortByDepthAndSiblings(
			FeatureGraph<String> g) {

		List<FeatureNode<String>> ordered = new ArrayList<FeatureNode<String>>();
		List<FeatureNode<String>> siblings = new ArrayList<FeatureNode<String>>();

		BreadthFirstVertexIterator<String> iter = new BreadthFirstVertexIterator<String>(
				g);
		int depth = 0;
		while (iter.hasNext()) {
			if (iter.getDepth() > depth) {
				ordered.addAll(sortBySiblingOrder(g, siblings));
				siblings.clear();
			}

			FeatureNode<String> next = iter.next();
			siblings.add(next);
		}
		ordered.addAll(sortBySiblingOrder(g, siblings));
		return ordered;

	}

	private List<FeatureNode<String>> sortBySiblingOrder(
			FeatureGraph<String> g, List<FeatureNode<String>> siblings) {
		MultiMap<FeatureNode<String>, FeatureNode<String>> pToChildren = new MultiHashMap<FeatureNode<String>, FeatureNode<String>>();

		for (FeatureNode<String> v : siblings)
			for (Iterator<FeatureNode<String>> localIterator2 = g.parents(v)
					.iterator(); localIterator2.hasNext();) {
				FeatureNode<String> p = localIterator2.next();
				pToChildren.put(p, v);
			}
		BasicDirectedGraph<FeatureNode<String>, DefaultEdge> depG = new BasicDirectedGraph<FeatureNode<String>, DefaultEdge>(
				DefaultEdge.class);

		for (FeatureNode<String> v : siblings) {
			depG.addVertex(v);
			for (FeatureNode<String> p : g.parents(v)) {
				Collection<FeatureNode<String>> coll = pToChildren.get(p);
				if (coll == null) {
					continue;
				}
				for (FeatureNode<String> dep : coll) {
					if (dep == v)
						continue;
					depG.addEdge(v, dep);
				}
			}
		}

		BreadthFirstIterator<FeatureNode<String>, DefaultEdge> depIter = new BreadthFirstIterator<FeatureNode<String>, DefaultEdge>(
				depG);

		List<FeatureNode<String>> ordered = new ArrayList<FeatureNode<String>>();
		while (depIter.hasNext()) {
			ordered.add(depIter.next());
		}
		return ordered;
	}


}
