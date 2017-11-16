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

import org.junit.Test;
import org.xtext.example.mydsl.fml.SliceMode;

import fr.familiar.operations.Mode;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConfigurationVariableSPLOTImpl;
import fr.familiar.variable.FeatureModelVariable;

public class ExamplesIEEESoftwareTest extends FMLTest {
	
	@Test
	public void example1() throws Exception { 
		FeatureModelVariable fm1 = FM ("fm1.xml") ; 
		double n1 = fm1.counting(); 
		boolean b1 = fm1.isValid();
		FeatureModelVariable fm2 = FM ("fm2.tvl");
		FeatureModelVariable fm3 = fm1.merge(fm2, Mode.Union);
		FeatureModelVariable fm4 = fm3.slice(SliceMode.INCLUDING, "A", "E", "F");
	}
	
	
	@Test
	public void example2() throws Exception {
		FeatureModelVariable fmCarFamily = FM ("FCar: [GPS] [Engine]; "
				+ "GPS : DisplaySize ; DisplaySize : (\"5\"|\"8\") ;"
				+ "Engine : FuelType ; FuelType : (Diesel|Electric|Hybrid) ;"
				+ " Diesel -> GPS ;"
		) ;
		ConfigurationVariable c1 = new ConfigurationVariableSPLOTImpl(fmCarFamily, "c1");
		//
		//...
		if (c1.getSelected().contains("GPS")) {
			
			// querying the catalog of products
			/*
			Optional<Product> person = 
				    from(Product.class)
				        .where(Product::getGPS)
				        .equalTo("true")
				        .select(
				            productMapper, 
				            connectionFactory::openConnection);*/
			
		}
	}

}
