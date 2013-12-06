/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
header{
	package es.us.isa.FAMA.parser;    
	import java.util.*;	
	import fr.familiar.attributedfm.*;
	import fr.familiar.attributedfm.domain.*;
	
	import fr.familiar.attributedfm.util.*;
	import es.us.isa.FAMA.parser.*;
}

class FaMaTreeParser extends TreeParser;

options{
	importVocab = Anasint;
}

{
	//Map<String,AST> mapASTFeatures = null;

	Map<String,Feature> features = new HashMap<String,Feature>();
	//zona de funciones	
	
	FMFParser fmp = new FMFParser();
	
	Collection<String> errors = new LinkedList<String>();
	
	//Creamos una feature con su nombre y dominio
	public Feature createFeature(AST name, Domain d){
		String n = name.getText();
		if (features.get(n) != null){
			//ya existe la feauture, asi que esta duplicada
			errors.add("Duplicated feature detected: "+n);	
		}
		Feature f = new Feature(name.getText());
		f.setDomain(d);
		features.put(name.getText(),f);
		return f;
	}
	
	public Relation createRelation(AST relName, AST card, Collection<Feature> children){
		//TODO en teoria debe funcionar
		Relation res = new Relation(relName.getText());
		Cardinality c = getCardinality(card);
		res.addCardinality(c);
		Iterator<Feature> it = children.iterator();
		while (it.hasNext()){
			Feature f = it.next();
			res.addDestination(f);	
		}
		return res;	
	}
	
	//aadimos a una feature todas sus relaciones
	public void addRelations(Feature f, Collection<Relation> rels){
		Iterator<Relation> it = rels.iterator();
		while (it.hasNext()){
			Relation r = it.next();
			f.addRelation(r);	
			//al aadir la relacion, a esta se le pone como feature padre la feature actual
		}	
	}
	
	public Cardinality getCardinality(AST t){
		String aux = t.getFirstChild().getText();
		int min = Integer.parseInt(aux);
		aux = t.getFirstChild().getNextSibling().getText();
		int max = Integer.parseInt(aux);
		Cardinality res = new Cardinality(min,max);
		return res;	
	}
	
	public TreeParserResult createFeatureModel(Feature root, Collection<Constraint> cons){
		TreeParserResult res;
		AttributedFeatureModel fm = new AttributedFeatureModel(root);
		Iterator<Constraint> it = cons.iterator();
		//TODO
		//aadir metodos a feature model para poder aadir todas las dependencias
		//del tiron, y lo mismo para las relaciones y las features
		while (it.hasNext()){
			Constraint d = it.next();
			fm.addConstraint(d);	
		}
		res = new TreeParserResult(fm,errors);
		return res;
	}
	
	public ExcludesDependency createExcludes(AST relName,AST f1, AST f2){
		//TODO
		Feature feat1 = features.get(f1.getText());
		Feature feat2 = features.get(f2.getText());
		ExcludesDependency res = new ExcludesDependency(relName.getText(),feat1,feat2);
		return res;	
	}
	
	public RequiresDependency createRequires(AST relName,AST f1, AST f2){
		//TODO
		Feature feat1 = features.get(f1.getText());
		Feature feat2 = features.get(f2.getText());
		RequiresDependency res = new RequiresDependency(relName.getText(),feat1,feat2);
		return res;	
	}
	
	public Constraint ASTtoConstraint(AST t, AST name){
		//TODO checkear que funciona bien
		String n = name.getText();
		Tree<String> tree = fmp.astToTree(t);
		Constraint res;
		if (t.getType() == EXCLUDES){
			AST f1 = t.getFirstChild();
			AST f2 = t.getFirstChild().getNextSibling();
			res = createExcludes(name,f1,f2);
		}
		else if (t.getType() == REQUIRES){
			AST f1 = t.getFirstChild();
			AST f2 = t.getFirstChild().getNextSibling();
			res = createRequires(name,f1,f2);
		}
		else{
			res = new ComplexConstraint(tree);
			res.setName(n);
		}
		return res;	
	}
	
	public Domain createEnumeratedDomain(Collection<Object> c){
		Domain d;
		Iterator<Object> it = c.iterator();
		if (it.hasNext()){
			Object aux = it.next();
			if (aux instanceof Integer){
				SetIntegerDomain auxDomain = new SetIntegerDomain();
				d = new SetIntegerDomain();
				Integer i = (Integer)aux;
				auxDomain.addValue(i);
				while (it.hasNext()){
					aux = it.next();
					if (aux instanceof Integer){
						i = (Integer)aux;
						auxDomain.addValue(i);
					}
					else{
						throw new IllegalStateException("Different types on the attribute domain");	
					}
				}
				d = auxDomain;
			}
			else{
				ObjectDomain auxDomain = new ObjectDomain();
				//d = new ObjectDomain();
				auxDomain.addValue(aux);
				while (it.hasNext()){
					aux = it.next();
					auxDomain.addValue(aux);
				}
				d = auxDomain;
			}
		}
		else{
			d = new SetIntegerDomain();
		}
		return d;
	}
	
	public Range createRange(AST min, AST max){
		int minimo = Integer.parseInt(min.getText());
		int maximo = Integer.parseInt(max.getText());
		Range res = new Range (minimo,maximo);
		return res;	
	}
	
	public Integer astToInteger(AST t){
		Integer res = new Integer(t.getText());
		return res;	
	}
	
	public Float astToFloat(AST t){
		Float res = new Float(t.getText());
		return res;	
	}
	
	public void addAttributes(Feature f, Collection<GenericAttribute> atts){
		f.addAttributes(atts);
	}
	
	public void addInvariants(Feature f, Collection<Constraint> cons){
		f.addUncheckedInvariants(cons);	
	}
}

