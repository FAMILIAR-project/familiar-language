/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ConstraintExpr;
import org.xtext.example.mydsl.fml.ConstraintsSpecification;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.HProduction;
import org.xtext.example.mydsl.fml.HierarchySpecification;
import org.xtext.example.mydsl.fml.KnowledgeSpecification;
import org.xtext.example.mydsl.fml.Synthesis;

//import fr.familiar.gui.synthesis.FMSynthesisEnvironment; // FIXME : removed with KSynthesis
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.KnowledgeSynthesis;
//import fr.familiar.operations.heuristics.InteractiveFMSynthesizer; // FIXME : removed with KSynthesis
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;

/**
 * @author macher
 *
 */
public class KSynthesisAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public KSynthesisAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public KSynthesisAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Synthesis);
		Synthesis ksCmd = (Synthesis) _command;

		FMCommand fmCmd = ksCmd.getFm();
		FeatureModelVariable fmToSynthesis = _environment.parseFMCommand(fmCmd, null, null);
		
		
		
		
		
		
		KnowledgeSynthesis kst1 = new KnowledgeSynthesis() ; 
		
		KnowledgeSpecification kstCmd = ksCmd.getKst() ;
		if (kstCmd == null) {
			// no knowledge
		}
		else {
			
			// hierarchy (if any)			
			HierarchySpecification hSpecification = kstCmd.getHierarchy() ;
			if (hSpecification != null) {
				FeatureGraph<String> hierarchy = parseHierarchySpecification(hSpecification);
				kst1.setHierarchy(hierarchy);
			}
			
			ConstraintsSpecification cstSpecification = kstCmd.getConstraints() ;
			if (cstSpecification != null) {
				Set<Expression<String>> exprs = new HashSet<Expression<String>>() ; 
				ConstraintExpr csts = cstSpecification.getCsts() ; 
				SetVariable s = _environment.parseSetCommand(csts, null) ;
				Set<Variable> cstVariables = s.getVars() ;
				for (Variable cstVariable : cstVariables) {
					if (!(cstVariable instanceof ConstraintVariable)) {
						FMLShell.getInstance().printError("Constraint expected but " + cstVariable);
						return ; 
					}
					ConstraintVariable cst = (ConstraintVariable) cstVariable ;
					Expression<String> expr = cst.getConstraint() ;
					exprs.add(expr);
				}
				kst1.setConstraints(exprs);
			}
			
			
		}
		
		
		
		fmToSynthesis.setBuilder(FMLCommandInterpreter.getBuilder()); // in case we want BDD
		
		if (ksCmd.isInteractive()) {
			// interactive mode
			// with or without knowledge
//			InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fmToSynthesis); // FIXME : removed with KSynthesis
//			FMSynthesisEnvironment environment = new FMSynthesisEnvironment(synthesizer); // FIXME : removed with KSynthesis
		}
		
		// TODO backend analysis for defining the synthesis strateg
		// no interactive mode
		
		
		
		/*
		 * The synthesis can be "over" a formula (local synthesis, a kind of slicing)
		 * It computes a feature diagram 
		 * 
		 */
		// TODO interactive mode 
		// + with (partial) knowledge
		// + backend 
		boolean isOver = ksCmd.isOver() ;
		if (isOver) {
			// TODO refactor with ComputeConstraints "over" to have a commun function for parsing it
			SetVariable setFts = _environment.parseSetCommand(ksCmd.getFts(), null) ;
			
			Set<String> fts = new HashSet<String>();
			Set<Variable> svars = setFts.getVars();
			for (Variable var : svars) {

				if (var instanceof RefVariable)
					var = ((RefVariable) var).getValueReference();

				if (!(var instanceof FeatureVariable)) {
					FMLShell.getInstance().printError(
							"var=" + var + " is not a feature in the set feature");
					return;
				}

				assert (var instanceof FeatureVariable);
				FeatureVariable ftv = (FeatureVariable) var;

				// TODO: check that ftv truly belongs to variables of the formula
				fts.add(ftv.getFtName());

			}
			
			FeatureModelVariable fmSynthesised = fmToSynthesis.ksynthesisOver(kst1, fts);
			setVariable(fmSynthesised) ;
			return ; 
		}
		
		
		FeatureModelVariable fmSynthesised = fmToSynthesis.ksynthesis(kst1);
			
		setVariable(fmSynthesised) ; 
	}

	private FeatureGraph<String> parseHierarchySpecification(HierarchySpecification hSpecification) {
		FeatureGraph<String> r = FeatureGraphFactory.mkStringFactory().mkTop() ;
		EList<HProduction> productions = hSpecification.getFeatures() ;
		boolean first = true ; 
		for (HProduction hProduction : productions) {
			String parentName = hProduction.getName() ;
			
			FeatureNode<String> fnParent = null ; 
			try {
				fnParent = r.findVertex(parentName); 
			}
			catch (Exception e) {
				fnParent = 	new FeatureNode<String>(parentName) ;
				r.addVertex(fnParent);
			}
			if (first) {
				r.addEdge(fnParent, r.getTopVertex(), FeatureEdge.HIERARCHY);
				first = false ;   
			}
			EList<String> fts = hProduction.getFeatures() ;
			for (String ft : fts) {
				r.addVertex(new FeatureNode<String>(ft));
			}			
		}
		
		for (HProduction hProduction : productions) {
			String parentName = hProduction.getName() ;
			FeatureNode<String> fnParent = r.findVertex(parentName); 
			EList<String> fts = hProduction.getFeatures() ;
			for (String ft : fts) {
				FeatureNode<String> fnChild = r.findVertex(ft); 
				r.addEdge(fnChild, fnParent, FeatureEdge.HIERARCHY);
			}			
		}
		FMLShell.getInstance().printDebugMessage("hierarchy: " + r);
		
		
		return r;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE_MODEL ;
	}

}
