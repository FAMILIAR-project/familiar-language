package fr.familiar.attributedfm.reasoning;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.login.Configuration;

import es.us.isa.util.Node;
import es.us.isa.util.Tree;
import fr.familiar.attributedfm.ConstantIntConverter;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.GenericAttribute;
import fr.familiar.attributedfm.Relation;
import fr.familiar.attributedfm.domain.Cardinality;
import fr.familiar.attributedfm.domain.KeyWords;

public class PacogenReasoner  implements FeatureModelReasoner {
	
	

	private LinkedList<String> ctrModel ;
	private LinkedList<String> VarAttrList ;
	private LinkedList<String> attrDomain ;
	private LinkedList<String> ftList ;
	private LinkedList<String> ftListFamaName ;
	private LinkedList<String> domainList ;
	private LinkedList<String> AttrListFamaName ;
	private LinkedList<String> AttrPrologNameList ;
	
	private HashMap<String,String> FamaToPacoName ;
	private HashMap<String,HashMap<String, String>> AttributesName ;
	

	public PacogenReasoner() {
		super();
		reset();
	}
	
	public void addCardinality(Relation rel,
			Feature child, Feature parent,
			Iterator<Cardinality> cardinalities) {
		//not supported by paco
		
	}

	
	public void addExcludes(Relation rel,
			Feature origin,
			Feature destination) {
		String originName = initVariable(origin) ;
		String destinationName = initVariable(destination) ;
		ctrModel.add("#\\"+ originName + "#\\/ #\\" + destinationName) ;
		
	}

	
	public void addFeature(Feature feature,
			Collection<Cardinality> cardIt) {
		

		
	}

	
	public void addMandatory(Relation rel,
			Feature child, Feature parent) {	
		String FatherName = initVariable(parent) ;
		String ChildName = initVariable(child) ;
		ctrModel.add("and("+ FatherName + ",[" +ChildName +"])") ;
		
		
	}

	
	public void addOptional(Relation rel,
			Feature child, Feature parent) {
		
		String FatherName = initVariable(parent) ;
		String ChildName = initVariable(child) ;
		ctrModel.add("opt("+ FatherName + ",[" +ChildName +"])") ;
	}

	
	protected void addRequires(Relation rel,
			Feature origin,
			Feature destination) {
		String originName = initVariable(origin) ;
		String destinationName = initVariable(destination) ;
		ctrModel.add("#\\"+ originName + "#\\/" + destinationName) ;
	}
	
	
	private String initVariable(Feature Feature)
	{
		if(FamaToPacoName.containsKey(Feature.getName()))
		{
			return FamaToPacoName.get(Feature.getName()) ;
		}
		else{	
		
			String PacoName = "A_" + Feature.getName().toUpperCase() ;
			VarAttrList.add("put_atts("+PacoName+",featureName('" + Feature.getName() + "'))") ;
			FamaToPacoName.put(Feature.getName(), PacoName ) ;
			AttributesName.put(Feature.getName(), new HashMap<String, String>()) ;
			ftList.add(PacoName) ;
			ftListFamaName.add(Feature.getName()) ;
			initAttributes(Feature) ;
			return PacoName  ; 
		}
		
		
		
		
		}
	
	private void initAttributes(Feature Feature)
	{		

		Iterator<? extends GenericAttribute> it = Feature.getAttributes().iterator();
		
			while (it.hasNext()) {
				
				GenericAttribute att = it.next();
				String name = att.getName() ;
				String plName = FamaToPacoName.get(Feature.getName()) + "_" + name.toUpperCase() ;
				if(att.getDefaultValue() instanceof Integer )
				{				
					if( (Integer)att.getDefaultValue() != 0)
					{
				AttrListFamaName.add(Feature.getName() + "." + name ) ;
				AttrPrologNameList.add(plName) ;
				AttributesName.get(Feature.getName()).put(name, plName) ;
				ctrModel.add("(" +FamaToPacoName.get(Feature.getName()) + ") #= 0 #<=> ("+ plName + "#= 0)")  ;
				ctrModel.add("(" +FamaToPacoName.get(Feature.getName()) + ") #\\= 0 #<=> ("+ plName + "#\\= 0)")  ;	
				String ListName = "L" + plName + " = [0," + att.getDefaultValue() + "]"; 
				attrDomain.add(ListName) ;
				attrDomain.add("list_to_fdset(L"+ plName + ", S" +plName +")") ;
				attrDomain.add(plName + " in_set  S" +plName ) ;
		
					}
				}
				else
				{
				
					System.err.println("Pacogen only supports integer attributes");
				}
				}
			
		

	}
	
	private String getPacogenName(String FeatureName)
	{
		if(FamaToPacoName.containsKey(FeatureName))
		{
			return FamaToPacoName.get(FeatureName) ;
		}
		else{
			String PacoName = "A_" + FeatureName.toUpperCase() ;
			FamaToPacoName.put(FeatureName, PacoName ) ;
			ftList.add(PacoName) ;
			return PacoName  ; 
		}
		
		
	}
	
