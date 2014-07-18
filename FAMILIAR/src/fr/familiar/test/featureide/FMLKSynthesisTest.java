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

package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.KSynthesis;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.operations.SlicerStrategy;
import fr.familiar.operations.featureide.KSynthesisSAT;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureGraph;

public class FMLKSynthesisTest extends FMLTest {
	
	
	
	@Test
	public void testSlicingSAT() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B [C] D [E] ; E : (F|G|H) ;  ");
		fmv1.setBuilder(_builder);  
		FeatureModelVariable fmv1BDD = fmv1.slice(SliceMode.EXCLUDING, "G", "D") ; 
		fmv1.setSlicerStrategy(SlicerStrategy.SAT);
		_shell.setVerbose(true);
		FeatureModelVariable fmv1SAT = fmv1.slice(SliceMode.EXCLUDING, "G", "D") ;
		
		System.err.println("fm1BDD=" + fmv1BDD);
		System.err.println("fm1SAT=" + fmv1SAT);
		
	}

	
	@Test
	public void testSAT() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C D ;");
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		FeatureGraph<String> h1 = fmv1.getFm().getDiagram() ; 
		kn1.setHierarchy(h1);
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		FeatureModelVariable fmv1bis = ksynthesis.build() ;
		assertNotNull(fmv1bis);
		
		// pure refactoring
		System.err.println("fm1bis=" + fmv1bis);
		
		FeatureGraph<String> h2 = FM ("h2", "A : B ; B : C D ;").getFm().getDiagram() ;  
		kn1.setHierarchy(h2);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		FeatureModelVariable fmv1bis2 = ksynthesis.build() ;
		assertNotNull(fmv1bis2);
		
		// different hierarchy
		System.err.println("fm1bis2=" + fmv1bis2);
		
		KSynthesis ksynthesis2 = 
				new KSynthesisSAT(fmv1, new KnowledgeSynthesis(fmv1.getHierarchy().getDiagram().clone())) ;
		FeatureModelVariable fmv2 = ksynthesis2.build() ;
		
		System.err.println("fmv2=" + fmv2);
		assertEquals(fmv1.features().size(), fmv2.features().size());
		assertEquals(fmv1.root().name(), fmv2.root().name());
		
		
	}
	
	@Test
	public void testOverlappingGroups1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "S : (E|F) [D] ; E <-> D ; "); // D and F can form an Xor-group as well
	
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		FeatureGraph<String> h1 = fmv1.getHierarchy().getDiagram() ; //getFm().getDiagram() ; 
		kn1.setHierarchy(h1);
		System.err.println("h1="+ h1);
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		//_shell.setVerbose(true); 
		FeatureModelVariable fmv1bis = ksynthesis.build() ;
		assertTrue(ksynthesis.hasConflictingChoices()); // since no group have been specified
		assertEquals(2, ksynthesis.getConflictingGroups().size()); 
		assertNotNull(ksynthesis.getConflictReport()); 
		
		System.err.println("" + ksynthesis.getConflictReport());
		
		assertNotNull(fmv1bis);
		System.err.println("fm1bis=" + fmv1bis);
		
		
	}
	@Test
	public void testSimple1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C D ;");
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		FeatureGraph<String> h1 = fmv1.getFm().getDiagram() ; 
		kn1.setHierarchy(h1);
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		FeatureModelVariable fmv1bis = ksynthesis.build() ;
		assertNotNull(fmv1bis);
		
		// pure refactoring
		System.err.println("fm1bis=" + fmv1bis);
		
		FeatureGraph<String> h2 = FM ("h2", "A : B ; B : C D ;").getFm().getDiagram() ;  
		kn1.setHierarchy(h2);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		FeatureModelVariable fmv1bis2 = ksynthesis.build() ;
		assertNotNull(fmv1bis2);
		
		// different hierarchy
		System.err.println("fm1bis2=" + fmv1bis2);
	}

	
	@Test
	public void testSimple2() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C D ; D : (E|F|G) ; ");
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		FeatureGraph<String> h1 = fmv1.getFm().getDiagram() ; 
		kn1.setHierarchy(h1);
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis);
		
		// pure refactoring
		System.err.println("fm1bis=" + fmv1bis);
		
		FeatureGraph<String> h2 = mkHierarchy("A : B ; B : C ; C : D ; D : E F G ; "); 
		
		
		kn1.setHierarchy(h2);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis2 = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis2);
		
		// different hierarchy
		System.err.println("fm1bis2=" + fmv1bis2);
		System.err.println("#fm1bis2=" + fmv1bis2.counting());
	}
	
	@Test
	public void testSimple3() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C D ; C : (H|I) ; D : (E|F|G) ; ");
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		FeatureGraph<String> h1 = fmv1.getFm().getDiagram() ; 
		kn1.setHierarchy(h1);
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis);
		
		// pure refactoring
		System.err.println("fm1bis=" + fmv1bis);
		
		FeatureGraph<String> h2 = mkHierarchy("A : B ; B : C ; C : D H I ; D : E F G ; "); 
		
		
		kn1.setHierarchy(h2);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis2 = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis2);
		
		// different hierarchy
		System.err.println("fm1bis2=" + fmv1bis2);
		System.err.println("#fm1bis2=" + fmv1bis2.counting());
		
		FeatureGraph<String> h3 = mkHierarchy("A : B C D H I ; D : E F G ; "); 
		
		
		kn1.setHierarchy(h3);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis3 = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis3);
		
		// different hierarchy
		System.err.println("fm1bis3=" + fmv1bis3);
		System.err.println("#fm1bis3=" + fmv1bis3.counting());
		
		FeatureGraph<String> h4 = mkHierarchy("A : B C D H I E F G ; "); 
		
		
		kn1.setHierarchy(h4);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis4 = ksynthesis.build() ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis4);
		
		// different hierarchy
		System.err.println("fm1bis4=" + fmv1bis4);
		System.err.println("#fm1bis4=" + fmv1bis4.counting());
	}
	
	
	@Test
	public void testSimple4() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C D ; C : (H|I) ; D : (E|F|G) ; ");
		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		KSynthesisBDD ksynthesis = new KSynthesisBDD(fmv1, kn1, _builder) ; 
		FeatureGraph<String> h2 = mkHierarchy("A : B D ; D : E F ; "); 
		kn1.setHierarchy(h2);
		
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		_shell.setVerbose(true);
		FeatureModelVariable fmv1bis2 = ksynthesis.buildOver(h2.features()) ;
		_shell.setVerbose(false);
		assertNotNull(fmv1bis2);
		
		System.err.println("fmv1bis2=" + fmv1bis2);
		
	
	}
	
	@Test
	public void testLopez() throws Exception {
		
		_shell.parse("X1_VOD = FM (VOD :  V P R D O T [M] [S] K Ad Ae [C] ; VOD -> !M; VOD -> !S; VOD -> !C;  )\n" + 
				"\n" + 
				"X4_VOD = FM (VOD :  V P [R] D O T [M] [S] K [Ad] Ae [C] ; VOD -> !R; VOD -> !M; VOD -> !S; VOD -> !Ad; VOD -> !C;  )\n" + 
				"\n" + 
				"X13_VOD = FM (VOD :  V P R D O [T] M S K Ad [Ae] [C] ; VOD -> !T; VOD -> !Ae; VOD -> !C;  )\n" + 
				"\n" + 
				"X5_VOD = FM (VOD :  V P R D O T [M] [S] K Ad Ae C ; VOD -> !M; VOD -> !S;  )\n" + 
				"\n" + 
				"X15_VOD = FM (VOD :  V P [R] D O [T] M S K Ad [Ae] [C] ; VOD -> !R; VOD -> !T; VOD -> !Ae; VOD -> !C;  )\n" + 
				"\n" + 
				"X14_VOD = FM (VOD :  V P R D O [T] M S K [Ad] [Ae] [C] ; VOD -> !T; VOD -> !Ad; VOD -> !Ae; VOD -> !C;  )\n" + 
				"\n" + 
				"X12_VOD = FM (VOD :  V P [R] D O T [M] [S] K [Ad] [Ae] C ; VOD -> !R; VOD -> !M; VOD -> !S; VOD -> !Ad; VOD -> !Ae;  )\n" + 
				"\n" + 
				"X8_VOD = FM (VOD :  V P R D O T [M] [S] K [Ad] [Ae] C ; VOD -> !M; VOD -> !S; VOD -> !Ad; VOD -> !Ae;  )\n" + 
				"\n" + 
				"X3_VOD = FM (VOD :  V P [R] D O T [M] [S] K Ad Ae [C] ; VOD -> !R; VOD -> !M; VOD -> !S; VOD -> !C;  )\n" + 
				"\n" + 
				"X11_VOD = FM (VOD :  V P [R] D O T [M] [S] K [Ad] Ae C ; VOD -> !R; VOD -> !M; VOD -> !S; VOD -> !Ad;  )\n" + 
				"\n" + 
				"X7_VOD = FM (VOD :  V P R D O T [M] [S] K [Ad] Ae C ; VOD -> !M; VOD -> !S; VOD -> !Ad;  )\n" + 
				"\n" + 
				"X16_VOD = FM (VOD :  V P [R] D O [T] M S K [Ad] [Ae] [C] ; VOD -> !R; VOD -> !T; VOD -> !Ad; VOD -> !Ae; VOD -> !C;  )\n" + 
				"\n" + 
				"X10_VOD = FM (VOD :  V P [R] D O T [M] [S] K Ad [Ae] C ; VOD -> !R; VOD -> !M; VOD -> !S; VOD -> !Ae;  )\n" + 
				"\n" + 
				"X9_VOD = FM (VOD :  V P [R] D O T [M] [S] K Ad Ae C ; VOD -> !R; VOD -> !M; VOD -> !S;  )\n" + 
				"\n" + 
				"X2_VOD = FM (VOD :  V P R D O T [M] [S] K [Ad] Ae [C] ; VOD -> !M; VOD -> !S; VOD -> !Ad; VOD -> !C;  )\n" + 
				"\n" + 
				"X6_VOD = FM (VOD :  V P R D O T [M] [S] K Ad [Ae] C ; VOD -> !M; VOD -> !S; VOD -> !Ae;  )\n" + 
				"\n" + 
				"fm_X10 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X10_*) do\n" + 
				"insert f into fm_X10.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X10.ID\n" + 
				"\n" + 
				"fm_X14 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X14_*) do\n" + 
				"insert f into fm_X14.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X14.ID\n" + 
				"\n" + 
				"fm_X1 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X1_*) do\n" + 
				"insert f into fm_X1.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X1.ID\n" + 
				"\n" + 
				"fm_X13 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X13_*) do\n" + 
				"insert f into fm_X13.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X13.ID\n" + 
				"\n" + 
				"fm_X2 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X2_*) do\n" + 
				"insert f into fm_X2.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X2.ID\n" + 
				"\n" + 
				"fm_X12 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X12_*) do\n" + 
				"insert f into fm_X12.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X12.ID\n" + 
				"\n" + 
				"fm_X3 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X3_*) do\n" + 
				"insert f into fm_X3.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X3.ID\n" + 
				"\n" + 
				"fm_X11 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X11_*) do\n" + 
				"insert f into fm_X11.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X11.ID\n" + 
				"\n" + 
				"fm_X4 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X4_*) do\n" + 
				"insert f into fm_X4.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X4.ID\n" + 
				"\n" + 
				"fm_X5 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X5_*) do\n" + 
				"insert f into fm_X5.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X5.ID\n" + 
				"\n" + 
				"fm_X6 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X6_*) do\n" + 
				"insert f into fm_X6.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X6.ID\n" + 
				"\n" + 
				"fm_X16 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X16_*) do\n" + 
				"insert f into fm_X16.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X16.ID\n" + 
				"\n" + 
				"fm_X15 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X15_*) do\n" + 
				"insert f into fm_X15.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X15.ID\n" + 
				"\n" + 
				"fm_X7 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X7_*) do\n" + 
				"insert f into fm_X7.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X7.ID\n" + 
				"\n" + 
				"fm_X8 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X8_*) do\n" + 
				"insert f into fm_X8.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X8.ID\n" + 
				"\n" + 
				"fm_X9 = FM(SyntheticRoot : ID ; )\n" + 
				"foreach(f in X9_*) do\n" + 
				"insert f into fm_X9.SyntheticRoot with mand\n" + 
				"end\n" + 
				"removeFeature fm_X9.ID\n" + 
				"\n" + 
				""); 
		
		for (int i = 1 ; i <= 16 ; i++) {
			FeatureModelVariable fm = getFMVariable("fm_X" + i);
			fm.setBuilder(_builder); 
			fm = fm.slice(SliceMode.EXCLUDING, "SyntheticRoot");
			System.err.println("fm_" + i + " = FM (" +  fm + " )");
			
		}
		_shell.setVerbose(true);
		_shell.parse("fmMerged = merge sunion fm*"); 
		_shell.setVerbose(false);
		FeatureModelVariable fmvMerged = getFMVariable("fmMerged");
		fmvMerged.setBuilder(_builder); 
		fmvMerged = fmvMerged.slice(SliceMode.EXCLUDING, "SyntheticRoot");
		System.err.println("#fmvMerged=" + fmvMerged.counting());
		System.err.println("fmvMerged=" + fmvMerged);
		System.err.println("groups fmvMerged=" + fmvMerged.computeGroups());
		System.err.println("mtx fmvMerged=" + fmvMerged.computeMutexGroups());
		
		KnowledgeSynthesis kn = new KnowledgeSynthesis() ;
		kn.setHierarchy(mkHierarchy("VOD : V P R D O ; O : K Ad ; D : T M ; T : Ae C ; M : S ; "));
		KSynthesisBDD kSynthesis = new KSynthesisBDD(fmvMerged.getFormula(), kn, _builder) ;
		FeatureModelVariable fmvMerged2 = kSynthesis.build() ;
		//fmvMerged2 = fmvMerged2.slice(SliceMode.EXCLUDING, _builder, "SyntheticRoot");
		System.err.println("#fmvMerged2=" + fmvMerged2.counting());
		System.err.println("fmvMerged2=" + fmvMerged2);
		
		System.err.println("excludes=" + fmvMerged2.computeExcludesEdge(_builder));
		
		
		
	}
	
	@Test
	public void testIllustrativeExamples() throws Exception {
		
		
		FeatureModelVariable fmv0 = FM ("fm0", "MI: ModalityAcquisition Format ; \n" + 
				"		ModalityAcquisition: (CT|MRI) ; \n" + 
				"		Format: (DICOM|Nifti) [Anonymized] ; \n" + 
				"		(DICOM -> MRI);\n" + 
				"		(MRI -> Anonymized);\n" + 
				"		(Nifti -> CT);") ;

		FeatureModelVariable fmv1 = FM ("fm1", "MI: [Anonymized] ModalityAcquisition Format ; \n" + 
				"		ModalityAcquisition: (DICOM|Nifti) ; \n" + 
				"		Format: (CT|MRI) ; \n" + 
				"		(Nifti -> CT);\n" + 
				"		(CT -> !DICOM);\n" + 
				"		(CT | Anonymized);"); 
		
		FeatureModelVariable fmv2 = FM ("fm2", "MI: [Anonymized] ModalityAcquisition Format ; \n" + 
				"		ModalityAcquisition: (CT|MRI) ; \n" + 
				"		CT: [Nifti] ; \n" + 
				"		MRI: [DICOM] ; \n" + 
				"		(MRI -> Anonymized);\n" + 
				"		(DICOM | Nifti);\n" + 
				"		(DICOM -> !Nifti);") ; 
		
		assertEquals(Comparison.REFACTORING, fmv0.compareBDD(fmv2, _builder));
		assertEquals(Comparison.REFACTORING, fmv1.compareBDD(fmv2, _builder));
		
		
		
		KnowledgeSynthesis kst1 = new KnowledgeSynthesis() ; 
		kst1.setHierarchy(mkHierarchy("MI: Anonymized ModalityAcquisition Format ; \n" + 
				"		ModalityAcquisition: DICOM Nifti ; \n" + 
				"		Format: CT MRI ; \n"));
		KSynthesisBDD kSynthesis1 = new KSynthesisBDD(fmv0, kst1, _builder);
		FeatureModelVariable fmv1bis = kSynthesis1.build(); 
		System.err.println("fmv1bis=" + fmv1bis);
		assertEquals(Comparison.REFACTORING, fmv1.compareBDD(fmv1bis, _builder));
		
		KnowledgeSynthesis kst2 = new KnowledgeSynthesis() ; 
		kst2.setHierarchy(mkHierarchy("MI: Anonymized ModalityAcquisition Format ; \n" + 
		"		ModalityAcquisition: CT MRI ; \n" + 
		"		CT: Nifti ; \n" + 
		"		MRI: DICOM ; \n"));
		KSynthesisBDD kSynthesis2 = new KSynthesisBDD(fmv0, kst2, _builder);
		FeatureModelVariable fmv2bis = kSynthesis2.build(); 
		System.err.println("fmv2bis=" + fmv2bis);
		System.err.println("#fmv2bis=" + fmv2bis.counting());
		System.err.println("[[fmv2bis]]=" + FMLUtils.setConfigToSetStr(fmv2bis.configs()));
		assertEquals(Comparison.REFACTORING, fmv1.compareBDD(fmv2bis, _builder));
		
	}


	private FeatureGraph<String> mkHierarchy(String hSpecification) throws Exception {
		FeatureModelVariable fm0 = FM ("h", hSpecification) ;
		SetVariable fts = fm0.features() ;
		for (Variable v : fts.getVars()) {
			FeatureVariable ft = (FeatureVariable) v ;
			if (!ft.isRoot())
				ft.setOptionalStatus() ; 
		}
		
		return fm0.getFm().getDiagram() ;		
	}
}
