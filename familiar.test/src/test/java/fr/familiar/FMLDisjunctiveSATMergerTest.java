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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.familiar.operations.FMLMerger;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMLMergerDisjunctiveSAT;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;

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
		FeatureModelVariable fm1 = getFMVariable("fm1") ; 
		System.err.println("eg fm1 = " + fm1.computeExcludesEdge());
		
		System.err.println("eg fm1 (bis)" + new SATFMLFormula(fm1).computeExclusionGraph(fm1.features().names()));
		fmvs.add(fm1);
		fmvs.add(getFMVariable("fm2"));
		fmvs.add(getFMVariable("fm3"));
		
		assertEquals(3, fmvs.size());
		
		FMLMergerDisjunctiveSAT merger = new FMLMergerDisjunctiveSAT(fmvs) ;
		FeatureModelVariable fmM1 = merger.union() ; 
		
		FMLMerger merger2 = new FMLMergerBDD(fmvs);
		FeatureModelVariable fmM2 = merger2.union() ; 
		
		System.err.println("IG 2: " + ImplicationGraphUtil.toExpressions(fmM2.computeImplicationGraph(_builder)));
		System.err.println("EG 2: " + fmM2.computeExcludesEdge(_builder));
	}

}
