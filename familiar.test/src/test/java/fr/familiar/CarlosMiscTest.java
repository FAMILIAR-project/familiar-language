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

import org.junit.Test;
import org.xtext.example.mydsl.fml.FMFormat;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;

public class CarlosMiscTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		_shell.parse("run \"inputFMLTests/hbt.fml\"");
		FeatureModelVariable fmHbt = getFMVariable("hbt");
		System.err.println("fmHbt=" + fmHbt.counting (CountingStrategy.BDD_FML)) ;
		System.err.println("fmHbt=" + fmHbt.counting (CountingStrategy.BDD_SPLOT)) ;
		
		System.err.println("" + fmHbt.convert(FMFormat.FIDE)); 
		
		_shell.setVerbose(true);
		_shell.parse("hbt2 = FM(\"inputFMLTests/hbt.m\")");
		
		FeatureModelVariable fmHbt2 = getFMVariable("hbt2");
		
	}

}
