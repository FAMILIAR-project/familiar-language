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
package fr.familiar.fm;

import org.xtext.example.mydsl.fml.CNF;
import org.xtext.example.mydsl.fml.Child;
import org.xtext.example.mydsl.fml.FeatureModel;
import org.xtext.example.mydsl.fml.Production;

/**
 * @author mathieuacher
 * 
 */
public abstract class FeatureModelVisitor {

	/*
	 * the feature model to visit
	 */

	protected FeatureModel fm;

	public FeatureModelVisitor(FeatureModel fm) {
		this.fm = fm;
	}

	/*
	 * Entry point
	 */

	public abstract String treatFeatureModel(FeatureModel fm);

	/*
	 * Treat a production (like a grammar production in GUIDSL)
	 */

	public abstract String treatProd(Production prod);

	/*
	 * Treat a child feature (Xor, Or, And)
	 */

	public abstract String treatChild(Child c);

	/*
	 * Treat a constraint (e.g., A implies B)
	 */

	public abstract String treatConstraint(CNF constraint);

}
