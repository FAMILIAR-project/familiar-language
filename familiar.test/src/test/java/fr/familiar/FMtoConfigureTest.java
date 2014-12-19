package fr.familiar;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

// fr.familiar.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.fm.converter.FMtoConfigure;

public class FMtoConfigureTest extends FMLTest{
	String affichage;
	String chaine;
	FMtoConfigure fmConf;
	
	@Test
	public void testFMtoConfigure() throws Exception {
		fmConf = new FMtoConfigure();
		chaine = "fm1 = FM (A: B C [D]; I : (L|K)+; E : G [Z]; D : (H|I|J) ; C : [F] E; )";
		//chaine = "fm1 = FM (A : B [C] ; C : I J ;)";
		//chaine = "fm1 = FM (A : B [C] ; C : (I|J)+ ;)";
		//chaine = "fm1 = FM (A : (B|C);)";
		//chaine = "fm1 = FM (A : (B|C)+ ; B: D E; E : (J|H);)";
		_shell.parse(chaine);
		FeatureModelVariable fm1 = getFMVariable("fm1");
		
		affichage = fm1.getFm().toString();
		/*
		 * A: B C D?;
		 * D : (H|I|J)
		 * C : F? E;
		 */
		//fail(affichage);
		affichage = fmConf.getConfigureFromFML(fm1);
		
	}
}
