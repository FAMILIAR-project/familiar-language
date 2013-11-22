package fr.pacogen.export;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import splar.core.fm.FeatureModel;
import splar.core.fm.XMLFeatureModel;

public class Splot_Test {

	private SplotAdapterFamiliar pvA ; 
	
	@Before
	public void setUp() throws Exception {
		pvA = new SplotAdapterFamiliar() ;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		pvA = new SplotAdapterFamiliar() ;

		String featureModelFile = "/home/aymeric/Projets/Developpement/RCP/RCPWorkspace/PacogenAdapter/car_fm.xml";
		FeatureModel featureModel = new XMLFeatureModel(featureModelFile,XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		
		pvA.parse(featureModel);
		System.out.println(pvA.getModel().toProlog());
		}

}
