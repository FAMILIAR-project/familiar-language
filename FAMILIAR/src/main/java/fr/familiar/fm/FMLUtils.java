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

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;


public class FMLUtils {

	/**
	 * @param sv1
	 *            basically a configuration a set of set variable (each being a
	 *            set of features)
	 * @return a set of set
	 */
	public static Set<Set<String>> setConfigToSetStr(Set<Variable> sv1) {
		Set<Set<String>> sv0 = new HashSet<Set<String>>();

		for (Variable v1 : sv1) {
			Set<String> cft = new HashSet<String>();
			assertTrue(v1 instanceof SetVariable);
			SetVariable ss1 = (SetVariable) v1;
			Set<Variable> fts = ss1.getVars();
			for (Variable ft : fts) {
				assertTrue(ft instanceof FeatureVariable);
				FeatureVariable ftv = (FeatureVariable) ft;
				cft.add(ftv.getFtName());
			}
			sv0.add(cft);
		}

		return sv0;
	}

}
