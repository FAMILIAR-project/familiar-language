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
package fr.unice.polytech.modalis.familiar.views.featureide;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.familiar.wizard.MyWizard;

public class VariableView extends ViewPart {

	public static final String ID = "FAMILIAR.varView";
	private TableViewer tableViewer;
	private Table table;
	public static IWorkbenchPage iw;

	private org.eclipse.swt.widgets.Menu menu;
	private MenuManager menuMgr;
	private Action action;

	public IWorkbenchPage getIW() {
		return getSite().getPage();
	}

	public VariableView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {

		iw = getIW();

		tableViewer = new TableViewer(parent, SWT.SINGLE);
		table = tableViewer.getTable();

		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setWidth(100);
		tc.setText("Name");

		TableColumn tc1 = new TableColumn(table, SWT.NONE);
		tc1.setWidth(100);
		tc1.setText("Type");

		TableColumn tc2 = new TableColumn(table, SWT.NONE);
		tc2.setWidth(100);
		tc2.setText("Value");

		tc1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// sort column 1
				TableItem[] items = table.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(1);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(1);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});

		tc.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// sort column 1
				TableItem[] items = table.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(0);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(0);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});

		// tc2.addListener(SWT.Selection, new Listener() {
		// public void handleEvent(Event e) {
		// // sort column 1
		// TableItem[] items = table.getItems();
		// Collator collator = Collator.getInstance(Locale.getDefault());
		// for (int i = 1; i < items.length; i++) {
		// String value1 = items[i].getText(2);
		// for (int j = 0; j < i; j++) {
		// String value2 = items[j].getText(2);
		// if (collator.compare(value1, value2) < 0) {
		// String[] values = { items[i].getText(0),
		// items[i].getText(1) };
		// items[i].dispose();
		// TableItem item = new TableItem(table, SWT.NONE, j);
		// item.setText(values);
		// items = table.getItems();
		// break;
		// }
		// }
		// }
		// }
		// });

		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				TableItem w = tableViewer.getTable().getItem(
						new Point(e.x, e.y));
				Variable v = VariableTableItem.getVariable(w);

				if (v instanceof FeatureModelVariable) {
					((FeatureModelVariable) v).gdisplay();
				}

				if (v instanceof ConfigurationVariable) {
					((ConfigurationVariable) v).gdisplay();
				}

			}
		});

		table.setHeaderVisible(true);

		// ************* creation de pop up menu

		action = new Action("Serialize") {
			public void run() {
				TableItem ti = table.getSelection()[0];
				Shell shell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
				MyWizard mw = new MyWizard();
				mw.setVariable(VariableTableItem.getVariable(ti));
				WizardDialog wd = new WizardDialog(shell, mw);
				wd.create();
				wd.open();
			}
		};

		MenuManager menuMgr = new MenuManager();// "#PopupMenu"
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager manager) {

				manager.add(action);

			}
		});
		menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, tableViewer);
		action.setEnabled(false);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void addItem(final Variable var) {
		getDisplay().syncExec(new Runnable() {
			public void run() {
				VariableTableItem.registerVariable(var, table, SWT.NONE);
				if (!action.isEnabled())
					action.setEnabled(true);
			}
		});

	}

	public void listen() {
		getDisplay().syncExec(new Runnable() {
			public void run() {
				table.addListener(SWT.MouseDoubleClick, new Listener() {
					public void handleEvent(Event e) {
						TableItem w = tableViewer.getTable().getItem(
								new Point(e.x, e.y));
						FMLShell.getInstance()
								.printDebugMessage("click w=" + w);
					}
				});
			}
		});
	}

	private Display getDisplay() {
		return getSite().getWorkbenchWindow().getWorkbench().getDisplay();
	}

	public void clear() {
		getDisplay().syncExec(new Runnable() {
			public void run() {
				table.removeAll();
			}
		});
	}

}
