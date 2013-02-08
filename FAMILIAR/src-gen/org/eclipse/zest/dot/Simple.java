package org.eclipse.zest.dot;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.*;
import org.eclipse.zest.dot.DotExport;
/** Zest graph generated from Graphviz DOT graph 'Simple'. */
public class Simple extends Graph {
	public Simple(final Composite parent, final int style) {
		super(parent, style);
		String nodeLabel = null;
		String edgeLabel = null;
		int edgeStyle = SWT.LINE_SOLID;
		setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);

		setLayoutAlgorithm(new TreeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		GraphNode n1 = new GraphNode(this, SWT.NONE, tryGlobal(nodeLabel, "1"));
		GraphNode n2 = new GraphNode(this, SWT.NONE, tryGlobal(nodeLabel, "2"));

		/* Connection from n1 to n2: */
		GraphConnection n1n2 = new GraphConnection(this, SWT.NONE, n1, n2);
		n1n2.setText(tryGlobal(edgeLabel, ""));
		n1n2.setLineStyle(edgeStyle);

	}
	private String tryGlobal(final String global, final String name) {
		return global == null ? name : global;
	}
	/** @return This graph as a DOT representation, to be rendered with Graphviz. */
	public String toDot() {
		return new DotExport(this).toDotString();
	}

	/* Support to run this graph as a Java application: */

	public static void main(final String[] args) {
		final Shell shell = createShell();
		String dot = new Simple(shell, SWT.NONE).toDot();
		System.out.println("Graph as DOT:\n" + dot);
		open(shell);
	}

	private static Shell createShell() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText(Simple.class.getSimpleName());
		shell.setLayout(new FillLayout());
		shell.setSize(200, 250);
		return shell;
	}

	private static void open(final Shell shell) {
		shell.open();
		while (!shell.isDisposed()) {
			while (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}
	}
}
