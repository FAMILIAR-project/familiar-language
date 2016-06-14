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

package fr.familiar.regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xtext.example.mydsl.fml.FMFormat;
import org.xtext.example.mydsl.fml.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.FMLSlicerUtilityTest;
import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Excludes;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

@RunWith(Parameterized.class)
public class FMLMedicalImageSliceTest2 extends FMLSlicerUtilityTest {

	protected static String DOT_EXTENSION = ".dot";

	protected static String OUTPUT_FOLDER = "output/dot/";

	protected final String _fmGridSpecification = "GridDeployment : Authentification GridComputingNode [LibraryRequired] ; "
			+ "LibraryRequired: [MatLab] ;\n"
			+ "GridComputingNode: OperatingSystem Processor [FileSizeLimit] ; "
			+ "OperatingSystem: (Windows|Linux) ; Linux : (ScientificLinux|Ubuntu) ; "
			+ "Processor : ProcessorBits [GPU] ; ProcessorBits : (x32|x64) ; "
			+ "Authentification : (PasswordBased|KerberosAuth|SSLAuth) ; " +

			"\n";

	protected final String _fmMISpecification = "MedicalImage: Format ModalityAcquisition ;   \n"
			+ "								 	Format: [MetaDataSupport] FormatName ; "
			+ "										FormatName : (DICOM|Nifti|Analyze) ;  \n"
			+ "										MetaDataSupport : [AnonymizedSupport] ; "
			+ "								 	ModalityAcquisition: (MRI|SPEC|CT|PET) ; \n" + //
			"								 			 	MRI : (T1|T2)+ ; \n";

	protected final String _fmProtoSpecification = "NetworkProtocol : "
			+ "[HeaderEncoding] TransferProtocol NetworkSecurity ; "
			+ "NetworkSecurity: [SSL] [PGP] Crypto ; "
			+ "Crypto: (Symetric|Asymetric) ; "
			+ "Symetric : (KDC|DES|TripleDES) ; "
			+ "TransferProtocol : (HTTPS|HTTP);";

	protected final String _fmAlgoSpecification = "MIAlgorithm : Method [Interactive] ; "
			+ " Method : (Linear|NonGrid|Atlas|Model) ; \n"
			+ "								Atlas : (CFL|EMS) ;   \n"
			+ "								Model : (PAM|BAM) ; "
			+ "								Linear : (Rotation|Scaling|Affine) ; " + "\n";

	protected final String _cstSpecification = "KerberosAuth -> KDC ; "
			+ "SSLAuth <-> SSL ; "
			+ "MRI -> PAM ; "
			+ "CT or SPEC -> BAM ; "
			+ "AnonymizedSupport -> HeaderEncoding ; "
			+ "Interactive -> HeaderEncoding ; "
			+ "GPU -> !Interactive ; "
			+ "Interactive -> Linux ; "
			+
			// "DICOM -> Rotation & PAM ;" +
			"DICOM -> Rotation & PAM ;"
			+
			// internal constraints of Grid Deployment
			"ScientificLinux -> !MatLab ; "
			+
			// internal constraints of Algo
			// "CFL -> PAM ; "
			"EMS -> Affine or Scaling ; "
			// + "Rotation -> Interactive ; "
			// + "Rotation -> !Interactive ; "
			// internal constraints of Network Protocol
			+ "HeaderEncoding -> HTTPS ; "
			// internal constraints of Medical Image
			+ "MetaDataSupport -> DICOM or Analyze ; "
			+ "Analyze -> !CT & !SPEC & !T1 ; "
	// + "DICOM -> Rotation ; "
	;

	// fmService specification
	protected final String _fmServiceSpecification = "FM (MIService : MIAlgorithm MedicalImage GridDeployment NetworkProtocol ; "
			+ _fmAlgoSpecification
			+ _fmMISpecification
			+ _fmGridSpecification
			+ _fmProtoSpecification + _cstSpecification + ")\n";

