/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import gsd.graph.ImplicationGraph;

/**
 * @author macher1
 *
 */
public class SATDisjunctiveFormula implements FMLFormula {
	
	
	private Collection<SATFMLFormula> _flas;

	public SATDisjunctiveFormula(Collection<SATFMLFormula> flas) {
		_flas = flas ; 
	}
	
	public ExclusionGraph<String> computeExclusionGraph(Set<String> domain) {
		
		String[] aDomain = (String[]) domain.toArray(new String[] {});
		ExclusionGraph<String> excl = new ExclusionGraph<String>();
		for (String ft : domain)
			excl.addVertex(ft);
		
	    for (int i = 0; i < domain.size(); i++) {	
			

			String u = aDomain[i];
			for (int j = 0; j < aDomain.length; j++) {
				
				String v = aDomain[j];
				
				// i -> j ?
				if (i == j)
					continue ; 
				
				if (exclusion(u, v, _flas)) {
					excl.addEdge(u, v);
				}
				else {
				
				}
				
	
			}
		}
	    return excl ; 
	}
	
	public ImplicationGraph<String> computeImplicationGraph (Set<String> domain) {
		String[] aDomain = (String[]) domain.toArray(new String[] {});
		ImplicationGraph<String> impl = new ImplicationGraph<String>();
		for (String ft : domain)
			impl.addVertex(ft);
		
	    for (int i = 0; i < domain.size(); i++) {	
			

			String u = aDomain[i];
			for (int j = 0; j < aDomain.length; j++) {
				
				String v = aDomain[j];
				
				// i -> j ?
				if (i == j)
					continue ; 
				
				if (implication(u, v, _flas)) {
					impl.addEdge(u, v);
				}
				else {
				
				}
				
	
			}
		}
	    return impl ; 
	}
	
	private boolean exclusion (String u, String v,	Collection<SATFMLFormula> flas) {
		
		for (SATFMLFormula fla : flas) {
			if (!fla.excludes (u, v))
				return false ; 
		}
		return true ; 	
	}

	private boolean implication(String u, String v,	Collection<SATFMLFormula> flas) {
		
		for (SATFMLFormula fla : flas) {
			if (!fla.implies (u, v))
				return false ; 
		}
		return true ; 	
	}


	@Override
	public Set<String> getDomain() {
		return _collectAllFeatures() ; 
	}
	
	private Set<String> _collectAllFeatures() {
		Set<String> fts = new HashSet<String>();
		for (SATFMLFormula fla : _flas) {
			fts.addAll(fla.getDomain());
		}
		fts.remove("True");
		fts.remove("False");
		return fts ; 
	}





	

}
