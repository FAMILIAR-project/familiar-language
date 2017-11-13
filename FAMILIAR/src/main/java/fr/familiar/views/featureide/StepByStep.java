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
package fr.familiar.views.featureide;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;

import fr.familiar.interpreter.FMLShell;

@Deprecated
public class StepByStep extends FamiliarRun {

	@Override
	public void launch(ISelection selection, String mode) {
		IFile file = null;

		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).size() == 1) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item instanceof IFile) {
				file = (IFile) item;
				execute(file);
				FMLShell.getInstance().setStepByStep(false);
			}

		}

	}

	@Override
	public void execute(IFile file) {
		FMLShell.executeEclipseScript(file, true);
		FMLShell.getInstance().printDebugMessage("Updating variable view...");
		updateVariableView();

	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub

	}

}
