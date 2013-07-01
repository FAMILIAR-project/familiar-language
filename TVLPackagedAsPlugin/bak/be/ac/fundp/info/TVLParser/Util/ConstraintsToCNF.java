package be.ac.fundp.info.TVLParser.Util;

/**
 * ConstraintsToCNF.java <br>
 * <br>
 * For the LTseq algorithm, see Towards an Optimal CNF Encoding of Boolean
 * Cardinality Constraints (Technical Report) <i>Carsten Sinz</i> and
 * <a>http://www.carstensinz.de/software.html</a> for the CPP source file.
 * 
 * This method has been originally wrote by Xavier Devroey and Michaël Marcozzi.
 * 
 */
public class ConstraintsToCNF {

	/**
	 * Generates a CNF encoding for a "less than" constraint.
	 * 
	 * @param firstEncVar
	 *            Must be > than all the numerical ID from inputs (otherwise the result
	 *            is not guaranteed). It represents the ID of the first artificial 
	 *            variable. Be sure that all the features and attributes have a numerical
	 *            ID before using this method.
	 *            
	 * @param k
	 *            Must be 1 <= k <= inputs.length. It represents the maximum number of
	 *            selectable inputs.
	 *            
	 * @param inputs
	 *            Must have inputs.length >= 2. It's an array of numerical ID.
	 *            
	 * @throws IllegalArgumentException
	 *             If the parameters don't respect : 1 <= k <= inputs.length and
	 *             inputs.length >= 2
	 *             
	 * @return A CNF encoding for a "less than" constraint.
	 */
	public static int[][] lessThanConstraint(int firstEncVar, int k,
			int[] inputs) {
		if (inputs.length < 2 || k < 1 || k > inputs.length) {
			throw new IllegalArgumentException(
					"Parameters must be : 1 <= k <= inputs.length and inputs.length >= 2 (inputs.length = "
							+ inputs.length + " ; k = " + k);
		}

		int n = inputs.length;
		int nbrClauses = 2 * n * k + n - 3 * k - 1;
		int[][] tab = new int[nbrClauses][];
		int curIndex = 0;

		// clauses of first partial sum (i.e. i=0)
		tab[curIndex++] = new int[] { -inputs[0], s_index(firstEncVar, 0, 0, k) };

		for (int j = 1; j < k; j++) {
			tab[curIndex++] = new int[] { -s_index(firstEncVar, 0, j, k) };
		}

		// clauses for general case (i.e. 0 < i < n-1)
		for (int i = 1; i < n - 1; i++) {
			tab[curIndex++] = new int[] { -inputs[i], s_index(firstEncVar, i, 0, k) };
			
			tab[curIndex++] = new int[] { -s_index(firstEncVar, i - 1, 0, k), s_index(firstEncVar, i, 0, k) };
			for (int j = 1; j < k; j++) {
				tab[curIndex++] = new int[] { -inputs[i], -s_index(firstEncVar, i - 1, j - 1, k), s_index(firstEncVar, i, j, k) };
				
				tab[curIndex++] = new int[] {-s_index(firstEncVar, i - 1, j, k), s_index(firstEncVar, i, j, k) };
			}
			tab[curIndex++] = new int[] { -inputs[i], -s_index(firstEncVar, i - 1, k - 1, k) };
		}

		// last clause for last variable
		tab[curIndex++] = new int[] { -inputs[n - 1], -s_index(firstEncVar, n - 2, k - 1, k) };

		return tab;
	}

	private static int s_index(int firstEncVar, int i, int j, int k) {
		// auxiliary encoding variables s_{i,j}:
		// (n-1)*k variables, starting from 'firstEncVar'
		// index of s_{i,j} is: firstEncVar + i*k + j
		// (0 <= i < n-1, 0 <= j < k)
		return firstEncVar + i * k + j;
	}

	/**
	 * Equivalent to lessThanConstraint(firstEncVar, inputs.length - k, inputs).
	 * >=k(x1,...,xn) <=> <=(n-k)(not x1,..., not xn)
	 * @return A CNF encoding for a "grater than" constraint.
	 * 
     * This method has been originally wrote by Xavier Devroey and Michaël Marcozzi.
	 */
	public static int[][] greaterThanConstraint(int firstEncVar, int k,
			int[] inputs) {
		// Negate all vars from inputs
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = -inputs[i];
		}
		int[][] res = lessThanConstraint(firstEncVar, inputs.length - k, inputs);
		return res;
	}

	/**
	 * Return the number of auxiliary variables used for the generation of the
	 * LTn,k constraint into a CNF formula.
	 * 
	 * @return The number of auxiliary variables used by lessThanConstraint
	 * 
     * This method has been originally wrote by Xavier Devroey and Michaël Marcozzi.
	 */
	public static int getNbrAuxilaryVariablesUsedByLessThanEncoding(int n, int k) {
		return (n - 1) * k;
	}

	/**
	 * Return the number of auxiliary variables used for the generation of the
	 * GTn,k constraint into a CNF formula.
	 * 
	 * @return The number of auxiliary variables used by greaterThanConstraint
	 * 
     * This method has been originally wrote by Xavier Devroey and Michaël Marcozzi.
	 */
	public static int getNbrAuxilaryVariablesUsedByGreaterThanEncoding(int n,
			int k) {
		return (n - 1) * (n - k);
	}
}
