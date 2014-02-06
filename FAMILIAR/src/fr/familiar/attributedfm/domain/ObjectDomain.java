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
import java.util.Set;

import fr.familiar.attributedfm.util.BijectiveMap;
import fr.familiar.attributedfm.util.BijectiveMapImpl;

/**
 * Represents a Domain based in Object
 */
public class ObjectDomain extends Domain {
	
	private BijectiveMap<Integer,Object> map;
	
	private int cont;
	
	public ObjectDomain(){
		map = new BijectiveMapImpl<Integer,Object>();
		cont = 1;
	}
	
	public void addValue(Object obj){
		map.put(cont,obj);
		cont++;
	}
	
	@Override
	public Object getValue(int i) {
		return map.get(i);
	}
	
	@Override
	public Integer getIntegerValue(Object obj){
		return map.getKey(obj);
	}

	@Override
	public Set<Integer> getAllIntegerValues() {
		return map.keySet();
	}

	public Collection<Object> getObjectValues(){
		return map.values();
		}
}