	private String getAttrPacoName(String Ft, String Attr)
	{
		AttrListFamaName.add(Ft + "." + Attr) ;
		String plName = "A_" + Ft.toUpperCase() + "_" + Attr.toUpperCase()  ;
		AttrPrologNameList.add(plName) ;
		return plName;
	}

	
	public void addRoot(Feature feature) {
	
		String Name =  initVariable(feature) ;
		ctrModel.add( Name + " #= 1") ;
	}

	
	public void addSet(Relation rel,
			Feature parent,
			Collection<Feature> children,
			Collection<Cardinality> cardinalities) {
		
		if(cardinalities.size() > 1)
		{
			System.err.println(" Multiple Cardinalities are not handled");
		}else {
			String FatherName = initVariable(parent) ;
			String childrenNameList = "[" ;
			for (Feature gaf : children) {
				childrenNameList += initVariable(gaf) + ","  ;
			}			
			childrenNameList = childrenNameList.substring(0, childrenNameList.length() - 1) + "]" ;			
			Cardinality card = 	(Cardinality) cardinalities.toArray()[0] ;
			if (card.getMin() == 1 && card.getMax() == children.size())
			{
				ctrModel.add("or("+ FatherName + "," +childrenNameList +")") ;
			}else if (card.getMin() == 1 && card.getMax() == 1)
			{
				ctrModel.add("xor("+ FatherName + "," +childrenNameList +")") ;
			}else 
			{
				ctrModel.add("card("+  card.getMin() + "," +card.getMax() +"," + FatherName + "," +childrenNameList +")") ;
			}
		}
		
		
	}

	
	public void addConstraint(Constraint c) {
		AstParser astp = new AstParser() ;
		ctrModel.add(astp.translateToConstraint(c.getAST()) 
);			
	}

	
	public void reset() {
		ctrModel = new LinkedList<String>() ;
		ftList = new LinkedList<String>() ;
		VarAttrList = new LinkedList<String>() ;
		FamaToPacoName = new HashMap<String,String>() ;
		attrDomain = new LinkedList<String>() ;
		AttributesName = new HashMap<String,HashMap<String, String>>() ;
		ftListFamaName = new LinkedList<String>() ;
		AttrListFamaName = new LinkedList<String>() ;
		AttrPrologNameList = new LinkedList<String>() ;

	}

	
//	public PerformanceResult ask(Question q) {
//			System.out.println(toPrologList(ctrModel));
//			System.out.println(toPrologList(attrDomain));
//			System.out.println(toPrologList(ftList));
//			System.out.println(toPrologList(ftListFamaName));
//			System.out.println(toPrologList(AttrListFamaName));
//			System.out.println(toPrologList(AttrPrologNameList));
//		
//		return null;
//	}
	
	public void buildModel() throws IOException {
		FileWriter fw = new FileWriter(new File("./model.txt")) ;
		String StrctrModel = toPrologList(ctrModel); 
		String StrattrDomain = toPrologList(attrDomain); 
		String StrftList = toPrologList(ftList); 
		String StrVarAttrName = toPrologList(VarAttrList); 
		String StrAttrPrologNameList = toPrologList(AttrPrologNameList); 

		String Output = "[reason," + StrctrModel + "," + StrftList + "," + StrattrDomain +"," + StrAttrPrologNameList +"]." ;
		fw.write(Output) ;
		fw.close() ;
	}

	public String Reason() throws IOException
	{
		domainList = new LinkedList<String>() ;
		String res = "" ;
		Runtime runtime = Runtime.getRuntime();
		Process p =	runtime.exec("./Pacogen");
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		try {
			while((line = reader.readLine()) != null) {
				res += line ;
				domainList.add(line) ;
			}
			
		} finally {
			reader.close();
		}
		
		
		return res;
		
	}
	
	public String getVarDomain()
	{
		String res = "" ;
		
		for (int i = 0 ; i < ftListFamaName.size() ; i++)
		{
			res += ftListFamaName.get(i)+ ": " + domainList.get(i) +"\n" ;
		}	
		
		for (int j = 0 ; j < AttrListFamaName.size() ; j++)
		{
			res += AttrListFamaName.get(j)+ ": " + domainList.get(ftListFamaName.size()   + j) +"\n" ;
		}

		return res ; 

	}
	
	private String toPrologList(LinkedList<String>  L)
	{
		String res = ""; 

		if(L.size() > 0)
			 {
			res = "["; 
		
			for (String string : L) {
				res += string + "," ;
			}
		res = res.substring(0,res.length() - 1) + "]" ;
			 }
		return res; 
	}
	



	protected class AstParser {
		
		private String ftName;
		
		
		public AstParser()
		{
			ftName = ""; 
		
		}
	
		
		public Constraint translateToInvariant(Tree<String> ast,
				String featInvariant) {

			return null;
		}

		public String translateToConstraint(Tree<String> ast) {
			ftName = null;
			Node<String> n = ast.getRootElement();
			String 	res = translateLogical(n);
			return res;
		}

