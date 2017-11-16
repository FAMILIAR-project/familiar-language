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

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;

/**
 * @author macher
 *
 */
public class FMLSetVariableTest extends FMLTest {

	
	
	
	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B [C] [D]; D : (E|F)+ ; ");
		FeatureModelVariable fmv2 = FM ("fm2", "A : B C [D] J; D : (E|F)+ ; ");
		
		
		SetVariable sv1 = fmv1.features() ; 
		SetVariable sv2 = fmv2.features() ;
		
		
		SetVariable svI = sv1.intersection(sv2);
		assertEquals(sv1.size(), svI.size()); // indeed, (though features belong to different feature models, equality is defined on their names)
		
		SetVariable svU = sv1.union(sv2);
		assertEquals((sv2.size() + sv1.size()) - svI.size(), svU.size()); // indeed, (though features belong to different feature models, equality is defined on their names)
		
		SetVariable sv21 = sv2.difference(sv1);
		assertEquals(1, sv21.size());
		
		SetVariable sv12 = sv1.difference(sv2);
		assertEquals(0, sv12.size());
		
		_shell.setVerbose(true);
		_shell.parse("s1 = names fm1.*");
		_shell.parse("s2 = names fm2.*");
		
		SetVariable s1 = getSetVariable("s1");
		SetVariable s2 = getSetVariable("s2");

		
		assertEquals(fmv1.features().size(), s1.size());
		
		SetVariable sI = s1.intersection(s2);
		assertEquals(s1.size(), sI.size());
		
		
		SetVariable sU = s1.union(s2);
		assertEquals(s2.size(), sU.size());
		
		SetVariable s12 = s1.difference(s2);
		assertEquals(0, s12.size());
		
		SetVariable s21 = s2.difference(s1);
		assertEquals(1, s21.size());
		assertEquals("J", s21.names().iterator().next());
		
		
		
		
		
	}
	
	
}
