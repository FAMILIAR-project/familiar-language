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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.experimental.FeatureMapping;
import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.FDOverApproximationStrategy;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.Mode;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Formula;

public class FMLRewritingTest extends FMLTest {
	
	
	@Test
	public void testRefactoring() throws Exception {
		
		String runningExample = "FM (MedicalImage : [Anonymized] MRI [Header] [DICOM] ; MRI : [T1] [T2] ; "
			+ "(!Header | !DICOM) ; "
			+ "(Header -> Anonymized); "
			+ "(Anonymized | Header | !DICOM | !T1 | !T2) ;"
			+ "(Anonymized | Header | DICOM | !T1 | !T2) ; " + ")";
		
		FeatureModelVariable fm1 = FM ("fm1", runningExample) ; 
		
		System.err.println("#fm1=" + fm1.counting());
		
		// basically: move Header below DICOM
		moveBelow("Header", "DICOM", fm1); // not a refactoring! aka by moving Header below DICOM you cannot have a refactored feature model
		moveBelow("Header", "MRI", fm1); // refactoring is possible and we synthesize it for you
		moveBelow("T1", "MedicalImage", fm1);
	
				
	}
	
	@Test
	public void testReconciliationClarke1() throws Exception {
		
		// consumerItems : ConsumerItems tv book iPod newspaper cd
		// F : ConsumerItems : Electronic book newspaper 
		// G : ConsumerItems tv iPod Paper 
		
		// tv, iPod => Electronic
		// book, newspaper => Paper
		
		List<String> sP = Arrays.asList(new String[] { "FM ( Electronic ; ) ", "FM ( Electronic : book ; ) ",  "FM ( Electronic : book newspaper ; ) " });
		int iP = 0 ; 
		List<FeatureModelVariable> fmvsP = new ArrayList<FeatureModelVariable>();
		for (String p : sP) {
			fmvsP.add(FM ("P" + iP++, p));
		}
		_shell.setVerbose(true);
		FeatureModelVariable P = new FMLMergerBDD(fmvsP, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion);
		
		List<String> sQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : iPod Paper ; ) " });
		int iQ = 0 ; 
		List<FeatureModelVariable> fmvsQ = new ArrayList<FeatureModelVariable>();
		for (String q : sQ) {
			fmvsQ.add(FM ("Q" + iQ++, q));
		}
		FeatureModelVariable Q = new FMLMergerBDD(fmvsQ, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion);
	
		
		
		Set<FeatureMapping> ftMappingsP = new HashSet<FeatureMapping>(); 		
		ftMappingsP.add(
				new FeatureMapping("Electronic", Arrays.asList(new String[] { "tv", "iPod" })));
		ftMappingsP.add(
				new FeatureMapping(Arrays.asList(new String[] { "book", "newspaper" }), "Paper")); 
		FeatureModelVariable Pprime = abstractFtsByFt(ftMappingsP, P);
		
		
		Set<FeatureMapping> ftMappingsQ = new HashSet<FeatureMapping>(); 		
		ftMappingsQ.add(new FeatureMapping("Paper", Arrays.asList(new String[] { "book", "newspaper" })));
		FeatureModelVariable Qprime = abstractFtsByFt(ftMappingsQ, Q);
		
		
		
		System.err.println("P=" + P);
		System.err.println("P'=" + Pprime);
		System.err.println("[[P']]=" + FMLUtils.setConfigToSetStr(Pprime.configs()));
		
		System.err.println("Q=" + Q);
		System.err.println("Q'=" + Qprime);
		
		HashSet<String> fakeFt = new HashSet<String>();
		fakeFt.add("fakeRoot");
		Set<Set<String>> pConfigs = new HashSet<Set<String>>();
		for (Set<String> conf : FMLUtils.setConfigToSetStr(Pprime.configs())) {
			pConfigs.add(Sets.difference(conf, fakeFt));
		}
		System.err.println("[[P']]=" + pConfigs);
		
		Set<Set<String>> qConfigs = FMLUtils.setConfigToSetStr(Qprime.configs()) ; 
		System.err.println("[[Q']]=" + qConfigs);
		
		Set<Set<String>> rConfigs = Sets.intersection(pConfigs, qConfigs);
		System.err.println("rConfigs=" + rConfigs);
		
