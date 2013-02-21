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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import prefuse.controls.ControlAdapter;
import prefuse.data.Node;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;

public class PopupMenuController extends ControlAdapter implements ActionListener {
	private VisualItem clickedItem;

	private JPopupMenu popupNodeMenu;  
	private JMenu submenuNAddChild;
	
	private JPopupMenu popupRootMenu; 
	private JMenu submenuRAddChild;
	
	private JPopupMenu popupGroupMenu; 
	private JPopupMenu popupGConstraintMenu; 
	private JPopupMenu popupSConstraintMenu; 
	
	private static final String feature = "feature";
	private static final String group = "group";
	private static final String constr = "constr";
	
	private static final String newChildFeature = "New Child Feature";
	private static final String newGroupedFeature = "New Grouped Feature";
	private static final String newMandFeature = "Mandatory";
	private static final String newOptFeature = "Optional";
	private static final String newOrGroup = "OR Group [1..*]";
	private static final String newXorGroup = "XOR Group [1..1]";
	private static final String renameFMName = "Rename FM Name";
	private static final String renameFeature = "Rename Feature";
	private static final String deleteFeature = "Delete Feature";
	private static final String deleteGroup = "Delete Group";
	
	private static final String newConstraint = "New Constraint";
	private static final String deleteAllConstraints = "Delete All Constraints";
	
	private static final String updateConstraint = "Update Constraint";
	private static final String deleteConstraint = "Delete Constraint";
	
