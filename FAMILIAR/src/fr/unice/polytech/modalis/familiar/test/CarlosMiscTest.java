package fr.unice.polytech.modalis.familiar.test;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

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
