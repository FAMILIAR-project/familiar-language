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
package fr.familiar.views.featureide;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import fr.familiar.gui.featureide.FM_Familiar_FeatureIDE;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

@Deprecated
public class FamiliarRun implements ILaunchShortcut {

	private static VariableView _VARIABLEVIEW = null;

	// private static FMZestRendererView _ZEST_RENDERER_VIEW = null;

	List<Variable> varList = new ArrayList<Variable>();

	@Override
	public void launch(ISelection selection, String mode) {
		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item instanceof IFile) {
				IFile fil = (IFile) item;
				execute(fil);

			}
		}

	}

	protected void execute(IFile file) {
		FMLShell.executeEclipseScript(file, false); // not step by step
		FMLShell.getInstance().printDebugMessage("Updating variable view...");

		if (FMLShell.getInstance().getPreference().isVariableViewActivated())
			updateVariableView();

	}

	@Override
	public void launch(IEditorPart editor, String mode) {

	}

	public void updateVariableView() {

		IWorkbenchPage page = null;
		IViewPart viewPart = null;
		VariableView variableView = null;

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(VariableView.ID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (page == null) {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage();
			FMLShell.getInstance().printDebugMessage("page env=" + page);
			FMLShell.getInstance().printDebugMessage(
					"environment view="
							+ page.findViewReference(VariableView.ID));
			viewPart = page.findViewReference(VariableView.ID).getView(true);
		}

		if (viewPart != null) {
			if (viewPart instanceof VariableView) {
				variableView = (VariableView) viewPart;
				varList = FMLShell.getInstance().getCurrentEnv().getVariables();
				variableView.clear();
				for (Variable v : varList) {
					FMLShell.getInstance().printDebugMessage(
							"Adding an element=" + v);
					variableView.addItem(v);
					FMLShell.getInstance().printDebugMessage(
							"End of the adding");
				}
				_VARIABLEVIEW = variableView;
				FMLShell.getInstance().printDebugMessage(
						"varView (running) " + variableView);
			}
		}
	}

	public static VariableView getVariableView() {
		if (_VARIABLEVIEW == null) {
			IWorkbenchPage wpage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IViewPart viewPart = wpage.findViewReference(VariableView.ID)
					.getView(true);
			if (viewPart != null)
				if (viewPart instanceof VariableView)
					_VARIABLEVIEW = (VariableView) viewPart;

		}
		return _VARIABLEVIEW;

	}

	/*
	 * public static FMZestRendererView getZestRendererView() { if
	 * (_ZEST_RENDERER_VIEW == null) { IWorkbenchPage wpage =
	 * PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	 * IViewPart viewPart =
	 * wpage.findViewReference(FMZestRendererView.ID).getView(true); if(viewPart
	 * != null) if(viewPart instanceof FMZestRendererView) _ZEST_RENDERER_VIEW =
	 * (FMZestRendererView) viewPart ;
	 * 
	 * } return _ZEST_RENDERER_VIEW ;
	 * 
	 * }
	 */

	public List<FeatureModelVariable> getFeatureModelList(List<Variable> list) {
		List<FeatureModelVariable> featureList = new ArrayList<FeatureModelVariable>();
		for (Variable var : list) {
			if (var instanceof FeatureModelVariable)
				featureList.add((FeatureModelVariable) var);
		}
		return featureList;
	}

	public List<ConfigurationVariable> getConfigurations(List<Variable> list) {
		List<ConfigurationVariable> configurationList = new ArrayList<ConfigurationVariable>();
		for (Variable var : list) {
			if (var instanceof ConfigurationVariable)
				configurationList.add((ConfigurationVariable) var);
		}
		return configurationList;
	}

	public List<ConfigurationVariable> getConfigurationListOf(
			FeatureModelVariable feature, List<Variable> varList) {
		List<ConfigurationVariable> configurationList = new ArrayList<ConfigurationVariable>();
		ConfigurationVariable conf = null;
		for (Variable var : varList) {
			if (var instanceof ConfigurationVariable) {
				conf = (ConfigurationVariable) var;
				if (conf.getFmv().equals(feature)) {
					configurationList.add(conf);
				}
			}
		}

		return configurationList;
	}

	/*
	 * 
	 * @param fmw a feature model variable register a FM variable so that there
	 * is a corresponding FM in FeatureIDE formalism
	 */
	public void register(FeatureModelVariable fmw) {
		FM_Familiar_FeatureIDE.register(fmw);
	}

}
