package fr.unice.polytech.modalis.familiar.gui.synthesis;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class FMExplorer extends FMViewer{
	
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	
	public FMExplorer() {
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		JTree tree = new JTree(model);
		tree.setRootVisible(false);
		this.add(new JScrollPane(tree));
	}

	@Override
	public void updateFM(FeatureModelVariable fmv) {
		root.removeAllChildren();
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		printFM(graph, graph.getTopVertex(), root); // TODO : avoid printing top vertex
		model.reload();
	}

	private void printFM(FeatureGraph<String> graph, FeatureNode<String> featureNode, DefaultMutableTreeNode treeNode) {
		DefaultMutableTreeNode feature = new DefaultMutableTreeNode(featureNode.getFeature());
		treeNode.add(feature);
		for (FeatureNode<String> child : graph.children(featureNode)) {
			printFM(graph, child, feature);
		}
	}

	

}
