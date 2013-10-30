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

/**
 * Represents a Domain bases in integers
 */
public abstract class IntegerDomain extends Domain {

	@Override
	public Integer getValue(int i) {
		return i;
	}
	
	@Override
	public Integer getIntegerValue(Object o){
		if (o instanceof Integer){
			return (Integer)o;
		}
		else{
			return null;
		}
	}

}
