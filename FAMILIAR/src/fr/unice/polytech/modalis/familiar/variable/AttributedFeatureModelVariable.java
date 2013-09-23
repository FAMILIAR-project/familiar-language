package fr.unice.polytech.modalis.familiar.variable;

import inria.FAMILIAR.Model.AttributedFeatureModel;
import inria.FAMILIAR.Model.Feature;

import java.util.Collection;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

import org.xtext.example.mydsl.fML.SliceMode;

import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.ContradictionException;
import choco.kernel.solver.Solver;
import choco.kernel.solver.variables.integer.IntDomainVar;

import es.us.isa.ChocoReasoner.attributed.ChocoReasoner;
import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedReader;
import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel;
import fr.unice.polytech.modalis.familiar.operations.KnowledgeSynthesis;
import gsd.graph.ImplicationGraph;

public class AttributedFeatureModelVariable extends VariableImpl implements
		FMLFeatureModel {

	/**
	 * Represent the Feature model to reason over.
	 */
	AttributedFeatureModel fm;
	/**
	 * reasoner to be used, possible values Choco2,Choco3, SAt4j (extend if necessary)
	 */
	String reasoner = "Choco2";

	
	public AttributedFeatureModelVariable(){}

	public AttributedFeatureModelVariable(AttributedFeatureModel fm){this.fm=fm;}

	public void readModelFAMAFormat(String location) {
		AttributedReader reader = new AttributedReader();
		try {
			fm = reader.parseFile(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isValid() {
		boolean res = false;
		if (reasoner.equals("Choco2")) {

			ChocoReasoner choco = new ChocoReasoner();
			fm.transformto(choco);
			Model chocoProblem = choco.getProblem();
			Solver solver = new CPSolver();
			solver.read(chocoProblem);
			res = solver.solve();
		} else if (reasoner.equals("Choco3")) {

		} else if (reasoner.equals("Sat4j")) {

		}

		return res;
	}

	@Override
	public SetVariable features() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setMandatory(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOptional(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAlternative(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOr(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double counting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> cores() {
		Set<String> res = new HashSet<String>();
		if (reasoner.equals("Choco2")) {//follow the same protocol if required for others reasoners
			Solver s = new CPSolver();
			ChocoReasoner choco = new ChocoReasoner();
			fm.transformto(choco);
			Model model = choco.getProblem();
			s.read(model);

			// utilizando propagacion
			try {
				s.propagate();
			} catch (ContradictionException e) {
				e.printStackTrace();
			}

			Collection<Feature> allFeats = choco.getAllFeatures();
			Map<String, IntegerVariable> vars = choco.getVariables();
			for (Feature feat : allFeats) {
				IntegerVariable v = vars.get(feat.getName());
				IntDomainVar vs = s.getVar(v);
				int upper = vs.getSup();
				int lower = vs.getInf();
				if ((upper == lower) && (upper > 0)) {
					res.add(feat.getName());
				}
			}
		}
		return res;
	}

	@Override
	public Set<String> deads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double CTCR() {
		return fm.getFeaturesNumber()/fm.getNumberOfConstraints();
	}

	@Override
	public ImplicationGraph<String> computeImplicationGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, String... fts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureModelVariable ksynthesis(KnowledgeSynthesis kn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFeatureAttribute(FeatureVariable ft, String attributeID,
			Variable rVar) {
		// TODO Auto-generated method stub

	}

	@Override
	public RType getRType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Variable vari) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSpecificValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureVariable root() {
		return null;
	}
}
