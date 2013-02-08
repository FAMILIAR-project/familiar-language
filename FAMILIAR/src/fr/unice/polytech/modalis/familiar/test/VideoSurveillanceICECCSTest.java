package fr.unice.polytech.modalis.familiar.test;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.unice.polytech.modalis.familiar.parser.ConfigurationVariableFactory;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

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
