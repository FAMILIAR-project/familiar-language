package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.FmlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFmlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_LEFT_HOOK", "RULE_META_ATTRIBUTE_SYMBOL", "RULE_RIGHT_HOOK", "RULE_PLUS", "RULE_MINUS", "RULE_MULT", "RULE_DIV", "RULE_EXP", "RULE_LEFT_PAREN", "RULE_RIGHT_PAREN", "RULE_INT", "RULE_STRING", "RULE_LEFT_BRACKET", "RULE_RIGHT_BRACKET", "RULE_COMMA", "RULE_ID", "RULE_B_OR", "RULE_B_AND", "RULE_B_IMPLY", "RULE_B_BIMPLY", "RULE_B_NOT", "RULE_STAR", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'='", "'not'", "'true'", "'false'", "'constraint'", "'constraints'", "';'", "'if'", "'then'", "'else'", "'end'", "'foreach'", "'in'", "'do'", "'FeatureModel'", "'Feature'", "'Boolean'", "'String'", "'Configuration'", "'Set'", "'Double'", "'Integer'", "'Constraint'", "'leaves'", "'over'", "'pw'", "'minimization='", "'partial='", "'isValid'", "'counting'", "'configs'", "'nbFeatures'", "'root'", "'features'", "'size'", "'setBelongs'", "'setUnion'", "'setIntersection'", "'setDiff'", "'setEmpty'", "'setAdd'", "'setRemove'", "'setIsEmpty'", "'names'", "'ancestors'", "'descendants'", "'children'", "'sibling'", "'parent'", "'name'", "'whichfm'", "'operator'", "'strInit'", "'strConcat'", "'strSubstring'", "'strIndexOf'", "'strLength'", "'compare'", "'parameter'", "':'", "'run'", "'into'", "'ctcr'", "'merge'", "'--lazy'", "'aggregateMerge'", "'--hierarchy'", "'ksynthesis'", "'--interactive'", "'with'", "'hierarchy='", "'groups='", "'xorGroup'", "'mtxGroup'", "'orGroup'", "'constraints='", "'slice'", "'aggregate'", "'--renamings'", "'withMapping'", "'insert'", "'removeFeature'", "'renameFeature'", "'as'", "'extract'", "'assert'", "'isNull'", "'export'", "'hide'", "'configuration'", "'isComplete'", "'select'", "'deselect'", "'unselect'", "'autoSelect'", "'selectedF'", "'deselectedF'", "'unselectedF'", "'asFM'", "'map'", "'unmap'", "'cleanup'", "'cores'", "'deads'", "'fullMandatorys'", "'falseOptionals'", "'cliques'", "'quit'", "'exit'", "'isExisting'", "'isConflicting'", "'ls'", "'vars'", "'memory'", "'cpu'", "'copy'", "'cp'", "'removeVariable'", "'rm'", "'convert'", "'save'", "'serialize'", "'hierarchy'", "'print'", "'println'", "'gdisplay'", "'glisting'", "'gls'", "'setMandatory'", "'setOptional'", "'setAlternative'", "'setOr'", "'addConstraint'", "'to'", "'removeConstraint'", "'FM'", "'featuremodel'", "')?'", "'$'", "'.'", "'mand'", "'opt'", "'Xor'", "'Or'", "'Mutex'", "'getImpliesHierarchy'", "'getExcludesHierarchy'", "'getBiimpliesHierarchy'", "'getImpliesConstraint'", "'getExcludesConstraint'", "'getBiimpliesConstraint'", "'computeImplies'", "'computeExcludes'", "'computeBiimplies'", "'getORGroups'", "'getXORGroups'", "'getMUTEXGroups'", "'computeORGroups'", "'computeXORGroups'", "'computeMUTEXGroups'", "'@backend=DEFAULT'", "'@backend=BDD'", "'@backend=BDD_SPLOT'", "'crossproduct'", "'union'", "'sunion'", "'intersection'", "'diff'", "'=basic'", "'=flat'", "'=mst'", "'including'", "'excluding'", "'eq'", "'neq'", "'<'", "'>'", "'=='", "'!='", "'++'", "'--'", "'RANDOM'", "'MAX'", "'MIN'", "'--normal'", "'--verbose'", "'--withValues'", "'DIMACS'", "'fmlconstraints'", "'fmlbdd'", "'featureide'", "'fmcalc'", "'fml'", "'SPLOT'", "'TVL'", "'fd'", "'xmi'", "'S2T2'", "'bdd'", "'||'", "'&&'"
    };
    public static final int T__144=144;
    public static final int RULE_EXP=11;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__137=137;
    public static final int T__52=52;
    public static final int T__136=136;
    public static final int T__53=53;
    public static final int T__139=139;
    public static final int T__54=54;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__60=60;
    public static final int T__135=135;
    public static final int T__61=61;
    public static final int T__134=134;
    public static final int RULE_ID=19;
    public static final int T__131=131;
    public static final int RULE_B_AND=21;
    public static final int T__130=130;
    public static final int RULE_INT=14;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__129=129;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int T__128=128;
    public static final int T__65=65;
    public static final int T__127=127;
    public static final int T__166=166;
    public static final int T__165=165;
    public static final int T__168=168;
    public static final int T__167=167;
    public static final int T__162=162;
    public static final int RULE_LEFT_PAREN=12;
    public static final int T__161=161;
    public static final int T__164=164;
    public static final int T__163=163;
    public static final int T__160=160;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__159=159;
    public static final int T__30=30;
    public static final int T__158=158;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__155=155;
    public static final int T__154=154;
    public static final int T__157=157;
    public static final int T__156=156;
    public static final int T__151=151;
    public static final int T__150=150;
    public static final int T__153=153;
    public static final int T__152=152;
    public static final int RULE_LEFT_BRACKET=16;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__148=148;
    public static final int T__41=41;
    public static final int T__147=147;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__149=149;
    public static final int T__100=100;
    public static final int T__221=221;
    public static final int T__220=220;
    public static final int T__102=102;
    public static final int T__223=223;
    public static final int T__101=101;
    public static final int T__222=222;
    public static final int RULE_DIV=10;
    public static final int T__218=218;
    public static final int T__217=217;
    public static final int T__219=219;
    public static final int T__214=214;
    public static final int RULE_STAR=25;
    public static final int T__213=213;
    public static final int T__216=216;
    public static final int T__215=215;
    public static final int T__210=210;
    public static final int T__212=212;
    public static final int T__211=211;
    public static final int RULE_COMMA=18;
    public static final int T__207=207;
    public static final int T__206=206;
    public static final int T__209=209;
    public static final int T__208=208;
    public static final int T__203=203;
    public static final int T__202=202;
    public static final int T__205=205;
    public static final int T__204=204;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_SL_COMMENT=27;
    public static final int RULE_RIGHT_BRACKET=17;
    public static final int T__119=119;
    public static final int T__118=118;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__114=114;
    public static final int T__117=117;
    public static final int T__116=116;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__230=230;
    public static final int RULE_B_BIMPLY=23;
    public static final int RULE_MINUS=8;
    public static final int T__108=108;
    public static final int T__229=229;
    public static final int T__107=107;
    public static final int T__228=228;
    public static final int T__109=109;
    public static final int T__104=104;
    public static final int T__225=225;
    public static final int T__103=103;
    public static final int T__224=224;
    public static final int T__106=106;
    public static final int T__227=227;
    public static final int T__105=105;
    public static final int T__226=226;
    public static final int RULE_B_IMPLY=22;
    public static final int RULE_LEFT_HOOK=4;
    public static final int RULE_B_NOT=24;
    public static final int RULE_RIGHT_HOOK=6;
    public static final int RULE_ML_COMMENT=26;
    public static final int T__201=201;
    public static final int RULE_B_OR=20;
    public static final int T__200=200;
    public static final int RULE_META_ATTRIBUTE_SYMBOL=5;
    public static final int RULE_RIGHT_PAREN=13;
    public static final int T__91=91;
    public static final int T__188=188;
    public static final int T__92=92;
    public static final int T__187=187;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__189=189;
    public static final int T__184=184;
    public static final int T__183=183;
    public static final int T__186=186;
    public static final int T__90=90;
    public static final int T__185=185;
    public static final int T__180=180;
    public static final int T__182=182;
    public static final int T__181=181;
    public static final int T__99=99;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__177=177;
    public static final int T__176=176;
    public static final int T__179=179;
    public static final int T__178=178;
    public static final int T__173=173;
    public static final int T__172=172;
    public static final int T__175=175;
    public static final int T__174=174;
    public static final int T__171=171;
    public static final int T__170=170;
    public static final int RULE_MULT=9;
    public static final int T__169=169;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=15;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int RULE_PLUS=7;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__199=199;
    public static final int T__81=81;
    public static final int T__198=198;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__195=195;
    public static final int T__194=194;
    public static final int RULE_WS=28;
    public static final int T__197=197;
    public static final int T__196=196;
    public static final int T__191=191;
    public static final int T__190=190;
    public static final int T__193=193;
    public static final int T__192=192;
    public static final int RULE_ANY_OTHER=29;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalFmlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalFmlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalFmlParser.tokenNames; }
    public String getGrammarFileName() { return "InternalFml.g"; }



     	private FmlGrammarAccess grammarAccess;

        public InternalFmlParser(TokenStream input, FmlGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "FamiliarScript";
       	}

       	@Override
       	protected FmlGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleFamiliarScript"
    // InternalFml.g:65:1: entryRuleFamiliarScript returns [EObject current=null] : iv_ruleFamiliarScript= ruleFamiliarScript EOF ;
    public final EObject entryRuleFamiliarScript() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFamiliarScript = null;


        try {
            // InternalFml.g:65:55: (iv_ruleFamiliarScript= ruleFamiliarScript EOF )
            // InternalFml.g:66:2: iv_ruleFamiliarScript= ruleFamiliarScript EOF
            {
             newCompositeNode(grammarAccess.getFamiliarScriptRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFamiliarScript=ruleFamiliarScript();

            state._fsp--;

             current =iv_ruleFamiliarScript; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFamiliarScript"


    // $ANTLR start "ruleFamiliarScript"
    // InternalFml.g:72:1: ruleFamiliarScript returns [EObject current=null] : ( ( (lv_params_0_0= ruleParameter ) )* ( (lv_cmds_1_0= ruleScriptCommand ) )* ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )* ) ;
    public final EObject ruleFamiliarScript() throws RecognitionException {
        EObject current = null;

        EObject lv_params_0_0 = null;

        EObject lv_cmds_1_0 = null;

        EObject lv_exports_2_1 = null;

        EObject lv_exports_2_2 = null;



        	enterRule();

        try {
            // InternalFml.g:78:2: ( ( ( (lv_params_0_0= ruleParameter ) )* ( (lv_cmds_1_0= ruleScriptCommand ) )* ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )* ) )
            // InternalFml.g:79:2: ( ( (lv_params_0_0= ruleParameter ) )* ( (lv_cmds_1_0= ruleScriptCommand ) )* ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )* )
            {
            // InternalFml.g:79:2: ( ( (lv_params_0_0= ruleParameter ) )* ( (lv_cmds_1_0= ruleScriptCommand ) )* ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )* )
            // InternalFml.g:80:3: ( (lv_params_0_0= ruleParameter ) )* ( (lv_cmds_1_0= ruleScriptCommand ) )* ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )*
            {
            // InternalFml.g:80:3: ( (lv_params_0_0= ruleParameter ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==88) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalFml.g:81:4: (lv_params_0_0= ruleParameter )
            	    {
            	    // InternalFml.g:81:4: (lv_params_0_0= ruleParameter )
            	    // InternalFml.g:82:5: lv_params_0_0= ruleParameter
            	    {

            	    					newCompositeNode(grammarAccess.getFamiliarScriptAccess().getParamsParameterParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_params_0_0=ruleParameter();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFamiliarScriptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"params",
            	    						lv_params_0_0,
            	    						"org.xtext.example.mydsl.Fml.Parameter");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalFml.g:99:3: ( (lv_cmds_1_0= ruleScriptCommand ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_LEFT_HOOK||LA2_0==RULE_LEFT_PAREN||(LA2_0>=RULE_INT && LA2_0<=RULE_LEFT_BRACKET)||LA2_0==RULE_ID||(LA2_0>=31 && LA2_0<=35)||LA2_0==37||LA2_0==41||LA2_0==53||LA2_0==55||(LA2_0>=58 && LA2_0<=87)||LA2_0==90||(LA2_0>=92 && LA2_0<=93)||LA2_0==95||LA2_0==97||(LA2_0>=106 && LA2_0<=107)||(LA2_0>=110 && LA2_0<=112)||(LA2_0>=114 && LA2_0<=116)||(LA2_0>=119 && LA2_0<=162)||(LA2_0>=164 && LA2_0<=166)||LA2_0==168||(LA2_0>=170 && LA2_0<=189)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalFml.g:100:4: (lv_cmds_1_0= ruleScriptCommand )
            	    {
            	    // InternalFml.g:100:4: (lv_cmds_1_0= ruleScriptCommand )
            	    // InternalFml.g:101:5: lv_cmds_1_0= ruleScriptCommand
            	    {

            	    					newCompositeNode(grammarAccess.getFamiliarScriptAccess().getCmdsScriptCommandParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_cmds_1_0=ruleScriptCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFamiliarScriptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"cmds",
            	    						lv_cmds_1_0,
            	    						"org.xtext.example.mydsl.Fml.ScriptCommand");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalFml.g:118:3: ( ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=117 && LA4_0<=118)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalFml.g:119:4: ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) )
            	    {
            	    // InternalFml.g:119:4: ( (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden ) )
            	    // InternalFml.g:120:5: (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden )
            	    {
            	    // InternalFml.g:120:5: (lv_exports_2_1= ruleExport | lv_exports_2_2= ruleHidden )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==117) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==118) ) {
            	        alt3=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // InternalFml.g:121:6: lv_exports_2_1= ruleExport
            	            {

            	            						newCompositeNode(grammarAccess.getFamiliarScriptAccess().getExportsExportParserRuleCall_2_0_0());
            	            					
            	            pushFollow(FOLLOW_5);
            	            lv_exports_2_1=ruleExport();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getFamiliarScriptRule());
            	            						}
            	            						add(
            	            							current,
            	            							"exports",
            	            							lv_exports_2_1,
            	            							"org.xtext.example.mydsl.Fml.Export");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }
            	            break;
            	        case 2 :
            	            // InternalFml.g:137:6: lv_exports_2_2= ruleHidden
            	            {

            	            						newCompositeNode(grammarAccess.getFamiliarScriptAccess().getExportsHiddenParserRuleCall_2_0_1());
            	            					
            	            pushFollow(FOLLOW_5);
            	            lv_exports_2_2=ruleHidden();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getFamiliarScriptRule());
            	            						}
            	            						add(
            	            							current,
            	            							"exports",
            	            							lv_exports_2_2,
            	            							"org.xtext.example.mydsl.Fml.Hidden");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFamiliarScript"


    // $ANTLR start "entryRuleScriptCommand"
    // InternalFml.g:159:1: entryRuleScriptCommand returns [EObject current=null] : iv_ruleScriptCommand= ruleScriptCommand EOF ;
    public final EObject entryRuleScriptCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScriptCommand = null;


        try {
            // InternalFml.g:159:54: (iv_ruleScriptCommand= ruleScriptCommand EOF )
            // InternalFml.g:160:2: iv_ruleScriptCommand= ruleScriptCommand EOF
            {
             newCompositeNode(grammarAccess.getScriptCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScriptCommand=ruleScriptCommand();

            state._fsp--;

             current =iv_ruleScriptCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScriptCommand"


    // $ANTLR start "ruleScriptCommand"
    // InternalFml.g:166:1: ruleScriptCommand returns [EObject current=null] : ( ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) ) | this_ComplexCommand_7= ruleComplexCommand ) ;
    public final EObject ruleScriptCommand() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_HOOK_1=null;
        Token this_META_ATTRIBUTE_SYMBOL_2=null;
        Token this_RIGHT_HOOK_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_var_0_0 = null;

        EObject lv_metaID_3_0 = null;

        EObject lv_cmd_6_0 = null;

        EObject this_ComplexCommand_7 = null;



        	enterRule();

        try {
            // InternalFml.g:172:2: ( ( ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) ) | this_ComplexCommand_7= ruleComplexCommand ) )
            // InternalFml.g:173:2: ( ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) ) | this_ComplexCommand_7= ruleComplexCommand )
            {
            // InternalFml.g:173:2: ( ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) ) | this_ComplexCommand_7= ruleComplexCommand )
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalFml.g:174:3: ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) )
                    {
                    // InternalFml.g:174:3: ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) )
                    // InternalFml.g:175:4: ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) )
                    {
                    // InternalFml.g:175:4: ( (lv_var_0_0= ruleFML_IDENTIFIER ) )
                    // InternalFml.g:176:5: (lv_var_0_0= ruleFML_IDENTIFIER )
                    {
                    // InternalFml.g:176:5: (lv_var_0_0= ruleFML_IDENTIFIER )
                    // InternalFml.g:177:6: lv_var_0_0= ruleFML_IDENTIFIER
                    {

                    						newCompositeNode(grammarAccess.getScriptCommandAccess().getVarFML_IDENTIFIERParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_6);
                    lv_var_0_0=ruleFML_IDENTIFIER();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getScriptCommandRule());
                    						}
                    						set(
                    							current,
                    							"var",
                    							lv_var_0_0,
                    							"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalFml.g:194:4: (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==RULE_LEFT_HOOK) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // InternalFml.g:195:5: this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK
                            {
                            this_LEFT_HOOK_1=(Token)match(input,RULE_LEFT_HOOK,FOLLOW_7); 

                            					newLeafNode(this_LEFT_HOOK_1, grammarAccess.getScriptCommandAccess().getLEFT_HOOKTerminalRuleCall_0_1_0());
                            				
                            this_META_ATTRIBUTE_SYMBOL_2=(Token)match(input,RULE_META_ATTRIBUTE_SYMBOL,FOLLOW_8); 

                            					newLeafNode(this_META_ATTRIBUTE_SYMBOL_2, grammarAccess.getScriptCommandAccess().getMETA_ATTRIBUTE_SYMBOLTerminalRuleCall_0_1_1());
                            				
                            // InternalFml.g:203:5: ( (lv_metaID_3_0= ruleStringExpr ) )
                            // InternalFml.g:204:6: (lv_metaID_3_0= ruleStringExpr )
                            {
                            // InternalFml.g:204:6: (lv_metaID_3_0= ruleStringExpr )
                            // InternalFml.g:205:7: lv_metaID_3_0= ruleStringExpr
                            {

                            							newCompositeNode(grammarAccess.getScriptCommandAccess().getMetaIDStringExprParserRuleCall_0_1_2_0());
                            						
                            pushFollow(FOLLOW_9);
                            lv_metaID_3_0=ruleStringExpr();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getScriptCommandRule());
                            							}
                            							set(
                            								current,
                            								"metaID",
                            								lv_metaID_3_0,
                            								"org.xtext.example.mydsl.Fml.StringExpr");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }

                            this_RIGHT_HOOK_4=(Token)match(input,RULE_RIGHT_HOOK,FOLLOW_10); 

                            					newLeafNode(this_RIGHT_HOOK_4, grammarAccess.getScriptCommandAccess().getRIGHT_HOOKTerminalRuleCall_0_1_3());
                            				

                            }
                            break;

                    }

                    otherlv_5=(Token)match(input,30,FOLLOW_11); 

                    				newLeafNode(otherlv_5, grammarAccess.getScriptCommandAccess().getEqualsSignKeyword_0_2());
                    			
                    // InternalFml.g:231:4: ( (lv_cmd_6_0= ruleComplexCommand ) )
                    // InternalFml.g:232:5: (lv_cmd_6_0= ruleComplexCommand )
                    {
                    // InternalFml.g:232:5: (lv_cmd_6_0= ruleComplexCommand )
                    // InternalFml.g:233:6: lv_cmd_6_0= ruleComplexCommand
                    {

                    						newCompositeNode(grammarAccess.getScriptCommandAccess().getCmdComplexCommandParserRuleCall_0_3_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_cmd_6_0=ruleComplexCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getScriptCommandRule());
                    						}
                    						set(
                    							current,
                    							"cmd",
                    							lv_cmd_6_0,
                    							"org.xtext.example.mydsl.Fml.ComplexCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:252:3: this_ComplexCommand_7= ruleComplexCommand
                    {

                    			newCompositeNode(grammarAccess.getScriptCommandAccess().getComplexCommandParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComplexCommand_7=ruleComplexCommand();

                    state._fsp--;


                    			current = this_ComplexCommand_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScriptCommand"


    // $ANTLR start "entryRuleComplexCommand"
    // InternalFml.g:264:1: entryRuleComplexCommand returns [EObject current=null] : iv_ruleComplexCommand= ruleComplexCommand EOF ;
    public final EObject entryRuleComplexCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComplexCommand = null;


        try {
            // InternalFml.g:264:55: (iv_ruleComplexCommand= ruleComplexCommand EOF )
            // InternalFml.g:265:2: iv_ruleComplexCommand= ruleComplexCommand EOF
            {
             newCompositeNode(grammarAccess.getComplexCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComplexCommand=ruleComplexCommand();

            state._fsp--;

             current =iv_ruleComplexCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComplexCommand"


    // $ANTLR start "ruleComplexCommand"
    // InternalFml.g:271:1: ruleComplexCommand returns [EObject current=null] : ( ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? ) | ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) ) ) ;
    public final EObject ruleComplexCommand() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_op_2_4=null;
        Token lv_op_2_5=null;
        Token lv_not_13_0=null;
        EObject lv_left_0_0 = null;

        EObject lv_right_3_0 = null;

        Enumerator lv_op_5_0 = null;

        EObject lv_right_6_0 = null;

        Enumerator lv_cmpOp_8_0 = null;

        EObject lv_right_9_0 = null;

        Enumerator lv_sop_11_0 = null;

        EObject lv_right_12_0 = null;

        EObject lv_batom_14_0 = null;



        	enterRule();

        try {
            // InternalFml.g:277:2: ( ( ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? ) | ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) ) ) )
            // InternalFml.g:278:2: ( ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? ) | ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) ) )
            {
            // InternalFml.g:278:2: ( ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? ) | ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_LEFT_HOOK||LA9_0==RULE_LEFT_PAREN||(LA9_0>=RULE_INT && LA9_0<=RULE_LEFT_BRACKET)||LA9_0==RULE_ID||(LA9_0>=32 && LA9_0<=35)||LA9_0==37||LA9_0==41||LA9_0==53||LA9_0==55||(LA9_0>=58 && LA9_0<=87)||LA9_0==90||(LA9_0>=92 && LA9_0<=93)||LA9_0==95||LA9_0==97||(LA9_0>=106 && LA9_0<=107)||(LA9_0>=110 && LA9_0<=112)||(LA9_0>=114 && LA9_0<=116)||(LA9_0>=119 && LA9_0<=162)||(LA9_0>=164 && LA9_0<=166)||LA9_0==168||(LA9_0>=170 && LA9_0<=189)) ) {
                alt9=1;
            }
            else if ( (LA9_0==31) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalFml.g:279:3: ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? )
                    {
                    // InternalFml.g:279:3: ( ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )? )
                    // InternalFml.g:280:4: ( (lv_left_0_0= ruleCommand ) ) ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )?
                    {
                    // InternalFml.g:280:4: ( (lv_left_0_0= ruleCommand ) )
                    // InternalFml.g:281:5: (lv_left_0_0= ruleCommand )
                    {
                    // InternalFml.g:281:5: (lv_left_0_0= ruleCommand )
                    // InternalFml.g:282:6: lv_left_0_0= ruleCommand
                    {

                    						newCompositeNode(grammarAccess.getComplexCommandAccess().getLeftCommandParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_left_0_0=ruleCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                    						}
                    						set(
                    							current,
                    							"left",
                    							lv_left_0_0,
                    							"org.xtext.example.mydsl.Fml.Command");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalFml.g:299:4: ( ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) ) | ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) ) | ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) ) | ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) ) )?
                    int alt8=5;
                    switch ( input.LA(1) ) {
                        case RULE_PLUS:
                        case RULE_MINUS:
                        case RULE_MULT:
                        case RULE_DIV:
                        case RULE_EXP:
                            {
                            alt8=1;
                            }
                            break;
                        case 229:
                        case 230:
                            {
                            alt8=2;
                            }
                            break;
                        case 203:
                        case 204:
                        case 205:
                        case 206:
                        case 207:
                        case 208:
                            {
                            alt8=3;
                            }
                            break;
                        case 209:
                        case 210:
                            {
                            alt8=4;
                            }
                            break;
                    }

                    switch (alt8) {
                        case 1 :
                            // InternalFml.g:300:5: ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) )
                            {
                            // InternalFml.g:300:5: ( () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) ) )
                            // InternalFml.g:301:6: () ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) ) ( (lv_right_3_0= ruleIntegerCommand ) )
                            {
                            // InternalFml.g:301:6: ()
                            // InternalFml.g:302:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getComplexCommandAccess().getIntegerOperationLeftAction_0_1_0_0(),
                            								current);
                            						

                            }

                            // InternalFml.g:308:6: ( ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) ) )
                            // InternalFml.g:309:7: ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) )
                            {
                            // InternalFml.g:309:7: ( (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP ) )
                            // InternalFml.g:310:8: (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP )
                            {
                            // InternalFml.g:310:8: (lv_op_2_1= RULE_PLUS | lv_op_2_2= RULE_MINUS | lv_op_2_3= RULE_MULT | lv_op_2_4= RULE_DIV | lv_op_2_5= RULE_EXP )
                            int alt7=5;
                            switch ( input.LA(1) ) {
                            case RULE_PLUS:
                                {
                                alt7=1;
                                }
                                break;
                            case RULE_MINUS:
                                {
                                alt7=2;
                                }
                                break;
                            case RULE_MULT:
                                {
                                alt7=3;
                                }
                                break;
                            case RULE_DIV:
                                {
                                alt7=4;
                                }
                                break;
                            case RULE_EXP:
                                {
                                alt7=5;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 0, input);

                                throw nvae;
                            }

                            switch (alt7) {
                                case 1 :
                                    // InternalFml.g:311:9: lv_op_2_1= RULE_PLUS
                                    {
                                    lv_op_2_1=(Token)match(input,RULE_PLUS,FOLLOW_13); 

                                    									newLeafNode(lv_op_2_1, grammarAccess.getComplexCommandAccess().getOpPLUSTerminalRuleCall_0_1_0_1_0_0());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getComplexCommandRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"op",
                                    										lv_op_2_1,
                                    										"org.xtext.example.mydsl.Fml.PLUS");
                                    								

                                    }
                                    break;
                                case 2 :
                                    // InternalFml.g:326:9: lv_op_2_2= RULE_MINUS
                                    {
                                    lv_op_2_2=(Token)match(input,RULE_MINUS,FOLLOW_13); 

                                    									newLeafNode(lv_op_2_2, grammarAccess.getComplexCommandAccess().getOpMINUSTerminalRuleCall_0_1_0_1_0_1());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getComplexCommandRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"op",
                                    										lv_op_2_2,
                                    										"org.xtext.example.mydsl.Fml.MINUS");
                                    								

                                    }
                                    break;
                                case 3 :
                                    // InternalFml.g:341:9: lv_op_2_3= RULE_MULT
                                    {
                                    lv_op_2_3=(Token)match(input,RULE_MULT,FOLLOW_13); 

                                    									newLeafNode(lv_op_2_3, grammarAccess.getComplexCommandAccess().getOpMULTTerminalRuleCall_0_1_0_1_0_2());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getComplexCommandRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"op",
                                    										lv_op_2_3,
                                    										"org.xtext.example.mydsl.Fml.MULT");
                                    								

                                    }
                                    break;
                                case 4 :
                                    // InternalFml.g:356:9: lv_op_2_4= RULE_DIV
                                    {
                                    lv_op_2_4=(Token)match(input,RULE_DIV,FOLLOW_13); 

                                    									newLeafNode(lv_op_2_4, grammarAccess.getComplexCommandAccess().getOpDIVTerminalRuleCall_0_1_0_1_0_3());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getComplexCommandRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"op",
                                    										lv_op_2_4,
                                    										"org.xtext.example.mydsl.Fml.DIV");
                                    								

                                    }
                                    break;
                                case 5 :
                                    // InternalFml.g:371:9: lv_op_2_5= RULE_EXP
                                    {
                                    lv_op_2_5=(Token)match(input,RULE_EXP,FOLLOW_13); 

                                    									newLeafNode(lv_op_2_5, grammarAccess.getComplexCommandAccess().getOpEXPTerminalRuleCall_0_1_0_1_0_4());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getComplexCommandRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"op",
                                    										lv_op_2_5,
                                    										"org.xtext.example.mydsl.Fml.EXP");
                                    								

                                    }
                                    break;

                            }


                            }


                            }

                            // InternalFml.g:388:6: ( (lv_right_3_0= ruleIntegerCommand ) )
                            // InternalFml.g:389:7: (lv_right_3_0= ruleIntegerCommand )
                            {
                            // InternalFml.g:389:7: (lv_right_3_0= ruleIntegerCommand )
                            // InternalFml.g:390:8: lv_right_3_0= ruleIntegerCommand
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getRightIntegerCommandParserRuleCall_0_1_0_2_0());
                            							
                            pushFollow(FOLLOW_2);
                            lv_right_3_0=ruleIntegerCommand();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"right",
                            									lv_right_3_0,
                            									"org.xtext.example.mydsl.Fml.IntegerCommand");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalFml.g:409:5: ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) )
                            {
                            // InternalFml.g:409:5: ( () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) ) )
                            // InternalFml.g:410:6: () ( (lv_op_5_0= ruleBOOL_Operator ) ) ( (lv_right_6_0= ruleComplexCommand ) )
                            {
                            // InternalFml.g:410:6: ()
                            // InternalFml.g:411:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getComplexCommandAccess().getBoolOperationLeftAction_0_1_1_0(),
                            								current);
                            						

                            }

                            // InternalFml.g:417:6: ( (lv_op_5_0= ruleBOOL_Operator ) )
                            // InternalFml.g:418:7: (lv_op_5_0= ruleBOOL_Operator )
                            {
                            // InternalFml.g:418:7: (lv_op_5_0= ruleBOOL_Operator )
                            // InternalFml.g:419:8: lv_op_5_0= ruleBOOL_Operator
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getOpBOOL_OperatorEnumRuleCall_0_1_1_1_0());
                            							
                            pushFollow(FOLLOW_11);
                            lv_op_5_0=ruleBOOL_Operator();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"op",
                            									lv_op_5_0,
                            									"org.xtext.example.mydsl.Fml.BOOL_Operator");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }

                            // InternalFml.g:436:6: ( (lv_right_6_0= ruleComplexCommand ) )
                            // InternalFml.g:437:7: (lv_right_6_0= ruleComplexCommand )
                            {
                            // InternalFml.g:437:7: (lv_right_6_0= ruleComplexCommand )
                            // InternalFml.g:438:8: lv_right_6_0= ruleComplexCommand
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getRightComplexCommandParserRuleCall_0_1_1_2_0());
                            							
                            pushFollow(FOLLOW_2);
                            lv_right_6_0=ruleComplexCommand();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"right",
                            									lv_right_6_0,
                            									"org.xtext.example.mydsl.Fml.ComplexCommand");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }


                            }
                            break;
                        case 3 :
                            // InternalFml.g:457:5: ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) )
                            {
                            // InternalFml.g:457:5: ( () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) ) )
                            // InternalFml.g:458:6: () ( (lv_cmpOp_8_0= ruleComparisonOperator ) ) ( (lv_right_9_0= ruleComplexCommand ) )
                            {
                            // InternalFml.g:458:6: ()
                            // InternalFml.g:459:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getComplexCommandAccess().getComparisonOperationLeftAction_0_1_2_0(),
                            								current);
                            						

                            }

                            // InternalFml.g:465:6: ( (lv_cmpOp_8_0= ruleComparisonOperator ) )
                            // InternalFml.g:466:7: (lv_cmpOp_8_0= ruleComparisonOperator )
                            {
                            // InternalFml.g:466:7: (lv_cmpOp_8_0= ruleComparisonOperator )
                            // InternalFml.g:467:8: lv_cmpOp_8_0= ruleComparisonOperator
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getCmpOpComparisonOperatorEnumRuleCall_0_1_2_1_0());
                            							
                            pushFollow(FOLLOW_11);
                            lv_cmpOp_8_0=ruleComparisonOperator();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"cmpOp",
                            									lv_cmpOp_8_0,
                            									"org.xtext.example.mydsl.Fml.ComparisonOperator");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }

                            // InternalFml.g:484:6: ( (lv_right_9_0= ruleComplexCommand ) )
                            // InternalFml.g:485:7: (lv_right_9_0= ruleComplexCommand )
                            {
                            // InternalFml.g:485:7: (lv_right_9_0= ruleComplexCommand )
                            // InternalFml.g:486:8: lv_right_9_0= ruleComplexCommand
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getRightComplexCommandParserRuleCall_0_1_2_2_0());
                            							
                            pushFollow(FOLLOW_2);
                            lv_right_9_0=ruleComplexCommand();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"right",
                            									lv_right_9_0,
                            									"org.xtext.example.mydsl.Fml.ComplexCommand");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }


                            }
                            break;
                        case 4 :
                            // InternalFml.g:505:5: ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) )
                            {
                            // InternalFml.g:505:5: ( () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) ) )
                            // InternalFml.g:506:6: () ( (lv_sop_11_0= ruleSetOperator ) ) ( (lv_right_12_0= ruleComplexCommand ) )
                            {
                            // InternalFml.g:506:6: ()
                            // InternalFml.g:507:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getComplexCommandAccess().getSetOperationLeftAction_0_1_3_0(),
                            								current);
                            						

                            }

                            // InternalFml.g:513:6: ( (lv_sop_11_0= ruleSetOperator ) )
                            // InternalFml.g:514:7: (lv_sop_11_0= ruleSetOperator )
                            {
                            // InternalFml.g:514:7: (lv_sop_11_0= ruleSetOperator )
                            // InternalFml.g:515:8: lv_sop_11_0= ruleSetOperator
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getSopSetOperatorEnumRuleCall_0_1_3_1_0());
                            							
                            pushFollow(FOLLOW_11);
                            lv_sop_11_0=ruleSetOperator();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"sop",
                            									lv_sop_11_0,
                            									"org.xtext.example.mydsl.Fml.SetOperator");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }

                            // InternalFml.g:532:6: ( (lv_right_12_0= ruleComplexCommand ) )
                            // InternalFml.g:533:7: (lv_right_12_0= ruleComplexCommand )
                            {
                            // InternalFml.g:533:7: (lv_right_12_0= ruleComplexCommand )
                            // InternalFml.g:534:8: lv_right_12_0= ruleComplexCommand
                            {

                            								newCompositeNode(grammarAccess.getComplexCommandAccess().getRightComplexCommandParserRuleCall_0_1_3_2_0());
                            							
                            pushFollow(FOLLOW_2);
                            lv_right_12_0=ruleComplexCommand();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                            								}
                            								set(
                            									current,
                            									"right",
                            									lv_right_12_0,
                            									"org.xtext.example.mydsl.Fml.ComplexCommand");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:555:3: ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) )
                    {
                    // InternalFml.g:555:3: ( ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) ) )
                    // InternalFml.g:556:4: ( (lv_not_13_0= 'not' ) ) ( (lv_batom_14_0= ruleComplexCommand ) )
                    {
                    // InternalFml.g:556:4: ( (lv_not_13_0= 'not' ) )
                    // InternalFml.g:557:5: (lv_not_13_0= 'not' )
                    {
                    // InternalFml.g:557:5: (lv_not_13_0= 'not' )
                    // InternalFml.g:558:6: lv_not_13_0= 'not'
                    {
                    lv_not_13_0=(Token)match(input,31,FOLLOW_11); 

                    						newLeafNode(lv_not_13_0, grammarAccess.getComplexCommandAccess().getNotNotKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getComplexCommandRule());
                    						}
                    						setWithLastConsumed(current, "not", true, "not");
                    					

                    }


                    }

                    // InternalFml.g:570:4: ( (lv_batom_14_0= ruleComplexCommand ) )
                    // InternalFml.g:571:5: (lv_batom_14_0= ruleComplexCommand )
                    {
                    // InternalFml.g:571:5: (lv_batom_14_0= ruleComplexCommand )
                    // InternalFml.g:572:6: lv_batom_14_0= ruleComplexCommand
                    {

                    						newCompositeNode(grammarAccess.getComplexCommandAccess().getBatomComplexCommandParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_batom_14_0=ruleComplexCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getComplexCommandRule());
                    						}
                    						set(
                    							current,
                    							"batom",
                    							lv_batom_14_0,
                    							"org.xtext.example.mydsl.Fml.ComplexCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComplexCommand"


    // $ANTLR start "entryRuleCommand"
    // InternalFml.g:594:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalFml.g:594:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalFml.g:595:2: iv_ruleCommand= ruleCommand EOF
            {
             newCompositeNode(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCommand=ruleCommand();

            state._fsp--;

             current =iv_ruleCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalFml.g:601:1: ruleCommand returns [EObject current=null] : ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN ) | (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand ) ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_PAREN_0=null;
        Token this_RIGHT_PAREN_2=null;
        EObject this_ComplexCommand_1 = null;

        EObject this_StringExpr_3 = null;

        EObject this_SetExpr_4 = null;

        EObject this_BooleanExpr_5 = null;

        EObject this_IdentifierExpr_6 = null;

        EObject this_IntegerExpr_7 = null;

        EObject this_FeatureVariabilityOperator_8 = null;

        EObject this_IfCondition_9 = null;

        EObject this_ForeachSet_10 = null;

        EObject this_FeatureModel_11 = null;

        EObject this_AddConstraint_12 = null;

        EObject this_RemoveConstraint_13 = null;

        EObject this_SetOperations_14 = null;

        EObject this_AnalysisOperation_15 = null;

        EObject this_FeatureOperation_16 = null;

        EObject this_StringOperation_17 = null;

        EObject this_Compare_18 = null;

        EObject this_LoadGeneric_19 = null;

        EObject this_Merge_20 = null;

        EObject this_AggregateMerge_21 = null;

        EObject this_Synthesis_22 = null;

        EObject this_Hierarchy_23 = null;

        EObject this_FeatureModelOperation_24 = null;

        EObject this_Aggregate_25 = null;

        EObject this_Slice_26 = null;

        EObject this_Map_27 = null;

        EObject this_UnMap_28 = null;

        EObject this_AtomicConstraintExpr_29 = null;

        EObject this_ConstraintExpr_30 = null;

        EObject this_GetConstraints_31 = null;

        EObject this_ComputeConstraints_32 = null;

        EObject this_GetFGroups_33 = null;

        EObject this_ComputeFGroups_34 = null;

        EObject this_VariableNull_35 = null;

        EObject this_Cores_36 = null;

        EObject this_Deads_37 = null;

        EObject this_Cliques_38 = null;

        EObject this_Leaves_39 = null;

        EObject this_FullMandatorys_40 = null;

        EObject this_PrinterUtility_41 = null;

        EObject this_Convert_42 = null;

        EObject this_Assertion_43 = null;

        EObject this_GDisplay_44 = null;

        EObject this_GListing_45 = null;

        EObject this_CleanUp_46 = null;

        EObject this_AsFM_47 = null;

        EObject this_ModifyVOperator_48 = null;

        EObject this_FMLSave_49 = null;

        EObject this_ConfigurationCmd_50 = null;

        EObject this_ScriptDefinition_51 = null;

        EObject this_Shell_52 = null;

        EObject this_CopyVariable_53 = null;

        EObject this_RemoveVariable_54 = null;

        EObject this_CTCRCommand_55 = null;

        EObject this_PairwiseCommand_56 = null;



        	enterRule();

        try {
            // InternalFml.g:607:2: ( ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN ) | (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand ) ) )
            // InternalFml.g:608:2: ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN ) | (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand ) )
            {
            // InternalFml.g:608:2: ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN ) | (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_LEFT_PAREN) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_LEFT_HOOK||(LA11_0>=RULE_INT && LA11_0<=RULE_LEFT_BRACKET)||LA11_0==RULE_ID||(LA11_0>=32 && LA11_0<=35)||LA11_0==37||LA11_0==41||LA11_0==53||LA11_0==55||(LA11_0>=58 && LA11_0<=87)||LA11_0==90||(LA11_0>=92 && LA11_0<=93)||LA11_0==95||LA11_0==97||(LA11_0>=106 && LA11_0<=107)||(LA11_0>=110 && LA11_0<=112)||(LA11_0>=114 && LA11_0<=116)||(LA11_0>=119 && LA11_0<=162)||(LA11_0>=164 && LA11_0<=166)||LA11_0==168||(LA11_0>=170 && LA11_0<=189)) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalFml.g:609:3: (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN )
                    {
                    // InternalFml.g:609:3: (this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN )
                    // InternalFml.g:610:4: this_LEFT_PAREN_0= RULE_LEFT_PAREN this_ComplexCommand_1= ruleComplexCommand this_RIGHT_PAREN_2= RULE_RIGHT_PAREN
                    {
                    this_LEFT_PAREN_0=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_11); 

                    				newLeafNode(this_LEFT_PAREN_0, grammarAccess.getCommandAccess().getLEFT_PARENTerminalRuleCall_0_0());
                    			

                    				newCompositeNode(grammarAccess.getCommandAccess().getComplexCommandParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_14);
                    this_ComplexCommand_1=ruleComplexCommand();

                    state._fsp--;


                    				current = this_ComplexCommand_1;
                    				afterParserOrEnumRuleCall();
                    			
                    this_RIGHT_PAREN_2=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

                    				newLeafNode(this_RIGHT_PAREN_2, grammarAccess.getCommandAccess().getRIGHT_PARENTerminalRuleCall_0_2());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:628:3: (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand )
                    {
                    // InternalFml.g:628:3: (this_StringExpr_3= ruleStringExpr | this_SetExpr_4= ruleSetExpr | this_BooleanExpr_5= ruleBooleanExpr | this_IdentifierExpr_6= ruleIdentifierExpr | this_IntegerExpr_7= ruleIntegerExpr | this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator | this_IfCondition_9= ruleIfCondition | this_ForeachSet_10= ruleForeachSet | this_FeatureModel_11= ruleFeatureModel | this_AddConstraint_12= ruleAddConstraint | this_RemoveConstraint_13= ruleRemoveConstraint | this_SetOperations_14= ruleSetOperations | this_AnalysisOperation_15= ruleAnalysisOperation | this_FeatureOperation_16= ruleFeatureOperation | this_StringOperation_17= ruleStringOperation | this_Compare_18= ruleCompare | this_LoadGeneric_19= ruleLoadGeneric | this_Merge_20= ruleMerge | this_AggregateMerge_21= ruleAggregateMerge | this_Synthesis_22= ruleSynthesis | this_Hierarchy_23= ruleHierarchy | this_FeatureModelOperation_24= ruleFeatureModelOperation | this_Aggregate_25= ruleAggregate | this_Slice_26= ruleSlice | this_Map_27= ruleMap | this_UnMap_28= ruleUnMap | this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr | this_ConstraintExpr_30= ruleConstraintExpr | this_GetConstraints_31= ruleGetConstraints | this_ComputeConstraints_32= ruleComputeConstraints | this_GetFGroups_33= ruleGetFGroups | this_ComputeFGroups_34= ruleComputeFGroups | this_VariableNull_35= ruleVariableNull | this_Cores_36= ruleCores | this_Deads_37= ruleDeads | this_Cliques_38= ruleCliques | this_Leaves_39= ruleLeaves | this_FullMandatorys_40= ruleFullMandatorys | this_PrinterUtility_41= rulePrinterUtility | this_Convert_42= ruleConvert | this_Assertion_43= ruleAssertion | this_GDisplay_44= ruleGDisplay | this_GListing_45= ruleGListing | this_CleanUp_46= ruleCleanUp | this_AsFM_47= ruleAsFM | this_ModifyVOperator_48= ruleModifyVOperator | this_FMLSave_49= ruleFMLSave | this_ConfigurationCmd_50= ruleConfigurationCmd | this_ScriptDefinition_51= ruleScriptDefinition | this_Shell_52= ruleShell | this_CopyVariable_53= ruleCopyVariable | this_RemoveVariable_54= ruleRemoveVariable | this_CTCRCommand_55= ruleCTCRCommand | this_PairwiseCommand_56= rulePairwiseCommand )
                    int alt10=54;
                    switch ( input.LA(1) ) {
                    case RULE_STRING:
                        {
                        alt10=1;
                        }
                        break;
                    case RULE_LEFT_BRACKET:
                        {
                        alt10=2;
                        }
                        break;
                    case 32:
                    case 33:
                        {
                        alt10=3;
                        }
                        break;
                    case RULE_ID:
                    case 168:
                        {
                        alt10=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt10=5;
                        }
                        break;
                    case 170:
                    case 171:
                    case 172:
                    case 173:
                    case 174:
                        {
                        alt10=6;
                        }
                        break;
                    case 37:
                        {
                        alt10=7;
                        }
                        break;
                    case 41:
                        {
                        alt10=8;
                        }
                        break;
                    case 165:
                    case 166:
                        {
                        alt10=9;
                        }
                        break;
                    case 162:
                        {
                        alt10=10;
                        }
                        break;
                    case 164:
                        {
                        alt10=11;
                        }
                        break;
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                        {
                        alt10=12;
                        }
                        break;
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                        {
                        alt10=13;
                        }
                        break;
                    case 74:
                    case 75:
                    case 76:
                    case 77:
                    case 78:
                    case 79:
                    case 80:
                    case 81:
                        {
                        alt10=14;
                        }
                        break;
                    case 82:
                    case 83:
                    case 84:
                    case 85:
                    case 86:
                        {
                        alt10=15;
                        }
                        break;
                    case 87:
                        {
                        alt10=16;
                        }
                        break;
                    case 90:
                        {
                        alt10=17;
                        }
                        break;
                    case 93:
                        {
                        alt10=18;
                        }
                        break;
                    case 95:
                        {
                        alt10=19;
                        }
                        break;
                    case 97:
                        {
                        alt10=20;
                        }
                        break;
                    case 152:
                        {
                        alt10=21;
                        }
                        break;
                    case 110:
                    case 111:
                    case 112:
                    case 114:
                        {
                        alt10=22;
                        }
                        break;
                    case 107:
                        {
                        alt10=23;
                        }
                        break;
                    case 106:
                        {
                        alt10=24;
                        }
                        break;
                    case 129:
                        {
                        alt10=25;
                        }
                        break;
                    case 130:
                        {
                        alt10=26;
                        }
                        break;
                    case 34:
                        {
                        alt10=27;
                        }
                        break;
                    case 35:
                        {
                        alt10=28;
                        }
                        break;
                    case 175:
                    case 176:
                    case 177:
                    case 178:
                    case 179:
                    case 180:
                        {
                        alt10=29;
                        }
                        break;
                    case 181:
                    case 182:
                    case 183:
                        {
                        alt10=30;
                        }
                        break;
                    case 184:
                    case 185:
                    case 186:
                        {
                        alt10=31;
                        }
                        break;
                    case 187:
                    case 188:
                    case 189:
                        {
                        alt10=32;
                        }
                        break;
                    case 116:
                        {
                        alt10=33;
                        }
                        break;
                    case 132:
                        {
                        alt10=34;
                        }
                        break;
                    case 133:
                        {
                        alt10=35;
                        }
                        break;
                    case 136:
                        {
                        alt10=36;
                        }
                        break;
                    case 53:
                        {
                        alt10=37;
                        }
                        break;
                    case 134:
                    case 135:
                        {
                        alt10=38;
                        }
                        break;
                    case 153:
                    case 154:
                        {
                        alt10=39;
                        }
                        break;
                    case 149:
                        {
                        alt10=40;
                        }
                        break;
                    case 115:
                        {
                        alt10=41;
                        }
                        break;
                    case 155:
                        {
                        alt10=42;
                        }
                        break;
                    case 156:
                    case 157:
                        {
                        alt10=43;
                        }
                        break;
                    case 131:
                        {
                        alt10=44;
                        }
                        break;
                    case 128:
                        {
                        alt10=45;
                        }
                        break;
                    case 158:
                    case 159:
                    case 160:
                    case 161:
                        {
                        alt10=46;
                        }
                        break;
                    case 150:
                    case 151:
                        {
                        alt10=47;
                        }
                        break;
                    case 119:
                    case 120:
                    case 121:
                    case 122:
                    case 123:
                    case 124:
                    case 125:
                    case 126:
                    case 127:
                        {
                        alt10=48;
                        }
                        break;
                    case RULE_LEFT_HOOK:
                        {
                        alt10=49;
                        }
                        break;
                    case 137:
                    case 138:
                    case 139:
                    case 140:
                    case 141:
                    case 142:
                    case 143:
                    case 144:
                        {
                        alt10=50;
                        }
                        break;
                    case 145:
                    case 146:
                        {
                        alt10=51;
                        }
                        break;
                    case 147:
                    case 148:
                        {
                        alt10=52;
                        }
                        break;
                    case 92:
                        {
                        alt10=53;
                        }
                        break;
                    case 55:
                        {
                        alt10=54;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 0, input);

                        throw nvae;
                    }

                    switch (alt10) {
                        case 1 :
                            // InternalFml.g:629:4: this_StringExpr_3= ruleStringExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getStringExprParserRuleCall_1_0());
                            			
                            pushFollow(FOLLOW_2);
                            this_StringExpr_3=ruleStringExpr();

                            state._fsp--;


                            				current = this_StringExpr_3;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 2 :
                            // InternalFml.g:638:4: this_SetExpr_4= ruleSetExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getSetExprParserRuleCall_1_1());
                            			
                            pushFollow(FOLLOW_2);
                            this_SetExpr_4=ruleSetExpr();

                            state._fsp--;


                            				current = this_SetExpr_4;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 3 :
                            // InternalFml.g:647:4: this_BooleanExpr_5= ruleBooleanExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getBooleanExprParserRuleCall_1_2());
                            			
                            pushFollow(FOLLOW_2);
                            this_BooleanExpr_5=ruleBooleanExpr();

                            state._fsp--;


                            				current = this_BooleanExpr_5;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 4 :
                            // InternalFml.g:656:4: this_IdentifierExpr_6= ruleIdentifierExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getIdentifierExprParserRuleCall_1_3());
                            			
                            pushFollow(FOLLOW_2);
                            this_IdentifierExpr_6=ruleIdentifierExpr();

                            state._fsp--;


                            				current = this_IdentifierExpr_6;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 5 :
                            // InternalFml.g:665:4: this_IntegerExpr_7= ruleIntegerExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getIntegerExprParserRuleCall_1_4());
                            			
                            pushFollow(FOLLOW_2);
                            this_IntegerExpr_7=ruleIntegerExpr();

                            state._fsp--;


                            				current = this_IntegerExpr_7;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 6 :
                            // InternalFml.g:674:4: this_FeatureVariabilityOperator_8= ruleFeatureVariabilityOperator
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFeatureVariabilityOperatorParserRuleCall_1_5());
                            			
                            pushFollow(FOLLOW_2);
                            this_FeatureVariabilityOperator_8=ruleFeatureVariabilityOperator();

                            state._fsp--;


                            				current = this_FeatureVariabilityOperator_8;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 7 :
                            // InternalFml.g:683:4: this_IfCondition_9= ruleIfCondition
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getIfConditionParserRuleCall_1_6());
                            			
                            pushFollow(FOLLOW_2);
                            this_IfCondition_9=ruleIfCondition();

                            state._fsp--;


                            				current = this_IfCondition_9;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 8 :
                            // InternalFml.g:692:4: this_ForeachSet_10= ruleForeachSet
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getForeachSetParserRuleCall_1_7());
                            			
                            pushFollow(FOLLOW_2);
                            this_ForeachSet_10=ruleForeachSet();

                            state._fsp--;


                            				current = this_ForeachSet_10;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 9 :
                            // InternalFml.g:701:4: this_FeatureModel_11= ruleFeatureModel
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFeatureModelParserRuleCall_1_8());
                            			
                            pushFollow(FOLLOW_2);
                            this_FeatureModel_11=ruleFeatureModel();

                            state._fsp--;


                            				current = this_FeatureModel_11;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 10 :
                            // InternalFml.g:710:4: this_AddConstraint_12= ruleAddConstraint
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAddConstraintParserRuleCall_1_9());
                            			
                            pushFollow(FOLLOW_2);
                            this_AddConstraint_12=ruleAddConstraint();

                            state._fsp--;


                            				current = this_AddConstraint_12;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 11 :
                            // InternalFml.g:719:4: this_RemoveConstraint_13= ruleRemoveConstraint
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getRemoveConstraintParserRuleCall_1_10());
                            			
                            pushFollow(FOLLOW_2);
                            this_RemoveConstraint_13=ruleRemoveConstraint();

                            state._fsp--;


                            				current = this_RemoveConstraint_13;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 12 :
                            // InternalFml.g:728:4: this_SetOperations_14= ruleSetOperations
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getSetOperationsParserRuleCall_1_11());
                            			
                            pushFollow(FOLLOW_2);
                            this_SetOperations_14=ruleSetOperations();

                            state._fsp--;


                            				current = this_SetOperations_14;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 13 :
                            // InternalFml.g:737:4: this_AnalysisOperation_15= ruleAnalysisOperation
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAnalysisOperationParserRuleCall_1_12());
                            			
                            pushFollow(FOLLOW_2);
                            this_AnalysisOperation_15=ruleAnalysisOperation();

                            state._fsp--;


                            				current = this_AnalysisOperation_15;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 14 :
                            // InternalFml.g:746:4: this_FeatureOperation_16= ruleFeatureOperation
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFeatureOperationParserRuleCall_1_13());
                            			
                            pushFollow(FOLLOW_2);
                            this_FeatureOperation_16=ruleFeatureOperation();

                            state._fsp--;


                            				current = this_FeatureOperation_16;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 15 :
                            // InternalFml.g:755:4: this_StringOperation_17= ruleStringOperation
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getStringOperationParserRuleCall_1_14());
                            			
                            pushFollow(FOLLOW_2);
                            this_StringOperation_17=ruleStringOperation();

                            state._fsp--;


                            				current = this_StringOperation_17;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 16 :
                            // InternalFml.g:764:4: this_Compare_18= ruleCompare
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCompareParserRuleCall_1_15());
                            			
                            pushFollow(FOLLOW_2);
                            this_Compare_18=ruleCompare();

                            state._fsp--;


                            				current = this_Compare_18;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 17 :
                            // InternalFml.g:773:4: this_LoadGeneric_19= ruleLoadGeneric
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getLoadGenericParserRuleCall_1_16());
                            			
                            pushFollow(FOLLOW_2);
                            this_LoadGeneric_19=ruleLoadGeneric();

                            state._fsp--;


                            				current = this_LoadGeneric_19;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 18 :
                            // InternalFml.g:782:4: this_Merge_20= ruleMerge
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getMergeParserRuleCall_1_17());
                            			
                            pushFollow(FOLLOW_2);
                            this_Merge_20=ruleMerge();

                            state._fsp--;


                            				current = this_Merge_20;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 19 :
                            // InternalFml.g:791:4: this_AggregateMerge_21= ruleAggregateMerge
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAggregateMergeParserRuleCall_1_18());
                            			
                            pushFollow(FOLLOW_2);
                            this_AggregateMerge_21=ruleAggregateMerge();

                            state._fsp--;


                            				current = this_AggregateMerge_21;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 20 :
                            // InternalFml.g:800:4: this_Synthesis_22= ruleSynthesis
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getSynthesisParserRuleCall_1_19());
                            			
                            pushFollow(FOLLOW_2);
                            this_Synthesis_22=ruleSynthesis();

                            state._fsp--;


                            				current = this_Synthesis_22;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 21 :
                            // InternalFml.g:809:4: this_Hierarchy_23= ruleHierarchy
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getHierarchyParserRuleCall_1_20());
                            			
                            pushFollow(FOLLOW_2);
                            this_Hierarchy_23=ruleHierarchy();

                            state._fsp--;


                            				current = this_Hierarchy_23;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 22 :
                            // InternalFml.g:818:4: this_FeatureModelOperation_24= ruleFeatureModelOperation
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFeatureModelOperationParserRuleCall_1_21());
                            			
                            pushFollow(FOLLOW_2);
                            this_FeatureModelOperation_24=ruleFeatureModelOperation();

                            state._fsp--;


                            				current = this_FeatureModelOperation_24;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 23 :
                            // InternalFml.g:827:4: this_Aggregate_25= ruleAggregate
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAggregateParserRuleCall_1_22());
                            			
                            pushFollow(FOLLOW_2);
                            this_Aggregate_25=ruleAggregate();

                            state._fsp--;


                            				current = this_Aggregate_25;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 24 :
                            // InternalFml.g:836:4: this_Slice_26= ruleSlice
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getSliceParserRuleCall_1_23());
                            			
                            pushFollow(FOLLOW_2);
                            this_Slice_26=ruleSlice();

                            state._fsp--;


                            				current = this_Slice_26;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 25 :
                            // InternalFml.g:845:4: this_Map_27= ruleMap
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getMapParserRuleCall_1_24());
                            			
                            pushFollow(FOLLOW_2);
                            this_Map_27=ruleMap();

                            state._fsp--;


                            				current = this_Map_27;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 26 :
                            // InternalFml.g:854:4: this_UnMap_28= ruleUnMap
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getUnMapParserRuleCall_1_25());
                            			
                            pushFollow(FOLLOW_2);
                            this_UnMap_28=ruleUnMap();

                            state._fsp--;


                            				current = this_UnMap_28;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 27 :
                            // InternalFml.g:863:4: this_AtomicConstraintExpr_29= ruleAtomicConstraintExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAtomicConstraintExprParserRuleCall_1_26());
                            			
                            pushFollow(FOLLOW_2);
                            this_AtomicConstraintExpr_29=ruleAtomicConstraintExpr();

                            state._fsp--;


                            				current = this_AtomicConstraintExpr_29;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 28 :
                            // InternalFml.g:872:4: this_ConstraintExpr_30= ruleConstraintExpr
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getConstraintExprParserRuleCall_1_27());
                            			
                            pushFollow(FOLLOW_2);
                            this_ConstraintExpr_30=ruleConstraintExpr();

                            state._fsp--;


                            				current = this_ConstraintExpr_30;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 29 :
                            // InternalFml.g:881:4: this_GetConstraints_31= ruleGetConstraints
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getGetConstraintsParserRuleCall_1_28());
                            			
                            pushFollow(FOLLOW_2);
                            this_GetConstraints_31=ruleGetConstraints();

                            state._fsp--;


                            				current = this_GetConstraints_31;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 30 :
                            // InternalFml.g:890:4: this_ComputeConstraints_32= ruleComputeConstraints
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getComputeConstraintsParserRuleCall_1_29());
                            			
                            pushFollow(FOLLOW_2);
                            this_ComputeConstraints_32=ruleComputeConstraints();

                            state._fsp--;


                            				current = this_ComputeConstraints_32;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 31 :
                            // InternalFml.g:899:4: this_GetFGroups_33= ruleGetFGroups
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getGetFGroupsParserRuleCall_1_30());
                            			
                            pushFollow(FOLLOW_2);
                            this_GetFGroups_33=ruleGetFGroups();

                            state._fsp--;


                            				current = this_GetFGroups_33;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 32 :
                            // InternalFml.g:908:4: this_ComputeFGroups_34= ruleComputeFGroups
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getComputeFGroupsParserRuleCall_1_31());
                            			
                            pushFollow(FOLLOW_2);
                            this_ComputeFGroups_34=ruleComputeFGroups();

                            state._fsp--;


                            				current = this_ComputeFGroups_34;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 33 :
                            // InternalFml.g:917:4: this_VariableNull_35= ruleVariableNull
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getVariableNullParserRuleCall_1_32());
                            			
                            pushFollow(FOLLOW_2);
                            this_VariableNull_35=ruleVariableNull();

                            state._fsp--;


                            				current = this_VariableNull_35;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 34 :
                            // InternalFml.g:926:4: this_Cores_36= ruleCores
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCoresParserRuleCall_1_33());
                            			
                            pushFollow(FOLLOW_2);
                            this_Cores_36=ruleCores();

                            state._fsp--;


                            				current = this_Cores_36;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 35 :
                            // InternalFml.g:935:4: this_Deads_37= ruleDeads
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getDeadsParserRuleCall_1_34());
                            			
                            pushFollow(FOLLOW_2);
                            this_Deads_37=ruleDeads();

                            state._fsp--;


                            				current = this_Deads_37;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 36 :
                            // InternalFml.g:944:4: this_Cliques_38= ruleCliques
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCliquesParserRuleCall_1_35());
                            			
                            pushFollow(FOLLOW_2);
                            this_Cliques_38=ruleCliques();

                            state._fsp--;


                            				current = this_Cliques_38;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 37 :
                            // InternalFml.g:953:4: this_Leaves_39= ruleLeaves
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getLeavesParserRuleCall_1_36());
                            			
                            pushFollow(FOLLOW_2);
                            this_Leaves_39=ruleLeaves();

                            state._fsp--;


                            				current = this_Leaves_39;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 38 :
                            // InternalFml.g:962:4: this_FullMandatorys_40= ruleFullMandatorys
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFullMandatorysParserRuleCall_1_37());
                            			
                            pushFollow(FOLLOW_2);
                            this_FullMandatorys_40=ruleFullMandatorys();

                            state._fsp--;


                            				current = this_FullMandatorys_40;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 39 :
                            // InternalFml.g:971:4: this_PrinterUtility_41= rulePrinterUtility
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getPrinterUtilityParserRuleCall_1_38());
                            			
                            pushFollow(FOLLOW_2);
                            this_PrinterUtility_41=rulePrinterUtility();

                            state._fsp--;


                            				current = this_PrinterUtility_41;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 40 :
                            // InternalFml.g:980:4: this_Convert_42= ruleConvert
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getConvertParserRuleCall_1_39());
                            			
                            pushFollow(FOLLOW_2);
                            this_Convert_42=ruleConvert();

                            state._fsp--;


                            				current = this_Convert_42;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 41 :
                            // InternalFml.g:989:4: this_Assertion_43= ruleAssertion
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAssertionParserRuleCall_1_40());
                            			
                            pushFollow(FOLLOW_2);
                            this_Assertion_43=ruleAssertion();

                            state._fsp--;


                            				current = this_Assertion_43;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 42 :
                            // InternalFml.g:998:4: this_GDisplay_44= ruleGDisplay
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getGDisplayParserRuleCall_1_41());
                            			
                            pushFollow(FOLLOW_2);
                            this_GDisplay_44=ruleGDisplay();

                            state._fsp--;


                            				current = this_GDisplay_44;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 43 :
                            // InternalFml.g:1007:4: this_GListing_45= ruleGListing
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getGListingParserRuleCall_1_42());
                            			
                            pushFollow(FOLLOW_2);
                            this_GListing_45=ruleGListing();

                            state._fsp--;


                            				current = this_GListing_45;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 44 :
                            // InternalFml.g:1016:4: this_CleanUp_46= ruleCleanUp
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCleanUpParserRuleCall_1_43());
                            			
                            pushFollow(FOLLOW_2);
                            this_CleanUp_46=ruleCleanUp();

                            state._fsp--;


                            				current = this_CleanUp_46;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 45 :
                            // InternalFml.g:1025:4: this_AsFM_47= ruleAsFM
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getAsFMParserRuleCall_1_44());
                            			
                            pushFollow(FOLLOW_2);
                            this_AsFM_47=ruleAsFM();

                            state._fsp--;


                            				current = this_AsFM_47;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 46 :
                            // InternalFml.g:1034:4: this_ModifyVOperator_48= ruleModifyVOperator
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getModifyVOperatorParserRuleCall_1_45());
                            			
                            pushFollow(FOLLOW_2);
                            this_ModifyVOperator_48=ruleModifyVOperator();

                            state._fsp--;


                            				current = this_ModifyVOperator_48;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 47 :
                            // InternalFml.g:1043:4: this_FMLSave_49= ruleFMLSave
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getFMLSaveParserRuleCall_1_46());
                            			
                            pushFollow(FOLLOW_2);
                            this_FMLSave_49=ruleFMLSave();

                            state._fsp--;


                            				current = this_FMLSave_49;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 48 :
                            // InternalFml.g:1052:4: this_ConfigurationCmd_50= ruleConfigurationCmd
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getConfigurationCmdParserRuleCall_1_47());
                            			
                            pushFollow(FOLLOW_2);
                            this_ConfigurationCmd_50=ruleConfigurationCmd();

                            state._fsp--;


                            				current = this_ConfigurationCmd_50;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 49 :
                            // InternalFml.g:1061:4: this_ScriptDefinition_51= ruleScriptDefinition
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getScriptDefinitionParserRuleCall_1_48());
                            			
                            pushFollow(FOLLOW_2);
                            this_ScriptDefinition_51=ruleScriptDefinition();

                            state._fsp--;


                            				current = this_ScriptDefinition_51;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 50 :
                            // InternalFml.g:1070:4: this_Shell_52= ruleShell
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getShellParserRuleCall_1_49());
                            			
                            pushFollow(FOLLOW_2);
                            this_Shell_52=ruleShell();

                            state._fsp--;


                            				current = this_Shell_52;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 51 :
                            // InternalFml.g:1079:4: this_CopyVariable_53= ruleCopyVariable
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCopyVariableParserRuleCall_1_50());
                            			
                            pushFollow(FOLLOW_2);
                            this_CopyVariable_53=ruleCopyVariable();

                            state._fsp--;


                            				current = this_CopyVariable_53;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 52 :
                            // InternalFml.g:1088:4: this_RemoveVariable_54= ruleRemoveVariable
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getRemoveVariableParserRuleCall_1_51());
                            			
                            pushFollow(FOLLOW_2);
                            this_RemoveVariable_54=ruleRemoveVariable();

                            state._fsp--;


                            				current = this_RemoveVariable_54;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 53 :
                            // InternalFml.g:1097:4: this_CTCRCommand_55= ruleCTCRCommand
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getCTCRCommandParserRuleCall_1_52());
                            			
                            pushFollow(FOLLOW_2);
                            this_CTCRCommand_55=ruleCTCRCommand();

                            state._fsp--;


                            				current = this_CTCRCommand_55;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;
                        case 54 :
                            // InternalFml.g:1106:4: this_PairwiseCommand_56= rulePairwiseCommand
                            {

                            				newCompositeNode(grammarAccess.getCommandAccess().getPairwiseCommandParserRuleCall_1_53());
                            			
                            pushFollow(FOLLOW_2);
                            this_PairwiseCommand_56=rulePairwiseCommand();

                            state._fsp--;


                            				current = this_PairwiseCommand_56;
                            				afterParserOrEnumRuleCall();
                            			

                            }
                            break;

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleIntegerExpr"
    // InternalFml.g:1119:1: entryRuleIntegerExpr returns [EObject current=null] : iv_ruleIntegerExpr= ruleIntegerExpr EOF ;
    public final EObject entryRuleIntegerExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerExpr = null;


        try {
            // InternalFml.g:1119:52: (iv_ruleIntegerExpr= ruleIntegerExpr EOF )
            // InternalFml.g:1120:2: iv_ruleIntegerExpr= ruleIntegerExpr EOF
            {
             newCompositeNode(grammarAccess.getIntegerExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerExpr=ruleIntegerExpr();

            state._fsp--;

             current =iv_ruleIntegerExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerExpr"


    // $ANTLR start "ruleIntegerExpr"
    // InternalFml.g:1126:1: ruleIntegerExpr returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_INT ) ) ) ;
    public final EObject ruleIntegerExpr() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalFml.g:1132:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) )
            // InternalFml.g:1133:2: ( () ( (lv_value_1_0= RULE_INT ) ) )
            {
            // InternalFml.g:1133:2: ( () ( (lv_value_1_0= RULE_INT ) ) )
            // InternalFml.g:1134:3: () ( (lv_value_1_0= RULE_INT ) )
            {
            // InternalFml.g:1134:3: ()
            // InternalFml.g:1135:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIntegerExprAccess().getIntLiteralAction_0(),
            					current);
            			

            }

            // InternalFml.g:1141:3: ( (lv_value_1_0= RULE_INT ) )
            // InternalFml.g:1142:4: (lv_value_1_0= RULE_INT )
            {
            // InternalFml.g:1142:4: (lv_value_1_0= RULE_INT )
            // InternalFml.g:1143:5: lv_value_1_0= RULE_INT
            {
            lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_value_1_0, grammarAccess.getIntegerExprAccess().getValueINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIntegerExprRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerExpr"


    // $ANTLR start "entryRuleBooleanExpr"
    // InternalFml.g:1163:1: entryRuleBooleanExpr returns [EObject current=null] : iv_ruleBooleanExpr= ruleBooleanExpr EOF ;
    public final EObject entryRuleBooleanExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanExpr = null;


        try {
            // InternalFml.g:1163:52: (iv_ruleBooleanExpr= ruleBooleanExpr EOF )
            // InternalFml.g:1164:2: iv_ruleBooleanExpr= ruleBooleanExpr EOF
            {
             newCompositeNode(grammarAccess.getBooleanExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanExpr=ruleBooleanExpr();

            state._fsp--;

             current =iv_ruleBooleanExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanExpr"


    // $ANTLR start "ruleBooleanExpr"
    // InternalFml.g:1170:1: ruleBooleanExpr returns [EObject current=null] : ( () ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) ) ) ;
    public final EObject ruleBooleanExpr() throws RecognitionException {
        EObject current = null;

        Token lv_val_1_1=null;
        Token lv_val_1_2=null;


        	enterRule();

        try {
            // InternalFml.g:1176:2: ( ( () ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) ) ) )
            // InternalFml.g:1177:2: ( () ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) ) )
            {
            // InternalFml.g:1177:2: ( () ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) ) )
            // InternalFml.g:1178:3: () ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) )
            {
            // InternalFml.g:1178:3: ()
            // InternalFml.g:1179:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBooleanExprAccess().getBooleanExprAction_0(),
            					current);
            			

            }

            // InternalFml.g:1185:3: ( ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) ) )
            // InternalFml.g:1186:4: ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) )
            {
            // InternalFml.g:1186:4: ( (lv_val_1_1= 'true' | lv_val_1_2= 'false' ) )
            // InternalFml.g:1187:5: (lv_val_1_1= 'true' | lv_val_1_2= 'false' )
            {
            // InternalFml.g:1187:5: (lv_val_1_1= 'true' | lv_val_1_2= 'false' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==32) ) {
                alt12=1;
            }
            else if ( (LA12_0==33) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalFml.g:1188:6: lv_val_1_1= 'true'
                    {
                    lv_val_1_1=(Token)match(input,32,FOLLOW_2); 

                    						newLeafNode(lv_val_1_1, grammarAccess.getBooleanExprAccess().getValTrueKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getBooleanExprRule());
                    						}
                    						setWithLastConsumed(current, "val", lv_val_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:1199:6: lv_val_1_2= 'false'
                    {
                    lv_val_1_2=(Token)match(input,33,FOLLOW_2); 

                    						newLeafNode(lv_val_1_2, grammarAccess.getBooleanExprAccess().getValFalseKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getBooleanExprRule());
                    						}
                    						setWithLastConsumed(current, "val", lv_val_1_2, null);
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanExpr"


    // $ANTLR start "entryRuleIdentifierExpr"
    // InternalFml.g:1216:1: entryRuleIdentifierExpr returns [EObject current=null] : iv_ruleIdentifierExpr= ruleIdentifierExpr EOF ;
    public final EObject entryRuleIdentifierExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdentifierExpr = null;


        try {
            // InternalFml.g:1216:55: (iv_ruleIdentifierExpr= ruleIdentifierExpr EOF )
            // InternalFml.g:1217:2: iv_ruleIdentifierExpr= ruleIdentifierExpr EOF
            {
             newCompositeNode(grammarAccess.getIdentifierExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIdentifierExpr=ruleIdentifierExpr();

            state._fsp--;

             current =iv_ruleIdentifierExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdentifierExpr"


    // $ANTLR start "ruleIdentifierExpr"
    // InternalFml.g:1223:1: ruleIdentifierExpr returns [EObject current=null] : ( ( (lv_val_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? ) ;
    public final EObject ruleIdentifierExpr() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_HOOK_1=null;
        Token this_META_ATTRIBUTE_SYMBOL_2=null;
        Token this_RIGHT_HOOK_4=null;
        AntlrDatatypeRuleToken lv_val_0_0 = null;

        EObject lv_metaID_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1229:2: ( ( ( (lv_val_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? ) )
            // InternalFml.g:1230:2: ( ( (lv_val_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? )
            {
            // InternalFml.g:1230:2: ( ( (lv_val_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? )
            // InternalFml.g:1231:3: ( (lv_val_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )?
            {
            // InternalFml.g:1231:3: ( (lv_val_0_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:1232:4: (lv_val_0_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:1232:4: (lv_val_0_0= ruleFML_IDENTIFIER )
            // InternalFml.g:1233:5: lv_val_0_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getIdentifierExprAccess().getValFML_IDENTIFIERParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_15);
            lv_val_0_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIdentifierExprRule());
            					}
            					set(
            						current,
            						"val",
            						lv_val_0_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:1250:3: (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_LEFT_HOOK) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==RULE_META_ATTRIBUTE_SYMBOL) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // InternalFml.g:1251:4: this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK
                    {
                    this_LEFT_HOOK_1=(Token)match(input,RULE_LEFT_HOOK,FOLLOW_7); 

                    				newLeafNode(this_LEFT_HOOK_1, grammarAccess.getIdentifierExprAccess().getLEFT_HOOKTerminalRuleCall_1_0());
                    			
                    this_META_ATTRIBUTE_SYMBOL_2=(Token)match(input,RULE_META_ATTRIBUTE_SYMBOL,FOLLOW_8); 

                    				newLeafNode(this_META_ATTRIBUTE_SYMBOL_2, grammarAccess.getIdentifierExprAccess().getMETA_ATTRIBUTE_SYMBOLTerminalRuleCall_1_1());
                    			
                    // InternalFml.g:1259:4: ( (lv_metaID_3_0= ruleStringExpr ) )
                    // InternalFml.g:1260:5: (lv_metaID_3_0= ruleStringExpr )
                    {
                    // InternalFml.g:1260:5: (lv_metaID_3_0= ruleStringExpr )
                    // InternalFml.g:1261:6: lv_metaID_3_0= ruleStringExpr
                    {

                    						newCompositeNode(grammarAccess.getIdentifierExprAccess().getMetaIDStringExprParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_9);
                    lv_metaID_3_0=ruleStringExpr();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIdentifierExprRule());
                    						}
                    						set(
                    							current,
                    							"metaID",
                    							lv_metaID_3_0,
                    							"org.xtext.example.mydsl.Fml.StringExpr");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    this_RIGHT_HOOK_4=(Token)match(input,RULE_RIGHT_HOOK,FOLLOW_2); 

                    				newLeafNode(this_RIGHT_HOOK_4, grammarAccess.getIdentifierExprAccess().getRIGHT_HOOKTerminalRuleCall_1_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdentifierExpr"


    // $ANTLR start "entryRuleStringExpr"
    // InternalFml.g:1287:1: entryRuleStringExpr returns [EObject current=null] : iv_ruleStringExpr= ruleStringExpr EOF ;
    public final EObject entryRuleStringExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringExpr = null;


        try {
            // InternalFml.g:1287:51: (iv_ruleStringExpr= ruleStringExpr EOF )
            // InternalFml.g:1288:2: iv_ruleStringExpr= ruleStringExpr EOF
            {
             newCompositeNode(grammarAccess.getStringExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringExpr=ruleStringExpr();

            state._fsp--;

             current =iv_ruleStringExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringExpr"


    // $ANTLR start "ruleStringExpr"
    // InternalFml.g:1294:1: ruleStringExpr returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringExpr() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:1300:2: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // InternalFml.g:1301:2: ( (lv_val_0_0= RULE_STRING ) )
            {
            // InternalFml.g:1301:2: ( (lv_val_0_0= RULE_STRING ) )
            // InternalFml.g:1302:3: (lv_val_0_0= RULE_STRING )
            {
            // InternalFml.g:1302:3: (lv_val_0_0= RULE_STRING )
            // InternalFml.g:1303:4: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getStringExprAccess().getValSTRINGTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getStringExprRule());
            				}
            				setWithLastConsumed(
            					current,
            					"val",
            					lv_val_0_0,
            					"org.eclipse.xtext.common.Terminals.STRING");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringExpr"


    // $ANTLR start "entryRuleSetExpr"
    // InternalFml.g:1322:1: entryRuleSetExpr returns [EObject current=null] : iv_ruleSetExpr= ruleSetExpr EOF ;
    public final EObject entryRuleSetExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetExpr = null;


        try {
            // InternalFml.g:1322:48: (iv_ruleSetExpr= ruleSetExpr EOF )
            // InternalFml.g:1323:2: iv_ruleSetExpr= ruleSetExpr EOF
            {
             newCompositeNode(grammarAccess.getSetExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetExpr=ruleSetExpr();

            state._fsp--;

             current =iv_ruleSetExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetExpr"


    // $ANTLR start "ruleSetExpr"
    // InternalFml.g:1329:1: ruleSetExpr returns [EObject current=null] : (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET ( (lv_e_1_0= ruleComplexCommand ) )+ this_RIGHT_BRACKET_2= RULE_RIGHT_BRACKET ) ;
    public final EObject ruleSetExpr() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_BRACKET_0=null;
        Token this_RIGHT_BRACKET_2=null;
        EObject lv_e_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1335:2: ( (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET ( (lv_e_1_0= ruleComplexCommand ) )+ this_RIGHT_BRACKET_2= RULE_RIGHT_BRACKET ) )
            // InternalFml.g:1336:2: (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET ( (lv_e_1_0= ruleComplexCommand ) )+ this_RIGHT_BRACKET_2= RULE_RIGHT_BRACKET )
            {
            // InternalFml.g:1336:2: (this_LEFT_BRACKET_0= RULE_LEFT_BRACKET ( (lv_e_1_0= ruleComplexCommand ) )+ this_RIGHT_BRACKET_2= RULE_RIGHT_BRACKET )
            // InternalFml.g:1337:3: this_LEFT_BRACKET_0= RULE_LEFT_BRACKET ( (lv_e_1_0= ruleComplexCommand ) )+ this_RIGHT_BRACKET_2= RULE_RIGHT_BRACKET
            {
            this_LEFT_BRACKET_0=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_11); 

            			newLeafNode(this_LEFT_BRACKET_0, grammarAccess.getSetExprAccess().getLEFT_BRACKETTerminalRuleCall_0());
            		
            // InternalFml.g:1341:3: ( (lv_e_1_0= ruleComplexCommand ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_LEFT_HOOK||LA14_0==RULE_LEFT_PAREN||(LA14_0>=RULE_INT && LA14_0<=RULE_LEFT_BRACKET)||LA14_0==RULE_ID||(LA14_0>=31 && LA14_0<=35)||LA14_0==37||LA14_0==41||LA14_0==53||LA14_0==55||(LA14_0>=58 && LA14_0<=87)||LA14_0==90||(LA14_0>=92 && LA14_0<=93)||LA14_0==95||LA14_0==97||(LA14_0>=106 && LA14_0<=107)||(LA14_0>=110 && LA14_0<=112)||(LA14_0>=114 && LA14_0<=116)||(LA14_0>=119 && LA14_0<=162)||(LA14_0>=164 && LA14_0<=166)||LA14_0==168||(LA14_0>=170 && LA14_0<=189)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalFml.g:1342:4: (lv_e_1_0= ruleComplexCommand )
            	    {
            	    // InternalFml.g:1342:4: (lv_e_1_0= ruleComplexCommand )
            	    // InternalFml.g:1343:5: lv_e_1_0= ruleComplexCommand
            	    {

            	    					newCompositeNode(grammarAccess.getSetExprAccess().getEComplexCommandParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_e_1_0=ruleComplexCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSetExprRule());
            	    					}
            	    					add(
            	    						current,
            	    						"e",
            	    						lv_e_1_0,
            	    						"org.xtext.example.mydsl.Fml.ComplexCommand");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            this_RIGHT_BRACKET_2=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

            			newLeafNode(this_RIGHT_BRACKET_2, grammarAccess.getSetExprAccess().getRIGHT_BRACKETTerminalRuleCall_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetExpr"


    // $ANTLR start "entryRuleAtomicConstraintExpr"
    // InternalFml.g:1368:1: entryRuleAtomicConstraintExpr returns [EObject current=null] : iv_ruleAtomicConstraintExpr= ruleAtomicConstraintExpr EOF ;
    public final EObject entryRuleAtomicConstraintExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicConstraintExpr = null;


        try {
            // InternalFml.g:1368:61: (iv_ruleAtomicConstraintExpr= ruleAtomicConstraintExpr EOF )
            // InternalFml.g:1369:2: iv_ruleAtomicConstraintExpr= ruleAtomicConstraintExpr EOF
            {
             newCompositeNode(grammarAccess.getAtomicConstraintExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomicConstraintExpr=ruleAtomicConstraintExpr();

            state._fsp--;

             current =iv_ruleAtomicConstraintExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomicConstraintExpr"


    // $ANTLR start "ruleAtomicConstraintExpr"
    // InternalFml.g:1375:1: ruleAtomicConstraintExpr returns [EObject current=null] : (otherlv_0= 'constraint' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_expr_2_0= ruleCNF ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) ;
    public final EObject ruleAtomicConstraintExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token this_RIGHT_PAREN_3=null;
        EObject lv_expr_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1381:2: ( (otherlv_0= 'constraint' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_expr_2_0= ruleCNF ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) )
            // InternalFml.g:1382:2: (otherlv_0= 'constraint' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_expr_2_0= ruleCNF ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:1382:2: (otherlv_0= 'constraint' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_expr_2_0= ruleCNF ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
            // InternalFml.g:1383:3: otherlv_0= 'constraint' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_expr_2_0= ruleCNF ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN
            {
            otherlv_0=(Token)match(input,34,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getAtomicConstraintExprAccess().getConstraintKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_18); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getAtomicConstraintExprAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:1391:3: ( (lv_expr_2_0= ruleCNF ) )
            // InternalFml.g:1392:4: (lv_expr_2_0= ruleCNF )
            {
            // InternalFml.g:1392:4: (lv_expr_2_0= ruleCNF )
            // InternalFml.g:1393:5: lv_expr_2_0= ruleCNF
            {

            					newCompositeNode(grammarAccess.getAtomicConstraintExprAccess().getExprCNFParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_14);
            lv_expr_2_0=ruleCNF();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAtomicConstraintExprRule());
            					}
            					set(
            						current,
            						"expr",
            						lv_expr_2_0,
            						"org.xtext.example.mydsl.Fml.CNF");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            this_RIGHT_PAREN_3=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_3, grammarAccess.getAtomicConstraintExprAccess().getRIGHT_PARENTerminalRuleCall_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomicConstraintExpr"


    // $ANTLR start "entryRuleConstraintExpr"
    // InternalFml.g:1418:1: entryRuleConstraintExpr returns [EObject current=null] : iv_ruleConstraintExpr= ruleConstraintExpr EOF ;
    public final EObject entryRuleConstraintExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintExpr = null;


        try {
            // InternalFml.g:1418:55: (iv_ruleConstraintExpr= ruleConstraintExpr EOF )
            // InternalFml.g:1419:2: iv_ruleConstraintExpr= ruleConstraintExpr EOF
            {
             newCompositeNode(grammarAccess.getConstraintExprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintExpr=ruleConstraintExpr();

            state._fsp--;

             current =iv_ruleConstraintExpr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintExpr"


    // $ANTLR start "ruleConstraintExpr"
    // InternalFml.g:1425:1: ruleConstraintExpr returns [EObject current=null] : (otherlv_0= 'constraints' ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) ) ) ;
    public final EObject ruleConstraintExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token otherlv_3=null;
        Token this_RIGHT_PAREN_4=null;
        EObject lv_constraints_2_0 = null;

        EObject lv_fm_5_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1431:2: ( (otherlv_0= 'constraints' ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) ) ) )
            // InternalFml.g:1432:2: (otherlv_0= 'constraints' ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) ) )
            {
            // InternalFml.g:1432:2: (otherlv_0= 'constraints' ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) ) )
            // InternalFml.g:1433:3: otherlv_0= 'constraints' ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) )
            {
            otherlv_0=(Token)match(input,35,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getConstraintExprAccess().getConstraintsKeyword_0());
            		
            // InternalFml.g:1437:3: ( (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) | ( (lv_fm_5_0= ruleFMCommand ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_LEFT_PAREN) ) {
                alt16=1;
            }
            else if ( (LA16_0==RULE_ID||LA16_0==80||LA16_0==93||LA16_0==95||LA16_0==97||(LA16_0>=106 && LA16_0<=107)||LA16_0==114||LA16_0==128||(LA16_0>=145 && LA16_0<=146)||LA16_0==152||(LA16_0>=165 && LA16_0<=166)||LA16_0==168) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalFml.g:1438:4: (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN )
                    {
                    // InternalFml.g:1438:4: (this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN )
                    // InternalFml.g:1439:5: this_LEFT_PAREN_1= RULE_LEFT_PAREN ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN
                    {
                    this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_18); 

                    					newLeafNode(this_LEFT_PAREN_1, grammarAccess.getConstraintExprAccess().getLEFT_PARENTerminalRuleCall_1_0_0());
                    				
                    // InternalFml.g:1443:5: ( ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==RULE_LEFT_PAREN||LA15_0==RULE_STRING||LA15_0==RULE_ID||LA15_0==RULE_B_NOT||(LA15_0>=32 && LA15_0<=33)||LA15_0==168) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalFml.g:1444:6: ( (lv_constraints_2_0= ruleCNF ) ) otherlv_3= ';'
                    	    {
                    	    // InternalFml.g:1444:6: ( (lv_constraints_2_0= ruleCNF ) )
                    	    // InternalFml.g:1445:7: (lv_constraints_2_0= ruleCNF )
                    	    {
                    	    // InternalFml.g:1445:7: (lv_constraints_2_0= ruleCNF )
                    	    // InternalFml.g:1446:8: lv_constraints_2_0= ruleCNF
                    	    {

                    	    								newCompositeNode(grammarAccess.getConstraintExprAccess().getConstraintsCNFParserRuleCall_1_0_1_0_0());
                    	    							
                    	    pushFollow(FOLLOW_20);
                    	    lv_constraints_2_0=ruleCNF();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getConstraintExprRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"constraints",
                    	    									lv_constraints_2_0,
                    	    									"org.xtext.example.mydsl.Fml.CNF");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }

                    	    otherlv_3=(Token)match(input,36,FOLLOW_21); 

                    	    						newLeafNode(otherlv_3, grammarAccess.getConstraintExprAccess().getSemicolonKeyword_1_0_1_1());
                    	    					

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);

                    this_RIGHT_PAREN_4=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

                    					newLeafNode(this_RIGHT_PAREN_4, grammarAccess.getConstraintExprAccess().getRIGHT_PARENTerminalRuleCall_1_0_2());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:1474:4: ( (lv_fm_5_0= ruleFMCommand ) )
                    {
                    // InternalFml.g:1474:4: ( (lv_fm_5_0= ruleFMCommand ) )
                    // InternalFml.g:1475:5: (lv_fm_5_0= ruleFMCommand )
                    {
                    // InternalFml.g:1475:5: (lv_fm_5_0= ruleFMCommand )
                    // InternalFml.g:1476:6: lv_fm_5_0= ruleFMCommand
                    {

                    						newCompositeNode(grammarAccess.getConstraintExprAccess().getFmFMCommandParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fm_5_0=ruleFMCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getConstraintExprRule());
                    						}
                    						set(
                    							current,
                    							"fm",
                    							lv_fm_5_0,
                    							"org.xtext.example.mydsl.Fml.FMCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintExpr"


    // $ANTLR start "entryRuleFeatureVariabilityOperator"
    // InternalFml.g:1498:1: entryRuleFeatureVariabilityOperator returns [EObject current=null] : iv_ruleFeatureVariabilityOperator= ruleFeatureVariabilityOperator EOF ;
    public final EObject entryRuleFeatureVariabilityOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureVariabilityOperator = null;


        try {
            // InternalFml.g:1498:67: (iv_ruleFeatureVariabilityOperator= ruleFeatureVariabilityOperator EOF )
            // InternalFml.g:1499:2: iv_ruleFeatureVariabilityOperator= ruleFeatureVariabilityOperator EOF
            {
             newCompositeNode(grammarAccess.getFeatureVariabilityOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureVariabilityOperator=ruleFeatureVariabilityOperator();

            state._fsp--;

             current =iv_ruleFeatureVariabilityOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureVariabilityOperator"


    // $ANTLR start "ruleFeatureVariabilityOperator"
    // InternalFml.g:1505:1: ruleFeatureVariabilityOperator returns [EObject current=null] : ( (lv_val_0_0= ruleFeatureEdgeKind ) ) ;
    public final EObject ruleFeatureVariabilityOperator() throws RecognitionException {
        EObject current = null;

        Enumerator lv_val_0_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1511:2: ( ( (lv_val_0_0= ruleFeatureEdgeKind ) ) )
            // InternalFml.g:1512:2: ( (lv_val_0_0= ruleFeatureEdgeKind ) )
            {
            // InternalFml.g:1512:2: ( (lv_val_0_0= ruleFeatureEdgeKind ) )
            // InternalFml.g:1513:3: (lv_val_0_0= ruleFeatureEdgeKind )
            {
            // InternalFml.g:1513:3: (lv_val_0_0= ruleFeatureEdgeKind )
            // InternalFml.g:1514:4: lv_val_0_0= ruleFeatureEdgeKind
            {

            				newCompositeNode(grammarAccess.getFeatureVariabilityOperatorAccess().getValFeatureEdgeKindEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_val_0_0=ruleFeatureEdgeKind();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getFeatureVariabilityOperatorRule());
            				}
            				set(
            					current,
            					"val",
            					lv_val_0_0,
            					"org.xtext.example.mydsl.Fml.FeatureEdgeKind");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureVariabilityOperator"


    // $ANTLR start "entryRuleIfCondition"
    // InternalFml.g:1534:1: entryRuleIfCondition returns [EObject current=null] : iv_ruleIfCondition= ruleIfCondition EOF ;
    public final EObject entryRuleIfCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfCondition = null;


        try {
            // InternalFml.g:1534:52: (iv_ruleIfCondition= ruleIfCondition EOF )
            // InternalFml.g:1535:2: iv_ruleIfCondition= ruleIfCondition EOF
            {
             newCompositeNode(grammarAccess.getIfConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIfCondition=ruleIfCondition();

            state._fsp--;

             current =iv_ruleIfCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfCondition"


    // $ANTLR start "ruleIfCondition"
    // InternalFml.g:1541:1: ruleIfCondition returns [EObject current=null] : (otherlv_0= 'if' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_bexpr_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN otherlv_4= 'then' ( (lv_then_5_0= ruleScriptCommand ) )+ (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )? otherlv_8= 'end' ) ;
    public final EObject ruleIfCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token this_RIGHT_PAREN_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_bexpr_2_0 = null;

        EObject lv_then_5_0 = null;

        EObject lv_else_7_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1547:2: ( (otherlv_0= 'if' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_bexpr_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN otherlv_4= 'then' ( (lv_then_5_0= ruleScriptCommand ) )+ (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )? otherlv_8= 'end' ) )
            // InternalFml.g:1548:2: (otherlv_0= 'if' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_bexpr_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN otherlv_4= 'then' ( (lv_then_5_0= ruleScriptCommand ) )+ (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )? otherlv_8= 'end' )
            {
            // InternalFml.g:1548:2: (otherlv_0= 'if' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_bexpr_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN otherlv_4= 'then' ( (lv_then_5_0= ruleScriptCommand ) )+ (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )? otherlv_8= 'end' )
            // InternalFml.g:1549:3: otherlv_0= 'if' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_bexpr_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN otherlv_4= 'then' ( (lv_then_5_0= ruleScriptCommand ) )+ (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )? otherlv_8= 'end'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getIfConditionAccess().getIfKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_11); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getIfConditionAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:1557:3: ( (lv_bexpr_2_0= ruleComplexCommand ) )
            // InternalFml.g:1558:4: (lv_bexpr_2_0= ruleComplexCommand )
            {
            // InternalFml.g:1558:4: (lv_bexpr_2_0= ruleComplexCommand )
            // InternalFml.g:1559:5: lv_bexpr_2_0= ruleComplexCommand
            {

            					newCompositeNode(grammarAccess.getIfConditionAccess().getBexprComplexCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_14);
            lv_bexpr_2_0=ruleComplexCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfConditionRule());
            					}
            					set(
            						current,
            						"bexpr",
            						lv_bexpr_2_0,
            						"org.xtext.example.mydsl.Fml.ComplexCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            this_RIGHT_PAREN_3=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_22); 

            			newLeafNode(this_RIGHT_PAREN_3, grammarAccess.getIfConditionAccess().getRIGHT_PARENTerminalRuleCall_3());
            		
            otherlv_4=(Token)match(input,38,FOLLOW_11); 

            			newLeafNode(otherlv_4, grammarAccess.getIfConditionAccess().getThenKeyword_4());
            		
            // InternalFml.g:1584:3: ( (lv_then_5_0= ruleScriptCommand ) )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_LEFT_HOOK||LA17_0==RULE_LEFT_PAREN||(LA17_0>=RULE_INT && LA17_0<=RULE_LEFT_BRACKET)||LA17_0==RULE_ID||(LA17_0>=31 && LA17_0<=35)||LA17_0==37||LA17_0==41||LA17_0==53||LA17_0==55||(LA17_0>=58 && LA17_0<=87)||LA17_0==90||(LA17_0>=92 && LA17_0<=93)||LA17_0==95||LA17_0==97||(LA17_0>=106 && LA17_0<=107)||(LA17_0>=110 && LA17_0<=112)||(LA17_0>=114 && LA17_0<=116)||(LA17_0>=119 && LA17_0<=162)||(LA17_0>=164 && LA17_0<=166)||LA17_0==168||(LA17_0>=170 && LA17_0<=189)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalFml.g:1585:4: (lv_then_5_0= ruleScriptCommand )
            	    {
            	    // InternalFml.g:1585:4: (lv_then_5_0= ruleScriptCommand )
            	    // InternalFml.g:1586:5: lv_then_5_0= ruleScriptCommand
            	    {

            	    					newCompositeNode(grammarAccess.getIfConditionAccess().getThenScriptCommandParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_23);
            	    lv_then_5_0=ruleScriptCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getIfConditionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"then",
            	    						lv_then_5_0,
            	    						"org.xtext.example.mydsl.Fml.ScriptCommand");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            // InternalFml.g:1603:3: (otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+ )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==39) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalFml.g:1604:4: otherlv_6= 'else' ( (lv_else_7_0= ruleScriptCommand ) )+
                    {
                    otherlv_6=(Token)match(input,39,FOLLOW_11); 

                    				newLeafNode(otherlv_6, grammarAccess.getIfConditionAccess().getElseKeyword_6_0());
                    			
                    // InternalFml.g:1608:4: ( (lv_else_7_0= ruleScriptCommand ) )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==RULE_LEFT_HOOK||LA18_0==RULE_LEFT_PAREN||(LA18_0>=RULE_INT && LA18_0<=RULE_LEFT_BRACKET)||LA18_0==RULE_ID||(LA18_0>=31 && LA18_0<=35)||LA18_0==37||LA18_0==41||LA18_0==53||LA18_0==55||(LA18_0>=58 && LA18_0<=87)||LA18_0==90||(LA18_0>=92 && LA18_0<=93)||LA18_0==95||LA18_0==97||(LA18_0>=106 && LA18_0<=107)||(LA18_0>=110 && LA18_0<=112)||(LA18_0>=114 && LA18_0<=116)||(LA18_0>=119 && LA18_0<=162)||(LA18_0>=164 && LA18_0<=166)||LA18_0==168||(LA18_0>=170 && LA18_0<=189)) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalFml.g:1609:5: (lv_else_7_0= ruleScriptCommand )
                    	    {
                    	    // InternalFml.g:1609:5: (lv_else_7_0= ruleScriptCommand )
                    	    // InternalFml.g:1610:6: lv_else_7_0= ruleScriptCommand
                    	    {

                    	    						newCompositeNode(grammarAccess.getIfConditionAccess().getElseScriptCommandParserRuleCall_6_1_0());
                    	    					
                    	    pushFollow(FOLLOW_24);
                    	    lv_else_7_0=ruleScriptCommand();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIfConditionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"else",
                    	    							lv_else_7_0,
                    	    							"org.xtext.example.mydsl.Fml.ScriptCommand");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,40,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getIfConditionAccess().getEndKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfCondition"


    // $ANTLR start "entryRuleForeachSet"
    // InternalFml.g:1636:1: entryRuleForeachSet returns [EObject current=null] : iv_ruleForeachSet= ruleForeachSet EOF ;
    public final EObject entryRuleForeachSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForeachSet = null;


        try {
            // InternalFml.g:1636:51: (iv_ruleForeachSet= ruleForeachSet EOF )
            // InternalFml.g:1637:2: iv_ruleForeachSet= ruleForeachSet EOF
            {
             newCompositeNode(grammarAccess.getForeachSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForeachSet=ruleForeachSet();

            state._fsp--;

             current =iv_ruleForeachSet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForeachSet"


    // $ANTLR start "ruleForeachSet"
    // InternalFml.g:1643:1: ruleForeachSet returns [EObject current=null] : (otherlv_0= 'foreach' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_val_2_0= ruleFML_IDENTIFIER ) ) otherlv_3= 'in' ( (lv_var_4_0= ruleFML_IDENTIFIER ) ) this_RIGHT_PAREN_5= RULE_RIGHT_PAREN otherlv_6= 'do' ( (lv_exprs_7_0= ruleScriptCommand ) )+ otherlv_8= 'end' ) ;
    public final EObject ruleForeachSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token otherlv_3=null;
        Token this_RIGHT_PAREN_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_val_2_0 = null;

        AntlrDatatypeRuleToken lv_var_4_0 = null;

        EObject lv_exprs_7_0 = null;



        	enterRule();

        try {
            // InternalFml.g:1649:2: ( (otherlv_0= 'foreach' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_val_2_0= ruleFML_IDENTIFIER ) ) otherlv_3= 'in' ( (lv_var_4_0= ruleFML_IDENTIFIER ) ) this_RIGHT_PAREN_5= RULE_RIGHT_PAREN otherlv_6= 'do' ( (lv_exprs_7_0= ruleScriptCommand ) )+ otherlv_8= 'end' ) )
            // InternalFml.g:1650:2: (otherlv_0= 'foreach' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_val_2_0= ruleFML_IDENTIFIER ) ) otherlv_3= 'in' ( (lv_var_4_0= ruleFML_IDENTIFIER ) ) this_RIGHT_PAREN_5= RULE_RIGHT_PAREN otherlv_6= 'do' ( (lv_exprs_7_0= ruleScriptCommand ) )+ otherlv_8= 'end' )
            {
            // InternalFml.g:1650:2: (otherlv_0= 'foreach' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_val_2_0= ruleFML_IDENTIFIER ) ) otherlv_3= 'in' ( (lv_var_4_0= ruleFML_IDENTIFIER ) ) this_RIGHT_PAREN_5= RULE_RIGHT_PAREN otherlv_6= 'do' ( (lv_exprs_7_0= ruleScriptCommand ) )+ otherlv_8= 'end' )
            // InternalFml.g:1651:3: otherlv_0= 'foreach' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_val_2_0= ruleFML_IDENTIFIER ) ) otherlv_3= 'in' ( (lv_var_4_0= ruleFML_IDENTIFIER ) ) this_RIGHT_PAREN_5= RULE_RIGHT_PAREN otherlv_6= 'do' ( (lv_exprs_7_0= ruleScriptCommand ) )+ otherlv_8= 'end'
            {
            otherlv_0=(Token)match(input,41,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getForeachSetAccess().getForeachKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_25); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getForeachSetAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:1659:3: ( (lv_val_2_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:1660:4: (lv_val_2_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:1660:4: (lv_val_2_0= ruleFML_IDENTIFIER )
            // InternalFml.g:1661:5: lv_val_2_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getForeachSetAccess().getValFML_IDENTIFIERParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_26);
            lv_val_2_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForeachSetRule());
            					}
            					set(
            						current,
            						"val",
            						lv_val_2_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,42,FOLLOW_25); 

            			newLeafNode(otherlv_3, grammarAccess.getForeachSetAccess().getInKeyword_3());
            		
            // InternalFml.g:1682:3: ( (lv_var_4_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:1683:4: (lv_var_4_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:1683:4: (lv_var_4_0= ruleFML_IDENTIFIER )
            // InternalFml.g:1684:5: lv_var_4_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getForeachSetAccess().getVarFML_IDENTIFIERParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_14);
            lv_var_4_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForeachSetRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_4_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            this_RIGHT_PAREN_5=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_27); 

            			newLeafNode(this_RIGHT_PAREN_5, grammarAccess.getForeachSetAccess().getRIGHT_PARENTerminalRuleCall_5());
            		
            otherlv_6=(Token)match(input,43,FOLLOW_11); 

            			newLeafNode(otherlv_6, grammarAccess.getForeachSetAccess().getDoKeyword_6());
            		
            // InternalFml.g:1709:3: ( (lv_exprs_7_0= ruleScriptCommand ) )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_LEFT_HOOK||LA20_0==RULE_LEFT_PAREN||(LA20_0>=RULE_INT && LA20_0<=RULE_LEFT_BRACKET)||LA20_0==RULE_ID||(LA20_0>=31 && LA20_0<=35)||LA20_0==37||LA20_0==41||LA20_0==53||LA20_0==55||(LA20_0>=58 && LA20_0<=87)||LA20_0==90||(LA20_0>=92 && LA20_0<=93)||LA20_0==95||LA20_0==97||(LA20_0>=106 && LA20_0<=107)||(LA20_0>=110 && LA20_0<=112)||(LA20_0>=114 && LA20_0<=116)||(LA20_0>=119 && LA20_0<=162)||(LA20_0>=164 && LA20_0<=166)||LA20_0==168||(LA20_0>=170 && LA20_0<=189)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalFml.g:1710:4: (lv_exprs_7_0= ruleScriptCommand )
            	    {
            	    // InternalFml.g:1710:4: (lv_exprs_7_0= ruleScriptCommand )
            	    // InternalFml.g:1711:5: lv_exprs_7_0= ruleScriptCommand
            	    {

            	    					newCompositeNode(grammarAccess.getForeachSetAccess().getExprsScriptCommandParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_24);
            	    lv_exprs_7_0=ruleScriptCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getForeachSetRule());
            	    					}
            	    					add(
            	    						current,
            	    						"exprs",
            	    						lv_exprs_7_0,
            	    						"org.xtext.example.mydsl.Fml.ScriptCommand");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            otherlv_8=(Token)match(input,40,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getForeachSetAccess().getEndKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForeachSet"


    // $ANTLR start "entryRulelType"
    // InternalFml.g:1736:1: entryRulelType returns [EObject current=null] : iv_rulelType= rulelType EOF ;
    public final EObject entryRulelType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelType = null;


        try {
            // InternalFml.g:1736:46: (iv_rulelType= rulelType EOF )
            // InternalFml.g:1737:2: iv_rulelType= rulelType EOF
            {
             newCompositeNode(grammarAccess.getLTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_rulelType=rulelType();

            state._fsp--;

             current =iv_rulelType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulelType"


    // $ANTLR start "rulelType"
    // InternalFml.g:1743:1: rulelType returns [EObject current=null] : ( ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) ) ) ;
    public final EObject rulelType() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;
        Token lv_val_0_3=null;
        Token lv_val_0_4=null;
        Token lv_val_0_5=null;
        Token lv_val_0_6=null;
        Token lv_val_0_7=null;
        Token lv_val_0_8=null;
        Token lv_val_0_9=null;


        	enterRule();

        try {
            // InternalFml.g:1749:2: ( ( ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) ) ) )
            // InternalFml.g:1750:2: ( ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) ) )
            {
            // InternalFml.g:1750:2: ( ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) ) )
            // InternalFml.g:1751:3: ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) )
            {
            // InternalFml.g:1751:3: ( (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' ) )
            // InternalFml.g:1752:4: (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' )
            {
            // InternalFml.g:1752:4: (lv_val_0_1= 'FeatureModel' | lv_val_0_2= 'Feature' | lv_val_0_3= 'Boolean' | lv_val_0_4= 'String' | lv_val_0_5= 'Configuration' | lv_val_0_6= 'Set' | lv_val_0_7= 'Double' | lv_val_0_8= 'Integer' | lv_val_0_9= 'Constraint' )
            int alt21=9;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt21=1;
                }
                break;
            case 45:
                {
                alt21=2;
                }
                break;
            case 46:
                {
                alt21=3;
                }
                break;
            case 47:
                {
                alt21=4;
                }
                break;
            case 48:
                {
                alt21=5;
                }
                break;
            case 49:
                {
                alt21=6;
                }
                break;
            case 50:
                {
                alt21=7;
                }
                break;
            case 51:
                {
                alt21=8;
                }
                break;
            case 52:
                {
                alt21=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // InternalFml.g:1753:5: lv_val_0_1= 'FeatureModel'
                    {
                    lv_val_0_1=(Token)match(input,44,FOLLOW_2); 

                    					newLeafNode(lv_val_0_1, grammarAccess.getLTypeAccess().getValFeatureModelKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_1, null);
                    				

                    }
                    break;
                case 2 :
                    // InternalFml.g:1764:5: lv_val_0_2= 'Feature'
                    {
                    lv_val_0_2=(Token)match(input,45,FOLLOW_2); 

                    					newLeafNode(lv_val_0_2, grammarAccess.getLTypeAccess().getValFeatureKeyword_0_1());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_2, null);
                    				

                    }
                    break;
                case 3 :
                    // InternalFml.g:1775:5: lv_val_0_3= 'Boolean'
                    {
                    lv_val_0_3=(Token)match(input,46,FOLLOW_2); 

                    					newLeafNode(lv_val_0_3, grammarAccess.getLTypeAccess().getValBooleanKeyword_0_2());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_3, null);
                    				

                    }
                    break;
                case 4 :
                    // InternalFml.g:1786:5: lv_val_0_4= 'String'
                    {
                    lv_val_0_4=(Token)match(input,47,FOLLOW_2); 

                    					newLeafNode(lv_val_0_4, grammarAccess.getLTypeAccess().getValStringKeyword_0_3());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_4, null);
                    				

                    }
                    break;
                case 5 :
                    // InternalFml.g:1797:5: lv_val_0_5= 'Configuration'
                    {
                    lv_val_0_5=(Token)match(input,48,FOLLOW_2); 

                    					newLeafNode(lv_val_0_5, grammarAccess.getLTypeAccess().getValConfigurationKeyword_0_4());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_5, null);
                    				

                    }
                    break;
                case 6 :
                    // InternalFml.g:1808:5: lv_val_0_6= 'Set'
                    {
                    lv_val_0_6=(Token)match(input,49,FOLLOW_2); 

                    					newLeafNode(lv_val_0_6, grammarAccess.getLTypeAccess().getValSetKeyword_0_5());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_6, null);
                    				

                    }
                    break;
                case 7 :
                    // InternalFml.g:1819:5: lv_val_0_7= 'Double'
                    {
                    lv_val_0_7=(Token)match(input,50,FOLLOW_2); 

                    					newLeafNode(lv_val_0_7, grammarAccess.getLTypeAccess().getValDoubleKeyword_0_6());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_7, null);
                    				

                    }
                    break;
                case 8 :
                    // InternalFml.g:1830:5: lv_val_0_8= 'Integer'
                    {
                    lv_val_0_8=(Token)match(input,51,FOLLOW_2); 

                    					newLeafNode(lv_val_0_8, grammarAccess.getLTypeAccess().getValIntegerKeyword_0_7());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_8, null);
                    				

                    }
                    break;
                case 9 :
                    // InternalFml.g:1841:5: lv_val_0_9= 'Constraint'
                    {
                    lv_val_0_9=(Token)match(input,52,FOLLOW_2); 

                    					newLeafNode(lv_val_0_9, grammarAccess.getLTypeAccess().getValConstraintKeyword_0_8());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLTypeRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_9, null);
                    				

                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulelType"


    // $ANTLR start "entryRuleFMCommand"
    // InternalFml.g:1857:1: entryRuleFMCommand returns [EObject current=null] : iv_ruleFMCommand= ruleFMCommand EOF ;
    public final EObject entryRuleFMCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMCommand = null;


        try {
            // InternalFml.g:1857:50: (iv_ruleFMCommand= ruleFMCommand EOF )
            // InternalFml.g:1858:2: iv_ruleFMCommand= ruleFMCommand EOF
            {
             newCompositeNode(grammarAccess.getFMCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMCommand=ruleFMCommand();

            state._fsp--;

             current =iv_ruleFMCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMCommand"


    // $ANTLR start "ruleFMCommand"
    // InternalFml.g:1864:1: ruleFMCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_AggregateMerge_2= ruleAggregateMerge | this_Merge_3= ruleMerge | this_Synthesis_4= ruleSynthesis | this_FeatureModel_5= ruleFeatureModel | this_FMFeature_6= ruleFMFeature | this_AsFM_7= ruleAsFM | this_Aggregate_8= ruleAggregate | this_Extract_9= ruleExtract | this_Slice_10= ruleSlice | this_Hierarchy_11= ruleHierarchy ) ;
    public final EObject ruleFMCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_CopyVariable_1 = null;

        EObject this_AggregateMerge_2 = null;

        EObject this_Merge_3 = null;

        EObject this_Synthesis_4 = null;

        EObject this_FeatureModel_5 = null;

        EObject this_FMFeature_6 = null;

        EObject this_AsFM_7 = null;

        EObject this_Aggregate_8 = null;

        EObject this_Extract_9 = null;

        EObject this_Slice_10 = null;

        EObject this_Hierarchy_11 = null;



        	enterRule();

        try {
            // InternalFml.g:1870:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_AggregateMerge_2= ruleAggregateMerge | this_Merge_3= ruleMerge | this_Synthesis_4= ruleSynthesis | this_FeatureModel_5= ruleFeatureModel | this_FMFeature_6= ruleFMFeature | this_AsFM_7= ruleAsFM | this_Aggregate_8= ruleAggregate | this_Extract_9= ruleExtract | this_Slice_10= ruleSlice | this_Hierarchy_11= ruleHierarchy ) )
            // InternalFml.g:1871:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_AggregateMerge_2= ruleAggregateMerge | this_Merge_3= ruleMerge | this_Synthesis_4= ruleSynthesis | this_FeatureModel_5= ruleFeatureModel | this_FMFeature_6= ruleFMFeature | this_AsFM_7= ruleAsFM | this_Aggregate_8= ruleAggregate | this_Extract_9= ruleExtract | this_Slice_10= ruleSlice | this_Hierarchy_11= ruleHierarchy )
            {
            // InternalFml.g:1871:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_AggregateMerge_2= ruleAggregateMerge | this_Merge_3= ruleMerge | this_Synthesis_4= ruleSynthesis | this_FeatureModel_5= ruleFeatureModel | this_FMFeature_6= ruleFMFeature | this_AsFM_7= ruleAsFM | this_Aggregate_8= ruleAggregate | this_Extract_9= ruleExtract | this_Slice_10= ruleSlice | this_Hierarchy_11= ruleHierarchy )
            int alt22=12;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt22=1;
                }
                break;
            case 145:
            case 146:
                {
                alt22=2;
                }
                break;
            case 95:
                {
                alt22=3;
                }
                break;
            case 93:
                {
                alt22=4;
                }
                break;
            case 97:
                {
                alt22=5;
                }
                break;
            case 165:
            case 166:
                {
                alt22=6;
                }
                break;
            case 80:
                {
                alt22=7;
                }
                break;
            case 128:
                {
                alt22=8;
                }
                break;
            case 107:
                {
                alt22=9;
                }
                break;
            case 114:
                {
                alt22=10;
                }
                break;
            case 106:
                {
                alt22=11;
                }
                break;
            case 152:
                {
                alt22=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // InternalFml.g:1872:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:1881:3: this_CopyVariable_1= ruleCopyVariable
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getCopyVariableParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CopyVariable_1=ruleCopyVariable();

                    state._fsp--;


                    			current = this_CopyVariable_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:1890:3: this_AggregateMerge_2= ruleAggregateMerge
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getAggregateMergeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_AggregateMerge_2=ruleAggregateMerge();

                    state._fsp--;


                    			current = this_AggregateMerge_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:1899:3: this_Merge_3= ruleMerge
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getMergeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Merge_3=ruleMerge();

                    state._fsp--;


                    			current = this_Merge_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:1908:3: this_Synthesis_4= ruleSynthesis
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getSynthesisParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Synthesis_4=ruleSynthesis();

                    state._fsp--;


                    			current = this_Synthesis_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:1917:3: this_FeatureModel_5= ruleFeatureModel
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getFeatureModelParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_FeatureModel_5=ruleFeatureModel();

                    state._fsp--;


                    			current = this_FeatureModel_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalFml.g:1926:3: this_FMFeature_6= ruleFMFeature
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getFMFeatureParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_FMFeature_6=ruleFMFeature();

                    state._fsp--;


                    			current = this_FMFeature_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalFml.g:1935:3: this_AsFM_7= ruleAsFM
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getAsFMParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_AsFM_7=ruleAsFM();

                    state._fsp--;


                    			current = this_AsFM_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalFml.g:1944:3: this_Aggregate_8= ruleAggregate
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getAggregateParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_Aggregate_8=ruleAggregate();

                    state._fsp--;


                    			current = this_Aggregate_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalFml.g:1953:3: this_Extract_9= ruleExtract
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getExtractParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_Extract_9=ruleExtract();

                    state._fsp--;


                    			current = this_Extract_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalFml.g:1962:3: this_Slice_10= ruleSlice
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getSliceParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_Slice_10=ruleSlice();

                    state._fsp--;


                    			current = this_Slice_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalFml.g:1971:3: this_Hierarchy_11= ruleHierarchy
                    {

                    			newCompositeNode(grammarAccess.getFMCommandAccess().getHierarchyParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_Hierarchy_11=ruleHierarchy();

                    state._fsp--;


                    			current = this_Hierarchy_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMCommand"


    // $ANTLR start "entryRuleFTCommand"
    // InternalFml.g:1983:1: entryRuleFTCommand returns [EObject current=null] : iv_ruleFTCommand= ruleFTCommand EOF ;
    public final EObject entryRuleFTCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFTCommand = null;


        try {
            // InternalFml.g:1983:50: (iv_ruleFTCommand= ruleFTCommand EOF )
            // InternalFml.g:1984:2: iv_ruleFTCommand= ruleFTCommand EOF
            {
             newCompositeNode(grammarAccess.getFTCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFTCommand=ruleFTCommand();

            state._fsp--;

             current =iv_ruleFTCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFTCommand"


    // $ANTLR start "ruleFTCommand"
    // InternalFml.g:1990:1: ruleFTCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureOperation_2= ruleFeatureOperation ) ;
    public final EObject ruleFTCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_CopyVariable_1 = null;

        EObject this_FeatureOperation_2 = null;



        	enterRule();

        try {
            // InternalFml.g:1996:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureOperation_2= ruleFeatureOperation ) )
            // InternalFml.g:1997:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureOperation_2= ruleFeatureOperation )
            {
            // InternalFml.g:1997:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureOperation_2= ruleFeatureOperation )
            int alt23=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt23=1;
                }
                break;
            case 145:
            case 146:
                {
                alt23=2;
                }
                break;
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt23=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalFml.g:1998:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getFTCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2007:3: this_CopyVariable_1= ruleCopyVariable
                    {

                    			newCompositeNode(grammarAccess.getFTCommandAccess().getCopyVariableParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CopyVariable_1=ruleCopyVariable();

                    state._fsp--;


                    			current = this_CopyVariable_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:2016:3: this_FeatureOperation_2= ruleFeatureOperation
                    {

                    			newCompositeNode(grammarAccess.getFTCommandAccess().getFeatureOperationParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_FeatureOperation_2=ruleFeatureOperation();

                    state._fsp--;


                    			current = this_FeatureOperation_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFTCommand"


    // $ANTLR start "entryRuleStrCommand"
    // InternalFml.g:2028:1: entryRuleStrCommand returns [EObject current=null] : iv_ruleStrCommand= ruleStrCommand EOF ;
    public final EObject entryRuleStrCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStrCommand = null;


        try {
            // InternalFml.g:2028:51: (iv_ruleStrCommand= ruleStrCommand EOF )
            // InternalFml.g:2029:2: iv_ruleStrCommand= ruleStrCommand EOF
            {
             newCompositeNode(grammarAccess.getStrCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStrCommand=ruleStrCommand();

            state._fsp--;

             current =iv_ruleStrCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStrCommand"


    // $ANTLR start "ruleStrCommand"
    // InternalFml.g:2035:1: ruleStrCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_StringExpr_2= ruleStringExpr | this_StringConcat_3= ruleStringConcat | this_StringInit_4= ruleStringInit | this_StringSubstring_5= ruleStringSubstring | this_FeatureOperation_6= ruleFeatureOperation | this_Convert_7= ruleConvert ) ;
    public final EObject ruleStrCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_CopyVariable_1 = null;

        EObject this_StringExpr_2 = null;

        EObject this_StringConcat_3 = null;

        EObject this_StringInit_4 = null;

        EObject this_StringSubstring_5 = null;

        EObject this_FeatureOperation_6 = null;

        EObject this_Convert_7 = null;



        	enterRule();

        try {
            // InternalFml.g:2041:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_StringExpr_2= ruleStringExpr | this_StringConcat_3= ruleStringConcat | this_StringInit_4= ruleStringInit | this_StringSubstring_5= ruleStringSubstring | this_FeatureOperation_6= ruleFeatureOperation | this_Convert_7= ruleConvert ) )
            // InternalFml.g:2042:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_StringExpr_2= ruleStringExpr | this_StringConcat_3= ruleStringConcat | this_StringInit_4= ruleStringInit | this_StringSubstring_5= ruleStringSubstring | this_FeatureOperation_6= ruleFeatureOperation | this_Convert_7= ruleConvert )
            {
            // InternalFml.g:2042:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_StringExpr_2= ruleStringExpr | this_StringConcat_3= ruleStringConcat | this_StringInit_4= ruleStringInit | this_StringSubstring_5= ruleStringSubstring | this_FeatureOperation_6= ruleFeatureOperation | this_Convert_7= ruleConvert )
            int alt24=8;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt24=1;
                }
                break;
            case 145:
            case 146:
                {
                alt24=2;
                }
                break;
            case RULE_STRING:
                {
                alt24=3;
                }
                break;
            case 83:
                {
                alt24=4;
                }
                break;
            case 82:
                {
                alt24=5;
                }
                break;
            case 84:
                {
                alt24=6;
                }
                break;
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt24=7;
                }
                break;
            case 149:
                {
                alt24=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // InternalFml.g:2043:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2052:3: this_CopyVariable_1= ruleCopyVariable
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getCopyVariableParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CopyVariable_1=ruleCopyVariable();

                    state._fsp--;


                    			current = this_CopyVariable_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:2061:3: this_StringExpr_2= ruleStringExpr
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getStringExprParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringExpr_2=ruleStringExpr();

                    state._fsp--;


                    			current = this_StringExpr_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:2070:3: this_StringConcat_3= ruleStringConcat
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getStringConcatParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringConcat_3=ruleStringConcat();

                    state._fsp--;


                    			current = this_StringConcat_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:2079:3: this_StringInit_4= ruleStringInit
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getStringInitParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringInit_4=ruleStringInit();

                    state._fsp--;


                    			current = this_StringInit_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:2088:3: this_StringSubstring_5= ruleStringSubstring
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getStringSubstringParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringSubstring_5=ruleStringSubstring();

                    state._fsp--;


                    			current = this_StringSubstring_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalFml.g:2097:3: this_FeatureOperation_6= ruleFeatureOperation
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getFeatureOperationParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_FeatureOperation_6=ruleFeatureOperation();

                    state._fsp--;


                    			current = this_FeatureOperation_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalFml.g:2106:3: this_Convert_7= ruleConvert
                    {

                    			newCompositeNode(grammarAccess.getStrCommandAccess().getConvertParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_Convert_7=ruleConvert();

                    state._fsp--;


                    			current = this_Convert_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStrCommand"


    // $ANTLR start "entryRuleConfigurationCommand"
    // InternalFml.g:2118:1: entryRuleConfigurationCommand returns [EObject current=null] : iv_ruleConfigurationCommand= ruleConfigurationCommand EOF ;
    public final EObject entryRuleConfigurationCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfigurationCommand = null;


        try {
            // InternalFml.g:2118:61: (iv_ruleConfigurationCommand= ruleConfigurationCommand EOF )
            // InternalFml.g:2119:2: iv_ruleConfigurationCommand= ruleConfigurationCommand EOF
            {
             newCompositeNode(grammarAccess.getConfigurationCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConfigurationCommand=ruleConfigurationCommand();

            state._fsp--;

             current =iv_ruleConfigurationCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConfigurationCommand"


    // $ANTLR start "ruleConfigurationCommand"
    // InternalFml.g:2125:1: ruleConfigurationCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_CreateConfiguration_1= ruleCreateConfiguration ) ;
    public final EObject ruleConfigurationCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_CreateConfiguration_1 = null;



        	enterRule();

        try {
            // InternalFml.g:2131:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_CreateConfiguration_1= ruleCreateConfiguration ) )
            // InternalFml.g:2132:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CreateConfiguration_1= ruleCreateConfiguration )
            {
            // InternalFml.g:2132:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CreateConfiguration_1= ruleCreateConfiguration )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ID||LA25_0==168) ) {
                alt25=1;
            }
            else if ( (LA25_0==119) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalFml.g:2133:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2142:3: this_CreateConfiguration_1= ruleCreateConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCommandAccess().getCreateConfigurationParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CreateConfiguration_1=ruleCreateConfiguration();

                    state._fsp--;


                    			current = this_CreateConfiguration_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConfigurationCommand"


    // $ANTLR start "entryRuleSetCommand"
    // InternalFml.g:2154:1: entryRuleSetCommand returns [EObject current=null] : iv_ruleSetCommand= ruleSetCommand EOF ;
    public final EObject entryRuleSetCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetCommand = null;


        try {
            // InternalFml.g:2154:51: (iv_ruleSetCommand= ruleSetCommand EOF )
            // InternalFml.g:2155:2: iv_ruleSetCommand= ruleSetCommand EOF
            {
             newCompositeNode(grammarAccess.getSetCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetCommand=ruleSetCommand();

            state._fsp--;

             current =iv_ruleSetCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetCommand"


    // $ANTLR start "ruleSetCommand"
    // InternalFml.g:2161:1: ruleSetCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_SetExpr_1= ruleSetExpr | this_FeatureOperation_2= ruleFeatureOperation | this_Cliques_3= ruleCliques | this_Cores_4= ruleCores | this_Deads_5= ruleDeads | this_FullMandatorys_6= ruleFullMandatorys | this_SetToNames_7= ruleSetToNames | this_SetUnionOrIntersection_8= ruleSetUnionOrIntersection | this_Leaves_9= ruleLeaves | this_SetEmpty_10= ruleSetEmpty | this_SelectedConfiguration_11= ruleSelectedConfiguration | this_DeselectedConfiguration_12= ruleDeselectedConfiguration | this_ConstraintExpr_13= ruleConstraintExpr | this_GetConstraints_14= ruleGetConstraints | this_ComputeConstraints_15= ruleComputeConstraints | this_GetFGroups_16= ruleGetFGroups | this_ComputeFGroups_17= ruleComputeFGroups | this_PairwiseCommand_18= rulePairwiseCommand ) ;
    public final EObject ruleSetCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_SetExpr_1 = null;

        EObject this_FeatureOperation_2 = null;

        EObject this_Cliques_3 = null;

        EObject this_Cores_4 = null;

        EObject this_Deads_5 = null;

        EObject this_FullMandatorys_6 = null;

        EObject this_SetToNames_7 = null;

        EObject this_SetUnionOrIntersection_8 = null;

        EObject this_Leaves_9 = null;

        EObject this_SetEmpty_10 = null;

        EObject this_SelectedConfiguration_11 = null;

        EObject this_DeselectedConfiguration_12 = null;

        EObject this_ConstraintExpr_13 = null;

        EObject this_GetConstraints_14 = null;

        EObject this_ComputeConstraints_15 = null;

        EObject this_GetFGroups_16 = null;

        EObject this_ComputeFGroups_17 = null;

        EObject this_PairwiseCommand_18 = null;



        	enterRule();

        try {
            // InternalFml.g:2167:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_SetExpr_1= ruleSetExpr | this_FeatureOperation_2= ruleFeatureOperation | this_Cliques_3= ruleCliques | this_Cores_4= ruleCores | this_Deads_5= ruleDeads | this_FullMandatorys_6= ruleFullMandatorys | this_SetToNames_7= ruleSetToNames | this_SetUnionOrIntersection_8= ruleSetUnionOrIntersection | this_Leaves_9= ruleLeaves | this_SetEmpty_10= ruleSetEmpty | this_SelectedConfiguration_11= ruleSelectedConfiguration | this_DeselectedConfiguration_12= ruleDeselectedConfiguration | this_ConstraintExpr_13= ruleConstraintExpr | this_GetConstraints_14= ruleGetConstraints | this_ComputeConstraints_15= ruleComputeConstraints | this_GetFGroups_16= ruleGetFGroups | this_ComputeFGroups_17= ruleComputeFGroups | this_PairwiseCommand_18= rulePairwiseCommand ) )
            // InternalFml.g:2168:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_SetExpr_1= ruleSetExpr | this_FeatureOperation_2= ruleFeatureOperation | this_Cliques_3= ruleCliques | this_Cores_4= ruleCores | this_Deads_5= ruleDeads | this_FullMandatorys_6= ruleFullMandatorys | this_SetToNames_7= ruleSetToNames | this_SetUnionOrIntersection_8= ruleSetUnionOrIntersection | this_Leaves_9= ruleLeaves | this_SetEmpty_10= ruleSetEmpty | this_SelectedConfiguration_11= ruleSelectedConfiguration | this_DeselectedConfiguration_12= ruleDeselectedConfiguration | this_ConstraintExpr_13= ruleConstraintExpr | this_GetConstraints_14= ruleGetConstraints | this_ComputeConstraints_15= ruleComputeConstraints | this_GetFGroups_16= ruleGetFGroups | this_ComputeFGroups_17= ruleComputeFGroups | this_PairwiseCommand_18= rulePairwiseCommand )
            {
            // InternalFml.g:2168:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_SetExpr_1= ruleSetExpr | this_FeatureOperation_2= ruleFeatureOperation | this_Cliques_3= ruleCliques | this_Cores_4= ruleCores | this_Deads_5= ruleDeads | this_FullMandatorys_6= ruleFullMandatorys | this_SetToNames_7= ruleSetToNames | this_SetUnionOrIntersection_8= ruleSetUnionOrIntersection | this_Leaves_9= ruleLeaves | this_SetEmpty_10= ruleSetEmpty | this_SelectedConfiguration_11= ruleSelectedConfiguration | this_DeselectedConfiguration_12= ruleDeselectedConfiguration | this_ConstraintExpr_13= ruleConstraintExpr | this_GetConstraints_14= ruleGetConstraints | this_ComputeConstraints_15= ruleComputeConstraints | this_GetFGroups_16= ruleGetFGroups | this_ComputeFGroups_17= ruleComputeFGroups | this_PairwiseCommand_18= rulePairwiseCommand )
            int alt26=19;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt26=1;
                }
                break;
            case RULE_LEFT_BRACKET:
                {
                alt26=2;
                }
                break;
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt26=3;
                }
                break;
            case 136:
                {
                alt26=4;
                }
                break;
            case 132:
                {
                alt26=5;
                }
                break;
            case 133:
                {
                alt26=6;
                }
                break;
            case 134:
            case 135:
                {
                alt26=7;
                }
                break;
            case 73:
                {
                alt26=8;
                }
                break;
            case 66:
            case 67:
            case 68:
                {
                alt26=9;
                }
                break;
            case 53:
                {
                alt26=10;
                }
                break;
            case 69:
                {
                alt26=11;
                }
                break;
            case 125:
                {
                alt26=12;
                }
                break;
            case 126:
                {
                alt26=13;
                }
                break;
            case 35:
                {
                alt26=14;
                }
                break;
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
                {
                alt26=15;
                }
                break;
            case 181:
            case 182:
            case 183:
                {
                alt26=16;
                }
                break;
            case 184:
            case 185:
            case 186:
                {
                alt26=17;
                }
                break;
            case 187:
            case 188:
            case 189:
                {
                alt26=18;
                }
                break;
            case 55:
                {
                alt26=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalFml.g:2169:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2178:3: this_SetExpr_1= ruleSetExpr
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getSetExprParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetExpr_1=ruleSetExpr();

                    state._fsp--;


                    			current = this_SetExpr_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:2187:3: this_FeatureOperation_2= ruleFeatureOperation
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getFeatureOperationParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_FeatureOperation_2=ruleFeatureOperation();

                    state._fsp--;


                    			current = this_FeatureOperation_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:2196:3: this_Cliques_3= ruleCliques
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getCliquesParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Cliques_3=ruleCliques();

                    state._fsp--;


                    			current = this_Cliques_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:2205:3: this_Cores_4= ruleCores
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getCoresParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Cores_4=ruleCores();

                    state._fsp--;


                    			current = this_Cores_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:2214:3: this_Deads_5= ruleDeads
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getDeadsParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_Deads_5=ruleDeads();

                    state._fsp--;


                    			current = this_Deads_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalFml.g:2223:3: this_FullMandatorys_6= ruleFullMandatorys
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getFullMandatorysParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_FullMandatorys_6=ruleFullMandatorys();

                    state._fsp--;


                    			current = this_FullMandatorys_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalFml.g:2232:3: this_SetToNames_7= ruleSetToNames
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getSetToNamesParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetToNames_7=ruleSetToNames();

                    state._fsp--;


                    			current = this_SetToNames_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalFml.g:2241:3: this_SetUnionOrIntersection_8= ruleSetUnionOrIntersection
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getSetUnionOrIntersectionParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetUnionOrIntersection_8=ruleSetUnionOrIntersection();

                    state._fsp--;


                    			current = this_SetUnionOrIntersection_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalFml.g:2250:3: this_Leaves_9= ruleLeaves
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getLeavesParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_Leaves_9=ruleLeaves();

                    state._fsp--;


                    			current = this_Leaves_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalFml.g:2259:3: this_SetEmpty_10= ruleSetEmpty
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getSetEmptyParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetEmpty_10=ruleSetEmpty();

                    state._fsp--;


                    			current = this_SetEmpty_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalFml.g:2268:3: this_SelectedConfiguration_11= ruleSelectedConfiguration
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getSelectedConfigurationParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_SelectedConfiguration_11=ruleSelectedConfiguration();

                    state._fsp--;


                    			current = this_SelectedConfiguration_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 13 :
                    // InternalFml.g:2277:3: this_DeselectedConfiguration_12= ruleDeselectedConfiguration
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getDeselectedConfigurationParserRuleCall_12());
                    		
                    pushFollow(FOLLOW_2);
                    this_DeselectedConfiguration_12=ruleDeselectedConfiguration();

                    state._fsp--;


                    			current = this_DeselectedConfiguration_12;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 14 :
                    // InternalFml.g:2286:3: this_ConstraintExpr_13= ruleConstraintExpr
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getConstraintExprParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_ConstraintExpr_13=ruleConstraintExpr();

                    state._fsp--;


                    			current = this_ConstraintExpr_13;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 15 :
                    // InternalFml.g:2295:3: this_GetConstraints_14= ruleGetConstraints
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getGetConstraintsParserRuleCall_14());
                    		
                    pushFollow(FOLLOW_2);
                    this_GetConstraints_14=ruleGetConstraints();

                    state._fsp--;


                    			current = this_GetConstraints_14;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 16 :
                    // InternalFml.g:2304:3: this_ComputeConstraints_15= ruleComputeConstraints
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getComputeConstraintsParserRuleCall_15());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComputeConstraints_15=ruleComputeConstraints();

                    state._fsp--;


                    			current = this_ComputeConstraints_15;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 17 :
                    // InternalFml.g:2313:3: this_GetFGroups_16= ruleGetFGroups
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getGetFGroupsParserRuleCall_16());
                    		
                    pushFollow(FOLLOW_2);
                    this_GetFGroups_16=ruleGetFGroups();

                    state._fsp--;


                    			current = this_GetFGroups_16;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 18 :
                    // InternalFml.g:2322:3: this_ComputeFGroups_17= ruleComputeFGroups
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getComputeFGroupsParserRuleCall_17());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComputeFGroups_17=ruleComputeFGroups();

                    state._fsp--;


                    			current = this_ComputeFGroups_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 19 :
                    // InternalFml.g:2331:3: this_PairwiseCommand_18= rulePairwiseCommand
                    {

                    			newCompositeNode(grammarAccess.getSetCommandAccess().getPairwiseCommandParserRuleCall_18());
                    		
                    pushFollow(FOLLOW_2);
                    this_PairwiseCommand_18=rulePairwiseCommand();

                    state._fsp--;


                    			current = this_PairwiseCommand_18;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetCommand"


    // $ANTLR start "entryRuleLeaves"
    // InternalFml.g:2343:1: entryRuleLeaves returns [EObject current=null] : iv_ruleLeaves= ruleLeaves EOF ;
    public final EObject entryRuleLeaves() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLeaves = null;


        try {
            // InternalFml.g:2343:47: (iv_ruleLeaves= ruleLeaves EOF )
            // InternalFml.g:2344:2: iv_ruleLeaves= ruleLeaves EOF
            {
             newCompositeNode(grammarAccess.getLeavesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLeaves=ruleLeaves();

            state._fsp--;

             current =iv_ruleLeaves; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLeaves"


    // $ANTLR start "ruleLeaves"
    // InternalFml.g:2350:1: ruleLeaves returns [EObject current=null] : (otherlv_0= 'leaves' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleLeaves() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2356:2: ( (otherlv_0= 'leaves' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:2357:2: (otherlv_0= 'leaves' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:2357:2: (otherlv_0= 'leaves' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:2358:3: otherlv_0= 'leaves' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getLeavesAccess().getLeavesKeyword_0());
            		
            // InternalFml.g:2362:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2363:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2363:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2364:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getLeavesAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLeavesRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLeaves"


    // $ANTLR start "entryRuleConstraintCommand"
    // InternalFml.g:2385:1: entryRuleConstraintCommand returns [EObject current=null] : iv_ruleConstraintCommand= ruleConstraintCommand EOF ;
    public final EObject entryRuleConstraintCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintCommand = null;


        try {
            // InternalFml.g:2385:58: (iv_ruleConstraintCommand= ruleConstraintCommand EOF )
            // InternalFml.g:2386:2: iv_ruleConstraintCommand= ruleConstraintCommand EOF
            {
             newCompositeNode(grammarAccess.getConstraintCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintCommand=ruleConstraintCommand();

            state._fsp--;

             current =iv_ruleConstraintCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintCommand"


    // $ANTLR start "ruleConstraintCommand"
    // InternalFml.g:2392:1: ruleConstraintCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_AtomicConstraintExpr_1= ruleAtomicConstraintExpr ) ;
    public final EObject ruleConstraintCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_AtomicConstraintExpr_1 = null;



        	enterRule();

        try {
            // InternalFml.g:2398:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_AtomicConstraintExpr_1= ruleAtomicConstraintExpr ) )
            // InternalFml.g:2399:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_AtomicConstraintExpr_1= ruleAtomicConstraintExpr )
            {
            // InternalFml.g:2399:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_AtomicConstraintExpr_1= ruleAtomicConstraintExpr )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_ID||LA27_0==168) ) {
                alt27=1;
            }
            else if ( (LA27_0==34) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalFml.g:2400:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getConstraintCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2409:3: this_AtomicConstraintExpr_1= ruleAtomicConstraintExpr
                    {

                    			newCompositeNode(grammarAccess.getConstraintCommandAccess().getAtomicConstraintExprParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_AtomicConstraintExpr_1=ruleAtomicConstraintExpr();

                    state._fsp--;


                    			current = this_AtomicConstraintExpr_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintCommand"


    // $ANTLR start "entryRuleGetConstraints"
    // InternalFml.g:2421:1: entryRuleGetConstraints returns [EObject current=null] : iv_ruleGetConstraints= ruleGetConstraints EOF ;
    public final EObject entryRuleGetConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGetConstraints = null;


        try {
            // InternalFml.g:2421:55: (iv_ruleGetConstraints= ruleGetConstraints EOF )
            // InternalFml.g:2422:2: iv_ruleGetConstraints= ruleGetConstraints EOF
            {
             newCompositeNode(grammarAccess.getGetConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGetConstraints=ruleGetConstraints();

            state._fsp--;

             current =iv_ruleGetConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGetConstraints"


    // $ANTLR start "ruleGetConstraints"
    // InternalFml.g:2428:1: ruleGetConstraints returns [EObject current=null] : ( ( (lv_kindOfGet_0_0= ruleKindOfGet ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleGetConstraints() throws RecognitionException {
        EObject current = null;

        Enumerator lv_kindOfGet_0_0 = null;

        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2434:2: ( ( ( (lv_kindOfGet_0_0= ruleKindOfGet ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:2435:2: ( ( (lv_kindOfGet_0_0= ruleKindOfGet ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:2435:2: ( ( (lv_kindOfGet_0_0= ruleKindOfGet ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:2436:3: ( (lv_kindOfGet_0_0= ruleKindOfGet ) ) ( (lv_fm_1_0= ruleFMCommand ) )
            {
            // InternalFml.g:2436:3: ( (lv_kindOfGet_0_0= ruleKindOfGet ) )
            // InternalFml.g:2437:4: (lv_kindOfGet_0_0= ruleKindOfGet )
            {
            // InternalFml.g:2437:4: (lv_kindOfGet_0_0= ruleKindOfGet )
            // InternalFml.g:2438:5: lv_kindOfGet_0_0= ruleKindOfGet
            {

            					newCompositeNode(grammarAccess.getGetConstraintsAccess().getKindOfGetKindOfGetEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_19);
            lv_kindOfGet_0_0=ruleKindOfGet();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGetConstraintsRule());
            					}
            					set(
            						current,
            						"kindOfGet",
            						lv_kindOfGet_0_0,
            						"org.xtext.example.mydsl.Fml.KindOfGet");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2455:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2456:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2456:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2457:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getGetConstraintsAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGetConstraintsRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGetConstraints"


    // $ANTLR start "entryRuleComputeConstraints"
    // InternalFml.g:2478:1: entryRuleComputeConstraints returns [EObject current=null] : iv_ruleComputeConstraints= ruleComputeConstraints EOF ;
    public final EObject entryRuleComputeConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputeConstraints = null;


        try {
            // InternalFml.g:2478:59: (iv_ruleComputeConstraints= ruleComputeConstraints EOF )
            // InternalFml.g:2479:2: iv_ruleComputeConstraints= ruleComputeConstraints EOF
            {
             newCompositeNode(grammarAccess.getComputeConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComputeConstraints=ruleComputeConstraints();

            state._fsp--;

             current =iv_ruleComputeConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComputeConstraints"


    // $ANTLR start "ruleComputeConstraints"
    // InternalFml.g:2485:1: ruleComputeConstraints returns [EObject current=null] : ( ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )? ) ;
    public final EObject ruleComputeConstraints() throws RecognitionException {
        EObject current = null;

        Token lv_over_2_0=null;
        Enumerator lv_kindOfCompute_0_0 = null;

        EObject lv_fm_1_0 = null;

        EObject lv_fts_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2491:2: ( ( ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )? ) )
            // InternalFml.g:2492:2: ( ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )? )
            {
            // InternalFml.g:2492:2: ( ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )? )
            // InternalFml.g:2493:3: ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )?
            {
            // InternalFml.g:2493:3: ( (lv_kindOfCompute_0_0= ruleKindOfCompute ) )
            // InternalFml.g:2494:4: (lv_kindOfCompute_0_0= ruleKindOfCompute )
            {
            // InternalFml.g:2494:4: (lv_kindOfCompute_0_0= ruleKindOfCompute )
            // InternalFml.g:2495:5: lv_kindOfCompute_0_0= ruleKindOfCompute
            {

            					newCompositeNode(grammarAccess.getComputeConstraintsAccess().getKindOfComputeKindOfComputeEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_19);
            lv_kindOfCompute_0_0=ruleKindOfCompute();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComputeConstraintsRule());
            					}
            					set(
            						current,
            						"kindOfCompute",
            						lv_kindOfCompute_0_0,
            						"org.xtext.example.mydsl.Fml.KindOfCompute");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2512:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2513:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2513:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2514:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getComputeConstraintsAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_28);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComputeConstraintsRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2531:3: ( ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==54) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalFml.g:2532:4: ( (lv_over_2_0= 'over' ) ) ( (lv_fts_3_0= ruleSetCommand ) )
                    {
                    // InternalFml.g:2532:4: ( (lv_over_2_0= 'over' ) )
                    // InternalFml.g:2533:5: (lv_over_2_0= 'over' )
                    {
                    // InternalFml.g:2533:5: (lv_over_2_0= 'over' )
                    // InternalFml.g:2534:6: lv_over_2_0= 'over'
                    {
                    lv_over_2_0=(Token)match(input,54,FOLLOW_29); 

                    						newLeafNode(lv_over_2_0, grammarAccess.getComputeConstraintsAccess().getOverOverKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getComputeConstraintsRule());
                    						}
                    						setWithLastConsumed(current, "over", true, "over");
                    					

                    }


                    }

                    // InternalFml.g:2546:4: ( (lv_fts_3_0= ruleSetCommand ) )
                    // InternalFml.g:2547:5: (lv_fts_3_0= ruleSetCommand )
                    {
                    // InternalFml.g:2547:5: (lv_fts_3_0= ruleSetCommand )
                    // InternalFml.g:2548:6: lv_fts_3_0= ruleSetCommand
                    {

                    						newCompositeNode(grammarAccess.getComputeConstraintsAccess().getFtsSetCommandParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fts_3_0=ruleSetCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getComputeConstraintsRule());
                    						}
                    						set(
                    							current,
                    							"fts",
                    							lv_fts_3_0,
                    							"org.xtext.example.mydsl.Fml.SetCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComputeConstraints"


    // $ANTLR start "entryRuleGetFGroups"
    // InternalFml.g:2570:1: entryRuleGetFGroups returns [EObject current=null] : iv_ruleGetFGroups= ruleGetFGroups EOF ;
    public final EObject entryRuleGetFGroups() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGetFGroups = null;


        try {
            // InternalFml.g:2570:51: (iv_ruleGetFGroups= ruleGetFGroups EOF )
            // InternalFml.g:2571:2: iv_ruleGetFGroups= ruleGetFGroups EOF
            {
             newCompositeNode(grammarAccess.getGetFGroupsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGetFGroups=ruleGetFGroups();

            state._fsp--;

             current =iv_ruleGetFGroups; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGetFGroups"


    // $ANTLR start "ruleGetFGroups"
    // InternalFml.g:2577:1: ruleGetFGroups returns [EObject current=null] : ( ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleGetFGroups() throws RecognitionException {
        EObject current = null;

        Enumerator lv_kindOfGroups_0_0 = null;

        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2583:2: ( ( ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:2584:2: ( ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:2584:2: ( ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:2585:3: ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) )
            {
            // InternalFml.g:2585:3: ( (lv_kindOfGroups_0_0= ruleKindOfGetGroups ) )
            // InternalFml.g:2586:4: (lv_kindOfGroups_0_0= ruleKindOfGetGroups )
            {
            // InternalFml.g:2586:4: (lv_kindOfGroups_0_0= ruleKindOfGetGroups )
            // InternalFml.g:2587:5: lv_kindOfGroups_0_0= ruleKindOfGetGroups
            {

            					newCompositeNode(grammarAccess.getGetFGroupsAccess().getKindOfGroupsKindOfGetGroupsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_19);
            lv_kindOfGroups_0_0=ruleKindOfGetGroups();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGetFGroupsRule());
            					}
            					set(
            						current,
            						"kindOfGroups",
            						lv_kindOfGroups_0_0,
            						"org.xtext.example.mydsl.Fml.KindOfGetGroups");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2604:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2605:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2605:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2606:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getGetFGroupsAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGetFGroupsRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGetFGroups"


    // $ANTLR start "entryRuleComputeFGroups"
    // InternalFml.g:2627:1: entryRuleComputeFGroups returns [EObject current=null] : iv_ruleComputeFGroups= ruleComputeFGroups EOF ;
    public final EObject entryRuleComputeFGroups() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputeFGroups = null;


        try {
            // InternalFml.g:2627:55: (iv_ruleComputeFGroups= ruleComputeFGroups EOF )
            // InternalFml.g:2628:2: iv_ruleComputeFGroups= ruleComputeFGroups EOF
            {
             newCompositeNode(grammarAccess.getComputeFGroupsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComputeFGroups=ruleComputeFGroups();

            state._fsp--;

             current =iv_ruleComputeFGroups; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComputeFGroups"


    // $ANTLR start "ruleComputeFGroups"
    // InternalFml.g:2634:1: ruleComputeFGroups returns [EObject current=null] : ( ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleComputeFGroups() throws RecognitionException {
        EObject current = null;

        Enumerator lv_kindOfGroups_0_0 = null;

        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2640:2: ( ( ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:2641:2: ( ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:2641:2: ( ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:2642:3: ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) ) ( (lv_fm_1_0= ruleFMCommand ) )
            {
            // InternalFml.g:2642:3: ( (lv_kindOfGroups_0_0= ruleKindOfComputeGroups ) )
            // InternalFml.g:2643:4: (lv_kindOfGroups_0_0= ruleKindOfComputeGroups )
            {
            // InternalFml.g:2643:4: (lv_kindOfGroups_0_0= ruleKindOfComputeGroups )
            // InternalFml.g:2644:5: lv_kindOfGroups_0_0= ruleKindOfComputeGroups
            {

            					newCompositeNode(grammarAccess.getComputeFGroupsAccess().getKindOfGroupsKindOfComputeGroupsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_19);
            lv_kindOfGroups_0_0=ruleKindOfComputeGroups();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComputeFGroupsRule());
            					}
            					set(
            						current,
            						"kindOfGroups",
            						lv_kindOfGroups_0_0,
            						"org.xtext.example.mydsl.Fml.KindOfComputeGroups");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2661:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2662:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2662:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2663:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getComputeFGroupsAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComputeFGroupsRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComputeFGroups"


    // $ANTLR start "entryRulePairwiseCommand"
    // InternalFml.g:2684:1: entryRulePairwiseCommand returns [EObject current=null] : iv_rulePairwiseCommand= rulePairwiseCommand EOF ;
    public final EObject entryRulePairwiseCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePairwiseCommand = null;


        try {
            // InternalFml.g:2684:56: (iv_rulePairwiseCommand= rulePairwiseCommand EOF )
            // InternalFml.g:2685:2: iv_rulePairwiseCommand= rulePairwiseCommand EOF
            {
             newCompositeNode(grammarAccess.getPairwiseCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePairwiseCommand=rulePairwiseCommand();

            state._fsp--;

             current =iv_rulePairwiseCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePairwiseCommand"


    // $ANTLR start "rulePairwiseCommand"
    // InternalFml.g:2691:1: rulePairwiseCommand returns [EObject current=null] : (otherlv_0= 'pw' ( (lv_fm_1_0= ruleFMCommand ) ) (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )? (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )? ) ;
    public final EObject rulePairwiseCommand() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_fm_1_0 = null;

        EObject lv_minimization_3_0 = null;

        EObject lv_partial_5_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2697:2: ( (otherlv_0= 'pw' ( (lv_fm_1_0= ruleFMCommand ) ) (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )? (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )? ) )
            // InternalFml.g:2698:2: (otherlv_0= 'pw' ( (lv_fm_1_0= ruleFMCommand ) ) (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )? (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )? )
            {
            // InternalFml.g:2698:2: (otherlv_0= 'pw' ( (lv_fm_1_0= ruleFMCommand ) ) (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )? (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )? )
            // InternalFml.g:2699:3: otherlv_0= 'pw' ( (lv_fm_1_0= ruleFMCommand ) ) (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )? (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )?
            {
            otherlv_0=(Token)match(input,55,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getPairwiseCommandAccess().getPwKeyword_0());
            		
            // InternalFml.g:2703:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:2704:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:2704:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:2705:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getPairwiseCommandAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_30);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPairwiseCommandRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:2722:3: (otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==56) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalFml.g:2723:4: otherlv_2= 'minimization=' ( (lv_minimization_3_0= ruleIntegerCommand ) )
                    {
                    otherlv_2=(Token)match(input,56,FOLLOW_13); 

                    				newLeafNode(otherlv_2, grammarAccess.getPairwiseCommandAccess().getMinimizationKeyword_2_0());
                    			
                    // InternalFml.g:2727:4: ( (lv_minimization_3_0= ruleIntegerCommand ) )
                    // InternalFml.g:2728:5: (lv_minimization_3_0= ruleIntegerCommand )
                    {
                    // InternalFml.g:2728:5: (lv_minimization_3_0= ruleIntegerCommand )
                    // InternalFml.g:2729:6: lv_minimization_3_0= ruleIntegerCommand
                    {

                    						newCompositeNode(grammarAccess.getPairwiseCommandAccess().getMinimizationIntegerCommandParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_31);
                    lv_minimization_3_0=ruleIntegerCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPairwiseCommandRule());
                    						}
                    						set(
                    							current,
                    							"minimization",
                    							lv_minimization_3_0,
                    							"org.xtext.example.mydsl.Fml.IntegerCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalFml.g:2747:3: (otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==57) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalFml.g:2748:4: otherlv_4= 'partial=' ( (lv_partial_5_0= ruleIntegerCommand ) )
                    {
                    otherlv_4=(Token)match(input,57,FOLLOW_13); 

                    				newLeafNode(otherlv_4, grammarAccess.getPairwiseCommandAccess().getPartialKeyword_3_0());
                    			
                    // InternalFml.g:2752:4: ( (lv_partial_5_0= ruleIntegerCommand ) )
                    // InternalFml.g:2753:5: (lv_partial_5_0= ruleIntegerCommand )
                    {
                    // InternalFml.g:2753:5: (lv_partial_5_0= ruleIntegerCommand )
                    // InternalFml.g:2754:6: lv_partial_5_0= ruleIntegerCommand
                    {

                    						newCompositeNode(grammarAccess.getPairwiseCommandAccess().getPartialIntegerCommandParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_partial_5_0=ruleIntegerCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPairwiseCommandRule());
                    						}
                    						set(
                    							current,
                    							"partial",
                    							lv_partial_5_0,
                    							"org.xtext.example.mydsl.Fml.IntegerCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePairwiseCommand"


    // $ANTLR start "entryRuleIntegerCommand"
    // InternalFml.g:2776:1: entryRuleIntegerCommand returns [EObject current=null] : iv_ruleIntegerCommand= ruleIntegerCommand EOF ;
    public final EObject entryRuleIntegerCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerCommand = null;


        try {
            // InternalFml.g:2776:55: (iv_ruleIntegerCommand= ruleIntegerCommand EOF )
            // InternalFml.g:2777:2: iv_ruleIntegerCommand= ruleIntegerCommand EOF
            {
             newCompositeNode(grammarAccess.getIntegerCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerCommand=ruleIntegerCommand();

            state._fsp--;

             current =iv_ruleIntegerCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerCommand"


    // $ANTLR start "ruleIntegerCommand"
    // InternalFml.g:2783:1: ruleIntegerCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_IntegerExpr_1= ruleIntegerExpr | this_SetCard_2= ruleSetCard | this_StringLength_3= ruleStringLength | this_StringIndexOf_4= ruleStringIndexOf | this_DoubleCommand_5= ruleDoubleCommand ) ;
    public final EObject ruleIntegerCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_IntegerExpr_1 = null;

        EObject this_SetCard_2 = null;

        EObject this_StringLength_3 = null;

        EObject this_StringIndexOf_4 = null;

        EObject this_DoubleCommand_5 = null;



        	enterRule();

        try {
            // InternalFml.g:2789:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_IntegerExpr_1= ruleIntegerExpr | this_SetCard_2= ruleSetCard | this_StringLength_3= ruleStringLength | this_StringIndexOf_4= ruleStringIndexOf | this_DoubleCommand_5= ruleDoubleCommand ) )
            // InternalFml.g:2790:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_IntegerExpr_1= ruleIntegerExpr | this_SetCard_2= ruleSetCard | this_StringLength_3= ruleStringLength | this_StringIndexOf_4= ruleStringIndexOf | this_DoubleCommand_5= ruleDoubleCommand )
            {
            // InternalFml.g:2790:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_IntegerExpr_1= ruleIntegerExpr | this_SetCard_2= ruleSetCard | this_StringLength_3= ruleStringLength | this_StringIndexOf_4= ruleStringIndexOf | this_DoubleCommand_5= ruleDoubleCommand )
            int alt31=6;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt31=1;
                }
                break;
            case RULE_INT:
                {
                alt31=2;
                }
                break;
            case 64:
                {
                alt31=3;
                }
                break;
            case 86:
                {
                alt31=4;
                }
                break;
            case 85:
                {
                alt31=5;
                }
                break;
            case 92:
                {
                alt31=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // InternalFml.g:2791:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2800:3: this_IntegerExpr_1= ruleIntegerExpr
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getIntegerExprParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntegerExpr_1=ruleIntegerExpr();

                    state._fsp--;


                    			current = this_IntegerExpr_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:2809:3: this_SetCard_2= ruleSetCard
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getSetCardParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetCard_2=ruleSetCard();

                    state._fsp--;


                    			current = this_SetCard_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:2818:3: this_StringLength_3= ruleStringLength
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getStringLengthParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringLength_3=ruleStringLength();

                    state._fsp--;


                    			current = this_StringLength_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:2827:3: this_StringIndexOf_4= ruleStringIndexOf
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getStringIndexOfParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringIndexOf_4=ruleStringIndexOf();

                    state._fsp--;


                    			current = this_StringIndexOf_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:2836:3: this_DoubleCommand_5= ruleDoubleCommand
                    {

                    			newCompositeNode(grammarAccess.getIntegerCommandAccess().getDoubleCommandParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_DoubleCommand_5=ruleDoubleCommand();

                    state._fsp--;


                    			current = this_DoubleCommand_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerCommand"


    // $ANTLR start "entryRuleDoubleCommand"
    // InternalFml.g:2848:1: entryRuleDoubleCommand returns [EObject current=null] : iv_ruleDoubleCommand= ruleDoubleCommand EOF ;
    public final EObject entryRuleDoubleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleCommand = null;


        try {
            // InternalFml.g:2848:54: (iv_ruleDoubleCommand= ruleDoubleCommand EOF )
            // InternalFml.g:2849:2: iv_ruleDoubleCommand= ruleDoubleCommand EOF
            {
             newCompositeNode(grammarAccess.getDoubleCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDoubleCommand=ruleDoubleCommand();

            state._fsp--;

             current =iv_ruleDoubleCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoubleCommand"


    // $ANTLR start "ruleDoubleCommand"
    // InternalFml.g:2855:1: ruleDoubleCommand returns [EObject current=null] : this_CTCRCommand_0= ruleCTCRCommand ;
    public final EObject ruleDoubleCommand() throws RecognitionException {
        EObject current = null;

        EObject this_CTCRCommand_0 = null;



        	enterRule();

        try {
            // InternalFml.g:2861:2: (this_CTCRCommand_0= ruleCTCRCommand )
            // InternalFml.g:2862:2: this_CTCRCommand_0= ruleCTCRCommand
            {

            		newCompositeNode(grammarAccess.getDoubleCommandAccess().getCTCRCommandParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_CTCRCommand_0=ruleCTCRCommand();

            state._fsp--;


            		current = this_CTCRCommand_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoubleCommand"


    // $ANTLR start "entryRuleVariabilityOpCommand"
    // InternalFml.g:2873:1: entryRuleVariabilityOpCommand returns [EObject current=null] : iv_ruleVariabilityOpCommand= ruleVariabilityOpCommand EOF ;
    public final EObject entryRuleVariabilityOpCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariabilityOpCommand = null;


        try {
            // InternalFml.g:2873:61: (iv_ruleVariabilityOpCommand= ruleVariabilityOpCommand EOF )
            // InternalFml.g:2874:2: iv_ruleVariabilityOpCommand= ruleVariabilityOpCommand EOF
            {
             newCompositeNode(grammarAccess.getVariabilityOpCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariabilityOpCommand=ruleVariabilityOpCommand();

            state._fsp--;

             current =iv_ruleVariabilityOpCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariabilityOpCommand"


    // $ANTLR start "ruleVariabilityOpCommand"
    // InternalFml.g:2880:1: ruleVariabilityOpCommand returns [EObject current=null] : (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureVariabilityOperator_2= ruleFeatureVariabilityOperator ) ;
    public final EObject ruleVariabilityOpCommand() throws RecognitionException {
        EObject current = null;

        EObject this_IdentifierExpr_0 = null;

        EObject this_CopyVariable_1 = null;

        EObject this_FeatureVariabilityOperator_2 = null;



        	enterRule();

        try {
            // InternalFml.g:2886:2: ( (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureVariabilityOperator_2= ruleFeatureVariabilityOperator ) )
            // InternalFml.g:2887:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureVariabilityOperator_2= ruleFeatureVariabilityOperator )
            {
            // InternalFml.g:2887:2: (this_IdentifierExpr_0= ruleIdentifierExpr | this_CopyVariable_1= ruleCopyVariable | this_FeatureVariabilityOperator_2= ruleFeatureVariabilityOperator )
            int alt32=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 168:
                {
                alt32=1;
                }
                break;
            case 145:
            case 146:
                {
                alt32=2;
                }
                break;
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
                {
                alt32=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // InternalFml.g:2888:3: this_IdentifierExpr_0= ruleIdentifierExpr
                    {

                    			newCompositeNode(grammarAccess.getVariabilityOpCommandAccess().getIdentifierExprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IdentifierExpr_0=ruleIdentifierExpr();

                    state._fsp--;


                    			current = this_IdentifierExpr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:2897:3: this_CopyVariable_1= ruleCopyVariable
                    {

                    			newCompositeNode(grammarAccess.getVariabilityOpCommandAccess().getCopyVariableParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CopyVariable_1=ruleCopyVariable();

                    state._fsp--;


                    			current = this_CopyVariable_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:2906:3: this_FeatureVariabilityOperator_2= ruleFeatureVariabilityOperator
                    {

                    			newCompositeNode(grammarAccess.getVariabilityOpCommandAccess().getFeatureVariabilityOperatorParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_FeatureVariabilityOperator_2=ruleFeatureVariabilityOperator();

                    state._fsp--;


                    			current = this_FeatureVariabilityOperator_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariabilityOpCommand"


    // $ANTLR start "entryRuleAnalysisOperation"
    // InternalFml.g:2918:1: entryRuleAnalysisOperation returns [EObject current=null] : iv_ruleAnalysisOperation= ruleAnalysisOperation EOF ;
    public final EObject entryRuleAnalysisOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysisOperation = null;


        try {
            // InternalFml.g:2918:58: (iv_ruleAnalysisOperation= ruleAnalysisOperation EOF )
            // InternalFml.g:2919:2: iv_ruleAnalysisOperation= ruleAnalysisOperation EOF
            {
             newCompositeNode(grammarAccess.getAnalysisOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalysisOperation=ruleAnalysisOperation();

            state._fsp--;

             current =iv_ruleAnalysisOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnalysisOperation"


    // $ANTLR start "ruleAnalysisOperation"
    // InternalFml.g:2925:1: ruleAnalysisOperation returns [EObject current=null] : ( ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) ) ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) ) ) ;
    public final EObject ruleAnalysisOperation() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        Token lv_op_0_3=null;
        Token lv_op_0_4=null;
        Token lv_op_0_5=null;
        Token lv_op_0_6=null;
        EObject lv_fm_1_1 = null;

        EObject lv_fm_1_2 = null;



        	enterRule();

        try {
            // InternalFml.g:2931:2: ( ( ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) ) ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) ) ) )
            // InternalFml.g:2932:2: ( ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) ) ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) ) )
            {
            // InternalFml.g:2932:2: ( ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) ) ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:2933:3: ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) ) ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:2933:3: ( ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) ) )
            // InternalFml.g:2934:4: ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) )
            {
            // InternalFml.g:2934:4: ( (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' ) )
            // InternalFml.g:2935:5: (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' )
            {
            // InternalFml.g:2935:5: (lv_op_0_1= 'isValid' | lv_op_0_2= 'counting' | lv_op_0_3= 'configs' | lv_op_0_4= 'nbFeatures' | lv_op_0_5= 'root' | lv_op_0_6= 'features' )
            int alt33=6;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt33=1;
                }
                break;
            case 59:
                {
                alt33=2;
                }
                break;
            case 60:
                {
                alt33=3;
                }
                break;
            case 61:
                {
                alt33=4;
                }
                break;
            case 62:
                {
                alt33=5;
                }
                break;
            case 63:
                {
                alt33=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // InternalFml.g:2936:6: lv_op_0_1= 'isValid'
                    {
                    lv_op_0_1=(Token)match(input,58,FOLLOW_32); 

                    						newLeafNode(lv_op_0_1, grammarAccess.getAnalysisOperationAccess().getOpIsValidKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:2947:6: lv_op_0_2= 'counting'
                    {
                    lv_op_0_2=(Token)match(input,59,FOLLOW_32); 

                    						newLeafNode(lv_op_0_2, grammarAccess.getAnalysisOperationAccess().getOpCountingKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalFml.g:2958:6: lv_op_0_3= 'configs'
                    {
                    lv_op_0_3=(Token)match(input,60,FOLLOW_32); 

                    						newLeafNode(lv_op_0_3, grammarAccess.getAnalysisOperationAccess().getOpConfigsKeyword_0_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalFml.g:2969:6: lv_op_0_4= 'nbFeatures'
                    {
                    lv_op_0_4=(Token)match(input,61,FOLLOW_32); 

                    						newLeafNode(lv_op_0_4, grammarAccess.getAnalysisOperationAccess().getOpNbFeaturesKeyword_0_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_4, null);
                    					

                    }
                    break;
                case 5 :
                    // InternalFml.g:2980:6: lv_op_0_5= 'root'
                    {
                    lv_op_0_5=(Token)match(input,62,FOLLOW_32); 

                    						newLeafNode(lv_op_0_5, grammarAccess.getAnalysisOperationAccess().getOpRootKeyword_0_0_4());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_5, null);
                    					

                    }
                    break;
                case 6 :
                    // InternalFml.g:2991:6: lv_op_0_6= 'features'
                    {
                    lv_op_0_6=(Token)match(input,63,FOLLOW_32); 

                    						newLeafNode(lv_op_0_6, grammarAccess.getAnalysisOperationAccess().getOpFeaturesKeyword_0_0_5());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnalysisOperationRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_6, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:3004:3: ( ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) ) )
            // InternalFml.g:3005:4: ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) )
            {
            // InternalFml.g:3005:4: ( (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand ) )
            // InternalFml.g:3006:5: (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand )
            {
            // InternalFml.g:3006:5: (lv_fm_1_1= ruleFMCommand | lv_fm_1_2= ruleConfigurationCommand )
            int alt34=2;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt34=1;
                }
                break;
            case 80:
            case 93:
            case 95:
            case 97:
            case 106:
            case 107:
            case 114:
            case 128:
            case 145:
            case 146:
            case 152:
            case 165:
            case 166:
            case 168:
                {
                alt34=1;
                }
                break;
            case 119:
                {
                alt34=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // InternalFml.g:3007:6: lv_fm_1_1= ruleFMCommand
                    {

                    						newCompositeNode(grammarAccess.getAnalysisOperationAccess().getFmFMCommandParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fm_1_1=ruleFMCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAnalysisOperationRule());
                    						}
                    						set(
                    							current,
                    							"fm",
                    							lv_fm_1_1,
                    							"org.xtext.example.mydsl.Fml.FMCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:3023:6: lv_fm_1_2= ruleConfigurationCommand
                    {

                    						newCompositeNode(grammarAccess.getAnalysisOperationAccess().getFmConfigurationCommandParserRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fm_1_2=ruleConfigurationCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAnalysisOperationRule());
                    						}
                    						set(
                    							current,
                    							"fm",
                    							lv_fm_1_2,
                    							"org.xtext.example.mydsl.Fml.ConfigurationCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalysisOperation"


    // $ANTLR start "entryRuleSetOperations"
    // InternalFml.g:3045:1: entryRuleSetOperations returns [EObject current=null] : iv_ruleSetOperations= ruleSetOperations EOF ;
    public final EObject entryRuleSetOperations() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetOperations = null;


        try {
            // InternalFml.g:3045:54: (iv_ruleSetOperations= ruleSetOperations EOF )
            // InternalFml.g:3046:2: iv_ruleSetOperations= ruleSetOperations EOF
            {
             newCompositeNode(grammarAccess.getSetOperationsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetOperations=ruleSetOperations();

            state._fsp--;

             current =iv_ruleSetOperations; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetOperations"


    // $ANTLR start "ruleSetOperations"
    // InternalFml.g:3052:1: ruleSetOperations returns [EObject current=null] : (this_SetCard_0= ruleSetCard | this_SetToNames_1= ruleSetToNames | this_SetBelongs_2= ruleSetBelongs | this_SetUnionOrIntersection_3= ruleSetUnionOrIntersection | this_SetEmpty_4= ruleSetEmpty | this_SetAddOrRemove_5= ruleSetAddOrRemove | this_IsEmptySet_6= ruleIsEmptySet ) ;
    public final EObject ruleSetOperations() throws RecognitionException {
        EObject current = null;

        EObject this_SetCard_0 = null;

        EObject this_SetToNames_1 = null;

        EObject this_SetBelongs_2 = null;

        EObject this_SetUnionOrIntersection_3 = null;

        EObject this_SetEmpty_4 = null;

        EObject this_SetAddOrRemove_5 = null;

        EObject this_IsEmptySet_6 = null;



        	enterRule();

        try {
            // InternalFml.g:3058:2: ( (this_SetCard_0= ruleSetCard | this_SetToNames_1= ruleSetToNames | this_SetBelongs_2= ruleSetBelongs | this_SetUnionOrIntersection_3= ruleSetUnionOrIntersection | this_SetEmpty_4= ruleSetEmpty | this_SetAddOrRemove_5= ruleSetAddOrRemove | this_IsEmptySet_6= ruleIsEmptySet ) )
            // InternalFml.g:3059:2: (this_SetCard_0= ruleSetCard | this_SetToNames_1= ruleSetToNames | this_SetBelongs_2= ruleSetBelongs | this_SetUnionOrIntersection_3= ruleSetUnionOrIntersection | this_SetEmpty_4= ruleSetEmpty | this_SetAddOrRemove_5= ruleSetAddOrRemove | this_IsEmptySet_6= ruleIsEmptySet )
            {
            // InternalFml.g:3059:2: (this_SetCard_0= ruleSetCard | this_SetToNames_1= ruleSetToNames | this_SetBelongs_2= ruleSetBelongs | this_SetUnionOrIntersection_3= ruleSetUnionOrIntersection | this_SetEmpty_4= ruleSetEmpty | this_SetAddOrRemove_5= ruleSetAddOrRemove | this_IsEmptySet_6= ruleIsEmptySet )
            int alt35=7;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt35=1;
                }
                break;
            case 73:
                {
                alt35=2;
                }
                break;
            case 65:
                {
                alt35=3;
                }
                break;
            case 66:
            case 67:
            case 68:
                {
                alt35=4;
                }
                break;
            case 69:
                {
                alt35=5;
                }
                break;
            case 70:
            case 71:
                {
                alt35=6;
                }
                break;
            case 72:
                {
                alt35=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // InternalFml.g:3060:3: this_SetCard_0= ruleSetCard
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetCardParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetCard_0=ruleSetCard();

                    state._fsp--;


                    			current = this_SetCard_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:3069:3: this_SetToNames_1= ruleSetToNames
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetToNamesParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetToNames_1=ruleSetToNames();

                    state._fsp--;


                    			current = this_SetToNames_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:3078:3: this_SetBelongs_2= ruleSetBelongs
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetBelongsParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetBelongs_2=ruleSetBelongs();

                    state._fsp--;


                    			current = this_SetBelongs_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:3087:3: this_SetUnionOrIntersection_3= ruleSetUnionOrIntersection
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetUnionOrIntersectionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetUnionOrIntersection_3=ruleSetUnionOrIntersection();

                    state._fsp--;


                    			current = this_SetUnionOrIntersection_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:3096:3: this_SetEmpty_4= ruleSetEmpty
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetEmptyParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetEmpty_4=ruleSetEmpty();

                    state._fsp--;


                    			current = this_SetEmpty_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:3105:3: this_SetAddOrRemove_5= ruleSetAddOrRemove
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getSetAddOrRemoveParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetAddOrRemove_5=ruleSetAddOrRemove();

                    state._fsp--;


                    			current = this_SetAddOrRemove_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalFml.g:3114:3: this_IsEmptySet_6= ruleIsEmptySet
                    {

                    			newCompositeNode(grammarAccess.getSetOperationsAccess().getIsEmptySetParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_IsEmptySet_6=ruleIsEmptySet();

                    state._fsp--;


                    			current = this_IsEmptySet_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetOperations"


    // $ANTLR start "entryRuleSetCard"
    // InternalFml.g:3126:1: entryRuleSetCard returns [EObject current=null] : iv_ruleSetCard= ruleSetCard EOF ;
    public final EObject entryRuleSetCard() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetCard = null;


        try {
            // InternalFml.g:3126:48: (iv_ruleSetCard= ruleSetCard EOF )
            // InternalFml.g:3127:2: iv_ruleSetCard= ruleSetCard EOF
            {
             newCompositeNode(grammarAccess.getSetCardRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetCard=ruleSetCard();

            state._fsp--;

             current =iv_ruleSetCard; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetCard"


    // $ANTLR start "ruleSetCard"
    // InternalFml.g:3133:1: ruleSetCard returns [EObject current=null] : (otherlv_0= 'size' ( (lv_set_1_0= ruleSetCommand ) ) ) ;
    public final EObject ruleSetCard() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3139:2: ( (otherlv_0= 'size' ( (lv_set_1_0= ruleSetCommand ) ) ) )
            // InternalFml.g:3140:2: (otherlv_0= 'size' ( (lv_set_1_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:3140:2: (otherlv_0= 'size' ( (lv_set_1_0= ruleSetCommand ) ) )
            // InternalFml.g:3141:3: otherlv_0= 'size' ( (lv_set_1_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,64,FOLLOW_29); 

            			newLeafNode(otherlv_0, grammarAccess.getSetCardAccess().getSizeKeyword_0());
            		
            // InternalFml.g:3145:3: ( (lv_set_1_0= ruleSetCommand ) )
            // InternalFml.g:3146:4: (lv_set_1_0= ruleSetCommand )
            {
            // InternalFml.g:3146:4: (lv_set_1_0= ruleSetCommand )
            // InternalFml.g:3147:5: lv_set_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSetCardAccess().getSetSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_set_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetCardRule());
            					}
            					set(
            						current,
            						"set",
            						lv_set_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetCard"


    // $ANTLR start "entryRuleSetBelongs"
    // InternalFml.g:3168:1: entryRuleSetBelongs returns [EObject current=null] : iv_ruleSetBelongs= ruleSetBelongs EOF ;
    public final EObject entryRuleSetBelongs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetBelongs = null;


        try {
            // InternalFml.g:3168:51: (iv_ruleSetBelongs= ruleSetBelongs EOF )
            // InternalFml.g:3169:2: iv_ruleSetBelongs= ruleSetBelongs EOF
            {
             newCompositeNode(grammarAccess.getSetBelongsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetBelongs=ruleSetBelongs();

            state._fsp--;

             current =iv_ruleSetBelongs; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetBelongs"


    // $ANTLR start "ruleSetBelongs"
    // InternalFml.g:3175:1: ruleSetBelongs returns [EObject current=null] : (otherlv_0= 'setBelongs' ( (lv_setl_1_0= ruleFML_IDENTIFIER ) ) ( (lv_setr_2_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleSetBelongs() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_setl_1_0 = null;

        AntlrDatatypeRuleToken lv_setr_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3181:2: ( (otherlv_0= 'setBelongs' ( (lv_setl_1_0= ruleFML_IDENTIFIER ) ) ( (lv_setr_2_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:3182:2: (otherlv_0= 'setBelongs' ( (lv_setl_1_0= ruleFML_IDENTIFIER ) ) ( (lv_setr_2_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:3182:2: (otherlv_0= 'setBelongs' ( (lv_setl_1_0= ruleFML_IDENTIFIER ) ) ( (lv_setr_2_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:3183:3: otherlv_0= 'setBelongs' ( (lv_setl_1_0= ruleFML_IDENTIFIER ) ) ( (lv_setr_2_0= ruleFML_IDENTIFIER ) )
            {
            otherlv_0=(Token)match(input,65,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getSetBelongsAccess().getSetBelongsKeyword_0());
            		
            // InternalFml.g:3187:3: ( (lv_setl_1_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:3188:4: (lv_setl_1_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:3188:4: (lv_setl_1_0= ruleFML_IDENTIFIER )
            // InternalFml.g:3189:5: lv_setl_1_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getSetBelongsAccess().getSetlFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_25);
            lv_setl_1_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetBelongsRule());
            					}
            					set(
            						current,
            						"setl",
            						lv_setl_1_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:3206:3: ( (lv_setr_2_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:3207:4: (lv_setr_2_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:3207:4: (lv_setr_2_0= ruleFML_IDENTIFIER )
            // InternalFml.g:3208:5: lv_setr_2_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getSetBelongsAccess().getSetrFML_IDENTIFIERParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_setr_2_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetBelongsRule());
            					}
            					set(
            						current,
            						"setr",
            						lv_setr_2_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetBelongs"


    // $ANTLR start "entryRuleSetUnionOrIntersection"
    // InternalFml.g:3229:1: entryRuleSetUnionOrIntersection returns [EObject current=null] : iv_ruleSetUnionOrIntersection= ruleSetUnionOrIntersection EOF ;
    public final EObject entryRuleSetUnionOrIntersection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetUnionOrIntersection = null;


        try {
            // InternalFml.g:3229:63: (iv_ruleSetUnionOrIntersection= ruleSetUnionOrIntersection EOF )
            // InternalFml.g:3230:2: iv_ruleSetUnionOrIntersection= ruleSetUnionOrIntersection EOF
            {
             newCompositeNode(grammarAccess.getSetUnionOrIntersectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetUnionOrIntersection=ruleSetUnionOrIntersection();

            state._fsp--;

             current =iv_ruleSetUnionOrIntersection; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetUnionOrIntersection"


    // $ANTLR start "ruleSetUnionOrIntersection"
    // InternalFml.g:3236:1: ruleSetUnionOrIntersection returns [EObject current=null] : ( ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_setr_2_0= ruleSetCommand ) ) ) ;
    public final EObject ruleSetUnionOrIntersection() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        Token lv_op_0_3=null;
        EObject lv_setl_1_0 = null;

        EObject lv_setr_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3242:2: ( ( ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_setr_2_0= ruleSetCommand ) ) ) )
            // InternalFml.g:3243:2: ( ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_setr_2_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:3243:2: ( ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_setr_2_0= ruleSetCommand ) ) )
            // InternalFml.g:3244:3: ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_setr_2_0= ruleSetCommand ) )
            {
            // InternalFml.g:3244:3: ( ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) ) )
            // InternalFml.g:3245:4: ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) )
            {
            // InternalFml.g:3245:4: ( (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' ) )
            // InternalFml.g:3246:5: (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' )
            {
            // InternalFml.g:3246:5: (lv_op_0_1= 'setUnion' | lv_op_0_2= 'setIntersection' | lv_op_0_3= 'setDiff' )
            int alt36=3;
            switch ( input.LA(1) ) {
            case 66:
                {
                alt36=1;
                }
                break;
            case 67:
                {
                alt36=2;
                }
                break;
            case 68:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // InternalFml.g:3247:6: lv_op_0_1= 'setUnion'
                    {
                    lv_op_0_1=(Token)match(input,66,FOLLOW_29); 

                    						newLeafNode(lv_op_0_1, grammarAccess.getSetUnionOrIntersectionAccess().getOpSetUnionKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetUnionOrIntersectionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:3258:6: lv_op_0_2= 'setIntersection'
                    {
                    lv_op_0_2=(Token)match(input,67,FOLLOW_29); 

                    						newLeafNode(lv_op_0_2, grammarAccess.getSetUnionOrIntersectionAccess().getOpSetIntersectionKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetUnionOrIntersectionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalFml.g:3269:6: lv_op_0_3= 'setDiff'
                    {
                    lv_op_0_3=(Token)match(input,68,FOLLOW_29); 

                    						newLeafNode(lv_op_0_3, grammarAccess.getSetUnionOrIntersectionAccess().getOpSetDiffKeyword_0_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetUnionOrIntersectionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:3282:3: ( (lv_setl_1_0= ruleSetCommand ) )
            // InternalFml.g:3283:4: (lv_setl_1_0= ruleSetCommand )
            {
            // InternalFml.g:3283:4: (lv_setl_1_0= ruleSetCommand )
            // InternalFml.g:3284:5: lv_setl_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSetUnionOrIntersectionAccess().getSetlSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_29);
            lv_setl_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetUnionOrIntersectionRule());
            					}
            					set(
            						current,
            						"setl",
            						lv_setl_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:3301:3: ( (lv_setr_2_0= ruleSetCommand ) )
            // InternalFml.g:3302:4: (lv_setr_2_0= ruleSetCommand )
            {
            // InternalFml.g:3302:4: (lv_setr_2_0= ruleSetCommand )
            // InternalFml.g:3303:5: lv_setr_2_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSetUnionOrIntersectionAccess().getSetrSetCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_setr_2_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetUnionOrIntersectionRule());
            					}
            					set(
            						current,
            						"setr",
            						lv_setr_2_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetUnionOrIntersection"


    // $ANTLR start "entryRuleSetEmpty"
    // InternalFml.g:3324:1: entryRuleSetEmpty returns [EObject current=null] : iv_ruleSetEmpty= ruleSetEmpty EOF ;
    public final EObject entryRuleSetEmpty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetEmpty = null;


        try {
            // InternalFml.g:3324:49: (iv_ruleSetEmpty= ruleSetEmpty EOF )
            // InternalFml.g:3325:2: iv_ruleSetEmpty= ruleSetEmpty EOF
            {
             newCompositeNode(grammarAccess.getSetEmptyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetEmpty=ruleSetEmpty();

            state._fsp--;

             current =iv_ruleSetEmpty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetEmpty"


    // $ANTLR start "ruleSetEmpty"
    // InternalFml.g:3331:1: ruleSetEmpty returns [EObject current=null] : ( (lv_val_0_0= 'setEmpty' ) ) ;
    public final EObject ruleSetEmpty() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3337:2: ( ( (lv_val_0_0= 'setEmpty' ) ) )
            // InternalFml.g:3338:2: ( (lv_val_0_0= 'setEmpty' ) )
            {
            // InternalFml.g:3338:2: ( (lv_val_0_0= 'setEmpty' ) )
            // InternalFml.g:3339:3: (lv_val_0_0= 'setEmpty' )
            {
            // InternalFml.g:3339:3: (lv_val_0_0= 'setEmpty' )
            // InternalFml.g:3340:4: lv_val_0_0= 'setEmpty'
            {
            lv_val_0_0=(Token)match(input,69,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getSetEmptyAccess().getValSetEmptyKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getSetEmptyRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "setEmpty");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetEmpty"


    // $ANTLR start "entryRuleSetAddOrRemove"
    // InternalFml.g:3355:1: entryRuleSetAddOrRemove returns [EObject current=null] : iv_ruleSetAddOrRemove= ruleSetAddOrRemove EOF ;
    public final EObject entryRuleSetAddOrRemove() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetAddOrRemove = null;


        try {
            // InternalFml.g:3355:55: (iv_ruleSetAddOrRemove= ruleSetAddOrRemove EOF )
            // InternalFml.g:3356:2: iv_ruleSetAddOrRemove= ruleSetAddOrRemove EOF
            {
             newCompositeNode(grammarAccess.getSetAddOrRemoveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetAddOrRemove=ruleSetAddOrRemove();

            state._fsp--;

             current =iv_ruleSetAddOrRemove; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetAddOrRemove"


    // $ANTLR start "ruleSetAddOrRemove"
    // InternalFml.g:3362:1: ruleSetAddOrRemove returns [EObject current=null] : ( ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_var_2_0= ruleCommand ) ) ) ;
    public final EObject ruleSetAddOrRemove() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        EObject lv_setl_1_0 = null;

        EObject lv_var_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3368:2: ( ( ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_var_2_0= ruleCommand ) ) ) )
            // InternalFml.g:3369:2: ( ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_var_2_0= ruleCommand ) ) )
            {
            // InternalFml.g:3369:2: ( ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_var_2_0= ruleCommand ) ) )
            // InternalFml.g:3370:3: ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) ) ( (lv_setl_1_0= ruleSetCommand ) ) ( (lv_var_2_0= ruleCommand ) )
            {
            // InternalFml.g:3370:3: ( ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) ) )
            // InternalFml.g:3371:4: ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) )
            {
            // InternalFml.g:3371:4: ( (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' ) )
            // InternalFml.g:3372:5: (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' )
            {
            // InternalFml.g:3372:5: (lv_op_0_1= 'setAdd' | lv_op_0_2= 'setRemove' )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==70) ) {
                alt37=1;
            }
            else if ( (LA37_0==71) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalFml.g:3373:6: lv_op_0_1= 'setAdd'
                    {
                    lv_op_0_1=(Token)match(input,70,FOLLOW_29); 

                    						newLeafNode(lv_op_0_1, grammarAccess.getSetAddOrRemoveAccess().getOpSetAddKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetAddOrRemoveRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:3384:6: lv_op_0_2= 'setRemove'
                    {
                    lv_op_0_2=(Token)match(input,71,FOLLOW_29); 

                    						newLeafNode(lv_op_0_2, grammarAccess.getSetAddOrRemoveAccess().getOpSetRemoveKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetAddOrRemoveRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_2, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:3397:3: ( (lv_setl_1_0= ruleSetCommand ) )
            // InternalFml.g:3398:4: (lv_setl_1_0= ruleSetCommand )
            {
            // InternalFml.g:3398:4: (lv_setl_1_0= ruleSetCommand )
            // InternalFml.g:3399:5: lv_setl_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSetAddOrRemoveAccess().getSetlSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_29);
            lv_setl_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetAddOrRemoveRule());
            					}
            					set(
            						current,
            						"setl",
            						lv_setl_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:3416:3: ( (lv_var_2_0= ruleCommand ) )
            // InternalFml.g:3417:4: (lv_var_2_0= ruleCommand )
            {
            // InternalFml.g:3417:4: (lv_var_2_0= ruleCommand )
            // InternalFml.g:3418:5: lv_var_2_0= ruleCommand
            {

            					newCompositeNode(grammarAccess.getSetAddOrRemoveAccess().getVarCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_var_2_0=ruleCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetAddOrRemoveRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_2_0,
            						"org.xtext.example.mydsl.Fml.Command");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetAddOrRemove"


    // $ANTLR start "entryRuleIsEmptySet"
    // InternalFml.g:3439:1: entryRuleIsEmptySet returns [EObject current=null] : iv_ruleIsEmptySet= ruleIsEmptySet EOF ;
    public final EObject entryRuleIsEmptySet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIsEmptySet = null;


        try {
            // InternalFml.g:3439:51: (iv_ruleIsEmptySet= ruleIsEmptySet EOF )
            // InternalFml.g:3440:2: iv_ruleIsEmptySet= ruleIsEmptySet EOF
            {
             newCompositeNode(grammarAccess.getIsEmptySetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIsEmptySet=ruleIsEmptySet();

            state._fsp--;

             current =iv_ruleIsEmptySet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIsEmptySet"


    // $ANTLR start "ruleIsEmptySet"
    // InternalFml.g:3446:1: ruleIsEmptySet returns [EObject current=null] : (otherlv_0= 'setIsEmpty' ( (lv_set_1_0= ruleSetCommand ) ) ) ;
    public final EObject ruleIsEmptySet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3452:2: ( (otherlv_0= 'setIsEmpty' ( (lv_set_1_0= ruleSetCommand ) ) ) )
            // InternalFml.g:3453:2: (otherlv_0= 'setIsEmpty' ( (lv_set_1_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:3453:2: (otherlv_0= 'setIsEmpty' ( (lv_set_1_0= ruleSetCommand ) ) )
            // InternalFml.g:3454:3: otherlv_0= 'setIsEmpty' ( (lv_set_1_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,72,FOLLOW_29); 

            			newLeafNode(otherlv_0, grammarAccess.getIsEmptySetAccess().getSetIsEmptyKeyword_0());
            		
            // InternalFml.g:3458:3: ( (lv_set_1_0= ruleSetCommand ) )
            // InternalFml.g:3459:4: (lv_set_1_0= ruleSetCommand )
            {
            // InternalFml.g:3459:4: (lv_set_1_0= ruleSetCommand )
            // InternalFml.g:3460:5: lv_set_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getIsEmptySetAccess().getSetSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_set_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIsEmptySetRule());
            					}
            					set(
            						current,
            						"set",
            						lv_set_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIsEmptySet"


    // $ANTLR start "entryRuleSetToNames"
    // InternalFml.g:3481:1: entryRuleSetToNames returns [EObject current=null] : iv_ruleSetToNames= ruleSetToNames EOF ;
    public final EObject entryRuleSetToNames() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetToNames = null;


        try {
            // InternalFml.g:3481:51: (iv_ruleSetToNames= ruleSetToNames EOF )
            // InternalFml.g:3482:2: iv_ruleSetToNames= ruleSetToNames EOF
            {
             newCompositeNode(grammarAccess.getSetToNamesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetToNames=ruleSetToNames();

            state._fsp--;

             current =iv_ruleSetToNames; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetToNames"


    // $ANTLR start "ruleSetToNames"
    // InternalFml.g:3488:1: ruleSetToNames returns [EObject current=null] : (otherlv_0= 'names' ( (lv_set_1_0= ruleSetCommand ) ) ) ;
    public final EObject ruleSetToNames() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3494:2: ( (otherlv_0= 'names' ( (lv_set_1_0= ruleSetCommand ) ) ) )
            // InternalFml.g:3495:2: (otherlv_0= 'names' ( (lv_set_1_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:3495:2: (otherlv_0= 'names' ( (lv_set_1_0= ruleSetCommand ) ) )
            // InternalFml.g:3496:3: otherlv_0= 'names' ( (lv_set_1_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,73,FOLLOW_29); 

            			newLeafNode(otherlv_0, grammarAccess.getSetToNamesAccess().getNamesKeyword_0());
            		
            // InternalFml.g:3500:3: ( (lv_set_1_0= ruleSetCommand ) )
            // InternalFml.g:3501:4: (lv_set_1_0= ruleSetCommand )
            {
            // InternalFml.g:3501:4: (lv_set_1_0= ruleSetCommand )
            // InternalFml.g:3502:5: lv_set_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSetToNamesAccess().getSetSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_set_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetToNamesRule());
            					}
            					set(
            						current,
            						"set",
            						lv_set_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetToNames"


    // $ANTLR start "entryRuleFeatureOperation"
    // InternalFml.g:3523:1: entryRuleFeatureOperation returns [EObject current=null] : iv_ruleFeatureOperation= ruleFeatureOperation EOF ;
    public final EObject entryRuleFeatureOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureOperation = null;


        try {
            // InternalFml.g:3523:57: (iv_ruleFeatureOperation= ruleFeatureOperation EOF )
            // InternalFml.g:3524:2: iv_ruleFeatureOperation= ruleFeatureOperation EOF
            {
             newCompositeNode(grammarAccess.getFeatureOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureOperation=ruleFeatureOperation();

            state._fsp--;

             current =iv_ruleFeatureOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureOperation"


    // $ANTLR start "ruleFeatureOperation"
    // InternalFml.g:3530:1: ruleFeatureOperation returns [EObject current=null] : ( ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) ) ( (lv_feature_1_0= ruleFTCommand ) ) ) ;
    public final EObject ruleFeatureOperation() throws RecognitionException {
        EObject current = null;

        EObject lv_op_0_1 = null;

        EObject lv_op_0_2 = null;

        EObject lv_op_0_3 = null;

        EObject lv_op_0_4 = null;

        EObject lv_op_0_5 = null;

        EObject lv_op_0_6 = null;

        EObject lv_op_0_7 = null;

        EObject lv_op_0_8 = null;

        EObject lv_feature_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:3536:2: ( ( ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) ) ( (lv_feature_1_0= ruleFTCommand ) ) ) )
            // InternalFml.g:3537:2: ( ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) ) ( (lv_feature_1_0= ruleFTCommand ) ) )
            {
            // InternalFml.g:3537:2: ( ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) ) ( (lv_feature_1_0= ruleFTCommand ) ) )
            // InternalFml.g:3538:3: ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) ) ( (lv_feature_1_0= ruleFTCommand ) )
            {
            // InternalFml.g:3538:3: ( ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) ) )
            // InternalFml.g:3539:4: ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) )
            {
            // InternalFml.g:3539:4: ( (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator ) )
            // InternalFml.g:3540:5: (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator )
            {
            // InternalFml.g:3540:5: (lv_op_0_1= ruleAncestorFeature | lv_op_0_2= ruleDescendantFeature | lv_op_0_3= ruleChildrenFeature | lv_op_0_4= ruleSiblingFeature | lv_op_0_5= ruleParentFeature | lv_op_0_6= ruleNameFeature | lv_op_0_7= ruleFMFeature | lv_op_0_8= ruleFeatureOperator )
            int alt38=8;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt38=1;
                }
                break;
            case 75:
                {
                alt38=2;
                }
                break;
            case 76:
                {
                alt38=3;
                }
                break;
            case 77:
                {
                alt38=4;
                }
                break;
            case 78:
                {
                alt38=5;
                }
                break;
            case 79:
                {
                alt38=6;
                }
                break;
            case 80:
                {
                alt38=7;
                }
                break;
            case 81:
                {
                alt38=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // InternalFml.g:3541:6: lv_op_0_1= ruleAncestorFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpAncestorFeatureParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_1=ruleAncestorFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_1,
                    							"org.xtext.example.mydsl.Fml.AncestorFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:3557:6: lv_op_0_2= ruleDescendantFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpDescendantFeatureParserRuleCall_0_0_1());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_2=ruleDescendantFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_2,
                    							"org.xtext.example.mydsl.Fml.DescendantFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 3 :
                    // InternalFml.g:3573:6: lv_op_0_3= ruleChildrenFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpChildrenFeatureParserRuleCall_0_0_2());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_3=ruleChildrenFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_3,
                    							"org.xtext.example.mydsl.Fml.ChildrenFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 4 :
                    // InternalFml.g:3589:6: lv_op_0_4= ruleSiblingFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpSiblingFeatureParserRuleCall_0_0_3());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_4=ruleSiblingFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_4,
                    							"org.xtext.example.mydsl.Fml.SiblingFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 5 :
                    // InternalFml.g:3605:6: lv_op_0_5= ruleParentFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpParentFeatureParserRuleCall_0_0_4());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_5=ruleParentFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_5,
                    							"org.xtext.example.mydsl.Fml.ParentFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 6 :
                    // InternalFml.g:3621:6: lv_op_0_6= ruleNameFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpNameFeatureParserRuleCall_0_0_5());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_6=ruleNameFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_6,
                    							"org.xtext.example.mydsl.Fml.NameFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 7 :
                    // InternalFml.g:3637:6: lv_op_0_7= ruleFMFeature
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpFMFeatureParserRuleCall_0_0_6());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_7=ruleFMFeature();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_7,
                    							"org.xtext.example.mydsl.Fml.FMFeature");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 8 :
                    // InternalFml.g:3653:6: lv_op_0_8= ruleFeatureOperator
                    {

                    						newCompositeNode(grammarAccess.getFeatureOperationAccess().getOpFeatureOperatorParserRuleCall_0_0_7());
                    					
                    pushFollow(FOLLOW_33);
                    lv_op_0_8=ruleFeatureOperator();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
                    						}
                    						set(
                    							current,
                    							"op",
                    							lv_op_0_8,
                    							"org.xtext.example.mydsl.Fml.FeatureOperator");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:3671:3: ( (lv_feature_1_0= ruleFTCommand ) )
            // InternalFml.g:3672:4: (lv_feature_1_0= ruleFTCommand )
            {
            // InternalFml.g:3672:4: (lv_feature_1_0= ruleFTCommand )
            // InternalFml.g:3673:5: lv_feature_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getFeatureOperationAccess().getFeatureFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_feature_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFeatureOperationRule());
            					}
            					set(
            						current,
            						"feature",
            						lv_feature_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureOperation"


    // $ANTLR start "entryRuleAncestorFeature"
    // InternalFml.g:3694:1: entryRuleAncestorFeature returns [EObject current=null] : iv_ruleAncestorFeature= ruleAncestorFeature EOF ;
    public final EObject entryRuleAncestorFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAncestorFeature = null;


        try {
            // InternalFml.g:3694:56: (iv_ruleAncestorFeature= ruleAncestorFeature EOF )
            // InternalFml.g:3695:2: iv_ruleAncestorFeature= ruleAncestorFeature EOF
            {
             newCompositeNode(grammarAccess.getAncestorFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAncestorFeature=ruleAncestorFeature();

            state._fsp--;

             current =iv_ruleAncestorFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAncestorFeature"


    // $ANTLR start "ruleAncestorFeature"
    // InternalFml.g:3701:1: ruleAncestorFeature returns [EObject current=null] : ( (lv_val_0_0= 'ancestors' ) ) ;
    public final EObject ruleAncestorFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3707:2: ( ( (lv_val_0_0= 'ancestors' ) ) )
            // InternalFml.g:3708:2: ( (lv_val_0_0= 'ancestors' ) )
            {
            // InternalFml.g:3708:2: ( (lv_val_0_0= 'ancestors' ) )
            // InternalFml.g:3709:3: (lv_val_0_0= 'ancestors' )
            {
            // InternalFml.g:3709:3: (lv_val_0_0= 'ancestors' )
            // InternalFml.g:3710:4: lv_val_0_0= 'ancestors'
            {
            lv_val_0_0=(Token)match(input,74,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getAncestorFeatureAccess().getValAncestorsKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getAncestorFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "ancestors");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAncestorFeature"


    // $ANTLR start "entryRuleDescendantFeature"
    // InternalFml.g:3725:1: entryRuleDescendantFeature returns [EObject current=null] : iv_ruleDescendantFeature= ruleDescendantFeature EOF ;
    public final EObject entryRuleDescendantFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDescendantFeature = null;


        try {
            // InternalFml.g:3725:58: (iv_ruleDescendantFeature= ruleDescendantFeature EOF )
            // InternalFml.g:3726:2: iv_ruleDescendantFeature= ruleDescendantFeature EOF
            {
             newCompositeNode(grammarAccess.getDescendantFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDescendantFeature=ruleDescendantFeature();

            state._fsp--;

             current =iv_ruleDescendantFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDescendantFeature"


    // $ANTLR start "ruleDescendantFeature"
    // InternalFml.g:3732:1: ruleDescendantFeature returns [EObject current=null] : ( (lv_val_0_0= 'descendants' ) ) ;
    public final EObject ruleDescendantFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3738:2: ( ( (lv_val_0_0= 'descendants' ) ) )
            // InternalFml.g:3739:2: ( (lv_val_0_0= 'descendants' ) )
            {
            // InternalFml.g:3739:2: ( (lv_val_0_0= 'descendants' ) )
            // InternalFml.g:3740:3: (lv_val_0_0= 'descendants' )
            {
            // InternalFml.g:3740:3: (lv_val_0_0= 'descendants' )
            // InternalFml.g:3741:4: lv_val_0_0= 'descendants'
            {
            lv_val_0_0=(Token)match(input,75,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getDescendantFeatureAccess().getValDescendantsKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getDescendantFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "descendants");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDescendantFeature"


    // $ANTLR start "entryRuleChildrenFeature"
    // InternalFml.g:3756:1: entryRuleChildrenFeature returns [EObject current=null] : iv_ruleChildrenFeature= ruleChildrenFeature EOF ;
    public final EObject entryRuleChildrenFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChildrenFeature = null;


        try {
            // InternalFml.g:3756:56: (iv_ruleChildrenFeature= ruleChildrenFeature EOF )
            // InternalFml.g:3757:2: iv_ruleChildrenFeature= ruleChildrenFeature EOF
            {
             newCompositeNode(grammarAccess.getChildrenFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChildrenFeature=ruleChildrenFeature();

            state._fsp--;

             current =iv_ruleChildrenFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChildrenFeature"


    // $ANTLR start "ruleChildrenFeature"
    // InternalFml.g:3763:1: ruleChildrenFeature returns [EObject current=null] : ( (lv_val_0_0= 'children' ) ) ;
    public final EObject ruleChildrenFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3769:2: ( ( (lv_val_0_0= 'children' ) ) )
            // InternalFml.g:3770:2: ( (lv_val_0_0= 'children' ) )
            {
            // InternalFml.g:3770:2: ( (lv_val_0_0= 'children' ) )
            // InternalFml.g:3771:3: (lv_val_0_0= 'children' )
            {
            // InternalFml.g:3771:3: (lv_val_0_0= 'children' )
            // InternalFml.g:3772:4: lv_val_0_0= 'children'
            {
            lv_val_0_0=(Token)match(input,76,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getChildrenFeatureAccess().getValChildrenKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getChildrenFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "children");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChildrenFeature"


    // $ANTLR start "entryRuleSiblingFeature"
    // InternalFml.g:3787:1: entryRuleSiblingFeature returns [EObject current=null] : iv_ruleSiblingFeature= ruleSiblingFeature EOF ;
    public final EObject entryRuleSiblingFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSiblingFeature = null;


        try {
            // InternalFml.g:3787:55: (iv_ruleSiblingFeature= ruleSiblingFeature EOF )
            // InternalFml.g:3788:2: iv_ruleSiblingFeature= ruleSiblingFeature EOF
            {
             newCompositeNode(grammarAccess.getSiblingFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSiblingFeature=ruleSiblingFeature();

            state._fsp--;

             current =iv_ruleSiblingFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSiblingFeature"


    // $ANTLR start "ruleSiblingFeature"
    // InternalFml.g:3794:1: ruleSiblingFeature returns [EObject current=null] : ( (lv_val_0_0= 'sibling' ) ) ;
    public final EObject ruleSiblingFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3800:2: ( ( (lv_val_0_0= 'sibling' ) ) )
            // InternalFml.g:3801:2: ( (lv_val_0_0= 'sibling' ) )
            {
            // InternalFml.g:3801:2: ( (lv_val_0_0= 'sibling' ) )
            // InternalFml.g:3802:3: (lv_val_0_0= 'sibling' )
            {
            // InternalFml.g:3802:3: (lv_val_0_0= 'sibling' )
            // InternalFml.g:3803:4: lv_val_0_0= 'sibling'
            {
            lv_val_0_0=(Token)match(input,77,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getSiblingFeatureAccess().getValSiblingKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getSiblingFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "sibling");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSiblingFeature"


    // $ANTLR start "entryRuleParentFeature"
    // InternalFml.g:3818:1: entryRuleParentFeature returns [EObject current=null] : iv_ruleParentFeature= ruleParentFeature EOF ;
    public final EObject entryRuleParentFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParentFeature = null;


        try {
            // InternalFml.g:3818:54: (iv_ruleParentFeature= ruleParentFeature EOF )
            // InternalFml.g:3819:2: iv_ruleParentFeature= ruleParentFeature EOF
            {
             newCompositeNode(grammarAccess.getParentFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParentFeature=ruleParentFeature();

            state._fsp--;

             current =iv_ruleParentFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParentFeature"


    // $ANTLR start "ruleParentFeature"
    // InternalFml.g:3825:1: ruleParentFeature returns [EObject current=null] : ( (lv_val_0_0= 'parent' ) ) ;
    public final EObject ruleParentFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3831:2: ( ( (lv_val_0_0= 'parent' ) ) )
            // InternalFml.g:3832:2: ( (lv_val_0_0= 'parent' ) )
            {
            // InternalFml.g:3832:2: ( (lv_val_0_0= 'parent' ) )
            // InternalFml.g:3833:3: (lv_val_0_0= 'parent' )
            {
            // InternalFml.g:3833:3: (lv_val_0_0= 'parent' )
            // InternalFml.g:3834:4: lv_val_0_0= 'parent'
            {
            lv_val_0_0=(Token)match(input,78,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getParentFeatureAccess().getValParentKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getParentFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "parent");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParentFeature"


    // $ANTLR start "entryRuleNameFeature"
    // InternalFml.g:3849:1: entryRuleNameFeature returns [EObject current=null] : iv_ruleNameFeature= ruleNameFeature EOF ;
    public final EObject entryRuleNameFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameFeature = null;


        try {
            // InternalFml.g:3849:52: (iv_ruleNameFeature= ruleNameFeature EOF )
            // InternalFml.g:3850:2: iv_ruleNameFeature= ruleNameFeature EOF
            {
             newCompositeNode(grammarAccess.getNameFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNameFeature=ruleNameFeature();

            state._fsp--;

             current =iv_ruleNameFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNameFeature"


    // $ANTLR start "ruleNameFeature"
    // InternalFml.g:3856:1: ruleNameFeature returns [EObject current=null] : ( (lv_val_0_0= 'name' ) ) ;
    public final EObject ruleNameFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3862:2: ( ( (lv_val_0_0= 'name' ) ) )
            // InternalFml.g:3863:2: ( (lv_val_0_0= 'name' ) )
            {
            // InternalFml.g:3863:2: ( (lv_val_0_0= 'name' ) )
            // InternalFml.g:3864:3: (lv_val_0_0= 'name' )
            {
            // InternalFml.g:3864:3: (lv_val_0_0= 'name' )
            // InternalFml.g:3865:4: lv_val_0_0= 'name'
            {
            lv_val_0_0=(Token)match(input,79,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getNameFeatureAccess().getValNameKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getNameFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "name");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNameFeature"


    // $ANTLR start "entryRuleFMFeature"
    // InternalFml.g:3880:1: entryRuleFMFeature returns [EObject current=null] : iv_ruleFMFeature= ruleFMFeature EOF ;
    public final EObject entryRuleFMFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMFeature = null;


        try {
            // InternalFml.g:3880:50: (iv_ruleFMFeature= ruleFMFeature EOF )
            // InternalFml.g:3881:2: iv_ruleFMFeature= ruleFMFeature EOF
            {
             newCompositeNode(grammarAccess.getFMFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMFeature=ruleFMFeature();

            state._fsp--;

             current =iv_ruleFMFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMFeature"


    // $ANTLR start "ruleFMFeature"
    // InternalFml.g:3887:1: ruleFMFeature returns [EObject current=null] : ( (lv_val_0_0= 'whichfm' ) ) ;
    public final EObject ruleFMFeature() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3893:2: ( ( (lv_val_0_0= 'whichfm' ) ) )
            // InternalFml.g:3894:2: ( (lv_val_0_0= 'whichfm' ) )
            {
            // InternalFml.g:3894:2: ( (lv_val_0_0= 'whichfm' ) )
            // InternalFml.g:3895:3: (lv_val_0_0= 'whichfm' )
            {
            // InternalFml.g:3895:3: (lv_val_0_0= 'whichfm' )
            // InternalFml.g:3896:4: lv_val_0_0= 'whichfm'
            {
            lv_val_0_0=(Token)match(input,80,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getFMFeatureAccess().getValWhichfmKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getFMFeatureRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "whichfm");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMFeature"


    // $ANTLR start "entryRuleFeatureOperator"
    // InternalFml.g:3911:1: entryRuleFeatureOperator returns [EObject current=null] : iv_ruleFeatureOperator= ruleFeatureOperator EOF ;
    public final EObject entryRuleFeatureOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureOperator = null;


        try {
            // InternalFml.g:3911:56: (iv_ruleFeatureOperator= ruleFeatureOperator EOF )
            // InternalFml.g:3912:2: iv_ruleFeatureOperator= ruleFeatureOperator EOF
            {
             newCompositeNode(grammarAccess.getFeatureOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureOperator=ruleFeatureOperator();

            state._fsp--;

             current =iv_ruleFeatureOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureOperator"


    // $ANTLR start "ruleFeatureOperator"
    // InternalFml.g:3918:1: ruleFeatureOperator returns [EObject current=null] : ( (lv_val_0_0= 'operator' ) ) ;
    public final EObject ruleFeatureOperator() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:3924:2: ( ( (lv_val_0_0= 'operator' ) ) )
            // InternalFml.g:3925:2: ( (lv_val_0_0= 'operator' ) )
            {
            // InternalFml.g:3925:2: ( (lv_val_0_0= 'operator' ) )
            // InternalFml.g:3926:3: (lv_val_0_0= 'operator' )
            {
            // InternalFml.g:3926:3: (lv_val_0_0= 'operator' )
            // InternalFml.g:3927:4: lv_val_0_0= 'operator'
            {
            lv_val_0_0=(Token)match(input,81,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getFeatureOperatorAccess().getValOperatorKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getFeatureOperatorRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "operator");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureOperator"


    // $ANTLR start "entryRuleStringOperation"
    // InternalFml.g:3942:1: entryRuleStringOperation returns [EObject current=null] : iv_ruleStringOperation= ruleStringOperation EOF ;
    public final EObject entryRuleStringOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringOperation = null;


        try {
            // InternalFml.g:3942:56: (iv_ruleStringOperation= ruleStringOperation EOF )
            // InternalFml.g:3943:2: iv_ruleStringOperation= ruleStringOperation EOF
            {
             newCompositeNode(grammarAccess.getStringOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringOperation=ruleStringOperation();

            state._fsp--;

             current =iv_ruleStringOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringOperation"


    // $ANTLR start "ruleStringOperation"
    // InternalFml.g:3949:1: ruleStringOperation returns [EObject current=null] : (this_StringInit_0= ruleStringInit | this_StringConcat_1= ruleStringConcat | this_StringSubstring_2= ruleStringSubstring | this_StringIndexOf_3= ruleStringIndexOf | this_StringLength_4= ruleStringLength ) ;
    public final EObject ruleStringOperation() throws RecognitionException {
        EObject current = null;

        EObject this_StringInit_0 = null;

        EObject this_StringConcat_1 = null;

        EObject this_StringSubstring_2 = null;

        EObject this_StringIndexOf_3 = null;

        EObject this_StringLength_4 = null;



        	enterRule();

        try {
            // InternalFml.g:3955:2: ( (this_StringInit_0= ruleStringInit | this_StringConcat_1= ruleStringConcat | this_StringSubstring_2= ruleStringSubstring | this_StringIndexOf_3= ruleStringIndexOf | this_StringLength_4= ruleStringLength ) )
            // InternalFml.g:3956:2: (this_StringInit_0= ruleStringInit | this_StringConcat_1= ruleStringConcat | this_StringSubstring_2= ruleStringSubstring | this_StringIndexOf_3= ruleStringIndexOf | this_StringLength_4= ruleStringLength )
            {
            // InternalFml.g:3956:2: (this_StringInit_0= ruleStringInit | this_StringConcat_1= ruleStringConcat | this_StringSubstring_2= ruleStringSubstring | this_StringIndexOf_3= ruleStringIndexOf | this_StringLength_4= ruleStringLength )
            int alt39=5;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt39=1;
                }
                break;
            case 83:
                {
                alt39=2;
                }
                break;
            case 84:
                {
                alt39=3;
                }
                break;
            case 85:
                {
                alt39=4;
                }
                break;
            case 86:
                {
                alt39=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // InternalFml.g:3957:3: this_StringInit_0= ruleStringInit
                    {

                    			newCompositeNode(grammarAccess.getStringOperationAccess().getStringInitParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringInit_0=ruleStringInit();

                    state._fsp--;


                    			current = this_StringInit_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:3966:3: this_StringConcat_1= ruleStringConcat
                    {

                    			newCompositeNode(grammarAccess.getStringOperationAccess().getStringConcatParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringConcat_1=ruleStringConcat();

                    state._fsp--;


                    			current = this_StringConcat_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:3975:3: this_StringSubstring_2= ruleStringSubstring
                    {

                    			newCompositeNode(grammarAccess.getStringOperationAccess().getStringSubstringParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringSubstring_2=ruleStringSubstring();

                    state._fsp--;


                    			current = this_StringSubstring_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:3984:3: this_StringIndexOf_3= ruleStringIndexOf
                    {

                    			newCompositeNode(grammarAccess.getStringOperationAccess().getStringIndexOfParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringIndexOf_3=ruleStringIndexOf();

                    state._fsp--;


                    			current = this_StringIndexOf_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:3993:3: this_StringLength_4= ruleStringLength
                    {

                    			newCompositeNode(grammarAccess.getStringOperationAccess().getStringLengthParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringLength_4=ruleStringLength();

                    state._fsp--;


                    			current = this_StringLength_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringOperation"


    // $ANTLR start "entryRuleStringInit"
    // InternalFml.g:4005:1: entryRuleStringInit returns [EObject current=null] : iv_ruleStringInit= ruleStringInit EOF ;
    public final EObject entryRuleStringInit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringInit = null;


        try {
            // InternalFml.g:4005:51: (iv_ruleStringInit= ruleStringInit EOF )
            // InternalFml.g:4006:2: iv_ruleStringInit= ruleStringInit EOF
            {
             newCompositeNode(grammarAccess.getStringInitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringInit=ruleStringInit();

            state._fsp--;

             current =iv_ruleStringInit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringInit"


    // $ANTLR start "ruleStringInit"
    // InternalFml.g:4012:1: ruleStringInit returns [EObject current=null] : ( (lv_val_0_0= 'strInit' ) ) ;
    public final EObject ruleStringInit() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalFml.g:4018:2: ( ( (lv_val_0_0= 'strInit' ) ) )
            // InternalFml.g:4019:2: ( (lv_val_0_0= 'strInit' ) )
            {
            // InternalFml.g:4019:2: ( (lv_val_0_0= 'strInit' ) )
            // InternalFml.g:4020:3: (lv_val_0_0= 'strInit' )
            {
            // InternalFml.g:4020:3: (lv_val_0_0= 'strInit' )
            // InternalFml.g:4021:4: lv_val_0_0= 'strInit'
            {
            lv_val_0_0=(Token)match(input,82,FOLLOW_2); 

            				newLeafNode(lv_val_0_0, grammarAccess.getStringInitAccess().getValStrInitKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getStringInitRule());
            				}
            				setWithLastConsumed(current, "val", lv_val_0_0, "strInit");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringInit"


    // $ANTLR start "entryRuleStringConcat"
    // InternalFml.g:4036:1: entryRuleStringConcat returns [EObject current=null] : iv_ruleStringConcat= ruleStringConcat EOF ;
    public final EObject entryRuleStringConcat() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringConcat = null;


        try {
            // InternalFml.g:4036:53: (iv_ruleStringConcat= ruleStringConcat EOF )
            // InternalFml.g:4037:2: iv_ruleStringConcat= ruleStringConcat EOF
            {
             newCompositeNode(grammarAccess.getStringConcatRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringConcat=ruleStringConcat();

            state._fsp--;

             current =iv_ruleStringConcat; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringConcat"


    // $ANTLR start "ruleStringConcat"
    // InternalFml.g:4043:1: ruleStringConcat returns [EObject current=null] : (otherlv_0= 'strConcat' ( (lv_lstr_1_0= ruleStrCommand ) ) ( (lv_rstr_2_0= ruleStrCommand ) ) ) ;
    public final EObject ruleStringConcat() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_lstr_1_0 = null;

        EObject lv_rstr_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4049:2: ( (otherlv_0= 'strConcat' ( (lv_lstr_1_0= ruleStrCommand ) ) ( (lv_rstr_2_0= ruleStrCommand ) ) ) )
            // InternalFml.g:4050:2: (otherlv_0= 'strConcat' ( (lv_lstr_1_0= ruleStrCommand ) ) ( (lv_rstr_2_0= ruleStrCommand ) ) )
            {
            // InternalFml.g:4050:2: (otherlv_0= 'strConcat' ( (lv_lstr_1_0= ruleStrCommand ) ) ( (lv_rstr_2_0= ruleStrCommand ) ) )
            // InternalFml.g:4051:3: otherlv_0= 'strConcat' ( (lv_lstr_1_0= ruleStrCommand ) ) ( (lv_rstr_2_0= ruleStrCommand ) )
            {
            otherlv_0=(Token)match(input,83,FOLLOW_34); 

            			newLeafNode(otherlv_0, grammarAccess.getStringConcatAccess().getStrConcatKeyword_0());
            		
            // InternalFml.g:4055:3: ( (lv_lstr_1_0= ruleStrCommand ) )
            // InternalFml.g:4056:4: (lv_lstr_1_0= ruleStrCommand )
            {
            // InternalFml.g:4056:4: (lv_lstr_1_0= ruleStrCommand )
            // InternalFml.g:4057:5: lv_lstr_1_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringConcatAccess().getLstrStrCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_34);
            lv_lstr_1_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringConcatRule());
            					}
            					set(
            						current,
            						"lstr",
            						lv_lstr_1_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4074:3: ( (lv_rstr_2_0= ruleStrCommand ) )
            // InternalFml.g:4075:4: (lv_rstr_2_0= ruleStrCommand )
            {
            // InternalFml.g:4075:4: (lv_rstr_2_0= ruleStrCommand )
            // InternalFml.g:4076:5: lv_rstr_2_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringConcatAccess().getRstrStrCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_rstr_2_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringConcatRule());
            					}
            					set(
            						current,
            						"rstr",
            						lv_rstr_2_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringConcat"


    // $ANTLR start "entryRuleStringSubstring"
    // InternalFml.g:4097:1: entryRuleStringSubstring returns [EObject current=null] : iv_ruleStringSubstring= ruleStringSubstring EOF ;
    public final EObject entryRuleStringSubstring() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringSubstring = null;


        try {
            // InternalFml.g:4097:56: (iv_ruleStringSubstring= ruleStringSubstring EOF )
            // InternalFml.g:4098:2: iv_ruleStringSubstring= ruleStringSubstring EOF
            {
             newCompositeNode(grammarAccess.getStringSubstringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringSubstring=ruleStringSubstring();

            state._fsp--;

             current =iv_ruleStringSubstring; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringSubstring"


    // $ANTLR start "ruleStringSubstring"
    // InternalFml.g:4104:1: ruleStringSubstring returns [EObject current=null] : (otherlv_0= 'strSubstring' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_begin_2_0= ruleIntegerCommand ) ) ( (lv_end_3_0= ruleIntegerCommand ) ) ) ;
    public final EObject ruleStringSubstring() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_str_1_0 = null;

        EObject lv_begin_2_0 = null;

        EObject lv_end_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4110:2: ( (otherlv_0= 'strSubstring' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_begin_2_0= ruleIntegerCommand ) ) ( (lv_end_3_0= ruleIntegerCommand ) ) ) )
            // InternalFml.g:4111:2: (otherlv_0= 'strSubstring' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_begin_2_0= ruleIntegerCommand ) ) ( (lv_end_3_0= ruleIntegerCommand ) ) )
            {
            // InternalFml.g:4111:2: (otherlv_0= 'strSubstring' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_begin_2_0= ruleIntegerCommand ) ) ( (lv_end_3_0= ruleIntegerCommand ) ) )
            // InternalFml.g:4112:3: otherlv_0= 'strSubstring' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_begin_2_0= ruleIntegerCommand ) ) ( (lv_end_3_0= ruleIntegerCommand ) )
            {
            otherlv_0=(Token)match(input,84,FOLLOW_34); 

            			newLeafNode(otherlv_0, grammarAccess.getStringSubstringAccess().getStrSubstringKeyword_0());
            		
            // InternalFml.g:4116:3: ( (lv_str_1_0= ruleStrCommand ) )
            // InternalFml.g:4117:4: (lv_str_1_0= ruleStrCommand )
            {
            // InternalFml.g:4117:4: (lv_str_1_0= ruleStrCommand )
            // InternalFml.g:4118:5: lv_str_1_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringSubstringAccess().getStrStrCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_13);
            lv_str_1_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringSubstringRule());
            					}
            					set(
            						current,
            						"str",
            						lv_str_1_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4135:3: ( (lv_begin_2_0= ruleIntegerCommand ) )
            // InternalFml.g:4136:4: (lv_begin_2_0= ruleIntegerCommand )
            {
            // InternalFml.g:4136:4: (lv_begin_2_0= ruleIntegerCommand )
            // InternalFml.g:4137:5: lv_begin_2_0= ruleIntegerCommand
            {

            					newCompositeNode(grammarAccess.getStringSubstringAccess().getBeginIntegerCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_13);
            lv_begin_2_0=ruleIntegerCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringSubstringRule());
            					}
            					set(
            						current,
            						"begin",
            						lv_begin_2_0,
            						"org.xtext.example.mydsl.Fml.IntegerCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4154:3: ( (lv_end_3_0= ruleIntegerCommand ) )
            // InternalFml.g:4155:4: (lv_end_3_0= ruleIntegerCommand )
            {
            // InternalFml.g:4155:4: (lv_end_3_0= ruleIntegerCommand )
            // InternalFml.g:4156:5: lv_end_3_0= ruleIntegerCommand
            {

            					newCompositeNode(grammarAccess.getStringSubstringAccess().getEndIntegerCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_end_3_0=ruleIntegerCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringSubstringRule());
            					}
            					set(
            						current,
            						"end",
            						lv_end_3_0,
            						"org.xtext.example.mydsl.Fml.IntegerCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringSubstring"


    // $ANTLR start "entryRuleStringIndexOf"
    // InternalFml.g:4177:1: entryRuleStringIndexOf returns [EObject current=null] : iv_ruleStringIndexOf= ruleStringIndexOf EOF ;
    public final EObject entryRuleStringIndexOf() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringIndexOf = null;


        try {
            // InternalFml.g:4177:54: (iv_ruleStringIndexOf= ruleStringIndexOf EOF )
            // InternalFml.g:4178:2: iv_ruleStringIndexOf= ruleStringIndexOf EOF
            {
             newCompositeNode(grammarAccess.getStringIndexOfRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringIndexOf=ruleStringIndexOf();

            state._fsp--;

             current =iv_ruleStringIndexOf; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringIndexOf"


    // $ANTLR start "ruleStringIndexOf"
    // InternalFml.g:4184:1: ruleStringIndexOf returns [EObject current=null] : (otherlv_0= 'strIndexOf' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_schar_2_0= ruleStrCommand ) ) ) ;
    public final EObject ruleStringIndexOf() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_str_1_0 = null;

        EObject lv_schar_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4190:2: ( (otherlv_0= 'strIndexOf' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_schar_2_0= ruleStrCommand ) ) ) )
            // InternalFml.g:4191:2: (otherlv_0= 'strIndexOf' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_schar_2_0= ruleStrCommand ) ) )
            {
            // InternalFml.g:4191:2: (otherlv_0= 'strIndexOf' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_schar_2_0= ruleStrCommand ) ) )
            // InternalFml.g:4192:3: otherlv_0= 'strIndexOf' ( (lv_str_1_0= ruleStrCommand ) ) ( (lv_schar_2_0= ruleStrCommand ) )
            {
            otherlv_0=(Token)match(input,85,FOLLOW_34); 

            			newLeafNode(otherlv_0, grammarAccess.getStringIndexOfAccess().getStrIndexOfKeyword_0());
            		
            // InternalFml.g:4196:3: ( (lv_str_1_0= ruleStrCommand ) )
            // InternalFml.g:4197:4: (lv_str_1_0= ruleStrCommand )
            {
            // InternalFml.g:4197:4: (lv_str_1_0= ruleStrCommand )
            // InternalFml.g:4198:5: lv_str_1_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringIndexOfAccess().getStrStrCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_34);
            lv_str_1_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringIndexOfRule());
            					}
            					set(
            						current,
            						"str",
            						lv_str_1_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4215:3: ( (lv_schar_2_0= ruleStrCommand ) )
            // InternalFml.g:4216:4: (lv_schar_2_0= ruleStrCommand )
            {
            // InternalFml.g:4216:4: (lv_schar_2_0= ruleStrCommand )
            // InternalFml.g:4217:5: lv_schar_2_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringIndexOfAccess().getScharStrCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_schar_2_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringIndexOfRule());
            					}
            					set(
            						current,
            						"schar",
            						lv_schar_2_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringIndexOf"


    // $ANTLR start "entryRuleStringLength"
    // InternalFml.g:4238:1: entryRuleStringLength returns [EObject current=null] : iv_ruleStringLength= ruleStringLength EOF ;
    public final EObject entryRuleStringLength() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLength = null;


        try {
            // InternalFml.g:4238:53: (iv_ruleStringLength= ruleStringLength EOF )
            // InternalFml.g:4239:2: iv_ruleStringLength= ruleStringLength EOF
            {
             newCompositeNode(grammarAccess.getStringLengthRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringLength=ruleStringLength();

            state._fsp--;

             current =iv_ruleStringLength; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringLength"


    // $ANTLR start "ruleStringLength"
    // InternalFml.g:4245:1: ruleStringLength returns [EObject current=null] : (otherlv_0= 'strLength' ( (lv_str_1_0= ruleStrCommand ) ) ) ;
    public final EObject ruleStringLength() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_str_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4251:2: ( (otherlv_0= 'strLength' ( (lv_str_1_0= ruleStrCommand ) ) ) )
            // InternalFml.g:4252:2: (otherlv_0= 'strLength' ( (lv_str_1_0= ruleStrCommand ) ) )
            {
            // InternalFml.g:4252:2: (otherlv_0= 'strLength' ( (lv_str_1_0= ruleStrCommand ) ) )
            // InternalFml.g:4253:3: otherlv_0= 'strLength' ( (lv_str_1_0= ruleStrCommand ) )
            {
            otherlv_0=(Token)match(input,86,FOLLOW_34); 

            			newLeafNode(otherlv_0, grammarAccess.getStringLengthAccess().getStrLengthKeyword_0());
            		
            // InternalFml.g:4257:3: ( (lv_str_1_0= ruleStrCommand ) )
            // InternalFml.g:4258:4: (lv_str_1_0= ruleStrCommand )
            {
            // InternalFml.g:4258:4: (lv_str_1_0= ruleStrCommand )
            // InternalFml.g:4259:5: lv_str_1_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getStringLengthAccess().getStrStrCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_str_1_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStringLengthRule());
            					}
            					set(
            						current,
            						"str",
            						lv_str_1_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringLength"


    // $ANTLR start "entryRuleCompare"
    // InternalFml.g:4280:1: entryRuleCompare returns [EObject current=null] : iv_ruleCompare= ruleCompare EOF ;
    public final EObject entryRuleCompare() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompare = null;


        try {
            // InternalFml.g:4280:48: (iv_ruleCompare= ruleCompare EOF )
            // InternalFml.g:4281:2: iv_ruleCompare= ruleCompare EOF
            {
             newCompositeNode(grammarAccess.getCompareRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompare=ruleCompare();

            state._fsp--;

             current =iv_ruleCompare; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompare"


    // $ANTLR start "ruleCompare"
    // InternalFml.g:4287:1: ruleCompare returns [EObject current=null] : (otherlv_0= 'compare' ( (lv_fm_left_1_0= ruleFMCommand ) ) ( (lv_fm_right_2_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCompare() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_left_1_0 = null;

        EObject lv_fm_right_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4293:2: ( (otherlv_0= 'compare' ( (lv_fm_left_1_0= ruleFMCommand ) ) ( (lv_fm_right_2_0= ruleFMCommand ) ) ) )
            // InternalFml.g:4294:2: (otherlv_0= 'compare' ( (lv_fm_left_1_0= ruleFMCommand ) ) ( (lv_fm_right_2_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:4294:2: (otherlv_0= 'compare' ( (lv_fm_left_1_0= ruleFMCommand ) ) ( (lv_fm_right_2_0= ruleFMCommand ) ) )
            // InternalFml.g:4295:3: otherlv_0= 'compare' ( (lv_fm_left_1_0= ruleFMCommand ) ) ( (lv_fm_right_2_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,87,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCompareAccess().getCompareKeyword_0());
            		
            // InternalFml.g:4299:3: ( (lv_fm_left_1_0= ruleFMCommand ) )
            // InternalFml.g:4300:4: (lv_fm_left_1_0= ruleFMCommand )
            {
            // InternalFml.g:4300:4: (lv_fm_left_1_0= ruleFMCommand )
            // InternalFml.g:4301:5: lv_fm_left_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCompareAccess().getFm_leftFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_19);
            lv_fm_left_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCompareRule());
            					}
            					set(
            						current,
            						"fm_left",
            						lv_fm_left_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4318:3: ( (lv_fm_right_2_0= ruleFMCommand ) )
            // InternalFml.g:4319:4: (lv_fm_right_2_0= ruleFMCommand )
            {
            // InternalFml.g:4319:4: (lv_fm_right_2_0= ruleFMCommand )
            // InternalFml.g:4320:5: lv_fm_right_2_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCompareAccess().getFm_rightFMCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_right_2_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCompareRule());
            					}
            					set(
            						current,
            						"fm_right",
            						lv_fm_right_2_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompare"


    // $ANTLR start "entryRuleParameter"
    // InternalFml.g:4341:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalFml.g:4341:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalFml.g:4342:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalFml.g:4348:1: ruleParameter returns [EObject current=null] : (otherlv_0= 'parameter' ( (lv_param_1_0= ruleFML_IDENTIFIER ) ) ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )? ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_typed_2_0=null;
        AntlrDatatypeRuleToken lv_param_1_0 = null;

        EObject lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4354:2: ( (otherlv_0= 'parameter' ( (lv_param_1_0= ruleFML_IDENTIFIER ) ) ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )? ) )
            // InternalFml.g:4355:2: (otherlv_0= 'parameter' ( (lv_param_1_0= ruleFML_IDENTIFIER ) ) ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )? )
            {
            // InternalFml.g:4355:2: (otherlv_0= 'parameter' ( (lv_param_1_0= ruleFML_IDENTIFIER ) ) ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )? )
            // InternalFml.g:4356:3: otherlv_0= 'parameter' ( (lv_param_1_0= ruleFML_IDENTIFIER ) ) ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )?
            {
            otherlv_0=(Token)match(input,88,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getParameterAccess().getParameterKeyword_0());
            		
            // InternalFml.g:4360:3: ( (lv_param_1_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:4361:4: (lv_param_1_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:4361:4: (lv_param_1_0= ruleFML_IDENTIFIER )
            // InternalFml.g:4362:5: lv_param_1_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getParameterAccess().getParamFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_35);
            lv_param_1_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterRule());
            					}
            					set(
            						current,
            						"param",
            						lv_param_1_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4379:3: ( ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==89) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalFml.g:4380:4: ( (lv_typed_2_0= ':' ) ) ( (lv_type_3_0= rulelType ) )
                    {
                    // InternalFml.g:4380:4: ( (lv_typed_2_0= ':' ) )
                    // InternalFml.g:4381:5: (lv_typed_2_0= ':' )
                    {
                    // InternalFml.g:4381:5: (lv_typed_2_0= ':' )
                    // InternalFml.g:4382:6: lv_typed_2_0= ':'
                    {
                    lv_typed_2_0=(Token)match(input,89,FOLLOW_36); 

                    						newLeafNode(lv_typed_2_0, grammarAccess.getParameterAccess().getTypedColonKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(current, "typed", lv_typed_2_0, ":");
                    					

                    }


                    }

                    // InternalFml.g:4394:4: ( (lv_type_3_0= rulelType ) )
                    // InternalFml.g:4395:5: (lv_type_3_0= rulelType )
                    {
                    // InternalFml.g:4395:5: (lv_type_3_0= rulelType )
                    // InternalFml.g:4396:6: lv_type_3_0= rulelType
                    {

                    						newCompositeNode(grammarAccess.getParameterAccess().getTypeLTypeParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_type_3_0=rulelType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getParameterRule());
                    						}
                    						set(
                    							current,
                    							"type",
                    							lv_type_3_0,
                    							"org.xtext.example.mydsl.Fml.lType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleLoadGeneric"
    // InternalFml.g:4418:1: entryRuleLoadGeneric returns [EObject current=null] : iv_ruleLoadGeneric= ruleLoadGeneric EOF ;
    public final EObject entryRuleLoadGeneric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadGeneric = null;


        try {
            // InternalFml.g:4418:52: (iv_ruleLoadGeneric= ruleLoadGeneric EOF )
            // InternalFml.g:4419:2: iv_ruleLoadGeneric= ruleLoadGeneric EOF
            {
             newCompositeNode(grammarAccess.getLoadGenericRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLoadGeneric=ruleLoadGeneric();

            state._fsp--;

             current =iv_ruleLoadGeneric; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLoadGeneric"


    // $ANTLR start "ruleLoadGeneric"
    // InternalFml.g:4425:1: ruleLoadGeneric returns [EObject current=null] : (otherlv_0= 'run' ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) ) (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )? (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )? ) ;
    public final EObject ruleLoadGeneric() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_stream_1_1=null;
        Token this_LEFT_BRACKET_2=null;
        Token this_RIGHT_BRACKET_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_stream_1_2 = null;

        AntlrDatatypeRuleToken lv_params_3_0 = null;

        AntlrDatatypeRuleToken lv_ns_6_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4431:2: ( (otherlv_0= 'run' ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) ) (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )? (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )? ) )
            // InternalFml.g:4432:2: (otherlv_0= 'run' ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) ) (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )? (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )? )
            {
            // InternalFml.g:4432:2: (otherlv_0= 'run' ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) ) (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )? (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )? )
            // InternalFml.g:4433:3: otherlv_0= 'run' ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) ) (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )? (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )?
            {
            otherlv_0=(Token)match(input,90,FOLLOW_37); 

            			newLeafNode(otherlv_0, grammarAccess.getLoadGenericAccess().getRunKeyword_0());
            		
            // InternalFml.g:4437:3: ( ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:4438:4: ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) )
            {
            // InternalFml.g:4438:4: ( (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER ) )
            // InternalFml.g:4439:5: (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:4439:5: (lv_stream_1_1= RULE_STRING | lv_stream_1_2= ruleFML_IDENTIFIER )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_STRING) ) {
                alt41=1;
            }
            else if ( (LA41_0==RULE_ID||LA41_0==168) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // InternalFml.g:4440:6: lv_stream_1_1= RULE_STRING
                    {
                    lv_stream_1_1=(Token)match(input,RULE_STRING,FOLLOW_38); 

                    						newLeafNode(lv_stream_1_1, grammarAccess.getLoadGenericAccess().getStreamSTRINGTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLoadGenericRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"stream",
                    							lv_stream_1_1,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:4455:6: lv_stream_1_2= ruleFML_IDENTIFIER
                    {

                    						newCompositeNode(grammarAccess.getLoadGenericAccess().getStreamFML_IDENTIFIERParserRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_38);
                    lv_stream_1_2=ruleFML_IDENTIFIER();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getLoadGenericRule());
                    						}
                    						set(
                    							current,
                    							"stream",
                    							lv_stream_1_2,
                    							"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:4473:3: (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )?
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // InternalFml.g:4474:4: this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_2=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_39); 

                    				newLeafNode(this_LEFT_BRACKET_2, grammarAccess.getLoadGenericAccess().getLEFT_BRACKETTerminalRuleCall_2_0());
                    			
                    // InternalFml.g:4478:4: ( (lv_params_3_0= ruleFML_IDENTIFIER ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==RULE_ID||LA42_0==168) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // InternalFml.g:4479:5: (lv_params_3_0= ruleFML_IDENTIFIER )
                    	    {
                    	    // InternalFml.g:4479:5: (lv_params_3_0= ruleFML_IDENTIFIER )
                    	    // InternalFml.g:4480:6: lv_params_3_0= ruleFML_IDENTIFIER
                    	    {

                    	    						newCompositeNode(grammarAccess.getLoadGenericAccess().getParamsFML_IDENTIFIERParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_39);
                    	    lv_params_3_0=ruleFML_IDENTIFIER();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getLoadGenericRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"params",
                    	    							lv_params_3_0,
                    	    							"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);

                    this_RIGHT_BRACKET_4=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_40); 

                    				newLeafNode(this_RIGHT_BRACKET_4, grammarAccess.getLoadGenericAccess().getRIGHT_BRACKETTerminalRuleCall_2_2());
                    			

                    }
                    break;

            }

            // InternalFml.g:4502:3: (otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==91) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalFml.g:4503:4: otherlv_5= 'into' ( (lv_ns_6_0= ruleFML_IDENTIFIER ) )
                    {
                    otherlv_5=(Token)match(input,91,FOLLOW_25); 

                    				newLeafNode(otherlv_5, grammarAccess.getLoadGenericAccess().getIntoKeyword_3_0());
                    			
                    // InternalFml.g:4507:4: ( (lv_ns_6_0= ruleFML_IDENTIFIER ) )
                    // InternalFml.g:4508:5: (lv_ns_6_0= ruleFML_IDENTIFIER )
                    {
                    // InternalFml.g:4508:5: (lv_ns_6_0= ruleFML_IDENTIFIER )
                    // InternalFml.g:4509:6: lv_ns_6_0= ruleFML_IDENTIFIER
                    {

                    						newCompositeNode(grammarAccess.getLoadGenericAccess().getNsFML_IDENTIFIERParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_ns_6_0=ruleFML_IDENTIFIER();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getLoadGenericRule());
                    						}
                    						set(
                    							current,
                    							"ns",
                    							lv_ns_6_0,
                    							"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLoadGeneric"


    // $ANTLR start "entryRuleCTCRCommand"
    // InternalFml.g:4531:1: entryRuleCTCRCommand returns [EObject current=null] : iv_ruleCTCRCommand= ruleCTCRCommand EOF ;
    public final EObject entryRuleCTCRCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCTCRCommand = null;


        try {
            // InternalFml.g:4531:52: (iv_ruleCTCRCommand= ruleCTCRCommand EOF )
            // InternalFml.g:4532:2: iv_ruleCTCRCommand= ruleCTCRCommand EOF
            {
             newCompositeNode(grammarAccess.getCTCRCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCTCRCommand=ruleCTCRCommand();

            state._fsp--;

             current =iv_ruleCTCRCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCTCRCommand"


    // $ANTLR start "ruleCTCRCommand"
    // InternalFml.g:4538:1: ruleCTCRCommand returns [EObject current=null] : (otherlv_0= 'ctcr' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCTCRCommand() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4544:2: ( (otherlv_0= 'ctcr' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:4545:2: (otherlv_0= 'ctcr' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:4545:2: (otherlv_0= 'ctcr' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:4546:3: otherlv_0= 'ctcr' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,92,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCTCRCommandAccess().getCtcrKeyword_0());
            		
            // InternalFml.g:4550:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:4551:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:4551:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:4552:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCTCRCommandAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCTCRCommandRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCTCRCommand"


    // $ANTLR start "entryRuleMerge"
    // InternalFml.g:4573:1: entryRuleMerge returns [EObject current=null] : iv_ruleMerge= ruleMerge EOF ;
    public final EObject entryRuleMerge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMerge = null;


        try {
            // InternalFml.g:4573:46: (iv_ruleMerge= ruleMerge EOF )
            // InternalFml.g:4574:2: iv_ruleMerge= ruleMerge EOF
            {
             newCompositeNode(grammarAccess.getMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMerge=ruleMerge();

            state._fsp--;

             current =iv_ruleMerge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMerge"


    // $ANTLR start "ruleMerge"
    // InternalFml.g:4580:1: ruleMerge returns [EObject current=null] : (otherlv_0= 'merge' ( (lv_backend_1_0= ruleBDDBackend ) )? ( (lv_lazy_2_0= '--lazy' ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) ) ;
    public final EObject ruleMerge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_lazy_2_0=null;
        Token this_LEFT_BRACKET_4=null;
        Token this_RIGHT_BRACKET_6=null;
        Enumerator lv_backend_1_0 = null;

        Enumerator lv_mode_3_0 = null;

        EObject lv_lfms_5_0 = null;

        EObject lv_fms_7_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4586:2: ( (otherlv_0= 'merge' ( (lv_backend_1_0= ruleBDDBackend ) )? ( (lv_lazy_2_0= '--lazy' ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) ) )
            // InternalFml.g:4587:2: (otherlv_0= 'merge' ( (lv_backend_1_0= ruleBDDBackend ) )? ( (lv_lazy_2_0= '--lazy' ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) )
            {
            // InternalFml.g:4587:2: (otherlv_0= 'merge' ( (lv_backend_1_0= ruleBDDBackend ) )? ( (lv_lazy_2_0= '--lazy' ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) )
            // InternalFml.g:4588:3: otherlv_0= 'merge' ( (lv_backend_1_0= ruleBDDBackend ) )? ( (lv_lazy_2_0= '--lazy' ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) )
            {
            otherlv_0=(Token)match(input,93,FOLLOW_41); 

            			newLeafNode(otherlv_0, grammarAccess.getMergeAccess().getMergeKeyword_0());
            		
            // InternalFml.g:4592:3: ( (lv_backend_1_0= ruleBDDBackend ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( ((LA45_0>=190 && LA45_0<=192)) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalFml.g:4593:4: (lv_backend_1_0= ruleBDDBackend )
                    {
                    // InternalFml.g:4593:4: (lv_backend_1_0= ruleBDDBackend )
                    // InternalFml.g:4594:5: lv_backend_1_0= ruleBDDBackend
                    {

                    					newCompositeNode(grammarAccess.getMergeAccess().getBackendBDDBackendEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_41);
                    lv_backend_1_0=ruleBDDBackend();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getMergeRule());
                    					}
                    					set(
                    						current,
                    						"backend",
                    						lv_backend_1_0,
                    						"org.xtext.example.mydsl.Fml.BDDBackend");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalFml.g:4611:3: ( (lv_lazy_2_0= '--lazy' ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==94) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalFml.g:4612:4: (lv_lazy_2_0= '--lazy' )
                    {
                    // InternalFml.g:4612:4: (lv_lazy_2_0= '--lazy' )
                    // InternalFml.g:4613:5: lv_lazy_2_0= '--lazy'
                    {
                    lv_lazy_2_0=(Token)match(input,94,FOLLOW_41); 

                    					newLeafNode(lv_lazy_2_0, grammarAccess.getMergeAccess().getLazyLazyKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getMergeRule());
                    					}
                    					setWithLastConsumed(current, "lazy", true, "--lazy");
                    				

                    }


                    }
                    break;

            }

            // InternalFml.g:4625:3: ( (lv_mode_3_0= ruleMergeMode ) )
            // InternalFml.g:4626:4: (lv_mode_3_0= ruleMergeMode )
            {
            // InternalFml.g:4626:4: (lv_mode_3_0= ruleMergeMode )
            // InternalFml.g:4627:5: lv_mode_3_0= ruleMergeMode
            {

            					newCompositeNode(grammarAccess.getMergeAccess().getModeMergeModeEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_42);
            lv_mode_3_0=ruleMergeMode();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMergeRule());
            					}
            					set(
            						current,
            						"mode",
            						lv_mode_3_0,
            						"org.xtext.example.mydsl.Fml.MergeMode");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4644:3: ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==RULE_LEFT_BRACKET) ) {
                alt48=1;
            }
            else if ( (LA48_0==RULE_ID||LA48_0==80||LA48_0==93||LA48_0==95||LA48_0==97||(LA48_0>=106 && LA48_0<=107)||LA48_0==114||LA48_0==128||(LA48_0>=145 && LA48_0<=146)||LA48_0==152||(LA48_0>=165 && LA48_0<=166)||LA48_0==168) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // InternalFml.g:4645:4: (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET )
                    {
                    // InternalFml.g:4645:4: (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET )
                    // InternalFml.g:4646:5: this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_4=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_19); 

                    					newLeafNode(this_LEFT_BRACKET_4, grammarAccess.getMergeAccess().getLEFT_BRACKETTerminalRuleCall_4_0_0());
                    				
                    // InternalFml.g:4650:5: ( (lv_lfms_5_0= ruleFMCommand ) )+
                    int cnt47=0;
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==RULE_ID||LA47_0==80||LA47_0==93||LA47_0==95||LA47_0==97||(LA47_0>=106 && LA47_0<=107)||LA47_0==114||LA47_0==128||(LA47_0>=145 && LA47_0<=146)||LA47_0==152||(LA47_0>=165 && LA47_0<=166)||LA47_0==168) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // InternalFml.g:4651:6: (lv_lfms_5_0= ruleFMCommand )
                    	    {
                    	    // InternalFml.g:4651:6: (lv_lfms_5_0= ruleFMCommand )
                    	    // InternalFml.g:4652:7: lv_lfms_5_0= ruleFMCommand
                    	    {

                    	    							newCompositeNode(grammarAccess.getMergeAccess().getLfmsFMCommandParserRuleCall_4_0_1_0());
                    	    						
                    	    pushFollow(FOLLOW_43);
                    	    lv_lfms_5_0=ruleFMCommand();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getMergeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"lfms",
                    	    								lv_lfms_5_0,
                    	    								"org.xtext.example.mydsl.Fml.FMCommand");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt47 >= 1 ) break loop47;
                                EarlyExitException eee =
                                    new EarlyExitException(47, input);
                                throw eee;
                        }
                        cnt47++;
                    } while (true);

                    this_RIGHT_BRACKET_6=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

                    					newLeafNode(this_RIGHT_BRACKET_6, grammarAccess.getMergeAccess().getRIGHT_BRACKETTerminalRuleCall_4_0_2());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:4675:4: ( (lv_fms_7_0= ruleLFMArgs ) )
                    {
                    // InternalFml.g:4675:4: ( (lv_fms_7_0= ruleLFMArgs ) )
                    // InternalFml.g:4676:5: (lv_fms_7_0= ruleLFMArgs )
                    {
                    // InternalFml.g:4676:5: (lv_fms_7_0= ruleLFMArgs )
                    // InternalFml.g:4677:6: lv_fms_7_0= ruleLFMArgs
                    {

                    						newCompositeNode(grammarAccess.getMergeAccess().getFmsLFMArgsParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fms_7_0=ruleLFMArgs();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMergeRule());
                    						}
                    						set(
                    							current,
                    							"fms",
                    							lv_fms_7_0,
                    							"org.xtext.example.mydsl.Fml.LFMArgs");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMerge"


    // $ANTLR start "entryRuleLFMArgs"
    // InternalFml.g:4699:1: entryRuleLFMArgs returns [EObject current=null] : iv_ruleLFMArgs= ruleLFMArgs EOF ;
    public final EObject entryRuleLFMArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLFMArgs = null;


        try {
            // InternalFml.g:4699:48: (iv_ruleLFMArgs= ruleLFMArgs EOF )
            // InternalFml.g:4700:2: iv_ruleLFMArgs= ruleLFMArgs EOF
            {
             newCompositeNode(grammarAccess.getLFMArgsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLFMArgs=ruleLFMArgs();

            state._fsp--;

             current =iv_ruleLFMArgs; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLFMArgs"


    // $ANTLR start "ruleLFMArgs"
    // InternalFml.g:4706:1: ruleLFMArgs returns [EObject current=null] : ( ( (lv_lfms_0_0= ruleFMCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )* ) ;
    public final EObject ruleLFMArgs() throws RecognitionException {
        EObject current = null;

        Token this_COMMA_1=null;
        EObject lv_lfms_0_0 = null;

        EObject lv_lfms_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4712:2: ( ( ( (lv_lfms_0_0= ruleFMCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )* ) )
            // InternalFml.g:4713:2: ( ( (lv_lfms_0_0= ruleFMCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )* )
            {
            // InternalFml.g:4713:2: ( ( (lv_lfms_0_0= ruleFMCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )* )
            // InternalFml.g:4714:3: ( (lv_lfms_0_0= ruleFMCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )*
            {
            // InternalFml.g:4714:3: ( (lv_lfms_0_0= ruleFMCommand ) )
            // InternalFml.g:4715:4: (lv_lfms_0_0= ruleFMCommand )
            {
            // InternalFml.g:4715:4: (lv_lfms_0_0= ruleFMCommand )
            // InternalFml.g:4716:5: lv_lfms_0_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getLFMArgsAccess().getLfmsFMCommandParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_44);
            lv_lfms_0_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLFMArgsRule());
            					}
            					add(
            						current,
            						"lfms",
            						lv_lfms_0_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4733:3: (this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) ) )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==RULE_COMMA) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // InternalFml.g:4734:4: this_COMMA_1= RULE_COMMA ( (lv_lfms_2_0= ruleFMCommand ) )
            	    {
            	    this_COMMA_1=(Token)match(input,RULE_COMMA,FOLLOW_19); 

            	    				newLeafNode(this_COMMA_1, grammarAccess.getLFMArgsAccess().getCOMMATerminalRuleCall_1_0());
            	    			
            	    // InternalFml.g:4738:4: ( (lv_lfms_2_0= ruleFMCommand ) )
            	    // InternalFml.g:4739:5: (lv_lfms_2_0= ruleFMCommand )
            	    {
            	    // InternalFml.g:4739:5: (lv_lfms_2_0= ruleFMCommand )
            	    // InternalFml.g:4740:6: lv_lfms_2_0= ruleFMCommand
            	    {

            	    						newCompositeNode(grammarAccess.getLFMArgsAccess().getLfmsFMCommandParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_44);
            	    lv_lfms_2_0=ruleFMCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getLFMArgsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"lfms",
            	    							lv_lfms_2_0,
            	    							"org.xtext.example.mydsl.Fml.FMCommand");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLFMArgs"


    // $ANTLR start "entryRuleAggregateMerge"
    // InternalFml.g:4762:1: entryRuleAggregateMerge returns [EObject current=null] : iv_ruleAggregateMerge= ruleAggregateMerge EOF ;
    public final EObject entryRuleAggregateMerge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAggregateMerge = null;


        try {
            // InternalFml.g:4762:55: (iv_ruleAggregateMerge= ruleAggregateMerge EOF )
            // InternalFml.g:4763:2: iv_ruleAggregateMerge= ruleAggregateMerge EOF
            {
             newCompositeNode(grammarAccess.getAggregateMergeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAggregateMerge=ruleAggregateMerge();

            state._fsp--;

             current =iv_ruleAggregateMerge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAggregateMerge"


    // $ANTLR start "ruleAggregateMerge"
    // InternalFml.g:4769:1: ruleAggregateMerge returns [EObject current=null] : (otherlv_0= 'aggregateMerge' ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) ) ;
    public final EObject ruleAggregateMerge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_hierarchySpecified_1_0=null;
        Token this_LEFT_BRACKET_4=null;
        Token this_RIGHT_BRACKET_6=null;
        Enumerator lv_hierarchyStrategy_2_0 = null;

        Enumerator lv_mode_3_0 = null;

        EObject lv_lfms_5_0 = null;

        EObject lv_fms_7_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4775:2: ( (otherlv_0= 'aggregateMerge' ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) ) )
            // InternalFml.g:4776:2: (otherlv_0= 'aggregateMerge' ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) )
            {
            // InternalFml.g:4776:2: (otherlv_0= 'aggregateMerge' ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) ) )
            // InternalFml.g:4777:3: otherlv_0= 'aggregateMerge' ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )? ( (lv_mode_3_0= ruleMergeMode ) ) ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) )
            {
            otherlv_0=(Token)match(input,95,FOLLOW_45); 

            			newLeafNode(otherlv_0, grammarAccess.getAggregateMergeAccess().getAggregateMergeKeyword_0());
            		
            // InternalFml.g:4781:3: ( ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==96) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalFml.g:4782:4: ( (lv_hierarchySpecified_1_0= '--hierarchy' ) ) ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) )
                    {
                    // InternalFml.g:4782:4: ( (lv_hierarchySpecified_1_0= '--hierarchy' ) )
                    // InternalFml.g:4783:5: (lv_hierarchySpecified_1_0= '--hierarchy' )
                    {
                    // InternalFml.g:4783:5: (lv_hierarchySpecified_1_0= '--hierarchy' )
                    // InternalFml.g:4784:6: lv_hierarchySpecified_1_0= '--hierarchy'
                    {
                    lv_hierarchySpecified_1_0=(Token)match(input,96,FOLLOW_46); 

                    						newLeafNode(lv_hierarchySpecified_1_0, grammarAccess.getAggregateMergeAccess().getHierarchySpecifiedHierarchyKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAggregateMergeRule());
                    						}
                    						setWithLastConsumed(current, "hierarchySpecified", true, "--hierarchy");
                    					

                    }


                    }

                    // InternalFml.g:4796:4: ( (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy ) )
                    // InternalFml.g:4797:5: (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy )
                    {
                    // InternalFml.g:4797:5: (lv_hierarchyStrategy_2_0= ruleHierarchyStrategy )
                    // InternalFml.g:4798:6: lv_hierarchyStrategy_2_0= ruleHierarchyStrategy
                    {

                    						newCompositeNode(grammarAccess.getAggregateMergeAccess().getHierarchyStrategyHierarchyStrategyEnumRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_41);
                    lv_hierarchyStrategy_2_0=ruleHierarchyStrategy();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAggregateMergeRule());
                    						}
                    						set(
                    							current,
                    							"hierarchyStrategy",
                    							lv_hierarchyStrategy_2_0,
                    							"org.xtext.example.mydsl.Fml.HierarchyStrategy");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalFml.g:4816:3: ( (lv_mode_3_0= ruleMergeMode ) )
            // InternalFml.g:4817:4: (lv_mode_3_0= ruleMergeMode )
            {
            // InternalFml.g:4817:4: (lv_mode_3_0= ruleMergeMode )
            // InternalFml.g:4818:5: lv_mode_3_0= ruleMergeMode
            {

            					newCompositeNode(grammarAccess.getAggregateMergeAccess().getModeMergeModeEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_42);
            lv_mode_3_0=ruleMergeMode();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAggregateMergeRule());
            					}
            					set(
            						current,
            						"mode",
            						lv_mode_3_0,
            						"org.xtext.example.mydsl.Fml.MergeMode");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4835:3: ( (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET ) | ( (lv_fms_7_0= ruleLFMArgs ) ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==RULE_LEFT_BRACKET) ) {
                alt52=1;
            }
            else if ( (LA52_0==RULE_ID||LA52_0==80||LA52_0==93||LA52_0==95||LA52_0==97||(LA52_0>=106 && LA52_0<=107)||LA52_0==114||LA52_0==128||(LA52_0>=145 && LA52_0<=146)||LA52_0==152||(LA52_0>=165 && LA52_0<=166)||LA52_0==168) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // InternalFml.g:4836:4: (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET )
                    {
                    // InternalFml.g:4836:4: (this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET )
                    // InternalFml.g:4837:5: this_LEFT_BRACKET_4= RULE_LEFT_BRACKET ( (lv_lfms_5_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_6= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_4=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_19); 

                    					newLeafNode(this_LEFT_BRACKET_4, grammarAccess.getAggregateMergeAccess().getLEFT_BRACKETTerminalRuleCall_3_0_0());
                    				
                    // InternalFml.g:4841:5: ( (lv_lfms_5_0= ruleFMCommand ) )+
                    int cnt51=0;
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==RULE_ID||LA51_0==80||LA51_0==93||LA51_0==95||LA51_0==97||(LA51_0>=106 && LA51_0<=107)||LA51_0==114||LA51_0==128||(LA51_0>=145 && LA51_0<=146)||LA51_0==152||(LA51_0>=165 && LA51_0<=166)||LA51_0==168) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // InternalFml.g:4842:6: (lv_lfms_5_0= ruleFMCommand )
                    	    {
                    	    // InternalFml.g:4842:6: (lv_lfms_5_0= ruleFMCommand )
                    	    // InternalFml.g:4843:7: lv_lfms_5_0= ruleFMCommand
                    	    {

                    	    							newCompositeNode(grammarAccess.getAggregateMergeAccess().getLfmsFMCommandParserRuleCall_3_0_1_0());
                    	    						
                    	    pushFollow(FOLLOW_43);
                    	    lv_lfms_5_0=ruleFMCommand();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getAggregateMergeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"lfms",
                    	    								lv_lfms_5_0,
                    	    								"org.xtext.example.mydsl.Fml.FMCommand");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt51 >= 1 ) break loop51;
                                EarlyExitException eee =
                                    new EarlyExitException(51, input);
                                throw eee;
                        }
                        cnt51++;
                    } while (true);

                    this_RIGHT_BRACKET_6=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_2); 

                    					newLeafNode(this_RIGHT_BRACKET_6, grammarAccess.getAggregateMergeAccess().getRIGHT_BRACKETTerminalRuleCall_3_0_2());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:4866:4: ( (lv_fms_7_0= ruleLFMArgs ) )
                    {
                    // InternalFml.g:4866:4: ( (lv_fms_7_0= ruleLFMArgs ) )
                    // InternalFml.g:4867:5: (lv_fms_7_0= ruleLFMArgs )
                    {
                    // InternalFml.g:4867:5: (lv_fms_7_0= ruleLFMArgs )
                    // InternalFml.g:4868:6: lv_fms_7_0= ruleLFMArgs
                    {

                    						newCompositeNode(grammarAccess.getAggregateMergeAccess().getFmsLFMArgsParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_fms_7_0=ruleLFMArgs();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAggregateMergeRule());
                    						}
                    						set(
                    							current,
                    							"fms",
                    							lv_fms_7_0,
                    							"org.xtext.example.mydsl.Fml.LFMArgs");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAggregateMerge"


    // $ANTLR start "entryRuleSynthesis"
    // InternalFml.g:4890:1: entryRuleSynthesis returns [EObject current=null] : iv_ruleSynthesis= ruleSynthesis EOF ;
    public final EObject entryRuleSynthesis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSynthesis = null;


        try {
            // InternalFml.g:4890:50: (iv_ruleSynthesis= ruleSynthesis EOF )
            // InternalFml.g:4891:2: iv_ruleSynthesis= ruleSynthesis EOF
            {
             newCompositeNode(grammarAccess.getSynthesisRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSynthesis=ruleSynthesis();

            state._fsp--;

             current =iv_ruleSynthesis; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSynthesis"


    // $ANTLR start "ruleSynthesis"
    // InternalFml.g:4897:1: ruleSynthesis returns [EObject current=null] : (otherlv_0= 'ksynthesis' ( (lv_interactive_1_0= '--interactive' ) )? ( (lv_fm_2_0= ruleFMCommand ) ) ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )? (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )? ) ;
    public final EObject ruleSynthesis() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_interactive_1_0=null;
        Token lv_over_3_0=null;
        Token otherlv_5=null;
        EObject lv_fm_2_0 = null;

        EObject lv_fts_4_0 = null;

        EObject lv_kst_6_0 = null;



        	enterRule();

        try {
            // InternalFml.g:4903:2: ( (otherlv_0= 'ksynthesis' ( (lv_interactive_1_0= '--interactive' ) )? ( (lv_fm_2_0= ruleFMCommand ) ) ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )? (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )? ) )
            // InternalFml.g:4904:2: (otherlv_0= 'ksynthesis' ( (lv_interactive_1_0= '--interactive' ) )? ( (lv_fm_2_0= ruleFMCommand ) ) ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )? (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )? )
            {
            // InternalFml.g:4904:2: (otherlv_0= 'ksynthesis' ( (lv_interactive_1_0= '--interactive' ) )? ( (lv_fm_2_0= ruleFMCommand ) ) ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )? (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )? )
            // InternalFml.g:4905:3: otherlv_0= 'ksynthesis' ( (lv_interactive_1_0= '--interactive' ) )? ( (lv_fm_2_0= ruleFMCommand ) ) ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )? (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )?
            {
            otherlv_0=(Token)match(input,97,FOLLOW_47); 

            			newLeafNode(otherlv_0, grammarAccess.getSynthesisAccess().getKsynthesisKeyword_0());
            		
            // InternalFml.g:4909:3: ( (lv_interactive_1_0= '--interactive' ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==98) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalFml.g:4910:4: (lv_interactive_1_0= '--interactive' )
                    {
                    // InternalFml.g:4910:4: (lv_interactive_1_0= '--interactive' )
                    // InternalFml.g:4911:5: lv_interactive_1_0= '--interactive'
                    {
                    lv_interactive_1_0=(Token)match(input,98,FOLLOW_19); 

                    					newLeafNode(lv_interactive_1_0, grammarAccess.getSynthesisAccess().getInteractiveInteractiveKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getSynthesisRule());
                    					}
                    					setWithLastConsumed(current, "interactive", true, "--interactive");
                    				

                    }


                    }
                    break;

            }

            // InternalFml.g:4923:3: ( (lv_fm_2_0= ruleFMCommand ) )
            // InternalFml.g:4924:4: (lv_fm_2_0= ruleFMCommand )
            {
            // InternalFml.g:4924:4: (lv_fm_2_0= ruleFMCommand )
            // InternalFml.g:4925:5: lv_fm_2_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getSynthesisAccess().getFmFMCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_48);
            lv_fm_2_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSynthesisRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_2_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:4942:3: ( ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==54) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalFml.g:4943:4: ( (lv_over_3_0= 'over' ) ) ( (lv_fts_4_0= ruleSetCommand ) )
                    {
                    // InternalFml.g:4943:4: ( (lv_over_3_0= 'over' ) )
                    // InternalFml.g:4944:5: (lv_over_3_0= 'over' )
                    {
                    // InternalFml.g:4944:5: (lv_over_3_0= 'over' )
                    // InternalFml.g:4945:6: lv_over_3_0= 'over'
                    {
                    lv_over_3_0=(Token)match(input,54,FOLLOW_29); 

                    						newLeafNode(lv_over_3_0, grammarAccess.getSynthesisAccess().getOverOverKeyword_3_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSynthesisRule());
                    						}
                    						setWithLastConsumed(current, "over", true, "over");
                    					

                    }


                    }

                    // InternalFml.g:4957:4: ( (lv_fts_4_0= ruleSetCommand ) )
                    // InternalFml.g:4958:5: (lv_fts_4_0= ruleSetCommand )
                    {
                    // InternalFml.g:4958:5: (lv_fts_4_0= ruleSetCommand )
                    // InternalFml.g:4959:6: lv_fts_4_0= ruleSetCommand
                    {

                    						newCompositeNode(grammarAccess.getSynthesisAccess().getFtsSetCommandParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_49);
                    lv_fts_4_0=ruleSetCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSynthesisRule());
                    						}
                    						set(
                    							current,
                    							"fts",
                    							lv_fts_4_0,
                    							"org.xtext.example.mydsl.Fml.SetCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalFml.g:4977:3: (otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==99) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalFml.g:4978:4: otherlv_5= 'with' ( (lv_kst_6_0= ruleKnowledgeSpecification ) )
                    {
                    otherlv_5=(Token)match(input,99,FOLLOW_50); 

                    				newLeafNode(otherlv_5, grammarAccess.getSynthesisAccess().getWithKeyword_4_0());
                    			
                    // InternalFml.g:4982:4: ( (lv_kst_6_0= ruleKnowledgeSpecification ) )
                    // InternalFml.g:4983:5: (lv_kst_6_0= ruleKnowledgeSpecification )
                    {
                    // InternalFml.g:4983:5: (lv_kst_6_0= ruleKnowledgeSpecification )
                    // InternalFml.g:4984:6: lv_kst_6_0= ruleKnowledgeSpecification
                    {

                    						newCompositeNode(grammarAccess.getSynthesisAccess().getKstKnowledgeSpecificationParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_kst_6_0=ruleKnowledgeSpecification();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSynthesisRule());
                    						}
                    						set(
                    							current,
                    							"kst",
                    							lv_kst_6_0,
                    							"org.xtext.example.mydsl.Fml.KnowledgeSpecification");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSynthesis"


    // $ANTLR start "entryRuleKnowledgeSpecification"
    // InternalFml.g:5006:1: entryRuleKnowledgeSpecification returns [EObject current=null] : iv_ruleKnowledgeSpecification= ruleKnowledgeSpecification EOF ;
    public final EObject entryRuleKnowledgeSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKnowledgeSpecification = null;


        try {
            // InternalFml.g:5006:63: (iv_ruleKnowledgeSpecification= ruleKnowledgeSpecification EOF )
            // InternalFml.g:5007:2: iv_ruleKnowledgeSpecification= ruleKnowledgeSpecification EOF
            {
             newCompositeNode(grammarAccess.getKnowledgeSpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKnowledgeSpecification=ruleKnowledgeSpecification();

            state._fsp--;

             current =iv_ruleKnowledgeSpecification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKnowledgeSpecification"


    // $ANTLR start "ruleKnowledgeSpecification"
    // InternalFml.g:5013:1: ruleKnowledgeSpecification returns [EObject current=null] : ( () ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )? (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )? (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )? ) ;
    public final EObject ruleKnowledgeSpecification() throws RecognitionException {
        EObject current = null;

        Token this_COMMA_2=null;
        Token this_COMMA_4=null;
        EObject lv_hierarchy_1_0 = null;

        EObject lv_groups_3_0 = null;

        EObject lv_constraints_5_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5019:2: ( ( () ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )? (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )? (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )? ) )
            // InternalFml.g:5020:2: ( () ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )? (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )? (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )? )
            {
            // InternalFml.g:5020:2: ( () ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )? (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )? (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )? )
            // InternalFml.g:5021:3: () ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )? (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )? (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )?
            {
            // InternalFml.g:5021:3: ()
            // InternalFml.g:5022:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKnowledgeSpecificationAccess().getKnowledgeSpecificationAction_0(),
            					current);
            			

            }

            // InternalFml.g:5028:3: ( (lv_hierarchy_1_0= ruleHierarchySpecification ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==100) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalFml.g:5029:4: (lv_hierarchy_1_0= ruleHierarchySpecification )
                    {
                    // InternalFml.g:5029:4: (lv_hierarchy_1_0= ruleHierarchySpecification )
                    // InternalFml.g:5030:5: lv_hierarchy_1_0= ruleHierarchySpecification
                    {

                    					newCompositeNode(grammarAccess.getKnowledgeSpecificationAccess().getHierarchyHierarchySpecificationParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_44);
                    lv_hierarchy_1_0=ruleHierarchySpecification();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKnowledgeSpecificationRule());
                    					}
                    					set(
                    						current,
                    						"hierarchy",
                    						lv_hierarchy_1_0,
                    						"org.xtext.example.mydsl.Fml.HierarchySpecification");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalFml.g:5047:3: (this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==RULE_COMMA) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalFml.g:5048:4: this_COMMA_2= RULE_COMMA ( (lv_groups_3_0= ruleGroupsSpecification ) )
                    {
                    this_COMMA_2=(Token)match(input,RULE_COMMA,FOLLOW_51); 

                    				newLeafNode(this_COMMA_2, grammarAccess.getKnowledgeSpecificationAccess().getCOMMATerminalRuleCall_2_0());
                    			
                    // InternalFml.g:5052:4: ( (lv_groups_3_0= ruleGroupsSpecification ) )
                    // InternalFml.g:5053:5: (lv_groups_3_0= ruleGroupsSpecification )
                    {
                    // InternalFml.g:5053:5: (lv_groups_3_0= ruleGroupsSpecification )
                    // InternalFml.g:5054:6: lv_groups_3_0= ruleGroupsSpecification
                    {

                    						newCompositeNode(grammarAccess.getKnowledgeSpecificationAccess().getGroupsGroupsSpecificationParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_44);
                    lv_groups_3_0=ruleGroupsSpecification();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKnowledgeSpecificationRule());
                    						}
                    						set(
                    							current,
                    							"groups",
                    							lv_groups_3_0,
                    							"org.xtext.example.mydsl.Fml.GroupsSpecification");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalFml.g:5072:3: (this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_COMMA) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalFml.g:5073:4: this_COMMA_4= RULE_COMMA ( (lv_constraints_5_0= ruleConstraintsSpecification ) )
                    {
                    this_COMMA_4=(Token)match(input,RULE_COMMA,FOLLOW_52); 

                    				newLeafNode(this_COMMA_4, grammarAccess.getKnowledgeSpecificationAccess().getCOMMATerminalRuleCall_3_0());
                    			
                    // InternalFml.g:5077:4: ( (lv_constraints_5_0= ruleConstraintsSpecification ) )
                    // InternalFml.g:5078:5: (lv_constraints_5_0= ruleConstraintsSpecification )
                    {
                    // InternalFml.g:5078:5: (lv_constraints_5_0= ruleConstraintsSpecification )
                    // InternalFml.g:5079:6: lv_constraints_5_0= ruleConstraintsSpecification
                    {

                    						newCompositeNode(grammarAccess.getKnowledgeSpecificationAccess().getConstraintsConstraintsSpecificationParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_constraints_5_0=ruleConstraintsSpecification();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKnowledgeSpecificationRule());
                    						}
                    						set(
                    							current,
                    							"constraints",
                    							lv_constraints_5_0,
                    							"org.xtext.example.mydsl.Fml.ConstraintsSpecification");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKnowledgeSpecification"


    // $ANTLR start "entryRuleHierarchySpecification"
    // InternalFml.g:5101:1: entryRuleHierarchySpecification returns [EObject current=null] : iv_ruleHierarchySpecification= ruleHierarchySpecification EOF ;
    public final EObject entryRuleHierarchySpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHierarchySpecification = null;


        try {
            // InternalFml.g:5101:63: (iv_ruleHierarchySpecification= ruleHierarchySpecification EOF )
            // InternalFml.g:5102:2: iv_ruleHierarchySpecification= ruleHierarchySpecification EOF
            {
             newCompositeNode(grammarAccess.getHierarchySpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHierarchySpecification=ruleHierarchySpecification();

            state._fsp--;

             current =iv_ruleHierarchySpecification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHierarchySpecification"


    // $ANTLR start "ruleHierarchySpecification"
    // InternalFml.g:5108:1: ruleHierarchySpecification returns [EObject current=null] : (otherlv_0= 'hierarchy=' ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ ) ) ;
    public final EObject ruleHierarchySpecification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_hierarchy_1_0 = null;

        EObject lv_features_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5114:2: ( (otherlv_0= 'hierarchy=' ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ ) ) )
            // InternalFml.g:5115:2: (otherlv_0= 'hierarchy=' ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ ) )
            {
            // InternalFml.g:5115:2: (otherlv_0= 'hierarchy=' ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ ) )
            // InternalFml.g:5116:3: otherlv_0= 'hierarchy=' ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ )
            {
            otherlv_0=(Token)match(input,100,FOLLOW_53); 

            			newLeafNode(otherlv_0, grammarAccess.getHierarchySpecificationAccess().getHierarchyKeyword_0());
            		
            // InternalFml.g:5120:3: ( ( (lv_hierarchy_1_0= ruleHierarchy ) ) | ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+ )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==152) ) {
                alt60=1;
            }
            else if ( (LA60_0==RULE_ID) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // InternalFml.g:5121:4: ( (lv_hierarchy_1_0= ruleHierarchy ) )
                    {
                    // InternalFml.g:5121:4: ( (lv_hierarchy_1_0= ruleHierarchy ) )
                    // InternalFml.g:5122:5: (lv_hierarchy_1_0= ruleHierarchy )
                    {
                    // InternalFml.g:5122:5: (lv_hierarchy_1_0= ruleHierarchy )
                    // InternalFml.g:5123:6: lv_hierarchy_1_0= ruleHierarchy
                    {

                    						newCompositeNode(grammarAccess.getHierarchySpecificationAccess().getHierarchyHierarchyParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_hierarchy_1_0=ruleHierarchy();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getHierarchySpecificationRule());
                    						}
                    						set(
                    							current,
                    							"hierarchy",
                    							lv_hierarchy_1_0,
                    							"org.xtext.example.mydsl.Fml.Hierarchy");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:5141:4: ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+
                    {
                    // InternalFml.g:5141:4: ( ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';' )+
                    int cnt59=0;
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==RULE_ID) ) {
                            int LA59_2 = input.LA(2);

                            if ( (LA59_2==89) ) {
                                alt59=1;
                            }


                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalFml.g:5142:5: ( (lv_features_2_0= ruleHProduction ) ) otherlv_3= ';'
                    	    {
                    	    // InternalFml.g:5142:5: ( (lv_features_2_0= ruleHProduction ) )
                    	    // InternalFml.g:5143:6: (lv_features_2_0= ruleHProduction )
                    	    {
                    	    // InternalFml.g:5143:6: (lv_features_2_0= ruleHProduction )
                    	    // InternalFml.g:5144:7: lv_features_2_0= ruleHProduction
                    	    {

                    	    							newCompositeNode(grammarAccess.getHierarchySpecificationAccess().getFeaturesHProductionParserRuleCall_1_1_0_0());
                    	    						
                    	    pushFollow(FOLLOW_20);
                    	    lv_features_2_0=ruleHProduction();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getHierarchySpecificationRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"features",
                    	    								lv_features_2_0,
                    	    								"org.xtext.example.mydsl.Fml.HProduction");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }

                    	    otherlv_3=(Token)match(input,36,FOLLOW_54); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getHierarchySpecificationAccess().getSemicolonKeyword_1_1_1());
                    	    				

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt59 >= 1 ) break loop59;
                                EarlyExitException eee =
                                    new EarlyExitException(59, input);
                                throw eee;
                        }
                        cnt59++;
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHierarchySpecification"


    // $ANTLR start "entryRuleHProduction"
    // InternalFml.g:5171:1: entryRuleHProduction returns [EObject current=null] : iv_ruleHProduction= ruleHProduction EOF ;
    public final EObject entryRuleHProduction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHProduction = null;


        try {
            // InternalFml.g:5171:52: (iv_ruleHProduction= ruleHProduction EOF )
            // InternalFml.g:5172:2: iv_ruleHProduction= ruleHProduction EOF
            {
             newCompositeNode(grammarAccess.getHProductionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHProduction=ruleHProduction();

            state._fsp--;

             current =iv_ruleHProduction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHProduction"


    // $ANTLR start "ruleHProduction"
    // InternalFml.g:5178:1: ruleHProduction returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleFT_ID ) )+ ) ;
    public final EObject ruleHProduction() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_features_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5184:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleFT_ID ) )+ ) )
            // InternalFml.g:5185:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleFT_ID ) )+ )
            {
            // InternalFml.g:5185:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleFT_ID ) )+ )
            // InternalFml.g:5186:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleFT_ID ) )+
            {
            // InternalFml.g:5186:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalFml.g:5187:4: (lv_name_0_0= RULE_ID )
            {
            // InternalFml.g:5187:4: (lv_name_0_0= RULE_ID )
            // InternalFml.g:5188:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_55); 

            					newLeafNode(lv_name_0_0, grammarAccess.getHProductionAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHProductionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,89,FOLLOW_37); 

            			newLeafNode(otherlv_1, grammarAccess.getHProductionAccess().getColonKeyword_1());
            		
            // InternalFml.g:5208:3: ( (lv_features_2_0= ruleFT_ID ) )+
            int cnt61=0;
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==RULE_STRING||LA61_0==RULE_ID||LA61_0==168) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // InternalFml.g:5209:4: (lv_features_2_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:5209:4: (lv_features_2_0= ruleFT_ID )
            	    // InternalFml.g:5210:5: lv_features_2_0= ruleFT_ID
            	    {

            	    					newCompositeNode(grammarAccess.getHProductionAccess().getFeaturesFT_IDParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_56);
            	    lv_features_2_0=ruleFT_ID();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getHProductionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_2_0,
            	    						"org.xtext.example.mydsl.Fml.FT_ID");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt61 >= 1 ) break loop61;
                        EarlyExitException eee =
                            new EarlyExitException(61, input);
                        throw eee;
                }
                cnt61++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHProduction"


    // $ANTLR start "entryRuleGroupsSpecification"
    // InternalFml.g:5231:1: entryRuleGroupsSpecification returns [EObject current=null] : iv_ruleGroupsSpecification= ruleGroupsSpecification EOF ;
    public final EObject entryRuleGroupsSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupsSpecification = null;


        try {
            // InternalFml.g:5231:60: (iv_ruleGroupsSpecification= ruleGroupsSpecification EOF )
            // InternalFml.g:5232:2: iv_ruleGroupsSpecification= ruleGroupsSpecification EOF
            {
             newCompositeNode(grammarAccess.getGroupsSpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGroupsSpecification=ruleGroupsSpecification();

            state._fsp--;

             current =iv_ruleGroupsSpecification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroupsSpecification"


    // $ANTLR start "ruleGroupsSpecification"
    // InternalFml.g:5238:1: ruleGroupsSpecification returns [EObject current=null] : (otherlv_0= 'groups=' ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+ ) ;
    public final EObject ruleGroupsSpecification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_groups_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5244:2: ( (otherlv_0= 'groups=' ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+ ) )
            // InternalFml.g:5245:2: (otherlv_0= 'groups=' ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+ )
            {
            // InternalFml.g:5245:2: (otherlv_0= 'groups=' ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+ )
            // InternalFml.g:5246:3: otherlv_0= 'groups=' ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+
            {
            otherlv_0=(Token)match(input,101,FOLLOW_57); 

            			newLeafNode(otherlv_0, grammarAccess.getGroupsSpecificationAccess().getGroupsKeyword_0());
            		
            // InternalFml.g:5250:3: ( ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';' )+
            int cnt62=0;
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( ((LA62_0>=102 && LA62_0<=104)) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalFml.g:5251:4: ( (lv_groups_1_0= ruleGroupSpec ) ) otherlv_2= ';'
            	    {
            	    // InternalFml.g:5251:4: ( (lv_groups_1_0= ruleGroupSpec ) )
            	    // InternalFml.g:5252:5: (lv_groups_1_0= ruleGroupSpec )
            	    {
            	    // InternalFml.g:5252:5: (lv_groups_1_0= ruleGroupSpec )
            	    // InternalFml.g:5253:6: lv_groups_1_0= ruleGroupSpec
            	    {

            	    						newCompositeNode(grammarAccess.getGroupsSpecificationAccess().getGroupsGroupSpecParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_20);
            	    lv_groups_1_0=ruleGroupSpec();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getGroupsSpecificationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"groups",
            	    							lv_groups_1_0,
            	    							"org.xtext.example.mydsl.Fml.GroupSpec");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_2=(Token)match(input,36,FOLLOW_58); 

            	    				newLeafNode(otherlv_2, grammarAccess.getGroupsSpecificationAccess().getSemicolonKeyword_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    if ( cnt62 >= 1 ) break loop62;
                        EarlyExitException eee =
                            new EarlyExitException(62, input);
                        throw eee;
                }
                cnt62++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroupsSpecification"


    // $ANTLR start "entryRuleGroupSpec"
    // InternalFml.g:5279:1: entryRuleGroupSpec returns [EObject current=null] : iv_ruleGroupSpec= ruleGroupSpec EOF ;
    public final EObject entryRuleGroupSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupSpec = null;


        try {
            // InternalFml.g:5279:50: (iv_ruleGroupSpec= ruleGroupSpec EOF )
            // InternalFml.g:5280:2: iv_ruleGroupSpec= ruleGroupSpec EOF
            {
             newCompositeNode(grammarAccess.getGroupSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGroupSpec=ruleGroupSpec();

            state._fsp--;

             current =iv_ruleGroupSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGroupSpec"


    // $ANTLR start "ruleGroupSpec"
    // InternalFml.g:5286:1: ruleGroupSpec returns [EObject current=null] : (this_XorGroupSpec_0= ruleXorGroupSpec | this_MtxGroupSpec_1= ruleMtxGroupSpec | this_OrGroupSpec_2= ruleOrGroupSpec ) ;
    public final EObject ruleGroupSpec() throws RecognitionException {
        EObject current = null;

        EObject this_XorGroupSpec_0 = null;

        EObject this_MtxGroupSpec_1 = null;

        EObject this_OrGroupSpec_2 = null;



        	enterRule();

        try {
            // InternalFml.g:5292:2: ( (this_XorGroupSpec_0= ruleXorGroupSpec | this_MtxGroupSpec_1= ruleMtxGroupSpec | this_OrGroupSpec_2= ruleOrGroupSpec ) )
            // InternalFml.g:5293:2: (this_XorGroupSpec_0= ruleXorGroupSpec | this_MtxGroupSpec_1= ruleMtxGroupSpec | this_OrGroupSpec_2= ruleOrGroupSpec )
            {
            // InternalFml.g:5293:2: (this_XorGroupSpec_0= ruleXorGroupSpec | this_MtxGroupSpec_1= ruleMtxGroupSpec | this_OrGroupSpec_2= ruleOrGroupSpec )
            int alt63=3;
            switch ( input.LA(1) ) {
            case 102:
                {
                alt63=1;
                }
                break;
            case 103:
                {
                alt63=2;
                }
                break;
            case 104:
                {
                alt63=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalFml.g:5294:3: this_XorGroupSpec_0= ruleXorGroupSpec
                    {

                    			newCompositeNode(grammarAccess.getGroupSpecAccess().getXorGroupSpecParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XorGroupSpec_0=ruleXorGroupSpec();

                    state._fsp--;


                    			current = this_XorGroupSpec_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:5303:3: this_MtxGroupSpec_1= ruleMtxGroupSpec
                    {

                    			newCompositeNode(grammarAccess.getGroupSpecAccess().getMtxGroupSpecParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_MtxGroupSpec_1=ruleMtxGroupSpec();

                    state._fsp--;


                    			current = this_MtxGroupSpec_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:5312:3: this_OrGroupSpec_2= ruleOrGroupSpec
                    {

                    			newCompositeNode(grammarAccess.getGroupSpecAccess().getOrGroupSpecParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_OrGroupSpec_2=ruleOrGroupSpec();

                    state._fsp--;


                    			current = this_OrGroupSpec_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGroupSpec"


    // $ANTLR start "entryRuleXorGroupSpec"
    // InternalFml.g:5324:1: entryRuleXorGroupSpec returns [EObject current=null] : iv_ruleXorGroupSpec= ruleXorGroupSpec EOF ;
    public final EObject entryRuleXorGroupSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXorGroupSpec = null;


        try {
            // InternalFml.g:5324:53: (iv_ruleXorGroupSpec= ruleXorGroupSpec EOF )
            // InternalFml.g:5325:2: iv_ruleXorGroupSpec= ruleXorGroupSpec EOF
            {
             newCompositeNode(grammarAccess.getXorGroupSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXorGroupSpec=ruleXorGroupSpec();

            state._fsp--;

             current =iv_ruleXorGroupSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXorGroupSpec"


    // $ANTLR start "ruleXorGroupSpec"
    // InternalFml.g:5331:1: ruleXorGroupSpec returns [EObject current=null] : (otherlv_0= 'xorGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) ;
    public final EObject ruleXorGroupSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token this_RIGHT_PAREN_5=null;
        AntlrDatatypeRuleToken lv_features_4_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5337:2: ( (otherlv_0= 'xorGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) )
            // InternalFml.g:5338:2: (otherlv_0= 'xorGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:5338:2: (otherlv_0= 'xorGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            // InternalFml.g:5339:3: otherlv_0= 'xorGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN
            {
            otherlv_0=(Token)match(input,102,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getXorGroupSpecAccess().getXorGroupKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_59); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getXorGroupSpecAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:5347:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalFml.g:5348:4: (lv_name_2_0= RULE_ID )
            {
            // InternalFml.g:5348:4: (lv_name_2_0= RULE_ID )
            // InternalFml.g:5349:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_55); 

            					newLeafNode(lv_name_2_0, grammarAccess.getXorGroupSpecAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXorGroupSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,89,FOLLOW_37); 

            			newLeafNode(otherlv_3, grammarAccess.getXorGroupSpecAccess().getColonKeyword_3());
            		
            // InternalFml.g:5369:3: ( (lv_features_4_0= ruleFT_ID ) )+
            int cnt64=0;
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==RULE_STRING||LA64_0==RULE_ID||LA64_0==168) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalFml.g:5370:4: (lv_features_4_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:5370:4: (lv_features_4_0= ruleFT_ID )
            	    // InternalFml.g:5371:5: lv_features_4_0= ruleFT_ID
            	    {

            	    					newCompositeNode(grammarAccess.getXorGroupSpecAccess().getFeaturesFT_IDParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_60);
            	    lv_features_4_0=ruleFT_ID();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXorGroupSpecRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_4_0,
            	    						"org.xtext.example.mydsl.Fml.FT_ID");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt64 >= 1 ) break loop64;
                        EarlyExitException eee =
                            new EarlyExitException(64, input);
                        throw eee;
                }
                cnt64++;
            } while (true);

            this_RIGHT_PAREN_5=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_5, grammarAccess.getXorGroupSpecAccess().getRIGHT_PARENTerminalRuleCall_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXorGroupSpec"


    // $ANTLR start "entryRuleMtxGroupSpec"
    // InternalFml.g:5396:1: entryRuleMtxGroupSpec returns [EObject current=null] : iv_ruleMtxGroupSpec= ruleMtxGroupSpec EOF ;
    public final EObject entryRuleMtxGroupSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMtxGroupSpec = null;


        try {
            // InternalFml.g:5396:53: (iv_ruleMtxGroupSpec= ruleMtxGroupSpec EOF )
            // InternalFml.g:5397:2: iv_ruleMtxGroupSpec= ruleMtxGroupSpec EOF
            {
             newCompositeNode(grammarAccess.getMtxGroupSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMtxGroupSpec=ruleMtxGroupSpec();

            state._fsp--;

             current =iv_ruleMtxGroupSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMtxGroupSpec"


    // $ANTLR start "ruleMtxGroupSpec"
    // InternalFml.g:5403:1: ruleMtxGroupSpec returns [EObject current=null] : (otherlv_0= 'mtxGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) ;
    public final EObject ruleMtxGroupSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token this_RIGHT_PAREN_5=null;
        AntlrDatatypeRuleToken lv_features_4_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5409:2: ( (otherlv_0= 'mtxGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) )
            // InternalFml.g:5410:2: (otherlv_0= 'mtxGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:5410:2: (otherlv_0= 'mtxGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            // InternalFml.g:5411:3: otherlv_0= 'mtxGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN
            {
            otherlv_0=(Token)match(input,103,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getMtxGroupSpecAccess().getMtxGroupKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_59); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getMtxGroupSpecAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:5419:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalFml.g:5420:4: (lv_name_2_0= RULE_ID )
            {
            // InternalFml.g:5420:4: (lv_name_2_0= RULE_ID )
            // InternalFml.g:5421:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_55); 

            					newLeafNode(lv_name_2_0, grammarAccess.getMtxGroupSpecAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMtxGroupSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,89,FOLLOW_37); 

            			newLeafNode(otherlv_3, grammarAccess.getMtxGroupSpecAccess().getColonKeyword_3());
            		
            // InternalFml.g:5441:3: ( (lv_features_4_0= ruleFT_ID ) )+
            int cnt65=0;
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==RULE_STRING||LA65_0==RULE_ID||LA65_0==168) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalFml.g:5442:4: (lv_features_4_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:5442:4: (lv_features_4_0= ruleFT_ID )
            	    // InternalFml.g:5443:5: lv_features_4_0= ruleFT_ID
            	    {

            	    					newCompositeNode(grammarAccess.getMtxGroupSpecAccess().getFeaturesFT_IDParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_60);
            	    lv_features_4_0=ruleFT_ID();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMtxGroupSpecRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_4_0,
            	    						"org.xtext.example.mydsl.Fml.FT_ID");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt65 >= 1 ) break loop65;
                        EarlyExitException eee =
                            new EarlyExitException(65, input);
                        throw eee;
                }
                cnt65++;
            } while (true);

            this_RIGHT_PAREN_5=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_5, grammarAccess.getMtxGroupSpecAccess().getRIGHT_PARENTerminalRuleCall_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMtxGroupSpec"


    // $ANTLR start "entryRuleOrGroupSpec"
    // InternalFml.g:5468:1: entryRuleOrGroupSpec returns [EObject current=null] : iv_ruleOrGroupSpec= ruleOrGroupSpec EOF ;
    public final EObject entryRuleOrGroupSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrGroupSpec = null;


        try {
            // InternalFml.g:5468:52: (iv_ruleOrGroupSpec= ruleOrGroupSpec EOF )
            // InternalFml.g:5469:2: iv_ruleOrGroupSpec= ruleOrGroupSpec EOF
            {
             newCompositeNode(grammarAccess.getOrGroupSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrGroupSpec=ruleOrGroupSpec();

            state._fsp--;

             current =iv_ruleOrGroupSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrGroupSpec"


    // $ANTLR start "ruleOrGroupSpec"
    // InternalFml.g:5475:1: ruleOrGroupSpec returns [EObject current=null] : (otherlv_0= 'orGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) ;
    public final EObject ruleOrGroupSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token this_RIGHT_PAREN_5=null;
        AntlrDatatypeRuleToken lv_features_4_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5481:2: ( (otherlv_0= 'orGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN ) )
            // InternalFml.g:5482:2: (otherlv_0= 'orGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:5482:2: (otherlv_0= 'orGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN )
            // InternalFml.g:5483:3: otherlv_0= 'orGroup' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_features_4_0= ruleFT_ID ) )+ this_RIGHT_PAREN_5= RULE_RIGHT_PAREN
            {
            otherlv_0=(Token)match(input,104,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getOrGroupSpecAccess().getOrGroupKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_59); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getOrGroupSpecAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:5491:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalFml.g:5492:4: (lv_name_2_0= RULE_ID )
            {
            // InternalFml.g:5492:4: (lv_name_2_0= RULE_ID )
            // InternalFml.g:5493:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_55); 

            					newLeafNode(lv_name_2_0, grammarAccess.getOrGroupSpecAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOrGroupSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,89,FOLLOW_37); 

            			newLeafNode(otherlv_3, grammarAccess.getOrGroupSpecAccess().getColonKeyword_3());
            		
            // InternalFml.g:5513:3: ( (lv_features_4_0= ruleFT_ID ) )+
            int cnt66=0;
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_STRING||LA66_0==RULE_ID||LA66_0==168) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalFml.g:5514:4: (lv_features_4_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:5514:4: (lv_features_4_0= ruleFT_ID )
            	    // InternalFml.g:5515:5: lv_features_4_0= ruleFT_ID
            	    {

            	    					newCompositeNode(grammarAccess.getOrGroupSpecAccess().getFeaturesFT_IDParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_60);
            	    lv_features_4_0=ruleFT_ID();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOrGroupSpecRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_4_0,
            	    						"org.xtext.example.mydsl.Fml.FT_ID");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt66 >= 1 ) break loop66;
                        EarlyExitException eee =
                            new EarlyExitException(66, input);
                        throw eee;
                }
                cnt66++;
            } while (true);

            this_RIGHT_PAREN_5=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_5, grammarAccess.getOrGroupSpecAccess().getRIGHT_PARENTerminalRuleCall_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrGroupSpec"


    // $ANTLR start "entryRuleConstraintsSpecification"
    // InternalFml.g:5540:1: entryRuleConstraintsSpecification returns [EObject current=null] : iv_ruleConstraintsSpecification= ruleConstraintsSpecification EOF ;
    public final EObject entryRuleConstraintsSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintsSpecification = null;


        try {
            // InternalFml.g:5540:65: (iv_ruleConstraintsSpecification= ruleConstraintsSpecification EOF )
            // InternalFml.g:5541:2: iv_ruleConstraintsSpecification= ruleConstraintsSpecification EOF
            {
             newCompositeNode(grammarAccess.getConstraintsSpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintsSpecification=ruleConstraintsSpecification();

            state._fsp--;

             current =iv_ruleConstraintsSpecification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintsSpecification"


    // $ANTLR start "ruleConstraintsSpecification"
    // InternalFml.g:5547:1: ruleConstraintsSpecification returns [EObject current=null] : (otherlv_0= 'constraints=' ( (lv_csts_1_0= ruleConstraintExpr ) ) ) ;
    public final EObject ruleConstraintsSpecification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_csts_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5553:2: ( (otherlv_0= 'constraints=' ( (lv_csts_1_0= ruleConstraintExpr ) ) ) )
            // InternalFml.g:5554:2: (otherlv_0= 'constraints=' ( (lv_csts_1_0= ruleConstraintExpr ) ) )
            {
            // InternalFml.g:5554:2: (otherlv_0= 'constraints=' ( (lv_csts_1_0= ruleConstraintExpr ) ) )
            // InternalFml.g:5555:3: otherlv_0= 'constraints=' ( (lv_csts_1_0= ruleConstraintExpr ) )
            {
            otherlv_0=(Token)match(input,105,FOLLOW_61); 

            			newLeafNode(otherlv_0, grammarAccess.getConstraintsSpecificationAccess().getConstraintsKeyword_0());
            		
            // InternalFml.g:5559:3: ( (lv_csts_1_0= ruleConstraintExpr ) )
            // InternalFml.g:5560:4: (lv_csts_1_0= ruleConstraintExpr )
            {
            // InternalFml.g:5560:4: (lv_csts_1_0= ruleConstraintExpr )
            // InternalFml.g:5561:5: lv_csts_1_0= ruleConstraintExpr
            {

            					newCompositeNode(grammarAccess.getConstraintsSpecificationAccess().getCstsConstraintExprParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_csts_1_0=ruleConstraintExpr();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstraintsSpecificationRule());
            					}
            					set(
            						current,
            						"csts",
            						lv_csts_1_0,
            						"org.xtext.example.mydsl.Fml.ConstraintExpr");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintsSpecification"


    // $ANTLR start "entryRuleSlice"
    // InternalFml.g:5582:1: entryRuleSlice returns [EObject current=null] : iv_ruleSlice= ruleSlice EOF ;
    public final EObject entryRuleSlice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSlice = null;


        try {
            // InternalFml.g:5582:46: (iv_ruleSlice= ruleSlice EOF )
            // InternalFml.g:5583:2: iv_ruleSlice= ruleSlice EOF
            {
             newCompositeNode(grammarAccess.getSliceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSlice=ruleSlice();

            state._fsp--;

             current =iv_ruleSlice; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSlice"


    // $ANTLR start "ruleSlice"
    // InternalFml.g:5589:1: ruleSlice returns [EObject current=null] : (otherlv_0= 'slice' ( (lv_fm_1_0= ruleFMCommand ) ) ( (lv_mode_2_0= ruleSliceMode ) ) ( (lv_fts_3_0= ruleSetCommand ) ) ) ;
    public final EObject ruleSlice() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;

        Enumerator lv_mode_2_0 = null;

        EObject lv_fts_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5595:2: ( (otherlv_0= 'slice' ( (lv_fm_1_0= ruleFMCommand ) ) ( (lv_mode_2_0= ruleSliceMode ) ) ( (lv_fts_3_0= ruleSetCommand ) ) ) )
            // InternalFml.g:5596:2: (otherlv_0= 'slice' ( (lv_fm_1_0= ruleFMCommand ) ) ( (lv_mode_2_0= ruleSliceMode ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:5596:2: (otherlv_0= 'slice' ( (lv_fm_1_0= ruleFMCommand ) ) ( (lv_mode_2_0= ruleSliceMode ) ) ( (lv_fts_3_0= ruleSetCommand ) ) )
            // InternalFml.g:5597:3: otherlv_0= 'slice' ( (lv_fm_1_0= ruleFMCommand ) ) ( (lv_mode_2_0= ruleSliceMode ) ) ( (lv_fts_3_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,106,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getSliceAccess().getSliceKeyword_0());
            		
            // InternalFml.g:5601:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:5602:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:5602:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:5603:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getSliceAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_62);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSliceRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:5620:3: ( (lv_mode_2_0= ruleSliceMode ) )
            // InternalFml.g:5621:4: (lv_mode_2_0= ruleSliceMode )
            {
            // InternalFml.g:5621:4: (lv_mode_2_0= ruleSliceMode )
            // InternalFml.g:5622:5: lv_mode_2_0= ruleSliceMode
            {

            					newCompositeNode(grammarAccess.getSliceAccess().getModeSliceModeEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_29);
            lv_mode_2_0=ruleSliceMode();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSliceRule());
            					}
            					set(
            						current,
            						"mode",
            						lv_mode_2_0,
            						"org.xtext.example.mydsl.Fml.SliceMode");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:5639:3: ( (lv_fts_3_0= ruleSetCommand ) )
            // InternalFml.g:5640:4: (lv_fts_3_0= ruleSetCommand )
            {
            // InternalFml.g:5640:4: (lv_fts_3_0= ruleSetCommand )
            // InternalFml.g:5641:5: lv_fts_3_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getSliceAccess().getFtsSetCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_fts_3_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSliceRule());
            					}
            					set(
            						current,
            						"fts",
            						lv_fts_3_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSlice"


    // $ANTLR start "entryRuleAggregate"
    // InternalFml.g:5662:1: entryRuleAggregate returns [EObject current=null] : iv_ruleAggregate= ruleAggregate EOF ;
    public final EObject entryRuleAggregate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAggregate = null;


        try {
            // InternalFml.g:5662:50: (iv_ruleAggregate= ruleAggregate EOF )
            // InternalFml.g:5663:2: iv_ruleAggregate= ruleAggregate EOF
            {
             newCompositeNode(grammarAccess.getAggregateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAggregate=ruleAggregate();

            state._fsp--;

             current =iv_ruleAggregate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAggregate"


    // $ANTLR start "ruleAggregate"
    // InternalFml.g:5669:1: ruleAggregate returns [EObject current=null] : (otherlv_0= 'aggregate' ( (lv_renamings_1_0= '--renamings' ) )? ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) ) (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )? ) ;
    public final EObject ruleAggregate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_renamings_1_0=null;
        Token this_LEFT_BRACKET_2=null;
        Token this_RIGHT_BRACKET_4=null;
        Token otherlv_6=null;
        EObject lv_fms_3_0 = null;

        EObject lv_sfms_5_0 = null;

        EObject lv_mapping_7_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5675:2: ( (otherlv_0= 'aggregate' ( (lv_renamings_1_0= '--renamings' ) )? ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) ) (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )? ) )
            // InternalFml.g:5676:2: (otherlv_0= 'aggregate' ( (lv_renamings_1_0= '--renamings' ) )? ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) ) (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )? )
            {
            // InternalFml.g:5676:2: (otherlv_0= 'aggregate' ( (lv_renamings_1_0= '--renamings' ) )? ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) ) (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )? )
            // InternalFml.g:5677:3: otherlv_0= 'aggregate' ( (lv_renamings_1_0= '--renamings' ) )? ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) ) (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )?
            {
            otherlv_0=(Token)match(input,107,FOLLOW_63); 

            			newLeafNode(otherlv_0, grammarAccess.getAggregateAccess().getAggregateKeyword_0());
            		
            // InternalFml.g:5681:3: ( (lv_renamings_1_0= '--renamings' ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==108) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalFml.g:5682:4: (lv_renamings_1_0= '--renamings' )
                    {
                    // InternalFml.g:5682:4: (lv_renamings_1_0= '--renamings' )
                    // InternalFml.g:5683:5: lv_renamings_1_0= '--renamings'
                    {
                    lv_renamings_1_0=(Token)match(input,108,FOLLOW_64); 

                    					newLeafNode(lv_renamings_1_0, grammarAccess.getAggregateAccess().getRenamingsRenamingsKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAggregateRule());
                    					}
                    					setWithLastConsumed(current, "renamings", true, "--renamings");
                    				

                    }


                    }
                    break;

            }

            // InternalFml.g:5695:3: ( (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET ) | ( (lv_sfms_5_0= ruleIdentifierExpr ) ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==RULE_LEFT_BRACKET) ) {
                alt69=1;
            }
            else if ( (LA69_0==RULE_ID||LA69_0==168) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // InternalFml.g:5696:4: (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
                    {
                    // InternalFml.g:5696:4: (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )
                    // InternalFml.g:5697:5: this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_fms_3_0= ruleFMCommand ) )+ this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET
                    {
                    this_LEFT_BRACKET_2=(Token)match(input,RULE_LEFT_BRACKET,FOLLOW_19); 

                    					newLeafNode(this_LEFT_BRACKET_2, grammarAccess.getAggregateAccess().getLEFT_BRACKETTerminalRuleCall_2_0_0());
                    				
                    // InternalFml.g:5701:5: ( (lv_fms_3_0= ruleFMCommand ) )+
                    int cnt68=0;
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==RULE_ID||LA68_0==80||LA68_0==93||LA68_0==95||LA68_0==97||(LA68_0>=106 && LA68_0<=107)||LA68_0==114||LA68_0==128||(LA68_0>=145 && LA68_0<=146)||LA68_0==152||(LA68_0>=165 && LA68_0<=166)||LA68_0==168) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // InternalFml.g:5702:6: (lv_fms_3_0= ruleFMCommand )
                    	    {
                    	    // InternalFml.g:5702:6: (lv_fms_3_0= ruleFMCommand )
                    	    // InternalFml.g:5703:7: lv_fms_3_0= ruleFMCommand
                    	    {

                    	    							newCompositeNode(grammarAccess.getAggregateAccess().getFmsFMCommandParserRuleCall_2_0_1_0());
                    	    						
                    	    pushFollow(FOLLOW_43);
                    	    lv_fms_3_0=ruleFMCommand();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getAggregateRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"fms",
                    	    								lv_fms_3_0,
                    	    								"org.xtext.example.mydsl.Fml.FMCommand");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt68 >= 1 ) break loop68;
                                EarlyExitException eee =
                                    new EarlyExitException(68, input);
                                throw eee;
                        }
                        cnt68++;
                    } while (true);

                    this_RIGHT_BRACKET_4=(Token)match(input,RULE_RIGHT_BRACKET,FOLLOW_65); 

                    					newLeafNode(this_RIGHT_BRACKET_4, grammarAccess.getAggregateAccess().getRIGHT_BRACKETTerminalRuleCall_2_0_2());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:5726:4: ( (lv_sfms_5_0= ruleIdentifierExpr ) )
                    {
                    // InternalFml.g:5726:4: ( (lv_sfms_5_0= ruleIdentifierExpr ) )
                    // InternalFml.g:5727:5: (lv_sfms_5_0= ruleIdentifierExpr )
                    {
                    // InternalFml.g:5727:5: (lv_sfms_5_0= ruleIdentifierExpr )
                    // InternalFml.g:5728:6: lv_sfms_5_0= ruleIdentifierExpr
                    {

                    						newCompositeNode(grammarAccess.getAggregateAccess().getSfmsIdentifierExprParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_65);
                    lv_sfms_5_0=ruleIdentifierExpr();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAggregateRule());
                    						}
                    						set(
                    							current,
                    							"sfms",
                    							lv_sfms_5_0,
                    							"org.xtext.example.mydsl.Fml.IdentifierExpr");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalFml.g:5746:3: (otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==109) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalFml.g:5747:4: otherlv_6= 'withMapping' ( (lv_mapping_7_0= ruleSetCommand ) )
                    {
                    otherlv_6=(Token)match(input,109,FOLLOW_29); 

                    				newLeafNode(otherlv_6, grammarAccess.getAggregateAccess().getWithMappingKeyword_3_0());
                    			
                    // InternalFml.g:5751:4: ( (lv_mapping_7_0= ruleSetCommand ) )
                    // InternalFml.g:5752:5: (lv_mapping_7_0= ruleSetCommand )
                    {
                    // InternalFml.g:5752:5: (lv_mapping_7_0= ruleSetCommand )
                    // InternalFml.g:5753:6: lv_mapping_7_0= ruleSetCommand
                    {

                    						newCompositeNode(grammarAccess.getAggregateAccess().getMappingSetCommandParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_mapping_7_0=ruleSetCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAggregateRule());
                    						}
                    						set(
                    							current,
                    							"mapping",
                    							lv_mapping_7_0,
                    							"org.xtext.example.mydsl.Fml.SetCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAggregate"


    // $ANTLR start "entryRuleFeatureModelOperation"
    // InternalFml.g:5775:1: entryRuleFeatureModelOperation returns [EObject current=null] : iv_ruleFeatureModelOperation= ruleFeatureModelOperation EOF ;
    public final EObject entryRuleFeatureModelOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureModelOperation = null;


        try {
            // InternalFml.g:5775:62: (iv_ruleFeatureModelOperation= ruleFeatureModelOperation EOF )
            // InternalFml.g:5776:2: iv_ruleFeatureModelOperation= ruleFeatureModelOperation EOF
            {
             newCompositeNode(grammarAccess.getFeatureModelOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureModelOperation=ruleFeatureModelOperation();

            state._fsp--;

             current =iv_ruleFeatureModelOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureModelOperation"


    // $ANTLR start "ruleFeatureModelOperation"
    // InternalFml.g:5782:1: ruleFeatureModelOperation returns [EObject current=null] : (this_Insert_0= ruleInsert | this_EditOperation_1= ruleEditOperation | this_Extract_2= ruleExtract ) ;
    public final EObject ruleFeatureModelOperation() throws RecognitionException {
        EObject current = null;

        EObject this_Insert_0 = null;

        EObject this_EditOperation_1 = null;

        EObject this_Extract_2 = null;



        	enterRule();

        try {
            // InternalFml.g:5788:2: ( (this_Insert_0= ruleInsert | this_EditOperation_1= ruleEditOperation | this_Extract_2= ruleExtract ) )
            // InternalFml.g:5789:2: (this_Insert_0= ruleInsert | this_EditOperation_1= ruleEditOperation | this_Extract_2= ruleExtract )
            {
            // InternalFml.g:5789:2: (this_Insert_0= ruleInsert | this_EditOperation_1= ruleEditOperation | this_Extract_2= ruleExtract )
            int alt71=3;
            switch ( input.LA(1) ) {
            case 110:
                {
                alt71=1;
                }
                break;
            case 111:
            case 112:
                {
                alt71=2;
                }
                break;
            case 114:
                {
                alt71=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // InternalFml.g:5790:3: this_Insert_0= ruleInsert
                    {

                    			newCompositeNode(grammarAccess.getFeatureModelOperationAccess().getInsertParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Insert_0=ruleInsert();

                    state._fsp--;


                    			current = this_Insert_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:5799:3: this_EditOperation_1= ruleEditOperation
                    {

                    			newCompositeNode(grammarAccess.getFeatureModelOperationAccess().getEditOperationParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_EditOperation_1=ruleEditOperation();

                    state._fsp--;


                    			current = this_EditOperation_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:5808:3: this_Extract_2= ruleExtract
                    {

                    			newCompositeNode(grammarAccess.getFeatureModelOperationAccess().getExtractParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Extract_2=ruleExtract();

                    state._fsp--;


                    			current = this_Extract_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureModelOperation"


    // $ANTLR start "entryRuleEditOperation"
    // InternalFml.g:5820:1: entryRuleEditOperation returns [EObject current=null] : iv_ruleEditOperation= ruleEditOperation EOF ;
    public final EObject entryRuleEditOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEditOperation = null;


        try {
            // InternalFml.g:5820:54: (iv_ruleEditOperation= ruleEditOperation EOF )
            // InternalFml.g:5821:2: iv_ruleEditOperation= ruleEditOperation EOF
            {
             newCompositeNode(grammarAccess.getEditOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEditOperation=ruleEditOperation();

            state._fsp--;

             current =iv_ruleEditOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEditOperation"


    // $ANTLR start "ruleEditOperation"
    // InternalFml.g:5827:1: ruleEditOperation returns [EObject current=null] : (this_RemoveFeature_0= ruleRemoveFeature | this_RenameFeature_1= ruleRenameFeature ) ;
    public final EObject ruleEditOperation() throws RecognitionException {
        EObject current = null;

        EObject this_RemoveFeature_0 = null;

        EObject this_RenameFeature_1 = null;



        	enterRule();

        try {
            // InternalFml.g:5833:2: ( (this_RemoveFeature_0= ruleRemoveFeature | this_RenameFeature_1= ruleRenameFeature ) )
            // InternalFml.g:5834:2: (this_RemoveFeature_0= ruleRemoveFeature | this_RenameFeature_1= ruleRenameFeature )
            {
            // InternalFml.g:5834:2: (this_RemoveFeature_0= ruleRemoveFeature | this_RenameFeature_1= ruleRenameFeature )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==111) ) {
                alt72=1;
            }
            else if ( (LA72_0==112) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalFml.g:5835:3: this_RemoveFeature_0= ruleRemoveFeature
                    {

                    			newCompositeNode(grammarAccess.getEditOperationAccess().getRemoveFeatureParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_RemoveFeature_0=ruleRemoveFeature();

                    state._fsp--;


                    			current = this_RemoveFeature_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:5844:3: this_RenameFeature_1= ruleRenameFeature
                    {

                    			newCompositeNode(grammarAccess.getEditOperationAccess().getRenameFeatureParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_RenameFeature_1=ruleRenameFeature();

                    state._fsp--;


                    			current = this_RenameFeature_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEditOperation"


    // $ANTLR start "entryRuleInsert"
    // InternalFml.g:5856:1: entryRuleInsert returns [EObject current=null] : iv_ruleInsert= ruleInsert EOF ;
    public final EObject entryRuleInsert() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInsert = null;


        try {
            // InternalFml.g:5856:47: (iv_ruleInsert= ruleInsert EOF )
            // InternalFml.g:5857:2: iv_ruleInsert= ruleInsert EOF
            {
             newCompositeNode(grammarAccess.getInsertRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInsert=ruleInsert();

            state._fsp--;

             current =iv_ruleInsert; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInsert"


    // $ANTLR start "ruleInsert"
    // InternalFml.g:5863:1: ruleInsert returns [EObject current=null] : (otherlv_0= 'insert' ( (lv_aspectfm_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_baseft_3_0= ruleFTCommand ) ) otherlv_4= 'with' ( (lv_op_5_0= ruleVariabilityOpCommand ) ) ) ;
    public final EObject ruleInsert() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_aspectfm_1_0 = null;

        EObject lv_baseft_3_0 = null;

        EObject lv_op_5_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5869:2: ( (otherlv_0= 'insert' ( (lv_aspectfm_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_baseft_3_0= ruleFTCommand ) ) otherlv_4= 'with' ( (lv_op_5_0= ruleVariabilityOpCommand ) ) ) )
            // InternalFml.g:5870:2: (otherlv_0= 'insert' ( (lv_aspectfm_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_baseft_3_0= ruleFTCommand ) ) otherlv_4= 'with' ( (lv_op_5_0= ruleVariabilityOpCommand ) ) )
            {
            // InternalFml.g:5870:2: (otherlv_0= 'insert' ( (lv_aspectfm_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_baseft_3_0= ruleFTCommand ) ) otherlv_4= 'with' ( (lv_op_5_0= ruleVariabilityOpCommand ) ) )
            // InternalFml.g:5871:3: otherlv_0= 'insert' ( (lv_aspectfm_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_baseft_3_0= ruleFTCommand ) ) otherlv_4= 'with' ( (lv_op_5_0= ruleVariabilityOpCommand ) )
            {
            otherlv_0=(Token)match(input,110,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getInsertAccess().getInsertKeyword_0());
            		
            // InternalFml.g:5875:3: ( (lv_aspectfm_1_0= ruleFMCommand ) )
            // InternalFml.g:5876:4: (lv_aspectfm_1_0= ruleFMCommand )
            {
            // InternalFml.g:5876:4: (lv_aspectfm_1_0= ruleFMCommand )
            // InternalFml.g:5877:5: lv_aspectfm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getInsertAccess().getAspectfmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_66);
            lv_aspectfm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInsertRule());
            					}
            					set(
            						current,
            						"aspectfm",
            						lv_aspectfm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,91,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getInsertAccess().getIntoKeyword_2());
            		
            // InternalFml.g:5898:3: ( (lv_baseft_3_0= ruleFTCommand ) )
            // InternalFml.g:5899:4: (lv_baseft_3_0= ruleFTCommand )
            {
            // InternalFml.g:5899:4: (lv_baseft_3_0= ruleFTCommand )
            // InternalFml.g:5900:5: lv_baseft_3_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getInsertAccess().getBaseftFTCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_67);
            lv_baseft_3_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInsertRule());
            					}
            					set(
            						current,
            						"baseft",
            						lv_baseft_3_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,99,FOLLOW_68); 

            			newLeafNode(otherlv_4, grammarAccess.getInsertAccess().getWithKeyword_4());
            		
            // InternalFml.g:5921:3: ( (lv_op_5_0= ruleVariabilityOpCommand ) )
            // InternalFml.g:5922:4: (lv_op_5_0= ruleVariabilityOpCommand )
            {
            // InternalFml.g:5922:4: (lv_op_5_0= ruleVariabilityOpCommand )
            // InternalFml.g:5923:5: lv_op_5_0= ruleVariabilityOpCommand
            {

            					newCompositeNode(grammarAccess.getInsertAccess().getOpVariabilityOpCommandParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_2);
            lv_op_5_0=ruleVariabilityOpCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInsertRule());
            					}
            					set(
            						current,
            						"op",
            						lv_op_5_0,
            						"org.xtext.example.mydsl.Fml.VariabilityOpCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInsert"


    // $ANTLR start "entryRuleRemoveFeature"
    // InternalFml.g:5944:1: entryRuleRemoveFeature returns [EObject current=null] : iv_ruleRemoveFeature= ruleRemoveFeature EOF ;
    public final EObject entryRuleRemoveFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRemoveFeature = null;


        try {
            // InternalFml.g:5944:54: (iv_ruleRemoveFeature= ruleRemoveFeature EOF )
            // InternalFml.g:5945:2: iv_ruleRemoveFeature= ruleRemoveFeature EOF
            {
             newCompositeNode(grammarAccess.getRemoveFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRemoveFeature=ruleRemoveFeature();

            state._fsp--;

             current =iv_ruleRemoveFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRemoveFeature"


    // $ANTLR start "ruleRemoveFeature"
    // InternalFml.g:5951:1: ruleRemoveFeature returns [EObject current=null] : (otherlv_0= 'removeFeature' ( (lv_feature_1_0= ruleFTCommand ) ) ) ;
    public final EObject ruleRemoveFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_feature_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5957:2: ( (otherlv_0= 'removeFeature' ( (lv_feature_1_0= ruleFTCommand ) ) ) )
            // InternalFml.g:5958:2: (otherlv_0= 'removeFeature' ( (lv_feature_1_0= ruleFTCommand ) ) )
            {
            // InternalFml.g:5958:2: (otherlv_0= 'removeFeature' ( (lv_feature_1_0= ruleFTCommand ) ) )
            // InternalFml.g:5959:3: otherlv_0= 'removeFeature' ( (lv_feature_1_0= ruleFTCommand ) )
            {
            otherlv_0=(Token)match(input,111,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getRemoveFeatureAccess().getRemoveFeatureKeyword_0());
            		
            // InternalFml.g:5963:3: ( (lv_feature_1_0= ruleFTCommand ) )
            // InternalFml.g:5964:4: (lv_feature_1_0= ruleFTCommand )
            {
            // InternalFml.g:5964:4: (lv_feature_1_0= ruleFTCommand )
            // InternalFml.g:5965:5: lv_feature_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getRemoveFeatureAccess().getFeatureFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_feature_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRemoveFeatureRule());
            					}
            					set(
            						current,
            						"feature",
            						lv_feature_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRemoveFeature"


    // $ANTLR start "entryRuleRenameFeature"
    // InternalFml.g:5986:1: entryRuleRenameFeature returns [EObject current=null] : iv_ruleRenameFeature= ruleRenameFeature EOF ;
    public final EObject entryRuleRenameFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRenameFeature = null;


        try {
            // InternalFml.g:5986:54: (iv_ruleRenameFeature= ruleRenameFeature EOF )
            // InternalFml.g:5987:2: iv_ruleRenameFeature= ruleRenameFeature EOF
            {
             newCompositeNode(grammarAccess.getRenameFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRenameFeature=ruleRenameFeature();

            state._fsp--;

             current =iv_ruleRenameFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRenameFeature"


    // $ANTLR start "ruleRenameFeature"
    // InternalFml.g:5993:1: ruleRenameFeature returns [EObject current=null] : (otherlv_0= 'renameFeature' ( (lv_feature_1_0= ruleFTCommand ) ) otherlv_2= 'as' ( (lv_featureNew_3_0= ruleStrCommand ) ) ) ;
    public final EObject ruleRenameFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_feature_1_0 = null;

        EObject lv_featureNew_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:5999:2: ( (otherlv_0= 'renameFeature' ( (lv_feature_1_0= ruleFTCommand ) ) otherlv_2= 'as' ( (lv_featureNew_3_0= ruleStrCommand ) ) ) )
            // InternalFml.g:6000:2: (otherlv_0= 'renameFeature' ( (lv_feature_1_0= ruleFTCommand ) ) otherlv_2= 'as' ( (lv_featureNew_3_0= ruleStrCommand ) ) )
            {
            // InternalFml.g:6000:2: (otherlv_0= 'renameFeature' ( (lv_feature_1_0= ruleFTCommand ) ) otherlv_2= 'as' ( (lv_featureNew_3_0= ruleStrCommand ) ) )
            // InternalFml.g:6001:3: otherlv_0= 'renameFeature' ( (lv_feature_1_0= ruleFTCommand ) ) otherlv_2= 'as' ( (lv_featureNew_3_0= ruleStrCommand ) )
            {
            otherlv_0=(Token)match(input,112,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getRenameFeatureAccess().getRenameFeatureKeyword_0());
            		
            // InternalFml.g:6005:3: ( (lv_feature_1_0= ruleFTCommand ) )
            // InternalFml.g:6006:4: (lv_feature_1_0= ruleFTCommand )
            {
            // InternalFml.g:6006:4: (lv_feature_1_0= ruleFTCommand )
            // InternalFml.g:6007:5: lv_feature_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getRenameFeatureAccess().getFeatureFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_69);
            lv_feature_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRenameFeatureRule());
            					}
            					set(
            						current,
            						"feature",
            						lv_feature_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,113,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getRenameFeatureAccess().getAsKeyword_2());
            		
            // InternalFml.g:6028:3: ( (lv_featureNew_3_0= ruleStrCommand ) )
            // InternalFml.g:6029:4: (lv_featureNew_3_0= ruleStrCommand )
            {
            // InternalFml.g:6029:4: (lv_featureNew_3_0= ruleStrCommand )
            // InternalFml.g:6030:5: lv_featureNew_3_0= ruleStrCommand
            {

            					newCompositeNode(grammarAccess.getRenameFeatureAccess().getFeatureNewStrCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_featureNew_3_0=ruleStrCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRenameFeatureRule());
            					}
            					set(
            						current,
            						"featureNew",
            						lv_featureNew_3_0,
            						"org.xtext.example.mydsl.Fml.StrCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRenameFeature"


    // $ANTLR start "entryRuleExtract"
    // InternalFml.g:6051:1: entryRuleExtract returns [EObject current=null] : iv_ruleExtract= ruleExtract EOF ;
    public final EObject entryRuleExtract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtract = null;


        try {
            // InternalFml.g:6051:48: (iv_ruleExtract= ruleExtract EOF )
            // InternalFml.g:6052:2: iv_ruleExtract= ruleExtract EOF
            {
             newCompositeNode(grammarAccess.getExtractRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtract=ruleExtract();

            state._fsp--;

             current =iv_ruleExtract; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtract"


    // $ANTLR start "ruleExtract"
    // InternalFml.g:6058:1: ruleExtract returns [EObject current=null] : (otherlv_0= 'extract' ( (lv_rootfeature_1_0= ruleFTCommand ) ) ) ;
    public final EObject ruleExtract() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_rootfeature_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6064:2: ( (otherlv_0= 'extract' ( (lv_rootfeature_1_0= ruleFTCommand ) ) ) )
            // InternalFml.g:6065:2: (otherlv_0= 'extract' ( (lv_rootfeature_1_0= ruleFTCommand ) ) )
            {
            // InternalFml.g:6065:2: (otherlv_0= 'extract' ( (lv_rootfeature_1_0= ruleFTCommand ) ) )
            // InternalFml.g:6066:3: otherlv_0= 'extract' ( (lv_rootfeature_1_0= ruleFTCommand ) )
            {
            otherlv_0=(Token)match(input,114,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getExtractAccess().getExtractKeyword_0());
            		
            // InternalFml.g:6070:3: ( (lv_rootfeature_1_0= ruleFTCommand ) )
            // InternalFml.g:6071:4: (lv_rootfeature_1_0= ruleFTCommand )
            {
            // InternalFml.g:6071:4: (lv_rootfeature_1_0= ruleFTCommand )
            // InternalFml.g:6072:5: lv_rootfeature_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getExtractAccess().getRootfeatureFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_rootfeature_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExtractRule());
            					}
            					set(
            						current,
            						"rootfeature",
            						lv_rootfeature_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtract"


    // $ANTLR start "entryRuleAssertion"
    // InternalFml.g:6093:1: entryRuleAssertion returns [EObject current=null] : iv_ruleAssertion= ruleAssertion EOF ;
    public final EObject entryRuleAssertion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssertion = null;


        try {
            // InternalFml.g:6093:50: (iv_ruleAssertion= ruleAssertion EOF )
            // InternalFml.g:6094:2: iv_ruleAssertion= ruleAssertion EOF
            {
             newCompositeNode(grammarAccess.getAssertionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssertion=ruleAssertion();

            state._fsp--;

             current =iv_ruleAssertion; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssertion"


    // $ANTLR start "ruleAssertion"
    // InternalFml.g:6100:1: ruleAssertion returns [EObject current=null] : (otherlv_0= 'assert' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_assertion_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) ;
    public final EObject ruleAssertion() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token this_LEFT_PAREN_1=null;
        Token this_RIGHT_PAREN_3=null;
        EObject lv_assertion_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6106:2: ( (otherlv_0= 'assert' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_assertion_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) )
            // InternalFml.g:6107:2: (otherlv_0= 'assert' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_assertion_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:6107:2: (otherlv_0= 'assert' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_assertion_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
            // InternalFml.g:6108:3: otherlv_0= 'assert' this_LEFT_PAREN_1= RULE_LEFT_PAREN ( (lv_assertion_2_0= ruleComplexCommand ) ) this_RIGHT_PAREN_3= RULE_RIGHT_PAREN
            {
            otherlv_0=(Token)match(input,115,FOLLOW_17); 

            			newLeafNode(otherlv_0, grammarAccess.getAssertionAccess().getAssertKeyword_0());
            		
            this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_11); 

            			newLeafNode(this_LEFT_PAREN_1, grammarAccess.getAssertionAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:6116:3: ( (lv_assertion_2_0= ruleComplexCommand ) )
            // InternalFml.g:6117:4: (lv_assertion_2_0= ruleComplexCommand )
            {
            // InternalFml.g:6117:4: (lv_assertion_2_0= ruleComplexCommand )
            // InternalFml.g:6118:5: lv_assertion_2_0= ruleComplexCommand
            {

            					newCompositeNode(grammarAccess.getAssertionAccess().getAssertionComplexCommandParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_14);
            lv_assertion_2_0=ruleComplexCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssertionRule());
            					}
            					set(
            						current,
            						"assertion",
            						lv_assertion_2_0,
            						"org.xtext.example.mydsl.Fml.ComplexCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            this_RIGHT_PAREN_3=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_3, grammarAccess.getAssertionAccess().getRIGHT_PARENTerminalRuleCall_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssertion"


    // $ANTLR start "entryRuleVariableNull"
    // InternalFml.g:6143:1: entryRuleVariableNull returns [EObject current=null] : iv_ruleVariableNull= ruleVariableNull EOF ;
    public final EObject entryRuleVariableNull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableNull = null;


        try {
            // InternalFml.g:6143:53: (iv_ruleVariableNull= ruleVariableNull EOF )
            // InternalFml.g:6144:2: iv_ruleVariableNull= ruleVariableNull EOF
            {
             newCompositeNode(grammarAccess.getVariableNullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableNull=ruleVariableNull();

            state._fsp--;

             current =iv_ruleVariableNull; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableNull"


    // $ANTLR start "ruleVariableNull"
    // InternalFml.g:6150:1: ruleVariableNull returns [EObject current=null] : (otherlv_0= 'isNull' ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleVariableNull() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_var_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6156:2: ( (otherlv_0= 'isNull' ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:6157:2: (otherlv_0= 'isNull' ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:6157:2: (otherlv_0= 'isNull' ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:6158:3: otherlv_0= 'isNull' ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            {
            otherlv_0=(Token)match(input,116,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getVariableNullAccess().getIsNullKeyword_0());
            		
            // InternalFml.g:6162:3: ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:6163:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:6163:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            // InternalFml.g:6164:5: lv_var_1_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getVariableNullAccess().getVarFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_var_1_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableNullRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_1_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableNull"


    // $ANTLR start "entryRuleExport"
    // InternalFml.g:6185:1: entryRuleExport returns [EObject current=null] : iv_ruleExport= ruleExport EOF ;
    public final EObject entryRuleExport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExport = null;


        try {
            // InternalFml.g:6185:47: (iv_ruleExport= ruleExport EOF )
            // InternalFml.g:6186:2: iv_ruleExport= ruleExport EOF
            {
             newCompositeNode(grammarAccess.getExportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExport=ruleExport();

            state._fsp--;

             current =iv_ruleExport; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExport"


    // $ANTLR start "ruleExport"
    // InternalFml.g:6192:1: ruleExport returns [EObject current=null] : (otherlv_0= 'export' ( (lv_arg_1_0= ruleLVidentifier ) ) ) ;
    public final EObject ruleExport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_arg_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6198:2: ( (otherlv_0= 'export' ( (lv_arg_1_0= ruleLVidentifier ) ) ) )
            // InternalFml.g:6199:2: (otherlv_0= 'export' ( (lv_arg_1_0= ruleLVidentifier ) ) )
            {
            // InternalFml.g:6199:2: (otherlv_0= 'export' ( (lv_arg_1_0= ruleLVidentifier ) ) )
            // InternalFml.g:6200:3: otherlv_0= 'export' ( (lv_arg_1_0= ruleLVidentifier ) )
            {
            otherlv_0=(Token)match(input,117,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getExportAccess().getExportKeyword_0());
            		
            // InternalFml.g:6204:3: ( (lv_arg_1_0= ruleLVidentifier ) )
            // InternalFml.g:6205:4: (lv_arg_1_0= ruleLVidentifier )
            {
            // InternalFml.g:6205:4: (lv_arg_1_0= ruleLVidentifier )
            // InternalFml.g:6206:5: lv_arg_1_0= ruleLVidentifier
            {

            					newCompositeNode(grammarAccess.getExportAccess().getArgLVidentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_arg_1_0=ruleLVidentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExportRule());
            					}
            					set(
            						current,
            						"arg",
            						lv_arg_1_0,
            						"org.xtext.example.mydsl.Fml.LVidentifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExport"


    // $ANTLR start "entryRuleHidden"
    // InternalFml.g:6227:1: entryRuleHidden returns [EObject current=null] : iv_ruleHidden= ruleHidden EOF ;
    public final EObject entryRuleHidden() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHidden = null;


        try {
            // InternalFml.g:6227:47: (iv_ruleHidden= ruleHidden EOF )
            // InternalFml.g:6228:2: iv_ruleHidden= ruleHidden EOF
            {
             newCompositeNode(grammarAccess.getHiddenRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHidden=ruleHidden();

            state._fsp--;

             current =iv_ruleHidden; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHidden"


    // $ANTLR start "ruleHidden"
    // InternalFml.g:6234:1: ruleHidden returns [EObject current=null] : (otherlv_0= 'hide' ( (lv_arg_1_0= ruleLVidentifier ) ) ) ;
    public final EObject ruleHidden() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_arg_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6240:2: ( (otherlv_0= 'hide' ( (lv_arg_1_0= ruleLVidentifier ) ) ) )
            // InternalFml.g:6241:2: (otherlv_0= 'hide' ( (lv_arg_1_0= ruleLVidentifier ) ) )
            {
            // InternalFml.g:6241:2: (otherlv_0= 'hide' ( (lv_arg_1_0= ruleLVidentifier ) ) )
            // InternalFml.g:6242:3: otherlv_0= 'hide' ( (lv_arg_1_0= ruleLVidentifier ) )
            {
            otherlv_0=(Token)match(input,118,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getHiddenAccess().getHideKeyword_0());
            		
            // InternalFml.g:6246:3: ( (lv_arg_1_0= ruleLVidentifier ) )
            // InternalFml.g:6247:4: (lv_arg_1_0= ruleLVidentifier )
            {
            // InternalFml.g:6247:4: (lv_arg_1_0= ruleLVidentifier )
            // InternalFml.g:6248:5: lv_arg_1_0= ruleLVidentifier
            {

            					newCompositeNode(grammarAccess.getHiddenAccess().getArgLVidentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_arg_1_0=ruleLVidentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getHiddenRule());
            					}
            					set(
            						current,
            						"arg",
            						lv_arg_1_0,
            						"org.xtext.example.mydsl.Fml.LVidentifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHidden"


    // $ANTLR start "entryRuleLVidentifier"
    // InternalFml.g:6269:1: entryRuleLVidentifier returns [EObject current=null] : iv_ruleLVidentifier= ruleLVidentifier EOF ;
    public final EObject entryRuleLVidentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLVidentifier = null;


        try {
            // InternalFml.g:6269:53: (iv_ruleLVidentifier= ruleLVidentifier EOF )
            // InternalFml.g:6270:2: iv_ruleLVidentifier= ruleLVidentifier EOF
            {
             newCompositeNode(grammarAccess.getLVidentifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLVidentifier=ruleLVidentifier();

            state._fsp--;

             current =iv_ruleLVidentifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLVidentifier"


    // $ANTLR start "ruleLVidentifier"
    // InternalFml.g:6276:1: ruleLVidentifier returns [EObject current=null] : ( ( (lv_args_0_0= ruleFML_IDENTIFIER ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )* ) ;
    public final EObject ruleLVidentifier() throws RecognitionException {
        EObject current = null;

        Token this_COMMA_1=null;
        AntlrDatatypeRuleToken lv_args_0_0 = null;

        AntlrDatatypeRuleToken lv_args_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6282:2: ( ( ( (lv_args_0_0= ruleFML_IDENTIFIER ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )* ) )
            // InternalFml.g:6283:2: ( ( (lv_args_0_0= ruleFML_IDENTIFIER ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )* )
            {
            // InternalFml.g:6283:2: ( ( (lv_args_0_0= ruleFML_IDENTIFIER ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )* )
            // InternalFml.g:6284:3: ( (lv_args_0_0= ruleFML_IDENTIFIER ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )*
            {
            // InternalFml.g:6284:3: ( (lv_args_0_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:6285:4: (lv_args_0_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:6285:4: (lv_args_0_0= ruleFML_IDENTIFIER )
            // InternalFml.g:6286:5: lv_args_0_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getLVidentifierAccess().getArgsFML_IDENTIFIERParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_44);
            lv_args_0_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLVidentifierRule());
            					}
            					add(
            						current,
            						"args",
            						lv_args_0_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:6303:3: (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) ) )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==RULE_COMMA) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalFml.g:6304:4: this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleFML_IDENTIFIER ) )
            	    {
            	    this_COMMA_1=(Token)match(input,RULE_COMMA,FOLLOW_25); 

            	    				newLeafNode(this_COMMA_1, grammarAccess.getLVidentifierAccess().getCOMMATerminalRuleCall_1_0());
            	    			
            	    // InternalFml.g:6308:4: ( (lv_args_2_0= ruleFML_IDENTIFIER ) )
            	    // InternalFml.g:6309:5: (lv_args_2_0= ruleFML_IDENTIFIER )
            	    {
            	    // InternalFml.g:6309:5: (lv_args_2_0= ruleFML_IDENTIFIER )
            	    // InternalFml.g:6310:6: lv_args_2_0= ruleFML_IDENTIFIER
            	    {

            	    						newCompositeNode(grammarAccess.getLVidentifierAccess().getArgsFML_IDENTIFIERParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_44);
            	    lv_args_2_0=ruleFML_IDENTIFIER();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getLVidentifierRule());
            	    						}
            	    						add(
            	    							current,
            	    							"args",
            	    							lv_args_2_0,
            	    							"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLVidentifier"


    // $ANTLR start "entryRuleConfigurationCmd"
    // InternalFml.g:6332:1: entryRuleConfigurationCmd returns [EObject current=null] : iv_ruleConfigurationCmd= ruleConfigurationCmd EOF ;
    public final EObject entryRuleConfigurationCmd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfigurationCmd = null;


        try {
            // InternalFml.g:6332:57: (iv_ruleConfigurationCmd= ruleConfigurationCmd EOF )
            // InternalFml.g:6333:2: iv_ruleConfigurationCmd= ruleConfigurationCmd EOF
            {
             newCompositeNode(grammarAccess.getConfigurationCmdRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConfigurationCmd=ruleConfigurationCmd();

            state._fsp--;

             current =iv_ruleConfigurationCmd; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConfigurationCmd"


    // $ANTLR start "ruleConfigurationCmd"
    // InternalFml.g:6339:1: ruleConfigurationCmd returns [EObject current=null] : (this_CreateConfiguration_0= ruleCreateConfiguration | this_CompleteConfiguration_1= ruleCompleteConfiguration | this_SelectionFeature_2= ruleSelectionFeature | this_AutoConfiguration_3= ruleAutoConfiguration | this_SelectedConfiguration_4= ruleSelectedConfiguration | this_DeselectedConfiguration_5= ruleDeselectedConfiguration | this_UnselectedConfiguration_6= ruleUnselectedConfiguration ) ;
    public final EObject ruleConfigurationCmd() throws RecognitionException {
        EObject current = null;

        EObject this_CreateConfiguration_0 = null;

        EObject this_CompleteConfiguration_1 = null;

        EObject this_SelectionFeature_2 = null;

        EObject this_AutoConfiguration_3 = null;

        EObject this_SelectedConfiguration_4 = null;

        EObject this_DeselectedConfiguration_5 = null;

        EObject this_UnselectedConfiguration_6 = null;



        	enterRule();

        try {
            // InternalFml.g:6345:2: ( (this_CreateConfiguration_0= ruleCreateConfiguration | this_CompleteConfiguration_1= ruleCompleteConfiguration | this_SelectionFeature_2= ruleSelectionFeature | this_AutoConfiguration_3= ruleAutoConfiguration | this_SelectedConfiguration_4= ruleSelectedConfiguration | this_DeselectedConfiguration_5= ruleDeselectedConfiguration | this_UnselectedConfiguration_6= ruleUnselectedConfiguration ) )
            // InternalFml.g:6346:2: (this_CreateConfiguration_0= ruleCreateConfiguration | this_CompleteConfiguration_1= ruleCompleteConfiguration | this_SelectionFeature_2= ruleSelectionFeature | this_AutoConfiguration_3= ruleAutoConfiguration | this_SelectedConfiguration_4= ruleSelectedConfiguration | this_DeselectedConfiguration_5= ruleDeselectedConfiguration | this_UnselectedConfiguration_6= ruleUnselectedConfiguration )
            {
            // InternalFml.g:6346:2: (this_CreateConfiguration_0= ruleCreateConfiguration | this_CompleteConfiguration_1= ruleCompleteConfiguration | this_SelectionFeature_2= ruleSelectionFeature | this_AutoConfiguration_3= ruleAutoConfiguration | this_SelectedConfiguration_4= ruleSelectedConfiguration | this_DeselectedConfiguration_5= ruleDeselectedConfiguration | this_UnselectedConfiguration_6= ruleUnselectedConfiguration )
            int alt74=7;
            switch ( input.LA(1) ) {
            case 119:
                {
                alt74=1;
                }
                break;
            case 120:
                {
                alt74=2;
                }
                break;
            case 121:
            case 122:
            case 123:
                {
                alt74=3;
                }
                break;
            case 124:
                {
                alt74=4;
                }
                break;
            case 125:
                {
                alt74=5;
                }
                break;
            case 126:
                {
                alt74=6;
                }
                break;
            case 127:
                {
                alt74=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }

            switch (alt74) {
                case 1 :
                    // InternalFml.g:6347:3: this_CreateConfiguration_0= ruleCreateConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getCreateConfigurationParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_CreateConfiguration_0=ruleCreateConfiguration();

                    state._fsp--;


                    			current = this_CreateConfiguration_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:6356:3: this_CompleteConfiguration_1= ruleCompleteConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getCompleteConfigurationParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CompleteConfiguration_1=ruleCompleteConfiguration();

                    state._fsp--;


                    			current = this_CompleteConfiguration_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:6365:3: this_SelectionFeature_2= ruleSelectionFeature
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getSelectionFeatureParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_SelectionFeature_2=ruleSelectionFeature();

                    state._fsp--;


                    			current = this_SelectionFeature_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:6374:3: this_AutoConfiguration_3= ruleAutoConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getAutoConfigurationParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_AutoConfiguration_3=ruleAutoConfiguration();

                    state._fsp--;


                    			current = this_AutoConfiguration_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:6383:3: this_SelectedConfiguration_4= ruleSelectedConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getSelectedConfigurationParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_SelectedConfiguration_4=ruleSelectedConfiguration();

                    state._fsp--;


                    			current = this_SelectedConfiguration_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalFml.g:6392:3: this_DeselectedConfiguration_5= ruleDeselectedConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getDeselectedConfigurationParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_DeselectedConfiguration_5=ruleDeselectedConfiguration();

                    state._fsp--;


                    			current = this_DeselectedConfiguration_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalFml.g:6401:3: this_UnselectedConfiguration_6= ruleUnselectedConfiguration
                    {

                    			newCompositeNode(grammarAccess.getConfigurationCmdAccess().getUnselectedConfigurationParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_UnselectedConfiguration_6=ruleUnselectedConfiguration();

                    state._fsp--;


                    			current = this_UnselectedConfiguration_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConfigurationCmd"


    // $ANTLR start "entryRuleCreateConfiguration"
    // InternalFml.g:6413:1: entryRuleCreateConfiguration returns [EObject current=null] : iv_ruleCreateConfiguration= ruleCreateConfiguration EOF ;
    public final EObject entryRuleCreateConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCreateConfiguration = null;


        try {
            // InternalFml.g:6413:60: (iv_ruleCreateConfiguration= ruleCreateConfiguration EOF )
            // InternalFml.g:6414:2: iv_ruleCreateConfiguration= ruleCreateConfiguration EOF
            {
             newCompositeNode(grammarAccess.getCreateConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCreateConfiguration=ruleCreateConfiguration();

            state._fsp--;

             current =iv_ruleCreateConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCreateConfiguration"


    // $ANTLR start "ruleCreateConfiguration"
    // InternalFml.g:6420:1: ruleCreateConfiguration returns [EObject current=null] : (otherlv_0= 'configuration' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCreateConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6426:2: ( (otherlv_0= 'configuration' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:6427:2: (otherlv_0= 'configuration' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:6427:2: (otherlv_0= 'configuration' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:6428:3: otherlv_0= 'configuration' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,119,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCreateConfigurationAccess().getConfigurationKeyword_0());
            		
            // InternalFml.g:6432:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:6433:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:6433:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:6434:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCreateConfigurationAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCreateConfigurationRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCreateConfiguration"


    // $ANTLR start "entryRuleCompleteConfiguration"
    // InternalFml.g:6455:1: entryRuleCompleteConfiguration returns [EObject current=null] : iv_ruleCompleteConfiguration= ruleCompleteConfiguration EOF ;
    public final EObject entryRuleCompleteConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompleteConfiguration = null;


        try {
            // InternalFml.g:6455:62: (iv_ruleCompleteConfiguration= ruleCompleteConfiguration EOF )
            // InternalFml.g:6456:2: iv_ruleCompleteConfiguration= ruleCompleteConfiguration EOF
            {
             newCompositeNode(grammarAccess.getCompleteConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompleteConfiguration=ruleCompleteConfiguration();

            state._fsp--;

             current =iv_ruleCompleteConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompleteConfiguration"


    // $ANTLR start "ruleCompleteConfiguration"
    // InternalFml.g:6462:1: ruleCompleteConfiguration returns [EObject current=null] : (otherlv_0= 'isComplete' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleCompleteConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_config_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6468:2: ( (otherlv_0= 'isComplete' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6469:2: (otherlv_0= 'isComplete' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6469:2: (otherlv_0= 'isComplete' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6470:3: otherlv_0= 'isComplete' ( (lv_config_1_0= ruleConfigurationCommand ) )
            {
            otherlv_0=(Token)match(input,120,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getCompleteConfigurationAccess().getIsCompleteKeyword_0());
            		
            // InternalFml.g:6474:3: ( (lv_config_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6475:4: (lv_config_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6475:4: (lv_config_1_0= ruleConfigurationCommand )
            // InternalFml.g:6476:5: lv_config_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getCompleteConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_config_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCompleteConfigurationRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompleteConfiguration"


    // $ANTLR start "entryRuleSelectionFeature"
    // InternalFml.g:6497:1: entryRuleSelectionFeature returns [EObject current=null] : iv_ruleSelectionFeature= ruleSelectionFeature EOF ;
    public final EObject entryRuleSelectionFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectionFeature = null;


        try {
            // InternalFml.g:6497:57: (iv_ruleSelectionFeature= ruleSelectionFeature EOF )
            // InternalFml.g:6498:2: iv_ruleSelectionFeature= ruleSelectionFeature EOF
            {
             newCompositeNode(grammarAccess.getSelectionFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectionFeature=ruleSelectionFeature();

            state._fsp--;

             current =iv_ruleSelectionFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectionFeature"


    // $ANTLR start "ruleSelectionFeature"
    // InternalFml.g:6504:1: ruleSelectionFeature returns [EObject current=null] : ( ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) ) ( (lv_fts_1_0= ruleFeatureExpression ) )+ otherlv_2= 'in' ( (lv_config_3_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleSelectionFeature() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        Token lv_op_0_3=null;
        Token otherlv_2=null;
        EObject lv_fts_1_0 = null;

        EObject lv_config_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6510:2: ( ( ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) ) ( (lv_fts_1_0= ruleFeatureExpression ) )+ otherlv_2= 'in' ( (lv_config_3_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6511:2: ( ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) ) ( (lv_fts_1_0= ruleFeatureExpression ) )+ otherlv_2= 'in' ( (lv_config_3_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6511:2: ( ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) ) ( (lv_fts_1_0= ruleFeatureExpression ) )+ otherlv_2= 'in' ( (lv_config_3_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6512:3: ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) ) ( (lv_fts_1_0= ruleFeatureExpression ) )+ otherlv_2= 'in' ( (lv_config_3_0= ruleConfigurationCommand ) )
            {
            // InternalFml.g:6512:3: ( ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) ) )
            // InternalFml.g:6513:4: ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) )
            {
            // InternalFml.g:6513:4: ( (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' ) )
            // InternalFml.g:6514:5: (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' )
            {
            // InternalFml.g:6514:5: (lv_op_0_1= 'select' | lv_op_0_2= 'deselect' | lv_op_0_3= 'unselect' )
            int alt75=3;
            switch ( input.LA(1) ) {
            case 121:
                {
                alt75=1;
                }
                break;
            case 122:
                {
                alt75=2;
                }
                break;
            case 123:
                {
                alt75=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }

            switch (alt75) {
                case 1 :
                    // InternalFml.g:6515:6: lv_op_0_1= 'select'
                    {
                    lv_op_0_1=(Token)match(input,121,FOLLOW_37); 

                    						newLeafNode(lv_op_0_1, grammarAccess.getSelectionFeatureAccess().getOpSelectKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSelectionFeatureRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:6526:6: lv_op_0_2= 'deselect'
                    {
                    lv_op_0_2=(Token)match(input,122,FOLLOW_37); 

                    						newLeafNode(lv_op_0_2, grammarAccess.getSelectionFeatureAccess().getOpDeselectKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSelectionFeatureRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalFml.g:6537:6: lv_op_0_3= 'unselect'
                    {
                    lv_op_0_3=(Token)match(input,123,FOLLOW_37); 

                    						newLeafNode(lv_op_0_3, grammarAccess.getSelectionFeatureAccess().getOpUnselectKeyword_0_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSelectionFeatureRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:6550:3: ( (lv_fts_1_0= ruleFeatureExpression ) )+
            int cnt76=0;
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==RULE_STRING||LA76_0==RULE_ID||LA76_0==168) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // InternalFml.g:6551:4: (lv_fts_1_0= ruleFeatureExpression )
            	    {
            	    // InternalFml.g:6551:4: (lv_fts_1_0= ruleFeatureExpression )
            	    // InternalFml.g:6552:5: lv_fts_1_0= ruleFeatureExpression
            	    {

            	    					newCompositeNode(grammarAccess.getSelectionFeatureAccess().getFtsFeatureExpressionParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_70);
            	    lv_fts_1_0=ruleFeatureExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSelectionFeatureRule());
            	    					}
            	    					add(
            	    						current,
            	    						"fts",
            	    						lv_fts_1_0,
            	    						"org.xtext.example.mydsl.Fml.FeatureExpression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt76 >= 1 ) break loop76;
                        EarlyExitException eee =
                            new EarlyExitException(76, input);
                        throw eee;
                }
                cnt76++;
            } while (true);

            otherlv_2=(Token)match(input,42,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getSelectionFeatureAccess().getInKeyword_2());
            		
            // InternalFml.g:6573:3: ( (lv_config_3_0= ruleConfigurationCommand ) )
            // InternalFml.g:6574:4: (lv_config_3_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6574:4: (lv_config_3_0= ruleConfigurationCommand )
            // InternalFml.g:6575:5: lv_config_3_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getSelectionFeatureAccess().getConfigConfigurationCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_config_3_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSelectionFeatureRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_3_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectionFeature"


    // $ANTLR start "entryRuleFeatureExpression"
    // InternalFml.g:6596:1: entryRuleFeatureExpression returns [EObject current=null] : iv_ruleFeatureExpression= ruleFeatureExpression EOF ;
    public final EObject entryRuleFeatureExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureExpression = null;


        try {
            // InternalFml.g:6596:58: (iv_ruleFeatureExpression= ruleFeatureExpression EOF )
            // InternalFml.g:6597:2: iv_ruleFeatureExpression= ruleFeatureExpression EOF
            {
             newCompositeNode(grammarAccess.getFeatureExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureExpression=ruleFeatureExpression();

            state._fsp--;

             current =iv_ruleFeatureExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureExpression"


    // $ANTLR start "ruleFeatureExpression"
    // InternalFml.g:6603:1: ruleFeatureExpression returns [EObject current=null] : ( ( (lv_ft_0_0= ruleIdentifierExpr ) ) | ( (lv_ft_1_0= ruleStringExpr ) ) ) ;
    public final EObject ruleFeatureExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_ft_0_0 = null;

        EObject lv_ft_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6609:2: ( ( ( (lv_ft_0_0= ruleIdentifierExpr ) ) | ( (lv_ft_1_0= ruleStringExpr ) ) ) )
            // InternalFml.g:6610:2: ( ( (lv_ft_0_0= ruleIdentifierExpr ) ) | ( (lv_ft_1_0= ruleStringExpr ) ) )
            {
            // InternalFml.g:6610:2: ( ( (lv_ft_0_0= ruleIdentifierExpr ) ) | ( (lv_ft_1_0= ruleStringExpr ) ) )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==RULE_ID||LA77_0==168) ) {
                alt77=1;
            }
            else if ( (LA77_0==RULE_STRING) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // InternalFml.g:6611:3: ( (lv_ft_0_0= ruleIdentifierExpr ) )
                    {
                    // InternalFml.g:6611:3: ( (lv_ft_0_0= ruleIdentifierExpr ) )
                    // InternalFml.g:6612:4: (lv_ft_0_0= ruleIdentifierExpr )
                    {
                    // InternalFml.g:6612:4: (lv_ft_0_0= ruleIdentifierExpr )
                    // InternalFml.g:6613:5: lv_ft_0_0= ruleIdentifierExpr
                    {

                    					newCompositeNode(grammarAccess.getFeatureExpressionAccess().getFtIdentifierExprParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ft_0_0=ruleIdentifierExpr();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExpressionRule());
                    					}
                    					set(
                    						current,
                    						"ft",
                    						lv_ft_0_0,
                    						"org.xtext.example.mydsl.Fml.IdentifierExpr");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:6631:3: ( (lv_ft_1_0= ruleStringExpr ) )
                    {
                    // InternalFml.g:6631:3: ( (lv_ft_1_0= ruleStringExpr ) )
                    // InternalFml.g:6632:4: (lv_ft_1_0= ruleStringExpr )
                    {
                    // InternalFml.g:6632:4: (lv_ft_1_0= ruleStringExpr )
                    // InternalFml.g:6633:5: lv_ft_1_0= ruleStringExpr
                    {

                    					newCompositeNode(grammarAccess.getFeatureExpressionAccess().getFtStringExprParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ft_1_0=ruleStringExpr();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFeatureExpressionRule());
                    					}
                    					set(
                    						current,
                    						"ft",
                    						lv_ft_1_0,
                    						"org.xtext.example.mydsl.Fml.StringExpr");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureExpression"


    // $ANTLR start "entryRuleAutoConfiguration"
    // InternalFml.g:6654:1: entryRuleAutoConfiguration returns [EObject current=null] : iv_ruleAutoConfiguration= ruleAutoConfiguration EOF ;
    public final EObject entryRuleAutoConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAutoConfiguration = null;


        try {
            // InternalFml.g:6654:58: (iv_ruleAutoConfiguration= ruleAutoConfiguration EOF )
            // InternalFml.g:6655:2: iv_ruleAutoConfiguration= ruleAutoConfiguration EOF
            {
             newCompositeNode(grammarAccess.getAutoConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAutoConfiguration=ruleAutoConfiguration();

            state._fsp--;

             current =iv_ruleAutoConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAutoConfiguration"


    // $ANTLR start "ruleAutoConfiguration"
    // InternalFml.g:6661:1: ruleAutoConfiguration returns [EObject current=null] : (otherlv_0= 'autoSelect' ( (lv_config_1_0= ruleConfigurationCommand ) ) ( (lv_mode_2_0= ruleAutoConfMode ) )? ) ;
    public final EObject ruleAutoConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_config_1_0 = null;

        Enumerator lv_mode_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6667:2: ( (otherlv_0= 'autoSelect' ( (lv_config_1_0= ruleConfigurationCommand ) ) ( (lv_mode_2_0= ruleAutoConfMode ) )? ) )
            // InternalFml.g:6668:2: (otherlv_0= 'autoSelect' ( (lv_config_1_0= ruleConfigurationCommand ) ) ( (lv_mode_2_0= ruleAutoConfMode ) )? )
            {
            // InternalFml.g:6668:2: (otherlv_0= 'autoSelect' ( (lv_config_1_0= ruleConfigurationCommand ) ) ( (lv_mode_2_0= ruleAutoConfMode ) )? )
            // InternalFml.g:6669:3: otherlv_0= 'autoSelect' ( (lv_config_1_0= ruleConfigurationCommand ) ) ( (lv_mode_2_0= ruleAutoConfMode ) )?
            {
            otherlv_0=(Token)match(input,124,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getAutoConfigurationAccess().getAutoSelectKeyword_0());
            		
            // InternalFml.g:6673:3: ( (lv_config_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6674:4: (lv_config_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6674:4: (lv_config_1_0= ruleConfigurationCommand )
            // InternalFml.g:6675:5: lv_config_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getAutoConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_71);
            lv_config_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAutoConfigurationRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:6692:3: ( (lv_mode_2_0= ruleAutoConfMode ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( ((LA78_0>=211 && LA78_0<=213)) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalFml.g:6693:4: (lv_mode_2_0= ruleAutoConfMode )
                    {
                    // InternalFml.g:6693:4: (lv_mode_2_0= ruleAutoConfMode )
                    // InternalFml.g:6694:5: lv_mode_2_0= ruleAutoConfMode
                    {

                    					newCompositeNode(grammarAccess.getAutoConfigurationAccess().getModeAutoConfModeEnumRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_mode_2_0=ruleAutoConfMode();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAutoConfigurationRule());
                    					}
                    					set(
                    						current,
                    						"mode",
                    						lv_mode_2_0,
                    						"org.xtext.example.mydsl.Fml.AutoConfMode");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAutoConfiguration"


    // $ANTLR start "entryRuleSelectedConfiguration"
    // InternalFml.g:6715:1: entryRuleSelectedConfiguration returns [EObject current=null] : iv_ruleSelectedConfiguration= ruleSelectedConfiguration EOF ;
    public final EObject entryRuleSelectedConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectedConfiguration = null;


        try {
            // InternalFml.g:6715:62: (iv_ruleSelectedConfiguration= ruleSelectedConfiguration EOF )
            // InternalFml.g:6716:2: iv_ruleSelectedConfiguration= ruleSelectedConfiguration EOF
            {
             newCompositeNode(grammarAccess.getSelectedConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectedConfiguration=ruleSelectedConfiguration();

            state._fsp--;

             current =iv_ruleSelectedConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectedConfiguration"


    // $ANTLR start "ruleSelectedConfiguration"
    // InternalFml.g:6722:1: ruleSelectedConfiguration returns [EObject current=null] : (otherlv_0= 'selectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleSelectedConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_config_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6728:2: ( (otherlv_0= 'selectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6729:2: (otherlv_0= 'selectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6729:2: (otherlv_0= 'selectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6730:3: otherlv_0= 'selectedF' ( (lv_config_1_0= ruleConfigurationCommand ) )
            {
            otherlv_0=(Token)match(input,125,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getSelectedConfigurationAccess().getSelectedFKeyword_0());
            		
            // InternalFml.g:6734:3: ( (lv_config_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6735:4: (lv_config_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6735:4: (lv_config_1_0= ruleConfigurationCommand )
            // InternalFml.g:6736:5: lv_config_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getSelectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_config_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSelectedConfigurationRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectedConfiguration"


    // $ANTLR start "entryRuleDeselectedConfiguration"
    // InternalFml.g:6757:1: entryRuleDeselectedConfiguration returns [EObject current=null] : iv_ruleDeselectedConfiguration= ruleDeselectedConfiguration EOF ;
    public final EObject entryRuleDeselectedConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeselectedConfiguration = null;


        try {
            // InternalFml.g:6757:64: (iv_ruleDeselectedConfiguration= ruleDeselectedConfiguration EOF )
            // InternalFml.g:6758:2: iv_ruleDeselectedConfiguration= ruleDeselectedConfiguration EOF
            {
             newCompositeNode(grammarAccess.getDeselectedConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDeselectedConfiguration=ruleDeselectedConfiguration();

            state._fsp--;

             current =iv_ruleDeselectedConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDeselectedConfiguration"


    // $ANTLR start "ruleDeselectedConfiguration"
    // InternalFml.g:6764:1: ruleDeselectedConfiguration returns [EObject current=null] : (otherlv_0= 'deselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleDeselectedConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_config_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6770:2: ( (otherlv_0= 'deselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6771:2: (otherlv_0= 'deselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6771:2: (otherlv_0= 'deselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6772:3: otherlv_0= 'deselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) )
            {
            otherlv_0=(Token)match(input,126,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getDeselectedConfigurationAccess().getDeselectedFKeyword_0());
            		
            // InternalFml.g:6776:3: ( (lv_config_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6777:4: (lv_config_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6777:4: (lv_config_1_0= ruleConfigurationCommand )
            // InternalFml.g:6778:5: lv_config_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getDeselectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_config_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDeselectedConfigurationRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDeselectedConfiguration"


    // $ANTLR start "entryRuleUnselectedConfiguration"
    // InternalFml.g:6799:1: entryRuleUnselectedConfiguration returns [EObject current=null] : iv_ruleUnselectedConfiguration= ruleUnselectedConfiguration EOF ;
    public final EObject entryRuleUnselectedConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnselectedConfiguration = null;


        try {
            // InternalFml.g:6799:64: (iv_ruleUnselectedConfiguration= ruleUnselectedConfiguration EOF )
            // InternalFml.g:6800:2: iv_ruleUnselectedConfiguration= ruleUnselectedConfiguration EOF
            {
             newCompositeNode(grammarAccess.getUnselectedConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnselectedConfiguration=ruleUnselectedConfiguration();

            state._fsp--;

             current =iv_ruleUnselectedConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnselectedConfiguration"


    // $ANTLR start "ruleUnselectedConfiguration"
    // InternalFml.g:6806:1: ruleUnselectedConfiguration returns [EObject current=null] : (otherlv_0= 'unselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleUnselectedConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_config_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6812:2: ( (otherlv_0= 'unselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6813:2: (otherlv_0= 'unselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6813:2: (otherlv_0= 'unselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6814:3: otherlv_0= 'unselectedF' ( (lv_config_1_0= ruleConfigurationCommand ) )
            {
            otherlv_0=(Token)match(input,127,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getUnselectedConfigurationAccess().getUnselectedFKeyword_0());
            		
            // InternalFml.g:6818:3: ( (lv_config_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6819:4: (lv_config_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6819:4: (lv_config_1_0= ruleConfigurationCommand )
            // InternalFml.g:6820:5: lv_config_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getUnselectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_config_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUnselectedConfigurationRule());
            					}
            					set(
            						current,
            						"config",
            						lv_config_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnselectedConfiguration"


    // $ANTLR start "entryRuleAsFM"
    // InternalFml.g:6841:1: entryRuleAsFM returns [EObject current=null] : iv_ruleAsFM= ruleAsFM EOF ;
    public final EObject entryRuleAsFM() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAsFM = null;


        try {
            // InternalFml.g:6841:45: (iv_ruleAsFM= ruleAsFM EOF )
            // InternalFml.g:6842:2: iv_ruleAsFM= ruleAsFM EOF
            {
             newCompositeNode(grammarAccess.getAsFMRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAsFM=ruleAsFM();

            state._fsp--;

             current =iv_ruleAsFM; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAsFM"


    // $ANTLR start "ruleAsFM"
    // InternalFml.g:6848:1: ruleAsFM returns [EObject current=null] : (otherlv_0= 'asFM' ( (lv_conf_1_0= ruleConfigurationCommand ) ) ) ;
    public final EObject ruleAsFM() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_conf_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6854:2: ( (otherlv_0= 'asFM' ( (lv_conf_1_0= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:6855:2: (otherlv_0= 'asFM' ( (lv_conf_1_0= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:6855:2: (otherlv_0= 'asFM' ( (lv_conf_1_0= ruleConfigurationCommand ) ) )
            // InternalFml.g:6856:3: otherlv_0= 'asFM' ( (lv_conf_1_0= ruleConfigurationCommand ) )
            {
            otherlv_0=(Token)match(input,128,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getAsFMAccess().getAsFMKeyword_0());
            		
            // InternalFml.g:6860:3: ( (lv_conf_1_0= ruleConfigurationCommand ) )
            // InternalFml.g:6861:4: (lv_conf_1_0= ruleConfigurationCommand )
            {
            // InternalFml.g:6861:4: (lv_conf_1_0= ruleConfigurationCommand )
            // InternalFml.g:6862:5: lv_conf_1_0= ruleConfigurationCommand
            {

            					newCompositeNode(grammarAccess.getAsFMAccess().getConfConfigurationCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_conf_1_0=ruleConfigurationCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAsFMRule());
            					}
            					set(
            						current,
            						"conf",
            						lv_conf_1_0,
            						"org.xtext.example.mydsl.Fml.ConfigurationCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAsFM"


    // $ANTLR start "entryRuleMap"
    // InternalFml.g:6883:1: entryRuleMap returns [EObject current=null] : iv_ruleMap= ruleMap EOF ;
    public final EObject entryRuleMap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMap = null;


        try {
            // InternalFml.g:6883:44: (iv_ruleMap= ruleMap EOF )
            // InternalFml.g:6884:2: iv_ruleMap= ruleMap EOF
            {
             newCompositeNode(grammarAccess.getMapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMap=ruleMap();

            state._fsp--;

             current =iv_ruleMap; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMap"


    // $ANTLR start "ruleMap"
    // InternalFml.g:6890:1: ruleMap returns [EObject current=null] : (otherlv_0= 'map' ( (lv_fm_1_0= ruleFMCommand ) ) otherlv_2= 'with' ( (lv_cst_3_0= ruleSetCommand ) ) ) ;
    public final EObject ruleMap() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_fm_1_0 = null;

        EObject lv_cst_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6896:2: ( (otherlv_0= 'map' ( (lv_fm_1_0= ruleFMCommand ) ) otherlv_2= 'with' ( (lv_cst_3_0= ruleSetCommand ) ) ) )
            // InternalFml.g:6897:2: (otherlv_0= 'map' ( (lv_fm_1_0= ruleFMCommand ) ) otherlv_2= 'with' ( (lv_cst_3_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:6897:2: (otherlv_0= 'map' ( (lv_fm_1_0= ruleFMCommand ) ) otherlv_2= 'with' ( (lv_cst_3_0= ruleSetCommand ) ) )
            // InternalFml.g:6898:3: otherlv_0= 'map' ( (lv_fm_1_0= ruleFMCommand ) ) otherlv_2= 'with' ( (lv_cst_3_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,129,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getMapAccess().getMapKeyword_0());
            		
            // InternalFml.g:6902:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:6903:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:6903:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:6904:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getMapAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_67);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMapRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,99,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getMapAccess().getWithKeyword_2());
            		
            // InternalFml.g:6925:3: ( (lv_cst_3_0= ruleSetCommand ) )
            // InternalFml.g:6926:4: (lv_cst_3_0= ruleSetCommand )
            {
            // InternalFml.g:6926:4: (lv_cst_3_0= ruleSetCommand )
            // InternalFml.g:6927:5: lv_cst_3_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getMapAccess().getCstSetCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_cst_3_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMapRule());
            					}
            					set(
            						current,
            						"cst",
            						lv_cst_3_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMap"


    // $ANTLR start "entryRuleUnMap"
    // InternalFml.g:6948:1: entryRuleUnMap returns [EObject current=null] : iv_ruleUnMap= ruleUnMap EOF ;
    public final EObject entryRuleUnMap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnMap = null;


        try {
            // InternalFml.g:6948:46: (iv_ruleUnMap= ruleUnMap EOF )
            // InternalFml.g:6949:2: iv_ruleUnMap= ruleUnMap EOF
            {
             newCompositeNode(grammarAccess.getUnMapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnMap=ruleUnMap();

            state._fsp--;

             current =iv_ruleUnMap; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnMap"


    // $ANTLR start "ruleUnMap"
    // InternalFml.g:6955:1: ruleUnMap returns [EObject current=null] : (otherlv_0= 'unmap' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleUnMap() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:6961:2: ( (otherlv_0= 'unmap' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:6962:2: (otherlv_0= 'unmap' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:6962:2: (otherlv_0= 'unmap' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:6963:3: otherlv_0= 'unmap' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,130,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getUnMapAccess().getUnmapKeyword_0());
            		
            // InternalFml.g:6967:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:6968:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:6968:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:6969:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getUnMapAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUnMapRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnMap"


    // $ANTLR start "entryRuleCleanUp"
    // InternalFml.g:6990:1: entryRuleCleanUp returns [EObject current=null] : iv_ruleCleanUp= ruleCleanUp EOF ;
    public final EObject entryRuleCleanUp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCleanUp = null;


        try {
            // InternalFml.g:6990:48: (iv_ruleCleanUp= ruleCleanUp EOF )
            // InternalFml.g:6991:2: iv_ruleCleanUp= ruleCleanUp EOF
            {
             newCompositeNode(grammarAccess.getCleanUpRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCleanUp=ruleCleanUp();

            state._fsp--;

             current =iv_ruleCleanUp; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCleanUp"


    // $ANTLR start "ruleCleanUp"
    // InternalFml.g:6997:1: ruleCleanUp returns [EObject current=null] : (otherlv_0= 'cleanup' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCleanUp() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7003:2: ( (otherlv_0= 'cleanup' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7004:2: (otherlv_0= 'cleanup' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7004:2: (otherlv_0= 'cleanup' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:7005:3: otherlv_0= 'cleanup' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,131,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCleanUpAccess().getCleanupKeyword_0());
            		
            // InternalFml.g:7009:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:7010:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:7010:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:7011:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCleanUpAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCleanUpRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCleanUp"


    // $ANTLR start "entryRuleCores"
    // InternalFml.g:7032:1: entryRuleCores returns [EObject current=null] : iv_ruleCores= ruleCores EOF ;
    public final EObject entryRuleCores() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCores = null;


        try {
            // InternalFml.g:7032:46: (iv_ruleCores= ruleCores EOF )
            // InternalFml.g:7033:2: iv_ruleCores= ruleCores EOF
            {
             newCompositeNode(grammarAccess.getCoresRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCores=ruleCores();

            state._fsp--;

             current =iv_ruleCores; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCores"


    // $ANTLR start "ruleCores"
    // InternalFml.g:7039:1: ruleCores returns [EObject current=null] : (otherlv_0= 'cores' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCores() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7045:2: ( (otherlv_0= 'cores' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7046:2: (otherlv_0= 'cores' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7046:2: (otherlv_0= 'cores' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:7047:3: otherlv_0= 'cores' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,132,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCoresAccess().getCoresKeyword_0());
            		
            // InternalFml.g:7051:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:7052:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:7052:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:7053:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCoresAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCoresRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCores"


    // $ANTLR start "entryRuleDeads"
    // InternalFml.g:7074:1: entryRuleDeads returns [EObject current=null] : iv_ruleDeads= ruleDeads EOF ;
    public final EObject entryRuleDeads() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeads = null;


        try {
            // InternalFml.g:7074:46: (iv_ruleDeads= ruleDeads EOF )
            // InternalFml.g:7075:2: iv_ruleDeads= ruleDeads EOF
            {
             newCompositeNode(grammarAccess.getDeadsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDeads=ruleDeads();

            state._fsp--;

             current =iv_ruleDeads; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDeads"


    // $ANTLR start "ruleDeads"
    // InternalFml.g:7081:1: ruleDeads returns [EObject current=null] : (otherlv_0= 'deads' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleDeads() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7087:2: ( (otherlv_0= 'deads' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7088:2: (otherlv_0= 'deads' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7088:2: (otherlv_0= 'deads' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:7089:3: otherlv_0= 'deads' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,133,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getDeadsAccess().getDeadsKeyword_0());
            		
            // InternalFml.g:7093:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:7094:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:7094:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:7095:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getDeadsAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDeadsRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDeads"


    // $ANTLR start "entryRuleFullMandatorys"
    // InternalFml.g:7116:1: entryRuleFullMandatorys returns [EObject current=null] : iv_ruleFullMandatorys= ruleFullMandatorys EOF ;
    public final EObject entryRuleFullMandatorys() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullMandatorys = null;


        try {
            // InternalFml.g:7116:55: (iv_ruleFullMandatorys= ruleFullMandatorys EOF )
            // InternalFml.g:7117:2: iv_ruleFullMandatorys= ruleFullMandatorys EOF
            {
             newCompositeNode(grammarAccess.getFullMandatorysRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFullMandatorys=ruleFullMandatorys();

            state._fsp--;

             current =iv_ruleFullMandatorys; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFullMandatorys"


    // $ANTLR start "ruleFullMandatorys"
    // InternalFml.g:7123:1: ruleFullMandatorys returns [EObject current=null] : ( (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' ) ( (lv_fm_2_0= ruleFMCommand ) ) ) ;
    public final EObject ruleFullMandatorys() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_fm_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7129:2: ( ( (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' ) ( (lv_fm_2_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7130:2: ( (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' ) ( (lv_fm_2_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7130:2: ( (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' ) ( (lv_fm_2_0= ruleFMCommand ) ) )
            // InternalFml.g:7131:3: (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' ) ( (lv_fm_2_0= ruleFMCommand ) )
            {
            // InternalFml.g:7131:3: (otherlv_0= 'fullMandatorys' | otherlv_1= 'falseOptionals' )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==134) ) {
                alt79=1;
            }
            else if ( (LA79_0==135) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalFml.g:7132:4: otherlv_0= 'fullMandatorys'
                    {
                    otherlv_0=(Token)match(input,134,FOLLOW_19); 

                    				newLeafNode(otherlv_0, grammarAccess.getFullMandatorysAccess().getFullMandatorysKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalFml.g:7137:4: otherlv_1= 'falseOptionals'
                    {
                    otherlv_1=(Token)match(input,135,FOLLOW_19); 

                    				newLeafNode(otherlv_1, grammarAccess.getFullMandatorysAccess().getFalseOptionalsKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalFml.g:7142:3: ( (lv_fm_2_0= ruleFMCommand ) )
            // InternalFml.g:7143:4: (lv_fm_2_0= ruleFMCommand )
            {
            // InternalFml.g:7143:4: (lv_fm_2_0= ruleFMCommand )
            // InternalFml.g:7144:5: lv_fm_2_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getFullMandatorysAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_2_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFullMandatorysRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_2_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFullMandatorys"


    // $ANTLR start "entryRuleCliques"
    // InternalFml.g:7165:1: entryRuleCliques returns [EObject current=null] : iv_ruleCliques= ruleCliques EOF ;
    public final EObject entryRuleCliques() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCliques = null;


        try {
            // InternalFml.g:7165:48: (iv_ruleCliques= ruleCliques EOF )
            // InternalFml.g:7166:2: iv_ruleCliques= ruleCliques EOF
            {
             newCompositeNode(grammarAccess.getCliquesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCliques=ruleCliques();

            state._fsp--;

             current =iv_ruleCliques; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCliques"


    // $ANTLR start "ruleCliques"
    // InternalFml.g:7172:1: ruleCliques returns [EObject current=null] : (otherlv_0= 'cliques' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleCliques() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7178:2: ( (otherlv_0= 'cliques' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7179:2: (otherlv_0= 'cliques' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7179:2: (otherlv_0= 'cliques' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:7180:3: otherlv_0= 'cliques' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,136,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getCliquesAccess().getCliquesKeyword_0());
            		
            // InternalFml.g:7184:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:7185:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:7185:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:7186:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getCliquesAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCliquesRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCliques"


    // $ANTLR start "entryRuleScriptDefinition"
    // InternalFml.g:7207:1: entryRuleScriptDefinition returns [EObject current=null] : iv_ruleScriptDefinition= ruleScriptDefinition EOF ;
    public final EObject entryRuleScriptDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScriptDefinition = null;


        try {
            // InternalFml.g:7207:57: (iv_ruleScriptDefinition= ruleScriptDefinition EOF )
            // InternalFml.g:7208:2: iv_ruleScriptDefinition= ruleScriptDefinition EOF
            {
             newCompositeNode(grammarAccess.getScriptDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScriptDefinition=ruleScriptDefinition();

            state._fsp--;

             current =iv_ruleScriptDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScriptDefinition"


    // $ANTLR start "ruleScriptDefinition"
    // InternalFml.g:7214:1: ruleScriptDefinition returns [EObject current=null] : (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_params_1_0= ruleParameter ) )* ( (lv_cmds_2_0= ruleScriptCommand ) )+ ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )* this_RIGHT_HOOK_4= RULE_RIGHT_HOOK ) ;
    public final EObject ruleScriptDefinition() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_HOOK_0=null;
        Token this_RIGHT_HOOK_4=null;
        EObject lv_params_1_0 = null;

        EObject lv_cmds_2_0 = null;

        EObject lv_exports_3_1 = null;

        EObject lv_exports_3_2 = null;



        	enterRule();

        try {
            // InternalFml.g:7220:2: ( (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_params_1_0= ruleParameter ) )* ( (lv_cmds_2_0= ruleScriptCommand ) )+ ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )* this_RIGHT_HOOK_4= RULE_RIGHT_HOOK ) )
            // InternalFml.g:7221:2: (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_params_1_0= ruleParameter ) )* ( (lv_cmds_2_0= ruleScriptCommand ) )+ ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )* this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )
            {
            // InternalFml.g:7221:2: (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_params_1_0= ruleParameter ) )* ( (lv_cmds_2_0= ruleScriptCommand ) )+ ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )* this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )
            // InternalFml.g:7222:3: this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_params_1_0= ruleParameter ) )* ( (lv_cmds_2_0= ruleScriptCommand ) )+ ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )* this_RIGHT_HOOK_4= RULE_RIGHT_HOOK
            {
            this_LEFT_HOOK_0=(Token)match(input,RULE_LEFT_HOOK,FOLLOW_72); 

            			newLeafNode(this_LEFT_HOOK_0, grammarAccess.getScriptDefinitionAccess().getLEFT_HOOKTerminalRuleCall_0());
            		
            // InternalFml.g:7226:3: ( (lv_params_1_0= ruleParameter ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==88) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalFml.g:7227:4: (lv_params_1_0= ruleParameter )
            	    {
            	    // InternalFml.g:7227:4: (lv_params_1_0= ruleParameter )
            	    // InternalFml.g:7228:5: lv_params_1_0= ruleParameter
            	    {

            	    					newCompositeNode(grammarAccess.getScriptDefinitionAccess().getParamsParameterParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_72);
            	    lv_params_1_0=ruleParameter();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScriptDefinitionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"params",
            	    						lv_params_1_0,
            	    						"org.xtext.example.mydsl.Fml.Parameter");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);

            // InternalFml.g:7245:3: ( (lv_cmds_2_0= ruleScriptCommand ) )+
            int cnt81=0;
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==RULE_LEFT_HOOK||LA81_0==RULE_LEFT_PAREN||(LA81_0>=RULE_INT && LA81_0<=RULE_LEFT_BRACKET)||LA81_0==RULE_ID||(LA81_0>=31 && LA81_0<=35)||LA81_0==37||LA81_0==41||LA81_0==53||LA81_0==55||(LA81_0>=58 && LA81_0<=87)||LA81_0==90||(LA81_0>=92 && LA81_0<=93)||LA81_0==95||LA81_0==97||(LA81_0>=106 && LA81_0<=107)||(LA81_0>=110 && LA81_0<=112)||(LA81_0>=114 && LA81_0<=116)||(LA81_0>=119 && LA81_0<=162)||(LA81_0>=164 && LA81_0<=166)||LA81_0==168||(LA81_0>=170 && LA81_0<=189)) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // InternalFml.g:7246:4: (lv_cmds_2_0= ruleScriptCommand )
            	    {
            	    // InternalFml.g:7246:4: (lv_cmds_2_0= ruleScriptCommand )
            	    // InternalFml.g:7247:5: lv_cmds_2_0= ruleScriptCommand
            	    {

            	    					newCompositeNode(grammarAccess.getScriptDefinitionAccess().getCmdsScriptCommandParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_73);
            	    lv_cmds_2_0=ruleScriptCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScriptDefinitionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"cmds",
            	    						lv_cmds_2_0,
            	    						"org.xtext.example.mydsl.Fml.ScriptCommand");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt81 >= 1 ) break loop81;
                        EarlyExitException eee =
                            new EarlyExitException(81, input);
                        throw eee;
                }
                cnt81++;
            } while (true);

            // InternalFml.g:7264:3: ( ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) ) )*
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( ((LA83_0>=117 && LA83_0<=118)) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // InternalFml.g:7265:4: ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) )
            	    {
            	    // InternalFml.g:7265:4: ( (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden ) )
            	    // InternalFml.g:7266:5: (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden )
            	    {
            	    // InternalFml.g:7266:5: (lv_exports_3_1= ruleExport | lv_exports_3_2= ruleHidden )
            	    int alt82=2;
            	    int LA82_0 = input.LA(1);

            	    if ( (LA82_0==117) ) {
            	        alt82=1;
            	    }
            	    else if ( (LA82_0==118) ) {
            	        alt82=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 82, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt82) {
            	        case 1 :
            	            // InternalFml.g:7267:6: lv_exports_3_1= ruleExport
            	            {

            	            						newCompositeNode(grammarAccess.getScriptDefinitionAccess().getExportsExportParserRuleCall_3_0_0());
            	            					
            	            pushFollow(FOLLOW_74);
            	            lv_exports_3_1=ruleExport();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getScriptDefinitionRule());
            	            						}
            	            						add(
            	            							current,
            	            							"exports",
            	            							lv_exports_3_1,
            	            							"org.xtext.example.mydsl.Fml.Export");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }
            	            break;
            	        case 2 :
            	            // InternalFml.g:7283:6: lv_exports_3_2= ruleHidden
            	            {

            	            						newCompositeNode(grammarAccess.getScriptDefinitionAccess().getExportsHiddenParserRuleCall_3_0_1());
            	            					
            	            pushFollow(FOLLOW_74);
            	            lv_exports_3_2=ruleHidden();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getScriptDefinitionRule());
            	            						}
            	            						add(
            	            							current,
            	            							"exports",
            	            							lv_exports_3_2,
            	            							"org.xtext.example.mydsl.Fml.Hidden");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop83;
                }
            } while (true);

            this_RIGHT_HOOK_4=(Token)match(input,RULE_RIGHT_HOOK,FOLLOW_2); 

            			newLeafNode(this_RIGHT_HOOK_4, grammarAccess.getScriptDefinitionAccess().getRIGHT_HOOKTerminalRuleCall_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScriptDefinition"


    // $ANTLR start "entryRuleShell"
    // InternalFml.g:7309:1: entryRuleShell returns [EObject current=null] : iv_ruleShell= ruleShell EOF ;
    public final EObject entryRuleShell() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShell = null;


        try {
            // InternalFml.g:7309:46: (iv_ruleShell= ruleShell EOF )
            // InternalFml.g:7310:2: iv_ruleShell= ruleShell EOF
            {
             newCompositeNode(grammarAccess.getShellRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleShell=ruleShell();

            state._fsp--;

             current =iv_ruleShell; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleShell"


    // $ANTLR start "ruleShell"
    // InternalFml.g:7316:1: ruleShell returns [EObject current=null] : ( ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) ) ) ;
    public final EObject ruleShell() throws RecognitionException {
        EObject current = null;

        EObject lv_cmd_0_1 = null;

        EObject lv_cmd_0_2 = null;

        EObject lv_cmd_0_3 = null;

        EObject lv_cmd_0_4 = null;

        EObject lv_cmd_0_5 = null;



        	enterRule();

        try {
            // InternalFml.g:7322:2: ( ( ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) ) ) )
            // InternalFml.g:7323:2: ( ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) ) )
            {
            // InternalFml.g:7323:2: ( ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) ) )
            // InternalFml.g:7324:3: ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) )
            {
            // InternalFml.g:7324:3: ( (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState ) )
            // InternalFml.g:7325:4: (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState )
            {
            // InternalFml.g:7325:4: (lv_cmd_0_1= ruleExit | lv_cmd_0_2= ruleExist | lv_cmd_0_3= ruleListing | lv_cmd_0_4= ruleIsConflicting | lv_cmd_0_5= ruleState )
            int alt84=5;
            switch ( input.LA(1) ) {
            case 137:
            case 138:
                {
                alt84=1;
                }
                break;
            case 139:
                {
                alt84=2;
                }
                break;
            case 141:
            case 142:
                {
                alt84=3;
                }
                break;
            case 140:
                {
                alt84=4;
                }
                break;
            case 143:
            case 144:
                {
                alt84=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }

            switch (alt84) {
                case 1 :
                    // InternalFml.g:7326:5: lv_cmd_0_1= ruleExit
                    {

                    					newCompositeNode(grammarAccess.getShellAccess().getCmdExitParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_cmd_0_1=ruleExit();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getShellRule());
                    					}
                    					set(
                    						current,
                    						"cmd",
                    						lv_cmd_0_1,
                    						"org.xtext.example.mydsl.Fml.Exit");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;
                case 2 :
                    // InternalFml.g:7342:5: lv_cmd_0_2= ruleExist
                    {

                    					newCompositeNode(grammarAccess.getShellAccess().getCmdExistParserRuleCall_0_1());
                    				
                    pushFollow(FOLLOW_2);
                    lv_cmd_0_2=ruleExist();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getShellRule());
                    					}
                    					set(
                    						current,
                    						"cmd",
                    						lv_cmd_0_2,
                    						"org.xtext.example.mydsl.Fml.Exist");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;
                case 3 :
                    // InternalFml.g:7358:5: lv_cmd_0_3= ruleListing
                    {

                    					newCompositeNode(grammarAccess.getShellAccess().getCmdListingParserRuleCall_0_2());
                    				
                    pushFollow(FOLLOW_2);
                    lv_cmd_0_3=ruleListing();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getShellRule());
                    					}
                    					set(
                    						current,
                    						"cmd",
                    						lv_cmd_0_3,
                    						"org.xtext.example.mydsl.Fml.Listing");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;
                case 4 :
                    // InternalFml.g:7374:5: lv_cmd_0_4= ruleIsConflicting
                    {

                    					newCompositeNode(grammarAccess.getShellAccess().getCmdIsConflictingParserRuleCall_0_3());
                    				
                    pushFollow(FOLLOW_2);
                    lv_cmd_0_4=ruleIsConflicting();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getShellRule());
                    					}
                    					set(
                    						current,
                    						"cmd",
                    						lv_cmd_0_4,
                    						"org.xtext.example.mydsl.Fml.IsConflicting");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;
                case 5 :
                    // InternalFml.g:7390:5: lv_cmd_0_5= ruleState
                    {

                    					newCompositeNode(grammarAccess.getShellAccess().getCmdStateParserRuleCall_0_4());
                    				
                    pushFollow(FOLLOW_2);
                    lv_cmd_0_5=ruleState();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getShellRule());
                    					}
                    					set(
                    						current,
                    						"cmd",
                    						lv_cmd_0_5,
                    						"org.xtext.example.mydsl.Fml.State");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleShell"


    // $ANTLR start "entryRuleExit"
    // InternalFml.g:7411:1: entryRuleExit returns [EObject current=null] : iv_ruleExit= ruleExit EOF ;
    public final EObject entryRuleExit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExit = null;


        try {
            // InternalFml.g:7411:45: (iv_ruleExit= ruleExit EOF )
            // InternalFml.g:7412:2: iv_ruleExit= ruleExit EOF
            {
             newCompositeNode(grammarAccess.getExitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExit=ruleExit();

            state._fsp--;

             current =iv_ruleExit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExit"


    // $ANTLR start "ruleExit"
    // InternalFml.g:7418:1: ruleExit returns [EObject current=null] : ( ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) ) ) ;
    public final EObject ruleExit() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;


        	enterRule();

        try {
            // InternalFml.g:7424:2: ( ( ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) ) ) )
            // InternalFml.g:7425:2: ( ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) ) )
            {
            // InternalFml.g:7425:2: ( ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) ) )
            // InternalFml.g:7426:3: ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) )
            {
            // InternalFml.g:7426:3: ( (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' ) )
            // InternalFml.g:7427:4: (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' )
            {
            // InternalFml.g:7427:4: (lv_val_0_1= 'quit' | lv_val_0_2= 'exit' )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==137) ) {
                alt85=1;
            }
            else if ( (LA85_0==138) ) {
                alt85=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // InternalFml.g:7428:5: lv_val_0_1= 'quit'
                    {
                    lv_val_0_1=(Token)match(input,137,FOLLOW_2); 

                    					newLeafNode(lv_val_0_1, grammarAccess.getExitAccess().getValQuitKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getExitRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_1, null);
                    				

                    }
                    break;
                case 2 :
                    // InternalFml.g:7439:5: lv_val_0_2= 'exit'
                    {
                    lv_val_0_2=(Token)match(input,138,FOLLOW_2); 

                    					newLeafNode(lv_val_0_2, grammarAccess.getExitAccess().getValExitKeyword_0_1());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getExitRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_2, null);
                    				

                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExit"


    // $ANTLR start "entryRuleExist"
    // InternalFml.g:7455:1: entryRuleExist returns [EObject current=null] : iv_ruleExist= ruleExist EOF ;
    public final EObject entryRuleExist() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExist = null;


        try {
            // InternalFml.g:7455:46: (iv_ruleExist= ruleExist EOF )
            // InternalFml.g:7456:2: iv_ruleExist= ruleExist EOF
            {
             newCompositeNode(grammarAccess.getExistRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExist=ruleExist();

            state._fsp--;

             current =iv_ruleExist; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExist"


    // $ANTLR start "ruleExist"
    // InternalFml.g:7462:1: ruleExist returns [EObject current=null] : ( ( (lv_val_0_0= 'isExisting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleExist() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;
        AntlrDatatypeRuleToken lv_var_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7468:2: ( ( ( (lv_val_0_0= 'isExisting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:7469:2: ( ( (lv_val_0_0= 'isExisting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:7469:2: ( ( (lv_val_0_0= 'isExisting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:7470:3: ( (lv_val_0_0= 'isExisting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            {
            // InternalFml.g:7470:3: ( (lv_val_0_0= 'isExisting' ) )
            // InternalFml.g:7471:4: (lv_val_0_0= 'isExisting' )
            {
            // InternalFml.g:7471:4: (lv_val_0_0= 'isExisting' )
            // InternalFml.g:7472:5: lv_val_0_0= 'isExisting'
            {
            lv_val_0_0=(Token)match(input,139,FOLLOW_25); 

            					newLeafNode(lv_val_0_0, grammarAccess.getExistAccess().getValIsExistingKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExistRule());
            					}
            					setWithLastConsumed(current, "val", lv_val_0_0, "isExisting");
            				

            }


            }

            // InternalFml.g:7484:3: ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:7485:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:7485:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            // InternalFml.g:7486:5: lv_var_1_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getExistAccess().getVarFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_var_1_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExistRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_1_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExist"


    // $ANTLR start "entryRuleIsConflicting"
    // InternalFml.g:7507:1: entryRuleIsConflicting returns [EObject current=null] : iv_ruleIsConflicting= ruleIsConflicting EOF ;
    public final EObject entryRuleIsConflicting() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIsConflicting = null;


        try {
            // InternalFml.g:7507:54: (iv_ruleIsConflicting= ruleIsConflicting EOF )
            // InternalFml.g:7508:2: iv_ruleIsConflicting= ruleIsConflicting EOF
            {
             newCompositeNode(grammarAccess.getIsConflictingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIsConflicting=ruleIsConflicting();

            state._fsp--;

             current =iv_ruleIsConflicting; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIsConflicting"


    // $ANTLR start "ruleIsConflicting"
    // InternalFml.g:7514:1: ruleIsConflicting returns [EObject current=null] : ( ( (lv_val_0_0= 'isConflicting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleIsConflicting() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;
        AntlrDatatypeRuleToken lv_var_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7520:2: ( ( ( (lv_val_0_0= 'isConflicting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:7521:2: ( ( (lv_val_0_0= 'isConflicting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:7521:2: ( ( (lv_val_0_0= 'isConflicting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:7522:3: ( (lv_val_0_0= 'isConflicting' ) ) ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            {
            // InternalFml.g:7522:3: ( (lv_val_0_0= 'isConflicting' ) )
            // InternalFml.g:7523:4: (lv_val_0_0= 'isConflicting' )
            {
            // InternalFml.g:7523:4: (lv_val_0_0= 'isConflicting' )
            // InternalFml.g:7524:5: lv_val_0_0= 'isConflicting'
            {
            lv_val_0_0=(Token)match(input,140,FOLLOW_25); 

            					newLeafNode(lv_val_0_0, grammarAccess.getIsConflictingAccess().getValIsConflictingKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIsConflictingRule());
            					}
            					setWithLastConsumed(current, "val", lv_val_0_0, "isConflicting");
            				

            }


            }

            // InternalFml.g:7536:3: ( (lv_var_1_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:7537:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:7537:4: (lv_var_1_0= ruleFML_IDENTIFIER )
            // InternalFml.g:7538:5: lv_var_1_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getIsConflictingAccess().getVarFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_var_1_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIsConflictingRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_1_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIsConflicting"


    // $ANTLR start "entryRuleListing"
    // InternalFml.g:7559:1: entryRuleListing returns [EObject current=null] : iv_ruleListing= ruleListing EOF ;
    public final EObject entryRuleListing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListing = null;


        try {
            // InternalFml.g:7559:48: (iv_ruleListing= ruleListing EOF )
            // InternalFml.g:7560:2: iv_ruleListing= ruleListing EOF
            {
             newCompositeNode(grammarAccess.getListingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleListing=ruleListing();

            state._fsp--;

             current =iv_ruleListing; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleListing"


    // $ANTLR start "ruleListing"
    // InternalFml.g:7566:1: ruleListing returns [EObject current=null] : ( ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) ) ( (lv_opt_1_0= ruleOPT_LISTING ) )? ) ;
    public final EObject ruleListing() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;
        Enumerator lv_opt_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7572:2: ( ( ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) ) ( (lv_opt_1_0= ruleOPT_LISTING ) )? ) )
            // InternalFml.g:7573:2: ( ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) ) ( (lv_opt_1_0= ruleOPT_LISTING ) )? )
            {
            // InternalFml.g:7573:2: ( ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) ) ( (lv_opt_1_0= ruleOPT_LISTING ) )? )
            // InternalFml.g:7574:3: ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) ) ( (lv_opt_1_0= ruleOPT_LISTING ) )?
            {
            // InternalFml.g:7574:3: ( ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) ) )
            // InternalFml.g:7575:4: ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) )
            {
            // InternalFml.g:7575:4: ( (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' ) )
            // InternalFml.g:7576:5: (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' )
            {
            // InternalFml.g:7576:5: (lv_val_0_1= 'ls' | lv_val_0_2= 'vars' )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==141) ) {
                alt86=1;
            }
            else if ( (LA86_0==142) ) {
                alt86=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // InternalFml.g:7577:6: lv_val_0_1= 'ls'
                    {
                    lv_val_0_1=(Token)match(input,141,FOLLOW_75); 

                    						newLeafNode(lv_val_0_1, grammarAccess.getListingAccess().getValLsKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getListingRule());
                    						}
                    						setWithLastConsumed(current, "val", lv_val_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:7588:6: lv_val_0_2= 'vars'
                    {
                    lv_val_0_2=(Token)match(input,142,FOLLOW_75); 

                    						newLeafNode(lv_val_0_2, grammarAccess.getListingAccess().getValVarsKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getListingRule());
                    						}
                    						setWithLastConsumed(current, "val", lv_val_0_2, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:7601:3: ( (lv_opt_1_0= ruleOPT_LISTING ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( ((LA87_0>=214 && LA87_0<=216)) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalFml.g:7602:4: (lv_opt_1_0= ruleOPT_LISTING )
                    {
                    // InternalFml.g:7602:4: (lv_opt_1_0= ruleOPT_LISTING )
                    // InternalFml.g:7603:5: lv_opt_1_0= ruleOPT_LISTING
                    {

                    					newCompositeNode(grammarAccess.getListingAccess().getOptOPT_LISTINGEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_opt_1_0=ruleOPT_LISTING();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getListingRule());
                    					}
                    					set(
                    						current,
                    						"opt",
                    						lv_opt_1_0,
                    						"org.xtext.example.mydsl.Fml.OPT_LISTING");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleListing"


    // $ANTLR start "entryRuleState"
    // InternalFml.g:7624:1: entryRuleState returns [EObject current=null] : iv_ruleState= ruleState EOF ;
    public final EObject entryRuleState() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleState = null;


        try {
            // InternalFml.g:7624:46: (iv_ruleState= ruleState EOF )
            // InternalFml.g:7625:2: iv_ruleState= ruleState EOF
            {
             newCompositeNode(grammarAccess.getStateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleState=ruleState();

            state._fsp--;

             current =iv_ruleState; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleState"


    // $ANTLR start "ruleState"
    // InternalFml.g:7631:1: ruleState returns [EObject current=null] : ( ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) ) ) ;
    public final EObject ruleState() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;


        	enterRule();

        try {
            // InternalFml.g:7637:2: ( ( ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) ) ) )
            // InternalFml.g:7638:2: ( ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) ) )
            {
            // InternalFml.g:7638:2: ( ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) ) )
            // InternalFml.g:7639:3: ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) )
            {
            // InternalFml.g:7639:3: ( (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' ) )
            // InternalFml.g:7640:4: (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' )
            {
            // InternalFml.g:7640:4: (lv_val_0_1= 'memory' | lv_val_0_2= 'cpu' )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==143) ) {
                alt88=1;
            }
            else if ( (LA88_0==144) ) {
                alt88=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }
            switch (alt88) {
                case 1 :
                    // InternalFml.g:7641:5: lv_val_0_1= 'memory'
                    {
                    lv_val_0_1=(Token)match(input,143,FOLLOW_2); 

                    					newLeafNode(lv_val_0_1, grammarAccess.getStateAccess().getValMemoryKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStateRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_1, null);
                    				

                    }
                    break;
                case 2 :
                    // InternalFml.g:7652:5: lv_val_0_2= 'cpu'
                    {
                    lv_val_0_2=(Token)match(input,144,FOLLOW_2); 

                    					newLeafNode(lv_val_0_2, grammarAccess.getStateAccess().getValCpuKeyword_0_1());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStateRule());
                    					}
                    					setWithLastConsumed(current, "val", lv_val_0_2, null);
                    				

                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleState"


    // $ANTLR start "entryRuleCopyVariable"
    // InternalFml.g:7668:1: entryRuleCopyVariable returns [EObject current=null] : iv_ruleCopyVariable= ruleCopyVariable EOF ;
    public final EObject entryRuleCopyVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCopyVariable = null;


        try {
            // InternalFml.g:7668:53: (iv_ruleCopyVariable= ruleCopyVariable EOF )
            // InternalFml.g:7669:2: iv_ruleCopyVariable= ruleCopyVariable EOF
            {
             newCompositeNode(grammarAccess.getCopyVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCopyVariable=ruleCopyVariable();

            state._fsp--;

             current =iv_ruleCopyVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCopyVariable"


    // $ANTLR start "ruleCopyVariable"
    // InternalFml.g:7675:1: ruleCopyVariable returns [EObject current=null] : ( (otherlv_0= 'copy' | otherlv_1= 'cp' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleCopyVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_vid_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7681:2: ( ( (otherlv_0= 'copy' | otherlv_1= 'cp' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:7682:2: ( (otherlv_0= 'copy' | otherlv_1= 'cp' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:7682:2: ( (otherlv_0= 'copy' | otherlv_1= 'cp' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:7683:3: (otherlv_0= 'copy' | otherlv_1= 'cp' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) )
            {
            // InternalFml.g:7683:3: (otherlv_0= 'copy' | otherlv_1= 'cp' )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==145) ) {
                alt89=1;
            }
            else if ( (LA89_0==146) ) {
                alt89=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }
            switch (alt89) {
                case 1 :
                    // InternalFml.g:7684:4: otherlv_0= 'copy'
                    {
                    otherlv_0=(Token)match(input,145,FOLLOW_25); 

                    				newLeafNode(otherlv_0, grammarAccess.getCopyVariableAccess().getCopyKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalFml.g:7689:4: otherlv_1= 'cp'
                    {
                    otherlv_1=(Token)match(input,146,FOLLOW_25); 

                    				newLeafNode(otherlv_1, grammarAccess.getCopyVariableAccess().getCpKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalFml.g:7694:3: ( (lv_vid_2_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:7695:4: (lv_vid_2_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:7695:4: (lv_vid_2_0= ruleFML_IDENTIFIER )
            // InternalFml.g:7696:5: lv_vid_2_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getCopyVariableAccess().getVidFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_vid_2_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCopyVariableRule());
            					}
            					set(
            						current,
            						"vid",
            						lv_vid_2_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCopyVariable"


    // $ANTLR start "entryRuleRemoveVariable"
    // InternalFml.g:7717:1: entryRuleRemoveVariable returns [EObject current=null] : iv_ruleRemoveVariable= ruleRemoveVariable EOF ;
    public final EObject entryRuleRemoveVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRemoveVariable = null;


        try {
            // InternalFml.g:7717:55: (iv_ruleRemoveVariable= ruleRemoveVariable EOF )
            // InternalFml.g:7718:2: iv_ruleRemoveVariable= ruleRemoveVariable EOF
            {
             newCompositeNode(grammarAccess.getRemoveVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRemoveVariable=ruleRemoveVariable();

            state._fsp--;

             current =iv_ruleRemoveVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRemoveVariable"


    // $ANTLR start "ruleRemoveVariable"
    // InternalFml.g:7724:1: ruleRemoveVariable returns [EObject current=null] : ( (otherlv_0= 'removeVariable' | otherlv_1= 'rm' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) ) ;
    public final EObject ruleRemoveVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_vid_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7730:2: ( ( (otherlv_0= 'removeVariable' | otherlv_1= 'rm' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) ) )
            // InternalFml.g:7731:2: ( (otherlv_0= 'removeVariable' | otherlv_1= 'rm' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) )
            {
            // InternalFml.g:7731:2: ( (otherlv_0= 'removeVariable' | otherlv_1= 'rm' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) ) )
            // InternalFml.g:7732:3: (otherlv_0= 'removeVariable' | otherlv_1= 'rm' ) ( (lv_vid_2_0= ruleFML_IDENTIFIER ) )
            {
            // InternalFml.g:7732:3: (otherlv_0= 'removeVariable' | otherlv_1= 'rm' )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==147) ) {
                alt90=1;
            }
            else if ( (LA90_0==148) ) {
                alt90=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // InternalFml.g:7733:4: otherlv_0= 'removeVariable'
                    {
                    otherlv_0=(Token)match(input,147,FOLLOW_25); 

                    				newLeafNode(otherlv_0, grammarAccess.getRemoveVariableAccess().getRemoveVariableKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalFml.g:7738:4: otherlv_1= 'rm'
                    {
                    otherlv_1=(Token)match(input,148,FOLLOW_25); 

                    				newLeafNode(otherlv_1, grammarAccess.getRemoveVariableAccess().getRmKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalFml.g:7743:3: ( (lv_vid_2_0= ruleFML_IDENTIFIER ) )
            // InternalFml.g:7744:4: (lv_vid_2_0= ruleFML_IDENTIFIER )
            {
            // InternalFml.g:7744:4: (lv_vid_2_0= ruleFML_IDENTIFIER )
            // InternalFml.g:7745:5: lv_vid_2_0= ruleFML_IDENTIFIER
            {

            					newCompositeNode(grammarAccess.getRemoveVariableAccess().getVidFML_IDENTIFIERParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_vid_2_0=ruleFML_IDENTIFIER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRemoveVariableRule());
            					}
            					set(
            						current,
            						"vid",
            						lv_vid_2_0,
            						"org.xtext.example.mydsl.Fml.FML_IDENTIFIER");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRemoveVariable"


    // $ANTLR start "entryRuleConvert"
    // InternalFml.g:7766:1: entryRuleConvert returns [EObject current=null] : iv_ruleConvert= ruleConvert EOF ;
    public final EObject entryRuleConvert() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConvert = null;


        try {
            // InternalFml.g:7766:48: (iv_ruleConvert= ruleConvert EOF )
            // InternalFml.g:7767:2: iv_ruleConvert= ruleConvert EOF
            {
             newCompositeNode(grammarAccess.getConvertRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConvert=ruleConvert();

            state._fsp--;

             current =iv_ruleConvert; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConvert"


    // $ANTLR start "ruleConvert"
    // InternalFml.g:7773:1: ruleConvert returns [EObject current=null] : (otherlv_0= 'convert' ( (lv_v_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_format_3_0= ruleFMFormat ) ) ) ;
    public final EObject ruleConvert() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_v_1_0 = null;

        Enumerator lv_format_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7779:2: ( (otherlv_0= 'convert' ( (lv_v_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_format_3_0= ruleFMFormat ) ) ) )
            // InternalFml.g:7780:2: (otherlv_0= 'convert' ( (lv_v_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_format_3_0= ruleFMFormat ) ) )
            {
            // InternalFml.g:7780:2: (otherlv_0= 'convert' ( (lv_v_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_format_3_0= ruleFMFormat ) ) )
            // InternalFml.g:7781:3: otherlv_0= 'convert' ( (lv_v_1_0= ruleFMCommand ) ) otherlv_2= 'into' ( (lv_format_3_0= ruleFMFormat ) )
            {
            otherlv_0=(Token)match(input,149,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getConvertAccess().getConvertKeyword_0());
            		
            // InternalFml.g:7785:3: ( (lv_v_1_0= ruleFMCommand ) )
            // InternalFml.g:7786:4: (lv_v_1_0= ruleFMCommand )
            {
            // InternalFml.g:7786:4: (lv_v_1_0= ruleFMCommand )
            // InternalFml.g:7787:5: lv_v_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getConvertAccess().getVFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_66);
            lv_v_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConvertRule());
            					}
            					set(
            						current,
            						"v",
            						lv_v_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,91,FOLLOW_76); 

            			newLeafNode(otherlv_2, grammarAccess.getConvertAccess().getIntoKeyword_2());
            		
            // InternalFml.g:7808:3: ( (lv_format_3_0= ruleFMFormat ) )
            // InternalFml.g:7809:4: (lv_format_3_0= ruleFMFormat )
            {
            // InternalFml.g:7809:4: (lv_format_3_0= ruleFMFormat )
            // InternalFml.g:7810:5: lv_format_3_0= ruleFMFormat
            {

            					newCompositeNode(grammarAccess.getConvertAccess().getFormatFMFormatEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_format_3_0=ruleFMFormat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConvertRule());
            					}
            					set(
            						current,
            						"format",
            						lv_format_3_0,
            						"org.xtext.example.mydsl.Fml.FMFormat");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConvert"


    // $ANTLR start "entryRuleFMLSave"
    // InternalFml.g:7831:1: entryRuleFMLSave returns [EObject current=null] : iv_ruleFMLSave= ruleFMLSave EOF ;
    public final EObject entryRuleFMLSave() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFMLSave = null;


        try {
            // InternalFml.g:7831:48: (iv_ruleFMLSave= ruleFMLSave EOF )
            // InternalFml.g:7832:2: iv_ruleFMLSave= ruleFMLSave EOF
            {
             newCompositeNode(grammarAccess.getFMLSaveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFMLSave=ruleFMLSave();

            state._fsp--;

             current =iv_ruleFMLSave; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFMLSave"


    // $ANTLR start "ruleFMLSave"
    // InternalFml.g:7838:1: ruleFMLSave returns [EObject current=null] : ( (otherlv_0= 'save' | otherlv_1= 'serialize' ) ( (lv_v_2_0= ruleFMCommand ) ) otherlv_3= 'into' ( (lv_format_4_0= ruleFMFormat ) ) ) ;
    public final EObject ruleFMLSave() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_v_2_0 = null;

        Enumerator lv_format_4_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7844:2: ( ( (otherlv_0= 'save' | otherlv_1= 'serialize' ) ( (lv_v_2_0= ruleFMCommand ) ) otherlv_3= 'into' ( (lv_format_4_0= ruleFMFormat ) ) ) )
            // InternalFml.g:7845:2: ( (otherlv_0= 'save' | otherlv_1= 'serialize' ) ( (lv_v_2_0= ruleFMCommand ) ) otherlv_3= 'into' ( (lv_format_4_0= ruleFMFormat ) ) )
            {
            // InternalFml.g:7845:2: ( (otherlv_0= 'save' | otherlv_1= 'serialize' ) ( (lv_v_2_0= ruleFMCommand ) ) otherlv_3= 'into' ( (lv_format_4_0= ruleFMFormat ) ) )
            // InternalFml.g:7846:3: (otherlv_0= 'save' | otherlv_1= 'serialize' ) ( (lv_v_2_0= ruleFMCommand ) ) otherlv_3= 'into' ( (lv_format_4_0= ruleFMFormat ) )
            {
            // InternalFml.g:7846:3: (otherlv_0= 'save' | otherlv_1= 'serialize' )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==150) ) {
                alt91=1;
            }
            else if ( (LA91_0==151) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // InternalFml.g:7847:4: otherlv_0= 'save'
                    {
                    otherlv_0=(Token)match(input,150,FOLLOW_19); 

                    				newLeafNode(otherlv_0, grammarAccess.getFMLSaveAccess().getSaveKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalFml.g:7852:4: otherlv_1= 'serialize'
                    {
                    otherlv_1=(Token)match(input,151,FOLLOW_19); 

                    				newLeafNode(otherlv_1, grammarAccess.getFMLSaveAccess().getSerializeKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalFml.g:7857:3: ( (lv_v_2_0= ruleFMCommand ) )
            // InternalFml.g:7858:4: (lv_v_2_0= ruleFMCommand )
            {
            // InternalFml.g:7858:4: (lv_v_2_0= ruleFMCommand )
            // InternalFml.g:7859:5: lv_v_2_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getFMLSaveAccess().getVFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_66);
            lv_v_2_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFMLSaveRule());
            					}
            					set(
            						current,
            						"v",
            						lv_v_2_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,91,FOLLOW_76); 

            			newLeafNode(otherlv_3, grammarAccess.getFMLSaveAccess().getIntoKeyword_2());
            		
            // InternalFml.g:7880:3: ( (lv_format_4_0= ruleFMFormat ) )
            // InternalFml.g:7881:4: (lv_format_4_0= ruleFMFormat )
            {
            // InternalFml.g:7881:4: (lv_format_4_0= ruleFMFormat )
            // InternalFml.g:7882:5: lv_format_4_0= ruleFMFormat
            {

            					newCompositeNode(grammarAccess.getFMLSaveAccess().getFormatFMFormatEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_format_4_0=ruleFMFormat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFMLSaveRule());
            					}
            					set(
            						current,
            						"format",
            						lv_format_4_0,
            						"org.xtext.example.mydsl.Fml.FMFormat");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMLSave"


    // $ANTLR start "entryRuleHierarchy"
    // InternalFml.g:7903:1: entryRuleHierarchy returns [EObject current=null] : iv_ruleHierarchy= ruleHierarchy EOF ;
    public final EObject entryRuleHierarchy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHierarchy = null;


        try {
            // InternalFml.g:7903:50: (iv_ruleHierarchy= ruleHierarchy EOF )
            // InternalFml.g:7904:2: iv_ruleHierarchy= ruleHierarchy EOF
            {
             newCompositeNode(grammarAccess.getHierarchyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHierarchy=ruleHierarchy();

            state._fsp--;

             current =iv_ruleHierarchy; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHierarchy"


    // $ANTLR start "ruleHierarchy"
    // InternalFml.g:7910:1: ruleHierarchy returns [EObject current=null] : (otherlv_0= 'hierarchy' ( (lv_fm_1_0= ruleFMCommand ) ) ) ;
    public final EObject ruleHierarchy() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fm_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7916:2: ( (otherlv_0= 'hierarchy' ( (lv_fm_1_0= ruleFMCommand ) ) ) )
            // InternalFml.g:7917:2: (otherlv_0= 'hierarchy' ( (lv_fm_1_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:7917:2: (otherlv_0= 'hierarchy' ( (lv_fm_1_0= ruleFMCommand ) ) )
            // InternalFml.g:7918:3: otherlv_0= 'hierarchy' ( (lv_fm_1_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,152,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getHierarchyAccess().getHierarchyKeyword_0());
            		
            // InternalFml.g:7922:3: ( (lv_fm_1_0= ruleFMCommand ) )
            // InternalFml.g:7923:4: (lv_fm_1_0= ruleFMCommand )
            {
            // InternalFml.g:7923:4: (lv_fm_1_0= ruleFMCommand )
            // InternalFml.g:7924:5: lv_fm_1_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getHierarchyAccess().getFmFMCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_1_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getHierarchyRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_1_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHierarchy"


    // $ANTLR start "entryRulePrinterUtility"
    // InternalFml.g:7945:1: entryRulePrinterUtility returns [EObject current=null] : iv_rulePrinterUtility= rulePrinterUtility EOF ;
    public final EObject entryRulePrinterUtility() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrinterUtility = null;


        try {
            // InternalFml.g:7945:55: (iv_rulePrinterUtility= rulePrinterUtility EOF )
            // InternalFml.g:7946:2: iv_rulePrinterUtility= rulePrinterUtility EOF
            {
             newCompositeNode(grammarAccess.getPrinterUtilityRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrinterUtility=rulePrinterUtility();

            state._fsp--;

             current =iv_rulePrinterUtility; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrinterUtility"


    // $ANTLR start "rulePrinterUtility"
    // InternalFml.g:7952:1: rulePrinterUtility returns [EObject current=null] : ( ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) ) ( (lv_arg_1_0= ruleLArgs ) ) ) ;
    public final EObject rulePrinterUtility() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        EObject lv_arg_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:7958:2: ( ( ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) ) ( (lv_arg_1_0= ruleLArgs ) ) ) )
            // InternalFml.g:7959:2: ( ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) ) ( (lv_arg_1_0= ruleLArgs ) ) )
            {
            // InternalFml.g:7959:2: ( ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) ) ( (lv_arg_1_0= ruleLArgs ) ) )
            // InternalFml.g:7960:3: ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) ) ( (lv_arg_1_0= ruleLArgs ) )
            {
            // InternalFml.g:7960:3: ( ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) ) )
            // InternalFml.g:7961:4: ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) )
            {
            // InternalFml.g:7961:4: ( (lv_op_0_1= 'print' | lv_op_0_2= 'println' ) )
            // InternalFml.g:7962:5: (lv_op_0_1= 'print' | lv_op_0_2= 'println' )
            {
            // InternalFml.g:7962:5: (lv_op_0_1= 'print' | lv_op_0_2= 'println' )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==153) ) {
                alt92=1;
            }
            else if ( (LA92_0==154) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // InternalFml.g:7963:6: lv_op_0_1= 'print'
                    {
                    lv_op_0_1=(Token)match(input,153,FOLLOW_29); 

                    						newLeafNode(lv_op_0_1, grammarAccess.getPrinterUtilityAccess().getOpPrintKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPrinterUtilityRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:7974:6: lv_op_0_2= 'println'
                    {
                    lv_op_0_2=(Token)match(input,154,FOLLOW_29); 

                    						newLeafNode(lv_op_0_2, grammarAccess.getPrinterUtilityAccess().getOpPrintlnKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPrinterUtilityRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_0_2, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalFml.g:7987:3: ( (lv_arg_1_0= ruleLArgs ) )
            // InternalFml.g:7988:4: (lv_arg_1_0= ruleLArgs )
            {
            // InternalFml.g:7988:4: (lv_arg_1_0= ruleLArgs )
            // InternalFml.g:7989:5: lv_arg_1_0= ruleLArgs
            {

            					newCompositeNode(grammarAccess.getPrinterUtilityAccess().getArgLArgsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_arg_1_0=ruleLArgs();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPrinterUtilityRule());
            					}
            					set(
            						current,
            						"arg",
            						lv_arg_1_0,
            						"org.xtext.example.mydsl.Fml.LArgs");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrinterUtility"


    // $ANTLR start "entryRuleLArgs"
    // InternalFml.g:8010:1: entryRuleLArgs returns [EObject current=null] : iv_ruleLArgs= ruleLArgs EOF ;
    public final EObject entryRuleLArgs() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLArgs = null;


        try {
            // InternalFml.g:8010:46: (iv_ruleLArgs= ruleLArgs EOF )
            // InternalFml.g:8011:2: iv_ruleLArgs= ruleLArgs EOF
            {
             newCompositeNode(grammarAccess.getLArgsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLArgs=ruleLArgs();

            state._fsp--;

             current =iv_ruleLArgs; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLArgs"


    // $ANTLR start "ruleLArgs"
    // InternalFml.g:8017:1: ruleLArgs returns [EObject current=null] : ( ( (lv_args_0_0= ruleCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )* ) ;
    public final EObject ruleLArgs() throws RecognitionException {
        EObject current = null;

        Token this_COMMA_1=null;
        EObject lv_args_0_0 = null;

        EObject lv_args_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8023:2: ( ( ( (lv_args_0_0= ruleCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )* ) )
            // InternalFml.g:8024:2: ( ( (lv_args_0_0= ruleCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )* )
            {
            // InternalFml.g:8024:2: ( ( (lv_args_0_0= ruleCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )* )
            // InternalFml.g:8025:3: ( (lv_args_0_0= ruleCommand ) ) (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )*
            {
            // InternalFml.g:8025:3: ( (lv_args_0_0= ruleCommand ) )
            // InternalFml.g:8026:4: (lv_args_0_0= ruleCommand )
            {
            // InternalFml.g:8026:4: (lv_args_0_0= ruleCommand )
            // InternalFml.g:8027:5: lv_args_0_0= ruleCommand
            {

            					newCompositeNode(grammarAccess.getLArgsAccess().getArgsCommandParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_44);
            lv_args_0_0=ruleCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLArgsRule());
            					}
            					add(
            						current,
            						"args",
            						lv_args_0_0,
            						"org.xtext.example.mydsl.Fml.Command");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:8044:3: (this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) ) )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==RULE_COMMA) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // InternalFml.g:8045:4: this_COMMA_1= RULE_COMMA ( (lv_args_2_0= ruleCommand ) )
            	    {
            	    this_COMMA_1=(Token)match(input,RULE_COMMA,FOLLOW_29); 

            	    				newLeafNode(this_COMMA_1, grammarAccess.getLArgsAccess().getCOMMATerminalRuleCall_1_0());
            	    			
            	    // InternalFml.g:8049:4: ( (lv_args_2_0= ruleCommand ) )
            	    // InternalFml.g:8050:5: (lv_args_2_0= ruleCommand )
            	    {
            	    // InternalFml.g:8050:5: (lv_args_2_0= ruleCommand )
            	    // InternalFml.g:8051:6: lv_args_2_0= ruleCommand
            	    {

            	    						newCompositeNode(grammarAccess.getLArgsAccess().getArgsCommandParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_44);
            	    lv_args_2_0=ruleCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getLArgsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"args",
            	    							lv_args_2_0,
            	    							"org.xtext.example.mydsl.Fml.Command");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop93;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLArgs"


    // $ANTLR start "entryRuleGDisplay"
    // InternalFml.g:8073:1: entryRuleGDisplay returns [EObject current=null] : iv_ruleGDisplay= ruleGDisplay EOF ;
    public final EObject entryRuleGDisplay() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGDisplay = null;


        try {
            // InternalFml.g:8073:49: (iv_ruleGDisplay= ruleGDisplay EOF )
            // InternalFml.g:8074:2: iv_ruleGDisplay= ruleGDisplay EOF
            {
             newCompositeNode(grammarAccess.getGDisplayRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGDisplay=ruleGDisplay();

            state._fsp--;

             current =iv_ruleGDisplay; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGDisplay"


    // $ANTLR start "ruleGDisplay"
    // InternalFml.g:8080:1: ruleGDisplay returns [EObject current=null] : ( ( (lv_cmdDisplay_0_0= 'gdisplay' ) ) ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) ) ) ;
    public final EObject ruleGDisplay() throws RecognitionException {
        EObject current = null;

        Token lv_cmdDisplay_0_0=null;
        EObject lv_var_1_1 = null;

        EObject lv_var_1_2 = null;



        	enterRule();

        try {
            // InternalFml.g:8086:2: ( ( ( (lv_cmdDisplay_0_0= 'gdisplay' ) ) ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) ) ) )
            // InternalFml.g:8087:2: ( ( (lv_cmdDisplay_0_0= 'gdisplay' ) ) ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) ) )
            {
            // InternalFml.g:8087:2: ( ( (lv_cmdDisplay_0_0= 'gdisplay' ) ) ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) ) )
            // InternalFml.g:8088:3: ( (lv_cmdDisplay_0_0= 'gdisplay' ) ) ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) )
            {
            // InternalFml.g:8088:3: ( (lv_cmdDisplay_0_0= 'gdisplay' ) )
            // InternalFml.g:8089:4: (lv_cmdDisplay_0_0= 'gdisplay' )
            {
            // InternalFml.g:8089:4: (lv_cmdDisplay_0_0= 'gdisplay' )
            // InternalFml.g:8090:5: lv_cmdDisplay_0_0= 'gdisplay'
            {
            lv_cmdDisplay_0_0=(Token)match(input,155,FOLLOW_32); 

            					newLeafNode(lv_cmdDisplay_0_0, grammarAccess.getGDisplayAccess().getCmdDisplayGdisplayKeyword_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGDisplayRule());
            					}
            					setWithLastConsumed(current, "cmdDisplay", lv_cmdDisplay_0_0, "gdisplay");
            				

            }


            }

            // InternalFml.g:8102:3: ( ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) ) )
            // InternalFml.g:8103:4: ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) )
            {
            // InternalFml.g:8103:4: ( (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand ) )
            // InternalFml.g:8104:5: (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand )
            {
            // InternalFml.g:8104:5: (lv_var_1_1= ruleFMCommand | lv_var_1_2= ruleConfigurationCommand )
            int alt94=2;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt94=1;
                }
                break;
            case 80:
            case 93:
            case 95:
            case 97:
            case 106:
            case 107:
            case 114:
            case 128:
            case 145:
            case 146:
            case 152:
            case 165:
            case 166:
            case 168:
                {
                alt94=1;
                }
                break;
            case 119:
                {
                alt94=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // InternalFml.g:8105:6: lv_var_1_1= ruleFMCommand
                    {

                    						newCompositeNode(grammarAccess.getGDisplayAccess().getVarFMCommandParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_var_1_1=ruleFMCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getGDisplayRule());
                    						}
                    						set(
                    							current,
                    							"var",
                    							lv_var_1_1,
                    							"org.xtext.example.mydsl.Fml.FMCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalFml.g:8121:6: lv_var_1_2= ruleConfigurationCommand
                    {

                    						newCompositeNode(grammarAccess.getGDisplayAccess().getVarConfigurationCommandParserRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_2);
                    lv_var_1_2=ruleConfigurationCommand();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getGDisplayRule());
                    						}
                    						set(
                    							current,
                    							"var",
                    							lv_var_1_2,
                    							"org.xtext.example.mydsl.Fml.ConfigurationCommand");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGDisplay"


    // $ANTLR start "entryRuleGListing"
    // InternalFml.g:8143:1: entryRuleGListing returns [EObject current=null] : iv_ruleGListing= ruleGListing EOF ;
    public final EObject entryRuleGListing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGListing = null;


        try {
            // InternalFml.g:8143:49: (iv_ruleGListing= ruleGListing EOF )
            // InternalFml.g:8144:2: iv_ruleGListing= ruleGListing EOF
            {
             newCompositeNode(grammarAccess.getGListingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGListing=ruleGListing();

            state._fsp--;

             current =iv_ruleGListing; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGListing"


    // $ANTLR start "ruleGListing"
    // InternalFml.g:8150:1: ruleGListing returns [EObject current=null] : ( ( (lv_cmd_0_0= 'glisting' ) ) | (otherlv_1= 'gls' () ) ) ;
    public final EObject ruleGListing() throws RecognitionException {
        EObject current = null;

        Token lv_cmd_0_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalFml.g:8156:2: ( ( ( (lv_cmd_0_0= 'glisting' ) ) | (otherlv_1= 'gls' () ) ) )
            // InternalFml.g:8157:2: ( ( (lv_cmd_0_0= 'glisting' ) ) | (otherlv_1= 'gls' () ) )
            {
            // InternalFml.g:8157:2: ( ( (lv_cmd_0_0= 'glisting' ) ) | (otherlv_1= 'gls' () ) )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==156) ) {
                alt95=1;
            }
            else if ( (LA95_0==157) ) {
                alt95=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // InternalFml.g:8158:3: ( (lv_cmd_0_0= 'glisting' ) )
                    {
                    // InternalFml.g:8158:3: ( (lv_cmd_0_0= 'glisting' ) )
                    // InternalFml.g:8159:4: (lv_cmd_0_0= 'glisting' )
                    {
                    // InternalFml.g:8159:4: (lv_cmd_0_0= 'glisting' )
                    // InternalFml.g:8160:5: lv_cmd_0_0= 'glisting'
                    {
                    lv_cmd_0_0=(Token)match(input,156,FOLLOW_2); 

                    					newLeafNode(lv_cmd_0_0, grammarAccess.getGListingAccess().getCmdGlistingKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getGListingRule());
                    					}
                    					setWithLastConsumed(current, "cmd", lv_cmd_0_0, "glisting");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:8173:3: (otherlv_1= 'gls' () )
                    {
                    // InternalFml.g:8173:3: (otherlv_1= 'gls' () )
                    // InternalFml.g:8174:4: otherlv_1= 'gls' ()
                    {
                    otherlv_1=(Token)match(input,157,FOLLOW_2); 

                    				newLeafNode(otherlv_1, grammarAccess.getGListingAccess().getGlsKeyword_1_0());
                    			
                    // InternalFml.g:8178:4: ()
                    // InternalFml.g:8179:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getGListingAccess().getGListingAction_1_1(),
                    						current);
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGListing"


    // $ANTLR start "entryRuleModifyVOperator"
    // InternalFml.g:8190:1: entryRuleModifyVOperator returns [EObject current=null] : iv_ruleModifyVOperator= ruleModifyVOperator EOF ;
    public final EObject entryRuleModifyVOperator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModifyVOperator = null;


        try {
            // InternalFml.g:8190:56: (iv_ruleModifyVOperator= ruleModifyVOperator EOF )
            // InternalFml.g:8191:2: iv_ruleModifyVOperator= ruleModifyVOperator EOF
            {
             newCompositeNode(grammarAccess.getModifyVOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModifyVOperator=ruleModifyVOperator();

            state._fsp--;

             current =iv_ruleModifyVOperator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModifyVOperator"


    // $ANTLR start "ruleModifyVOperator"
    // InternalFml.g:8197:1: ruleModifyVOperator returns [EObject current=null] : (this_MandatoryEdit_0= ruleMandatoryEdit | this_OptionalEdit_1= ruleOptionalEdit | this_AlternativeEdit_2= ruleAlternativeEdit | this_OrEdit_3= ruleOrEdit ) ;
    public final EObject ruleModifyVOperator() throws RecognitionException {
        EObject current = null;

        EObject this_MandatoryEdit_0 = null;

        EObject this_OptionalEdit_1 = null;

        EObject this_AlternativeEdit_2 = null;

        EObject this_OrEdit_3 = null;



        	enterRule();

        try {
            // InternalFml.g:8203:2: ( (this_MandatoryEdit_0= ruleMandatoryEdit | this_OptionalEdit_1= ruleOptionalEdit | this_AlternativeEdit_2= ruleAlternativeEdit | this_OrEdit_3= ruleOrEdit ) )
            // InternalFml.g:8204:2: (this_MandatoryEdit_0= ruleMandatoryEdit | this_OptionalEdit_1= ruleOptionalEdit | this_AlternativeEdit_2= ruleAlternativeEdit | this_OrEdit_3= ruleOrEdit )
            {
            // InternalFml.g:8204:2: (this_MandatoryEdit_0= ruleMandatoryEdit | this_OptionalEdit_1= ruleOptionalEdit | this_AlternativeEdit_2= ruleAlternativeEdit | this_OrEdit_3= ruleOrEdit )
            int alt96=4;
            switch ( input.LA(1) ) {
            case 158:
                {
                alt96=1;
                }
                break;
            case 159:
                {
                alt96=2;
                }
                break;
            case 160:
                {
                alt96=3;
                }
                break;
            case 161:
                {
                alt96=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }

            switch (alt96) {
                case 1 :
                    // InternalFml.g:8205:3: this_MandatoryEdit_0= ruleMandatoryEdit
                    {

                    			newCompositeNode(grammarAccess.getModifyVOperatorAccess().getMandatoryEditParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_MandatoryEdit_0=ruleMandatoryEdit();

                    state._fsp--;


                    			current = this_MandatoryEdit_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:8214:3: this_OptionalEdit_1= ruleOptionalEdit
                    {

                    			newCompositeNode(grammarAccess.getModifyVOperatorAccess().getOptionalEditParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_OptionalEdit_1=ruleOptionalEdit();

                    state._fsp--;


                    			current = this_OptionalEdit_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:8223:3: this_AlternativeEdit_2= ruleAlternativeEdit
                    {

                    			newCompositeNode(grammarAccess.getModifyVOperatorAccess().getAlternativeEditParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_AlternativeEdit_2=ruleAlternativeEdit();

                    state._fsp--;


                    			current = this_AlternativeEdit_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:8232:3: this_OrEdit_3= ruleOrEdit
                    {

                    			newCompositeNode(grammarAccess.getModifyVOperatorAccess().getOrEditParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_OrEdit_3=ruleOrEdit();

                    state._fsp--;


                    			current = this_OrEdit_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModifyVOperator"


    // $ANTLR start "entryRuleMandatoryEdit"
    // InternalFml.g:8244:1: entryRuleMandatoryEdit returns [EObject current=null] : iv_ruleMandatoryEdit= ruleMandatoryEdit EOF ;
    public final EObject entryRuleMandatoryEdit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatoryEdit = null;


        try {
            // InternalFml.g:8244:54: (iv_ruleMandatoryEdit= ruleMandatoryEdit EOF )
            // InternalFml.g:8245:2: iv_ruleMandatoryEdit= ruleMandatoryEdit EOF
            {
             newCompositeNode(grammarAccess.getMandatoryEditRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMandatoryEdit=ruleMandatoryEdit();

            state._fsp--;

             current =iv_ruleMandatoryEdit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMandatoryEdit"


    // $ANTLR start "ruleMandatoryEdit"
    // InternalFml.g:8251:1: ruleMandatoryEdit returns [EObject current=null] : (otherlv_0= 'setMandatory' ( (lv_ft_1_0= ruleFTCommand ) ) ) ;
    public final EObject ruleMandatoryEdit() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_ft_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8257:2: ( (otherlv_0= 'setMandatory' ( (lv_ft_1_0= ruleFTCommand ) ) ) )
            // InternalFml.g:8258:2: (otherlv_0= 'setMandatory' ( (lv_ft_1_0= ruleFTCommand ) ) )
            {
            // InternalFml.g:8258:2: (otherlv_0= 'setMandatory' ( (lv_ft_1_0= ruleFTCommand ) ) )
            // InternalFml.g:8259:3: otherlv_0= 'setMandatory' ( (lv_ft_1_0= ruleFTCommand ) )
            {
            otherlv_0=(Token)match(input,158,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getMandatoryEditAccess().getSetMandatoryKeyword_0());
            		
            // InternalFml.g:8263:3: ( (lv_ft_1_0= ruleFTCommand ) )
            // InternalFml.g:8264:4: (lv_ft_1_0= ruleFTCommand )
            {
            // InternalFml.g:8264:4: (lv_ft_1_0= ruleFTCommand )
            // InternalFml.g:8265:5: lv_ft_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getMandatoryEditAccess().getFtFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_ft_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMandatoryEditRule());
            					}
            					set(
            						current,
            						"ft",
            						lv_ft_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMandatoryEdit"


    // $ANTLR start "entryRuleOptionalEdit"
    // InternalFml.g:8286:1: entryRuleOptionalEdit returns [EObject current=null] : iv_ruleOptionalEdit= ruleOptionalEdit EOF ;
    public final EObject entryRuleOptionalEdit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalEdit = null;


        try {
            // InternalFml.g:8286:53: (iv_ruleOptionalEdit= ruleOptionalEdit EOF )
            // InternalFml.g:8287:2: iv_ruleOptionalEdit= ruleOptionalEdit EOF
            {
             newCompositeNode(grammarAccess.getOptionalEditRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionalEdit=ruleOptionalEdit();

            state._fsp--;

             current =iv_ruleOptionalEdit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionalEdit"


    // $ANTLR start "ruleOptionalEdit"
    // InternalFml.g:8293:1: ruleOptionalEdit returns [EObject current=null] : (otherlv_0= 'setOptional' ( (lv_ft_1_0= ruleFTCommand ) ) ) ;
    public final EObject ruleOptionalEdit() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_ft_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8299:2: ( (otherlv_0= 'setOptional' ( (lv_ft_1_0= ruleFTCommand ) ) ) )
            // InternalFml.g:8300:2: (otherlv_0= 'setOptional' ( (lv_ft_1_0= ruleFTCommand ) ) )
            {
            // InternalFml.g:8300:2: (otherlv_0= 'setOptional' ( (lv_ft_1_0= ruleFTCommand ) ) )
            // InternalFml.g:8301:3: otherlv_0= 'setOptional' ( (lv_ft_1_0= ruleFTCommand ) )
            {
            otherlv_0=(Token)match(input,159,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getOptionalEditAccess().getSetOptionalKeyword_0());
            		
            // InternalFml.g:8305:3: ( (lv_ft_1_0= ruleFTCommand ) )
            // InternalFml.g:8306:4: (lv_ft_1_0= ruleFTCommand )
            {
            // InternalFml.g:8306:4: (lv_ft_1_0= ruleFTCommand )
            // InternalFml.g:8307:5: lv_ft_1_0= ruleFTCommand
            {

            					newCompositeNode(grammarAccess.getOptionalEditAccess().getFtFTCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_ft_1_0=ruleFTCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptionalEditRule());
            					}
            					set(
            						current,
            						"ft",
            						lv_ft_1_0,
            						"org.xtext.example.mydsl.Fml.FTCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionalEdit"


    // $ANTLR start "entryRuleAlternativeEdit"
    // InternalFml.g:8328:1: entryRuleAlternativeEdit returns [EObject current=null] : iv_ruleAlternativeEdit= ruleAlternativeEdit EOF ;
    public final EObject entryRuleAlternativeEdit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAlternativeEdit = null;


        try {
            // InternalFml.g:8328:56: (iv_ruleAlternativeEdit= ruleAlternativeEdit EOF )
            // InternalFml.g:8329:2: iv_ruleAlternativeEdit= ruleAlternativeEdit EOF
            {
             newCompositeNode(grammarAccess.getAlternativeEditRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAlternativeEdit=ruleAlternativeEdit();

            state._fsp--;

             current =iv_ruleAlternativeEdit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAlternativeEdit"


    // $ANTLR start "ruleAlternativeEdit"
    // InternalFml.g:8335:1: ruleAlternativeEdit returns [EObject current=null] : (otherlv_0= 'setAlternative' ( (lv_fts_1_0= ruleSetCommand ) ) ) ;
    public final EObject ruleAlternativeEdit() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fts_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8341:2: ( (otherlv_0= 'setAlternative' ( (lv_fts_1_0= ruleSetCommand ) ) ) )
            // InternalFml.g:8342:2: (otherlv_0= 'setAlternative' ( (lv_fts_1_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:8342:2: (otherlv_0= 'setAlternative' ( (lv_fts_1_0= ruleSetCommand ) ) )
            // InternalFml.g:8343:3: otherlv_0= 'setAlternative' ( (lv_fts_1_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,160,FOLLOW_29); 

            			newLeafNode(otherlv_0, grammarAccess.getAlternativeEditAccess().getSetAlternativeKeyword_0());
            		
            // InternalFml.g:8347:3: ( (lv_fts_1_0= ruleSetCommand ) )
            // InternalFml.g:8348:4: (lv_fts_1_0= ruleSetCommand )
            {
            // InternalFml.g:8348:4: (lv_fts_1_0= ruleSetCommand )
            // InternalFml.g:8349:5: lv_fts_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getAlternativeEditAccess().getFtsSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fts_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAlternativeEditRule());
            					}
            					set(
            						current,
            						"fts",
            						lv_fts_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAlternativeEdit"


    // $ANTLR start "entryRuleOrEdit"
    // InternalFml.g:8370:1: entryRuleOrEdit returns [EObject current=null] : iv_ruleOrEdit= ruleOrEdit EOF ;
    public final EObject entryRuleOrEdit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrEdit = null;


        try {
            // InternalFml.g:8370:47: (iv_ruleOrEdit= ruleOrEdit EOF )
            // InternalFml.g:8371:2: iv_ruleOrEdit= ruleOrEdit EOF
            {
             newCompositeNode(grammarAccess.getOrEditRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrEdit=ruleOrEdit();

            state._fsp--;

             current =iv_ruleOrEdit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrEdit"


    // $ANTLR start "ruleOrEdit"
    // InternalFml.g:8377:1: ruleOrEdit returns [EObject current=null] : (otherlv_0= 'setOr' ( (lv_fts_1_0= ruleSetCommand ) ) ) ;
    public final EObject ruleOrEdit() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_fts_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8383:2: ( (otherlv_0= 'setOr' ( (lv_fts_1_0= ruleSetCommand ) ) ) )
            // InternalFml.g:8384:2: (otherlv_0= 'setOr' ( (lv_fts_1_0= ruleSetCommand ) ) )
            {
            // InternalFml.g:8384:2: (otherlv_0= 'setOr' ( (lv_fts_1_0= ruleSetCommand ) ) )
            // InternalFml.g:8385:3: otherlv_0= 'setOr' ( (lv_fts_1_0= ruleSetCommand ) )
            {
            otherlv_0=(Token)match(input,161,FOLLOW_29); 

            			newLeafNode(otherlv_0, grammarAccess.getOrEditAccess().getSetOrKeyword_0());
            		
            // InternalFml.g:8389:3: ( (lv_fts_1_0= ruleSetCommand ) )
            // InternalFml.g:8390:4: (lv_fts_1_0= ruleSetCommand )
            {
            // InternalFml.g:8390:4: (lv_fts_1_0= ruleSetCommand )
            // InternalFml.g:8391:5: lv_fts_1_0= ruleSetCommand
            {

            					newCompositeNode(grammarAccess.getOrEditAccess().getFtsSetCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_fts_1_0=ruleSetCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOrEditRule());
            					}
            					set(
            						current,
            						"fts",
            						lv_fts_1_0,
            						"org.xtext.example.mydsl.Fml.SetCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrEdit"


    // $ANTLR start "entryRuleAddConstraint"
    // InternalFml.g:8412:1: entryRuleAddConstraint returns [EObject current=null] : iv_ruleAddConstraint= ruleAddConstraint EOF ;
    public final EObject entryRuleAddConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddConstraint = null;


        try {
            // InternalFml.g:8412:54: (iv_ruleAddConstraint= ruleAddConstraint EOF )
            // InternalFml.g:8413:2: iv_ruleAddConstraint= ruleAddConstraint EOF
            {
             newCompositeNode(grammarAccess.getAddConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddConstraint=ruleAddConstraint();

            state._fsp--;

             current =iv_ruleAddConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAddConstraint"


    // $ANTLR start "ruleAddConstraint"
    // InternalFml.g:8419:1: ruleAddConstraint returns [EObject current=null] : (otherlv_0= 'addConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'to' ( (lv_fm_3_0= ruleFMCommand ) ) ) ;
    public final EObject ruleAddConstraint() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_cst_1_0 = null;

        EObject lv_fm_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8425:2: ( (otherlv_0= 'addConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'to' ( (lv_fm_3_0= ruleFMCommand ) ) ) )
            // InternalFml.g:8426:2: (otherlv_0= 'addConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'to' ( (lv_fm_3_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:8426:2: (otherlv_0= 'addConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'to' ( (lv_fm_3_0= ruleFMCommand ) ) )
            // InternalFml.g:8427:3: otherlv_0= 'addConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'to' ( (lv_fm_3_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,162,FOLLOW_77); 

            			newLeafNode(otherlv_0, grammarAccess.getAddConstraintAccess().getAddConstraintKeyword_0());
            		
            // InternalFml.g:8431:3: ( (lv_cst_1_0= ruleConstraintCommand ) )
            // InternalFml.g:8432:4: (lv_cst_1_0= ruleConstraintCommand )
            {
            // InternalFml.g:8432:4: (lv_cst_1_0= ruleConstraintCommand )
            // InternalFml.g:8433:5: lv_cst_1_0= ruleConstraintCommand
            {

            					newCompositeNode(grammarAccess.getAddConstraintAccess().getCstConstraintCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_78);
            lv_cst_1_0=ruleConstraintCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAddConstraintRule());
            					}
            					set(
            						current,
            						"cst",
            						lv_cst_1_0,
            						"org.xtext.example.mydsl.Fml.ConstraintCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,163,FOLLOW_19); 

            			newLeafNode(otherlv_2, grammarAccess.getAddConstraintAccess().getToKeyword_2());
            		
            // InternalFml.g:8454:3: ( (lv_fm_3_0= ruleFMCommand ) )
            // InternalFml.g:8455:4: (lv_fm_3_0= ruleFMCommand )
            {
            // InternalFml.g:8455:4: (lv_fm_3_0= ruleFMCommand )
            // InternalFml.g:8456:5: lv_fm_3_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getAddConstraintAccess().getFmFMCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_3_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAddConstraintRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_3_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddConstraint"


    // $ANTLR start "entryRuleRemoveConstraint"
    // InternalFml.g:8477:1: entryRuleRemoveConstraint returns [EObject current=null] : iv_ruleRemoveConstraint= ruleRemoveConstraint EOF ;
    public final EObject entryRuleRemoveConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRemoveConstraint = null;


        try {
            // InternalFml.g:8477:57: (iv_ruleRemoveConstraint= ruleRemoveConstraint EOF )
            // InternalFml.g:8478:2: iv_ruleRemoveConstraint= ruleRemoveConstraint EOF
            {
             newCompositeNode(grammarAccess.getRemoveConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRemoveConstraint=ruleRemoveConstraint();

            state._fsp--;

             current =iv_ruleRemoveConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRemoveConstraint"


    // $ANTLR start "ruleRemoveConstraint"
    // InternalFml.g:8484:1: ruleRemoveConstraint returns [EObject current=null] : (otherlv_0= 'removeConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'in' ( (lv_fm_3_0= ruleFMCommand ) ) ) ;
    public final EObject ruleRemoveConstraint() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_cst_1_0 = null;

        EObject lv_fm_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8490:2: ( (otherlv_0= 'removeConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'in' ( (lv_fm_3_0= ruleFMCommand ) ) ) )
            // InternalFml.g:8491:2: (otherlv_0= 'removeConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'in' ( (lv_fm_3_0= ruleFMCommand ) ) )
            {
            // InternalFml.g:8491:2: (otherlv_0= 'removeConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'in' ( (lv_fm_3_0= ruleFMCommand ) ) )
            // InternalFml.g:8492:3: otherlv_0= 'removeConstraint' ( (lv_cst_1_0= ruleConstraintCommand ) ) otherlv_2= 'in' ( (lv_fm_3_0= ruleFMCommand ) )
            {
            otherlv_0=(Token)match(input,164,FOLLOW_77); 

            			newLeafNode(otherlv_0, grammarAccess.getRemoveConstraintAccess().getRemoveConstraintKeyword_0());
            		
            // InternalFml.g:8496:3: ( (lv_cst_1_0= ruleConstraintCommand ) )
            // InternalFml.g:8497:4: (lv_cst_1_0= ruleConstraintCommand )
            {
            // InternalFml.g:8497:4: (lv_cst_1_0= ruleConstraintCommand )
            // InternalFml.g:8498:5: lv_cst_1_0= ruleConstraintCommand
            {

            					newCompositeNode(grammarAccess.getRemoveConstraintAccess().getCstConstraintCommandParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_26);
            lv_cst_1_0=ruleConstraintCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRemoveConstraintRule());
            					}
            					set(
            						current,
            						"cst",
            						lv_cst_1_0,
            						"org.xtext.example.mydsl.Fml.ConstraintCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,42,FOLLOW_19); 

            			newLeafNode(otherlv_2, grammarAccess.getRemoveConstraintAccess().getInKeyword_2());
            		
            // InternalFml.g:8519:3: ( (lv_fm_3_0= ruleFMCommand ) )
            // InternalFml.g:8520:4: (lv_fm_3_0= ruleFMCommand )
            {
            // InternalFml.g:8520:4: (lv_fm_3_0= ruleFMCommand )
            // InternalFml.g:8521:5: lv_fm_3_0= ruleFMCommand
            {

            					newCompositeNode(grammarAccess.getRemoveConstraintAccess().getFmFMCommandParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_fm_3_0=ruleFMCommand();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRemoveConstraintRule());
            					}
            					set(
            						current,
            						"fm",
            						lv_fm_3_0,
            						"org.xtext.example.mydsl.Fml.FMCommand");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRemoveConstraint"


    // $ANTLR start "entryRuleCNF"
    // InternalFml.g:8542:1: entryRuleCNF returns [EObject current=null] : iv_ruleCNF= ruleCNF EOF ;
    public final EObject entryRuleCNF() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCNF = null;


        try {
            // InternalFml.g:8542:44: (iv_ruleCNF= ruleCNF EOF )
            // InternalFml.g:8543:2: iv_ruleCNF= ruleCNF EOF
            {
             newCompositeNode(grammarAccess.getCNFRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCNF=ruleCNF();

            state._fsp--;

             current =iv_ruleCNF; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCNF"


    // $ANTLR start "ruleCNF"
    // InternalFml.g:8549:1: ruleCNF returns [EObject current=null] : this_Or_expr_0= ruleOr_expr ;
    public final EObject ruleCNF() throws RecognitionException {
        EObject current = null;

        EObject this_Or_expr_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8555:2: (this_Or_expr_0= ruleOr_expr )
            // InternalFml.g:8556:2: this_Or_expr_0= ruleOr_expr
            {

            		newCompositeNode(grammarAccess.getCNFAccess().getOr_exprParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_Or_expr_0=ruleOr_expr();

            state._fsp--;


            		current = this_Or_expr_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCNF"


    // $ANTLR start "entryRuleOr_expr"
    // InternalFml.g:8567:1: entryRuleOr_expr returns [EObject current=null] : iv_ruleOr_expr= ruleOr_expr EOF ;
    public final EObject entryRuleOr_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr_expr = null;


        try {
            // InternalFml.g:8567:48: (iv_ruleOr_expr= ruleOr_expr EOF )
            // InternalFml.g:8568:2: iv_ruleOr_expr= ruleOr_expr EOF
            {
             newCompositeNode(grammarAccess.getOr_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOr_expr=ruleOr_expr();

            state._fsp--;

             current =iv_ruleOr_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOr_expr"


    // $ANTLR start "ruleOr_expr"
    // InternalFml.g:8574:1: ruleOr_expr returns [EObject current=null] : (this_And_expr_0= ruleAnd_expr ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )* ) ;
    public final EObject ruleOr_expr() throws RecognitionException {
        EObject current = null;

        Token this_B_OR_2=null;
        EObject this_And_expr_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8580:2: ( (this_And_expr_0= ruleAnd_expr ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )* ) )
            // InternalFml.g:8581:2: (this_And_expr_0= ruleAnd_expr ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )* )
            {
            // InternalFml.g:8581:2: (this_And_expr_0= ruleAnd_expr ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )* )
            // InternalFml.g:8582:3: this_And_expr_0= ruleAnd_expr ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )*
            {

            			newCompositeNode(grammarAccess.getOr_exprAccess().getAnd_exprParserRuleCall_0());
            		
            pushFollow(FOLLOW_79);
            this_And_expr_0=ruleAnd_expr();

            state._fsp--;


            			current = this_And_expr_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalFml.g:8590:3: ( () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) ) )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==RULE_B_OR) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // InternalFml.g:8591:4: () this_B_OR_2= RULE_B_OR ( (lv_right_3_0= ruleAnd_expr ) )
            	    {
            	    // InternalFml.g:8591:4: ()
            	    // InternalFml.g:8592:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_B_OR_2=(Token)match(input,RULE_B_OR,FOLLOW_18); 

            	    				newLeafNode(this_B_OR_2, grammarAccess.getOr_exprAccess().getB_ORTerminalRuleCall_1_1());
            	    			
            	    // InternalFml.g:8602:4: ( (lv_right_3_0= ruleAnd_expr ) )
            	    // InternalFml.g:8603:5: (lv_right_3_0= ruleAnd_expr )
            	    {
            	    // InternalFml.g:8603:5: (lv_right_3_0= ruleAnd_expr )
            	    // InternalFml.g:8604:6: lv_right_3_0= ruleAnd_expr
            	    {

            	    						newCompositeNode(grammarAccess.getOr_exprAccess().getRightAnd_exprParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_79);
            	    lv_right_3_0=ruleAnd_expr();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOr_exprRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"org.xtext.example.mydsl.Fml.And_expr");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOr_expr"


    // $ANTLR start "entryRuleAnd_expr"
    // InternalFml.g:8626:1: entryRuleAnd_expr returns [EObject current=null] : iv_ruleAnd_expr= ruleAnd_expr EOF ;
    public final EObject entryRuleAnd_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd_expr = null;


        try {
            // InternalFml.g:8626:49: (iv_ruleAnd_expr= ruleAnd_expr EOF )
            // InternalFml.g:8627:2: iv_ruleAnd_expr= ruleAnd_expr EOF
            {
             newCompositeNode(grammarAccess.getAnd_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnd_expr=ruleAnd_expr();

            state._fsp--;

             current =iv_ruleAnd_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnd_expr"


    // $ANTLR start "ruleAnd_expr"
    // InternalFml.g:8633:1: ruleAnd_expr returns [EObject current=null] : (this_Impl_expr_0= ruleImpl_expr ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )* ) ;
    public final EObject ruleAnd_expr() throws RecognitionException {
        EObject current = null;

        Token this_B_AND_2=null;
        EObject this_Impl_expr_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8639:2: ( (this_Impl_expr_0= ruleImpl_expr ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )* ) )
            // InternalFml.g:8640:2: (this_Impl_expr_0= ruleImpl_expr ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )* )
            {
            // InternalFml.g:8640:2: (this_Impl_expr_0= ruleImpl_expr ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )* )
            // InternalFml.g:8641:3: this_Impl_expr_0= ruleImpl_expr ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )*
            {

            			newCompositeNode(grammarAccess.getAnd_exprAccess().getImpl_exprParserRuleCall_0());
            		
            pushFollow(FOLLOW_80);
            this_Impl_expr_0=ruleImpl_expr();

            state._fsp--;


            			current = this_Impl_expr_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalFml.g:8649:3: ( () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==RULE_B_AND) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // InternalFml.g:8650:4: () this_B_AND_2= RULE_B_AND ( (lv_right_3_0= ruleImpl_expr ) )
            	    {
            	    // InternalFml.g:8650:4: ()
            	    // InternalFml.g:8651:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_B_AND_2=(Token)match(input,RULE_B_AND,FOLLOW_18); 

            	    				newLeafNode(this_B_AND_2, grammarAccess.getAnd_exprAccess().getB_ANDTerminalRuleCall_1_1());
            	    			
            	    // InternalFml.g:8661:4: ( (lv_right_3_0= ruleImpl_expr ) )
            	    // InternalFml.g:8662:5: (lv_right_3_0= ruleImpl_expr )
            	    {
            	    // InternalFml.g:8662:5: (lv_right_3_0= ruleImpl_expr )
            	    // InternalFml.g:8663:6: lv_right_3_0= ruleImpl_expr
            	    {

            	    						newCompositeNode(grammarAccess.getAnd_exprAccess().getRightImpl_exprParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_80);
            	    lv_right_3_0=ruleImpl_expr();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnd_exprRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"org.xtext.example.mydsl.Fml.Impl_expr");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnd_expr"


    // $ANTLR start "entryRuleImpl_expr"
    // InternalFml.g:8685:1: entryRuleImpl_expr returns [EObject current=null] : iv_ruleImpl_expr= ruleImpl_expr EOF ;
    public final EObject entryRuleImpl_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpl_expr = null;


        try {
            // InternalFml.g:8685:50: (iv_ruleImpl_expr= ruleImpl_expr EOF )
            // InternalFml.g:8686:2: iv_ruleImpl_expr= ruleImpl_expr EOF
            {
             newCompositeNode(grammarAccess.getImpl_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImpl_expr=ruleImpl_expr();

            state._fsp--;

             current =iv_ruleImpl_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImpl_expr"


    // $ANTLR start "ruleImpl_expr"
    // InternalFml.g:8692:1: ruleImpl_expr returns [EObject current=null] : (this_Biimpl_expr_0= ruleBiimpl_expr ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )* ) ;
    public final EObject ruleImpl_expr() throws RecognitionException {
        EObject current = null;

        Token this_B_IMPLY_2=null;
        EObject this_Biimpl_expr_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8698:2: ( (this_Biimpl_expr_0= ruleBiimpl_expr ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )* ) )
            // InternalFml.g:8699:2: (this_Biimpl_expr_0= ruleBiimpl_expr ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )* )
            {
            // InternalFml.g:8699:2: (this_Biimpl_expr_0= ruleBiimpl_expr ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )* )
            // InternalFml.g:8700:3: this_Biimpl_expr_0= ruleBiimpl_expr ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )*
            {

            			newCompositeNode(grammarAccess.getImpl_exprAccess().getBiimpl_exprParserRuleCall_0());
            		
            pushFollow(FOLLOW_81);
            this_Biimpl_expr_0=ruleBiimpl_expr();

            state._fsp--;


            			current = this_Biimpl_expr_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalFml.g:8708:3: ( () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) ) )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==RULE_B_IMPLY) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // InternalFml.g:8709:4: () this_B_IMPLY_2= RULE_B_IMPLY ( (lv_right_3_0= ruleBiimpl_expr ) )
            	    {
            	    // InternalFml.g:8709:4: ()
            	    // InternalFml.g:8710:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_B_IMPLY_2=(Token)match(input,RULE_B_IMPLY,FOLLOW_18); 

            	    				newLeafNode(this_B_IMPLY_2, grammarAccess.getImpl_exprAccess().getB_IMPLYTerminalRuleCall_1_1());
            	    			
            	    // InternalFml.g:8720:4: ( (lv_right_3_0= ruleBiimpl_expr ) )
            	    // InternalFml.g:8721:5: (lv_right_3_0= ruleBiimpl_expr )
            	    {
            	    // InternalFml.g:8721:5: (lv_right_3_0= ruleBiimpl_expr )
            	    // InternalFml.g:8722:6: lv_right_3_0= ruleBiimpl_expr
            	    {

            	    						newCompositeNode(grammarAccess.getImpl_exprAccess().getRightBiimpl_exprParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_81);
            	    lv_right_3_0=ruleBiimpl_expr();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getImpl_exprRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"org.xtext.example.mydsl.Fml.Biimpl_expr");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImpl_expr"


    // $ANTLR start "entryRuleBiimpl_expr"
    // InternalFml.g:8744:1: entryRuleBiimpl_expr returns [EObject current=null] : iv_ruleBiimpl_expr= ruleBiimpl_expr EOF ;
    public final EObject entryRuleBiimpl_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBiimpl_expr = null;


        try {
            // InternalFml.g:8744:52: (iv_ruleBiimpl_expr= ruleBiimpl_expr EOF )
            // InternalFml.g:8745:2: iv_ruleBiimpl_expr= ruleBiimpl_expr EOF
            {
             newCompositeNode(grammarAccess.getBiimpl_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBiimpl_expr=ruleBiimpl_expr();

            state._fsp--;

             current =iv_ruleBiimpl_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBiimpl_expr"


    // $ANTLR start "ruleBiimpl_expr"
    // InternalFml.g:8751:1: ruleBiimpl_expr returns [EObject current=null] : (this_Unary_expr_0= ruleUnary_expr ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )* ) ;
    public final EObject ruleBiimpl_expr() throws RecognitionException {
        EObject current = null;

        Token this_B_BIMPLY_2=null;
        EObject this_Unary_expr_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8757:2: ( (this_Unary_expr_0= ruleUnary_expr ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )* ) )
            // InternalFml.g:8758:2: (this_Unary_expr_0= ruleUnary_expr ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )* )
            {
            // InternalFml.g:8758:2: (this_Unary_expr_0= ruleUnary_expr ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )* )
            // InternalFml.g:8759:3: this_Unary_expr_0= ruleUnary_expr ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )*
            {

            			newCompositeNode(grammarAccess.getBiimpl_exprAccess().getUnary_exprParserRuleCall_0());
            		
            pushFollow(FOLLOW_82);
            this_Unary_expr_0=ruleUnary_expr();

            state._fsp--;


            			current = this_Unary_expr_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalFml.g:8767:3: ( () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) ) )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==RULE_B_BIMPLY) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // InternalFml.g:8768:4: () this_B_BIMPLY_2= RULE_B_BIMPLY ( (lv_right_3_0= ruleUnary_expr ) )
            	    {
            	    // InternalFml.g:8768:4: ()
            	    // InternalFml.g:8769:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_B_BIMPLY_2=(Token)match(input,RULE_B_BIMPLY,FOLLOW_18); 

            	    				newLeafNode(this_B_BIMPLY_2, grammarAccess.getBiimpl_exprAccess().getB_BIMPLYTerminalRuleCall_1_1());
            	    			
            	    // InternalFml.g:8779:4: ( (lv_right_3_0= ruleUnary_expr ) )
            	    // InternalFml.g:8780:5: (lv_right_3_0= ruleUnary_expr )
            	    {
            	    // InternalFml.g:8780:5: (lv_right_3_0= ruleUnary_expr )
            	    // InternalFml.g:8781:6: lv_right_3_0= ruleUnary_expr
            	    {

            	    						newCompositeNode(grammarAccess.getBiimpl_exprAccess().getRightUnary_exprParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_82);
            	    lv_right_3_0=ruleUnary_expr();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getBiimpl_exprRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"org.xtext.example.mydsl.Fml.Unary_expr");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop100;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBiimpl_expr"


    // $ANTLR start "entryRuleUnary_expr"
    // InternalFml.g:8803:1: entryRuleUnary_expr returns [EObject current=null] : iv_ruleUnary_expr= ruleUnary_expr EOF ;
    public final EObject entryRuleUnary_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary_expr = null;


        try {
            // InternalFml.g:8803:51: (iv_ruleUnary_expr= ruleUnary_expr EOF )
            // InternalFml.g:8804:2: iv_ruleUnary_expr= ruleUnary_expr EOF
            {
             newCompositeNode(grammarAccess.getUnary_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnary_expr=ruleUnary_expr();

            state._fsp--;

             current =iv_ruleUnary_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnary_expr"


    // $ANTLR start "ruleUnary_expr"
    // InternalFml.g:8810:1: ruleUnary_expr returns [EObject current=null] : (this_Neg_expr_0= ruleNeg_expr | this_Primary_expr_1= rulePrimary_expr ) ;
    public final EObject ruleUnary_expr() throws RecognitionException {
        EObject current = null;

        EObject this_Neg_expr_0 = null;

        EObject this_Primary_expr_1 = null;



        	enterRule();

        try {
            // InternalFml.g:8816:2: ( (this_Neg_expr_0= ruleNeg_expr | this_Primary_expr_1= rulePrimary_expr ) )
            // InternalFml.g:8817:2: (this_Neg_expr_0= ruleNeg_expr | this_Primary_expr_1= rulePrimary_expr )
            {
            // InternalFml.g:8817:2: (this_Neg_expr_0= ruleNeg_expr | this_Primary_expr_1= rulePrimary_expr )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==RULE_B_NOT) ) {
                alt101=1;
            }
            else if ( (LA101_0==RULE_LEFT_PAREN||LA101_0==RULE_STRING||LA101_0==RULE_ID||(LA101_0>=32 && LA101_0<=33)||LA101_0==168) ) {
                alt101=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // InternalFml.g:8818:3: this_Neg_expr_0= ruleNeg_expr
                    {

                    			newCompositeNode(grammarAccess.getUnary_exprAccess().getNeg_exprParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Neg_expr_0=ruleNeg_expr();

                    state._fsp--;


                    			current = this_Neg_expr_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:8827:3: this_Primary_expr_1= rulePrimary_expr
                    {

                    			newCompositeNode(grammarAccess.getUnary_exprAccess().getPrimary_exprParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Primary_expr_1=rulePrimary_expr();

                    state._fsp--;


                    			current = this_Primary_expr_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnary_expr"


    // $ANTLR start "entryRuleNeg_expr"
    // InternalFml.g:8839:1: entryRuleNeg_expr returns [EObject current=null] : iv_ruleNeg_expr= ruleNeg_expr EOF ;
    public final EObject entryRuleNeg_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNeg_expr = null;


        try {
            // InternalFml.g:8839:49: (iv_ruleNeg_expr= ruleNeg_expr EOF )
            // InternalFml.g:8840:2: iv_ruleNeg_expr= ruleNeg_expr EOF
            {
             newCompositeNode(grammarAccess.getNeg_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNeg_expr=ruleNeg_expr();

            state._fsp--;

             current =iv_ruleNeg_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNeg_expr"


    // $ANTLR start "ruleNeg_expr"
    // InternalFml.g:8846:1: ruleNeg_expr returns [EObject current=null] : (this_B_NOT_0= RULE_B_NOT ( (lv_expr_1_0= rulePrimary_expr ) ) ) ;
    public final EObject ruleNeg_expr() throws RecognitionException {
        EObject current = null;

        Token this_B_NOT_0=null;
        EObject lv_expr_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8852:2: ( (this_B_NOT_0= RULE_B_NOT ( (lv_expr_1_0= rulePrimary_expr ) ) ) )
            // InternalFml.g:8853:2: (this_B_NOT_0= RULE_B_NOT ( (lv_expr_1_0= rulePrimary_expr ) ) )
            {
            // InternalFml.g:8853:2: (this_B_NOT_0= RULE_B_NOT ( (lv_expr_1_0= rulePrimary_expr ) ) )
            // InternalFml.g:8854:3: this_B_NOT_0= RULE_B_NOT ( (lv_expr_1_0= rulePrimary_expr ) )
            {
            this_B_NOT_0=(Token)match(input,RULE_B_NOT,FOLLOW_18); 

            			newLeafNode(this_B_NOT_0, grammarAccess.getNeg_exprAccess().getB_NOTTerminalRuleCall_0());
            		
            // InternalFml.g:8858:3: ( (lv_expr_1_0= rulePrimary_expr ) )
            // InternalFml.g:8859:4: (lv_expr_1_0= rulePrimary_expr )
            {
            // InternalFml.g:8859:4: (lv_expr_1_0= rulePrimary_expr )
            // InternalFml.g:8860:5: lv_expr_1_0= rulePrimary_expr
            {

            					newCompositeNode(grammarAccess.getNeg_exprAccess().getExprPrimary_exprParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_expr_1_0=rulePrimary_expr();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNeg_exprRule());
            					}
            					set(
            						current,
            						"expr",
            						lv_expr_1_0,
            						"org.xtext.example.mydsl.Fml.Primary_expr");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNeg_expr"


    // $ANTLR start "entryRulePrimary_expr"
    // InternalFml.g:8881:1: entryRulePrimary_expr returns [EObject current=null] : iv_rulePrimary_expr= rulePrimary_expr EOF ;
    public final EObject entryRulePrimary_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary_expr = null;


        try {
            // InternalFml.g:8881:53: (iv_rulePrimary_expr= rulePrimary_expr EOF )
            // InternalFml.g:8882:2: iv_rulePrimary_expr= rulePrimary_expr EOF
            {
             newCompositeNode(grammarAccess.getPrimary_exprRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimary_expr=rulePrimary_expr();

            state._fsp--;

             current =iv_rulePrimary_expr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimary_expr"


    // $ANTLR start "rulePrimary_expr"
    // InternalFml.g:8888:1: rulePrimary_expr returns [EObject current=null] : ( ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) ) | (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) ) ;
    public final EObject rulePrimary_expr() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_2=null;
        Token lv_name_0_3=null;
        Token this_LEFT_PAREN_1=null;
        Token this_RIGHT_PAREN_3=null;
        AntlrDatatypeRuleToken lv_name_0_1 = null;

        EObject this_Or_expr_2 = null;



        	enterRule();

        try {
            // InternalFml.g:8894:2: ( ( ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) ) | (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) ) )
            // InternalFml.g:8895:2: ( ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) ) | (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) )
            {
            // InternalFml.g:8895:2: ( ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) ) | (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN ) )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==RULE_STRING||LA103_0==RULE_ID||(LA103_0>=32 && LA103_0<=33)||LA103_0==168) ) {
                alt103=1;
            }
            else if ( (LA103_0==RULE_LEFT_PAREN) ) {
                alt103=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // InternalFml.g:8896:3: ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) )
                    {
                    // InternalFml.g:8896:3: ( ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) ) )
                    // InternalFml.g:8897:4: ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) )
                    {
                    // InternalFml.g:8897:4: ( (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' ) )
                    // InternalFml.g:8898:5: (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' )
                    {
                    // InternalFml.g:8898:5: (lv_name_0_1= ruleFT_ID | lv_name_0_2= 'true' | lv_name_0_3= 'false' )
                    int alt102=3;
                    switch ( input.LA(1) ) {
                    case RULE_STRING:
                    case RULE_ID:
                    case 168:
                        {
                        alt102=1;
                        }
                        break;
                    case 32:
                        {
                        alt102=2;
                        }
                        break;
                    case 33:
                        {
                        alt102=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 102, 0, input);

                        throw nvae;
                    }

                    switch (alt102) {
                        case 1 :
                            // InternalFml.g:8899:6: lv_name_0_1= ruleFT_ID
                            {

                            						newCompositeNode(grammarAccess.getPrimary_exprAccess().getNameFT_IDParserRuleCall_0_0_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_name_0_1=ruleFT_ID();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPrimary_exprRule());
                            						}
                            						set(
                            							current,
                            							"name",
                            							lv_name_0_1,
                            							"org.xtext.example.mydsl.Fml.FT_ID");
                            						afterParserOrEnumRuleCall();
                            					

                            }
                            break;
                        case 2 :
                            // InternalFml.g:8915:6: lv_name_0_2= 'true'
                            {
                            lv_name_0_2=(Token)match(input,32,FOLLOW_2); 

                            						newLeafNode(lv_name_0_2, grammarAccess.getPrimary_exprAccess().getNameTrueKeyword_0_0_1());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getPrimary_exprRule());
                            						}
                            						setWithLastConsumed(current, "name", lv_name_0_2, null);
                            					

                            }
                            break;
                        case 3 :
                            // InternalFml.g:8926:6: lv_name_0_3= 'false'
                            {
                            lv_name_0_3=(Token)match(input,33,FOLLOW_2); 

                            						newLeafNode(lv_name_0_3, grammarAccess.getPrimary_exprAccess().getNameFalseKeyword_0_0_2());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getPrimary_exprRule());
                            						}
                            						setWithLastConsumed(current, "name", lv_name_0_3, null);
                            					

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:8940:3: (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
                    {
                    // InternalFml.g:8940:3: (this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN )
                    // InternalFml.g:8941:4: this_LEFT_PAREN_1= RULE_LEFT_PAREN this_Or_expr_2= ruleOr_expr this_RIGHT_PAREN_3= RULE_RIGHT_PAREN
                    {
                    this_LEFT_PAREN_1=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_18); 

                    				newLeafNode(this_LEFT_PAREN_1, grammarAccess.getPrimary_exprAccess().getLEFT_PARENTerminalRuleCall_1_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimary_exprAccess().getOr_exprParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_14);
                    this_Or_expr_2=ruleOr_expr();

                    state._fsp--;


                    				current = this_Or_expr_2;
                    				afterParserOrEnumRuleCall();
                    			
                    this_RIGHT_PAREN_3=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

                    				newLeafNode(this_RIGHT_PAREN_3, grammarAccess.getPrimary_exprAccess().getRIGHT_PARENTerminalRuleCall_1_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimary_expr"


    // $ANTLR start "entryRuleFeatureModel"
    // InternalFml.g:8962:1: entryRuleFeatureModel returns [EObject current=null] : iv_ruleFeatureModel= ruleFeatureModel EOF ;
    public final EObject entryRuleFeatureModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureModel = null;


        try {
            // InternalFml.g:8962:53: (iv_ruleFeatureModel= ruleFeatureModel EOF )
            // InternalFml.g:8963:2: iv_ruleFeatureModel= ruleFeatureModel EOF
            {
             newCompositeNode(grammarAccess.getFeatureModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeatureModel=ruleFeatureModel();

            state._fsp--;

             current =iv_ruleFeatureModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeatureModel"


    // $ANTLR start "ruleFeatureModel"
    // InternalFml.g:8969:1: ruleFeatureModel returns [EObject current=null] : ( (otherlv_0= 'FM' | otherlv_1= 'featuremodel' ) this_LEFT_PAREN_2= RULE_LEFT_PAREN ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) ) this_RIGHT_PAREN_10= RULE_RIGHT_PAREN ) ;
    public final EObject ruleFeatureModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token this_LEFT_PAREN_2=null;
        Token lv_root_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token this_RIGHT_PAREN_10=null;
        EObject lv_features_5_0 = null;

        EObject lv_expr_7_0 = null;

        EObject lv_file_9_0 = null;



        	enterRule();

        try {
            // InternalFml.g:8975:2: ( ( (otherlv_0= 'FM' | otherlv_1= 'featuremodel' ) this_LEFT_PAREN_2= RULE_LEFT_PAREN ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) ) this_RIGHT_PAREN_10= RULE_RIGHT_PAREN ) )
            // InternalFml.g:8976:2: ( (otherlv_0= 'FM' | otherlv_1= 'featuremodel' ) this_LEFT_PAREN_2= RULE_LEFT_PAREN ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) ) this_RIGHT_PAREN_10= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:8976:2: ( (otherlv_0= 'FM' | otherlv_1= 'featuremodel' ) this_LEFT_PAREN_2= RULE_LEFT_PAREN ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) ) this_RIGHT_PAREN_10= RULE_RIGHT_PAREN )
            // InternalFml.g:8977:3: (otherlv_0= 'FM' | otherlv_1= 'featuremodel' ) this_LEFT_PAREN_2= RULE_LEFT_PAREN ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) ) this_RIGHT_PAREN_10= RULE_RIGHT_PAREN
            {
            // InternalFml.g:8977:3: (otherlv_0= 'FM' | otherlv_1= 'featuremodel' )
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==165) ) {
                alt104=1;
            }
            else if ( (LA104_0==166) ) {
                alt104=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }
            switch (alt104) {
                case 1 :
                    // InternalFml.g:8978:4: otherlv_0= 'FM'
                    {
                    otherlv_0=(Token)match(input,165,FOLLOW_17); 

                    				newLeafNode(otherlv_0, grammarAccess.getFeatureModelAccess().getFMKeyword_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalFml.g:8983:4: otherlv_1= 'featuremodel'
                    {
                    otherlv_1=(Token)match(input,166,FOLLOW_17); 

                    				newLeafNode(otherlv_1, grammarAccess.getFeatureModelAccess().getFeaturemodelKeyword_0_1());
                    			

                    }
                    break;

            }

            this_LEFT_PAREN_2=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_37); 

            			newLeafNode(this_LEFT_PAREN_2, grammarAccess.getFeatureModelAccess().getLEFT_PARENTerminalRuleCall_1());
            		
            // InternalFml.g:8992:3: ( ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) ) | ( (lv_file_9_0= ruleStringExpr ) ) )
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==RULE_ID||LA108_0==168) ) {
                alt108=1;
            }
            else if ( (LA108_0==RULE_STRING) ) {
                int LA108_2 = input.LA(2);

                if ( (LA108_2==89) ) {
                    alt108=1;
                }
                else if ( (LA108_2==RULE_RIGHT_PAREN) ) {
                    alt108=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 108, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;
            }
            switch (alt108) {
                case 1 :
                    // InternalFml.g:8993:4: ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) )
                    {
                    // InternalFml.g:8993:4: ( ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' ) | ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* ) )
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==RULE_ID) ) {
                        int LA107_1 = input.LA(2);

                        if ( (LA107_1==89) ) {
                            alt107=2;
                        }
                        else if ( (LA107_1==36) ) {
                            alt107=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 107, 1, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA107_0==RULE_STRING||LA107_0==168) ) {
                        alt107=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 107, 0, input);

                        throw nvae;
                    }
                    switch (alt107) {
                        case 1 :
                            // InternalFml.g:8994:5: ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' )
                            {
                            // InternalFml.g:8994:5: ( ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';' )
                            // InternalFml.g:8995:6: ( (lv_root_3_0= RULE_ID ) ) otherlv_4= ';'
                            {
                            // InternalFml.g:8995:6: ( (lv_root_3_0= RULE_ID ) )
                            // InternalFml.g:8996:7: (lv_root_3_0= RULE_ID )
                            {
                            // InternalFml.g:8996:7: (lv_root_3_0= RULE_ID )
                            // InternalFml.g:8997:8: lv_root_3_0= RULE_ID
                            {
                            lv_root_3_0=(Token)match(input,RULE_ID,FOLLOW_20); 

                            								newLeafNode(lv_root_3_0, grammarAccess.getFeatureModelAccess().getRootIDTerminalRuleCall_2_0_0_0_0());
                            							

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getFeatureModelRule());
                            								}
                            								setWithLastConsumed(
                            									current,
                            									"root",
                            									lv_root_3_0,
                            									"org.eclipse.xtext.common.Terminals.ID");
                            							

                            }


                            }

                            otherlv_4=(Token)match(input,36,FOLLOW_14); 

                            						newLeafNode(otherlv_4, grammarAccess.getFeatureModelAccess().getSemicolonKeyword_2_0_0_1());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalFml.g:9019:5: ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* )
                            {
                            // InternalFml.g:9019:5: ( ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )* )
                            // InternalFml.g:9020:6: ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+ ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )*
                            {
                            // InternalFml.g:9020:6: ( ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';' )+
                            int cnt105=0;
                            loop105:
                            do {
                                int alt105=2;
                                switch ( input.LA(1) ) {
                                case RULE_ID:
                                    {
                                    int LA105_2 = input.LA(2);

                                    if ( (LA105_2==89) ) {
                                        alt105=1;
                                    }


                                    }
                                    break;
                                case 168:
                                    {
                                    int LA105_3 = input.LA(2);

                                    if ( (LA105_3==89) ) {
                                        alt105=1;
                                    }


                                    }
                                    break;
                                case RULE_STRING:
                                    {
                                    int LA105_4 = input.LA(2);

                                    if ( (LA105_4==89) ) {
                                        alt105=1;
                                    }


                                    }
                                    break;

                                }

                                switch (alt105) {
                            	case 1 :
                            	    // InternalFml.g:9021:7: ( (lv_features_5_0= ruleProduction ) ) otherlv_6= ';'
                            	    {
                            	    // InternalFml.g:9021:7: ( (lv_features_5_0= ruleProduction ) )
                            	    // InternalFml.g:9022:8: (lv_features_5_0= ruleProduction )
                            	    {
                            	    // InternalFml.g:9022:8: (lv_features_5_0= ruleProduction )
                            	    // InternalFml.g:9023:9: lv_features_5_0= ruleProduction
                            	    {

                            	    									newCompositeNode(grammarAccess.getFeatureModelAccess().getFeaturesProductionParserRuleCall_2_0_1_0_0_0());
                            	    								
                            	    pushFollow(FOLLOW_20);
                            	    lv_features_5_0=ruleProduction();

                            	    state._fsp--;


                            	    									if (current==null) {
                            	    										current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                            	    									}
                            	    									add(
                            	    										current,
                            	    										"features",
                            	    										lv_features_5_0,
                            	    										"org.xtext.example.mydsl.Fml.Production");
                            	    									afterParserOrEnumRuleCall();
                            	    								

                            	    }


                            	    }

                            	    otherlv_6=(Token)match(input,36,FOLLOW_21); 

                            	    							newLeafNode(otherlv_6, grammarAccess.getFeatureModelAccess().getSemicolonKeyword_2_0_1_0_1());
                            	    						

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt105 >= 1 ) break loop105;
                                        EarlyExitException eee =
                                            new EarlyExitException(105, input);
                                        throw eee;
                                }
                                cnt105++;
                            } while (true);

                            // InternalFml.g:9045:6: ( ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';' )*
                            loop106:
                            do {
                                int alt106=2;
                                int LA106_0 = input.LA(1);

                                if ( (LA106_0==RULE_LEFT_PAREN||LA106_0==RULE_STRING||LA106_0==RULE_ID||LA106_0==RULE_B_NOT||(LA106_0>=32 && LA106_0<=33)||LA106_0==168) ) {
                                    alt106=1;
                                }


                                switch (alt106) {
                            	case 1 :
                            	    // InternalFml.g:9046:7: ( (lv_expr_7_0= ruleCNF ) ) otherlv_8= ';'
                            	    {
                            	    // InternalFml.g:9046:7: ( (lv_expr_7_0= ruleCNF ) )
                            	    // InternalFml.g:9047:8: (lv_expr_7_0= ruleCNF )
                            	    {
                            	    // InternalFml.g:9047:8: (lv_expr_7_0= ruleCNF )
                            	    // InternalFml.g:9048:9: lv_expr_7_0= ruleCNF
                            	    {

                            	    									newCompositeNode(grammarAccess.getFeatureModelAccess().getExprCNFParserRuleCall_2_0_1_1_0_0());
                            	    								
                            	    pushFollow(FOLLOW_20);
                            	    lv_expr_7_0=ruleCNF();

                            	    state._fsp--;


                            	    									if (current==null) {
                            	    										current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                            	    									}
                            	    									add(
                            	    										current,
                            	    										"expr",
                            	    										lv_expr_7_0,
                            	    										"org.xtext.example.mydsl.Fml.CNF");
                            	    									afterParserOrEnumRuleCall();
                            	    								

                            	    }


                            	    }

                            	    otherlv_8=(Token)match(input,36,FOLLOW_21); 

                            	    							newLeafNode(otherlv_8, grammarAccess.getFeatureModelAccess().getSemicolonKeyword_2_0_1_1_1());
                            	    						

                            	    }
                            	    break;

                            	default :
                            	    break loop106;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9073:4: ( (lv_file_9_0= ruleStringExpr ) )
                    {
                    // InternalFml.g:9073:4: ( (lv_file_9_0= ruleStringExpr ) )
                    // InternalFml.g:9074:5: (lv_file_9_0= ruleStringExpr )
                    {
                    // InternalFml.g:9074:5: (lv_file_9_0= ruleStringExpr )
                    // InternalFml.g:9075:6: lv_file_9_0= ruleStringExpr
                    {

                    						newCompositeNode(grammarAccess.getFeatureModelAccess().getFileStringExprParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_file_9_0=ruleStringExpr();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                    						}
                    						set(
                    							current,
                    							"file",
                    							lv_file_9_0,
                    							"org.xtext.example.mydsl.Fml.StringExpr");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            this_RIGHT_PAREN_10=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_10, grammarAccess.getFeatureModelAccess().getRIGHT_PARENTerminalRuleCall_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureModel"


    // $ANTLR start "entryRuleProduction"
    // InternalFml.g:9101:1: entryRuleProduction returns [EObject current=null] : iv_ruleProduction= ruleProduction EOF ;
    public final EObject entryRuleProduction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProduction = null;


        try {
            // InternalFml.g:9101:51: (iv_ruleProduction= ruleProduction EOF )
            // InternalFml.g:9102:2: iv_ruleProduction= ruleProduction EOF
            {
             newCompositeNode(grammarAccess.getProductionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProduction=ruleProduction();

            state._fsp--;

             current =iv_ruleProduction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProduction"


    // $ANTLR start "ruleProduction"
    // InternalFml.g:9108:1: ruleProduction returns [EObject current=null] : ( ( (lv_name_0_0= ruleFT_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleChild ) )+ ) ;
    public final EObject ruleProduction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_features_2_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9114:2: ( ( ( (lv_name_0_0= ruleFT_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleChild ) )+ ) )
            // InternalFml.g:9115:2: ( ( (lv_name_0_0= ruleFT_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleChild ) )+ )
            {
            // InternalFml.g:9115:2: ( ( (lv_name_0_0= ruleFT_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleChild ) )+ )
            // InternalFml.g:9116:3: ( (lv_name_0_0= ruleFT_ID ) ) otherlv_1= ':' ( (lv_features_2_0= ruleChild ) )+
            {
            // InternalFml.g:9116:3: ( (lv_name_0_0= ruleFT_ID ) )
            // InternalFml.g:9117:4: (lv_name_0_0= ruleFT_ID )
            {
            // InternalFml.g:9117:4: (lv_name_0_0= ruleFT_ID )
            // InternalFml.g:9118:5: lv_name_0_0= ruleFT_ID
            {

            					newCompositeNode(grammarAccess.getProductionAccess().getNameFT_IDParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_55);
            lv_name_0_0=ruleFT_ID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProductionRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.xtext.example.mydsl.Fml.FT_ID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,89,FOLLOW_83); 

            			newLeafNode(otherlv_1, grammarAccess.getProductionAccess().getColonKeyword_1());
            		
            // InternalFml.g:9139:3: ( (lv_features_2_0= ruleChild ) )+
            int cnt109=0;
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==RULE_LEFT_HOOK||LA109_0==RULE_LEFT_PAREN||LA109_0==RULE_STRING||LA109_0==RULE_ID||LA109_0==168) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // InternalFml.g:9140:4: (lv_features_2_0= ruleChild )
            	    {
            	    // InternalFml.g:9140:4: (lv_features_2_0= ruleChild )
            	    // InternalFml.g:9141:5: lv_features_2_0= ruleChild
            	    {

            	    					newCompositeNode(grammarAccess.getProductionAccess().getFeaturesChildParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_84);
            	    lv_features_2_0=ruleChild();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProductionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_2_0,
            	    						"org.xtext.example.mydsl.Fml.Child");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt109 >= 1 ) break loop109;
                        EarlyExitException eee =
                            new EarlyExitException(109, input);
                        throw eee;
                }
                cnt109++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProduction"


    // $ANTLR start "entryRuleChild"
    // InternalFml.g:9162:1: entryRuleChild returns [EObject current=null] : iv_ruleChild= ruleChild EOF ;
    public final EObject entryRuleChild() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChild = null;


        try {
            // InternalFml.g:9162:46: (iv_ruleChild= ruleChild EOF )
            // InternalFml.g:9163:2: iv_ruleChild= ruleChild EOF
            {
             newCompositeNode(grammarAccess.getChildRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChild=ruleChild();

            state._fsp--;

             current =iv_ruleChild; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChild"


    // $ANTLR start "ruleChild"
    // InternalFml.g:9169:1: ruleChild returns [EObject current=null] : (this_Mandatory_0= ruleMandatory | this_Optional_1= ruleOptional | this_Xorgroup_2= ruleXorgroup | this_Orgroup_3= ruleOrgroup | this_Mutexgroup_4= ruleMutexgroup ) ;
    public final EObject ruleChild() throws RecognitionException {
        EObject current = null;

        EObject this_Mandatory_0 = null;

        EObject this_Optional_1 = null;

        EObject this_Xorgroup_2 = null;

        EObject this_Orgroup_3 = null;

        EObject this_Mutexgroup_4 = null;



        	enterRule();

        try {
            // InternalFml.g:9175:2: ( (this_Mandatory_0= ruleMandatory | this_Optional_1= ruleOptional | this_Xorgroup_2= ruleXorgroup | this_Orgroup_3= ruleOrgroup | this_Mutexgroup_4= ruleMutexgroup ) )
            // InternalFml.g:9176:2: (this_Mandatory_0= ruleMandatory | this_Optional_1= ruleOptional | this_Xorgroup_2= ruleXorgroup | this_Orgroup_3= ruleOrgroup | this_Mutexgroup_4= ruleMutexgroup )
            {
            // InternalFml.g:9176:2: (this_Mandatory_0= ruleMandatory | this_Optional_1= ruleOptional | this_Xorgroup_2= ruleXorgroup | this_Orgroup_3= ruleOrgroup | this_Mutexgroup_4= ruleMutexgroup )
            int alt110=5;
            alt110 = dfa110.predict(input);
            switch (alt110) {
                case 1 :
                    // InternalFml.g:9177:3: this_Mandatory_0= ruleMandatory
                    {

                    			newCompositeNode(grammarAccess.getChildAccess().getMandatoryParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Mandatory_0=ruleMandatory();

                    state._fsp--;


                    			current = this_Mandatory_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:9186:3: this_Optional_1= ruleOptional
                    {

                    			newCompositeNode(grammarAccess.getChildAccess().getOptionalParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Optional_1=ruleOptional();

                    state._fsp--;


                    			current = this_Optional_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:9195:3: this_Xorgroup_2= ruleXorgroup
                    {

                    			newCompositeNode(grammarAccess.getChildAccess().getXorgroupParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Xorgroup_2=ruleXorgroup();

                    state._fsp--;


                    			current = this_Xorgroup_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFml.g:9204:3: this_Orgroup_3= ruleOrgroup
                    {

                    			newCompositeNode(grammarAccess.getChildAccess().getOrgroupParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Orgroup_3=ruleOrgroup();

                    state._fsp--;


                    			current = this_Orgroup_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalFml.g:9213:3: this_Mutexgroup_4= ruleMutexgroup
                    {

                    			newCompositeNode(grammarAccess.getChildAccess().getMutexgroupParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Mutexgroup_4=ruleMutexgroup();

                    state._fsp--;


                    			current = this_Mutexgroup_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChild"


    // $ANTLR start "entryRuleMandatory"
    // InternalFml.g:9225:1: entryRuleMandatory returns [EObject current=null] : iv_ruleMandatory= ruleMandatory EOF ;
    public final EObject entryRuleMandatory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMandatory = null;


        try {
            // InternalFml.g:9225:50: (iv_ruleMandatory= ruleMandatory EOF )
            // InternalFml.g:9226:2: iv_ruleMandatory= ruleMandatory EOF
            {
             newCompositeNode(grammarAccess.getMandatoryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMandatory=ruleMandatory();

            state._fsp--;

             current =iv_ruleMandatory; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMandatory"


    // $ANTLR start "ruleMandatory"
    // InternalFml.g:9232:1: ruleMandatory returns [EObject current=null] : ( (lv_name_0_0= ruleFT_ID ) ) ;
    public final EObject ruleMandatory() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9238:2: ( ( (lv_name_0_0= ruleFT_ID ) ) )
            // InternalFml.g:9239:2: ( (lv_name_0_0= ruleFT_ID ) )
            {
            // InternalFml.g:9239:2: ( (lv_name_0_0= ruleFT_ID ) )
            // InternalFml.g:9240:3: (lv_name_0_0= ruleFT_ID )
            {
            // InternalFml.g:9240:3: (lv_name_0_0= ruleFT_ID )
            // InternalFml.g:9241:4: lv_name_0_0= ruleFT_ID
            {

            				newCompositeNode(grammarAccess.getMandatoryAccess().getNameFT_IDParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_name_0_0=ruleFT_ID();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getMandatoryRule());
            				}
            				set(
            					current,
            					"name",
            					lv_name_0_0,
            					"org.xtext.example.mydsl.Fml.FT_ID");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMandatory"


    // $ANTLR start "entryRuleOptional"
    // InternalFml.g:9261:1: entryRuleOptional returns [EObject current=null] : iv_ruleOptional= ruleOptional EOF ;
    public final EObject entryRuleOptional() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptional = null;


        try {
            // InternalFml.g:9261:49: (iv_ruleOptional= ruleOptional EOF )
            // InternalFml.g:9262:2: iv_ruleOptional= ruleOptional EOF
            {
             newCompositeNode(grammarAccess.getOptionalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptional=ruleOptional();

            state._fsp--;

             current =iv_ruleOptional; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptional"


    // $ANTLR start "ruleOptional"
    // InternalFml.g:9268:1: ruleOptional returns [EObject current=null] : (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_name_1_0= ruleFT_ID ) ) this_RIGHT_HOOK_2= RULE_RIGHT_HOOK ) ;
    public final EObject ruleOptional() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_HOOK_0=null;
        Token this_RIGHT_HOOK_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9274:2: ( (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_name_1_0= ruleFT_ID ) ) this_RIGHT_HOOK_2= RULE_RIGHT_HOOK ) )
            // InternalFml.g:9275:2: (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_name_1_0= ruleFT_ID ) ) this_RIGHT_HOOK_2= RULE_RIGHT_HOOK )
            {
            // InternalFml.g:9275:2: (this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_name_1_0= ruleFT_ID ) ) this_RIGHT_HOOK_2= RULE_RIGHT_HOOK )
            // InternalFml.g:9276:3: this_LEFT_HOOK_0= RULE_LEFT_HOOK ( (lv_name_1_0= ruleFT_ID ) ) this_RIGHT_HOOK_2= RULE_RIGHT_HOOK
            {
            this_LEFT_HOOK_0=(Token)match(input,RULE_LEFT_HOOK,FOLLOW_37); 

            			newLeafNode(this_LEFT_HOOK_0, grammarAccess.getOptionalAccess().getLEFT_HOOKTerminalRuleCall_0());
            		
            // InternalFml.g:9280:3: ( (lv_name_1_0= ruleFT_ID ) )
            // InternalFml.g:9281:4: (lv_name_1_0= ruleFT_ID )
            {
            // InternalFml.g:9281:4: (lv_name_1_0= ruleFT_ID )
            // InternalFml.g:9282:5: lv_name_1_0= ruleFT_ID
            {

            					newCompositeNode(grammarAccess.getOptionalAccess().getNameFT_IDParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_9);
            lv_name_1_0=ruleFT_ID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptionalRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.example.mydsl.Fml.FT_ID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            this_RIGHT_HOOK_2=(Token)match(input,RULE_RIGHT_HOOK,FOLLOW_2); 

            			newLeafNode(this_RIGHT_HOOK_2, grammarAccess.getOptionalAccess().getRIGHT_HOOKTerminalRuleCall_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptional"


    // $ANTLR start "entryRuleXorgroup"
    // InternalFml.g:9307:1: entryRuleXorgroup returns [EObject current=null] : iv_ruleXorgroup= ruleXorgroup EOF ;
    public final EObject entryRuleXorgroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXorgroup = null;


        try {
            // InternalFml.g:9307:49: (iv_ruleXorgroup= ruleXorgroup EOF )
            // InternalFml.g:9308:2: iv_ruleXorgroup= ruleXorgroup EOF
            {
             newCompositeNode(grammarAccess.getXorgroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXorgroup=ruleXorgroup();

            state._fsp--;

             current =iv_ruleXorgroup; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXorgroup"


    // $ANTLR start "ruleXorgroup"
    // InternalFml.g:9314:1: ruleXorgroup returns [EObject current=null] : (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) ;
    public final EObject ruleXorgroup() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_PAREN_0=null;
        Token this_B_OR_2=null;
        Token this_RIGHT_PAREN_4=null;
        AntlrDatatypeRuleToken lv_features_1_0 = null;

        AntlrDatatypeRuleToken lv_features_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9320:2: ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN ) )
            // InternalFml.g:9321:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN )
            {
            // InternalFml.g:9321:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN )
            // InternalFml.g:9322:3: this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN
            {
            this_LEFT_PAREN_0=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_37); 

            			newLeafNode(this_LEFT_PAREN_0, grammarAccess.getXorgroupAccess().getLEFT_PARENTerminalRuleCall_0());
            		
            // InternalFml.g:9326:3: ( (lv_features_1_0= ruleFT_ID ) )
            // InternalFml.g:9327:4: (lv_features_1_0= ruleFT_ID )
            {
            // InternalFml.g:9327:4: (lv_features_1_0= ruleFT_ID )
            // InternalFml.g:9328:5: lv_features_1_0= ruleFT_ID
            {

            					newCompositeNode(grammarAccess.getXorgroupAccess().getFeaturesFT_IDParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_85);
            lv_features_1_0=ruleFT_ID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXorgroupRule());
            					}
            					add(
            						current,
            						"features",
            						lv_features_1_0,
            						"org.xtext.example.mydsl.Fml.FT_ID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:9345:3: (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+
            int cnt111=0;
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==RULE_B_OR) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // InternalFml.g:9346:4: this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) )
            	    {
            	    this_B_OR_2=(Token)match(input,RULE_B_OR,FOLLOW_37); 

            	    				newLeafNode(this_B_OR_2, grammarAccess.getXorgroupAccess().getB_ORTerminalRuleCall_2_0());
            	    			
            	    // InternalFml.g:9350:4: ( (lv_features_3_0= ruleFT_ID ) )
            	    // InternalFml.g:9351:5: (lv_features_3_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:9351:5: (lv_features_3_0= ruleFT_ID )
            	    // InternalFml.g:9352:6: lv_features_3_0= ruleFT_ID
            	    {

            	    						newCompositeNode(grammarAccess.getXorgroupAccess().getFeaturesFT_IDParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_86);
            	    lv_features_3_0=ruleFT_ID();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXorgroupRule());
            	    						}
            	    						add(
            	    							current,
            	    							"features",
            	    							lv_features_3_0,
            	    							"org.xtext.example.mydsl.Fml.FT_ID");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt111 >= 1 ) break loop111;
                        EarlyExitException eee =
                            new EarlyExitException(111, input);
                        throw eee;
                }
                cnt111++;
            } while (true);

            this_RIGHT_PAREN_4=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_2); 

            			newLeafNode(this_RIGHT_PAREN_4, grammarAccess.getXorgroupAccess().getRIGHT_PARENTerminalRuleCall_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXorgroup"


    // $ANTLR start "entryRuleOrgroup"
    // InternalFml.g:9378:1: entryRuleOrgroup returns [EObject current=null] : iv_ruleOrgroup= ruleOrgroup EOF ;
    public final EObject entryRuleOrgroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrgroup = null;


        try {
            // InternalFml.g:9378:48: (iv_ruleOrgroup= ruleOrgroup EOF )
            // InternalFml.g:9379:2: iv_ruleOrgroup= ruleOrgroup EOF
            {
             newCompositeNode(grammarAccess.getOrgroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrgroup=ruleOrgroup();

            state._fsp--;

             current =iv_ruleOrgroup; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrgroup"


    // $ANTLR start "ruleOrgroup"
    // InternalFml.g:9385:1: ruleOrgroup returns [EObject current=null] : (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN this_PLUS_5= RULE_PLUS ) ;
    public final EObject ruleOrgroup() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_PAREN_0=null;
        Token this_B_OR_2=null;
        Token this_RIGHT_PAREN_4=null;
        Token this_PLUS_5=null;
        AntlrDatatypeRuleToken lv_features_1_0 = null;

        AntlrDatatypeRuleToken lv_features_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9391:2: ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN this_PLUS_5= RULE_PLUS ) )
            // InternalFml.g:9392:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN this_PLUS_5= RULE_PLUS )
            {
            // InternalFml.g:9392:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN this_PLUS_5= RULE_PLUS )
            // InternalFml.g:9393:3: this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ this_RIGHT_PAREN_4= RULE_RIGHT_PAREN this_PLUS_5= RULE_PLUS
            {
            this_LEFT_PAREN_0=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_37); 

            			newLeafNode(this_LEFT_PAREN_0, grammarAccess.getOrgroupAccess().getLEFT_PARENTerminalRuleCall_0());
            		
            // InternalFml.g:9397:3: ( (lv_features_1_0= ruleFT_ID ) )
            // InternalFml.g:9398:4: (lv_features_1_0= ruleFT_ID )
            {
            // InternalFml.g:9398:4: (lv_features_1_0= ruleFT_ID )
            // InternalFml.g:9399:5: lv_features_1_0= ruleFT_ID
            {

            					newCompositeNode(grammarAccess.getOrgroupAccess().getFeaturesFT_IDParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_85);
            lv_features_1_0=ruleFT_ID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOrgroupRule());
            					}
            					add(
            						current,
            						"features",
            						lv_features_1_0,
            						"org.xtext.example.mydsl.Fml.FT_ID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:9416:3: (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+
            int cnt112=0;
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==RULE_B_OR) ) {
                    alt112=1;
                }


                switch (alt112) {
            	case 1 :
            	    // InternalFml.g:9417:4: this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) )
            	    {
            	    this_B_OR_2=(Token)match(input,RULE_B_OR,FOLLOW_37); 

            	    				newLeafNode(this_B_OR_2, grammarAccess.getOrgroupAccess().getB_ORTerminalRuleCall_2_0());
            	    			
            	    // InternalFml.g:9421:4: ( (lv_features_3_0= ruleFT_ID ) )
            	    // InternalFml.g:9422:5: (lv_features_3_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:9422:5: (lv_features_3_0= ruleFT_ID )
            	    // InternalFml.g:9423:6: lv_features_3_0= ruleFT_ID
            	    {

            	    						newCompositeNode(grammarAccess.getOrgroupAccess().getFeaturesFT_IDParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_86);
            	    lv_features_3_0=ruleFT_ID();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOrgroupRule());
            	    						}
            	    						add(
            	    							current,
            	    							"features",
            	    							lv_features_3_0,
            	    							"org.xtext.example.mydsl.Fml.FT_ID");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt112 >= 1 ) break loop112;
                        EarlyExitException eee =
                            new EarlyExitException(112, input);
                        throw eee;
                }
                cnt112++;
            } while (true);

            this_RIGHT_PAREN_4=(Token)match(input,RULE_RIGHT_PAREN,FOLLOW_87); 

            			newLeafNode(this_RIGHT_PAREN_4, grammarAccess.getOrgroupAccess().getRIGHT_PARENTerminalRuleCall_3());
            		
            this_PLUS_5=(Token)match(input,RULE_PLUS,FOLLOW_2); 

            			newLeafNode(this_PLUS_5, grammarAccess.getOrgroupAccess().getPLUSTerminalRuleCall_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrgroup"


    // $ANTLR start "entryRuleMutexgroup"
    // InternalFml.g:9453:1: entryRuleMutexgroup returns [EObject current=null] : iv_ruleMutexgroup= ruleMutexgroup EOF ;
    public final EObject entryRuleMutexgroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMutexgroup = null;


        try {
            // InternalFml.g:9453:51: (iv_ruleMutexgroup= ruleMutexgroup EOF )
            // InternalFml.g:9454:2: iv_ruleMutexgroup= ruleMutexgroup EOF
            {
             newCompositeNode(grammarAccess.getMutexgroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMutexgroup=ruleMutexgroup();

            state._fsp--;

             current =iv_ruleMutexgroup; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMutexgroup"


    // $ANTLR start "ruleMutexgroup"
    // InternalFml.g:9460:1: ruleMutexgroup returns [EObject current=null] : (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ otherlv_4= ')?' ) ;
    public final EObject ruleMutexgroup() throws RecognitionException {
        EObject current = null;

        Token this_LEFT_PAREN_0=null;
        Token this_B_OR_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_features_1_0 = null;

        AntlrDatatypeRuleToken lv_features_3_0 = null;



        	enterRule();

        try {
            // InternalFml.g:9466:2: ( (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ otherlv_4= ')?' ) )
            // InternalFml.g:9467:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ otherlv_4= ')?' )
            {
            // InternalFml.g:9467:2: (this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ otherlv_4= ')?' )
            // InternalFml.g:9468:3: this_LEFT_PAREN_0= RULE_LEFT_PAREN ( (lv_features_1_0= ruleFT_ID ) ) (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+ otherlv_4= ')?'
            {
            this_LEFT_PAREN_0=(Token)match(input,RULE_LEFT_PAREN,FOLLOW_37); 

            			newLeafNode(this_LEFT_PAREN_0, grammarAccess.getMutexgroupAccess().getLEFT_PARENTerminalRuleCall_0());
            		
            // InternalFml.g:9472:3: ( (lv_features_1_0= ruleFT_ID ) )
            // InternalFml.g:9473:4: (lv_features_1_0= ruleFT_ID )
            {
            // InternalFml.g:9473:4: (lv_features_1_0= ruleFT_ID )
            // InternalFml.g:9474:5: lv_features_1_0= ruleFT_ID
            {

            					newCompositeNode(grammarAccess.getMutexgroupAccess().getFeaturesFT_IDParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_85);
            lv_features_1_0=ruleFT_ID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMutexgroupRule());
            					}
            					add(
            						current,
            						"features",
            						lv_features_1_0,
            						"org.xtext.example.mydsl.Fml.FT_ID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFml.g:9491:3: (this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) ) )+
            int cnt113=0;
            loop113:
            do {
                int alt113=2;
                int LA113_0 = input.LA(1);

                if ( (LA113_0==RULE_B_OR) ) {
                    alt113=1;
                }


                switch (alt113) {
            	case 1 :
            	    // InternalFml.g:9492:4: this_B_OR_2= RULE_B_OR ( (lv_features_3_0= ruleFT_ID ) )
            	    {
            	    this_B_OR_2=(Token)match(input,RULE_B_OR,FOLLOW_37); 

            	    				newLeafNode(this_B_OR_2, grammarAccess.getMutexgroupAccess().getB_ORTerminalRuleCall_2_0());
            	    			
            	    // InternalFml.g:9496:4: ( (lv_features_3_0= ruleFT_ID ) )
            	    // InternalFml.g:9497:5: (lv_features_3_0= ruleFT_ID )
            	    {
            	    // InternalFml.g:9497:5: (lv_features_3_0= ruleFT_ID )
            	    // InternalFml.g:9498:6: lv_features_3_0= ruleFT_ID
            	    {

            	    						newCompositeNode(grammarAccess.getMutexgroupAccess().getFeaturesFT_IDParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_88);
            	    lv_features_3_0=ruleFT_ID();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMutexgroupRule());
            	    						}
            	    						add(
            	    							current,
            	    							"features",
            	    							lv_features_3_0,
            	    							"org.xtext.example.mydsl.Fml.FT_ID");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt113 >= 1 ) break loop113;
                        EarlyExitException eee =
                            new EarlyExitException(113, input);
                        throw eee;
                }
                cnt113++;
            } while (true);

            otherlv_4=(Token)match(input,167,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getMutexgroupAccess().getRightParenthesisQuestionMarkKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMutexgroup"


    // $ANTLR start "entryRuleFT_ID"
    // InternalFml.g:9524:1: entryRuleFT_ID returns [String current=null] : iv_ruleFT_ID= ruleFT_ID EOF ;
    public final String entryRuleFT_ID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFT_ID = null;


        try {
            // InternalFml.g:9524:45: (iv_ruleFT_ID= ruleFT_ID EOF )
            // InternalFml.g:9525:2: iv_ruleFT_ID= ruleFT_ID EOF
            {
             newCompositeNode(grammarAccess.getFT_IDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFT_ID=ruleFT_ID();

            state._fsp--;

             current =iv_ruleFT_ID.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFT_ID"


    // $ANTLR start "ruleFT_ID"
    // InternalFml.g:9531:1: ruleFT_ID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | kw= '$' | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleFT_ID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_STRING_2=null;


        	enterRule();

        try {
            // InternalFml.g:9537:2: ( (this_ID_0= RULE_ID | kw= '$' | this_STRING_2= RULE_STRING ) )
            // InternalFml.g:9538:2: (this_ID_0= RULE_ID | kw= '$' | this_STRING_2= RULE_STRING )
            {
            // InternalFml.g:9538:2: (this_ID_0= RULE_ID | kw= '$' | this_STRING_2= RULE_STRING )
            int alt114=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt114=1;
                }
                break;
            case 168:
                {
                alt114=2;
                }
                break;
            case RULE_STRING:
                {
                alt114=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;
            }

            switch (alt114) {
                case 1 :
                    // InternalFml.g:9539:3: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_0);
                    		

                    			newLeafNode(this_ID_0, grammarAccess.getFT_IDAccess().getIDTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalFml.g:9547:3: kw= '$'
                    {
                    kw=(Token)match(input,168,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getFT_IDAccess().getDollarSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalFml.g:9553:3: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_2);
                    		

                    			newLeafNode(this_STRING_2, grammarAccess.getFT_IDAccess().getSTRINGTerminalRuleCall_2());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFT_ID"


    // $ANTLR start "entryRuleFML_IDENTIFIER"
    // InternalFml.g:9564:1: entryRuleFML_IDENTIFIER returns [String current=null] : iv_ruleFML_IDENTIFIER= ruleFML_IDENTIFIER EOF ;
    public final String entryRuleFML_IDENTIFIER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFML_IDENTIFIER = null;


        try {
            // InternalFml.g:9564:54: (iv_ruleFML_IDENTIFIER= ruleFML_IDENTIFIER EOF )
            // InternalFml.g:9565:2: iv_ruleFML_IDENTIFIER= ruleFML_IDENTIFIER EOF
            {
             newCompositeNode(grammarAccess.getFML_IDENTIFIERRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFML_IDENTIFIER=ruleFML_IDENTIFIER();

            state._fsp--;

             current =iv_ruleFML_IDENTIFIER.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFML_IDENTIFIER"


    // $ANTLR start "ruleFML_IDENTIFIER"
    // InternalFml.g:9571:1: ruleFML_IDENTIFIER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* ) | (this_ID_7= RULE_ID this_STAR_8= RULE_STAR ) ) ;
    public final AntlrDatatypeRuleToken ruleFML_IDENTIFIER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_3=null;
        Token this_STAR_5=null;
        Token this_STRING_6=null;
        Token this_ID_7=null;
        Token this_STAR_8=null;


        	enterRule();

        try {
            // InternalFml.g:9577:2: ( ( ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* ) | (this_ID_7= RULE_ID this_STAR_8= RULE_STAR ) ) )
            // InternalFml.g:9578:2: ( ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* ) | (this_ID_7= RULE_ID this_STAR_8= RULE_STAR ) )
            {
            // InternalFml.g:9578:2: ( ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* ) | (this_ID_7= RULE_ID this_STAR_8= RULE_STAR ) )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==RULE_ID) ) {
                int LA118_1 = input.LA(2);

                if ( (LA118_1==EOF||LA118_1==RULE_LEFT_HOOK||(LA118_1>=RULE_RIGHT_HOOK && LA118_1<=RULE_ID)||(LA118_1>=30 && LA118_1<=35)||LA118_1==37||(LA118_1>=39 && LA118_1<=42)||(LA118_1>=53 && LA118_1<=93)||LA118_1==95||LA118_1==97||LA118_1==99||(LA118_1>=106 && LA118_1<=107)||(LA118_1>=109 && LA118_1<=166)||(LA118_1>=168 && LA118_1<=189)||(LA118_1>=201 && LA118_1<=213)||(LA118_1>=229 && LA118_1<=230)) ) {
                    alt118=1;
                }
                else if ( (LA118_1==RULE_STAR) ) {
                    alt118=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 118, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA118_0==168) ) {
                alt118=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // InternalFml.g:9579:3: ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* )
                    {
                    // InternalFml.g:9579:3: ( (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )* )
                    // InternalFml.g:9580:4: (this_ID_0= RULE_ID | kw= '$' ) (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )*
                    {
                    // InternalFml.g:9580:4: (this_ID_0= RULE_ID | kw= '$' )
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==RULE_ID) ) {
                        alt115=1;
                    }
                    else if ( (LA115_0==168) ) {
                        alt115=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 115, 0, input);

                        throw nvae;
                    }
                    switch (alt115) {
                        case 1 :
                            // InternalFml.g:9581:5: this_ID_0= RULE_ID
                            {
                            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_89); 

                            					current.merge(this_ID_0);
                            				

                            					newLeafNode(this_ID_0, grammarAccess.getFML_IDENTIFIERAccess().getIDTerminalRuleCall_0_0_0());
                            				

                            }
                            break;
                        case 2 :
                            // InternalFml.g:9589:5: kw= '$'
                            {
                            kw=(Token)match(input,168,FOLLOW_89); 

                            					current.merge(kw);
                            					newLeafNode(kw, grammarAccess.getFML_IDENTIFIERAccess().getDollarSignKeyword_0_0_1());
                            				

                            }
                            break;

                    }

                    // InternalFml.g:9595:4: (kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING ) )*
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==169) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // InternalFml.g:9596:5: kw= '.' (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING )
                    	    {
                    	    kw=(Token)match(input,169,FOLLOW_90); 

                    	    					current.merge(kw);
                    	    					newLeafNode(kw, grammarAccess.getFML_IDENTIFIERAccess().getFullStopKeyword_0_1_0());
                    	    				
                    	    // InternalFml.g:9601:5: (this_ID_3= RULE_ID | kw= '$' | this_STAR_5= RULE_STAR | this_STRING_6= RULE_STRING )
                    	    int alt116=4;
                    	    switch ( input.LA(1) ) {
                    	    case RULE_ID:
                    	        {
                    	        alt116=1;
                    	        }
                    	        break;
                    	    case 168:
                    	        {
                    	        alt116=2;
                    	        }
                    	        break;
                    	    case RULE_STAR:
                    	        {
                    	        alt116=3;
                    	        }
                    	        break;
                    	    case RULE_STRING:
                    	        {
                    	        alt116=4;
                    	        }
                    	        break;
                    	    default:
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 116, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt116) {
                    	        case 1 :
                    	            // InternalFml.g:9602:6: this_ID_3= RULE_ID
                    	            {
                    	            this_ID_3=(Token)match(input,RULE_ID,FOLLOW_89); 

                    	            						current.merge(this_ID_3);
                    	            					

                    	            						newLeafNode(this_ID_3, grammarAccess.getFML_IDENTIFIERAccess().getIDTerminalRuleCall_0_1_1_0());
                    	            					

                    	            }
                    	            break;
                    	        case 2 :
                    	            // InternalFml.g:9610:6: kw= '$'
                    	            {
                    	            kw=(Token)match(input,168,FOLLOW_89); 

                    	            						current.merge(kw);
                    	            						newLeafNode(kw, grammarAccess.getFML_IDENTIFIERAccess().getDollarSignKeyword_0_1_1_1());
                    	            					

                    	            }
                    	            break;
                    	        case 3 :
                    	            // InternalFml.g:9616:6: this_STAR_5= RULE_STAR
                    	            {
                    	            this_STAR_5=(Token)match(input,RULE_STAR,FOLLOW_89); 

                    	            						current.merge(this_STAR_5);
                    	            					

                    	            						newLeafNode(this_STAR_5, grammarAccess.getFML_IDENTIFIERAccess().getSTARTerminalRuleCall_0_1_1_2());
                    	            					

                    	            }
                    	            break;
                    	        case 4 :
                    	            // InternalFml.g:9624:6: this_STRING_6= RULE_STRING
                    	            {
                    	            this_STRING_6=(Token)match(input,RULE_STRING,FOLLOW_89); 

                    	            						current.merge(this_STRING_6);
                    	            					

                    	            						newLeafNode(this_STRING_6, grammarAccess.getFML_IDENTIFIERAccess().getSTRINGTerminalRuleCall_0_1_1_3());
                    	            					

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop117;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9635:3: (this_ID_7= RULE_ID this_STAR_8= RULE_STAR )
                    {
                    // InternalFml.g:9635:3: (this_ID_7= RULE_ID this_STAR_8= RULE_STAR )
                    // InternalFml.g:9636:4: this_ID_7= RULE_ID this_STAR_8= RULE_STAR
                    {
                    this_ID_7=(Token)match(input,RULE_ID,FOLLOW_91); 

                    				current.merge(this_ID_7);
                    			

                    				newLeafNode(this_ID_7, grammarAccess.getFML_IDENTIFIERAccess().getIDTerminalRuleCall_1_0());
                    			
                    this_STAR_8=(Token)match(input,RULE_STAR,FOLLOW_2); 

                    				current.merge(this_STAR_8);
                    			

                    				newLeafNode(this_STAR_8, grammarAccess.getFML_IDENTIFIERAccess().getSTARTerminalRuleCall_1_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFML_IDENTIFIER"


    // $ANTLR start "ruleFeatureEdgeKind"
    // InternalFml.g:9655:1: ruleFeatureEdgeKind returns [Enumerator current=null] : ( (enumLiteral_0= 'mand' ) | (enumLiteral_1= 'opt' ) | (enumLiteral_2= 'Xor' ) | (enumLiteral_3= 'Or' ) | (enumLiteral_4= 'Mutex' ) ) ;
    public final Enumerator ruleFeatureEdgeKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;


        	enterRule();

        try {
            // InternalFml.g:9661:2: ( ( (enumLiteral_0= 'mand' ) | (enumLiteral_1= 'opt' ) | (enumLiteral_2= 'Xor' ) | (enumLiteral_3= 'Or' ) | (enumLiteral_4= 'Mutex' ) ) )
            // InternalFml.g:9662:2: ( (enumLiteral_0= 'mand' ) | (enumLiteral_1= 'opt' ) | (enumLiteral_2= 'Xor' ) | (enumLiteral_3= 'Or' ) | (enumLiteral_4= 'Mutex' ) )
            {
            // InternalFml.g:9662:2: ( (enumLiteral_0= 'mand' ) | (enumLiteral_1= 'opt' ) | (enumLiteral_2= 'Xor' ) | (enumLiteral_3= 'Or' ) | (enumLiteral_4= 'Mutex' ) )
            int alt119=5;
            switch ( input.LA(1) ) {
            case 170:
                {
                alt119=1;
                }
                break;
            case 171:
                {
                alt119=2;
                }
                break;
            case 172:
                {
                alt119=3;
                }
                break;
            case 173:
                {
                alt119=4;
                }
                break;
            case 174:
                {
                alt119=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 119, 0, input);

                throw nvae;
            }

            switch (alt119) {
                case 1 :
                    // InternalFml.g:9663:3: (enumLiteral_0= 'mand' )
                    {
                    // InternalFml.g:9663:3: (enumLiteral_0= 'mand' )
                    // InternalFml.g:9664:4: enumLiteral_0= 'mand'
                    {
                    enumLiteral_0=(Token)match(input,170,FOLLOW_2); 

                    				current = grammarAccess.getFeatureEdgeKindAccess().getMANDATORYEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFeatureEdgeKindAccess().getMANDATORYEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9671:3: (enumLiteral_1= 'opt' )
                    {
                    // InternalFml.g:9671:3: (enumLiteral_1= 'opt' )
                    // InternalFml.g:9672:4: enumLiteral_1= 'opt'
                    {
                    enumLiteral_1=(Token)match(input,171,FOLLOW_2); 

                    				current = grammarAccess.getFeatureEdgeKindAccess().getOPTIONALEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFeatureEdgeKindAccess().getOPTIONALEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9679:3: (enumLiteral_2= 'Xor' )
                    {
                    // InternalFml.g:9679:3: (enumLiteral_2= 'Xor' )
                    // InternalFml.g:9680:4: enumLiteral_2= 'Xor'
                    {
                    enumLiteral_2=(Token)match(input,172,FOLLOW_2); 

                    				current = grammarAccess.getFeatureEdgeKindAccess().getALTERNATIVEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getFeatureEdgeKindAccess().getALTERNATIVEEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalFml.g:9687:3: (enumLiteral_3= 'Or' )
                    {
                    // InternalFml.g:9687:3: (enumLiteral_3= 'Or' )
                    // InternalFml.g:9688:4: enumLiteral_3= 'Or'
                    {
                    enumLiteral_3=(Token)match(input,173,FOLLOW_2); 

                    				current = grammarAccess.getFeatureEdgeKindAccess().getOREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getFeatureEdgeKindAccess().getOREnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalFml.g:9695:3: (enumLiteral_4= 'Mutex' )
                    {
                    // InternalFml.g:9695:3: (enumLiteral_4= 'Mutex' )
                    // InternalFml.g:9696:4: enumLiteral_4= 'Mutex'
                    {
                    enumLiteral_4=(Token)match(input,174,FOLLOW_2); 

                    				current = grammarAccess.getFeatureEdgeKindAccess().getMUTEXEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getFeatureEdgeKindAccess().getMUTEXEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeatureEdgeKind"


    // $ANTLR start "ruleKindOfGet"
    // InternalFml.g:9706:1: ruleKindOfGet returns [Enumerator current=null] : ( (enumLiteral_0= 'getImpliesHierarchy' ) | (enumLiteral_1= 'getExcludesHierarchy' ) | (enumLiteral_2= 'getBiimpliesHierarchy' ) | (enumLiteral_3= 'getImpliesConstraint' ) | (enumLiteral_4= 'getExcludesConstraint' ) | (enumLiteral_5= 'getBiimpliesConstraint' ) ) ;
    public final Enumerator ruleKindOfGet() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalFml.g:9712:2: ( ( (enumLiteral_0= 'getImpliesHierarchy' ) | (enumLiteral_1= 'getExcludesHierarchy' ) | (enumLiteral_2= 'getBiimpliesHierarchy' ) | (enumLiteral_3= 'getImpliesConstraint' ) | (enumLiteral_4= 'getExcludesConstraint' ) | (enumLiteral_5= 'getBiimpliesConstraint' ) ) )
            // InternalFml.g:9713:2: ( (enumLiteral_0= 'getImpliesHierarchy' ) | (enumLiteral_1= 'getExcludesHierarchy' ) | (enumLiteral_2= 'getBiimpliesHierarchy' ) | (enumLiteral_3= 'getImpliesConstraint' ) | (enumLiteral_4= 'getExcludesConstraint' ) | (enumLiteral_5= 'getBiimpliesConstraint' ) )
            {
            // InternalFml.g:9713:2: ( (enumLiteral_0= 'getImpliesHierarchy' ) | (enumLiteral_1= 'getExcludesHierarchy' ) | (enumLiteral_2= 'getBiimpliesHierarchy' ) | (enumLiteral_3= 'getImpliesConstraint' ) | (enumLiteral_4= 'getExcludesConstraint' ) | (enumLiteral_5= 'getBiimpliesConstraint' ) )
            int alt120=6;
            switch ( input.LA(1) ) {
            case 175:
                {
                alt120=1;
                }
                break;
            case 176:
                {
                alt120=2;
                }
                break;
            case 177:
                {
                alt120=3;
                }
                break;
            case 178:
                {
                alt120=4;
                }
                break;
            case 179:
                {
                alt120=5;
                }
                break;
            case 180:
                {
                alt120=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 120, 0, input);

                throw nvae;
            }

            switch (alt120) {
                case 1 :
                    // InternalFml.g:9714:3: (enumLiteral_0= 'getImpliesHierarchy' )
                    {
                    // InternalFml.g:9714:3: (enumLiteral_0= 'getImpliesHierarchy' )
                    // InternalFml.g:9715:4: enumLiteral_0= 'getImpliesHierarchy'
                    {
                    enumLiteral_0=(Token)match(input,175,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getHIERARCHY_IMPLIESEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getKindOfGetAccess().getHIERARCHY_IMPLIESEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9722:3: (enumLiteral_1= 'getExcludesHierarchy' )
                    {
                    // InternalFml.g:9722:3: (enumLiteral_1= 'getExcludesHierarchy' )
                    // InternalFml.g:9723:4: enumLiteral_1= 'getExcludesHierarchy'
                    {
                    enumLiteral_1=(Token)match(input,176,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getHIERARCHY_EXCLUDESEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getKindOfGetAccess().getHIERARCHY_EXCLUDESEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9730:3: (enumLiteral_2= 'getBiimpliesHierarchy' )
                    {
                    // InternalFml.g:9730:3: (enumLiteral_2= 'getBiimpliesHierarchy' )
                    // InternalFml.g:9731:4: enumLiteral_2= 'getBiimpliesHierarchy'
                    {
                    enumLiteral_2=(Token)match(input,177,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getHIERARCHY_BIIMPLIESEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getKindOfGetAccess().getHIERARCHY_BIIMPLIESEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalFml.g:9738:3: (enumLiteral_3= 'getImpliesConstraint' )
                    {
                    // InternalFml.g:9738:3: (enumLiteral_3= 'getImpliesConstraint' )
                    // InternalFml.g:9739:4: enumLiteral_3= 'getImpliesConstraint'
                    {
                    enumLiteral_3=(Token)match(input,178,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getCONSTRAINT_IMPLIESEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getKindOfGetAccess().getCONSTRAINT_IMPLIESEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalFml.g:9746:3: (enumLiteral_4= 'getExcludesConstraint' )
                    {
                    // InternalFml.g:9746:3: (enumLiteral_4= 'getExcludesConstraint' )
                    // InternalFml.g:9747:4: enumLiteral_4= 'getExcludesConstraint'
                    {
                    enumLiteral_4=(Token)match(input,179,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getCONSTRAINT_EXCLUDESEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getKindOfGetAccess().getCONSTRAINT_EXCLUDESEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalFml.g:9754:3: (enumLiteral_5= 'getBiimpliesConstraint' )
                    {
                    // InternalFml.g:9754:3: (enumLiteral_5= 'getBiimpliesConstraint' )
                    // InternalFml.g:9755:4: enumLiteral_5= 'getBiimpliesConstraint'
                    {
                    enumLiteral_5=(Token)match(input,180,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetAccess().getCONSTRAINT_BIIMPLIESEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getKindOfGetAccess().getCONSTRAINT_BIIMPLIESEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKindOfGet"


    // $ANTLR start "ruleKindOfCompute"
    // InternalFml.g:9765:1: ruleKindOfCompute returns [Enumerator current=null] : ( (enumLiteral_0= 'computeImplies' ) | (enumLiteral_1= 'computeExcludes' ) | (enumLiteral_2= 'computeBiimplies' ) ) ;
    public final Enumerator ruleKindOfCompute() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:9771:2: ( ( (enumLiteral_0= 'computeImplies' ) | (enumLiteral_1= 'computeExcludes' ) | (enumLiteral_2= 'computeBiimplies' ) ) )
            // InternalFml.g:9772:2: ( (enumLiteral_0= 'computeImplies' ) | (enumLiteral_1= 'computeExcludes' ) | (enumLiteral_2= 'computeBiimplies' ) )
            {
            // InternalFml.g:9772:2: ( (enumLiteral_0= 'computeImplies' ) | (enumLiteral_1= 'computeExcludes' ) | (enumLiteral_2= 'computeBiimplies' ) )
            int alt121=3;
            switch ( input.LA(1) ) {
            case 181:
                {
                alt121=1;
                }
                break;
            case 182:
                {
                alt121=2;
                }
                break;
            case 183:
                {
                alt121=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;
            }

            switch (alt121) {
                case 1 :
                    // InternalFml.g:9773:3: (enumLiteral_0= 'computeImplies' )
                    {
                    // InternalFml.g:9773:3: (enumLiteral_0= 'computeImplies' )
                    // InternalFml.g:9774:4: enumLiteral_0= 'computeImplies'
                    {
                    enumLiteral_0=(Token)match(input,181,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeAccess().getIMPLIESEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getKindOfComputeAccess().getIMPLIESEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9781:3: (enumLiteral_1= 'computeExcludes' )
                    {
                    // InternalFml.g:9781:3: (enumLiteral_1= 'computeExcludes' )
                    // InternalFml.g:9782:4: enumLiteral_1= 'computeExcludes'
                    {
                    enumLiteral_1=(Token)match(input,182,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeAccess().getEXCLUDESEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getKindOfComputeAccess().getEXCLUDESEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9789:3: (enumLiteral_2= 'computeBiimplies' )
                    {
                    // InternalFml.g:9789:3: (enumLiteral_2= 'computeBiimplies' )
                    // InternalFml.g:9790:4: enumLiteral_2= 'computeBiimplies'
                    {
                    enumLiteral_2=(Token)match(input,183,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeAccess().getBIIMPLIESEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getKindOfComputeAccess().getBIIMPLIESEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKindOfCompute"


    // $ANTLR start "ruleKindOfGetGroups"
    // InternalFml.g:9800:1: ruleKindOfGetGroups returns [Enumerator current=null] : ( (enumLiteral_0= 'getORGroups' ) | (enumLiteral_1= 'getXORGroups' ) | (enumLiteral_2= 'getMUTEXGroups' ) ) ;
    public final Enumerator ruleKindOfGetGroups() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:9806:2: ( ( (enumLiteral_0= 'getORGroups' ) | (enumLiteral_1= 'getXORGroups' ) | (enumLiteral_2= 'getMUTEXGroups' ) ) )
            // InternalFml.g:9807:2: ( (enumLiteral_0= 'getORGroups' ) | (enumLiteral_1= 'getXORGroups' ) | (enumLiteral_2= 'getMUTEXGroups' ) )
            {
            // InternalFml.g:9807:2: ( (enumLiteral_0= 'getORGroups' ) | (enumLiteral_1= 'getXORGroups' ) | (enumLiteral_2= 'getMUTEXGroups' ) )
            int alt122=3;
            switch ( input.LA(1) ) {
            case 184:
                {
                alt122=1;
                }
                break;
            case 185:
                {
                alt122=2;
                }
                break;
            case 186:
                {
                alt122=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }

            switch (alt122) {
                case 1 :
                    // InternalFml.g:9808:3: (enumLiteral_0= 'getORGroups' )
                    {
                    // InternalFml.g:9808:3: (enumLiteral_0= 'getORGroups' )
                    // InternalFml.g:9809:4: enumLiteral_0= 'getORGroups'
                    {
                    enumLiteral_0=(Token)match(input,184,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetGroupsAccess().getOREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getKindOfGetGroupsAccess().getOREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9816:3: (enumLiteral_1= 'getXORGroups' )
                    {
                    // InternalFml.g:9816:3: (enumLiteral_1= 'getXORGroups' )
                    // InternalFml.g:9817:4: enumLiteral_1= 'getXORGroups'
                    {
                    enumLiteral_1=(Token)match(input,185,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetGroupsAccess().getXOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getKindOfGetGroupsAccess().getXOREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9824:3: (enumLiteral_2= 'getMUTEXGroups' )
                    {
                    // InternalFml.g:9824:3: (enumLiteral_2= 'getMUTEXGroups' )
                    // InternalFml.g:9825:4: enumLiteral_2= 'getMUTEXGroups'
                    {
                    enumLiteral_2=(Token)match(input,186,FOLLOW_2); 

                    				current = grammarAccess.getKindOfGetGroupsAccess().getMUTEXEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getKindOfGetGroupsAccess().getMUTEXEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKindOfGetGroups"


    // $ANTLR start "ruleKindOfComputeGroups"
    // InternalFml.g:9835:1: ruleKindOfComputeGroups returns [Enumerator current=null] : ( (enumLiteral_0= 'computeORGroups' ) | (enumLiteral_1= 'computeXORGroups' ) | (enumLiteral_2= 'computeMUTEXGroups' ) ) ;
    public final Enumerator ruleKindOfComputeGroups() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:9841:2: ( ( (enumLiteral_0= 'computeORGroups' ) | (enumLiteral_1= 'computeXORGroups' ) | (enumLiteral_2= 'computeMUTEXGroups' ) ) )
            // InternalFml.g:9842:2: ( (enumLiteral_0= 'computeORGroups' ) | (enumLiteral_1= 'computeXORGroups' ) | (enumLiteral_2= 'computeMUTEXGroups' ) )
            {
            // InternalFml.g:9842:2: ( (enumLiteral_0= 'computeORGroups' ) | (enumLiteral_1= 'computeXORGroups' ) | (enumLiteral_2= 'computeMUTEXGroups' ) )
            int alt123=3;
            switch ( input.LA(1) ) {
            case 187:
                {
                alt123=1;
                }
                break;
            case 188:
                {
                alt123=2;
                }
                break;
            case 189:
                {
                alt123=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 123, 0, input);

                throw nvae;
            }

            switch (alt123) {
                case 1 :
                    // InternalFml.g:9843:3: (enumLiteral_0= 'computeORGroups' )
                    {
                    // InternalFml.g:9843:3: (enumLiteral_0= 'computeORGroups' )
                    // InternalFml.g:9844:4: enumLiteral_0= 'computeORGroups'
                    {
                    enumLiteral_0=(Token)match(input,187,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeGroupsAccess().getOREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getKindOfComputeGroupsAccess().getOREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9851:3: (enumLiteral_1= 'computeXORGroups' )
                    {
                    // InternalFml.g:9851:3: (enumLiteral_1= 'computeXORGroups' )
                    // InternalFml.g:9852:4: enumLiteral_1= 'computeXORGroups'
                    {
                    enumLiteral_1=(Token)match(input,188,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeGroupsAccess().getXOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getKindOfComputeGroupsAccess().getXOREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9859:3: (enumLiteral_2= 'computeMUTEXGroups' )
                    {
                    // InternalFml.g:9859:3: (enumLiteral_2= 'computeMUTEXGroups' )
                    // InternalFml.g:9860:4: enumLiteral_2= 'computeMUTEXGroups'
                    {
                    enumLiteral_2=(Token)match(input,189,FOLLOW_2); 

                    				current = grammarAccess.getKindOfComputeGroupsAccess().getMUTEXEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getKindOfComputeGroupsAccess().getMUTEXEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKindOfComputeGroups"


    // $ANTLR start "ruleBDDBackend"
    // InternalFml.g:9870:1: ruleBDDBackend returns [Enumerator current=null] : ( (enumLiteral_0= '@backend=DEFAULT' ) | (enumLiteral_1= '@backend=BDD' ) | (enumLiteral_2= '@backend=BDD_SPLOT' ) ) ;
    public final Enumerator ruleBDDBackend() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:9876:2: ( ( (enumLiteral_0= '@backend=DEFAULT' ) | (enumLiteral_1= '@backend=BDD' ) | (enumLiteral_2= '@backend=BDD_SPLOT' ) ) )
            // InternalFml.g:9877:2: ( (enumLiteral_0= '@backend=DEFAULT' ) | (enumLiteral_1= '@backend=BDD' ) | (enumLiteral_2= '@backend=BDD_SPLOT' ) )
            {
            // InternalFml.g:9877:2: ( (enumLiteral_0= '@backend=DEFAULT' ) | (enumLiteral_1= '@backend=BDD' ) | (enumLiteral_2= '@backend=BDD_SPLOT' ) )
            int alt124=3;
            switch ( input.LA(1) ) {
            case 190:
                {
                alt124=1;
                }
                break;
            case 191:
                {
                alt124=2;
                }
                break;
            case 192:
                {
                alt124=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);

                throw nvae;
            }

            switch (alt124) {
                case 1 :
                    // InternalFml.g:9878:3: (enumLiteral_0= '@backend=DEFAULT' )
                    {
                    // InternalFml.g:9878:3: (enumLiteral_0= '@backend=DEFAULT' )
                    // InternalFml.g:9879:4: enumLiteral_0= '@backend=DEFAULT'
                    {
                    enumLiteral_0=(Token)match(input,190,FOLLOW_2); 

                    				current = grammarAccess.getBDDBackendAccess().getBDD_DEFAULTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBDDBackendAccess().getBDD_DEFAULTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9886:3: (enumLiteral_1= '@backend=BDD' )
                    {
                    // InternalFml.g:9886:3: (enumLiteral_1= '@backend=BDD' )
                    // InternalFml.g:9887:4: enumLiteral_1= '@backend=BDD'
                    {
                    enumLiteral_1=(Token)match(input,191,FOLLOW_2); 

                    				current = grammarAccess.getBDDBackendAccess().getBDD_BASICEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBDDBackendAccess().getBDD_BASICEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9894:3: (enumLiteral_2= '@backend=BDD_SPLOT' )
                    {
                    // InternalFml.g:9894:3: (enumLiteral_2= '@backend=BDD_SPLOT' )
                    // InternalFml.g:9895:4: enumLiteral_2= '@backend=BDD_SPLOT'
                    {
                    enumLiteral_2=(Token)match(input,192,FOLLOW_2); 

                    				current = grammarAccess.getBDDBackendAccess().getBDD_SPLOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getBDDBackendAccess().getBDD_SPLOTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBDDBackend"


    // $ANTLR start "ruleMergeMode"
    // InternalFml.g:9905:1: ruleMergeMode returns [Enumerator current=null] : ( (enumLiteral_0= 'crossproduct' ) | (enumLiteral_1= 'union' ) | (enumLiteral_2= 'sunion' ) | (enumLiteral_3= 'intersection' ) | (enumLiteral_4= 'diff' ) ) ;
    public final Enumerator ruleMergeMode() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;


        	enterRule();

        try {
            // InternalFml.g:9911:2: ( ( (enumLiteral_0= 'crossproduct' ) | (enumLiteral_1= 'union' ) | (enumLiteral_2= 'sunion' ) | (enumLiteral_3= 'intersection' ) | (enumLiteral_4= 'diff' ) ) )
            // InternalFml.g:9912:2: ( (enumLiteral_0= 'crossproduct' ) | (enumLiteral_1= 'union' ) | (enumLiteral_2= 'sunion' ) | (enumLiteral_3= 'intersection' ) | (enumLiteral_4= 'diff' ) )
            {
            // InternalFml.g:9912:2: ( (enumLiteral_0= 'crossproduct' ) | (enumLiteral_1= 'union' ) | (enumLiteral_2= 'sunion' ) | (enumLiteral_3= 'intersection' ) | (enumLiteral_4= 'diff' ) )
            int alt125=5;
            switch ( input.LA(1) ) {
            case 193:
                {
                alt125=1;
                }
                break;
            case 194:
                {
                alt125=2;
                }
                break;
            case 195:
                {
                alt125=3;
                }
                break;
            case 196:
                {
                alt125=4;
                }
                break;
            case 197:
                {
                alt125=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 125, 0, input);

                throw nvae;
            }

            switch (alt125) {
                case 1 :
                    // InternalFml.g:9913:3: (enumLiteral_0= 'crossproduct' )
                    {
                    // InternalFml.g:9913:3: (enumLiteral_0= 'crossproduct' )
                    // InternalFml.g:9914:4: enumLiteral_0= 'crossproduct'
                    {
                    enumLiteral_0=(Token)match(input,193,FOLLOW_2); 

                    				current = grammarAccess.getMergeModeAccess().getCROSSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getMergeModeAccess().getCROSSEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9921:3: (enumLiteral_1= 'union' )
                    {
                    // InternalFml.g:9921:3: (enumLiteral_1= 'union' )
                    // InternalFml.g:9922:4: enumLiteral_1= 'union'
                    {
                    enumLiteral_1=(Token)match(input,194,FOLLOW_2); 

                    				current = grammarAccess.getMergeModeAccess().getUNIONEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getMergeModeAccess().getUNIONEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9929:3: (enumLiteral_2= 'sunion' )
                    {
                    // InternalFml.g:9929:3: (enumLiteral_2= 'sunion' )
                    // InternalFml.g:9930:4: enumLiteral_2= 'sunion'
                    {
                    enumLiteral_2=(Token)match(input,195,FOLLOW_2); 

                    				current = grammarAccess.getMergeModeAccess().getSUNIONEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getMergeModeAccess().getSUNIONEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalFml.g:9937:3: (enumLiteral_3= 'intersection' )
                    {
                    // InternalFml.g:9937:3: (enumLiteral_3= 'intersection' )
                    // InternalFml.g:9938:4: enumLiteral_3= 'intersection'
                    {
                    enumLiteral_3=(Token)match(input,196,FOLLOW_2); 

                    				current = grammarAccess.getMergeModeAccess().getINTEREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getMergeModeAccess().getINTEREnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalFml.g:9945:3: (enumLiteral_4= 'diff' )
                    {
                    // InternalFml.g:9945:3: (enumLiteral_4= 'diff' )
                    // InternalFml.g:9946:4: enumLiteral_4= 'diff'
                    {
                    enumLiteral_4=(Token)match(input,197,FOLLOW_2); 

                    				current = grammarAccess.getMergeModeAccess().getDIFFEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getMergeModeAccess().getDIFFEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMergeMode"


    // $ANTLR start "ruleHierarchyStrategy"
    // InternalFml.g:9956:1: ruleHierarchyStrategy returns [Enumerator current=null] : ( (enumLiteral_0= '=basic' ) | (enumLiteral_1= '=flat' ) | (enumLiteral_2= '=mst' ) ) ;
    public final Enumerator ruleHierarchyStrategy() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:9962:2: ( ( (enumLiteral_0= '=basic' ) | (enumLiteral_1= '=flat' ) | (enumLiteral_2= '=mst' ) ) )
            // InternalFml.g:9963:2: ( (enumLiteral_0= '=basic' ) | (enumLiteral_1= '=flat' ) | (enumLiteral_2= '=mst' ) )
            {
            // InternalFml.g:9963:2: ( (enumLiteral_0= '=basic' ) | (enumLiteral_1= '=flat' ) | (enumLiteral_2= '=mst' ) )
            int alt126=3;
            switch ( input.LA(1) ) {
            case 198:
                {
                alt126=1;
                }
                break;
            case 199:
                {
                alt126=2;
                }
                break;
            case 200:
                {
                alt126=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;
            }

            switch (alt126) {
                case 1 :
                    // InternalFml.g:9964:3: (enumLiteral_0= '=basic' )
                    {
                    // InternalFml.g:9964:3: (enumLiteral_0= '=basic' )
                    // InternalFml.g:9965:4: enumLiteral_0= '=basic'
                    {
                    enumLiteral_0=(Token)match(input,198,FOLLOW_2); 

                    				current = grammarAccess.getHierarchyStrategyAccess().getBASICEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getHierarchyStrategyAccess().getBASICEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:9972:3: (enumLiteral_1= '=flat' )
                    {
                    // InternalFml.g:9972:3: (enumLiteral_1= '=flat' )
                    // InternalFml.g:9973:4: enumLiteral_1= '=flat'
                    {
                    enumLiteral_1=(Token)match(input,199,FOLLOW_2); 

                    				current = grammarAccess.getHierarchyStrategyAccess().getFLATEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getHierarchyStrategyAccess().getFLATEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:9980:3: (enumLiteral_2= '=mst' )
                    {
                    // InternalFml.g:9980:3: (enumLiteral_2= '=mst' )
                    // InternalFml.g:9981:4: enumLiteral_2= '=mst'
                    {
                    enumLiteral_2=(Token)match(input,200,FOLLOW_2); 

                    				current = grammarAccess.getHierarchyStrategyAccess().getMSTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getHierarchyStrategyAccess().getMSTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHierarchyStrategy"


    // $ANTLR start "ruleSliceMode"
    // InternalFml.g:9991:1: ruleSliceMode returns [Enumerator current=null] : ( (enumLiteral_0= 'including' ) | (enumLiteral_1= 'excluding' ) ) ;
    public final Enumerator ruleSliceMode() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalFml.g:9997:2: ( ( (enumLiteral_0= 'including' ) | (enumLiteral_1= 'excluding' ) ) )
            // InternalFml.g:9998:2: ( (enumLiteral_0= 'including' ) | (enumLiteral_1= 'excluding' ) )
            {
            // InternalFml.g:9998:2: ( (enumLiteral_0= 'including' ) | (enumLiteral_1= 'excluding' ) )
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==201) ) {
                alt127=1;
            }
            else if ( (LA127_0==202) ) {
                alt127=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }
            switch (alt127) {
                case 1 :
                    // InternalFml.g:9999:3: (enumLiteral_0= 'including' )
                    {
                    // InternalFml.g:9999:3: (enumLiteral_0= 'including' )
                    // InternalFml.g:10000:4: enumLiteral_0= 'including'
                    {
                    enumLiteral_0=(Token)match(input,201,FOLLOW_2); 

                    				current = grammarAccess.getSliceModeAccess().getINCLUDINGEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSliceModeAccess().getINCLUDINGEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10007:3: (enumLiteral_1= 'excluding' )
                    {
                    // InternalFml.g:10007:3: (enumLiteral_1= 'excluding' )
                    // InternalFml.g:10008:4: enumLiteral_1= 'excluding'
                    {
                    enumLiteral_1=(Token)match(input,202,FOLLOW_2); 

                    				current = grammarAccess.getSliceModeAccess().getEXCLUDINGEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSliceModeAccess().getEXCLUDINGEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSliceMode"


    // $ANTLR start "ruleComparisonOperator"
    // InternalFml.g:10018:1: ruleComparisonOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'eq' ) | (enumLiteral_1= 'neq' ) | (enumLiteral_2= '<' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '==' ) | (enumLiteral_5= '!=' ) ) ;
    public final Enumerator ruleComparisonOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalFml.g:10024:2: ( ( (enumLiteral_0= 'eq' ) | (enumLiteral_1= 'neq' ) | (enumLiteral_2= '<' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '==' ) | (enumLiteral_5= '!=' ) ) )
            // InternalFml.g:10025:2: ( (enumLiteral_0= 'eq' ) | (enumLiteral_1= 'neq' ) | (enumLiteral_2= '<' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '==' ) | (enumLiteral_5= '!=' ) )
            {
            // InternalFml.g:10025:2: ( (enumLiteral_0= 'eq' ) | (enumLiteral_1= 'neq' ) | (enumLiteral_2= '<' ) | (enumLiteral_3= '>' ) | (enumLiteral_4= '==' ) | (enumLiteral_5= '!=' ) )
            int alt128=6;
            switch ( input.LA(1) ) {
            case 203:
                {
                alt128=1;
                }
                break;
            case 204:
                {
                alt128=2;
                }
                break;
            case 205:
                {
                alt128=3;
                }
                break;
            case 206:
                {
                alt128=4;
                }
                break;
            case 207:
                {
                alt128=5;
                }
                break;
            case 208:
                {
                alt128=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }

            switch (alt128) {
                case 1 :
                    // InternalFml.g:10026:3: (enumLiteral_0= 'eq' )
                    {
                    // InternalFml.g:10026:3: (enumLiteral_0= 'eq' )
                    // InternalFml.g:10027:4: enumLiteral_0= 'eq'
                    {
                    enumLiteral_0=(Token)match(input,203,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getEQUALEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getComparisonOperatorAccess().getEQUALEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10034:3: (enumLiteral_1= 'neq' )
                    {
                    // InternalFml.g:10034:3: (enumLiteral_1= 'neq' )
                    // InternalFml.g:10035:4: enumLiteral_1= 'neq'
                    {
                    enumLiteral_1=(Token)match(input,204,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getNotEqualEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getComparisonOperatorAccess().getNotEqualEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:10042:3: (enumLiteral_2= '<' )
                    {
                    // InternalFml.g:10042:3: (enumLiteral_2= '<' )
                    // InternalFml.g:10043:4: enumLiteral_2= '<'
                    {
                    enumLiteral_2=(Token)match(input,205,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getLesserThanEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getComparisonOperatorAccess().getLesserThanEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalFml.g:10050:3: (enumLiteral_3= '>' )
                    {
                    // InternalFml.g:10050:3: (enumLiteral_3= '>' )
                    // InternalFml.g:10051:4: enumLiteral_3= '>'
                    {
                    enumLiteral_3=(Token)match(input,206,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getGreaterThanEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getComparisonOperatorAccess().getGreaterThanEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalFml.g:10058:3: (enumLiteral_4= '==' )
                    {
                    // InternalFml.g:10058:3: (enumLiteral_4= '==' )
                    // InternalFml.g:10059:4: enumLiteral_4= '=='
                    {
                    enumLiteral_4=(Token)match(input,207,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getREF_EQUALEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getComparisonOperatorAccess().getREF_EQUALEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalFml.g:10066:3: (enumLiteral_5= '!=' )
                    {
                    // InternalFml.g:10066:3: (enumLiteral_5= '!=' )
                    // InternalFml.g:10067:4: enumLiteral_5= '!='
                    {
                    enumLiteral_5=(Token)match(input,208,FOLLOW_2); 

                    				current = grammarAccess.getComparisonOperatorAccess().getREF_NotEqualEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getComparisonOperatorAccess().getREF_NotEqualEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparisonOperator"


    // $ANTLR start "ruleSetOperator"
    // InternalFml.g:10077:1: ruleSetOperator returns [Enumerator current=null] : ( (enumLiteral_0= '++' ) | (enumLiteral_1= '--' ) ) ;
    public final Enumerator ruleSetOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalFml.g:10083:2: ( ( (enumLiteral_0= '++' ) | (enumLiteral_1= '--' ) ) )
            // InternalFml.g:10084:2: ( (enumLiteral_0= '++' ) | (enumLiteral_1= '--' ) )
            {
            // InternalFml.g:10084:2: ( (enumLiteral_0= '++' ) | (enumLiteral_1= '--' ) )
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==209) ) {
                alt129=1;
            }
            else if ( (LA129_0==210) ) {
                alt129=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 129, 0, input);

                throw nvae;
            }
            switch (alt129) {
                case 1 :
                    // InternalFml.g:10085:3: (enumLiteral_0= '++' )
                    {
                    // InternalFml.g:10085:3: (enumLiteral_0= '++' )
                    // InternalFml.g:10086:4: enumLiteral_0= '++'
                    {
                    enumLiteral_0=(Token)match(input,209,FOLLOW_2); 

                    				current = grammarAccess.getSetOperatorAccess().getSUNIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSetOperatorAccess().getSUNIONEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10093:3: (enumLiteral_1= '--' )
                    {
                    // InternalFml.g:10093:3: (enumLiteral_1= '--' )
                    // InternalFml.g:10094:4: enumLiteral_1= '--'
                    {
                    enumLiteral_1=(Token)match(input,210,FOLLOW_2); 

                    				current = grammarAccess.getSetOperatorAccess().getSDIFFEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSetOperatorAccess().getSDIFFEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetOperator"


    // $ANTLR start "ruleAutoConfMode"
    // InternalFml.g:10104:1: ruleAutoConfMode returns [Enumerator current=null] : ( (enumLiteral_0= 'RANDOM' ) | (enumLiteral_1= 'MAX' ) | (enumLiteral_2= 'MIN' ) ) ;
    public final Enumerator ruleAutoConfMode() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:10110:2: ( ( (enumLiteral_0= 'RANDOM' ) | (enumLiteral_1= 'MAX' ) | (enumLiteral_2= 'MIN' ) ) )
            // InternalFml.g:10111:2: ( (enumLiteral_0= 'RANDOM' ) | (enumLiteral_1= 'MAX' ) | (enumLiteral_2= 'MIN' ) )
            {
            // InternalFml.g:10111:2: ( (enumLiteral_0= 'RANDOM' ) | (enumLiteral_1= 'MAX' ) | (enumLiteral_2= 'MIN' ) )
            int alt130=3;
            switch ( input.LA(1) ) {
            case 211:
                {
                alt130=1;
                }
                break;
            case 212:
                {
                alt130=2;
                }
                break;
            case 213:
                {
                alt130=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 130, 0, input);

                throw nvae;
            }

            switch (alt130) {
                case 1 :
                    // InternalFml.g:10112:3: (enumLiteral_0= 'RANDOM' )
                    {
                    // InternalFml.g:10112:3: (enumLiteral_0= 'RANDOM' )
                    // InternalFml.g:10113:4: enumLiteral_0= 'RANDOM'
                    {
                    enumLiteral_0=(Token)match(input,211,FOLLOW_2); 

                    				current = grammarAccess.getAutoConfModeAccess().getRANDOMEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getAutoConfModeAccess().getRANDOMEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10120:3: (enumLiteral_1= 'MAX' )
                    {
                    // InternalFml.g:10120:3: (enumLiteral_1= 'MAX' )
                    // InternalFml.g:10121:4: enumLiteral_1= 'MAX'
                    {
                    enumLiteral_1=(Token)match(input,212,FOLLOW_2); 

                    				current = grammarAccess.getAutoConfModeAccess().getMAXEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getAutoConfModeAccess().getMAXEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:10128:3: (enumLiteral_2= 'MIN' )
                    {
                    // InternalFml.g:10128:3: (enumLiteral_2= 'MIN' )
                    // InternalFml.g:10129:4: enumLiteral_2= 'MIN'
                    {
                    enumLiteral_2=(Token)match(input,213,FOLLOW_2); 

                    				current = grammarAccess.getAutoConfModeAccess().getMINEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getAutoConfModeAccess().getMINEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAutoConfMode"


    // $ANTLR start "ruleOPT_LISTING"
    // InternalFml.g:10139:1: ruleOPT_LISTING returns [Enumerator current=null] : ( (enumLiteral_0= '--normal' ) | (enumLiteral_1= '--verbose' ) | (enumLiteral_2= '--withValues' ) ) ;
    public final Enumerator ruleOPT_LISTING() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFml.g:10145:2: ( ( (enumLiteral_0= '--normal' ) | (enumLiteral_1= '--verbose' ) | (enumLiteral_2= '--withValues' ) ) )
            // InternalFml.g:10146:2: ( (enumLiteral_0= '--normal' ) | (enumLiteral_1= '--verbose' ) | (enumLiteral_2= '--withValues' ) )
            {
            // InternalFml.g:10146:2: ( (enumLiteral_0= '--normal' ) | (enumLiteral_1= '--verbose' ) | (enumLiteral_2= '--withValues' ) )
            int alt131=3;
            switch ( input.LA(1) ) {
            case 214:
                {
                alt131=1;
                }
                break;
            case 215:
                {
                alt131=2;
                }
                break;
            case 216:
                {
                alt131=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 131, 0, input);

                throw nvae;
            }

            switch (alt131) {
                case 1 :
                    // InternalFml.g:10147:3: (enumLiteral_0= '--normal' )
                    {
                    // InternalFml.g:10147:3: (enumLiteral_0= '--normal' )
                    // InternalFml.g:10148:4: enumLiteral_0= '--normal'
                    {
                    enumLiteral_0=(Token)match(input,214,FOLLOW_2); 

                    				current = grammarAccess.getOPT_LISTINGAccess().getNORMALEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getOPT_LISTINGAccess().getNORMALEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10155:3: (enumLiteral_1= '--verbose' )
                    {
                    // InternalFml.g:10155:3: (enumLiteral_1= '--verbose' )
                    // InternalFml.g:10156:4: enumLiteral_1= '--verbose'
                    {
                    enumLiteral_1=(Token)match(input,215,FOLLOW_2); 

                    				current = grammarAccess.getOPT_LISTINGAccess().getVERBOSEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getOPT_LISTINGAccess().getVERBOSEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:10163:3: (enumLiteral_2= '--withValues' )
                    {
                    // InternalFml.g:10163:3: (enumLiteral_2= '--withValues' )
                    // InternalFml.g:10164:4: enumLiteral_2= '--withValues'
                    {
                    enumLiteral_2=(Token)match(input,216,FOLLOW_2); 

                    				current = grammarAccess.getOPT_LISTINGAccess().getVALUE_ONLYEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getOPT_LISTINGAccess().getVALUE_ONLYEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOPT_LISTING"


    // $ANTLR start "ruleFMFormat"
    // InternalFml.g:10174:1: ruleFMFormat returns [Enumerator current=null] : ( (enumLiteral_0= 'DIMACS' ) | (enumLiteral_1= 'fmlconstraints' ) | (enumLiteral_2= 'fmlbdd' ) | (enumLiteral_3= 'featureide' ) | (enumLiteral_4= 'fmcalc' ) | (enumLiteral_5= 'fml' ) | (enumLiteral_6= 'SPLOT' ) | (enumLiteral_7= 'TVL' ) | (enumLiteral_8= 'fd' ) | (enumLiteral_9= 'xmi' ) | (enumLiteral_10= 'S2T2' ) | (enumLiteral_11= 'bdd' ) ) ;
    public final Enumerator ruleFMFormat() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;


        	enterRule();

        try {
            // InternalFml.g:10180:2: ( ( (enumLiteral_0= 'DIMACS' ) | (enumLiteral_1= 'fmlconstraints' ) | (enumLiteral_2= 'fmlbdd' ) | (enumLiteral_3= 'featureide' ) | (enumLiteral_4= 'fmcalc' ) | (enumLiteral_5= 'fml' ) | (enumLiteral_6= 'SPLOT' ) | (enumLiteral_7= 'TVL' ) | (enumLiteral_8= 'fd' ) | (enumLiteral_9= 'xmi' ) | (enumLiteral_10= 'S2T2' ) | (enumLiteral_11= 'bdd' ) ) )
            // InternalFml.g:10181:2: ( (enumLiteral_0= 'DIMACS' ) | (enumLiteral_1= 'fmlconstraints' ) | (enumLiteral_2= 'fmlbdd' ) | (enumLiteral_3= 'featureide' ) | (enumLiteral_4= 'fmcalc' ) | (enumLiteral_5= 'fml' ) | (enumLiteral_6= 'SPLOT' ) | (enumLiteral_7= 'TVL' ) | (enumLiteral_8= 'fd' ) | (enumLiteral_9= 'xmi' ) | (enumLiteral_10= 'S2T2' ) | (enumLiteral_11= 'bdd' ) )
            {
            // InternalFml.g:10181:2: ( (enumLiteral_0= 'DIMACS' ) | (enumLiteral_1= 'fmlconstraints' ) | (enumLiteral_2= 'fmlbdd' ) | (enumLiteral_3= 'featureide' ) | (enumLiteral_4= 'fmcalc' ) | (enumLiteral_5= 'fml' ) | (enumLiteral_6= 'SPLOT' ) | (enumLiteral_7= 'TVL' ) | (enumLiteral_8= 'fd' ) | (enumLiteral_9= 'xmi' ) | (enumLiteral_10= 'S2T2' ) | (enumLiteral_11= 'bdd' ) )
            int alt132=12;
            switch ( input.LA(1) ) {
            case 217:
                {
                alt132=1;
                }
                break;
            case 218:
                {
                alt132=2;
                }
                break;
            case 219:
                {
                alt132=3;
                }
                break;
            case 220:
                {
                alt132=4;
                }
                break;
            case 221:
                {
                alt132=5;
                }
                break;
            case 222:
                {
                alt132=6;
                }
                break;
            case 223:
                {
                alt132=7;
                }
                break;
            case 224:
                {
                alt132=8;
                }
                break;
            case 225:
                {
                alt132=9;
                }
                break;
            case 226:
                {
                alt132=10;
                }
                break;
            case 227:
                {
                alt132=11;
                }
                break;
            case 228:
                {
                alt132=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);

                throw nvae;
            }

            switch (alt132) {
                case 1 :
                    // InternalFml.g:10182:3: (enumLiteral_0= 'DIMACS' )
                    {
                    // InternalFml.g:10182:3: (enumLiteral_0= 'DIMACS' )
                    // InternalFml.g:10183:4: enumLiteral_0= 'DIMACS'
                    {
                    enumLiteral_0=(Token)match(input,217,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getDIMACSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFMFormatAccess().getDIMACSEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10190:3: (enumLiteral_1= 'fmlconstraints' )
                    {
                    // InternalFml.g:10190:3: (enumLiteral_1= 'fmlconstraints' )
                    // InternalFml.g:10191:4: enumLiteral_1= 'fmlconstraints'
                    {
                    enumLiteral_1=(Token)match(input,218,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFMLCONSTRAINTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFMFormatAccess().getFMLCONSTRAINTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFml.g:10198:3: (enumLiteral_2= 'fmlbdd' )
                    {
                    // InternalFml.g:10198:3: (enumLiteral_2= 'fmlbdd' )
                    // InternalFml.g:10199:4: enumLiteral_2= 'fmlbdd'
                    {
                    enumLiteral_2=(Token)match(input,219,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFMLBDDEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getFMFormatAccess().getFMLBDDEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalFml.g:10206:3: (enumLiteral_3= 'featureide' )
                    {
                    // InternalFml.g:10206:3: (enumLiteral_3= 'featureide' )
                    // InternalFml.g:10207:4: enumLiteral_3= 'featureide'
                    {
                    enumLiteral_3=(Token)match(input,220,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFIDEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getFMFormatAccess().getFIDEEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalFml.g:10214:3: (enumLiteral_4= 'fmcalc' )
                    {
                    // InternalFml.g:10214:3: (enumLiteral_4= 'fmcalc' )
                    // InternalFml.g:10215:4: enumLiteral_4= 'fmcalc'
                    {
                    enumLiteral_4=(Token)match(input,221,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFCALCEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getFMFormatAccess().getFCALCEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalFml.g:10222:3: (enumLiteral_5= 'fml' )
                    {
                    // InternalFml.g:10222:3: (enumLiteral_5= 'fml' )
                    // InternalFml.g:10223:4: enumLiteral_5= 'fml'
                    {
                    enumLiteral_5=(Token)match(input,222,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFFMLEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getFMFormatAccess().getFFMLEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalFml.g:10230:3: (enumLiteral_6= 'SPLOT' )
                    {
                    // InternalFml.g:10230:3: (enumLiteral_6= 'SPLOT' )
                    // InternalFml.g:10231:4: enumLiteral_6= 'SPLOT'
                    {
                    enumLiteral_6=(Token)match(input,223,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFSPLOTEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getFMFormatAccess().getFSPLOTEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalFml.g:10238:3: (enumLiteral_7= 'TVL' )
                    {
                    // InternalFml.g:10238:3: (enumLiteral_7= 'TVL' )
                    // InternalFml.g:10239:4: enumLiteral_7= 'TVL'
                    {
                    enumLiteral_7=(Token)match(input,224,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFTVLEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getFMFormatAccess().getFTVLEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalFml.g:10246:3: (enumLiteral_8= 'fd' )
                    {
                    // InternalFml.g:10246:3: (enumLiteral_8= 'fd' )
                    // InternalFml.g:10247:4: enumLiteral_8= 'fd'
                    {
                    enumLiteral_8=(Token)match(input,225,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFTRISKELLEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getFMFormatAccess().getFTRISKELLEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalFml.g:10254:3: (enumLiteral_9= 'xmi' )
                    {
                    // InternalFml.g:10254:3: (enumLiteral_9= 'xmi' )
                    // InternalFml.g:10255:4: enumLiteral_9= 'xmi'
                    {
                    enumLiteral_9=(Token)match(input,226,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFFML2EnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getFMFormatAccess().getFFML2EnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalFml.g:10262:3: (enumLiteral_10= 'S2T2' )
                    {
                    // InternalFml.g:10262:3: (enumLiteral_10= 'S2T2' )
                    // InternalFml.g:10263:4: enumLiteral_10= 'S2T2'
                    {
                    enumLiteral_10=(Token)match(input,227,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getS2T2EnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getFMFormatAccess().getS2T2EnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalFml.g:10270:3: (enumLiteral_11= 'bdd' )
                    {
                    // InternalFml.g:10270:3: (enumLiteral_11= 'bdd' )
                    // InternalFml.g:10271:4: enumLiteral_11= 'bdd'
                    {
                    enumLiteral_11=(Token)match(input,228,FOLLOW_2); 

                    				current = grammarAccess.getFMFormatAccess().getFMLBDD_ONLYEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_11, grammarAccess.getFMFormatAccess().getFMLBDD_ONLYEnumLiteralDeclaration_11());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFMFormat"


    // $ANTLR start "ruleBOOL_Operator"
    // InternalFml.g:10281:1: ruleBOOL_Operator returns [Enumerator current=null] : ( (enumLiteral_0= '||' ) | (enumLiteral_1= '&&' ) ) ;
    public final Enumerator ruleBOOL_Operator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalFml.g:10287:2: ( ( (enumLiteral_0= '||' ) | (enumLiteral_1= '&&' ) ) )
            // InternalFml.g:10288:2: ( (enumLiteral_0= '||' ) | (enumLiteral_1= '&&' ) )
            {
            // InternalFml.g:10288:2: ( (enumLiteral_0= '||' ) | (enumLiteral_1= '&&' ) )
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==229) ) {
                alt133=1;
            }
            else if ( (LA133_0==230) ) {
                alt133=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }
            switch (alt133) {
                case 1 :
                    // InternalFml.g:10289:3: (enumLiteral_0= '||' )
                    {
                    // InternalFml.g:10289:3: (enumLiteral_0= '||' )
                    // InternalFml.g:10290:4: enumLiteral_0= '||'
                    {
                    enumLiteral_0=(Token)match(input,229,FOLLOW_2); 

                    				current = grammarAccess.getBOOL_OperatorAccess().getBOOL_OREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBOOL_OperatorAccess().getBOOL_OREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFml.g:10297:3: (enumLiteral_1= '&&' )
                    {
                    // InternalFml.g:10297:3: (enumLiteral_1= '&&' )
                    // InternalFml.g:10298:4: enumLiteral_1= '&&'
                    {
                    enumLiteral_1=(Token)match(input,230,FOLLOW_2); 

                    				current = grammarAccess.getBOOL_OperatorAccess().getBOOL_ANDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBOOL_OperatorAccess().getBOOL_ANDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBOOL_Operator"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA110 dfa110 = new DFA110(this);
    static final String dfa_1s = "\17\uffff";
    static final String dfa_2s = "\1\uffff\2\3\3\uffff\1\3\1\uffff\4\3\2\uffff\1\3";
    static final String dfa_3s = "\3\4\1\uffff\1\17\2\4\1\uffff\4\4\1\17\1\6\1\4";
    static final String dfa_4s = "\1\u00bd\2\u00e6\1\uffff\1\u00a8\1\u00bd\1\u00e6\1\uffff\4\u00e6\1\17\1\6\1\u00e6";
    static final String dfa_5s = "\3\uffff\1\2\3\uffff\1\1\7\uffff";
    static final String dfa_6s = "\17\uffff}>";
    static final String[] dfa_7s = {
            "\1\3\7\uffff\1\3\1\uffff\3\3\2\uffff\1\1\13\uffff\5\3\1\uffff\1\3\3\uffff\1\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\3\3\2\uffff\54\3\1\uffff\3\3\1\uffff\1\2\1\uffff\24\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\5\uffff\1\6\4\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "",
            "\1\13\3\uffff\1\10\5\uffff\1\12\u008e\uffff\1\11",
            "\1\3\1\14\6\uffff\1\3\1\uffff\3\3\2\uffff\1\3\13\uffff\5\3\1\uffff\1\3\3\uffff\1\3\13\uffff\1\3\1\uffff\1\3\2\uffff\37\3\1\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\3\3\2\uffff\54\3\1\uffff\3\3\1\uffff\1\3\1\uffff\24\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\uffff\24\3\15\uffff\10\3\22\uffff\2\3",
            "",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "\1\5\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\4\24\3\15\uffff\10\3\22\uffff\2\3",
            "\1\15",
            "\1\16",
            "\1\3\1\uffff\7\3\1\uffff\3\3\2\uffff\1\3\12\uffff\1\7\5\3\1\uffff\1\3\1\uffff\3\3\13\uffff\1\3\1\uffff\1\3\2\uffff\36\3\2\uffff\1\3\1\uffff\2\3\1\uffff\1\3\1\uffff\1\3\10\uffff\2\3\2\uffff\3\3\1\uffff\61\3\1\uffff\3\3\1\uffff\1\3\1\uffff\24\3\15\uffff\10\3\22\uffff\2\3"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "173:2: ( ( ( (lv_var_0_0= ruleFML_IDENTIFIER ) ) (this_LEFT_HOOK_1= RULE_LEFT_HOOK this_META_ATTRIBUTE_SYMBOL_2= RULE_META_ATTRIBUTE_SYMBOL ( (lv_metaID_3_0= ruleStringExpr ) ) this_RIGHT_HOOK_4= RULE_RIGHT_HOOK )? otherlv_5= '=' ( (lv_cmd_6_0= ruleComplexCommand ) ) ) | this_ComplexCommand_7= ruleComplexCommand )";
        }
    }
    static final String dfa_8s = "\15\uffff";
    static final String dfa_9s = "\1\2\14\uffff";
    static final String dfa_10s = "\2\4\1\uffff\2\4\1\uffff\1\4\1\17\1\uffff\4\4";
    static final String dfa_11s = "\1\u00e6\1\u00bd\1\uffff\2\u00e6\1\uffff\1\u00e6\1\u00a8\1\uffff\4\u00e6";
    static final String dfa_12s = "\2\uffff\1\2\2\uffff\1\1\2\uffff\1\1\4\uffff";
    static final String dfa_13s = "\15\uffff}>";
    static final String[] dfa_14s = {
            "\1\2\1\uffff\12\2\1\1\3\2\13\uffff\5\2\1\uffff\1\2\1\uffff\3\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\4\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\61\2\1\uffff\3\2\1\uffff\1\2\1\uffff\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\2\7\uffff\1\2\1\uffff\3\2\1\5\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\uffff\24\2",
            "",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\5\uffff\1\6\5\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2",
            "",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\uffff\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\14\3\uffff\1\11\5\uffff\1\13\u008e\uffff\1\12",
            "",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2",
            "\1\2\2\uffff\6\2\1\uffff\3\2\1\10\1\uffff\1\3\13\uffff\5\2\1\uffff\1\2\3\uffff\1\2\13\uffff\1\2\1\uffff\1\2\2\uffff\36\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1\uffff\1\2\10\uffff\2\2\2\uffff\3\2\1\uffff\3\2\2\uffff\54\2\1\uffff\3\2\1\uffff\1\4\1\7\24\2\15\uffff\10\2\22\uffff\2\2"
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "4473:3: (this_LEFT_BRACKET_2= RULE_LEFT_BRACKET ( (lv_params_3_0= ruleFML_IDENTIFIER ) )* this_RIGHT_BRACKET_4= RULE_RIGHT_BRACKET )?";
        }
    }
    static final String dfa_15s = "\13\uffff\1\15\3\uffff";
    static final String dfa_16s = "\1\4\2\uffff\1\17\3\24\1\17\3\15\1\4\3\uffff";
    static final String dfa_17s = "\1\u00a8\2\uffff\1\u00a8\3\24\1\u00a8\3\u00a7\1\u00a8\3\uffff";
    static final String dfa_18s = "\1\uffff\1\1\1\2\11\uffff\1\5\1\3\1\4";
    static final String[] dfa_19s = {
            "\1\2\7\uffff\1\3\2\uffff\1\1\3\uffff\1\1\u0094\uffff\1\1",
            "",
            "",
            "\1\6\3\uffff\1\4\u0094\uffff\1\5",
            "\1\7",
            "\1\7",
            "\1\7",
            "\1\12\3\uffff\1\10\u0094\uffff\1\11",
            "\1\13\6\uffff\1\7\u0092\uffff\1\14",
            "\1\13\6\uffff\1\7\u0092\uffff\1\14",
            "\1\13\6\uffff\1\7\u0092\uffff\1\14",
            "\1\15\2\uffff\1\16\4\uffff\1\15\2\uffff\1\15\3\uffff\1\15\20\uffff\1\15\u0083\uffff\1\15",
            "",
            "",
            ""
    };
    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA110 extends DFA {

        public DFA110(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 110;
            this.eot = dfa_1;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_6;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "9176:2: (this_Mandatory_0= ruleMandatory | this_Optional_1= ruleOptional | this_Xorgroup_2= ruleXorgroup | this_Orgroup_3= ruleOrgroup | this_Mutexgroup_4= ruleMutexgroup )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0xFCA0022F8009D012L,0xFFFDCC02B5FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0xFCA0022F8009D012L,0xFFFDCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000002L,0x0060000000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0xFCA0022F8009D010L,0xFF9DCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000F82L,0x0000000000000000L,0x0000000000000000L,0x000000600007F800L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000084000L,0x00000000107C0001L,0x0000010000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0xFCA0022F800BD010L,0xFF9DCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000301089000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000081000L,0x0005CC02A0010000L,0x0000016001060001L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000030108B000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0xFCA003AF8009D010L,0xFF9DCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0xFCA0032F8009D010L,0xFF9DCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000080000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xFCA0022F0009D010L,0xFF9DCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000081000L,0x0085CC02A0010000L,0x0000016001060001L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000080000L,0x000000000003FC00L,0x0000010000060000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000088000L,0x00000000001FFC00L,0x0000010000260000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x001FF00000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000088000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000010002L,0x0000000008000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x00000000000A0000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L,0xC000000000000000L,0x000000000000003FL});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000091000L,0x0005CC02A0010000L,0x0000016001060001L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x00000000000A1000L,0x0005CC02A0010000L,0x0000016001060001L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000140000000L,0xC000000000000000L,0x000000000000003FL});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x00000000000001C0L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000081000L,0x0005CC06A0010000L,0x0000016001060001L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0040000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000040000L,0x0000001000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000080000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000080002L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000088002L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000000L,0x000001C000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000002L,0x000001C000000000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x000000000008A000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000090000L,0x0000100000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000090000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000002L,0x0000200000000000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000080000L,0x0000000000000000L,0x00007D0000060000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000040000088000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000380000L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0xFCA0022F8009D010L,0xFFFDCC02B5FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0xFCA0022F8009D050L,0xFFFDCC02B4FFFFFFL,0x3FFFFD77FFFFFFFFL});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000040L,0x0060000000000000L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000001C00000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000001FFE000000L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000400080000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000089010L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000089012L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000102000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000100000L,0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000002088000L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000000002000000L});

}