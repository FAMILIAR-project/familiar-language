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
package fr.familiar.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xtext.example.fml.ui.internal.FmlActivator;
import org.xtext.example.mydsl.FmlStandaloneSetup;
import org.xtext.example.mydsl.fml.AddConstraint;
import org.xtext.example.mydsl.fml.AnalysisOperation;
import org.xtext.example.mydsl.fml.Assertion;
import org.xtext.example.mydsl.fml.AtomicConstraintExpr;
import org.xtext.example.mydsl.fml.BoolOperation;
import org.xtext.example.mydsl.fml.BooleanExpr;
import org.xtext.example.mydsl.fml.CTCRCommand;
import org.xtext.example.mydsl.fml.CleanUp;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.Compare;
import org.xtext.example.mydsl.fml.ComparisonOperation;
import org.xtext.example.mydsl.fml.ComplexCommand;
import org.xtext.example.mydsl.fml.ConfigurationCmd;
import org.xtext.example.mydsl.fml.ConfigurationCommand;
import org.xtext.example.mydsl.fml.ConstraintCommand;
import org.xtext.example.mydsl.fml.Convert;
import org.xtext.example.mydsl.fml.CopyVariable;
import org.xtext.example.mydsl.fml.CreateConfiguration;
import org.xtext.example.mydsl.fml.DoubleCommand;
import org.xtext.example.mydsl.fml.Export;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.FMLAbstractCommand;
import org.xtext.example.mydsl.fml.FMLSave;
import org.xtext.example.mydsl.fml.FTCommand;
import org.xtext.example.mydsl.fml.FamiliarScript;
import org.xtext.example.mydsl.fml.FeatureEdgeKind;
import org.xtext.example.mydsl.fml.FeatureOperation;
import org.xtext.example.mydsl.fml.FeatureVariabilityOperator;
import org.xtext.example.mydsl.fml.ForeachSet;
import org.xtext.example.mydsl.fml.GDisplay;
import org.xtext.example.mydsl.fml.GListing;
import org.xtext.example.mydsl.fml.Hidden;
import org.xtext.example.mydsl.fml.IdentifierExpr;
import org.xtext.example.mydsl.fml.IfCondition;
import org.xtext.example.mydsl.fml.Insert;
import org.xtext.example.mydsl.fml.IntegerCommand;
import org.xtext.example.mydsl.fml.IntegerExpr;
import org.xtext.example.mydsl.fml.IntegerOperation;
import org.xtext.example.mydsl.fml.LVidentifier;
import org.xtext.example.mydsl.fml.LoadGeneric;
import org.xtext.example.mydsl.fml.Map;
import org.xtext.example.mydsl.fml.ModifyVOperator;
import org.xtext.example.mydsl.fml.Parameter;
import org.xtext.example.mydsl.fml.PrinterUtility;
import org.xtext.example.mydsl.fml.RemoveConstraint;
import org.xtext.example.mydsl.fml.RemoveFeature;
import org.xtext.example.mydsl.fml.RemoveVariable;
import org.xtext.example.mydsl.fml.RenameFeature;
import org.xtext.example.mydsl.fml.ScriptCommand;
import org.xtext.example.mydsl.fml.ScriptDefinition;
import org.xtext.example.mydsl.fml.SetCard;
import org.xtext.example.mydsl.fml.SetCommand;
import org.xtext.example.mydsl.fml.SetOperation;
import org.xtext.example.mydsl.fml.SetOperations;
import org.xtext.example.mydsl.fml.Shell;
import org.xtext.example.mydsl.fml.StrCommand;
import org.xtext.example.mydsl.fml.StringExpr;
import org.xtext.example.mydsl.fml.StringIndexOf;
import org.xtext.example.mydsl.fml.StringLength;
import org.xtext.example.mydsl.fml.StringOperation;
import org.xtext.example.mydsl.fml.UnMap;
import org.xtext.example.mydsl.fml.VariabilityOpCommand;
import org.xtext.example.mydsl.fml.VariableNull;
import org.xtext.example.mydsl.fml.impl.ComplexCommandImpl;

import com.google.inject.Injector;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.FMLShellConfiguration;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableManager;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.operations.BDDMerger;
import fr.familiar.utils.FileListing;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureName;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.VariabilityOperatorVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;
import fr.familiar.views.featureide.FamiliarRun;
import fr.familiar.views.featureide.VariableView;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 *         starting point for interpreting commands design pattern visitor
 */

public class FMLCommandInterpreter {
	
	private static Logger _LOGGER = Logger.getLogger(FMLCommandInterpreter.class);

	
	private Resource _resource;
	private XtextResourceSet _resourceSet;
	private Injector _injector;

	private NameSpace ns;
	private static BDDMerger _merger = null;

	/**
	 * Manage variables
	 */
	private VariableManager _varManager;

	private Stack<VariableIdentifier> _hiddens;
	private Stack<VariableIdentifier> _exported;
	private boolean _declaration;

	private List<String> _parameters;
	private List<Variable> _parametersToBound; // effective parameters

	/**
	 * current command of the script file
	 */
	private ScriptCommand _currentCmd;

	@SuppressWarnings("restriction")
	public FMLCommandInterpreter(NameSpace ns) {

		this.ns = ns;
		_hiddens = new Stack<VariableIdentifier>();
		_varManager = VariableManager.mkEnvironment();
		_declaration = true;

		_parameters = new ArrayList<String>();
		_parametersToBound = new ArrayList<Variable>();

		// this.injector = new FMMStandaloneSetup()
		// .createInjectorAndDoEMFRegistration();

		_LOGGER.debug("creating FMM injector");

		if (FMLShell.getInstance().isStandalone())
			this._injector = new FmlStandaloneSetup().createInjectorAndDoEMFRegistration();
		else
			// vp: plugin
			this._injector = FmlActivator.getInstance().getInjector("org.xtext.example.mydsl.Fml");
		// FAMILIARActivator.getInstance().getInjector("org.xtext.example.mydsl.FAMILIAR");

		// TODO
		// rebind stdout/stderr to logger
		// avoid printing of GCStats and Resize in JavaBDD
		/*
		 * PrintStream stderr = System.err; PrintStream stdout = System.out;
		 * PrintStream out = null; try { out = new PrintStream(new
		 * BufferedOutputStream( new FileOutputStream("gc.debug")));
		 * System.setErr(out); System.setOut(out); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } merger =
		 * getMerger(); if (out != null) out.close(); System.setErr(stderr);
		 * System.setOut(stdout);
		 */

	}
	
	

	// TODO: handle when a variable does not exist!

	public static BDDBuilder<String> getBuilder() {
		if (_merger == null) {
			_merger = getMerger();
		}
		return _merger.getBuilder(); // merge intersection False .......
	}
	
	public static void setMerger(BDDMerger merger) {
		_merger = merger ;
	}

	public static BDDMerger getMerger() {
		if (_merger == null) {
			// close loggers

			_LOGGER.debug(
					"Closing loggers temporary");
			
			PrintStream output = System.out; // save
			PrintStream errput = System.err; // save
			// try {
			// PrintStream ps = new PrintStream(new FileOutputStream(
			// "/dev/null"));

			// now rebind stdout/stderr to logger

			// @see
			// http://blogs.sun.com/nickstephen/entry/java_redirecting_system_out_and
			java.util.logging.Logger logger;
			LoggingOutputStream los;

			logger = java.util.logging.Logger.getLogger("stdout");
			los = new LoggingOutputStream(logger, java.util.logging.Level.OFF);
			System.setOut(new PrintStream(los, true));

			logger = java.util.logging.Logger.getLogger("stderr");
			los = new LoggingOutputStream(logger, java.util.logging.Level.OFF);
			System.setErr(new PrintStream(los, true));

			// }
			// catch (FileNotFoundException e) {
			// FMLShell.getInstance().printWarning("Unable to desactivate the output during BDD factory building");
			// return null;
			// }

			_merger = new BDDMerger(FMLShellConfiguration.getBDDnodes(),
					FMLShellConfiguration.getBDDcache(),
					FMLShellConfiguration.getBDDvar()); // new BDDMerge();
			System.setOut(output);
			System.setErr(errput);
			_LOGGER.debug("Loggers are back");
		}
		return _merger;
	}

