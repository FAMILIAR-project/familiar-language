/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.fm;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mathieuacher
 * 
 */
public class FeatureNodeUtils {

	public static boolean hasOrGroup(FeatureGraph<String> fg,
			FeatureNode<String> v) {
		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.OR:
				return true;

			}
		}
		return false;
	}

	public static boolean hasXorGroup(FeatureGraph<String> fg,
			FeatureNode<String> v) {
		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.XOR:
				return true;

			}
		}
		return false;
	}

	public static boolean hasMutexGroup(FeatureGraph<String> fg,
			FeatureNode<String> v) {
		return !fg.incomingEdges(v, FeatureEdge.MUTEX).isEmpty();
		/*
		 * for (FeatureEdge e : fg.incomingEdges(v)) { switch (e.getType()) {
		 * case FeatureEdge.MUTEX: return true ;
		 * 
		 * } } return false ;
		 */
	}

	public static boolean hasOptionalFeatures(FeatureGraph<String> fg,
			FeatureNode<String> v) {

		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.HIERARCHY:
				boolean notMandatory = (fg.findEdge(fg.getSource(e),
						fg.getTarget(e), FeatureEdge.MANDATORY) == null);
				boolean notXor = !isInXOR(fg, fg.getSource(e));
				boolean notOr = !isInOR(fg, fg.getSource(e));
				if (notMandatory && notXor && notOr)
					return true;

			}
		}

		return false;
	}

	public static boolean hasMandatoryFeatures(FeatureGraph<String> fg,
			FeatureNode<String> v) {

		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.MANDATORY:
				return true;

			}
		}

		return false;
	}

	public static boolean isInOR(FeatureGraph<String> fg, FeatureNode<String> v) {
		for (FeatureEdge e : fg.outgoingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.OR:
				return true;

			}
		}
		return false;
	}

	public static boolean isInXOR(FeatureGraph<String> fg, FeatureNode<String> v) {
		for (FeatureEdge e : fg.outgoingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.XOR:
				return true;

			}
		}
		return false;
	}

	public static <T> boolean isMandatory(FeatureGraph<T> g,
			FeatureNode<T> child) {

		for (FeatureEdge e : g.outgoingEdges(child)) {
			switch (e.getType()) {
			case FeatureEdge.MANDATORY:
				return true;

			}
		}

		return false;
	}

	public static boolean isOptional(FeatureGraph<String> fg,
			FeatureNode<String> v) {

		for (FeatureEdge e : fg.outgoingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				return false;
			case FeatureEdge.XOR:
				return false;
			case FeatureEdge.OR:
				return false;
			case FeatureEdge.MANDATORY:
				return false;

			}
		}

		return true;
	}

	/**
	 * Weird/reuse method (TODO: rewrite)
	 * 
	 * @param fg
	 *            the feature hierarchy/diagram
	 * @param v
	 *            the parent feature
	 * @return true if v has several "groups", e.g., A : (B|C)+ D ; => 2 groups
	 */
	public static boolean isMultiGroup(FeatureGraph<String> fg,
			FeatureNode<String> v) {

		boolean andGroups = (hasOptionalFeatures(fg, v) || hasMandatoryFeatures(
				fg, v));
		boolean alternativeGroups = (hasOrGroup(fg, v) || hasXorGroup(fg, v) || hasMutexGroup(
				fg, v));
		if (andGroups && alternativeGroups) // alternative with And
			return true;

		// several Xor/Or groups
		if (hasOrGroup(fg, v) && hasXorGroup(fg, v)) // Or with Xor-
			return true;

		// several Or/Mutex groups
		if (hasOrGroup(fg, v) && hasMutexGroup(fg, v)) // Or with Mutex
			return true;

		// several Xor/Mutex groups
		if (hasXorGroup(fg, v) && hasMutexGroup(fg, v)) // Xor with Mutex
			return true;

		// several Xor
		int i = 0;
		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.XOR:
				i++;

			}
		}

		if (i > 1)
			return true;

		// several Or
		int j = 0;
		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.OR:
				j++;

			}
		}
		if (j > 1)
			return true;

		// several Mutex
		int k = 0;
		for (FeatureEdge e : fg.incomingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				k++;

			}
		}
		if (k > 1)
			return true;

		return false;

	}

	/**
	 * @param fg
	 *            feature diagram/graph
	 * @param v
	 *            the parent feature
	 * @param type
	 *            Xor/Or/Mutex
	 * @return a set of Xor/Or/Mutex groups where a group is a set of feature
	 *         nodes
	 */
	public static Set<Set<FeatureNode<String>>> selectGroups(
			FeatureGraph<String> fg, FeatureNode<String> v, int type) {
		assert (type == FeatureEdge.OR || type == FeatureEdge.XOR || type == FeatureEdge.MUTEX);
		Set<Set<FeatureNode<String>>> groups = new HashSet<Set<FeatureNode<String>>>();
		Collection<FeatureEdge> edgesType = fg.incomingEdges(v, type);
		// FMLShell.getInstance().printDebugMessage("Selecting edges of type=" +
		// type + " for v=" + v + " :" + edgesType);
		for (FeatureEdge e : edgesType) {
			groups.add(fg.getSources(e));
		}
		return groups;

	}

	/**
	 * obvisouly wrong for free variables
	 * 
	 * @param <T>
	 * @param ftNode
	 * @param fg
	 * @return
	 */
	public static <T> boolean isRoot(FeatureNode<T> ftNode, FeatureGraph<T> fg) {
		FeatureNode<T> top = fg.getTopVertex();
		return fg.findEdge(ftNode, top, FeatureEdge.HIERARCHY) != null;
	}

	public static boolean isTop(FeatureNode<String> ftNode) {
		return ftNode.getFeature().equals(
				FeatureGraphFactory.DEFAULT_TOP_STRING);
	}

	public static boolean isBottom(FeatureNode<String> ftNode) {
		return ftNode.getFeature().equals(
				FeatureGraphFactory.DEFAULT_BOTTOM_STRING);
	}

	public static boolean isInMTX(FeatureGraph<String> fg, FeatureNode<String> v) {
		for (FeatureEdge e : fg.outgoingEdges(v)) {
			switch (e.getType()) {
			case FeatureEdge.MUTEX:
				return true;

			}
		}
		return false;
	}

}
