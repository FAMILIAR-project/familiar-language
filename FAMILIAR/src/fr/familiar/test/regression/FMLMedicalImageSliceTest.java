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

package fr.familiar.test.regression;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.test.FMLSlicerUtilityTest;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;

@RunWith(Parameterized.class)
public class FMLMedicalImageSliceTest extends FMLSlicerUtilityTest {

	public static final String _fmGridSpecification = "GridDeployment : Authentification GridComputingNode [LibraryRequired] ; "
			+ "LibraryRequired: [MatLab] ;\n"
			+ "GridComputingNode: OperatingSystem Processor [FileSizeLimit] ; "
			+ "OperatingSystem: (Windows|Linux) ; Linux : (ScientificLinux|Ubuntu) ; "
			+ "Processor : ProcessorBits [GPU] ; ProcessorBits : (x32|x64) ; "
			+ "Authentification : (PasswordBased|KerberosAuth|SSLAuth|CertificationAuth) ; \n";

	public static final String _fmMISpecification = "MedicalImage: Format ModalityAcquisition AnatomicalStructure ;   \n"
			+ "								 	Format: [MetaDataSupport] FormatName ; "
			+ "										FormatName : (DICOM|Nifti|Analyze) ;  \n"
			+ "										MetaDataSupport : [AnonymizedSupport] ; "
			+ "								 	ModalityAcquisition: (MRI|SPEC|CT|PET) ; \n"
			+ "								 			 	MRI : (T1|T2)+ ; \n"
			+ "							 		AnatomicalStructure : Brain ;";

	public static final String _fmProtoSpecification = "NetworkProtocol : "
			+ "[HeaderEncoding] TransferProtocol NetworkSecurity ; "
			+ "NetworkSecurity: [SSL] [PGP] Crypto ; "
			+ "Crypto: (Symetric|Asymetric) ; "
			+ "Symetric : (KDC|DES|TripleDES|BlowFish) ; "
			+ "TransferProtocol : (HTTPS|HTTP);";

	public static final String _fmAlgoSpecification = "MIAlgorithm : Method [Interactive] ; "
			+ " Method : (Linear|NonGrid|Atlas|Model) ; \n"
			+ "								Atlas : (CFL|EMS) ;   \n"
			+ "								Model : (PAM|BAM) ; "
			+ "								Linear : (Rotation|Scaling|Affine) ; ";

	public static final String _cstSpecification = "KerberosAuth -> KDC ; ";

	// fmService specification
	public static final String _fmServiceSpecification = "FM (MIService : MIAlgorithm MedicalImage GridDeployment NetworkProtocol ; "
			+ _fmAlgoSpecification
			+ _fmMISpecification
			+ _fmGridSpecification
			+ _fmProtoSpecification + _cstSpecification + ")\n";

