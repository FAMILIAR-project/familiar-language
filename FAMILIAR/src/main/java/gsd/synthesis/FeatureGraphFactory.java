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


public class FeatureGraphFactory<T> {

	private final T _topFeat;
	private final T _bottomFeat;
	public static final String DEFAULT_TOP_STRING = "1";
	public static final String DEFAULT_BOTTOM_STRING = "0";

	public FeatureGraphFactory(T topFeat, T bottomFeat) {
		_topFeat = topFeat;
		_bottomFeat = bottomFeat;
	}

	/**
	 * @return A {@link FeatureGraph} representing true.
	 */
	public FeatureGraph<T> mkTop() {
		FeatureGraph<T> fg = new FeatureGraph<T>(_topFeat, _bottomFeat);
		return fg;
	}

	public FeatureModel<T> mkTopModel() {
		FeatureGraph<T> fg = mkTop();
		return new FeatureModel<T>(fg);
	}

	/**
	 * @return A {@link FeatureGraph} representing false (contradiction).
	 */
	public FeatureGraph<T> mkBottom() {
		FeatureGraph<T> fg = new FeatureGraph<T>(_topFeat, _bottomFeat);
		fg.addEdge(fg.getBottomVertex(), fg.getTopVertex(), FeatureEdge.HIERARCHY);
		fg.addEdge(fg.getBottomVertex(), fg.getTopVertex(), FeatureEdge.MANDATORY);
		return fg;
	}

	public FeatureModel<T> mkBottomModel() {
		FeatureGraph<T> fg = mkBottom();
		return new FeatureModel<T>(fg);
	}

	public T getTopFeature() {
		return _topFeat;
	}

	public T getBottomFeature() {
		return _bottomFeat;
	}

	public static FeatureGraphFactory<String> mkStringFactory() {
		return new FeatureGraphFactory<String>(DEFAULT_TOP_STRING, DEFAULT_BOTTOM_STRING);
	}
}