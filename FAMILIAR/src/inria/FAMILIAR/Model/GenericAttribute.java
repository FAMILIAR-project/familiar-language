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
package inria.FAMILIAR.Model;

import inria.FAMILIAR.Model.Domain.BoundedElement;
import inria.FAMILIAR.Model.Domain.Domain;

/**
 * 
 */
public class GenericAttribute extends BoundedElement {

	protected Feature feature;

	protected Object value;

	protected Object defaultValue;

	protected Object nullValue;

	public static final int OBJECT_NULL_VALUE = -1;

	public GenericAttribute(String n, Domain d, Object nv, Object dv) {
		domain = d;
		name = n;
		defaultValue = dv;
		nullValue = nv;
		value = null;
	}

	public Object getNullValue() {
		return nullValue;
	}

	public void setNullValue(Object nullValue) {
		this.nullValue = nullValue;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object o) {
		value = o;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object o) {
		defaultValue = o;
	}

	public Feature getFeature() {
		return feature;
	}

	public String toString() {
		return getFullName();
	}

	public String getFullName() {
		return feature.getName() + "." + name;
	}

	public Integer getIntegerValue(Object o) {
		Integer res;
		if (o instanceof Integer) {
			res = (Integer) o;
		} else {
			if (o.equals(nullValue)) {
				res = OBJECT_NULL_VALUE;
			} else {
				res = domain.getIntegerValue(o);
			}
		}
		// Integer res = domain.getIntegerValue(o);
		return res;
	}

	public boolean equals(Object o) {
		boolean b = false;
		if (o instanceof GenericAttribute) {
			GenericAttribute aux = (GenericAttribute) o;
			if (aux.getFeature().equals(feature) && aux.getName().equals(name)) {
				b = true;
			}
		}
		return b;
	}

	public boolean hasFixedValue() {
		return (value != null);
	}
}
