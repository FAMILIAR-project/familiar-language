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

package fr.familiar.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.AggregateMerge;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.HierarchyStrategy;
import org.xtext.example.mydsl.fml.LFMArgs;
import org.xtext.example.mydsl.fml.MergeMode;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.Mode;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author macher
 *
 */
public class MergeAggregateAnalyzer extends MergeAnalyzer {
	
	
	
	
	private static Logger _LOGGER = Logger.getLogger(MergeAggregateAnalyzer.class);

	private HierarchyMergerStrategy _hierarchyMergerStrategy = FMLMergerWithConstraints._DEFAULT_HIERARCHY_STRATEGY ; 
	
	
	private List<FeatureModelVariable> _lfmvs = new ArrayList<FeatureModelVariable>();
	
	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public MergeAggregateAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public MergeAggregateAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
		// TODO Auto-generated constructor stub
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
		_LOGGER.debug("starting the effective aggregate merging");

		if (_mode != Mode.StrictUnion && _mode != Mode.Intersection) {
			FMLShell.getInstance().printTODO("Unsupported mode: " + _mode + " for aggregate merge ");
			return ; 
		}

		
		// TODO		
		FeatureModelVariable fmv = null ; 
		FMLMergerWithConstraints merger = new FMLMergerWithConstraints(_lfmvs) ;
		merger.setHierarchyStrategy(_hierarchyMergerStrategy);
		if (_mode == Mode.StrictUnion) 
			fmv = merger.union() ;
		else if (_mode == Mode.Intersection)
			fmv = merger.intersection() ; 

		_LOGGER.debug("size fmMerged.* = " + fmv.features().size());
		setVariable(fmv);

	}

	@Override
	protected void compile() {
		assert (_command instanceof AggregateMerge);
		AggregateMerge mergeCmd = (AggregateMerge) _command;

		_LOGGER.debug(
				"merge aggregate " + mergeCmd.getMode() + " " + mergeCmd.getLfms());
		
		
		HierarchyStrategy hierarchyStrategy = mergeCmd.getHierarchyStrategy() ; 
		boolean hasHierarchyStrategy = mergeCmd.isHierarchySpecified() ; 
		if (hasHierarchyStrategy) {
			if (hierarchyStrategy == HierarchyStrategy.BASIC) {
				_hierarchyMergerStrategy = HierarchyMergerStrategy.BASIC ; 
			}
			else if (hierarchyStrategy == HierarchyStrategy.FLAT) {
				_hierarchyMergerStrategy = HierarchyMergerStrategy.FLAT ; 
			}
			else if (hierarchyStrategy == HierarchyStrategy.MST) {
				_hierarchyMergerStrategy = HierarchyMergerStrategy.MST ; 
			}
						
		}

		/*******
		 * 1. populate fmsToMerge: type checking + retrieve the FMs in the set
		 ***********/
		List<FeatureModelVariable> fmsToMerge = new ArrayList<FeatureModelVariable>();
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
				fmsToMerge.add((FeatureModelVariable) v.copy());
			} else if (v instanceof SetVariable) {

				SetVariable sw = (SetVariable) v;
				Set<Variable> vars = sw.getVars();
				for (Variable vr : vars) {

					if (vr instanceof FeatureModelVariable) { // TODO:
						// configuration (asFM)
						_LOGGER.debug(
								"feature model: " + vr.getIdentifier()
										+ " considered to be merged");
						fmsToMerge.add((FeatureModelVariable) vr.copy());
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
		assert (fmsToMerge.size() > 0);
		/******* end (the list fmsToMerge is populated) ***********/
		for (FeatureModelVariable fm : fmsToMerge)
			_lfmvs.add((FeatureModelVariable) fm.copy());

		if (mergeCmd.getMode() == MergeMode.SUNION || mergeCmd.getMode() == MergeMode.UNION)
			_mode = Mode.StrictUnion;
		else if (mergeCmd.getMode() == MergeMode.INTER)
			_mode = Mode.Intersection;
		else {
			FMLShell.getInstance().setError("Unknown merge mode");
			return;
		}
	}
	
	



}
