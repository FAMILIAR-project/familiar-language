// $ANTLR : "Anasint.g" -> "Anasint.java"$

	package es.us.isa.FAMA.parser;       
	import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.collections.impl.ASTArray;
import antlr.collections.impl.BitSet;

public class Anasint extends antlr.LLkParser       implements AnasintTokenTypes
 {



//seran necesarias estructuras de datos, pues en primer lugar
//construimos el modelo sin atributos, y luego se los insertamos

//en el propio anasint realizaremos las resoluciones de nombres
//que sean necesarias (sobre todo en declaraciones de atributos y CTC)

	//map con todas las features declaradas hasta el momento, mapeadas por nombre
	Map<String,AST> mapFeatures = new HashMap<String,AST>();
	
	//map feature (string) -> atributos (collection<atributo>)
	Map<String,Collection<Atributo>> attributes = new HashMap<String,Collection<Atributo>>();
	
	//contador para ir nombrando las relaciones
	int contRels = 1;
	
	//cadena para ir nombrando las relaciones
	String relConst = "rel-";
	
	//errores :D
	Collection<String> errors = new LinkedList<String>();
	
	//flag usado en la definicion de invariantes (un ident dentro de una
	//invariante no es una feature, sino un atributo)
	boolean flag = true;
	
	//al procesar un conjunto de invariantes, feature a la que pertenecen estas
	String currentFeature;
	
	public AST AST_feature(AST f){
		String featName = f.getText();
		AST declaracion_feature = mapFeatures.get(featName);
		if (declaracion_feature == null){
			AST dominio = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(DOMINIO,"Domain")).add(astFactory.create(LIT_ENTERO,"0")).add(astFactory.create(LIT_ENTERO,"1")));
			AST atributos = astFactory.create(ATRIBUTOS,"Attributes");
			AST relaciones = astFactory.create(RELACIONES,"Relationships");
			AST invariantes = astFactory.create(INVARIANTES,"Invariants");
			declaracion_feature = (AST)astFactory.make( (new ASTArray(6)).add(astFactory.create(FEATURE,"Feature")).add(f).add(dominio).add(atributos).add(relaciones).add(invariantes));
			mapFeatures.put(featName,declaracion_feature);
		}
		//else {
		//	errors.add("Duplicated feature detected: "+featName);	
		//}	
		return declaracion_feature;
	}

	public AST AST_feature_relacion(AST dec_feat, AST relaciones){
		dec_feat.getFirstChild().getNextSibling().getNextSibling().getNextSibling().setFirstChild(relaciones);
		return dec_feat;
	}
	
	public AST AST_relacion_simple_card(AST cardinality, AST feature){
		//TODO realizar comprobacion de que el rango de la cardinalidad esta entre 0 y 1
		AST relName = nextRelationName();
		AST rel = (AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(RELACION,"Relationship")).add(relName).add(cardinality).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FEATURES,"Features")).add(feature))));
		return rel;
	}
	
	public AST AST_relacion_grupo(AST cardinality, AST lista_features){
		AST relName = nextRelationName();
		AST rel = (AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(RELACION,"Relationship")).add(relName).add(cardinality).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FEATURES,"Features")).add(lista_features))));
		return rel;
	}
	
	public AST AST_relacion_binaria(AST feature, int min, int max){
		AST auxMin = astFactory.create(LIT_ENTERO,String.valueOf(min));
		AST auxMax = astFactory.create(LIT_ENTERO,String.valueOf(max));
		AST card = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(CARDINALIDAD,"Cardinality")).add(auxMin).add(auxMax));
		AST res = AST_relacion_simple_card(card,feature);
		return res;
	}
	
	public AST nextRelationName(){
		String aux = relConst+contRels;
		AST relName = astFactory.create(IDENT,aux);
		contRels++;
		return relName;
	}
	
	public void addAttribute(AST feature, AST att){
		String fName = feature.getText();
		AST f = mapFeatures.get(fName);
		if (f != null){
			//AST rels = f.getFirstChild().getNextSibling().getNextSibling();
			//att.setNextSibling(rels);
			//f.getFirstChild().getNextSibling().setNextSibling(att);
			AST atts = f.getFirstChild().getNextSibling().getNextSibling();
			atts.addChild(att);
			String nombreAtt = att.getFirstChild().getText();
			AST tipoAtt = att.getFirstChild().getNextSibling().getFirstChild();
			Atributo a;
			if (tipoAtt.getType() == INTEGER){
				a = new Atributo(nombreAtt,Atributo.ENTERO);	
			}
			else{
				a = new Atributo(nombreAtt,Atributo.ENUMERADO);
			}
			Collection<Atributo> currentAtts = attributes.get(fName);
			if (currentAtts == null){
				currentAtts = new ArrayList<Atributo>();
			
			}
			currentAtts.add(a);
			attributes.put(fName,currentAtts);
		}
		else{
			String error = "Error at line "+feature.getLine()+": feature "+fName+" does not exist";
			errors.add(error);
		}
		
	}
	
	public void checkFeatures(AST... features){
		for (int i = 0; i < features.length; i++){
			String featName = features[i].getText();
			if (!existsFeature(featName)){
				String error = "Error at line "+features[i].getLine()+
					": feature "+featName+" does not exist";
				errors.add(error);
			}
		}
	}
	
	public boolean existsFeature(String f){
		return (mapFeatures.get(f) != null);
	}
	
	public AST createDependency(AST rel, AST f1, AST f2){
		//String aux = relConst+contRels;
		//contRels++;
		//AST nombre = #[IDENT,aux];
		//AST nombre = nextRelationName();
		AST aux = (AST)astFactory.make( (new ASTArray(3)).add(rel).add(f1).add(f2));
		AST res = constraintExpresion(aux);
		return res;	
	}
	
	public void setInvariantes(AST f, AST invs){
		String fName = f.getText();
		AST feature = mapFeatures.get(fName);
		if (feature != null){
			AST i = feature.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
		i.addChild(invs);
		}
		else{
			int line = f.getLine();
			String error = "Error at line "+line+": feature "+fName+" is not declared";
		}
		
	}
	
	
	public void resuelveNombre(AST t){
		//t puede ser feature (flag = true) o atributo (flag = false, si se
		//esta definiendo una invariante)
		//en caso de ser atributo, usamos currentFeature (tiene la feature
		//actual que esta definiendo invariantes)
		String s = t.getText();
		int line = t.getLine();
		if (flag){
			AST dec = mapFeatures.get(s);
			if (dec == null){
				//error, feature no declarada
				errors.add("Error at line "+line+": feature "+s+" is not declared");	
			}
		}
		else{
			if (!existsAtt(currentFeature,s)){
				errors.add("Error at line "+line+": attribute "+currentFeature+"."+s+" is not declared");	
			}	
		}
	}
	
	 // Cambiar tambien el tree parser
	
	public AST constraintExpresion(AST e){
		//TODO
		AST name = nextRelationName();
		AST res =  (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(CONSTRAINT,"Constraint")).add(name).add(e));
		return res;
	}
	
	public AST invarianteExpresion(AST e){
		//TODO
		AST name = nextRelationName();
		AST res =  (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(INVARIANTE,"Invariant")).add(name).add(e));
		return res;	
	}
	
	public void resuelveNombreAtributo(AST att){
		String fName = att.getFirstChild().getText();
		String attName = att.getFirstChild().getNextSibling().getText();
		int line = att.getFirstChild().getLine();
		if (mapFeatures.get(fName) == null){
			errors.add("Error at line "+line+": feature "+fName+" is not declared");	
		}
		if (!existsAtt(fName,attName)){
			errors.add("Error at line "+line+": attribute "+fName+"."+attName+" is not declared");	
		}	
	}
	
	public boolean existsAtt(String f, String a){
		Collection<Atributo> atts = attributes.get(f);
		if (atts != null){
			Atributo aux = new Atributo(a,0);//atributo espureo, para simplificar la busqueda
			return atts.contains(aux);	
		}
		return false;
	}
		

