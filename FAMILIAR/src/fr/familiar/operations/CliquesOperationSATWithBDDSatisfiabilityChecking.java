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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;

public class CliquesOperationSATWithBDDSatisfiabilityChecking extends
		CliquesOperation {

	public CliquesOperationSATWithBDDSatisfiabilityChecking(FeatureModelVariable fmv) {
		super(fmv);
		// TODO Auto-generated constructor stub
	}

	/**
	 * part of cliques computation rely on BDDs
	 * @return
	 */
	@Override
	public Collection<Set<String>> cliques() {
						
		if (!_fmv.isValid()) 
			return new HashSet<Set<String>>();
		// FIXME @FeatureIDE 
		SATFMLFormula satFla1 = new SATFMLFormula(_fmv);
		
		
		Set<String> fts = _fmv.features().names() ; 
		ImplicationGraph<String> impl = satFla1.computeImplicationGraph(fts);		
		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		
		Set<String> deads = _fmv.deads(); 
		if (deads.size() >= 1) {
			List<Set<String>> cliquesCleaned = new ArrayList<Set<String>> ();
			for (Set<String> cl : cliques) {
				Set<String> cleaned = Sets.difference(cl, deads) ; 
				if (!cleaned.isEmpty())
					cliquesCleaned.add(cleaned);
			}
			return cliquesCleaned ;
		}
		return cliques  ;
	}

}
