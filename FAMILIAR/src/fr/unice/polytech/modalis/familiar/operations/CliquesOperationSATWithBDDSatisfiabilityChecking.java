package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
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
