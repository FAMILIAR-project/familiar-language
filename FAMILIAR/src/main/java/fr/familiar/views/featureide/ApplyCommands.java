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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import fr.familiar.gui.featureide.Trace;


public class ApplyCommands implements IViewActionDelegate {

	@Override
	public void run(IAction action) {
		System.out.println("**** Applying commands ****");
		for (String s : Trace.getCommandList()) {
			System.out.println(s);
			MessageBox msgb = new MessageBox(Display.getDefault()
					.getActiveShell(), SWT.YES | SWT.NO | SWT.ICON_QUESTION);
			msgb.setText("Mode pas a pas");
			msgb.setMessage(s);
			int rep = msgb.open();
			if (rep == SWT.NO)
				return;
		}

		// TODO Mathieu

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

}
