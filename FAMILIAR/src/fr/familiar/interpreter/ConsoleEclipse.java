/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.interpreter;

import java.io.IOException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;

/**
 * @author mathieuacher
 * 
 */
public class ConsoleEclipse implements Output {

	private static IConsoleManager _consoleMgr = null;
	private static final String FAMILIAR_CONSOLE = "FAMILIAR Console";

	private static IOConsole _console = null;
	private static IOConsoleOutputStream _consoleStream = null;

	/**
	 * The default constructor.
	 */
	public ConsoleEclipse() {
		super();

		if (_console == null)
			initConsole();

	}

	/**
	 * Finds the console in the list of registered consoles. If the console with
	 * name='name' is not registered, a new one is created.
	 * 
	 * @param name
	 * @return
	 */
	private IOConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		_consoleMgr = plugin.getConsoleManager();
		IConsole[] existing = _consoleMgr.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (IOConsole) existing[i];
		// no console found, so create a new one
		ImageDescriptor fDescr = null;
		try {
			// InputStream stream =
			// getClass().getResourceAsStream("img/mofscriptConsole.gif");
			// Image consoleImage = new Image (null, stream);
			// fDescr = ImageDescriptor.createFromImage(consoleImage); /*
			// Eclipse 3.1 */
		} catch (Exception ex) {

		}

		IOConsole myConsole = new IOConsole(name, fDescr);
		// MessageConsole myConsole = new MessageConsole(name, fDescr);
		_consoleMgr.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	/**
	 * prints a message to the console
	 * 
	 * @param s
	 */
	public void print(String s) {
		if (_consoleStream != null) {
			try {
				_consoleStream.write(s);
				_consoleStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			_consoleMgr.warnOfContentChange(_console);
		}
	}

	/**
	 * Prints a message to the console
	 * 
	 * @param s
	 */
	public void println(String s) {
		this.print(s + "\n");
	}

	/**
	 * Returns the message stream associated with the console
	 * 
	 * @return
	 */
	public static IOConsoleOutputStream getStream() {
		return _consoleStream;
	}

	/**
	 * Returns the input stream associated with the console
	 * 
	 * @return
	 */
	public IOConsoleInputStream getInputStream() {
		return _console.getInputStream();
	}

	/**
	 * Initiates output console
	 * 
	 * @see MOFSCRIPT_CONSOLE
	 */
	private void initConsole() {
		_console = findConsole(FAMILIAR_CONSOLE);

		Font f = null;
		try {
			FontData data = new FontData("Arial", 12, 12);
			data.setStyle(SWT.NORMAL);
			f = new Font(null, data);

			_console.setFont(f);
		} catch (Exception ex) {
		}
		// console.setFont(new Font());
		_consoleStream = _console.newOutputStream();

		activateConsole();
	}

	/**
	 * Activates the MOFScript console
	 */
	private void activateConsole() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {

			IWorkbenchPage page = window.getActivePage();
			String id = IConsoleConstants.ID_CONSOLE_VIEW;

			try {
				if (page != null) {
					IConsoleView view = (IConsoleView) page.showView(id);
					view.display(_console);
					// view.pin(_console);
					// view.setPinned(true); /* 3.1 operation */
				}

			} catch (PartInitException pex) {

				System.out.println("FAMILIARPlugin - " + pex);
			}
		}
	}

	@Override
	public void print(org.eclipse.swt.graphics.Color color, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void println(org.eclipse.swt.graphics.Color color, String msg) {
		// TODO Auto-generated method stub

	}

	public void clear() {
		_console.clearConsole();
	}

	@Override
	public void close() {
		try {
			_console.getInputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