entrada returns [TreeParserResult res = null;] 
	{Feature root; Collection<Constraint> cons = new LinkedList<Constraint>();}: 
			//{mapASTFeatures = mapFeats;}
			#(FEATURE_MODEL root = seccion_rels (cons = seccion_cons)?) //regla
			{res = createFeatureModel(root,cons);};
			
seccion_rels returns [Feature root = null;]: #(SECCION_RELACIONES root=feature);

feature returns [Feature feat = null;] 
{Collection<Relation> rels;Collection<GenericAttribute> atts;
Collection<Constraint> invs;Domain d;} : 
		#(FEATURE f:IDENT d = dom {feat = createFeature(f,d);} 
		atts = atributos {addAttributes(feat,atts);} 
		rels = relaciones {addRelations(feat,rels);} 
		invs = invariantes {addInvariants(feat,invs);});

dom returns [RangeIntegerDomain d = new RangeIntegerDomain();]{Range r;}: 
	#(DOMINIO min:LIT_ENTERO max:LIT_ENTERO 
	{r = createRange(min,max);
	d.addRange(r);});

atributos returns [Collection<GenericAttribute> atts = new LinkedList<GenericAttribute>()]
{GenericAttribute aux;}:
 #(ATRIBUTOS (aux = atributo {atts.add(aux);})*)
 ;
 
//el dominio de una feature es diferente al de un atributo
atributo returns [GenericAttribute att = null;]{Domain d;Object defVal, nullVal;}: 
	#(ATRIBUTO n:IDENT d = dominio_att defVal = default_value nullVal = null_value)
	{att = new GenericAttribute(n.getText(),d,nullVal,defVal);};

dominio_att returns [Domain d = null;]: #(DOMINIO (d = dominio_rango | d = dominio_enumerado));

dominio_rango returns [Domain d = null]{Collection<Range> ranges;}: 
	#(INTEGER ranges = rangos){d = new RangeIntegerDomain(ranges);};

rangos returns [Collection<Range> ranges = new HashSet<Range>();]{Range aux = null;}: 
	#(RANGOS (aux = rango {ranges.add(aux);})+ );