	private void handleFeature(ActionEvent e) {
		FeatureModelVariable fmv = null;
		if (e.getActionCommand().endsWith(newMandFeature) ||
			e.getActionCommand().endsWith(newOptFeature)) {
			Node parentNode = getClickedNode();
			int groupType = e.getActionCommand().endsWith(newMandFeature) ? 
				FeatureEdge.MANDATORY : FeatureEdge.MUTEX;
			
			JTextField newF = new JTextField();
			
			final JComponent[] inputs = new JComponent[] {
                new JLabel("Feature Name:"), newF
			};
			String title = "Create New ";
			title = groupType == FeatureEdge.MANDATORY ? 
				title + newMandFeature : title + newOptFeature;
			title += " Feature"; 
			
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
				inputs, title, JOptionPane.PLAIN_MESSAGE);
			
			if (newF.getText().isEmpty()) return;

			fmv = Translator.INSTANCE.addChildFeature( 
					parentNode.getString(Converter.NAME), 
					RuleEnforcer.onlyDigitsAndLetters(newF.getText()), groupType);
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
	            	"Error adding a new child feature.",
	                "Add New Child Feature", JOptionPane.ERROR_MESSAGE);
			} 
		} else if ((e.getActionCommand().endsWith(newOrGroup)) ||
				(e.getActionCommand().endsWith(newXorGroup))) {
			int groupType = e.getActionCommand().endsWith(newOrGroup) ? 
					FeatureEdge.OR : FeatureEdge.XOR;
			
			JTextField f1 = new JTextField(); 
			JTextField f2 = new JTextField();
		
			final JComponent[] inputs = new JComponent[] {
                new JLabel("Feature Name (1):"), f1,
                new JLabel("Feature Name (2):"), f2
			};
			String title = "Create New ";
			title = groupType == FeatureEdge.OR ? 
				title + newOrGroup : title + newXorGroup;
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE,
				inputs, title, JOptionPane.PLAIN_MESSAGE);
			
			if (f1.getText().isEmpty() || f2.getText().isEmpty() || 
				f1.getText().equals(f2.getText())) return;
			
			fmv = Translator.INSTANCE.addNewGroup(
				getClickedNodeLabel(), 
				RuleEnforcer.onlyDigitsAndLetters(f1.getText()), 
				RuleEnforcer.onlyDigitsAndLetters(f2.getText()),
				groupType);
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
	            	"Error creating a new group.",
	            	title, JOptionPane.ERROR_MESSAGE);
			} 
		} else if (e.getActionCommand().endsWith(renameFeature)) {
			String origFeatureStr = getClickedNodeLabel();
			JTextField origF = new JTextField();
			origF.setText(origFeatureStr);
			origF.setEditable(false) ;  
			JTextField newF = new JTextField();
			newF.setText(origFeatureStr);
		
			final JComponent[] inputs = new JComponent[] {
                new JLabel("Original Feature Name:"), origF,
                new JLabel("New Feature Name:"), newF
			};
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE,
				inputs, renameFeature, JOptionPane.PLAIN_MESSAGE);
			
			if (newF.getText().isEmpty() || newF.getText().equals(origFeatureStr)) return;
			
			fmv = Translator.INSTANCE.renameFeature(origFeatureStr, 
					RuleEnforcer.onlyDigitsAndLetters(newF.getText()));
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
					"Error renaming feature. The change has not been committed.",
					renameFeature, JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getActionCommand().endsWith(deleteFeature)) {
			Node nodeToDelete = getClickedNode();
			fmv = Translator.INSTANCE.deleteFeature(nodeToDelete.getString(Converter.NAME));
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
		            "Error deleting feature " + nodeToDelete.getString(Converter.NAME) + 
		            ". Resulting FM is not valid, this change has not been committed.",
		            deleteFeature, JOptionPane.ERROR_MESSAGE);
			}
		} 
		Translator.INSTANCE.changedFmv(fmv);
	}
	
	private void handleGroup(ActionEvent e) {
		FeatureModelVariable fmv = null;
		if (e.getActionCommand().endsWith(newGroupedFeature)) {
			String newFeatureName = JOptionPane.showInputDialog(FamiliarEditor.INSTANCE, 
				    "Feature Name: ", 
				    newGroupedFeature, JOptionPane.QUESTION_MESSAGE);
				if (newFeatureName == null || newFeatureName.isEmpty()) return;
			Node groupNode = getClickedNode();
			fmv = Translator.INSTANCE.addGroupedFeature( 
				groupNode.getParent().getString(Converter.NAME), 
				RuleEnforcer.onlyDigitsAndLetters(newFeatureName), groupNode.getInt(Converter.GROUP));
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
	            	"Error adding a new grouped feature.",
	            	newGroupedFeature, JOptionPane.ERROR_MESSAGE);
			} 
		} else if (e.getActionCommand().endsWith(deleteGroup)) {
			fmv = Translator.INSTANCE.deleteGroup(getClickedNode());
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
		            "Error deleting group's children.",
		            deleteGroup, JOptionPane.ERROR_MESSAGE);
			}
		}
		Translator.INSTANCE.changedFmv(fmv);
	}
	
	private void handleConstraint(ActionEvent e) {
		FeatureModelVariable fmv = null;
		 if (e.getActionCommand().endsWith(newConstraint)) {
			JTextField newConstr = new JTextField();
			
			final JComponent[] inputs = new JComponent[] {
               new JLabel("New Constraint:"), newConstr
			};
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
				inputs, newConstraint, JOptionPane.PLAIN_MESSAGE);
			
			if (newConstr.getText().isEmpty()) return;
			
			fmv = Translator.INSTANCE.newConstraint(newConstr.getText());
			if (null == fmv) {
				 JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
			         "Error adding a new constraint.",
			         newConstraint, JOptionPane.ERROR_MESSAGE);
			} 
		 } else if (e.getActionCommand().endsWith(deleteAllConstraints)) {
			 fmv = Translator.INSTANCE.deleteAllConstraints( 
					getClickedNode());
		 } else if (e.getActionCommand().endsWith(updateConstraint)) {
			String origConstrStr = getClickedNodeLabel();
			JTextField origConstr = new JTextField();
			origConstr.setText(origConstrStr);
			origConstr.setEditable(false) ;  
			JTextField updatedConstr = new JTextField();
			updatedConstr.setText(origConstrStr);
		
			final JComponent[] inputs = new JComponent[] {
               new JLabel("Original Constraint:"), origConstr,
               new JLabel("New Constraint:"), updatedConstr
			};
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE,
				inputs, updateConstraint, JOptionPane.PLAIN_MESSAGE);
			
			if (updatedConstr.getText().isEmpty() || updatedConstr.getText().equals(origConstrStr)) return;
			
			fmv = Translator.INSTANCE.updateConstraint(origConstrStr, updatedConstr.getText());
			if (null == fmv) {
				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
					"Error updating constraint. The change has not been committed.",
					updateConstraint, JOptionPane.ERROR_MESSAGE);
			}
		 } else if (e.getActionCommand().endsWith(deleteConstraint)) {
			 fmv = Translator.INSTANCE.deleteConstraint(getClickedNodeLabel());
			 if (null == fmv) {
				 JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
					"Error deleting a constraint.",
					deleteConstraint, JOptionPane.ERROR_MESSAGE);
			 }
		 }
		 Translator.INSTANCE.changedFmv(fmv);
	}
	
	private void handleRenameFMName() {
		FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		if (null == fmv) {
			return;
		}
		String origFMName = fmv.getIdentifier();
		JTextField origF = new JTextField();
		origF.setText(origFMName);
		origF.setEditable(false) ;  
		JTextField newF = new JTextField();
		newF.setText(origFMName);
	
		final JComponent[] inputs = new JComponent[] {
            new JLabel("Original FM Name:"), origF,
            new JLabel("New FM Name:"), newF
		};
		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE,
			inputs, renameFMName, JOptionPane.PLAIN_MESSAGE);
		
		if (newF.getText().isEmpty() || newF.getText().equals(origFMName)) return;
		
		FamiliarConsole.INSTANCE.renameFMV(origFMName, RuleEnforcer.onlyDigitsAndLetters(newF.getText()));
	}
	
	private void handleConfiguration() {
		new NewConfiguration();
	}
	
	public PopupMenuController() {
		buildNonRootFeaturePopupMenu();
		buildRootFeaturePopupMenu();
		buildGroupPopupMenu();
		buildGroupConstraintsPopupMenu();
		buildSingleConstraintsPopupMenu();
	}
	
	private void buildNonRootFeaturePopupMenu() {
		popupNodeMenu = new JPopupMenu(); 
		submenuNAddChild = new JMenu(newChildFeature);
		JMenuItem newMand = new JMenuItem(newMandFeature);
		submenuNAddChild.add(newMand);
		JMenuItem newOpt = new JMenuItem(newOptFeature);
		submenuNAddChild.add(newOpt);
		JMenuItem newOr = new JMenuItem(newOrGroup);
		submenuNAddChild.add(newOr);
		JMenuItem newXor = new JMenuItem(newXorGroup);
		submenuNAddChild.add(newXor);
		popupNodeMenu.add(submenuNAddChild);
		popupNodeMenu.addSeparator();
		JMenuItem renameNode = new JMenuItem(renameFeature);
		popupNodeMenu.add(renameNode);
		JMenuItem deleteNode = new JMenuItem(deleteFeature);
		popupNodeMenu.add(deleteNode);
		
		newMand.setActionCommand(feature + newMandFeature);
		newOpt.setActionCommand(feature + newOptFeature);
		newOr.setActionCommand(feature + newOrGroup);
		newXor.setActionCommand(feature + newXorGroup);
		renameNode.setActionCommand(feature + renameFeature);
		deleteNode.setActionCommand(feature + deleteFeature);
		
		newMand.addActionListener(this);
		newOpt.addActionListener(this);
		newOr.addActionListener(this);
		newXor.addActionListener(this);
		renameNode.addActionListener(this);
		deleteNode.addActionListener(this);
	}
	
	private void buildRootFeaturePopupMenu() {
		popupRootMenu = new JPopupMenu(); 
		JMenuItem renFMName = new JMenuItem(renameFMName);
		popupRootMenu.add(renFMName);
		popupRootMenu.addSeparator();
		
		submenuRAddChild = new JMenu(newChildFeature);
		JMenuItem newMand = new JMenuItem(newMandFeature);
		submenuRAddChild.add(newMand);
		JMenuItem newOpt = new JMenuItem(newOptFeature);
		submenuRAddChild.add(newOpt);
		JMenuItem newOr = new JMenuItem(newOrGroup);
		submenuRAddChild.add(newOr);
		JMenuItem newXor = new JMenuItem(newXorGroup);
		submenuRAddChild.add(newXor);
		popupRootMenu.add(submenuRAddChild);
		
		JMenuItem renameNode = new JMenuItem(renameFeature);
		popupRootMenu.add(renameNode);
		
		newMand.setActionCommand(feature + newMandFeature);
		newOpt.setActionCommand(feature + newOptFeature);
		newOr.setActionCommand(feature + newOrGroup);
		newXor.setActionCommand(feature + newXorGroup);
		renFMName.setActionCommand(renameFMName);
		renameNode.setActionCommand(feature + renameFeature);
		
		newMand.addActionListener(this);
		newOpt.addActionListener(this);
		newOr.addActionListener(this);
		newXor.addActionListener(this);
		renFMName.addActionListener(this);
		renameNode.addActionListener(this);
		
		popupRootMenu.addSeparator();
		JMenuItem newCItem = new JMenuItem(newConstraint);
		popupRootMenu.add(newCItem);
		newCItem.setActionCommand(constr + newConstraint);
		newCItem.addActionListener(this);
		
		popupRootMenu.addSeparator();
		JMenuItem newConfItem = new JMenuItem(NewConfiguration.title);
		popupRootMenu.add(newConfItem);
		newConfItem.setActionCommand(NewConfiguration.title);
		newConfItem.addActionListener(this);
	}
	
	private void buildGroupPopupMenu() {
		popupGroupMenu = new JPopupMenu(); 
		JMenuItem newGFeature = new JMenuItem(newGroupedFeature);
		popupGroupMenu.add(newGFeature);
		JMenuItem deleteGroupItem = new JMenuItem(deleteGroup);
		popupGroupMenu.add(deleteGroupItem);
		
		newGFeature.setActionCommand(group + newGroupedFeature);
		deleteGroupItem.setActionCommand(group + deleteGroup);
		
		newGFeature.addActionListener(this);
		deleteGroupItem.addActionListener(this);
	}
	
	private void buildGroupConstraintsPopupMenu() {
		popupGConstraintMenu = new JPopupMenu(); 
		JMenuItem newConstrItem = new JMenuItem(newConstraint);
		popupGConstraintMenu.add(newConstrItem);
		JMenuItem deleteAllConstrItem = new JMenuItem(deleteAllConstraints);
		popupGConstraintMenu.add(deleteAllConstrItem);
		
		newConstrItem.setActionCommand(constr + newConstraint);
		deleteAllConstrItem.setActionCommand(constr + deleteAllConstraints);
		
		newConstrItem.addActionListener(this);
		deleteAllConstrItem.addActionListener(this);
	}
	
	private void buildSingleConstraintsPopupMenu() {
		popupSConstraintMenu = new JPopupMenu(); 
		JMenuItem updateCItem = new JMenuItem(updateConstraint);
		popupSConstraintMenu.add(updateCItem);
		JMenuItem deleteCItem = new JMenuItem(deleteConstraint);
		popupSConstraintMenu.add(deleteCItem);
		
		updateCItem.setActionCommand(constr + updateConstraint);
		deleteCItem.setActionCommand(constr + deleteConstraint);
		
		updateCItem.addActionListener(this);
		deleteCItem.addActionListener(this);
	}
	
	@Override
	public void itemClicked(VisualItem item, MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			clickedItem = item;
			if (item instanceof NodeItem) {
				if (Converter.NOTAVAILABLE == item.getInt(Converter.GROUP)) {
					Node node = (Node)item.getSourceTuple();
					if (null == node.getParent()) {
						// Root feature
						popupRootMenu.show(e.getComponent(), e.getX(), e.getY());
					} else if (Converter.CONSTRAINT != node.getParent().getInt(Converter.GROUP) &&
							Converter.CONSTRAINT != item.getInt(Converter.SOLITARY)) {
						// Solitary features
						popupNodeMenu.show(e.getComponent(), e.getX(), e.getY());
					} else if (Converter.CONSTRAINT == item.getInt(Converter.SOLITARY)) {
						// Single constraints
						popupSConstraintMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				} else if (Converter.CONSTRAINT != item.getInt(Converter.GROUP)) {
					// Group features
					popupGroupMenu.show(e.getComponent(), e.getX(), e.getY());
				} else if (Converter.CONSTRAINT == item.getInt(Converter.GROUP)) {
					// Group constraints
					popupGConstraintMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		} 
	}
	
	private Node getClickedNode() {
		return ((Node)clickedItem.getSourceTuple());
	}
	
	private String getClickedNodeLabel() {
		return getClickedNode().getString(Converter.NAME);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(feature)) {
			handleFeature(e);
		} else if (e.getActionCommand().startsWith(group)) {
			handleGroup(e);
		} else if (e.getActionCommand().startsWith(constr)) {
			handleConstraint(e);
		} else if (e.getActionCommand().equals(renameFMName)) {
			handleRenameFMName();
		} else if (e.getActionCommand().equals(NewConfiguration.title)) {
			handleConfiguration();
		}
		clickedItem = null;
	}
	
} // end of class PopupMenuController