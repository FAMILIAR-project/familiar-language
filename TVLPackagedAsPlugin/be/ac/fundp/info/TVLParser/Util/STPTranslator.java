package be.ac.fundp.info.TVLParser.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import com.sun.tools.javac.util.Pair;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class STPTranslator {

		protected FeatureSymbol root ;
		protected File output;
		protected FileWriter outfw;
		//protected Map<String,String> varTypes = new HashMap<String,String>();
		protected Map<String,Integer> varIndices = new HashMap<String,Integer>();
		protected List<Pair<String,String>> parents = new Vector<Pair<String,String>>();
		protected List<String> constraints = new Vector<String>();
		protected List<String> intAttributes = new Vector<String>();
		protected int nbFeatures = 0;
		
		public STPTranslator(FeatureSymbol root, String outputFile) throws IOException {
			this.root = root;
			this.output = new File(outputFile);
			if(output.exists()) output.delete();
			output.createNewFile();
			
		}
		
		public STPTranslator() {
			
		}
		
		protected void getVariables(FeatureSymbol f,String parent) throws Exception {
			String s = f.getID();
			int idOfThisFeature = nbFeatures++;
			if(!varIndices.containsKey(s)) {
				varIndices.put(s,idOfThisFeature);
			}
			
			if(parent != null) {
				parents.add(new Pair<String, String>(s, parent));
			}
			if(f.hasAttributesSymbols()) {
				for(Map.Entry<String,AttributeSymbol> entry : f.getAttributesSymbols().entrySet()){
					String attId = f.getID()+"_"+entry.getValue().getID().replace(".", "_");
					String trueType = "default";
					switch (entry.getValue().getTrueType()) {
					case Expression.INT : trueType = "int"; break;
					case Expression.REAL : trueType = "real"; break;
					case Expression.ENUM : trueType = "enum"; break;
					case Expression.STRUCT : trueType = "struct"; break;
					case Expression.BOOL : trueType = "bool"; break;
					}
					if(!intAttributes.contains(attId)) {
						intAttributes.add(attId);
					   
					}
				}
			} 
			
			if(f.hasChildrenFeatures()) {
				for(Map.Entry<String,FeatureSymbol> entry : f.getChildrenFeatures().entrySet()){
					getVariables(entry.getValue(),s);
				}
			}
		}
		
		public void getConstraints(FeatureSymbol f) throws Exception {
			if(f.hasConstraintSymbols()) {
				for(ConstraintSymbol cs : f.getConstraintSymbols()) {
					STPTranslatorExpressionVisitor visitor = new STPTranslatorExpressionVisitor(varIndices,intAttributes);
					cs.getExpression().accept(visitor);
					outfw.write("ASSERT("+visitor.toString()+");\n");
				}
			}
			if(f.hasChildrenFeatures()) {
				for(Map.Entry<String,FeatureSymbol> entry : f.getChildrenFeatures().entrySet()){
					getConstraints(entry.getValue());
				}
			}
		}
		
		public void getCardinalities(FeatureSymbol f) throws Exception {
			
			if(f.hasChildrenFeatures()) {
				StringWriter swSum = new StringWriter();
				int n = (int)(Math.ceil(Math.log(f.getChildrenFeatures().entrySet().size()+1) / Math.log(2)));
				if(n>1) {
					swSum.append("BVPLUS("+n);
				}
				for(Map.Entry<String,FeatureSymbol> entry : f.getChildrenFeatures().entrySet()){
					getCardinalities(entry.getValue());
					if(n>1) {
						swSum.append(", 0b");
						for(int i=1;i<n;i++){
							swSum.append("0");
						}
						swSum.append("@");
					}
					swSum.append("F["+entry.getValue().getID()+"]");
					
				}
				if(n>1)
					swSum.append(")");
					
				outfw.write("ASSERT(F["+f.getID()+"]=0b1 => BVLE("+swSum.toString()+","+dec2bin(f.getMaxGroupCardinality(),n)+"));\n");
				outfw.write("ASSERT(F["+f.getID()+"]=0b1 => BVGE("+swSum.toString()+","+dec2bin(f.getMinGroupCardinality(),n)+"));\n");
			}
			
		}
		
		public void write() throws Exception {
			outfw = new FileWriter(output);
			getVariables(this.root,null);
			writeVariables();
			getConstraints(this.root);
			getCardinalities(this.root);
			//sw.append(writeConstraints());
			outfw.write("QUERY(F["+this.root.getID()+"]=0bin0);");
			//return sw.toString();
			outfw.flush();
			outfw.close();
			
		}
		
		
		protected void writeVariables() throws IOException {
			int n = (int)(Math.ceil(Math.log(nbFeatures) / Math.log(2)));
			
			outfw.write("F: ARRAY BITVECTOR("+n+") OF BITVECTOR(1);\n");
			
			for(String featureName : this.varIndices.keySet()) {
				outfw.write(featureName+": BITVECTOR("+n+")="+dec2bin(this.varIndices.get(featureName),n)+";\n");
			}
			
			for(String attName : intAttributes) {
				outfw.write(attName+": BITVECTOR(32);\n");
			}
			outfw.write("ASSERT(F["+this.root.getID()+"]=0bin1);\n");
			
			for(Pair<String,String> pair : this.parents) {
				outfw.write("ASSERT(F["+pair.fst+"]=0bin1 => F["+pair.snd+"]=0bin1);\n");
			}
			

			
		//	return sw.toString();
		}
		
		protected String writeConstraints() {
			StringWriter sw = new StringWriter();
			for(String constraint : this.constraints) {
				sw.append("ASSERT("+constraint+");\n");
			}
			return sw.toString();
		}
		
		public static String dec2bin(int i,int length) {
			StringWriter sw = new StringWriter();
			sw.append("0bin");
			String binaryString = Integer.toBinaryString(i);
			
			while(binaryString.length() < length--) {
				sw.append("0");
			}
			sw.append(binaryString);
			return sw.toString();
		}
	
}
