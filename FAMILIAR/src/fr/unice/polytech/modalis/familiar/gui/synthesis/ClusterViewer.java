package fr.unice.polytech.modalis.familiar.gui.synthesis;

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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ClusterViewer extends JPanel {

	private FMSynthesisEnvironment environment;
	
	private HashMap<DefaultMutableTreeNode, Set<String>> clusterMap;
	private JTree similarityTree, supportTree;
	private DefaultTreeModel similarityModel, supportModel;
	private DefaultMutableTreeNode similarityRoot, supportRoot;
	private Set<Object> expandedClusters;
	private Set<TreePath> selectedPaths;
	
	private JPopupMenu clusterMenu;
	private JPopupMenu featureMenu;
	private Set<String> lastSelectedCluster;
	private String lastSelectedFeature;
	

	public ClusterViewer(FMSynthesisEnvironment environment) {
		this.environment = environment;
		clusterMap = new HashMap<DefaultMutableTreeNode, Set<String>>();
		
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
		//		JScrollPane left =  new JScrollPane(similarityTree);
		//		JScrollPane right = new JScrollPane(supportTree);
		//		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right), BorderLayout.CENTER);
		this.add(new JScrollPane(similarityTree), BorderLayout.CENTER);

		// Update list of expanded features
		expandedClusters = new HashSet<Object>();
		similarityTree.addTreeExpansionListener(new ClusterExpansionListener());

		// Update selected clusters
		similarityTree.addTreeSelectionListener(new ClusterSelectionListener());

		// Create popp menus
		similarityTree.addMouseListener(new ClusterTreeMouseListener());
		
		clusterMenu = new JPopupMenu();
		JMenuItem selectClusterParentItem = new JMenuItem("Select parent...");
		selectClusterParentItem.addActionListener(new SelectClusterParentActionListener());
		clusterMenu.add(selectClusterParentItem);

		featureMenu = new JPopupMenu();
		JMenuItem selectFeatureAsParentItem = new JMenuItem("Select as parent of...");
		selectFeatureAsParentItem.addActionListener(new SelectFeatureAsParentActionListener());
		featureMenu.add(selectFeatureAsParentItem);
	}

	public void updateSimilarityClusters(Set<Set<String>> clusters) {
		clusterMap = new HashMap<DefaultMutableTreeNode, Set<String>>();
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		similarityRoot.removeAllChildren();

		for (Set<String> cluster : clusters) {
			if (cluster.size() == 1) {
				continue; // Avoid singletons
			}
			DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode("Cluster");
			similarityRoot.add(clusterNode);
			clusterMap.put(clusterNode, cluster);
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


	private class ClusterExpansionListener implements TreeExpansionListener {
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
	}

	private class SelectClusterParentActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			environment.selectClusterParent(lastSelectedCluster);
		}

	}
	
	private class SelectFeatureAsParentActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			environment.selectFeatureAsParentOf(lastSelectedFeature, lastSelectedCluster);
		}
		
	}
	
	private class ClusterTreeMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				
				int row = similarityTree.getRowForLocation(e.getX(), e.getY());
				TreePath path = similarityTree.getPathForRow(row);
				similarityTree.setSelectionRow(row);
				if (path != null) {
					if (path.getPathCount() == 2) {
						lastSelectedCluster = clusterMap.get(path.getPathComponent(1));
						clusterMenu.show(similarityTree, e.getX(), e.getY());
					} else if (path.getPathCount() == 3) {
						lastSelectedCluster = clusterMap.get(path.getPathComponent(1));
						lastSelectedFeature = path.getPathComponent(2).toString();
						featureMenu.show(similarityTree, e.getX(), e.getY());		
					}
				}

			}
		}
	}
	
	private class ClusterSelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			List<Set<String>> selectedClusters = new ArrayList<Set<String>>();
			List<Set<String>> unselectedClusters = new ArrayList<Set<String>>();
			for (TreePath path : e.getPaths()) {
				if (e.isAddedPath(path)) {
					selectedClusters.add(findCluster(path));	
				} else {
					unselectedClusters.add(findCluster(path));
				}
			}
			environment.updateSelectedClusters(selectedClusters, unselectedClusters);
		}

		/**
		 * Find the cluster corresponding to the path
		 * @param path
		 * @return the corresponding cluster or an empty cluster if it is not found
		 */
		private Set<String> findCluster(TreePath path) {
			for (Object o : path.getPath()) {
				if (clusterMap.containsKey(o)){
					return clusterMap.get(o);
				}
			}
			return new HashSet<String>();
		}
		
		
		
	}
}
