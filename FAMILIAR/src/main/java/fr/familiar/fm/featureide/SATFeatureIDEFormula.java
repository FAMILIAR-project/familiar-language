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

package fr.familiar.fm.featureide;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.editing.NodeCreator;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

public class SATFeatureIDEFormula extends SATFormula {

	public SATFeatureIDEFormula(FeatureModelVariable fmv) {
		super(fmv);
	}

	public SATFeatureIDEFormula(Node n) {
		super(n);
	}

	@Override
	public Node mkNode(FeatureModelVariable fmv) {
		return NodeCreator.createNodes(new FMLtoFeatureIDE(fmv).convert());
	}

	@Override
	public Node mkNode(Expression<String> expr) {
		// TODO Auto-generated method stub
		return null;
	}

}
