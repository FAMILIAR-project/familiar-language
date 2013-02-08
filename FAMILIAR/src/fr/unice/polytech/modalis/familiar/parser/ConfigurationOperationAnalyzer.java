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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;
import org.xtext.example.mydsl.fML.AutoConfMode;
import org.xtext.example.mydsl.fML.AutoConfiguration;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.CompleteConfiguration;
import org.xtext.example.mydsl.fML.ConfigurationCmd;
import org.xtext.example.mydsl.fML.ConfigurationCommand;
import org.xtext.example.mydsl.fML.CreateConfiguration;
import org.xtext.example.mydsl.fML.DeselectedConfiguration;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FeatureExpression;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.SelectedConfiguration;
import org.xtext.example.mydsl.fML.SelectionFeature;
import org.xtext.example.mydsl.fML.StringExpr;
import org.xtext.example.mydsl.fML.UnselectedConfiguration;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.StringVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

public class ConfigurationOperationAnalyzer extends FMLAbstractCommandAnalyzer {

	public ConfigurationOperationAnalyzer(Command cmd, String var,
			NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	@Override
	public RType getType() {
		return RType.CONFIGURATION;
	}

	@Override
	public void eval() {

		assert (getCmd() instanceof ConfigurationCmd);
		ConfigurationCmd strCmd = (ConfigurationCmd) getCmd();

		Variable vari = null;
		ConfigurationVariable cw = null;
		if (strCmd instanceof CreateConfiguration) {
			vari = parseConfiguration(strCmd);
			if (vari instanceof ConfigurationVariable)
				cw = (ConfigurationVariable) vari;
			else {
				FMLShell.getInstance()
						.setError(
								"Unexpected error during the construction of the configuration (configuration variable expected but "
										+ vari);
				return;
			}
		} else if (strCmd instanceof SelectionFeature) {

			SelectionFeature selector = (SelectionFeature) strCmd;
			ConfigurationCommand configCmd = selector.getConfig();

			String oper = selector.getOp(); // select, deselect or unselected

			boolean bv = true;
			cw = _environment.parseConfigurationCommand(configCmd, null);
	

			EList<FeatureExpression> fts = selector.getFts();
			for (FeatureExpression fexpr : fts) {
				EObject ft = fexpr.getFt();
				if (ft instanceof IdentifierExpr) {
					IdentifierExpr ie = (IdentifierExpr) ft;
					String ftName = ie.getVal() ;
										
					if (!cw.applySelection(ftName, oper)) {
						FMLShell.getInstance().printDebugMessage(
								"Unable to apply the " + oper + " with " + ftName);
						
						// may be a variable 						
						try {
							Variable v = _environment.getVariable(ie.getVal());
							if (v instanceof RefVariable) {
								RefVariable rv = (RefVariable)v;
								v = rv.getValueReference();
							}
							if (v instanceof StringVariable) {
								StringVariable sv = (StringVariable)v;
								String strFT = sv.getSpecificValue();
								if (!cw.applySelection(strFT, oper)) {
									FMLShell.getInstance().printDebugMessage(
											"Unable to apply the " + oper + " with " + strFT);
									bv = false;
								}
							} else if (v instanceof FeatureVariable) {
								FeatureVariable fv = (FeatureVariable) v;
								String strFT = fv.getFtName() ;
								if (!cw.applySelection(strFT, oper)) {
									FMLShell.getInstance().printDebugMessage(
											"Unable to apply the " + oper + " with " + strFT);
									bv = false;
								}
								
							} else {
								FMLShell.getInstance().printDebugMessage(
										"Unable to apply the " + oper + " with variable : "+v);
								bv = false;
							}
						} catch (VariableNotExistingException e) {
							FMLShell.getInstance().printDebugMessage(
									"Unable to apply the " + oper + " : variable is not existing : "+ie.getVal());
							bv = false;
						} catch (VariableAmbigousConflictException e) {
							// TODO : treat this case in taking the feature from the FM ? 
							FMLShell.getInstance().printDebugMessage(
									"Unable to apply the " + oper + " : variable is ambigous : "+ie.getVal());
							bv = false;
						}
						
						bv = false;
					}
					
										
				} else if (ft instanceof StringExpr) {
					StringExpr st = (StringExpr)ft;
					String strFT = st.getVal();
					if (!cw.applySelection(strFT, oper)) {
						FMLShell.getInstance().printDebugMessage(
								"Unable to apply the " + oper + " with " + strFT);
						bv = false;
					}
				}
				
			}
			vari = new BooleanVariable(getAssigner(), bv);

		}

		else if (strCmd instanceof SelectedConfiguration) {

			SelectedConfiguration sconfig = (SelectedConfiguration) strCmd;
			cw = _environment.parseConfigurationCommand(sconfig.getConfig(), null);
			vari = _toSetVariable(cw.getSelected(), cw.getFmv()); 
		}

		else if (strCmd instanceof DeselectedConfiguration) {

			DeselectedConfiguration sconfig = (DeselectedConfiguration) strCmd;
			cw = _environment.parseConfigurationCommand(sconfig.getConfig(), null);
			vari = _toSetVariable(cw.getDeselected(), cw.getFmv());
		}

		else if (strCmd instanceof UnselectedConfiguration) {

			UnselectedConfiguration sconfig = (UnselectedConfiguration) strCmd;
			cw = _environment.parseConfigurationCommand(sconfig.getConfig(), null);
			vari = _toSetVariable(cw.getUnselected(), cw.getFmv());

		}

		else if (strCmd instanceof CompleteConfiguration) {
			CompleteConfiguration compl = (CompleteConfiguration) strCmd;
			cw = _environment.parseConfigurationCommand(compl.getConfig(), null);
			boolean bsw = cw.isComplete();
			vari = new BooleanVariable(getAssigner(), bsw);

		}

		else if (strCmd instanceof AutoConfiguration) {
			assert (strCmd instanceof AutoConfiguration);
			AutoConfiguration autoconf = (AutoConfiguration) strCmd;
			cw = _environment.parseConfigurationCommand(autoconf.getConfig(), null); // just
																			// for
																			// displaying
			boolean b = parseAuto(strCmd);
			vari = new BooleanVariable(getAssigner(), b);

		}

		else {
			FMLShell.getInstance().printTODO(
					"configuration operation not recognized");
			return;
		}

		setVariable(vari);

		final ConfigurationVariable cv = cw;

		UIJob analyzingJob = null;
		/*************** gdisplay *************/

		if (!FMLShell.getInstance().getPreference()
				.isConfigurationGraphicalDisplay())
			return;

		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())

			/*
			 * analyzingJob = new
			 * UIJob(" Opening configuration editor FeatureIDE"){
			 * 
			 * @Override public IStatus runInUIThread(IProgressMonitor monitor)
			 * { cv.gdisplay() ; return Status.OK_STATUS;
			 * 
			 * } }; analyzingJob.setPriority(Job.DECORATE);
			 * analyzingJob.schedule();
			 * 
			 * if (true) return ;
			 */
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (cv == null)
						FMLShell.getInstance().printDebugMessage(
								"Unable to display after renaming (null)");
					cv.gdisplay();

				}
			});
	}

	private Variable _toSetVariable(Set<String> ftures, FeatureModelVariable fmv) {
		Set<Variable> vars = new HashSet<Variable>();
		for (String s : ftures) {
			FeatureVariable ft = new FeatureVariable(null, s, fmv); // anonymous
																			// variable
			vars.add(ft);
		}

		return new SetVariable(vars, null);
	}

	private boolean parseAuto(ConfigurationCmd strCmd) {

		assert (strCmd instanceof AutoConfiguration);
		AutoConfiguration autoconf = (AutoConfiguration) strCmd;

		ConfigurationVariable cw = _environment.parseConfigurationCommand(
				autoconf.getConfig(), null);
		AutoConfMode mode = autoconf.getMode();
		if (mode == null)
			mode = AutoConfMode.RANDOM;

		FMLShell.getInstance().printDebugMessage("autoselection...");
		boolean b = true;
		try {
			cw.autoselect(mode);
		} catch (Exception e) {
			b = false;
		}

		return b;

	}

	private Variable parseConfiguration(ConfigurationCmd strCmd) {
		CreateConfiguration configCmd = (CreateConfiguration) strCmd;
		FMCommand fmCmd = configCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		return ConfigurationVariableFactory.INSTANCE.mkDefault(fmv, getAssigner());
	}

}
