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
