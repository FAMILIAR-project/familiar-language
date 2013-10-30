package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

import org.junit.Ignore;
import org.junit.Test;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Or;

import com.google.common.collect.Sets;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;

@Ignore
public class FMLBDDtoCNF extends SATBuilderTest {

	public FMLBDDtoCNF(String fm) {
		super(fm);
	}

	@Test
	public void test1() throws Exception {

		FeatureModelVariable fmv1 = FM("fmv1", _fm);
		double n1 = fmv1.counting (CountingStrategy.BDD_FML);
		System.err.println("#fmv1=" + n1);
		BDD b1 = fmv1.getFormula().getBDD();
		if (fmv1.isValid()) { // FIXME in case unvalid
			Node nodeCNF = bDD2CNF(b1, fmv1.features().names());
			assertEquals(n1, new SATFMLFormula(nodeCNF).counting(), 0);
		}
	}

	private Node toPositives(int[] fDDs) {
		List<Node> positives = new ArrayList<Node>();
		for (int fDD : fDDs) {
			positives.add(mkPositiveLiteral(_builder.feature(fDD)));
		}
		Node pos = new And(positives); // sat.scanSet.map { v =>
										// List(PosLit(varMap(v))) } ++
		return pos;
	}

	private Node mkNegativeLiteral(String feature) {
		return new Literal(feature, false);
	}

	private Node mkPositiveLiteral(String feature) {
		return new Literal(feature, true);
	}

	public Node bDD2CNF(BDD b, Set<String> domain) {
		int varNum = _builder.getFactory().varNum();
		int[] set = new int[varNum];
		Set<Node> disj = new HashSet<Node>();

		bdd_2CNF_rec(_builder, b, set, disj, domain);
		int n = 0;

		/*
		 * Set<Node> conjs = new HashSet<Node>(); for (Node n1 : disj) { for
		 * (Node n2 : disj) { if (n1 != n2) { Node[] ch1 = n1.getChildren() ;
		 * for (int i = 0; i < ch1.length; i++) { Node c1 = ch1[i]; Node[] ch2 =
		 * n2.getChildren() ; for (int j = 0; j < ch2.length; j++) { Node c2 =
		 * ch2[j]; conjs.add(new Or(c1, c2)); } } }
		 * 
		 * } }
		 */
		Node conj = disj.iterator().next(); // FIXME if non valid
		for (Node node : disj) {
			conj = new Or(conj, node);
			conj = conj.toCNF(); // the winning strategy?
		}

		// new AtLeast(1, disj);

		return conj.toCNF();
	}

	private static void bdd_2CNF_rec(BDDBuilder<String> builder, BDD r,
			int[] set, Set<Node> disj, final Set<String> domain) {
		BDDFactory f = builder.getFactory();
		int n;

		if (r.isZero())
			return;
		else if (r.isOne()) {
			Node nod = SATBuilder.mkTrueNode();
			Set<String> varIncluded = new HashSet<String>();

			for (n = 0; n < set.length; n++) {

				if (set[n] > 0) {

					int var = f.level2Var(n);
					int val = set[n];
					String valName = builder.feature(var);

					if (val == 2) {
						// positive (truth assignment)
						nod = new And(nod, new Literal(valName, true));
					} else {
						// negative (false assignment)
						nod = new And(nod, new Literal(valName, false));
					}

					varIncluded.add(valName);
				}

			}

			Set<String> varsNotIncluded = Sets.difference(domain, varIncluded);

			for (String var : varsNotIncluded) {
				nod = new And(nod, new Or(new Literal(var, false), new Literal(
						var, true)));
			}

			disj.add(nod);

		} else {
			set[f.var2Level(r.var())] = 1;
			BDD rl = r.low();
			bdd_2CNF_rec(builder, rl, set, disj, domain);
			rl.free();

			set[f.var2Level(r.var())] = 2;
			BDD rh = r.high();
			bdd_2CNF_rec(builder, rh, set, disj, domain);
			rh.free();

			set[f.var2Level(r.var())] = 0;
		}
	}

}
