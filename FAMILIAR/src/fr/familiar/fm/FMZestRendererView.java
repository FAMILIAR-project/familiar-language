/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.fm;

public class FMZestRendererView {
}
//
//
// import org.eclipse.swt.widgets.Composite;
// import org.eclipse.ui.part.ViewPart;
// import org.eclipse.zest.core.widgets.Graph;
// import org.eclipse.zest.layouts.LayoutStyles;
// import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
// import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
// import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
// import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
// import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
//
//
// public class FMZestRendererView extends ViewPart {
//
// /**
// * The ID of the view as specified by the extension.
// */
// public static final String ID =
// "fr.unice.polytech.modalis.familiar.views.FMZestRendererView";
//
// private Graph _graph;
//
// private int _layout = 1;
//
// private Composite _parent;
//
//
// public void createPartControl(Composite parent) {
// setParent(parent) ;
// }
//
//
// public void setGraph(Graph graph) {
// _graph = graph ;
// _graph.getShell().setActive();
//
// }
//
// public void setLayoutManager() {
//
//
// switch (_layout) {
// case 1:
// _graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(
// LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
// _layout++;
// break;
// case 2:
// _graph.setLayoutAlgorithm(new SpringLayoutAlgorithm(
// LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
// _layout++;
// break;
// case 3:
// _graph.setLayoutAlgorithm(new GridLayoutAlgorithm(
// LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
// _layout++;
// break;
// case 4:
// _graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(
// LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
// _layout++;
// break;
// case 5:
// _graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(
// LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
// _layout = 1;
// break;
//
// }
//
//
//
// }
//
// /**
// * Passing the focus request to the viewer's control.
// */
// public void setFocus() {
// }
//
//
// /**
// * @param _parent the _parent to set
// */
// public void setParent(Composite _parent) {
// this._parent = _parent;
// }
//
//
// /**
// * @return the _parent
// */
// public Composite getParent() {
// return _parent;
// }
//
//
//
//
// }