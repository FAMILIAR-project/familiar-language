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

import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.graph.TransitiveReduction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

import org.apache.commons.collections15.CollectionUtils;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Builds a generalised feature diagram from a {@link Formula}. Free variables
 * in the extra constraints are added to the diagram as optional children of
 * TOP.
 *
 * @author Steven She (shshe@uwaterloo.ca)
 */
public class FGBuilder<T extends Comparable<T>> {

	private Logger logger = Logger.getLogger("fmm.FGBuilder");
	private int maxsib = 0;
	private int maxnodesize = 0;

	private BDD _formula;
	private BDDFactory _factory;
	private final BDDBuilder<T> _builder;
	private final FeatureGraphFactory<T> _fgf;
	private Set<T> _support;

	/**
	 * Assigned in mkMutexGroups (should refactor this out)
	 */
	private Collection<Set<FeatureNode<T>>> _mutexCliques;


	public FGBuilder(BDDBuilder<T> builder) {
		_builder = builder;
		_fgf = builder.getFeatureGraphFactory();
	}

	public FeatureModel<T> build(Formula<T> f) {
		_formula = f.getBDD();
		_support = f.getDomain();
		_factory = _formula.getFactory();

		FeatureGraph<T> g = _fgf.mkTop();
		FeatureModel<T> fm = new FeatureModel<T>(g);

		logger.info("Implication graph");
		ImplicationGraph<T> impl = IGBuilder.build(f, _builder);

		logger.info("And-groups");
		mkHierarchyAndGroups(g, impl);


		logger.info("Transitive reduction");
		TransitiveReduction.INSTANCE.reduce(g);

		mkSyntheticRoot(g);

		logger.info("Mutex graph / groups");
		mkMutexGroups(g);

		logger.info("Prime implicants / Or groups");
		mkOrGroups(g);

		return fm;
	}

	public void mkHierarchyAndGroups(FeatureGraph<T> g, ImplicationGraph<T> impl) {

		List<Set<T>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);

		//Make the hierarchy
		for (Set<T> clique : cliques)
			g.addVertex(new FeatureNode<T>(clique));

		for (T v : impl.vertices())
			if (!g.features().contains(v))
				g.addVertex(new FeatureNode<T>(v));

		for (Set<T> clique : cliques) {

			FeatureNode<T> group = g.findVertex(clique.iterator().next());

			for (T u : clique) {
				for (SimpleEdge e : impl.incomingEdges(u).toArray(new SimpleEdge[0])) {
					T source = impl.getSource(e);
					if (!clique.contains(source))
						g.addEdge(g.findVertex(source), group, FeatureEdge.HIERARCHY);
					impl.removeEdge(e);
				}

				for (SimpleEdge e : impl.outgoingEdges(u).toArray(new SimpleEdge[0])) {
					T target = impl.getTarget(e);
					if (!clique.contains(target))
						g.addEdge(group, g.findVertex(target), FeatureEdge.HIERARCHY);
					impl.removeEdge(e);
				}
			}
		}

