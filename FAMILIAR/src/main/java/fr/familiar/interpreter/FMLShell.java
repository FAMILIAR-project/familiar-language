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
package fr.familiar.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.SatisfiableStrategy;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.parser.WildCardVariable;
import fr.familiar.utils.AmbigousFileNameException;
import fr.familiar.utils.FileListing;
import fr.familiar.variable.Variable;

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

	private static final String OUTPUT_FOLDER = "output";

	private static final boolean DEFAULT_VERBOSE = false;
	private boolean _verbose = DEFAULT_VERBOSE;

	public static final String FML_VERSION = "1.2 (beta)";

	/*
	 * output facilities (display messages, debug messages, etc.)
	 */
	private Output _output;

	/*
	 * input stream of the shell (file stream, input from the user, etc.)
	 */
	private InputStream _input = null;

	private BufferedReader _reader;

	private static FMLShell _INSTANCE = null;
	private List<File> _paths;
	private FileListing _lst = null;

	/**
	 * FML environment: management of variables, parsing facilities, etc.
	 */
	private FMLCommandInterpreter _currentEnv;

	public static final boolean LINE_BY_LINE = false;

	private ExecutionMode _executionMode = ExecutionMode.NON_INTERACTIVE;

	// mode: control, warning, nothing
	private ErrorMode _errorMode = ErrorMode.NORMAL;

	private FMLInterpreterTracer _tracer;

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
	private static final ComparisonStrategy DEFAULT_CMP_STRATEGY = ComparisonStrategy.BDD;
	private ComparisonStrategy _comparisonStrategy = DEFAULT_CMP_STRATEGY;

	/**
	 * BDD construction strategy (SPLOT or FAMILIAR)
	 */
	private static final BDDStrategy DEFAULT_BDD_STRATEGY = BDDStrategy.FML;
	private BDDStrategy _bddStrategy = DEFAULT_BDD_STRATEGY;

	/*
	 * instantiate an FML interpreter in a standalone environment
	 */
	public static FMLShell instantiateStandalone(InputStream in) {
		FMLShell shell = new FMLShell(in, new DefaultOutput());
		shell.addPath(new File(System.getProperty("user.dir")));
		return shell;
	}

	public FMLShell(InputStream input, Output output) {
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
	 * Close loggers of third party libraries
	 */
	private void closeLogger() {
		// Placeholder for logger configuration
	}

	// Prints a 'info' message to the specified console.
	public void printInfoMessage(java.lang.String info) {
		_output.println("Info: " + info);
	}

	// Prints a 'cmd' message to the specified console.
	public void printInfoCmd(java.lang.String info) {
		printDebugMessage("(Info cmd) " + info);
	}

	// Prints a prompt to the specified console.
	public void printPrompt() {
		_output.print(PROMPT + " ");
	}

	private void runPrompt() {
		if (_input == null) {
			printDebugMessage("No input.");
			return;
		}

		printDebugMessage("Loading prompt.");

		_reader = new BufferedReader(new InputStreamReader(_input));

		while (true) {
			try {
				if (!_reader.ready()) {
					switchToInterativeMode();
					return;
				}

				String cmd = null;
				String sbuffer = "";
				while (((cmd = _reader.readLine()) != null)) {
					sbuffer += cmd + System.getProperty("line.separator");
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

		if (_executionMode == ExecutionMode.NON_INTERACTIVE) {
			if (isVerbose())
				FMLShell.getInstance().printDisplay(_currentEnv.allVariablesToString());
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
			}
			_output.close();
			if (_input != null)
				_input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void launch() {
		_LOGGER.debug("Launching FAMILIAR");
		runPrompt();
	}

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
	 * @param currentEnv the currentEnv to set
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
		_executionMode = ExecutionMode.NON_INTERACTIVE;
	}

	public void switchToInterativeMode() {
		setToInteractiveMode();
		printFMLHeader();
		printPrompt();
		InputStream input = System.in;

		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			isr = new InputStreamReader(input);
			br = new BufferedReader(isr);

			while (true) {
				String s = br.readLine();
				if (s == null) {
					break;
				}

				_LOGGER.debug("FAMILIAR: " + s);
				FMLShell.getInstance().parse(s);
				FMLShell.getInstance().printPrompt();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (isr != null) isr.close();
			} catch (IOException e) {
				e.printStackTrace();
				_LOGGER.debug("Unable to close");
			}
		}
	}

	public void printFMLHeader() {
		_output.println("FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) "
				+ " version " + FML_VERSION);
		_output.println("http://familiar-project.github.com/");
	}

	/*
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
		Variable v = _currentEnv.parseCommand(cmd, NSFactory.mkEmpty(),
				new ArrayList<Variable>());

		_tracer.registerCommand(cmd);
		if (v != null)
			v.setShell(this);
		return v;
	}

	public boolean isVerbose() {
		return _verbose;
	}

	public void printTODO(String string) {
		setError("(TODO) " + string);
	}

	public boolean isEclipseBased() {
		return false;
	}

	public boolean isStandalone() {
		return true;
	}

	public boolean isInteractiveMode() {
		return _executionMode == ExecutionMode.INTERACTIVE;
	}

	/**
	 * @return false - Eclipse interactive mode is not supported in standalone
	 */
	public boolean isEclipseInteractiveMode() {
		return false;
	}

	/**
	 * @return false - step-by-step mode is not supported in standalone
	 */
	public boolean isStepByStep() {
		return false;
	}

	@SuppressWarnings("unchecked")
	public void setVerbose(boolean verbose) {
		_verbose = verbose;
		if (_verbose) {
			Logger.getRootLogger().setLevel(Level.DEBUG);
		} else {
			Logger.getRootLogger().setLevel(Level.ERROR);
		}
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
	 * @param error e.g., happens when there is a parsing error
	 */
	public void setFatalError(String error) {
		_fatalErrors.add(error);
		setError(error);
	}

	/**
	 * @return fatal errors
	 */
	public List<String> getFatalErrors() {
		return _fatalErrors;
	}

	/**
	 * @param error e.g., happens when there is an assertion error
	 */
	public void setAssertionError(String error) {
		_assertionErrors.add(error);
		setError(error);
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
	 * @return the output directory of FAMILIAR in standalone mode
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
		init();
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

	public SatisfiableStrategy getSatisfiableStrategy() {
		return _satisfiableStrategy;
	}

	private boolean _runnableMode = false;

	/**
	 * @param b set runnable mode
	 */
	public void setRunnableMode(boolean b) {
		_runnableMode = b;
	}

	/**
	 * @return whether a script is currently running
	 */
	public boolean isRunningScript() {
		return _runnableMode;
	}
}
