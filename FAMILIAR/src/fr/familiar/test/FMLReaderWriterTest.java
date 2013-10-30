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
package fr.familiar.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.fML.FeatureModel;

import fr.familiar.fm.basic.FMLFeatureModelReader;
import fr.familiar.fm.basic.FMLFeatureModelWriter;
import fr.familiar.fm.basic.FeatureModelStringBuilder;

/**
 * @author mathieuacher
 * 
 */

@RunWith(value = Parameterized.class)
public class FMLReaderWriterTest {

	protected String _fm;

	public FMLReaderWriterTest(String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] { { "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ FMLTest.FM_LAPTOP }, };
		return Arrays.asList(data);
	}

	@Test
	public void testReader1() {
		// String to fm
		FeatureModel fm = new FMLFeatureModelReader().parseString(_fm);
		FeatureModelStringBuilder visitor = new FeatureModelStringBuilder(fm);
		String produced = visitor.toString();
		assertEquals(_fm.trim(), produced.trim());

	}

	@Test
	public void testReader2() {

		// FML file to fm
		File file = new File("examples/testing/FMs/fmTestReader2.fml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(new StringReader(_fm));
			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str);
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {

		}
		FeatureModel fm = new FMLFeatureModelReader().parseFile(file);
		FeatureModelStringBuilder visitor = new FeatureModelStringBuilder(fm);
		String produced = visitor.toString();
		assertEquals(_fm, produced);

		// fm to XMI
		XMIResource xmi = new FMLFeatureModelWriter(fm)
				.toXMI("examples/testing/FMs/fmTestReader2ecore");
		// XMI to fm
		FeatureModel fm3 = new FMLFeatureModelReader().parseXMIFile(xmi);
		FeatureModelStringBuilder visitor3 = new FeatureModelStringBuilder(fm3);
		String produced2 = visitor3.toString();

		assertEquals(_fm, produced2);

	}

}
