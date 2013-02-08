package fr.unice.polytech.modalis.familiar.test.featureide;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.prop4j.And;
import org.prop4j.Node;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.experimental.featureide.Node4JUtil;
import fr.unice.polytech.modalis.familiar.fm.converter.featureide.FMLDimacsReaderSAT;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATBuilder;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFormula;
import fr.unice.polytech.modalis.familiar.operations.featureide.SlicerSATFormula;
import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.test.regression.SetUtility;
import fr.unice.polytech.modalis.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

public class FMLKernelLinuxTest extends FMLTest {

	
	@Ignore
	@Test
	public void testBIG() throws Exception {
		
		FeatureModelVariableSATFormula fla = (FeatureModelVariableSATFormula) FMBuilder.parseDimacsWithSAT(
					new File ("/Users/mathieuacher/linux-variability-analysis/linux-variability-analysis-tools.formulas/" +
							//"2.6.32-2var.dimacs")) ; 
							//"freebsd-icse11.dimacs")) ;
							"ecos-icse11.dimacs")) ; 
							//"2.6.28.6-icse11.dimacs"));
		boolean b = fla.isValid() ; 
		System.err.println("b=" + b);
		/*
		Set<String> cores = fla.cores();
		System.err.println("cores=" + cores);
		System.err.println("#cores=" + cores.size());*/
		
		/*
		_shell.setVerbose(true);
		ExclusionGraph<String> eg = fla.computeExclusionGraph(_builder);
		Set<String> nE = eg.vertexSet() ;
		Set<Excludes<String>> edgesE = eg.edgeSet() ;
		
		System.err.println("#n=" + nE.size());
		System.err.println("#edges=" + edgesE.size());
		*/
		
		
		_shell.setVerbose(true);
		ImplicationGraph<String> ig = fla.computeImplicationGraph() ; 
		Set<String> n = ig.vertexSet() ;
		Set<SimpleEdge> edges = ig.edgeSet() ;
		
		System.err.println("#n=" + n.size());
		System.err.println("#edges=" + edges.size());
		
	}
	
	
	@Test
	public void testDimacs() throws Exception {

		String pathFolder = "/Users/mathieuacher/linux-variability-analysis/linux-variability-analysis-tools.formulas/";

		String filename = "ecos-icse11.dimacs" ; // "freebsd-icse11.dimacs"; //  "2.6.28.6-icse11.dimacs" ;
													// //"ecos-icse11.dimacs" ;
													// //"2.6.28.6-icse11.dimacs"
													// ; // "2.6.32-2var.dimacs"
													// ; //"2.6.32-2var.dimacs"
													// ; ////

		File file = new File(pathFolder + filename);
		
		FMLDimacsReaderSAT dimacsReader = new FMLDimacsReaderSAT() ; 
		List<Node> disjClauses = dimacsReader.parseDisjNodes(file) ;
		
		
	
		Node n = mkAnd0(disjClauses);

		List<Node> splited = Node4JUtil.splitConjunctions(n);
		System.err.println("splited=" + splited.size());
		// System.err.println("#" + Node4JUtil.countSizeOfNode(n));
		/*
		 * 
		 * time consuming STATS Set<String> deads = new
		 * SATFMLFormula(n).deads(new HashSet<String>(_varIDs.values()));
		 * System.err.println("deads=" + deads); System.err.println("#deads=" +
		 * deads.size());
		 * 
		 * Set<String> cores = new SATFMLFormula(n).cores(new
		 * HashSet<String>(_varIDs.values())); System.err.println("cores=" +
		 * cores);
		 */
		_shell.setVerbose(true);
		Set<String> meaningFullFtsDomain = new HashSet<String>(dimacsReader.getDomain());
		Set<String> allFtsDomain = Sets.union(dimacsReader.getFakes(), meaningFullFtsDomain);

		Set<String> ftsToExclude = SetUtility.selectRandomly(
				meaningFullFtsDomain, 95);

		
		// TODO: refactor
		SATFormula satSlice = (SATFormula) new SlicerSATFormula().runSliceFormulaSAT(null, n,
				allFtsDomain, Sets.union(dimacsReader.getFakes(), ftsToExclude),
				SliceMode.INCLUDING);

		System.err.println("****** end simplification");
	}

	private Node mkAnd0(List<Node> disjClauses) {
		return new And(disjClauses);
	}

	private Node mkAnd(List<Node> disjClauses) {
		int nAND = 0;
		Node n = SATBuilder.mkTrueNode();
		for (Node disj : disjClauses) {
			n = new And(n, disj);
			nAND++;
		}
		return n;
	}

	

	

}
