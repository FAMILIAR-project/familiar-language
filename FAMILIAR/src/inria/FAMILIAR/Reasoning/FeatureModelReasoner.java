package inria.FAMILIAR.Reasoning;

import inria.FAMILIAR.Model.ConstantIntConverter;
import inria.FAMILIAR.Model.Constraint;
import inria.FAMILIAR.Model.Feature;
import inria.FAMILIAR.Model.Relation;
import inria.FAMILIAR.Model.Domain.Cardinality;

import java.util.Collection;
import java.util.Iterator;

import javax.security.auth.login.Configuration;

public interface FeatureModelReasoner {

	public void addRoot(Feature root);

	public void reset();

	public void addConstraint(Constraint c);

	public void addMandatory(Relation rel, Feature destinationAt, Feature f);

	public void addOptional(Relation rel, Feature destinationAt, Feature f);

	public void addCardinality(Relation rel, Feature destinationAt, Feature f,
			Iterator<Cardinality> cardinalities);

	public void addFeature(Feature f, Collection<Cardinality> cards);

	public void addSet(Relation rel, Feature f, Collection<Feature> children,
			Collection<Cardinality> cards);

	public void applyStagedConfiguration(Configuration conf);

	public void unapplyStagedConfigurations();

	public void setConstantIntConverter(
			ConstantIntConverter constantIntConverter);

}
