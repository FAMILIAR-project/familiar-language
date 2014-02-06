package fr.familiar.attributedfm.reasoning;

import java.util.Collection;
import java.util.Iterator;

import fr.familiar.attributedfm.Configuration;
import fr.familiar.attributedfm.ConstantIntConverter;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.Relation;
import fr.familiar.attributedfm.domain.Cardinality;



public abstract class FeatureModelReasoner {

	public ConstantIntConverter constantIntConverter= new ConstantIntConverter();
	
	public abstract void addRoot(Feature root);

	public abstract void reset();

	public abstract void addConstraint(Constraint c);

	public abstract void addMandatory(Relation rel, Feature destinationAt, Feature f);

	public abstract void addOptional(Relation rel, Feature destinationAt, Feature f);

	public abstract void addCardinality(Relation rel, Feature destinationAt, Feature f,	Iterator<Cardinality> cardinalities);

	public abstract void addFeature(Feature f, Collection<Cardinality> cards);

	public abstract void addSet(Relation rel, Feature f, Collection<Feature> children,	Collection<Cardinality> cards);

	public abstract void applyStagedConfiguration(Configuration conf);

	public abstract void unapplyStagedConfigurations();

	public void setConstantIntConverter(ConstantIntConverter constantIntConverter){this.constantIntConverter=constantIntConverter;};

	public abstract void addRequires(Relation rel, Feature origin, Feature destination);

	public abstract void addExcludes(Relation rel, Feature origin, Feature destination);
}
