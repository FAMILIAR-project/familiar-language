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
package fr.unice.polytech.modalis.familiar.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fML.Aggregate;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.SetCommand;
import org.xtext.example.mydsl.fML.impl.AggregateImpl;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.AggregatorFM;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher build a new feature model which manages a set
 *         (collection) of feature models precondition: the feature models do
 *         not share any feature
 */

public class AggregatorAnalyzer extends FMLAbstractCommandAnalyzer {

	public static String DEFAULT_FAKEROOT_NAME = "FakeRoot";

	
	// reason

	public AggregatorAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
		

	}

	public AggregatorAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		this(cmd, null, ns, an);

	}

	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public void eval() {
		assert (_command instanceof Aggregate);
		AggregateImpl aggCmd = (AggregateImpl) _command;

		

		// first: collect feature models variables
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		IdentifierExpr idAgg = aggCmd.getSfms(); // necessary a set variable
		// (e.g., fm*)
		if (idAgg != null) {
			SetVariable setFMs = _environment.parseSetCommand(idAgg, null);
			for (Variable fm : setFMs.getVars()) {
				if (fm instanceof RefVariable)
					fm = ((RefVariable) fm).getValueReference();
				if (!(fm instanceof FeatureModelVariable)) {
					FMLShell.getInstance().setError(
							"variable is not of type feature model (fm=" + fm
									+ ")");
					return;
				}
				lfms.add((FeatureModelVariable) fm);
			}
		}

		else { // { .... } notation

			EList<FMCommand> largs = aggCmd.getFms();
			for (FMCommand fm : largs) {
				FeatureModelVariable vfm = _environment.parseFMCommand(fm, null, null);
				lfms.add(vfm);
			}
			assert (lfms.size() == largs.size());
		}
		

		// setting the fake root
		String fakeRootName = getAssigner() != null ? getAssigner()
				: DEFAULT_FAKEROOT_NAME;

		// process constraints
		SetCommand cstCmd = aggCmd.getMapping();
		
		Set<Expression<String>> constraints = new HashSet<Expression<String>>();
		if (cstCmd != null) {
			SetVariable vs = _environment.parseSetCommand(cstCmd,
					null);
			
			Set<Variable> vars = vs.getVars(); 
			for (Variable var : vars) {
				if (! (var instanceof ConstraintVariable)) {
					FMLShell.getInstance().printError("constraint expected in the set but var=" + var);
					return ; 
				}
				else {
					ConstraintVariable cstVar = (ConstraintVariable) var ;
					constraints.add(cstVar.getConstraint());
				}
			}
			if (constraints.size() == 0)
				FMLShell.getInstance().printWarning("no constraints!");
		} else {
			FMLShell.getInstance().printWarning("no mapping");
		}
		
		

		
		AggregatorFM aggregator = new AggregatorFM() ;
		FeatureModelVariable resultingFM = aggregator.build(lfms, constraints,	fakeRootName);
		resultingFM.setIdentifier(getAssigner()); 

		setVariable(resultingFM);

	}

	

}
