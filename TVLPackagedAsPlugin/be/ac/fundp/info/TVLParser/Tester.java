package be.ac.fundp.info.TVLParser;

import java.io.File;

public class Tester {

	public static void main (String[] args) {

		String dirName = "/Users/phw/Documents/Cours/master/MEMOIRE/testMustSucceed";
		File dir = new File(dirName);

		String[] children = dir.list();
		if (children == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int i=0; i<children.length; i++) {
		        // Get filename of file or directory
		        String fileName = children[i];  
		        if (fileName.length() > 4 && fileName.substring(0, 4).equals("test")){
		        	System.out.println("File : " + fileName + "------------------------------------------------------------");		        
			        try{
			        	Launcher.main(new String[]{dirName + "/" + fileName}); 
			        	System.out.println("File OK");
			        } catch (Exception e){
			        	System.out.println("File refused, Exception : " + e);
			        }
			        System.out.println("");	   
		        }
		              
		    }
		}
		
	}
}
