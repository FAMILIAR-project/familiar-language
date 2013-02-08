/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;
import gsd.synthesis.Formula;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

/**
 * @author macher1
 *
 */
public class FMSlicerBDD extends FMSlicer {
	
	protected static Logger _LOGGER = Logger.getLogger(FMSlicerBDD.class);

	protected BDDBuilder<String> _builder;

	/**
	 * 
	 */
	public FMSlicerBDD(BDDBuilder<String> builder) {
		_builder = builder ; 
 	}
	
	
	
	

	
	@Override
	public FeatureModelVariable sliceFM(FeatureModelVariable fmToSlice,
			Collection<String> fts, SliceMode sliceMode) {
		// 1. compute the sliced formula
		Formula<String> slicedFla = sliceFormula(fmToSlice, fts, sliceMode);

		FeatureModelVariable fmvSliced = _computeFMVSliced(fmToSlice,
				slicedFla, fts, sliceMode);

		//fmvSliced = MergeAnalyzer.complement(slicedFla, fmvSliced, builder); // constraints
		//TODO: parameterize 
		fmvSliced = FMLMergerBDD.complementLazy(slicedFla, fmvSliced, _builder); // constraints

		return fmvSliced ;
	}

	private FeatureModelVariable _computeFMVSliced(
			FeatureModelVariable fmToSlice, Formula<String> slicedFla,
			Collection<String> fts, SliceMode sliceMode) {

		// 2. we compute the expected hierarchy
		FeatureGraph<String> hierarchy = sliceHierarchy(fmToSlice, fts, sliceMode);

		// TODO: add variability information as well (prefered groups)
		_LOGGER.debug(
				"expected hierarchy:\n\tfgHierarchy=" + hierarchy);

		// 3. we apply the Czarnecki's algorithm while taking into account the
		// expected hierarchy

		if (!checkRootsWellFormedRule(hierarchy, slicedFla, _builder)) {
			_LOGGER.debug(
					"obliged: add a synthetic root! and modify the slicedFla");
			addSyntheticRoot(hierarchy, FAKE_ROOT_NAME);
			_LOGGER.debug("NEW HIERARCHY " + hierarchy);
		}
		
		try { 
			hierarchy.findVertex(FAKE_ROOT_NAME) ; 
			Set<String> sRoot = new HashSet<String>();
			sRoot.add(FAKE_ROOT_NAME);		
			slicedFla.andWith(new Formula<String>(_builder.get(FAKE_ROOT_NAME),
					sRoot, _builder));
		}
		catch (Exception e) {
			// ok nothing to do
		}
		
		
		FeatureModel<String> fmHierarchy = new FeatureModel<String>(hierarchy) ;	
	
		FeatureModelVariable fmvSliced = new KSynthesisBDD(slicedFla, 
					new KnowledgeSynthesis(fmHierarchy.getDiagram()), _builder).build();

		return fmvSliced;
	}
	
	private boolean checkRootsWellFormedRule(
			FeatureGraph<String> hierarchy, Formula<String> slicedFla,
			BDDBuilder<String> builder) {

		Set<FeatureNode<String>> roots = findRoots(hierarchy); // roots as now defined
		_LOGGER.debug("fn roots=" + roots);
		
		// special case
		/*
		if (roots.size() == 1) {
			FeatureNode<String> fnRoot = roots.iterator().next() ;
			if (fnRoot.getFeature().equals(FAKE_ROOT_NAME))
				return true ; 
		}*/
						
		Set<String> cores = new FormulaAnalyzer<String>(slicedFla, builder)
				.computeCoreFeatures();
	
			
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
	
	
	
	


		
	/**
	 * @param fm
	 * @param features
	 * @param slideMode
	 * @return
	 */
	public Formula<String> sliceFormula(FeatureModelVariable fm,
			Collection<String> features, SliceMode sliceMode) {
		return new SlicerBDDFormula(_builder).sliceFormula(fm.getFormula(), features, sliceMode);
	}

}
