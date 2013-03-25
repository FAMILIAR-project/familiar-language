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
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ClusterViewer extends JPanel {


	private JTree similarityTree, supportTree;
	private DefaultTreeModel similarityModel, supportModel;
	private DefaultMutableTreeNode similarityRoot, supportRoot;
	private Set<Object> expandedClusters;
	private Set<TreePath> selectedPaths;

	public ClusterViewer() {
		// Create explorer view
		similarityRoot = new DefaultMutableTreeNode();
		similarityModel = new DefaultTreeModel(similarityRoot);
		similarityTree = new JTree(similarityModel);
		similarityTree.setRootVisible(false);

		supportRoot = new DefaultMutableTreeNode();
		supportModel = new DefaultTreeModel(supportRoot);
		supportTree = new JTree(supportModel);
		supportTree.setRootVisible(false);

		selectedPaths = new HashSet<TreePath>();

		// Set layout
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Clusters"), BorderLayout.NORTH);
		JScrollPane left =  new JScrollPane(similarityTree);
		JScrollPane right = new JScrollPane(supportTree);
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right), BorderLayout.CENTER);

		// Update list of expanded features
		expandedClusters = new HashSet<Object>();
		similarityTree.addTreeExpansionListener(new TreeExpansionListener() {

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

	public void updateSimilarityClusters(Set<Set<String>> clusters) {
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		similarityRoot.removeAllChildren();

		for (Set<String> cluster : clusters) {
			if (cluster.size() == 1) {
				continue; // Avoid singletons
			}
			DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode("Cluster");
			similarityRoot.add(clusterNode);
			for (String feature : cluster) {
				clusterNode.add(new DefaultMutableTreeNode(feature));
			}

			if (expandedClusters.contains(cluster)) {
				pathsToExpand.add(new TreePath(clusterNode.getPath()));
			}
		}

		similarityModel.reload();

		// Keep clusters expanded
		for (TreePath path : pathsToExpand) {
			similarityTree.expandPath(path);	
		}
	}

	public void updateSupportClusters(List<Set<Set<String>>> clusters) {
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		supportRoot.removeAllChildren();

		int levelNumber = 0;
		for (Set<Set<String>> clusterLevel : clusters) {
			DefaultMutableTreeNode level = new DefaultMutableTreeNode(levelNumber);
			supportRoot.add(level);
			
			for (Set<String> cluster : clusterLevel) {
				if (cluster.size() == 1) {
					continue; // Avoid singletons
				}
				DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode("Cluster");
				level.add(clusterNode);
				for (String feature : cluster) {
					clusterNode.add(new DefaultMutableTreeNode(feature));
				}

				if (expandedClusters.contains(cluster)) {
					pathsToExpand.add(new TreePath(clusterNode.getPath()));
				}
			}
			
			levelNumber++;
		}
		supportModel.reload();

		// Keep clusters expanded
		for (TreePath path : pathsToExpand) {
			supportTree.expandPath(path);	
		}
	}


	public void updateSelectedClusters(List<String> selectedFeatures, List<String> unselectedFeatures) {
		updateSelectedClusters(selectedFeatures, unselectedFeatures, similarityTree, similarityRoot);
		updateSelectedClusters(selectedFeatures, unselectedFeatures, supportTree, supportRoot);
	}

	private void updateSelectedClusters(List<String> selectedFeatures, List<String> unselectedFeatures, JTree tree, DefaultMutableTreeNode root) {
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
