/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import fr.unice.polytech.modalis.familiar.operations.AggregatorFM;
import fr.unice.polytech.modalis.familiar.operations.ExpressionUtility;
import fr.unice.polytech.modalis.familiar.operations.FMLMerger;
import fr.unice.polytech.modalis.familiar.operations.Mode;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.Formula;

/**
 * @author macher1
 *
 */
public class FMLMergerWithConstraints extends FMLMerger {
	
	private static Logger _LOGGER = Logger.getLogger(FMLMergerWithConstraints.class) ; 


	// DEFAULT
	public static HierarchyMergerStrategy _DEFAULT_HIERARCHY_STRATEGY = HierarchyMergerStrategy.BASIC ; 
	private HierarchyMergerStrategy _hierarchyMergerStrategy = _DEFAULT_HIERARCHY_STRATEGY ; 

	public FMLMergerWithConstraints(List<FeatureModelVariable> lfmvs) {
		super (lfmvs);
	}

	@Override
	public FeatureModelVariable union() {
		FeatureModelVariable iFM = mergeFMVsWithConstraints(_lfms, Mode.StrictUnion); 
		return new FeatureModelVariable("", iFM.getFm());
	}

	@Override
	public FeatureModelVariable intersection() {
		FeatureModelVariable iFM = mergeFMVsWithConstraints(_lfms, Mode.Intersection) ; 
		return new FeatureModelVariable("", iFM.getFm());
	}
	
	/**
	 * @param lfmvs
	 *            list of feature model variables
	 * @param mode
	 * @return wrapper/utility function
	 */
	private FeatureModelVariable mergeFMVsWithConstraints(
			Collection<FeatureModelVariable> lfmvs, Mode mode) {

		assert (_hierarchyMergerStrategy == HierarchyMergerStrategy.BASIC
				|| 
			   _hierarchyMergerStrategy == HierarchyMergerStrategy.FLAT
				);
		FeatureModelVariable fm = mergeWithConstraints(lfmvs, mode, null);
		return fm ; 

	}
	
