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

package fr.familiar.test;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.variable.FeatureModelVariable;

public class FMLICVS2011Test extends FMLTest {

	protected String _fmSpecification = "FM (VSspecification : Application ObjectOfInterest Context ; "
			+ "				Application : Intrusion; Intrusion : (WithReco|WithoutReco) ; "
			+ ""
			+ "ObjectOfInterest : (People|Any) ; "
			+ "Context : Lighting ; Lighting : (LightingHigh|LightingLow) ; "
			// + "ObjectOfInterest implies Any ; "
			+ "WithReco -> !Any ; " + "LightingLow -> WithoutReco ; )";

	protected String _fmImplementation = "FM (VSimplementation : Acquisition Segmentation [ShadowRemoval] Classification Tracking ScenarioRecognition ; "
			+ "Acquisition : Resolution Color ; Resolution: (High|Low) ; Color: (Full|BlackAndWhite|NearIR) ; "
			+ "Segmentation : MotionBased MultiGaussian ; "
			+ "Classification : (AnyObject|ClassificationPeople)+ ; "
			+ "Tracking : (FrameToFrame|LongTerm)+ ; FrameToFrame: (ThreeD|Texture|F2FColor)+ ; "
			+ "LongTerm : (ThreeDLongTerm|TextureLongTerm|ColorLongTerm) ; "
			+ "ScenarioRecognition : (Simple|PeopleBased)+ ; "
			+ "PeopleBased -> ClassificationPeople ; " + "Texture -> High ; " + // Tracking.*.Texture
																				// implies Acquisition.Resolution.High
			"F2FColor -> Full ; " + // Tracking.*.Color implies Acquisition.Color.Full
			")";

	protected String _rules = "constraints (ClassificationPeople -> People ; " + // internal?
																					// (5)
																					// Classification.People
																					//  implies ObjectOfInterest.People
			"LightingLow -> NearIR ; " + // (C1) Context.Ligh+ng.Low implies Acquisi+on.Color.NearIR
			"LightingLow -> Low ; " + // (C2) Context.Ligh+ng.Low
										//  implies Acquisi+on.Resolu+on.Low
			// "ShadowRemoval -> LightingHigh ; " + // (C3)  Shadow
			// Removal implies Context.Ligh+ng.High
			"LightingHigh -> ShadowRemoval ; " + "PeopleBased <-> People ; " + // (C4)
																				// Scenario Recogni+on.People
																				//  based
																				// equiv
																				// Object
																				//  of
																				//  Interest.People
			"PeopleBased <-> WithReco ;" + // (C5) Scenario Recogni+on.People
											//  based equiv
											// Applica+on.Intrusion.With  Reco
			")";

	@Test
	public void testScenario() throws Exception {

		FeatureModelVariable fmvSpecification = FM("fmSpecification",
				_fmSpecification);
		FeatureModelVariable fmvImplementation = FM("fmImplementation",
				_fmImplementation);

		System.err.println("#fmvSpecification=" + fmvSpecification.counting());
		System.err.println("[[fmvSpecification]]=");
		Set<Set<String>> configs = FMLUtils.setConfigToSetStr(fmvSpecification.configs());
		for (Set<String> config : configs) {
			System.err.println(Sets.intersection(config, fmvSpecification
					.leaves().names()));
		}
		System.err.println("*** before specialization ***");
		Set<String> beforeCores = statsFull().cores();

		// specialization steps
		_shell.parse("map fmSpecification with "
				+ "constraints (WithReco ; People ; LightingHigh ; )");
		System.err
				.println("(specialized) fmvSpecification=" + fmvSpecification);

		System.err.println("*** after specialization ***");
		Set<String> afterCores = statsFull().cores();

		System.err.println("\ncores infered="
				+ Sets.difference(afterCores, beforeCores));

	}

	private FeatureModelVariable statsFull() throws Exception {
		_shell.parse("fmVSFull = aggregate { fmSpecification fmImplementation } withMapping "
				+ _rules + "\n");
		FeatureModelVariable fmvVSFull = getFMVariable("fmVSFull");

		System.err.println("fmvVSFull=" + fmvVSFull);
		System.err.println("deads fmvVSFull=" + fmvVSFull.deads());
		System.err.println("cores fmvVSFull=" + fmvVSFull.cores());

		return fmvVSFull;
	}

}
