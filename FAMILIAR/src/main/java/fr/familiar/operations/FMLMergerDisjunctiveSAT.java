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
import java.util.Set;

import org.prop4j.And;
import org.prop4j.Node;
import org.prop4j.Not;

import com.google.common.collect.Sets;

import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
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
		//Set<Expression<String>> ig = ImplicationGraphUtil.toExpressions(impl) ; 
	   // System.err.println("#IG=" + ig.size());
	  //  System.err.println("IG=" + ig);
	    ExclusionGraph<String> excl = fla.computeExclusionGraph(flaDomain)  ;
	    //Set<Excludes<String>> eg = ImplicationGraphUtil.toExpressions(excl) ; 
	   // System.err.println("#EG=" + eg.size());
	  // System.err.println("EG=" + eg);
	   
				
		return null ; 
	}
	
	
	

	

}
