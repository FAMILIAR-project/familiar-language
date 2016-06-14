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

package fr.familiar.operations.featureide;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.xtext.example.mydsl.fml.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.operations.FMSlicer;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;

/**
 * @author mathieuacher move to package operations
 */
public class FMSlicerSAT extends FMSlicer {

	
	protected static Logger _LOGGER = Logger.getLogger(FMSlicerSAT.class);
	
	public FMSlicerSAT() {

	}

	@Override
	public FeatureModelVariable sliceFM(FeatureModelVariable fmv, Collection<String> fts, SliceMode mode) {
		SATFormula slicedFormula = (SATFormula) new SlicerSATFormula().runSliceFormulaSAT(fmv,
				new SATFMLFormula(fmv).getNode(), fmv.features().names(), fts,
				mode);
		FeatureGraph<String> hierarchy = sliceHierarchy(fmv, fts, mode);
		
		
		if (!checkRootsWellFormedRule(hierarchy, slicedFormula)) {
			_LOGGER.debug(
					"obliged: add a synthetic root! and modify the slicedFla");
			addSyntheticRoot(hierarchy, FAKE_ROOT_NAME);
			_LOGGER.debug("NEW HIERARCHY " + hierarchy);
		}
		
		try { 
			hierarchy.findVertex(FAKE_ROOT_NAME) ; 
			Set<String> sRoot = new HashSet<String>();
			sRoot.add(FAKE_ROOT_NAME);	
			
			Node n = new And (slicedFormula.getNode(), new Literal(FAKE_ROOT_NAME)) ; 
			slicedFormula = new SATFMLFormula(n);
					
		}
		catch (Exception e) {
			// ok nothing to do
		}
		
		
		FeatureModel<String> fmHierarchy = new FeatureModel<String>(hierarchy) ;	
	
		FeatureModelVariable fmvSliced = new KSynthesisSAT(slicedFormula, 
					new KnowledgeSynthesis(fmHierarchy.getDiagram())).build();

		return fmvSliced;
		
	}
	
	/**
	 * REFACTOR in line with BDD
	 * @param hierarchy
	 * @param slicedFla
	 * @return
	 */
	private boolean checkRootsWellFormedRule(
			FeatureGraph<String> hierarchy, SATFormula slicedFla) {

		Set<FeatureNode<String>> roots = findRoots(hierarchy); // roots as now defined
		_LOGGER.debug("fn roots=" + roots);
		
		// special case
		/*
		if (roots.size() == 1) {
			FeatureNode<String> fnRoot = roots.iterator().next() ;
			if (fnRoot.getFeature().equals(FAKE_ROOT_NAME))
				return true ; 
		}*/
						
		Set<String> cores = slicedFla.cores(hierarchy.features());
	
			
		Set<String> roots2String = new HashSet<String>();
		for (FeatureNode<String> root : roots) {
			assert (root.getType() == FeatureType.SOLITARY);
			String ft = root.getFeature();
			roots2String.add(ft);
		}

		_LOGGER.debug("cores=" + cores);
		_LOGGER.debug("roots=" + roots2String);
		
		
				
		Set<String> rootCandidates = Sets.intersection(roots2String, cores);
		
		if (rootCandidates.size() >= 1) { // it goes fine: root features are indeed core features
			String electedRoot = rootCandidates.iterator().next() ; // we choose one
			_LOGGER.debug("root feature " + electedRoot + " chosen");
			//forceNewRoot(hierarchy, electedRoot); // TODO: randomize a bit
			return true ; 
		}
		
		if (rootCandidates.size() == 0) { // no root being core
			if (cores.size() == 0)  // and no cores!
				return false ; // synthetic will be added to ensure well-formedness, obliged				
			if (cores.size() == 1) { // no choice: the only core is the root!
				String defactoRoot = cores.iterator().next() ;
				_LOGGER.debug("core feature " + defactoRoot + " as defacto root");
				forceNewRoot(hierarchy, defactoRoot);
				cleanUpFakeNode(hierarchy);
				return true ; 
			}
			else {
				forceNewRoot(hierarchy, cores.iterator().next()); // TODO: randomize a bit
				cleanUpFakeNode(hierarchy);
				return true ;
			}
		}
				
		// other cases
		return false ;
	}

}
