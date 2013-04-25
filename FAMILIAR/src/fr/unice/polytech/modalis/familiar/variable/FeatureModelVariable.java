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
package fr.unice.polytech.modalis.familiar.variable;

import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.isInOR;
import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.isInXOR;
import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.isOptional;
import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.isRoot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

import javax.naming.OperationNotSupportedException;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.SliceMode;

import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModelException;
import splar.core.fm.FeatureModelStatistics;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.XMLFeatureModel;
import splar.core.fm.reasoning.FMReasoningException;
import splar.core.heuristics.FTPreOrderSortedECTraversalHeuristic;
import splar.core.heuristics.VariableOrderingHeuristic;
import splar.core.heuristics.VariableOrderingHeuristicsManager;
import splar.plugins.reasoners.bdd.javabdd.ReasoningWithBDD;
import splar.plugins.reasoners.sat.sat4j.FMReasoningWithSAT;
import splar.plugins.reasoners.sat.sat4j.ReasoningWithSAT;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.experimental.FMtoProtovis;
import fr.unice.polytech.modalis.familiar.experimental.MutexGroup;
import fr.unice.polytech.modalis.familiar.experimental.XorGroup;
import fr.unice.polytech.modalis.familiar.fm.FGroupBuilder;
import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.FMLSynthetizer;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelCloner;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelGraphvizRenderer;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelPrinter;
import fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils;
import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel;
import fr.unice.polytech.modalis.familiar.fm.basic.IFeature;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.fm.featureide.AllConfigsSAT;
import fr.unice.polytech.modalis.familiar.gui.GDisplayPrefuseHandler;
import fr.unice.polytech.modalis.familiar.interpreter.ComparisonStrategy;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.operations.AllConfigsBDD;
import fr.unice.polytech.modalis.familiar.operations.CliquesComputation;
import fr.unice.polytech.modalis.familiar.operations.ConstraintSimplificationStrategy;
import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.operations.EGBuilder;
import fr.unice.polytech.modalis.familiar.operations.ExclusionGraphUtil;
import fr.unice.polytech.modalis.familiar.operations.ExpressionUtility;
import fr.unice.polytech.modalis.familiar.operations.FMLMerger;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.FMSlicerBDD;
import fr.unice.polytech.modalis.familiar.operations.FMSlicerBDDWithSPLOT;
import fr.unice.polytech.modalis.familiar.operations.FormulaAnalyzer;
import fr.unice.polytech.modalis.familiar.operations.IConstraintReasoner;
import fr.unice.polytech.modalis.familiar.operations.IGBuilderDomain;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.operations.KSTReport;
import fr.unice.polytech.modalis.familiar.operations.KSynthesis;
import fr.unice.polytech.modalis.familiar.operations.KSynthesisBDD;
import fr.unice.polytech.modalis.familiar.operations.KnowledgeSynthesis;
import fr.unice.polytech.modalis.familiar.operations.Mode;
import fr.unice.polytech.modalis.familiar.operations.MyFMReasoningWithBDD;
import fr.unice.polytech.modalis.familiar.operations.MyFMReasoningWithSAT;
import fr.unice.polytech.modalis.familiar.operations.SatisfiableStrategy;
import fr.unice.polytech.modalis.familiar.operations.SlicerStrategy;
import fr.unice.polytech.modalis.familiar.operations.SynthesisStrategy;
import fr.unice.polytech.modalis.familiar.operations.featureide.FMComparatorSATFeatureIDE;
import fr.unice.polytech.modalis.familiar.operations.featureide.FMLConstraintReasoner;
import fr.unice.polytech.modalis.familiar.operations.featureide.FMQuotient;
import fr.unice.polytech.modalis.familiar.operations.featureide.FMSlicerSAT;
import fr.unice.polytech.modalis.familiar.operations.featureide.KSynthesisSAT;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.parser.ConvertAnalyzer;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMerger;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMergerFactory;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMergerStrategy;
import fr.unice.polytech.modalis.familiar.parser.NameSpace;
import fr.unice.polytech.modalis.familiar.test.featureide.SATFeatureIDEFormula;
import fr.unice.polytech.modalis.familiar.test.regression.SPLOTUtility;
import fr.unice.polytech.modalis.familiar.variable.featureide.FMDisplayFeatureIDE;
import fr.unice.polytech.modalis.utils.FileSerializer;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Excludes;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FGBuilder;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;
import gsd.synthesis.Requires;

/**
 * @author macher
 * edited by Taran
 *
 */
