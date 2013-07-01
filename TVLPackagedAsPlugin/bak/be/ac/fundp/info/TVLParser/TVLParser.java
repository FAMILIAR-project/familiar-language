package be.ac.fundp.info.TVLParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import org.sat4j.specs.TimeoutException;

import be.ac.fundp.info.TVLParser.Parser.Lexer;
import be.ac.fundp.info.TVLParser.Parser.Parser;
import be.ac.fundp.info.TVLParser.SyntaxTree.Model;
import be.ac.fundp.info.TVLParser.Util.BooleanForm;
import be.ac.fundp.info.TVLParser.Util.FileLineReader;
import be.ac.fundp.info.TVLParser.Util.IDGenerator;
import be.ac.fundp.info.TVLParser.Util.NormalForm;
import be.ac.fundp.info.TVLParser.Util.Solver;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.ConstantsSymbolTable;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;
import be.ac.fundp.info.TVLParser.symbolTables.TypesSymbolTable;

/**
 * This class is the main class of the TVL Parser, it contains different methods
 * to :
 * - Parse a .TVL file and to get information about the feature model that this
 * file contains.
 * - Create the normal form of the feature model contained in the input file and
 * to get
 * information about this form.
 * - Create the boolean form of the feature model contained in the input file
 * and to get
 * information about this form.
 * - Compute operations (satisfiability, number of solutions,...) about the
 * feature model described
 * into the input file.
 * 
 * If you want to use TVL Parser as a library of you own software, you have to
 * use this class.
 */
public class TVLParser {

	// The content of "inputFile" with all the includes resolved.
	protected String input;

	// Show if the the run() method has been launched.
	protected boolean hasRun;

	// Show if the solver has compute operations about the FM.
	protected boolean hasRunSolver;

	// Show if "inputFile" contains includes.
	protected boolean hasIncludes;

	// Show if "inputFile" contains includes.
	protected int nbIncludes;

	// If an error has been thrown during the parsing, it will be recorded in
	// this variable.
	protected Exception syntacticalException;

	// If an error has been thrown during the type checking, it will be recorded
	// in this variable.
	protected Exception typeException;

	// If an error has been thrown during the feature model solving, it will be
	// recorded in this variable.
	protected Exception solverException;

	// Show if the feature model contained in "inputs" is valid or not. The
	// variable only makes sense
	// if "hasRun" is true.
	boolean isValid;

	// The feature symbol table resulting from the type checking of "inputs".
	protected FeaturesSymbolTable featuresSymbolTable;

	// Show if the parsing of the normal form of the feature has been launched
	// with no exceptions thrown.
	protected boolean hasRunNormalizedFormParser;

	// Show if the parsing of the boolean form of the feature model has been
	// launched with no exceptions thrown.
	protected boolean hasRunBooleanFormParser;

	// Contain the normalized version of the feature model contains in "input"
	protected String normalizedFormText;

	// Contain the boolean version of the feature model contains in "input"
	protected String booleanFormText;

	// The TVL Parser of the normal form of the feature model contained in
	// "input".
	protected TVLParser normalizedFormParser;

	// The TVL Parser of the boolean form of the feature model contained in
	// "input".
	protected TVLParser booleanFormParser;

	// The SAT4J solver which will be used to compute operations about the
	// boolean form (if this form exists)
	protected Solver solver;

	protected NormalForm nf;
	protected BooleanForm bf;
	
	// defines if we use the debug version of the parse() method to gather some trace info
	protected boolean do_debug_parse;