		// reconciliation
		List<String> rQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : iPod book ; ) ",  "FM ( tv : iPod book newspaper ; ) "});
		
	
				
	}
	
	@Test
	public void testReconciliationClarke2() throws Exception {
		
		// consumerItems : ConsumerItems tv book iPod newspaper cd
		// F : ConsumerItems : Electronic book newspaper 
		// G : ConsumerItems tv iPod Paper cd
		
		// tv, iPod => Electronic
		// book, newspaper => Paper
		// cd => nothing
		
		List<String> sP = Arrays.asList(new String[] { "FM ( Electronic ; ) ", "FM ( Electronic : book ; ) ",  "FM ( Electronic : book newspaper ; ) " });
		int iP = 0 ; 
		List<FeatureModelVariable> fmvsP = new ArrayList<FeatureModelVariable>();
		for (String p : sP) {
			fmvsP.add(FM ("P" + iP++, p));
		}
		FeatureModelVariable P =new FMLMergerBDD(fmvsP, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion); 
		P.insert(FM("cdAdded", "FM (cd ; )"), "Electronic", FeatureEdgeKind.OPTIONAL); // since 'cd' is not abstracted! (no mapping)
		
		List<String> sQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : iPod Paper cd ; ) " });
		int iQ = 0 ; 
		List<FeatureModelVariable> fmvsQ = new ArrayList<FeatureModelVariable>();
		for (String q : sQ) {
			fmvsQ.add(FM ("Q" + iQ++, q));
		}
		FeatureModelVariable Q = new FMLMergerBDD(fmvsQ, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion); 
	
		
		
		Set<FeatureMapping> ftMappingsP = new HashSet<FeatureMapping>(); 		
		ftMappingsP.add(new FeatureMapping("Electronic", Arrays.asList(new String[] { "tv", "iPod" }))); // new FeatureMapping(Arrays.asList(new String[] { "tv", "iPod" }), "Electronic")); // 
		FeatureModelVariable Pprime = abstractFtsByFt(ftMappingsP, P);
		
		
		Set<FeatureMapping> ftMappingsQ = new HashSet<FeatureMapping>(); 		
		ftMappingsQ.add(new FeatureMapping("Paper", Arrays.asList(new String[] { "book", "newspaper" })));
		FeatureModelVariable Qprime = abstractFtsByFt(ftMappingsQ, Q);
		
		
		
		System.err.println("P=" + P);
		System.err.println("P'=" + Pprime);
		System.err.println("[[P']]=" + FMLUtils.setConfigToSetStr(Pprime.configs()));
		
		System.err.println("Q=" + Q);
		System.err.println("Q'=" + Qprime);
		
		HashSet<String> fakeFt = new HashSet<String>();
		fakeFt.add("fakeRoot");
		Set<Set<String>> pConfigs = new HashSet<Set<String>>();
		for (Set<String> conf : FMLUtils.setConfigToSetStr(Pprime.configs())) {
			pConfigs.add(Sets.difference(conf, fakeFt));
		}
		System.err.println("[[P']]=" + pConfigs);
		
		Set<Set<String>> qConfigs = FMLUtils.setConfigToSetStr(Qprime.configs()) ; 
		System.err.println("[[Q']]=" + qConfigs);
		
		Set<Set<String>> rConfigs = Sets.intersection(pConfigs, qConfigs);
		System.err.println("rConfigs=" + rConfigs);
		
		// reconciliation
		List<String> rQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : cd iPod book ; ) ",  "FM ( tv : cd iPod book newspaper ; ) "});
		
	
				
	}
	
	
	@Test
	public void testReconciliationClarke3() throws Exception {
		
		// consumerItems : ConsumerItems tv book iPod newspaper cd
		// F : ConsumerItems : Electronic book newspaper 
		// G : ConsumerItems tv iPod Paper 
		
		// tv, iPod => Electronic
		// book, newspaper => Paper
		
		List<String> sP = Arrays.asList(new String[] { "FM ( Electronic ; ) ", "FM ( Electronic : book ; ) ",  "FM ( Electronic : book newspaper ; ) " });
		int iP = 0 ; 
		List<FeatureModelVariable> fmvsP = new ArrayList<FeatureModelVariable>();
		for (String p : sP) {
			fmvsP.add(FM ("P" + iP++, p));
		}
		FeatureModelVariable P = new FMLMergerBDD(fmvsP, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion); 
		
		List<String> sQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : iPod Paper ; ) ", "FM ( Paper ; ) " }); // the difference compared to Clarke1
		int iQ = 0 ; 
		List<FeatureModelVariable> fmvsQ = new ArrayList<FeatureModelVariable>();
		for (String q : sQ) {
			fmvsQ.add(FM ("Q" + iQ++, q));
		}
		FeatureModelVariable Q = new FMLMergerBDD(fmvsQ, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion); 
		
		
		Set<FeatureMapping> ftMappingsP = new HashSet<FeatureMapping>(); 		
		ftMappingsP.add(new FeatureMapping("Electronic", Arrays.asList(new String[] { "tv", "iPod" }))); // new FeatureMapping(Arrays.asList(new String[] { "tv", "iPod" }), "Electronic")); // 
		FeatureModelVariable Pprime = abstractFtsByFt(ftMappingsP, P);
		
		
		Set<FeatureMapping> ftMappingsQ = new HashSet<FeatureMapping>(); 		
		ftMappingsQ.add(new FeatureMapping("Paper", Arrays.asList(new String[] { "book", "newspaper" })));
		FeatureModelVariable Qprime = abstractFtsByFt(ftMappingsQ, Q);
		
		
		
		System.err.println("P=" + P);
		System.err.println("P'=" + Pprime);
		System.err.println("[[P']]=" + FMLUtils.setConfigToSetStr(Pprime.configs()));
		
		System.err.println("Q=" + Q);
		System.err.println("Q'=" + Qprime);
		
		HashSet<String> fakeFt = new HashSet<String>();
		fakeFt.add("fakeRoot"); // TODO: call the constant name of fake root
		Set<Set<String>> pConfigs = new HashSet<Set<String>>();
		for (Set<String> conf : FMLUtils.setConfigToSetStr(Pprime.configs())) {
			pConfigs.add(Sets.difference(conf, fakeFt));
		}
		System.err.println("[[P']]=" + pConfigs);
		
		Set<Set<String>> qConfigs = FMLUtils.setConfigToSetStr(Qprime.configs()) ; 
		System.err.println("[[Q']]=" + qConfigs);
		
		Set<Set<String>> rConfigs = Sets.intersection(pConfigs, qConfigs);
		System.err.println("rConfigs=" + rConfigs);
		
		// reconciliation
		List<String> rQ = Arrays.asList(new String[] { "FM ( tv ; ) ", "FM ( tv : iPod book ; ) ",  "FM ( tv : iPod book newspaper ; ) "});
		
	
				
	}
	
	@Test
	public void testReconciliationClarke3bis() throws Exception {
		
		_shell.parse("fmElectronic = merge sunion { FM ( items: Electronic ; ) " +
				"FM ( items : Electronic book ; ) FM ( items : Electronic book newspaper ; ) }" +
				"fmTv = merge sunion { FM ( items : tv ; tv : (ThreeD|HQ) ; ) FM ( items : tv iPod Paper ; tv : (ThreeD|HQ) ; ) FM (items : Paper ; )  } " +
				"alignDirectives = constraints (Electronic <-> (tv | iPod) ; Paper <-> (book | newspaper) ; ) " +
				"fmTvElectronic = aggregate { fmElectronic fmTv } withMapping alignDirectives " ) ;

		FeatureModelVariable fmTvElectronic = getFMVariable("fmTvElectronic");
		FeatureModelVariable fmTv = getFMVariable("fmTv")  ;
		_shell.setVerbose(true);
		System.err.println("#fmTv=" + fmTv.counting (CountingStrategy.BDD_FML));
		Formula<String> fmTvCheckFla = new FMSlicerBDD(_builder).sliceFormula(fmTvElectronic, fmTv.features().names(), SliceMode.INCLUDING);
		System.err.println("#flaCheckTv=" + new FormulaAnalyzer<String>(fmTvCheckFla, _builder).counting());
		fmTvElectronic.setBuilder(_builder);  
		FeatureModelVariable fmTvCheck = fmTvElectronic.slice(SliceMode.INCLUDING, fmTv.features().names()) ; 
		
		_shell.setVerbose(false);
		
	}
	
	@Test
	public void testReconciliationClarke1bis() throws Exception {
		
		
		FeatureModelVariable fmTvElectronic = FM ("fmTvElectronic", "fmTvElectronic: tv Electronic ; tv: [Paper] [iPod] ;	Electronic: [newspaper] [book] ;" + 
							"(Paper <-> iPod); (Electronic <-> (tv | iPod)); (Paper <-> (book | newspaper)); (newspaper -> book);");

		System.err.println("cores: " + fmTvElectronic.cores()); 
		Set<String> fts = new HashSet<String>(Arrays.asList(new String[] { "tv", "iPod", "newspaper", "book" })) ; 
		_shell.setVerbose(true) ; 
		//FeatureGraph<String> hierarchy = SliceAnalyzer.sliceHierarchy(fmTvElectronic, fts, SliceMode.INCLUDING, _builder);
		// TODO		
		fmTvElectronic.setBuilder(_builder); 
		FeatureModelVariable fmReconciled = fmTvElectronic.slice(SliceMode.INCLUDING, fts);
		System.err.println("fmReconciled: " + fmReconciled); 
		_shell.setVerbose(false) ; 
		System.err.println("cores: " + fmReconciled.cores()); 
		Formula<String> slicedFla = new FMSlicerBDD(_builder).sliceFormula(fmTvElectronic, fts, SliceMode.INCLUDING);
		System.err.println("slicedFla:\n\t" +  slicedFla + " vs\n\t" + fmReconciled.getFormula());
		
		System.err.println("#slicedFla: " + new FormulaAnalyzer<String>(slicedFla, _builder).counting()); 
	}

	private FeatureModelVariable abstractFtsByFt(Set<FeatureMapping> ftMappings,	FeatureModelVariable fm) throws Exception {
		
		Set<String> toExcludes = new HashSet<String>() ; 
		Set<String> toIncludes = new HashSet<String>() ; 
		FeatureModelVariable fmCopy = (FeatureModelVariable) fm.copy() ; 
		int nMapping = 0 ; 
		for (FeatureMapping ftMapping : ftMappings) {
			List<String> left = ftMapping.getLeft() ;
			List<String> right = ftMapping.getRight() ;
			String cst = mkOrConstraint (left) + " <-> " + mkOrConstraint(right) ;   // mkAndConstraint (left) + " <-> " + mkAndConstraint(right) //  "&" + mkOrConstraint(right) + 
			fmCopy.addConstraint(mkConstraintVariable("mapping" + nMapping++, cst));
			toExcludes.addAll(left);
			toIncludes.addAll(right);
		}
		for (String toInclude : toIncludes) {
			fmCopy.insert(FM ("a", "FM (" + toInclude +  "; )"), fmCopy.root(), FeatureEdgeKind.OPTIONAL);
		}
		
		fmCopy.setBuilder(_builder);  
		return fmCopy.slice(SliceMode.EXCLUDING, toExcludes);
	}

	private String mkAndConstraint(List<String> fts) {
		StringBuilder sb = new StringBuilder();
		sb.append("("); 
		int n = fts.size() ;
		int i = 0 ; 
		for (String ft : fts) {
			sb.append(ft) ; 
			if (i++ != n - 1)
				sb.append(" & "); 
		}
		sb.append(")"); 
		return sb.toString();
	}
	
	private String mkOrConstraint(List<String> fts) {
		StringBuilder sb = new StringBuilder();
		sb.append("("); 
		int n = fts.size() ;
		int i = 0 ; 
		for (String ft : fts) {
			sb.append(ft) ; 
			if (i++ != n - 1)
				sb.append(" | "); 
		}
		sb.append(")"); 
		return sb.toString();
	}

	/**
	 * move a feature to another place 
	 * @param ftToMoveName
	 * @param ftParentName
	 * @param fm
	 * @throws Exception
	 */
	private void moveBelow(String ftToMoveName, String ftParentName,	FeatureModelVariable fm) throws Exception {
		
		
		FeatureModelVariable fmCopy = (FeatureModelVariable) fm.copy() ;
		
		String ftToMoveNamePrime = prime (ftToMoveName);
		
		FeatureModelVariable rwFM = FM ("rwFM", "FM (" + ftToMoveNamePrime + "; )");
		
		fmCopy.insert(rwFM, ftParentName, FeatureEdgeKind.OPTIONAL);
		
		System.err.println("fm1=" + fmCopy);
		
		ConstraintVariable cv1 = mkConstraintVariable("c1", ftToMoveName + " <-> " + ftToMoveNamePrime) ; 
		fmCopy.addConstraint(cv1) ;
		System.err.println("fm1=" + fmCopy);
		System.err.println("#fm1=" + fmCopy.counting());
		
		fmCopy.setBuilder(_builder); 
		FeatureModelVariable fmRW1 = fmCopy.slice(SliceMode.EXCLUDING, ftToMoveName);
		fmRW1.renameFeature(ftToMoveNamePrime, ftToMoveName); // if possible, i.e., the slice can remove dead features
		System.err.println("fmRW1=" + fmRW1);
		
		System.err.println("*** comparison: " + fm.compare(fmRW1));
		
	}

	private String prime(String ftToMoveName) {
		return ftToMoveName + "P";
	}

	

}
