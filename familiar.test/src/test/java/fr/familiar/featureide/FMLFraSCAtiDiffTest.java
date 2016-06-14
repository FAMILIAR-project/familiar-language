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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Ignore;
import org.junit.Test;
import org.prop4j.Node;
import org.xtext.example.mydsl.fml.FMFormat;
import org.xtext.example.mydsl.fml.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.FMLCAiSE2012DiffTest;
import fr.familiar.FMLTest;
import fr.familiar.experimental.featureide.Node4JUtil;
import fr.familiar.fm.FMLBDDReader;
import fr.familiar.fm.converter.S2T2Converter;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.FeatureIDEUtils;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.operations.featureide.SlicerSATFormula;
import fr.familiar.parser.FMBuilder;
import fr.familiar.parser.MyExpressionParser;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelLazyVariable;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

@Ignore
public class FMLFraSCAtiDiffTest extends FMLTest {
	
	
	
	private FeatureModelVariable getFMMerle() throws Exception {
		String fmMerleID = "fmMerle";
		FeatureModelVariable fmMerle = FM(fmMerleID, AdamArchTest._fmMerleSpecification); // _fmMerleSpecification
		_shell.parse("nFTs = size " + fmMerle.getIdentifier() + ".*") ;
		assertEquals(39, getIntegerVariable("nFTs").getV()); // manual control
		return fmMerle;
	}
	
	
	@Test
	public void testFraSCAti14VS13() throws Exception {
		FeatureModelVariable fmArchi13 = getFraSCAtiEnforced13();
		myStats(fmArchi13);

		FeatureModelVariable fmArchi14 = getFraSCAtiEnforced14();
		myStats(fmArchi14);
	
		
		System.err.println("diff43 (features) = " + Sets.difference(fmArchi14.features().names(), fmArchi13.features().names()));
		System.err.println("diff34 (features) = " + Sets.difference(fmArchi13.features().names(), fmArchi14.features().names())); // 0 normal
		
		
		fmArchi13.setBuilder(_builder); 
		fmArchi13 = fmArchi13.slice(SliceMode.EXCLUDING, Sets.difference(fmArchi13.features().names(), fmArchi14.features().names()));
		myStats(fmArchi13);
		fmArchi14.setBuilder(_builder); 
		fmArchi14 = fmArchi14.slice(SliceMode.EXCLUDING, Sets.difference(fmArchi14.features().names(), fmArchi13.features().names()));
		myStats(fmArchi14);
		
		System.err.println("REL: " + fmArchi14.compareBDD(fmArchi13, _builder));
		//FeatureModelVariable diff15VS14 = fmArchi15.mergeDiff(fmArchi14) ; 
		//System.err.println("diff:" + diff15VS14);
		//System.err.println("#diff:" + diff15VS14.counting());
		
		FMLCAiSE2012DiffTest.analyzeAll(fmArchi13, fmArchi14, _builder); // SPECIALIZATION
		
	}
	
	
	private FeatureModelVariable getFraSCAtiEnforced13() {
		return getFraSCAtiEnforced(13) ;
	}
	
