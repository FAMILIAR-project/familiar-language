/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.operations.FMLMerger;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerDisjunctiveSAT;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public class FMLDisjunctiveSATMergerTest extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		
		_shell.parse("// with explicity negated variables\n" + 
				"fm1 = FM (A : [B] [C] [E] [F] ; !E ; !F ; ) \n" + 
				"fm2 = FM (A : [B] [E] [F] ; B : C ; !E ; )\n" + 
				"fm3 = FM (A : B [F] [E] [C] ; !F ; !C ; )");
		
		List<FeatureModelVariable> fmvs = new ArrayList<FeatureModelVariable>() ;
		fmvs.add(getFMVariable("fm1"));
		fmvs.add(getFMVariable("fm2"));
		fmvs.add(getFMVariable("fm3"));
		
		assertEquals(3, fmvs.size());
		
		FMLMergerDisjunctiveSAT merger = new FMLMergerDisjunctiveSAT(fmvs) ;
		FeatureModelVariable fmv1 = merger.union() ; 
		
		FMLMerger merger2 = new FMLMergerBDD(fmvs);
		FeatureModelVariable fmv2 = merger2.union() ; 
		
		System.err.println("IG 2: " + ImplicationGraphUtil.toExpressions(fmv2.computeImplicationGraph(_builder)));
		System.err.println("EG 2: " + fmv2.computeExcludesEdge(_builder));
	}

}
