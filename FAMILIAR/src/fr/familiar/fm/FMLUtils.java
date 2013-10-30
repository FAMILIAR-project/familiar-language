package fr.familiar.fm;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;


public class FMLUtils {

	/**
	 * @param sv1
	 *            basically a configuration a set of set variable (each being a
	 *            set of features)
	 * @return a set of set
	 */
	public static Set<Set<String>> setConfigToSetStr(Set<Variable> sv1) {
		Set<Set<String>> sv0 = new HashSet<Set<String>>();

		for (Variable v1 : sv1) {
			Set<String> cft = new HashSet<String>();
			assertTrue(v1 instanceof SetVariable);
			SetVariable ss1 = (SetVariable) v1;
			Set<Variable> fts = ss1.getVars();
			for (Variable ft : fts) {
				assertTrue(ft instanceof FeatureVariable);
				FeatureVariable ftv = (FeatureVariable) ft;
				cft.add(ftv.getFtName());
			}
			sv0.add(cft);
		}

		return sv0;
	}

}
