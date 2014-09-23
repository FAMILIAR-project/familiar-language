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

package fr.familiar.parser;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureModel;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author macher1
 *
 */
public abstract class HierarchyMerger {
	
	protected static Logger _LOGGER = Logger.getLogger(HierarchyMerger.class);
	
	
	/**
	 * given a collection of feature models compute the "merged" hierarchy
	 * different strategies are feasible (from basic to correct-by-construction and "optimized" hierarchies) 
	 * @param lfms collection of feature models
	 * @return the "merged" hierarchy 
	 */
	public abstract FeatureModel<String> build(Collection<FeatureModelVariable> lfms) ; 

}
