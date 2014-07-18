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

package fr.familiar.fm.converter.tvl;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.xtext.example.mydsl.fML.FeatureModel;

/**
 * TVL to FAMILIAR Translator class.
 * Currently supported :
 * 
 * - Parental relations :
 * + Mandatory / Optional relation
 * + XOR group relation in the case minCardinality == maxCardinality &&
 * minCardinality == 1 && nChildren > 1
 * + OR group relation in the case minCardinality != maxCardinality &&
 * minCardinality == 1 && maxCardinality == nChildren
 * + Special cardinality groups ex : x = 5 children, minCardinality = 2,
 * maxCardinality = 4.
 * 
 * - Attributes :
 * + Boolean attributes. Handle special case like or-xor-special card subgroups.
 * 
 * @author Charles Vanbeneden
 * 
 */

public class TVLTranslator {

	static String ERROR_MESSAGE = "TVL library is not installed";

	/**
	 * Constructor for a file input.
	 * 
	 * @param inputFile
	 *            TVL file containing the feature model to translate.
	 * @throws Exception
	 */
	public TVLTranslator(File inputFile) throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * Constructor for a string input.
	 * 
	 * @param input
	 *            String containing the TVL feature model to parse.
	 * @throws Exception
	 */
	public TVLTranslator(String input) throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

//	/**
//	 * 
//	 * @return The complete FAMILIAR feature model resulting of the TVL feature
//	 *         model translation.
//	 * @throws Exception
//	 */
//	private FeatureModelImpl translateToFAMILIAR() throws Exception {
//		throw new Exception(ERROR_MESSAGE);
//	}

//	/**
//	 * Side effect : Modify this._fm, the FAMILIAR feature model. Complete
//	 * _nameSpace with the names encountered. Fill _warnings with messages
//	 * concerning ignored TVL structures.
//	 * Adds :
//	 * - productions
//	 * - temp productions when there is a special transformation. Ex: boolean
//	 * attribute with xor-or-spec card group.
//	 * - contraints when there is special cardinality relations.
//	 * 
//	 * @param featureTVL
//	 *            The TVL feature from which it's necessary to create FAMILIAR
//	 *            productions. This function is recursive and thus generates
//	 *            children's productions and so on...
//	 * @param path
//	 *            Must be set at "". It's the current path in the TVL tree.
//	 */
//	private void generateEntryProduction(Feature featureTVL, String path) {
//		
//	}

//	/**
//	 * 
//	 * Attribute type :<br />
//	 * UNKNOWN = 0;<br />
//	 * INT = 1;<br />
//	 * REAL = 2;<br />
//	 * BOOL = 3;<br />
//	 * ENUM = 4;<br />
//	 * STRUCT = 5;<br />
//	 * STRUCT_FIELD = 6;<br />
//	 * USER_CREATED = 7;<br />
//	 * 
//	 * @param type
//	 *            Type from a TVL Feature / Feature Symbol / ...
//	 * @return A string containing the english representation of the given type.
//	 */
//	private String getStringEquivalentToAttributeType(Integer type) {
//		return null;
//	}

//	/**
//	 * Side-effect :<br />
//	 * Modify the FAMILIAR feature model <i>_fm</i> by adding the translated
//	 * constraints.<br />
//	 * Modify the warnings list <i>_warnings</i> if there are constraints
//	 * ignored.
//	 * 
//	 * @param featureTVL
//	 *            The feature containing constraints you want to translate.
//	 * @param path
//	 *            The path of <i>featureTVL</i>.
//	 */
//	private void generateConstraints(Feature featureTVL, String path) {
//		
//	}

//	/**
//	 * Recursive function.
//	 * 
//	 * @param be
//	 *            The expression to verify.
//	 * @return True if the given <i>be</i> expression is only composed of
//	 *         booleans. False otherwise.
//	 */
//	private boolean isOnlyComposedOfBooleanExpression(BooleanExpression be) {
//		return null;
//	}

//	/**
//	 * Recursive function.
//	 * 
//	 * Side-effect : <br />
//	 * <i>_warnings</i> is modified for adding messages about ignored
//	 * structures.
//	 * 
//	 * @param expression
//	 *            TVL expression to translate to a FAMILIAR expression.
//	 * @return FAMILIAR expression resulting of the translation of the given
//	 *         <i>expression</i>
//	 */
//	private CNFExpression translateConstraintExpressionTree(Expression expression) {
//		return null;
//	}

//	/**
//	 * Represent (not g v (GTseq(fn,...,fi) ^ LTseq(fn,...fj))) from Metzger
//	 * 2007 p250. This formula creates a boolean constraint equivalent of a i-j
//	 * card special group relation.
//	 * 
//	 * @param top
//	 *            TVL feature from where you call this function.
//	 * @param currentFAMILIARProduction
//	 *            The FAMILIAR production from where you call this function.
//	 *            This
//	 *            production is modified when artificial features are created.
//	 * @return An OPTIMAL constraint representing special cardinality relation.
//	 */
//	private CNF cardIJ(Feature top, Production currentFAMILIARProduction) {
//		return null;
//	}

//	/**
//	 * Used to translate a TVL constraint representation (int[][]) into a
//	 * FAMILIAR constraint representation.
//	 * The first dimension of this array represent AND relations (so you call
//	 * this function first).
//	 * The second dimension of this array represent OR relations.
//	 * 
//	 * @example The tree is composed like that :<br />
//	 *          And<br />
//	 *          | \<br />
//	 *          OR And<br />
//	 *          .........<br />
//	 *          There is NO null left or right.
//	 * 
//	 * @param alreadyDone
//	 *            Initialize it to 0.
//	 * @param solution
//	 *            TVL int array representing a constraint.
//	 * @param currentFAMILIARProduction
//	 *            The production from where you call this function. This
//	 *            production is modified when artificial features are created.
//	 * @return A CNFExpression representing the translated int tab to a FAMILIAR
//	 *         constraint.
//	 */
//	private CNFExpression generateAndExpressionFromTVLSolution(int alreadyDone, int[][] solution, Production currentFAMILIARProduction) {
//		return null;
//	}

//	/**
//	 * Used to translate a TVL or constraints representation (int[]) into a
//	 * FAMILIAR constraint representation.
//	 * 
//	 * @example The tree is composed like that :<br />
//	 *          OR<br />
//	 *          | \<br />
//	 *          CNFExpression OR<br />
//	 * <br />
//	 *          There is no null left or right.
//	 * 
//	 * @param alreadyDone
//	 *            Initialise it to 0.
//	 * @param solution
//	 *            TVL int array representing a constraint.
//	 * @param currentFAMILIARProduction
//	 *            The production from where you call this function. This
//	 *            production is modified when artificial features are created.
//	 * @returnA CNFExpression representing the translated int tab to a FAMILIAR
//	 *          constraint.
//	 */
//	private CNFExpression generateOrExpressionFromTVLSolution(int alreadyDone, int[] solution, Production currentFAMILIARProduction) {
//		return null;
//	}

//	/**
//	 * Add the <i>name</i> feature to <i>production</i> if the name doesn't
//	 * already exists.
//	 * 
//	 * This function is used to add temp feature to the production without
//	 * thinking about duplicates.
//	 * 
//	 * Called by generateOrExpressionFromTVLSolution.
//	 * 
//	 * @param production
//	 *            The production where you want to add a feature.
//	 * @param name
//	 *            The name of the feature to add.
//	 */
//	private void addOptionalFeatureToProductionNoDuplicates(Production production, String name) {
//		
//	}

