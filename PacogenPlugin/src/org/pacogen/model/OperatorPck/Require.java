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

import org.pacogen.model.treeStructure.Node;




public class Require  implements CCT {

	private String type ;
	private Node nodeA ;
	private Node nodeB ;
	
	public String ToPrologSplot()
	{
		return "Stub";
		
	}
	public Node getNodeA() {
		return nodeA;
	}
	public String toProlog()
	{
		
		return "require("+nodeA.getPrologName() + "," + nodeB.getPrologName()+ ")" ;
	}
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}

	public Node getNodeB() {
		return nodeB;
	}

	public void setNodeB(Node nodeB) {
		this.nodeB = nodeB;
	}

	public Require() {
		super();
		type  = "require" ;
	}

	public String getType() {
		return type;
	}

	public Require(Node nodeA, Node nodeB) {
		super();
		type  = "require" ;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
}
