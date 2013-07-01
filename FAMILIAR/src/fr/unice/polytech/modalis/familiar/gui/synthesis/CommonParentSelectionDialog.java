package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CommonParentSelectionDialog {

	private FMSynthesisEnvironment environment;
	private String selectedParent;
	private Set<String> selectedChildren;
	private JDialog dialog;
	private JComboBox parentSelector;

	public CommonParentSelectionDialog(FMSynthesisEnvironment environment, String title, String message, Set<String> options, Set<String> initialOptions) {
		this.environment = environment;
		
		selectedParent = null;
		selectedChildren = new HashSet<String>(initialOptions);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel messageLabel = new JLabel(message);
		panel.add(messageLabel);

		// Define check boxes
		HashMap<JCheckBox, String> optionMap = new HashMap<JCheckBox, String>();
		List<JCheckBox> input = new ArrayList<JCheckBox>();

		for (String option : options) {
			JCheckBox checkBox = new JCheckBox(option.toString(), initialOptions.contains(option));
			checkBox.addItemListener(new CheckBoxListener(option));
			optionMap.put(checkBox, option);
			input.add(checkBox);
			panel.add(checkBox);
		}

		// Define parent selector
		Set<String> possibleParents = environment.getPossibleParents(initialOptions);
		parentSelector = new JComboBox(possibleParents.toArray());
		parentSelector.setEditable(false);
		panel.add(parentSelector);

		// Define buttons
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");

		JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.NO_OPTION, null, new JButton[]{ok, cancel}, ok);
		dialog = pane.createDialog(title);

		// Set the behaviour of the buttons
		ok.addActionListener(new OkButtonListener());
		cancel.addActionListener(new CancelButtonListener());
	}

	public void show() {
		dialog.setVisible(true);
	}

	public Set<String> getSelectedChildren() {
		return selectedChildren;
	}

	public String getSelectedParent() {
		return selectedParent;
	}

	private class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedParent = (String) parentSelector.getSelectedItem();
			dialog.setVisible(false);
		}

	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(false);
		}

	}
	
	private class CheckBoxListener implements ItemListener {

		private String feature;

		public CheckBoxListener(String feature) {
			this.feature = feature;
			
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// Update selected children
			if (e.getStateChange() == ItemEvent.SELECTED) {
				selectedChildren.add(feature);
			} else {
				selectedChildren.remove(feature);
			}
			
			// Update common parents
			parentSelector.removeAllItems();
			Set<String> possibleParents = environment.getPossibleParents(selectedChildren);
			for (String possibleParent : possibleParents) {
				parentSelector.addItem(possibleParent);	
			}
		}

		
	}

}
