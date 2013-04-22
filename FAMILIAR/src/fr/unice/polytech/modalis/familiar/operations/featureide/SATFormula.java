/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations.featureide;

import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.SatSolver;
import org.sat4j.specs.TimeoutException;

import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.fm.converter.featureide.MyDimacsOutputSolver;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public abstract class SATFormula implements FMLFormula {
	
	private static Logger _LOGGER = Logger.getLogger(SATFormula.class);

	protected Node _node;

	protected Set<String> _domain = new HashSet<String>() ; 

	public SATFormula(FeatureModelVariable fmv) {
		_node = mkNode(fmv);
		computeAndFixDomain(); 	
		
	}

	private void computeAndFixDomain() {
		// collect all nodes' names in domain
		_collectNodeNames(_node, _domain);
	}
	
	@Override
	public String toString() {
		return "" + _node + " " + _domain ; 
	}
	
	


	 private void _collectNodeNames(Node node, Set<String> domain) {
		 
		if (node == null) return ; 
		if (node instanceof Literal) {
			Literal lit = (Literal) node ;
			domain.add(lit.var.toString().trim());
			return ;
		}		
		
		Node[] children = node.getChildren() ;
		if (children.length == 0)
			return ; 
		
		for (Node child : children) {
			_collectNodeNames(child, domain) ;
		}
		

	}
	
		



	/**
	 * @param fmv
	 *            feature model to transform in propositional logic
	 * @return Node (for Node4J) representing the formula of feature model
	 */
	public abstract Node mkNode(FeatureModelVariable fmv);
	
	/**
	 * @param constraint to transform in propositional logic
	 * @return Node (for Node4J) representing the formula of the constraint
	 */
	public abstract Node mkNode(Expression<String> expr);

	public SATFormula(Node node) {
		_node = node;
		computeAndFixDomain();
	}



	public SATFormula(Expression<String> expr) {
		_node = mkNode(expr) ;
	}


	public boolean isValid() {
		try {
			return mkSATSolver().isSatisfiable();
		} catch (TimeoutException e) {
			FMLShell.getInstance().printError(
					"Satisfiability timeout: " + e.getMessage());
			return false;
		}
	}

	/**
	 * @return the number of valid configurations note that we need to create a
	 *         new SAT solver since internal state of the SAT solver is modified
	 *         when calling counting / configs *
	 */
	public double counting() {
		return mkSATSolver().countSolutions();
	}

	public String configs() {

		double nConfigs = new Double(counting()); // TODO: rather sad to count
													// and then enumerate
		// TODO: much more better to extend SatSolver (fields are protected,
		// good design choice!)
		try {
			return mkSATSolver().getSolutions((int) nConfigs);
		} catch (TimeoutException e) {
			FMLShell.getInstance().printError(
					"Unable to load the solver " + e.getMessage());
			return null;
		}

	}

	public Set<String> configs2() {

		double nConfigs = counting(); // TODO: rather sad to count and then
										// enumerate
		// TODO: much more better to extend SatSolver (fields are protected,
		// good design choice!)
		MySatSolver satSolver = (MySatSolver) mkSATSolver();
		try {
			return satSolver.getSetSolutions((int) nConfigs);
		} catch (TimeoutException e) {
			FMLShell.getInstance().printError(
					"Unable to load the solver " + e.getMessage());
			return null;
		}
	}

	protected SatSolver mkSATSolver() {
		return new MySatSolver(_node, SAT_TIMEOUT);
	}

	protected SatSolver mkSATSolver(Node node) {
		return new MySatSolver(node, SAT_TIMEOUT);
	}

	public Set<String> _cores(Collection<String> ftsToConsider) {

		Set<String> fts = new HashSet<String>();
		if (!new SATFMLFormula(_node).isValid())
			return fts;

		for (Literal literal : mkSATSolver().knownValues()) {
			if (literal.positive) {
				fts.add(literal.var.toString().trim());
			}
		}

		return fts;
	}

	public Set<String> cores(Collection<String> ftsToConsider) {
		return _cores(ftsToConsider);
	}

	public Set<String> _cores0(Collection<String> ftsToConsider) {

		Set<String> fts = new HashSet<String>();
		if (!new SATFMLFormula(_node).isValid())
			return fts;

		for (String ftToConsider : ftsToConsider) {
			Literal lFt = new Literal(ftToConsider, false);
			if (!new SATFMLFormula(new And(_node, lFt)).isValid()) {
				fts.add(ftToConsider);
			}
		}

		return fts;
	}

	public Set<String> deads(Set<String> ftsToConsider) {
		return _deads (ftsToConsider);
	}

	public Set<String> _deads(Set<String> ftsToConsider) {

		Set<String> fts = new HashSet<String>();
		if (!new SATFMLFormula(_node).isValid()) // FIXME time consuming
		 return ftsToConsider ; // all deads!

		for (Literal e : mkSATSolver().knownValues())
			if (!e.positive && !e.var.toString().equals("False")
					&& !e.var.toString().equals("True")) {

				fts.add(e.var.toString().trim());

			}

		return fts;
	}

	public String configsDebug() {

		double nConfigs = new Double(counting()); // TODO: rather sad to count
													// and then enumerate
		// TODO: much more better to extend SatSolver (fields are protected,
		// good design choice!)
		try {
			return new SatSolver(_node, SAT_TIMEOUT)
					.getSolutions((int) nConfigs);
		} catch (TimeoutException e) {
			FMLShell.getInstance().printError(
					"Unable to load the solver " + e.getMessage());
			return null;
		}

	}

	public Set<String> _deads0(Set<String> ftsToConsider) {
		Set<String> fts = new HashSet<String>();
		if (!new SATFMLFormula(_node).isValid())
			return ftsToConsider; // all deads!

		for (String ftToConsider : ftsToConsider) {
			Literal lFt = new Literal(ftToConsider, true);
			if (!new SATFMLFormula(new And(_node, lFt)).isValid()) {
				fts.add(ftToConsider);
			}
		}

		return fts;
	}

	public Node getNode() {
		return _node;
	}

	public void setNode(Node node) {
		_node = node;
	}
	
	public boolean implies(String u, String v) {
		Node problem = new And(getNode(), 
				SATBuilder.mkTrueNode(), 
				new Not(SATBuilder.mkFalseNode()));

		SatSolver solver = mkSATSolver(problem);
		return implication(u, v, solver); 
	}

	public <T> ImplicationGraph<T> computeImplicationGraph(Set<T> domain) {
		_LOGGER.debug("SAT computation of IG");
		ImplicationGraph<T> impl = new ImplicationGraph<T>();
		Node problem = new And(getNode(), 
							SATBuilder.mkTrueNode(), 
							new Not(SATBuilder.mkFalseNode()));

		SatSolver solver = mkSATSolver(problem);

		for (T f : domain)
			impl.addVertex(f);

		T[] aDomain = (T[]) domain.toArray();

		
		boolean[][] marked = new boolean[aDomain.length][aDomain.length];
		for (int i = 0; i < aDomain.length; i++) 
			for (int j = 0; j < aDomain.length; j++)
				if (i == j) {
					marked[i][j] = true ;
				}
				else {
					marked[i][j] = false ;
				}
		
		for (int i = 0; i < aDomain.length; i++) {	
			

			T u = aDomain[i];
			_LOGGER.debug("u(" + i + ") = " + u);
			for (int j = 0; j < aDomain.length; j++) {
				
				if (marked[i][j])
					continue ; // next
				
				T v = aDomain[j];
				if (implication(u, v, solver)) {
				//if (implication(u, v, problem)) {
					impl.addEdge(u, v);
					marked[i][j] = true ; 
				}
				else {
					/*
					Node problemWithImpl = new And(problem, new Literal(u), new Literal(v, false));
					SatSolver solverWithImpl = mkSATSolver(problemWithImpl);
					for (int x = 0; x < aDomain.length; x++) {
						
						T lX = aDomain[x];
						_LOGGER.debug("lX(" + x + ") = " + lX);
						
						for (int y = 0; y < aDomain.length; y++) {
							
							T lY = aDomain[y];
							
							try {
								if (solverWithImpl.isSatisfiable(new Literal(lX))
										&& !solverWithImpl.isSatisfiable(new Literal(lY))  
										&& (marked[x][y] == false))
								    marked[x][y] = true ;
							} catch (TimeoutException e) {
								e.printStackTrace();
								continue ; 
							} 
						}
					}
					*/
					
				}
				
	
			}
		}
		return impl;
	}
	
	@Deprecated
	private <T> boolean implication(T u, T v, Node n) {
		And imply = new And(n, new Literal(u), new Literal(v, false));
		return !new SATFMLFormula(imply).isValid() ;
	}

	
	
	/** FIXME It seems the solver has its own internal state, thus disturbing a bit the process
	 * @param u
	 * @param v
	 * @param solver
	 * @return
	 */
	private <T> boolean implication(T u, T v, SatSolver solver) {
		// _LOGGER.debug("(implication) u=" + u +
		// "; v=" + v);

		// !(u -> v) => (u ^ !v)
		And imply = new And (new Literal(u, true), new Literal (v, false)); //new And(new Literal(u), new Literal(v, false));
		//return !new SATFMLFormula(imply).isValid() ;
		try {
			return !solver.isSatisfiable(imply);
		} catch (TimeoutException e) {
			e.printStackTrace();
			FMLShell.getInstance().printError("Timeout IG " + e);
			return false;
		}
	}

	public <T> ExclusionGraph<T> computeExclusionGraph(Set<T> domain) {
		_LOGGER.debug("SAT computation of EG");
		ExclusionGraph<T> excl = new ExclusionGraph<T>();

		Node problem = getNode(); // + TRUE & FALSE
		problem = new And(problem, SATBuilder.mkTrueNode(), new Not(
				SATBuilder.mkFalseNode()));
		for (T f : domain)
			excl.addVertex(f);

		SatSolver solver = mkSATSolver(problem);

		T[] aDomain = (T[]) domain.toArray();

		for (int i = 0; i < aDomain.length; i++) {

			T u = aDomain[i];
			_LOGGER.debug("u(" + i + ") = " + u);

			/*
			 * if (isDead(u, problem)) { continue ; }
			 */

			for (int j = i + 1; j < aDomain.length; j++) {

				T v = aDomain[j];
				/*
				 * if (isDead(v, problem)) { continue ; }
				 */
				if (exclusion(u, v, solver))
					excl.addEdge(u, v);

			}
		}

		return excl;
	}

	private <T> boolean isDead(T u, Node problem) {

		try {
			return !mkSATSolver(problem).isSatisfiable(new Literal(u, true));
		} catch (TimeoutException e) {
			e.printStackTrace();
			FMLShell.getInstance().printError("Timeout dead " + e);
			return false;
		}
	}

	private <T> boolean exclusion(T u, T v, SatSolver solver) {
		And ch = new And(new Literal(u), new Literal(v)); // ! (u -> !v) = u & v
		try {
			return !solver.isSatisfiable(ch);
		} catch (TimeoutException e) {
			e.printStackTrace();
			FMLShell.getInstance().printError("Timeout EG " + e);
			return false;
		}
	}
	
	public boolean excludes (String u, String v) {
		Node problem = new And(getNode(), 
				SATBuilder.mkTrueNode(), 
				new Not(SATBuilder.mkFalseNode()));

		SatSolver solver = mkSATSolver(problem);
		return exclusion(u, v, solver);
	}
	
	
	
	
	public String toDIMACS() {
		MyDimacsOutputSolver dimacsSerializer = mkDimacsOutputSolver();
		OutputStream oStream = dimacsSerializer.getOutputStream() ;
		return mkHeader(dimacsSerializer.getVar2IDS()) + oStream.toString() ; 
	}
	
	private String mkHeader(Map<String, Number> var2IDs) {
		StringBuilder sb = new StringBuilder() ; 
		Set<String> keys = var2IDs.keySet() ;
		for (String key : keys) {
			Number n = var2IDs.get(key);
			sb.append("c ") ;
			sb.append(n + " ") ;
			sb.append(key + System.getProperty("line.separator"));
		}
		
		return sb.toString() ; 
	}

	/**
	 * @return set of variable names (domain)
	 */
	@Override
	public Set<String> getDomain() {
		return _domain;
	}

	
	protected MyDimacsOutputSolver mkDimacsOutputSolver() {
		return new MyDimacsOutputSolver(_node, SAT_TIMEOUT);
	}
	
	public Comparison compare(SATFMLFormula fla2) {
		SATFormulaComparator cmp = new SATFormulaComparator(SAT_TIMEOUT);
		return cmp.compare(this, fla2); 
	}

}
