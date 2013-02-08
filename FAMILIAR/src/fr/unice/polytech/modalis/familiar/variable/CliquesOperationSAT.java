/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.operations.CliquesOperation;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;

/**
 * @author macher1
 *
 */
public class CliquesOperationSAT extends CliquesOperation {

	/**
	 * @param fmv
	 */
	public CliquesOperationSAT(FeatureModelVariable fmv) {
		super(fmv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Set<String>> cliques() {
		
		
		// parameterize
		//Formula<String> fla = getFormula().id() ;
		//Formula<String> properFla = new FormulaAnalyzer<String>(fla, getBuilder()).removeDeadFeatures() ; // precondition
		//ImplicationGraph<String> impl = IGBuilder.build(getFormula(), getBuilder());
		//fla.free() ;
		//properFla.free() ;
		
		// FIXME @FeatureIDE
		
		SATFMLFormula satFla1 = new SATFMLFormula(_fmv);
		if (!satFla1.isValid()) // FIXME: isValid()
			return new HashSet<Set<String>>();
		
		Set<String> fts = _fmv.features().names() ; 
		ImplicationGraph<String> impl = satFla1.computeImplicationGraph(fts);
		
		
		
		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		
		Set<String> deads = satFla1.deads(fts); // FIXME: deads() 
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
