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

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fr.familiar.handlers.ModeStaticVars;
import fr.familiar.interpreter.FMLShell;

public class FamiliarPreferencesPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private String path = "default path";
	private Button normalMode;
	private Button strictMode;
	private Button warningMode;
	private int val = 1;

	private Button normalGraphicalMode;
	private int valGraphical = 1;

	public FamiliarPreferencesPage() {
	}

	public FamiliarPreferencesPage(String title) {
		super(title);
	}

	public FamiliarPreferencesPage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {

		Label pathLabel = new Label(parent, SWT.NONE);
		// Text pathText = new Text(parent, SWT.BORDER);
		final DirectoryDialog directoryDialog = new DirectoryDialog(
				this.getShell());
		Button browseButton = new Button(parent, SWT.PUSH);

		pathLabel.setText("Path of .fml files");
		browseButton.setText("Browse");
		// pathText.setEditable(false);
		// pathText.setSize(200, 25);
		browseButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				path = directoryDialog.open();
				System.out.println(path);
				if (path == null)
					path = Activator.getDefault().getPreferenceStore()
							.getString(Activator.PATH); // TODO foudil

				// *********** passer le parametre au FMShell
				// TODO mathieu

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		Group graphicalGroup = new Group(parent, SWT.None);
		graphicalGroup.setText("Graphical display");
		graphicalGroup.setLayout(new FillLayout());
		normalGraphicalMode = new Button(graphicalGroup, SWT.CHECK);
		normalGraphicalMode.setText("Activated");
		// valGraphical =
		// Activator.getDefault().getPreferenceStore().getInt(Activator.NORMAL);

		// normalGraphicalMode.setSelection(valGraphical == 1);

		normalGraphicalMode.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FMLShell shell = FMLShell.getInstance();
				if (shell == null)
					return;
				if (!ModeStaticVars.isGraphicalNormal()) {
					ModeStaticVars.setGraphicalNormal(false);
					valGraphical = 1;
					shell.getPreference().setGraphicalDisplay(false);
				} else {
					ModeStaticVars.setGraphicalNormal(true);
					valGraphical = 2;
					shell.getPreference().setGraphicalDisplay(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		Group modeGroup = new Group(parent, SWT.None);
		normalMode = new Button(modeGroup, SWT.RADIO);
		strictMode = new Button(modeGroup, SWT.RADIO);
		warningMode = new Button(modeGroup, SWT.RADIO);
		modeGroup.setText("Error Mode");
		modeGroup.setLayout(new FillLayout());
		normalMode.setText("Normal");
		strictMode.setText("Strict");
		warningMode.setText("Warning");
		val = Activator.getDefault().getPreferenceStore()
				.getInt(Activator.NORMAL);

		System.out.println(val);
		// normalMode.setSelection(
		// Activator.getDefault().getPreferenceStore().getDefaultBoolean(Activator.NORMAL));
		// strictMode.setSelection(
		// Activator.getDefault().getPreferenceStore().getDefaultBoolean(Activator.STRICT));
		// warningMode.setSelection(
		// Activator.getDefault().getPreferenceStore().getDefaultBoolean(Activator.WARNING));
		if (val == 1)
			normalMode.setSelection(true);
		else if (val == 2)
			strictMode.setSelection(true);
		else
			warningMode.setSelection(true);

		normalMode.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!ModeStaticVars.isNormal()) {
					ModeStaticVars.setNormal(true);
					ModeStaticVars.setStrict(false);
					ModeStaticVars.setWarning(false);
					val = 1;
					System.out.println(val);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		strictMode.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!ModeStaticVars.isStrict()) {
					ModeStaticVars.setNormal(false);
					ModeStaticVars.setStrict(true);
					ModeStaticVars.setWarning(false);
					val = 2;
					System.out.println(val);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		warningMode.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!ModeStaticVars.isWarning()) {
					ModeStaticVars.setNormal(false);
					ModeStaticVars.setStrict(false);
					ModeStaticVars.setWarning(true);
					val = 3;
					System.out.println(val);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		Button applying = new Button(parent, SWT.CHECK);
		applying.setText("Applying graphical interactions");
		// FamiliarPlugin.getDefault().getPreferenceStore().getBoolean(FamiliarPlugin.APPLYING);
		applying.setSelection(Activator.getDefault().getPreferenceStore()
				.getBoolean(Activator.APPLYING));
		applying.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ModeStaticVars.setApplying(!ModeStaticVars.isApplying());
				// TODO mathieu

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		return null;
	}

	public boolean performOk() {
		Activator.getDefault().getPreferenceStore()
				.setValue(Activator.NORMAL, val);
		Activator.getDefault().getPreferenceStore()
				.setValue(Activator.STRICT, ModeStaticVars.isStrict());
		Activator.getDefault().getPreferenceStore()
				.setValue(Activator.WARNING, ModeStaticVars.isWarning());
		Activator.getDefault().getPreferenceStore()
				.setValue(Activator.APPLYING, ModeStaticVars.isApplying());
		Activator.getDefault().getPreferenceStore()
				.setValue(Activator.PATH, path);
		return true;
	}

}
