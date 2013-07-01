package be.ac.fundp.info.TVLParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import org.sat4j.specs.TimeoutException;

import be.ac.fundp.info.TVLParser.Util.NormalForm;
import be.ac.fundp.info.TVLParser.Util.STPTranslator;
import be.ac.fundp.info.TVLParser.Util.Util;
//import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
//import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
//import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeaturesGroupAlreadySpecifiedException;
//import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

/**
 * This class allows transforms the library into a command line tool.
 */
public class Launcher {
	
	public static void main (String[] args) throws Exception{
		final String usage = 	
						"Usage: java -jar TVLParser.jar [option] file.tvl\n" +
						" with option being one of:\n" +
						"  -s       perform syntactic checks and print info; the default option,\n" +
						"  -c       print the number of features,\n" +
						"  -sat     perform satisfiability check,\n" +
						"  -prods   list products (may contain duplicates due to differences in \n" +
						"           dummy variables),\n" +
						"  -uprods  list products but filter out duplicates (less efficient),\n" +
						"  -nf      normalise model (prints normalised TVL code on stdout),\n" +
						"  -nfout file \n" + 
						"           prints normalised TVL code to file,\n" +
						"  -bf      normalise and make boolean (prints normalised, boolean-only, \n" +
						"           CNF-only TVL code on stdout),\n" +
						"  -dimacs MapFileName ClauseFileName \n" +
						"           export dimacs variables and clauses into the two files given\n" +
						"           as parameters.\n" +
						" -r        create AST, symbols Table and print their content's report\n" +
						" -cvc CvcFile\n" +
						"           exports the model to a cvc file \n" +
						" Only specify a single option.\n";
		
		if(args.length == 0) {
			System.out.println(usage);
			System.exit(1);
		} else {
			String dimacsMapFile = "";
			String dimacsClauseFile = "";
			String cvcFile = "";
			String nfFile = "";
			boolean noarg = true, dimacs = false, count = false, nf = false, bf = false, syntax = false, prods = false, uprods = false, sat = false, report = false, cvc = false, nfout = false;
			TVLParser parser = null;
			long startTime = System.currentTimeMillis();
			
			for(int i = 0; i < args.length - 1; i++) {
				noarg = false;
				
				if (args[i].equals("-r")){
					report = true;
					break;
				} else if(args[i].equals("-dimacs") || args[i].equals("-d")) {
					dimacs = true;
					if(i + 2 + 2 > args.length) {
						System.out.println("Dimacs file arguments not provided\n"+usage);
						System.exit(1);
					}
					i++;
					dimacsMapFile = args[i];
					i++;
					dimacsClauseFile = args[i];
				} else if(args[i].equals("-c")) {
					count = true;
				} else if(args[i].equals("-nf")) {
					nf = true;
				} else if(args[i].equals("-bf")) {
					bf = true;
				} else if(args[i].equals("-s")) {
					syntax = true;
				} else if(args[i].equals("-sat")) {
					sat = true;
				} else if(args[i].equals("-prods")) {
					prods = true;
				} else if(args[i].equals("-uprods")) {
					prods = true;
					uprods = true;
				} else if(args[i].equals("-cvc")) {
					cvc = true;
					if(i + 1 > args.length) {
						System.out.println("CVC file not provided\n"+usage);
						System.exit(1);
					}
					i++;
					cvcFile = args[i];
				} else if(args[i].equals("-nfout")) {
					nfout = true;
					if(i + 1 > args.length) {
						System.out.println("NF file not provided\n"+usage);
						System.exit(1);
					}
					i++;
					nfFile = args[i];
				} else {
					System.out.println("Unknown option '"+args[i]+"'.\n"+usage);
					System.exit(1);
				}
			}
			
			if(noarg) {
				syntax = true;
			}			
						
			try {
				parser = new TVLParser(new File(args[args.length - 1]));
			} catch(FileNotFoundException e) { 
				System.out.println("The file "+args[args.length - 1]+" could not be found or opened.");
				System.exit(1);
			}
			
			if(parser != null) {
				parser.run();
				if(!parser.isValid) {
					parser.printInfo();
					System.exit(1);
				} else if (report) {
					parser.report();
				} else if(syntax) {
					parser.printInfo();
				} else if(count) {
					System.out.println(parser.nbFeatures());
				} else if(nf) {
					System.out.println(parser.getNormalForm());
				} else if(bf && !parser.isBoolean()) {
					System.out.println("Error, the boolean form can only be generated for models without numeric attributes (int or real).");
					System.exit(1);
				} else if(bf) {
					System.out.println(parser.getBooleanForm());
				} else if(dimacs && !parser.isBoolean()) {
					System.out.println("Error, the dimacs can only be generated for models without numeric attributes (int or real).");
					System.exit(1);
				} else if(dimacs) {
					parser.saveDimacsFormat(dimacsMapFile, dimacsClauseFile);
				} else if(sat || prods) {
					if(!parser.isBoolean()) {
						System.out.println("Error, the library does not yet support numeric attributes.  Only models without numeric attributes (int or real) can be checked for satisfiability.");
						System.exit(1);
					} else {
						try {
							if(!prods) System.out.println(parser.isSatisfiable() ? "Ok, feature model satisfiable." : "Ko, feature model *not* satisfiable.");
							else {
								int[][] solutions = parser.getSolutions();
								if(solutions == null) System.out.println("Feature model *not* satisfiable.");
								else {
									System.out.println("Found "+solutions.length+" products(s); maybe with duplicates.");
									if(!uprods) {
										for(int i = 0; i < solutions.length; i++) {
											System.out.println(" - " + Util.toTextualIDNumericalSolution(solutions[i]));
										}
									} else {
										System.out.println("Caching solutions..");
										String[] solutionStrings = new String[solutions.length];
										for(int i = 0; i < solutions.length; i++) {
											solutionStrings[i] = Util.toTextualIDNumericalSolution(solutions[i]);
										}
										System.out.println("Sorting..");
										Arrays.sort(solutionStrings);
										String last = "";
										int total = 0;
										System.out.println("Printing and counting..");
										for(int i = 0; i < solutionStrings.length; i++) {
											if(!solutionStrings[i].equals(last)) {
												System.out.println(" - " + solutionStrings[i]);
												total++;
											}
											last = solutionStrings[i];
										}
										System.out.println("Found "+total+" unique products.");
									}
								}
							} 
						} catch (TimeoutException e) {
							System.out.println("Error, the Parser timed out.");
							System.exit(1);
						}
					}
				}
				else if(cvc) {
					NormalForm normalForm = new NormalForm(parser.featuresSymbolTable);
						
					FeatureSymbol root = normalForm.getFeaturesSymbolTable().getFeatureSymbol(normalForm.getFeaturesSymbolTable().getRootFeatureID());
					STPTranslator stp = new STPTranslator(root,cvcFile); 
					stp.write();
				}
				else if(nfout) {
					File nfoutfile = new File(nfFile);
					if(nfoutfile.exists()) {
						nfoutfile.delete();
					}
					nfoutfile.createNewFile();
					FileWriter fw = new FileWriter(nfoutfile);
					fw.write(parser.getNormalForm());
					fw.flush();
					fw.close();
				}
			}
			System.out.println("/* Took " + (System.currentTimeMillis() - startTime) + "ms to compute */");
		}
	}
}
