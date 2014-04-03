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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class CliqueViewer extends JPanel {
	
	private HashMap<DefaultMutableTreeNode, Set<String>> cliqueMap;
	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private Set<Object> expandedCliques;
	private Set<TreePath> selectedPaths;
	private FMSynthesisEnvironment environment;
	
	private JPopupMenu featureMenu;
	private Set<String> lastSelectedClique;
	private String lastSelectedFeature;
	
	
	
	public CliqueViewer(FMSynthesisEnvironment environment) {
		this.environment = environment;
		cliqueMap = new HashMap<DefaultMutableTreeNode, Set<String>>();
		
		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		selectedPaths = new HashSet<TreePath>();
		
		// Set layout
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Cliques"), BorderLayout.NORTH);
		this.add(new JScrollPane(tree), BorderLayout.CENTER);
		
		// Update list of expanded features
		expandedCliques = new HashSet<Object>();
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				Object cluster = event.getPath().getLastPathComponent();
				expandedCliques.add(cluster);
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				Object cluster = event.getPath().getLastPathComponent();
				expandedCliques.remove(cluster);
			}
		});
		
		// Create popup menu
		tree.addMouseListener(new CliqueTreeMouseListener());
		
		featureMenu = new JPopupMenu();
		JMenuItem selectFeatureAsParentItem = new JMenuItem("Select as parent of...");
		selectFeatureAsParentItem.addActionListener(new SelectFeatureAsParentActionListener());
		featureMenu.add(selectFeatureAsParentItem);
	}

	public void updateCliques(List<Set<String>> cliques) {
		cliqueMap = new HashMap<DefaultMutableTreeNode, Set<String>>();
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		root.removeAllChildren();

		for (Set<String> clique : cliques) {
			DefaultMutableTreeNode cliqueNode = new DefaultMutableTreeNode("Clique");
			root.add(cliqueNode);
			cliqueMap.put(cliqueNode, clique);
			for (String feature : clique) {
				cliqueNode.add(new DefaultMutableTreeNode(feature));
			}
			
			if (expandedCliques.contains(clique)) {
				pathsToExpand.add(new TreePath(cliqueNode.getPath()));
			}
		}
		
		model.reload();

		// Keep cliques expanded
		for (TreePath path : pathsToExpand) {
			tree.expandPath(path);	
		}
	}

	public void updateSelectedCliques(List<String> selectedFeatures, List<String> unselectedFeatures) {
		Enumeration<DefaultMutableTreeNode> it = root.depthFirstEnumeration();
		while (it.hasMoreElements()) {
			DefaultMutableTreeNode node = it.nextElement();
			if (selectedFeatures.contains(node.toString())) {
				selectedPaths.add(new TreePath(node.getPath()));
			} else if (unselectedFeatures.contains(node.toString())) {
				selectedPaths.remove(new TreePath(node.getPath()));
			}
		}
		
		tree.setSelectionPaths(selectedPaths.toArray(new TreePath[selectedPaths.size()]));
		
	}
	
	private class SelectFeatureAsParentActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			environment.selectFeatureAsParentOf(lastSelectedFeature, lastSelectedClique);
		}
		
	}
	
	private class CliqueTreeMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				
				int row = tree.getRowForLocation(e.getX(), e.getY());
				TreePath path = tree.getPathForRow(row);
				tree.setSelectionRow(row);
				if (path != null) {
					if (path.getPathCount() == 2) {
//						lastSelectedClique = cliqueMap.get(path.getPathComponent(1));
//						cliqueMenu.show(tree, e.getX(), e.getY());
					} else if (path.getPathCount() == 3) {
						lastSelectedClique = cliqueMap.get(path.getPathComponent(1));
						lastSelectedFeature = path.getPathComponent(2).toString();
						featureMenu.show(tree, e.getX(), e.getY());		
					}
				}

			}
		}
	}
}
