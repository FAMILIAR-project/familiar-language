/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)
 * project (http://familiar-project.github.com/).
 *
 * Copyright (C) 2011 - 2013
 *     University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *     Colorado State University, Computer Science Department
 *     
 * Author: Aleksandar Jaksic
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
package fr.unice.polytech.modalis.familiar.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.Element;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

public class FamiliarConsole {
	// A simple, fast, and thread-safe singleton implementation.
	public final static FamiliarConsole INSTANCE = new FamiliarConsole();
	private final static JTextArea console = new JTextArea();
	private OutputStream out = null;
	
	private FMLShell fShell;
	private FMLCommandInterpreter fEnv;
	private boolean fVerbose = false;
	
	private FamiliarConsole() {
	}
	
	public JTextArea createTextArea() {
		console.setLineWrap(true);
		console.setRows(15);
		console.setWrapStyleWord(true);
		console.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		redirectSystemStreams();
		
		fShell = FMLShell.instantiateStandalone(System.in);
		fShell.setToInteractiveMode();
		
		fEnv = fShell.getCurrentEnv();
		
		console.addKeyListener(new KeyAdapter() { 
			public void keyTyped(KeyEvent evt) { } 
			public void keyPressed(KeyEvent evt) { 
				switch (evt.getKeyCode()) { 
					case KeyEvent.VK_ENTER: 
						try {	
							// Read the command from the last line of TextArea
							Document document = console.getDocument();
							Element rootElem = document.getDefaultRootElement();
							int numLines = rootElem.getElementCount();
							Element lineElem = rootElem.getElement(numLines - 1);
							int lineStart = lineElem.getStartOffset();
							int lineEnd = lineElem.getEndOffset();
							String lastLine = document.getText(lineStart, lineEnd - lineStart);
							lastLine = lastLine.replaceFirst(FMLShell.PROMPT, "").trim();
							parseCommand(lastLine);
							if (lastLine.contains("configuration")) {
								String[] tokens = lastLine.split("="); // c = configuration Laptop  
								if (null != tokens && !tokens[0].isEmpty()) {
									Variable v = getVariableByName(tokens[0].trim());
									if (v instanceof ConfigurationVariable) {
										Tab2EnvVar.INSTANCE.createNewConfigurationTab(
												(ConfigurationVariable) v, false);
									}
								}
							} else {
								// Update the FM view
								Translator.INSTANCE.changedFmv(FamiliarConsole.INSTANCE.getLoadedFMV());
							}
						} catch (Exception e) {
							e.printStackTrace();
							fShell.printPrompt();
						}
					break; 
				} 
		} });
		return console;
	}
	
	public void createNewConfig(String configName, String fmvName) {
		fShell.parse(configName + "= configuration " + fmvName);
		fShell.printPrompt();
		console.setCaretPosition(console.getDocument().getLength()); 
		Variable v = getVariableByName(configName);
		if (v instanceof ConfigurationVariable) {
			Tab2EnvVar.INSTANCE.createNewConfigurationTab(
					(ConfigurationVariable) v, true);
		}
	}
	
	public void runScript(File f) {
		String currentDir = System.getProperty("user.dir");
		
		String filename = null;
		int i = f.getAbsolutePath().indexOf(currentDir);
		if(i != -1) {
			// Remove 1st line from 2nd to get the 3rd line
			// D:\Workspace\FAMILIAR
			// D:\Workspace\FAMILIAR\examples\testing\FMs\fm0.fml
			// examples\testing\FMs\fm0.fml
			filename = f.getAbsolutePath().substring(currentDir.length() + 1, f.getAbsolutePath().length());
			// examples/testing/FMs/fm0.fml
			filename = filename.replace('\\', '/');
		} else {
			filename = f.getName();
		}
		parseCommand("run \"" + filename + "\"");
		StatusBar.INSTANCE.setLoadedFMVStatusBar(allFmvToString());
		
		List<Variable> vars = fEnv.getVariables();
		ListIterator<Variable> itr = vars.listIterator(vars.size());
		
		while (itr.hasPrevious()) {
			Variable v = (Variable) itr.previous();
			if (v instanceof FeatureModelVariable) {
				Tab2EnvVar.INSTANCE.createNewTab((FeatureModelVariable) v);
			} else if (v instanceof ConfigurationVariable) {
				Tab2EnvVar.INSTANCE.createNewConfigurationTab((ConfigurationVariable) v, false);
			}
		}
	}
	
	public void setMessage(String msg) {
		if (!msg.isEmpty()) {
			console.append("\n" + msg + "\n");
		}
		setPrompt();
	}
	
	public void displayHeader() {
		console.setText("");
		fShell.printFMLHeader();
		fShell.printPrompt();
	}
	
	public void setPrompt() { 
		fShell.printPrompt();
	}
	
	public void clearFMVariables() {
		fEnv.clear();
		StatusBar.INSTANCE.setLoadedFMVStatusBar(allFmvToString());
	}
	
	public void ClearConsole() {
		console.setText("");
		fShell.printPrompt();
	}
	
	public void setVerboseLogging(boolean verbose) {
		fVerbose = verbose;
		fShell.setVerbose(verbose);
	}
	
	public boolean isVerbose() { return fVerbose; }
	
	public void renameFMV(String oldName, String newName) {
		FeatureModelVariable fmv = null;
		List<Variable> vars = fEnv.getVariables();
		ListIterator<Variable> itr = vars.listIterator(vars.size());
		while (itr.hasPrevious()) {
			Variable v = (Variable) itr.previous();
			if (v instanceof FeatureModelVariable) {
				fmv = (FeatureModelVariable) v;
				if (fmv.getIdentifier().contentEquals(oldName)) {
					fmv.setIdentifier(newName);
					StatusBar.INSTANCE.setLoadedFMVStatusBar(allFmvToString());
					StatusBar.INSTANCE.setLoadedFMlabel(fmv);
					Tab2EnvVar.INSTANCE.renameCurrentFMVName(newName);
					FamiliarEditor.INSTANCE.pVisRenameKey(oldName, newName);
					break;
				}
			}
		}
	}
	
	public Variable getVariableByName(String name) {
		if (null == name) {
			return null;
		}
		List<Variable> vars = fEnv.getVariables();
		ListIterator<Variable> itr = vars.listIterator(vars.size());
		while (itr.hasPrevious()) {
			Variable v = (Variable) itr.previous();
			if (v instanceof FeatureModelVariable || v instanceof ConfigurationVariable) {
				if (v.getIdentifier().contentEquals(name)) {
					return v;
				} 
			} 
		}
		return null;
	}
	
	public FeatureModelVariable getLoadedFMV() {
		String fmvName = Tab2EnvVar.INSTANCE.getCurrentFMVName();
		if (null == fmvName) {
			String[] fms = FamiliarConsole.INSTANCE.allFmvToStringArray();
			if (null != fms && fms.length > 0) {
				if (1 == fms.length) {
					return (FeatureModelVariable)getVariableByName(fms[0]);
				}
				final String selectFM = "Select Current Feature Model";
				String loadFM = (String) JOptionPane.showInputDialog(null, "Choose your current FM...",
						selectFM, JOptionPane.QUESTION_MESSAGE, null, fms, fms[0]); // Initial choice
			    if (null != loadFM) {
		            fmvName = loadFM;
			    } else {
			    	return null;
			    }
			} else {
				return null;
			}
		}
		Variable v = getVariableByName(fmvName);
		if (v instanceof FeatureModelVariable) {
			return (FeatureModelVariable)v;
		}
		return null;
	}
	
	public void addOrReplaceFMVariable(FeatureModelVariable fmv) {
		fEnv.addOrReplaceVariable(fmv.getIdentifier(), fmv);
		StatusBar.INSTANCE.setLoadedFMVStatusBar(allFmvToString());
		if (isVerbose()) {
    		setPrompt();
    	}
	}
	
	public String[] allFmvToStringArray() {
		ListIterator<Variable> itr = fEnv.getVariables().listIterator();
		if (fEnv.getVariables().size() <= 0 ) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		Variable v = null; 
		if(itr.hasNext()) {
			v = itr.next();
		} else {
			return null;
		}
	
		while (null != v) {
			if (v instanceof FeatureModelVariable) {
				FeatureModelVariable fmv = (FeatureModelVariable) v;
				list.add(fmv.getIdentifier());
			}
			if (itr.hasNext()) {
				v = itr.next();
			} else {
				break;
			}
		}
		String[] array = list.toArray(new String[list.size()]);
		return array;
	}
	
	public void showAllVariables() {
		ListIterator<Variable> itr = fEnv.getVariables().listIterator();
		if (fEnv.getVariables().size() < 1 ) {
			return;
		}
		Variable v = null; 
		if(itr.hasNext()) {
			v = itr.next();
		} else {
			return;
		}
	
		while (null != v) {
			if (v instanceof FeatureModelVariable) {
				Tab2EnvVar.INSTANCE.switchToNewTab(v.getIdentifier());
			} else if (v instanceof ConfigurationVariable) {
				Tab2EnvVar.INSTANCE.switchToNewTab(v.getIdentifier());
			}
			if (itr.hasNext()) {
				v = itr.next();
			} else {
				break;
			}
		}
	}
	
	private String allFmvToString() {
		StringBuilder sb = new StringBuilder(StatusBar.loadedFMVars);
		ListIterator<Variable> itr = fEnv.getVariables().listIterator();
		Variable v = null; 
		if(itr.hasNext()) {
			v = itr.next();
		} else {
			sb.append("NONE");
			return sb.toString();
		}
		while (null != v) {
			if (v instanceof FeatureModelVariable) {
				FeatureModelVariable fmv = (FeatureModelVariable) v;
				sb.append("\"" + fmv.getIdentifier() + "\" | ");
			} 
			if (itr.hasNext()) {
				v = itr.next();
			} else {
				break;
			}
		}
		return sb.toString();
	}
	
	private void parseCommand(String command) {
		fShell.parse(command);
		fShell.printPrompt();
		console.setCaretPosition(console.getDocument().getLength()); 
		StatusBar.INSTANCE.setLoadedFMVStatusBar(allFmvToString());
	}

	private void updateTextArea(final String text) {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  if (!text.contains("prefuse")) {
	    		  console.append(text);
	    	  }
	      }
	    });
	}
	
	private void redirectSystemStreams() {
		out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				updateTextArea(String.valueOf((char) b));
			}
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				updateTextArea(new String(b, off, len));
			}
			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
	    };
	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	}
} // end of class FamiliarConsole
