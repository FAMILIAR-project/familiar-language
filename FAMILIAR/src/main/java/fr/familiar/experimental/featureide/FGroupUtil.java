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

package fr.familiar.experimental.featureide;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;

import fr.familiar.experimental.MutexGroup;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.operations.featureide.SATFormula;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FGroupUtil {
	
	/**
	 * an Xor-group is a special kind of Mutex-group SAT version
	 * 
	 * @param mtx
	 * @param fla
	 * @return
	 */
	public static boolean isXorGroup (MutexGroup mtx, SATFormula fla) {
		
		Node parent = new Literal(mtx.getTarget().getFeature());
		
		// mkDisjunctions of sources
		Node disj = SATBuilder.mkFalseNode() ; 
		for (FeatureNode<String> fNode : mtx.getSources()) {
			disj = new Or (disj, new Literal(fNode.getFeature()));
		}
		Node entail = new Or (new Not(parent), disj);
		Node oNode = fla.getNode() ; 
		
		if (!new SATFMLFormula(new And (oNode, new Not(entail))).isValid())
			return true ; 
		

		return false;
		
		
	}

}