	public final static int N_TIMES = 1;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[N_TIMES][0]);
	}

	public FMLMedicalImageSliceTest() {
	}

	@Test
	public void repeatMedicalImage() throws Exception {
		// testMedicalImage1();
		// checkEquivalenceFMService();
		testMedicalImageASE();
	}

	public void testMedicalImage1() throws Exception {

		String fmServiceID = "fmService";
		_shell.parse(fmServiceID + " = " + _fmServiceSpecification);
		FeatureModelVariable fmvService = getFMVariable(fmServiceID);
		serializeToS2T2(fmvService);

		// 2. VIEWS extraction
		// 2.1 security expert view extraction
		List<String> securityView = Arrays.asList(new String[] { "MIService",
				"NetworkSecurity", "SSL", "PGP", "Crypto", "Symetric",
				"Asymetric", "KDC", "DES", "TripleDES", "BlowFish",
				"TransferProtocol", "HTTP", "HTTPS", "GridDeployment",
				"Authentication", "PasswordBased", "KerberosAuth", "SSLAuth",
				"CertificationAuth", "MetaDataSupport", "AnonymizedSupport" });
		// 2.2 medical image expert view extraction
		List<String> medicalView = Arrays.asList(new String[] { "MIService",
				"MedicalImage", "FormatName", "MIAlgorithm", "Interactive" });

		// _shell.setVerbose(true);

		FeatureModelVariable fmvSecurity = runSliceFMV(fmvService,
				securityView, SliceMode.INCLUDING, "fmSecurity");
		serializeToS2T2(fmvSecurity);

		FeatureModelVariable fmvMedicalView = runSliceFMV(fmvService,
				medicalView, SliceMode.INCLUDING, "fmMedical");
		serializeToS2T2(fmvMedicalView);

	}

	public void testMedicalImageASE() throws Exception {

		_shell.reset();
		String fmServiceID = "fmService";
		FeatureModelVariable fmvService = getFMServiceFromAggregation(fmServiceID); // getFMService(fmServiceID)
																					// ;
																					// //
																					// //

		String viewSecuritySpecification = fmServiceID + "."
				+ "GridDeployment.Authentification.* ++ { " + fmServiceID + "."
				+ "GridDeployment.Authentification } " + "++ { " + fmServiceID
				+ "." + "MIService } ++ " + fmServiceID + "."
				+ "NetworkProtocol.*" + " ++ { " + fmServiceID + "."
				+ "NetworkProtocol } ++ {" + fmServiceID + "."
				+ "MedicalImage.MetaDataSupport } ++ " + fmServiceID + "."
				+ "MedicalImage.MetaDataSupport.* ";
		// + "++ { " + fmServiceID + "." + "MIAlgorithm.Interactive }" ;
		String viewSecurityID = "viewSecurity";

		_shell.parse(viewSecurityID + " = " + viewSecuritySpecification);

		SetVariable ftsViewSecurity = getSetVariable(viewSecurityID);
		System.err.println("ftsViewSecurity=" + ftsViewSecurity.getValue());

		String fmViewSecurityID = "fmViewSecurity";

		_shell.parse(fmViewSecurityID + " = " + "slice " + fmServiceID
				+ " including " + viewSecurityID);
		FeatureModelVariable fmViewSecurity = getFMVariable(fmViewSecurityID);

		System.err.println("fmViewSecurity=" + fmViewSecurity);
		assertEquals(ftsViewSecurity.size(), fmViewSecurity.features().size());

		serializeToS2T2(fmViewSecurity);

		/*
		 * String viewGMI = "({" + fmServiceID + "." + "MedicalImage" + " }" +
		 * " ++ " + fmServiceID + "." + "MedicalImage.*" + ")" ; String
		 * viewGlobalMIID = "viewGMI"; _shell.parse(viewGlobalMIID + " = " +
		 * viewGMI);
		 * 
		 * String viewMetaData = "({" + fmServiceID + "." +
		 * "MedicalImage.MetaDataSupport } ++ {" + fmServiceID + "." +
		 * "MedicalImage.AnonymizedSupport })"; String viewMetaDataID =
		 * "vMetaData"; _shell.parse(viewMetaDataID + " = " + viewMetaData);
		 * 
		 * 
		 * String viewMISpecification = viewGlobalMIID + " -- " + viewMetaDataID
		 * ;
		 */

		String viewMISpecification = "(" + "{" + fmServiceID + "."
				+ "MIService" + " } ++ " + "({" + fmServiceID + "."
				+ "MedicalImage" + " }" + " ++ " + fmServiceID + "."
				+ "MedicalImage.*" + ")" + " -- " + "({" + fmServiceID + "."
				+ "MedicalImage.MetaDataSupport } ++ {" + fmServiceID + "."
				+ "MedicalImage.AnonymizedSupport })) ++ " + "" + "({ "
				+ fmServiceID + "." + "GridDeployment.GPU } ++ " + fmServiceID
				+ "." + "MIAlgorithm.*" + " ++ { " + fmServiceID + "."
				+ "MIAlgorithm }) ++ { " + fmServiceID + "."
				+ "LibraryRequired }" + "++ { " + fmServiceID + "."
				+ "MatLab }";

		String viewMIID = "viewMI";
		_shell.parse(viewMIID + " = " + viewMISpecification);
		SetVariable ftsViewMI = getSetVariable(viewMIID);
		System.err.println("ftsViewMI=" + ftsViewMI.getValue());

		String fmViewMIID = "fmViewMI";

		_shell.parse(fmViewMIID + " = " + "slice " + fmServiceID
				+ " including " + viewMIID);
		FeatureModelVariable fmViewMI = getFMVariable(fmViewMIID);

		System.err.println("fmViewMI="
				+ ConvertAnalyzer.convert(fmViewMI, FMFormat.FIDE));
		assertEquals(ftsViewMI.size(), fmViewMI.features().size());

		serializeToS2T2(fmViewMI);

		System.out.println("recap:\n" + _shell.getHistory());

	}

	/*
	private void checkEquivalenceFMService() throws Exception {
		FeatureModelVariable fm1 = getFMService("fmService1");
		FeatureModelVariable fm2 = getFMServiceFromAggregation("fmService2");
		assertNotNull(fm1);
		assertNotNull(fm2);

		System.err.println("fm1=" + fm1);
		System.err.println("fm2=" + fm2);
		System.err.println("fm2 -- fm1 ="
				+ new AllConfigsBDD(_builder).process(FMLMerger.diff(
						fm1.getFormula(), fm2.getFormula())));
		System.err.println("fm1 -- fm2 ="
				+ new AllConfigsBDD(_builder).process(FMLMerger.diff(
						fm2.getFormula(), fm1.getFormula())));
		System.err.println("fm1 (domain)=" + fm1.getFormula().getDomain());
		System.err.println("fm2 (domain)=" + fm2.getFormula().getDomain());

		assertTrue(fm1.getFormula().getBDD().equals(fm2.getFormula().getBDD()));
		_shell.reset();
	}*/

	private FeatureModelVariable getFMService(String fmServiceID)
			throws Exception {
		FeatureModelVariable fmvService = FM(fmServiceID,
				_fmServiceSpecification);
		return fmvService;
	}

	private FeatureModelVariable getFMServiceFromAggregation(String fmServiceID)
			throws Exception {

		String fmGridID = "fmGrid";
		FeatureModelVariable fmvGrid = FM(fmGridID, "FM ("
				+ _fmGridSpecification + ")");

		String fmAlgoID = "fmAlgo";
		FeatureModelVariable fmvAlgo = FM(fmAlgoID, "FM ("
				+ _fmAlgoSpecification + ")");

		String fmProtoID = "fmProto";
		FeatureModelVariable fmvProto = FM(fmProtoID, "FM ("
				+ _fmProtoSpecification + ")");

		String fmMIsupportID = "fmMI";
		FeatureModelVariable fmvMI = FM(fmMIsupportID, "FM ("
				+ _fmMISpecification + ")");

		String cstID = "cst";
		_shell.parse(cstID + " = " + "constraints (" + _cstSpecification + ")");

		String aggregateInstruction = fmServiceID + " = " + "aggregate { "
				+ fmAlgoID + " " + fmGridID + " " + fmProtoID + " "
				+ fmMIsupportID + " } withMapping " + cstID;

		System.err.println("aggregateInstruction=" + aggregateInstruction);

		_shell.parse(aggregateInstruction + "\n");
		FeatureModelVariable fmvService = getFMVariable(fmServiceID);
		fmvService.renameFeature(fmvService.root().name(), "MIService");

		/*
		 * _shell.getCurrentEnv().removeVariable(fmvProto);
		 * _shell.getCurrentEnv().removeVariable(fmvMI);
		 * _shell.getCurrentEnv().removeVariable(fmvGrid);
		 * _shell.getCurrentEnv().removeVariable(fmvAlgo);
		 */

		return fmvService;
	}

}
