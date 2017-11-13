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
package fr.familiar.parser;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.CNF;
import org.xtext.example.mydsl.fml.Child;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.FeatureModel;
import org.xtext.example.mydsl.fml.Mandatory;
import org.xtext.example.mydsl.fml.Mutexgroup;
import org.xtext.example.mydsl.fml.Optional;
import org.xtext.example.mydsl.fml.Orgroup;
import org.xtext.example.mydsl.fml.Production;
import org.xtext.example.mydsl.fml.Xorgroup;

import splar.core.fm.FeatureModelException;
import splar.core.fm.XMLFeatureModel;
import fr.familiar.fm.FMLBDDReader;
import fr.familiar.fm.FeatureModelChecker;
import fr.familiar.fm.basic.FMLFeatureModel;
import fr.familiar.fm.basic.FMLFeatureModelReader;
import fr.familiar.fm.converter.FDModelErrorException;
import fr.familiar.fm.converter.FDUnsupportedModelException;
import fr.familiar.fm.converter.FMLDimacsReaderBDD;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.fm.converter.featureide.FMLDimacsReaderSAT;
import fr.familiar.fm.converter.tvl.TVLTranslator;
import fr.familiar.interpreter.FMLAssertionError;
import fr.familiar.interpreter.FMLBasicInterpreter;
import fr.familiar.interpreter.FMLFatalError;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.BDDMerger;
import fr.familiar.operations.CNFtoExpression;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.featureide.FeatureIDEReader;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelLazyVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableBDDFormula;
import fr.familiar.variable.FeatureName;
import fr.familiar.variable.RType;
import fr.familiar.variable.featureide.FeatureModelVariableConstraints;
import fr.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

/**
 * @author mathieuacher build a feature model (various formats)
 * 
 */
public class FMBuilder extends FMLAbstractCommandAnalyzer {
	
	
	private static Logger _LOGGER = Logger.getLogger(FMBuilder.class);


