/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.attributedfm.reasoning.pairwise;

import fr.familiar.attributedfm.Feature;


public class Pair {

	public Feature featurea;
	public Feature featureb;
	
	public Pair(Feature featurea, Feature featureb){
		this.featurea=featurea;
		this.featureb=featureb;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Pair){
			Pair newPair=(Pair)o;
			if((featurea.getName().equals(newPair.featureb.getName())&&featureb.getName().equals(newPair.featurea.getName()))||(featurea.getName().equals(newPair.featurea.getName())&&featureb.getName().equals(newPair.featureb.getName()))){
				return true;
			}else{
				return false;
			}
		}else{
			return false;

		}
		
	}
	
}
