package fr.familiar.attributedfm.reasoning;

import fr.familiar.attributedfm.ConstantIntConverter;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.Relation;
import fr.familiar.attributedfm.domain.Cardinality;

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
