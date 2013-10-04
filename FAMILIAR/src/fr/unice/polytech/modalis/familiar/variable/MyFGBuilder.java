/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable;

import fr.unice.polytech.modalis.familiar.experimental.XorGroup;
import gsd.graph.ImplicationGraph;
import gsd.graph.TransitiveReduction;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FGBuilder;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;

import java.util.HashSet;
import java.util.Set;

import de.ovgu.featureide.fm.ui.editors.featuremodel.editparts.FeatureEditPart;
import net.sf.javabdd.BDD;

/**
 * A quick adaptation of FG synthesis for not computing prime implicants 
 * (does not scale well for PCMs)
 * @author macher1
 *
 */
public class MyFGBuilder<T extends Comparable<T>> extends FGBuilder<T> {


	
	/**
	 * @param builder
	 */
	public MyFGBuilder(BDDBuilder<T> builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}
	
	public FeatureModel<T> build(Formula<T> f) {
		_formula = f.getBDD();
		_support = f.getDomain();
		_factory = _formula.getFactory();

		FeatureGraph<T> g = _fgf.mkTop();
		FeatureModel<T> fm = new FeatureModel<T>(g);

		ImplicationGraph<T> impl = IGBuilder.build(f, _builder);

		mkHierarchyAndGroups(g, impl);

		TransitiveReduction.INSTANCE.reduce(g);

		mkSyntheticRoot(g);

		mkMutexGroups(g);

		mkXorGroups (g, f); // with Mutex and without Prime implicants/Or-groups
		// mkOrGroups(g);

		return fm;
	}
	
	public void mkXorGroups(FeatureGraph<T> g, Formula<T> f) {
		Set<FeatureEdge> mtxs = g.selectEdges(FeatureEdge.MUTEX);
		for (FeatureEdge mutexGroup : mtxs) {
			
			if (isXorGroup(f, g, mutexGroup)) {
				g.addEdge(g.getSources(mutexGroup), 
						g.getTarget(mutexGroup), FeatureEdge.XOR);
				g.removeEdge(mutexGroup);

			}		
	
		}
	
	}
	
	private boolean isXorGroup(Formula<T> f, FeatureGraph<T> g, FeatureEdge e) {
		
		FeatureNode<T> target = g.getTarget(e); 
		Set<FeatureNode<T>> sources = g.getSources(e) ;
		
		Set<T> fts = target.features() ;
		T ft = fts.iterator().next() ;  // first one is sufficient 
		
		BDD parent = _builder.get(ft);
		BDD children = mkDisjunction(sources);

		BDD entail = parent.imp(children);

		BDD oBDD = f.getBDD().id();
		if (oBDD.and(entail.not()).isZero()) {
			return true;
		}

		return false;
	}
	
	private BDD mkDisjunction(Set<FeatureNode<T>> fnodes) {
		BDD result = _builder.zero();
		for (FeatureNode<T> f : fnodes) {
			Set<T> fts = f.features() ;
			T ft = fts.iterator().next() ; // first one is sufficient 
			result.orWith(_builder.get(ft));
		}
		return result;

	}

	

}
