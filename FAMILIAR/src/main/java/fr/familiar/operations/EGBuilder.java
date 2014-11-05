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

import fr.familiar.fm.converter.ExclusionGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.Util;
import gsd.synthesis.ValidDomains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class EGBuilder {

	// TODO: refactor the code (already present in the synthetizer)
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> UndirectedGraph<FeatureNode<T>, DefaultEdge> buildForGroups(
			Formula<T> f, FeatureGraph<T> g, BDDBuilder<T> builder) {
		// Make Mutex Graph
		UndirectedGraph<FeatureNode<T>, DefaultEdge> mutex = new SimpleGraph<FeatureNode<T>, DefaultEdge>(
				DefaultEdge.class);

		for (FeatureNode<T> v : g.vertices())
			mutex.addVertex(v);

		List<Integer> support = support(f.getDomain(), builder);

		for (FeatureNode<T> v : g.vertices()) {
			FeatureNode<T>[] siblings = g.children(v).toArray(
					new FeatureNode[0]);

			for (int i = 0; i < siblings.length; i++) {
				FeatureNode<T> s1 = siblings[i];
				BDD mx = f.getBDD().id().andWith(builder.mkConjunction(s1));

				if (mx.isZero())
					throw new UnsupportedOperationException(
							"Dead features should have been removed!");

				ValidDomains vd = new ValidDomains(mx,
						Collections.min(support), Collections.max(support));

				for (int j = i + 1; j < siblings.length; j++) {
					FeatureNode<T> s2 = siblings[j];

					// Only need to check one of the features (if it's an
					// and-group)
					int v2 = builder.variable(s2.features().iterator().next());
					if (!vd.canBeOne(v2) && vd.canBeZero(v2))
						mutex.addEdge(s1, s2);
				}
				mx.free();
			}
		}

		return mutex;
	}

	public static <T extends Comparable<T>> ExclusionGraph<T> build(
			BDD problem, BDDBuilder<T> builder, Set<T> domain) {
		// Make Mutex Graph
		ExclusionGraph<T> mutex = new ExclusionGraph<T>();

		for (T f : domain)
			mutex.addVertex(f);

		int[] support = builder.vars(domain);

		HashSet<Integer> set_support = new HashSet<Integer>();
		for (int i : support)
			set_support.add(i);

		BDDFactory B = problem.getFactory();

		for (int i : support) {

			BDD temp = problem.id().andWith(B.ithVar(i));
			Set<Integer> falsified;
			if (temp.isZero()) { // if B is true in all assignments then it is
									// excluded by nothing
				falsified = new HashSet<Integer>();
			} else
				falsified = findFalsified(temp, Util.min(support),
						Util.max(support));

			temp.free();
			falsified.retainAll(set_support);
			falsified.remove(i);

			for (int fal : falsified) {
				mutex.addEdge(builder.feature(fal), builder.feature(i));
			}
		}

		return mutex;
	}

	/**
	 * Find all variables in the problem that are 0 in all satisfiable
	 * assignments
	 * 
	 * @param problem
	 *            constraint representing the problem
	 * @param min_var
	 *            only consider variables with identifiers min_var or larger
	 * @param max_var
	 *            only consider variables with
	 * @return
	 */
	static public Set<Integer> findFalsified(BDD problem, int min_var,
			int max_var) {

		Set<Integer> value = new HashSet<Integer>();
		ValidDomains vd = new ValidDomains(problem, min_var, max_var);
		for (int var = min_var; var <= max_var; ++var)
			if (vd.canBeZero(var) && !vd.canBeOne(var))
				value.add(var);
		return value;
	}

	protected static <T extends Comparable<T>> List<Integer> support(
			Set<T> support, BDDBuilder<T> builder) {
		List<Integer> result = new ArrayList<Integer>();
		for (T f : support)
			result.add(builder.variable(f));
		return result;
	}

	public static <T extends Comparable<T>> ExclusionGraph<T> build(
			Formula<T> formula, BDDBuilder<T> builder) {
		return build(formula.getBDD(), builder, formula.getDomain());
	}

}
