package fr.unice.polytech.modalis.familiar.operations;

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