	/**
	 * The class constructor, it takes one input file and resolve all its
	 * includes.
	 * The content of the file with all the includes resolved is saved in
	 * "input". Be careful,
	 * this construct doesn't parse "inputFile". For parse a file, you have to
	 * use the run()
	 * method.
	 * 
	 * This constructor also display the new content of "input".
	 * 
	 * @param inputFile
	 *            The .tvl file which will be parsed.
	 * 
	 * @throws FileNotFoundException
	 *             If the file corresponding to "inputFile" has not been found.
	 */
	public TVLParser(File inputFile) throws FileNotFoundException {
		//this.do_debug_parse= true;
		if (inputFile.exists() && inputFile.isFile() && inputFile.canRead()) {
			String inputFileBasePath = inputFile.getAbsolutePath();
			inputFileBasePath = inputFileBasePath.substring(0, inputFileBasePath.length() - inputFile.getName().length());
			if (inputFileBasePath.endsWith("/")) {
				inputFileBasePath = inputFileBasePath.substring(0, inputFileBasePath.length() - 1);
			}
						
			try {
				StringBuilder sb = readInputsContent(inputFile, inputFileBasePath);
				this.input = sb.toString();				
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("File '" + inputFile.getPath() + "' not found");
		}
	}

	/**
	 * Another class constructor which take a string in input and not a file.
	 * "inputDiagram" can't contain includes. Otherwise, the Parser will crash.
	 * Currently, this constructor is only used to create a Parser for the
	 * normal form
	 * or for the boolean form of the feature model.
	 * 
	 * 
	 * @param inputTextDiagram
	 *            A String containing the feature diagram which will be parsed.
	 */
	public TVLParser(String inputDiagram) {
		this.input = inputDiagram;
	}

	/**
	 * Reads the input file and replaces all includes by their content.
	 * 
	 * @param f
	 *            The input file (a TVL file..).
	 * @param basePath
	 *            Used as base for all relative include paths inside the TVL
	 *            file, WITHOUT trailing slash!
	 * @return The content of the input file with all includes replaced by their
	 *         content.
	 * @throws FileNotFoundException
	 *             in case the file does not exist.
	 */
	private StringBuilder readInputsContent(File f, String basePath) throws IOException {
		String includePath;
		File includeFile;		
		FileLineReader inputFileContent;
		final String LINE_SEP = System.getProperty("line.separator");
		final long fileSize= f.length() + 1;		
		StringBuilder result;

		try {
			inputFileContent = new FileLineReader(f);
			if (fileSize >= Integer.MAX_VALUE) {
				result= new StringBuilder(Integer.MAX_VALUE);
			} else {
				result= new StringBuilder((int)fileSize);
			}
		}
		catch(Exception ex) {			
			throw new IOException(ex.getMessage()); //should probably be "not readable" exception
		}

		for (String line : inputFileContent) {	
			while (line.indexOf("include(") != -1 && line.indexOf(");", line.indexOf("include(")) != -1) {				
				result.append(line.substring(0, line.indexOf("include("))); // The text before the include				

				includePath = line.substring(line.indexOf("include(") + 8, line.indexOf(");", line.indexOf("include(")));
				if (!includePath.startsWith("/")) {
					if (includePath.startsWith("./")) {
						includePath = basePath + includePath.substring(1);
					} else {
						includePath = basePath + "/" + includePath;
					}
				}

				includeFile = new File(includePath);
				if (includeFile.exists() && includeFile.isFile() && includeFile.canRead()) {
					this.hasIncludes = true;
					this.nbIncludes++;
					result.append(readInputsContent(includeFile, basePath));
				} else {
					System.out.println("Error: The instruction include(" + includePath + ") refers to an inexisting file.");
				}

				if (line.length() > line.indexOf(");", line.indexOf("include(")) + 2) {
					line = line.substring(line.indexOf(");", line.indexOf("include(")) + 2); // The text before the includes closing )
				} else {
					line = "";
				}

			}

			// No include in this line
			result.append(line);
			result.append(LINE_SEP);
		}
		try {
			inputFileContent.Close();
		}
		catch(Exception ex) 
		{
			throw new IOException("Can't close FileLineReader instance.");
		}

		return result;
	}

	/**
	 * Launch the parsing of the input file. If an error is thrown, it will be
	 * recorded in "syntacticalException"
	 * or in "typeException" and the parsing will be interrupted.
	 */
	public void run() {
		if (!this.hasRun) {
			this.hasRun = true;

			final Lexer lexer_obj = new Lexer(new StringReader(this.input));
			Parser parser_obj = new Parser(lexer_obj); /* open input files, etc. here */			
			Symbol parse_tree = null;			

			try {
				if (this.do_debug_parse) {
					parse_tree = parser_obj.debug_parse();
				} else {
					parse_tree = parser_obj.parse();
				}
			} catch (Exception e) {
				this.syntacticalException = e;
			}

			if (parse_tree != null) {
				Model model = (Model) parse_tree.value;
				try {
					TypesSymbolTable typesSymbolTable = new TypesSymbolTable(model.getTypes());
					parser_obj.getFeaturesSymbolTable().launchConstruction(model.getFeatures(), typesSymbolTable,
							new ConstantsSymbolTable(model.getConstants(), typesSymbolTable));
					this.featuresSymbolTable = parser_obj.getFeaturesSymbolTable();
					this.isValid = true;
				} catch (Exception e) {
					this.typeException = e;
				}
			}
		}
	}

	/**
	 * Display information about the parsing of the input file. If the feature
	 * model contained in the file
	 * is valid, it print information about the number of features, the
	 * attributes types,...Otherwise, it
	 * shows that feature model is invalid and print information about the
	 * exception which has been thrown
	 * during the parsing or during the type checking.
	 */
	public void printInfo() {
		if (!this.hasRun) {
			this.run();
		}

		if (!this.isValid) {
			System.out.print("Feature model invalid, ");

			if (!this.isSyntacticallyCorrect()) {
				System.out.println("syntax error:");
				System.out.println(" '" + this.syntacticalException.getMessage() + "'");
				System.out.println("Stack trace:");
				syntacticalException.printStackTrace(System.out);
			} else if (!this.isCorrectlyTyped()) {
				System.out.println("type error:");
				System.out.println(" '" + this.typeException.getMessage() + "'");
				System.out.println("Stack trace:");
				typeException.printStackTrace(System.out);
			}

		} else {
			System.out.println("Feature model valid");
			System.out.println("  #features   = " + featuresSymbolTable.getNBFeatures());
			if (this.hasIncludes)
				System.out.println("  #includes   = " + this.nbIncludes);
			if (this.hasTypes())
				System.out.println("  #types      = " + this.getNbTypes());
			if (this.hasConstants())
				System.out.println("  #constants  = " + this.getNbConstants());
			if (this.hasAttributes()) {
				System.out.println("  #attributes = " + this.getNbAttributes() + ", type(s):");
				if (this.hasInt())
					System.out.println("     #int     = " + this.getNbIntAttributes());
				if (this.hasReal())
					System.out.println("     #real    = " + this.getNbRealAttributes());
				if (this.hasBool())
					System.out.println("     #bool    = " + this.getNbBoolAttributes());
				if (this.hasEnum())
					System.out.println("     #enum    = " + this.getNbEnumAttributes() + ", with " + this.getNbEnumValues()
							+ " enum values");
				if (this.hasStruct())
					System.out.println("     #struct  = " + this.getNbStructAttributes());
			}
		}
	}
	
	/**
	 * Check if the input Model passed to the Parser is Valid, 
	 * if not throws the exception encountered by TVLParser during the parsing and validation of the input
	 */
	public void validate() throws Exception{
		if(!this.hasRun) {
			this.run();
		}
		
		if (!this.isValid) {
			if (!this.isSyntacticallyCorrect()) {
				throw this.syntacticalException;
			} else if (!this.isCorrectlyTyped()) {
				throw this.typeException;
			} else {
				throw new Exception("Invalid Feature Model!");
			}
		}
	}
	
	

	public void report() {
		if(!this.hasRun) {
			this.run();
		}
		if(!this.isValid) {
			System.out.print("Invalid Feature Model!");
		} else {			
			featuresSymbolTable.printTable();
		}
	}
	
	/**
	 * If the feature model described in the input file is valid, it generates
	 * the normal form of
	 * this feature model and starts to parse this new form. If no exception is
	 * thrown, the .TVL
	 * corresponding to the normal form is saved in the String
	 * "normalizedFormText" and the Parser
	 * which has parsed this .TVL is saved in "normalizedFormParser".
	 */
	public void runNormalizedFormParser() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			if (!this.hasRunNormalizedFormParser) {
				try {
					nf = new NormalForm(this.featuresSymbolTable);
					this.normalizedFormText = nf.getRootFeature().toString("");
					System.out.println(this.normalizedFormText);
					this.normalizedFormParser = new TVLParser(this.normalizedFormText);
					this.normalizedFormParser.run();
					this.hasRunNormalizedFormParser = true;
				} catch (Exception e) {
					System.err.println("There has been an error during the construction of the normal form.");
					e.printStackTrace();
				}
			}
		}
	}

	protected NormalForm getNormalFormInternal() {
		if (!hasRunNormalizedFormParser) {
			runNormalizedFormParser();
		}
		return nf;
	}

	/**
	 * Get the normalised form of the model.
	 */
	public String getNormalForm() {
		if (!this.hasRunNormalizedFormParser) {
			this.runNormalizedFormParser();
		}
		if (this.hasRunNormalizedFormParser) {
			return this.normalizedFormText;
		}
		return "";
	}

	/**
	 * If the normal form of the feature model described in the input file is
	 * valid, it generates the boolean
	 * form of this feature model and starts to parse this new form. If no
	 * exception is thrown, the .TVL
	 * corresponding to the boolean form is saved in the String
	 * "booleanFormText" and the Parser
	 * which has parsed this .TVL is saved in "booleanFormParser".
	 */
	public void runBooleanFormParser() {
		if (!this.hasRun) {
			this.run();
		}
		if (!this.hasRunNormalizedFormParser) {
			this.runNormalizedFormParser();
		}
		if (this.isValid && this.normalizedFormParser.isValid) {
			if (!(this.normalizedFormParser.hasInt() || this.normalizedFormParser.hasReal())) {
				if (!this.hasRunBooleanFormParser) {
					try {
						bf = new BooleanForm(this.normalizedFormParser.featuresSymbolTable);
						this.booleanFormText = bf.getRootFeature().toString("");
						this.booleanFormParser = new TVLParser(this.booleanFormText);
						this.booleanFormParser.run();
						this.hasRunBooleanFormParser = true;
					} catch (Exception e) {
						System.err.println("There has been an error during the building of the boolean form.");
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Gets the boolean form of the model.
	 */
	public String getBooleanForm() {
		if (!this.hasRunBooleanFormParser) {
			this.runBooleanFormParser();
		}
		if (this.hasRunBooleanFormParser) {
			return this.booleanFormText;
		}
		return "";
	}

	protected BooleanForm getBooleanFormInternal() {
		if (!this.hasRunBooleanFormParser) {
			this.runBooleanFormParser();
		}
		return this.bf;
	}

	/**
	 * If the boolean form of the FM exists and is valid, try to initialize the
	 * solver with it.
	 */
	public void runSolver() {
		if (!this.hasRunSolver) {
			FeatureSymbol root = getRoot();
			if (root != null) {
				try {
					this.solver = new Solver(root);
					this.hasRunSolver = true;
				} catch (Exception e) {
					this.solverException = e;
				}
			}
		}
	}

	/**
	 * If the boolean form of the FM exists and is valid, try to initialize the
	 * solver with it.
	 */
	public void runSolver(Integer timeout) {
		if (!this.hasRunSolver) {
			FeatureSymbol root = getRoot();
			if (root != null) {
				try {
					this.solver = new Solver(root, timeout);
					this.hasRunSolver = true;
				} catch (Exception e) {
					this.solverException = e;
				}
			}
		}
	}

	public FeaturesSymbolTable getFeaturesSymbolTable() {
		return featuresSymbolTable;
	}

	public FeatureSymbol getFeatureSymbol(String path) throws AmbiguousReferenceException, SymbolNotFoundException,
			ChildrenFeatureNotFoundException {
		return featuresSymbolTable.getFeatureSymbol(path);
	}

	/**
	 * Returns the root of the boolean form.
	 */
	public FeatureSymbol getRoot() {
		if (!this.hasRunBooleanFormParser) {
			this.runBooleanFormParser();
		}
		if (this.hasRunBooleanFormParser) {
			if (this.booleanFormParser.isValid) {
				try {
					return this.booleanFormParser.featuresSymbolTable.getFeatureSymbol(booleanFormParser.featuresSymbolTable
							.getRootFeatureID());
				} catch (AmbiguousReferenceException e) {
					e.printStackTrace();
				} catch (SymbolNotFoundException e) {
					e.printStackTrace();
				} catch (ChildrenFeatureNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Checks whether the model can be transformed into boolean form.
	 */
	public boolean isBoolean() {
		return !this.hasInt() && !this.hasReal();
	}

	/**
	 * Checks whether the model is satisfiable.
	 * 
	 * @throws TimeoutException
	 *             This method calls Sat4J, so it might timeout.
	 */
	public boolean isSatisfiable() throws TimeoutException {
		if (!this.hasRunSolver) {
			this.runSolver();
		}
		if (this.hasRunSolver) {
			return this.solver.isSatisfiable();
		}
		return false;
	}

	/**
	 * Returns a list of solutions or NULL if not satisfiable.
	 * To print a solution in textual form, use
	 * "Util.toTextualIDNumericalSolution(solutions[i]));"
	 * 
	 * @throws TimeoutException
	 *             This method calls Sat4J, so it might timeout.
	 */
	public int[][] getSolutions() throws TimeoutException {
		if (!this.hasRunSolver) {
			this.runSolver();
		}
		if (this.hasRunSolver) {
			if (!this.solver.isSatisfiable()) {
				return null;
			}
			return this.solver.getAllSolutions();
		}
		return null;
	}

	/**
	 * @return true if the feature model contained in the input file is valid.
	 */
	public boolean isValid() {
		if (!this.hasRun) {
			this.run();
		}
		return this.isValid;
	}

	/**
	 * @return If the input file could be parsed (no reference checks, type
	 *         checks, ...)
	 */
	public boolean isSyntacticallyCorrect() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.syntacticalException == null) {
			return true;
		} 
		return false;		
	}

	/**
	 * @return If there are no type errors.
	 */
	public boolean isCorrectlyTyped() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.typeException == null) {
			return true;
		} 
		return false;		
	}

	/**
	 * 
	 * @return null if no exception has been thrown during the parsing.
	 *         Otherwise, it returns
	 *         the exception which has been thrown.
	 */
	public Exception getSyntaxError() {
		if (!this.hasRun) {
			this.run();
		}
		return this.syntacticalException;
	}

	/**
	 * 
	 * @return null if no exception has been thrown during the type checking.
	 *         Otherwise, it returns
	 *         the exception which has been thrown.
	 */
	public Exception getTypeError() {
		if (!this.hasRun) {
			this.run();
		}
		return this.typeException;
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return Are there types declared in the input file?
	 * 
	 */
	public boolean hasTypes() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return (!featuresSymbolTable.getTypesSymbolTable().isEmpty());
		}
		return false;		
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return Are there constants declared in the input file?
	 */
	public boolean hasConstants() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return (!featuresSymbolTable.getConstantsSymbolTable().isEmpty());		
		}
		return false;		
	}

	/**
	 * @return Are there includes in the input file?
	 */
	public boolean hasIncludes() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return this.hasIncludes;
		} 
		return false;		
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return true if the feature model contains int attributes (unused types
	 *         do not count here).
	 */
	public boolean hasInt() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.hasInt();
		} 
		return false;		
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return true if the feature model contains real attributes (unused types
	 *         do not count here).
	 */
	public boolean hasReal() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.hasReal();
		}
		return false;		
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return true if the feature model contains int attributes (unused types
	 *         do not count here).
	 */
	public boolean hasBool() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.hasBool();
		}
		return false;
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return true if the feature model contains enum attributes (unused types
	 *         do not count here).
	 */
	public boolean hasEnum() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.hasEnum();
		}		
		return false;		
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return true if the feature model contains struct attributes (unused
	 *         types do not count here).
	 */
	public boolean hasStruct() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.hasStruct();
		}
		return false;
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return Are there attributes in the feature model (unused types do not
	 *         count here).
	 */
	public boolean hasAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return featuresSymbolTable.hasAttributes();
	}

	/**
	 * Be careful, the result of this method has no sense if the feature model
	 * described in the input file is not valid.
	 * 
	 * @return The number of features that the feature model contains.
	 */
	public int nbFeatures() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.isValid) {
			return featuresSymbolTable.getNBFeatures();
		}
		return 0;
	}

	/**
	 * Generates and fills in two files: one containing the mapping between the
	 * features and their numerical ID,
	 * and one containing the clauses in the DIMACS format (in CNF).
	 * 
	 * @param mapPath
	 *            is the absolute or relative path of the file which will
	 *            contain the mapping.
	 * @param clausesPath
	 *            is the absolute or relative path of the file which will
	 *            contain the DIMACS clauses..
	 */
	public void saveDimacsFormat(String mapPath, String clausesPath) {
		if (!this.hasRun) {
			this.run();
		}
		if (!this.hasRunSolver) {
			this.runSolver();
		}
		int varsNbr = IDGenerator.getInstance().storeDictionnary(mapPath);
		if (varsNbr < 0) {
			System.exit(1);
		}
		this.solver.storeDimacsClauses(clausesPath, varsNbr);
	}

	public int getNbTypes() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.hasTypes()) {
			return this.featuresSymbolTable.getTypesSymbolTable().getTable().size();
		}		
		return 0;
	}

	public int getNbConstants() {
		if (!this.hasRun) {
			this.run();
		}
		if (this.hasConstants()) {
			return this.featuresSymbolTable.getConstantsSymbolTable().getTable().size();
		}		
		return 0;
	}

	public int getNbAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return (this.featuresSymbolTable.getNbBoolAttributes() + this.featuresSymbolTable.getNbEnumAttributes()
				+ this.featuresSymbolTable.getNbIntAttributes() + this.featuresSymbolTable.getNbRealAttributes() + this.featuresSymbolTable
				.getNbStructAttributes());
	}

	public int getNbIntAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbIntAttributes();
	}

	public int getNbRealAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbRealAttributes();
	}

	public int getNbBoolAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbBoolAttributes();
	}

	public int getNbStructAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbStructAttributes();
	}

	public int getNbEnumAttributes() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbEnumAttributes();
	}

	public int getNbEnumValues() {
		if (!this.hasRun) {
			this.run();
		}
		return this.featuresSymbolTable.getNbEnumValues();
	}
}