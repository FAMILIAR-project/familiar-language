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

package fr.familiar.variable;

import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

import java.util.Set;

import net.sf.javabdd.BDD;

/**
 * @author mathieuacher much more to do (rename, removal, etc.) and a real lazy
 *         mechanism
 */
public class FeatureModelLazyVariable extends FeatureModelVariable {

	
	private Formula<String> _overFormula = null;
	
	private BDDBuilder<String> _builder = null ; 

	public FeatureModelLazyVariable(String assigner, FeatureModel<String> fm) {
		this(assigner, fm, null);
	}

	public FeatureModelLazyVariable(String assigner, FeatureModel<String> fm,
			Formula<String> overFormula) {
		super(assigner, fm);
		_overFormula = overFormula;
	}
	
	public FeatureModelLazyVariable(String assigner, FeatureModel<String> fm,
			Formula<String> overFormula, BDDBuilder<String> builder) {
		super(assigner, fm);
		_overFormula = overFormula;
		_builder = builder ; 
	}
	
	
	@Override
	public BDDBuilder<String> getBuilder() {
		if (_builder == null)
			return super.getBuilder() ;
		return _builder ; 
	}

	@Override
	public Formula<String> getFormula() {
		
		BDDBuilder<String> builder = getBuilder() ; 
		Formula<String> fmFormula = builder.mkFeatureModel(_fm); // FIXME lazy?
		BDD overBDD = builder.one();
		if (_overFormula != null) {
			overBDD = _overFormula.getBDD().not();
		}

		
		BDD fdBDD = fmFormula.getBDD() ; 
		BDD comprehensiveBDD = fdBDD.andWith(overBDD);
		
		Set<String> fts = features().names();
		Formula<String> comprehensiveFormula = new Formula<String>(
				comprehensiveBDD, fts, builder);
		return comprehensiveFormula;
	}

}
