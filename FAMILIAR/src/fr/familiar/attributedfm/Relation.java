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
package fr.familiar.attributedfm;

import fr.familiar.attributedfm.domain.Cardinality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author   Manuel Nieto Uc Relation has a name.
 */
public class Relation extends VariabilityElement{
	protected Feature parent_feature;
	protected List<Cardinality> cardinalities;	
	protected List<Feature> destination;
	private String name;
	
	/* Constructors *******************************************************/
	public Relation(){
		this.setName("");
		this.parent_feature = null;
		this.cardinalities = new ArrayList<Cardinality>();
		this.destination = new ArrayList<Feature>();
	}
	
	public Relation(String name){
		this.setName(name);
		this.parent_feature = null;
		this.cardinalities = new ArrayList<Cardinality>();
		this.destination = new ArrayList<Feature>();
	}
	
	/* Parent *************************************************************/
	public Feature getParent(){
		return parent_feature;
	}
	
	public void setParent(Feature f){
		this.parent_feature = f;
	}
	
	public void removeParent(){
		this.parent_feature = null;
	}
	
	/* Cardinalities ******************************************************/
	/**
	 * @return
	 * @uml.property  name="cardinalities"
	 */
	public Iterator<Cardinality> getCardinalities(){
		return this.cardinalities.iterator();
	}
	
	public void addCardinality(Cardinality c){
		this.cardinalities.add(c);
	}
	
	public void removeCardinality(Cardinality c){
		this.cardinalities.remove(c);
	}
	
	/* Destination ********************************************************/
	/**
	 * @return
	 * @uml.property  name="destination"
	 */
	public Iterator<Feature> getDestination(){
		return this.destination.iterator();
	}
	
	public int getNumberOfDestination(){
		return destination.size();
	}
	
	public void addDestination(Feature f){
		if (f != null){
			destination.add(f);
			f.setParent(this);
		}
	}
	
	public void removeDestination(Feature f){
		destination.remove(f);
		f.removeParent();
	}
	
	public Feature getDestinationAt(int i){
		return destination.get(i);
	}
	
	public int getIndexOf(Feature f){
		return destination.indexOf(f);
	}
	
	/* Others *************************************************************/
	public String toString(){
		String res = getName() +": " + parent_feature.getName() + " -> ";
		Iterator<Feature> it = destination.iterator();
		while (it.hasNext()){
			Feature aux = it.next();
			res += aux.getName()+" ";
		}
		return res;
	}
	
	public void remove(){
		if(getParent() != null)
			getParent().removeRelation(this);
		Iterator<Feature> it = getDestination();
		while(it.hasNext()){
			Feature f = it.next();
			f.removeParent();
		}
	}
	
	/* Observers **********************************************************/
	public boolean isMandatory() {
		boolean res;
		
		if (cardinalities.size() == 1) {
			Cardinality card = cardinalities.get(0);
			res = (card.getMin() == 1) && (card.getMax() == 1); 
		} 
		else
			res = false;
		
		return res;
	}
	
	public boolean isOptional() {
		boolean res;
		
		if (cardinalities.size() == 1) {
			Cardinality card = cardinalities.get(0);
			res = (card.getMin() == 0) && (card.getMax() == 1); 
		} 
		else
			res = false;
		
		return res;
	}
	public boolean isAlternative() {
		boolean res;

		if (cardinalities.size() == 1) {
			Cardinality card = cardinalities.get(0);
			res = (card.getMin() == 1) && (card.getMax() == 1);
			res = res && destination.size() > 1;

		} else
			res = false;

		return res;
	}
	public boolean isOr() {
		boolean res;

		if (cardinalities.size() == 1) {
			Cardinality card = cardinalities.get(0);
			res = (card.getMin() == 1) ;
			res = res && destination.size() > 1;
		} else
			res = false;

		return res && !isAlternative();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}