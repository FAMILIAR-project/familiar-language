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

import fr.familiar.parser.NameSpace;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

/**
 * @author macher
 * TODO: override many methods
 */
public class FeatureModelFalseVariable extends FeatureModelVariable {

	@Override
	public boolean isValid() {
		return false ; 
	}

	@Override
	public String getSpecificValue() {
		return "False"; 
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 */
	public FeatureModelFalseVariable(String name, FeatureModel<String> fm,
			Formula<String> formula) {
		super(name, fm, formula);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 * @param ns
	 */
	public FeatureModelFalseVariable(String name, FeatureModel<String> fm,
			Formula<String> formula, NameSpace ns) {
		super(name, fm, formula, ns);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param assigner
	 * @param fm
	 */
	public FeatureModelFalseVariable(String assigner, FeatureModel<String> fm) {
		super(assigner, fm);
		// TODO Auto-generated constructor stub
	}

}
