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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;

/**
 * @author mathieuacher
 * 
 */
public class VariableTableItem {

	private static Map<TableItem, Variable> map = new HashMap<TableItem, Variable>();
	private static Map<Variable, TableItem> map2 = new HashMap<Variable, TableItem>();
	private static TableItem lastTableItem;

	/**
	 * @param var
	 * @param parent
	 * @param style
	 */
	public static void registerVariable(final Variable var, Table parent,
			int style) {
		if (lastTableItem != null) {
			// lastTableItem.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		}
		// TableItem item = new TableItem(parent, style);
		lastTableItem = new TableItem(parent, style);
		lastTableItem.setText(new String[] {
				VariableIdentifier.completeName(var.getVid()), var.getType(),
				var.getValue() });
		map.put(lastTableItem, var);
		map2.put(var, lastTableItem);
		// lastTableItem.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
	}

	/**
	 * @return the item
	 */
	public static Variable getVariable(TableItem item) {
		return map.get(item);
	}

	public static void bold(final Variable var) {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				map2.get(var).setBackground(
						Display.getDefault().getSystemColor(SWT.COLOR_GRAY));

			}
		});
	}

}
