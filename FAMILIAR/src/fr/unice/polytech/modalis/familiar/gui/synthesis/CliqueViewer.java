package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class CliqueViewer extends JPanel {
	
	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private Set<Object> expandedCliques;
	
	public CliqueViewer() {
		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		
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
	}

	public void updateCliques(List<Set<String>> cliques) {
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		root.removeAllChildren();

		for (Set<String> clique : cliques) {
			DefaultMutableTreeNode cliqueNode = new DefaultMutableTreeNode("Clique");
			root.add(cliqueNode);
			for (String feature : clique) {
				cliqueNode.add(new DefaultMutableTreeNode(feature));
			}
			
			if (expandedCliques.contains(clique)) {
				pathsToExpand.add(new TreePath(cliqueNode.getPath()));
			}
		}
		
		model.reload();

		// Keep features expanded
		for (TreePath path : pathsToExpand) {
			tree.expandPath(path);	
		}
	}
}
