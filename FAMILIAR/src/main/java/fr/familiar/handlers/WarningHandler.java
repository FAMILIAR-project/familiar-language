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
package fr.familiar.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class WarningHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("warning mode");
		ModeStaticVars.setNormal(false);
		ModeStaticVars.setStrict(false);
		ModeStaticVars.setWarning(true);

		// ICommandService commandService = (ICommandService)
		// PlatformUI.getWorkbench().getService(ICommandService.class);
		// Command command = commandService.getCommand("FAMILIAR.mode.strict");
		// command.setEnabled(null);
		// // Command command1 =
		// commandService.getCommand("FAMILIAR.mode.normal");
		// // command1.setEnabled(null);
		// // Command command2 =
		// commandService.getCommand("FAMILIAR.mode.warning");
		// // command2.setEnabled(null);
		// PlatformUI.getWorkbench().getDisplay().getActiveShell().layout();
		// IWorkbenchPage page;
		// IViewPart viewPart;
		// page =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		// System.out.println("page " + page);
		// System.out.println("vreference " +
		// page.findViewReference("org.eclipse.ui.console.ConsoleView")) ;
		//
		// viewPart =
		// page.findViewReference("org.eclipse.ui.navigator.ProjectExplorer").getView(true);
		// page.activate(viewPart);
		// viewPart =
		// page.findViewReference("org.eclipse.ui.console.ConsoleView").getView(true);
		// page.activate(viewPart);
		return null;
	}

	@Override
	public boolean isEnabled() {
		return !ModeStaticVars.isWarning();
	}

	@Override
	public boolean isHandled() {
		return !ModeStaticVars.isWarning();
	}

}
