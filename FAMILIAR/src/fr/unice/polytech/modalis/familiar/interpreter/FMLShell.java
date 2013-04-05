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
package fr.unice.polytech.modalis.familiar.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import fr.unice.polytech.modalis.familiar.fm.URIEclipseExtractor;
import fr.unice.polytech.modalis.familiar.gui.featureide.Trace;
import fr.unice.polytech.modalis.familiar.gui.featureide.TraceObserver;
import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.operations.SatisfiableStrategy;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.parser.WildCardVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.utils.AmbigousFileNameException;
import fr.unice.polytech.modalis.utils.FileListing;
import fr.unice.polytech.modalis.utils.URIUtils;

/*
 * Shell/Prompt to interact with FML Interpreter 
 * 
 */

/**
 * @author mathieuacher
 * 
 */
public class FMLShell {
	
	
	private static Logger _LOGGER = Logger.getLogger(FMLShell.class);


	public static final String PROMPT = "fml>";
	public static final String FILE_SEPARATOR = File.separator;

	private static final String TMP_FOLDER = "tmp";
	private static final String OUTPUT_FOLDER = "output";

	private static final boolean DEFAULT_VERBOSE = false;
	private boolean _verbose = DEFAULT_VERBOSE;

	public static final String FML_VERSION = "1.0.7 (beta)";

	/*
	 * output facilities (display messages, debug messages, etc.)
	 */
	private Output _output;

	/*
	 * input stream of the shell (file stream, input from the user, etc.)
	 */
	private InputStream _input = null; // TODO: can be null?

	private BufferedReader _reader;

	private boolean _stepByStep = false;

	private static FMLShell _INSTANCE = null;
	private List<File> _paths;
	private FileListing _lst = null;

	/**
	 * FML environment: management of variables, parsing facilities, etc.
	 */
	private FMLCommandInterpreter _currentEnv;

	public static final boolean LINE_BY_LINE = false;

	private ExecutionMode _executionMode = ExecutionMode.NON_INTERACTIVE; // TODO

	// mode: control, warning, nothing
	private ErrorMode _errorMode = ErrorMode.NORMAL; // TODO

	/*
	 * current FML file that is interpreted
	 */
	private IFile _currentFile = null;

	private boolean _runningScript = false; // by default we are not running
											// script

	private FMLInterpreterTracer _tracer;

	// Shell in Eclipse environment
	private boolean _eclipse = false;

	/*
	 * Is it the first time you call a script?
	 */
	private boolean _firstScriptFile = true;

	private List<String> _errors;

	/**
	 * e.g., when the script is not syntactically correct
	 */
	private List<String> _fatalErrors;

	/**
	 * i.e., when some assertions fail during the script execution
	 */
	private List<String> _assertionErrors;

	/**
	 * counting function
	 */
	private static final CountingStrategy DEFAULT_COUNTING_STRATEGY = CountingStrategy.BDD_FML;
	private CountingStrategy _countingStrategy = DEFAULT_COUNTING_STRATEGY;
	
	/**
	 * satisfiable function
	 */
	private static final SatisfiableStrategy DEFAULT_SATISFIABLE_STRATEGY = SatisfiableStrategy.BDD_FML;
	private SatisfiableStrategy _satisfiableStrategy = DEFAULT_SATISFIABLE_STRATEGY;

	/**
	 * compare strategy
	 */
	private static final ComparisonStrategy DEFAULT_CMP_STRATEGY = ComparisonStrategy.BDD; // ComparisonStrategy.SAT;
	private ComparisonStrategy _comparisonStrategy = DEFAULT_CMP_STRATEGY;

	/**
	 * BDD construction strategy (SPLOT or FAMILIAR)
	 */
	private static final BDDStrategy DEFAULT_BDD_STRATEGY = BDDStrategy.FML;
	private BDDStrategy _bddStrategy = DEFAULT_BDD_STRATEGY;;

	/*
	 * instantiate an FML interpreter in an Eclipse environment
	 */

