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

package fr.familiar.operations.featureide;

import org.prop4j.Node;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class SATFMLFormula extends SATFormula {

	public SATFMLFormula(FeatureModelVariable fmv) {
		super(fmv);
	}

	public SATFMLFormula(Node n) {
		super(n);
	}

	public SATFMLFormula(Expression<String> expr) {
		super (expr);
	}

	@Override
	public Node mkNode(FeatureModelVariable fmv) {
		return new SATBuilder().mkNode(fmv);
	}
	
	@Override
	public Node mkNode(Expression<String> expr) {
		return new SATBuilder().mkExpression(expr);
	}

	

	

	

}
