/* **********************************************************************
 * Copyright (c) 2007, 2008 INRIA and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    INRIA - initial API and implementation
 **********************************************************************/

package be.cleve.anthony;

import org.eclipse.emf.ecore.EPackage;

public class NotValidEPackageURIException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EPackage ePackage;

	public NotValidEPackageURIException(EPackage epack) {
		ePackage = epack;
	}

	public EPackage getEPackage() {
		return ePackage;
	}

	public void setEPackage(EPackage package1) {
		ePackage = package1;
	}

}