		//Add the remaining hierarchy edges
		for (SimpleEdge e : impl.edges())
			g.addEdge(g.findVertex(impl.getSource(e)),
					g.findVertex(impl.getTarget(e)),
					FeatureEdge.HIERARCHY);

	}

	@SuppressWarnings("unchecked")
	public void mkMutexGroups(FeatureGraph<T> g) {
		//Make Mutex Graph
		UndirectedGraph<FeatureNode<T>, DefaultEdge> mutex
			= new SimpleGraph<FeatureNode<T>, DefaultEdge>(DefaultEdge.class);

		for (FeatureNode<T> v : g.vertices())
			mutex.addVertex(v);

		List<Integer> support = support();

		for (FeatureNode<T> v : g.vertices()) {
			FeatureNode<T>[] siblings = g.children(v).toArray(new FeatureNode[0]);

			for (int i=0; i < siblings.length; i++) {
				FeatureNode<T> s1 = siblings[i];
				BDD mx = _formula.id ().andWith (_builder.mkConjunction(s1));

				if (mx.isZero ())
					throw new UnsupportedOperationException("Dead features should have been removed!");

				ValidDomains vd = new ValidDomains(mx,
						Collections.min(support), Collections.max(support));

				for (int j=i+1;j<siblings.length; j++) {
					FeatureNode<T> s2 = siblings[j];

					//Only need to check one of the features (if it's an and-group)
					int v2 = _builder.variable(s2.features().iterator().next());
					if (!vd.canBeOne(v2) && vd.canBeZero(v2))
						mutex.addEdge(s1, s2);
				}
				mx.free();
			}
		}

		//Create Mutex Groups
		_mutexCliques = findMutexCliques(mutex);
		for (Set<FeatureNode<T>> clique : _mutexCliques) {
			FeatureNode<T> parent = commonParent(g, clique);

			//Mutex group doesn't have a common parent ?
			if (parent == null) {
				logger.warning("Mutex group without a common parent: " + clique);
				continue;
			}

			g.addEdge(clique, parent, FeatureEdge.MUTEX);
		}

	}



	public void mkOrGroups(FeatureGraph<T> g) {
		for (FeatureNode<T> v : g.vertices()) {
			BDD b = _factory.one();
			for (T f: v.features())
				b.andWith(_builder.nget(f));

			// project formula on v and its children
			// keep only 1 feature in an and-group
			Set<T> keep = new HashSet<T>();
			keep.add(v.features().iterator().next());

			for (FeatureNode<T> child : g.children(v))
				keep.add(child.features().iterator().next());
			Collection<T> remove = CollectionUtils.subtract(_support, keep);
			BDD exist = _builder.mkSet(remove);
			BDD frag = _formula.id().exist(exist);

			//Debug code
			BDD supp = frag.support();
			int[] var = supp.scanSet();
			int sibs = var == null ? 0 : var.length;
			maxsib = Math.max(maxsib, sibs);
			maxnodesize = Math.max(maxnodesize, frag.nodeCount());
			supp.free();

			PrimeImplicants pi = new PrimeImplicants(b, frag);

			frag.free();
			exist.free();
			b.free();

			for (Implicant imp : pi) {
				Implicant imp1 = imp.removeNegations();

				Set<FeatureNode<T>> grouped = new HashSet<FeatureNode<T>>();
				for (int i : imp1) {
					//24-Apr added to handle new domains passed with Formula
					if (_support.contains(_builder.feature(i)))
						grouped.add(g.findVertex(_builder.feature(i)));
				}

				if (grouped.size() < 2 || grouped.contains(v))
					continue;
				assert g.children(v).containsAll(grouped);

				if (g.findEdge(grouped, v, FeatureEdge.XOR) != null)
					continue;
				//TODO maybe better to do a restrict on frag and calculate ValidDomains
				else if (_mutexCliques.contains(grouped)) {
					//Need to remove mutex since xor edge subsumes
					g.removeEdge(g.findEdge(grouped, v, FeatureEdge.MUTEX));
					g.addEdge(grouped, v, FeatureEdge.XOR);
				}
				else
					g.addEdge(grouped, v, FeatureEdge.OR);
			}

			pi.freeCache();
		}
	}

	/**
	 * Adds all trees to the synthetic root (ie. TOP). This has the effect of
	 * moving free variables to be children of TOP.
	 */
	public void mkSyntheticRoot(FeatureGraph<T> g) {

		Set<FeatureNode<T>> roots = findRoots(g);
		FeatureNode<T> synth = g.getTopVertex();

		for (FeatureNode<T> v : roots) {
			g.addEdge(v, synth, FeatureEdge.HIERARCHY);
			g.addEdge(v, synth, FeatureEdge.MANDATORY);
		}
	}


	/**
	 * Finds roots excluding the top and bottom nodes
	 * @param g
	 * @return
	 */
	private Set<FeatureNode<T>> findRoots(FeatureGraph<T> g) {
		Set<FeatureNode<T>> cand = new HashSet<FeatureNode<T>>(g.vertices());
		for (FeatureEdge e : g.edges()) {
			cand.removeAll(g.getSources(e));
		}
		cand.remove(g.getTopVertex());
		cand.remove(g.getBottomVertex());
		return cand;
	}



	/**
	 * @return null if the group doesn't have a common parent
	 */
	private FeatureNode<T> commonParent(FeatureGraph<T> g, Set<FeatureNode<T>> group) {
		Set<FeatureNode<T>> parents = new HashSet<FeatureNode<T>>(g.vertices());
		for (FeatureNode<T> v : group) {
			parents.retainAll(g.parents(v));
			if (parents.size() == 0) return null;
		}

		return parents.iterator().next ();
	}

	/**
	 * No longer needed since we're working on transitive reduction.
	 * @deprecated
	 */
	private FeatureNode<T> leastCommonAncestor(FeatureGraph<T> g, Set<FeatureNode<T>> group) {
		Set<FeatureNode<T>> common = new HashSet<FeatureNode<T>>(g.vertices());

		for (FeatureNode<T> v : group) {
			Set<FeatureNode<T>> ancestors = g.ancestors(v);
			common.retainAll (ancestors);
		}

		//Remove transitive parents //FIXME ?
		Iterator<FeatureNode<T>> iter = common.iterator();
		while (iter.hasNext()) {
			FeatureNode<T> v = iter.next();
			if (CollectionUtils.intersection(common, g.descendants(v)).size() > 0)
				iter.remove();
		}

		return common.iterator().next ();
	}


	private List<Integer> support() {
		List<Integer> result = new ArrayList<Integer>();
		for (T f : _support)
			result.add(_builder.variable(f));
		return result;
	}


	private Collection<Set<FeatureNode<T>>> findMutexCliques(
			UndirectedGraph<FeatureNode<T>, DefaultEdge> g) {

		BronKerboschCliqueFinder<FeatureNode<T>, DefaultEdge> finder
			= new BronKerboschCliqueFinder<FeatureNode<T>, DefaultEdge>(g);

		Collection<Set<FeatureNode<T>>> cliques = finder.getAllMaximalCliques();
		Iterator<Set<FeatureNode<T>>> iter = cliques.iterator();
		while (iter.hasNext()) {
			if (iter.next().size() < 2)
				iter.remove();
		}
		return cliques;
	}
}