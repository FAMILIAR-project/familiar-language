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

import java.util.Set;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SelectClusterParentAction implements SynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private Set<String> children;
	private String parent;

	public SelectClusterParentAction(InteractiveFMSynthesizer synthesizer, Set<String> children, String parent) {
		this.synthesizer = synthesizer;
		this.children = children;
		this.parent = parent;
	}

	@Override
	public void undo() {
		for (String child : children) {
			synthesizer.resetParentCandidates(child);
		}
	}
}
