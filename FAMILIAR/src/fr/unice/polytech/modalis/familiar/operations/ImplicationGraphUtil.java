package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.fm.SimpleExtendedEdge;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Excludes;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

public class ImplicationGraphUtil {

	public static Set<Expression<String>> toExpressionsWithBiImply(
			ImplicationGraph<String> impl) {

		Set<Expression<String>> biimply = new HashSet<Expression<String>>();
		Set<SimpleEdge> edges = impl.edgeSet();
		for (SimpleEdge se : edges) {
			String s1 = impl.getSource(se);
			String t1 = impl.getTarget(se);
			if (impl.containsEdge(t1, s1)) {
				// to avoid duplicate
				biimply.remove(new Expression<String>(ExpressionType.IFF,
						new Expression<String>(t1), new Expression<String>(s1)));

				biimply.add(new Expression<String>(ExpressionType.IFF,
						new Expression<String>(s1), new Expression<String>(t1)));
			}
		}
		return biimply;
	}

	public static <T> Set<Expression<T>> toExpressions(ImplicationGraph<T> impl) {

		Set<Expression<T>> imply = new HashSet<Expression<T>>();
		Set<SimpleEdge> edges = impl.edgeSet();
		for (SimpleEdge se : edges) {
			T s1 = impl.getSource(se);
			T t1 = impl.getTarget(se);
			imply.add(new Expression<T>(ExpressionType.IMPLIES,
					new Expression<T>(s1), new Expression<T>(t1)));
		}
		return imply;
	}

	public static Set<Expression<String>> computeImpliesEdge(
			FeatureModelVariable fmv1, ImplicationGraph<String> implEG,
			BDDBuilder<String> builder) {

		// FeatureModel<String> fm = fmv1.getFm() ;
		// FeatureGraph<String> fg = fm.getDiagram().clone();

		ImplicationGraph<String> implE = fmv1
				.getImplicationGraphFromFeatureHierarchy(builder);
		// getImplicationsFromHierarchy(fg); //

		/*
		 * System.err.println("\nhierarchy =="); debugImplicationGraph(implE);
		 * System.err.println("== hierarchy");
		 * System.err.println("implication graph ==");
		 * debugImplicationGraph(implEG);
		 * System.err.println("== implication graph\n");
		 */

		// remaining implications that are not represented in the feature
		// hierarchy
		// or as a feature group.
		// eg - e: edges in eg (implication graph) but not in e (feature
		// hierarchy)
		Set<SimpleExtendedEdge<String>> des = diffEdges(implEG, implE);
		Set<Expression<String>> imply = new HashSet<Expression<String>>();
		for (SimpleExtendedEdge<String> de : des) {
			String source = de.getSource();
			String target = de.getTarget();
			imply.add(new Expression<String>(ExpressionType.IMPLIES,
					new Expression<String>(target), new Expression<String>(
							source)));
		}

		/*
		 * Set<Expression<String>> alreadyImpliedByFeatureGraph = new
		 * HashSet<Expression<String>>();
		 * 
		 * for (Expression<String> expr : imply) { if (!fm.addConstraint(expr))
		 * alreadyImpliedByFeatureGraph.add(expr); }
		 * 
		 * for (Expression<String> expr : alreadyImpliedByFeatureGraph) {
		 * System.err.println("impl edge removed: " + expr); imply.remove(expr);
		 * }
		 */

		return imply;
	}

	public static <T> void debugImplicationGraph(ImplicationGraph<T> impl1) {
		Set<SimpleEdge> edges1 = impl1.edgeSet();
		for (SimpleEdge e1 : edges1) {
			T s1 = impl1.getEdgeSource(e1);
			T t1 = impl1.getEdgeTarget(e1);
			System.err.println("" + s1 + " => " + t1);
		}
	}

	@Deprecated
	public static ImplicationGraph<String> getImplicationsFromHierarchy(
			FeatureGraph<String> fg) {
		ImplicationGraph<String> impl = new ImplicationGraph<String>();
		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge fe : edges) {
			FeatureNode<String> s = fg.getSource(fe);
			FeatureNode<String> t = fg.getTarget(fe);
			if (t.isTop())
				continue;
			String strSource = s.getFeature();
			String strTarget = t.getFeature();
			impl.addVertex(strSource);
			impl.addVertex(strTarget);
			impl.addEdge(strSource, strTarget);

		}

