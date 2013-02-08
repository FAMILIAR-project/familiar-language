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

public interface IFeatureModel {

	/*
	 * root feature of the feature model
	 */
	public IFeature root();

	/*
	 * does the feature model have at least one valid configuration
	 */

	public boolean isValid();

	/*
	 * the set of features
	 */

	public Set<IFeature> features();

	/*
	 * set the variability operator associated to features
	 */
	public boolean setMandatory(IFeature ft);

	public boolean setOptional(IFeature ft);

	public boolean setAlternative(IFeature ft);

	public boolean setOr(IFeature ft);

}
