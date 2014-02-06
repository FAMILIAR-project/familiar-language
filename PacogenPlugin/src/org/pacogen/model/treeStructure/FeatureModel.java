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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.pacogen.model.OperatorPck.CCT;
import org.pacogen.model.OperatorPck.Model_Operator;
import org.w3c.dom.NodeList;






public class FeatureModel {

	private String Name  = "";
	
	private Node root;
	private Vector<Node> nodelist ;
	private Vector<CCT> cctlVect; 
	
	
	public Node getNodeFromID(String ID)
	{
		Node Res = null ;
		
		Iterator<Node> nodeIt = nodelist.iterator() ;
		boolean found = false ;
		while (nodeIt.hasNext() && !found)
		{
			Node currNode = nodeIt.next() ;
			if (currNode.getId().equals(ID))
					{
					found = true ;
					Res = currNode;
					}
		}
	return Res ;
		
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	
	public String toProlog()
	{
		String res ="" ;
		if(root != null)
		{
		 res = root.ToProlog(); 
		}
		if (cctlVect.size() > 0 )
		{
			Iterator<CCT> CCTit = cctlVect.iterator() ;
			while(CCTit.hasNext())
			{
				CCT currCCt =  CCTit.next() ;
				String	res2 = currCCt.toProlog() ;
				if (CCTit.hasNext())
				{
					if (!res2.equals(""))
					{
					res += res2 + ",";
					}
				}else
				{
					if (!res2.equals(""))
					{
					res += res2 ;
					}
				}
			}
		}
		if(res.endsWith(","))
			res = res.substring(0,res.length()  -1 );
		return res ; 
	}

	
	public FeatureModel(Node root)
	{
	
		this.root = root ;
		nodelist = root.getNodeLstRec() ;
		nodelist.add(root);
		cctlVect = new Vector<CCT>();
	}
	
	public FeatureModel()
	{
	
		this.root = null ;
		nodelist = new Vector<Node>() ;
		cctlVect = new Vector<CCT>();
	}
	
	
	public Vector<CCT> getCctlVect() {
		return cctlVect;
	}


	public void setCctlVect(Vector<CCT> cctlVect) {
		this.cctlVect = cctlVect;
	}
	public void addCCT(CCT cct)
	{
		cctlVect.add(cct);	
	}

	public Node get_root() {
		return root;
	}
	public void set_root(Node _root) {
		this.root = _root;
		nodelist.add(_root);
		nodelist.addAll(root.getNodeLstRec());
	}
	public Vector<Node> get_nodelist() {
		return nodelist;
	}
	public void set_nodelist(Vector<Node> _nodelist) {
		this.nodelist = _nodelist;
	}
	public FeatureModel(Node _root, Vector<Node> _nodelist) {
		super();
		this.root = _root;
		this.nodelist = _nodelist;
	}
	
	public Node getNode(String nodename)
	{
		
		Iterator<Node> nodeIt = nodelist.iterator() ;
		boolean found = false ;
		Node targetNode = null ;
		while (nodeIt.hasNext() && !found)
		{
			Node currNode = nodeIt.next() ;
			if (currNode.getName().equals(nodename))
					{
				found = true ;
				targetNode = currNode;
					}
			
		}
		return targetNode ;
	}
	
	
	public String generatepair()
	{
		String Res = "";
		for (int i = 0; i < nodelist.size(); i++) {
			for (int j = 0; j < nodelist.size(); j++) {
			
				String nodeName1 = nodelist.get(i).getPrologName() ;
				String nodeName2 = nodelist.get(j).getPrologName() ;
				String attr1 = getCommonOperator(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()),nodelist.get(i),nodelist.get(j));
				int attr2 =	getDepth(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()).getName());
				int attr3 = getDepth( nodelist.get(i).getName())  + getDepth(nodelist.get(j).getName()) ;
			
				Res += "a(" + nodeName1.toLowerCase() + "," + nodeName2.toLowerCase() + ",["+ attr1 +"," + attr2 + "," + attr3 + "]).\n" ;
			}

		}
		
