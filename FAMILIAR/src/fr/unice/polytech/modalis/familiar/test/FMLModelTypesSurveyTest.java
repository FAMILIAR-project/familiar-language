package fr.unice.polytech.modalis.familiar.test;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FMLModelTypesSurveyTest extends FMLTest {
	
	private String _fmModelTypes = "\"Système de types orienté-modèle\" : "
			+ "[\"Relations de sous-typage entre types de modèles\"] [\"Relations d'héritage entre métamodèles\"] ;"  
			+ "\"Relations de sous-typage entre types de modèles\" : Structure Contexte \"Déclaration\" \"Vérification\" ; "
			+ "Structure: (Isomorphique|\"Non-isomorphique\")+ ; "
			+ "Contexte: (Totale|Partielle)+ ; "
			+ "\"Déclaration\" : (Quand|Comment)+ ; "
			+ "Comment: (Explicite|Implicite)+ ; "
			+ "\"Vérification\": (Statique|Dynamique) ; "
			+ "\"Relations d'héritage entre métamodèles\" : (\"Avec sous-typage\"|\"Sans sous-typage\")+ ; "
			+ "\"Avec sous-typage\" -> \"Relations de sous-typage entre types de modèles\" ; "
			;

	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", _fmModelTypes ) ; 
		System.err.println("" + fmv1);
		System.err.println("#" + fmv1.counting()); 
		
	}

}