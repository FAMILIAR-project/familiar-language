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

package fr.familiar.fm;

import java.util.Set;

import org.apache.log4j.Logger;

import fr.familiar.experimental.FGroup;
import fr.familiar.experimental.MutexGroup;
import fr.familiar.experimental.OrGroup;
import fr.familiar.experimental.XorGroup;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FGroupBuilder {

	private static Logger _LOGGER = Logger.getLogger(FGroupBuilder.class) ; 
	
	public static FGroup make(Set<FeatureNode<String>> sources,	FeatureNode<String> target, int edgeType) {
		if (edgeType == FeatureEdge.OR)
			return new OrGroup (sources, target);
		else if (edgeType == FeatureEdge.XOR)
			return new XorGroup (sources, target);
		else if (edgeType == FeatureEdge.MUTEX)
			return new MutexGroup (sources, target);
		else {
			_LOGGER.error("Unknown feature group: " + edgeType);
			return null ;
		}
	}

}
