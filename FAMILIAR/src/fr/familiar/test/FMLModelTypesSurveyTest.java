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

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;

public class FMLModelTypesSurveyTest extends FMLTest {
	
//	private String _fmModelTypes = "\"Système de types orienté-modèle\" : "
//			+ "[\"Relations de sous-typage entre types de modèles\"] [\"Relations d'héritage entre métamodèles\"] ;"  
//			+ "\"Relations de sous-typage entre types de modèles\" : Structure Contexte \"Déclaration\" \"Vérification\" ; "
//			+ "Structure: (Isomorphique|\"Non-isomorphique\")+ ; "
//			+ "Contexte: (Totale|Partielle)+ ; "
//			+ "\"Déclaration\" : (Quand|Comment)+ ; "
//			+ "Comment: (Explicite|Implicite)+ ; "
//			+ "\"Vérification\": (Statique|Dynamique) ; "
//			+ "\"Relations d'héritage entre métamodèles\" : (\"Avec sous-typage\"|\"Sans sous-typage\")+ ; "
//			+ "\"Avec sous-typage\" -> \"Relations de sous-typage entre types de modèles\" ; "
//			;
	
	private String _fmModelTypes = "\"Model-oriented type system\" : "
	+ "[SubtypingRelations] [InheritanceRelations] ;"  
	+ "SubtypingRelations: STStructure STContext Declaration Verification ; "
	+ "STStructure: (STIsomorphic|STNonIsomorphic)+ ; "
	+ "STContext: (STTotal|STPartial)+ ; "
	+ "Declaration: (When|How)+ ; "
	+ "How: (Explicit|Implicit)+ ; "
	+ "Verification: (Static|Dynamic) ; "
	+ "InheritanceRelations: (WithSubtyping|WithoutSubtyping)+ ; "
	+ "WithSubtyping: InhStructure InhContext ; "
	+ "InhStructure: (InhIsomorphic|InhNonIsomorphic)+ ; "
	+ "InhContext: (InhTotal|InhPartial)+ ; "
	+ "InhIsomorphic -> STIsomorphic ; "
	+ "InhNonIsomorphic -> STNonIsomorphic ; "
	+ "InhTotal -> STTotal ; "
	+ "InhPartial -> STPartial ; "
	;

	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", _fmModelTypes ) ; 
		System.err.println("" + fmv1);
		System.err.println("#" + fmv1.counting()); 
		
	}

}
