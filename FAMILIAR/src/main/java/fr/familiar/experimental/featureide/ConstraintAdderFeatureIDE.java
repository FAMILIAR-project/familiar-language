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

package fr.familiar.experimental.featureide;

import java.util.Set;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.editing.ModelComparator;
import fr.familiar.experimental.ConstraintAdder;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

public class ConstraintAdderFeatureIDE extends ConstraintAdder {

	private de.ovgu.featureide.fm.core.FeatureModel _oModel ;	

	public ConstraintAdderFeatureIDE(FeatureModel<String> fm, 
								Set<Expression<String>> biimplies, 
								ImplicationGraph<String> impl, 
								ExclusionGraph<String> excl1
								) {
		super (fm, biimplies, impl, excl1);
		_oModel = new FMLtoFeatureIDE(new FeatureModelVariable("", (FeatureModel<String>) fm)).convert() ;
		 
	}

		
	public ConstraintAdderFeatureIDE(FeatureModel<String> fm) {
		super(fm);
		_oModel = new FMLtoFeatureIDE(new FeatureModelVariable("", (FeatureModel<String>) fm)).convert() ;
		 
	}


	@Override
	public boolean addNonEntailedConstraint(Expression<String> expr) {

		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		de.ovgu.featureide.fm.core.FeatureModel dirtyModel = _oModel.clone() ;
		Node exprNode = exprFla.getNode() ;
		dirtyModel.addPropositionalNode(exprNode);
		ModelComparator comparator = new ModelComparator(SATFMLFormula.SAT_TIMEOUT);
		Comparison comparison = FMComparator.convert(comparator.compare (_oModel, dirtyModel));
		boolean b = comparison == Comparison.REFACTORING ; 
		if (!b) {
			_oModel.addPropositionalNode(exprNode);
			return _fm.addConstraint(expr); // should be true
		}
		return false ;
	
	}
	
	

}
