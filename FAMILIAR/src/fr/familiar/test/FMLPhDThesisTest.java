package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.parser.FMBuilder;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

public class FMLPhDThesisTest extends FMLTest {

	@Test
	public void testFooExample() throws Exception {

		FeatureModelVariable fm1 = FM("fm1",
				"FM (W : P (T|U); P : (R|S)+ ; T : [V] A ; " + "V -> R ; "
						+ "!U | !S ; " + ")");

		Set<Set<String>> sfm1 = FMLUtils.setConfigToSetStr(fm1.configs());
		System.err.println("[[fm1]]=");
		Set<String> cores1 = fm1.cores();
		for (Set<String> cf1 : sfm1) {
			System.err.println("" + Sets.difference(cf1, cores1));
		}

		System.err.println("#fm1=" + fm1.counting());

	}

	@Test
	public void testInternalBDDTranslation() throws IOException {
		
		FeatureModel<String> fm = FMBuilder.getInternalFM("W: (T|U) P ; T: A [V] ; P: (R|S) ;	(A <-> R);	(U <-> S);"); 
		Formula<String> fla = _builder.mkFeatureModel(fm) ;
		System.err.println("fla=" + fla);
		Formula<String> fla2 = FMLCommandInterpreter.getBuilder().mkFeatureModel(fm) ;
		System.err.println("fla2=" + fla2);
		
	}
	
	@Test
	public void testAnomaliesExample() throws Exception {

		FeatureModelVariable fmv1 = FM("fm1",
				"FM (W : P (T|U); P : (R|S)+ ; T : [V] [A] ; " + "R -> !V ; "
						+ "S -> U ; R -> A ; " + ")");

		double n1 = fmv1.counting() ; 
		
		
		
		_shell.parse("dfm1 = deads fm1 ");
		
		SetVariable deads1 = getSetVariable("dfm1");
		System.err.println("deads fm1: " + deads1.names());
		assertEquals(1, deads1.size());
		assertTrue(deads1.names().contains("V"));
		
		_shell.setVerbose(true);
		_shell.parse("fo1 = falseOptionals fm1");
		_shell.setVerbose(false);
		
		_shell.parse("fm1Clean = slice fm1 including fm1.*"); // FIXME: V is still here :(
		
		
		FeatureModelVariable fmv1Clean = getFMVariable("fm1Clean");
		System.err.println("fmv1Clean=" + fmv1Clean.getFm()) ; 
		double n2 = fmv1Clean.counting() ;
		
		
		
		SetVariable fo1 = getSetVariable("fo1");
		
		System.err.println("false optionals fm1: " + fo1.names());
		assertEquals(1, fo1.size());
		assertTrue(fo1.names().contains("A"));
		
		Set<Set<String>> sfm1 = FMLUtils.setConfigToSetStr(fmv1.configs());
		System.err.println("#fm1=" + n1);
		System.err.println("[[fm1]]=");
		Set<String> cores1 = fmv1.cores();
		for (Set<String> cf1 : sfm1) {
			System.err.println("" + Sets.difference(cf1, cores1));
		}
		
		System.err.println("");
		System.err.println("#fm2=" + n2);
		
		
		_shell.setVerbose(true);
		Formula<String> fdFla = _builder.mkFeatureModel(fmv1Clean.getFm());
		System.err.println("fdFla=" + fdFla);
		Set<String> cores2 = fmv1Clean.cores(); 
		Set<Set<String>> sfm2 =  FMLUtils.setConfigToSetStr(fmv1Clean.configs());
		_shell.setVerbose(false);
		
		
		
		
		for (Set<String> cf2 : sfm2) {
			System.err.println("" + Sets.difference(cf2, cores2));
		}

		
		System.err.println("#fmClean=" + n2);
		
		assertEquals(n1, n2, 0);
		
		System.err.println("recap\n\n" + _shell.getHistory()) ;

	}
	
	// not in the PhD thesis, move to another class
	//
	@Test
	public void testFooSCPExample() throws Exception {

		// AN: Anonymized
		// MI: MedicalImage
		// MA: ModalityAcquisition

		FeatureModelVariable fm1 = FM("fm1", "FM (MI : MA [AN] ; "
				+ "MA: (MRI|CT) ; " + "MRI : (T1|T2)+ ; " + "MRI -> !AN ; )");

		Set<Set<String>> sfm1 = FMLUtils.setConfigToSetStr(fm1.configs());
		System.err.println("[[fm1]]=");
		Set<String> cores1 = fm1.cores();
		for (Set<String> cf1 : sfm1) {
			System.err.println("" + cf1);
			// System.err.println("" + Sets.difference(cf1, cores1));
		}

		System.err.println("#fm1=" + fm1.counting());

	}

}
