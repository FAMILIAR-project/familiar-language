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
package fr.familiar.gui.featureide;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.MessageBox;

import fr.familiar.views.featureide.FeatureModelLabel;

public class FMLabelListener implements MouseListener {

	private FeatureModelLabel featureModelLabel;

	public FMLabelListener(FeatureModelLabel fml) {
		this.featureModelLabel = fml;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		MessageBox box = new MessageBox(featureModelLabel.getParent()
				.getShell(), SWT.ICON_INFORMATION);
		box.setMessage(featureModelLabel.getInformations());
		box.open();
		System.out
				.println("displaying the feature model in the FeatureIDE editor");
		featureModelLabel.getFeatureModelVariable().gdisplay();
		System.out
				.println("(end) displaying the feature model in the FeatureIDE editor");
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
