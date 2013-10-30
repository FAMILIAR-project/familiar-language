/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.familiar.attributedfm.domain;

import java.util.HashSet;
import java.util.Set;


/**
 * Represents a Domain based in set of integers
 */
public class SetIntegerDomain extends IntegerDomain {

	private Set<Integer> values;
	
	public SetIntegerDomain(){
		values = new HashSet<Integer>();
	}
	
	public SetIntegerDomain(Set<Integer> vals){
		values = vals;
	}
	
	@Override
	public Set<Integer> getAllIntegerValues() {
		return values;
	}
	
	public void addValue(Integer i){
		values.add(i);
	}

}
