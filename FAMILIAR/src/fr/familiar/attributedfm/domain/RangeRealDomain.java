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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Represents a Domain bases range of integers
 */
public class RangeRealDomain extends Domain {

	private Set<RealRange> ranges;
	
	public RangeRealDomain(){
		ranges = new HashSet<RealRange>();
	}
	
	public RangeRealDomain(Collection<RealRange> r){
		ranges = new HashSet<RealRange>(r);
	}
	
	@Override
	public Set<Integer> getAllIntegerValues() {
		// TODO pensar si esta es la mejor forma de hacerlo
		System.out.println("we cannot do this for reals");
		return null;
	}
	
	public Set<RealRange> getRanges(){
		return ranges;
	}
	
	public void addRange(RealRange r){
		ranges.add(r);
	}

	@Override
	public Object getValue(int i) {
		return null;
	}

	@Override
	public Integer getIntegerValue(Object o) {
		return null;
	}
	
	

}
