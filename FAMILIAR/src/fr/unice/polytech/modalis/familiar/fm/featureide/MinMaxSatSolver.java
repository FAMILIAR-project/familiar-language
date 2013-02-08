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
package fr.unice.polytech.modalis.familiar.fm.featureide;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.opt.MinOneDecorator;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IOptimizationProblem;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;
import org.xtext.example.mydsl.fML.AutoConfMode;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;

/*
 * TODO rewrite completly this part!
 */
public class MinMaxSatSolver extends SatSolver {

	private AutoConfMode mode;
	private Node node;
	private long timeout;

	public MinMaxSatSolver(Node node, long timeout, AutoConfMode mode) {
		super(node, timeout);
		this.node = node;
		this.timeout = timeout;
		this.mode = mode;

	}

	@Override
	public List<Literal> knownValues() {
		LinkedList<Literal> list = new LinkedList<Literal>();

		FMLShell.getInstance().printDebugMessage(mode.name());

		IProblem problem = null;
		int[] model = null;
		// tries to select the maximum number of features
		if (mode == AutoConfMode.MAX) {
			// problem = new MaxSatDecorator(solver); // seems to be buggy
			problem = new ModelIterator(solver); // new
													// Minimal4InclusionModel(solver);
													// // MaxSAT
			try {
				int[] modelCandidate = null;
				int optim = 0;
				while (problem.isSatisfiable()) {
					modelCandidate = problem.model();

					int o = 0;
					if (modelCandidate != null)
						for (int q : modelCandidate) {
							if (q > 0) {
								o++;
							}
						}

					if (o > optim) {
						optim = o;
						model = modelCandidate;
					}

					// FMShell.getInstance().printDebugMessage
					// ("o: " + o + " optim: " + optim);
				}
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// tries to select the minimum number of features
		else if (mode == AutoConfMode.MIN) {
			problem = new MinOneDecorator(solver);
			try {
				solve(problem);
				model = problem.model();
			} catch (TimeoutException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		// tries to randomly select/deselect features
		else if (mode == AutoConfMode.RANDOM) {

			int max = (int) countSolutions(); // dangerous (since it has side
												// effect on solver)
			initSolver(node, timeout); // reset the solver (needed)
			problem = new ModelIterator(solver);

			Random r = new Random();
			int rd = r.nextInt(max);

			try {
				FMLShell.getInstance().printDebugMessage(
						"rand: " + rd + " count " + max);
				int i = 0;
				while (problem.isSatisfiable()) {
					if (i == rd) {
						model = problem.model();
						break;
					}
					problem.model();
					i++;
				}
				FMLShell.getInstance().printDebugMessage(
						"i: " + i + " model " + model);
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else { // never reached
			FMLShell.getInstance().printTODO();
			return null;
		}

		try {
			problem.isSatisfiable();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int val : model) {
			// if (val > 0) {
			// FMShell.getInstance().printDebugMessage("@@@@val: " + val);
			Object var = intToVar.get(Math.abs(val));
			Literal literal = new Literal(var, val > 0);
			// FMShell.getInstance().printDebugMessage("@@@@lit: " +
			// literal.toString());
			list.add(literal);
			// }
		}

		// catch (TimeoutException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return list;
	}

	public void solve(IProblem problem) throws TimeoutException {
		boolean isSatisfiable = false;

		IOptimizationProblem optproblem = (IOptimizationProblem) problem;

		try {
			while (optproblem.admitABetterSolution()) {
				if (!isSatisfiable) {
					if (optproblem.nonOptimalMeansSatisfiable()) {
						// setExitCode(ExitCode.SATISFIABLE);
						if (optproblem.hasNoObjectiveFunction()) {
							FMLShell.getInstance().printDebugMessage(
									"NO OBJECTIVE FUNCTION");
							return;
						}
						FMLShell.getInstance().printDebugMessage("SATISFIABLE");
					}
					isSatisfiable = true;
					FMLShell.getInstance().printDebugMessage("OPTIMIZING...");
				}
				// FMShell.getInstance().printWarning("Got one! Elapsed wall clock time (in seconds):");
				// FMShell.getInstance().printDebugMessage
				// ("CURRENT_OPTIMUM_VALUE_PREFIX " +
				// optproblem.getObjectiveValue().intValue());

				optproblem.discard();
				// discardCurrentSolution();

			}
			if (isSatisfiable) {
				// setExitCode(ExitCode.OPTIMUM_FOUND);
				// FMLShell.getInstance().printDebugMessage("OPTIMUM_FOUND " +
				// optproblem.getObjectiveValue().intValue());
			} else {
				FMLShell.getInstance().printWarning("UNSATISFIABLE");
				// setExitCode(ExitCode.UNSATISFIABLE);
			}
		} catch (ContradictionException ex) {
			assert isSatisfiable;
			// FMLShell.getInstance().printDebugMessage("OPTIMUM_FOUND (contradiction) "
			// + optproblem.getObjectiveValue().intValue());
			ex.printStackTrace();
			// setExitCode(ExitCode.OPTIMUM_FOUND);
		}
	}

}
