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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FeatureModelLabel extends CLabel {

	private FeatureModelVariable featureModelVariable = null;
	private List<ConfigurationVariable> configurationVariables = new ArrayList<ConfigurationVariable>();

	public FeatureModelLabel(Composite parent, int style) {
		super(parent, style);
	}

	public FeatureModelLabel(Composite parent, FeatureModelVariable feature,
			List<ConfigurationVariable> list) {
		super(parent, SWT.SHADOW_OUT);
		this.featureModelVariable = feature;
		this.configurationVariables = list;
		this.setText(featureModelVariable.getIdentifier());
	}

	public String getInformations() {
		String s = "Nom de la varible : "
				+ featureModelVariable.getIdentifier()
				+ "\nType de la varible : " + featureModelVariable.getType()
				+ "\nValeur de la variable : \n"
				+ featureModelVariable.getValue() + "\nConfigurations : \n";

		for (ConfigurationVariable conf : configurationVariables) {
			s = s + conf.getIdentifier() + " : " + conf.getValue() + "\n";
		}

		return s;
	}

	/**
	 * @return the featureModelVariable
	 */
	public FeatureModelVariable getFeatureModelVariable() {
		return featureModelVariable;
	}

	/**
	 * @param featureModelVariable
	 *            the featureModelVariable to set
	 */
	public void setFeatureModelVariable(
			FeatureModelVariable featureModelVariable) {
		this.featureModelVariable = featureModelVariable;
	}

}
