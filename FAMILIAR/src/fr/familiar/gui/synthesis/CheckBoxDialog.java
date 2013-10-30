package fr.familiar.gui.synthesis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CheckBoxDialog {

	public static <T> Set<T> showCheckBoxDialog(String title, String message, Set<T> options, Set<T> initialOptions) {
		final Set<T> selection = new HashSet<T>();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel messageLabel = new JLabel(message);
		panel.add(messageLabel);
		
		// Define check boxes
		final HashMap<JCheckBox, T> optionMap = new HashMap<JCheckBox, T>();
		final List<JCheckBox> input = new ArrayList<JCheckBox>();

		for (T option : options) {
			JCheckBox checkBox = new JCheckBox(option.toString(), initialOptions.contains(option));
			optionMap.put(checkBox, option);
			input.add(checkBox);
			panel.add(checkBox);
		}
		
		// Define buttons
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");

		JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.NO_OPTION, null, new JButton[]{ok, cancel}, ok);
		final JDialog dialog = pane.createDialog(title);

		// Set the behaviour of the buttons
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkBox : input) {
					if (checkBox.isSelected()) {
						selection.add(optionMap.get(checkBox));
					}
				}
 				dialog.setVisible(false);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});

		// Display the dialog
		dialog.setVisible(true);

		return selection;
	}
	
}
