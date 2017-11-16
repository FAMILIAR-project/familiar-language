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

import java.util.StringTokenizer;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;

/**
 * @author mathieuacher
 * 
 */
// time consuming test suite
@Ignore 
public class VideoSurveillanceREFSQTest extends FMLTest {

	@Test
	public void testScenario1() throws Exception {

		loadModels();

		// specialization using configuration facilities
		String FTsIncluded = "Color TopView Indoors Counting LessPrecision Tracking Person";
		String FTsNotIncluded = "LightingVariation Outdoors ArtificialLight Sensitivity BackgroundMovement Noise";
		specialize(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithSpe();

	}

	@Test
	public void testScenario2() throws Exception {

		loadModels();
		// _shell.setVerbose(true);

		/*
		 * 145 (2) 146 (Outdoors 147 Night 148 Day) 149 150 // QoS 151 //
		 * Sensitivity
		 */
		// specialization using configuration facilities
		String FTsIncluded = "Noise Indoors Precision ArtificialLight Person Movable SingleObject MonoSensor Camera FieldOfView Narrow";
		String FTsNotIncluded = "SeaOceanWaves SandOrDust VingtCinqFrSec Sensitivity Outdoors MultiSensor BackgroundMovement";
		specialize(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithSpe();

	}

	@Test
	public void testScenario3() throws Exception {

		loadModels();

		// night / outdoors (similar to Scenario 2)
		String FTsIncluded = "Night Outdoors Person Movable SingleObject MonoSensor Camera FieldOfView Narrow";
		String FTsNotIncluded = "Noise Indoors MultiSensor";
		specialize(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithSpe();

	}

	@Test
	public void testScenario4() throws Exception {

		loadModels();

		// 2.1�D�tection�d'intrusion�sur�parking�ext�rieur
		/*
		 * Intrusion�detection Person Vehicle Single�Object MonoSensor
		 * 8�frames�per�second Color
		 * Camera�location�hight�with�large�field�of�view Natural�light
		 * outdoor,�daynight Noise,�vegetation Light�variations
		 * Response�time�<�1�second Sensitivity�must�be�hight
		 */
		// Day Night
		// LowFrameRate, ArtificialLight not possible!?

		String FTsIncluded = "Noise BackgroundMovement VegetationMovement NaturalLight ResponseTime IntrusionDetection Sensitivity Person Vehicle SingleObject MonoSensor Color Camera";
		String FTsNotIncluded = "LessPrecision ComputerLoad Flashes SeaOceanWaves SandOrDust";
		specialize(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithSpe();

	}

	@Test
	public void testScenario5() throws Exception {

		loadModels();

		/*
		 * 
		 * D�tection�d'intrusionʈ�l'int�rieur�d'un�b�timent Intrusion�detection
		 * Person Single�Object MonoSensor 8�frames�per�second
		 * Day/Night�camera�(color/infrared)
		 * Camera�location�(on�wall),�normal�field�of�view Artificial�light
		 * Indoor Light�variations Response�time�<�1�second
		 * Sensitivity�must�be�hight
		 */
		// Day Night
		String FTsIncluded = "Narrow ResponseTime Color Natural Camera Infrared MonoSensor SingleObject Person Indoors LightingVariation";
		String FTsNotIncluded = "ArtificialLight BackgroundMovement HeadLight Vehicle Manufactured";
		specialize(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithSpe();

	}


	@Test
	public void testScenario6() throws Exception {

		loadModels();

		/*
		 * 
		 * Counting Person Single�Object
		 * MultiSensor�(one�for�entrance�and�one�for�exit) 25�frames�per�second
		 * Color Camera�location:�top�view,�small�field�of�view Indoor
		 * Artificial�light Noise,�fences
		 * Response�time�not�so�important�<�1�minute
		 */

		String FTsIncluded = "Color Indoor TopView Noise VingtCinqFrSec Color Camera MultiSensor SingleObject";
		String FTsNotIncluded = "SandOrDust SeaOceanWaves VingtCinqFrSec ResponseTime Noise BackgroundMovement";

		specializeWithConf(FTsIncluded, FTsNotIncluded);

		finalizeProcessWithConf();

	}

	private void specializeWithConf(String fTsIncluded, String fTsNotIncluded) {
		if (!fTsIncluded.isEmpty())
			_shell.parse("select " + fTsIncluded + " in deployment_conf");
		if (!fTsNotIncluded.isEmpty())
			_shell.parse("deselect " + fTsNotIncluded + " in deployment_conf");

		int nedits = new StringTokenizer(fTsIncluded).countTokens()
				+ new StringTokenizer(fTsNotIncluded).countTokens();
		System.err.print("#edits=" + nedits + " ");

	}

	private void specialize(String fTsIncluded, String fTsNotIncluded)
			throws Exception {

		int nedits = 0;

		if (!fTsIncluded.isEmpty()) {
			StringTokenizer tok = new StringTokenizer(fTsIncluded);
			FeatureModelVariable requirement_spe = getFMVariable("requirement_spe");
			while (tok.hasMoreTokens()) {
				String mand = tok.nextToken();
				if (!setVariabletoString(requirement_spe.features()).contains(
						mand)) {
					System.err.println("\t\t" + mand + " already removed");
				} else {
					nedits++;
					_shell.parse("setMandatory" + " requirement_spe." + mand
							+ "\n");
				}
			}

		}

		if (!fTsNotIncluded.isEmpty()) {
			StringTokenizer tok = new StringTokenizer(fTsNotIncluded);
			while (tok.hasMoreTokens()) {
				nedits++;
				_shell.parse("removeFeature " + " requirement_spe."
						+ tok.nextToken() + "\n");
			}
		}

		System.err.print("#edits=" + nedits + " ");

		// FeatureModelVariable requirement_spe =
		// getFMVariable("requirement_spe");
		// System.err.println("requirement_spe=" + requirement_spe.getFm());

	}

	private void finalizeProcessWithSpe() throws Exception {
		// restriction view on the platform
		_shell.parse("newDeploymentFM = aggregate { requirement_spe platform } withMapping TRANSrules\n"
				+ "cleanup newDeploymentFM\n"
				+ "new_platform_fm = extract newDeploymentFM.VSPlatform\n"
				+ "map new_platform_fm with PlatformRules");

		/*
		 * FeatureModelVariable newDeploymentFM =
		 * getFMVariable("newDeploymentFM");
		 * System.err.println("newDeploymentFM=" + newDeploymentFM.getFm());
		 * 
		 * FeatureModelVariable new_platform_fm =
		 * getFMVariable("new_platform_fm");
		 * System.err.println("new_platform_fm=" + new_platform_fm.getFm());
		 */

		_shell.parse("nNewPlatform = counting new_platform_fm\n");
		// IntegerVariable oPlatform = getIntegerVariable("oPlatform");
		IntegerVariable nNewPlatform = getIntegerVariable("nNewPlatform");
		// System.err.println("oplatform=" + oPlatform.getV());
		System.err.print("#configurations=" + nNewPlatform.getV() + " ");

		FeatureModelVariable platform = getFMVariable("platform");
		int nfeatures = platform.features().size();
		FeatureModelVariable new_platform_fm = getFMVariable("new_platform_fm");
		int ncores = new_platform_fm.cores().size();
		System.err.print("#cores=" + ncores + " ");
		int ndeads = new_platform_fm.deads().size();
		System.err.print("#deads=" + ndeads + " ");

		System.err.println("#noncores=" + (nfeatures - (ncores + ndeads)));

	}

	private void finalizeProcessWithConf() throws Exception {
		// restriction view on the platform

		_shell.parse("deployment_speConf = asFM deployment_conf\n"
				+ "new_platform_fmConf = extract deployment_speConf.VSPlatform\n"
				+ "map new_platform_fmConf with PlatformRules");

		_shell.parse("nNewPlatform = counting new_platform_fmConf\n");
		// IntegerVariable oPlatform = getIntegerVariable("oPlatform");
		IntegerVariable nNewPlatform = getIntegerVariable("nNewPlatform");
		// System.err.println("oplatform=" + oPlatform.getV());
		System.err.print("#configurations=" + nNewPlatform.getV() + " ");

		FeatureModelVariable platform = getFMVariable("platform");
		int nfeatures = platform.features().size();
		FeatureModelVariable new_platform_fm = getFMVariable("new_platform_fmConf");
		int ncores = new_platform_fm.cores().size();
		System.err.print("#cores=" + ncores + " ");
		int ndeads = new_platform_fm.deads().size();
		System.err.print("#deads=" + ndeads + " ");

		System.err.println("#noncores=" + (nfeatures - (ncores + ndeads)));
	}

	private void loadModels() throws Exception {

		_shell.setCountingStrategy(CountingStrategy.BDD_FML); // TODO: should work
															// well with SPLOT

		_shell.parse("run \"deployment.fml\"" + "\n");
		IntegerVariable oPlatform = getIntegerVariable("oPlatform");
		System.err.println("#oplatform=" + oPlatform.getV());
		FeatureModelVariable VSAR = getFMVariable("requirement");
		System.out.println("#features (VSAR)=" + VSAR.features().size());
		System.out.println("#VSAR=" + VSAR.counting());

		FeatureModelVariable platform = getFMVariable("platform");
		System.out.println("#cores (original platform) ="
				+ platform.cores().size());

		/*
		 * _shell.parse("configO = configuration original_deployment_FM\n");
		 * 
		 * ConfigurationVariable configO = getConfigurationVariable("configO");
		 * System.err.println("deselected=" + configO.getDeselected());
		 * System.err.println("selected=" + configO.getSelected());
		 * System.err.println("unselected=" + configO.getUnselected());
		 */
	}

}
