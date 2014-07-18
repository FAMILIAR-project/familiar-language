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
