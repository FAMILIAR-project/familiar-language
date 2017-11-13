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
package fr.familiar.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class FirstPageWizard extends WizardPage implements Listener {

	/**
	 * Constants to be used to define the file type
	 */
	public static final String FAMILIAR = "FAMILIAR";
	public static final String FEATURE_IDE = "FeatureIDE";
	public static final String SPLOT = "SPLOT";
	public static final String TVL = "TVL";
	public static final String TRISKELL = "Triskell";

	private Label comboLabel;
	private Combo combo;
	private Label nameLabel;
	private Text nameText;
	private Label browseLabel;
	private Button browse;
	private DirectoryDialog directory;
	private String chemin = "";

	GridLayout gridLayout = new GridLayout();

	public FirstPageWizard(String pageName) {
		super(pageName);
		this.setTitle("My first wizard page!");
		this.setDescription("Descreption of the wizard page ...");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		nameLabel = new Label(composite, SWT.SHADOW_OUT);
		nameLabel.setText("File name ");
		nameLabel.setSize(100, 25);
		nameText = new Text(composite, SWT.BORDER);
		nameText.setSize(150, 25);
		nameText.addModifyListener(new ModifyListener() {
			// this listener checks all changes in the text field (Name text
			// field)
			@Override
			public void modifyText(ModifyEvent e) {
				if (getFileName().equals(""))
					setErrorMessage("File name is empty");// this method puts an
															// error message in
															// write top of the
															// wizard page
				else
					setErrorMessage(null);
				setPageComplete(isPageComplete());
			}
		});
		browse = new Button(composite, SWT.PUSH);
		browse.setText("Browse...");
		browse.setSize(100, 150);
		browseLabel = new Label(composite, SWT.SHADOW_OUT);
		browseLabel.setSize(150, 25);
		browseLabel.setText("No folder selected...");
		directory = new DirectoryDialog(parent.getShell());
		browse.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// DIRECTORY.OPEN() : opens a dialog frame for the to let him
				// select the path of the file
				// it returns a string contains the path if he selected one and
				// returns NULL if he aborts the operation
				FirstPageWizard.this.chemin = directory.open();
				if (chemin.equals("")) {
					browseLabel.setText("No folder selected...");
				} else {
					browseLabel.setText(chemin);
					setMessage("");
				}

				setPageComplete(isPageComplete());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		comboLabel = new Label(composite, SWT.SHADOW_OUT);
		comboLabel.setText("File type");
		comboLabel.setSize(100, 25);
		combo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		combo.setSize(150, 25);
		combo.select(0);
		combo.add(FAMILIAR);
		combo.add(FEATURE_IDE);
		combo.add(SPLOT);
		combo.add(TVL);
		combo.add(TRISKELL);
		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(isPageComplete());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		composite.layout();

		combo.addListener(SWT.Selection, this);
		setControl(composite);
	}

	@Override
	public void handleEvent(Event event) {

	}

	public String getFileName() {
		return nameText.getText();
	}

	public String getFileType() {
		int index = combo.getSelectionIndex();
		if (index > 0)
			return (String) combo.getItem(index);
		else
			return "";
	}

	public String getLocation() {
		return chemin;
	}

	/**
	 * checks if the page is complete or no checks if the users inputs the file
	 * name , checks if the he selected a file type and checks if he selected a
	 * path or no if all the informations are correct it returns true and if
	 * there is one missing data it returns false
	 * 
	 * @return
	 */
	public boolean isPageComplete() {
		if ((getFileName() == null) || (getFileName().equals(""))
				|| (getFileType().equals("")) || getLocation().equals(""))
			return false;

		return true;
	}
}
