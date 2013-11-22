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
import java.util.LinkedList;
import java.util.Vector;

import org.pacogen.model.treeStructure.Node;





public class Model_Operator {

//	mxCell v6 = (mxCell) graph.insertEdge(parent, null, "", v4, v5,"entryX=0.5;entryY=0;exitX=0.5;exitY=1;endArrow=none;strokeColor=A67500;rounded=1");	
	

	
	
	
	
	
	protected Node Father ;
	public Node getFather() {
		return Father;
	}
	public void setFather(Node father) {
		Father = father;
	}
	protected String type ;
	protected Vector<Node> nodeVect ;
	
	public Model_Operator ()
	{
		
		nodeVect = new Vector<Node> () ;
	}
	public String toProlog()
	{
		String res ="" ;
		res += type + "(" + Father.getIDPrologName() + ",[" ;
		Iterator<Node> itNode = nodeVect.iterator() ;
		while(itNode.hasNext())
		{
			res += itNode.next().getIDPrologName() ;
			if (itNode.hasNext())
			{
				res +="," ;
			}
		}
	res += "])" ;	
	
		return res ;
	}
	

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vector<Node> getNodeVect() {
		return nodeVect;
	}
	public void setNodeVect(Vector<Node> nodeVect) {
		this.nodeVect = nodeVect;
	} 
	public void addsons(Node n)
	{
		nodeVect.add(n);
	}
	
 
}