		private String translateLogical(Node<String> tree) {
			String res = "" ;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			int n = children.size();
			if (n == 2) {
				if (data.equals(KeyWords.AND)) {
				System.err.println("Pacogen doen't handle logical relations yet");
				} else if (data.equals(KeyWords.OR)) {
					System.err.println("Pacogen doen't handle logical relations yet");
				} else if (data.equals(KeyWords.IMPLIES)
						|| data.equals(KeyWords.REQUIRES)){
					String left = translateLogical(children.get(0));
					String right = translateLogical(children.get(1));
			
					res += "#\\" + left + "#\\/" + right ;
			
				} else if (data.equals(KeyWords.IFF)) {
					System.err.println("Pacogen doen't handle logical relations yet");
				} else if (data.equals(KeyWords.EXCLUDES)) {
					String left = translateLogical(children.get(0));
					String right = translateLogical(children.get(1));
			
					res += "#\\" + left + " #\\/ #\\ " + right ;
				} else {
					 res += translateRelational(tree);
				}
			} else if (n == 1) {
				if (data.equals(KeyWords.NOT)) {
					System.err.println("Pacogen doen't handle logical relations yet");
				}
			} else {
				if (isFeature(tree)) {
					res += FamaToPacoName.get(tree.getData()) ;
				}
			}
			return res;
		}

		private String translateRelational(Node<String> tree) {

			String res = "" ;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			String left =  translateInteger(children.get(0)) ;
			String right =  translateInteger(children.get(1)) ;
			if (data.equals(KeyWords.GREATER)) {
				res += " #> " ;
			} else if (data.equals(KeyWords.GREATER_EQUAL)) {
				res += " #>= " ;
			} else if (data.equals(KeyWords.LESS)) {
				res += " #< " ;
			} else if (data.equals(KeyWords.LESS_EQUAL)) {
				res += " #=< " ;
			} else if (data.equals(KeyWords.EQUAL)) {
				res += " #= " ;
			} else if (data.equals(KeyWords.NON_EQUAL)) {
				res += " #\\= " ;
			}
			
			res = left + res + right ;
			return res;
		}

		private String translateInteger(Node<String> tree) {
	
			String Restmp = "" ;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			if (data.equals(KeyWords.PLUS)) {
				String  left = translateInteger(children.get(0));
				String  right = translateInteger(children.get(1));
				Restmp += left +" + " + right ;
			} else if (data.equals(KeyWords.MINUS)) {
				String  left = translateInteger(children.get(0));
				String  right = translateInteger(children.get(1));
				Restmp +=left +" - " + right ;
			} else if (data.equals(KeyWords.MULT)) {
				String  left = translateInteger(children.get(0));
				String  right = translateInteger(children.get(1));
				Restmp +=left +" * " + right ;
			} else if (data.equals(KeyWords.DIV)) {
				String  left = translateInteger(children.get(0));
				String  right = translateInteger(children.get(1));
				Restmp +=left +" / " + right ;
			} else if (data.equals(KeyWords.MOD)) {
				System.err.println("Pacogen doesn't handle Mod operator");
			} else if (data.equals(KeyWords.POW)) {
				System.err.println("Pacogen doesn't handle Pow operator");
			} else if (data.equals(KeyWords.UNARY_MINUS)) {
				String  left = translateInteger(children.get(0));
				Restmp += "-" + left ;
			} else if (isIntegerConstant(tree)) {
				Restmp += data ;		
			}
			else if (isAttribute(tree)) {	
				String attName = getAttributeName(tree);
				Restmp += attName ;
			}
			else {
				//TODO
				//Integer i = constantIntConverter.translate2Integer(tree.getData());
				//Restmp += i.toString() ;
			}


			
			return Restmp;
		}

		private String getAttributeName(Node<String> n) {
			String res = null;
			if (ftName == null) {
				String s = n.getData();
				boolean b = s.equals(KeyWords.ATTRIBUTE);
				if (b && (n.getNumberOfChildren() == 2)) {
					List<Node<String>> list = n.getChildren();
					res = list.get(0).getData() + "." + list.get(1).getData();
					String featureName = list.get(0).getData() ;
					String AttrName = list.get(1).getData();
					String Name = AttributesName.get(featureName).get(AttrName) ;
					if (Name == null)
					{
						Name = getAttrPacoName(featureName,AttrName) ;
					}
					res = Name ;
				}
			} else {;
			res = AttributesName.get(ftName).get(n.getData()) ;
			}

			return res;
		}

		private boolean isAttribute(Node<String> n) {
			if (ftName == null) {
				return n.getData().equals(KeyWords.ATTRIBUTE);
			} else {
				String aux = ftName + "." + n.getData();

				return false ;
			}

		}

		private boolean isFeature(Node<String> n) {
			String s = n.getData();
			return (FamaToPacoName.get(s) != null);

		}

		private boolean isIntegerConstant(Node<String> n) {
			String s = n.getData();
			try {
				Integer.parseInt(s);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		

		
		
	}






	@Override
	public void applyStagedConfiguration(Configuration conf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unapplyStagedConfigurations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConstantIntConverter(
			ConstantIntConverter constantIntConverter) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
