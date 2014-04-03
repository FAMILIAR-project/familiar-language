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

package fr.familiar.gui.synthesis.actions;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class IgnoreParentAction implements SynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private String feature;
	private String parent;
	
	public IgnoreParentAction(InteractiveFMSynthesizer synthesizer, String feature, String parent) {
		this.synthesizer = synthesizer;
		this.feature = feature;
		this.parent = parent;
	}
	
	@Override
	public void undo() {
		synthesizer.resetParentCandidates(feature);
		// TODO : restore previous actions except this action
	}
}
