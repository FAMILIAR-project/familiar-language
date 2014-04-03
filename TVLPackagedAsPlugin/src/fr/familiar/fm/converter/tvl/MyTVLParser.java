package fr.familiar.fm.converter.tvl;

import java.io.File;
import java.io.FileNotFoundException;

import org.sat4j.specs.TimeoutException;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.BooleanForm;
import be.ac.fundp.info.TVLParser.Util.NormalForm;
import be.ac.fundp.info.TVLParser.Util.Solver;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class MyTVLParser extends TVLParser {

	public MyTVLParser(File inputFile) throws FileNotFoundException {
		super(inputFile);
	}

	public MyTVLParser(String inputDiagram) {
		super(inputDiagram);
	}

	public NormalForm getNormalFormInternalRepresentation() throws Exception {
		try {
			return getNormalFormInternal();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public BooleanForm getBooleanFormRepresentation() throws Exception {
		if (!hasRunNormalizedFormParser)
			runNormalizedFormParser();
		try {
			return getBooleanFormInternal();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Get the normalised form of the model.
	 * 
	 * @throws Exception
	 */
	public String getNormalFormToString() throws Exception {
		return getNormalFormInternalRepresentation().getRootFeature().toString("");
	}

	/**
	 * Get the boolean form of the model.
	 * 
	 * @throws Exception
	 */
	public String getBooleanFormToString() throws Exception {
		return getBooleanFormRepresentation().getRootFeature().toString("");
	}

	public void runSolver() {
		if (!this.hasRunSolver) {
			FeatureSymbol root = getRoot();
			if (root != null) {
				try {
					this.solver = new Solver(root, 180);
					this.hasRunSolver = true;
				} catch (Exception e) {
					this.solverException = e;
				}
			}
		}
	}

	/**
	 * Return the feature symbol corresponding to the path "path". Make sure
	 * that this path
	 * corresponds to an existing feature symbol.
	 * 
	 * @param path
	 *            A path which leads to a feature.
	 * 
	 * @return
	 *         The feature symbol corresponding to the path "path".
	 */
	public FeatureSymbol getFeatureSymbol(String path) throws AmbiguousReferenceException, SymbolNotFoundException,
			ChildrenFeatureNotFoundException {
		return normalizedFormParser.getFeatureSymbol(path);
	}

	public long countSolutions() throws TimeoutException, FeatureModelInvalidException {
		if (!hasRunSolver)
			runSolver(120); // TODO verify is enough
		if (hasRunSolver) {
			if (!solver.isSatisfiable())
				return 0;
			return solver.countSolutions();
		}
		return 0;
	}
}
