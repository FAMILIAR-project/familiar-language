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

import inria.FAMILIAR.Model.Domain.Domain;
import inria.FAMILIAR.Model.Domain.SetIntegerDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.us.isa.util.Node;

public class Feature {
	
	/**
	 * @uml.property  name="name"
	 */
	protected Relation parent_relation;
	protected List<Relation> relations;
	protected Map<String,GenericAttribute> attributes;
	protected List<Constraint> invariants;
	protected String name;
	private Domain domain;
	
	/* Constructors *******************************************************/
	public Feature () {
		this("");
	}
	
	public Feature (String name) {
		//inicializamos el dominio en este constructor
		SetIntegerDomain d = new SetIntegerDomain();
		d.addValue(0);
		d.addValue(1);
		this.setDomain(d);
		this.name = name;
		this.parent_relation = null;
		this.relations = new ArrayList<Relation>();
		this.attributes = new HashMap<String,GenericAttribute>();
		this.invariants = new ArrayList<Constraint>();
	}
	
	public Feature(String name, Collection<GenericAttribute> atts){
		this(name);
		Iterator<GenericAttribute> it = atts.iterator();
		while (it.hasNext()){
			GenericAttribute aux = it.next();
			attributes.put(aux.getName(), aux);
		}
		//attributes.addAll(atts);
	}
	
	
	/* Parent *************************************************************/
	public Relation getParent(){
		return parent_relation;
	}
	
	public void setParent(Relation r){
		parent_relation = r;
	}
	
	public void removeParent(){
		this.parent_relation = null;
	}
	
	/* Relations **********************************************************/
	public void addRelation (Relation r) {
		relations.add(r);
		r.setParent(this);
	}

	/**
	 * @return
	 * @uml.property  name="relations"
	 */
	public Iterator<Relation> getRelations () {
		return relations.iterator();
	}
	
	public void removeRelation(Relation r){
		relations.remove(r);
		r.removeParent();
	}
	
	public void removeAllRelations(){
		relations = new ArrayList<Relation>();
	}
	
	public int getNumberOfRelations() {
		return relations.size();
	}
	
	public Relation getRelationAt(int i){
		return relations.get(i);
	}
	
	public int getIndexOf(Relation r){
		return relations.indexOf(r);
	}
	
	/* Attributes *********************************************************/
	protected void newAttribute (GenericAttribute a) {
		attributes.put(a.getName(),a);
	}
	
	public void addAttributes(Collection<GenericAttribute> c){
		Iterator<GenericAttribute> it = c.iterator();
		while (it.hasNext()){
			GenericAttribute aux = it.next();
			attributes.put(aux.getName(), aux);
			//this.addAttribute(aux);
		}
	}

	/**
	 * @return
	 * @uml.property  name="attributes"
	 */
	public Collection<GenericAttribute> getAttributes() {
		return attributes.values();
	}
	
	public int getNumberOfAttributes() {
		return attributes.size();
	}
	
	/* Others *************************************************************/
	public String toString() {
		String res = name+"\n";
		Iterator<GenericAttribute> it1 = attributes.values().iterator();
		while (it1.hasNext()){
			GenericAttribute aux = it1.next();
			res+= aux+"\n";
		}
		
		Iterator<Constraint> it2 = invariants.iterator();
		if (it2.hasNext()){
			res+="Invariants:\n";
		}		
		while (it2.hasNext()){
			Constraint inv = it2.next();
			res+=inv+"\n";
		}
		return res;
	}

	/*
	 * @Override
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Feature) {
			Feature f = (Feature)obj;
			return this.name.equalsIgnoreCase(f.getName());
		}
		return res;
	}
	
	public String getName() {
		return name;
	}

	public void remove(){
		if(getParent() != null)
			getParent().remove();
		Iterator<Relation> it = getRelations();		
		while(it.hasNext()){
			Relation r = it.next();
			r.remove();
		}
	}

	public Collection<Constraint> getInvariants() {
		return invariants;
	}
	
	public void addInvariant(Constraint inv){
		if (isValidInvariant(inv)){
			invariants.add(inv);
		}
		else{
			throw new IllegalArgumentException("Illegal invariant: "+inv);
		}	
	}
	
	public void addUncheckedInvariant(Constraint inv){
		invariants.add(inv);
	}
	
	public void addInvariants(Collection<Constraint> invs){
		Iterator<Constraint> it = invs.iterator();
		while (it.hasNext()){
			Constraint aux = it.next();
			addInvariant(aux);
		}
	}
	
	public void addUncheckedInvariants(Collection<Constraint> invs){
		invariants.addAll(invs);
//		Iterator<Constraint> it = invs.iterator();
//		while (it.hasNext()){
//			Constraint aux = it.next();
//			addUncheckedInvariant(aux);
//		}
	}

	/*
	 * Comprueba si una invariante es sintacticamente correcta
	 */
	private boolean isValidInvariant(Constraint inv) {
		Node<String> n = inv.getAST().getRootElement();
		return isValidInvariant(n);
	}

	/*
	 * Metodo recursivo para comprobar la validez sintactica
	 * de la invariante
	 */
	private boolean isValidInvariant(Node<String> n) {
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
				res = res && isValidInvariant(node);
			}
		}
		return res;
	}

	/*
	 * Comprueba si una cadena representa de forma valida un atributo
	 * de la feature actual
	 */
	private boolean isValidAttribute(String data) {
		int index = data.indexOf('.');
		String att;
		if (index > 0){
			//notacion feature.attribute
			String feat = data.substring(0,index);
			//eliminamos el '.'
			att = data.substring(index + 1);
			if (!feat.equals(this.name)){
				//la feature referida no es esta
				return false;
			}
		}
		else{
			//notacion attribute
			att = data;
		}
		return (this.attributes.get(att)!= null);
	}

	public GenericAttribute searchAttributeByName(String name) {
		return attributes.get(name);
	}
	
	/*
	 * Version recursiva de toString(), mostrando la propia feature
	 * y todas sus hijas. Utilizada para imprimir el feature model entero
	 */
	public String fmToString(){
		String res = toString();
		Iterator<Relation> it = relations.iterator();
		while (it.hasNext()){
			Relation aux = it.next();
			Iterator<Feature> itFeats = aux.getDestination();
			while (itFeats.hasNext()){
				Feature f = itFeats.next();
				res+=f.fmToString();
			}
		}
		return res;
	}
	

	private boolean checkIfNumber(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
}
