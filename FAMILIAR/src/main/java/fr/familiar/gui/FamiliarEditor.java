/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.filter.FisheyeTreeFilter;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.activity.Activity;
import prefuse.controls.Control;
import prefuse.controls.FocusControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Tree;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;
import prefuse.visual.VisualTree;
import fr.familiar.variable.FeatureModelVariable;

public class FamiliarEditor extends JPanel implements Observer {
	// A simple, fast, and thread-safe singleton implementation.
	public final static FamiliarEditor INSTANCE = new FamiliarEditor();
	
	private static final long serialVersionUID = 1L;

	private static final String treeGroup = "tree";
    private static final String treeNodes = "tree.nodes";
    public static final String treeEdges = "tree.edges";
	
	private static HashMap<String, Visualization> pVis;
	// This is for cases when we cannot retrieve the visualization by FMV name since
	// its tab has not been created yet
	private static Visualization lastCreatedVis;
    
    public void updateDisplay(FeatureModelVariable fmv) {
    	boolean revert = false;
    	Tree t = null;
    	if (null == fmv || false == fmv.isValid()) {
    		FamiliarConsole.INSTANCE.setMessage("Error: FMV is not valid.");
    		revert = true;
    	} else {
	    	t = Converter.INSTANCE.fmv2PrefuseTree(fmv);
	    	if (null == t || false == t.isValidTree()) {
//	    		FamiliarConsole.INSTANCE.setMessage("Error: Prefuse tree is not valid.");
	    		revert = true;
	    	} 	
    	}
    	
    	if (revert) {
//    		JOptionPane.showMessageDialog(this, 
//	            "FM is not valid, and it will not be updated and/or loaded.",
//	            "Error updating FM display", JOptionPane.ERROR_MESSAGE);
    		fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
    		if (null == fmv) {
    			FamiliarConsole.INSTANCE.setMessage("Error: No current FeatureModelVariable.");
    			return;
    		}
    		
    		t = Converter.INSTANCE.fmv2PrefuseTree(fmv);
    		if (false == fmv.isValid() || 
    			null == t || false == t.isValidTree()) {
//    			FamiliarConsole.INSTANCE.setMessage("Error: FMV and/or Prefuse tree is not valid.");
    			return;
    		}
    	} 
    	FamiliarConsole.INSTANCE.addOrReplaceFMVariable(fmv);
    	
    	Visualization v = getVis();
		if (null == v) {
			JOptionPane.showMessageDialog(this, 
					"Visualization of an FM is null. FN cannot be updated and/or loaded.",
		            "Error updating FM display", JOptionPane.ERROR_MESSAGE);
			return;
		}
    	v.setRendererFactory(Renderer.INSTANCE.getDRF(Converter.NAME));
    	v.removeGroup(treeGroup);
        VisualTree vt = v.addTree(treeGroup, t);
        v.setValue(treeEdges, null, VisualItem.INTERACTIVE, Boolean.FALSE);
        VisualItem f = (VisualItem)vt.getNode(0);
        v.getGroup(Visualization.FOCUS_ITEMS).setTuple(f);
        f.setFixed(false);
        v.run("draw");
    }
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
    private void initPrefuseVis() {
    	// Create a new, empty visualization for our data
        pVis = new HashMap<String, Visualization>();
    }
    
