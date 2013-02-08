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
package fr.unice.polytech.modalis.familiar.fm.basic;

import java.util.Set;

/**
 * @author mathieuacher
 * 
 */
public interface IFeature {

	/*
	 * name of the feature
	 */

	public String name();

	/*
	 * parent feature
	 */
	public IFeature parent();

	/*
	 * children of the feature if any
	 */
	public Set<IFeature> children();

	/*
	 * in which feature model does the feature ft belong to?
	 */

	public IFeatureModel whichfm();

	/*
	 * variability operator of the feature
	 */
	public VEdge operator();

}
