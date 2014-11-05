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

package fr.familiar;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

/**
 * @author macher1
 *
 */
public class HelloWorldTest extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1", "A : B [C] ; ");
				
		SetVariable cores1 = (SetVariable) _shell.parse("cores fm1");
		System.err.println("cores1=" + cores1.names());
		assertEquals(2, cores1.size());
		ImplicationGraph<String> big1 = fm1.computeImplicationGraph() ;
		Collection<SimpleEdge> e1 = big1.edges() ;
		for (SimpleEdge e : e1) {
			System.err.println("" + big1.getEdgeSource(e) + " => " + big1.getEdgeTarget(e));
		}
					
	}
	
	@Test
	public void test2() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1", "A : B [C] D ; D : (E|F|G) ; C -> E ; ");
		ConfigurationVariable cf1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cf1");
		
		cf1.applySelection("C", OpSelection.SELECT);
		System.err.println("" + cf1.getSelected() + " " + cf1.getDeselected()); 
		
		
					
	}
	

}
