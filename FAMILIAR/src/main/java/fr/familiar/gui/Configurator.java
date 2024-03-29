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
package fr.familiar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import org.xtext.example.mydsl.fml.OpSelection;

import prefuse.data.Node;
import prefuse.data.tuple.TableNode;
import prefuse.util.ui.JPrefuseTree;
import fr.familiar.variable.ConfigurationVariable;

public class Configurator extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPrefuseTree tree;
	
    public void expandAll() {
        int row = 0;
        while (row < tree.getRowCount()) {
        	tree.expandRow(row++);
        }
    }
  
  public Configurator(ConfigurationVariable cv) {
	  	tree = new JPrefuseTree(
			  Converter.INSTANCE.fmv2PrefuseTree(cv), 
					  Converter.NAME);
  
	    tree.setName(cv.getIdentifier());
	    tree.setCellRenderer(new CheckRenderer());
	    tree.addMouseListener(new NodeSelectionListener(tree));
    
	    JScrollPane sp = new JScrollPane(tree);
	    JPanel panel = new JPanel(new BorderLayout());
	    getContentPane().add(sp,    BorderLayout.CENTER);
	    getContentPane().add(panel, BorderLayout.EAST);
	    expandAll();
	    
	       
  }



class NodeSelectionListener extends MouseAdapter {
    JPrefuseTree  tree;
    
    NodeSelectionListener(JPrefuseTree tree) {
    	this.tree = tree;
    }
    
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	int row = tree.getRowForLocation(x, y);
    	TreePath  path = tree.getPathForRow(row);
    	if (path != null) {
    		TableNode node = (TableNode)path.getLastPathComponent();
    		String tName = tree.getName() ; 
    		String nodeName = node.getString(Converter.NAME) ; 
    		if (ConfigsSelected.INSTANCE.isSelected(tName, nodeName))
    			ConfigsSelected.INSTANCE.applySelection(
    					tName, nodeName, OpSelection.DESELECT);
    		else if (ConfigsSelected.INSTANCE.isDeselected(tName, nodeName))
    			ConfigsSelected.INSTANCE.applySelection(
    					tName, nodeName, OpSelection.UNSELECT);
    		else
    			ConfigsSelected.INSTANCE.applySelection(
    					tName, nodeName, OpSelection.SELECT);    		
    		//tree.updateUI() ; 	
    		// Reopen the configuration variable tab
    		//Variable cv = FamiliarConsole.INSTANCE.getVariableByName(tName);
    		//Tab2EnvVar.INSTANCE.createNewConfigurationTab((ConfigurationVariable)cv, false);
    	}
    }
}

class CheckRenderer extends JPanel implements TreeCellRenderer {
	private static final long serialVersionUID = 1L;

	protected JCheckBox check;
	protected TreeLabel label;

	public CheckRenderer() {
		setLayout(null);
		add(check = new JCheckBox());
		add(label = new TreeLabel());
		check.setBackground(UIManager.getColor("Tree.textBackground"));
		label.setForeground(UIManager.getColor("Tree.textForeground"));
	}

	public Component getTreeCellRendererComponent(JTree tree, 
			Object value,
			boolean isSelected, 
			boolean expanded, 
			boolean leaf, 
			int row,
			boolean hasFocus) {
		String name = ((Node)value).getString(Converter.NAME);
		
		boolean selected = ConfigsSelected.INSTANCE.isSelected(tree.getName(), name) ; 
		check.setSelected(selected);
			  
	    label.setFont(tree.getFont());
	    
	    String labelContent = name + "";
	    if (selected)
	    	labelContent += " (selected)" ; 
	    label.setText(labelContent);
	    label.setFocus(hasFocus);

	    if (leaf) {
	    	label.setIcon(UIManager.getIcon("Tree.leafIcon"));
	    } else if (expanded) {
	    	label.setIcon(UIManager.getIcon("Tree.openIcon")); 
	    } 
	
		if (name.startsWith("XOR")) {
			 label.setIcon(Menu.createImageIcon("images/xor.gif"));
			 label.setText("XOR Group");
			 label.setFocus(false);
			 setEnabled(false);
		} else if (name.startsWith("OR")) {
		 	label.setIcon(Menu.createImageIcon("images/or.gif"));
		 	label.setText("OR Group");
		 	label.setFocus(false);
		 	setEnabled(false);
		}
		return this;
	}

  public Dimension getPreferredSize() {
	  Dimension d_check = check.getPreferredSize();
	  Dimension d_label = label.getPreferredSize();
	  return new Dimension(d_check.width + d_label.width,
			  (d_check.height < d_label.height ? d_label.height : d_check.height));
  }

  public void doLayout() {
	  Dimension d_check = check.getPreferredSize();
	  Dimension d_label = label.getPreferredSize();
	  int y_check = 0;
	  int y_label = 0;
	  if (d_check.height < d_label.height) {
		  y_check = (d_label.height-d_check.height) / 2;
	  } else {
		  y_label = (d_check.height-d_label.height) / 2;
	  }
	  check.setLocation(0, y_check);
	  check.setBounds(0, y_check, d_check.width, d_check.height-2);
	  label.setLocation(d_check.width, y_label);
	  label.setBounds(d_check.width, y_label, d_label.width, d_label.height);
  }

  public void setBackground(Color color) {
	  if (color instanceof ColorUIResource) {
		  color = null;
	  }
	  super.setBackground(color);
  }
}

} // end of class Configurator
