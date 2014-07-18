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

import java.util.Collection;
import java.util.Set;

import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public abstract class CliquesOperation {
	
	protected FeatureModelVariable _fmv ; 
	
	public CliquesOperation(FeatureModelVariable fmv) {
		_fmv = fmv ;  
	}
	
	/**
	 * cliques are set of features that have the same assignment (based on Implication graph)
	 * @return the set of cliques where a clique is a set of names (feature/variable)
	 */
	public abstract Collection<Set<String>> cliques() ; 

}