	/******** entry point ************/

	/**
	 * @return the injector
	 */
	public Injector getInjector() {
		return _injector;
	}

	/**
	 * @param injector
	 *            the injector to set
	 */
	public void setInjector(Injector injector) {
		this._injector = injector;
	}

	/**
	 * @param strcmd
	 *            string stream to analyze
	 * @param ns
	 *            current name space
	 * @param paramsVar
	 *            parameter variables (parameters are optional) parses a string
	 *            stream using Xtext facilities
	 */
	public Variable parseCommand(String strcmd, NameSpace ns,
			List<Variable> paramsVar) {

		FMLShell.getInstance().printInfoCmd(strcmd);

		/***** Using Xtext facilities to get a FML model ****/

		if (strcmd == null)
			return null;

		if (_resourceSet == null) {
			_LOGGER.debug("resourseSet creation...");
			_resourceSet = _injector.getInstance(XtextResourceSet.class);
			_LOGGER.debug("resourseSet=" + _resourceSet);
			_resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL,
					Boolean.TRUE);
		}
		
		
		
		_resource = _resourceSet.createResource(URI.createURI("dummy:/example"+ Math.abs(new Random().nextInt())
				+ "." + FileListing.FAMILIAR_EXTENSION));
		if (_resource == null) {
			FMLShell.getInstance().setError("unable to create the resource ");
			return null;
		}
		
		_LOGGER.debug(
				"resourseSet options loaded + resource dummy...");
		_LOGGER.debug("resource=" + _resource);
		
		
		InputStream in = new ByteArrayInputStream(strcmd.getBytes());

		try {
			_resource.load(in, _resourceSet.getLoadOptions());

		} catch (Exception e) {
			FMLShell.getInstance().setFatalError(
					"Unable to load the resource " + e.getMessage());
			return null;
		}
		
		_LOGGER.debug("resourse loaded...");

		if (_resource.getErrors().size() > 0) {
			FMLShell.getInstance().setFatalError(
					"parsing errors " + _resource.getErrors().toString()); // better
			return null;
		}