		Set<FeatureEdge> mandatoryEdges = fg.selectEdges(FeatureEdge.MANDATORY);
		for (FeatureEdge fe : mandatoryEdges) {
			FeatureNode<String> s = fg.getSource(fe);
			FeatureNode<String> t = fg.getTarget(fe);
			if (t.isTop())
				continue;
			String strSource = s.getFeature();
			String strTarget = t.getFeature();
			impl.addVertex(strSource);
			impl.addVertex(strTarget);
			impl.addEdge(strSource, strTarget);
			impl.addEdge(strTarget, strSource);

		}

		// transitive closure

		return impl;
	}

	public static <T> Set<SimpleExtendedEdge<T>> diffEdges(
			ImplicationGraph<T> impl1, ImplicationGraph<T> impl2) {
		Set<SimpleExtendedEdge<T>> cedges12 = new HashSet<SimpleExtendedEdge<T>>(); // in
																					// impl1
																					// but
																					// not
																					// in
																					// impl2
		AbstractGraph<T, SimpleExtendedEdge<T>> graph = new SimpleDirectedGraph<T, SimpleExtendedEdge<T>>(
				(Class<? extends SimpleExtendedEdge<T>>) SimpleExtendedEdge.class);

		for (SimpleEdge e1 : impl1.edgeSet()) {
			boolean foundInE2 = false;
			for (SimpleEdge e2 : impl2.edgeSet()) {
				if (edgeEquality(e1, e2, impl1, impl2)) {
					foundInE2 = true;
					continue;
				}

			}

			if (!foundInE2) {
				T s1 = impl1.getEdgeSource(e1);
				T t1 = impl1.getEdgeTarget(e1);
				graph.addVertex(s1);
				graph.addVertex(t1);
				SimpleExtendedEdge<T> se = new SimpleExtendedEdge<T>(s1, t1,
						graph);
				cedges12.add(se);
			}
		}

		return cedges12;
	}

	public static <T> Set<SimpleExtendedEdge<T>> commonEdges(
			ImplicationGraph<T> impl1, ImplicationGraph<T> impl2) {
		Set<SimpleExtendedEdge<T>> cedgesCommon = new HashSet<SimpleExtendedEdge<T>>(); // in
																					// impl1
																					// AND
																					// in
																					// impl2
		AbstractGraph<T, SimpleExtendedEdge<T>> graph = new SimpleDirectedGraph<T, SimpleExtendedEdge<T>>((Class<? extends SimpleExtendedEdge<T>>) SimpleExtendedEdge.class);

		for (SimpleEdge e1 : impl1.edgeSet()) {
			for (SimpleEdge e2 : impl2.edgeSet()) {
				if (edgeEquality(e1, e2, impl1, impl2)) {

					T s1 = impl1.getEdgeSource(e1); // say in impl1
					T t1 = impl1.getEdgeTarget(e1);
					SimpleExtendedEdge<T> se = new SimpleExtendedEdge<T>(s1, t1, graph);
					cedgesCommon.add(se);

				}
			}
		}

		return cedgesCommon;
	}

	public static <T> boolean edgeEquality(SimpleEdge e1, SimpleEdge e2,
			ImplicationGraph<T> impl1, ImplicationGraph<T> impl2) {

		T s1 = impl1.getEdgeSource(e1);
		T t1 = impl1.getEdgeTarget(e1);

		T s2 = impl2.getEdgeSource(e2);
		T t2 = impl2.getEdgeTarget(e2);
		
		

		return s1.equals(s2) && t1.equals(t2);
	}

	public static Set<Expression<String>> computeImpliesEdge(
			FeatureModelVariable fmv1, BDDBuilder<String> builder) {
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(builder);
		return computeImpliesEdge(fmv1, impl1, builder);
	}

	public static Set<Expression<String>> computeExcludesEdge(
			FeatureModelVariable fmv1, BDDBuilder<String> builder) {
		ExclusionGraph<String> excl1 = fmv1.computeExclusionGraph(builder);
		
		ExclusionGraph<String> exclE = new ExclusionGraph<String>() ; 
		/*TODO: fixme why this => fmv1.getExclusionGraphFromFeatureHierarchy(builder);*/

		Set<SimpleExtendedEdge<String>> des = diffExclEdges(excl1, exclE);
		Set<Expression<String>> excludes = new HashSet<Expression<String>>();
		for (SimpleExtendedEdge<String> de : des) {
			String source = de.getSource();
			String target = de.getTarget();
			excludes.add(new Expression<String>(ExpressionType.IMPLIES,
					new Expression<String>(target), new Expression<String>(
							source).not()));
		}

		return excludes; //
	}

	public static <T> Set<SimpleExtendedEdge<T>> diffExclEdges(
			ExclusionGraph<T> excl1, ExclusionGraph<T> excl2) {
		Set<SimpleExtendedEdge<T>> cedges12 = new HashSet<SimpleExtendedEdge<T>>(); // in
																					// excl1
																					// but
																					// not
																					// in
																					// excl2
		AbstractGraph<T, SimpleExtendedEdge<T>> graph = new SimpleDirectedGraph<T, SimpleExtendedEdge<T>>(
				(Class<? extends SimpleExtendedEdge<T>>) SimpleExtendedEdge.class);

		for (Excludes<T> e1 : excl1.edgeSet()) {
			boolean foundInE2 = false;
			for (Excludes<T> e2 : excl2.edgeSet()) {
				if (edgeExclEquality(e1, e2, excl1, excl2)) {
					foundInE2 = true;
					continue;
				}

			}

			if (!foundInE2) {
				T s1 = excl1.getEdgeSource(e1);
				T t1 = excl1.getEdgeTarget(e1);
				graph.addVertex(s1);
				graph.addVertex(t1);
				SimpleExtendedEdge<T> se = new SimpleExtendedEdge<T>(s1, t1,
						graph);
				cedges12.add(se);
			}
		}

		return cedges12;
	}

	private static <T> boolean edgeExclEquality(Excludes<T> e1, Excludes<T> e2,
			ExclusionGraph<T> excl1, ExclusionGraph<T> excl2) {
		T s1 = excl1.getEdgeSource(e1);
		T t1 = excl1.getEdgeTarget(e1);

		T s2 = excl2.getEdgeSource(e2);
		T t2 = excl2.getEdgeTarget(e2);

		return s1.equals(s2) && t1.equals(t2);
	}

	private static Set<Expression<String>> computeExcudesEdge(
			FeatureModelVariable fmv1, ExclusionGraph<String> excl1,
			BDDBuilder<String> builder) {
		Set<Expression<String>> r = new HashSet<Expression<String>>();
		Set<Excludes<String>> excls = excl1.edgeSet();
		for (Excludes<String> excludes : excls) {
			r.add(excludes);
		}
		return r;
	}

	public static void debugExclusionGraph(ExclusionGraph<String> eg1) {
		Set<Excludes<String>> mutexes = eg1.edgeSet();
		for (Excludes<String> excludes : mutexes) {
			System.err.println("" + excludes);
		}

	}

	public static <T> FeatureGraph<T> toFeatureGraph(ImplicationGraph<T> impl) {
		
		FeatureGraph<T> fg = (FeatureGraph<T>) FeatureGraphFactory.mkStringFactory().mkTop() ; 
		Collection<T> vertices = impl.vertices() ;
		for (T v : vertices) {
			fg.addVertex(new FeatureNode<T>(v));
		}
		
		Set<SimpleEdge> edges = impl.edgeSet();
		for (SimpleEdge se : edges) {
			T s1 = impl.getSource(se);
			T t1 = impl.getTarget(se);
			FeatureNode<T> s = fg.findVertex(s1);
			FeatureNode<T> t = fg.findVertex(t1);
			fg.addEdge(s, t, FeatureEdge.HIERARCHY);
		}
		
		return fg;
	}

	public static boolean eq(ImplicationGraph<String> impl1, ImplicationGraph<String> impl2) {
		return _eqIn (impl1, impl2) && _eqIn (impl2, impl1);
		
	}

	private static boolean _eqIn(ImplicationGraph<String> impl1, ImplicationGraph<String> impl2) {
		
		Set<SimpleEdge> edges1 = impl1.edgeSet() ;
		Set<SimpleEdge> edges2 = impl2.edgeSet() ; 
		for (SimpleEdge e1 : edges1) {
			boolean found = false ; 
			for (SimpleEdge e2 : edges2) {
				if (edgeEquality(e1, e2, impl1, impl2)) 
					found = true ; 
			}
			if (found == false)
				return false ; 
		}
		return true ; 
		
	}

}
