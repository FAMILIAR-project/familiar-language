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
package fr.familiar.wizard;

import org.eclipse.jface.wizard.Wizard;

import fr.familiar.variable.Variable;

public class MyWizard extends Wizard {

	private FirstPageWizard firstPageWizard;
	private Variable var;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		firstPageWizard = new FirstPageWizard("MonWzard");
		addPage(firstPageWizard);
	}

	/**
	 * In this method we implements the code that will be executed after
	 * validating the wizaed in our case we serialize variables in files.
	 * 
	 * Returns true to indicate the finish request was accepted, and false to
	 * indicate that the finish request was refused
	 * 
	 */
	@Override
	public boolean performFinish() {
		String name = firstPageWizard.getFileName();
		String type = firstPageWizard.getFileType();
		String location = firstPageWizard.getLocation();
		String extension = "";
		if ((name == null) || (name.equals("")) || (type.equals(""))
				|| location.equals("")) {
			return false;
		}
		System.out.println(name);
		System.out.println(type);
		System.out.println(location);
		System.out.println(var);
		if (type.equals(FirstPageWizard.FAMILIAR))
			extension = ".fmm";
		else if (type.equals(FirstPageWizard.FEATURE_IDE))
			extension = ".m";
		else if (type.equals(FirstPageWizard.SPLOT))
			extension = ".xml";
		else if (type.equals(FirstPageWizard.TVL))
			extension = "tvl";
		else
			extension = "triskell";
		return true;
	}

	public void setVariable(Variable v) {
		var = v;
	}
}