	public static FMLShell instantiateWithEclipse(InputStream in) {
		FMLShell shell = new FMLShell(in, new ConsoleEclipse(), true);
		// vp: plugin
		shell.addPath(new File(URIEclipseExtractor.getWorkspacePath()));

		Trace.register(new TraceObserver() {
			public void warn(String command) {
				List<String> commands = Trace.getCommandList();
				FMLShell.getInstance()
						.printDebugMessage("commands=" + commands);
				if (commands.size() != 0) {
					String lastCommand = commands.get(commands.size() - 1);
					FMLShell.getInstance().printDisplay(
							lastCommand + System.getProperty("line.separator"));
					FMLShell.getInstance()
							.getCurrentEnv()
							.parseCommand(command, NSFactory.mkEmpty(),
									new ArrayList<Variable>());
					FMLShell.getInstance().printPrompt();
				}
			}
		});

		return shell;
	}

	private void createTemporaryPath() {

		if (!_eclipse)
			return;
		assert (_eclipse);

		_LOGGER.debug(
				"FMShell.createTemporaryPath()");

		IFile scriptLocation = getCurrentFile();
		IProject project = scriptLocation.getProject();
		IFolder folder = project.getFolder(TMP_FOLDER);
		try {
			if (!folder.exists()) {
				folder.create(false, true, null);
				_LOGGER.debug("tmp folder created");
			} else
				_LOGGER.debug(
						"tmp folder already exists");
		} catch (CoreException e) {
			FMLShell.getInstance().setError(
					"Unable to create the tmp folder "
							+ e.getLocalizedMessage());
			return;
		}

	}

	private void enableEclipse(boolean b) {
		_eclipse = b;
	}

	/*
	 * instantiate an FML interpreter in a standalone environment
	 */
	public static FMLShell instantiateStandalone(InputStream in) {
		FMLShell shell = new FMLShell(in, new DefaultOutput());

		// TODO: configuration files or aguments
		// shell.addPath(new
		// File("/Users/mathieuacher/Documents/workspaceScala/FAMILIAR/examples"));
		shell.addPath(new File(System.getProperty("user.dir")));
		return shell;
	}

	private FMLShell(InputStream input, Output output) {
		this(input, output, false);
	}

	public FMLShell(InputStream input, Output output, boolean eclipseBased) {
		enableEclipse(eclipseBased);
		_output = output;
		_input = input;
		_paths = new ArrayList<File>();
		_tracer = new FMLInterpreterTracer();
		_INSTANCE = this;
		_currentEnv = new FMLCommandInterpreter(NSFactory.mkEmpty());

		closeLogger();
		init();

	}

	public void init() {

		_errors = new ArrayList<String>();
		_fatalErrors = new ArrayList<String>();
		_assertionErrors = new ArrayList<String>();

	}

	/**
	 * Close loggers of third party libraries, e.g., JavaBDD
	 */
	private void closeLogger() {
		/*
		 * LogManager.getLoggerRepository().setThreshold(Level.OFF);
		 * 
		 * try { java.util.logging.LogManager.getLogManager().readConfiguration(
		 * new ByteArrayInputStream(".level=OFF".getBytes())); } catch
		 * (SecurityException e) { e.printStackTrace(); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 * 
		 * System.out.println ("setting loggers");
		 */
	}

	// Prints a 'info' message to the specified console.
	public void printInfoMessage(java.lang.String info) {

		// output.println("info: " + info);
		_output.println("Info: " + info);
	}

	// Prints a 'cmd' message to the specified console.
	public void printInfoCmd(java.lang.String info) {

		// output.println("(info cmd) " + info);
		printDebugMessage("(Info cmd) " + info);
	}

	// Prints a prompt to the specified console.
	public void printPrompt() {
		_output.print(PROMPT + " ");
	}

