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