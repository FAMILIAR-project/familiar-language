/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.fm.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author macher1
 *
 */
public class FMDimacsReader {

	protected BiMap<Integer, String> _var2IDs = HashBiMap.create();
	
	protected Set<String> _fakes = new HashSet<String>();
	

	
	public String getVariable(Integer lt) {
		return _var2IDs.get(lt);
	}

		
	public Integer getVariable(String id) {
		return _var2IDs.inverse().get(id);
	}

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
