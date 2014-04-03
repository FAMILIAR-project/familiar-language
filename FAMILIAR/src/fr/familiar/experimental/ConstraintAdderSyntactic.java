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

package fr.familiar.experimental;

import fr.familiar.fm.converter.ExclusionGraph;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

import java.util.Set;

public class ConstraintAdderSyntactic extends ConstraintAdder {

	public ConstraintAdderSyntactic(FeatureModel<String> fm,
			Set<Expression<String>> biimplies, ImplicationGraph<String> impl,
			ExclusionGraph<String> excl1) {
		super(fm, biimplies, impl, excl1);
	}
	
	public ConstraintAdderSyntactic(FeatureModel<String> fm) {
		super(fm);
	}

	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {
		return _fm.addConstraint(expr) ; 
	}

}