	private void runPrompt() {

		// shell loop
		if (_input == null) {
			printDebugMessage("No input.");
			return ;
		}

		printDebugMessage("Loading prompt.");

		// vp: plugin
		if (FMLShell.getInstance().isEclipseBased())
			((ConsoleEclipse) _output).clear();// clear the console

		_reader = new BufferedReader(new InputStreamReader(_input));
		
		while (true) {
			// System.out.println("WHILE.");
			try {
				
				if (!_reader.ready()) {

					switchToInterativeMode();
					return;
					// input = ((ConsoleEclipse) output).getInputStream(); //
					// System.in;
					//
					// LINE_BY_LINE = false ;
				}

				String cmd = null;
				String sbuffer = "";
				while (((cmd = _reader.readLine()) != null)) {
					sbuffer += cmd + System.getProperty("line.separator");

					// FMShell.getInstance().printDebugMessage("stepBystep mode?"
					// + stepByStep);
					if (LINE_BY_LINE || _stepByStep) {
						MessageBox msgb = new MessageBox(Display.getDefault()
								.getActiveShell(), SWT.YES | SWT.NO
								| SWT.ICON_QUESTION);
						msgb.setText("Mode pas a pas");
						msgb.setMessage(cmd);
						int rep = msgb.open();
						if (rep == SWT.YES) {
							_currentEnv.parseCommand(cmd, NSFactory.mkEmpty(), // no
																				// namespace
									new ArrayList<Variable>()); // no parameters
						} else {
							return;
						}

						// printPrompt();
					}
					// else

				}
				if (!LINE_BY_LINE)
					_currentEnv.parseCommand(sbuffer, NSFactory.mkEmpty(),
							new ArrayList<Variable>());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void setError(String string) {
		_errors.add(string);

		String errorInformation = "(" + _currentEnv.getCurrentCommand() + ") ";

		// eventually?
		printError(errorInformation + string);
	}

	public void printError(String string) {
		printDisplay("###########\t error: " + string
				+ System.getProperty("line.separator"));
		if (_executionMode == ExecutionMode.INTERACTIVE) {
			if (_errorMode == ErrorMode.WARNING)
				printWarning("Command is ignored."
						+ System.getProperty("line.separator"));
		}

		if (_executionMode == ExecutionMode.NON_INTERACTIVE) { // TODO
			if (isVerbose())
				_currentEnv.printAllVariables();
		}

		if (_errorMode == ErrorMode.STRONG)
			close();
	}

	public void close() {

		FMLShell.getInstance().printDisplay(
				"Bye, FAMILIAR user!" + System.getProperty("line.separator"));
		try {
			if (_reader != null && _reader.ready()) {
				_reader.close();
				// other things
			}

			if (_eclipse)
				((ConsoleEclipse) _output).clear();

			if (!_eclipse) { // standalone
				_output.close();
				if (_input != null)
					_input.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!_eclipse) // standalone
			System.exit(0);

	}

	public void launch() {
		_LOGGER.debug("Launching FAMILIAR");
		runPrompt();

	}

	// public Output getOutput() {
	// return output;
	// }

	// public void setOutput(Output output) {
	// this.output = output;
	// }

	public void printTODO() {
		printTODO("command not yet implemented!");
	}

	public void printWarning(String string) {
		_output.println("\t \t Warning: " + string);

	}

	/*
	 * set the base path
	 */
	public void addPath(File basePath) {
		if (!this._paths.contains(basePath)) {
			// add new path only if it wasn't added before
			this._paths.add(basePath);
		}
	}

	public File searchFile(String aStrFile) {

		printDebugMessage("searching file " + aStrFile);
		if (this._lst == null)
			this._lst = new FileListing(_paths);
		try {
			return _lst.searchFile(aStrFile);
		} catch (FileNotFoundException e) {
			setError(e.toString());
		} catch (AmbigousFileNameException e) {
			setError(e.toString());
		}

		return null;

	}

	/**
	 * Search resursively in all paths (directory tree) an aFile
	 * 
	 * @param aFile
	 * @return file if found
	 */
	@Deprecated
	public File searchFile(File aFile) {
		if (this._lst == null)
			this._lst = new FileListing(_paths);
		try {
			return _lst.searchFile(aFile);
		} catch (FileNotFoundException e) {
			setError(e.toString());
		} catch (AmbigousFileNameException e) {
			setError(e.toString());
		}

		return null;
	}

	public void printDebugMessage(String str) {
		if (_verbose)
			System.out.println("DEBUG: " + str);

	}

	/**
	 * @return an instance of a FAMILIAR shell
	 */
	public static FMLShell getInstance() {
		return _INSTANCE;
	}

	/**
	 * @return the current environment
	 */
	public FMLCommandInterpreter getCurrentEnv() {
		return _currentEnv;
	}

	/**
	 * @param currentEnv
	 *            the currentEnv to set
	 */
	public void setCurrentEnv(FMLCommandInterpreter currentEnv) {
		this._currentEnv = currentEnv;
	}

	public void setAssertionViolation(String assertion) {
		setAssertionError(assertion);
		printDisplay("Assertion error: " + assertion);
	}

	public void setAssertionViolation() {
		setAssertionViolation("");

	}

	public void printDeprecated() {
		System.err.println("Deprecated ");
		System.exit(0);

	}
	
	public void setToInteractiveMode() {
		_executionMode = ExecutionMode.INTERACTIVE;
	}
	
	public void setToNonInteractiveMode() {
		_executionMode = ExecutionMode.NON_INTERACTIVE ; 
	}

	public void switchToInterativeMode() {
		setToInteractiveMode();
		printFMLHeader();
		printPrompt();
		InputStream input = FMLShell.getInstance().isStandalone() ? System.in
				: ((ConsoleEclipse) _output).getInputStream();

		// TODO for standalone
		if (FMLShell.getInstance().isEclipseBased()) {
			mkShellInput(input);
		}

		// input shell / standalone
		else {
			InputStreamReader isr = null ;
			BufferedReader br = null ; 
			
			try {
				isr = new InputStreamReader(input);
				br = new BufferedReader(isr);

				while (true) {
					//if (br.ready()) {
						String s = br.readLine();
						if (s == null) {
							break;
						}

						_LOGGER.debug(
								"FAMILIAR" + ": " + s);
						FMLShell.getInstance().parse(s);
						FMLShell.getInstance().printPrompt();

					//}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				
				try {
					br.close();
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
					_LOGGER.debug(
							"Unable to close " + ": ");
					
				}
			}
		}

	}

	private static StreamProcessor _sp = null;
	
	private void mkShellInput(InputStream input) {
		if (_sp == null) {
			_sp = new StreamProcessor(input);
			_sp.start();
		} else {
			//
		}

	}

	public void printFMLHeader() {
		_output.println("FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) "
				+ " version " + FML_VERSION);
		//_output.println("University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory");
		_output.println("http://familiar-project.github.com/");
		//_output.println("https://nyx.unice.fr/projects/familiar/");
	}

	public void launchEclipseInteractiveMode() {

		this._currentEnv = new FMLCommandInterpreter(NSFactory.mkEmpty());
		switchToInterativeMode();
	}

	/*
	 * 
	 * @param filename can be a directory with a wildcard
	 */
	public static void testScript(String filename) {
		assert (filename != null);

		int len = filename.length();

		if (WildCardVariable.WILD_CARD.equals("" + filename.charAt(len - 1))) {
			String directoryName = filename.substring(0, len - 1);
			File directory = new File(directoryName);
			if (directory.exists() && directory.isDirectory()) {

				FileListing ls = new FileListing(directory);
				List<File> scripts = ls.getFileListing(directory);
				System.err.println("\t#### directory testing" + directoryName);
				for (File file : scripts) {
					testIndividualScript(file.getAbsolutePath());
				}
				System.err.println("\t#### end of directory testing "
						+ directoryName);
			} else {
				System.err.println("\t\tUnable to open directory "
						+ directoryName);
			}
		} else {
			testIndividualScript(filename);
		}

	}

	private static void testIndividualScript(String filename) {
		try {
			System.err.println("\t\tbegin of test: " + filename);
			FileInputStream fin = new FileInputStream(filename);
			FMLShell shell = instantiateStandalone(fin);
			shell.launch();

			System.err.println("\t\tend of test: " + filename);

		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}
	}

	public void printDisplay(String toDisplay) {
		_LOGGER.debug("(DISPLAYING) " + toDisplay);
		_output.print(toDisplay);
	}

	public Variable parse(String cmd) {
		final String c = cmd;
		Variable v = _currentEnv.parseCommand(cmd, NSFactory.mkEmpty(),
				new ArrayList<Variable>());

		/*****
		 * in Eclipse interactive mode, we copy the last command to the trace
		 * view
		 *******/
		if (isEclipseInteractiveMode()) {

			if (getPreference().isTraceActivated()) {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						registerCommandtoEclipseTrace(c);

					}
				});

			}
		}

		/**** by default we maintain a trace (e.g., for autocompletions) ****/
		_tracer.registerCommand(c);
		if (v != null)
			v.setShell(this);
		return v ; 
	}

	/**
	 * "register" the command to the Eclipse trace view
	 * 
	 * @param commande
	 *            the string-based representation of the command to register
	 */
	public void registerCommandtoEclipseTrace(String commande) {

		IWorkbenchPage page = null;
		IViewPart viewPart = null;
		Trace trace;

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(Trace.ID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (page == null) {
			page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage();
			_LOGGER.debug("page " + page);
			_LOGGER.debug(
					"vreference " + page.findViewReference(Trace.ID));
			viewPart = page.findViewReference(Trace.ID).getView(true);
		}

		if (viewPart != null) {
			if (viewPart instanceof Trace) {
				trace = (Trace) viewPart;
				trace.addCommand(commande, true);

			}
		}

	}

	// *******************

	public static void executeEclipseScript(IFile file, boolean stepByStepMode) {
		java.net.URI u = file.getLocationURI();
		String filename = u.getPath();
		try {
			System.err.println("\t\trunning FAMILIAR script: " + filename);
			FileInputStream fin = new FileInputStream(filename);
			FMLShell shell = instantiateWithEclipse(fin);
			shell.setCurrentFile(file);

			shell.setStepByStep(stepByStepMode);

			shell.launch();

			System.err.println("\t\tend of FAMILIAR script: " + filename);

		} catch (IOException e) {
			System.err.println("File not found!");
			e.printStackTrace();
		}

		catch (Exception e) {
			System.err.println("Unexpected error");
			e.printStackTrace();
		}

	}

	private void setCurrentFile(IFile file) {
		this._currentFile = file;
		if (_firstScriptFile && _eclipse) { // we create the temporary folder
											// the first time a script is called
			createTemporaryPath();
			_firstScriptFile = !_firstScriptFile;
		}

	}

	/**
	 * @return the currentFile
	 */
	public IFile getCurrentFile() {
		return _currentFile;
	}

	public boolean isEclipseInteractiveMode() {
		return _eclipse && isInteractiveMode();
	}

	public boolean isInteractiveMode() {
		return _executionMode == ExecutionMode.INTERACTIVE;
	}

	public final String getTemporaryPath() {

		java.net.URI folder = URIEclipseExtractor
				.getProjectPath(getCurrentFile());
		_LOGGER.debug("FMShell.getTemporaryPath()");
		_LOGGER.debug("folder: " + folder);
		_LOGGER.debug(
				"folder (path): " + folder.getPath());
		// return folder.toString();

		return folder + FILE_SEPARATOR + TMP_FOLDER + FILE_SEPARATOR;
	}

	public boolean isVerbose() {
		return _verbose;
	}

	public void setRunnableMode(boolean b) {
		_runningScript = b;

	}

	public boolean isRunningScript() {
		return _runningScript;
	}

	public void printTODO(String string) {
		setError("(TODO) " + string);
	}

	public boolean isStepByStep() {
		return _stepByStep;
	}

	public void setStepByStep(boolean step) {
		_stepByStep = step;
	}

	public IFile getOutputPath() {

		if (_eclipse) {
			IFile scriptLocation = getCurrentFile();
			IProject project = scriptLocation.getProject();
			IFolder folder = project.getFolder(OUTPUT_FOLDER);
			try {
				if (!folder.exists())
					folder.create(false, true, null);
				else
					_LOGGER.debug(
							"output folder already exists");
			} catch (CoreException e) {
				FMLShell.getInstance().setError(
						"Unable to get the output path "
								+ e.getLocalizedMessage());
				return null;
			}

			URI uri = folder.getLocationURI();
			_LOGGER.debug(
					"URI folder of outputpath = " + uri);
			IFile file = URIUtils.getIFileFromURI(uri);

			return file;
		} else {
			return null; // new File("");
		}

	}

	public boolean isEclipseBased() {
		return _eclipse;
	}

	@SuppressWarnings("unchecked")
	public void setVerbose(boolean verbose) {
		_verbose = verbose;
		//PropertyConfigurator.configure("log4j.properties");
		if (_verbose) {			
			Logger.getRootLogger().setLevel(Level.DEBUG);
		}
		else {
			Logger.getRootLogger().setLevel(Level.ERROR);
		}
		/*
		if (_verbose) {
			Enumeration<Logger> eLoggers = LogManager.getCurrentLoggers() ;
			while (eLoggers.hasMoreElements()) {
				Logger l = eLoggers.nextElement() ; 
				// yeah, Xtext relies also on Log4J so that the verbosing information can be massively huge 
				// FIXME should be revised since the Xtext exclusion is not comprehensive (getCurrentLoggers() is at a given moment)
				if (!l.getName().startsWith("org.eclipse.xtext.")) 
					l.setLevel(Level.DEBUG);	
			}
			Logger.getRootLogger().setLevel(Level.DEBUG);
		}*/

	}

	public boolean isStandalone() {
		return !isEclipseBased();
	}

	/**
	 * @return whether or not shell execution has lead to errors
	 */
	public boolean hasErrors() {
		return _errors.size() > 0;
	}

	/**
	 * @return whether or not shell execution has lead to fatal errors
	 */
	public boolean hasFatalErrors() {
		return _fatalErrors.size() > 0;
	}

	/**
	 * @param error
	 *            e.g., happens when there is a parsing error
	 */
	public void setFatalError(String error) {
		_fatalErrors.add(error);
		setError(error);
		// close();
	}

	/**
	 * @return
	 */
	public List<String> getFatalErrors() {
		return _fatalErrors;
	}

	/**
	 * @param error
	 *            e.g., happens when there is an assertion error
	 */
	public void setAssertionError(String error) {
		_assertionErrors.add(error);
		setError(error);
		// close();
	}

	/**
	 * @return assertion errors
	 */
	public List<String> getAssertionErrors() {
		return _assertionErrors;
	}

	/**
	 * @return whether or not shell execution has lead to assertion errors
	 */
	public boolean hasAssertionErrors() {
		return _assertionErrors.size() > 0;
	}

	/**
	 * @return the output directory of FAMILIAR in standalone mode (works with
	 *         File, not IFile)
	 */
	public File getStandaloneOutputPath() {
		File fileOutput = new File(OUTPUT_FOLDER);
		if (!(fileOutput.exists()))
			fileOutput.mkdir();

		assert (fileOutput.exists() && fileOutput.isDirectory());

		return fileOutput;
	}

	/**
	 * Reset the shell
	 */
	public void reset() {
		init(); // reinitialize various things like errors handling
		_currentEnv.clear();
		_tracer.reset();
	}

	public void setCountingStrategy(CountingStrategy countingStrategy) {
		_countingStrategy = countingStrategy;
	}

	public CountingStrategy getCountingStrategy() {
		return _countingStrategy;
	}

	public String getHistory() {
		StringBuilder sb = new StringBuilder();
		List<String> traces = _tracer.getTraces();

		for (String trace : traces) {
			sb.append(trace + "\n");
		}

		return sb.toString();

	}

	public ComparisonStrategy getComparisonStrategy() {
		return _comparisonStrategy;
	}

	public void setComparisonStrategy(ComparisonStrategy cmpStrategy) {
		_comparisonStrategy = cmpStrategy;
	}

	public BDDStrategy getBDDStrategy() {
		return _bddStrategy;
	}

	public void setBDDStrategy(BDDStrategy bddStrategy) {
		_bddStrategy = bddStrategy;

	}

	public FMLPreference getPreference() {
		return new FMLPreference();
	}

	// TODO move in FMLPreference (it is rather "default" strategy")
	public SatisfiableStrategy getSatisfiableStrategy() {
		return _satisfiableStrategy ; 
	}

}
