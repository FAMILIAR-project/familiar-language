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
package fr.familiar.fm.featureide;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.ConfigurationReader;
import de.ovgu.featureide.fm.core.configuration.SelectableFeature;
import de.ovgu.featureide.fm.core.configuration.Selection;
import de.ovgu.featureide.fm.ui.editors.configuration.ConfigurationContentProvider;
import fr.familiar.gui.featureide.MyConfigurationWriter;

public class ConfigurationMyEditor extends ApplicationWindow implements
		PropertyChangeListener {

	private TreeViewer viewer;
	private Configuration configuration;
	private FeatureModel featureModel;
	private boolean dirty;

	public ConfigurationMyEditor(Configuration configuration) {

		super(null);
		this.featureModel = configuration.getFeatureModel();
		this.configuration = configuration;
		// Don't return from open() until window closes
		setBlockOnOpen(true);

		open();
		Display.getCurrent().dispose();

	}

	private IDoubleClickListener listener = new IDoubleClickListener() {

		public void doubleClick(DoubleClickEvent event) {
			Object object = ((ITreeSelection) event.getSelection())
					.getFirstElement();
			if (object instanceof SelectableFeature) {
				final SelectableFeature feature = (SelectableFeature) object;
				changeSelection(feature);
			}
		}
	};

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(400, 400);
	}

	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		viewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.addDoubleClickListener(listener);
		viewer.getTree().addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.character == ' ') {
					if (viewer.getSelection() instanceof ITreeSelection) {
						final ITreeSelection tree = (ITreeSelection) viewer
								.getSelection();
						Object object = tree.getFirstElement();
						if (object instanceof SelectableFeature) {
							final SelectableFeature feature = (SelectableFeature) object;
							changeSelection(feature);
						}
					}
				}
			}

			public void keyReleased(KeyEvent e) {

			}
		});
		viewer.setContentProvider(new ConfigurationContentProvider());
		// should work normally
		// viewer.setLabelProvider(new ConfigurationLabelProvider());
		viewer.setInput(configuration);
		viewer.expandAll();
		composite.setLayout(new FillLayout());
		return composite;
	}

	/**
	 * @param The
	 *            feature which change the selection status
	 * 
	 */
	protected void changeSelection(SelectableFeature feature) {
		if (feature.getAutomatic() == Selection.UNDEFINED) {
			// set to the next value
			if (feature.getManual() == Selection.UNDEFINED)
				set(feature, Selection.SELECTED);
			else if (feature.getManual() == Selection.SELECTED)
				set(feature, Selection.UNSELECTED);
			else
				// case: unselected
				set(feature, Selection.UNDEFINED);
			if (!dirty) {
				dirty = true;
			}
			viewer.refresh();
		}
	}

	private void refreshTree() throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		// call it: TODO
		setConfiguration();
		viewer.setContentProvider(new ConfigurationContentProvider());
		// should work normally
		// viewer.setLabelProvider(new ConfigurationLabelProvider());
		viewer.setInput(configuration);
		viewer.expandAll();
		viewer.refresh();
	}

	private void setConfiguration() throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		String text = new MyConfigurationWriter(configuration)
				.writeIntoString();
		configuration = new Configuration(featureModel, true);
		boolean wasDirty = dirty;
		try {
			dirty = !new ConfigurationReader(configuration)
					.readFromString(text);
			// if (!dirty)
			// dirty = !configuration.validManually();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dirty = wasDirty;
	}

	protected void set(SelectableFeature feature, Selection selection) {
		configuration.setManual(feature, selection);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			refreshTree();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
