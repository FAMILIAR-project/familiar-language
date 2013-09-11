package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableBDDFormula;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;

public class FMLOntologicalASETest extends FMLTest {
	
	
	
	@Test
	public void testFG() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "A : [B] [E] ; B : (C|D) ; ");
		
		Set<FGroup> xors = fm1.computeXorGroups();
		Set<FGroup> mtxs = fm1.computeMutexGroups();
		System.err.println("xors=" + xors);
		System.err.println("mtxs=" + mtxs);
		System.err.println("" + fm1.toGeneralizedNotation()); //.getMutexGroups());
		
		FeatureModelVariable fm1bis = FM ("fm1", "A : [B] [E] [F] ; B : (C|D)+ I ; E -> !I ; ");
		System.err.println("" + fm1bis.toGeneralizedNotation()); //.getMutexGroups());
		System.err.println("" + fm1bis.toGeneralizedNotation().getMutexGroups());
		System.err.println("" + fm1bis.computeMutexGroups());
	}
	
	@Test
	public void testFASE() throws Exception {
		
		FeatureModelVariable faseExample = FM ("fase_example", 
                "\"Cell Phone\": Games Display \"Accu Cell\" [\"Wireless\"] ; \n" +
                "Wireless: (Infrared | Bluetooth)+; \n" +
                "\"Accu Cell\": (Strong | Medium | Weak); \n" +
                "Games: (\"Multi Player\" | \"Single Player\")+; \n" +
                 "\"Single Player\": \"Artificial Opponent\"; \n" +
                "Bluetooth -> Strong ; \n" + 
                "(\"Multi Player\" -> Wireless); \n" +
                "(\"Multi Player\" -> !Weak); \n" +
                ""
                );
		
		System.err.println("#" + faseExample.counting());
		System.err.println("" + faseExample.computeOrGroups()); 
		
		System.err.println("" + faseExample.computeXorGroups()); 
		
		
		FeatureModelVariable faseReduced = new FeatureModelVariableBDDFormula("", faseExample.getFormula(), _builder).slice(SliceMode.EXCLUDING, new HashSet<String>(Arrays.asList(new String[] {
				"Weak"
		})));
		
		
		//System.err.println("" + faseReduced.compare(faseExample));
		//faseExample.addConstraint(new ConstraintVariable(new Expression<String>("Weak").not(), ""));
		
		//System.err.println("#" + faseReduced.counting());
		
		
	}
	
	
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
		
		ImplicationGraph<String> big1 = fm1.computeImplicationGraph(_builder);
		Set<String> a1 = big1.ancestors("PHP");
		Set<String> a2 = big1.ancestors("Java");
		System.err.println("a1=" + a1 + " a2=" + a2);
		Set<String> a12 = Sets.intersection(a1, a2);
		System.err.println("a12=" + a12);
		
		
		
		System.err.println("" + fm1.computeOrGroups());
		
		System.err.println("" + fm1.features().names()); 
		System.err.println("" + fm1.counting());
		Set<Set<String>> s1 = _configsToSet(fm1.configs()) ; 
		System.err.println("[[fm1]]=" + s1);
		
		FeatureModelVariable fm1S = fm1.slice(SliceMode.INCLUDING, "Wiki", "MySQL", "PostgreSQL", "Open Source", "Proprietary Licence");
		
		
	
		System.err.println("\n\n\n");
		System.err.println("fm1S=" + fm1S);
		System.err.println("[[fm1S]]=" +_configsToSet(fm1S.configs()));
		
		
		String fm2Specification = "Wiki: Storage Licence Hosting ; " +
				"Storage : (\"Open Source\"|\"Hosted Service\"|MySQL)+ ; Hosting : (PostgreSQL|\"Programming Language\")? ;" +
				//"\"Programming Language\": Java ; " +
				"Licence: [Local] [\"Proprietary Licence\"] ; " +
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
				"Local | \"Hosted Service\" ; " +
				"Local -> ! \"Hosted Service\" ; " +
				"MySQL <-> \"Open Source\" ; " +
				"\"Proprietary Licence\" | \"Open Source\" ; " + 
				"" +
				"" +
				""  ;
		FeatureModelVariable fm2 = FM ("fm2", fm2Specification);
		System.err.println("fm2=" + fm2Specification);
		
		System.err.println("fm2.*=" + fm2.features().names());
		
		System.err.println("#fm2=" + fm2.counting());
		Set<Set<String>> s2 = _configsToSet(fm2.configs()) ; 
		System.err.println("[[fm2]]=" + s2);
		
		System.err.println("s1 / s2 = " + Sets.difference(s1, s2));
		System.err.println("s2 / s1 = " + Sets.difference(s2, s1));
		assertEquals(Comparison.REFACTORING, fm2.compareBDD(fm1, _builder));
		
		ImplicationGraphUtil.debugExclusionGraph(fm2.computeExclusionGraph(_builder));
				
		assertEquals(Comparison.REFACTORING, fm2.compareBDD(fm1, _builder));
		
		FeatureModelVariable fm2Corrected = FM ("fm2bis", "Wiki: Hosting Licence Storage ; \n" + 
				"		Hosting: (PostgreSQL|\"Programming Language\")? ; \n" + 
				"		Licence: (\"Proprietary Licence\"|Local)? ; \n" + 
				"		Storage: (\"Hosted Service\"|MySQL)+ [\"Open Source\"] ; \n" + 
				"		\"Hosted Service\": [Domain] ; \n" + 
				"		MySQL: (Java|PHP)? ; \n" + 
				"		(PostgreSQL -> !\"Open Source\");\n" + 
				"		(PHP -> \"Programming Language\");\n" + 
				"		(PostgreSQL <-> \"Proprietary Licence\");\n" + 
				"		(MySQL <-> \"Open Source\");\n" + 
				"		(Java -> \"Programming Language\");\n" + 
				"		(Local -> !\"Hosted Service\") ; " + 
				"MySQL | PostgreSQL ;" 
				+ "PostgreSQL -> Domain ;"
				+ "(\"Programming Language\" -> PHP) or (\"Programming Language\" -> Java) ; " 
				+ "Local | \"Hosted Service\" ; "
				//+ "Local -> PostgreSQL ; "
				//+ "\"Proprietary Licence\" | \"Open Source\" ; " 
				);
		
		System.err.println("#fm2Corrected=" + fm2Corrected.counting());
		Set<Set<String>> s2Corrected = _configsToSet(fm2Corrected.configs()) ; 
		System.err.println("[[fm2Corrected]]=" + s2Corrected);
		System.err.println("cliques fm2" + fm2Corrected.cliques().names());
		
		System.err.println("s1 / s2 = " + Sets.difference(s1, s2Corrected));
		System.err.println("s2 / s1 = " + Sets.difference(s2Corrected, s1));
		assertEquals(Comparison.REFACTORING, fm2Corrected.compareBDD(fm1, _builder));
		
		
		
		FeatureModelVariable fm1bis = FM ("fm1bis", 
				" Wiki: Hosting Licence Storage [\"Programming Language\"] ; \n" + 
				"Hosting: (\"Hosted Service\"|Local) ; \n" + 
				"Licence: (\"Proprietary Licence\"|\"Open Source\") ; \n" + 
				"Storage: (PostgreSQL|MySQL) ; \n" + 
				"\"Programming Language\": (Java|PHP) ; \n" + 
				"\"Hosted Service\": [Domain] ; \n" + 
				"(\"Proprietary Licence\" -> !\"Programming Language\");\n" + 
				"(Local -> !\"Proprietary Licence\");\n" + 
				"(PostgreSQL <-> \"Proprietary Licence\");" +
				"PostgreSQL -> Domain ;" +
				//"\"Open Source\" -> MySQL ; " +
				//"PHP -> MySQL ; " +
				//"Java -> MySQL ; " +
				//"\"Proprietary Licence\" -> !MySQL ; " +  
				"" +
				"" +
				""
				) ;
		assertEquals(Comparison.REFACTORING, fm1bis.compareBDD(fm1, _builder));
		
		
				
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
