/**
 * 
 */
package fr.unice.polytech.modalis.familiar.fm.converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author macher1
 *
 */
public class FMDimacsReader {

	protected Map<Integer, String> _var2IDs = new HashMap<Integer, String>();
	
	protected Set<String> _fakes = new HashSet<String>();
	
	
	

	protected String extractVarName(String line, int l) {
		return line.substring(l + 2);
	}
	
	/**
	 * @param line
	 * @return
	 */
	protected int extractNumber(String line) {

		char[] cars = line.substring(2).toCharArray();
		String number = "";
		for (int i = 0; i < cars.length; i++) {
			if (Character.isDigit(cars[i])) {
				number += cars[i];
			} else
				break;
		}

		return Integer.parseInt(number.trim());
	}

	public Collection<String> getDomain() {
		return _var2IDs.values() ; 
	}

	public Set<String> getFakes() {
		return _fakes ; 
	}
}
