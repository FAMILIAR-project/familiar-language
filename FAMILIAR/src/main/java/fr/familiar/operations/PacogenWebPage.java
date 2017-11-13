/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.operations;

import java.util.LinkedList;

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
