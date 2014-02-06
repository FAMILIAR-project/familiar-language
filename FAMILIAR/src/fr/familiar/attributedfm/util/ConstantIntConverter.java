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

package fr.familiar.attributedfm.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.familiar.attributedfm.AbstractDomainIntConverter;

public class ConstantIntConverter {

	//XXX es muy importante el orden el que añadamos
	//los conversores, porque unos pueden solaparse con otros
	//para nuestro caso, meter el string converter el ultimo
	private List<AbstractDomainIntConverter> intConverters;
	
	public ConstantIntConverter(){
		intConverters = new LinkedList<AbstractDomainIntConverter>();
	}
	
	public Integer translate2Integer(String constant){
		Integer res = null;
		Iterator<AbstractDomainIntConverter> it = intConverters.iterator();
		while (it.hasNext() && res == null){
			AbstractDomainIntConverter conv = it.next();
			if (conv.canTranslate(constant)){
				res = conv.convertToInteger(constant);
			}
		}
		return res;
	}
	
	public void addIntConverter(AbstractDomainIntConverter conv){
		intConverters.add(conv);
	}
	
}
