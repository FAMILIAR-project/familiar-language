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
