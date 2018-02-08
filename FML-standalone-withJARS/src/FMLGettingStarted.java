import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import fr.familiar.FMLTest;
import fr.familiar.FeatureModelLoader;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import splar.core.fm.FeatureModelException;
import splar.core.fm.XMLFeatureModel;

/**
 * 
 * Moving to another repo 
 * Created by macher1 on 16/11/2017.
 */
public class FMLGettingStarted extends FMLTest {


    private static final String TARGET_FOLDER = "ouputCSV";

    
    private static final String absoluteFolder = "/Users/macher1/Documents/SANDBOX/FML-from-scratch/FOReverSE-KSynthesis/Evaluation/";
	private static final String SPLOT_FOLDER = absoluteFolder + "input/splot-models-2014-01-30";
    
	@Test
	public void testREAL11() throws Exception {
		FeatureModelVariable fmvReal11 = mkFMFromSPLOTFile(new File(SPLOT_FOLDER + "/" + "REAL-FM-11.xml"), "fm1");
		assertNotNull(fmvReal11);
		
		System.err.println("" + fmvReal11.counting());
		System.err.println("" + fmvReal11.cores());
		System.err.println("" + fmvReal11.falseOptionalFeatures());
		
		
	}
	
	public FeatureModelVariable mkFMFromSPLOTFile(File splotFile, String varID) {
		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				splotFile.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			FMLShell.getInstance().printError(
					"Unable to load SPLOT feature model "
							+ e.getMessage());
			return null;
		}

