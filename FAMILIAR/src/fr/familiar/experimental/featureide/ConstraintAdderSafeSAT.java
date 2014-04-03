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

import java.util.Set;

import org.prop4j.And;
import org.prop4j.Not;

import fr.familiar.experimental.ConstraintAdder;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

public class ConstraintAdderSafeSAT extends ConstraintAdder {

	private SATFMLFormula _fmFla;


	public ConstraintAdderSafeSAT(FeatureModel<String> fm,
			Set<Expression<String>> biimplies, ImplicationGraph<String> impl,
			ExclusionGraph<String> excl1) {
		super(fm, biimplies, impl, excl1);
		
		_fmFla = new SATFMLFormula(new FeatureModelVariable("", _fm));
	}

	
	public ConstraintAdderSafeSAT(FeatureModel<String> fm) {
		super(fm);
		_fmFla = new SATFMLFormula(new FeatureModelVariable("", _fm));
	}


	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {

	

		SATFMLFormula exprFla = new SATFMLFormula(expr);
		And newNode = new And(_fmFla.getNode().clone(), new Not(exprFla.getNode()));
		SATFMLFormula newFla = new SATFMLFormula(newNode);
		boolean b = newFla.isValid() ; 
		if (b) {
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
		/*
		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		And newNode = new And(_fmFla.getNode().clone(), exprFla.getNode());
		SATFMLFormula newFla = new SATFMLFormula(newNode);
		
		
		Comparison comparison = newFla.compare(_fmFla);
		boolean b = comparison == Comparison.REFACTORING ; 
		if (!b) {
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
		*/

	}
	
	
	
	

}
