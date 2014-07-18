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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import fr.familiar.experimental.FGroup;
import fr.familiar.fm.FMLUtils;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher
 *
 */
public class FMLFGroupManagementTest extends FMLTest {
	
	
	@Test
	public void testComputerMtx() throws Exception {
		
		
		_shell.parse("// your FAMILIAR code here!\n" + 
				"// your FAMILIAR code here!\n" + 
				"fm1 = FM (A: B [C] ; )\n" + 
				"\n" + 
				"\n" + 
				"fm9 = FM (A : B ; )\n" + 
				"fm2 = FM (A : B [C] ; )\n" + 
				"fm3 = FM (A : B [E] ; )\n" + 
				"fm4 = merge sunion fm*\n" + 
				"fm5 = FM (A : J [K] [L] ; )\n" + 
				"\n" + 
				"fm0 = merge sunion fm*\n");
		
		FeatureModelVariable fm0 = getFMVariable("fm0");
		System.err.println("fm0=" + fm0);
		System.err.println("[[fm0]]=" + FMLUtils.setConfigToSetStr(fm0.configs()));
		Set<FGroup> fMtxgroups = fm0.computeMutexGroups();
		
		for (FGroup fMtxGroup : fMtxgroups) {
			System.err.println("fMtxgroup=" + fMtxGroup);
		}
		
		Set<FGroup> fXorgroups = fm0.computeXorGroups();
		for (FGroup fXorGroup : fXorgroups) {
			System.err.println("fXorgroup=" + fXorGroup);
		}
		
	}
	
	@Test
	public void testGetter1() throws Exception {
		FeatureModelVariable fmv1 = FM ("fm1", "A : (B|C|D) ; ");
		Set<FGroup> xors = fmv1.getXorGroups();
		assertEquals(1, xors.size());
		assertEquals(0, fmv1.getMutexGroups().size());
		assertEquals(0, fmv1.getOrGroups().size());
	
		assertEquals(1, fmv1.computeXorGroups().size());
		assertEquals(0, fmv1.computeMutexGroups().size());
		assertEquals(0, fmv1.computeOrGroups().size());
	}
	
	@Test
	public void testGetter2() throws Exception {
		FeatureModelVariable fmv1 = FM ("fm1", "A : (B|C|D)+ ; ");
		Set<FGroup> ors = fmv1.getOrGroups();
		assertEquals(1, ors.size());
		assertEquals(0, fmv1.getMutexGroups().size());
		assertEquals(0, fmv1.getXorGroups().size());
		
		assertEquals(1, fmv1.computeOrGroups().size());
		assertEquals(0, fmv1.computeMutexGroups().size());
		assertEquals(0, fmv1.computeXorGroups().size());
	}
	
	@Test
	public void testGetter3() throws Exception {
		FeatureModelVariable fmv1 = FM ("fm1", "A : (B|C|D)? ; ");
		Set<FGroup> mtxs = fmv1.getMutexGroups();
		assertEquals(1, mtxs.size());
		assertEquals(0, fmv1.getOrGroups().size());
		assertEquals(0, fmv1.getXorGroups().size());
		
		assertEquals(1, mtxs.size());
		
		assertEquals(1, fmv1.computeMutexGroups().size());
		assertEquals(0, fmv1.computeOrGroups().size());
		assertEquals(0, fmv1.computeXorGroups().size());
	}
	
	@Test
	public void testGetter4() throws Exception {
		FeatureModelVariable fmv1 = FM ("fm1", "A : (B|C|D)? ; C : (F|G) ; D : (I|J)+ ; ");
		assertEquals(1, fmv1.getMutexGroups().size());
		assertEquals(1, fmv1.getXorGroups().size());
		assertEquals(1, fmv1.getOrGroups().size());
		
		assertEquals(6, fmv1.computeMutexGroups().size());
		assertEquals(1, fmv1.computeXorGroups().size());
		assertEquals(1, fmv1.computeOrGroups().size());
	}
	
	@Test
	public void testGetter5() throws Exception {
		FeatureModelVariable fmv1 = FM ("fm1", "A : (B|C|D)? ; C : (F|G)+ ; D : (I|J)+ ; ");
		assertEquals(1, fmv1.getMutexGroups().size());
		assertEquals(0, fmv1.getXorGroups().size());
		assertEquals(2, fmv1.getOrGroups().size());
		
		assertEquals(9, fmv1.computeMutexGroups().size());
		assertEquals(0, fmv1.computeXorGroups().size());
		assertEquals(2, fmv1.computeOrGroups().size());
		
	}
	
	
	@Test
	public void testCompute1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", 
				"A : [B] [C] ; B : E [F] ; F : (G|H|I)+ [KK]  ; C : Z ; Z : X [Y] ; H | C; KK | I ; ");
		
		assertEquals(0, fmv1.getMutexGroups().size());
		assertEquals(0, fmv1.getXorGroups().size());
		assertEquals(1, fmv1.getOrGroups().size());
		
		assertEquals(0, fmv1.computeMutexGroups().size());
		assertEquals(0, fmv1.computeXorGroups().size());
		assertEquals(26, fmv1.computeOrGroups().size());
		
		
		
	}
	
	@Test
	public void testGetterCompute1() throws Exception {
		
		
		Object[][] fmSpecifications = new Object[][] { 
				{ "A : [B] [C] ; B : (E|F)+ ; F : (G|H|I)+ [KK]  ; C : Z ; Z : X [Y] ; H | C; KK | I ; ", 0, 0, 26 }, // F is a core feature
				{ "A : [B] [C] ; B : (E|F)+ ; F : (G|H|I)+ [KK]  ; C : Z ; Z : X [Y] ; ", 0, 0, 4 },
				{ "A : [B] [C] ; B : (E|F) ; F : (G|H|I) [KK]  ; C : Z ; Z : X [Y] ; ", 0, 3, 1 },
				{ "A : [B] [C] ; !B | !C ; ", 1, 0, 0 },
				{ "A : [B] [C] ; !B | !C ; B | C ; ", 0, 1, 0 },
				{ "A : [B] [C] ; B | C ; ", 0, 0, 1 },
				{ "A : [B] [C] (D|E) ; !B | !C ; B | C ; !B | !E ; !C | !E; ", 0, 3, 0 },
				{ "A : [B] [C] (D|E) ; !B | !C ; !B | !E ; !C | !E; ", 1, 1, 0 },
				{ "A : [B] [C] ; B : E [F] ; F : (G|H|I)+ [KK]  ; C : Z ; Z : X [Y] ; H | C; KK | I ; ", 0, 0, 26} 
				
		};
		for (int i = 0; i < fmSpecifications.length; i++) {
			
			FeatureModelVariable fmv1 = FM ("fm" + i, (String) fmSpecifications[i][0]);
			int nMutexExpected = (Integer) fmSpecifications[i][1];
			int nXorsExpected = (Integer) fmSpecifications[i][2];
			int nOrsExpected = (Integer) fmSpecifications[i][3];
			
	
			assertTrue(nMutexExpected >= fmv1.getMutexGroups().size());
			assertTrue(nXorsExpected >= fmv1.getXorGroups().size());
			assertTrue(nOrsExpected >= fmv1.getOrGroups().size());
			
			assertEquals(nMutexExpected, fmv1.computeMutexGroups().size());
			assertEquals(nXorsExpected, fmv1.computeXorGroups().size());
			assertEquals(nOrsExpected, fmv1.computeOrGroups().size());		
			
		}
		
		
		
		
	}
	
	

}