		else {

			if (_resource.getContents().size() == 0) {
				_LOGGER.debug(
						"resource.getContents().size() == 0 ");
				return null;
			} // no FML command!
			
			 

			FamiliarScript model = (FamiliarScript) _resource.getContents()
					.get(0);
			
			_resource.unload() ; 
			
			_LOGGER.debug("model: " + model);

			/******* set the effective commands which will then be bound *********/
			_parametersToBound = paramsVar;

			EList<Parameter> parameters = model.getParams();
			for (Parameter parameter : parameters) {
				treatParameter(parameter);
			}

			EList<ScriptCommand> cmds = model.getCmds(); // only one command
			// normally

			// treat command
			Variable v = null ;
			for (ScriptCommand cmd : cmds) {
				_LOGGER.debug(
						"parsing: " + cmd.toString());

				_currentCmd = cmd;
				v = parse(cmd); // parse command by command

				/*
				 * in a REPL read-eval-print (interactive mode) we print the
				 * value of the variable
				 */
				if (FMLShell.getInstance().isInteractiveMode() && (v != null)) {
					FMLShell.getInstance().printDisplay(
							v.getIdentifier() + ": (" + v.getType() + ") "
									+ v.getValue()
									+ System.getProperty("line.separator"));
				}

			}
			return v ; 

		}
		
	
		

		
		
	}

	public void checkEnvironmentIntegrity(Command cmd) {
		checkEnvironmentIntegrity(cmd.toString());
	}

	public void checkEnvironmentIntegrity(String cmd) {

		List<Variable> vars = getVariables(); // to iterate
		for (Variable v1 : vars) {
			int appear = 0;
			for (Variable v2 : vars) {
				if (v1.equals(v2)) {
					appear = appear + 1;
				}
			}
			if (appear != 1) {
				// printAllVariables();
				FMLShell.getInstance()
						.setError(
								"command "
										+ cmd
										+ " cannot be executed due to environment integrity: "
										+ "variable " + v1.getValue()
										+ " (val) " + v1.getVid()
										+ " (name) appears " + appear);
				return;
			}
		}

		// TODO: other checkings

		// e.g. the 'type' to the instanceof

	}
	
	/**
	 * @param cmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 * @param varID
	 *            assignment variable (can be null)
	 */           
	public Variable parse(Command cmd, String varID) {
		return parse(cmd, varID, null);
	}

	/**
	 * @param cmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 * @param varID
	 *            assignment variable (can be null)
	 * @param attributeID (can be null)
	 */
	public Variable parse(Command cmd, String varID, String attributeID) {
		
		
		assert (cmd != null);

		FMLAbstractCommandAnalyzer pars = null;

		if (!(cmd instanceof Parameter))
			closeDeclaration();

		// after
		checkEnvironmentIntegrity(cmd); // debug (assertion)

		if (cmd instanceof IdentifierExpr) {
			_LOGGER.debug("identifier: ");
			pars = new IdentifierExprParser(cmd, varID, ns, this);
			pars.parse();
		}

		else if (cmd instanceof IntegerExpr) {
			_LOGGER.debug("integer: ");
			pars = new IntegerExprParser(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof BooleanExpr) {
			_LOGGER.debug("bool: ");
			pars = new BooleanExprParser(cmd, varID, ns, this);
			pars.parse();
		}

		else if (cmd instanceof CopyVariable) {
			pars = new CopyVariableAnalyzer((Command) cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof RemoveVariable) {

			pars = new RemoveVariableAnalyzer((Command) cmd, varID, ns, this);
			pars.parse();
		}

		/********** FM commands (merge, asFM, etc.) **********/
		else if (cmd instanceof FMCommand) {
			Variable v = parseFMCommand(cmd, varID, null);
			//assign(v, varID, attributeID);
			return v ; 
		}

		/********** Configuration command **********/
		else if (cmd instanceof ConfigurationCommand) {
			return parseConfigurationCommand((ConfigurationCommand) cmd, varID);
		}

		// HACK (type hierarchy needs to be revised in the grammar)
		else if (cmd instanceof ConfigurationCmd) {
			pars = new ConfigurationOperationAnalyzer((Command) cmd, varID, ns,
					this);
			pars.parse();
		}

		else if (cmd instanceof SetOperations) { // weird: size
			pars = new SetOperationAnalyzer((Command) cmd, ns, varID, this);
			pars.parse();
		}

		/*************** Feature Operation ***************/
		else if (cmd instanceof FeatureOperation) {
			// TODO: horrible, duplicated with FTCommand
			pars = new FeatureOperationAnalyzer((Command) cmd, varID, ns, this);
			pars.parse();
		}

		/********** Feature command **********/
		else if (cmd instanceof FTCommand) {
			return parseFTCommand((FTCommand) cmd, varID);
		}

	

		/********** Integer command **********/
		else if (cmd instanceof IntegerCommand) {
			return parseIntegerCmd((IntegerCommand) cmd, varID, attributeID);
		}

		/********** Set command **********/
		else if (cmd instanceof SetCommand) {
			return parseSetCommand((SetCommand) cmd, varID);
		}

		/********** String command **********/
		else if (cmd instanceof StrCommand) {
			return parseStringCommand((StrCommand) cmd, varID, attributeID);
		}
		
		else if (cmd instanceof ConstraintCommand) {
			return parseConstraint((ConstraintCommand)cmd, varID); 
		}

		else if (cmd instanceof IntegerOperation) {
			_LOGGER.debug("integer operation ");
			pars = new IntegerOperationParser((IntegerOperation) cmd, varID, ns,
					this); // hack
			pars.parse();

		}

		else if (cmd instanceof ModifyVOperator) {
			pars = new ModifyVOperatorParser((ModifyVOperator) cmd, varID, ns,
					this);
			pars.parse();
		}

		else if (cmd instanceof ScriptDefinition) {
			pars = new ScriptParser(cmd, varID, ns, this);
			pars.parse();
		}

		// load
		else if (cmd instanceof LoadGeneric) {
			pars = new RunParser(cmd, ns, this);
			pars.parse();
		}
		// shell family commands
		else if (cmd instanceof Shell) {
			pars = new ShellAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		// shell family commands
		else if (cmd instanceof CleanUp) {
			pars = new CleanUpAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof UnMap) {
			pars = new UnMapConstraint((Command) cmd, varID, ns, this);
			pars.parse();
		}

		// compare two feature models
		else if (cmd instanceof Compare) {
			pars = new CompareAnalyzer(cmd, varID, ns, this);
			pars.parse();
		}

		// insert
		else if (cmd instanceof Insert) {
			pars = new InsertAnalyzer(cmd, varID, ns, this);
			pars.parse();
		}

		else if (cmd instanceof GListing) {
			pars = new GListingAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof FeatureVariabilityOperator) {

			pars = new FeatureVariabilityOperatorAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof RemoveFeature) {

			pars = new RemoveAnalyzer(cmd, varID, ns, this);
			pars.parse();
		}

		// card, valid?, etc.
		else if (cmd instanceof AnalysisOperation) {
			pars = new ReasoningOperationAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof RenameFeature) {
			pars = new RenameAnalyzer(cmd, varID, ns, this);
			pars.parse();
		}

		/****** control loop ***********/
		// foreach
		else if (cmd instanceof ForeachSet) {
			pars = new ForeachSetAnalyzer(cmd, ns, this);
			pars.parse();
		}

		else if (cmd instanceof IfCondition) {
			pars = new IfConditionAnalyzer(cmd, ns, this);
			pars.parse();
		}

		else if (cmd instanceof FMLSave) {
			pars = new FMLSaveAnalyzer(cmd, ns, varID, this);
			pars.parse();

		}

		/********** DISPLAY Utilities **********/

		else if (cmd instanceof GDisplay) { // only dedicated to graphical
			// displaying

			pars = new GDisplayAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof PrinterUtility) {
			pars = new PrinterAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof Assertion) {

			pars = new AssertAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}
		
		else if (cmd instanceof RemoveConstraint) {

			pars = new RemoveConstraintAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof AddConstraint) {

			pars = new AddConstraintAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}
		
		
		


		else if (cmd instanceof Hidden)
			parseHidden((Hidden) cmd);

		else if (cmd instanceof Export)
			parseExport((Export) cmd);

		// null
		else if (cmd instanceof VariableNull) {

			pars = new VariableNullAnalyzer(cmd, varID, ns, this);
			pars.parse();

		}

		else if (cmd instanceof ComplexCommand) {

			return parse((ComplexCommand) cmd, varID);

		}

		else if (cmd instanceof Map) {
			pars = new MapConstraint((Command) cmd, varID, ns, this);
			pars.parse();
		}

		else {
			FMLShell.getInstance().printTODO("command: " + cmd);
			FMLShell.getInstance().printTODO();
		}

		if (pars == null) // void command
		{
			_LOGGER.debug("void command");
			return null;
		}
		
		Variable rVar = pars.getVariable();
		//assign(rVar, varID, attributeID);
		
		return rVar ; 

	}
	
	public ConstraintVariable parseConstraint(ConstraintCommand cstCmd, String varID) {
		// TODO Auto-generated method stub
		
		_LOGGER.debug("\t\t parsing cstCmd=" + cstCmd);
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		if (cstCmd instanceof IdentifierExpr) {
			v = parse((Command) cstCmd, varID);
		}

		else if (cstCmd instanceof AtomicConstraintExpr) {

			pars = new AtomicConstraintExprAnalyzer((Command)cstCmd, varID, ns, this);
			pars.parse();
			v =  pars.getVariable();

		}

		
		

		else {
			FMLShell.getInstance().printTODO(
					"unknown constraint command " + cstCmd);
			return null;
		}
		
		if (v instanceof RefVariable) {
			v = ((RefVariable) v).getValueReference();
		}

		if (!(v instanceof ConstraintVariable)) {
			FMLShell.getInstance().setError("constraint expected but v=" + v);
			return null;
		}
		return (ConstraintVariable) v;
		
	}



	public FeatureModelVariable parseFMCommand(FMLAbstractCommand cmd,
			String varID, String attributeID) {

		assert (cmd != null);
		assert (cmd instanceof FMCommand);

		FMAnalyzer fmParser = new FMAnalyzer((Command)cmd, varID, ns, this) ;
		fmParser.parse();
		Variable v = fmParser.getVariable() ; 
		if (v == null)
			return null ; 
		return (FeatureModelVariable) v ; 		
		
	}

	/**
	 * @param scmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 */
	public Variable parse(ScriptCommand scmd) {
	
		
		if (scmd instanceof ComplexCommand) 
			return parse((ComplexCommand) scmd, null); 
		else {

			ComplexCommand compCmd = scmd.getCmd();
			String varID = scmd.getVar(); // cannot be null
	
			
			Variable rVar = parse(compCmd, varID);
			
			
			StringExpr metaAttribute = scmd.getMetaID() ; // can be null	
			String metaAttributeID = null ; 
			if (metaAttribute != null)
				metaAttributeID = metaAttribute.getVal() ; 
			
			assign(rVar, varID, metaAttributeID);
			
			

			
			return rVar ; 
			
		}
		
	}

	



	/**
	 * @param rVar
	 * @param varID
	 * @param attributeID
	 */
	private void assign(Variable rVar, String varID, String attributeID) {
		if (rVar == null)
			return ; 
		// FIXME assert(v != null) ;

		// check whether there is need to assign the evaluation to a variable
		if (varID == null) {
			/*
			 * if (FMShell.getInstance().isInteractiveMode() && (getType() !=
			 * RType.VOID) )
			 * 
			 * FMShell.getInstance().printDisplay (v.getName() + ": (" +
			 * v.getType() + ") " + v.getValue() + "\n" );
			 */

			if (FMLShell.getInstance().isEclipseInteractiveMode()
					|| FMLShell.getInstance().isStepByStep())
				show(rVar); // display the variable

			return;
		}
		
	
		
		if (varID != null && attributeID != null) {
			
			try {
				Variable oVar = getVariable(varID);
				
				// hack
				if (oVar instanceof FeatureVariable && attributeID != null) {
					_LOGGER.debug("oVar is a feature with an attribute... setting meta-information " + attributeID + " to the feature model and feature");
					FeatureVariable ft = (FeatureVariable) oVar ; 
					FeatureModelVariable fmv = ft.getFeatureModel() ;
					fmv.setFeatureAttribute(ft, attributeID, rVar);
					return ; 
				}				
				oVar.put(attributeID, rVar);
				return ; 
			} catch (VariableNotExistingException e) {
				FMLShell.getInstance().printError("Unable to retrieve the variable " + varID + " associated to attribute " + attributeID + " " + e.getLocalizedMessage());
				return ; 
			} catch (VariableAmbigousConflictException e) {
				FMLShell.getInstance().printError("(ambigous) Unable to retrieve the variable " + varID + " associated to attribute " + attributeID +  " " + e.getLocalizedMessage());
				return ;
			} 		
			
			
		}
		
		

		updateVariableView(); // TODO: incorrect (Foudil's code)

		_LOGGER.debug("assignment: " + varID + " = "	+ rVar.hashCode());
		rVar.setIdentifier(varID); // seems appropriate
		addOrReplaceVariable(varID, rVar);


		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())
			show(rVar); // display the variable

	
		
	}
	
	public void show(final Variable v) {

		/*************** gdisplay *************/
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (v instanceof FeatureModelVariable)
					((FeatureModelVariable) v).gdisplay();
				else if (v instanceof ConfigurationVariable) {
					((ConfigurationVariable) v).gdisplay();
				}

			}
		});

	}
	
	



	/**
	 * @param compCmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 */
	public Variable parse(ComplexCommand compCmd) {
		return parse(compCmd, null);
	}
	
	
	/**
	 * @param compCmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 */
	public Variable parse(ComplexCommand compCmd, String varID) {
		return parse(compCmd, varID, null);
	}
	

	/**
	 * @param compCmd
	 *            the command to parse Visitor-like pattern (no double dispatch
	 *            though since we cannot modify SCommand classes)
	 *            
	 *  @param varID variable identifier
	 *  @param attributeID attribute identifier (associated to varID)
	 */
	public Variable parse(ComplexCommand compCmd, String varID, String attributeID) {

		// manage *complex* operations (e.g., BOOL, INTEGER, COMPARISON, SET)
		FMLAbstractCommandAnalyzer pars = null;
		if (compCmd instanceof BoolOperation
				|| compCmd instanceof ComparisonOperation
				|| compCmd instanceof IntegerOperation
				|| compCmd instanceof SetOperation) {

			if (compCmd instanceof BoolOperation) {
				_LOGGER.debug("boolean operation ");
				pars = new BoolOperationParser(compCmd, varID, ns, this);
				pars.parse();
			}

			else if (compCmd instanceof ComparisonOperation) {
				_LOGGER.debug(
						"boolean operation (comparison) ");
				pars = new ComparisonOperationParser(compCmd, varID, ns, this);
				pars.parse();
			}

			else if (compCmd instanceof IntegerOperation) { // FIXME strange
															// that
															// IntegerOperation
															// is also in the
															// main visitor
															// function
				_LOGGER.debug(
						"integer operation (complex) ");
				pars = new IntegerOperationParser(compCmd, varID, ns, this); // hack
				pars.parse();
			}

			else if (compCmd instanceof SetOperation) { // FIXME strange that
														// IntegerOperation is
														// also in the main
														// visitor function
				_LOGGER.debug(
						"set operation (complex) ");
				pars = new SetOperationParser(compCmd, varID, ns, this); // hack
				pars.parse();
			}

			Variable rVar = pars.getVariable();
			//assign (rVar, varID, attributeID);
			return rVar ; 

		}

		// SIMPLE COMMAND aka Command
		if (!(compCmd instanceof ComplexCommandImpl)) {
			// should never be reached
			FMLShell.getInstance().setError(
					"Unknow command (neither a bool or comparison or integer operations) "
							+ compCmd);
			return null;
		}
		_LOGGER.debug(
				"parsing: complex command " + compCmd + " with var=" + varID);

		if (compCmd.getBatom() != null) {
			_LOGGER.debug("NOT boolean operation ");
			pars = new NotBooleanParser(compCmd.getBatom(), varID, ns, this);
			pars.parse();
			return pars.getVariable();
		}

		EObject locmd = compCmd.getLeft();
		if (!(locmd instanceof Command)) {
			FMLShell.getInstance().setError(
					"Unknown eobject (should be a command) " + locmd);
		}
		return parse((Command) locmd, varID);

	}

	



	/******** helper functions for Feature and Feature Model retrievers ********/

	public static boolean isExplicitFeatureName(String feature) {
		return feature.indexOf(FeatureVariable.SEPARATOR) != -1;
	}

	private String extractFMfromFeature(String feature) {
		int base = 0;
		if (isExplicitFeatureName(feature))
			base = feature.indexOf(FeatureVariable.SEPARATOR);

		String realFM = feature.substring(0, base);

		return realFM;
	}

	public String extractFeaturefromFeature(String featureID) {
		int base = 0;
		if (isExplicitFeatureName(featureID))
			base = featureID.indexOf(FeatureVariable.SEPARATOR) + 1;

		String actualFeatureName = featureID.substring(base);
		_LOGGER.debug(
				"(extraction) actualFeatureName=" + actualFeatureName);

		if (FeatureName.isQuoted(actualFeatureName))
			return actualFeatureName ;
		
		// hack (qualified or not)
		// fm1.(B.A) is qualified
		// A is not
		if (isQualifiedFtName(actualFeatureName)) {
			_LOGGER.debug(
					"(extraction) qualified feature name");
			int lastIndexSeparator = actualFeatureName
					.lastIndexOf(FeatureVariable.SEPARATOR);
			String realFeatureName = actualFeatureName
					.substring(lastIndexSeparator + 1); // last element
			_LOGGER.debug(
					"(extraction) real feature name=" + realFeatureName);
			if (realFeatureName.equals(WildCardVariable.WILD_CARD)) {
				FMLShell.getInstance()
						.printError(
								"should not be reached... compute descendant features of ... ");
				return null;
			} else {
				// some checkings
				return realFeatureName;
			}
		}

		try {
			Variable var = getExplicitVariable(actualFeatureName);
			if (var instanceof StringVariable)
				actualFeatureName = var.getValue();
			else if (var instanceof FeatureVariable) {
				actualFeatureName = var.getValue();
			} else if (var instanceof RefVariable) {
				_LOGGER.debug("Ref variable...");
				RefVariable refVar = (RefVariable) var;
				Variable varValue = refVar.getValueReference();
				if (varValue instanceof StringVariable)
					actualFeatureName = varValue.getValue();
				else if (varValue instanceof FeatureVariable)
					actualFeatureName = varValue.getValue();

				_LOGGER.debug(
						"var value: " + varValue.getValue() + " = "
								+ actualFeatureName);
				// TODO: what's happen if var is a reference?

			}
		} catch (VariableNotExistingException e) {
			_LOGGER.debug(
					"not a variable " + e.toString());

		} catch (VariableAmbigousConflictException e) {
			_LOGGER.debug(
					"(ambigous) not a variable " + e.toString());

		}

		return actualFeatureName;
	}

	/**
	 * @param featureID
	 *            feature identifier
	 * @return the feature model associated to the feature identifier
	 * @throws FeatureNotFoundException
	 *             in case the identifier corresponds to no FM
	 * @throws FeatureAmbigousException
	 *             in case the identifier corresponds to several FMs
	 */
	public FeatureModelVariable retrieveFeatureModel(String featureID)
			throws FeatureNotFoundException, FeatureAmbigousException {

		if (isExplicitFeatureName(featureID)) {

			String fmName = extractFMfromFeature(featureID);
			if (fmName == null) {
				throw new FeatureNotFoundException("variable " + fmName
						+ " is not a feature model");
			}

			_LOGGER.debug("feature model found: " + fmName);

			int base = 0;
			if (isExplicitFeatureName(featureID))
				base = featureID.indexOf(FeatureVariable.SEPARATOR) + 1;
			String actualFeatureName = featureID.substring(base);

			if (actualFeatureName == null) {
				throw new FeatureNotFoundException("feature extraction "
						+ actualFeatureName + " does not work");
			}

			actualFeatureName = FeatureName.unquote(actualFeatureName);
			_LOGGER.debug("actual feature name: " + actualFeatureName);

			try {
				FeatureModelVariable fmw = null;
				Variable var = getExplicitVariable(fmName);

				if (var instanceof RefVariable) {
					var = ((RefVariable) var).getValueReference();
				}

				if (var instanceof FeatureModelVariable) {
					fmw = (FeatureModelVariable) var;
				}

				if (fmw == null) {
					_LOGGER.debug("variable " + fmw + " is not a feature model");
					throw new FeatureNotFoundException("variable " + fmw
							+ " is not a feature model");
				}

				_LOGGER.debug("feature model found " + fmw.getVid());
				assert (fmw != null);

				// hack (qualified or not)
				// fm1.(B.A) is qualified
				// A is not
				
				if (fmw.features().names().contains(actualFeatureName)) {
					_LOGGER.debug(
							"actualFeatureName seems to be a qualified feature name but it's a feature of the feature model");
					return fmw ; 
				}
				
				if (isQualifiedFtName(actualFeatureName)) {
					_LOGGER.debug(
							"actualFeatureName=" + actualFeatureName
									+ " is indeed a qualified feature name");
					int lastIndexSeparator = actualFeatureName
							.lastIndexOf(FeatureVariable.SEPARATOR);
					String realFeatureName = actualFeatureName
							.substring(lastIndexSeparator + 1); // last element
					_LOGGER.debug(
							"real feature name=" + realFeatureName);

					if (realFeatureName.equals(WildCardVariable.WILD_CARD)) {
						StringTokenizer tokenizer = new StringTokenizer(
								actualFeatureName, FeatureVariable.SEPARATOR);
						int nSeparator = tokenizer.countTokens();
						int nToken = 0;
						String parentFt = null; // last but one before *, e.g.,
												// fm1.A.B.* => B
						while (tokenizer.hasMoreTokens()) {
							String token = tokenizer.nextToken();
							if (++nToken == (nSeparator - 1))
								parentFt = token;
						}
						if (parentFt == null) {
							FMLShell.getInstance().printError(
									"Unable to process and extract: "
											+ actualFeatureName);
						}

						_LOGGER.debug(
								"checking parentFt=" + parentFt);
						// checking that the parent does exist
						try {
							FeatureNode<String> fn = fmw.getFm().getDiagram()
									.findVertex(parentFt);
							if (fmw.getFm().getDiagram().outgoingEdges(fn)
									.size() == 0)
								throw new FeatureNotFoundException(
										actualFeatureName, fmw);
							_LOGGER.debug(
									"parent feature actually there: "
											+ fn.getFeature());
							return fmw;

						} catch (Exception e) {
							throw new FeatureNotFoundException(
									actualFeatureName, fmw);
						}

					}

					else {

						// some checkings

						String[] fts = actualFeatureName.split("\\"
								+ FeatureVariable.SEPARATOR);
						_LOGGER.debug(
								"fts=" + fts + " #" + fts.length);
						for (int i = 0; i < fts.length; i++) { // do fts exist?
							String ft = fts[i];
							try {
								fmw.getFm().getDiagram().findVertex(ft);
							} catch (Exception e) {
								throw new FeatureNotFoundException(
										actualFeatureName, fmw);
							}

						}
						for (int i = 0; i < (fts.length - 1); i++) { // control
																		// descendants/ancestors
																		// in
																		// qualified
																		// name
							String parent = fts[i];
							String descendant = fts[i + 1];
							_LOGGER.debug(
									"checking... parent=" + parent
											+ " descendant=" + descendant);
							if (!checkParentDescendant(parent, descendant, fmw)) {
								FMLShell.getInstance()
										.printError(
												"parent="
														+ parent
														+ " is not parent feature of descendant="
														+ descendant);
								return null;
							}
						}

						// anyway,
						actualFeatureName = realFeatureName;

					}

				}

				
				// no qualified feature name
				try {
					Set<String> fts = fmw.features().names() ; 
					if (!fts.contains(actualFeatureName)) 
						throw new FeatureNotFoundException(actualFeatureName, fmw);
					_LOGGER.debug("feature still there: " + actualFeatureName);
					return fmw;
				} catch (Exception e) {
					throw new FeatureNotFoundException(actualFeatureName, fmw);
				}

			} catch (VariableNotExistingException e) {
				_LOGGER.debug(
						"feature model not found " + e.toString());
				return null;
			} catch (VariableAmbigousConflictException e) {
				_LOGGER.debug(
						"(ambigous) feature model not found " + e.toString());
				return null;
			}

		}

		// TODO: more complex
		// e.g.:
		// fm1 = FM (A : B C ; B : D ; )
		// fm2 = FM (A : B D ; )
		// B.D
		// the current strategy fails since B is considered as an FM!

		List<FeatureModelVariable> fms = getFeatureModels();
		List<FeatureModelVariable> fmwr = new ArrayList<FeatureModelVariable>();
		for (FeatureModelVariable fmw : fms) {
			Set<String> fts = fmw.features().names() ;
			if (fts.contains(featureID))
				fmwr.add(fmw);
		}

		if (fmwr.size() == 0)
			throw new FeatureNotFoundException(featureID);
		if (fmwr.size() > 1)
			throw new FeatureAmbigousException(featureID);

		return fmwr.get(0);
	}

	/**
	 * check the descendant/ancestor relationship in fmw
	 * 
	 * @param parent
	 * @param child
	 * @param fmw
	 * @return true if parent is actually an ancestor feature of descendant in
	 *         fmw
	 */
	private boolean checkParentDescendant(String parent, String child,
			FeatureModelVariable fmw) {
		// other way checking is possible ;-)
		FeatureVariable childFt = new FeatureVariable(null, child, fmw);
		SetVariable ancestors = childFt.ancestors();
		Set<Variable> ancs = ancestors.getVars();
		for (Variable anc : ancs) {
			FeatureVariable ftAnc = (FeatureVariable) anc;
			if (ftAnc.getFtName().equals(parent))
				return true;
		}

		return false;

	}

	/**
	 * B.A is qualified A is not
	 * 
	 * @param ftName
	 * @return true if feature name is qualified
	 */
	public static boolean isQualifiedFtName(String ftName) {
		return ftName.indexOf(FeatureVariable.SEPARATOR) != -1;
	}

	/**
	 * @param featureID
	 *            the feature identifier
	 * @return the feature variable associated to the identifier (if any)
	 * @throws FeatureNotFoundException
	 * @throws FeatureAmbigousException
	 */
	public FeatureVariable retrieveFeature(String featureID)
			throws FeatureNotFoundException, FeatureAmbigousException {

		assert (featureID != null);

		Variable vstr = null;
		try {
			vstr = getExplicitVariable(featureID);
		} catch (VariableNotExistingException e) {
			throw new FeatureNotFoundException(featureID + " (no variable)");
		} catch (VariableAmbigousConflictException e) {
			throw new FeatureAmbigousException(featureID + " (no variable)");
		}
		if (vstr == null)
			return null;

		if (vstr instanceof RefVariable) {
			vstr = ((RefVariable) vstr).getValueReference();
		}

		if (vstr instanceof FeatureVariable) {
			return (FeatureVariable) vstr;
		}

		throw new FeatureNotFoundException(featureID + " (wrong type) "
				+ vstr.getType());

	}

	/**
	 * @param identifier
	 * @return explicit string identifier of a feature variable or string value
	 *         of identifier if the identifier is not a reference to a feature,
	 *         return identifier
	 */
	public String featureNameOrVariable(String identifier) {
		assert (identifier != null);

		FeatureVariable vfw;
		try {
			vfw = retrieveFeature(identifier); // TODO: check if there is
												// quote?!
		} catch (FeatureNotFoundException e) {
			return identifier;
		} catch (FeatureAmbigousException e) {
			return identifier;
		}

		FeatureModelVariable fmw = vfw.getFeatureModel();

		// explicit name!
		return "" + VariableIdentifier.completeName(fmw.getVid())
				+ FeatureVariable.SEPARATOR + vfw.getFtName();

	}

	/******** end of helper functions for Feature and Feature Model retrievers ********/

	private void closeDeclaration() {

		// we do not authorize variables/parameters to be declared after other
		// commands
		// so that when we first encounter a non declarative command, we do not
		// allow variable declaration
		// for the rest of the script
		if (!this._declaration)
			return;

		_LOGGER.debug(
				"No longer possible to declare parameters");
		this._declaration = false;

		if (_parametersToBound.size() != _parameters.size()) {
			FMLShell.getInstance().setError(
					"Number of arguments (" + _parametersToBound.size()
							+ ") or " + "parammeters (" + _parameters.size()
							+ ") is not correct.");
			return;
		}

		_LOGGER.debug(
				"Number of arguments (" + _parametersToBound.size() + ") or "
						+ "parammeters (" + _parameters.size()
						+ ") is correct.");

		/*** we bound the effective parameters *****/
		int nArg = 0;
		for (Variable var : _parametersToBound) {

			// TODO: Control type!
			String varName = _parameters.get(nArg);
			_LOGGER.debug(
					varName + " bound to " + "(" + var.getType() + ") "
							+ var.getVid() + " = " + var.getValue() + "");
			VariableIdentifier vid = new VariableIdentifier(varName);
			Variable refVar = new RefVariable(var, vid);
			addOrReplaceVariable(varName, refVar);

			nArg = nArg + 1;

		}
		assert (nArg == _parametersToBound.size());

	}

	private void treatParameter(Parameter param) {
		if (!this._declaration) {
			FMLShell.getInstance()
					.setError("Parameters must be first declared");
			return;
		}

		String parameter = param.getParam();
		recordParameter(parameter);
	}

	private void recordParameter(String param) {

		if (_parameters.contains(param)) {
			FMLShell.getInstance().setError(
					"Parameter already declared " + param);
			return;
		}

		_parameters.add(param);

	}

	private void parseHidden(Hidden cmd) {

		LVidentifier lvid = cmd.getArg();
		EList<String> vars = lvid.getArgs();

		for (String var : vars) {
			String varName = var;
			String varNS = null;
			if (var.indexOf(NameSpace.DELIMITER) != -1) { // namespace
				varName = var
						.substring(var.lastIndexOf(NameSpace.DELIMITER) + 1);
				varNS = var.substring(0, var.lastIndexOf(NameSpace.DELIMITER));
			}

			_LOGGER.debug(
					"hidden: " + varName + " NS " + varNS);
			getHiddenVariableIdentifiers().push(
					new VariableIdentifier(varName, NSFactory.mkNS(varNS)));
		}

	}

	private void parseExport(Export cmd) {
		// TODO Auto-generated method stub

	}

	/***** Variable Manager (a.k.a. environment) **********/

	public Variable getExplicitVariable(String varName)
			throws VariableNotExistingException,
			VariableAmbigousConflictException {

		// Variable cst = getConstantVariable(varName);
		// if (cst != null)
		// return cst;

		if (varName.indexOf(WildCardVariable.WILD_CARD) != -1) {
			WildCardVariable wvar = new WildCardVariable(this, varName);
			return wvar.parse();
		}

		if (!_varManager.hasVariable(varName))
			throw new VariableNotExistingException(varName);
		if (_varManager.hasAmbigousVariable(varName))
			throw new VariableAmbigousConflictException(varName,
					_varManager.getCandidateVariable(varName));

		return _varManager.getVariable(varName);
	}

	/**
	 * @param id
	 * @return the variable associated to the identifier id
	 */
	public Variable getVariable(String id) throws VariableNotExistingException,
			VariableAmbigousConflictException {

		Variable variable = null;
		boolean found = false;

		/********** CONSTANT (not integrated in the Grammar -- reserved keywords) ***********/
		if (id.equals(Comparison.ARBITRARY.name())
				|| id.equals(Comparison.GENERALIZATION.name())
				|| id.equals(Comparison.REFACTORING.name())
				|| id.equals(Comparison.SPECIALIZATION.name())) {

			_LOGGER.debug("FM edit constant found!");
			variable = new StringVariable(null, id);
			found = true;
		}

		String[] operatorConstants = new String[] {
				FeatureEdgeKind.ALTERNATIVE.toString(),
				FeatureEdgeKind.MANDATORY.toString(),
				FeatureEdgeKind.OPTIONAL.toString(),
				FeatureEdgeKind.OR.toString(), };

		for (String cst : operatorConstants) {
			if (cst.equalsIgnoreCase(id)) {
				_LOGGER.debug(
						"Operator constant " + cst);
				FeatureEdgeKind fek = null;

				if (cst.equalsIgnoreCase(FeatureEdgeKind.ALTERNATIVE.toString()))
					fek = FeatureEdgeKind.ALTERNATIVE;
				else if (cst.equalsIgnoreCase(FeatureEdgeKind.MANDATORY
						.toString()))
					fek = FeatureEdgeKind.MANDATORY;
				else if (cst.equalsIgnoreCase(FeatureEdgeKind.OPTIONAL
						.toString()))
					fek = FeatureEdgeKind.OPTIONAL;
				else if (cst.equalsIgnoreCase(FeatureEdgeKind.OR.toString()))
					fek = FeatureEdgeKind.OR;
				else {
					FMLShell.getInstance().setError("Unknow FEK " + cst);
					return null;
				}

				variable = new VariabilityOperatorVariable(null, fek);
				found = true;
			}
		}

		Exception errorException = null;
		if (!found) { // neither a variability operator nor a constant value.
						// let us try other cases.
			try {
				variable = getExplicitVariable(id);
				found = true;
			} catch (VariableNotExistingException e) {
				assert (!found);
				errorException = e;
			} catch (VariableAmbigousConflictException e) {
				errorException = e;
				assert (!found);
			}
		}

		// TODO:
		// can be a FEATURE!!!!
		if (!found) {
			
			_LOGGER.debug(
					"Determining if identifier= " + id + " is a feature!");
			FeatureVariable fw = null;
			FeatureModelVariable fmw = null;
			
			try {
				fw = retrieveFeature(id);
				variable = fw;
				found = true;
			} catch (FeatureNotFoundException e) {
				_LOGGER.debug(
						"Unable to find feature (1): " + e.toString());

				try {
					// TODO: we should retrieve feature and feature model with
					// only one call to a function / one pass
					// TODO: ns2.ns1.fm1.A.D.E is possible!
					fmw = retrieveFeatureModel(id); // retrieve the feature
													// model by considering the
													// first part of the
													// identifier
					id = extractFeaturefromFeature(id);
					id = FeatureName.unquote(id) ; 
					// TODO
					if (fmw != null) {
						variable = new FeatureVariable(null, id, fmw);
						// _LOGGER.debug("feature found: "
						// + id + " in fm=" + fmw);
						_LOGGER.debug(
								"corresponding variable: " + variable);
						found = true;
					} else {
						_LOGGER.debug(
								"Unable to find feature model fmw=" + fmw);
						errorException = e;
					}

				} catch (FeatureNotFoundException e1) {
					_LOGGER.debug(
							"Unable to find feature (2): " + e1.toString());
					errorException = e1;
				} catch (FeatureAmbigousException e1) {
					_LOGGER.debug(
							"(ambigous) Unable to find feature (2): "
									+ e1.toString());
					errorException = e1;
				}
			} catch (FeatureAmbigousException e) { // TODO to handle qualified
													// names
				_LOGGER.debug(
						"(ambigous) Unable to find feature (1): "
								+ e.toString());

				try {

					fmw = retrieveFeatureModel(id);
					id = extractFeaturefromFeature(id);

					// TODO
					// TODO
					if (fmw != null) {
						variable = new FeatureVariable(null, id, fmw);
						_LOGGER.debug(
								"feature found: " + id + " in fm=" + fmw);
						_LOGGER.debug(
								"corresponding variable: " + variable);

						found = true;
					} else {
						_LOGGER.debug(
								"Unable to find feature model (ambigous) fmw="
										+ fmw);
						errorException = e;
					}
				} catch (FeatureNotFoundException e1) {
					_LOGGER.debug(
							"Unable to find feature (2): " + e1.toString());
					errorException = e1;
				} catch (FeatureAmbigousException e1) {
					_LOGGER.debug(
							"(ambigous) Unable to find feature (2): "
									+ e1.toString());
					errorException = e1;
				}
			}
		}

		if (!found) {
			assert (errorException != null);
			_LOGGER.debug(
					"variable " + id + " does not exist: "
							+ errorException.getClass().toString());
			// FMLShell.getInstance().setError("variable " + id +
			// " does not exist: " + errorException);

			if (errorException instanceof VariableNotExistingException)
				throw (VariableNotExistingException) errorException;
			else if (errorException instanceof VariableAmbigousConflictException)
				throw (VariableAmbigousConflictException) errorException;
			else if (errorException instanceof FeatureNotFoundException) {
				FeatureNotFoundException ftException = (FeatureNotFoundException) errorException;
				throw new VariableNotExistingException(ftException.getMessage());
			} else if (errorException instanceof FeatureAmbigousException) {
				FeatureAmbigousException ftException = (FeatureAmbigousException) errorException;
				throw new VariableAmbigousConflictException(
						ftException.getMessage());
			}
			// not reachable
			return null;

		}

		return variable;
	}

	public void addVariable(Variable v) {
		if (_varManager.hasVariable(v.getVid()))
			FMLShell.getInstance().printWarning(
					"Variable " + v.getIdentifier() + " already exists.");
		if (_varManager.hasVariableWithNS(v.getVid())) // TODO: check with the
														// NS
			FMLShell.getInstance().printWarning(
					"Variable " + v.getIdentifier()
							+ " already exists (in the same namespace "
							+ v.getNS() + " ).");
		if (_varManager.hasAmbigousVariable(v.getIdentifier()))
			FMLShell.getInstance().printWarning(
					"Ambigous variable " + v.getIdentifier() + "");
		_varManager.addVariable(v);

		updateVariableView();

	}

	public void updateVariableView() {

		// FIXME: weird
		if (!FMLShell.getInstance().getPreference().isVariableViewActivated())
			return;

		// view
		if (FMLShell.getInstance().isEclipseInteractiveMode()
				&& !FMLShell.getInstance().isRunningScript()) {
			_LOGGER.debug("updating view");

			// FIXME @FeatureIDE 
			
			VariableView variableView = FamiliarRun.getVariableView();
			if (variableView == null)
				return;
			_LOGGER.debug(
					"variableView=" + variableView);

			variableView.clear();

			List<Variable> varList = FMLShell.getInstance().getCurrentEnv()
					.getVariables();
			for (Variable var : varList) {
				System.out.println("ajout d'un element");

				variableView.addItem(var);
				System.out.println("fin d'ajout de l'element");
			}

			variableView.listen();
			variableView.dispose() ;

		}

	}

	/*
	 * add a variable (if it does not exist) or override the value of the
	 * variable
	 * 
	 * @param var the variable name to be assigned
	 * 
	 * @param vari the variable to add or override
	 */

	public void addOrReplaceVariable(String var, Variable vari) {

		if (var == null) {
			FMLShell.getInstance().printWarning("No assignment");
			return;
		}

		assert (var != null);

		Variable var2Save = null;
		try {
			var2Save = getExplicitVariable(var);
		} catch (VariableNotExistingException e1) {
			_LOGGER.debug(
					"variable " + var + " does not exist: " + e1.toString());
		} catch (VariableAmbigousConflictException e1) {
			_LOGGER.debug(
					"(ambigous) variable " + var + " does not exist: "
							+ e1.toString());
		}

		// shell.printDebugMessage("value of new variable: " + vari.getValue());
		if (var2Save == null) {
			_LOGGER.debug("Creation needed");
			addVariable(vari);
		} else {
			_LOGGER.debug("already exists... ");
			if (var2Save instanceof RefVariable) {
				_LOGGER.debug(
						"set the reference... " + vari.getIdentifier() + " "
								+ vari);
				RefVariable refVar2Save = (RefVariable) var2Save;
				Variable eternalRef = refVar2Save.getReference();
				_LOGGER.debug(
						"eternal reference: " + eternalRef.getIdentifier());
				refVar2Save.setValue(vari);

			} else {
				removeVariable(var2Save.getVid());
				addVariable(vari);

			}

		}

	}

	public List<Variable> getVariables() {
		return _varManager.getVariables();
	}

	public List<FeatureModelVariable> getFeatureModels() {
		List<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		List<Variable> vars = getVariables();
		for (Variable var : vars) {
			if (var instanceof RefVariable) {
				var = ((RefVariable) var).getValueReference();
			}

			if (var instanceof FeatureModelVariable)
				fms.add((FeatureModelVariable) var);
		}

		return fms;
	}

	/*
	 * 
	 * @return variables with the namespace ns
	 */
	public List<Variable> getVariables(NameSpace aNS) {

		List<Variable> vars = _varManager.getVariables();
		List<Variable> res = new ArrayList<Variable>();
		for (Variable variable : vars) {
			NameSpace nsVar = variable.getNS();
			if (aNS.equals(nsVar)) // TODO: recursively
				res.add(variable);
		}
		return res;
	}

	// we assume that vold exists
	// newName should not exist
	public void replaceVar(Variable vold, String newName) {
		removeVariable(vold.getVid());
		vold.setIdentifier(newName);
		addVariable(vold);
		// varManager.replaceVar(newName, vold);

	}

	public void removeVariable(VariableIdentifier var) {
		_varManager.removeVariable(var);

	}
	
	
	public String allVariablesToString() {
		StringBuilder sb = new StringBuilder() ; 
		List<Variable> vars = getVariables();
		for (Variable variable : vars) {
			sb.append(
					"(" + variable.getType() + "" + ", completeName: "
							+ variable.getVid() + ", ns:" + variable.getNS()
							+ ") " + variable.getIdentifier() + " = "
							+ variable.getValue()
							+ System.getProperty("line.separator"));
		}
		return sb.toString() ; 
	}

	public void hiddenVariables() {

		Stack<VariableIdentifier> hiddens = getHiddenVariableIdentifiers();
		while (!hiddens.empty()) {
			VariableIdentifier vid = hiddens.pop();

			// check if the variable exists
			Variable lvar = null;
			try {
				lvar = getVariable(vid.getName());
			} catch (VariableNotExistingException e) {
				FMLShell.getInstance().setError(e.toString());
				return;
			} catch (VariableAmbigousConflictException e) {
				FMLShell.getInstance().setError(e.toString());
				return;
			}

			// lvar != null
			removeVariable(lvar.getVid());

		}

		Stack<VariableIdentifier> exports = getExported();
		// TODO
	}

	/****** Hidden and Exported variables *******/

	public Stack<VariableIdentifier> getHiddenVariableIdentifiers() {
		return _hiddens;
	}

	public Stack<VariableIdentifier> getExported() {
		return _exported;
	}

	public VariableManager getEnvironment() {
		return _varManager;
	}

	public void addOrReplaceEnv(VariableManager newEnv) {

		VariableManager currentEnv = getEnvironment();

		// diff
		List<Variable> currentEnvVars = currentEnv.getVariables();
		List<Variable> newEnvVars = newEnv.getVariables();

		for (Variable af : newEnvVars) {
			boolean newVar = true;
			for (Variable bf : currentEnvVars) {
				if (af.equals(bf)) {
					newVar = false;
				}

			}

			if (!newVar) {
				_LOGGER.debug(
						"override variable in the environment: " + af.getVid());
				currentEnv.removeVariable(af.getVid());
				// currentEnv.replaceVar(af); // TODO: hack
			}

		}

		getEnvironment().addEnvironment(newEnv);

	}

	@Deprecated
	// TODO
	public void removeVariable(Variable bf) {
		_varManager.removeVariable(bf);

	}

	public ConfigurationVariable parseConfigurationCommand(ConfigurationCommand confCmd,
			String var) {

		_LOGGER.debug(
				"\t\t parsing confCmd=" + confCmd);
		Variable v = null;
		if (confCmd instanceof IdentifierExpr) {
			v = parse((Command) confCmd, var);
		}

		else if (confCmd instanceof CopyVariable) {
			v = parse((Command) confCmd, var);
		}

		else if (confCmd instanceof CreateConfiguration) {
			FMLAbstractCommandAnalyzer pars = new ConfigurationOperationAnalyzer(
					(Command) confCmd, var, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		else {
			FMLShell.getInstance().printTODO("unknown ConfigurationCommand");
			return null;
		}

		if (v instanceof RefVariable) {
			v = ((RefVariable) v).getValueReference();
		}

		if (!(v instanceof ConfigurationVariable)) {
			FMLShell.getInstance()
					.setError("configuration expected but v=" + v);
			return null;
		}
		return (ConfigurationVariable) v;

	}

	public FeatureVariable parseFTCommand(FMLAbstractCommand ftCmd, String var) {

		assert (ftCmd != null);
		_LOGGER.debug("\t\t parsing ftCmd=" + ftCmd);
	

		FeatureAnalyzer fmParser = new FeatureAnalyzer((Command)ftCmd, var, ns, this) ;
		fmParser.parse();
		Variable v = fmParser.getVariable() ; 
		if (v == null)
			return null ; 
		return (FeatureVariable) v ; 
		
		
	}

	public StringVariable parseStringCommand(StrCommand strExpr, String varID, String attributeID) {
		_LOGGER.debug(
				"\t\t parsing strCmd=" + strExpr);
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		if (strExpr instanceof IdentifierExpr) {
			v = parse((Command) strExpr, varID);
		} else if (strExpr instanceof CopyVariable) {
			v = parse((Command) strExpr, varID);
		}

		else if (strExpr instanceof FeatureOperation) {
			v = parse((Command) strExpr, varID);
		}

		else if (strExpr instanceof StringExpr) {
			_LOGGER.debug("string: ");
			pars = new StringExprParser((Command) strExpr, varID, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		/*********** STRING operations **************/
		else if (strExpr instanceof StringOperation) {
			pars = new StringOperationAnalyzer((Command) strExpr, varID, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		else if (strExpr instanceof Convert) {
			pars = new ConvertAnalyzer((Command) strExpr, varID, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		else {
			FMLShell.getInstance().printTODO("unknown STRCommand " + strExpr);
			return null;
		}

		if (v instanceof RefVariable) {
			v = ((RefVariable) v).getValueReference();
		}

		if (!(v instanceof StringVariable)) {
			FMLShell.getInstance().setError("string expected but v=" + v);
			return null;
		}
		return (StringVariable) v;
	}

	public SetVariable parseSetCommand(SetCommand setCmd, String var) {
		
		assert (setCmd != null);
		_LOGGER.debug("\t\t parse set=" + setCmd);
		
		SetAnalyzer fmParser = new SetAnalyzer((Command)setCmd, var, ns, this) ;
		fmParser.parse();
		Variable v = fmParser.getVariable() ; 
		if (v == null)
			return null ; 
		return (SetVariable) v ; 

		

	}

	public VariabilityOperatorVariable parseVOP(VariabilityOpCommand vop,
			String var) {
		_LOGGER.debug("\t\t parsing vop=" + vop);
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		if (vop instanceof IdentifierExpr) {
			v = parse((Command) vop, var);
		}

		else if (vop instanceof CopyVariable) {
			v = parse((Command) vop, var);
		}

		else if (vop instanceof FeatureVariabilityOperator) {
			_LOGGER.debug(
					"variability operator (VOP): ");
			pars = new VOPExprParser((Command) vop, var, ns, this);
			pars.parse();
			v = pars.getVariable();
		} else {
			FMLShell.getInstance().printTODO("unknown VOP command " + vop);
			return null;
		}

		if (!(v instanceof VariabilityOperatorVariable)) {
			FMLShell.getInstance().setError(
					"variability operator expected but v=" + v);
			return null;
		}
		return (VariabilityOperatorVariable) v;
	}

	public DoubleVariable parseDoubleCmd(DoubleCommand doubleCmd, String var) {
		_LOGGER.debug(
				"\t\t parsing doubleCmd=" + doubleCmd);
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		if (doubleCmd instanceof IdentifierExpr) {
			v = parse((Command) doubleCmd, var);
		}

		else if (doubleCmd instanceof CTCRCommand) {
			pars = new CTCRAnalyzer((Command) doubleCmd, var, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		else {
			FMLShell.getInstance().printTODO(
					"unknown double command " + doubleCmd);
			return null;
		}

		if (!(v instanceof DoubleVariable)) {
			FMLShell.getInstance().setError("double expected but v=" + v);
			return null;
		}

		return (DoubleVariable) v;

	}
	
	public IntegerVariable parseIntegerCmd(IntegerCommand intCmd, String varID) {
		return parseIntegerCmd(intCmd, varID, null);
	}

	public IntegerVariable parseIntegerCmd(IntegerCommand intCmd, String varID, String attributeID) {
		_LOGGER.debug(
				"\t\t parsing intCmd=" + intCmd);
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		if (intCmd instanceof IdentifierExpr) {
			v = parse((Command) intCmd, varID);
		}

		else if (intCmd instanceof DoubleCommand) {
			v = parseDoubleCmd((DoubleCommand) intCmd, varID);
		}

		else if (intCmd instanceof StringIndexOf
				|| intCmd instanceof StringLength) {
			pars = new StringOperationAnalyzer((Command) intCmd, varID, ns, this);
			pars.parse();
			v = pars.getVariable();
		}

		else if (intCmd instanceof SetCard) {
			pars = new SetOperationAnalyzer((Command) intCmd, ns, varID, this);
			pars.parse();
			v = pars.getVariable();
		}

		else if (intCmd instanceof IntegerExpr) {
			v = parse((Command) intCmd, varID);
		}

		else {
			FMLShell.getInstance().printTODO(
					"unknown integer command " + intCmd);
			return null;
		}
		
		if (v instanceof RefVariable)
			v = ((RefVariable) v).getValueReference();

		if (!(v instanceof IntegerVariable)) {
			FMLShell.getInstance().setError("integer expected but v=" + v);
			return null;
		}
		return (IntegerVariable) v;

	}

	
	public Set<ConfigurationVariable> retrieveAllConfigurations(
			FeatureModelVariable fmv) {
		Set<ConfigurationVariable> scv = new HashSet<ConfigurationVariable>();

		for (Variable v : getVariables()) {
			if (v instanceof RefVariable)
				v = (RefVariable) v;
			if (v instanceof ConfigurationVariable) {
				ConfigurationVariable cv = (ConfigurationVariable) v;
				FeatureModelVariable fmv2 = cv.getFmv();
				if (fmv2 == fmv)
					scv.add(cv);
			}

		}

		return scv;

	}

	/**
	 * @return the current command of the script to process
	 */
	public ScriptCommand getCurrentCommand() {
		return _currentCmd;
	}

	/**
	 * Delete all variables in the current environment
	 */
	public void clear() {
		_varManager.clear();
		if (_merger != null)
			getBuilder().reset();

	}



	public StringVariable parseStringCommand(StrCommand strExpr, String varID) {
		return parseStringCommand(strExpr, varID, null);
	}

}
