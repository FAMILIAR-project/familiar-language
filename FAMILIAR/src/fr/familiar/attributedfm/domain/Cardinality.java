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
 * Cardinality represent the cardinality of a relation, it has a max value and a min value.
 */
public class Cardinality {	
	
	private int min;
	
	private int max;
	
	public Cardinality(int min, int max){
		this.min = min;
		this.max = max;
	}
	
	
	public int getMin(){
		return this.min;
	}
	
	
	public void setMin(int min){
		this.min = min;
	}
	
	
	public int getMax(){
		return this.max;
	}
	
	
	public void setMax(int max){
		this.max = max;
	}
	
	public String toString() {
		return "[" + min + "," + max + "]";
	}
}