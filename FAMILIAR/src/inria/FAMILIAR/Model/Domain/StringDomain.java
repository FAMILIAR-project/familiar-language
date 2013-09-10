/**
 * 	This file is part of FaMaTS.
 *
 *     FaMaTS is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     FaMaTS is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.
 */

package inria.FAMILIAR.Model.Domain;

import inria.FAMILIAR.Model.AbstractDomainIntConverter;

import java.util.HashSet;
import java.util.Set;


public class StringDomain extends ObjectDomain {

	//TODO el dominio solo debe devolver un valor,
	//la constante que tenga asignada en el sistema scada
	
	private AbstractDomainIntConverter domConverter;
	private String stringVal;
	private int integerVal;
	
	
	public StringDomain(AbstractDomainIntConverter conv, String value){
		domConverter = conv;
		stringVal = value;
		integerVal = domConverter.convertToInteger(stringVal);
	}
	
	public void setValue(String val){
		stringVal = val;
		integerVal = domConverter.convertToInteger(stringVal);
	}
	
	@Override
	public Object getValue(int i){
		if (i == integerVal){
			return stringVal;
		}
		else{
			return null;
		}
	}
	
	@Override
	public Integer getIntegerValue(Object obj){
		if (obj.equals(stringVal)){
			return integerVal;
		}
		else{
			return -1;
		}
		
	}

	@Override
	public Set<Integer> getAllIntegerValues(){
		HashSet<Integer> res = new HashSet<Integer>();
		res.add(integerVal);
		return res;
	}
	
}
