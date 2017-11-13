/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.experimental;

import java.util.Arrays;
import java.util.List;

public class FeatureMapping {
	
	
	private List<String> _left ; 
	private List<String> _right ; 

	public FeatureMapping(List<String> left, String right) {
		this(left, Arrays.asList(new String[] { right })) ;
	}
	
	public FeatureMapping(List<String> left, List<String> right) {
		setLeft(left) ; 
		setRight(right) ; 
	}
	
	public FeatureMapping(String left, String right) {
		this(Arrays.asList(new String[] { left }), Arrays.asList(new String[] { right }));
	}
	
	public FeatureMapping(String left, List<String> right) {
		this(Arrays.asList(new String[] { left }), right );
	}

	public void setLeft(List<String> _left) {
		this._left = _left;
	}

	public List<String> getLeft() {
		return _left;
	}

	public void setRight(List<String> _right) {
		this._right = _right;
	}

	public List<String> getRight() {
		return _right;
	}

}
