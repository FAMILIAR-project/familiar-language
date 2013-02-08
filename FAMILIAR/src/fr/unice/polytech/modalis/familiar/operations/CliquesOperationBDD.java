/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;

/**
 * @author macher1
 *
 */
public class CliquesOperationBDD extends CliquesOperation {

	/**
	 * @param fmv
	 */
	public CliquesOperationBDD(FeatureModelVariable fmv) {
		super(fmv);
		// TODO Auto-generated constructor stub
	}

	/**
	 * computation rely on BDDs
	 * @return
	 */
	@Override
	public Collection<Set<String>> cliques() {
	
		if (!_fmv.isValid()) 
			return new HashSet<Set<String>>();
				
		Set<String> fts = _fmv.features().names() ; // TODO: IG over fts for BDDs
		ImplicationGraph<String> impl = _fmv.computeImplicationGraph(_fmv.getBuilder());		
		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		
		Set<String> deads = _fmv.deads(); // TODO: ensure that this is based on BDD
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
