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
/*
 * Created on 10-Jan-2005
 *
 */
package fr.familiar.attributedfm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



/**
 * A product is a set of features.
 */
public class Product {
	
	protected String name;
	
	public  Integer value;
	protected List<Feature> listOfFeatures;
	
	public Product () {
		listOfFeatures = new ArrayList<Feature>();
	}
	
	public int getNumberOfFeatures() {
		return listOfFeatures.size();
	}
	

	
	public void addFeature (Feature f) {
		listOfFeatures.add(f);
	}
	
	public Collection<Feature> getFeatures(){
		return listOfFeatures;
	}
	
	public boolean equals(Object p){
		boolean eq=false;
		if (p instanceof Product){
			Collection<? extends VariabilityElement> listOfFeat1=((Product) p).getFeatures();
			if(listOfFeat1.containsAll(listOfFeatures)&&listOfFeatures.containsAll(listOfFeat1))
				eq=true;
		}
		
		return eq;
	}
	
	@Override
	public String toString(){
		Iterator<Feature> it = listOfFeatures.iterator();
		String str="";
		while  (it.hasNext()){
			VariabilityElement feat = it.next();
			String str2=feat.getName();
			str+=str2+";";
		
		}
		return str;

		
	}

	public boolean removeFeature(Feature f) {
		return listOfFeatures.remove(f);
	}

	public void addAllFeatures(Collection<Feature> allFeatures) {
		listOfFeatures.addAll(allFeatures);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
