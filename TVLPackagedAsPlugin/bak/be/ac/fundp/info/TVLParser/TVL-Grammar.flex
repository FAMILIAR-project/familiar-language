// FEATUREMX VFD STAR FORMAT JFLEX LEXER SPECIFICATION
// Initial implementation: Xavier Devroey & Michael Marcozzi 
// Updated by Germain Saval

// 1) UserCode 
// -----------
package be.ac.fundp.info.TVLParser.Parser;

import be.ac.fundp.info.TVLParser.exceptions.ParsingException;

import java_cup.runtime.*; // Import Symbol class and other things for Cup compatibility 


%% 
// 2) Options and declarations 
// ---------------------------

%class Lexer // Name of the Java generated class
%cupsym Symbols
%cup // Enable the CUP compatibility
%public // class and constructor are public 
%apiprivate // all other methods are private
%unicode // Cause the generated scanner to use the full 16 bit Unicode input character set that Java supports natively 
%line // Switches line counting on
%column // Switches column counting on

// Create tokens for CUP :
%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, String value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

// Macros :
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

Identifier     = [:jletterdigit:] ( [:jletterdigit:] | "_" )*


InputCharacter = [^\r\n]

DocumentationComment 	   = "/**" {CommentContent} "*"+ "/" | null
CommentContent             = ( [^*] | \*+ [^/*] )*

String 					   = "\"" [^*] ~"\""

NonDocumentationComment	   = {TraditionalComment} | {EndOfLineComment}
TraditionalComment         = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment           = "//" {InputCharacter}* {LineTerminator}


Integer     = (-)? [1-9][0-9]*

Real	   = {FLit1}|{FLit2}

FLit1    = (-)? [0-9]+ \. [0-9]+  
FLit2    = (-)? [0-9]+ 

%% 
// 3) Lexical rules
// ----------------

// Comment
{NonDocumentationComment}		   {  }			
{DocumentationComment}             {  }

// White space
{WhiteSpace}                       {  }

// Numbers
"0"                                { return symbol(Symbols.ZERO); }
{Integer}                          { return symbol(Symbols.INTEGER,yytext()); }
{Real}                      	   { return symbol(Symbols.REAL,yytext()); }
{String}						   { return symbol(Symbols.STRING,yytext()); }	

// Properties
"shared"                          { return symbol(Symbols.SHARED); }
"root"                            { return symbol(Symbols.ROOT); }
"opt"                             { return symbol(Symbols.OPT); }
"bool"                            { return symbol(Symbols.BOOL); }
"int"                             { return symbol(Symbols.INT); }
"enum"                            { return symbol(Symbols.ENUM); }
"struct"                          { return symbol(Symbols.STRUCT); }
"real"                            { return symbol(Symbols.REALP); }
"ifin"                            { return symbol(Symbols.IFIN); }
"ifout"                           { return symbol(Symbols.IFOUT); }
"ifIn"                            { return symbol(Symbols.IFIN); }
"ifOut"                           { return symbol(Symbols.IFOUT); }
"const"                           { return symbol(Symbols.CONST); }

// Cardinality
"group"                           { return symbol(Symbols.GROUP); }
"oneof"                           { return symbol(Symbols.ONEOF); }
"someof"                          { return symbol(Symbols.SOMEOF); }
"allof"                           { return symbol(Symbols.ALLOF); }
"oneOf"                           { return symbol(Symbols.ONEOF); }
"someOf"                          { return symbol(Symbols.SOMEOF); }
"allOf"                           { return symbol(Symbols.ALLOF); }

// Selectors
"parent"                           { return symbol(Symbols.PARENT); }
"this"                             { return symbol(Symbols.THIS); }
"children"                         { return symbol(Symbols.CHILDREN); }
"selectedchildren"                 { return symbol(Symbols.SCHILDREN); }
"selectedChildren"                 { return symbol(Symbols.SCHILDREN); }

// Operators
"&&"                               { return symbol(Symbols.AND); }
"||"                               { return symbol(Symbols.OR); }
"->"                               { return symbol(Symbols.IMPLIES); }
"<-"                               { return symbol(Symbols.INVERSEIMPLIES); } //
"<->"                              { return symbol(Symbols.IFANDONLYIF); }
"!"                                { return symbol(Symbols.NOT); }
"true"                             { return symbol(Symbols.TRUE); }
"false"                            { return symbol(Symbols.FALSE); }
"=="                               { return symbol(Symbols.EQUALS); }
"!="							   { return symbol(Symbols.NOTEQUALS); } //
">="                               { return symbol(Symbols.GEQ); }
"<="                               { return symbol(Symbols.LEQ); }
">"								   { return symbol(Symbols.GREATER); } //
"<"								   { return symbol(Symbols.LOWER) ; } //
"+"                                { return symbol(Symbols.PLUS); }
"-"                                { return symbol(Symbols.MINUS); }
"*"                                { return symbol(Symbols.TIMES); }
"/"                                { return symbol(Symbols.DIVIDE); }
"max"                              { return symbol(Symbols.MAX); }
"min"                              { return symbol(Symbols.MIN); }
"sum"                              { return symbol(Symbols.SUM); }
"mul"                              { return symbol(Symbols.MUL); }
"avg"                              { return symbol(Symbols.AVG); }
"count"                            { return symbol(Symbols.COUNT); }
"and"                              { return symbol(Symbols.ANDAGG); }
"or"                               { return symbol(Symbols.ORAGG); }
"xor"                              { return symbol(Symbols.XORAGG); }
"abs"                              { return symbol(Symbols.ABS); }
"is"							   { return symbol(Symbols.IS); }
"in"                               { return symbol(Symbols.IN); }
"excludes"                         { return symbol(Symbols.EXCLUDES); }
"requires"                         { return symbol(Symbols.REQUIRES); }

// Other symbols
"["                                { return symbol(Symbols.SQBRA); }
"]"                                { return symbol(Symbols.SQBRAEND); }
","                                { return symbol(Symbols.COMMA); }
"("                                { return symbol(Symbols.BRA); }
")"                                { return symbol(Symbols.BRAEND); }
"{"                                { return symbol(Symbols.CBRA); }
"}"                                { return symbol(Symbols.CBRAEND); }
"."                                { return symbol(Symbols.DOT); }
"?"                                { return symbol(Symbols.QUEST); }
":"                                { return symbol(Symbols.COLON); }
";"                                { return symbol(Symbols.SEMICOLON); }
"data"                             { return symbol(Symbols.DATA); }	

// Identifier
{Identifier}                	   { return symbol(Symbols.ID,yytext()); }


// Error
.|\n
{ 
	try {
		throw new ParsingException("Parsing error : Illegal character (line "+yyline+", column "+yycolumn+") : <"+yytext()+">");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
}