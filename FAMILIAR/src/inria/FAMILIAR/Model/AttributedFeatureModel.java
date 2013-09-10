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
 * Created on 04-Dec-2004
 */
package inria.FAMILIAR.Model;

import inria.FAMILIAR.Model.Domain.Cardinality;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import es.us.isa.util.Node;
import fr.pacogen.model.treeStructure.FeatureModel;

public class AttributedFeatureModel extends FeatureModel{
	
	protected Feature root;
	protected List<Constraint> constraints;
	protected ConstantIntConverter converter;

	public AttributedFeatureModel(){
		super();
		root = null;
		constraints = new ArrayList<Constraint>();
	}
	
	public AttributedFeatureModel(Feature root){
		this();
		this.root = root;
	}
	
	public AttributedFeatureModel(AttributedFeatureModel fm) {
		this();
		this.root = fm.getRoot();
	}
	

	public Feature getRoot() {
		return root;
	}
	
	public void setRoot(Feature root) {
		this.root = root;
	}
	
	public int getFeaturesNumber() {
		int res = 0;
		if(root != null)
			res = getFeaturesNumber(root);
		return res;
	}
	
	private int getFeaturesNumber(Feature f){
		int res = 1;
		Iterator<Relation> it = f.getRelations();
		while(it.hasNext()){
			Relation r = it.next();
			Iterator<Feature> it2 = r.getDestination();
			while(it2.hasNext()){
				Feature f2 = it2.next();
				res += getFeaturesNumber(f2); 
			}
		}		
		return res;
	}
		
	public Collection<Feature> getAttributedFeatures() {
		Collection<Feature> res = new HashSet<Feature>();
		getFeatures(res, root);
		return res;
	}
	
	private void getFeatures(Collection<Feature> c, Feature f){
		c.add(f);
		Iterator<Relation> it = f.getRelations();
		while(it.hasNext()){
			Relation r = it.next();
			Iterator<Feature> it2 = r.getDestination();
			while(it2.hasNext()){
				Feature f2 =  it2.next();
				getFeatures(c,f2); 
			}
		}
	}
	
	public int getNumberOfLevels () {
		Integer res = new Integer(0);
		getNumberOfLevels(res, 0, root);
		return res.intValue() + 1;
	}
	
	private void getNumberOfLevels(Integer maxlevel, int level, Feature f){
		level++;
		if(maxlevel.intValue() < level) 
			maxlevel = new Integer(level);
		Iterator<Relation> it = f.getRelations();
		while(it.hasNext()){
			Relation r = it.next();
			Iterator<Feature> it2 = r.getDestination();
			while(it2.hasNext()){
				Feature f2 = it2.next();
				getNumberOfLevels(maxlevel, level, f2);
			}
		}
	}
	

	public void addConstraint(Constraint c){
		constraints.add(c);
	}

	
	public int getNumberOfConstraints(){
		return constraints.size();
	}
	
	public Collection<Constraint> getConstraints(){
		return constraints;
	}
	
	public Feature searchFeatureByName (String name) {
		Feature res = null;
		if(root != null)
			res = searchFeatureByName (name, root);
		return res;
	}
	
	private Feature searchFeatureByName(String name, Feature f){
		Feature res = null;
		boolean encontrado = false;
		if ( f.getName().equalsIgnoreCase(name) ) {
			res = f;
		}else{			
			Iterator<Relation> it = f.getRelations();
			while(it.hasNext() && !encontrado){
				Relation r = it.next();
				Iterator<Feature> it2 = r.getDestination();
				while(it2.hasNext() && !encontrado){
					Feature f2 = it2.next();
					res = searchFeatureByName(name, f2);
					if (res != null)
						encontrado = true;
				}
			}			
		}
		return res;		
	}
	
	public Relation searchRelationByName(String name){
		Relation res = null;
		if(root != null)
			res = searchRelationByName(name, root); 
		return res;
	}
	
	private Relation searchRelationByName(String name, Feature f){
		Relation res = null;
		boolean encontrado = false;		
					
		Iterator<Relation> it = f.getRelations();
		while(it.hasNext() && !encontrado){
			Relation r =  it.next();
			
			if(r.getName().equals(name)){
				encontrado = true;
				res = r;
			}
				
			Iterator<Feature> it2 = r.getDestination();
			while(it2.hasNext() && !encontrado){
				Feature f2 = it2.next();
				res = searchRelationByName(name, f2);
				if (res != null)
					encontrado = true;
			}
		}			
		
		return res;
	}
	
	public String toString() {
		String res = "Feature model:\n" + root.fmToString();
		
		Iterator<Constraint> it1 = constraints.iterator();
		if (it1.hasNext()){
			res+= "Dependencies:\n";
		}
		while (it1.hasNext()){
			res+=it1.next()+"\n";
		}
		

		
		return res;
	}


	private void getRelations(Collection<Relation> c, Feature f) {
		Iterator<Relation> it = f.getRelations();
		while(it.hasNext()){
			Relation r = (Relation) it.next();
			c.add(r);
			Iterator<Feature> it2 = r.getDestination();
			while(it2.hasNext()){
				Feature f2 = (Feature) it2.next();
				getRelations(c,f2); 
			}
		}
	}



	private boolean isMandatory(Relation parentRelation) {
		boolean res = false;
		if (parentRelation.getNumberOfDestination()==1) {
			Iterator<Cardinality> itc = parentRelation.getCardinalities();
			if (itc.hasNext()) {
				Cardinality card = itc.next();
				if (card.getMin() == 1 && card.getMax() == 1 && !itc.hasNext())
					res = true;
			}
		}
		return res;
	}
	
	public void addAttributeRelationship(ComplexConstraint r){
		if (isValidAttRelation(r)){
			constraints.add(r);
		}
		else{
			throw new IllegalArgumentException("Illegal Attribute Relationship: "+r);
		}
		
	}

	private boolean isValidAttRelation(ComplexConstraint r) {
		Node<String> n = r.getAST().getRootElement();
		return isValidAttRelation(n);
	}

	private boolean isValidAttRelation(Node<String> n) {
		boolean res = true;
		if (n.getNumberOfChildren() == 0){
			//caso base
			res = isValidAttribute(n.getData()) || checkIfNumber(n.getData());
		}
		else{
			//comprobamos la validez de cada uno de los hijos
			Iterator<Node<String>> it = n.getChildren().iterator();
			while (it.hasNext() && res){
				Node<String> node = it.next();
				res = res && isValidAttRelation(node);
			}
		}
		return res;
	}

	private boolean isValidAttribute(String data) {
		int index = data.indexOf('.');
		if (index > 0){
			//notacion feature.attribute
			String feat = data.substring(0,index);
			//eliminamos el '.'
			String att = data.substring(index + 1);
			Feature f = this.searchFeatureByName(feat);
			if (f != null){
				return (f.searchAttributeByName(att) != null);
			}
		}
		return false;
	}
	
	private boolean checkIfNumber(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
	public void setConstantIntConverter(ConstantIntConverter conv){
		converter = conv;
	}
	
	public ConstantIntConverter getConstantIntConverter(){
		return converter;
	}
}
