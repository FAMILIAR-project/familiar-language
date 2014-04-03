/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

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