public class FeatureModelVariable extends VariableImpl implements FMLFeatureModel {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureModelVariable.class);

	protected gsd.synthesis.FeatureModel<String> _fm;
	
	protected boolean _synthesizedFM = true ;


	/**
	 * in the default implementation (here), we compute again the formula
	 */
	protected Formula<String> _formula;

	public FeatureModelVariable(String name,
			gsd.synthesis.FeatureModel<String> fm, Formula<String> formula) {
		this(name, fm, formula, NSFactory.mkEmpty());
	}

	public FeatureModelVariable(String name,
			gsd.synthesis.FeatureModel<String> fm, Formula<String> formula,
			NameSpace ns) {
		this.name = name;
		this._fm = fm;
		this._formula = formula;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	// lazy mechanism
	public FeatureModelVariable(String assigner,
			gsd.synthesis.FeatureModel<String> fm) {
		this(assigner, fm, null);
	}

	public Formula<String> getFormula() {
		/*
		 * TODO (lazy mechanism -- currently, we keep up-to-date the formula
		 * according to the current feature model)
		 */
		Formula<String> fla = getBuilder().mkFeatureModel(_fm) ; //.id();
		setFormula(fla);
		return _formula;
	}
	
	public Formula<String> getFormulaAsIs() {
		return _formula ; 
	}

	public Formula<String> getSPLOTFormula() {
		/*
		 * TODO (lazy mechanism -- currently, we keep up-to-date the formula
		 * according to the current feature model)
		 */
		ReasoningWithBDD reasoner = getSPLOTBDDReasoner();
		Formula<String> fla = new Formula<String>(reasoner.getBDD(),
				_fm.features(), null);
		setFormula(fla);
		return _formula;
	}

	/**
	 * convert Formula SPLOT into Formula FML (names of variable should
	 * coincide) the overhead is negligible (mean: it is still relevant to use
	 * heuristics of SPLOT)
	 * 
	 * @param builder
	 *            used to find corresponding names in FML universe
	 * @return an aligned formula SPLOT (typically to compare SPLOT formula with
	 *         FML formula)
	 */
	public Formula<String> getSPLOTFormulaAligned(BDDBuilder<String> builder) {
		/*
		 * TODO (lazy mechanism -- currently, we keep up-to-date the formula
		 * according to the current feature model)
		 */

		splar.core.fm.FeatureModel featureModel = toSPLOT();

		assert (featureModel != null);

		Formula<String> fla = mkSPLOTFlaAligned(featureModel, builder);

		setFormula(fla);
		return _formula;
	}

	public Formula<String> mkSPLOTFlaAligned(
			splar.core.fm.FeatureModel featureModel, BDDBuilder<String> builder) {

		// create BDD variable order heuristic
		String heuristicName = "Pre-CL-MinSpan"; // "Pre-CL-Size" ; //
		new FTPreOrderSortedECTraversalHeuristic("Pre-CL-MinSpan",
				featureModel, FTPreOrderSortedECTraversalHeuristic.FORCE_SORT);
		// new FTPreOrderSortedECTraversalHeuristic(heuristicName, featureModel,
		// FTPreOrderSortedECTraversalHeuristic.SIZE_SORT);

		VariableOrderingHeuristic heuristic = VariableOrderingHeuristicsManager
				.createHeuristicsManager().getHeuristic(heuristicName);

		// BDD construction parameters
		// - Tuning this parameters can be tricky at times and may require
		// playing a bit
		// - For the purpose of this example let's assume "large enough" values
		int bddNodeNum = 10000; // sets the initial size of the BDD table
		int bddCacheSize = 10000; // sets the size of the BDD cache table

		int maxBuildingtime = 600000;
		// Creates the BDD reasoner
		MyFMReasoningWithBDD reasoner = new MyFMReasoningWithBDD(featureModel,
				heuristic, bddNodeNum, bddCacheSize, maxBuildingtime,
				"pre-order");

		// alignment
		Map<String, Integer> alignedVarToInt = new HashMap<String, Integer>();

		Collection<FeatureTreeNode> fnodes = featureModel.getNodes();
		for (FeatureTreeNode featureTreeNode : fnodes) {
			String realName = featureTreeNode.getName();
			String splotID = featureTreeNode.getID();
			if (featureTreeNode instanceof FeatureGroup)
				continue;
			if ((realName.indexOf("synth") != -1)
					&& !builder.getFeatureMap().containsKey(realName)) {
				builder.add(realName);
			}
			if (!builder.getFeatureMap().containsKey(realName)) { // may happen
																	// if
																	// formula
																	// in FML
																	// has not
																	// been
																	// already
																	// created!
				// FMLShell.getInstance().printWarning("Error (?) in translating SPLOT BDD in FML universe vName="
				// + realName);
				builder.add(realName);
				// return null ;
			}
			int fmlID = builder.getFeatureMap().get(realName);
			alignedVarToInt.put(splotID, fmlID);
		}

		// Initialize the reasoner (BDD is created at this moment)
		try {
			reasoner.init(alignedVarToInt, builder.getFactory()); // varName2IndexMap={_r1=1,
																	// _r2=4,
																	// _r0=2,
																	// _r3=3}
		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to init the SPLOT/SPLAR reasoner: "
							+ e.getMessage());
			e.printStackTrace();
			return null;
		}

		BDD bddSplot = reasoner.getBDD();
		Formula<String> fla = new Formula<String>(bddSplot, _fm.features(),
				null);
		return fla;
	}

	public void setFormula(Formula<String> formula) {
		this._formula = formula;
	}

	public gsd.synthesis.FeatureModel<String> getFm() {
		return _fm;
	}

	public void setFm(gsd.synthesis.FeatureModel<String> fm) {
		this._fm = fm;
	}

	@Override
	public String getSpecificValue() {
		// SCALA:
		// return
		// fr.unice.polytech.modalis.familiar.fm.scala.FMConverter.convertToFMCalc(getFm());
		String fmRepresentation = new FeatureModelPrinter(this).toString();
		fmRepresentation = normalizeRepresentation(fmRepresentation);
		return fmRepresentation;
	}

	public String getSyntacticalRepresentation() {
		String fmRepresentation = new FeatureModelPrinter(this)
				.pureSyntacticalRepresentation(); // avoid formula computation
		fmRepresentation = normalizeRepresentation(fmRepresentation);
		return fmRepresentation;
	}

	/**
	 * so weird
	 * @param strFM
	 * @return
	 */
	private String normalizeRepresentation(String strFM) {

		String fmlRepresentation = strFM;
		if (fmlRepresentation.indexOf("1:") != -1) {
			fmlRepresentation = fmlRepresentation.substring(fmlRepresentation
					.indexOf(";") + 1); // 1: A ; A : .... => A : .....
			//fmlRepresentation = fmlRepresentation.substring(fmlRepresentation
				//	.indexOf(":") + 1);// 1: A ; A : .... => A ; A : .....
			
			fmlRepresentation = fmlRepresentation.trim();
		}

		fmlRepresentation = fmlRepresentation.replace("(1", "(true");
		fmlRepresentation = fmlRepresentation.replace(" 1", " true");

		fmlRepresentation = fmlRepresentation.replace("(0", "(false");
		fmlRepresentation = fmlRepresentation.replace(" 0", " false");
		return fmlRepresentation;
	}

	@Override
	public RType getRType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public Variable copy() {

		// clone of the feature model and the formula
		FeatureModel<String> fmCopy = FeatureModelCloner.clone(_fm);

		return new FeatureModelVariable(name, fmCopy, null, ns);

	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof FeatureModelVariable) {
			FeatureModelVariable fmw = (FeatureModelVariable) vari;
			setFm(fmw.getFm());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	public splar.core.fm.FeatureModel toSPLOT() {
		String splot = ConvertAnalyzer.convert(this, FMFormat.FSPLOT);
		File tmp;
		try {
			tmp = File.createTempFile("splot" + new Random().nextInt(), ".xml");
		} catch (IOException e) {
			FMLShell.getInstance().setError(
					"Unable to transform (serialization) the feature model to SPLOT/SPLAR: "
							+ e.getMessage());
			return null;
		}
		String filename = tmp.getAbsolutePath();
		try {
			FileSerializer.write(filename, splot);
		} catch (IOException e) {
			FMLShell.getInstance().setError(
					"Unable to transform the feature model to SPLOT/SPLAR: "
							+ e.getMessage());
			return null;
		}
		splar.core.fm.FeatureModel featureModel = new XMLFeatureModel(filename,
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		// load feature model from
		try {
			featureModel.loadModel();
		} catch (FeatureModelException e) {
			FMLShell.getInstance()
					.setError(
							"Feature Model exception: Unable to transform the feature model to SPLOT/SPLAR: "
									+ e.getMessage() + "\t splot=" + splot);
			return null;
		}
		return featureModel;

	}

	/**
	 * display graphically the content of the feature model label,
	 * */
	public void gdisplay() {
		if (FMLShell.getInstance().isEclipseBased()) {
			// FIXME @FeatureIDE 
			new FMDisplayFeatureIDE(this).perform() ;
		} else { 
			// Prefuse stuff: First, create an event source with FMV name
	        final GDisplayPrefuseEvent gdpEvent = new GDisplayPrefuseEvent(this.name);
	        // Second, create an observer
	        final GDisplayPrefuseHandler gdpHandler = new GDisplayPrefuseHandler();
	        // Third, subscribe the observer to the event source
	        gdpEvent.addObserver(gdpHandler);
	        // Finally, start the event thread
	        Thread thread = new Thread(gdpEvent);
	        thread.start();
		}
	}

	

	/**
	 * display graphically the content of a feature model, i.e., the feature
	 * model in Zent/GET using DOT/graphviz facilities
	 */
	/*
	 * public void gdisplayZest() {
	 * 
	 * FMZestRendererView zestRenderer = FamiliarRun.getZestRendererView();
	 * Composite zestContainer = zestRenderer.getParent(); int childcount =
	 * zestContainer.getChildren().length; for (int i = childcount-1; i >= 0;
	 * i--) { zestContainer.getChildren()[i].dispose(); }
	 * 
	 * Graph zestGraph = new
	 * FMZestRenderer(this).computeZestGraph(zestContainer);
	 * zestRenderer.setGraph(zestGraph); }
	 */

	public String toDOT() {
		FeatureModelGraphvizRenderer<String> dotifier = new FeatureModelGraphvizRenderer<String>(
				_fm.getDiagram(), getIdentifier());
		String dotcontent = dotifier.toString();
		return dotcontent;
	}


	@Override
	public FeatureVariable root() {
		FeatureGraph<String> fgraph = getFm().getDiagram();
		FeatureNode<String> ntop = fgraph.getTopVertex();
		Set<FeatureNode<String>> childs = fgraph.children(ntop);
		FeatureNode<String> noderoot = childs.iterator().next(); // first one!
		String rootName = noderoot.toString();

		return new FeatureVariable(null, rootName, this);
	}

	@Override
	public boolean isValid() {
		return isValid (FMLShell.getInstance().getSatisfiableStrategy()) ; 
	}
	
	public boolean isValid(SatisfiableStrategy saStrategy) {
		if (saStrategy == SatisfiableStrategy.BDD_FML) {
			Formula<String> fla = getFormula() ; 
			return !fla.isZero();
		}
		else if (saStrategy == SatisfiableStrategy.BDD_SPLOT) {
			Formula<String> fla = getSPLOTFormula() ; 
			return !fla.isZero();
		}
		else if (saStrategy == SatisfiableStrategy.SAT_FML) {
			// FIXME @FeatureIDE 
			return new SATFMLFormula(this).isValid() ; 
		}
		else if (saStrategy == SatisfiableStrategy.SAT_FEATUREIDE) {
			// FIXME @FeatureIDE 
			return new SATFeatureIDEFormula(this).isValid() ; 
		}
		
		FMLShell.getInstance().printError("Unable to characterize the satisfiable strategy " + saStrategy);
		return false ; 
		
	}

	@Override
	public SetVariable features() {
		Set<Variable> varsR = new HashSet<Variable>();
		FeatureGraph<String> fgraph = getFm().getDiagram();
		Set<FeatureNode<String>> vtx = fgraph.vertices();

		for (FeatureNode<String> fn : vtx) {
			// if (!fn.isBottom() && !fn.isTop() && !fn.isDead())
			if (!fn.getFeature().equals(FeatureGraphFactory.DEFAULT_TOP_STRING)
					&& !fn.getFeature().equals(
							FeatureGraphFactory.DEFAULT_BOTTOM_STRING)) {
				varsR.add(new FeatureVariable("", fn.getFeature(), this));
			}

		}
		SetVariable sv = new SetVariable(varsR);
		return sv;
	}

	@Override
	public boolean setMandatory(IFeature ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOptional(IFeature ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAlternative(IFeature ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOr(IFeature ft) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Cleanup the feature model remove dead features full mandatory features
	 * simplify constraints
	 */
	public void cleanup() {
		
		Formula<String> formula = getFormula() ; 
		Formula<String> cleanFla = new FormulaAnalyzer<String>(formula, getBuilder()).removeDeadFeatures();
		formula.free() ; 
		KSynthesisBDD ksynth = new KSynthesisBDD(cleanFla, getBuilder());
		
		
		KnowledgeSynthesis kst = new KnowledgeSynthesis() ;
		FeatureModel<String> oHierarchy = getHierarchy() ;
		
		FeatureGraph<String> cleanHierarchy = _removeFtsInHierarchy(oHierarchy.getDiagram(), Sets.difference(formula.getDomain(), cleanFla.getDomain()));
		
		kst.setHierarchy(cleanHierarchy);
		ksynth.setKST(kst);	
		
		FeatureModelVariable fmvCleanedUp = ksynth.build() ;
		_fm = fmvCleanedUp.getFm() ; 
		_formula = cleanFla ; 


	}
	
	private FeatureGraph<String> _removeFtsInHierarchy(FeatureGraph<String> fd, Set<String> ftsToRemove) {
		FeatureModelVariable oFD = new FeatureModelVariable("", new FeatureModel<String>(fd));
		for (String ft : ftsToRemove) {
			try {
				oFD.removeFeature(ft);
			} catch (Exception e) {
			}
		}
		return oFD.getFm().getDiagram() ; 
	}

	public void cleanup2() {
		/**** first, remove dead features ****/
		Set<String> deads = deads();
		for (String ftDead : deads) {
			_LOGGER.debug(
					"dead feature " + ftDead + " removed");
			try {
				FeatureNode<String> fnDead = _fm.getDiagram().findVertex(
						(String) ftDead);
				removeFeature(fnDead); // remove also the constraints

			} catch (Exception e) {

				_LOGGER.debug(
						"Should not happen for deads=" + deads
								+ " in cleanup operation " + e.getMessage());
			}
		}

		/****
		 * second: set mandatory to full mandatory features using the cores
		 * features
		 ****/
		Set<String> cores = cores();
		FeatureGraph<String> fg = _fm.getDiagram();
		for (String ftCore : cores) {
			_LOGGER.debug(
					"core feature " + ftCore + " to set");
			try {
				FeatureNode<String> fnCore = fg.findVertex((String) ftCore);
				Set<FeatureNode<String>> parents = fg.parents(fnCore);
				if (parents.size() > 1)
					_LOGGER.debug(
							"More than one parent -- currently not supported ftCore="
									+ ftCore + " parents=" + parents);
				if (parents.size() == 1) {
					fg.addEdge(fnCore, parents.iterator().next(),
							FeatureEdge.MANDATORY);

					// if Xor-group, normally the other are dead and previously
					// deleted
					// in Or-group, this is more subtle
					// e.g. (1) say that F is a core feature in Or-group A :
					// (F|G|H)+ you have to make something like A : F (G|H)+
					// with multi-groups that we do not currently support
					// e.g. (2) say that F is a core feature in Or-group A :
					// (F|G)+ you have to make something like A : F (G)+
					// which is equivalent to A : F [G]
					if (isInOR(fg, fnCore))
						for (FeatureEdge e : fg.outgoingEdges(fnCore)) {
							if (e.getType() == FeatureEdge.OR) {
								_LOGGER.debug(
										"Core in an OR-group");
								Set<FeatureNode<String>> orGroup = fg
										.getSources(e);
								if (orGroup.size() == 2) {
									_LOGGER.debug(
											"We can simplify the OR-group="
													+ orGroup);
									// transform in optional feature the sibling
									// feature (the hierarchy relation is
									// already set)
									// so delete the group!
									fg.removeEdge(e);

									/*
									 * @Deprecated for (FeatureNode<String> fn :
									 * orGroup) { if (fn.equals(fnCore))
									 * continue ; else { FeatureNode<String>
									 * sibling = fn ; fg.addEdge(sibling,
									 * fg.getTarget(e), FeatureEdge.HIERARCHY);
									 * // no need actually }
									 * 
									 * }
									 */

								} else {
									FMLShell.getInstance().printWarning(
											"Cannot simplify OR-group="
													+ orGroup);
								}

							}
						}
				}

				// set true to each occurence in constraints and simplify it!
				Set<Expression<String>> constraints = _fm.getConstraints();
				for (Expression<String> expression : constraints) {
					ExpressionUtility.replaceOccurenceInExpression(
							fnCore.getFeature(),
							FeatureGraphFactory.DEFAULT_TOP_STRING, // true
																	// value
							expression);

				}

			} catch (Exception e) {
				_LOGGER.debug(
						"Should not happen for cores=" + cores
								+ " in cleanup operation " + e.getMessage());
			}

		}

		setFm(FeatureModelCloner.clone(_fm)); // update (weird)

		BDDBuilder<String> builder = getBuilder() ;

		/**** finally, simplifying constraints *****/

		// core features (induced by the hierarchy) to true
		for (Expression<String> expr : _fm.getConstraints()) {
			Set<String> fts = ExpressionUtil.getAllFeatures(expr);
			for (String ft : fts) {
				if (cores.contains(ft)) {
					ExpressionUtility.replaceOccurenceInExpression(ft,
							FeatureGraphFactory.DEFAULT_TOP_STRING, // true
																	// value
							expr);
				}
			}
		}

		for (Expression<String> expression : _fm.getConstraints()) {
			BDD bddExpression = builder.mkExpression(expression);

			if (bddExpression.isOne()) { // no need tautology
				_LOGGER.debug(
						"tautology detected: " + expression.toString());
				removeConstraint(_fm, expression);
				// if (!_fm.removeConstraint(expression))
				// _LOGGER.debug("Unable to remove tautology");
			}

			// if the expression is like !feature, remove it (dead)
			else if (expression.getType() == ExpressionType.NOT) {
				Expression<String> lexpr = expression.getLeft();
				if (lexpr.getType() == ExpressionType.FEATURE) {
					removeConstraint(_fm, expression);
					try {
						FeatureNode<String> fnode = _fm.getDiagram()
								.findVertex(lexpr.getFeature());
						_fm.getDiagram().removeVertex(fnode);
					} catch (Exception e) {

					}

				}
			}

			else {

				// constraint induced by hierarchy (e.g., parent-child relation)
				Formula<String> oflaHierarchy = builder.mkHierarchy(_fm
						.getDiagram());
				BDD bddHierarchy = oflaHierarchy.getBDD();

				BDD bddHierarchyExpr = bddHierarchy.and(bddExpression);
				if (bddHierarchy.equals(bddHierarchyExpr)) {
					_LOGGER.debug(
							"constraint expressed by hierarchy: "
									+ expression.toString());
					removeConstraint(_fm, expression);
				}

			}

		}
	}

	/**
	 * FIXME weird but the removeConstraint in gsd is rather strange and
	 * restrictive
	 * 
	 * @param fm
	 * @param expr2Remove
	 */
	private boolean removeConstraint(FeatureModel<String> fm,
			Expression<String> expr2Remove) {
		
		boolean rmOK = true ; 
		Set<Expression<String>> ocsts = fm.getConstraints();
		if (!ocsts.contains(expr2Remove))
			rmOK = false ; 
		fm.removeAllConstraints();
		for (Expression<String> expression : ocsts) {
			if (!expression.equals(expr2Remove)) {
				if (!fm.addConstraint(expression)) {
					_LOGGER.debug(
							"unable to add a constraint (when removing)=" + expression);
					
				}
			}
		}
		return rmOK  ; 
	}

	@Override
	public double CTCR() {
		// alternative SPLOT reuse
		// TODO: write in FAMILIAR
		Set<Expression<String>> csts = _fm.getConstraints();
		Set<String> ftsCst = new HashSet<String>();
		for (Expression<String> cst : csts) {
			ftsCst.addAll(ExpressionUtil.getAllFeatures(cst));
		}
		int nFTsInCst = ftsCst.size();

		Set<String> fts = _fm.features();
		int nFTs = fts.size();
		return nFTsInCst / (1.0 * nFTs);
	}

	public double CTCR_SPLOT() {
		// SPLOT reuse
		FeatureModelStatistics statsFM = new FeatureModelStatistics(toSPLOT());
		return statsFM.getECRepresentativeness();
	}

	@Override
	public double counting() {
		return counting (FMLShell.getInstance().getCountingStrategy()) ; // default solution
	}
	
	public double counting(CountingStrategy countingStrategy) {
		if (countingStrategy == CountingStrategy.BDD_FML) // efficient
			return _countingBDD();
		else if (countingStrategy == CountingStrategy.BDD_SPLOT) // most efficient
			return _countingBDDSPLOT();
		else if (countingStrategy == CountingStrategy.SAT_FEATUREIDE) // not efficient at all
			return _countingSATFeatureIDE();
		else if (countingStrategy == CountingStrategy.SAT_FML) // not efficient at all
			return _countingSAT();

		FMLShell.getInstance().printError(
				"Unknown counting strategy: " + countingStrategy);
		return Double.NaN;

	}
	
	/**
	 * @return the number of configurations of a feature model (based on SAT
	 *         solver)
	 */
	protected double _countingSATFeatureIDE() {
		// FIXME @FeatureIDE 
		return new SATFeatureIDEFormula(this).counting() ;
		/*DEPRECATED 
		de.ovgu.featureide.fm.core.FeatureModel fm = this.toFeatureIDE();
		long c = new FeatureIDEOperations().card(fm);
		double r = (double) c;
		return r;*/
	}
	
	/**
	 * @return the number of configurations of a feature model (based on SAT
	 *         solver)
	 */
	protected double _countingSAT() {
		// FIXME @FeatureIDE 
		return new SATFMLFormula(this).counting() ;
	}
	
	/**
	 * @return the number of configurations of a feature model (based on BDD)
	 */
	protected double _countingBDD() {
		Formula<String> fla = getFormula(); // builder.mkFeatureModel(_fm); //
		if (fla.isZero())
			return 0;
		return new FormulaAnalyzer<String>(fla, getBuilder()).counting();
	}
	
	protected double _countingBDDSPLOT() {

		ReasoningWithBDD reasoner = getSPLOTBDDReasoner();
		try {
			double c = reasoner.countValidConfigurations();
			return c;
		} catch (OperationNotSupportedException e) {
			// should never happen, the operation is supported ;-)
			FMLShell.getInstance()
					.setError(
							"Unable to use the SPLOT/SPLAR reasoner: "
									+ e.getMessage());
			return Double.NaN;
		}

	}

	public ReasoningWithBDD getSPLOTBDDReasoner() {
		splar.core.fm.FeatureModel featureModel = toSPLOT();
		assert (featureModel != null);
		return SPLOTUtility.getSPLOTBDDReasoner(featureModel);
	}

	/**
	 * @param node2Remove
	 * @return remove the feature in the hierarchy and simplify constraints
	 */
	public boolean removeFeature(String ftName) {

		FeatureNode<String> node2Remove = null;
		try {
			node2Remove = _fm.getDiagram().findVertex(ftName);
		} catch (Exception e) {
			return false;
		}

		if (node2Remove == null)
			return false;

		return removeFeature(node2Remove);

	}

	/**
	 * @param node2Remove
	 * @return remove the feature in the hierarchy and simplify constraints
	 */
	public boolean removeFeature(FeatureNode<String> node2Remove) {

		if (!removeRec(node2Remove))
			return false;

		// replace node2remove by false value in each expression
		Set<Expression<String>> constraints = this.getFm().getConstraints();
		for (Expression<String> expression : constraints) {
			ExpressionUtility.replaceOccurenceInExpression(
					node2Remove.getFeature(),
					FeatureGraphFactory.DEFAULT_BOTTOM_STRING, // false value
					expression);
		}

		return true;

	}

	/**
	 * @param node
	 *            the feature to remove
	 * @return recursively remove the feature and the children features
	 */
	private boolean removeRec(FeatureNode<String> node) {

		FeatureGraph<String> fgraph = this.getFm().getDiagram();
		Set<FeatureNode<String>> descendants = null;
		try {
			descendants = fgraph.descendants(node);
		} catch (IllegalArgumentException e) { // strange
			_LOGGER.debug(
					"descendants=null " + e.toString());
		}
		if (!simpleRemove(node))
			return false;
		if (descendants != null)
			for (FeatureNode<String> fn : descendants) { // all descendants! (no
															// need to be
															// recursive)
				_LOGGER.debug(
						"removing descendant=" + fn);
				if (!simpleRemove(fn))
					return false;

			}
		return true;
	}

	/**
	 * @param node
	 *            the feature to remove
	 * @return delete the vertex from the graph of the feature model and
	 *         associated edges
	 */
	private boolean simpleRemove(FeatureNode<String> node) {

		// check that is actually exists in the graph
		FeatureGraph<String> fg = getFm().getDiagram();
		try {
			fg.findVertex(node.getFeature());
		} catch (IllegalArgumentException e) {
			_LOGGER.debug(
					"does not exist! " + e.toString());
			return false;
		}

		// perform removal
		try {

			Collection<FeatureEdge> outgoing = fg.outgoingEdges(node);

			for (FeatureEdge e : outgoing) {
				Set<FeatureNode<String>> toKeep = new HashSet<FeatureNode<String>>();
				Set<FeatureNode<String>> sources = fg.getSources(e);
				FeatureNode<String> target = fg.getTarget(e);
				for (FeatureNode<String> n : sources) {
					if (!(n.equals(node))) {
						toKeep.add(n);
					}
				}

				if (toKeep.size() > 1)
					fg.addEdge(toKeep, target, e.getType());
				else if (toKeep.size() == 1) {
					// mandatory ? // optional?
					if (e.getType() == FeatureEdge.XOR
							|| e.getType() == FeatureEdge.OR)
						fg.addEdge(toKeep.iterator().next(), target,
								FeatureEdge.MANDATORY);
				} else {
					// nothing
				}
			}

			fg.removeAllEdges(outgoing);

			fg.removeAllEdges(fg.incomingEdges(node));
			fg.removeAllVertices(fg.descendants(node));
			fg.removeVertex(node);

			return true;
		} catch (IllegalArgumentException e) { // strange
			_LOGGER.debug(
					"STRANGE during the removal: should not happen "
							+ e.toString());
			return true;
		}
	}

	/**
	 * @return the set of "dead" features, i.e., features that do not appear in
	 *         any configuration of a feature model
	 */
	@Override
	public Set<String> deads() {
		FormulaAnalyzer<String> fop = new FormulaAnalyzer<String>(getFormula(), getBuilder()); // 		// FMLCommandInterpreter.getBuilder().mkFeatureModel(_fm)
		Set<String> deads = fop.computeDeadFeatures();
		return deads;
	}

	/**
	 * @return the set of "core" features, i.e., features that appear in every
	 *         configuration of a feature model
	 */
	@Override
	public Set<String> cores() {
		FormulaAnalyzer<String> fop = new FormulaAnalyzer<String>(getFormula(), getBuilder()); // 		// FMLCommandInterpreter.getBuilder().mkFeatureModel(_fm)
		Set<String> cores = fop.computeCoreFeatures();
		return cores;
	}
	
	
	
	private BDDBuilder<String> _builder = null ;

	
	private static final SlicerStrategy DEFAULT_SLICER_STRATEGY = SlicerStrategy.BDD ;

	private static final SynthesisStrategy _DEFAULT_SYNTHESIS_STRATEGY = SynthesisStrategy.BDD ; 
	private SlicerStrategy _slicerStrategy = DEFAULT_SLICER_STRATEGY ; 

	/**
	 * @return the _slicerStrategy
	 */
	public SlicerStrategy getSlicerStrategy() {
		return _slicerStrategy;
	}

	/**
	 * @param slicerStrategy the slicerStrategy to set
	 */
	public void setSlicerStrategy(SlicerStrategy slicerStrategy) {
		_slicerStrategy = slicerStrategy;
	}

	public BDDBuilder<String> getBuilder() {
		
		if (_builder == null)
			_builder = FMLCommandInterpreter.getBuilder() ;
		return _builder ; 
	}
	
	public void setBuilder(BDDBuilder<String> builder) {
		_builder = builder ; 		
	}
	
	
	/**
	 * @return cliques of a feature model
	 */
	public SetVariable cliques() {
				
		Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(this, CliquesComputation.BDD) ; 
				//SAT_WITH_BDD_SATISFIABILITY_CHECKS) ; // default 
		Set<Variable> vars = new HashSet<Variable>();
		for (Set<String> cliques : cls) {
			
			Set<Variable> clFts = new HashSet<Variable>();
			for (String cl : cliques) {
				clFts.add(new FeatureVariable(cl, this));
			}			
			vars.add(new SetVariable(clFts));
		}

		SetVariable svs = new SetVariable(vars);		
		
		return svs; 		
	}
	
	public SetVariable atomicSet() {
		Collection<Set<String>> cls = _atomicSet(); 
		Set<Variable> vars = new HashSet<Variable>();
		for (Set<String> cliques : cls) {
			
			Set<Variable> clFts = new HashSet<Variable>();
			for (String cl : cliques) {
				clFts.add(new FeatureVariable(cl, this));
			}			
			vars.add(new SetVariable(clFts));
		}

		SetVariable svs = new SetVariable(vars);		
		return svs; 		
	}
	
	private Collection<Set<String>> _atomicSet() {
		
		
		
		// TODO: we can be more syntactical
		ImplicationGraph<String> g = getImplicationGraphFromFeatureHierarchy(getBuilder());
		/*
		Collection<Set<String>> aS = new HashSet<Set<String>>();
		FeatureGraph<String> fg = getFm().getDiagram();
		Set<FeatureEdge> fes = fg.selectEdges(FeatureEdge.MANDATORY);
		for (FeatureEdge fe : fes) {
			FeatureNode<String> source = fg.getSource(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			
		}
		*/
		
		return DirectedCliqueFinder.INSTANCE.findAll(g);
	}
	

	
	protected static final CliquesComputation DEFAULT_CLIQUES_COMPUTATION = CliquesComputation.BDD ;
	protected CliquesComputation _cliquesHierarchyComputation = DEFAULT_CLIQUES_COMPUTATION  ;
	
	/**
	 * TODO: fix with fullMandatory issues (should return a SetVariable also!)
	 * @return the set of false optional features (string-based representation)
	 */
	public Set<String> falseOptionalFeatures() {
		Set<String> r = new HashSet<String>();
		
		Collection<Set<String>> cliques = CliquesOperationFactory.mkCliques(this, _cliquesHierarchyComputation);
		if (cliques.size() == 0)
			return r;		
		
		FeatureGraph<String> fg = _fm.getDiagram();
		Set<FeatureEdge> hierarchies = fg.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge fe : hierarchies) {
			FeatureNode<String> source = fg.getSource(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			if (KSynthesis.biImplied(source, target, cliques)) {
				if (isInXOR(fg, source) || isInOR(fg, source)
						|| isOptional(fg, source) && !isRoot(source, fg)) // FIXME: free variables?
					r.add(source.getFeature());
			}
		}		
		
		
		return r ; 
	}
	
	
	/**
	 * TODO: Set<FeatureVariable> or even SetVariable? 
	 * FIXME: wrong (here: core features, but should be calculated using cliques)
	 * @return the set of full mandatory features or (false optional features).
	 *         wrong definition: A feature is false optional if it is included in all the products
	 *         of the product line despite not being modeled as mandatory. e.g.,
	 *         FM (A : B [C]; B -> C; ) in which C is full mandatory
	 *         
	 *         good one: A feature is false optional if it is modeled as optional while actually being involved 
	 *         in (1) a mandatory one (real sense of fullMandatory) or (2) being in a non ambigous Mutex/Xor/Or group  
	 *         TODO: I think we have to make the distinction
	 */
	public Set<String> fullMandatoryFeatures() {
	
		return falseOptionalFeatures() ; 
		//return naivefullMandatoryFeatures() ; 
	
	}
	
	
	/**
	 * @return the number of features of the feature model equivalent to size
	 *         fm1.* or size features fm1
	 * 
	 */
	public int nbFeatures() {
		return features().size();
	}

	/**
	 * @return the set of valid configurations of a feature models A
	 *         configuration is a set of variable, each set being a set of
	 *         feature variables so basically it is a set of set -- based on SAT
	 *         solvers or BDD (default)
	 */
	public Set<Variable> configs() {
		return configsBDD();
	}

	/**
	 * @return the set of valid configurations of a feature models A
	 *         configuration is a set of variable, each set being a set of
	 *         feature variables so basically it is a set of set -- based on SAT
	 *         solvers (FeatureIDE reuse)
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public Set<Variable> configsSAT() throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {
		// FIXME @FeatureIDE 
		return AllConfigsSAT.make(this).process();
	}

	/**
	 * @return the set of valid configurations of a feature models A
	 *         configuration is a set of variable, each set being a set of
	 *         feature variables so basically it is a set of set -- based on BDD
	 */
	public Set<Variable> configsBDD() {

		
		AllConfigsBDD allBDD = new AllConfigsBDD(
				getBuilder());
		Set<Set<String>> sols = allBDD.process(getFormula());

		Set<Variable> vars = new HashSet<Variable>();
		for (Set<String> sol : sols) {

			Set<Variable> fts = new HashSet<Variable>();

			for (String ftName : sol) {
				FeatureVariable ft = new FeatureVariable(null, ftName, this); // no
																				// name
				fts.add(ft);
			}

			SetVariable sv = new SetVariable(fts, null); // no name
			vars.add(sv);

		}

		return vars;

	}
	
	public static FeatureModel<String> mkFalseFM() {
		return FeatureGraphFactory.mkStringFactory().mkBottomModel() ;
	}

	public static FeatureModelVariable mkFalse(Formula<String> fla, FeatureModel<String> fm) {
		return new FeatureModelFalseVariable("", fm, fla) ;
	}

	@Override
	public String toString() {
		return getSpecificValue();
	}

	public String toProtovis() {
		// TODO void fm?
		return new FMtoProtovis(this).convert();
	}

	
	public Comparison compareSAT(FeatureModelVariable rfmv,	boolean refactoringBDD) {
		// FIXME @FeatureIDE 
		return new FMComparatorSATFeatureIDE(this, rfmv, refactoringBDD).compare();
	}
	
	
	public Comparison compare(FeatureModelVariable fm) {
		BDDBuilder<String> lbuilder = getBuilder() ; //FMLCommandInterpreter.getBuilder();
		return compare(fm, lbuilder);
	}

	public Comparison compare(FeatureModelVariable fm,
			BDDBuilder<String> abuilder) {

		ComparisonStrategy cmpStrategy = FMLShell.getInstance()
				.getComparisonStrategy();
		if (cmpStrategy == ComparisonStrategy.SAT) // classics, Feature IDE,
													// Batory 2009
			return compareSAT(fm, true);
		else if (cmpStrategy == ComparisonStrategy.BDD) // classics
			return compareBDD(fm, abuilder);
		else if (cmpStrategy == ComparisonStrategy.SAT_FLA) // my SAT (due to FeatureIDE support for abstract)
			return compareSAT_Formula(fm);

		FMLShell.getInstance().printError(
				"Unknown comparison strategy: " + cmpStrategy);
		return null ;

	}

	public Comparison compareSAT_Formula(FeatureModelVariable fmv2) {
		// FIXME @FeatureIDE
		SATFMLFormula fla = new SATFMLFormula(this);
		SATFMLFormula fla2 = new SATFMLFormula(fmv2);
		return fla.compare(fla2) ;
	}

	public Comparison compareBDD(FeatureModelVariable fm,
			BDDBuilder<String> builder) {

		BDD b1 = getFormula().getBDD();
		BDD b2 = fm.getFormula().getBDD();

		if (b1.equals(b2))
			return Comparison.REFACTORING;
		if (diffFormula(fm, builder).isZero())
			return Comparison.SPECIALIZATION;
		if (fm.diffFormula(this, builder).isZero())
			return Comparison.GENERALIZATION;
		return Comparison.ARBITRARY;
	}

	public Formula<String> diffFormula(FeatureModelVariable fm,
			BDDBuilder<String> builder) {
		return this.diffFormula(fm.getFormula(), builder);
	}

	public Formula<String> diffFormula(Formula<String> otherFormula,
			BDDBuilder<String> builder) {
		return FMLMergerBDD.diff(getFormula(), otherFormula, builder);
	}

	public FMReasoningWithSAT getSPLOTSATReasoner() {

		splar.core.fm.FeatureModel fmSplot = toSPLOT();
		return SPLOTUtility.getSPLOTSATReasoner(fmSplot);
	}

	public FMReasoningWithSAT getSPLOTSATReasoner(
			Map<String, Integer> imposedIntToVar) {
		// SAT reasoner construction parameters
		// - "MiniSAT" - name of the SAT4J solver used
		// - Timeout parameter
		int SATtimeout = (int) FMLFormula.SAT_TIMEOUT; 
		splar.core.fm.FeatureModel fmSplot = toSPLOT();

		FMReasoningWithSAT reasoner = new MyFMReasoningWithSAT("MiniSAT",
				fmSplot, SATtimeout, imposedIntToVar);

		// Initialize the reasoner
		try {
			reasoner.init();
		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to load the My SPLOT SAT reasoner " + e);
			return null;
		}

		return reasoner;
	}

	public double countingSATSPLOT() {

		FMReasoningWithSAT satReasoner = getSPLOTSATReasoner();

		try {
			return satReasoner.countValidConfigurations();
		} catch (FMReasoningException e) {
			FMLShell.getInstance().printError(
					"Unable to perform the SPLOT SAT counting " + e);
			return Double.NaN;
		}
	}

	public int depth() {
		// we assume it is a rooted *tree*
		FeatureGraph<String> fg = _fm.getDiagram();
		FeatureNode<String> root = fg.getTopVertex();
		return depth(root, fg, -1); // top vertex is not taken into account,
									// that is why we start from -1

	}
	
	/**
	 * @param fn
	 * @return depth of a given node in the hierarchy
	 */
	public int depth(FeatureNode<String> fn) {
		// we assume it is a rooted *tree*
		FeatureGraph<String> fg = _fm.getDiagram();
		return depth(fn, fg, -1); // top vertex is not taken into account,
									// that is why we start from -1

	}

	private int depth(FeatureNode<String> node, FeatureGraph<String> fg,
			int cdepth) {
		Set<FeatureNode<String>> children = fg.children(node);
		if (children.size() == 0)
			return cdepth;
		int max = cdepth;
		for (FeatureNode<String> child : children) {
			int lmac = depth(child, fg, cdepth + 1);
			max = Math.max(lmac, max);
		}
		return max;
	}

	// FIXME: refactor code in RenameAnalyzer
	public boolean renameFeature(String oname, String newName) {

		FeatureGraph<String> fgraph = _fm.getDiagram();
		Set<String> features = _fm.features();
		FeatureNode<String> oldNode = null;

		try {
			oldNode = fgraph.findVertex(oname);
		} catch (IllegalArgumentException e) {
			FMLShell.getInstance().printWarning(
					"(old) " + oname + " does not exist (" + features + ")");
			return false ; 
		}
		assert (oldNode != null) ; 

		
		if (features.contains(newName)) {
			FMLShell.getInstance().printWarning(
					"(new) " + newName + " already exists (" + features + ")");
			return false;
		}
		

		// we can actually perform the renaming
		FeatureNode<String> newNode = new FeatureNode<String>(newName);
		fgraph.replaceVertex(oldNode, newNode);

		Set<Expression<String>> constraints = _fm.getConstraints();
		for (Expression<String> expression : constraints) {
			ExpressionUtility.replaceOccurenceInExpression(
					oldNode.getFeature(), newName, expression);
		}
		
		return true;

	}

	/**
	 * TODO: facilities as an FML command
	 * 
	 * @return the leaves of the diagram/tree hierarchy
	 */
	public SetVariable leaves() {
		Set<Variable> varsR = new HashSet<Variable>();
		FeatureGraph<String> fgraph = getFm().getDiagram();
		Set<FeatureNode<String>> vtx = fgraph.leaves();

		for (FeatureNode<String> fn : vtx) {
			if (!fn.getFeature().equals(FeatureGraphFactory.DEFAULT_TOP_STRING)
					&& !fn.getFeature().equals(
							FeatureGraphFactory.DEFAULT_BOTTOM_STRING)) {
				varsR.add(new FeatureVariable("", fn.getFeature(), this));
			}

		}
		SetVariable sv = new SetVariable(varsR);
		return sv;
	}

	@Override
	public void free() {
		if (_formula != null)
			_formula.free() ; 
	}

	public String getSyntacticalRepresentationWithoutCst() {
		String fmRepresentation = new FeatureModelPrinter(this)
				.pureSyntacticalRepresentationWithoutConstraint(); // avoid
																	// formula
																	// computation
		fmRepresentation = normalizeRepresentation(fmRepresentation);
		return fmRepresentation;
	}

	public FeatureModel<String> extract(String feature) {

		FeatureModel<String> newFM = FeatureGraphFactory.mkStringFactory().mkTopModel();
		FeatureGraph<String> newFgraph = newFM.getDiagram();

		// FIXME: clone?
		final FeatureGraph<String> ofgraph = _fm.getDiagram();

		/****** we can perform extraction *******/
		FeatureNode<String> fnode = null;
		try {
			fnode = ofgraph.findVertex(feature);
		} catch (IllegalArgumentException e) {
			// or at least try to do it (features should exist!)
			Set<String> features = _fm.features();
			FMLShell.getInstance().printWarning(
					"(old) " + feature + " does not exist (" + features + ")");
			return null;
		}

		// root
		newFgraph.addVertex(fnode);
		newFgraph.addEdge(fnode, newFgraph.getTopVertex(),
				FeatureEdge.HIERARCHY);
		newFgraph.addEdge(fnode, newFgraph.getTopVertex(),
				FeatureEdge.MANDATORY);

		Set<FeatureNode<String>> desc = ofgraph.descendants(fnode);
		for (FeatureNode<String> d : desc) {
			newFgraph.addVertex(d);
		}
		desc.add(fnode);
		_LOGGER.debug(
				"feature: " + fnode + " descendant: " + desc);
		Set<FeatureEdge> edges = ofgraph.edges();
		for (FeatureEdge fe : edges) {
			FeatureNode<String> target = ofgraph.getTarget(fe);
			try {
				FeatureNode<String> source = ofgraph.getSource(fe);
				if (desc.contains(source) && desc.contains(target)) {
					_LOGGER.debug(
							"" + source + " -> " + target + " (" + fe.getType()
									+ ")");
					newFgraph.addEdge(source, target, fe.getType());

				}
			} catch (IllegalArgumentException e) {
				Set<FeatureNode<String>> sources = ofgraph.getSources(fe);
				if (desc.contains(target)) {
					_LOGGER.debug(
							"" + sources + " -> " + target + " ("
									+ fe.getType() + ")");
					newFgraph.addEdge(sources, target, fe.getType());
				}
			}

		}

		/**
		 * copy constraints that involve feature of the subtree
		 */

		Set<String> ftsFM = newFM.features();
		Set<Expression<String>> csts = _fm.getConstraints();
		for (Expression<String> cst : csts) {
			Set<String> fts = ExpressionUtil.getAllFeatures(cst); // features
																	// involve
																	// in the
																	// expression
			boolean internalConstraint = true;
			for (String ft : fts) {
				if (!ftsFM.contains(ft))
					internalConstraint = false;
			}

			if (internalConstraint) {
				newFM.addConstraint(cst);
			}
		}

		return newFM;

	}

	public String convert(FMFormat format) {
		return ConvertAnalyzer.convert(this, format);
	}

	/**
	 * 
	 * 
	 * A : B C D ; D : (E|F) ; subVOP D => XOR VOP D => Mandatory
	 * 
	 * @param ftName
	 * @return
	 */
	public VariabilityOperatorVariable getSubVOP(String ftName) {
		FeatureGraph<String> fg = _fm.getDiagram();

		// root
		String rootName = root().name;
		if (ftName.equals(rootName)) {
			FMLShell.getInstance().printError(
					"root has no operator! ftName=" + ftName);
			return null;
		}

		int ftEdge = FeatureEdge.HIERARCHY;
		try {
			FeatureNode<String> ftNode = fg.findVertex(ftName);
			Collection<FeatureEdge> edges = fg.incidentEdges(ftNode);
			for (FeatureEdge fe : edges) {
				int feType = fe.getType();
				if (feType != FeatureEdge.HIERARCHY) {
					ftEdge = feType;
					continue; // we take the first one
				}
			}

		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to find the feature name for VOP " + ftName);
			return null;
		}

		assert (ftEdge != -1);

		return convertToFMLVOP(ftEdge);

	}

	public VariabilityOperatorVariable getVOP(String ftName) {
		FeatureGraph<String> fg = _fm.getDiagram();

		// root
		String rootName = root().name;
		if (ftName.equals(rootName)) {
			FMLShell.getInstance().printError(
					"root has no operator! ftName=" + ftName);
			return null;
		}

		int ftEdge = FeatureEdge.HIERARCHY;
		try {
			FeatureNode<String> ftNode = fg.findVertex(ftName);
			Collection<FeatureEdge> edges = fg.outgoingEdges(ftNode);
			for (FeatureEdge fe : edges) {
				int feType = fe.getType();
				if (feType != FeatureEdge.HIERARCHY) {
					ftEdge = feType;
					continue; // we take the first one
				}
			}

		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to find the feature name for VOP " + ftName);
			return null;
		}

		assert (ftEdge != -1);

		return convertToFMLVOP(ftEdge);

	}

	private VariabilityOperatorVariable convertToFMLVOP(int ftEdge) {
		FeatureEdgeKind fk = FeatureEdgeKind.OPTIONAL; // default
		if (ftEdge == FeatureEdge.MANDATORY)
			fk = FeatureEdgeKind.MANDATORY;
		else if (ftEdge == FeatureEdge.OR)
			fk = FeatureEdgeKind.OR;
		else if (ftEdge == FeatureEdge.XOR)
			fk = FeatureEdgeKind.ALTERNATIVE;
		else if (ftEdge == FeatureEdge.MUTEX)
			fk = FeatureEdgeKind.MUTEX;
		else
			assert (ftEdge == FeatureEdge.HIERARCHY || ftEdge == FeatureEdge.FROZEN);
		return new VariabilityOperatorVariable("", fk);
	}

	public Formula<String> intersectionFormula(FeatureModelVariable fm2,
			BDDBuilder<String> builder) {
		List<Formula<String>> flas = new ArrayList<Formula<String>>();
		flas.add(getFormula());
		flas.add(fm2.getFormula());
		return FMLMergerBDD
				.calculateFormulas(flas, Mode.Intersection, builder);
	}

	// TODO
	public Set<Expression<String>> simplifyConstraints(
			ConstraintSimplificationStrategy strategy) {
		Set<Expression<String>> csts = _fm.getConstraints();
		if (strategy == ConstraintSimplificationStrategy.BIIMPLY) {

		}

		return csts;
	}

	public ImplicationGraph<String> computeImplicationGraph(
			BDDBuilder<String> builder) {
		return IGBuilder.build(getFormula(), builder);

	}
	
	public ImplicationGraph<String> computeImplicationGraph(
			Set<String> fts) {
		return IGBuilderDomain.build(getFormula(), getBuilder(), fts) ; 

	}

	/**
	 * @param builder
	 * @return implication graphs without the constraints
	 */
	public ImplicationGraph<String> getImplicationGraphFromFeatureHierarchy(
			BDDBuilder<String> builder) {

		FeatureModel<String> lfm = getFm().clone();
		lfm.removeAllConstraints();
		assert (lfm.getConstraints().size() == 0);

		return IGBuilder.build(builder.mkFeatureModel(lfm), builder);

	}

	public Set<Expression<String>> computeImpliesEdge(BDDBuilder<String> builder) {
		return ImplicationGraphUtil.computeImpliesEdge(this, builder);
	}

	public FeatureVariable getFeature(String ftName) {
		return new FeatureVariable(ftName, this);
	}

	public ExclusionGraph<String> computeExclusionGraph(BDDBuilder<String> builder) {
		return EGBuilder.build(getFormula(), builder);

	}
	
	public ExclusionGraph<String> computeExclusionGraph(BDDBuilder<String> builder, Set<String> fts) {
		return EGBuilder.build(getFormula().getBDD(), builder, fts);

	}
	
	public ExclusionGraph<String> computeExclusionGraph(Set<String> fts) {
		return EGBuilder.build(getFormula().getBDD(), getBuilder(), fts);

	}

	public ExclusionGraph<String> getExclusionGraphFromFeatureHierarchy(
			BDDBuilder<String> builder) {

		FeatureModel<String> lfm = getFm().clone();
		lfm.removeAllConstraints();
		assert (lfm.getConstraints().size() == 0);

		return EGBuilder.build(builder.mkFeatureModel(lfm), builder);

	}

	public Set<Expression<String>> computeExcludesEdge(
			BDDBuilder<String> builder) {
		return ImplicationGraphUtil.computeExcludesEdge(this, builder);
	}
	
	public Set<Expression<String>> computeExcludesEdge(Set<String> fts) {
		return ImplicationGraphUtil.computeExcludesEdge(this, getBuilder(), fts);
	}

	/**
	 * based on SAT note that we have to deactivate -ea (otherwise there is a
	 * strange assertion error, that does not happen with "cores")
	 * 
	 * @return set of deads features
	 */
	public Set<String> deadsSPLOT() {
		try {
			return new HashSet<String>(getSPLOTSATReasoner().allDeadFeatures(
					new HashMap<String, String>()));
		} catch (FMReasoningException e) {
			FMLShell.getInstance().printError(
					"Unable to compute dead features using SPLOT " + e);
			return new HashSet<String>();
		}
	}

	public Formula<String> mkSPLOTFla(
			splar.core.fm.FeatureModel featureModelSPLOT,
			BDDBuilder<String> _builder) {
		ReasoningWithBDD reasoner = SPLOTUtility
				.getSPLOTBDDReasoner(featureModelSPLOT);
		return new Formula<String>(reasoner.getBDD(), _fm.features(), null);
	}

	public Set<String> coresWithSPLOT() {
		ReasoningWithSAT reasoner = getSPLOTSATReasoner();

		try {
			return new HashSet<String>(
					reasoner.allCoreFeatures(new HashMap<String, String>()));
		} catch (Exception e) {
			// should never happen, the operation is supported ;-)
			FMLShell.getInstance()
					.setError(
							"Unable to use the SPLOT/SPLAR reasoner: "
									+ e.getMessage());
			return new HashSet<String>();
		}

	}

	@Deprecated
	public void fixFreeVariables() {
		// _LOGGER.debug("allFts=" +
		// features().names());
		addFreeVariables();
		reorganizeFreeVariablesAsSubFeatureRoot();

	}

	private void reorganizeFreeVariablesAsSubFeatureRoot() {
		// first we collect free features
		Set<String> freeFts = new HashSet<String>();
		FeatureGraph<String> fgraph = getFm().getDiagram();
		Set<FeatureNode<String>> fns = fgraph.vertices();
		for (FeatureNode<String> fn : fns) {
			Collection<FeatureEdge> fedges = fgraph.incidentEdges(fn);

			for (FeatureEdge featureEdge : fedges) {

			}

			if (fn.isFree()) {
				_LOGGER.debug("FREE");
				freeFts.add(fn.getFeature());
				fgraph.removeVertex(fn);
			}

		}

		// second we add to the root
		for (String free : freeFts) {
			addFreeVariableToRoot(free);
		}
	}

	private void addFreeVariables() {

		Set<String> allFts = features().names();
		for (Expression<String> e : getFm().getConstraints()) {
			Collection<String> exprFeats = ExpressionUtil.getAllFeatures(e);
			for (String f : Sets.difference(new HashSet<String>(exprFeats),
					allFts))
				addFreeVariableToRoot(f);
		}

	}

	/**
	 * create and add a feature below the root features (optional)
	 * 
	 * @param f
	 */
	public void addFreeVariableToRoot(String f) {
		_LOGGER.debug(
				"adding free variable to root f=" + f);
		String rootName = this.root().name();
		FeatureNode<String> fnRootName = getFm().getDiagram().findVertex(
				rootName);
		FeatureNode<String> fNode = new FeatureNode<String>(f);
		getFm().getDiagram().addVertex(fNode);
		getFm().getDiagram().addEdge(fNode, fnRootName, FeatureEdge.HIERARCHY);

	}
	
	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, String... fts) {
		return slice(sliceMode, new HashSet<String>(Arrays.asList(fts)));
	}
	
	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		return slice(sliceMode, getBuilder(), fts);
	}

	protected FeatureModelVariable slice(SliceMode sliceMode, BDDBuilder<String> builder, String... fts) {
		return slice(sliceMode, builder, new HashSet<String>(Arrays.asList(fts)));
	}
	
	private FeatureModelVariable slice(SliceMode sliceMode,
			BDDBuilder<String> builder, Set<String> fts) {
		// TODO: when a constraint involves a non existing feature
		// precondition of many operations 
		
		if (_slicerStrategy == SlicerStrategy.BDD)
			return new FMSlicerBDD(builder).sliceFM(this, fts, sliceMode);
		else if (_slicerStrategy == SlicerStrategy.BDD_SPLOT) {
			return new FMSlicerBDDWithSPLOT(builder).sliceFM(this, fts, sliceMode); 
		}
		else if (_slicerStrategy == SlicerStrategy.SAT) {
			_LOGGER.debug("SAT slicing");
			// FIXME @FeatureIDE 
			return new FMSlicerSAT().sliceFM(this, fts, sliceMode);
		}
		else {
			FMLShell.getInstance().printError("Unknown slicer strategy/backend = " + _slicerStrategy) ; 
			return null ; 
		}

	}

	public boolean removeConstraint(Expression<String> e) {
		return removeConstraint(getFm(), e);
	}
	
	

	/**
	 * TODO refactoring
	 * @param fmv2
	 * @return
	 */
	public FeatureModelVariable mergeDiff(FeatureModelVariable fmv2) {
		
		Formula<String> flaMerged = FMLMergerBDD.diff(getFormula(), fmv2.getFormula(), getBuilder());
		
		/*** hierarchy ****/
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add((FeatureModelVariable) this.copy()); // VERY IMPORTANT
		lfms.add((FeatureModelVariable) fmv2.copy());
		
		
		/****** we have obtained the formula *****/
		if (flaMerged.isZero()) { // false
			FeatureModelVariable fmR = FeatureModelVariable.mkFalse(flaMerged,
					HierarchyMergerFactory.mkMerger(HierarchyMergerStrategy.BASIC, null, null).build(lfms));
			return fmR ; 
		}
		
		Set<String> deads = new FormulaAnalyzer<String>(flaMerged, getBuilder()).computeDeadFeatures();
		_LOGGER.debug("deads=" + deads);
				
		// first, we eliminate dead features (synthesis algorithm precondition)
		flaMerged = new FormulaAnalyzer<String>(flaMerged,	getBuilder()).removeDeadFeatures();
		
		
		
		
		HierarchyMerger hMerger = HierarchyMergerFactory.mkMerger(FMLMerger._DEFAULT_HIERARCHY_MERGER, Mode.Diff, flaMerged);
		FeatureModel<String> fmProj = hMerger.build(lfms) ;
		Set<String> rDeads = Sets.intersection(deads, fmProj.features()) ; 
		_LOGGER.debug("rdeads=" + rDeads);
		FMLMergerBDD.removeDeadFeaturesFromHierarchy(fmProj, rDeads); 
		
		/*** now we actually use the synthesis algorithm ****/
		FMLSynthetizer synthetiser = new FMLSynthetizer(getBuilder()) ;
		
		return synthetiser.synthetize(flaMerged, fmProj);
	
	
	}
	
	
	

	/**
	 * TODO: generalize and refactoring
	 * @param fmv2
	 * @return
	 */
	public FeatureModelVariable merge(FeatureModelVariable fmv2, Mode mode) {
		
		List<FeatureModelVariable> lFlas = new ArrayList<FeatureModelVariable>();
				
		lFlas.add(this);  
		lFlas.add(fmv2);
		Formula<String> flaMerged = new FMLMergerBDD(lFlas, getBuilder()).calculateFormula(mode);
		
		//_LOGGER.debug("#" + new FormulaAnalyzer<String>(flaMerged.id(), getBuilder()).counting());
		
		/*** hierarchy ****/
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add((FeatureModelVariable) this.copy());
		lfms.add((FeatureModelVariable) fmv2.copy());
		
		/****** we have obtained the formula *****/
		if (flaMerged.isZero()) { // false
			FeatureModelVariable fmR = FeatureModelVariable.mkFalse(flaMerged,
					HierarchyMergerFactory.mkMerger(HierarchyMergerStrategy.BASIC, null, null).build(lfms));		
			return fmR ; 
		}
		
		
		// FIXME
		Set<String> deads = new FormulaAnalyzer<String>(flaMerged, getBuilder()).computeDeadFeatures();
		
				
		// first, we eliminate dead features (synthesis algorithm precondition)
		flaMerged = new FormulaAnalyzer<String>(flaMerged,	getBuilder()).removeDeadFeatures();
		
		
	
		
		HierarchyMerger hMerger = HierarchyMergerFactory.mkMerger(FMLMerger._DEFAULT_HIERARCHY_MERGER, mode, flaMerged);
		FeatureModel<String> hierarchy = hMerger.build(lfms) ;
		_LOGGER.debug("hierarchy=" + hierarchy);
		FMLMergerBDD.removeDeadFeaturesFromHierarchy(hierarchy, deads);
		
		/*** now we actually use the synthesis algorithm ****/
		// FIXME and directly use KSynthesis
		FMLSynthetizer synthetiser = new FMLSynthetizer(getBuilder()) ;
		
		FeatureModelVariable fmvR = synthetiser.synthetize(flaMerged, hierarchy);
		return fmvR ;
		
	}
	
	

	/**
	 * TODO reify this concept into the language? 
	 * TODO can be useful for parameterizing the synthesizer for example
	 * a hierarchy of features -- all features are optional
	 * @return
	 */
	public FeatureModel<String> getHierarchy() {
		
		FeatureModel<String> lFm = getFm() ;
		FeatureGraph<String> fg = lFm.getDiagram() ; 
		
		FeatureModel<String> rFm = FeatureGraphFactory.mkStringFactory().mkTopModel() ; 
		FeatureGraph<String> rFg = rFm.getDiagram() ; 
		Set<String> fts = fg.features() ; 
		for (String ft : fts) {
			rFg.addVertex(new FeatureNode<String>(ft));
		}
		
		Set<FeatureEdge> fes = fg.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge e : fes) {
			FeatureNode<String> source = fg.getSource(e);
			FeatureNode<String> target = fg.getTarget(e);
			rFm.getDiagram().addEdge(rFg.findVertex(source.getFeature()), rFg.findVertex(target.getFeature()), FeatureEdge.HIERARCHY);
		}
		
		return rFm;
	}
	
	/*
	 * 
	 * 
	 * REDUNDANT constraints
	 * 
	 * TODO: what if the feature model is void?
	 */

	/**
	 * @return whether the current feature model has redundant constraints
	 */
	public boolean hasRedundantConstraints() {
		IConstraintReasoner cstReasoner = getConstraintReasoner();
		return cstReasoner.hasRedundantConstraints();
	}

	protected IConstraintReasoner getConstraintReasoner() {
		// FIXME @FeatureIDE 
		return new FMLConstraintReasoner(this, getBuilder()) ;
	}

	/**
	 * TODO integrate this function and others in the language?
	 * @return the set of redundant constraints
	 */
	public Set<Expression<String>> computeRendundantConstraints() {
		IConstraintReasoner cstReasoner = getConstraintReasoner();
		return cstReasoner.computeRedundantConstraints();
	}

	/**
	 * TODO remove other similar methods and pieces of code
	 * TODO should return something?
	 * removes the redundant constraints, of any, of the current feature model
	 */
	public void eliminateRedundantConstraints() {
		if (!isValid())
			return ; 
		IConstraintReasoner cstReasoner = getConstraintReasoner();
		cstReasoner.eliminateRedundantConstraints() ; 
	}
	
	
		

	@Deprecated
	public FeatureModelVariable toGeneralizedNotation() {
		
				
		
		Formula<String> fla = getFormula().id() ;
		Formula<String> properFla = new FormulaAnalyzer<String>(fla, getBuilder()).removeDeadFeatures() ; // precondition
		
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("fmm.FGBuilder");
		logger.setLevel(Level.OFF);
		 
		FGBuilder<String> synthesizer = new FGBuilder<String>(getBuilder());
		FeatureModel<String> fmR = synthesizer.build(properFla);
		
		
		/*
		KnowledgeSynthesis<String> kn = new KnowledgeSynthesis<String>() ;
		kn.setHierarchy(hierarchy());
		KSynthesis synthesizer = new KSynthesis(properFla, kn, getBuilder());
		FeatureModel<String> fmR = synthesizer.buildWithoutKST() ;
		//normalizeCliques(fmR);
		//new FeatureHierarchySelector<String>(getBuilder()).contractSameLevel(fmR);
		*/ 

		fla.free() ;
		properFla.free() ;		

		return new FeatureModelVariable("", fmR);
	}
	
	
	
	private String convertSetOfFtsToString(Set<String> fts) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int n = fts.size() ; 
		for (String ft : fts) {
			sb.append(ft);
			if (i++ != n - 1)
				sb.append("_");
			
		}
		return sb.toString() ; 
	}

	/*
	 *  
	 * 
	 * get groups in the hierarchy! (syntactical technique)
	 * 
	 */

	public Set<FGroup> getXorGroups() {
		return _getGroups(FeatureEdge.XOR);
	}

	public Set<FGroup> getOrGroups() {
		return _getGroups(FeatureEdge.OR);
	}

	public Set<FGroup> getMutexGroups() {
		return _getGroups(FeatureEdge.MUTEX);
	}
	
	private Set<FGroup> _getGroups(int edgeType) {
		FeatureGraph<String> fg = getFm().getDiagram() ;
		Set<FeatureEdge> edges = fg.selectEdges(edgeType);
		Set<FGroup> groups = new HashSet<FGroup>() ;
		for (FeatureEdge fe : edges) {
			Set<FeatureNode<String>> sources = fg.getSources(fe);
			FeatureNode<String> target = fg.getTarget(fe);
			
			FGroup group = FGroupBuilder.make (sources, target, edgeType);
			groups.add(group);
		}
		return groups ;
	}
	
	/*
	 * 
	 * compute groups (semantical)
	 * 
	 */

	public Set<FGroup> computeMutexGroups() {
// this solution has some limitations (it considers a specific; arbitary hierarchy)		
//	FeatureModelVariable fmvG = toGeneralizedNotation() ; 
//		return fmvG.getMutexGroups();
	
		// this solution performs over the IG and EG
		return _mtxGroups(false);
		
	}
	

	/**
	 * when xor is false, strict mutex groups (without mtx groups that are actually xor)
	 * when xor is true, xor groups 
	 * @param xor
	 * @return
	 */
	private Set<FGroup> _mtxGroups(boolean xor) {
		
		
		Set<FGroup> mtxGroups = new HashSet<FGroup>() ; // note that some of them can be Xor
		Set<FGroup> xorGroups = new HashSet<FGroup>() ; 
		
		ExclusionGraph<String> exg = computeExclusionGraph(getBuilder());
		ImplicationGraph<String> impl = computeImplicationGraph(getBuilder()) ;
				
		Set<String> fts = features().names() ;
		
		for (String p : fts) {
			ExclusionGraph<String> exgP = exg.subgraph(p, impl.getPredecessors(p));
			
			Collection<Set<String>> cls = new ExclusionGraphUtil<String>().cliques(exgP);
			
			for (Set<String> cl : cls) {
				Collection<FeatureNode<String>> fNodes = new HashSet<FeatureNode<String>>();
				for (String c : cl) {
					/*if (c.equals("False") || c.equals("True"))
						continue ; */
					try {
					FeatureNode<String> v = new FeatureNode<String>(c); 
							
					//fg.findVertex(c); 
					fNodes.add(v) ;
					}
					catch (Exception e) {
						FMLShell.getInstance().printError("Unable to find " + c + " feature during computation of MTX/XOR groups");
					}
								
				}
				
				FeatureNode<String> nodeParent = new FeatureNode<String>(p); 
				
				// if parent implies one of the child in the clique: Xor-group
				if (_checkImplications(cl, p, getFormula())) { // TODO: SAT version as well (easy)
					xorGroups.add(new XorGroup((Set<FeatureNode<String>>) fNodes, 
							nodeParent));
				}
				else {
					
					mtxGroups.add(new MutexGroup((Set<FeatureNode<String>>) fNodes, 
							nodeParent));
				}
				
				
				
			}
									
		}
		
		if (!xor)
			return mtxGroups;
		else
			return xorGroups;
		
	}

	private boolean _checkImplications(Set<String> cl, String p, Formula<String> formula) {
		BDDBuilder<String> builder = getBuilder() ; 
		BDD children = _mkDisjunctions(cl, builder);
		BDD parent = builder.get(p);
		parent.impWith(children);
		BDD r = parent.not().and(getFormula().getBDD());
		return r.isZero() ; 		
	}

	private BDD _mkDisjunctions(Set<String> cl, BDDBuilder<String> builder) {
		
		BDD result = builder.zero() ; 
		for (String c : cl) {
			result.orWith(builder.get(c));
		}
		return result ; 
	}

	public Set<FGroup> computeXorGroups() {
		// similar as Mutex groups
		// this solution performs over the IG and EG
		return _mtxGroups(true);
	}
	
	public Set<FGroup> computeGroups() {
		Set<FGroup> rGroups = new HashSet<FGroup>();
		Set<FGroup> mtxs = computeMutexGroups() ; 
		Set<FGroup> xors = computeXorGroups() ; 
		Set<FGroup> ors = computeOrGroups() ;
		
		rGroups.addAll(mtxs);
		rGroups.addAll(xors);
		rGroups.addAll(ors);
		
		return rGroups ; 

	}

	public Set<FGroup> computeOrGroups() {
		FeatureModelVariable fmvG = toGeneralizedNotation() ; // FIXME: WRONG solutions since the generalized synthesizer (1) subsumes Xor/Mtx to Or-groups (2) reduction of the IG 
		return fmvG.getOrGroups();
	}

	public void contractWithAtomicSet() {
		
		FeatureGraph<String> fg = getFm().getDiagram() ; 
		Collection<Set<String>> aS = _atomicSet() ;
		for (Set<String> atomicSet : aS) {
			
			
			
			FeatureNode<String> cToReplace = null ; 
			Set<String> currentAtomicSet = new HashSet<String>();
			for (String maS : atomicSet) {
				currentAtomicSet.add(maS);
				String atomicSetName = convertSetOfFtsToString(currentAtomicSet) ; 
				FeatureNode<String> atomicSetVertex = new FeatureNode<String>(atomicSetName);
				if (cToReplace == null) 
					cToReplace = fg.findVertex(maS) ; 
				fg.replaceVertex(cToReplace, atomicSetVertex);
				cToReplace = atomicSetVertex ;
			}
			
		}
		
	}

	public Set<Expression<String>> quotient(FeatureModelVariable fmv2) {
		// FIXME @FeatureIDE 
		return new FMQuotient(this, fmv2).quotient() ;
	}
		

	/**
	 * @param builder
	 * @return the set of implications induced by parent-child relationships
	 */
	public ImplicationGraph<String> getImplicationGraphFromPCEdges(BDDBuilder<String> builder) {
		
		FeatureGraph<String> fg = getFm().getDiagram() ;
		ImplicationGraph<String> impl = new ImplicationGraph<String>();
		Set<String> fts = fg.features() ;
		for (String ft : fts) {
			impl.addVertex(ft);
		}
		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge e : edges) {
			FeatureNode<String> source = fg.getSource(e);
			if (source.isTop() || source.isBottom())
				continue ; 
			FeatureNode<String> target = fg.getTarget(e);
			if (target.isTop() || target.isBottom())
				continue ; 
			impl.addEdge(source.getFeature(), target.getFeature());
			
		}
		
		Set<FeatureEdge> mandatoryEdges = fg.selectEdges(FeatureEdge.MANDATORY);
		for (FeatureEdge e : mandatoryEdges) {
			FeatureNode<String> source = fg.getSource(e);
			if (source.isTop() || source.isBottom())
				continue ; 
			FeatureNode<String> target = fg.getTarget(e);
			if (target.isTop() || target.isBottom())
				continue ; 
			impl.addEdge(target.getFeature(), source.getFeature());
			
		}
		
		return impl ; 
	}
	
	

	public Set<FGroup> getGroups() {
		Set<FGroup> ors = getOrGroups() ; 
		Set<FGroup> xors = getXorGroups(); 
		Set<FGroup> mtxs = getMutexGroups() ;
		
		Set<FGroup> groups = new HashSet<FGroup>();
		groups.addAll(ors);
		groups.addAll(xors);
		groups.addAll(mtxs);
		
		return groups ; 
	}

	public Set<Expression<String>> getImplicationConstraints() {
		Set<Expression<String>> expr = new HashSet<Expression<String>>();
		Set<Requires<String>> requires = _fm.getRequires() ;
		for (Requires<String> req : requires) {
			expr.add(req);
		}
		return expr ; 
	}

	public Set<Expression<String>> getExcludeConstraints() {
		 
		Set<Expression<String>> expr = new HashSet<Expression<String>>();
		Set<Excludes<String>> excludes = _fm.getExcludes() ;
		for (Excludes<String> excl : excludes) {
			expr.add(excl);
		}
		return expr ;
	}

	/**
	 * side effect on the current feature model variable
	 * insert aspectFMW into fnw (jp) using the variability operator operator
	 * TODO: check if it is in line with the PhD thesis
	 * @param aspectFMW
	 * @param fnw
	 * @param operator
	 * @return true in case the insert succeeds (jp exists)
	 */
	 public boolean insert(FeatureModelVariable aspectFMW, FeatureVariable fnw,
			FeatureEdgeKind operator) {
		 return insert (aspectFMW, fnw.getFtName(), operator);
	 }
	 
	 /**
	 * side effect on the current feature model variable
	 * insert aspectFMW into ftName (jp) using the variability operator operator
	 * TODO: check if it is in line with the PhD thesis
	 * @param aspectFMW
	 * @param ftName
	 * @param operator
	 * @return true in case the insert succeeds (jp exists)
	 */	 
	 public boolean insert(FeatureModelVariable aspectFMW, String ftName,
				FeatureEdgeKind operator) {
		
		
		// _LOGGER.debug("baseFMW= " +
		// baseFMW.getSpecificValue()) ;

	

		FeatureModel<String> baseFM = getFm();
		FeatureGraph<String> baseGraph = baseFM.getDiagram();

		FeatureModel<String> aspectFM = aspectFMW.getFm();
		FeatureGraph<String> aspectGraph = aspectFM.getDiagram();

		// precondition: check that features (name) in aspect FM are not already
		// existing in the base FM
		_LOGGER.debug("checking precondition ");
		Set<FeatureNode<String>> aspectvtx = aspectGraph.vertices();
		for (FeatureNode<String> aspectfn : aspectvtx) {
			_LOGGER.debug(
					"in? " + aspectfn.getFeature());
			boolean fail = false;
			try {
				if (!aspectfn.isBottom() && !aspectfn.isTop()
						&& !aspectfn.isDead())
					if (baseGraph.findVertex(aspectfn.getFeature()) != null)
						fail = true;
			} catch (Exception e) {
				_LOGGER.debug(
						"exception during the search");
				// fail = true;
			}
			if (fail) {
				FMLShell.getInstance().printWarning(
						"" + aspectfn.getFeature()
								+ " already exists in the base feature model");
				return false ; 
			}

		}

		// joinpoint found
		FeatureNode<String> jp = baseGraph.findVertex(ftName);

		// aspect feature found: root of aspect FM
		FeatureNode<String> aspectRootNode = aspectGraph
				.children(aspectGraph.getTopVertex()).iterator().next(); // root

		

		int type = -1;

		if (operator == FeatureEdgeKind.MANDATORY)
			type = FeatureEdge.MANDATORY;
		else if (operator == FeatureEdgeKind.OPTIONAL)
			type = FeatureEdge.HIERARCHY;
		// TODO
		// else if (operator.equals("xor"))
		// type = FeatureEdge.MUTEX ;
		// else if (operator.equals("or"))
		// type = FeatureEdge.MUTEX ;
		else {
			FMLShell.getInstance().setError(
					"Unknown operator " + operator + " for insert mode");
			return false;
		}

		_LOGGER.debug(
				"base feature model: " + getVid());
		_LOGGER.debug(
				"aspect feature model: " + aspectFMW.getVid());

		/* copy subtree */
		baseGraph.addVertex(aspectRootNode);
		baseGraph.addEdge(aspectRootNode, jp, FeatureEdge.HIERARCHY);
		baseGraph.addEdge(aspectRootNode, jp, type);

		Set<FeatureNode<String>> desc = aspectGraph.descendants(aspectRootNode);
		for (FeatureNode<String> d : desc) {
			baseGraph.addVertex(d);
		}
		desc.add(aspectRootNode);
		_LOGGER.debug(
				"feature: " + aspectRootNode + " descendant: " + desc);
		Set<FeatureEdge> edges = aspectGraph.edges();
		for (FeatureEdge fe : edges) {
			FeatureNode<String> target = aspectGraph.getTarget(fe);
			try {
				FeatureNode<String> source = aspectGraph.getSource(fe);
				if (desc.contains(source) && desc.contains(target)) {
					_LOGGER.debug(
							"" + source + " -> " + target + " (" + fe.getType()
									+ ")");
					baseGraph.addEdge(source, target, FeatureEdge.HIERARCHY);
					baseGraph.addEdge(source, target, fe.getType());

				}
			} catch (IllegalArgumentException e) {
				Set<FeatureNode<String>> sources = aspectGraph.getSources(fe);
				if (desc.contains(target)) {
					_LOGGER.debug(
							"" + sources + " -> " + target + " ("
									+ fe.getType() + ")");
					// baseGraph.addEdge(sources, target,
					// FeatureEdge.HIERARCHY);
					baseGraph.addEdge(sources, target, fe.getType());
				}
			}

		}

		baseFM.addAllConstraints(aspectFM.getConstraints());

		return true ; 
		
	}

	/**
	 * @param cv
	 * @return
	 */
	public boolean addConstraint(ConstraintVariable cv) {
		return addConstraint(cv.getConstraint());
	}	
	

	protected boolean checkConstraintisInFM(Expression<String> cnf) {
		Set<String> features = features().names() ;
		Set<String> featuresOfCNF = new HashSet<String>();
		findFeatures(cnf, featuresOfCNF);
		return features.containsAll(featuresOfCNF);
	}

	private void findFeatures(Expression<String> expr, Set<String> acc) {
		if (expr == null)
			return;
		if (expr.getType() == ExpressionType.FEATURE) {
			String ft = expr.getFeature();
			if (ft != null)
				acc.add(ft);
			return;
		}

		// iff, implies, and, not : binary
		findFeatures(expr.getLeft(), acc);
		findFeatures(expr.getRight(), acc);

	}
	
	public boolean addConstraint(Expression<String> e) {
		boolean isIn = checkConstraintisInFM(e);
		if (isIn) {
			return getFm().addConstraint(e);
		} 
		return false;
		
	}

	public boolean removeAllConstraints() {
		return _fm.removeAllConstraints() ; 
		
	}

	public Set<Expression<String>> getAllConstraints() {
		return _fm.getConstraints() ;
		
	}

	public boolean addAllConstraints(Set<Expression<String>> csts) {
		boolean addOK = true ; 
		for (Expression<String> cst : csts) {
			if (!addConstraint(new ConstraintVariable(cst, null))) {
				addOK = false ; 
			}
		}
		return addOK ;		
	}

	public Set<Expression<String>> getBiImplicationConstraints() {
		Set<Expression<String>> expr = new HashSet<Expression<String>>();
		Set<Expression<String>> csts = _fm.getConstraints() ;
		for (Expression<String> cst : csts) {
			if (cst.getType() == ExpressionType.IFF)
				expr.add(cst); 
		}
		
		return expr ; 
	}

	
	public Collection<Set<String>> mutexCliques(BDDBuilder<String> builder) {
		ExclusionGraph<String> excl = computeExclusionGraph(builder);
		return new ExclusionGraphUtil<String>().cliques(excl);
	}

	
	
	public Set<String> _getFeatureOptionalOrMandatory(boolean optional) {
		Set<String> opts = new HashSet<String>() ;
		FeatureGraph<String> fg = _fm.getDiagram() ;
		Set<String> fts = features().names() ; 
		for (String ft : fts) {
			FeatureNode<String> fn = fg.findVertex(ft);
			if (optional) {
				if (FeatureNodeUtils.isOptional(fg, fn))
					opts.add(fn.toString());
			}
			else {
				if(FeatureNodeUtils.isMandatory(fg, fn))
					opts.add(fn.toString());
			}
		}
		return opts ; 
	}

	public Set<String> getOptionals() {
		return _getFeatureOptionalOrMandatory(true);
	}
	
	public Set<String> getMandatory() {
		return _getFeatureOptionalOrMandatory(false);
	}

	
	@Override
	public ImplicationGraph<String> computeImplicationGraph() {
		return computeImplicationGraph(getBuilder());
	}

	public Set<Expression<String>> computeExcludesEdge() {
		return computeExcludesEdge(getBuilder());
	}

	@Override
	public FeatureModelVariable ksynthesis(KnowledgeSynthesis kn) {
		if (!kn.isConsistent()) {
			FMLShell.getInstance().printError("Knowledge specification is not consistent: " + kn );
			return null; 
		}
		
		KSTReport kst1Report = kn.ckeckKSTCoherence(this) ; 
		if (!kst1Report.isCoherent()) {
			FMLShell.getInstance().printError("Knowledge specification is not consistent w.r.t feature model: " + kst1Report.getMessage());
			return null ; 
		}
		// refactor for implementing in a clearer way the template patterns for other kind of feature models
		return _ksynthesis (kn) ; 			
	}
	
	public FeatureModelVariable _ksynthesis (KnowledgeSynthesis kn) {
		return ksynthesis(kn, _DEFAULT_SYNTHESIS_STRATEGY) ;  
	}
	
	protected FeatureModelVariable ksynthesis(KnowledgeSynthesis kn, SynthesisStrategy synthStrategy) {
			
		KSynthesis synth = null ; 
		if (synthStrategy == SynthesisStrategy.BDD)
			synth = new KSynthesisBDD(getFormula(), kn, getBuilder());
		else if (synthStrategy == SynthesisStrategy.SAT) {
			// FIXME @FeatureIDE 
			synth = new KSynthesisSAT(this, kn) ;
		}
		else {
			FMLShell.getInstance().printError("Unknown synthesis strategy: " + synthStrategy) ; 
			return null ; 
		}
		assert (synth != null);
		FeatureModelVariable rFM = synth.build() ;
		
		if (synth.hasConflictingChoices()) {
			FMLShell.getInstance().printWarning("Knowledge specification is not complete:" + 
						synth.getConflictReport());
		}
				
		return rFM ; 
	}

	/**
	 *  
	 * @param kn
	 * @param fts
	 * @return
	 */
	public FeatureModelVariable ksynthesisOver(KnowledgeSynthesis kn, SynthesisStrategy synthStrategy, Set<String> fts) {
		KSynthesis synth = null ; 
		if (synthStrategy == SynthesisStrategy.BDD)
			synth = new KSynthesisBDD(getFormula(), kn, getBuilder());
		else if (synthStrategy == SynthesisStrategy.SAT) {
			// FIXME @FeatureIDE 
			synth = new KSynthesisSAT(this, kn) ;
		}
		else {
			FMLShell.getInstance().printError("Unknown synthesis strategy: " + synthStrategy) ; 
			return null ; 
		}
		assert (synth != null);
		FeatureModelVariable rFM = synth.buildOver(fts) ;
		
		if (synth.hasConflictingChoices()) {
			FMLShell.getInstance().printWarning("Knowledge specification is not complete:" + 
						synth.getConflictReport());
		}
				
		return rFM ; 
	}

	public FeatureModelVariable ksynthesisOver(KnowledgeSynthesis kst1,	Set<String> fts) {
		return ksynthesisOver(kst1, _DEFAULT_SYNTHESIS_STRATEGY, fts);
	}
	
	
	private Map<String, List<FeatureAttribute>> _ftsAttributes = new HashMap<String, List<FeatureAttribute>>() ; 

	public Map<String, List<FeatureAttribute>> getFeatureAttributes() {
		return _ftsAttributes;
	}

	public void setFeatureAttributes(Map<String, List<FeatureAttribute>> _ftsAttributes) {
		this._ftsAttributes = _ftsAttributes;
	}

	@Override
	public void setFeatureAttribute (FeatureVariable ft, String attributeID, Variable rVar) {
		String ftName = ft.getFtName() ;
		FeatureAttribute ftAttribute = new FeatureAttribute(ft, attributeID, rVar) ; 
		if (_ftsAttributes.containsKey(ftName)) {
			List<FeatureAttribute> attr = _ftsAttributes.get(ftName);
			attr.add(ftAttribute);
		}
		else {
			List<FeatureAttribute> attr = new ArrayList<FeatureAttribute> () ; 
			attr.add(ftAttribute) ;
			_ftsAttributes.put(ftName, attr);
		}
	}

	

	
}
