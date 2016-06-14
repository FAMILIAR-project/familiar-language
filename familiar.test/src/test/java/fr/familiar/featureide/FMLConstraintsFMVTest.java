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

package fr.familiar.featureide;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fml.SliceMode;

import fr.familiar.FMLTest;
import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.SatisfiableStrategy;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */

public class FMLConstraintsFMVTest extends FMLTest {

	

	@Test
	public void testConstraints() throws Exception {
		FeatureModelVariable cstFmv = FMBuilder.parseConstraints("A ; B -> A ; C -> A ; ", _builder) ; 
		assertEquals (3, cstFmv.features().size()); 
		double cn = cstFmv.counting() ;
		assertNotNull(cn); 
		System.err.println("#" + cn);
		Set<Variable> configs = cstFmv.configs() ; 
		assertEquals (cn, configs.size(), 0);
		System.err.println("[[configs]]=" + FMLUtils.setConfigToSetStr(configs));
		
		Set<String> cores = cstFmv.cores() ;
		assertEquals(1, cores.size(), 0);
		Set<String> deads = cstFmv.deads() ;
		assertEquals(0, deads.size(), 0);
		
		double cn2 = cstFmv.counting(CountingStrategy.SAT_FML) ;
		assertNotNull(cn2); 
		System.err.println("#" + cn2);
		
		
		boolean b = cstFmv.isValid(SatisfiableStrategy.SAT_FML) ; 
		assertTrue (b);
		
		boolean b1 = cstFmv.isValid() ; 
		assertTrue (b1);
		
		boolean b2 = cstFmv.isValid(SatisfiableStrategy.BDD_FML) ; 
		assertTrue (b2);
		
		try { 
			cstFmv.isValid(SatisfiableStrategy.BDD_SPLOT) ; // not allowed (since it relies on a synthesised FM)
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
		
		cstFmv.setBuilder(_builder) ; 
		FeatureModelVariable sliceCst = cstFmv.slice(SliceMode.EXCLUDING, "C"); 
		assertNotNull(sliceCst);
		
		System.err.println("#" + sliceCst.counting());
		System.err.println("[[configs]]" + FMLUtils.setConfigToSetStr(sliceCst.configs()));
		

	}

}


