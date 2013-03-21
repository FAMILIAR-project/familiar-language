package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
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
	private Set<TreePath> selectedPaths;
	
	public ClusterViewer() {
		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		selectedPaths = new HashSet<TreePath>();
		
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
			if (cluster.size() == 1) {
				continue; // Avoid singletons
			}
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

		// Keep clusters expanded
		for (TreePath path : pathsToExpand) {
			tree.expandPath(path);	
		}
	}

	public void updateSelectedClusters(List<String> selectedFeatures, List<String> unselectedFeatures) {
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
		
	
}