	private String _fmMedicalExpertExpected = "FM (MedicalImage : ModalityAcquisition Format ; ModalityAcquisition: (PET|CT|MRI) ; "
			+ "Format: FormatName [MetaDataSupport] ; MRI: T2 ; FormatName: (Nifti|Analyze) ; "
			+ "MetaDataSupport: [AnonymizedSupport] ; "
			+ "MetaDataSupport -> Analyze ;" + "Analyze -> !CT ; " + ")";

	public final static int N_TIMES = 1;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[N_TIMES][0]);
	}

	public FMLMedicalImageSliceTest2() {
	}

	@Test
	public void repeatMedicalImage() throws Exception {
		// testMedicalImage1();
		// checkEquivalenceFMService();
		testMedicalImageASE();
		// testIdentityASE();
		// testFmServiceAggregation() ;
	}

	public void testFmServiceAggregation() throws Exception {
		_shell.reset();
		String fmServiceID = "fmService";
		FeatureModelVariable fmvService = getFMServiceFromAggregation(fmServiceID); // getFMService(fmServiceID)
																					// ;
																					// //
																					// //
		fmvService.getFm().removeAllConstraints();
		_shell.setVerbose(false);

		String cstID = "cstID";
		_shell.parse("" + cstID + " = " + "constraints (" + _cstSpecification
				+ ")\n");
		SetVariable csts = getSetVariable(cstID);

		List<Expression<String>> exprs = new ArrayList<Expression<String>>() ; 
		Set<Variable> constraints = csts.getVars() ; 
		for (Variable cst : constraints) {
			assertTrue(cst instanceof ConstraintVariable);
			ConstraintVariable cstv = (ConstraintVariable) cst;
			exprs.add(cstv.getConstraint());
		}
		
		
		
		// ScientificLinux -> !MatLab ;
		for (Expression<String> expr : exprs) {
			System.err.println("adding expr..." + expr);
			Set<Expression<String>> implies = ImplicationGraphUtil
					.toExpressions(fmvService.computeImplicationGraph(_builder));
			Set<Excludes<String>> excludes = fmvService.computeExclusionGraph(
					_builder).edgeSet();
			assertTrue(fmvService.getFm().addConstraint(expr));
			assertFalse(implies.contains(expr));
			assertFalse(excludes.contains(expr));

		}

	}

	public void testIdentityASE() throws Exception {

		_shell.reset();
		String fmServiceID = "fmService";
		FeatureModelVariable fmvService = getFMServiceFromAggregation(fmServiceID); // getFMService(fmServiceID)
																					// ;
																					// //
																					// //

		_shell.setVerbose(true);

		FeatureGraph<String> hierarchy = new FMSlicerBDD(_builder).sliceHierarchy(
				fmvService, fmvService.features().names(), SliceMode.INCLUDING);
		System.out.println("hierarchy=" + hierarchy);
		System.out.println("fm hierarchy="
				+ new FeatureModel<String>(hierarchy));

		/*
		 * FeatureModelVariable fmvServiceCleanUpBySlice =
		 * SliceAnalyzer.sliceFMVlazyConstraint(fmvService,
		 * fmvService.features().names(), SliceMode.INCLUDING, _builder);
		 * System.err.println("\n====== fmvServiceCleanUpBySlice ======\n");
		 * System.err.println("fmvServiceCleanUpBySlice=" +
		 * fmvServiceCleanUpBySlice.getSyntacticalRepresentationWithoutCst());
		 */
		// Formula<String> flaServiceCleanUpBySlice =
		// fmvServiceCleanUpBySlice.getFormula();
		// //SliceAnalyzer.sliceFormula(fmvService,
		// fmvService.features().names(), SliceMode.INCLUDING, _builder);

	}

	@Test
	public void testExpectedViews() throws Exception {

		FeatureModelVariable fmMedicalExpertView = getFMMedicalExpertExpected();
		assertEquals(9, fmMedicalExpertView.counting (CountingStrategy.BDD_FML), 0);

	}

	private FeatureModelVariable getFMMedicalExpertExpected() throws Exception {
		FeatureModelVariable fmMedicalExpert = FM("medicalExpert",
				_fmMedicalExpertExpected);
		return fmMedicalExpert;

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
		FileSerializer.write(OUTPUT_FOLDER + "fmvService-original"
				+ DOT_EXTENSION, fmvService.toDOT());

		String fmSplotStr = fmvService.convert(FMFormat.FSPLOT);
		System.err.println("fmSplotStr=" + fmSplotStr);

		System.err.println("==== initial state ====");
		System.err.println("fmvService=" + fmvService);
		// System.err.println("#fmvService (splot)=" +
		// fmvService.getSPLOTSATReasoner().countValidConfigurations());
		System.err.println("deads fmvService (splot)="
				+ fmvService.getSPLOTSATReasoner().allDeadFeatures(
						new HashMap<String, String>()));
		System.err.println("cores fmvService (splot)="
				+ fmvService.getSPLOTSATReasoner().allCoreFeatures(
						new HashMap<String, String>()));
		System.err.println("#fmvService=" + fmvService.counting (CountingStrategy.BDD_FML));
		Set<String> fmServiceCores = fmvService.cores();
		Set<String> fmServiceDeads = fmvService.deads();
		System.err.println("cores fmvService=" + fmServiceCores);
		System.err.println("deads fmvService=" + fmServiceDeads);

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

		Set<String> unionCores = Sets
				.union(fmvMI.cores(), Sets.union(
						Sets.union(fmvGrid.cores(), fmvAlgo.cores()),
						fmvProto.cores()));
		Set<String> unionDeads = Sets
				.union(fmvMI.deads(), Sets.union(
						Sets.union(fmvGrid.deads(), fmvAlgo.deads()),
						fmvProto.deads()));
		System.err.println("union cores=" + unionCores);
		System.err.println("union deads=" + unionDeads);

		System.err.println("\nInfered\n");
		Set<String> coresInfered = Sets.difference(fmServiceCores, unionCores);
		System.err.println("cores infered=" + coresInfered);
		Set<String> deadsInfered = Sets.difference(fmServiceDeads, unionDeads);
		System.err.println("deads infered=" + deadsInfered);
		FeatureModelVariable fmvServiceCleanUpBySlice = new FMSlicerBDD(_builder).sliceFM(fmvService, fmvService.features()
		.names(), SliceMode.INCLUDING);
		System.err.println("\n====== fmvServiceCleanUpBySlice ======\n");
		System.err.println("fmvServiceCleanUpBySlice="
				+ fmvServiceCleanUpBySlice
						.getSyntacticalRepresentationWithoutCst());
		Formula<String> flaServiceCleanUpBySlice = fmvServiceCleanUpBySlice
				.getFormula(); // SliceAnalyzer.sliceFormula(fmvService,
								// fmvService.features().names(),
								// SliceMode.INCLUDING, _builder);

		// fmvService.getFormula();
		assertEquals(
				0,
				Sets.intersection(flaServiceCleanUpBySlice.getDomain(),
						deadsInfered).size());
		System.err.println("domain=" + flaServiceCleanUpBySlice.getDomain());
		System.err.println("implies constraints="
				+ fmvServiceCleanUpBySlice.computeImpliesEdge(_builder));
		System.err.println("==== end ====");

		// updating the four views

		/*
		 * FeatureModelVariable fmMedicalExpertView = updateMI(fmvService,
		 * "MedicalImage"); assertEquals(Comparison.REFACTORING,
		 * fmMedicalExpertView.compareBDD(getFMMedicalExpertExpected(),
		 * _builder)); updateMI(fmvService, "GridDeployment");
		 * updateMI(fmvService, "MIAlgorithm"); updateMI(fmvService,
		 * "NetworkProtocol");
		 */

		String viewSecuritySpecification = fmServiceID + "."
				+ "GridDeployment.Authentification.* ++ { " + fmServiceID + "."
				+ "GridDeployment.Authentification } " + "++ { " + fmServiceID
				+ "." + "MIService } ++ " + fmServiceID + "."
				+ "NetworkProtocol.*" + " ++ { " + fmServiceID + "."
				+ "NetworkProtocol } ++ {" + fmServiceID + "."
				+ "MedicalImage.MetaDataSupport } ++ " + fmServiceID + "."
				+ "MedicalImage.MetaDataSupport.* ";
		// + "++ { " + fmServiceID + "." + "MIAlgorithm.Interactive }" ;

		extractView2(fmvService, viewSecuritySpecification);

		// assertEquals(ftsViewSecurity.size(),
		// fmViewSecurity.features().size());

		// serializeToS2T2(fmViewSecurity);

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

		// extractView(fmvService, viewMISpecification);

		System.out.println("recap:\n" + _shell.getHistory());

	}

	private void extractView2(FeatureModelVariable fmvService,
			String viewSecuritySpecification) throws Exception {

		System.err.println("\t\t******* (1) fmvService=" + fmvService);
		String viewSecurityID = "viewSecurity";

		_shell.parse(viewSecurityID + " = " + viewSecuritySpecification);

		SetVariable ftsViewSecurity = getSetVariable(viewSecurityID);
		System.err.println("ftsViewSecurity=" + ftsViewSecurity.getValue());

		String fmViewSecurityID = "fmViewSecurity";

		// _shell.parse(fmViewSecurityID + " = " + "slice " + fmServiceID +
		// " including " + viewSecurityID);
		// FeatureModelVariable fmViewSecurity =
		// getFMVariable(fmViewSecurityID);

		FeatureModelVariable fmViewSecurity = new FMSlicerBDD(_builder).sliceFM(fmvService, ftsViewSecurity.names(), SliceMode.INCLUDING);
		//fmViewSecurity.cleanUpConstraints(_builder);

		System.err.println("\t\t******* (2) fmvService=" + fmvService);

		System.err.println("\n\nfmViewSecurity=" + fmViewSecurity);

		System.err.println("#fmViewSecurity=" + fmViewSecurity.counting (CountingStrategy.BDD_FML));
		System.err.println("[[fmViewSecurity]]="
				+ FMLUtils.setConfigToSetStr(fmViewSecurity.configs()));
		System.err.println("implies fmViewSecurity="
				+ fmViewSecurity.computeImpliesEdge(_builder));
		System.err.println("excludes fmViewSecurity="
				+ fmViewSecurity.computeExcludesEdge(_builder));
		FileSerializer.write(OUTPUT_FOLDER + "fmViewSecurity" + DOT_EXTENSION,
				fmViewSecurity.toDOT());
		System.err.println("diff_1"
				+ Sets.difference(ftsViewSecurity.names(), fmViewSecurity
						.features().names()));
		System.err.println("diff_2"
				+ Sets.difference(fmViewSecurity.features().names(),
						ftsViewSecurity.names()));

		analyzeImpliesExcludes(fmvService, fmViewSecurity,
				ftsViewSecurity.names());

	}

	private void extractView(FeatureModelVariable fmvService,
			String viewMISpecification) throws Exception {
		String viewMIID = "viewMI";
		_shell.parse(viewMIID + " = " + viewMISpecification);
		SetVariable ftsViewMI = getSetVariable(viewMIID);
		System.err.println("ftsViewMI=" + ftsViewMI.getValue());
		assertTrue(fmvService.features().names().containsAll(ftsViewMI.names()));
		String fmViewMIID = "fmViewMI";
		// _shell.setVerbose(true);
		// _shell.parse(fmViewMIID + " = " + "slice " + fmServiceID +
		// " including " + viewMIID);
		FeatureModelVariable fmViewMI = // getFMVariable(fmViewMIID);
				new FMSlicerBDD(_builder).sliceFM(fmvService, ftsViewMI.names(), SliceMode.INCLUDING);

		System.err.println("fmViewMI=" + fmViewMI);
		System.err.println("cores fmViewMI=" + fmViewMI.cores());
		System.err.println("deads fmViewMI=" + fmViewMI.deads());
		System.err.println("[[fmViewMI]]="
				+ FMLUtils.setConfigToSetStr(fmViewMI.configs()));
		System.err.println("#fmViewMI=" + fmViewMI.counting (CountingStrategy.BDD_FML));
		System.err.println("implies fmViewMI="
				+ fmViewMI.computeImpliesEdge(_builder));
		System.err.println("excludes fmViewMI="
				+ fmViewMI.computeExcludesEdge(_builder));

		FileSerializer.write(OUTPUT_FOLDER + "fmViewMI" + DOT_EXTENSION,
				fmViewMI.toDOT());
		System.err.println("diff_1"
				+ Sets.difference(ftsViewMI.names(), fmViewMI.features()
						.names()));
		System.err.println("diff_2"
				+ Sets.difference(fmViewMI.features().names(),
						ftsViewMI.names()));
		// System.err.println("fmViewMI=" + ConvertAnalyzer.convert(fmViewMI,
		// FMFormat.FIDE));
		// assertEquals(ftsViewMI.size(), fmViewMI.features().size());

		// serializeToS2T2(fmViewMI) ;

	}

	private FeatureModelVariable updateMI(FeatureModelVariable fmvService,
			String ftRootName) throws Exception {

		Set<String> ftNames = fmvService.features().names();
		assertTrue(ftNames.contains(ftRootName));

		FeatureVariable ftV = new FeatureVariable(ftRootName, fmvService);
		Set<String> ftsToSlice = ftV.descendants().names();
		ftsToSlice.add(ftRootName);

		assertTrue(ftNames.containsAll(ftsToSlice));

		FeatureModelVariable fmvServiceWithoutConstraints = (FeatureModelVariable) fmvService
				.copy();
		fmvServiceWithoutConstraints.getFm().removeAllConstraints();
		FeatureModelVariable fmNaiveView = new FeatureModelVariable(
				"fmNaiveView" + ftRootName,
				fmvServiceWithoutConstraints.extract(ftRootName));
		FileSerializer.write(OUTPUT_FOLDER + ftRootName + "-naiveView"
				+ DOT_EXTENSION, fmNaiveView.toDOT());

		FeatureModelVariable fmView = new FMSlicerBDD(_builder).sliceFM(fmvService, ftsToSlice, SliceMode.INCLUDING);
		Set<Set<String>> fmViewConfigs = FMLUtils.setConfigToSetStr(fmView.configs());
		System.err.println("[[" + ftRootName + "]]=" + fmViewConfigs);
		System.err.println("#" + ftRootName + "="
				+ countingFormula(fmView.getFormula()));
		FileSerializer.write(OUTPUT_FOLDER + ftRootName + "-view"
				+ DOT_EXTENSION, fmView.toDOT());
		System.err.println("\n" + ftRootName + "=" + fmView);

//		fmView.cleanUpConstraints(_builder);
		System.err.println("\n (before cleanup constraints) " + ftRootName
				+ "=" + fmView);

		System.err.println("compare fmView fmNaiveView ="
				+ fmView.compareBDD(fmNaiveView, _builder));

		System.err.println("****** features removed from view:\t"
				+ Sets.difference(fmNaiveView.features().names(), fmView
						.features().names()));

		analyzeImpliesExcludes(fmvService, fmView, ftsToSlice);

		return fmView;

	}

	private void analyzeImpliesExcludes(FeatureModelVariable fmvService,
			FeatureModelVariable fmView, Set<String> ftsToSlice)
			throws Exception {

		Set<String> deads = fmvService.deads();
		Set<String> cores = fmvService.cores();

		System.err.println("\t\tIMPLIES=");
		Set<Expression<String>> implies = fmView.computeImpliesEdge(_builder);
		int nImpl = 0;
		for (Expression<String> impl : implies) {
			System.err.println("impl" + nImpl + "=" + impl);
			nImpl++;
		}
		Set<Expression<String>> toAddInPriority = new HashSet<Expression<String>>();
		Set<Expression<String>> toAddLowPriority = new HashSet<Expression<String>>();
		Set<Expression<String>> originalImplies = fmvService
				.computeImpliesEdge(_builder); // getFm().getRequires() ;
		System.err.println("originalImplies=" + originalImplies);
		for (Expression<String> requires : originalImplies) {

			Set<String> allFTs = ExpressionUtil.getAllFeatures(requires);
			if (Sets.intersection(ftsToSlice, allFTs).equals(allFTs)) { // TOP
																		// priority
				toAddInPriority.add(requires);
			} else {
				Set<String> cFTs = Sets.intersection(allFTs, ftsToSlice);
				if (cFTs.size() == 0)
					continue;
				//
				else {

					Set<String> notIns = Sets.difference(allFTs, ftsToSlice);
					for (String notIn : notIns) {
						Expression<String> newRequires = null;
						if (cores.contains(notIn)) {
							newRequires = ExpressionUtility
									.replaceOccurenceInExpression(notIn, "1",
											requires);
						} else if (deads.contains(notIn)) {
							newRequires = ExpressionUtility
									.replaceOccurenceInExpression(notIn, "0",
											requires);
						} else
							newRequires = ExpressionUtility
									.replaceOccurenceInExpression(notIn,
											"(0 | 1)", requires);

						if (ExpressionUtility
								.isTautology(newRequires, _builder)) {
							System.err.println("tautology detected -- "
									+ newRequires + " won't be added");
						} else
							toAddLowPriority.add(newRequires);
					}

				}

			}
		}
		System.err.println("\t\tselected IMPLIES=");
		System.err.println("\t\thigh priority=");
		for (Expression<String> e : toAddInPriority) {
			System.err.println("\t\t" + e);
		}
		System.err.println("\t\tlow priority=");
		for (Expression<String> e : toAddLowPriority) {
			System.err.println("\t\t" + e);
		}

		System.err.println("\t\tEXCLUDES=");
		Set<Expression<String>> excludes = fmView.computeExcludesEdge(_builder);
		int nExcl = 0;
		for (Expression<String> excl : excludes) {
			System.err.println("excl" + nExcl + "=" + excl);
			nExcl++;
		}

		Set<Expression<String>> exclToAddInPriority = new HashSet<Expression<String>>();
		Set<Expression<String>> exclToAddLowPriority = new HashSet<Expression<String>>();
		Set<Excludes<String>> originalExcludes = fmvService.getFm()
				.getExcludes();
		for (Excludes<String> excl : originalExcludes) {
			Set<String> allFTs = ExpressionUtil.getAllFeatures(excl);
			if (ftsToSlice.containsAll(allFTs)) { // TOP priority
				exclToAddInPriority.add(excl);
			} else {
				Set<String> cFTs = Sets.intersection(allFTs, ftsToSlice);
				if (cFTs.size() == 0)
					continue;
				//
				else {

					Set<String> notIns = Sets.difference(allFTs, ftsToSlice);
					for (String notIn : notIns) {
						Expression<String> newExcludes = null;
						if (cores.contains(notIn)) {
							newExcludes = ExpressionUtility
									.replaceOccurenceInExpression(notIn, "1",
											excl);
						} else if (deads.contains(notIn)) {
							newExcludes = ExpressionUtility
									.replaceOccurenceInExpression(notIn, "0",
											excl);
						} else
							newExcludes = ExpressionUtility
									.replaceOccurenceInExpression(notIn,
											"(0 | 1)", excl);

						if (ExpressionUtility
								.isTautology(newExcludes, _builder)) {
							System.err.println("tautology detected -- "
									+ newExcludes + " won't be added");
						} else
							exclToAddLowPriority.add(newExcludes);
					}

				}

			}
		}
		System.err.println("\t\tselected EXCLUDES=");
		System.err.println("\t\thigh priority=");
		for (Expression<String> e : exclToAddInPriority) {
			System.err.println("\t\t" + e);
		}
		System.err.println("\t\tlow priority=");
		for (Expression<String> e : exclToAddLowPriority) {
			System.err.println("\t\t" + e);
		}

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

		_shell.setVerbose(true);
		_shell.parse(aggregateInstruction + "\n");
		_shell.setVerbose(false);
		FeatureModelVariable fmvService = getFMVariable(fmServiceID);
		String rootName = fmvService.root().name();
		System.err.println("rootName=" + rootName);
		fmvService.renameFeature(rootName, "MIService");

		/*
		 * _shell.getCurrentEnv().removeVariable(fmvProto);
		 * _shell.getCurrentEnv().removeVariable(fmvMI);
		 * _shell.getCurrentEnv().removeVariable(fmvGrid);
		 * _shell.getCurrentEnv().removeVariable(fmvAlgo);
		 */

		return fmvService;
	}

}
