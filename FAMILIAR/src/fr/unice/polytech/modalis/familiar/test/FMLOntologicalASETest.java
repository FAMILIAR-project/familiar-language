package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

public class FMLOntologicalASETest extends FMLTest {
	
	@Test
	public void testIllustrativeExamples() throws Exception {
		
		String fm1Specification = "Wiki: Storage Licence Hosting [\"Programming Language\"] ; " +
				"Licence : (\"Open Source\"|\"Proprietary Licence\") ; Storage : (MySQL|PostgreSQL) ;" +
				"\"Programming Language\": Java ; " +
				"Hosting: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"Java -> MySQL ; " +
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				""  ; 
		System.err.println(fm1Specification + "\n\n\n");
		
		FeatureModelVariable fm1 = FM ("fm1", fm1Specification);
		
		System.err.println("" + fm1.features().names()); 
		System.err.println("" + fm1.counting());
		Set<Set<String>> s1 = _configsToSet(fm1.configs()) ; 
		System.err.println("[[fm1]]=" + s1);
		
		FeatureModelVariable fm1S = fm1.slice(SliceMode.INCLUDING, "Wiki", "MySQL", "PostgreSQL", "Open Source", "Proprietary Licence");
	
		
		System.err.println("fm1S=" + fm1S);
		
		FeatureModelVariable fm2 = FM ("fm2", "Wiki: Storage Licence Hosting [\"Programming Language\"] [MySQL] ; " +
				"Licence : (\"Open Source\"|\"Proprietary Licence\") ; Storage : [PostgreSQL] ;" +
				//"\"Programming Language\": Java ; " +
				"Hosting: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				"Java <-> \"Programming Language\" ; " + // added 
				//"Java -> MySQL ; " + // redundant
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		System.err.println("#fm2=" + fm2.counting());
		Set<Set<String>> s2 = _configsToSet(fm2.configs()) ; 
		System.err.println("[[fm2]]=" + s2);
		
		System.err.println("s1 / s2 = " + Sets.difference(s1, s2));
		System.err.println("s2 / s1 = " + Sets.difference(s2, s1));
		
		
		
		assertEquals(Comparison.REFACTORING, fm2.compareBDD(fm1, _builder));
		
		FeatureModelVariable fm3 = FM ("fm3", "Wiki: Storage Licence Hosting [\"Programming Language\"] [MySQL] ; " +
				"Storage : (\"Open Source\"|\"Proprietary Licence\") ; Hosting : [PostgreSQL] ;" +
				//"\"Programming Language\": Java ; " +
				"Licence: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				"Java <-> \"Programming Language\" ; " + // added 
				//"Java -> MySQL ; " + // redundant
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		System.err.println("#fm3=" + fm3.counting());
		Set<Set<String>> s3 = _configsToSet(fm3.configs()) ; 
		System.err.println("[[fm3]]=" + s3);
		
		System.err.println("s1 / s3 = " + Sets.difference(s1, s3));
		System.err.println("s3 / s1 = " + Sets.difference(s3, s1));
		assertEquals(Comparison.REFACTORING, fm3.compareBDD(fm1, _builder));
		
		ImplicationGraphUtil.debugExclusionGraph(fm3.computeExclusionGraph(_builder));
		
		
		FeatureModelVariable fm4 = FM ("fm4", "Wiki: Storage Licence Hosting [MySQL] ; " +
				"Storage : (\"Open Source\"|\"Proprietary Licence\") ; Hosting : (PostgreSQL|\"Programming Language\")? ;" +
				//"\"Programming Language\": Java ; " +
				"Licence: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				"Java <-> \"Programming Language\" ; " + // added 
				//"Java -> MySQL ; " + // redundant
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		assertEquals(Comparison.REFACTORING, fm4.compareBDD(fm1, _builder));
	}
	
	/**
	 * Another attempt
	 * @throws Exception
	 */
	@Test
	public void testIllustrativeExamples2() throws Exception {
		
		String fm1Specification = "Wiki: Storage Licence Hosting [\"Programming Language\"] ; " +
				"Licence : (\"Open Source\"|\"Proprietary Licence\") ; Storage : (MySQL|PostgreSQL) ;" +
				"\"Programming Language\": (Java|PHP) ; " +
				"Hosting: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"PHP -> MySQL ; " +
				"Java -> MySQL ; " +
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				""  ; 
		System.err.println(fm1Specification + "\n\n\n");
		
		FeatureModelVariable fm1 = FM ("fm1", fm1Specification);
		
		System.err.println("" + fm1.features().names()); 
		System.err.println("" + fm1.counting());
		Set<Set<String>> s1 = _configsToSet(fm1.configs()) ; 
		System.err.println("[[fm1]]=" + s1);
		
		FeatureModelVariable fm1S = fm1.slice(SliceMode.INCLUDING, "Wiki", "MySQL", "PostgreSQL", "Open Source", "Proprietary Licence");
	
		
		System.err.println("fm1S=" + fm1S);
		
		FeatureModelVariable fm2 = FM ("fm2", "Wiki: Storage Licence Hosting [\"Programming Language\"] [MySQL] ; " +
				"Licence : (\"Open Source\"|\"Proprietary Licence\") ; Storage : [PostgreSQL] ;" +
				//"\"Programming Language\": Java ; " +
				"Hosting: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] [PHP] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				"Java -> \"Programming Language\" ; " + // added 
				"PHP -> \"Programming Language\" ; " + // added 
				"(\"Programming Language\" -> PHP) or (\"Programming Language\" -> Java) ; " + // added 
				"PHP -> !Java ; " + 
				//"Java -> MySQL ; " + // redundant
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		System.err.println("#fm2=" + fm2.counting());
		Set<Set<String>> s2 = _configsToSet(fm2.configs()) ; 
		System.err.println("[[fm2]]=" + s2);
		
		System.err.println("s1 / s2 = " + Sets.difference(s1, s2));
		System.err.println("s2 / s1 = " + Sets.difference(s2, s1));
		
		
		
		assertEquals(Comparison.REFACTORING, fm2.compareBDD(fm1, _builder));
		
		FeatureModelVariable fm3 = FM ("fm3", "Wiki: Storage Licence Hosting [\"Programming Language\"] [MySQL] ; " +
				"Storage : (\"Open Source\"|\"Proprietary Licence\") ; Hosting : [PostgreSQL] ;" +
				//"\"Programming Language\": Java ; " +
				"Licence: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] [PHP] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				"Java -> \"Programming Language\" ; " + // added 
				"PHP -> \"Programming Language\" ; " + // added 
				"(\"Programming Language\" -> PHP) or (\"Programming Language\" -> Java) ; " + // added 
				"PHP -> !Java ; " + 
				//"Java -> MySQL ; " + // redundant
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		System.err.println("#fm3=" + fm3.counting());
		Set<Set<String>> s3 = _configsToSet(fm3.configs()) ; 
		System.err.println("[[fm3]]=" + s3);
		
		System.err.println("s1 / s3 = " + Sets.difference(s1, s3));
		System.err.println("s3 / s1 = " + Sets.difference(s3, s1));
		assertEquals(Comparison.REFACTORING, fm3.compareBDD(fm1, _builder));
		
		ImplicationGraphUtil.debugExclusionGraph(fm3.computeExclusionGraph(_builder));
		
		
		FeatureModelVariable fm4 = FM ("fm4", "Wiki: Storage Licence Hosting [MySQL] ; " +
				"Storage : (\"Open Source\"|\"Proprietary Licence\") ; Hosting : (PostgreSQL|\"Programming Language\")? ;" +
				//"\"Programming Language\": Java ; " +
				"Licence: (Local|\"Hosted Service\") ; " +
				"\"Hosted Service\": [Domain] ; MySQL: [Java] [PHP] ; " +
				"PostgreSQL -> Domain ;" +
				"\"Open Source\" -> MySQL ; " +
				"MySQL -> !PostgreSQL ;" + // added 
	    		"MySQL -> Storage ;" + // added
				"MySQL | PostgreSQL ;" +  // added
				//"Java -> MySQL ; " + // redundant
				"Java -> \"Programming Language\" ; " + // added 
				"PHP -> \"Programming Language\" ; " + // added 
				"(\"Programming Language\" -> PHP) or (\"Programming Language\" -> Java) ; " + // added 
				"PHP -> !Java ; " + 
				"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				"" +
				"" );
		
		assertEquals(Comparison.REFACTORING, fm4.compareBDD(fm1, _builder));
	}
	
	private Set<Set<String>> _configsToSet(Set<Variable> configs) {
		Set<Set<String>> r = new HashSet<Set<String>>() ; 
		for (Variable config : configs) {
			//Set<String> s = new HashSet<String>() ;
			SetVariable sv = (SetVariable) config ; 
			r.add(sv.names()); 
			/*
			Set<Variable> fts = sv.getVars() ;
			for (Variable ft : fts) {
				FeatureVariable fv = (FeatureVariable) ft ;
				fv.getFtName() ; 
			}*/
			
		}
		return r ; 
	}

}