	/**
	 * TODO: mode
	 * 
	 * An alternative merge Instead of the classical merging (formula +
	 * synthesis) We relate the input FMs to be merged with constraints and with
	 * a new FM, FMexp. => it gives a new FM, FMagg, that we slice considering
	 * features of FMexp FMi1 x FMi2 x ... x FMin x FMexp x Constraints = FMagg
	 * slice/project FMexp onto FMexp.*
	 * 
	 * 
	 * 
	 * @param lfms
	 *            FMi1, FMi2, ... , FMin
	 * @param mode
	 * @return merged FM
	 */
	 private FeatureModelVariable mergeWithConstraints(
			Collection<FeatureModelVariable> lfms, Mode mode, Formula<String> fla) {
		
		
		

		HierarchyMerger hMerger = HierarchyMergerFactory.mkMerger(_hierarchyMergerStrategy, mode, fla);
		FeatureModelVariable fmExp = new FeatureModelVariable("", hMerger.build(lfms));

		// first we collect all feature names
		Set<String> allFts = new HashSet<String>() ;  
		for (FeatureModelVariable fm : lfms) {
			allFts.addAll(fm.features().names());			
		}
		
		// we add "free variable" and negate them
		for (FeatureModelVariable fm : lfms) {
			Set<String> fts = fm.features().names();
			Set<String> ftsToNegate = Sets.difference(allFts, fts);
			for (String ftToNegate : ftsToNegate) {
				fm.addFreeVariableToRoot(ftToNegate);
				fm.addConstraint(new Expression<String>(ftToNegate).not());
			}
		}
		
		/**
		 * 
		 * Now we prime features in FMi1, FMi2, ... , FMin so that all features
		 * have an unique name
		 * 
		 */
		
		List<FeatureModelVariable> primeLfms = new ArrayList<FeatureModelVariable>();
		Set<String> rootFts = new HashSet<String>();
		Iterator<FeatureModelVariable> it = lfms.iterator() ; 
		for (int i = 0; i < lfms.size(); i++) {
			FeatureModelVariable fmi = it.next(); 
			FeatureModelVariable pfm = primeFTs(fmi, i);

			primeLfms.add(pfm);
			rootFts.add(pfm.root().name());
		}
		
				

		/* FMi1 x FMi2 x ... x FMin = FMI */
		FeatureModelVariable fmInput = new AggregatorFM().build(primeLfms,
				new HashSet<Expression<String>>(), "InputFMs");

		if (mode == Mode.StrictUnion) { // in intersection mandatory is OK
			boolean edit = ModifyVOperatorParser.setAlternative(rootFts, fmInput.getFm());
			assert (edit);
		}

		// we construct constraints
		Set<Expression<String>> constraints = new HashSet<Expression<String>>();
		Set<String> fts = fmExp.features().names();
		for (String ft : fts) {

			// for each feature 'ft' of fmExp, we construct a constraint
			// relating 'ft'
			// with corresponding features of FMi1 x FMi2 x ... x FMin

			// (1) we first collect corresponding features' name
			Set<String> correspondingFts = new HashSet<String>();
			for (int i = 0; i < primeLfms.size(); i++) {
				String fti = primeFt(ft, i, primeLfms.get(i));
				correspondingFts.add(fti);
			}

			// (2) we construct the constraint

			// mutex relations
			//List<Expression<String>> lMutex = mkMutex(correspondingFts);
			//Expression<String> cstMutex = lMutex.size() == 0 ? null
				//	: mkConjunction(lMutex);

			// parent-child relations btw 'ft' and corresponding fts
			Expression<String> ftTarget = new Expression<String>(ft);
			Set<Expression<String>> ftExprs = new HashSet<Expression<String>>();
			for (String fnode : correspondingFts)
				ftExprs.add(new Expression<String>(fnode));			
			
			if (mode == Mode.StrictUnion) {
				Expression<String> cstPChild = ftExprs.size() == 0 ? new Expression<String>(
						ExpressionType.IFF, ftTarget, ftTarget)
						: new Expression<String>(ExpressionType.IFF, ftTarget,
								ExpressionUtility.mkDisjunction(ftExprs));
					constraints.add(cstPChild);
			}
			else if (mode == Mode.Intersection) {
				if (ftExprs.size() < lfms.size()) { // FIXME: very strange (AM)
					// no corresponding features
					constraints.add(ftTarget.not());
					for (Expression<String> ftExpr : ftExprs) {
						constraints.add(ftExpr.not());
					}
				}
				else {
					for (Expression<String> ftExpr : ftExprs) {
						constraints.add(
								new Expression<String>(ExpressionType.IFF, ftTarget,
										ftExpr));		
					}
				}
			}

			/*		
			Expression<String> cst = cstMutex == null ? cstPChild
					: new Expression<String>(ExpressionType.AND, cstPChild,
							cstMutex);*/

			
		}
		
		
		/* FMI x FMexp x constraints */
		List<FeatureModelVariable> qfms = new ArrayList<FeatureModelVariable>();
		qfms.add(fmInput);
		qfms.add(fmExp);

		FeatureModelVariable fm = new AggregatorFM().build(qfms, constraints,
				"MergeCST");
		return fm;
	}

	/**
	 * TODO: independent of getFm()
	 * @param featureModel
	 * @param i
	 * @return
	 */
	private FeatureModelVariable primeFTs(
			FeatureModelVariable featureModel, int i) {

		FeatureModelVariable rFM = (FeatureModelVariable) featureModel.copy();

		Set<String> ftsName = rFM.features().names() ; 
	
		
		for (String ftName : ftsName) {
			String newFeatureName = primeFt(ftName, i, featureModel);
			rFM.renameFeature(ftName, newFeatureName) ; 
		}

		return rFM;
	}

	private String primeFt(String ftName, int i, FeatureModelVariable fm) {
		String fmIdentifier = fm.getIdentifier() ; 
		if (!fmIdentifier.isEmpty())
			return fmIdentifier + "_" + ftName ;
		else 
			return ftName + "" + Math.abs((i * 1000000) + 1000) ; 

	}

	public void setHierarchyStrategy(HierarchyMergerStrategy hierarchyStrategy) {
		_hierarchyMergerStrategy = hierarchyStrategy ; 		
	}
	
	

}
