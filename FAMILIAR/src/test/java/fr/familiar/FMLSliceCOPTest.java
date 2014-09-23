/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar;

import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.variable.FeatureModelVariable;

public class FMLSliceCOPTest extends FMLSlicerUtilityTest {

	@Test
	public void testIdea() throws Exception {

		String fmSpecification = "FM (Buffer: [Logging] [Restore] B1 B2 B3 B4 B5 B6 ; "
				+ "Restore: [MultiRes] R1 R2 R3 R4; "
				+ "MultiRes: M1 M2 M3 M4 M5 [M6]; "
				+ "Logging : PrintInstVars LogMethod InvokeLog ; "
				+ "PrintInstVars: [L5] [L6] ; "
				+ "InvokeLog: [L2] [L3] [L4] ; "
				+ "LogMethod: L1 ; "
				+ "B2 -> B1; B3 -> B1; B5 -> B1; B4 -> B3; B4 -> B2; B6 -> B2; B6 -> B5;"
				+ "R1 -> B1; R2 -> B1; R3 -> R1; R3 -> B3;\n"
				+ "R3 -> B2; R4 -> R1; R4 -> R2; R4 -> B2; L1 -> B1; L2 -> L1; "
				+ "L2 -> B3; L3 -> L1; L3 -> R2; L4 -> L1; L4 -> B5;\n"
				+ "M1 -> R1; M2 -> R3; M3 -> M2; M4 -> R4; M5 -> M4; "
				+ "M6 -> L5; L5 -> L1; L5 -> R1; L6 -> L1; L6 -> B2;)";

		FeatureModelVariable fmChopExtracted = FM("fmChopExtracted",
				fmSpecification);

		System.err.println("#fmChopExtracted=" + fmChopExtracted.counting());

		String fmOSpecification = "FM (Buffer: [Logging] [Restore]; "
				+ "Restore: [MultiRes] ; "
				+ "Logging : PrintInstVars LogMethod InvokeLog ;) ";
		FeatureModelVariable fmChopO = FM("fmChopOriginallyDesigned",
				fmOSpecification);

		FeatureModelVariable fmChopExtractedAndSliced = runSliceFMV(
				fmChopExtracted, fmChopO.features().names(),
				SliceMode.INCLUDING, "");
		System.err.println("=====================");

		System.err.println("fmChopExtractedAndSliced="
				+ fmChopExtractedAndSliced);
		System.err.println("#fmChopExtractedAndSliced="
				+ fmChopExtractedAndSliced.counting());
		Set<Set<String>> fmChopESlicedConfigs = FMLUtils.setConfigToSetStr(fmChopExtractedAndSliced
		.configs());
		;
		System.err.println("[[fmChopExtractedAndSliced]]="
				+ fmChopESlicedConfigs);
		System.err.println("=====================");

		System.err.println("fmChopO=" + fmChopO);
		System.err.println("#fmChopO=" + fmChopO.counting());
		Set<Set<String>> fmChopOConfigs = FMLUtils.setConfigToSetStr(fmChopO.configs());
		System.err.println("[[fmChopO]]=" + fmChopOConfigs);

		System.err.println("[[fmChopExtractedAndSliced]] -- [[fmChopO]]="
				+ Sets.difference(fmChopESlicedConfigs, fmChopOConfigs));
		System.err.println("[[fmChopO]] -- [[fmChopExtractedAndSliced]]="
				+ Sets.difference(fmChopOConfigs, fmChopESlicedConfigs));

	}

}
