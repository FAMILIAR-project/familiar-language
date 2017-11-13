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

package fr.familiar.operations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

import splar.core.constraints.CNFClause;
import splar.core.constraints.CNFFormula;
import splar.core.constraints.CNFLiteral;

public class CNFFormula2SAT {

	private Map<String, Integer> _ftsToVar;

	public CNFFormula2SAT() {
		_ftsToVar = new HashMap<String, Integer>();
	}

	public CNFFormula2SAT(Map<String, Integer> ftToVar) {
		_ftsToVar = ftToVar;
	}

	public IVec<IVecInt> mkClauses(CNFFormula cnf) {
		IVec<IVecInt> result = new Vec<IVecInt>();

		Set<CNFClause> clauses = cnf.getClauses();
		for (CNFClause cnfClause : clauses) {
			IVecInt vectInt = new VecInt(cnfClause.countLiterals());
			for (CNFLiteral literal : cnfClause.getLiterals()) {
				int signal = literal.isPositive() ? 1 : -1;
				int varID = getVariableIndex(literal.getVariable().getID());
				vectInt.push(signal * varID);
			}
			result.push(vectInt);

		}

		return result;
	}

	private int getVariableIndex(String id) {
		assert (_ftsToVar.containsKey(id));
		return _ftsToVar.get(id);
	}

}
