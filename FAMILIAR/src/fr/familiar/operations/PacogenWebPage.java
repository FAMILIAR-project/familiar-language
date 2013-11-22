package fr.familiar.operations;

import java.util.LinkedList;
import com.hp.gagawa.*; 
import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;

public class PacogenWebPage {

	private LinkedList<String> features ;
	private LinkedList<String> configurations;
	private String model  ;
	private String path ;

	public PacogenWebPage(LinkedList<String> _Features , LinkedList<String> _Configurations,  String ModelName, String Path)
	{
		features = _Features ;
		configurations = _Configurations ;
		model = ModelName ;
		path = Path ;
	}
	
	public void buildWebPage()
	{
		Document page = new Document(DocumentType.HTMLStrict);
		
	}
	
}