protected Anasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected Anasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Anasint(TokenStream lexer) {
  this(lexer,2);
}

public Anasint(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final Collection<String>  entrada() throws RecognitionException, TokenStreamException {
		Collection<String> e = null;;
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST entrada_AST = null;
		
		try {      // for error handling
			conjunto_relaciones();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case SECCION_ATRIBUTOS:
			{
				conjunto_atributos();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SECCION_CONSTRAINTS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case SECCION_CONSTRAINTS:
			{
				conjunto_constraints();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(Token.EOF_TYPE);
			if ( inputState.guessing==0 ) {
				entrada_AST = (AST)currentAST.root;
				entrada_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FEATURE_MODEL,"Feature Model")).add(entrada_AST));
					//feats = mapFeatures;
					e = errors;
				currentAST.root = entrada_AST;
				currentAST.child = entrada_AST!=null &&entrada_AST.getFirstChild()!=null ?
					entrada_AST.getFirstChild() : entrada_AST;
				currentAST.advanceChildToEnd();
			}
			entrada_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = entrada_AST;
		return e;
	}
	
	public final void conjunto_relaciones() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conjunto_relaciones_AST = null;
		
		try {      // for error handling
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp2_AST);
			match(SECCION_RELACIONES);
			declaraciones_feature();
			astFactory.addASTChild(currentAST, returnAST);
			conjunto_relaciones_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = conjunto_relaciones_AST;
	}
	
	public final void conjunto_atributos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conjunto_atributos_AST = null;
		
		try {      // for error handling
			AST tmp3_AST = null;
			tmp3_AST = astFactory.create(LT(1));
			match(SECCION_ATRIBUTOS);
			{
			int _cnt478=0;
			_loop478:
			do {
				if ((LA(1)==IDENT)) {
					declaracion_atributo();
				}
				else {
					if ( _cnt478>=1 ) { break _loop478; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt478++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = conjunto_atributos_AST;
	}
	
	public final void conjunto_constraints() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conjunto_constraints_AST = null;
		AST cons_AST = null;
		
		try {      // for error handling
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp4_AST);
			match(SECCION_CONSTRAINTS);
			lista_constraints();
			cons_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				conjunto_constraints_AST = (AST)currentAST.root;
				conjunto_constraints_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(CONSTRAINTS,"Constraints")).add(cons_AST));
				currentAST.root = conjunto_constraints_AST;
				currentAST.child = conjunto_constraints_AST!=null &&conjunto_constraints_AST.getFirstChild()!=null ?
					conjunto_constraints_AST.getFirstChild() : conjunto_constraints_AST;
				currentAST.advanceChildToEnd();
			}
			conjunto_constraints_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = conjunto_constraints_AST;
	}
	
	public final void declaraciones_feature() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaraciones_feature_AST = null;
		AST r_AST = null;
		
		try {      // for error handling
			declaracion_feature();
			r_AST = (AST)returnAST;
			{
			_loop459:
			do {
				if ((LA(1)==IDENT)) {
					declaracion_feature();
				}
				else {
					break _loop459;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				declaraciones_feature_AST = (AST)currentAST.root;
				declaraciones_feature_AST = r_AST;
				currentAST.root = declaraciones_feature_AST;
				currentAST.child = declaraciones_feature_AST!=null &&declaraciones_feature_AST.getFirstChild()!=null ?
					declaraciones_feature_AST.getFirstChild() : declaraciones_feature_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = declaraciones_feature_AST;
	}
	
	public final void declaracion_feature() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_feature_AST = null;
		AST f_AST = null;
		AST r_AST = null;
		
		try {      // for error handling
			feature();
			f_AST = (AST)returnAST;
			match(DOSPUNTOS);
			lista_relaciones();
			r_AST = (AST)returnAST;
			match(PyC);
			if ( inputState.guessing==0 ) {
				declaracion_feature_AST = (AST)currentAST.root;
				declaracion_feature_AST = AST_feature_relacion(f_AST,r_AST);
				currentAST.root = declaracion_feature_AST;
				currentAST.child = declaracion_feature_AST!=null &&declaracion_feature_AST.getFirstChild()!=null ?
					declaracion_feature_AST.getFirstChild() : declaracion_feature_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_feature_AST;
	}
	
	public final void feature() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST feature_AST = null;
		Token  f = null;
		AST f_AST = null;
		
		try {      // for error handling
			f = LT(1);
			f_AST = astFactory.create(f);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				feature_AST = (AST)currentAST.root;
				feature_AST = AST_feature(f_AST);
				currentAST.root = feature_AST;
				currentAST.child = feature_AST!=null &&feature_AST.getFirstChild()!=null ?
					feature_AST.getFirstChild() : feature_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = feature_AST;
	}
	
	public final void lista_relaciones() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lista_relaciones_AST = null;
		
		try {      // for error handling
			{
			int _cnt464=0;
			_loop464:
			do {
				if ((LA(1)==IDENT||LA(1)==CORCHETE_ABRIR)) {
					relacion();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt464>=1 ) { break _loop464; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt464++;
			} while (true);
			}
			lista_relaciones_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = lista_relaciones_AST;
	}
	
	public final void relacion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST relacion_AST = null;
		AST r_AST = null;
		AST mf_AST = null;
		AST of_AST = null;
		
		try {      // for error handling
			boolean synPredMatched467 = false;
			if (((LA(1)==CORCHETE_ABRIR) && (LA(2)==LIT_ENTERO))) {
				int _m467 = mark();
				synPredMatched467 = true;
				inputState.guessing++;
				try {
					{
					cardinalidad();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched467 = false;
				}
				rewind(_m467);
inputState.guessing--;
			}
			if ( synPredMatched467 ) {
				relacion_cardinalidad();
				r_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					relacion_AST = (AST)currentAST.root;
					relacion_AST = r_AST;
					currentAST.root = relacion_AST;
					currentAST.child = relacion_AST!=null &&relacion_AST.getFirstChild()!=null ?
						relacion_AST.getFirstChild() : relacion_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else if ((LA(1)==IDENT)) {
				mandatory_feature();
				mf_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					relacion_AST = (AST)currentAST.root;
					relacion_AST = AST_relacion_binaria(mf_AST,1,1);
					currentAST.root = relacion_AST;
					currentAST.child = relacion_AST!=null &&relacion_AST.getFirstChild()!=null ?
						relacion_AST.getFirstChild() : relacion_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else if ((LA(1)==CORCHETE_ABRIR) && (LA(2)==IDENT)) {
				optional_feature();
				of_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					relacion_AST = (AST)currentAST.root;
					relacion_AST = AST_relacion_binaria(of_AST,0,1);
					currentAST.root = relacion_AST;
					currentAST.child = relacion_AST!=null &&relacion_AST.getFirstChild()!=null ?
						relacion_AST.getFirstChild() : relacion_AST;
					currentAST.advanceChildToEnd();
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = relacion_AST;
	}
	
	public final void cardinalidad() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cardinalidad_AST = null;
		Token  min = null;
		AST min_AST = null;
		Token  max = null;
		AST max_AST = null;
		
		try {      // for error handling
			match(CORCHETE_ABRIR);
			min = LT(1);
			min_AST = astFactory.create(min);
			match(LIT_ENTERO);
			match(COMA);
			max = LT(1);
			max_AST = astFactory.create(max);
			match(LIT_ENTERO);
			match(CORCHETE_CERRAR);
			if ( inputState.guessing==0 ) {
				cardinalidad_AST = (AST)currentAST.root;
				cardinalidad_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(CARDINALIDAD,"Cardinality")).add(astFactory.create(min_AST)).add(astFactory.create(max_AST)));
				currentAST.root = cardinalidad_AST;
				currentAST.child = cardinalidad_AST!=null &&cardinalidad_AST.getFirstChild()!=null ?
					cardinalidad_AST.getFirstChild() : cardinalidad_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = cardinalidad_AST;
	}
	
	public final void relacion_cardinalidad() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST relacion_cardinalidad_AST = null;
		AST card_AST = null;
		AST f_AST = null;
		AST l_AST = null;
		
		try {      // for error handling
			cardinalidad();
			card_AST = (AST)returnAST;
			{
			switch ( LA(1)) {
			case IDENT:
			{
				feature();
				f_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					relacion_cardinalidad_AST = (AST)currentAST.root;
					relacion_cardinalidad_AST = AST_relacion_simple_card(card_AST,f_AST);
					currentAST.root = relacion_cardinalidad_AST;
					currentAST.child = relacion_cardinalidad_AST!=null &&relacion_cardinalidad_AST.getFirstChild()!=null ?
						relacion_cardinalidad_AST.getFirstChild() : relacion_cardinalidad_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case LLAVE_ABRIR:
			{
				lista_features();
				l_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					relacion_cardinalidad_AST = (AST)currentAST.root;
					relacion_cardinalidad_AST = AST_relacion_grupo(card_AST,l_AST);
					currentAST.root = relacion_cardinalidad_AST;
					currentAST.child = relacion_cardinalidad_AST!=null &&relacion_cardinalidad_AST.getFirstChild()!=null ?
						relacion_cardinalidad_AST.getFirstChild() : relacion_cardinalidad_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = relacion_cardinalidad_AST;
	}
	
	public final void mandatory_feature() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST mandatory_feature_AST = null;
		
		try {      // for error handling
			feature();
			astFactory.addASTChild(currentAST, returnAST);
			mandatory_feature_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = mandatory_feature_AST;
	}
	
	public final void optional_feature() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST optional_feature_AST = null;
		
		try {      // for error handling
			match(CORCHETE_ABRIR);
			feature();
			astFactory.addASTChild(currentAST, returnAST);
			match(CORCHETE_CERRAR);
			optional_feature_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = optional_feature_AST;
	}
	
	public final void lista_features() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lista_features_AST = null;
		
		try {      // for error handling
			match(LLAVE_ABRIR);
			feature();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt475=0;
			_loop475:
			do {
				if ((LA(1)==IDENT)) {
					feature();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt475>=1 ) { break _loop475; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt475++;
			} while (true);
			}
			match(LLAVE_CERRAR);
			lista_features_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = lista_features_AST;
	}
	
	public final void declaracion_atributo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_atributo_AST = null;
		Token  f = null;
		AST f_AST = null;
		Token  att = null;
		AST att_AST = null;
		AST d_AST = null;
		AST dv_AST = null;
		AST nv_AST = null;
		AST aux;
		
		try {      // for error handling
			f = LT(1);
			f_AST = astFactory.create(f);
			match(IDENT);
			match(PUNTO);
			att = LT(1);
			att_AST = astFactory.create(att);
			match(IDENT);
			match(DOSPUNTOS);
			dominio_att();
			d_AST = (AST)returnAST;
			match(COMA);
			defaultValue();
			dv_AST = (AST)returnAST;
			match(COMA);
			nullValue();
			nv_AST = (AST)returnAST;
			match(PyC);
			if ( inputState.guessing==0 ) {
				aux = (AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(ATRIBUTO,"Attribute")).add(astFactory.create(att_AST)).add(d_AST).add(dv_AST).add(nv_AST));
					addAttribute(f_AST,aux);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_atributo_AST;
	}
	
	public final void dominio_att() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dominio_att_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case INTEGER:
			{
				tipo();
				astFactory.addASTChild(currentAST, returnAST);
				dominio_att_AST = (AST)currentAST.root;
				break;
			}
			case CORCHETE_ABRIR:
			{
				enumerado();
				astFactory.addASTChild(currentAST, returnAST);
				dominio_att_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = dominio_att_AST;
	}
	
	public final void defaultValue() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST defaultValue_AST = null;
		AST l_AST = null;
		
		try {      // for error handling
			literal();
			l_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				defaultValue_AST = (AST)currentAST.root;
				defaultValue_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DEF_VALUE,"DefaultValue")).add(l_AST));
				currentAST.root = defaultValue_AST;
				currentAST.child = defaultValue_AST!=null &&defaultValue_AST.getFirstChild()!=null ?
					defaultValue_AST.getFirstChild() : defaultValue_AST;
				currentAST.advanceChildToEnd();
			}
			defaultValue_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = defaultValue_AST;
	}
	
	public final void nullValue() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST nullValue_AST = null;
		AST l_AST = null;
		
		try {      // for error handling
			literal();
			l_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				nullValue_AST = (AST)currentAST.root;
				nullValue_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NULL_VALUE,"NullValue")).add(l_AST));
				currentAST.root = nullValue_AST;
				currentAST.child = nullValue_AST!=null &&nullValue_AST.getFirstChild()!=null ?
					nullValue_AST.getFirstChild() : nullValue_AST;
				currentAST.advanceChildToEnd();
			}
			nullValue_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = nullValue_AST;
	}
	
	public final void tipo() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tipo_AST = null;
		Token  i = null;
		AST i_AST = null;
		AST r_AST = null;
		
		try {      // for error handling
			i = LT(1);
			i_AST = astFactory.create(i);
			astFactory.addASTChild(currentAST, i_AST);
			match(INTEGER);
			rangos();
			r_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				tipo_AST = (AST)currentAST.root;
				tipo_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DOMINIO,"Dominio")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(i_AST)).add(r_AST))));
				currentAST.root = tipo_AST;
				currentAST.child = tipo_AST!=null &&tipo_AST.getFirstChild()!=null ?
					tipo_AST.getFirstChild() : tipo_AST;
				currentAST.advanceChildToEnd();
			}
			tipo_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = tipo_AST;
	}
	
	public final void enumerado() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST enumerado_AST = null;
		AST lista_AST = null;
		
		try {      // for error handling
			match(CORCHETE_ABRIR);
			lista_enum();
			lista_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			match(CORCHETE_CERRAR);
			if ( inputState.guessing==0 ) {
				enumerado_AST = (AST)currentAST.root;
				enumerado_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DOMINIO,"Dominio")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ENUM,"Enum")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(VALORES,"Valores")).add(lista_AST))))));
				currentAST.root = enumerado_AST;
				currentAST.child = enumerado_AST!=null &&enumerado_AST.getFirstChild()!=null ?
					enumerado_AST.getFirstChild() : enumerado_AST;
				currentAST.advanceChildToEnd();
			}
			enumerado_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = enumerado_AST;
	}
	
	public final void rangos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rangos_AST = null;
		
		try {      // for error handling
			{
			int _cnt484=0;
			_loop484:
			do {
				if ((LA(1)==CORCHETE_ABRIR)) {
					rango();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt484>=1 ) { break _loop484; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt484++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				rangos_AST = (AST)currentAST.root;
				rangos_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(RANGOS,"Ranges")).add(rangos_AST));
				currentAST.root = rangos_AST;
				currentAST.child = rangos_AST!=null &&rangos_AST.getFirstChild()!=null ?
					rangos_AST.getFirstChild() : rangos_AST;
				currentAST.advanceChildToEnd();
			}
			rangos_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = rangos_AST;
	}
	
	public final void rango() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rango_AST = null;
		Token  min = null;
		AST min_AST = null;
		Token  max = null;
		AST max_AST = null;
		
		try {      // for error handling
			match(CORCHETE_ABRIR);
			min = LT(1);
			min_AST = astFactory.create(min);
			match(LIT_ENTERO);
			match(TO);
			max = LT(1);
			max_AST = astFactory.create(max);
			match(LIT_ENTERO);
			match(CORCHETE_CERRAR);
			if ( inputState.guessing==0 ) {
				rango_AST = (AST)currentAST.root;
				rango_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(RANGO,"Range")).add(min_AST).add(max_AST));
				currentAST.root = rango_AST;
				currentAST.child = rango_AST!=null &&rango_AST.getFirstChild()!=null ?
					rango_AST.getFirstChild() : rango_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = rango_AST;
	}
	
	public final void lista_enum() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lista_enum_AST = null;
		
		try {      // for error handling
			literal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop489:
			do {
				if ((LA(1)==COMA)) {
					match(COMA);
					literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop489;
				}
				
			} while (true);
			}
			lista_enum_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = lista_enum_AST;
	}
	
	public final void literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST literal_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LIT_REAL:
			{
				AST tmp25_AST = null;
				tmp25_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp25_AST);
				match(LIT_REAL);
				literal_AST = (AST)currentAST.root;
				break;
			}
			case LIT_ENTERO:
			{
				AST tmp26_AST = null;
				tmp26_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp26_AST);
				match(LIT_ENTERO);
				literal_AST = (AST)currentAST.root;
				break;
			}
			case LIT_STRING:
			{
				AST tmp27_AST = null;
				tmp27_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp27_AST);
				match(LIT_STRING);
				literal_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = literal_AST;
	}
	
	public final void lista_constraints() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lista_constraints_AST = null;
		
		try {      // for error handling
			{
			int _cnt496=0;
			_loop496:
			do {
				if ((_tokenSet_13.member(LA(1)))) {
					constraint();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt496>=1 ) { break _loop496; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt496++;
			} while (true);
			}
			lista_constraints_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = lista_constraints_AST;
	}
	
	public final void constraint() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constraint_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==IDENT) && (LA(2)==LLAVE_ABRIR)) {
				declaracion_invariantes();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_13.member(LA(1))) && (_tokenSet_14.member(LA(2)))) {
				declaracion_expresion();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==IDENT) && (LA(2)==EXCLUDES||LA(2)==REQUIRES)) {
				dependencia_simple();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			constraint_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = constraint_AST;
	}
	
	public final void declaracion_invariantes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_invariantes_AST = null;
		Token  f = null;
		AST f_AST = null;
		AST invs_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				flag = false;
			}
			f = LT(1);
			f_AST = astFactory.create(f);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				currentFeature = f.getText();
			}
			match(LLAVE_ABRIR);
			lista_invariantes();
			invs_AST = (AST)returnAST;
			match(LLAVE_CERRAR);
			if ( inputState.guessing==0 ) {
				setInvariantes(f_AST,invs_AST);flag=true;
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_invariantes_AST;
	}
	
	public final void declaracion_expresion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_expresion_AST = null;
		AST e_AST = null;
		
		try {      // for error handling
			expresion();
			e_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			match(PyC);
			if ( inputState.guessing==0 ) {
				declaracion_expresion_AST = (AST)currentAST.root;
				declaracion_expresion_AST = constraintExpresion(e_AST);
				currentAST.root = declaracion_expresion_AST;
				currentAST.child = declaracion_expresion_AST!=null &&declaracion_expresion_AST.getFirstChild()!=null ?
					declaracion_expresion_AST.getFirstChild() : declaracion_expresion_AST;
				currentAST.advanceChildToEnd();
			}
			declaracion_expresion_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_expresion_AST;
	}
	
	public final void dependencia_simple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dependencia_simple_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==IDENT) && (LA(2)==EXCLUDES)) {
				exclusion();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==IDENT) && (LA(2)==REQUIRES)) {
				inclusion();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(PyC);
			dependencia_simple_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = dependencia_simple_AST;
	}
	
	public final void exclusion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exclusion_AST = null;
		Token  f1 = null;
		AST f1_AST = null;
		Token  d = null;
		AST d_AST = null;
		Token  f2 = null;
		AST f2_AST = null;
		
		try {      // for error handling
			f1 = LT(1);
			f1_AST = astFactory.create(f1);
			match(IDENT);
			d = LT(1);
			d_AST = astFactory.create(d);
			match(EXCLUDES);
			f2 = LT(1);
			f2_AST = astFactory.create(f2);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				exclusion_AST = (AST)currentAST.root;
				checkFeatures(f1_AST,f2_AST);
														exclusion_AST = createDependency(d_AST,f1_AST,f2_AST);
				currentAST.root = exclusion_AST;
				currentAST.child = exclusion_AST!=null &&exclusion_AST.getFirstChild()!=null ?
					exclusion_AST.getFirstChild() : exclusion_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = exclusion_AST;
	}
	
	public final void inclusion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST inclusion_AST = null;
		Token  f1 = null;
		AST f1_AST = null;
		Token  d = null;
		AST d_AST = null;
		Token  f2 = null;
		AST f2_AST = null;
		
		try {      // for error handling
			f1 = LT(1);
			f1_AST = astFactory.create(f1);
			match(IDENT);
			d = LT(1);
			d_AST = astFactory.create(d);
			match(REQUIRES);
			f2 = LT(1);
			f2_AST = astFactory.create(f2);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				inclusion_AST = (AST)currentAST.root;
				checkFeatures(f1_AST,f2_AST);
														 inclusion_AST = createDependency(d_AST,f1_AST,f2_AST);
				currentAST.root = inclusion_AST;
				currentAST.child = inclusion_AST!=null &&inclusion_AST.getFirstChild()!=null ?
					inclusion_AST.getFirstChild() : inclusion_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = inclusion_AST;
	}
	
	public final void lista_invariantes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lista_invariantes_AST = null;
		
		try {      // for error handling
			{
			int _cnt506=0;
			_loop506:
			do {
				if ((_tokenSet_13.member(LA(1)))) {
					declaracion_invariante();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt506>=1 ) { break _loop506; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt506++;
			} while (true);
			}
			lista_invariantes_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = lista_invariantes_AST;
	}
	
	public final void declaracion_invariante() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declaracion_invariante_AST = null;
		AST e_AST = null;
		
		try {      // for error handling
			declaracion_expresion();
			e_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			declaracion_invariante_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
		returnAST = declaracion_invariante_AST;
	}
	
	public final void expresion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_AST = null;
		
		try {      // for error handling
			expresion_nivel_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case IFF:
			case IMPLIES:
			{
				{
				switch ( LA(1)) {
				case IFF:
				{
					AST tmp32_AST = null;
					tmp32_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp32_AST);
					match(IFF);
					break;
				}
				case IMPLIES:
				{
					AST tmp33_AST = null;
					tmp33_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp33_AST);
					match(IMPLIES);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expresion_nivel_1();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case PyC:
			case COMA:
			case PARENTESIS_CERRAR:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expresion_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_AST;
	}
	
	public final void expresion_nivel_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_nivel_1_AST = null;
		
		try {      // for error handling
			expresion_nivel_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop514:
			do {
				if ((LA(1)==OR)) {
					AST tmp34_AST = null;
					tmp34_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp34_AST);
					match(OR);
					expresion_nivel_2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop514;
				}
				
			} while (true);
			}
			expresion_nivel_1_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_nivel_1_AST;
	}
	
	public final void expresion_nivel_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_nivel_2_AST = null;
		
		try {      // for error handling
			expresion_nivel_3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop517:
			do {
				if ((LA(1)==AND)) {
					AST tmp35_AST = null;
					tmp35_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp35_AST);
					match(AND);
					expresion_nivel_3();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop517;
				}
				
			} while (true);
			}
			expresion_nivel_2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_nivel_2_AST;
	}
	
	public final void expresion_nivel_3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_nivel_3_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case NOT:
			{
				{
				AST tmp36_AST = null;
				tmp36_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp36_AST);
				match(NOT);
				expresion_nivel_3();
				astFactory.addASTChild(currentAST, returnAST);
				}
				expresion_nivel_3_AST = (AST)currentAST.root;
				break;
			}
			case IDENT:
			case LIT_ENTERO:
			case LIT_REAL:
			case LIT_STRING:
			case MENOS:
			case ABS:
			case SIN:
			case COS:
			case PARENTESIS_ABRIR:
			case MAX:
			case MIN:
			case SUM:
			{
				expresion_nivel_4();
				astFactory.addASTChild(currentAST, returnAST);
				expresion_nivel_3_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_nivel_3_AST;
	}
	
	public final void expresion_nivel_4() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_nivel_4_AST = null;
		
		try {      // for error handling
			expresion_nivel_5();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case MAYOR:
			case MENOR:
			case MAYOR_IGUAL:
			case MENOR_IGUAL:
			case IGUAL:
			case DISTINTO:
			{
				{
				switch ( LA(1)) {
				case MAYOR:
				{
					AST tmp37_AST = null;
					tmp37_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp37_AST);
					match(MAYOR);
					break;
				}
				case MENOR:
				{
					AST tmp38_AST = null;
					tmp38_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp38_AST);
					match(MENOR);
					break;
				}
				case MAYOR_IGUAL:
				{
					AST tmp39_AST = null;
					tmp39_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp39_AST);
					match(MAYOR_IGUAL);
					break;
				}
				case MENOR_IGUAL:
				{
					AST tmp40_AST = null;
					tmp40_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp40_AST);
					match(MENOR_IGUAL);
					break;
				}
				case IGUAL:
				{
					AST tmp41_AST = null;
					tmp41_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp41_AST);
					match(IGUAL);
					break;
				}
				case DISTINTO:
				{
					AST tmp42_AST = null;
					tmp42_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp42_AST);
					match(DISTINTO);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expresion_nivel_5();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case PyC:
			case COMA:
			case IFF:
			case IMPLIES:
			case OR:
			case AND:
			case PARENTESIS_CERRAR:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expresion_nivel_4_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_nivel_4_AST;
	}
	
	public final void expresion_nivel_5() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_nivel_5_AST = null;
		
		try {      // for error handling
			exp_mult();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop526:
			do {
				if ((LA(1)==MAS||LA(1)==MENOS)) {
					{
					switch ( LA(1)) {
					case MAS:
					{
						AST tmp43_AST = null;
						tmp43_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp43_AST);
						match(MAS);
						break;
					}
					case MENOS:
					{
						AST tmp44_AST = null;
						tmp44_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp44_AST);
						match(MENOS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					exp_mult();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop526;
				}
				
			} while (true);
			}
			expresion_nivel_5_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_nivel_5_AST;
	}
	
	public final void exp_mult() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_mult_AST = null;
		
		try {      // for error handling
			expresion_unaria();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop530:
			do {
				if (((LA(1) >= MULT && LA(1) <= POW))) {
					{
					switch ( LA(1)) {
					case MULT:
					{
						AST tmp45_AST = null;
						tmp45_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp45_AST);
						match(MULT);
						break;
					}
					case DIV:
					{
						AST tmp46_AST = null;
						tmp46_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp46_AST);
						match(DIV);
						break;
					}
					case MOD:
					{
						AST tmp47_AST = null;
						tmp47_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp47_AST);
						match(MOD);
						break;
					}
					case POW:
					{
						AST tmp48_AST = null;
						tmp48_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp48_AST);
						match(POW);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					expresion_unaria();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop530;
				}
				
			} while (true);
			}
			exp_mult_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_24);
			} else {
			  throw ex;
			}
		}
		returnAST = exp_mult_AST;
	}
	
	public final void expresion_unaria() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_unaria_AST = null;
		AST j_AST = null;
		AST e_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case MENOS:
			{
				match(MENOS);
				exp_func();
				j_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					expresion_unaria_AST = (AST)currentAST.root;
					expresion_unaria_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(MENOS_UNARIO,"Unary Minus")).add(j_AST));
					currentAST.root = expresion_unaria_AST;
					currentAST.child = expresion_unaria_AST!=null &&expresion_unaria_AST.getFirstChild()!=null ?
						expresion_unaria_AST.getFirstChild() : expresion_unaria_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case IDENT:
			case LIT_ENTERO:
			case LIT_REAL:
			case LIT_STRING:
			case ABS:
			case SIN:
			case COS:
			case PARENTESIS_ABRIR:
			case MAX:
			case MIN:
			case SUM:
			{
				exp_func();
				e_AST = (AST)returnAST;
				if ( inputState.guessing==0 ) {
					expresion_unaria_AST = (AST)currentAST.root;
					expresion_unaria_AST = e_AST;
					currentAST.root = expresion_unaria_AST;
					currentAST.child = expresion_unaria_AST!=null &&expresion_unaria_AST.getFirstChild()!=null ?
						expresion_unaria_AST.getFirstChild() : expresion_unaria_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = expresion_unaria_AST;
	}
	
	public final void exp_func() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_func_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			case LIT_ENTERO:
			case LIT_REAL:
			case LIT_STRING:
			case PARENTESIS_ABRIR:
			{
				exp_base();
				astFactory.addASTChild(currentAST, returnAST);
				exp_func_AST = (AST)currentAST.root;
				break;
			}
			case ABS:
			case SIN:
			case COS:
			case MAX:
			case MIN:
			case SUM:
			{
				func_compleja();
				astFactory.addASTChild(currentAST, returnAST);
				exp_func_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = exp_func_AST;
	}
	
	public final void exp_base() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exp_base_AST = null;
		AST a_AST = null;
		Token  b = null;
		AST b_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LIT_REAL:
			{
				AST tmp50_AST = null;
				tmp50_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp50_AST);
				match(LIT_REAL);
				exp_base_AST = (AST)currentAST.root;
				break;
			}
			case LIT_ENTERO:
			{
				AST tmp51_AST = null;
				tmp51_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp51_AST);
				match(LIT_ENTERO);
				exp_base_AST = (AST)currentAST.root;
				break;
			}
			case LIT_STRING:
			{
				AST tmp52_AST = null;
				tmp52_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp52_AST);
				match(LIT_STRING);
				exp_base_AST = (AST)currentAST.root;
				break;
			}
			case PARENTESIS_ABRIR:
			{
				match(PARENTESIS_ABRIR);
				expresion();
				astFactory.addASTChild(currentAST, returnAST);
				match(PARENTESIS_CERRAR);
				exp_base_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((LA(1)==IDENT) && (LA(2)==PUNTO)) {
					att();
					a_AST = (AST)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						resuelveNombreAtributo(a_AST);
					}
					exp_base_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==IDENT) && (_tokenSet_25.member(LA(2)))) {
					b = LT(1);
					b_AST = astFactory.create(b);
					astFactory.addASTChild(currentAST, b_AST);
					match(IDENT);
					if ( inputState.guessing==0 ) {
						resuelveNombre(b_AST);
					}
					exp_base_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = exp_base_AST;
	}
	
	public final void func_compleja() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_compleja_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case MAX:
			case MIN:
			case SUM:
			{
				func_multiparam();
				astFactory.addASTChild(currentAST, returnAST);
				func_compleja_AST = (AST)currentAST.root;
				break;
			}
			case ABS:
			case SIN:
			case COS:
			{
				func_uniparam();
				astFactory.addASTChild(currentAST, returnAST);
				func_compleja_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = func_compleja_AST;
	}
	
	public final void func_multiparam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_multiparam_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case MAX:
			{
				AST tmp55_AST = null;
				tmp55_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp55_AST);
				match(MAX);
				break;
			}
			case MIN:
			{
				AST tmp56_AST = null;
				tmp56_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp56_AST);
				match(MIN);
				break;
			}
			case SUM:
			{
				AST tmp57_AST = null;
				tmp57_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp57_AST);
				match(SUM);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(PARENTESIS_ABRIR);
			expresion();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop539:
			do {
				if ((LA(1)==COMA)) {
					match(COMA);
					expresion();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop539;
				}
				
			} while (true);
			}
			match(PARENTESIS_CERRAR);
			func_multiparam_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = func_multiparam_AST;
	}
	
	public final void func_uniparam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST func_uniparam_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case ABS:
			{
				AST tmp61_AST = null;
				tmp61_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp61_AST);
				match(ABS);
				break;
			}
			case SIN:
			{
				AST tmp62_AST = null;
				tmp62_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp62_AST);
				match(SIN);
				break;
			}
			case COS:
			{
				AST tmp63_AST = null;
				tmp63_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp63_AST);
				match(COS);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(PARENTESIS_ABRIR);
			expresion();
			astFactory.addASTChild(currentAST, returnAST);
			match(PARENTESIS_CERRAR);
			func_uniparam_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = func_uniparam_AST;
	}
	
	public final void att() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST att_AST = null;
		Token  f = null;
		AST f_AST = null;
		Token  a = null;
		AST a_AST = null;
		
		try {      // for error handling
			f = LT(1);
			f_AST = astFactory.create(f);
			match(IDENT);
			AST tmp66_AST = null;
			tmp66_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp66_AST);
			match(PUNTO);
			a = LT(1);
			a_AST = astFactory.create(a);
			astFactory.addASTChild(currentAST, a_AST);
			match(IDENT);
			if ( inputState.guessing==0 ) {
				att_AST = (AST)currentAST.root;
				att_AST = (AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(ATRIBUTO,"Attribute")).add(f_AST).add(a_AST));
				currentAST.root = att_AST;
				currentAST.child = att_AST!=null &&att_AST.getFirstChild()!=null ?
					att_AST.getFirstChild() : att_AST;
				currentAST.advanceChildToEnd();
			}
			att_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
			} else {
			  throw ex;
			}
		}
		returnAST = att_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"FEATURE_MODEL",
		"FEATURE",
		"FEATURES",
		"CONSTRAINTS",
		"CONSTRAINT",
		"DOMINIO",
		"DEF_VALUE",
		"NULL_VALUE",
		"ATRIBUTOS",
		"ATRIBUTO",
		"RELACION",
		"CARDINALIDAD",
		"RELACIONES",
		"INVARIANTES",
		"INVARIANTE",
		"RANGO",
		"LITERAL",
		"RANGOS",
		"VALORES",
		"ENUM",
		"MENOS_UNARIO",
		"SECCION_RELACIONES",
		"DOSPUNTOS",
		"PyC",
		"IDENT",
		"CORCHETE_ABRIR",
		"CORCHETE_CERRAR",
		"LIT_ENTERO",
		"COMA",
		"LLAVE_ABRIR",
		"LLAVE_CERRAR",
		"SECCION_ATRIBUTOS",
		"PUNTO",
		"INTEGER",
		"TO",
		"LIT_REAL",
		"LIT_STRING",
		"SECCION_CONSTRAINTS",
		"EXCLUDES",
		"REQUIRES",
		"IFF",
		"IMPLIES",
		"OR",
		"AND",
		"NOT",
		"MAYOR",
		"MENOR",
		"MAYOR_IGUAL",
		"MENOR_IGUAL",
		"IGUAL",
		"DISTINTO",
		"MAS",
		"MENOS",
		"MULT",
		"DIV",
		"MOD",
		"POW",
		"ABS",
		"SIN",
		"COS",
		"PARENTESIS_ABRIR",
		"PARENTESIS_CERRAR",
		"MAX",
		"MIN",
		"SUM"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2233382993922L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 2199023255554L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2233651429378L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 19260243968L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 134217728L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 939524096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 8858370048L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 2199291691010L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 4294967296L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 4831838208L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 1073741824L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 5502926848L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { -2233502288515694592L, 29L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { -15871648989184L, 29L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { -2233502288515694590L, 29L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { -2233502271335825406L, 29L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 17179869184L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { -2233502271335825408L, 29L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 4429185024L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { 52780987318272L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { 123149731495936L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { 263887219851264L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { 35729734285393920L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { 143816125342285824L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { 2305543946480123904L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	
	}
