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

import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SynthesisTabListener implements ChangeListener {
	
	private JMenu synthesisMenu;

	public SynthesisTabListener(JMenu synthesisMenu) {
		this.synthesisMenu = synthesisMenu;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabs = (JTabbedPane) e.getSource();
	    if (tabs.getSelectedComponent() instanceof FMSynthesisEnvironment) {
	    	synthesisMenu.setEnabled(true);			    	
	    } else {
	    	synthesisMenu.setEnabled(false);
	    }
	}

}
