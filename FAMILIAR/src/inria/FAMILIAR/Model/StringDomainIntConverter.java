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

package inria.FAMILIAR.Model;

import java.util.LinkedList;
import java.util.List;

public class StringDomainIntConverter extends AbstractDomainIntConverter {

	private List<String> strings;
	
	public StringDomainIntConverter(){
		strings = new LinkedList<String>();
	}
	
	public Integer convertToInteger(Object o) {
		if (o instanceof String){
			Integer res = strings.indexOf(o);
			if (res < 0){
				strings.add(o.toString());
				res = strings.size() - 1;
			}
			return res;
		}
		else{
			return -1;
		}
		
	}
	
	public boolean canTranslate(Object o){
		return (o instanceof String);
	}

}
