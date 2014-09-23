/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.fm.featureide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;

import fr.familiar.interpreter.FMLShell;

/**
 * @author mathieuacher
 * 
 */
public class AllConfigsSolver extends SatSolver {

	/**
	 * @param node
	 * @param timeout
	 */
	public AllConfigsSolver(Node node, long timeout) {
		super(node, timeout);

	}

	public List<List<String>> getSolutions() throws TimeoutException {

		if (contradiction) {
			FMLShell.getInstance()
					.printDebugMessage("No solutions found (SAT)");
			return new ArrayList<List<String>>();
		}

		return getSolutions(solver, intToVar);

	}

	public static List<List<String>> getSolutions(ISolver aSolver,
			Map<Integer, Object> aIntToVar) throws TimeoutException {

		List<List<String>> out = new ArrayList<List<String>>();
		IProblem problem = new ModelIterator(aSolver);

		StringBuilder sout = new StringBuilder();
		while (problem.isSatisfiable()) {

			int[] model = problem.model();
			List<String> currentSol = new ArrayList<String>();

			StringBuilder pos = new StringBuilder();
			StringBuilder neg = new StringBuilder();
			for (int var : model) {
				if (var > 0) {
					String ftSelected = aIntToVar.get(Math.abs(var)).toString();
					pos.append(ftSelected + " ");
					currentSol.add(ftSelected);
				} else
					neg.append(aIntToVar.get(Math.abs(var)) + " ");
			}
			out.add(currentSol);
			sout.append("+: " + pos + "    -: " + neg + "\n");
			// debug
		}

		return out;
	}

}
