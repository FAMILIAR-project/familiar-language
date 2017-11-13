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

package fr.familiar.gui.Difference;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public class CommentDiffDialog {


	private JDialog dialog;
	private JButton ok;
	private JButton cancel;
	private JTextField input;
	
	public CommentDiffDialog() {
	
		
		// Create the dialog
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel message = new JLabel("Compare and enter your comment :");
		panel.add(message);
		input = new JTextField();
		panel.add(input);
		ok = new JButton("Ok");
		ok.setEnabled(false);
		cancel = new JButton("Cancel");
		
		JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.NO_OPTION, null, new JButton[]{ok, cancel}, ok);
		dialog = pane.createDialog("Clustering threshold");
		
		// Set the behaviour of the components
		ok.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		// Display the dialog
		dialog.setVisible(true);
	}

	/**
	 * Returns the new threshold value 
	 * or -1 if the user did not entered one.
	 * @return
	 */
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(ok)) {
				Double threshold = Double.parseDouble(input.getText());
			}
			
			dialog.setVisible(false);
		}
		
	}
	
	private class ThresholdChecker implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			checkThreshold(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			checkThreshold(e);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
		
		private void checkThreshold(DocumentEvent event) {
			Document doc = event.getDocument();
			try {
				String thresholdText = doc.getText(0, doc.getLength());
				double thresholdValue = Double.parseDouble(thresholdText);
				if (thresholdValue >= 0 && thresholdValue <=1) {
					ok.setEnabled(true);	
				} else {
					ok.setEnabled(false);
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				ok.setEnabled(false);
			}
		}
	}
}
