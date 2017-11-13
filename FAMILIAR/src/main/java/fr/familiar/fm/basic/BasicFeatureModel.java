/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.fm.basic;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.FeatureEdgeKind;

/**
 * @author mathieuacher
 * 
 */
public class BasicFeatureModel extends AbstractFeatureModel {

	private FeatureTree<AbstractFeature, FeatureEdgeKind> tree;

	/**
	 * 
	 */
	public BasicFeatureModel() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.familiar.fm.basic.FeatureModel#counting()
	 */
	@Override
	int counting() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.familiar.fm.basic.FeatureModel#getRoot()
	 */
	@Override
	AbstractFeature getRoot() {
		return tree.getRoot();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.fm.basic.FeatureModel#getFeatures()
	 */
	@Override
	Set<AbstractFeature> getFeatures() {
		return tree.getNodes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.familiar.fm.basic.FeatureModel#isValid()
	 */
	@Override
	boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	String format() {
		AbstractFeature root = getRoot();
		formatFeature(root);
		Set<AbstractFeature> childFeatures = root.getChildren();
		for (AbstractFeature feature : childFeatures) {
			formatFeature(feature);
		}
		for (AbstractFeature feature : childFeatures) { // recrusively
			formatRecursivelyFeature(feature);
		}

		return null;
	}

	private Set<String> formatRecursivelyFeature(AbstractFeature feature) {
		Set<String> r = new HashSet<String>();
		Set<AbstractFeature> childrenFeatures = feature.getChildren();
		for (AbstractFeature ft : childrenFeatures) {
			r.add(formatFeature(ft));
		}
		for (AbstractFeature ft : childrenFeatures) {
			r.addAll(formatRecursivelyFeature(ft));
		}
		return r;
	}

	private String formatFeature(AbstractFeature feature) {
		return feature.getName();

	}

	@Override
	Set<FMConstraint> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

}
