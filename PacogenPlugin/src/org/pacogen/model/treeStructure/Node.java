/*
Copyright or © or Copr. 
 contributor(s) : 
Aymeric Hervieu
Arnaud Gotlieb
Benoit Baudry
(29/05/2013)

aymeric.hervieu@gmail.com
benoit.baudry@inria.com
arnaud@simula.no

This software is a computer program whose purpose is to generate pairwise 
configurations on feature models.

This software is governed by the CeCILL-C license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the  CeCILL-C 
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL-C  license and that you accept its terms.
*/


package org.pacogen.model.treeStructure;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.pacogen.model.OperatorPck.Model_Operator;





public class Node {
	
	
	
	private Vector<Model_Operator> Vectoperator ;
	
	private String cct ;
	
	private String name ;
	
	private Node father ;
	
	private Vector<Node> sons ;
	
	private  String ID ;
	
	public void setId(String _ID)
	{
		ID = _ID;
		
	}
	
	public String getId()
	{
		
		return ID ;
	}

	
	public String getPrologName()
	{
		String res = StringOperation.sansAccent(name);
		String res2 = "";
		for (int i = 0 ; i < res.length(); i++)
		{
			if(Character.isLetterOrDigit(res.charAt(i)) || res.charAt(i) == '_' )
			{
				res2 += res.charAt(i);
			}
			
		}
		res2 = "a" + res2 ;
		return res2.toUpperCase();
		
	}
	
	public String getIDPrologName()
	{
		String res = StringOperation.sansAccent(ID);
		String res2 = "";
		for (int i = 0 ; i < res.length(); i++)
		{
			if(Character.isLetterOrDigit(res.charAt(i))|| res.charAt(i) == '_' )
			{
				res2 += res.charAt(i);
			}
			
		}
		res2 = "a" + res2 ;
		return res2.toUpperCase();
		}
	
	
	public String ToProlog()
	{
		String res = ""; 
		
		Iterator<Model_Operator> opit = Vectoperator.iterator() ;
		while (opit.hasNext())
		{
			res +=  opit.next().toProlog() + ",";
			
		}
		

		Iterator<Node> nodeIt = sons.iterator() ;
		while (nodeIt.hasNext())
		{
			
			res += nodeIt.next().ToProlog() ;
		}
				return res ;
		
	}
	
	


	public Node(Vector<Model_Operator> operator, String cct, String name, Node father,
			Vector<Node> sons) {
		super();
		this.Vectoperator = operator;
		this.cct = cct;
		this.name = name;
		this.father = father;
		this.sons = sons;
	}
	
	public Node(Model_Operator operator, String cct, String name, Node father,
			Vector<Node> sons) {
		super();
		this.Vectoperator = new  Vector<Model_Operator> ();
		Vectoperator.add(operator) ;
		this.cct = cct;
		
		this.name = name;
		this.father = father;
		this.sons = sons;
	}
	
	
	public Node( String name, Node father,
			Vector<Node> sons) {
		super();
		this.Vectoperator =new  Vector<Model_Operator> ();
		this.cct = null;
		setName(name);
		this.father = father;
		this.sons = sons;
	}
	
	public Node( String name) {
		super();
		this.Vectoperator =new  Vector<Model_Operator> ();
		this.cct = null;
		setName(name);
		this.father = null;
		this.sons = new Vector<Node>();
	}
	
	
	public void addOperator(Model_Operator Op)
	{
		Op.setFather(this);
		Vectoperator.add(Op);
		
		Vector<Node> vectnode = Op.getNodeVect();
		Iterator<Node> nodeIt = vectnode.iterator() ;
		while (nodeIt.hasNext())
		{
			
			nodeIt.next().setFather(this);
		}
		sons.addAll(Op.getNodeVect()) ;
	}
	
	public Vector<Model_Operator> getVectoperator() {
		return Vectoperator;
	}

	public void setVectoperator(Vector<Model_Operator> vectoperator) {
		
		Vectoperator = vectoperator;
		Iterator<Model_Operator> opit = Vectoperator.iterator() ;
		while (opit.hasNext())
		{
			Model_Operator currop = opit.next(); 
			currop.setFather(this);
			sons.addAll(currop.getNodeVect());
			
		}
		
		
	}

	public String getCct() {
		return cct;
	}

	public void setCct(String cct) {
		this.cct = cct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Vector<Node> getSons() {
		return sons;
	}

	public void setSons(Vector<Node> sons) {
		this.sons = sons;
	}

	public boolean equals(Node n)
	{
		return this.name.equals(n.getName());
	}

	
	public void addson(Node son)
	{
		son.setFather(this);
		sons.add(son) ;	
	}
	
	public Vector<Node> getNodeLstRec()
	{
		Vector<Node> vectRes = new Vector<Node>() ;
		vectRes.addAll(sons);
		Iterator<Node> nodeIt = sons.iterator();
		while(nodeIt.hasNext())
		{
			vectRes.addAll(nodeIt.next().getNodeLstRec());		
		}
		return vectRes;
		
	}
	

}
