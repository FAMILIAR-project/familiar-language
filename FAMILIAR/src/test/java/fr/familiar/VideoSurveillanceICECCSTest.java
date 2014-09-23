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

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;

public class VideoSurveillanceICECCSTest extends FMLTest {

	@Test
	public void testConfigsTalk() throws Exception {

		String VSARfm = "FM ( Scene:  LightingConditions; \n"
				+ "				LightingConditions: (Indoors|Outdoors) [LightingNoise] (ArtificialLight|NaturalLight) TimeOfDay ; "
				+ "TimeOfDay: (Night|Day) ; "
				+ "LightingNoise : (Flashes|Shadows|HeadLight) ; )";

		FeatureModelVariable vSARFMv = FM("vsar", VSARfm);
		System.err.println("#vSARFMv=" + vSARFMv.counting());

	}

	@Test
	public void testCleanup() throws Exception {

		String VSARfm = "vsar = FM (VSApplicationRequirement: Scene Sort ; \n"
				+ "				Scene:  LightingConditions; \n"
				+ "				Sort: (Person|Vehicle)+ ; \n"
				+ "				LightingConditions: (Indoors|Outdoors) [LightingNoise] (ArtificialLight|NaturalLight) TimeOfDay ; "
				+ "TimeOfDay: (Night|Day) ; "
				+ "LightingNoise : (Flashes|Shadows|HeadLight) ; )";

		_shell.parse(VSARfm);
		FeatureModelVariable vsar_fm = getFMVariable("vsar");

		// some edits
		String edits = "setMandatory vsar.LightingNoise\n"
				+ "removeFeature vsar.Shadows\n"
				+ "setMandatory vsar.Outdoors\n"
				+ "removeFeature vsar.Indoors\n"
				+ "removeFeature vsar.Vehicle\n" + "setMandatory vsar.Person\n";

		_shell.parse(edits);
		_shell.parse("cleanup vsar");

		// VSAR'
		FeatureModelVariable vsarp_fm = getFMVariable("vsar");

		// what we expected
		String VSARprimeExpected = "vsarPrimeExpected = FM (VSApplicationRequirement: Scene Sort ; \n"
				+ "				Scene:  LightingConditions; \n"
				+ "				Sort: Person ; \n"
				+ "				LightingConditions: Outdoors LightingNoise (ArtificialLight|NaturalLight) TimeOfDay ; "
				+ "TimeOfDay: (Night|Day) ; "
				+ "LightingNoise : (Flashes|HeadLight) ; )\n";

		_shell.parse(VSARprimeExpected);

		FeatureModelVariable fmvVSARprimeExpected = getFMVariable("vsarPrimeExpected");
		assertFormulaEquals(fmvVSARprimeExpected.getFormula(),
				vsarp_fm.getFormula());

		// FIXME: should work
		// _shell.parse("assert (vsar eq vsarPrimeExpected)");

		String PCFfm = "pcf = FM (VSPlatform: Segmentation Classification [LightingAnalyses]; \n"
				+ "				Segmentation:  KernelFunction; \n"
				+ "KernelFunction : (Color|Grey) (Edge|Region) ; "
				+ "				Classification : [Contour] [Density] [Model] ; "
				+ "Density : HighDensity ; "
				+ "LightingAnalyses : [HeadLightDetect] ; "
				+ "Model : [Omega] (ThreeD|Paral|Ellipse) ; Edge -> !Density ; "
				+ ")";

		_shell.parse(PCFfm);
		FeatureModelVariable pcf_fm = getFMVariable("pcf");

		String prules = "prules = constraints ( LightingNoise -> (Edge and LightingAnalyses) ; (Flashes or HeadLight) -> Contour ; Person -> Omega ; )";

		_shell.parse(prules);

		String glFM = "glFM"; // global name FM
		_shell.parse(glFM + " = aggregate { vsar pcf } withMapping prules");

		_shell.parse("cleanup glFM");

		FeatureModelVariable gl_fm = getFMVariable("glFM");

		_shell.parse("pcfp = extract glFM.VSPlatform");
		_shell.parse("cleanup pcfp");

		// PC'
		FeatureModelVariable pcfp_fm = getFMVariable("pcfp");

		System.err.println("pcfp_fm=" + pcfp_fm);
		System.err.println("glFM_fm=" + gl_fm);

		ConfigurationVariable cf = ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(gl_fm, "confVSAR"); 
		cf.applySelection("Night", OpSelection.SELECT);

		cf.getDeselected();
		cf.getSelected();

		gl_fm.getFm();

		//

	}

}
