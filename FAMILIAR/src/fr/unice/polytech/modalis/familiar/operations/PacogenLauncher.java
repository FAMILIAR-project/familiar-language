package fr.unice.polytech.modalis.familiar.operations;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import org.xtext.example.mydsl.fML.OpSelection;

import fr.pacogen.adapter.SplotAdapter;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariableSPLOTImpl;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

public class PacogenLauncher {
	
	
	
	public PacogenLauncher(FeatureModelVariable testFm) {
		
		fm = testFm;
	}

	public PacogenLauncher(FeatureModelVariable testFm,int executionType) {
		super();
		if (executionType > 1)
		{
		this.executionType = 1;
		}else
		{
			this.executionType = executionType;
		}
		fm = testFm;
	}

	public PacogenLauncher(FeatureModelVariable testFm,int executionType, int _PartialMatrix) {
		super();
		if (executionType > 1)
		{
		this.executionType = 1;
		}else
		{
			this.executionType = executionType;
		}
		if (_PartialMatrix > 1)
		{
		this.PartialMatrix = 1;
		}else
		{
			PartialMatrix = _PartialMatrix ;
		}
		fm = testFm;

	}
	private int executionType = 1;
	
	private FeatureModelVariable fm ;
	
	private SetVariable testConfig ;
	
	private int PartialMatrix = 0 ;

	public int getExecutionType() {
		return executionType;
	}

	public FeatureModelVariable getTestFm() {
		return fm;
	}


	public SetVariable getTestConfig() {
		return testConfig;
	}

	public void launchPacogen()
	{
		if(System.getProperty("os.name").equals("Linux") && System.getProperty("os.arch").equals("amd64") )
		{

		
	 File pac = new File("Pacogen") ;
		if(pac.exists())
		{
			 File sol = new File("solution.txt") ;
			 if(sol.exists())
			 {
			sol.delete() ;
			 }
			 File mod = new File("model.txt") ;
			 if(mod.exists())
			 {
				 mod.delete() ;
			 }
			 
		SplotAdapter splAdapt = new SplotAdapter() ;
		splAdapt.parse(fm.toSPLOT());
		fr.pacogen.model.treeStructure.FeatureModel FmPaco = splAdapt.getModel() ;
		String ftlsit = FmPaco.getFeatureLst();
		System.out.println(ftlsit);
		String LstFt = FmPaco.getFeatureLst();
		outputgenerator(FmPaco); 
		Runtime runtime = Runtime.getRuntime();
		try {
		
			runtime.exec("chmod +x Pacogen");
			Process p = runtime.exec("./Pacogen") ;
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			LinkedList<String> configLst = solutionReader();
			int i = 0 ;
			Set<Variable> LstConf = new HashSet<Variable>();
			for (Iterator<String> iterator = configLst.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string);
				
				
				LstConf.add(confBuilder(string, LstFt, i)) ;
			}
			
		SetVariable stVar = new SetVariable(LstConf, "pwConfig")	;
		testConfig	= stVar;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else
		{
			FMLShell.getInstance().printError("Pacogen Runtime is missing") ;
		}
		}else
		{
			FMLShell.getInstance().printError("Sorry your architecture is not supported") ;
		}
	}
		
	
	public void launchPacogenBug()
	{
				 
		SplotAdapter splAdapt = new SplotAdapter() ;
		splAdapt.parse(fm.toSPLOT());
		fr.pacogen.model.treeStructure.FeatureModel FmPaco = splAdapt.getModel() ;

	String LstFt = FmPaco.getFeatureLst();
		outputgenerator(FmPaco); 
	
		Set<Variable> LstConf = new HashSet<Variable>();
				
		LstConf.add(confBuilder("1,1,1,1,1,1,1,1,0,1,0", LstFt, 0)) ;
			
			
		SetVariable stVar = new SetVariable(LstConf, "pwConfig")	;
		testConfig	= stVar;
}
		
		
	private ConfigurationVariable confBuilder(String ConfigPaco, String LstFt, int ConfNb)
	{
		System.out.println(ConfNb);
		ConfigurationVariableSPLOTImpl Ci = new ConfigurationVariableSPLOTImpl(fm, "c" + Integer.toString(ConfNb)) ;
		System.out.println(fm);
		Collection<String> varcoll = Ci.getUnselected() ;
		System.out.println("la");
		
		for (String variable : varcoll) {
			System.out.println(variable);
		}
		System.out.println("Finla");
		String[] FtTab = LstFt.split(",");
		String[] confTab = ConfigPaco.split(",");
		
		for (int i = 0 ; i < FtTab.length ; i++)
		{
			String FtName = FtTab[i];
			String FtState = confTab[i];
			
			if(FtState.equals("0"))
			{ 
				Ci.applyBasicDecisions(FtName, OpSelection.DESELECT)	;
			}else if(FtState.equals("1"))
			{
				Ci.applyBasicDecisions(FtName, OpSelection.SELECT)	 ;
			}
			else {
				Ci.applyBasicDecisions(FtName, OpSelection.UNSELECT)	 ;
			}
			
		}
		
		return Ci ;
		
	}
		
	
	
	private LinkedList<String> solutionReader()
	{
		LinkedList<String> res = new LinkedList<String>();
		try {
			File sol = new File("solution.txt") ;
			Scanner sc = new Scanner(sol)  ;
		//	System.out.println(sol.getAbsolutePath());
				while(sc.hasNext())
				{
					res.add(sc.next());
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res ;
	}
	
	
	private void 	 outputgenerator(fr.pacogen.model.treeStructure.FeatureModel PacoFM)
	{
		int i = 0 ;
		String FileName = "fm";
		String MatrixSize = "200";
		String Labelling = "[ff]";
		String MinimizationTime = "_";
		String AllDif = "yes";
		String PartialMatrix = Integer.toString(this.PartialMatrix) ;
		String Sort = "yes";
		String executionTypeStr = Integer.toString(executionType) ;
		String Feature = "" ;
		String Contraintes = "" ;
		String PairInfo = "" ;
		String Attr = "" ;
		String FileName2 = FileName.replace("./", "");
		FileName2 = FileName.replace("/", "");
		FileName2 = FileName.replace("\\", "");
		


		PairInfo = PacoFM.generatepairV2();
		Attr = "[" + PacoFM.getAttribute() +"]";
	
		Feature = PacoFM. printFt();
		Contraintes = PacoFM.toProlog();
		Feature =PacoFM. printFt();
		if(PacoFM.get_root()!=null)
		{
			Contraintes +="," + PacoFM.get_root().getIDPrologName() + "= 1" ;
		}

		
		/*
		 * 
		 * PairInformations,Attributes,Features,CtrList,MatrixSize,ModelName,Labelling,Minimization,AllDif,Sort,SymetricBreaking,FileName
		 */
		
		Contraintes ="[" + Contraintes + "]";
		java.io.File f2 = new java.io.File("model.txt") ;
		String output = "[[" + PairInfo +"],"+  Attr  +","+ Feature+","+ Contraintes + ","   +  MatrixSize + "," +"'"+ executionTypeStr +"'," + Labelling  + ","+ MinimizationTime + ","+ AllDif +","+ Sort +","+ PartialMatrix +",'" +FileName2+"']." ;

		java.io.FileWriter fw;
		try {
			fw = new java.io.FileWriter(f2);
			fw.write(output);
			fw.close() ;
			
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	

}
