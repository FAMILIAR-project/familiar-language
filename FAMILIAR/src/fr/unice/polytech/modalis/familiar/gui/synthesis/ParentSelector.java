package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class ParentSelector extends JPanel {
	
	private FMSynthesisEnvironment environment;
	private JTree tree;
	private DefaultMutableTreeNode parentSelector;
	private JPopupMenu popupMenu;
	private String lastSelectedParent;
	private String lastSelectedFeature;
	
	public ParentSelector(FMSynthesisEnvironment environment) {
		this.environment = environment;
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		parentSelector = new DefaultMutableTreeNode();
		root.add(parentSelector);
		TreeModel model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setRootVisible(false);
		this.add(new JScrollPane(tree));
		
		// Create popup menu
		popupMenu = new JPopupMenu();
		JMenuItem selectParentItem = new JMenuItem("Select this parent");
		
		selectParentItem.addActionListener(new SelectParentActionListener());
		popupMenu.add(selectParentItem);
		
		JMenuItem ignoreParentItem = new JMenuItem("Ignore this parent");
		ignoreParentItem.addActionListener(new IgnoreParentActionListener());
		popupMenu.add(ignoreParentItem);
		
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = tree.getRowForLocation(e.getX(), e.getY());
					TreePath path = tree.getPathForRow(row);
					tree.setSelectionRow(row);
					if (path.getPathCount() == 3) {
						lastSelectedFeature = path.getPathComponent(1).toString();
						lastSelectedParent = path.getPathComponent(2).toString();
						popupMenu.show(tree, e.getX(), e.getY());	
					}
					
				}
			}
		});
		
	}

	public void updateParents(List<Entry<String, List<String>>> list) {
		System.out.println("UPDATING PARENTS");
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root =  (DefaultMutableTreeNode) model.getRoot();
		
		for (Entry<String, List<String>> entry : list) {
			DefaultMutableTreeNode feature = new DefaultMutableTreeNode(entry.getKey());
	        try {
	            TreePath path = tree.getNextMatch(entry.getKey(), 0, Position.Bias.Forward);
	        if (path == null) {
				root.add(feature);
	        } else {
	            feature = (DefaultMutableTreeNode) path.getLastPathComponent();
	            feature.removeAllChildren();
	        } } catch (Exception e) {
	             e.printStackTrace();
	        }
			
			for (String parent : entry.getValue()) {
				feature.add(new DefaultMutableTreeNode(parent));
			}
			
		}
		
		model.reload();
		
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
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
