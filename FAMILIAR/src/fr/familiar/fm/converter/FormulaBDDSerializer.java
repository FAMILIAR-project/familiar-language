/**
 * 
 */
package fr.familiar.fm.converter;

import gsd.synthesis.Formula;

import java.io.IOException;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

/**
 * @author mathieuacher
 * 
 */
public class FormulaBDDSerializer {

	public static void save(String fileName, Formula<String> fla)
			throws IOException {
		BDD oBDD = fla.getBDD();
		BDDFactory oBDDFactory = oBDD.getFactory();

		oBDDFactory.save(fileName, oBDD);

	}

}
