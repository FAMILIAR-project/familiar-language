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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;

public class ExpressionUtil {
	public static <T> Set<T> getAllFeatures(Expression<T> expr) {
		Set<T> result = new HashSet<T>();
		getAllFeatures_Internal(expr, result);
		return result;
	}
	private static <T> void getAllFeatures_Internal(Expression<T> expr, Collection<T> result) {
		if (expr == null)
			return;
		else if (expr.getType() == FEATURE) {
			result.add(expr.getFeature());
			return;
		}
		getAllFeatures_Internal(expr.getLeft(), result);
		// added by AM
		if (expr.getType() != ExpressionType.NOT)
			getAllFeatures_Internal(expr.getRight(), result);
	}

	public static <T extends Comparable<T>>
			Expression<T> mkConjunction(Collection<T> feats) {
		if (feats.size() == 0)
			throw new IllegalArgumentException("There must be at least 1 feature!");

		Iterator<T> iter = feats.iterator();
		Expression<T> e = new Expression<T>(iter.next());
		while (iter.hasNext()) {
			e = e.and(new Expression<T>(iter.next()));
		}
		return e;
	}

	public static <T extends Comparable<T>>
			Expression<T> mkDisjunction(Collection<T> feats) {
		if (feats.size() == 0)
			throw new IllegalArgumentException("There must be at least 1 feature!");

		Iterator<T> iter = feats.iterator();
		Expression<T> e = new Expression<T>(iter.next());
		while (iter.hasNext())
			e = e.or(new Expression<T>(iter.next()));

		return e;
	}

	public static <T extends Comparable<T>>
		Expression<T> mkDNF(Collection<FeatureNode<T>> vs) {
		if (vs.size() == 0)
			throw new IllegalArgumentException("There must be at least 1 feature node!");

		Iterator<FeatureNode<T>> iter = vs.iterator();
		Expression<T> e = mkConjunction(iter.next().features());
		while (iter.hasNext()) {
			e = e.or(mkConjunction(iter.next().features()));
		}
		return e;
	}

	/**
	 * Splits a top-level conjunction into a collection of conjoined
	 * expressions. For example, for the expression A & (B | C) & D & E, a
	 * collection containing 4 expressions is returned: A, (B|C) D, E
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>>
			Collection<Expression<T>> splitConjunction(Expression<T> e) {
		if (e == null) return Collections.emptyList();
		else if (e.getType() == ExpressionType.AND) {
			return CollectionUtils.union(
					splitConjunction(e.getLeft()),
					splitConjunction(e.getRight()));
		}
		else
			return Arrays.asList( e );
	}


	public static <T extends Comparable<T>>
		List<BinaryExpression<T>> mkBinaryEdge(FeatureEdge e, FeatureGraph<T> g) {

		List<BinaryExpression<T>> result = new ArrayList<BinaryExpression<T>>();

		if (!e.isBinary() && e.getType() != FeatureEdge.FROZEN)
			throw new IllegalArgumentException("FeatureEdge must be binary, was type: " + e.getType());

		switch (e.getType()) {
		case FeatureEdge.FROZEN:
			//Return empty list for frozen
			return result;
		case FeatureEdge.HIERARCHY:
			for (T fs : g.getSource(e).features())
				for (T ft : g.getTarget(e).features())
					result.add(new Requires<T>(fs, ft));
			return result;
		case FeatureEdge.MANDATORY:
			for (T fs : g.getSource(e).features())
				for (T ft : g.getTarget(e).features())
					result.add(new Requires<T>(ft, fs));
			return result;
		}

		assert false;
		return null;

	}

	/**
	 * FIXME This should probably belong in an ExpressionBuilder class
	 */
	public static <T extends Comparable<T>>
			List<Expression<T>> mkFeatureEdge(FeatureEdge e, FeatureGraph<T> g) {
		List<Expression<T>> result = new ArrayList<Expression<T>>();

		switch (e.getType()) {
		case FeatureEdge.HIERARCHY:
			for (T fs : g.getSource(e).features())
				for (T ft : g.getTarget(e).features())
					result.add(new Expression<T>(fs).implies(new Expression<T>(ft)));
			return result;
		case FeatureEdge.MANDATORY:
			for (T fs : g.getSource(e).features())
				for (T ft : g.getTarget(e).features())
					result.add(new Expression<T>(ft).implies(new Expression<T>(fs)));
			return result;
		case FeatureEdge.OR:
			result.add(mkDisjunction(g.getTarget(e).features())
						.implies(mkDNF(g.getSources(e))));
			return result;
		case FeatureEdge.XOR:
			result.add(mkDisjunction(g.getTarget(e).features())
					.implies(mkDNF(g.getSources(e))));
			result.addAll(mkMutex(e, g));
			return result;
		case FeatureEdge.MUTEX:
			return mkMutex(e, g);
		}

		assert false : e.getType();
		return null;
	}

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> List<Expression<T>>
		mkMutex(FeatureEdge e, FeatureGraph<T> g) {

		List<Expression<T>> result = new ArrayList<Expression<T>>();

		assert e.getType() == FeatureEdge.MUTEX || e.getType() == FeatureEdge.XOR;

		FeatureNode<T>[] arr = g.getSources(e).toArray(new FeatureNode[0]);
		for (int i=0; i<arr.length-1; i++) {
			for (int j=i+1; j<arr.length; j++) {
				FeatureNode<T> v1 = arr[i];
				FeatureNode<T> v2 = arr[j];
				result.add(mkDisjunction(v1.features())
							.implies(mkConjunction(v2.features()).not()));
			}
		}

		return result;
	}
}