package fr.pacogen.export;
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




import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.pacogen.model.treeStructure.FeatureModel;
import org.pacogen.model.treeStructure.Node;

import splar.core.constraints.PropositionalFormula;
import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModelException;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.SolitaireFeature;





public class SplotAdapterFamiliar {
	
	
	private String Extension = "*.xml" ;
	
	private String Model ;
	
	private FeatureModel Fm ; 
	
	private HashMap<String, String> table;
	
	public SplotAdapterFamiliar()
	{
		
		Model = "";
		Fm = new FeatureModel();
		table = new HashMap<String, String>();
	}
	
	public FeatureModel getModel() {
		
		return Fm;
	}
	


	public void parse(splar.core.fm.FeatureModel featureModel ) {
		String featureModelFile = Model;

		try {
			featureModel.loadModel();
		} catch (FeatureModelException e1) {
	
			e1.printStackTrace();
		}

		String nom = featureModel.getName();
	//	nom = nom.replace(' ', '_');
		Node Root = new Node(featureModel.getRoot().getName()) ;
		table.put(featureModel.getRoot().getID(), featureModel.getRoot().getName());
		Root.setId(featureModel.getRoot().getID());
		buildModel(featureModel.getRoot(),Root);
		Fm.set_root(Root);

		Fm.setName(featureModel.getName());
	
		if (featureModel.getConstraints().size() != 0) {
			createCTC(featureModel,Fm);
		}
	}


	public void loadModel(String File) {
		Model = File ;

	}

	public String getExtension() {
		
		return Extension;
	}
	
	private org.pacogen.model.OperatorPck.Model_Operator getOptOperator(Node n)
	{
		org.pacogen.model.OperatorPck.Model_Operator res = null;
		List<org.pacogen.model.OperatorPck.Model_Operator> lstOp = n.getVectoperator();
		Boolean Stop = false ;
		for (int i = 0 ; i<lstOp.size() && !Stop; i++)
		{
			
			if(lstOp.get(i) instanceof org.pacogen.model.OperatorPck.Optionnal)
			{
				res = lstOp.get(i);
				Stop = true ;
			}
		}
	if(!Stop)
	{
		res = new org.pacogen.model.OperatorPck.Optionnal();
		n.addOperator(res);
		
	}
		return res;
	}

private org.pacogen.model.OperatorPck.Model_Operator getAndOperator(Node n)
{
	org.pacogen.model.OperatorPck.Model_Operator res = null;
	List<org.pacogen.model.OperatorPck.Model_Operator> lstOp = n.getVectoperator();
	Boolean Stop = false ;
	for (int i = 0 ; i<lstOp.size() && !Stop; i++)
	{
		
		if(lstOp.get(i) instanceof org.pacogen.model.OperatorPck.And)
		{
			res = lstOp.get(i);
			Stop = true ;
		}
	}
if(!Stop)
{

	res = new org.pacogen.model.OperatorPck.And();
	n.addOperator(res);
	
}
	return res;
}


private void buildModel(FeatureTreeNode _FTN, Node Ft)
{
	
	
	 table.put(_FTN.getID(), _FTN.getName());
	
		for( int i = 0 ; i < _FTN.getChildCount() ; i++ ) {	
			FeatureTreeNode Curr_FTN =(FeatureTreeNode) _FTN.getChildAt(i);

			 if ( Curr_FTN instanceof SolitaireFeature ) {
				
				 org.pacogen.model.OperatorPck.Model_Operator Op = null;
				 if ( ((SolitaireFeature)Curr_FTN).isOptional())
					{
					  Op = getOptOperator(Ft);		
				
					}
					else
					{
					  Op = getAndOperator(Ft);	
					}
				 Node n2 = new Node(Curr_FTN.getName());
				 n2.setId(Curr_FTN.getID());
				 table.put(Curr_FTN.getID(), Curr_FTN.getName());
				 Op.addsons(n2);
				 Ft.addson(n2);
				 buildModel(Curr_FTN,n2); 
			 }else if ( Curr_FTN instanceof FeatureGroup )
			 {
				 org.pacogen.model.OperatorPck.Model_Operator Op = null;			
				 int minCardinality = ((FeatureGroup)Curr_FTN).getMin();
				 int maxCardinality = ((FeatureGroup)Curr_FTN).getMax();
				 if(minCardinality==1 && maxCardinality==1 )
				 {
					 Op = new org.pacogen.model.OperatorPck.Xor();
					 
				 }else  if(minCardinality==1 && maxCardinality==-1 ){
				 
					 Op = new org.pacogen.model.OperatorPck.Or();
				 }else
				 {
					 Op = new org.pacogen.model.OperatorPck.Card(minCardinality, maxCardinality);
				 }
				 
				 for (int j = 0; j <Curr_FTN.getChildCount() ; j++)
				 {
					 Node n2 = new Node(((FeatureTreeNode)Curr_FTN.getChildAt(j)).getName());
					 n2.setId(((FeatureTreeNode)Curr_FTN.getChildAt(j)).getID());
					 table.put(Curr_FTN.getID(), Curr_FTN.getName());
					 Op.addsons(n2);	
					 buildModel(((FeatureTreeNode)Curr_FTN.getChildAt(j)),n2); 
				 }
				 Ft.addOperator(Op);
				
			 }
			else {
	
				}
			}
}



	



/* Fin du traitemement */

public String addElement(String ID, String Name) {
	if (table.containsValue(Name)) {
		String str = random_name();
		Name = Name + str;
	}
	table.put(ID, Name);
	return Name;
}

public static String random_name() {
	Random r = new Random();
	int v = r.nextInt();
	String res = String.valueOf(v);
	return res;
}


public void createCTC(splar.core.fm.FeatureModel featureModel,org.pacogen.model.treeStructure.FeatureModel FM) {

	for (PropositionalFormula formula : featureModel.getConstraints()) {

		org.pacogen.model.OperatorPck.SpecialCCT myCCT = new org.pacogen.model.OperatorPck.SpecialCCT();
		Vector<String> IDtable = new Vector<String>();
		Vector<Boolean> NegTable = new Vector<Boolean>();
		String str = formula.getFormula();
		String[] tab = str.split(" or ");

		for (int i = 0; i < tab.length; i++) {
			if (tab[i].contains("~")) {
				NegTable.add(true);

				String tmp = tab[i].replace("~", "");
				String tmp2 = tmp.replace("(", "");
				String ID1 = tmp2.replace(")", "");
				myCCT.addNegNode(FM.getNodeFromID(ID1));
				//IDtable.add(ID1);
			} else {
				NegTable.add(false);
				String tmp = tab[i].replace("~", "");
				String tmp2 = tmp.replace("(", "");
				String ID1 = tmp2.replace(")", "");
				myCCT.addPosNode(FM.getNodeFromID(ID1));

			}

		}

		Fm.addCCT(myCCT);
	}
}


}
