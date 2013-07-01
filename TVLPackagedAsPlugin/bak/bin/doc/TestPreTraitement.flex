// FEATUREMX VFD STAR FORMAT JFLEX LEXER SPECIFICATION
// Xavier Devroey & Michael Marcozzi 

// 1) UserCode 
// -----------
package conditionningTreatement;

import java_cup.runtime.*; // Import Symbol class and other things for Cup compatibility 


%% 
// 2) Options and declarations 
// ---------------------------

%class Lexer // Name of the Java generated class 
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
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]


Comment = {TraditionalComment} | {EndOfLineComment} |

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}



%% 
// 3) Lexical rules
// ----------------

{Text}		   					   { return symbol(sym.TEXT,yytext()); }
{WhiteSpace}                       {  }
{LineTerminator}				   { return symbol(sym.EOL,yytext()); }

"/"								   { return symbol(sym.SLASH,yytext()); }
"*"								   { return symbol(sym.STAR,yytext()); }



// Error
.|\n                               { try {
			throw new Exception("VFDStar schema parsing - Illegal character (line "+yyline+", column "+yycolumn+") : <"+yytext()+">");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};}
