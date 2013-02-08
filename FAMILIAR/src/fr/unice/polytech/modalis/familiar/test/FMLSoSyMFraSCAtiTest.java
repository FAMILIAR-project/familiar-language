package fr.unice.polytech.modalis.familiar.test;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.fm.FMLUtils;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

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
