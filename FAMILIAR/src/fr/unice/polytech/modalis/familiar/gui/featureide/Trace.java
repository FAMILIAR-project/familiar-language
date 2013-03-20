package fr.unice.polytech.modalis.familiar.gui.featureide;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class Trace extends ViewPart {

	public static final String ID = "de.ovgu.featureide.fm.ui.Trace";
	private TableViewer tableViewer;
	private Table table;

	private static ArrayList<String> commands = new ArrayList<String>();
	private static ArrayList<TraceObserver> observers = new ArrayList<TraceObserver>();

	public Trace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.SINGLE);
		table = tableViewer.getTable();

		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setWidth(100);
		tc.setText("Commands");
		table.setHeaderVisible(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param command
	 *            the command to consider (simply a String representation of an
	 *            FML command)
	 * @param console
	 *            determines whether or not the command comes from the console
	 *            and the interactive mode (instead of being a graphical
	 *            interaction with FeatureIDE editor)
	 */
	public void addCommand(String command, boolean console) {
		TableItem ti = new TableItem(table, SWT.NONE);
		ti.setText(command);
		if (console)
			ti.setBackground(Display.getDefault()
					.getSystemColor(SWT.COLOR_GRAY));
		commands.add(command);

	}

	/**
	 * 
	 */
	private void prevent(String commande) {
		System.out.println("DEBUG:: " + commande + " observers=" + observers);
		for (TraceObserver to : observers) {
			to.warn(commande);
		}

	}

	public static ArrayList<String> getCommandList() {
		return commands;
	}

	public static void register(TraceObserver to) {
		observers.add(to);
	}

	/**
	 * @param commande
	 */
	public void applyFMLCmd(String commande) {
		prevent(commande);
	}

}
