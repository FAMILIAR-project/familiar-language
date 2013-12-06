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
package fr.familiar.attributedfm.reasoning;

import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.ConstantIntConverter;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.Relation;
import fr.familiar.attributedfm.domain.Cardinality;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class FeatureModelTransform {
	
	private FeatureModelReasoner r;
	private AttributedFeatureModel fm;
	
	public void transform(AttributedFeatureModel vmodel, FeatureModelReasoner reasoner) {
		this.r=reasoner;
		fm = (AttributedFeatureModel)vmodel;

		ConstantIntConverter conv = fm.getConstantIntConverter();
		if (conv != null){
			r.setConstantIntConverter(fm.getConstantIntConverter());
		}
		r.reset();
		setFeatureModel(fm);
	}

	private void setFeatureModel(AttributedFeatureModel fm) {
		this.fm = fm;
		generateVariables(fm);
		Feature root = fm.getRoot();
		r.addRoot(root);
		generateConstraints(root);
		Iterator<Constraint> it = fm.getConstraints().iterator();
		while (it.hasNext()){
			Constraint c = it.next();
			r.addConstraint(c);
		}
	}
	

	private void generateConstraints(Feature f) {
		Iterator<Relation> relations = f.getRelations();
		while(relations.hasNext()) {
			Relation rel = relations.next();
			if (rel.getNumberOfDestination() == 1) {
				if (rel.isMandatory()) {
					r.addMandatory(rel,rel.getDestinationAt(0),f);
				} else if (rel.isOptional()) {
					r.addOptional(rel,rel.getDestinationAt(0),f);
				} else {
					r.addCardinality(rel,rel.getDestinationAt(0),f,rel.getCardinalities());
				}
				generateConstraints(rel.getDestinationAt(0));
			}
			else {
				Collection<Feature> children = new ArrayList<Feature>();
				Iterator<Feature> it = rel.getDestination();
				while (it.hasNext()) {
					Feature child = it.next();
					children.add(child);
					generateConstraints(child);
				}
				Collection<Cardinality> cards = new ArrayList<Cardinality>();
				Iterator<Cardinality> itc = rel.getCardinalities();
				while (itc.hasNext()) {
					cards.add(itc.next());
				}
				r.addSet(rel,f,children,cards);
			}
		}
	}
	
	private void generateVariables(AttributedFeatureModel fm) {
		Iterator<Feature> it = fm.getFeatures().iterator();
		while (it.hasNext()) {
			Feature f = (Feature)it.next();
			Relation parentRelation = f.getParent();
			if (parentRelation != null && parentRelation.getNumberOfDestination() == 1 &&
				parentRelation.getCardinalities().hasNext()) {
				Iterator<Cardinality> itc = parentRelation.getCardinalities();
				Collection<Cardinality> cards = new ArrayList<Cardinality>();
				while (itc.hasNext())
					cards.add(itc.next());
				r.addFeature(f,cards);
			}
			else {
				ArrayList <Cardinality> cards = new ArrayList <Cardinality>();
				cards.add(new Cardinality(0,1));
				r.addFeature(f,cards);
			}
		}
	}


}
