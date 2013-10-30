/**
 * 
 */
package fr.familiar.parser;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

import java.util.Collection;

/**
 * @author macher1
 *
 */
public class HierarchyMergerWithMSTIG extends HierarchyMerger {

	
	private Formula<String> _fla;

	/**
	 * @param fla 
	 * @param m 
	 * 
	 */
	public HierarchyMergerWithMSTIG(Formula<String> fla) {
		_fla = fla ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.HierarchyMerger#build(java.util.Collection)
	 */
	@Override
	public FeatureModel<String> build(Collection<FeatureModelVariable> lfms) {

		FeatureHierarchySelectorMST fmHierarchySel = new FeatureHierarchySelectorMST(lfms, FMLCommandInterpreter.getBuilder()); 
		FeatureModel<String> fmImplHierarchy = fmHierarchySel.build(_fla);
		FeatureModel<String> fmMST = fmHierarchySel.mkMST(fmImplHierarchy);
		_LOGGER.debug("hierarchy (MST IMPL)=" + fmMST);

		return fmMST;
	}

}