rango returns [Range r = null;]: #(RANGO min:LIT_ENTERO max:LIT_ENTERO) {r = createRange(min,max);};

dominio_enumerado returns [Domain d = null;]
{Collection<Object> c = new LinkedList<Object>();Object aux;}: 
    #(ENUM #(VALORES (aux = valor {c.add(aux);})+ ) ){d = createEnumeratedDomain(c);};

//si es un entero, convertirlo en un Integer
default_value returns [Object o = null;]: #(DEF_VALUE o = valor);

null_value returns [Object o = null;]: #(NULL_VALUE o = valor);

valor returns [Object o = null;]: (i:LIT_ENTERO {o = astToInteger(i);} 
					      | r:LIT_REAL {o = astToFloat(r);} 
					      | s:LIT_STRING {o = s.getText();});

relaciones returns [Collection<Relation> rels = new LinkedList<Relation>();] {Relation aux;}: 
	#(RELACIONES (aux = relacion
	{rels.add(aux);}
	)*);

relacion returns [Relation r = null;] {Collection<Feature> children;}:
		 #(RELACION n:IDENT c:card children=features)
		 {r = createRelation(n,c,children);};
		 
invariantes returns [Collection<Constraint> invs = new LinkedList<Constraint>();]
{Constraint aux;}:
 #(INVARIANTES (aux = constraint {invs.add(aux);})*);

//aqui debemos usar la traduccion de AST al arbol de FaMa
//invariante returns [Constraint inv = null;]: #(INVARIANTE inv = expr);

constraint returns [Constraint c = null;]: #(CONSTRAINT n:IDENT e:expresion {c = ASTtoConstraint(e,n);});

//expr returns [Constraint c = null;]: e:expresion {c = ASTtoConstraint(e);};

expresion: #(IFF expresion expresion)
	 | #(IMPLIES expresion expresion)
	 | #(EXCLUDES expresion expresion)
	 | #(REQUIRES expresion expresion)
	 | #(OR expresion expresion)
	 | #(AND expresion expresion)
	 | #(NOT expresion)
	 | #(MAYOR expresion expresion)
	 | #(MENOR expresion expresion)
	 | #(MAYOR_IGUAL expresion expresion)
	 | #(MENOR_IGUAL expresion expresion)
	 | #(IGUAL expresion expresion)
	 | #(DISTINTO expresion expresion)
	 | #(MAS expresion expresion)
	 | #(MENOS expresion expresion)
	 | #(MULT expresion expresion)
	 | #(DIV expresion expresion)
	 | #(MOD expresion expresion)
	 | #(POW expresion expresion)
	 | #(MENOS_UNARIO expresion)
	 | valor
	 | IDENT
	 | id_att
	 ;
	 
id_att: #(ATRIBUTO IDENT IDENT);

features returns [Collection<Feature> feats = new LinkedList<Feature>();] {Feature aux;}: 
	#(FEATURES (aux=feature {feats.add(aux);} )*);

card: #(CARDINALIDAD LIT_ENTERO LIT_ENTERO);

seccion_cons returns [Collection<Constraint> res = new LinkedList<Constraint>();]
	{Constraint aux;}:
		 #(CONSTRAINTS (aux=constraint {res.add(aux);})*);

//restriccion returns [Constraint c = null;]: c = expr;

//restriccion returns [Dependency d = null;]:  (d = exclusion | d = inclusion);

//TODO hay que terminar esta parte, y posiblemente, aadirle un nombre a las dependencias
//exclusion returns [ExcludesDependency d = null;]: 
//	#(EXCLUDES n:IDENT f1:IDENT f2:IDENT){d = createExcludes(n,f1,f2);};

//inclusion returns [RequiresDependency d = null;]:
//	 #(IMPLIES n:IDENT f1:IDENT f2:IDENT){d = createRequires(n,f1,f2);};
