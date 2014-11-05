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

import java.util.ArrayList;
import java.util.Collection;

public class EdgeContainer<T> {

	private final Collection<FeatureNode<T>> _sources;
	private FeatureNode<T> _target;
	private int _type;

	public EdgeContainer(FeatureNode<T> source, FeatureNode<T> target) {
		_sources = new ArrayList<FeatureNode<T>>();
		_sources.add(source);
		_target = target;
		_type = FeatureEdge.HIERARCHY;
	}

	public EdgeContainer(Collection<FeatureNode<T>> sources, FeatureNode<T> target,
			int type) {
		_sources = sources;
		_target = target;
		_type = type;
	}

	public Collection<FeatureNode<T>> getSources() {
		return _sources;
	}
	public FeatureNode<T> getTarget() {
		return _target;
	}
	public int getType() {
		return _type;
	}

}