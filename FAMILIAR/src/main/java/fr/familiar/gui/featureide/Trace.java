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

package fr.familiar.gui.featureide;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class Trace extends ViewPart {

	public static final String ID = "de.ovgu.featureide.fm.ui.Trace";
	private TableViewer tableViewer;
	private Table table;

	private static ArrayList<String> commands = new ArrayList<String>();
	private static ArrayList<TraceObserver> observers = new ArrayList<TraceObserver>();

	public Trace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.SINGLE);
		table = tableViewer.getTable();

		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setWidth(100);
		tc.setText("Commands");
		table.setHeaderVisible(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param command
	 *            the command to consider (simply a String representation of an
	 *            FML command)
	 * @param console
	 *            determines whether or not the command comes from the console
	 *            and the interactive mode (instead of being a graphical
	 *            interaction with FeatureIDE editor)
	 */
	public void addCommand(String command, boolean console) {
		TableItem ti = new TableItem(table, SWT.NONE);
		ti.setText(command);
		if (console)
			ti.setBackground(Display.getDefault()
					.getSystemColor(SWT.COLOR_GRAY));
		commands.add(command);

	}

	/**
	 * 
	 */
	private void prevent(String commande) {
		System.out.println("DEBUG:: " + commande + " observers=" + observers);
		for (TraceObserver to : observers) {
			to.warn(commande);
		}

	}

	public static ArrayList<String> getCommandList() {
		return commands;
	}

	public static void register(TraceObserver to) {
		observers.add(to);
	}

	/**
	 * @param commande
	 */
	public void applyFMLCmd(String commande) {
		prevent(commande);
	}

}
