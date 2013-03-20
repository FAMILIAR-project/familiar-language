/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)
 * project (http://familiar-project.github.com/).
 *
 * Copyright (C) 2011 - 2013
 *     University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *     Colorado State University, Computer Science Department
 *     
 * Author: Aleksandar Jaksic
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;


public class CompareFMsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String allModesStr = "ALL MODES";

	// Java 6 will not compile with generics on JComboBox class
	// For backwards compatibility remove <String> part from JComboBox<String>
	private JComboBox jc1 = null;
	private JComboBox jc2 = null;
	private String selection = "";

public CompareFMsFrame(String[] fms, String compareTitle) {
	super(compareTitle);
	setResizable(false);
    ModePanel checkbox = new ModePanel();
 
    JButton button = new JButton("Compare");
    button.addActionListener(new ButtonActionListener(this));
    
    jc1 = new JComboBox(fms);
    jc2 = new JComboBox(fms);
    
    JPanel fmsPanel = new JPanel();
    fmsPanel.setBorder(new TitledBorder("Select Feature Models to Compare"));
    fmsPanel.setLayout(new GridLayout(2, 2));
    fmsPanel.add(new JLabel("Feature Model 1:"));
    fmsPanel.add(jc1);
    fmsPanel.add(new JLabel("Feature Model 2:"));
    fmsPanel.add(jc2);
    
    JPanel specChoice = new JPanel(new BorderLayout());
    specChoice.add(checkbox, BorderLayout.CENTER);
    specChoice.add(button, BorderLayout.SOUTH);
    
    getContentPane().add(fmsPanel,    BorderLayout.CENTER);
    getContentPane().add(specChoice, BorderLayout.SOUTH);
    
    Dimension dim = this.getToolkit().getScreenSize();  
    int screenWidth = dim.width;  
    int screenHeight = dim.height;  
    int frameWidth = screenWidth/3;  
    int frameHeight = screenHeight/3;  
    setSize(230, 280); 
    setLocation((screenWidth-frameWidth)/2,(screenHeight-frameHeight)/2);  
    setVisible(true);
  }


  class ModePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JRadioButton b_spec, b_gener, b_ref, b_arbit, b_all;

    ModePanel() {
      setLayout(new GridLayout(5, 1));
      setBorder(new TitledBorder("Comparison Mode"));
      ButtonGroup group = new ButtonGroup();
      add(b_spec = new JRadioButton(Comparison.SPECIALIZATION.toString()));
      add(b_gener = new JRadioButton(Comparison.GENERALIZATION.toString()));
      add(b_ref = new JRadioButton(Comparison.REFACTORING.toString()));
      add(b_arbit = new JRadioButton(Comparison.ARBITRARY.toString()));
      add(b_all = new JRadioButton(allModesStr));
      group.add(b_spec);
      group.add(b_gener);
      group.add(b_ref);
      group.add(b_arbit);
      group.add(b_all);
      b_spec.addActionListener(this);
      b_gener.addActionListener(this);
      b_ref.addActionListener(this);
      b_arbit.addActionListener(this);
      b_all.addActionListener(this);
      b_all.setSelected(true);
      selection = allModesStr;
    }

    public void actionPerformed(ActionEvent e) {
    	if (b_spec == e.getSource()) {
    		selection = Comparison.SPECIALIZATION.toString();
    	} else if (b_gener == e.getSource()) {
    		selection = Comparison.GENERALIZATION.toString();
    	} else if (b_ref == e.getSource()) {
    		selection = Comparison.REFACTORING.toString();
    	} else if (b_arbit == e.getSource()) {
    		selection = Comparison.ARBITRARY.toString();
    	} else if (b_all == e.getSource()) {
    		selection = allModesStr;
    	}
    }
 }

  class ButtonActionListener implements ActionListener {
	  JFrame parent; 
	
	  ButtonActionListener(final JFrame parent) {
		  this.parent = parent;
	  }

	  public void actionPerformed(ActionEvent ev) {
		  String fmName1 = (String)jc1.getSelectedItem();
		  String fmName2 = (String)jc2.getSelectedItem();
		 
		  Variable v1 = FamiliarConsole.INSTANCE.getVariableByName(fmName1);
		  if (v1 instanceof FeatureModelVariable) {
			  Variable v2 = FamiliarConsole.INSTANCE.getVariableByName(fmName2);
			  if (v2 instanceof FeatureModelVariable) {
				  Comparison cRes = ((FeatureModelVariable)v1).compareBDD(
						  ((FeatureModelVariable)v2), FMLCommandInterpreter.getBuilder());
				  boolean isSpec = false, isGen = false, isRef = false, isArb = false;
				  String sMsg="", gMsg="", rMsg="", aMsg="";
				  
				  if (selection.equals(allModesStr) || 
						  selection.equals(Comparison.SPECIALIZATION.toString())) {
					  if (Comparison.SPECIALIZATION == cRes) {
						  isSpec = true;
					  }
					  sMsg = "FM " + fmName1 + (isSpec ? " is a " : " is NOT a ") 
							  + Comparison.SPECIALIZATION.toString() + " of FM " + fmName2;
				  }
				  
				  if (selection.equals(allModesStr) || 
						  selection.equals(Comparison.GENERALIZATION.toString())) {
					  if (Comparison.GENERALIZATION == cRes) {
						  isGen = true;
					  }
					  gMsg = "FM " + fmName1 + (isGen ? " is a " : " is NOT a ") 
							  + Comparison.GENERALIZATION.toString() + " of FM " + fmName2;
				  }
				  
				  if (selection.equals(allModesStr) || 
						  selection.equals(Comparison.REFACTORING.toString())) {
					  if (Comparison.REFACTORING == cRes) {
						  isRef = true;
					  }
					  rMsg = "FM " + fmName1 + (isRef ? " is a " : " is NOT a ") 
							  + Comparison.REFACTORING.toString() + " of FM " + fmName2;
				  }
				  
				  if (selection.equals(allModesStr) || 
						  selection.equals(Comparison.ARBITRARY.toString())) {
					  if (Comparison.ARBITRARY == cRes) {
						  isArb = true;
					  }
					  aMsg = "FM " + fmName1 + (isArb ? " is an " : " is NOT an ") 
							  + Comparison.ARBITRARY.toString() + " of FM " + fmName2;
				  }
				
				  if (selection.equals(allModesStr)) {
					  FamiliarConsole.INSTANCE.setMessage(sMsg + "\n" + gMsg + "\n" + rMsg + "\n" + aMsg);
				  } else if (selection.equals(Comparison.SPECIALIZATION.toString())) {
					  FamiliarConsole.INSTANCE.setMessage(sMsg);
				  } else if (selection.equals(Comparison.GENERALIZATION.toString())) {
					  FamiliarConsole.INSTANCE.setMessage(gMsg);
				  } else if (selection.equals(Comparison.REFACTORING.toString())) {
					  FamiliarConsole.INSTANCE.setMessage(rMsg);
				  } else if (selection.equals(Comparison.ARBITRARY.toString())) {
					  FamiliarConsole.INSTANCE.setMessage(aMsg);
				  }
				  parent.dispose();
			  }
		  }
	  }
}

} // End of class CompareFMsFrame