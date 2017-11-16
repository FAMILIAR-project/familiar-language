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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Sets;

import fr.familiar.FMLTest;
import fr.familiar.fm.FMLUtils;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.StringVariable;

/**
 * @author mathieuacher Testing Quality of Journal content
 */
public class FMLQualitySPLTest extends FMLTest {

	@Test
	public void SPLSegmentation() throws Exception {

		// from services to family of services

		_shell.parse("ServiceSegm1 = FM (MedicalImage : Format ModalityAcquisition [Anonymized]; ModalityAcquisition : MRI; MRI : T1;  Format : DICOM ; )"
				+ "ServiceSegm2 = FM (MedicalImage : Format ModalityAcquisition ; ModalityAcquisition : MRI; MRI : T2;  Format : DICOM ; )"
				+ "ServiceSegm3 = FM (MedicalImage : Format ModalityAcquisition ; ModalityAcquisition : SPECT; Format : DICOM ; )"
				+ "ServiceSegm4 = FM (MedicalImage : Format ModalityAcquisition [Anonymized]; ModalityAcquisition : CT;  Format : Analyze ; )"
				+ "SegmSPL = merge sunion ServiceSegm*\n");

		FeatureModelVariable ServiceSegm1 = getFMVariable("ServiceSegm1");
		FeatureModelVariable ServiceSegm2 = getFMVariable("ServiceSegm2");
		FeatureModelVariable ServiceSegm3 = getFMVariable("ServiceSegm3");
		FeatureModelVariable ServiceSegm4 = getFMVariable("ServiceSegm4");
		FeatureModelVariable SegmSPL = getFMVariable("SegmSPL");

		System.err.println("#SegmSPL=" + SegmSPL.counting());
		System.err.println("SegmSPL=" + SegmSPL);

		assertGeneralization("SegmSPL", "ServiceSegm1");
		assertGeneralization("SegmSPL", "ServiceSegm2");
		assertGeneralization("SegmSPL", "ServiceSegm3");
		assertGeneralization("SegmSPL", "ServiceSegm4");

	}

	@Ignore
	@Test
	public void catalogSegmentation() throws Exception {

		_shell.setVerbose(true);
		String segm_catalog = ""
				+ "segm1 = FM (Segmentation: Method MedicalImage ; \n"
				+ "							Method: Clustering SemiAutomatic ; \n"
				+ "							MedicalImage: Format ModalityAcquisition ; \n"
				+ "							 	Format: (DICOM|Analyze) ;\n"
				+ "							 	ModalityAcquisition: (SPEC|CT) ; )\n" + "\n"
				+ "segm2 = FM (Segmentation: Method MedicalImage ; \n"
				+ "							Method: (Clustering|Graph) ; \n"
				+ "							MedicalImage: Format ModalityAcquisition ; \n"
				+ "							 	Format: (DICOM|Nifti) ;\n"
				+ "							 	ModalityAcquisition: (SPEC|CT) ; )\n"
				+ "							 	\n"
				+ "segm3 = FM (Segmentation: Method MedicalImage ; \n"
				+ "							Method: (Clustering|Histogram) ; \n"
				+ "							MedicalImage: Format ModalityAcquisition ; \n"
				+ "							 	Format: (Analyze|Nifti) ;\n"
				+ "							 	ModalityAcquisition: (SPEC|CT) ; )	\n"
				+ "							 	\n" + "							 	\n"
				+ "sgm12 = merge intersection { segm1 segm2 }\n"
				+ "sgm23 = merge intersection { segm2 segm3 }\n"
				+ "sgm13 = merge intersection { segm1 segm3 }\n"
				+ "sgm123 = merge sunion segm*"
				+ "sgm123common = merge intersection segm*";

		_shell.parse(segm_catalog);

		FeatureModelVariable sgm123 = getFMVariable("sgm123");
		System.err.println("sgm123=" + sgm123);
		System.err.println("#sgm123=" + sgm123.counting());

		assertGeneralization("sgm123", "segm1");
		assertGeneralization("sgm123", "segm2");
		assertGeneralization("sgm123", "segm3");

		FeatureModelVariable segm1 = getFMVariable("segm1");
		FeatureModelVariable segm2 = getFMVariable("segm2");
		FeatureModelVariable segm3 = getFMVariable("segm3");
		FeatureModelVariable sgm12 = getFMVariable("sgm12");
		FeatureModelVariable sgm23 = getFMVariable("sgm23");
		FeatureModelVariable sgm13 = getFMVariable("sgm13");
		FeatureModelVariable sgm123common = getFMVariable("sgm123common");

		Set<Set<String>> configsExpected = Sets.union(Sets.union(
				FMLUtils.setConfigToSetStr(segm1.configs()),
				FMLUtils.setConfigToSetStr(segm2.configs())), FMLUtils.setConfigToSetStr(segm3
				.configs()));

		Set<Set<String>> resultingConfigs = FMLUtils.setConfigToSetStr(sgm123.configs());
		assertEquals(configsExpected.size(), resultingConfigs.size());
		assertSetEquals(configsExpected, resultingConfigs);

		// http://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle
		assertEquals(segm1.counting() + segm2.counting() + segm3.counting()
				- (sgm12.counting() + sgm13.counting() + sgm23.counting())
				+ sgm123common.counting(), sgm123.counting(), 0);

		FileSerializer.write("output/" + sgm123.getIdentifier() + ".dot",
				sgm123.toDOT());
		assertNotNull(new FMLtoFeatureIDE(sgm123).convert());
		_shell.parse("sgm123FeatureIDE=" + "convert sgm123 into featureide");

		StringVariable sgm123FeatureIDE = getStringVariable("sgm123FeatureIDE");
		System.err.println("sgm123FeatureIDE="
				+ sgm123FeatureIDE.getSpecificValue());
		System.err.println("#sgm123 (SAT)=" + sgm123.counting (CountingStrategy.SAT_FEATUREIDE));
	}

}
