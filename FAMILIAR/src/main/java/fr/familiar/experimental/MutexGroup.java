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

import fr.familiar.operations.ExpressionUtility;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

import java.util.Set;

import net.sf.javabdd.BDD;

public class MutexGroup extends FGroup {

	public MutexGroup (Set<FeatureNode<String>> source, FeatureNode<String> target) {
		_sources = source;
		_target = target;
		_edgeType = FeatureEdge.MUTEX ;
	}

	

	private BDD mkDisjunction(Set<FeatureNode<String>> fnodes, BDDBuilder<String> builder) {
		BDD result = builder.zero();
		for (FeatureNode<String> f : fnodes) {
			result.orWith(builder.get(f.getFeature()));
		}
		return result;

	}

	
	
	/**
	 * an Xor-group is a special kind of Mutex-group
	 * 
	 * @param fla
	 * @return
	 */
	public boolean isXorGroup(Formula<String> fla, BDDBuilder<String> builder) {
		BDD parent = builder.get(_target.getFeature());
		BDD children = mkDisjunction(_sources, builder);

		BDD entail = parent.imp(children);

		BDD oBDD = fla.getBDD().id();
		if (oBDD.and(entail.not()).isZero()) {
			return true;
		}

		return false;

	}
	
	@Override
	public Expression<String> toExpression() {
		assert (_sources.size() > 1) ; 
		return new Expression<String>(ExpressionType.AND, 
				ExpressionUtility.atMost1(_sources),
				ExpressionUtility.mkImplicationHierarchies(_sources, _target)) ; 
	}
 

}
