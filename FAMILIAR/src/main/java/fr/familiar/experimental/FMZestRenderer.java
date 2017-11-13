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

package fr.familiar.experimental;

public class FMZestRenderer {
}
//
// import org.eclipse.swt.SWT;
// import org.eclipse.swt.graphics.Color;
// import org.eclipse.swt.graphics.Device;
// import org.eclipse.swt.layout.FillLayout;
// import org.eclipse.swt.widgets.Composite;
// import org.eclipse.swt.widgets.Display;
// import org.eclipse.swt.widgets.Shell;
// import org.eclipse.zest.core.widgets.Graph;
// import org.eclipse.zest.dot.DotImport;
// import org.eclipse.zest.layouts.LayoutStyles;
// import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
//
// import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
//
// /**
// * @author mathieuacher
// *
// */
// public class FMZestRenderer {
//
// /**
// * feature model to display
// */
// private FeatureModelVariable _fmv;
//
// public FMZestRenderer(FeatureModelVariable featureModelVariable) {
// _fmv = featureModelVariable ;
// }
//
// public void display() {
//
// final Shell shell = new Shell();
// computeZestGraph(shell);
// shell.setText(_fmv.getIdentifier());
// shell.setLayout(new FillLayout());
// shell.setSize(600, 300);
// shell.open();
// while (!shell.isDisposed()) {
// while (!shell.getDisplay().readAndDispatch()) {
// shell.getDisplay().sleep();
// }
// }
//
// }
//
// /******************************************************************
// * Graph
// ******************************************************************/
// /**
// * @param parent
// * add a Zest graph to parent (e.g., can be a shell)
// */
// public Graph computeZestGraph(Composite parent) {
//
// String dot = _fmv.toDOT();
// DotImport importer = new DotImport(dot); //new
// DotImport("digraph Simple { 1;2; 1->2 }");
//
// /* Create a Zest graph instance in a parent, with a style: */
// Graph graph = importer.newGraphInstance(parent, SWT.NONE);
// Device device = Display.getCurrent() ;
// Color background = new Color(device, 100, 100, 100);
//
// graph.setBackground(background);
//
// TreeLayoutAlgorithm treeLayoutAlgorithm = new
// TreeLayoutAlgorithm(LayoutStyles.ENFORCE_BOUNDS);
// graph.setLayoutAlgorithm(treeLayoutAlgorithm, true);
// return graph ;
// }
//
// }
