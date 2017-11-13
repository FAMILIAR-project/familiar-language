/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.parser;

import java.util.Collection;

import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.Mode;
import fr.familiar.variable.FeatureModelVariable;
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
