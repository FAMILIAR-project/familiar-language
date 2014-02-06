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
package fr.familiar.attributedfm.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BijectiveMapImpl<K, V> implements BijectiveMap<K, V> {

	private Map<K, V> kToV;
	
	private Map<V, K> vToK;
	
	public BijectiveMapImpl(){
		kToV = new HashMap<K, V>();
		vToK = new HashMap<V, K>();
	}
	
	
	public K getKey(V val) {
		return vToK.get(val);
	}

	
	public void clear() {
		kToV.clear();
		vToK.clear();
	}

	
	public boolean containsKey(Object arg0) {
		boolean res = kToV.containsKey(arg0);
		return res;
	}

	
	public boolean containsValue(Object arg0) {
		boolean res = kToV.containsValue(arg0);
		return res;
	}

	
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return kToV.entrySet();
	}

	
	public V get(Object arg0) {
		return kToV.get(arg0);
	}

	
	public boolean isEmpty() {
		return kToV.isEmpty();
	}

	
	public Set<K> keySet() {
		return kToV.keySet();
	}

	
	public V put(K arg0, V arg1) {
		V res = kToV.put(arg0, arg1);
		vToK.put(arg1, arg0);
		return res;
	}

	
	public void putAll(Map<? extends K, ? extends V> arg0) {
		kToV.putAll(arg0);
		Collection<? extends K> keys = arg0.keySet();
		Iterator<? extends K> it = keys.iterator();
		while (it.hasNext()){
			K clave = it.next();
			V valor = arg0.get(clave);
			vToK.put(valor, clave);
		}
	}

	
	public V remove(Object arg0) {
		V res = kToV.remove(arg0);
		vToK.remove(res);
		return res;
	}

	
	public int size() {
		return kToV.size();
	}

	
	public Collection<V> values() {
		return kToV.values();
	}

}
