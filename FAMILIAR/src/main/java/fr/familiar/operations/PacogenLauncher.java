///*
// * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
// * manIpulation and Automatic Reasoning) project.
// * http://familiar-project.github.com/
// *
// * FAMILIAR is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Lesser General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * FAMILIAR is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Lesser General Public License for more details.
// *
// * You should have received a copy of the GNU Lesser General Public License
// * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package fr.familiar.operations;
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.xtext.example.mydsl.fML.OpSelection;
//
//import fr.familiar.interpreter.FMLShell;
//import fr.familiar.variable.ConfigurationVariable;
//import fr.familiar.variable.ConfigurationVariableSPLOTImpl;
//import fr.familiar.variable.FeatureModelVariable;
//import fr.familiar.variable.SetVariable;
//import fr.familiar.variable.Variable;
//import fr.pacogen.adapter.SplotAdapter;
//
//public class PacogenLauncher {
//	
//	
//	
//	public PacogenLauncher(FeatureModelVariable testFm) {
//		
//		fm = testFm;
//	}
//
//	public PacogenLauncher(FeatureModelVariable testFm,int executionType) {
//		super();
//		if (executionType > 1)
//		{
//		this.executionType = 1;
//		}else
//		{
//			this.executionType = executionType;
//		}
//		fm = testFm;
//	}
//
//	public PacogenLauncher(FeatureModelVariable testFm,int executionType, int _PartialMatrix) {
//		super();
//		if (executionType > 1)
//		{
//		this.executionType = 1;
//		}else
//		{
//			this.executionType = executionType;
//		}
//		if (_PartialMatrix > 1)
//		{
//		this.PartialMatrix = 1;
//		}else
//		{
//			PartialMatrix = _PartialMatrix ;
//		}
//		fm = testFm;
//
//	}
//	private int executionType = 1;
//	
//	private FeatureModelVariable fm ;
//	
//	private SetVariable testConfig ;
//	
//	private int PartialMatrix = 0 ;
//
//	public int getExecutionType() {
//		return executionType;
//	}
//
//	public FeatureModelVariable getTestFm() {
//		return fm;
//	}
//
//
//	public SetVariable getTestConfig() {
//		return testConfig;
//	}
//
//	public void launchPacogen()
//	{
//		
//		String pacogenFilename = null ; 
//		
//		
//		if(System.getProperty("os.name").equals("Linux") && System.getProperty("os.arch").equals("amd64"))
//		{
//			pacogenFilename = "Pacogen" ;
//		}
//		else if(isOSX())
//		{
//			pacogenFilename = "PacogenForMacOS" ;
//		}
//		else
//		{
//			FMLShell.getInstance().printError("Sorry your architecture is not supported") ;
//			return ; 
//		}
//		
//		assert (pacogenFilename != null); 
//		
//		File pac = new File (pacogenFilename);		
//	  
//		if(pac.exists())
//		{
//			 File sol = new File("solution.txt") ;
//			 if(sol.exists())
//			 {
//			sol.delete() ;
//			 }
//			 File mod = new File("model.txt") ;
//			 if(mod.exists())
//			 {
//				 mod.delete() ;
//			 }
//			 
//		SplotAdapter splAdapt = new SplotAdapter() ;
//		splAdapt.parse(fm.toSPLOT());
//		fr.pacogen.model.treeStructure.FeatureModel FmPaco = splAdapt.getModel() ;
//		String ftlsit = FmPaco.getFeatureLst();
//		System.out.println(ftlsit);
//		String LstFt = FmPaco.getFeatureLst();
//		outputgenerator(FmPaco); 
//		Runtime runtime = Runtime.getRuntime();
//		try {
//		
//			runtime.exec("chmod +x " + pacogenFilename);
//			Process p = runtime.exec("./" + pacogenFilename) ;
//			try {
//				p.waitFor();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if(!sol.exists())
//			{
//				FMLShell.getInstance().printError("An error with pacogen occured, please report a bug on github with the associated feature model") ;
//			}
//			else
//			{
//			LinkedList<String> configLst = solutionReader();
//			int i = 0 ;
//			Set<Variable> LstConf = new HashSet<Variable>();
//			for (Iterator<String> iterator = configLst.iterator(); iterator.hasNext();) {
//				String string = (String) iterator.next();
//				LstConf.add(confBuilder(string, LstFt, i)) ;
//			}
//			
//		SetVariable stVar = new SetVariable(LstConf, "pwConfig")	;
//		testConfig	= stVar;
//		mod.delete() ;
//		sol.delete() ;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}else
//		{
//			FMLShell.getInstance().printError("Pacogen Runtime is missing") ;
//		}
//		
//	}
//		
//	
//	/**
//	 * TODO move to another place 
//	 * https://developer.apple.com/library/mac/#technotes/tn2002/tn2110.html
//	 * @return
//	 */
//	public static boolean isOSX() {
//		String osName = System.getProperty("os.name");
//		return osName.contains("OS X");
//	}
//
//		
//	private ConfigurationVariable confBuilder(String ConfigPaco, String LstFt, int ConfNb)
//	{
//		ConfigurationVariableSPLOTImpl Ci = new ConfigurationVariableSPLOTImpl(fm, "c" + Integer.toString(ConfNb)) ;
//		Collection<String> varcoll = Ci.getUnselected() ;
//		for (String variable : varcoll) {
//			System.out.println(variable);
//		}
//		
//		String[] FtTab = LstFt.split(",");
//		String[] confTab = ConfigPaco.split(",");
//		
//		for (int i = 0 ; i < FtTab.length ; i++)
//		{
//			String FtName = FtTab[i];
//			String FtState = confTab[i];
//			
//			if(FtState.equals("0"))
//			{ 
//				Ci.applyBasicDecisions(FtName, OpSelection.DESELECT)	;
//			}else if(FtState.equals("1"))
//			{
//				Ci.applyBasicDecisions(FtName, OpSelection.SELECT)	 ;
//			}
//			else {
//				Ci.applyBasicDecisions(FtName, OpSelection.UNSELECT)	 ;
//			}
//			
//		}
//		
//		return Ci ;
//		
//	}
//		
//	
//	
//	private LinkedList<String> solutionReader()
//	{
//		LinkedList<String> res = new LinkedList<String>();
//		try {
//			File sol = new File("solution.txt") ;
//			Scanner sc = new Scanner(sol)  ;
//		//	System.out.println(sol.getAbsolutePath());
//				while(sc.hasNext())
//				{
//					res.add(sc.next());
//				}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return res ;
//	}
//	
//	
//	private void 	 outputgenerator(fr.pacogen.model.treeStructure.FeatureModel PacoFM)
//	{
//		int i = 0 ;
//		String FileName = "fm";
//		String MatrixSize = "200";
//		String Labelling = "[ff]";
//		String MinimizationTime = "_";
//		String AllDif = "yes";
//		String PartialMatrix = Integer.toString(this.PartialMatrix) ;
//		String Sort = "yes";
//		String executionTypeStr = Integer.toString(executionType) ;
//		String Feature = "" ;
//		String Contraintes = "" ;
//		String PairInfo = "" ;
//		String Attr = "" ;
//		String FileName2 = FileName.replace("./", "");
//		FileName2 = FileName.replace("/", "");
//		FileName2 = FileName.replace("\\", "");
//		
//
//
//		PairInfo = PacoFM.generatepairV2();
//		Attr = "[" + PacoFM.getAttribute() +"]";
//	
//		Feature = PacoFM. printFt();
//		Contraintes = PacoFM.toProlog();
//		Feature =PacoFM. printFt();
//		if(PacoFM.get_root()!=null)
//		{
//			Contraintes +="," + PacoFM.get_root().getIDPrologName() + "= 1" ;
//		}
//
//		
//		/*
//		 * 
//		 * PairInformations,Attributes,Features,CtrList,MatrixSize,ModelName,Labelling,Minimization,AllDif,Sort,SymetricBreaking,FileName
//		 */
//		
//		Contraintes ="[" + Contraintes + "]";
//		java.io.File f2 = new java.io.File("model.txt") ;
//		String output = "[[" + PairInfo +"],"+  Attr  +","+ Feature+","+ Contraintes + ","   +  MatrixSize + "," +"'"+ executionTypeStr +"'," + Labelling  + ","+ MinimizationTime + ","+ AllDif +","+ Sort +","+ PartialMatrix +",'" +FileName2+"']." ;
//
//		java.io.FileWriter fw;
//		try {
//			fw = new java.io.FileWriter(f2);
//			fw.write(output);
//			fw.close() ;
//			
//		} catch (java.io.IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		
//	}
//	
//
//}