	private FeatureModelChecker _checker;

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public FMBuilder(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public FMBuilder(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public void eval() {

		assert (_command instanceof org.xtext.example.mydsl.fml.FeatureModel);
		org.xtext.example.mydsl.fml.FeatureModel fm = (org.xtext.example.mydsl.fml.FeatureModel) _command;

		// fm to String
		String strfm = "";

		if (fm.getFile() != null) { // internal reader/writer

			String filename = fm.getFile().getVal();

			// Feature IDE
			if (filename.endsWith(".m")) {
				_LOGGER.debug("FeatureIDE import");
				File file = FMLShell.getInstance().searchFile(filename) ; 
				// FIXME @FeatureIDE
				strfm = new FeatureIDEReader(file).writeToString() ; // TODO model-to-model transformations
			}
			
			else if (filename.endsWith(".tvl")) {
				
				
				File file = FMLShell.getInstance()
				.searchFile(filename) ; 
				try {
					FeatureModelVariable fmv = parseTVLModel(file) ; 	
					setVariable(fmv);
					return ; 
				} catch (Exception e) {
					FMLShell.getInstance().printError("Unable to parse the TVL model..." + e.getMessage());
					return ; 
				}
				/*
				inputFAMILIAR = _translator.getFAMILIARFMLOutput();
				_shell.parse("fm = " + inputFAMILIAR);
				FeatureModelVariable fmVariable = getFMVariable("fm");
				*/
			}
			
			else if (filename.endsWith(".fmlbdd")) {
				_LOGGER.debug("FML BDD import");
				
				FeatureModelVariable fmv = parseFMLBDD(filename, FMLCommandInterpreter.getBuilder());				
				setVariable(fmv);
				return ; 
			}
			
			else if (filename.endsWith(".bdd")) {
				_LOGGER.debug("FML BDD import (BDD only)");
				
				FMLBDDReader reader = new FMLBDDReader(filename); // fixed interoperability
				
				BDDBuilder<String> builder = FMLCommandInterpreter.getBuilder() ; // reader.getBDDBuilder() ; //
				
				FMLCommandInterpreter.setMerger(new BDDMerger(builder));
								
				
				Map<String, Integer> map = reader.getMapBuilder();
				BDD bdd = reader.getBDD();
				
				
				Formula<String> fla = new Formula<String>(bdd, map.keySet(), builder);
							
				FeatureModelVariable fmv = new FeatureModelVariableBDDFormula(getAssigner(), fla, builder);
				setVariable(fmv);
				return ; 
			}

			else if (filename.endsWith(".xmi")) {

				_LOGGER.debug("FD Triskell import");
				// FDConverter2FML converter = new FDConverter2FML(true);

				FMConverter converter = FMLConverterFactory.createConverter(
						FMConverterFormat.FDGillesPerrrouin, true);
				boolean isTriskell = false;
				try {
					strfm = converter.parseFile(FMLShell.getInstance()
							.searchFile(filename));
					isTriskell = true;
				} catch (FDModelErrorException e) {
					// FMLShell.getInstance().setError(e.getMessage());
				} catch (FDUnsupportedModelException e) {
					// FMLShell.getInstance().setError(e.getMessage());
				}

				// XMI: not triskell
				if (!isTriskell) {
					fm = parseFile(filename);
					setVariable(new FeatureModelVariable(_assigner, mkInternalFM(fm)));
					return ; 
					/*
					FeatureModelStringBuilder fmp = new FeatureModelStringBuilder(
							fm, false);
					strfm = fmp.toString();
					*/
				}

			}

			// TODO: Unit testing, I have the feeling that is is highly buggy
			// (constraints + groups)
			else if (filename.endsWith(".xml")) {

				// SPLOT
				File splotFile = FMLShell.getInstance().searchFile(filename);
				splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
						splotFile.getAbsolutePath(),
						XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
				try {
					featureModelSPLOT.loadModel();
				} catch (FeatureModelException e) {
					FMLShell.getInstance().printError(
							"Unable to load SPLOT feature model "
									+ e.getMessage());
					return;
				}

				// strfm = new SPLOTtoFML().convert(featureModelSPLOT); // @Deprecated
			
				
				gsd.synthesis.FeatureModel<String> rFM = new SPLOTtoFML().convertToFeatureModel(featureModelSPLOT);
				FeatureModelVariable fmv = new FeatureModelVariable(_assigner, rFM) ; 
				setVariable(fmv);
				return ;
				
				

			}
			
			else if (filename.endsWith(".dimacs")) {

				File dimacsFile = FMLShell.getInstance().searchFile(filename);
				FeatureModelVariableBDDFormula v = parseDimacsWithBDD(dimacsFile, FMLCommandInterpreter.getBuilder());
				v.setIdentifier(_assigner) ;
				setVariable(v);
				return ;

			}

			
			else if (filename.endsWith(".constraints")) {
				// FIXME @FeatureIDE
				File cstsFile = FMLShell.getInstance().searchFile(filename);
				FeatureModelVariable v = parseConstraints(cstsFile, FMLCommandInterpreter.getBuilder());
				v.setIdentifier(_assigner) ;
				setVariable(v);
				return ;

			}
			
			// e.g., .fml
			else {

				fm = parseFile(filename);
				FeatureModelVariable fmv = new FeatureModelVariable(_assigner, mkInternalFM(fm)) ; 
				setVariable(fmv);
				return ;				
			}

		} else { // Xtext facilities fail (model to text) for some examples so
					// let us do it by hand
			if (!checkWellFormed(fm)) {
				FMLShell.getInstance().setError(
						"Unable to parse the feature model (syntaxic errors): "
								+ _checker.getErrors());
				return;
			}
			
			
			gsd.synthesis.FeatureModel<String> iFM = mkInternalFM(fm);
			
			
			if (iFM == null) {
				FMLShell.getInstance()
						.setError("Unable to parse the feature model");
				return;
			}

			FeatureModelVariable fmv = new FeatureModelVariable(_assigner, iFM) ; 
			//_unquote (fmv);
			setVariable(fmv);
			return ;
			
		}

		/***** merge operations: connection needed with FMCalc *****/
		gsd.synthesis.FeatureModel<String> nfm = getInternalFM(strfm);

		if (nfm == null) {
			FMLShell.getInstance()
					.setError("Unable to parse the feature model");
			return;
		}

		FeatureModelVariable fmv = new FeatureModelVariable(_assigner, nfm) ; 
		_unquote (fmv);
		setVariable(fmv);
	}



	public static FeatureModelVariable parseFMLBDD(String filename, BDDBuilder<String> builder) {
		FMLBDDReader reader = new FMLBDDReader(filename); // fixed interoperability
		
		FMLCommandInterpreter.setMerger(new BDDMerger(builder));
								
		Map<String, Integer> map = reader.getMapBuilder();
		BDD bdd = reader.getBDD();
		
		
		Formula<String> fla = new Formula<String>(bdd, map.keySet(), builder);
					
		gsd.synthesis.FeatureModel<String> ifm = FMBuilder.getInternalFM("FM (" + reader.getFMRepresentation() + ")");
		assertNotNull (ifm);
		Formula<String> diffFla = FMLMergerBDD.diff(fla, builder.mkFeatureModel(ifm), builder);
		FeatureModelLazyVariable fmv = new FeatureModelLazyVariable("", ifm, diffFla);
		return fmv ; 
	}

	/** unquote feature names 
	 * @param fmv
	 */
	@Deprecated
	private void _unquote(FeatureModelVariable fmv) {
		Set<String> fts = fmv.features().names() ;
		for (String ft : fts) {
			fmv.renameFeature(ft, FeatureName.unquote(ft));
		}		
	}
	
	

	

	public static FeatureModelVariable parseConstraints(File file,	BDDBuilder<String> builder) {
		// FIXME @FeatureIDE
		FMLConstraintsReader cstReader = new FMLConstraintsReader() ; 
		Collection<Expression<String>> disjClauses = cstReader.parse (file);
		return new FeatureModelVariableConstraints("", disjClauses);
	}
	
	public static FeatureModelVariable parseConstraints(String content, BDDBuilder<String> builder) {
		// FIXME  @FeatureIDE
		FMLConstraintsReader cstReader = new FMLConstraintsReader() ; 
		Collection<Expression<String>> disjClauses = cstReader.parse (content);
		return new FeatureModelVariableConstraints("", disjClauses);
	}

	public static FeatureModelVariable parseTVLModel(File file) throws Exception {
		
		TVLTranslator translator = new TVLTranslator(file);
		FeatureModel fmTranslated = translator.getFAMILIARFeatureModel() ; 
		gsd.synthesis.FeatureModel<String> ifm = mkInternalFM(fmTranslated);
		assertNotNull (ifm);
		FeatureModelVariable fmv = new FeatureModelVariable("", ifm);
		return fmv ;
	}

	public static gsd.synthesis.FeatureModel<String> mkInternalFM(FeatureModel fm) {
		// TODO: FeatureModelVisitor?
		
		gsd.synthesis.FeatureModel<String> irFM = FeatureGraphFactory.mkStringFactory().mkTopModel() ;
		
		/*
		 * case 1: only root!
		 */
		String rootName = fm.getRoot() ;
		if (rootName != null) {
			FeatureGraph<String> fd = irFM.getDiagram() ; 
			FeatureNode<String> fnRoot = mkFeatureNode(rootName, fd) ; 
			fd.addVertex(fnRoot); 
			fd.addEdge(fnRoot, fd.getTopVertex(), FeatureEdge.HIERARCHY) ;
			fd.addEdge(fnRoot, fd.getTopVertex(), FeatureEdge.MANDATORY) ; 
		}
		
		else {
				
			EList<Production> productions = fm.getFeatures() ;
			boolean root = false ; 
			for (Production production : productions) {
				if (!root) {				
					FeatureGraph<String> fd = irFM.getDiagram() ; 
					String parentFtName = production.getName() ; 
					FeatureNode<String> fnParent = mkFeatureNode(parentFtName, fd) ; 
					fd.addVertex(fnParent); 
					fd.addEdge(fnParent, fd.getTopVertex(), FeatureEdge.HIERARCHY) ;
					fd.addEdge(fnParent, fd.getTopVertex(), FeatureEdge.MANDATORY) ;
					root = true ; 
				}
				treatProd(production, irFM);
			}
		}
		
		// root should come without constraints
		
			
		List<Expression<String>> iExprs = new ArrayList<Expression<String>>() ;
		EList<CNF> exprs = fm.getExpr() ;
		for (CNF expr : exprs) {
			Expression<String> iExpr = new CNFtoExpression(expr).convert() ; 
			iExprs.add(iExpr);
		}
		irFM.addAllConstraints(iExprs);
		
		return irFM;
	}
	
	
	private static FeatureNode<String> mkFeatureNode(String ftName, FeatureGraph<String> fd) {
		if (ftName.equals("true")) { // FIXME: should not happen?
			return fd.getTopVertex() ;
		}			
		else if (ftName.equals("false")) {
			return fd.getBottomVertex() ; 
		}
		else {
			return new FeatureNode<String>(FeatureName.unquote(ftName));
		}
	}

	private static void treatProd(Production prod, gsd.synthesis.FeatureModel<String> fm) {
		
		FeatureGraph<String> fd = fm.getDiagram() ; 
		String parentFtName = prod.getName() ; 
		FeatureNode<String> fnParent = mkFeatureNode(parentFtName, fd) ; 
		fd.addVertex(fnParent); // dont check?
		
		List<Child> childs = prod.getFeatures();
		for (Iterator<Child> iterator = childs.iterator(); iterator.hasNext();) {
			Child child = (Child) iterator.next();
			treatChildren(child, fnParent, fm);

		}
		
	}
	


	private static void treatChildren(Child c, FeatureNode<String> fnParent, gsd.synthesis.FeatureModel<String> fm) {
	
		FeatureGraph<String> fd = fm.getDiagram() ; 
	
		if (c instanceof Xorgroup) {
			Xorgroup g = (Xorgroup) c;
			EList<String> features = g.getFeatures();
			addGroup(features, fnParent, fd, FeatureEdge.XOR);
	
	
		} else if (c instanceof Orgroup) {
			Orgroup g = (Orgroup) c;
			EList<String> features = g.getFeatures();
			addGroup(features, fnParent, fd, FeatureEdge.OR);	
		}
	
		else if (c instanceof Mutexgroup) {
			Mutexgroup g = (Mutexgroup) c;
			EList<String> features = g.getFeatures();
			addGroup(features, fnParent, fd, FeatureEdge.MUTEX);	
		}
	
		else if (c instanceof Optional) {
			Optional o = (Optional) c;
			String ftName = o.getName() ;
			FeatureNode<String> fnChild = mkFeatureNode(ftName, fd) ; // new FeatureNode<String>(ftName);
			fd.addVertex(fnChild);
			fd.addEdge(fnChild, fnParent, FeatureEdge.HIERARCHY);
	
		} else if (c instanceof Mandatory) {
			Mandatory m = (Mandatory) c;
			String ftName = m.getName() ;
			FeatureNode<String> fnChild =  mkFeatureNode(ftName, fd) ; // new FeatureNode<String>(ftName);
			fd.addVertex(fnChild);
			fd.addEdge(fnChild, fnParent, FeatureEdge.HIERARCHY);
			fd.addEdge(fnChild, fnParent, FeatureEdge.MANDATORY);
			
	
		} else {
			// should never be reached
			FMLShell.getInstance().setError(
					"Unexpected error, unknown child feature: " + c);
			return ;
		}
	
	
	}

	private static void addGroup(EList<String> features, FeatureNode<String> fnParent,	FeatureGraph<String> fd, int kindOfEdge) {
		Collection<FeatureNode<String>> fnSources = new HashSet<FeatureNode<String>>();
		for (String ftName : features) {
			FeatureNode<String> fn = mkFeatureNode(ftName, fd) ; 
			fnSources.add(fn);
			fd.addVertex(fn);
			fd.addEdge(fn, fnParent, FeatureEdge.HIERARCHY);
		}
		
		fd.addEdge(fnSources, fnParent, kindOfEdge);
		
	}

	public static gsd.synthesis.FeatureModel<String> getInternalFM(String strfm) {
				
		String completeStrFM = strfm ; 
		if (!strfm.startsWith("FM (")) {
			completeStrFM = "FM (" + strfm + " )" ; 
		}
		
		String satanizeStrFM = FeatureName.normalize(completeStrFM);
		
			
		FeatureModelVariable fmv = null ;
		try {
			
			fmv = (FeatureModelVariable) new FMLBasicInterpreter(FMLShell.getInstance()).eval(satanizeStrFM);
			
		} catch (FMLFatalError e) {
			FMLShell.getInstance().printError("Impossible to parse feature model " + satanizeStrFM + "\n" + 
											e.getMessage());
			
		} catch (FMLAssertionError e) {
			FMLShell.getInstance().printError("Impossible to parse feature model " + satanizeStrFM + "\n" +  e.getMessage());
		}
		
		return fmv.getFm() ; 

	}

	

	/**
	 * @param fm
	 *            the feature model
	 * @return determine whether the feature model is syntactically well-formed
	 *         i.e., FM (A : B C ; C : B A ;)
	 */
	private boolean checkWellFormed(FeatureModel fm) {
		if (_checker == null)
			_checker = new FeatureModelChecker(fm);
		return _checker.isSyntacticallyValid();
	}

	private FeatureModel parseFile(String filename) {
		_LOGGER.debug(
				"parsing feature model (file) " + filename);

		File file = FMLShell.getInstance().searchFile(filename);
		FMLFeatureModelReader fmReader = new FMLFeatureModelReader(false); // no
																			// standalone

		return fmReader.parseFile(file);

	}

	/**
	 * @param tvlContent tvl input (not filename!)
	 */
	public static FeatureModelVariable mkTVLModel(String tvlContent) throws Exception {
		TVLTranslator translator = new TVLTranslator(tvlContent);
		FeatureModel fmTranslated = translator.getFAMILIARFeatureModel() ; 
		gsd.synthesis.FeatureModel<String> ifm = mkInternalFM(fmTranslated);
		assertNotNull (ifm);
		FeatureModelVariable fmv = new FeatureModelVariable("", ifm);
		return fmv ;
		
	}
	
	public static FMLFeatureModel parseDimacsWithSAT(String dimacs) {
		// FIXME @FeatureIDE 
		FMLDimacsReaderSAT dimacsReeader = new FMLDimacsReaderSAT() ; 
		SATFMLFormula fla = dimacsReeader.parseSATFMLFormula(dimacs) ; 
		return new FeatureModelVariableSATFormula("", fla);
	}

	public static FMLFeatureModel parseDimacsWithSAT(File dimacs) {
		// FIXME @FeatureIDE 
		FMLDimacsReaderSAT dimacsReeader = new FMLDimacsReaderSAT() ; 
		SATFMLFormula fla = dimacsReeader.parseSATFMLFormula(dimacs) ; 
		return new FeatureModelVariableSATFormula("", fla);
	}
	
	public static FeatureModelVariableBDDFormula parseDimacsWithBDD(String dimacs, BDDBuilder<String> builder) {
		FMLDimacsReaderBDD dimacsReeader = new FMLDimacsReaderBDD() ; 
		Collection<Expression<String>> exprs = dimacsReeader.parseExpressions(dimacs) ;
		BDD r = builder.one() ; 
		Set<String> domain = new HashSet<String>() ;
		for (Expression<String> e : exprs) {
				r.andWith(builder.mkExpression(e));
				domain.addAll(ExpressionUtil.getAllFeatures(e));				
			}
		Formula<String> fla = new FormulaAnalyzer<String> (new Formula<String>(r, domain, builder), builder).uniformize();
		
					
		return new FeatureModelVariableBDDFormula("", fla, builder);
	}
	
	public static FeatureModelVariableBDDFormula parseDimacsWithBDD(File dimacsFile, BDDBuilder<String> builder) {
		FMLDimacsReaderBDD dimacsReader = new FMLDimacsReaderBDD() ; 
		
		try {
			Collection<Expression<String>> exprs = dimacsReader.parseExpressions(dimacsFile);
			BDD r = builder.one() ; 
			Set<String> domain = new HashSet<String>() ;
			for (Expression<String> e : exprs) {
					r.andWith(builder.mkExpression(e));
					domain.addAll(ExpressionUtil.getAllFeatures(e));				
				}
			Formula<String> fla = new FormulaAnalyzer<String> (new Formula<String>(r, domain, builder), builder).uniformize();
			return new FeatureModelVariableBDDFormula("", fla, builder);
		} catch (IOException e1) {
			FMLShell.getInstance().printError("Unable to parse dimacs file " + e1);
			return null ;
		}

		
	}

	

}
