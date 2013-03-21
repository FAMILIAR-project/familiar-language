package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

public class ClusterViewer extends JPanel {

	
	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private Set<Object> expandedClusters;
	
	public ClusterViewer() {
		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		
		// Set layout
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Clusters"), BorderLayout.NORTH);
		this.add(new JScrollPane(tree), BorderLayout.CENTER);
		
		// Update list of expanded features
		expandedClusters = new HashSet<Object>();
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				Object cluster = event.getPath().getLastPathComponent();
				expandedClusters.add(cluster);
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				Object cluster = event.getPath().getLastPathComponent();
				expandedClusters.remove(cluster);
			}
		});
	}

	public void updateClusters(Set<Set<String>> clusters) {
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		root.removeAllChildren();

		for (Set<String> cluster : clusters) {
			DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode("Cluster");
			root.add(clusterNode);
			for (String feature : cluster) {
				clusterNode.add(new DefaultMutableTreeNode(feature));
			}
			
			if (expandedClusters.contains(cluster)) {
				pathsToExpand.add(new TreePath(clusterNode.getPath()));
			}
		}
		
		model.reload();

		// Keep features expanded
		for (TreePath path : pathsToExpand) {
			tree.expandPath(path);	
		}
	}
		
	
}
