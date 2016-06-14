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

package fr.familiar.operations.featureide;

import java.util.Collection;

import org.prop4j.Node;
import org.xtext.example.mydsl.fml.SliceMode;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.editing.NodeCreator;
import fr.familiar.fm.FMLFormula;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.FeatureModelVariable;

/**
 * without FeatureIDE stuff 
 * @author macher1
 *
 */
public class SlicerSATFormulaWithFeatureIDE extends SlicerSATFormula {

	public SlicerSATFormulaWithFeatureIDE() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public FMLFormula sliceFormula (FeatureModelVariable fmv, Collection<String> fts, SliceMode mode) {
				
		FeatureModel fmToSliceFeatureIDE = new FMLtoFeatureIDE(fmv).convert();
		Node node = NodeCreator.createNodes(fmToSliceFeatureIDE) ; 
		FMLFormula rFla = runSliceFormulaSAT(fmv, node,
				fmToSliceFeatureIDE.getFeatureNames(), fts, mode);
		return rFla ;
	}
	
	

}
