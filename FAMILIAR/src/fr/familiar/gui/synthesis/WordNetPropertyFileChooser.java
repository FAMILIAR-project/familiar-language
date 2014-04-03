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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import fr.familiar.gui.FamiliarEditor;


public class WordNetPropertyFileChooser {

	private WordNetPropertyFileChooser() {

	}

	/**
	 * Return the current instance of the WordNet dictionary or null if it does not exist
	 * @return
	 */
	public static File getInstance() {
		File propertiesFile = null;
		JFileChooser fileChooser = new JFileChooser();
		int choice = fileChooser.showOpenDialog(FamiliarEditor.INSTANCE);
		if (choice == JFileChooser.APPROVE_OPTION) {
			propertiesFile = fileChooser.getSelectedFile();
			//				try {
			//					dictionary = Dictionary.getInstance(new FileInputStream(propertiesFile));
			//					Dictionary.setInstance(dictionary);
			//				} catch (FileNotFoundException e) {
			//					e.printStackTrace();
			//				} catch (JWNLException e) {
			//					e.printStackTrace();
			//				}
		}	
		return propertiesFile;
	}

}
