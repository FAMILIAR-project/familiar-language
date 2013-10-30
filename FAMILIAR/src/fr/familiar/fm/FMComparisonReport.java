package fr.familiar.fm;

import java.util.Set;

import com.google.common.collect.Sets;

import fr.familiar.experimental.FGroup;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

public class FMComparisonReport {
	
	private FeatureModelVariable _fm1 ; 
	private FeatureModelVariable _fm2 ; 
	
	private int _i = 0 ; 

	public FMComparisonReport(FeatureModelVariable fm1, FeatureModelVariable fm2) {
		this._fm1 = fm1 ; 
		this._fm2 = fm2 ; 
	}

		
	public boolean perform() {
		Set<FGroup> xor1 = _fm1.getXorGroups() ;
		Set<FGroup> or1 = _fm1.getOrGroups() ;
		Set<FGroup> mtx1 = _fm1.getMutexGroups() ; 
		Set<String> opts1 = _fm1.getOptionals() ; 
		Set<String> mand1 = _fm1.getMandatory() ; 
		Set<Expression<String>> impl1 = _fm1.getImplicationConstraints() ;
		Set<Expression<String>> biimpl1 = _fm1.getBiImplicationConstraints() ; 
		Set<Expression<String>> excl1 = _fm1.getExcludeConstraints() ;
		
		Set<FGroup> xor2 = _fm2.getXorGroups() ;
		Set<FGroup> or2 = _fm2.getOrGroups() ;
		Set<FGroup> mtx2 = _fm2.getMutexGroups() ; 
		Set<String> opts2 = _fm2.getOptionals() ; 
		Set<String> mand2 = _fm2.getMandatory() ; 
		Set<Expression<String>> impl2 = _fm2.getImplicationConstraints() ;
		Set<Expression<String>> biimpl2 = _fm2.getBiImplicationConstraints() ; 
		Set<Expression<String>> excl2 = _fm2.getExcludeConstraints() ;
	 
		boolean b = true ; 
		b = reportDiffGroups(xor1, xor2, "XOR") && b;
		b = reportDiffGroups(or1, or2, "OR") && b;
		b = reportDiffGroups(mtx1, mtx2, "MTX") && b;
		
		if (!opts1.equals(opts2)) {
			System.err.println("difference" + _i++ + "==>" + "opts" + "1=" + Sets.difference(opts1, opts2) + 
					" and " + "opts" + "2=" + Sets.difference(opts2, opts1)); 
			b = false ; 
		}
		
		if (!mand1.equals(mand2)) {
			System.err.println("difference" + _i++ + "==>" + "mands" + "1=" + Sets.difference(mand1, mand2) + 
					" and " + "mands" + "2=" + Sets.difference(mand2, mand1)); 
			b = false ; 
		}
		
		
		b = reportDiffCst(impl1, impl2, "implications") && b;
		b = reportDiffCst(biimpl1, biimpl2, "biimplications") && b ;
		b = reportDiffCst(excl1, excl2, "exclusions") && b;
		
		return b ; 
	}


	public boolean reportDiffCst(Set<Expression<String>> e1, Set<Expression<String>> e2, String kindOfCst) {
		if (!e1.equals(e2)) {
			System.err.println("difference" + _i++ + "==>" + kindOfCst + "1=" + Sets.difference(e1, e2) + " VS " + 
					kindOfCst + "2=" + Sets.difference(e2, e1)); 
			return false ;
		}
		return true ;
		
	}


	public boolean reportDiffGroups(Set<FGroup> gs1, Set<FGroup> gs2, String gKind) {
		boolean bR = true ; 
		
		for (FGroup g1 : gs1) {
			boolean eqG = false ; 
			for (FGroup g2 : gs2) {
				if (g1.equals(g2)) {
					eqG = true ; 
				}
			}
			if (!eqG) {
				System.err.println("difference" + _i++ + "==>" + gKind + "1=" + g1);
				bR = false ; 
			}
		}
		
		for (FGroup g2 : gs2) {
			boolean eqG = false ; 
			for (FGroup g1 : gs1) {
				if (g1.equals(g2)) {
					eqG = true ; 
				}
			}
			if (!eqG) {
				System.err.println("difference" + _i++ + "==>" + gKind + "2=" + g2);
				bR = false ; 
			}
		}
				
		return bR ; 
		
	}


}
