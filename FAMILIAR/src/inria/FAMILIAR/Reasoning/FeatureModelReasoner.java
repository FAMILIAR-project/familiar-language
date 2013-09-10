package inria.FAMILIAR.Reasoning;

import java.util.Collection;
import java.util.Iterator;

import javax.security.auth.login.Configuration;

import inria.FAMILIAR.Model.ConstantIntConverter;
import inria.FAMILIAR.Model.Constraint;
import inria.FAMILIAR.Model.Feature;
import inria.FAMILIAR.Model.Relation;
import inria.FAMILIAR.Model.Domain.Cardinality;

public class FeatureModelReasoner {

	public void addRoot(Feature root) {
		// TODO Auto-generated method stub
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void addConstraint(Constraint c) {
		// TODO Auto-generated method stub
		
	}

	public void addMandatory(Relation rel, Feature destinationAt, Feature f) {
		// TODO Auto-generated method stub
		
	}

	public void addOptional(Relation rel, Feature destinationAt, Feature f) {
		// TODO Auto-generated method stub
		
	}

	public void addCardinality(Relation rel, Feature destinationAt, Feature f,
			Iterator<Cardinality> cardinalities) {
		// TODO Auto-generated method stub
		
	}

	public void addFeature(Feature f, Collection<Cardinality> cards) {
		// TODO Auto-generated method stub
		
	}

	public void addSet(Relation rel, Feature f, Collection<Feature> children,
			Collection<Cardinality> cards) {
		// TODO Auto-generated method stub
		
	}

	public void applyStagedConfiguration(Configuration conf) {
		// TODO Auto-generated method stub
		
	}

	public void unapplyStagedConfigurations() {
		// TODO Auto-generated method stub
		
	}

	public void setConstantIntConverter(
			ConstantIntConverter constantIntConverter) {
		// TODO Auto-generated method stub
		
	}

}
