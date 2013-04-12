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
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fML.BDDBackend;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.LFMArgs;
import org.xtext.example.mydsl.fML.Merge;
import org.xtext.example.mydsl.fML.MergeMode;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.FDOverApproximationStrategy;
import fr.unice.polytech.modalis.familiar.operations.FMLMerger;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDDSPLOT;
import fr.unice.polytech.modalis.familiar.operations.Mode;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

/**
 * @author mathieuacher merge operators mode: sunion, diff, intersection, etc.
 *         see: examples/testing/merge/intersection.fmm
 *         examples/testing/merge/sunion.fmm examples/testing/merge/diff.fmm"
 *         see FMLMergeAnalyser in the experimental branch (almost freezed)
 * */
public class MergeAnalyzer extends FMLAbstractCommandAnalyzer {
	
	
	private static Logger _LOGGER = Logger.getLogger(MergeAnalyzer.class);
	
	private static final BDDBackend DEFAULT_MERGER_BACKEND = BDDBackend.BDD_BASIC ;


	/**
	 * list of feature models to merge
	 */
	protected List<FeatureModelVariable> _lfms = new ArrayList<FeatureModelVariable>();

	/**
	 * merge mode
	 */
	protected Mode _mode = null;


	private BDDBackend _backend ;

	private boolean _lazy = false ;
	

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public MergeAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public MergeAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public void eval() {

		// REFACTORING NEEDED

		/***
		 * 1. interpret the Xtext stuff set _lfms and _mode + checking
		 * ***/
		compile();

		/****
		 * 2. now we merge, in the Boolean space, the different FMs in order to
		 * compute the set of configurations expected
		 *****/
		_LOGGER.debug(
				"starting the effective merging");
		
		if (_lazy) {
			
			_LOGGER.debug("LAZY computation (only formula) when merging");
			FMLMergerBDD merger = (FMLMergerBDD) mkMerger () ;  					
			FeatureModelVariable fmv = merger.mergeFMs(_mode, _lazy);
			fmv.setIdentifier(_assigner) ;	
			setVariable(fmv);
			return ; 
			
		}

		if (_mode == Mode.Diff) {
			assert (_lfms.size() == 2);
			FeatureModelVariable fmv1 = _lfms.get(0);
			FeatureModelVariable fmv2 = _lfms.get(1);
			FeatureModelVariable fmvR = fmv1.mergeDiff(fmv2);
			setVariable(fmvR);
			return;
		}

		if (_mode == Mode.StrictUnion || _mode == Mode.Intersection) {
	
			_LOGGER.debug("#fms=" + _lfms.size());
			FMLMerger merger = mkMerger () ;  					
			FeatureModelVariable fmv = null ; 
			if (_mode == Mode.StrictUnion)
				fmv = merger.union() ;
			else if (_mode == Mode.Intersection) 
				fmv = merger.intersection() ;
			
			
			fmv.setIdentifier(_assigner) ;	
			setVariable(fmv);

		}

	}

	private FMLMerger mkMerger() {
		if (_backend == BDDBackend.BDD_BASIC) 
			return new FMLMergerBDD (_lfms, FDOverApproximationStrategy.SYNCHRONIZED_FLA) ;
		else if (_backend == BDDBackend.BDD_SPLOT)
			return new FMLMergerBDDSPLOT (_lfms, FDOverApproximationStrategy.SYNCHRONIZED_FLA) ;
		else {
			FMLShell.getInstance().printError("Unknown merger backend=" + _backend);
			return null ; 
		}
	}

	protected void compile() {

		assert (_command instanceof Merge);
		Merge mergeCmd = (Merge) _command;


		_LOGGER.debug(
				"merge " +  mergeCmd.getBackend() + " " + mergeCmd.getMode() + " " + mergeCmd.getLfms());
		
		_backend = mergeCmd.getBackend() ;
		if (_backend == BDDBackend.BDD_DEFAULT)
			_backend = DEFAULT_MERGER_BACKEND ; 
		
		_lazy  = mergeCmd.isLazy() ; 
	

		/*******
		 * 1. populate fmsToMerge: type checking + retrieve the FMs in the set
		 ***********/
		EList<FMCommand> lFMS = mergeCmd.getLfms();

		if (lFMS == null || lFMS.size() == 0) {
			_LOGGER.debug("LFMArgs");
			LFMArgs lFMArgs = mergeCmd.getFms();
			lFMS = lFMArgs.getLfms();
		} else
			_LOGGER.debug("LFMS");
		assert (lFMS != null);

		for (FMCommand fmCommand : lFMS) {
			Variable v = _environment.parse((Command) fmCommand, null);
			if (v instanceof RefVariable)
				v = ((RefVariable) v).getValueReference();
			if (v instanceof FeatureModelVariable) {
				_LOGGER.debug(
						"feature model: " + v.getIdentifier()
								+ " considered to be merged");
				_lfms.add((FeatureModelVariable) v.copy());
			} else if (v instanceof SetVariable) {

				SetVariable sw = (SetVariable) v;
				Set<Variable> vars = sw.getVars();
				for (Variable vr : vars) {

					if (vr instanceof FeatureModelVariable) { // TODO:
						// configuration (asFM)
						_LOGGER.debug(
								"feature model: " + vr.getIdentifier()
										+ " considered to be merged");
						_lfms.add((FeatureModelVariable) vr.copy());
					} else {
						FMLShell.getInstance()
								.setError(
										"one of the variable "
												+ vr.getIdentifier()
												+ " of the set is not a feature model: "
												+ vr.getType());
						return;
					}

				}
				// end of set variable
			} else {
				FMLShell.getInstance().setError(
						"variable " + v.getIdentifier() + " is not a set: "
								+ v.getType());
				return;
			}

		} // type checking is finished. We have now a list of FM variables
		assert (_lfms.size() > 0);
		/******* end (the list fmsToMerge is populated) ***********/

		if (mergeCmd.getMode() == MergeMode.SUNION || mergeCmd.getMode() == MergeMode.UNION)
			_mode = Mode.StrictUnion;
		else if (mergeCmd.getMode() == MergeMode.INTER)
			_mode = Mode.Intersection;
		else if (mergeCmd.getMode() == MergeMode.DIFF) {
			// checking
			if (_lfms.size() != 2) {
				FMLShell.getInstance().setError(
						"merge diff: two feature models are required (instead of "
								+ _lfms.size() + ")");
				return;
			}
			_mode = Mode.Diff;
		} else {
			FMLShell.getInstance().setError("Unknown merge mode");
			return;
		}

	}



}
