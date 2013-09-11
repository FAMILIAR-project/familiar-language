// $ANTLR : "Analex.g" -> "Analex.java"$

    package es.us.isa.FAMA.parser;    

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class Analex extends antlr.CharScanner implements AnalexTokenTypes, TokenStream
 {
public Analex(InputStream in) {
	this(new ByteBuffer(in));
}
public Analex(Reader in) {
	this(new CharBuffer(in));
}
public Analex(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public Analex(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("REQUIRES", this), new Integer(43));
	literals.put(new ANTLRHashString("to", this), new Integer(38));
	literals.put(new ANTLRHashString("sum", this), new Integer(68));
	literals.put(new ANTLRHashString("min", this), new Integer(67));
	literals.put(new ANTLRHashString("abs", this), new Integer(61));
	literals.put(new ANTLRHashString("sin", this), new Integer(62));
	literals.put(new ANTLRHashString("Integer", this), new Integer(37));
	literals.put(new ANTLRHashString("EXCLUDES", this), new Integer(42));
	literals.put(new ANTLRHashString("NOT", this), new Integer(48));
	literals.put(new ANTLRHashString("IMPLIES", this), new Integer(45));
	literals.put(new ANTLRHashString("AND", this), new Integer(47));
	literals.put(new ANTLRHashString("max", this), new Integer(66));
	literals.put(new ANTLRHashString("IFF", this), new Integer(44));
	literals.put(new ANTLRHashString("cos", this), new Integer(63));
	literals.put(new ANTLRHashString("OR", this), new Integer(46));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mBLANCO(true);
					theRetToken=_returnToken;
					break;
				}
				case '.':
				{
					mPUNTO(true);
					theRetToken=_returnToken;
					break;
				}
				case '#':
				{
					mCOMENT_LIN(true);
					theRetToken=_returnToken;
					break;
				}
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':  case '_':  case 'a':
				case 'b':  case 'c':  case 'd':  case 'e':
				case 'f':  case 'g':  case 'h':  case 'i':
				case 'j':  case 'k':  case 'l':  case 'm':
				case 'n':  case 'o':  case 'p':  case 'q':
				case 'r':  case 's':  case 't':  case 'u':
				case 'v':  case 'w':  case 'x':  case 'y':
				case 'z':
				{
					mIDENT(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':
				{
					mLIT_STRING(true);
					theRetToken=_returnToken;
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					mNUMERO(true);
					theRetToken=_returnToken;
					break;
				}
				case ',':
				{
					mCOMA(true);
					theRetToken=_returnToken;
					break;
				}
				case ';':
				{
					mPyC(true);
					theRetToken=_returnToken;
					break;
				}
				case ':':
				{
					mDOSPUNTOS(true);
					theRetToken=_returnToken;
					break;
				}
				case '(':
				{
					mPARENTESIS_ABRIR(true);
					theRetToken=_returnToken;
					break;
				}
				case ')':
				{
					mPARENTESIS_CERRAR(true);
					theRetToken=_returnToken;
					break;
				}
				case '{':
				{
					mLLAVE_ABRIR(true);
					theRetToken=_returnToken;
					break;
				}
				case '}':
				{
					mLLAVE_CERRAR(true);
					theRetToken=_returnToken;
					break;
				}
				case '[':
				{
					mCORCHETE_ABRIR(true);
					theRetToken=_returnToken;
					break;
				}
				case ']':
				{
					mCORCHETE_CERRAR(true);
					theRetToken=_returnToken;
					break;
				}
				case '+':
				{
					mMAS(true);
					theRetToken=_returnToken;
					break;
				}
				case '-':
				{
					mMENOS(true);
					theRetToken=_returnToken;
					break;
				}
				case '*':
				{
					mMULT(true);
					theRetToken=_returnToken;
					break;
				}
				case '/':
				{
					mDIV(true);
					theRetToken=_returnToken;
					break;
				}
				case '^':
				{
					mPOW(true);
					theRetToken=_returnToken;
					break;
				}
				case '!':
				{
					mDISTINTO(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='>') && (LA(2)=='=')) {
						mMAYOR_IGUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='=')) {
						mMENOR_IGUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (LA(2)=='=')) {
						mIGUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (LA(2)=='R')) {
						mSECCION_RELACIONES(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (LA(2)=='A')) {
						mSECCION_ATRIBUTOS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (LA(2)=='C')) {
						mSECCION_CONSTRAINTS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (true)) {
						mMOD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (true)) {
						mASIG(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (true)) {
						mMAYOR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (true)) {
						mMENOR(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	protected final void mSALTO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SALTO;
		int _saveIndex;
		
		switch ( LA(1)) {
		case '\r':
		{
			match("\r\n");
			break;
		}
		case '\n':
		{
			match('\n');
			if ( inputState.guessing==0 ) {
				newline();
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBLANCO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BLANCO;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '\n':  case '\r':
		{
			mSALTO(false);
			break;
		}
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mLETRA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LETRA;
		int _saveIndex;
		
		switch ( LA(1)) {
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':
		{
			matchRange('A','Z');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mBARRA_BAJA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BARRA_BAJA;
		int _saveIndex;
		
		match('_');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mDIGITO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIGITO;
		int _saveIndex;
		
		matchRange('0','9');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCOMILLA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMILLA;
		int _saveIndex;
		
		match('\'');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPUNTO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PUNTO;
		int _saveIndex;
		
		match('.');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mALMOADILLA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ALMOADILLA;
		int _saveIndex;
		
		match('#');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMENT_LIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMENT_LIN;
		int _saveIndex;
		
		mALMOADILLA(false);
		{
		_loop404:
		do {
			if ((LA(1)=='\r') && (_tokenSet_0.member(LA(2)))) {
				{
				int _cnt401=0;
				_loop401:
				do {
					if ((LA(1)=='\r') && (_tokenSet_0.member(LA(2)))) {
						match('\r');
					}
					else {
						if ( _cnt401>=1 ) { break _loop401; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt401++;
				} while (true);
				}
				{
				match(_tokenSet_0);
				}
			}
			else if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
				}
			}
			else {
				break _loop404;
			}
			
		} while (true);
		}
		match("\r\n");
		if ( inputState.guessing==0 ) {
			newline();_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mIDENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IDENT;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':  case 'a':  case 'b':
		case 'c':  case 'd':  case 'e':  case 'f':
		case 'g':  case 'h':  case 'i':  case 'j':
		case 'k':  case 'l':  case 'm':  case 'n':
		case 'o':  case 'p':  case 'q':  case 'r':
		case 's':  case 't':  case 'u':  case 'v':
		case 'w':  case 'x':  case 'y':  case 'z':
		{
			mLETRA(false);
			break;
		}
		case '_':
		{
			mBARRA_BAJA(false);
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		_loop408:
		do {
			switch ( LA(1)) {
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case 'a':  case 'b':
			case 'c':  case 'd':  case 'e':  case 'f':
			case 'g':  case 'h':  case 'i':  case 'j':
			case 'k':  case 'l':  case 'm':  case 'n':
			case 'o':  case 'p':  case 'q':  case 'r':
			case 's':  case 't':  case 'u':  case 'v':
			case 'w':  case 'x':  case 'y':  case 'z':
			{
				mLETRA(false);
				break;
			}
			case '_':
			{
				mBARRA_BAJA(false);
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				mDIGITO(false);
				break;
			}
			default:
			{
				break _loop408;
			}
			}
		} while (true);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLIT_STRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LIT_STRING;
		int _saveIndex;
		
		match('"');
		{
		_loop411:
		do {
			if ((_tokenSet_2.member(LA(1)))) {
				matchNot('"');
			}
			else {
				break _loop411;
			}
			
		} while (true);
		}
		match('"');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNUMERO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NUMERO;
		int _saveIndex;
		
		boolean synPredMatched416 = false;
		if ((((LA(1) >= '0' && LA(1) <= '9')) && (_tokenSet_3.member(LA(2))))) {
			int _m416 = mark();
			synPredMatched416 = true;
			inputState.guessing++;
			try {
				{
				{
				int _cnt415=0;
				_loop415:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDIGITO(false);
					}
					else {
						if ( _cnt415>=1 ) { break _loop415; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt415++;
				} while (true);
				}
				match('.');
				}
			}
			catch (RecognitionException pe) {
				synPredMatched416 = false;
			}
			rewind(_m416);
inputState.guessing--;
		}
		if ( synPredMatched416 ) {
			{
			int _cnt418=0;
			_loop418:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDIGITO(false);
				}
				else {
					if ( _cnt418>=1 ) { break _loop418; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt418++;
			} while (true);
			}
			match('.');
			{
			int _cnt420=0;
			_loop420:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDIGITO(false);
				}
				else {
					if ( _cnt420>=1 ) { break _loop420; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt420++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				_ttype = LIT_REAL;
			}
		}
		else {
			boolean synPredMatched424 = false;
			if ((((LA(1) >= '0' && LA(1) <= '9')) && (true))) {
				int _m424 = mark();
				synPredMatched424 = true;
				inputState.guessing++;
				try {
					{
					{
					int _cnt423=0;
					_loop423:
					do {
						if (((LA(1) >= '0' && LA(1) <= '9'))) {
							mDIGITO(false);
						}
						else {
							if ( _cnt423>=1 ) { break _loop423; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
						}
						
						_cnt423++;
					} while (true);
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched424 = false;
				}
				rewind(_m424);
inputState.guessing--;
			}
			if ( synPredMatched424 ) {
				{
				int _cnt426=0;
				_loop426:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDIGITO(false);
					}
					else {
						if ( _cnt426>=1 ) { break _loop426; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt426++;
				} while (true);
				}
				if ( inputState.guessing==0 ) {
					_ttype = LIT_ENTERO;
				}
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		}
		
	public final void mCOMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPyC(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PyC;
		int _saveIndex;
		
		match(';');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDOSPUNTOS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOSPUNTOS;
		int _saveIndex;
		
		match(':');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPARENTESIS_ABRIR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PARENTESIS_ABRIR;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPARENTESIS_CERRAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PARENTESIS_CERRAR;
		int _saveIndex;
		
		match(')');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLLAVE_ABRIR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LLAVE_ABRIR;
		int _saveIndex;
		
		match('{');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLLAVE_CERRAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LLAVE_CERRAR;
		int _saveIndex;
		
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCORCHETE_ABRIR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CORCHETE_ABRIR;
		int _saveIndex;
		
		match('[');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCORCHETE_CERRAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CORCHETE_CERRAR;
		int _saveIndex;
		
		match(']');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMAS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MAS;
		int _saveIndex;
		
		match('+');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMENOS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MENOS;
		int _saveIndex;
		
		match('-');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMULT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MULT;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIV(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIV;
		int _saveIndex;
		
		match('/');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMOD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MOD;
		int _saveIndex;
		
		match('%');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPOW(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = POW;
		int _saveIndex;
		
		match('^');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASIG(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASIG;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMAYOR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MAYOR;
		int _saveIndex;
		
		match('>');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMENOR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MENOR;
		int _saveIndex;
		
		match('<');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMAYOR_IGUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MAYOR_IGUAL;
		int _saveIndex;
		
		match(">=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMENOR_IGUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MENOR_IGUAL;
		int _saveIndex;
		
		match("<=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mIGUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IGUAL;
		int _saveIndex;
		
		match("==");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDISTINTO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DISTINTO;
		int _saveIndex;
		
		match("!=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSECCION_RELACIONES(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SECCION_RELACIONES;
		int _saveIndex;
		
		match("%Relationships");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSECCION_ATRIBUTOS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SECCION_ATRIBUTOS;
		int _saveIndex;
		
		match("%Attributes");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSECCION_CONSTRAINTS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SECCION_CONSTRAINTS;
		int _saveIndex;
		
		match("%Constraints");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[260];
		data[0]=-1032L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		for (int i = 64; i<=127; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[260];
		data[0]=-8200L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		for (int i = 64; i<=127; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[260];
		data[0]=-17179869192L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		for (int i = 64; i<=127; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[131];
		data[0]=288019269919178752L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
