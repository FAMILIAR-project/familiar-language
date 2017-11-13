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

package fr.familiar.experimental.featureide;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.editing.ModelComparator;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.familiar.fm.FMLFormula;
import fr.familiar.variable.Comparison;

/**
 * @author macher1
 *
 */
public class FMComparator {
	
	public static Comparison convert(de.ovgu.featureide.fm.core.editing.Comparison comparison) {
		if (comparison == de.ovgu.featureide.fm.core.editing.Comparison.REFACTORING)
			return Comparison.REFACTORING;
		else if (comparison == de.ovgu.featureide.fm.core.editing.Comparison.SPECIALIZATION)
			return Comparison.SPECIALIZATION;
		else if (comparison == de.ovgu.featureide.fm.core.editing.Comparison.GENERALIZATION)
			return Comparison.GENERALIZATION;
		else if (comparison == de.ovgu.featureide.fm.core.editing.Comparison.ARBITRARY)
			return Comparison.ARBITRARY;
		else // not scaling
			return null ;
	}
	
	/**
	 * @param contentFM1
	 *            str representation of the first feature model
	 * @param contentFM2
	 *            str representation of the second feature model
	 * @return relationship in terms of sets of configuration between FM1 and
	 *         FM2: specialization, generalization, arbitrary edit, refactoring
	 */
	public Comparison compare(String contentFM1, String contentFM2) {

		FeatureModel FM1 = new FeatureModel();
		FeatureModel FM2 = new FeatureModel();

		try {
			new GuidslReader(FM1).readFromString(contentFM1);
			new GuidslReader(FM2).readFromString(contentFM2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Configuration cfm1 = new Configuration(FM1, true) ; Configuration
		 * cfm2 = new Configuration(FM2, true) ;
		 */

		// FMGenerator.featureIDEtoFMCalc(FM1, "plop", 1);
		// FMGenerator.featureIDEtoFMCalc(FM2, "plop", 2);

		ModelComparator comparator = new ModelComparator(FMLFormula.SAT_TIMEOUT); // TODO: look
																	// at
																	// different
																	// strategies
																	// (Strategy.WithoutIdenticalRules,
																	// etc.)
		de.ovgu.featureide.fm.core.editing.Comparison comparison = comparator.compare(FM1, FM2);
		/*
		 * try { Configuration c = comparator.calculateExample(true);
		 * System.out.println ("selected: " + c.getSelectedFeatures());
		 * System.out.println ("deselected: " + c.getUnSelectedFeatures()); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		return convert(comparison);

	}

}
