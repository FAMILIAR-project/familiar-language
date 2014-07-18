/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

// $ANTLR : "TreeParser.g" -> "FaMaTreeParser.java"$

	package es.us.isa.FAMA.parser;    
	import java.util.*;	

import fr.familiar.attributedfm.*;
import fr.familiar.attributedfm.domain.*;
import fr.familiar.attributedfm.util.*;
import es.us.isa.FAMA.parser.*;

public interface FaMaTreeParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int FEATURE_MODEL = 4;
	int FEATURE = 5;
	int FEATURES = 6;
	int CONSTRAINTS = 7;
	int CONSTRAINT = 8;
	int DOMINIO = 9;
	int DEF_VALUE = 10;
	int NULL_VALUE = 11;
	int ATRIBUTOS = 12;
	int ATRIBUTO = 13;
	int RELACION = 14;
	int CARDINALIDAD = 15;
	int RELACIONES = 16;
	int INVARIANTES = 17;
	int INVARIANTE = 18;
	int RANGO = 19;
	int LITERAL = 20;
	int RANGOS = 21;
	int VALORES = 22;
	int ENUM = 23;
	int MENOS_UNARIO = 24;
	int SECCION_RELACIONES = 25;
	int DOSPUNTOS = 26;
	int PyC = 27;
	int IDENT = 28;
	int CORCHETE_ABRIR = 29;
	int CORCHETE_CERRAR = 30;
	int LIT_ENTERO = 31;
	int COMA = 32;
	int LLAVE_ABRIR = 33;
	int LLAVE_CERRAR = 34;
	int SECCION_ATRIBUTOS = 35;
	int PUNTO = 36;
	int INTEGER = 37;
	int TO = 38;
	int LIT_REAL = 39;
	int LIT_STRING = 40;
	int SECCION_CONSTRAINTS = 41;
	int EXCLUDES = 42;
	int REQUIRES = 43;
	int IFF = 44;
	int IMPLIES = 45;
	int OR = 46;
	int AND = 47;
	int NOT = 48;
	int MAYOR = 49;
	int MENOR = 50;
	int MAYOR_IGUAL = 51;
	int MENOR_IGUAL = 52;
	int IGUAL = 53;
	int DISTINTO = 54;
	int MAS = 55;
	int MENOS = 56;
	int MULT = 57;
	int DIV = 58;
	int MOD = 59;
	int POW = 60;
	int ABS = 61;
	int SIN = 62;
	int COS = 63;
	int PARENTESIS_ABRIR = 64;
	int PARENTESIS_CERRAR = 65;
	int MAX = 66;
	int MIN = 67;
	int SUM = 68;
}
