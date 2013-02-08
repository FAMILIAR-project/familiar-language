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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;



public class EcoreRegistering {
	

	
	public static void registerPackages(URI ecoreFileUri) throws NotValidEPackageURIException {
		ResourceSet rs = new ResourceSetImpl();
		
		String ecore_ext = org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getName().toLowerCase();
		if( !rs.getResourceFactoryRegistry().getExtensionToFactoryMap().containsKey(ecore_ext) ) {
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ecore_ext, new XMIResourceFactoryImpl());
		}
		
		Resource res = rs.getResource(ecoreFileUri, true);
		
		for(EObject eobj : res.getContents()) {
			if( eobj instanceof EPackage) {
				registerPackages((EPackage) eobj);
			}
		}
	}

	/**
	 * Register the given EPackage and all its contained EPackages
	 * @param pack
	 * @throws NotValidEPackageURIException 
	 */
	public static void registerPackages(EPackage pack) throws NotValidEPackageURIException {
		String pack_NsURI = pack.getNsURI();
		if( pack_NsURI != null && !pack_NsURI.equals("") ) {
			
			if( !Registry.INSTANCE.containsKey(pack_NsURI) ) {
				Registry.INSTANCE.put(pack_NsURI, pack);
			}

			for(EPackage subPack : pack.getESubpackages()) {
				registerPackages(subPack);
			}

		} else {
			throw new NotValidEPackageURIException(pack);
		}
	}



}