	/**
	 * serialization facilities for CAiSE (companion web page)
	 * @throws Exception
	 * 
	 */
	@Test
	public void testSerializeAll() throws Exception {
		
		String output = "inputFML/FraSCAti/CAiSEOutput/" ; 
		
		FeatureModelVariable arch13 = getFraSCAtiArch13() ;
		FileSerializer.write(output + "Arch13.fml", arch13.convert(FMFormat.FFML));
		FileSerializer.write(output + "Arch13.m", arch13.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Arch13.fmprimitives", arch13.convert(FMFormat.S2T2));
		
		FeatureModelVariable arch14 = getFraSCAtiArch14() ; 
		FileSerializer.write(output + "Arch14.fml", arch14.convert(FMFormat.FFML));
		FileSerializer.write(output + "Arch14.m", arch14.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Arch14.fmprimitives", arch14.convert(FMFormat.S2T2));
				
		FeatureModelVariable arch15 = getFraSCAtiArch15() ; 
		FileSerializer.write(output + "Arch15.fml", arch15.convert(FMFormat.FFML));
		FileSerializer.write(output + "Arch15.m", arch15.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Arch15.fmprimitives", arch15.convert(FMFormat.S2T2));
		
		
		FeatureModelVariable full13 = getFraSCAtiFull13() ; 
		FileSerializer.write(output + "Full13.fml", full13.convert(FMFormat.FFML));
		FileSerializer.write(output + "Full13.m", full13.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Full13.fmprimitives", full13.convert(FMFormat.S2T2));
		
		FeatureModelVariable full14 = getFraSCAtiFull14() ; 
		FileSerializer.write(output + "Full14.fml", full14.convert(FMFormat.FFML));
		FileSerializer.write(output + "Full14.m", full14.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Full14.fmprimitives", full14.convert(FMFormat.S2T2));
		
		
		FeatureModelVariable full15 = getFraSCAtiFull15() ; 
		FileSerializer.write(output + "Full15.fml", full15.convert(FMFormat.FFML));
		FileSerializer.write(output + "Full15.m", full15.convert(FMFormat.FIDE));
		FileSerializer.write(output + "Full15.fmprimitives", full15.convert(FMFormat.S2T2));
		
		
		/*
		getFraSCAtiEnforced13() ;
		getFraSCAtiEnforced14() ; 
		getFraSCAtiEnforced15() ;
		*/
		
	}

	
	@Test
	public void testWhichVersions() throws Exception {
		FeatureModelVariable fmFullECSA = AdamArchTest.getFMFull() ;
		FeatureModelVariable fmvFull13 = getFraSCAtiFull13();
		
		Set<String> diff12 = Sets.difference(fmFullECSA.features().names(), fmvFull13.features().names()); 
		Set<String> diff21 = Sets.difference(fmFullECSA.features().names(), fmvFull13.features().names()); 
		
		System.err.println("diff12=" + diff12);
		System.err.println("diff21=" + diff21);
		AdamArchTest.compareVariabilityOperators(fmvFull13, fmFullECSA);
		
		
		
	}

	@Test
	public void testSerialize14() throws Exception {
		
		FeatureModelVariable fmArchi = getFraSCAtiArch14();
		FileSerializer.write("inputFML/FraSCAti/fmArch14_150.fml", fmArchi.convert(FMFormat.FFML));
		
		FeatureModelVariable fmvPlugin = getFraSCAtiPlugin14() ; 
		FileSerializer.write("inputFML/FraSCAti/fmPlugin14.fml", fmvPlugin.convert(FMFormat.FFML));
		
		FeatureModelVariable fmvFull = getFraSCAtiFull14();
		FileSerializer.write("inputFML/FraSCAti/fmFull14.fml", fmvFull.convert(FMFormat.FFML));
		
		System.err.println("#fm150=" + fmArchi.counting());
		
		sliceWithSAT(fmArchi, fmvFull);

		System.err.println("\n\t=========== begin slicing ========\n");
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmvFull, fmArchi.features().names(), SliceMode.INCLUDING);
		System.err.println("\n\t===========  end slicing ========\n");
		
		FileSerializer.write(getLocationFMEnforced(14), fmvSliced.convert(FMFormat.FMLBDD));
		
		System.err.println("#fmvSliced=" + fmvSliced.counting());
	}
	
	@Test
	public void testSerialize13() throws Exception {
		
		FeatureModelVariable fmArchi = getFraSCAtiArch13();
		FeatureModelVariable fmvFull = getFraSCAtiFull13();
		
		
		

		System.err.println("\n\t=========== begin slicing ========\n");
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmvFull, fmArchi.features().names(), SliceMode.INCLUDING);
		System.err.println("\n\t===========  end slicing ========\n");
		
		FileSerializer.write(getLocationFMEnforced(13), fmvSliced.convert(FMFormat.FMLBDD));
		
		System.err.println("#fmvSliced=" + fmvSliced.counting());
		
		
	}
	
	private Formula<String> sliceWithSAT(FeatureModelVariable fmArchi,	FeatureModelVariable fmvFull) {
		Node nodeFmv = new SATBuilder().mkNode(fmvFull);
		System.err.println("# original node = ");
		System.err.println(Node4JUtil.countSizeOfNode(nodeFmv));
		System.err
				.println("\n\n==== SAT formula is ready -- now slicing! =====");
		Set<String> slicingCriterion = fmArchi.features().names() ; 
		SATFormula satFla = (SATFormula) new SlicerSATFormula().runSliceFormulaSAT(fmvFull,
				nodeFmv, fmvFull.features().names(), slicingCriterion,
				SliceMode.INCLUDING);

		System.err
		.println("\n\n==== end SAT slicing! =====");
		//System.err.println("# (conf) = " + satFla.counting());
		
		Expression<String> expr = MyExpressionParser.parseString(satFla.getNode().toString(FeatureIDEUtils._textualSymbols));
		expr = ExpressionUtility.replaceOccurenceInExpression("True", "1", expr);
		expr = ExpressionUtility.replaceOccurenceInExpression("False", "0", expr);
		Set<String> domainExpr = ExpressionUtil.getAllFeatures(expr);
		System.err.println("domainExpr=" + domainExpr) ; 
		BDD bddNode = _builder.mkExpression(expr);
		
		Formula<String> flaNode = new Formula<String>(bddNode, domainExpr, _builder) ;
		return flaNode ; 
		
	}


	private FeatureModelVariable getFraSCAtiFull13() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/FullFeatureModel13.fmprimitives"));
	}


	private FeatureModelVariable getFraSCAtiArch13() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/ArchitectureFeatureModel13.fmprimitives"));
		
	}


	/**
	 * dont know why it does not scale :( 
	 * @throws Exception
	 */
	@Test
	public void testSerialize15() throws Exception {
		
		
				
		
		FeatureModelVariable fmvFull = getFraSCAtiFull15();
		FileSerializer.write("inputFML/FraSCAti/fmFull15.fml", fmvFull.convert(FMFormat.FFML));
		
		FeatureModelVariable fmArchi = getFraSCAtiArch15();
		FileSerializer.write("inputFML/FraSCAti/fmArch15_150.fml", fmArchi.convert(FMFormat.FFML));
		
		System.err.println("#fm150=" + fmArchi.counting());
		
		FeatureModelVariable fmvPlugin = getFraSCAtiPlugin15() ; 
		FileSerializer.write("inputFML/FraSCAti/fmPlugin15.fml", fmvPlugin.convert(FMFormat.FFML));
		
		// it does scale Baby!!
		Formula<String> flaNode = sliceWithSAT(fmArchi, fmvFull); 		
		System.err.println("#flaNode=" + new FormulaAnalyzer<String>(flaNode, _builder).counting());
		FeatureModelVariable fmvSATSliced = new FMSlicerBDD(_builder).sliceFM(fmvFull, fmArchi.features().names(), SliceMode.INCLUDING); // FIXME: reuse flaNode directly
		System.err.println("fmvSATSliced=" + fmvSATSliced.getSyntacticalRepresentation());
		System.err.println("#fmvSATSliced=" + fmvSATSliced.counting());
		FileSerializer.write(getLocationFMEnforced(15), fmvSATSliced.convert(FMFormat.FMLBDD));
		
		System.err.println("\n\t=========== begin slicing ========\n");
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmvFull, fmArchi.features().names(), SliceMode.INCLUDING);
		System.err.println("\n\t===========  end slicing ========\n");
		
		
		
		FileSerializer.write(getLocationFMEnforced(15), fmvSliced.convert(FMFormat.FMLBDD));
		
		
	}
	
