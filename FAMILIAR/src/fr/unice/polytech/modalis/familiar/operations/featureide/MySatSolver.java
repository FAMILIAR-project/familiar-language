/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations.featureide;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;

/**
 * @author mathieuacher
 * 
 */
public class MySatSolver extends SatSolver {

	/**
	 * @param node
	 * @param timeout
	 */
	public MySatSolver(Node node, long timeout) {
		super(node, timeout);
	}
	
	
	public Set<String> getSetSolutions(int number) throws TimeoutException {
		Set<String> out = new HashSet<String>();
		if (contradiction)
			return out;

		IProblem problem = new ModelIterator(solver);
		int[] lastModel = null;
		for (int i = 0; i < number; i++) {
			if (!problem.isSatisfiable(i > 0)) {
				out.add("only " + i + " solutions\n");
				break;
			}
			int[] model = problem.model();
			if (lastModel != null) {
				boolean same = true;
				for (int j = 0; j < model.length; j++)
					if (model[j] != lastModel[j])
						same = false;
				if (same) {
					out.add("only " + i + " solutions\n");
					break;
				}
			}
			lastModel = model;
			StringBuilder pos = new StringBuilder();
			StringBuilder neg = new StringBuilder();
			for (int var : model)
				if (var > 0)
					pos.append(intToVar.get(Math.abs(var)) + " ");
				else
					neg.append(intToVar.get(Math.abs(var)) + " ");
			out.add(pos.toString());
		}
		return out;
	}


	public Map<Integer, Object> getVars2ID() {
		return intToVar ; 
	}

}
