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

package fr.familiar.featureide;

import org.junit.Test;
import org.prop4j.Node;
import org.xtext.example.mydsl.fml.FMFormat;

import fr.familiar.FMLTest;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.variable.FeatureModelVariable;

public class SATBuilder2Test extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		
		FeatureModelVariable fm1 = FM ("fm1", "A : B [C] [D] ; C : (E|F|G) ; D : (I|J)+ ; I -> E ; ");
		Node n1 = new SATBuilder().mkNode(fm1);
		
		System.err.println("fm1=" + fm1);
		System.err.println("fm1 (splot)=" + fm1.convert(FMFormat.FSPLOT));
		System.err.println("n1=" + n1);
		
		
	}

}
