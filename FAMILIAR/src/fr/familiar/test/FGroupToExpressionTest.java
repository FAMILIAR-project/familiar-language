package fr.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fr.familiar.experimental.FGroup;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableBDDFormula;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.Formula;

public class FGroupToExpressionTest extends FMLTest {

	
	@Test
	public void testMTX1() throws Exception {
		FeatureModelVariable fmv1 = (FeatureModelVariable) _shell.parse("fm1 = FM (A : (B|C|D)? ; )") ;
		Set<FGroup> mtx1 = fmv1.getMutexGroups() ;
		assertGroupsConvertion(mtx1, fmv1);
		
	}
	
	@Test
	public void testMTX2() throws Exception {
		FeatureModelVariable fmv1 = (FeatureModelVariable) _shell.parse("fm1 = FM (A : (B|C)? ; )") ;
		Set<FGroup> mtx1 = fmv1.getMutexGroups() ;
		assertGroupsConvertion(mtx1, fmv1);
		
	}
	
	@Test
	public void testMTX3() throws Exception {
		FeatureModelVariable fmv1 = (FeatureModelVariable) _shell.parse("fm1 = FM (A : (B|C|F|E|G|I)? ; )") ;
		Set<FGroup> mtx1 = fmv1.getMutexGroups() ;
		assertGroupsConvertion(mtx1, fmv1);
		
	}
	
	@Test
	public void testXOR1() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C|D) ; )") ;
		Set<FGroup> xor1 = fmv1.getXorGroups() ;
		assertGroupsConvertion(xor1, fmv1);
		
	}
	
	@Test
	public void testXOR2() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C) ; )") ;
		Set<FGroup> xor1 = fmv1.getXorGroups() ;
		assertGroupsConvertion(xor1, fmv1);
		
	}
	
	@Test
	public void testXOR3() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C|E|G) ; )") ;
		Set<FGroup> xor1 = fmv1.getXorGroups() ;
		assertGroupsConvertion(xor1, fmv1);
		
	}
	
	@Test
	public void testOR1() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C|D)+ ; )") ;
		Set<FGroup> or1 = fmv1.getOrGroups() ;
		assertGroupsConvertion(or1, fmv1);
		
	}
	
	@Test
	public void testOR2() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C)+ ; )") ;
		Set<FGroup> or1 = fmv1.getOrGroups() ;
		assertGroupsConvertion(or1, fmv1);
		
	}
	
	@Test
	public void testOR3() throws Exception {
		
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C|E|G)+ ; )") ;
		Set<FGroup> or1 = fmv1.getOrGroups() ;
		assertGroupsConvertion(or1, fmv1);
		
	}
	
	private void assertGroupsConvertion(Set<FGroup> mtx1,
			FeatureModelVariable fmv1) {
		FGroup fGroup = mtx1.iterator().next(); 
		Expression<String> gE = fGroup.toExpression() ; 
		FeatureModelVariableBDDFormula fmG = new FeatureModelVariableBDDFormula("", 
				new Formula<String>(_builder.mkExpression(new Expression<String>(ExpressionType.AND, 
						new Expression<String>(fmv1.root().name()), gE)), 
						ExpressionUtil.getAllFeatures(gE), _builder),					
						_builder) ;
		assertEquals(Comparison.REFACTORING, fmG.compare(fmv1));
		
	}

	

}
