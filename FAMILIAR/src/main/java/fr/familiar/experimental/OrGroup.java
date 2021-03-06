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

package fr.familiar.experimental;

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

import java.util.Set;

public class OrGroup extends FGroup {

	public OrGroup(Set<FeatureNode<String>> source, FeatureNode<String> target) {
		_sources = source ; 
		_target = target ; 
		_edgeType = FeatureEdge.OR ;
	}

	@Override
	public Expression<String> toExpression() {
		return new Expression<String>(ExpressionType.AND,
				ExpressionUtility.atLeast1(_sources, _target), // at least one
				ExpressionUtility.mkImplicationHierarchies(_sources, _target)); 
	}

	

}
