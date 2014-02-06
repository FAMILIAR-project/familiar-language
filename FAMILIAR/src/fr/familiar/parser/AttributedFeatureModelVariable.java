package fr.familiar.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xtext.example.mydsl.fML.SliceMode;

import choco.Choco;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.ContradictionException;
import choco.kernel.solver.Solver;
import choco.kernel.solver.variables.integer.IntDomainVar;
import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedReader;
import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.Configuration;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.PacogenReasoner;
import fr.familiar.attributedfm.reasoning.pairwise.Pair;
import fr.familiar.fm.basic.FMLFeatureModel;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableImpl;
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

	public AttributedFeatureModelVariable(){super();};
	
	public AttributedFeatureModelVariable(AttributedFeatureModel model){
		this.fm=model;
	}
	public void readModelFAMAFormat(String location) {
		AttributedReader reader = new AttributedReader();
		try {
			fm = reader.parseFile(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPairWiseChoco(){
		fr.familiar.attributedfm.reasoning.pairwise.ChocoReasoner reasoner = new fr.familiar.attributedfm.reasoning.pairwise.ChocoReasoner(getPairs());
		fm.transformto(reasoner);
		reasoner.addPairWiseConstraints();
		
		Model p = reasoner.getProblem();
		Solver sol = new CPSolver();
		sol.read(p);

		sol.solve();

		Map<String, Collection<String>> pairWiseCoverage = new HashMap<String, Collection<String>>();
		// I will only retrieve 1 solution.
		for (int i = 0; i < p.getNbIntVars(); i++) {
			IntDomainVar aux = sol.getVar(p.getIntVar(i));
			if (aux.getName().equals("_suma")) {
				System.out.println("The maximum value is: " + aux.getVal());
			}
			if (aux.getVal() > 0) {
				String name = aux.getName();
				if (!name.contains(".") && !name.endsWith("_card")) {
				
					int index = name.indexOf('_');
					String featureName = name.substring(0, index);
					String pairName = name.substring(index + 1);
					if (pairWiseCoverage.containsKey(pairName)) {
						pairWiseCoverage.get(pairName).add(featureName);
					} else {
						Collection<String> col = new LinkedList<String>();
						col.add(featureName);
						pairWiseCoverage.put(pairName, col);
					}
				}
			}
		}

		for (Entry<String, Collection<String>> entry : pairWiseCoverage
				.entrySet()) {
			String str = "The pair " + entry.getKey()
					+ " is covered by the product composed by: ";
			for (String s : entry.getValue()) {
				str += s + ";";
			}
			System.out.println(str);
		}

	}
	
	public void getPairWiseChocoMaximizing(String attname){

		fr.familiar.attributedfm.reasoning.pairwise.ChocoReasoner reasoner = new fr.familiar.attributedfm.reasoning.pairwise.ChocoReasoner(getPairs());
		fm.transformto(reasoner);	
		reasoner.addPairWiseConstraints();
		Model p = reasoner.getProblem();

		// primero cramos la coleccion con los atributos que nos interesan
		// dependiendo de la cadena de entrada

		Collection<IntegerVariable> selectedAtts = new ArrayList<IntegerVariable>();

		Iterator<Entry<Pair, Map<String, IntegerVariable>>> atributesIt = reasoner.attVars
				.entrySet().iterator();
		while (atributesIt.hasNext()) {
			Entry<Pair, Map<String, IntegerVariable>> e = atributesIt.next();
			for (Entry<String, IntegerVariable> entry : e.getValue().entrySet()) {
				if (entry.getKey().contains(attname)) {
					selectedAtts.add(entry.getValue());
				}
			}
		}

		// Ahora necesitamos crear una variable suma de todos los atributos
		// anteriores"
		IntegerVariable[] reifieds = new IntegerVariable[selectedAtts.size()];

		IntegerVariable suma = Choco.makeIntVar("_suma", 0, 2000000);
		IntegerExpressionVariable sumatorio = Choco.sum(selectedAtts
				.toArray(reifieds));
		Constraint sumReifieds = Choco.eq(suma, sumatorio);
		p.addConstraint(sumReifieds);

		Solver sol = new CPSolver();
		sol.read(p);

		IntDomainVar maxVar = sol.getVar(suma);
		sol.maximize(maxVar, false);

		Map<String, Collection<String>> pairWiseCoverage = new HashMap<String, Collection<String>>();
		// I will only retrieve 1 assignation.
		for (int i = 0; i < p.getNbIntVars(); i++) {
			IntDomainVar aux = sol.getVar(p.getIntVar(i));
			if (aux.getName().equals("_suma")) {
				System.out.println("The maximum value is: " + aux.getVal());
			}
			if (aux.getVal() > 0) {
				String name = aux.getName();
				if (!name.contains(".") && !name.endsWith("_card")) {
					// System.out.println(name);
					int index = name.indexOf('_');
					String featureName = name.substring(0, index);
					String pairName = name.substring(index + 1);
					if (pairWiseCoverage.containsKey(pairName)) {
						pairWiseCoverage.get(pairName).add(featureName);
					} else {
						Collection<String> col = new LinkedList<String>();
						col.add(featureName);
						pairWiseCoverage.put(pairName, col);
					}
				}
			}
		}

		for (Entry<String, Collection<String>> entry : pairWiseCoverage
				.entrySet()) {
			String str = "The pair " + entry.getKey()
					+ " is covered by the product composed by: ";
			for (String s : entry.getValue()) {
				str += s + ";";
			}
			System.out.println(str);
		}
	}

	public void getOptimalPairWiseChoco(){
		
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
		} else if (reasoner.equals("Pacogen")) {
			
			PacogenReasoner paco = new PacogenReasoner();
			try {
				paco.buildModel() ;
				paco.Reason() ;

			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Where is Paco??");
			}
			System.out.println(paco.getVarDomain());

		} else if (reasoner.equals("Sat4j")) {
			
		}

		return res;
	}

	public boolean isValidConfiguration(Configuration conf) {
		boolean res = false;
		if (reasoner.equals("Choco2")) {

			ChocoReasoner choco = new ChocoReasoner();
			fm.transformto(choco);
			choco.applyStagedConfiguration(conf);
			Model chocoProblem = choco.getProblem();
			Solver solver = new CPSolver();
			solver.read(chocoProblem);
			res = solver.solve();
			choco.unapplyStagedConfigurations();
		} else if (reasoner.equals("Pacogen")) {
			
			PacogenReasoner paco = new PacogenReasoner();
			try {
				paco.buildModel() ;
				paco.Reason() ;

			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Where is Paco??");
			}
			System.out.println(paco.getVarDomain());

		} else if (reasoner.equals("Sat4j")) {
			
		}

		return res;
	}

	
	@Override
	public SetVariable features() {
		return null;
	}

	@Override
	public boolean setMandatory(FeatureVariable ft) {
		return false;
	}

	@Override
	public boolean setOptional(FeatureVariable ft) {
		return false;
	}

	@Override
	public boolean setAlternative(FeatureVariable ft) {
		return false;
	}

	@Override
	public boolean setOr(FeatureVariable ft) {
		return false;
	}

	@Override
	public double counting() {
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

			// propagation
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
		return null;
	}

	@Override
	public double CTCR() {
		return 0;
	}

	@Override
	public ImplicationGraph<String> computeImplicationGraph() {
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, String... fts) {
		return null;
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		return null;
	}

	@Override
	public FeatureModelVariable ksynthesis(KnowledgeSynthesis kn) {
		return null;
	}

	@Override
	public void setFeatureAttribute(FeatureVariable ft, String attributeID,
			Variable rVar) {}

	@Override
	public RType getRType() {
		return null;
	}

	@Override
	public Variable copy() {
		return null;
	}

	@Override
	public void setValue(Variable vari) {

	}

	@Override
	public String getSpecificValue() {
		return null;
	}

	@Override
	public FeatureVariable root() {
		return null;
	}


	private Collection<Pair>getPairs(){
		Collection<Feature> features = fm.getFeatures();
		
		//1st generate all pairs
		ArrayList<Pair> res = new ArrayList<Pair>();
		for(Feature f: features){
			for(Feature f2:features){
				if(f instanceof Feature && f2 instanceof Feature && !f.getName().equals(f2.getName())){
					Pair p = new Pair(f, f2);
					if(!res.contains(p)){
						res.add(p);
					}
				}
			}
		}
		Iterator<Pair> pairs=res.listIterator();
		while(pairs.hasNext()){
			Pair p = pairs.next();
			Configuration c = new Configuration();
			c.addElement(p.featurea, 1);
			c.addElement(p.featureb, 1);
			
			if(!isValidConfiguration(c)){
				pairs.remove();
			}
		}

		return res;
	}

}