	private String getLocationFMEnforced(int v) {
		return "inputFML/FraSCAti/Full" + v + "Extracted.fmlbdd" ; 
	}


	@Test
	public void testFraSCAti14VS15() throws Exception {
		FeatureModelVariable fmArchi14 = getFraSCAtiEnforced14();
		myStats(fmArchi14);

		FeatureModelVariable fmArchi15 = getFraSCAtiEnforced15();
		myStats(fmArchi15);
	
		
		System.err.println("diff54 (features) = " + Sets.difference(fmArchi15.features().names(), fmArchi14.features().names()));
		System.err.println("diff45 (features) = " + Sets.difference(fmArchi14.features().names(), fmArchi15.features().names())); // 0 normal
		
		
		Set<String> fts54 = Sets.difference(fmArchi15.features().names(), fmArchi14.features().names());
		//fmArchi14 = fmArchi14.slice(SliceMode.EXCLUDING, _builder, fts54);
		fmArchi15.setBuilder(_builder); 
		fmArchi15 = fmArchi15.slice(SliceMode.EXCLUDING, fts54);
		myStats(fmArchi15);
		
		System.err.println("rel:" + fmArchi14.compareBDD(fmArchi15, _builder));
		//FeatureModelVariable diff15VS14 = fmArchi15.mergeDiff(fmArchi14) ; 
		//System.err.println("diff:" + diff15VS14);
		//System.err.println("#diff:" + diff15VS14.counting());
		
		FMLCAiSE2012DiffTest.analyzeAll(fmArchi15, fmArchi14, _builder);
		
		
		
	}
	
	private FeatureModelVariable getFraSCAtiEnforced(int v) {
		FMLBDDReader reader = new FMLBDDReader(getLocationFMEnforced(v));
		// TODO
		// reader.getFMV(assigner);
		Map<String, Integer> map = reader.getMapBuilder();
		BDD bdd = reader.getBDD();
		Formula<String> fla = new Formula<String>(bdd, map.keySet(), _builder);
		
		
		FeatureModel<String> ifm = FMBuilder.getInternalFM("FM (" + reader.getFMRepresentation() + ")");
		assertNotNull (ifm);
		Formula<String> diffFla = FMLMergerBDD.diff(fla, _builder.mkFeatureModel(ifm), _builder);
		return new FeatureModelLazyVariable("", ifm, diffFla);
	}


	private FeatureModelVariable getFraSCAtiEnforced14() {
		return getFraSCAtiEnforced(14) ;
	}
	
	private FeatureModelVariable getFraSCAtiEnforced15() {
		return getFraSCAtiEnforced(15) ;
	}


