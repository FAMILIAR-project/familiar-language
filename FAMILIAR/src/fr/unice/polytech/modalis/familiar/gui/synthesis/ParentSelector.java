package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ParentSelector extends JPanel {

	private FMSynthesisEnvironment environment;

	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;

	private JPopupMenu parentPopupMenu;
	private JPopupMenu featurePopupMenu;
	private String lastSelectedParent;
	private String lastSelectedFeature;

	private Set<String> expandedFeatures;

	private JTextField search;

	private List<KeyValue<String, List<String>>> parentsList;

	private boolean hideDefinedFeatures;
	private JButton hideDefinedFeaturesButton;

	public ParentSelector(FMSynthesisEnvironment environment) {
		this.environment = environment;
		hideDefinedFeatures = true;

		// Create explorer view
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		search = new JTextField();
		search.getDocument().addDocumentListener(new SearchFieldListener());

		// Create tool bar
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		hideDefinedFeaturesButton = new JButton("Hide");
		hideDefinedFeaturesButton.setToolTipText("Hide features with a defined parent.");
		hideDefinedFeaturesButton.addActionListener(new HideDefinedFeaturesListener());
		hideDefinedFeaturesButton.setSelected(hideDefinedFeatures);
		toolBar.add(hideDefinedFeaturesButton);
		
		// Set layout and add components
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Parent selector"), BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(toolBar, BorderLayout.NORTH);
		centerPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(search, BorderLayout.SOUTH);

		// Create parent popup menu
		parentPopupMenu = new JPopupMenu();
		
		JMenuItem selectParentItem = new JMenuItem("Select this parent");
		selectParentItem.addActionListener(new SelectParentActionListener());
		parentPopupMenu.add(selectParentItem);

		JMenuItem ignoreParentItem = new JMenuItem("Ignore this parent");
		ignoreParentItem.addActionListener(new IgnoreParentActionListener());
		parentPopupMenu.add(ignoreParentItem);

		// Create feature popup menu
		featurePopupMenu = new JPopupMenu();
		JMenuItem selectAsRootItem = new JMenuItem("Select as root");
		selectAsRootItem.addActionListener(new SelectAsRootActionListener());
		featurePopupMenu.add(selectAsRootItem);

		// Inform selected features
		tree.addTreeSelectionListener(new SelectedFeaturesListener(environment));

		// Show popup menu		
		tree.addMouseListener(new PopupMenuAdapter());

		// Update list of expanded features
		expandedFeatures = new HashSet<String>();
		tree.addTreeExpansionListener(new FeatureExpansionListener());
	}

	public void updateParents(List<KeyValue<String, List<String>>> list) {
		parentsList = list;
		updateParents();
	}

	private void updateParents() {
		List<TreePath> pathsToExpand = new ArrayList<TreePath>();
		root.removeAllChildren();

		for (KeyValue<String, List<String>> entry : parentsList) {
			String feature = entry.getKey();
			List<String> parents = entry.getValue();
			
			boolean containsSearchedWord = feature.toLowerCase().contains(search.getText().toLowerCase());
			boolean hidden = hideDefinedFeatures && parents.size() < 2;
			
			if (containsSearchedWord && !hidden) {
				DefaultMutableTreeNode featureNode = new DefaultMutableTreeNode(feature);
				root.add(featureNode);
				for (String parent : parents) {
					featureNode.add(new DefaultMutableTreeNode(parent));
				}

				if (expandedFeatures.contains(feature)) {
					pathsToExpand.add(new TreePath(featureNode.getPath()));
				}
			}
		}

		model.reload();

		// Keep features expanded
		for (TreePath path : pathsToExpand) {
			tree.expandPath(path);	
		}
	}

	private class SelectParentActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			environment.selectParent(lastSelectedFeature, lastSelectedParent);
		}
	}

	private class IgnoreParentActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			environment.ignoreParent(lastSelectedFeature, lastSelectedParent);
		}

	}

	private class SelectAsRootActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			environment.setRoot(lastSelectedFeature);
		}

	}

	private class SearchFieldListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			updateParents();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateParents();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {

		}

	}
	
	private class PopupMenuAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {

				int row = tree.getRowForLocation(e.getX(), e.getY());
				TreePath path = tree.getPathForRow(row);
				tree.setSelectionRow(row);
				if (path != null) {
					if (path.getPathCount() == 2) {
						lastSelectedFeature = path.getPathComponent(1).toString();
						featurePopupMenu.show(tree, e.getX(), e.getY());
					} else if (path.getPathCount() == 3) {
						lastSelectedFeature = path.getPathComponent(1).toString();
						lastSelectedParent = path.getPathComponent(2).toString();
						parentPopupMenu.show(tree, e.getX(), e.getY());		
					}
				}

			}
		}
	}
	
	private class FeatureExpansionListener implements TreeExpansionListener {
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
	}
	
	private class HideDefinedFeaturesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			hideDefinedFeatures = !hideDefinedFeatures;
			hideDefinedFeaturesButton.setSelected(hideDefinedFeatures);
			updateParents();
		}
	}
}