		// strfm = new SPLOTtoFML().convert(featureModelSPLOT); // @Deprecated
	
		
		gsd.synthesis.FeatureModel<String> rFM = new SPLOTtoFML().convertToFeatureModel(featureModelSPLOT);
		FeatureModelVariable fmv = new FeatureModelVariable(varID, rFM) ; 
		return fmv;
		
	}
	
	@Test
    public void testHelloWorld() throws Exception {
        FeatureModelVariable fmv = FM ("fm1", "FM (A : [B] [C] ;)");
        
        assertEquals(4.0, fmv.counting(), 0.0);

        FeatureModelVariable fmv2 = new FeatureModelVariable ("fm2", FMBuilder.getInternalFM("A : [B] [C] ;"));
        assertEquals(Comparison.REFACTORING, fmv2.compare(fmv));
        
    }
    
    @Test
    public void testSPLOT() throws Exception {
    	
    	
    	// dataset: SPLOT archive 
    	FeatureModelLoader loader = new FeatureModelLoader(_shell, _builder);
    	List<FeatureModelVariable> fmvs = loader.getAllSPLOTFeatureModels();
    	assertEquals(201, fmvs.size());
    	
    	
    	final int MAX_CONFIGS = 200000;  	 
    	 
         // now we remove constraints (strategy here: ALL)
        // we only want feature models with not large configuration spaces (ie for which we can practically enumerate all configs) 
    	// ("ground-truth" setting)
    	 List<PairFMV> pairsFM = new ArrayList<>();
    	 for (FeatureModelVariable fmv : fmvs) {
    		 
    		 
    		//if (fmv.getAllConstraints().size() == 0)
    			//continue; 
    		fmv.setBuilder(new BDDBuilder<String>(_builder.getFeatureGraphFactory()));
    		 double c = fmv.counting();
    		 
    		 FeatureModelVariable fmvPrime = (FeatureModelVariable) fmv.copy();
    		 assertNotNull(fmvPrime);    		 
    		 fmvPrime.removeAllConstraints();
    		 
    		 fmvPrime.setBuilder(new BDDBuilder<String>(_builder.getFeatureGraphFactory()));
    		 double cPrime = fmvPrime.counting();
    		 
    		// idea: if the original configuration is the "same", then it means there are actually no constraints in the original feature models
       		 if (c == cPrime) {     
       			 continue ;
       			//assertEquals(0, fmv.getAllConstraints().size());
       			// not necessarily the case if cross-tree constraints are pointless 
       			// (and actually do not "further" constraint the feature model) 
       			// we could use fmv.computeRendundantConstraints() but it only operates over implies/excludes 
       			// simple stuff to fix if we want
       		 }
    		 
			if (cPrime <= MAX_CONFIGS) {				 	
				 pairsFM.add(new PairFMV(fmv, fmvPrime));
			}    		 
    		     		 
    	 }
    	 System.out.println("Number of feature models for which configuration size is < " + MAX_CONFIGS + " => "  + pairsFM.size());
 
    	 
    	 /*
    	  * We got a set of pairs <fmv, fmvPrime>
    	  * 
    	  */    	 
    	 
    	// stats(pairsFM); // some basic stats
    	 
    	 /*
    	  *  for each feature model, we create a CSV file with all configs with labels 
    	  */
    	 
    	 for (PairFMV pFmv : pairsFM) {
    		 
    		 
    		 FeatureModelVariable fmvPrime = pFmv.getGeneralizedFM();
    		 FeatureModelVariable fmv = pFmv.getOriginalFM();
    		 System.err.println("Processing... " + fmv.getIdentifier());
    		 System.err.println("#############");  
    		 System.err.println("" + fmv);
    		 System.err.println("------"); 
    		 System.err.println("" + fmvPrime);
    		 System.err.println("#############\n\n\n");  
    	
    		Set<Variable> allConfigs = fmvPrime.configs();	 
    		Collection<Set<String>> scfs = new HashSet<Set<String>>();
	        for (Variable cf : allConfigs) {
	            Set<String> confFts = ((SetVariable) cf).names();
	            scfs.add(confFts);	        

	        }
	        
	    	
   		 	Set<String> fts = fmv.features().names();
   		 	String fullHeader = "configurationID" + "," + fts.stream().sorted().collect(Collectors.joining(",")) + ",isValid" + "";
   		 	StringBuilder csvLines = new StringBuilder(); 
	        int idConf = 0;
	         for (Set<String> cf : scfs) {
	             idConf++;
	             FeatureConfiguration ftConf = new FeatureConfiguration(cf, fmv);	            
	             Map<String, Boolean> conf = ftConf.getConfMap();
	             // toCSV line
	             String csvLine = idConf + ","; 
	             csvLine += conf.keySet().stream().sorted().map(ft -> conf.get(ft) ? "TRUE" : "FALSE").collect(Collectors.joining(","));
	             boolean b = isValid (ftConf, fmv);
	             csvLine += "," + (b ? "TRUE" : "FALSE") + "\n";
	   			 // CSV line: configID + config values + label value "b"
	             csvLines.append(csvLine);
	         }
	         
	         String csvContent = fullHeader + "\n" + csvLines;
	         
	         new File(TARGET_FOLDER).mkdir();
	        
	         FileWriter fw = new FileWriter(new File(TARGET_FOLDER + "/" + fmv.getIdentifier().trim() + ".csv"));
	         fw.write(csvContent);
	         fw.close();
    		 
    	 } 
    	
    	
    }
    
    
   
    
    private void stats(List<PairFMV> pairsFM) {
    	for (PairFMV pFmv : pairsFM) {
   		 
   		 FeatureModelVariable fmvPrime = pFmv.getGeneralizedFM();
   		 FeatureModelVariable fmv = pFmv.getOriginalFM();
   		 
   		 fmvPrime.setBuilder(new BDDBuilder<String>(_builder.getFeatureGraphFactory()));
   		 double cPrime = fmvPrime.counting();
   		 
   		 fmv.setBuilder(new BDDBuilder<String>(_builder.getFeatureGraphFactory()));
   		 double c = fmv.counting();
   		 
   		// idea: if the original configuration is the "same", then it means there are actually no constraints in the original feature models
   		 if (c == cPrime) {     			 
   			//assertEquals(0, fmv.getAllConstraints().size());
   			// not necessarily the case if cross-tree constraints are pointless 
   			// (and actually do not "further" constraint the feature model) 
   			// we could use fmv.computeRendundantConstraints() but it only operates over implies/excludes 
   			// simple stuff to fix if we want
   		 }
   		 
   		 System.err.println("#originalConfigurations " + c + "\t\t#specializedConfigurations " + cPrime);       		     		 
   	 }
		
	}

	/*
     *  config is valid if valid in fmv (original FM)
     *  config is non-valid if non-valid in fmv (original FM)
     */
    private boolean isValid(FeatureConfiguration config, FeatureModelVariable fmv) {
		
    	// make a constraint out of a config (conjunctions of literal)
    	Set<String> fts = config.getSelectedFeatures();    	
    	Expression<String> e = ExpressionUtil.mkConjunction(fts);
    	Set<String> dfts = config.getDeselectFeatures();
    	
    	// add to fmv
    	FeatureModelVariable ofmv = (FeatureModelVariable) fmv.copy();
    	ofmv.addConstraint(e);
    	if (dfts.size() > 0) {
        	Expression<String> eNot = ExpressionUtil.mkDisjunction(dfts).not(); // logic pleasure ;) ! (A v B v C) ~ !A ^ !B ^ !C
        	ofmv.addConstraint(eNot);
    	}
    	// if fmv is non valid, config is non-valid
    	return ofmv.isValid();
	}

	private class PairFMV {
    	
    	private FeatureModelVariable _fmv;
    	private FeatureModelVariable _fmvPrime;
    	
    	public PairFMV(FeatureModelVariable fmv, FeatureModelVariable fmvPrime) {
    		_fmv = fmv;
    		_fmvPrime = fmvPrime;
    	}
    	
    	public FeatureModelVariable getOriginalFM() {
    		return _fmv;
    	}
    	
    	public FeatureModelVariable getGeneralizedFM() {
    		return _fmvPrime;
    	}
		 
		 
	}
}

