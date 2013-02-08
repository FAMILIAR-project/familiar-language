/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import java.util.Collection;

import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.Mode;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

/**
 * @author macher1
 *
 */
public class HierarchyMergerWithImpl extends HierarchyMerger {

	private Mode _m;

	public HierarchyMergerWithImpl(Mode m) {
		_m = m ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.HierarchyMerger#build(java.util.Collection)
	 */
	@Override
	public FeatureModel<String> build(Collection<FeatureModelVariable> lfms) {
			
		BDDBuilder<String> builder = FMLCommandInterpreter.getBuilder() ; 
		Formula<String> fla = new FMLMergerBDD(lfms, builder).calculateFormula(_m); 
		FeatureHierarchySelector<String> fmHierarchySel = new FeatureHierarchySelector<String>(builder); 

		FeatureModel<String> fmImplHierarchy = fmHierarchySel.build(fla);

		fmHierarchySel.contract(fmImplHierarchy);
		_LOGGER.debug("hierarchy (IMPL)=" + fmImplHierarchy);

		return fmImplHierarchy;
	}
	
	

}
