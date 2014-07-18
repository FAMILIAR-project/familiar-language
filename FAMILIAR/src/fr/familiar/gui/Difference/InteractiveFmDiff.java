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

package fr.familiar.gui.Difference;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;

import java.util.ArrayList;
import java.util.List;

public class InteractiveFmDiff {
	
	private FeatureModelVariable fmv1;
	private FeatureModelVariable fmv2;
	private List<FeatureEdge> fm1_diffEdges  = new ArrayList<FeatureEdge>();
	private List<FeatureEdge> fm2_diffEdges  = new ArrayList<FeatureEdge>();
	
	public FeatureModelVariable getFeatureModelVariable1() {
		return fmv1;
	}
	public FeatureModelVariable getFeatureModelVariable2() {
		return fmv2;
	}
	public List<FeatureEdge> getFm1_diffEdges() {
		return fm1_diffEdges;
	}
	public List<FeatureEdge> getFm2_diffEdges() {
		return fm2_diffEdges;
	}
	
	
}