    private FamiliarEditor() {
    	super(new BorderLayout());
    	
    	Translator.INSTANCE.addObserver(this);
    	
    	initPrefuseVis();
    	
    	visualizeFM(Converter.INSTANCE.getStartupFMV());
        
		fixSelectedFocusNodes();
		
        JPanel south = new JPanel(new BorderLayout());
        south.add(new JScrollPane(FamiliarConsole.INSTANCE.createTextArea()), BorderLayout.CENTER);
        south.add(StatusBar.INSTANCE.createStatusBar(), BorderLayout.SOUTH);
        
        // Create a new JSplitPane to present the interface
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, Tab2EnvVar.INSTANCE.getTab(), south);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(false);
        split.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().getHeight() <= 768 ? 450 : 700);
        split.setDividerSize(10);
        split.setSize(1000, 2100);
        add(split); 
    }
    
    public void visualizeFM(FeatureModelVariable fmv) {
    	Visualization v = new Visualization();

        // Create actions to process the visual data
        int depth = 9999;
        final FisheyeTreeFilter filter = new FisheyeTreeFilter(treeGroup, depth);
        
        ActionList draw = new ActionList();
        draw.add(filter);
        draw.add(new ColorAction(treeNodes, VisualItem.FILLCOLOR, Color.WHITE.getRGB()));
        draw.add(new ColorAction(treeNodes, VisualItem.TEXTCOLOR, Color.BLUE.getRGB()));
        // Border of the rectangle (node) and line (edge)
        draw.add(new ColorAction(treeNodes, VisualItem.STROKECOLOR, Color.BLACK.getRGB()));
        draw.add(new ColorAction(treeEdges, VisualItem.STROKECOLOR, Color.BLACK.getRGB()));
        v.putAction("draw", draw);
        
        // Create the tree layout action
        NodeLinkTreeLayout treeLayout = new NodeLinkTreeLayout(treeGroup, Constants.ORIENT_TOP_BOTTOM, 20, 5, 5);
        treeLayout.setLayoutAnchor(new Point2D.Double(25, 250));
        v.putAction("treeLayout", treeLayout);
        
        CollapsedSubtreeLayout subLayout = new CollapsedSubtreeLayout(treeGroup, Constants.ORIENT_TOP_BOTTOM);
        v.putAction("subLayout", subLayout);
        
        ActionList animate = new ActionList(Activity.INFINITY);
        animate.add(treeLayout);
        animate.add(subLayout);
        animate.add(new RepaintAction());
        v.putAction("animate", animate);
        v.runAfter("draw", "animate");
        
        // Get the screen dimensions.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) screen.getWidth();
        int h = (int) screen.getHeight();
        
        // Set up a display to show the visualization
        Display display = new Display(v);
        display.setSize(w, h);
        if (h <= 768) {
        	display.pan(w/2-70, -130); 
        	display.zoom(new Point2D.Double(w/2,h/2), 1.0);
        } else {
        	display.pan(w/2-70, 30);
        	display.zoom(new Point2D.Double(w/2,h/2), 1.2);
        }
        display.addControlListener(new FocusControl(1));
        display.addControlListener(new PanControl());
        display.addControlListener(new ZoomControl());
        display.addControlListener(new WheelZoomControl());
        display.addControlListener(new ZoomToFitControl(Control.MIDDLE_MOUSE_BUTTON));
        
        PopupMenuController popup = new PopupMenuController();
        display.addControlListener(popup);
        display.addKeyListener(popup);
        
        Tab2EnvVar.INSTANCE.createNewTab(fmv.getIdentifier(), display);
        lastCreatedVis = v;
        pVis.put(fmv.getIdentifier(), v);
        updateDisplay(fmv);
    }
    
    public static Visualization getVis() {
    	String fmvName = Tab2EnvVar.INSTANCE.getCurrentFMVName();
    	if (null == fmvName) {
    		return null;
    	}
    	Visualization v = pVis.get(fmvName);
    	return (null == v ? lastCreatedVis : v);
    }

	@Override
	public void update(Observable obj, Object arg) {
		if (arg instanceof FeatureModelVariable) {
			FeatureModelVariable fmv = (FeatureModelVariable)arg;
			FamiliarEditor.INSTANCE.updateDisplay(fmv);
		}
	}
	
	public void pVisRenameKey(String oldName, String newName) {
		if (pVis.containsKey(oldName)) {
			Visualization v = pVis.get(oldName);
			pVis.remove(oldName);
			pVis.put(newName, v);
		}
	}
	
	private void fixSelectedFocusNodes() {
        TupleSet focusGroup = getVis().getGroup(Visualization.FOCUS_ITEMS); 
        focusGroup.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem) {
                for (int i=0; i<rem.length; ++i)
                    ((VisualItem)rem[i]).setFixed(false);
                for (int i=0; i<add.length; ++i) {
                    ((VisualItem)add[i]).setFixed(false);
                    ((VisualItem)add[i]).setFixed(true);
                }
                if (ts.getTupleCount() == 0) {
                    ts.addTuple(rem[0]);
                    ((VisualItem)rem[0]).setFixed(false);
                }
                getVis().run("draw");
            }
        });
	}
} // end of class FamiliarEditor
