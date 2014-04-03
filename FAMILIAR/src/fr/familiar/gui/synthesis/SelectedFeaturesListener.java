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

package fr.familiar.gui.synthesis;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class SelectedFeaturesListener implements TreeSelectionListener {


	private FMSynthesisEnvironment environment;
	
	
	
	public SelectedFeaturesListener(FMSynthesisEnvironment environment) {
		this.environment = environment;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		List<String> selectedFeatures = new ArrayList<String>();
		List<String> unselectedFeatures = new ArrayList<String>();
		for (TreePath path : e.getPaths()) {
			if (e.isAddedPath(path)) {
				selectedFeatures.add(path.getLastPathComponent().toString());	
			} else {
				unselectedFeatures.add(path.getLastPathComponent().toString());
			}
		}
		environment.updateSelectedFeatures(selectedFeatures, unselectedFeatures);
	}

}
