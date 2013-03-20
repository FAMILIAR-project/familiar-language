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
package fr.unice.polytech.modalis.familiar.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class NormalHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("normal mode");
		ModeStaticVars.setNormal(true);
		ModeStaticVars.setStrict(false);
		ModeStaticVars.setWarning(false);

		// ICommandService commandService = (ICommandService)
		// PlatformUI.getWorkbench().getService(ICommandService.class);
		// Command command = commandService.getCommand("FAMILIAR.mode.normal");
		// command.setEnabled(null);
		// // Command command1 =
		// commandService.getCommand("FAMILIAR.mode.strict");
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
		return !ModeStaticVars.isNormal();
	}

	@Override
	public boolean isHandled() {
		return !ModeStaticVars.isNormal();
	}

}
