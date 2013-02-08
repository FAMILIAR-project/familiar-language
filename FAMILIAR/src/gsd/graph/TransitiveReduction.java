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

import java.util.HashSet;
import java.util.Set;

public class TransitiveReduction {
	private TransitiveReduction() {}

	public static TransitiveReduction INSTANCE = new TransitiveReduction();

	public <V,E> void reduce(BasicGraph<V,E> g) {
		for (V v : g.vertices()) {
			_transitiveReduction(v, g);
		}
	}

	private <V,E> void _transitiveReduction(V v, BasicGraph<V,E> g) {
		Set<V> needless = descendants(v, g);

		Set<V> desc = new HashSet<V>();
		for (V u : g.children(v))
			desc.addAll(descendants(u, g));

		needless.retainAll (desc);

		for (V u : needless)
			g.removeEdge(g.findEdge(u,v));
	}


	public static <V,E> Set<V> descendants(V v, BasicGraph<V,E> g) {
		HashSet<V> result = new HashSet<V> ();
		for (V u : g.children(v)) {
			result.add(u);
			result.addAll (descendants(u, g));
		}
		return result;
	}

}