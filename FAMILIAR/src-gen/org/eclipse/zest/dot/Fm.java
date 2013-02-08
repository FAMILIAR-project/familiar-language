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
/** Zest graph generated from Graphviz DOT graph 'fm'. */
public class Fm extends Graph {
	public Fm(final Composite parent, final int style) {
		super(parent, style);
		String nodeLabel = null;
		String edgeLabel = null;
		int edgeStyle = SWT.LINE_SOLID;
		setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);

		setLayoutAlgorithm(new TreeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		GraphNode n0 = new GraphNode(this, SWT.NONE, "I");
		GraphNode n1 = new GraphNode(this, SWT.NONE, "H");
		GraphNode n2 = new GraphNode(this, SWT.NONE, "B");
		GraphNode n3 = new GraphNode(this, SWT.NONE, "K");
		GraphNode n4 = new GraphNode(this, SWT.NONE, "C");
		GraphNode n5 = new GraphNode(this, SWT.NONE, "F");
		GraphNode n6 = new GraphNode(this, SWT.NONE, "J");
		GraphNode n7 = new GraphNode(this, SWT.NONE, "A");
		GraphNode n8 = new GraphNode(this, SWT.NONE, "D");
		GraphNode n9 = new GraphNode(this, SWT.NONE, "[BOT] 0");
		GraphNode n10 = new GraphNode(this, SWT.NONE, "E");
		GraphNode n11 = new GraphNode(this, SWT.NONE, "[TOP] 1");
		GraphNode n12 = new GraphNode(this, SWT.NONE, "G");

		/* Connection from n8 to n7: */
		GraphConnection n8n7 = new GraphConnection(this, SWT.NONE, n8, n7);
		n8n7.setText("");

		n8n7.setLineStyle(edgeStyle);

		/* Connection from n1 to n4: */
		GraphConnection n1n4 = new GraphConnection(this, SWT.NONE, n1, n4);
		n1n4.setText("");

		n1n4.setLineStyle(edgeStyle);

		/* Connection from n7 to n11: */
		GraphConnection n7n11 = new GraphConnection(this, SWT.NONE, n7, n11);
		n7n11.setText("");

		n7n11.setLineStyle(edgeStyle);

		/* Connection from n5 to n8: */
		GraphConnection n5n8 = new GraphConnection(this, SWT.NONE, n5, n8);
		n5n8.setText("");

		n5n8.setLineStyle(edgeStyle);

		/* Connection from n7 to n11: */
		GraphConnection n7n11 = new GraphConnection(this, SWT.NONE, n7, n11);
		n7n11.setText("");

		n7n11.setLineStyle(edgeStyle);

		/* Connection from n0 to n4: */
		GraphConnection n0n4 = new GraphConnection(this, SWT.NONE, n0, n4);
		n0n4.setText("");

		n0n4.setLineStyle(edgeStyle);

		/* Connection from n3 to n4: */
		GraphConnection n3n4 = new GraphConnection(this, SWT.NONE, n3, n4);
		n3n4.setText("");

		n3n4.setLineStyle(edgeStyle);

		/* Connection from n4 to n7: */
		GraphConnection n4n7 = new GraphConnection(this, SWT.NONE, n4, n7);
		n4n7.setText("");

		n4n7.setLineStyle(edgeStyle);

		/* Connection from n4 to n7: */
		GraphConnection n4n7 = new GraphConnection(this, SWT.NONE, n4, n7);
		n4n7.setText("");

		n4n7.setLineStyle(edgeStyle);

		/* Connection from n12 to n8: */
		GraphConnection n12n8 = new GraphConnection(this, SWT.NONE, n12, n8);
		n12n8.setText("");

		n12n8.setLineStyle(edgeStyle);

		/* Connection from n2 to n7: */
		GraphConnection n2n7 = new GraphConnection(this, SWT.NONE, n2, n7);
		n2n7.setText("");

		n2n7.setLineStyle(edgeStyle);

		/* Connection from n2 to n7: */
		GraphConnection n2n7 = new GraphConnection(this, SWT.NONE, n2, n7);
		n2n7.setText("");

		n2n7.setLineStyle(edgeStyle);

		/* Connection from n10 to n8: */
		GraphConnection n10n8 = new GraphConnection(this, SWT.NONE, n10, n8);
		n10n8.setText("");

		n10n8.setLineStyle(edgeStyle);

		/* Connection from n6 to n4: */
		GraphConnection n6n4 = new GraphConnection(this, SWT.NONE, n6, n4);
		n6n4.setText("");

		n6n4.setLineStyle(edgeStyle);

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
		String dot = new Fm(shell, SWT.NONE).toDot();
		System.out.println("Graph as DOT:\n" + dot);
		open(shell);
	}

	private static Shell createShell() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText(Fm.class.getSimpleName());
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
