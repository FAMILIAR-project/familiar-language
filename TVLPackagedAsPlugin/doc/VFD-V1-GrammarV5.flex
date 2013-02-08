// FEATUREMX VFD STAR FORMAT JFLEX LEXER SPECIFICATION
// Xavier Devroey & Michael Marcozzi 

// 1) UserCode 
// -----------


import java_cup.runtime.*; // Import Symbol class and other things for Cup compatibility 


%% 
// 2) Options and declarations 
// ---------------------------

%class VFDStarLexer // Name of the Java generated class 
%cup // Enable the CUP compatibility
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

FeatureIdentifier     = [:uppercase:] [:jletterdigit:]*
AttributeIdentifier   = [:lowercase:] [:jletterdigit:]*

Comment 	   = "/*" [^*] ~"*/" | "/*" "*"+ "/"

PosInteger     = [1-9][0-9]*

Real	   = {FLit1}|{FLit2}

FLit1    = [0-9]+ \. [0-9]+  
FLit2    = [0-9]+ 

%% 
// 3) Lexical rules
// ----------------

// Comment
{Comment}                          { return symbol(sym.COMMENT,yytext()); }

// White space
{WhiteSpace}                       {  }

// Numbers
"0"                                { return symbol(sym.ZERO); }
{PosInteger}                       { return symbol(sym.POSINT,yytext()); }
{Real}                      	   { return symbol(sym.REAL,yytext()); }

// Properties
"shared"                          { return symbol(sym.SHARED); }
"root"                            { return symbol(sym.ROOT); }
"opt"                             { return symbol(sym.OPT); }
"bool"                            { return symbol(sym.BOOL); }
"int"                             { return symbol(sym.INT); }
"enum"                            { return symbol(sym.ENUM); }
"struct"                          { return symbol(sym.STRUCT); }
"real"                            { return symbol(sym.REALP); }
"ifin"                            { return symbol(sym.IFIN); }
"ifout"                           { return symbol(sym.IFOUT); }
"define"                           { return symbol(sym.DEFINE); }

// Cardinality
"group"                           { return symbol(sym.GROUP); }
"oneof"                           { return symbol(sym.ONEOF); }
"someof"                          { return symbol(sym.SOMEOF); }
"allof"                           { return symbol(sym.ALLOF); }

// Selectors
"parent"                           { return symbol(sym.PARENT); }
"this"                             { return symbol(sym.THIS); }
"children"                         { return symbol(sym.CHILDREN); }
"selectedchildren"                 { return symbol(sym.SCHILDREN); }

// Operators
"&&"                               { return symbol(sym.AND); }
"||"                               { return symbol(sym.OR); }
"==>"                              { return symbol(sym.IMPLIES); }
"<=>"                              { return symbol(sym.IFANDONLYIF); }
"!"                                { return symbol(sym.NOT); }
"true"                             { return symbol(sym.TRUE); }
"false"                            { return symbol(sym.FALSE); }
"=="                               { return symbol(sym.EQUALS); }
">="                               { return symbol(sym.GEQ); }
"<="                               { return symbol(sym.LEQ); }
"+"                                { return symbol(sym.PLUS); }
"-"                                { return symbol(sym.MINUS); }
"*"                                { return symbol(sym.TIMES); }
"/"                                { return symbol(sym.DIVIDE); }
"max"                              { return symbol(sym.MAX); }
"min"                              { return symbol(sym.MIN); }
"sum"                              { return symbol(sym.SUM); }
"mul"                              { return symbol(sym.MUL); }
"avg"                              { return symbol(sym.AVG); }
"count"                            { return symbol(sym.COUNT); }
"and"                              { return symbol(sym.ANDAGG); }
"or"                               { return symbol(sym.ORAGG); }
"xor"                              { return symbol(sym.XORAGG); }
"abs"                              { return symbol(sym.ABS); }
"in"                               { return symbol(sym.IN); }
"excludes"                               { return symbol(sym.EXCLUDES); }
"includes"                               { return symbol(sym.INCLUDES); }

// Other symbols
"["                                { return symbol(sym.SQBRA); }
"]"                                { return symbol(sym.SQBRAEND); }
","                                { return symbol(sym.COMMA); }
"("                                { return symbol(sym.BRA); }
")"                                { return symbol(sym.BRAEND); }
"{"                                { return symbol(sym.CBRA); }
"}"                                { return symbol(sym.CBRAEND); }
"."                                { return symbol(sym.DOT); }
"?"                                { return symbol(sym.QUEST); }
":"                                { return symbol(sym.COLON); }
";"                                { return symbol(sym.SEMICOLON); }
"data"                            { return symbol(sym.DATA); }


// Identifier
{FeatureIdentifier}                { return symbol(sym.FEATURE_ID,yytext()); }
{AttributeIdentifier}              { return symbol(sym.ATTRIBUTE_ID,yytext()); }

// Error
.|\n                               { try {
			throw new Exception("VFDStar schema parsing - Illegal character (line "+yyline+", column "+yycolumn+") : <"+yytext()+">");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};}
