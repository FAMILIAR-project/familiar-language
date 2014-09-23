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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import fr.familiar.gui.featureide.ConfigurationLabelListener;
import fr.familiar.gui.featureide.FMLabelListener;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;

@Deprecated
public class VariableConfigurationView extends ViewPart {

	public static final String ID = "FAMILIAR.varConfigurationView";
	private Composite composite;
	private GridLayout gr = new GridLayout();
	private ArrayList<FeatureModelLabel> fmll = new ArrayList<FeatureModelLabel>();
	private ArrayList<ConfigurationLabel> cll = new ArrayList<ConfigurationLabel>();

	public VariableConfigurationView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		composite = new Composite(parent, SWT.None);
		gr.numColumns = 8;
		composite.setLayout(gr);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void addLabel(FeatureModelVariable fv, List<ConfigurationVariable> l) {
		if (composite != null) {
			FeatureModelLabel fml = new FeatureModelLabel(composite, fv, l);
			fml.addMouseListener(new FMLabelListener(fml));
			fmll.add(fml);
			composite.layout();
		}
	}

	public void addConfigurationLabel(ConfigurationVariable cv) {
		if (composite != null) {
			ConfigurationLabel cl = new ConfigurationLabel(composite, cv);
			cl.addMouseListener(new ConfigurationLabelListener(cl));
			cll.add(cl);
			composite.layout();
		}
	}

	public void clear() {
		// Composite p = composite.getParent();
		// composite.dispose();
		// composite = null;
		// composite = new Composite( p, SWT.None);
		// composite.setLayout(gr);
		for (FeatureModelLabel f : fmll) {
			f.dispose();
		}
		for (ConfigurationLabel c : cll) {
			c.dispose();
		}
		fmll.clear();
		cll.clear();
		composite.layout();
	}

}
