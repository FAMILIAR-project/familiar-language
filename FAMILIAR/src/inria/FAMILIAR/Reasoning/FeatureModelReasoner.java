package inria.FAMILIAR.Reasoning;

import java.util.Collection;
import java.util.Iterator;

import javax.security.auth.login.Configuration;

import inria.FAMILIAR.Model.ConstantIntConverter;
import inria.FAMILIAR.Model.Constraint;
import inria.FAMILIAR.Model.Feature;
import inria.FAMILIAR.Model.Relation;
import inria.FAMILIAR.Model.Domain.Cardinality;

public abstract class FeatureModelReasoner {

	public abstract void addRoot(Feature root);
	public abstract void reset();
	public abstract void addConstraint(Constraint c);
	public abstract void addMandatory(Relation rel, Feature destinationAt, Feature f);
	public abstract void addOptional(Relation rel, Feature destinationAt, Feature f);
	public abstract void addCardinality(Relation rel, Feature destinationAt, Feature f,
			Iterator<Cardinality> cardinalities);
	public abstract void addFeature(Feature f, Collection<Cardinality> cards);
	public abstract void addSet(Relation rel, Feature f, Collection<Feature> children,
			Collection<Cardinality> cards);
	public abstract void applyStagedConfiguration(Configuration conf);
	public abstract void unapplyStagedConfigurations();
	public abstract void setConstantIntConverter(
			ConstantIntConverter constantIntConverter);

}
