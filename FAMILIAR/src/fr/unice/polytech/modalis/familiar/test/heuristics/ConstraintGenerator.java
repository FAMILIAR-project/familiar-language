package fr.unice.polytech.modalis.familiar.test.heuristics;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;

public class ConstraintGenerator {

	private static final String OUTPUT_FOLDER = "inputFML/splot-modified/";
	
	private static final double CONSTRAINT_PER_FEATURE = 1;
	private static final double PERCENTAGE_IMPLIES = 0.8;

	private Random random;

	public static void main(String[] args) {
		ASE2013KSynthesisTest ase2013kSynthesisTest = new ASE2013KSynthesisTest();
		List<FeatureModelVariable> fms = ase2013kSynthesisTest.getSPLOTFeatureModels();
		ConstraintGenerator constraintGenerator = new ConstraintGenerator();

		for (FeatureModelVariable fm : fms) {
			System.out.println(fm.getCompleteIdentifier());
			constraintGenerator.clearFM(fm);
			int nbAddedConstraints = constraintGenerator.generateConstraints(fm, CONSTRAINT_PER_FEATURE, PERCENTAGE_IMPLIES);
			System.out.println(nbAddedConstraints + " added constraints");
		}
	}


	public ConstraintGenerator() {
		random = new Random();
	}

	/**
	 * Remove all the constraints and keep only the hierarchy with optional features
	 * @param fm
	 */
	private void clearFM(FeatureModelVariable fm) {
		fm.removeAllConstraints();
		FeatureGraph<String> diagram = fm.getFm().getDiagram();
		for (FeatureEdge edge : new HashSet<FeatureEdge>(diagram.edges())) {
			if (edge.getType() != FeatureEdge.HIERARCHY) {
				diagram.removeEdge(edge);
			}
		}
	}

	
	/**
	 * Generate constraints and save the new FM
	 * @param percentageImplies 
	 * @param constraintPerFeature 
	 * @param fms
	 * @return number of added constraints
	 */
	public int generateConstraints(FeatureModelVariable fm, double constraintPerFeature, double percentageImplies) {

		ImplicationGraph<String> big = fm.computeImplicationGraph();
		Set<Expression<String>> excludesEdges = fm.computeExcludesEdge();

		int maxNbAddedConstraints = (int) (fm.features().size() * constraintPerFeature);
		int nbAddedConstraints = 0;

		for (int i = 0; i < maxNbAddedConstraints ; i++) {
			boolean addedConstraint = false;
			for (int nbTry = 0; nbTry<10 && !addedConstraint; nbTry++) {
				if (random.nextDouble() < percentageImplies) {
					addedConstraint = addImpliesConstraint(fm, big);	
				} else {
					addedConstraint = addExcludesConstraint(fm, excludesEdges);	
				}
			}
			if (addedConstraint) {
				nbAddedConstraints++;
			}
		}

		saveFM(fm);

		return nbAddedConstraints;

	}


	/**
	 * Try to add an "implies" constraint
	 * @param fm
	 * @param big
	 * @return the constraint was successfully added
	 */
	private boolean addImpliesConstraint(FeatureModelVariable fm, ImplicationGraph<String> big) {
		List<String> leftFeatures = new ArrayList<String>(fm.getFm().features());
		List<String> rightFeatures = new ArrayList<String>(fm.getFm().features());

		String feature1 = null;
		String feature2 = null;

		// Find a pair of features that are not in an "implies" relation
		while (!leftFeatures.isEmpty() && feature2 == null) {
			// Select feature1
			feature1 = leftFeatures.get(random.nextInt(leftFeatures.size()));

			// Select feature2 among the features that are not implied by feature1
			rightFeatures = new ArrayList<String>(fm.getFm().features());
			rightFeatures.remove(feature1);
			for (SimpleEdge implied : big.outgoingEdges(feature1)) {
				rightFeatures.remove(big.getTarget(implied));
			}

			if (!rightFeatures.isEmpty()) {
				feature2 = rightFeatures.get(random.nextInt(rightFeatures.size()));
			} else {
				leftFeatures.remove(feature1);
			}
		}

		if (feature2 == null) {
			return false;
		}

		// Add a constraint
		Expression<String> exp1 = new Expression<String>(feature1);
		Expression<String> exp2 = new Expression<String>(feature2);

		Expression<String> constraint = new Expression<String>(ExpressionType.IMPLIES, exp1, exp2);
		fm.addConstraint(constraint);

		// Check that the constraint does not produce dead features
		if (!fm.deads().isEmpty()) {
			fm.removeConstraint(constraint);
			return false;
		} else {
			big.addEdge(feature1, feature2);
			return true;
		}

	}

	/**
	 * Try to add an "excludes" constraint
	 * @param fm
	 * @param excludesEdges
	 * @return the constraint was successfully added
	 */
	private boolean addExcludesConstraint(FeatureModelVariable fm, Set<Expression<String>> excludesEdges) {
		List<String> leftFeatures = new ArrayList<String>(fm.getFm().features());
		List<String> rightFeatures = new ArrayList<String>(fm.getFm().features());

		String feature1 = null;
		String feature2 = null;

		// Find a pair of features that are not in an "excludes" relation
		while (!leftFeatures.isEmpty() && feature2 == null) {
			// Select feature1
			feature1 = leftFeatures.get(random.nextInt(leftFeatures.size()));

			// Select feature2 among the features that are not excluded by feature1
			rightFeatures = new ArrayList<String>(fm.getFm().features());
			rightFeatures.remove(feature1);
			for (Expression<String> excluded : excludesEdges) {
				String left = excluded.getLeft().getFeature();
				String right = excluded.getRight().getLeft().getFeature();
				if (feature1.equals(left)) {
					rightFeatures.remove(right);	
				} else if (feature1.equals(right)) {
					rightFeatures.remove(left);
				}

			}

			if (!rightFeatures.isEmpty()) {
				feature2 = rightFeatures.get(random.nextInt(rightFeatures.size()));
			} else {
				leftFeatures.remove(feature1);
			}
		}

		if (feature2 == null) {
			return false;
		}

		// Add a constraint
		Expression<String> exp1 = new Expression<String>(feature1);
		Expression<String> exp2 = new Expression<String>(feature2);

		Expression<String> constraint = new Expression<String>(ExpressionType.IMPLIES, exp1, exp2.not());
		fm.addConstraint(constraint);

		// Check that the constraint does not produce dead features
		if (!fm.deads().isEmpty()) {
			fm.removeConstraint(constraint);
			return false;
		} else {
			excludesEdges.add(constraint);
			return true;
		}

	}


	/**
	 * Save the FM in the ouput folder
	 * @param fm
	 */
	private void saveFM(FeatureModelVariable fm) {
		File computedFMFile = new File(OUTPUT_FOLDER + fm.getIdentifier().substring(8) + ".xml");
		try {
			PrintStream standardOutput = System.out;
			PrintStream fileOutput = new PrintStream(computedFMFile);
			System.setOut(fileOutput); // Hack for this stupid dumpXML function
			fm.toSPLOT().dumpXML();
			System.setOut(standardOutput);
			fileOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
