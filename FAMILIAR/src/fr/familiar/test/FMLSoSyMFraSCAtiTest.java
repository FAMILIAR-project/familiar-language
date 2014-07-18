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

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

public class FMLSoSyMFraSCAtiTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		
		FeatureModelVariable fm1 = FM ("fm1", "FraSCAti : SCAParser AssemblyFactory ComponentFactory ; " +
				"" +
				"SCAParser: Metamodel ;  " +
				"Metamodel: [MMFraSCAti] [MMTuscany] ; " +
				"AssemblyFactory : Binding ; " +
				"Binding: (http|rest)+ ; " +
				"ComponentFactory : JavaCompiler ; " + 
				"JavaCompiler : (JDK6|JDT) ; " +
				"rest implies MMFraSCAti ; " +
				"http implies MMTuscany ;");
		
		Set<String> cores1 = fm1.cores() ; 
		
		Set<Variable> fm1Configs = fm1.configs() ;
		Set<Set<String>> confs1 = FMLUtils.setConfigToSetStr(fm1Configs);
		for (Set<String> conf1 : confs1) {
			System.err.println("" + Sets.difference(conf1, cores1));
		}
		
		
	}

}
