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

package fr.familiar;

import org.junit.Test;

import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

public class FMLVisualization2Test extends FMLTest {

	@Test
	public void testProtovisVS() throws Exception {

		_shell.parse("run \"deployment.fml\"" + "\n");
		FeatureModelVariable VSAR = getFMVariable("requirement");
		FeatureModelVariable platform = getFMVariable("platform");
		FileSerializer.write("output/" + VSAR.getIdentifier() + ".html",
				VSAR.toProtovis());
		FileSerializer.write("output/" + platform.getIdentifier() + ".html",
				platform.toProtovis());

		_shell.parse("aggVS = aggregate { requirement platform } withMapping TRANSrules");
		FeatureModelVariable aggVS = getFMVariable("aggVS");
		FileSerializer.write("output/" + aggVS.getIdentifier() + ".html",
				aggVS.toProtovis());

	}

}
