package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class FMExplorer extends FMViewer{
	
	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private Set<String> expandedFeatures;
	
	public FMExplorer() {
		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		
		// Set layout
		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(tree));
		
		// Update list of expanded features
		expandedFeatures = new HashSet<String>();
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				String feature = event.getPath().getLastPathComponent().toString();
				expandedFeatures.add(feature);
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				String feature = event.getPath().getLastPathComponent().toString();
				expandedFeatures.remove(feature);
			}
		});
	}

	@Override
	public void updateFM(FeatureModelVariable fmv) {
		root.removeAllChildren();
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		Set<FeatureNode<String>> possibleRoots = graph.children(graph.getTopVertex());
		if (!possibleRoots.isEmpty()) {
			List<TreePath> pathsToExpand = new ArrayList<TreePath>(); 
			printFM(graph, possibleRoots.iterator().next(), root, pathsToExpand);
			model.reload();	
			
			// Keep features expanded
			for (TreePath path : pathsToExpand) {
				tree.expandPath(path);	
			}
		}
		
		
	}

	private void printFM(FeatureGraph<String> graph, FeatureNode<String> featureNode, DefaultMutableTreeNode treeNode, List<TreePath> pathsToExpand) {
		DefaultMutableTreeNode feature = new DefaultMutableTreeNode(featureNode.getFeature());
		treeNode.add(feature);
		
		// Keep features expanded
		if (expandedFeatures.contains(featureNode.getFeature())) {
			pathsToExpand.add(new TreePath(feature.getPath()));
		}
		
		for (FeatureNode<String> child : graph.children(featureNode)) {
			printFM(graph, child, feature, pathsToExpand);
		}
	}

	

}
