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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

import fr.familiar.variable.ConfigurationVariable;

public class ConfigurationLabel extends CLabel {

	private ConfigurationVariable cv;

	public ConfigurationLabel(Composite parent, ConfigurationVariable cv) {
		super(parent, SWT.SHADOW_OUT);
		this.setCv(cv);
		this.setText("(config) " + cv.getIdentifier());

	}

	public void setCv(ConfigurationVariable cv) {
		this.cv = cv;
	}

	public ConfigurationVariable getCv() {
		return cv;
	}

	public String getInformations() throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		return "selected: " + cv.getSelected().toString() + "\ndeselected: "
				+ cv.getDeselected().toString();
	}

}
