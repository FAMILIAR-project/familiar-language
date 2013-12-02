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
public class SetRealDomain extends IntegerDomain {

	private Set<Float> values;
	
	public SetRealDomain(){
		values = new HashSet<Float>();
	}
	
	public SetRealDomain(Set<Float> vals){
		values = vals;
	}
	
	public Set<Integer> getAllIntegerValues() {
		Set<Integer> res = new HashSet<Integer>();
		for(Float f:values){
			res.add(f.intValue());
		}
		return res;
	}
	
	public Set<Float> getAllFloatValues() {
		return values;
	}
	public void addValue(Float i){
		values.add(i);
	}

}
