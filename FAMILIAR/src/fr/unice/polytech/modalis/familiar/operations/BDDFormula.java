/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

/**
 * @author macher
 *
 */
public class BDDFormula extends Formula<String> implements FMLFormula {
	
	public BDDFormula (Formula<String> fla, BDDBuilder<String> builder) {
		super(fla.getBDD(), fla.getDomain(), builder) ;
	}
	
	public BDDFormula (Formula<String> fla) {
		super(fla.getBDD(), fla.getDomain(), null) ;
	}


}
