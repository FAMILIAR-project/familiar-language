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


package org.pacogen.model.OperatorPck;

import java.util.Iterator;
import java.util.Vector;

import org.pacogen.model.treeStructure.Node;



public class SpecialCCT implements CCT {
	
	private Vector<Node> neg_node ;
	private Vector<Node> node ;
	
	public String toProlog2()
	{
	StringBuilder res = new StringBuilder("");
		
		if(neg_node.size() > 0)
		{
		 res.append("cnf([ ") ;
		Iterator<Node> it = neg_node.iterator();
		while(it.hasNext())
		{
			Node CurrNode = it.next();
			if(it.hasNext())
			{
			
				 res.append(CurrNode.getIDPrologName() +",") ;
			}
			else
			{
			
				res.append(CurrNode.getIDPrologName());
			}
			
		}
		
		res.append("]");
		Iterator<Node> it2 = node.iterator();
		
		res.append(",[");
	
		while(it2.hasNext())
		{
			Node CurrNode = it2.next();
			if(it2.hasNext())
			{
			
				res.append(CurrNode.getIDPrologName()  +", ") ;
			}
			else
			{
				res.append(CurrNode.getIDPrologName()) ;
			}
		}
		res.append("]) ") ;
		}
		else
		{
			 res.append("cnf([]") ;
			Iterator<Node> it2 = node.iterator();
			
			res.append(",[");
		
			while(it2.hasNext())
			{
				Node CurrNode = it2.next();
				if(it2.hasNext())
				{
				
					res.append(CurrNode.getIDPrologName()  +", ") ;
				}
				else
				{
					res.append(CurrNode.getIDPrologName()) ;
				}
			}
			res.append("]) ") ;
		}
		return res.toString() ;
	}
	
	

	
	
	
	public String toProlog()
	{
	StringBuilder res = new StringBuilder("");
	
		if(neg_node.size() > 0)
		{
			
		 res.append("") ;
		Iterator<Node> it = neg_node.iterator();
		while(it.hasNext())
		{
			Node CurrNode = it.next();
			if(it.hasNext())
			{
			
				 res.append(" #\\("+CurrNode.getIDPrologName() +") #\\/ ") ;
			}
			else
			{
			
				res.append(" #\\("+CurrNode.getIDPrologName() +") ");
			}
			
		}
		
		}

		Iterator<Node> it2 = node.iterator();
		
		if(res.length() != 0 && node.size() > 0)
		{
			res.append(" #\\/ ");		
		}
	
	
		while(it2.hasNext())
		{
			Node CurrNode = it2.next();
			if(it2.hasNext())
			{
			
				 res.append(" ("+CurrNode.getIDPrologName() +") #\\/ ") ;
			}
			else
			{
			
				res.append(" ("+CurrNode.getIDPrologName() +" )");
			}
		}
	
		
		return res.toString() ;
		
	}
	

	
	public SpecialCCT() {
		super();
		neg_node = new Vector<Node>() ;
		node = new Vector<Node>() ;
		
	}

	public void addNegNode(Node n)
	{
		
		neg_node.add(n);
	}

	public void addPosNode(Node n)
	{
		
		node.add(n);
	}
	public Vector<Node> getNeg_node() {
		return neg_node;
	}


	public void setNeg_node(Vector<Node> neg_node) {
		this.neg_node = neg_node;
	}


	public Vector<Node> getNode() {
		return node;
	}


	public void setNode(Vector<Node> node) {
		this.node = node;
	}
	

}
