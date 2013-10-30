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
public class RangeIntegerDomain extends IntegerDomain {

	private Set<Range> ranges;
	
	public RangeIntegerDomain(){
		ranges = new HashSet<Range>();
	}
	
	public RangeIntegerDomain(Collection<Range> r){
		ranges = new HashSet<Range>(r);
	}
	
	@Override
	public Set<Integer> getAllIntegerValues() {
		// TODO pensar si esta es la mejor forma de hacerlo
		Set<Integer> res = new HashSet<Integer>();
		Iterator<Range> it = ranges.iterator();
		while (it.hasNext()){
			Range r = it.next();
			int min = r.getMin();
			int max = r.getMax();
			for (int i = min; i < max; i++){
				res.add(i);
			}
		}
		return res;
	}
	
	public Set<Range> getRanges(){
		return ranges;
	}
	
	public void addRange(Range r){
		ranges.add(r);
	}


}