	private FeatureModelVariable getFraSCAtiFull15() {
		FeatureModelVariable fmv = new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/FullFeatureModel15.fmprimitives"));
		return getFullOnlyNFirstCst(fmv, 158);
	}


	private FeatureModelVariable getFraSCAtiFull14() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/FullFeatureModel14.fmprimitives"));
	}


	private FeatureModelVariable getFraSCAtiPlugin15() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/PluginFeatureModel15.fmprimitives"));
	}


	private FeatureModelVariable getFraSCAtiPlugin14() {
		 return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/PluginFeatureModel14.fmprimitives"));
	}
	
	/*
	private FeatureModelVariable getFraSCAtiPlugin13() {
		 return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/PluginFeatureModel13.fmprimitives"));
	}*/


	private FeatureModelVariable getFraSCAtiArch15() {
		 return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/ArchitectureFeatureModel15.fmprimitives"));
		
			
	}
	
	private FeatureModelVariable getFullOnlyNFirstCst(FeatureModelVariable fmFull, int nbCst) {
		 
		
		gsd.synthesis.FeatureModel<String> fm = fmFull.getFm() ; 
		
		Set<Expression<String>> csts = fm.getConstraints();
		int idExpr = 0 ;
		Set<Expression<String>> cstsToKeep = new HashSet<Expression<String>>();
		for (Expression<String> cst : csts) {
			if (idExpr++ < nbCst)
				cstsToKeep.add(cst);
		}
		
		fm.removeAllConstraints() ;
		
		for (Expression<String> cst : cstsToKeep) {
			if (!fm.addConstraint(cst))
				assertFalse("Unable to add constraint: " + cst, true);
		}
		fm.addFreeVariables();	
		
		return fmFull;
	}


	private FeatureModelVariable getFraSCAtiArch14() {
		return new S2T2Converter(true).S2T2ToFMLFM(new File("inputFML/FraSCAti/ArchitectureFeatureModel14.fmprimitives"));
		//new File("inputFML/FraSCAti/ArchitectureFeatureModel-1.4.fmprimitives"));
		
	}


	private void myStats(FeatureModelVariable fm) {
		System.err.println("#fm=" + fm.counting (CountingStrategy.BDD_FML));
		System.err.println("#fm (features)=" + fm.features().size());
		
	}


	@Test	
	public void testDiffStats() throws Exception {
		FeatureModelVariable fmArchExtracted = loadFM150SerializedEnforcedLazy() ; 
		System.err.println("#fmArchExtracted=" + fmArchExtracted.counting (CountingStrategy.BDD_FML));
		System.err.println("#fmArchExtracted (features)=" + fmArchExtracted.features().size());
		
		FeatureModelVariable fmMerle = getFMMerle() ; 
		System.err.println("#fmMerle=" + fmMerle.counting (CountingStrategy.BDD_FML));
		System.err.println("#fmMerle (features)=" + fmMerle.features().size());
		
	}
	
	
	@Test	
	public void testDiff1() throws Exception {
		FeatureModelVariable fmArchExtracted = loadFM150SerializedEnforcedLazy() ; 
		FeatureModelVariable fmMerle = //getFMMerle() ; 
			new S2T2Converter(true).S2T2ToFMLFM(new File("/Users/mathieuacher/Documents/workspaceScala/ArchFM/feature-models/ArchitectureFeatureModel.fmprimitives"));
		
		
		
		/*
		 * 
		 *  (1) matching / alignment
		 * 
		 */
		
		//AdamArchTest.alignMerleAndArchi(fmMerle, fmArchExtracted, _shell);
		
		/*
		 * 
		 *  (2) diffing
		 * 
		 */
		
		FMLCAiSE2012DiffTest.analyzeAll(fmMerle, fmArchExtracted, _builder);
		
		
		System.err.println("\n\n==========\n\n");
		
		FMLCAiSE2012DiffTest.analyzeAll(fmArchExtracted, fmMerle, _builder);
	}
	
	
	
	private FeatureModelVariable loadFM150SerializedEnforcedLazy() {

		FMLBDDReader reader = new FMLBDDReader("inputFML/FraSCAti/" + "fmArchiEnforced" + AdamArchTest.N_CONSTRAINT_MAX + ".fmlbdd"); 
				
		Map<String, Integer> map = reader.getMapBuilder();
		BDD bdd = reader.getBDD();
		Formula<String> fla = new Formula<String>(bdd, map.keySet(), _builder);
		
		
		FeatureModel<String> ifm = FMBuilder.getInternalFM("FM (" + reader.getFMRepresentation() + ")");
		assertNotNull (ifm);
		Formula<String> diffFla = FMLMergerBDD.diff(fla, _builder.mkFeatureModel(ifm), _builder);
		return new FeatureModelLazyVariable("", ifm, diffFla);
	}
	

}