			return Res ;
	}
	
	
	public String generatepairV2()
	{
		String Res = "";
		
		if(root ==null)
		{
			// Case of Dimacs files where there is no structure of the model
			Res = "[a(_,_,[null,1,1])]"; 
			
		}else
		{
		for (int i = 0; i < nodelist.size(); i++) {
			for (int j = 0; j < nodelist.size(); j++) {
			
				String nodeName1 = nodelist.get(i).getPrologName() ;
				String nodeName2 = nodelist.get(j).getPrologName() ;
				String attr1 = getCommonOperator(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()),nodelist.get(i),nodelist.get(j));
				int attr2 =	getDepth(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()).getName());
				int attr3 = getDepth( nodelist.get(i).getName())  + getDepth(nodelist.get(j).getName()) ;
			
				Res += "a(" + nodeName1.toLowerCase() + "," + nodeName2.toLowerCase() + ",["+ attr1 +"," + attr2 + "," + attr3 + "])," ;

			}

		}
		Res= Res.substring(0,Res.length() -1 );
		}
	
			return Res ;
	}
	
	public String generatepair2(File f) 
	{
		String Res = "";
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			
			
			for (int i = 0; i < nodelist.size(); i++) {
				for (int j = 0; j < nodelist.size(); j++) {
				
					String nodeName1 = nodelist.get(i).getPrologName() ;
					String nodeName2 = nodelist.get(j).getPrologName() ;
					String attr1 = getCommonOperator(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()),nodelist.get(i),nodelist.get(j));
					int attr2 =	getDepth(getCommonAncestor(nodelist.get(i).getName(),nodelist.get(j).getName()).getName());
					int attr3 = getDepth( nodelist.get(i).getName())  + getDepth(nodelist.get(j).getName()) ;
					
					try {
						fw.write("a(" + nodeName1.toLowerCase() + "," + nodeName2.toLowerCase() + ",["+ attr1 +"," + attr2 + "," + attr3 + "]).\n") ;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			fw.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
			return Res ;
	}
	
	public String printFt()
	{
		String Res = "" ;
		Res += "[";
		for (Iterator<Node> node = nodelist.iterator(); node.hasNext();) {
			Node nd = (Node) node.next();
			
			Res += nd.getIDPrologName();
			if(node.hasNext())
			{
				Res += "," ;
			}
			
		}
		Res += "]";
		
		return Res ;
	}
	

	public String getFeatureLst()
	{
		String Res = "" ;

		for (Iterator node = nodelist.iterator(); node.hasNext();) {
			Node nd = (Node) node.next();
			
			Res += nd.getName();
			if(node.hasNext())
			{
				Res += "," ;
			}
			
		}
	
		return Res ;
	}
	
	public LinkedList<String> getFeatureLinkedList()
	{
		LinkedList<String> L = new LinkedList<String>();

		for (Iterator node = nodelist.iterator(); node.hasNext();) {
			Node nd = (Node) node.next();
			
			L.add(nd.getName());

			
		}
	
		return L ;
	}
	
	
	public String getPrologFeatureLst()
	{
		String Res = "" ;

		for (Iterator<Node> node = nodelist.iterator(); node.hasNext();) {
			Node nd = (Node) node.next();
			
			Res += nd.getPrologName();
			if(node.hasNext())
			{
				Res += "," ;
			}
			
		}
	
		return Res ;
	}
	/*
	 * Retourne l'opérateur qui est commun à Na et Nb, rattaché au père
	 */

	public String getCommonOperator(Node Father, Node NA,Node NB)
	{
		String res = " "; 
		if(Father.getVectoperator().size() == 1)
		{
			
			return Father.getVectoperator().get(0).getType() ;
		}else
			
		{
			boolean stop = false ;
			
			for (Iterator<Model_Operator> iterator = Father.getVectoperator().iterator(); iterator.hasNext() && !stop;) 
			{
				Model_Operator Currop = (Model_Operator) iterator.next();
				
				Vector<Node> nodevect = new Vector<Node>() ;
				
				for (Node node : Currop.getNodeVect()) {
					nodevect.addAll(node.getNodeLstRec()) ;
					
				}
				if (nodevect.contains(NA) && nodevect.contains(NB))
				{
					stop = true ;
				}
				res = Currop.getType() ;
				
				
			}
			
			if (stop == false)
			{
				res = "null" ;
			}
			
		}
			
		
		
		return res ;
		
	}
	
	
	public int getDepth(String getNode)
	{
		
	//	System.err.println(getNode);
		int res =  1 ;
		// recovery of the node of name nodename
		Node targetNode = getNode(getNode) ;
		// then we backup to the root
		if (targetNode == null)
		{
			res = -1 ;
		}
		else if(targetNode.equals(root))
		{
			res = 0 ;
		}
		else
		{
			while(!targetNode.getFather().equals(root))
			{
				targetNode = targetNode.getFather() ;
				res += 1 ;
				
			}
		}
		
		return res ;
	}


	/*
	 *  getCommonAncestor(String nodenameA,String nodenameB)
	 *  V2
	 */
	public Node getCommonAncestor(String nodenameA,String nodenameB)
	{
		
		Vector<Node> AFatherList = new Vector<Node>();
		Vector<Node> BFatherList = new Vector<Node>();
		
		Node currnode = null ;
		Node nodeA = getNode(nodenameA);
		Node nodeB = getNode(nodenameB);

		boolean stop = false ;
		currnode = nodeA;
		AFatherList.add(currnode);
		while (!stop)
		{
			if(currnode.equals(root))
			{
				BFatherList.add(currnode);
				stop = true ;
			}else
			{
				currnode = currnode.getFather() ;
				AFatherList.add(currnode);				
			}
		}
		currnode = null ;
		stop = false ;
		currnode = nodeB;
		BFatherList.add(currnode);
		while (!stop)
		{
		if(currnode.equals(root))
			{
				BFatherList.add(currnode);
				stop = true ;
			}else
			{
				currnode = currnode.getFather() ;
			BFatherList.add(currnode);
			}
		
		}	
		
	
			return intersection(AFatherList,BFatherList).get(0) ; 
	}
	
/*	
	public String setAttribute() 
	{
		String Res = "" ;
		for (Iterator nodeIt = nodelist.iterator(); nodeIt.hasNext();) {
			Node currNode = (Node) nodeIt.next();
			if(currNode.getName().equals(root.getName()))
					{
			Res +=  "put_atts("+ currNode.getPrologName() +" ,ftAttr("+currNode.getPrologName() .toLowerCase() +"))" ;
					}else
					{
						Res +=  "put_atts("+ currNode.getPrologName() +" ,ftAttr("+currNode.getPrologName() .toLowerCase() +"))" ;			
					}
			if(nodeIt.hasNext())
			{
				Res +="," ;
			}
		Res += "\n";
		}
		
		
		return Res ;
	}
*/
	
	
	public String getAttribute() 
	{

		String Res = "" ;
		if(root == null)
		{
	
			
		}else
		{
			for (Iterator<Node> nodeIt = nodelist.iterator(); nodeIt.hasNext();) {
				Node currNode = (Node) nodeIt.next();
				if(currNode.getName().equals(root.getName()))
						{
				Res +=  "put_atts("+ currNode.getIDPrologName() +" ,ftAttr("+currNode.getPrologName() .toLowerCase() +"))" ;
						}else
						{
							Res +=  "put_atts("+ currNode.getIDPrologName() +" ,ftAttr("+currNode.getPrologName() .toLowerCase() +"))" ;			
						}
				if(nodeIt.hasNext())
				{
					Res +="," ;
				}
			Res += "\n";
			}
			
		}

		
		return Res ;
	}
	
	
	private Vector<Node> intersection(Vector<Node> Vect1 , Vector<Node> Vect2)
	{
		Vector<Node> VectRes = new Vector<Node>();
		  
		for (Node elem : Vect1)
		{
			if(Vect2.contains(elem))
			{
							VectRes.add(elem) ;
			}
			
		}
		
		return VectRes ;
	}


}
