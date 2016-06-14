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
package fr.familiar.fm.basic;

import java.io.File;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.xtext.example.mydsl.fml.FeatureModel;

/**
 * @author mathieuacher Examples on how to use FML feature model reader/writer
 */
public class FMLFeatureModelRWExample {

	public static void main(String[] args) {

		// String to fm
		FeatureModel fm = new FMLFeatureModelReader()
				.parseString("FM (A: B C [D]; D -> !C; ) ");

		FeatureModelStringBuilder visitor = new FeatureModelStringBuilder(fm);
		System.out.println(visitor.toString());

		// FML file to fm2
		FeatureModel fm2 = new FMLFeatureModelReader().parseFile(new File(
				"examples/testing/FMs/fm2.fml"));

		FeatureModelStringBuilder visitor2 = new FeatureModelStringBuilder(fm2);
		System.out.println(visitor2.toString());

		// fm2 to XMI
		XMIResource xmi = new FMLFeatureModelWriter(fm2)
				.toXMI("examples/testing/FMs/fm2ecore");

		// XMI to fm3
		FeatureModel fm3 = new FMLFeatureModelReader().parseXMIFile(xmi);

		FeatureModelStringBuilder visitor3 = new FeatureModelStringBuilder(fm3);
		System.out.println(visitor3.toString());

	}

}
