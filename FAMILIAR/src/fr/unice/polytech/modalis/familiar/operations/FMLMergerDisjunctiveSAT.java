/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.prop4j.And;
import org.prop4j.Node;
import org.prop4j.Not;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;

/**
 * @author macher1
 *
 */
public class FMLMergerDisjunctiveSAT extends FMLMerger {
	
	
	public FMLMergerDisjunctiveSAT(Collection<FeatureModelVariable> lfms) {
		// clone ? 
		super(lfms);
	}

	@Override
	public FeatureModelVariable intersection() {
		FMLShell.getInstance().printTODO("intersection not yet implemented in " + this.getClass());
		return null;
	}

	@Override
	public FeatureModelVariable union() {
		Set<String> fts = new HashSet<String>();
		for (FeatureModelVariable fmv : _lfms) {
			fts.addAll(fmv.features().names());
		}
		
		// negated variables
		Collection<SATFMLFormula> flas = new ArrayList<SATFMLFormula>() ; 
		for (FeatureModelVariable fmv : _lfms) {
			Node n = new SATFMLFormula(fmv).getNode() ; 
			Set<String> cFts = fmv.features().names() ;
			Set<String> notIncls = Sets.difference(fts, cFts);
			for (String nFt : notIncls) {
				n = new And (n, new Not(nFt));
			}
			flas.add(new SATFMLFormula(n));
		}
		
		SATDisjunctiveFormula fla = new SATDisjunctiveFormula(flas) ;
		Set<String> flaDomain = fla.getDomain() ; 
		ImplicationGraph<String> impl = fla.computeImplicationGraph(flaDomain)  ;
	    System.err.println("IG=" + ImplicationGraphUtil.toExpressions(impl));
	    ExclusionGraph<String> excl = fla.computeExclusionGraph(flaDomain)  ;
	    System.err.println("EG=" + ImplicationGraphUtil.toExpressions(excl));
				
		return null ; 
	}
	
	
	

	

}