	/**
	 * 
	 * @return The FAMILIAR internal representation of the TVL feature model.
	 * @throws Exception
	 */
	public FeatureModel getFAMILIARFeatureModel() throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * 
	 * @return A string containing the translation of the TVL normal form into
	 *         FAMILIAR FML representation.
	 * @throws Exception
	 */
	public String getFAMILIARFMLOutput() throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * Put the parser output to the specified (<i>path</i>) file.
	 * 
	 * @param path
	 *            Path to the file to write.
	 * @return True if the operation succeed. False otherwise.
	 */
	public boolean getFAMILIARFMLOuputToFile(String path) {
		return false;
	}

	/**
	 * 
	 * @return The string representation of the TVL Normal Form Object.
	 * @throws Exception
	 */
	public String getTVLNormalFormToString() throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * 
	 * @return The string representation of the TVL Boolean Form Object.
	 * @throws Exception
	 */
	public String getTVLBooleanFormToString() throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * @example
	 *          Input of the parser : "root A{group oneOf{C,D}}"<br />
	 *          Will return in console :<br />
	 *          Set<br />
	 *          |-Set<br />
	 *          | |- A - C<br />
	 *          |-Set<br />
	 *          | |- A - D
	 * 
	 * @return A set of solutions. A solution is a set of String representing
	 *         the name of each feature. There is no duplicates (definition of
	 *         Set). If the Feature Model is unsatisfiable, an empty Set is
	 *         returned. So, set.size() == 0 -> true
	 * @throws TimeoutException
	 *             If the solver compute for too long.
	 * @throws FeatureModelInvalidException
	 *             Thrown if the TVL input contain an invalid feature model.
	 * @throws UnsatisfiableModelException
	 */
	public Set<Set<String>> getTVLFilteredSolutions() throws Exception {
		throw new Exception(ERROR_MESSAGE);
	}

	/**
	 * 
	 * @return The number of solutions given by the TVL parser.
	 */
	public long getTVLNumberSolutions() {
		return 0;
	}

	/**
	 * Ouput in the console the TVL solutions of the input passed to the
	 * translator.
	 * 
	 * @example
	 *          Input of the parser : "root A{group oneOf{C,D}}"<br />
	 *          Will ouput in console :<br />
	 *          AC<br />
	 *          AD
	 */
	public void getTVLFilteredSolutionsToString() {
		
	}

	/**
	 * 
	 * @return True if there are warnings messages. So there are ignored TVL
	 *         structures.
	 */
	public Boolean hasWarnings() {
		return null;
	}

	/**
	 * 
	 * @return A string containing the representation of all warnings. Each
	 *         warning is on the next line (\r\n)
	 */
	public String getWarningsToString() {
		return null;
	}
}