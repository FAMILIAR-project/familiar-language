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
import org.xtext.example.mydsl.services.FmleroGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFmleroParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'FeatureModel'", "'{'", "'name'", "'features'", "','", "'}'", "'primitives'", "'Feature'", "'groupHasParent'", "'('", "')'", "'groupHasChild'", "'featureHasParent'", "'featureHasSubfeature'", "'selectedFeature'", "'eliminatedFeature'", "'undirectedRelationships'", "'incomingDirectedRelationships'", "'outgoingDirectedRelationships'", "'featureIsRoot'", "'GroupHasParent'", "'configurationSource'", "'explanations'", "'parent'", "'group'", "'GroupHasChild'", "'child'", "'FeatureHasSubfeature'", "'subfeature'", "'SelectedFeature'", "'feature'", "'EliminatedFeature'", "'FeatureIsRoot'", "'GroupHasMax'", "'max'", "'GroupHasMin'", "'min'", "'AlternativeGroup'", "'groupHasMax'", "'groupHasMin'", "'OrGroup'", "'-'", "'FeatureHasOptionalSubfeature'", "'FeatureHasMandatorySubfeature'", "'MutualExclusive'", "'relatedFeatures'", "'CustomUndirectedRelationship'", "'stereotype'", "'Requires'", "'sources'", "'targets'", "'TemporalOrderingSequential'", "'CustomDirectedRelationship'", "'AutoComplete'", "'MODEL'", "'MODELCONSEQUENCE'", "'USER'", "'USERCONSEQUENCE'"
    };
    public static final int T__68=68;
    public static final int RULE_ID=5;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__29=29;
    public static final int T__65=65;
    public static final int T__28=28;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__63=63;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__16=16;
    public static final int T__52=52;
    public static final int T__15=15;
    public static final int T__53=53;
    public static final int T__18=18;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__59=59;
    public static final int RULE_INT=6;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_STRING=4;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=9;

    // delegates
    // delegators


        public InternalFmleroParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalFmleroParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalFmleroParser.tokenNames; }
    public String getGrammarFileName() { return "../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g"; }



     	private FmleroGrammarAccess grammarAccess;
     	
        public InternalFmleroParser(TokenStream input, FmleroGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "FeatureModel";	
       	}
       	
       	@Override
       	protected FmleroGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleFeatureModel"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:68:1: entryRuleFeatureModel returns [EObject current=null] : iv_ruleFeatureModel= ruleFeatureModel EOF ;
    public final EObject entryRuleFeatureModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureModel = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:69:2: (iv_ruleFeatureModel= ruleFeatureModel EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:70:2: iv_ruleFeatureModel= ruleFeatureModel EOF
            {
             newCompositeNode(grammarAccess.getFeatureModelRule()); 
            pushFollow(FOLLOW_ruleFeatureModel_in_entryRuleFeatureModel75);
            iv_ruleFeatureModel=ruleFeatureModel();

            state._fsp--;

             current =iv_ruleFeatureModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureModel85); 

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
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:77:1: ruleFeatureModel returns [EObject current=null] : (otherlv_0= 'FeatureModel' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )? (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) ;
    public final EObject ruleFeatureModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_features_7_0 = null;

        EObject lv_features_9_0 = null;

        EObject lv_primitives_13_0 = null;

        EObject lv_primitives_15_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:80:28: ( (otherlv_0= 'FeatureModel' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )? (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:81:1: (otherlv_0= 'FeatureModel' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )? (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:81:1: (otherlv_0= 'FeatureModel' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )? (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:81:3: otherlv_0= 'FeatureModel' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )? (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )? otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleFeatureModel122); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureModelAccess().getFeatureModelKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:85:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:86:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:86:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:87:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureModelAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureModel143);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeatureModel155); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureModelAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeatureModel167); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureModelAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:111:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:112:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:112:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:113:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureModelAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureModel188);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:129:2: (otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:129:4: otherlv_5= 'features' otherlv_6= '{' ( (lv_features_7_0= ruleFeature ) ) (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )* otherlv_10= '}'
                    {
                    otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleFeatureModel201); 

                        	newLeafNode(otherlv_5, grammarAccess.getFeatureModelAccess().getFeaturesKeyword_5_0());
                        
                    otherlv_6=(Token)match(input,12,FOLLOW_12_in_ruleFeatureModel213); 

                        	newLeafNode(otherlv_6, grammarAccess.getFeatureModelAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:137:1: ( (lv_features_7_0= ruleFeature ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:138:1: (lv_features_7_0= ruleFeature )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:138:1: (lv_features_7_0= ruleFeature )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:139:3: lv_features_7_0= ruleFeature
                    {
                     
                    	        newCompositeNode(grammarAccess.getFeatureModelAccess().getFeaturesFeatureParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFeature_in_ruleFeatureModel234);
                    lv_features_7_0=ruleFeature();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                    	        }
                           		add(
                           			current, 
                           			"features",
                            		lv_features_7_0, 
                            		"Feature");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:155:2: (otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==15) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:155:4: otherlv_8= ',' ( (lv_features_9_0= ruleFeature ) )
                    	    {
                    	    otherlv_8=(Token)match(input,15,FOLLOW_15_in_ruleFeatureModel247); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getFeatureModelAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:159:1: ( (lv_features_9_0= ruleFeature ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:160:1: (lv_features_9_0= ruleFeature )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:160:1: (lv_features_9_0= ruleFeature )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:161:3: lv_features_9_0= ruleFeature
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureModelAccess().getFeaturesFeatureParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFeature_in_ruleFeatureModel268);
                    	    lv_features_9_0=ruleFeature();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"features",
                    	            		lv_features_9_0, 
                    	            		"Feature");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,16,FOLLOW_16_in_ruleFeatureModel282); 

                        	newLeafNode(otherlv_10, grammarAccess.getFeatureModelAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:181:3: (otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:181:5: otherlv_11= 'primitives' otherlv_12= '{' ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) ) (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,17,FOLLOW_17_in_ruleFeatureModel297); 

                        	newLeafNode(otherlv_11, grammarAccess.getFeatureModelAccess().getPrimitivesKeyword_6_0());
                        
                    otherlv_12=(Token)match(input,12,FOLLOW_12_in_ruleFeatureModel309); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureModelAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:189:1: ( (lv_primitives_13_0= ruleFeatureModelPrimitive ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:190:1: (lv_primitives_13_0= ruleFeatureModelPrimitive )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:190:1: (lv_primitives_13_0= ruleFeatureModelPrimitive )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:191:3: lv_primitives_13_0= ruleFeatureModelPrimitive
                    {
                     
                    	        newCompositeNode(grammarAccess.getFeatureModelAccess().getPrimitivesFeatureModelPrimitiveParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFeatureModelPrimitive_in_ruleFeatureModel330);
                    lv_primitives_13_0=ruleFeatureModelPrimitive();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                    	        }
                           		add(
                           			current, 
                           			"primitives",
                            		lv_primitives_13_0, 
                            		"FeatureModelPrimitive");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:207:2: (otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==15) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:207:4: otherlv_14= ',' ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FOLLOW_15_in_ruleFeatureModel343); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getFeatureModelAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:211:1: ( (lv_primitives_15_0= ruleFeatureModelPrimitive ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:212:1: (lv_primitives_15_0= ruleFeatureModelPrimitive )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:212:1: (lv_primitives_15_0= ruleFeatureModelPrimitive )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:213:3: lv_primitives_15_0= ruleFeatureModelPrimitive
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureModelAccess().getPrimitivesFeatureModelPrimitiveParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleFeatureModelPrimitive_in_ruleFeatureModel364);
                    	    lv_primitives_15_0=ruleFeatureModelPrimitive();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getFeatureModelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"primitives",
                    	            		lv_primitives_15_0, 
                    	            		"FeatureModelPrimitive");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,16,FOLLOW_16_in_ruleFeatureModel378); 

                        	newLeafNode(otherlv_16, grammarAccess.getFeatureModelAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleFeatureModel392); 

                	newLeafNode(otherlv_17, grammarAccess.getFeatureModelAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleFeatureModelPrimitive"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:245:1: entryRuleFeatureModelPrimitive returns [EObject current=null] : iv_ruleFeatureModelPrimitive= ruleFeatureModelPrimitive EOF ;
    public final EObject entryRuleFeatureModelPrimitive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureModelPrimitive = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:246:2: (iv_ruleFeatureModelPrimitive= ruleFeatureModelPrimitive EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:247:2: iv_ruleFeatureModelPrimitive= ruleFeatureModelPrimitive EOF
            {
             newCompositeNode(grammarAccess.getFeatureModelPrimitiveRule()); 
            pushFollow(FOLLOW_ruleFeatureModelPrimitive_in_entryRuleFeatureModelPrimitive428);
            iv_ruleFeatureModelPrimitive=ruleFeatureModelPrimitive();

            state._fsp--;

             current =iv_ruleFeatureModelPrimitive; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureModelPrimitive438); 

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
    // $ANTLR end "entryRuleFeatureModelPrimitive"


    // $ANTLR start "ruleFeatureModelPrimitive"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:254:1: ruleFeatureModelPrimitive returns [EObject current=null] : (this_GroupHasParent_0= ruleGroupHasParent | this_GroupHasChild_1= ruleGroupHasChild | this_GroupHasMax_2= ruleGroupHasMax | this_GroupHasMin_3= ruleGroupHasMin | this_FeatureHasSubfeature_Impl_4= ruleFeatureHasSubfeature_Impl | this_SelectedFeature_5= ruleSelectedFeature | this_EliminatedFeature_6= ruleEliminatedFeature | this_FeatureIsRoot_7= ruleFeatureIsRoot | this_AlternativeGroup_8= ruleAlternativeGroup | this_OrGroup_9= ruleOrGroup | this_FeatureHasOptionalSubfeature_10= ruleFeatureHasOptionalSubfeature | this_FeatureHasMandatorySubfeature_11= ruleFeatureHasMandatorySubfeature | this_MutualExclusive_12= ruleMutualExclusive | this_Requires_13= ruleRequires | this_AutoComplete_14= ruleAutoComplete | this_TemporalOrderingSequential_15= ruleTemporalOrderingSequential | this_CustomDirectedRelationship_16= ruleCustomDirectedRelationship | this_CustomUndirectedRelationship_17= ruleCustomUndirectedRelationship ) ;
    public final EObject ruleFeatureModelPrimitive() throws RecognitionException {
        EObject current = null;

        EObject this_GroupHasParent_0 = null;

        EObject this_GroupHasChild_1 = null;

        EObject this_GroupHasMax_2 = null;

        EObject this_GroupHasMin_3 = null;

        EObject this_FeatureHasSubfeature_Impl_4 = null;

        EObject this_SelectedFeature_5 = null;

        EObject this_EliminatedFeature_6 = null;

        EObject this_FeatureIsRoot_7 = null;

        EObject this_AlternativeGroup_8 = null;

        EObject this_OrGroup_9 = null;

        EObject this_FeatureHasOptionalSubfeature_10 = null;

        EObject this_FeatureHasMandatorySubfeature_11 = null;

        EObject this_MutualExclusive_12 = null;

        EObject this_Requires_13 = null;

        EObject this_AutoComplete_14 = null;

        EObject this_TemporalOrderingSequential_15 = null;

        EObject this_CustomDirectedRelationship_16 = null;

        EObject this_CustomUndirectedRelationship_17 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:257:28: ( (this_GroupHasParent_0= ruleGroupHasParent | this_GroupHasChild_1= ruleGroupHasChild | this_GroupHasMax_2= ruleGroupHasMax | this_GroupHasMin_3= ruleGroupHasMin | this_FeatureHasSubfeature_Impl_4= ruleFeatureHasSubfeature_Impl | this_SelectedFeature_5= ruleSelectedFeature | this_EliminatedFeature_6= ruleEliminatedFeature | this_FeatureIsRoot_7= ruleFeatureIsRoot | this_AlternativeGroup_8= ruleAlternativeGroup | this_OrGroup_9= ruleOrGroup | this_FeatureHasOptionalSubfeature_10= ruleFeatureHasOptionalSubfeature | this_FeatureHasMandatorySubfeature_11= ruleFeatureHasMandatorySubfeature | this_MutualExclusive_12= ruleMutualExclusive | this_Requires_13= ruleRequires | this_AutoComplete_14= ruleAutoComplete | this_TemporalOrderingSequential_15= ruleTemporalOrderingSequential | this_CustomDirectedRelationship_16= ruleCustomDirectedRelationship | this_CustomUndirectedRelationship_17= ruleCustomUndirectedRelationship ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:258:1: (this_GroupHasParent_0= ruleGroupHasParent | this_GroupHasChild_1= ruleGroupHasChild | this_GroupHasMax_2= ruleGroupHasMax | this_GroupHasMin_3= ruleGroupHasMin | this_FeatureHasSubfeature_Impl_4= ruleFeatureHasSubfeature_Impl | this_SelectedFeature_5= ruleSelectedFeature | this_EliminatedFeature_6= ruleEliminatedFeature | this_FeatureIsRoot_7= ruleFeatureIsRoot | this_AlternativeGroup_8= ruleAlternativeGroup | this_OrGroup_9= ruleOrGroup | this_FeatureHasOptionalSubfeature_10= ruleFeatureHasOptionalSubfeature | this_FeatureHasMandatorySubfeature_11= ruleFeatureHasMandatorySubfeature | this_MutualExclusive_12= ruleMutualExclusive | this_Requires_13= ruleRequires | this_AutoComplete_14= ruleAutoComplete | this_TemporalOrderingSequential_15= ruleTemporalOrderingSequential | this_CustomDirectedRelationship_16= ruleCustomDirectedRelationship | this_CustomUndirectedRelationship_17= ruleCustomUndirectedRelationship )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:258:1: (this_GroupHasParent_0= ruleGroupHasParent | this_GroupHasChild_1= ruleGroupHasChild | this_GroupHasMax_2= ruleGroupHasMax | this_GroupHasMin_3= ruleGroupHasMin | this_FeatureHasSubfeature_Impl_4= ruleFeatureHasSubfeature_Impl | this_SelectedFeature_5= ruleSelectedFeature | this_EliminatedFeature_6= ruleEliminatedFeature | this_FeatureIsRoot_7= ruleFeatureIsRoot | this_AlternativeGroup_8= ruleAlternativeGroup | this_OrGroup_9= ruleOrGroup | this_FeatureHasOptionalSubfeature_10= ruleFeatureHasOptionalSubfeature | this_FeatureHasMandatorySubfeature_11= ruleFeatureHasMandatorySubfeature | this_MutualExclusive_12= ruleMutualExclusive | this_Requires_13= ruleRequires | this_AutoComplete_14= ruleAutoComplete | this_TemporalOrderingSequential_15= ruleTemporalOrderingSequential | this_CustomDirectedRelationship_16= ruleCustomDirectedRelationship | this_CustomUndirectedRelationship_17= ruleCustomUndirectedRelationship )
            int alt5=18;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt5=1;
                }
                break;
            case 36:
                {
                alt5=2;
                }
                break;
            case 44:
                {
                alt5=3;
                }
                break;
            case 46:
                {
                alt5=4;
                }
                break;
            case 38:
                {
                alt5=5;
                }
                break;
            case 40:
                {
                alt5=6;
                }
                break;
            case 42:
                {
                alt5=7;
                }
                break;
            case 43:
                {
                alt5=8;
                }
                break;
            case 48:
                {
                alt5=9;
                }
                break;
            case 51:
                {
                alt5=10;
                }
                break;
            case 53:
                {
                alt5=11;
                }
                break;
            case 54:
                {
                alt5=12;
                }
                break;
            case 55:
                {
                alt5=13;
                }
                break;
            case 59:
                {
                alt5=14;
                }
                break;
            case 64:
                {
                alt5=15;
                }
                break;
            case 62:
                {
                alt5=16;
                }
                break;
            case 63:
                {
                alt5=17;
                }
                break;
            case 57:
                {
                alt5=18;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:259:5: this_GroupHasParent_0= ruleGroupHasParent
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getGroupHasParentParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleGroupHasParent_in_ruleFeatureModelPrimitive485);
                    this_GroupHasParent_0=ruleGroupHasParent();

                    state._fsp--;

                     
                            current = this_GroupHasParent_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:269:5: this_GroupHasChild_1= ruleGroupHasChild
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getGroupHasChildParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleGroupHasChild_in_ruleFeatureModelPrimitive512);
                    this_GroupHasChild_1=ruleGroupHasChild();

                    state._fsp--;

                     
                            current = this_GroupHasChild_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:279:5: this_GroupHasMax_2= ruleGroupHasMax
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getGroupHasMaxParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleGroupHasMax_in_ruleFeatureModelPrimitive539);
                    this_GroupHasMax_2=ruleGroupHasMax();

                    state._fsp--;

                     
                            current = this_GroupHasMax_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:289:5: this_GroupHasMin_3= ruleGroupHasMin
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getGroupHasMinParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleGroupHasMin_in_ruleFeatureModelPrimitive566);
                    this_GroupHasMin_3=ruleGroupHasMin();

                    state._fsp--;

                     
                            current = this_GroupHasMin_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:299:5: this_FeatureHasSubfeature_Impl_4= ruleFeatureHasSubfeature_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getFeatureHasSubfeature_ImplParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleFeatureHasSubfeature_Impl_in_ruleFeatureModelPrimitive593);
                    this_FeatureHasSubfeature_Impl_4=ruleFeatureHasSubfeature_Impl();

                    state._fsp--;

                     
                            current = this_FeatureHasSubfeature_Impl_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:309:5: this_SelectedFeature_5= ruleSelectedFeature
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getSelectedFeatureParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleSelectedFeature_in_ruleFeatureModelPrimitive620);
                    this_SelectedFeature_5=ruleSelectedFeature();

                    state._fsp--;

                     
                            current = this_SelectedFeature_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:319:5: this_EliminatedFeature_6= ruleEliminatedFeature
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getEliminatedFeatureParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleEliminatedFeature_in_ruleFeatureModelPrimitive647);
                    this_EliminatedFeature_6=ruleEliminatedFeature();

                    state._fsp--;

                     
                            current = this_EliminatedFeature_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:329:5: this_FeatureIsRoot_7= ruleFeatureIsRoot
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getFeatureIsRootParserRuleCall_7()); 
                        
                    pushFollow(FOLLOW_ruleFeatureIsRoot_in_ruleFeatureModelPrimitive674);
                    this_FeatureIsRoot_7=ruleFeatureIsRoot();

                    state._fsp--;

                     
                            current = this_FeatureIsRoot_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:339:5: this_AlternativeGroup_8= ruleAlternativeGroup
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getAlternativeGroupParserRuleCall_8()); 
                        
                    pushFollow(FOLLOW_ruleAlternativeGroup_in_ruleFeatureModelPrimitive701);
                    this_AlternativeGroup_8=ruleAlternativeGroup();

                    state._fsp--;

                     
                            current = this_AlternativeGroup_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:349:5: this_OrGroup_9= ruleOrGroup
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getOrGroupParserRuleCall_9()); 
                        
                    pushFollow(FOLLOW_ruleOrGroup_in_ruleFeatureModelPrimitive728);
                    this_OrGroup_9=ruleOrGroup();

                    state._fsp--;

                     
                            current = this_OrGroup_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:359:5: this_FeatureHasOptionalSubfeature_10= ruleFeatureHasOptionalSubfeature
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getFeatureHasOptionalSubfeatureParserRuleCall_10()); 
                        
                    pushFollow(FOLLOW_ruleFeatureHasOptionalSubfeature_in_ruleFeatureModelPrimitive755);
                    this_FeatureHasOptionalSubfeature_10=ruleFeatureHasOptionalSubfeature();

                    state._fsp--;

                     
                            current = this_FeatureHasOptionalSubfeature_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:369:5: this_FeatureHasMandatorySubfeature_11= ruleFeatureHasMandatorySubfeature
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getFeatureHasMandatorySubfeatureParserRuleCall_11()); 
                        
                    pushFollow(FOLLOW_ruleFeatureHasMandatorySubfeature_in_ruleFeatureModelPrimitive782);
                    this_FeatureHasMandatorySubfeature_11=ruleFeatureHasMandatorySubfeature();

                    state._fsp--;

                     
                            current = this_FeatureHasMandatorySubfeature_11; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 13 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:379:5: this_MutualExclusive_12= ruleMutualExclusive
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getMutualExclusiveParserRuleCall_12()); 
                        
                    pushFollow(FOLLOW_ruleMutualExclusive_in_ruleFeatureModelPrimitive809);
                    this_MutualExclusive_12=ruleMutualExclusive();

                    state._fsp--;

                     
                            current = this_MutualExclusive_12; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 14 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:389:5: this_Requires_13= ruleRequires
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getRequiresParserRuleCall_13()); 
                        
                    pushFollow(FOLLOW_ruleRequires_in_ruleFeatureModelPrimitive836);
                    this_Requires_13=ruleRequires();

                    state._fsp--;

                     
                            current = this_Requires_13; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 15 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:399:5: this_AutoComplete_14= ruleAutoComplete
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getAutoCompleteParserRuleCall_14()); 
                        
                    pushFollow(FOLLOW_ruleAutoComplete_in_ruleFeatureModelPrimitive863);
                    this_AutoComplete_14=ruleAutoComplete();

                    state._fsp--;

                     
                            current = this_AutoComplete_14; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 16 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:409:5: this_TemporalOrderingSequential_15= ruleTemporalOrderingSequential
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getTemporalOrderingSequentialParserRuleCall_15()); 
                        
                    pushFollow(FOLLOW_ruleTemporalOrderingSequential_in_ruleFeatureModelPrimitive890);
                    this_TemporalOrderingSequential_15=ruleTemporalOrderingSequential();

                    state._fsp--;

                     
                            current = this_TemporalOrderingSequential_15; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 17 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:419:5: this_CustomDirectedRelationship_16= ruleCustomDirectedRelationship
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getCustomDirectedRelationshipParserRuleCall_16()); 
                        
                    pushFollow(FOLLOW_ruleCustomDirectedRelationship_in_ruleFeatureModelPrimitive917);
                    this_CustomDirectedRelationship_16=ruleCustomDirectedRelationship();

                    state._fsp--;

                     
                            current = this_CustomDirectedRelationship_16; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 18 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:429:5: this_CustomUndirectedRelationship_17= ruleCustomUndirectedRelationship
                    {
                     
                            newCompositeNode(grammarAccess.getFeatureModelPrimitiveAccess().getCustomUndirectedRelationshipParserRuleCall_17()); 
                        
                    pushFollow(FOLLOW_ruleCustomUndirectedRelationship_in_ruleFeatureModelPrimitive944);
                    this_CustomUndirectedRelationship_17=ruleCustomUndirectedRelationship();

                    state._fsp--;

                     
                            current = this_CustomUndirectedRelationship_17; 
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
    // $ANTLR end "ruleFeatureModelPrimitive"


    // $ANTLR start "entryRuleEString"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:453:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:454:2: (iv_ruleEString= ruleEString EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:455:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_ruleEString_in_entryRuleEString988);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEString999); 

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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:462:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:465:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:466:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:466:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_STRING) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:466:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEString1039); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:474:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEString1065); 

                    		current.merge(this_ID_1);
                        
                     
                        newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                        

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
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:489:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:490:2: (iv_ruleFeature= ruleFeature EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:491:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_ruleFeature_in_entryRuleFeature1110);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeature1120); 

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
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:498:1: ruleFeature returns [EObject current=null] : (otherlv_0= 'Feature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )? (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )? (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )? (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )? (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )? (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )? (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )? (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )? otherlv_57= '}' ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token otherlv_32=null;
        Token otherlv_34=null;
        Token otherlv_36=null;
        Token otherlv_37=null;
        Token otherlv_38=null;
        Token otherlv_40=null;
        Token otherlv_42=null;
        Token otherlv_43=null;
        Token otherlv_44=null;
        Token otherlv_46=null;
        Token otherlv_48=null;
        Token otherlv_49=null;
        Token otherlv_50=null;
        Token otherlv_52=null;
        Token otherlv_54=null;
        Token otherlv_55=null;
        Token otherlv_57=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:501:28: ( (otherlv_0= 'Feature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )? (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )? (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )? (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )? (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )? (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )? (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )? (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )? otherlv_57= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:502:1: (otherlv_0= 'Feature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )? (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )? (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )? (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )? (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )? (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )? (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )? (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )? otherlv_57= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:502:1: (otherlv_0= 'Feature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )? (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )? (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )? (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )? (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )? (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )? (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )? (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )? otherlv_57= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:502:3: otherlv_0= 'Feature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )? (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )? (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )? (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )? (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )? (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )? (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )? (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )? otherlv_57= '}'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleFeature1157); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureAccess().getFeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:506:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:507:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:507:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:508:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeature1178);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeature1190); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeature1202); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:532:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:533:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:533:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:534:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeature1223);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:550:2: (otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:550:4: otherlv_5= 'groupHasParent' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')'
                    {
                    otherlv_5=(Token)match(input,19,FOLLOW_19_in_ruleFeature1236); 

                        	newLeafNode(otherlv_5, grammarAccess.getFeatureAccess().getGroupHasParentKeyword_5_0());
                        
                    otherlv_6=(Token)match(input,20,FOLLOW_20_in_ruleFeature1248); 

                        	newLeafNode(otherlv_6, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:558:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:559:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:559:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:560:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getGroupHasParentGroupHasParentCrossReference_5_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1271);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:573:2: (otherlv_8= ',' ( ( ruleEString ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==15) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:573:4: otherlv_8= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_8=(Token)match(input,15,FOLLOW_15_in_ruleFeature1284); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getFeatureAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:577:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:578:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:578:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:579:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getGroupHasParentGroupHasParentCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1307);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,21,FOLLOW_21_in_ruleFeature1321); 

                        	newLeafNode(otherlv_10, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:596:3: (otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==22) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:596:5: otherlv_11= 'groupHasChild' otherlv_12= '(' ( ( ruleEString ) ) (otherlv_14= ',' ( ( ruleEString ) ) )* otherlv_16= ')'
                    {
                    otherlv_11=(Token)match(input,22,FOLLOW_22_in_ruleFeature1336); 

                        	newLeafNode(otherlv_11, grammarAccess.getFeatureAccess().getGroupHasChildKeyword_6_0());
                        
                    otherlv_12=(Token)match(input,20,FOLLOW_20_in_ruleFeature1348); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:604:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:605:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:605:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:606:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getGroupHasChildGroupHasChildCrossReference_6_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1371);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:619:2: (otherlv_14= ',' ( ( ruleEString ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==15) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:619:4: otherlv_14= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FOLLOW_15_in_ruleFeature1384); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getFeatureAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:623:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:624:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:624:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:625:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getGroupHasChildGroupHasChildCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1407);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,21,FOLLOW_21_in_ruleFeature1421); 

                        	newLeafNode(otherlv_16, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:642:3: (otherlv_17= 'featureHasParent' ( ( ruleEString ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:642:5: otherlv_17= 'featureHasParent' ( ( ruleEString ) )
                    {
                    otherlv_17=(Token)match(input,23,FOLLOW_23_in_ruleFeature1436); 

                        	newLeafNode(otherlv_17, grammarAccess.getFeatureAccess().getFeatureHasParentKeyword_7_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:646:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:647:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:647:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:648:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getFeatureHasParentFeatureHasSubfeatureCrossReference_7_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1459);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:661:4: (otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:661:6: otherlv_19= 'featureHasSubfeature' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')'
                    {
                    otherlv_19=(Token)match(input,24,FOLLOW_24_in_ruleFeature1474); 

                        	newLeafNode(otherlv_19, grammarAccess.getFeatureAccess().getFeatureHasSubfeatureKeyword_8_0());
                        
                    otherlv_20=(Token)match(input,20,FOLLOW_20_in_ruleFeature1486); 

                        	newLeafNode(otherlv_20, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_8_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:669:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:670:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:670:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:671:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getFeatureHasSubfeatureFeatureHasSubfeatureCrossReference_8_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1509);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:684:2: (otherlv_22= ',' ( ( ruleEString ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==15) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:684:4: otherlv_22= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_22=(Token)match(input,15,FOLLOW_15_in_ruleFeature1522); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getFeatureAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:688:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:689:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:689:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:690:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getFeatureHasSubfeatureFeatureHasSubfeatureCrossReference_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1545);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,21,FOLLOW_21_in_ruleFeature1559); 

                        	newLeafNode(otherlv_24, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_8_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:707:3: (otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==25) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:707:5: otherlv_25= 'selectedFeature' otherlv_26= '(' ( ( ruleEString ) ) (otherlv_28= ',' ( ( ruleEString ) ) )* otherlv_30= ')'
                    {
                    otherlv_25=(Token)match(input,25,FOLLOW_25_in_ruleFeature1574); 

                        	newLeafNode(otherlv_25, grammarAccess.getFeatureAccess().getSelectedFeatureKeyword_9_0());
                        
                    otherlv_26=(Token)match(input,20,FOLLOW_20_in_ruleFeature1586); 

                        	newLeafNode(otherlv_26, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:715:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:716:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:716:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:717:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getSelectedFeatureSelectedFeatureCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1609);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:730:2: (otherlv_28= ',' ( ( ruleEString ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==15) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:730:4: otherlv_28= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_28=(Token)match(input,15,FOLLOW_15_in_ruleFeature1622); 

                    	        	newLeafNode(otherlv_28, grammarAccess.getFeatureAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:734:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:735:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:735:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:736:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getSelectedFeatureSelectedFeatureCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1645);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    otherlv_30=(Token)match(input,21,FOLLOW_21_in_ruleFeature1659); 

                        	newLeafNode(otherlv_30, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:753:3: (otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:753:5: otherlv_31= 'eliminatedFeature' otherlv_32= '(' ( ( ruleEString ) ) (otherlv_34= ',' ( ( ruleEString ) ) )* otherlv_36= ')'
                    {
                    otherlv_31=(Token)match(input,26,FOLLOW_26_in_ruleFeature1674); 

                        	newLeafNode(otherlv_31, grammarAccess.getFeatureAccess().getEliminatedFeatureKeyword_10_0());
                        
                    otherlv_32=(Token)match(input,20,FOLLOW_20_in_ruleFeature1686); 

                        	newLeafNode(otherlv_32, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:761:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:762:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:762:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:763:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getEliminatedFeatureEliminatedFeatureCrossReference_10_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1709);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:776:2: (otherlv_34= ',' ( ( ruleEString ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==15) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:776:4: otherlv_34= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_34=(Token)match(input,15,FOLLOW_15_in_ruleFeature1722); 

                    	        	newLeafNode(otherlv_34, grammarAccess.getFeatureAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:780:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:781:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:781:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:782:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getEliminatedFeatureEliminatedFeatureCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1745);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    otherlv_36=(Token)match(input,21,FOLLOW_21_in_ruleFeature1759); 

                        	newLeafNode(otherlv_36, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:799:3: (otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==27) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:799:5: otherlv_37= 'undirectedRelationships' otherlv_38= '(' ( ( ruleEString ) ) (otherlv_40= ',' ( ( ruleEString ) ) )* otherlv_42= ')'
                    {
                    otherlv_37=(Token)match(input,27,FOLLOW_27_in_ruleFeature1774); 

                        	newLeafNode(otherlv_37, grammarAccess.getFeatureAccess().getUndirectedRelationshipsKeyword_11_0());
                        
                    otherlv_38=(Token)match(input,20,FOLLOW_20_in_ruleFeature1786); 

                        	newLeafNode(otherlv_38, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_11_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:807:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:808:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:808:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:809:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getUndirectedRelationshipsUndirectedRelationshipCrossReference_11_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1809);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:822:2: (otherlv_40= ',' ( ( ruleEString ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==15) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:822:4: otherlv_40= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_40=(Token)match(input,15,FOLLOW_15_in_ruleFeature1822); 

                    	        	newLeafNode(otherlv_40, grammarAccess.getFeatureAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:826:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:827:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:827:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:828:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getUndirectedRelationshipsUndirectedRelationshipCrossReference_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1845);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_42=(Token)match(input,21,FOLLOW_21_in_ruleFeature1859); 

                        	newLeafNode(otherlv_42, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_11_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:845:3: (otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:845:5: otherlv_43= 'incomingDirectedRelationships' otherlv_44= '(' ( ( ruleEString ) ) (otherlv_46= ',' ( ( ruleEString ) ) )* otherlv_48= ')'
                    {
                    otherlv_43=(Token)match(input,28,FOLLOW_28_in_ruleFeature1874); 

                        	newLeafNode(otherlv_43, grammarAccess.getFeatureAccess().getIncomingDirectedRelationshipsKeyword_12_0());
                        
                    otherlv_44=(Token)match(input,20,FOLLOW_20_in_ruleFeature1886); 

                        	newLeafNode(otherlv_44, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_12_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:853:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:854:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:854:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:855:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getIncomingDirectedRelationshipsDirectedRelationshipCrossReference_12_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature1909);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:868:2: (otherlv_46= ',' ( ( ruleEString ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==15) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:868:4: otherlv_46= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_46=(Token)match(input,15,FOLLOW_15_in_ruleFeature1922); 

                    	        	newLeafNode(otherlv_46, grammarAccess.getFeatureAccess().getCommaKeyword_12_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:872:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:873:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:873:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:874:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getIncomingDirectedRelationshipsDirectedRelationshipCrossReference_12_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature1945);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_48=(Token)match(input,21,FOLLOW_21_in_ruleFeature1959); 

                        	newLeafNode(otherlv_48, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_12_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:891:3: (otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==29) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:891:5: otherlv_49= 'outgoingDirectedRelationships' otherlv_50= '(' ( ( ruleEString ) ) (otherlv_52= ',' ( ( ruleEString ) ) )* otherlv_54= ')'
                    {
                    otherlv_49=(Token)match(input,29,FOLLOW_29_in_ruleFeature1974); 

                        	newLeafNode(otherlv_49, grammarAccess.getFeatureAccess().getOutgoingDirectedRelationshipsKeyword_13_0());
                        
                    otherlv_50=(Token)match(input,20,FOLLOW_20_in_ruleFeature1986); 

                        	newLeafNode(otherlv_50, grammarAccess.getFeatureAccess().getLeftParenthesisKeyword_13_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:899:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:900:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:900:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:901:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getOutgoingDirectedRelationshipsDirectedRelationshipCrossReference_13_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature2009);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:914:2: (otherlv_52= ',' ( ( ruleEString ) ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==15) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:914:4: otherlv_52= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_52=(Token)match(input,15,FOLLOW_15_in_ruleFeature2022); 

                    	        	newLeafNode(otherlv_52, grammarAccess.getFeatureAccess().getCommaKeyword_13_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:918:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:919:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:919:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:920:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureAccess().getOutgoingDirectedRelationshipsDirectedRelationshipCrossReference_13_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeature2045);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    otherlv_54=(Token)match(input,21,FOLLOW_21_in_ruleFeature2059); 

                        	newLeafNode(otherlv_54, grammarAccess.getFeatureAccess().getRightParenthesisKeyword_13_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:937:3: (otherlv_55= 'featureIsRoot' ( ( ruleEString ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==30) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:937:5: otherlv_55= 'featureIsRoot' ( ( ruleEString ) )
                    {
                    otherlv_55=(Token)match(input,30,FOLLOW_30_in_ruleFeature2074); 

                        	newLeafNode(otherlv_55, grammarAccess.getFeatureAccess().getFeatureIsRootKeyword_14_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:941:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:942:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:942:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:943:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureAccess().getFeatureIsRootFeatureIsRootCrossReference_14_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeature2097);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_57=(Token)match(input,16,FOLLOW_16_in_ruleFeature2111); 

                	newLeafNode(otherlv_57, grammarAccess.getFeatureAccess().getRightCurlyBracketKeyword_15());
                

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
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleGroupHasParent"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:968:1: entryRuleGroupHasParent returns [EObject current=null] : iv_ruleGroupHasParent= ruleGroupHasParent EOF ;
    public final EObject entryRuleGroupHasParent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupHasParent = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:969:2: (iv_ruleGroupHasParent= ruleGroupHasParent EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:970:2: iv_ruleGroupHasParent= ruleGroupHasParent EOF
            {
             newCompositeNode(grammarAccess.getGroupHasParentRule()); 
            pushFollow(FOLLOW_ruleGroupHasParent_in_entryRuleGroupHasParent2147);
            iv_ruleGroupHasParent=ruleGroupHasParent();

            state._fsp--;

             current =iv_ruleGroupHasParent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupHasParent2157); 

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
    // $ANTLR end "entryRuleGroupHasParent"


    // $ANTLR start "ruleGroupHasParent"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:977:1: ruleGroupHasParent returns [EObject current=null] : (otherlv_0= 'GroupHasParent' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleGroupHasParent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:980:28: ( (otherlv_0= 'GroupHasParent' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:981:1: (otherlv_0= 'GroupHasParent' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:981:1: (otherlv_0= 'GroupHasParent' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:981:3: otherlv_0= 'GroupHasParent' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,31,FOLLOW_31_in_ruleGroupHasParent2194); 

                	newLeafNode(otherlv_0, grammarAccess.getGroupHasParentAccess().getGroupHasParentKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:985:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:986:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:986:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:987:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2215);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasParentRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleGroupHasParent2227); 

                	newLeafNode(otherlv_2, grammarAccess.getGroupHasParentAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleGroupHasParent2239); 

                	newLeafNode(otherlv_3, grammarAccess.getGroupHasParentAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1011:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1012:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1012:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1013:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2260);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasParentRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleGroupHasParent2272); 

                	newLeafNode(otherlv_5, grammarAccess.getGroupHasParentAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1033:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1034:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1034:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1035:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleGroupHasParent2293);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasParentRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1051:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==33) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1051:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleGroupHasParent2306); 

                        	newLeafNode(otherlv_7, grammarAccess.getGroupHasParentAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleGroupHasParent2318); 

                        	newLeafNode(otherlv_8, grammarAccess.getGroupHasParentAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1059:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1060:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1060:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1061:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGroupHasParentRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2341);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1074:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==15) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1074:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleGroupHasParent2354); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getGroupHasParentAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1078:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1079:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1079:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1080:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGroupHasParentRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2377);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleGroupHasParent2391); 

                        	newLeafNode(otherlv_12, grammarAccess.getGroupHasParentAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,34,FOLLOW_34_in_ruleGroupHasParent2405); 

                	newLeafNode(otherlv_13, grammarAccess.getGroupHasParentAccess().getParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1101:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1102:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1102:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1103:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasParentRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getParentFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2428);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,35,FOLLOW_35_in_ruleGroupHasParent2440); 

                	newLeafNode(otherlv_15, grammarAccess.getGroupHasParentAccess().getGroupKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1120:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1121:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1121:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1122:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasParentRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasParentAccess().getGroupFeatureGroupCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasParent2463);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleGroupHasParent2475); 

                	newLeafNode(otherlv_17, grammarAccess.getGroupHasParentAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleGroupHasParent"


    // $ANTLR start "entryRuleGroupHasChild"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1147:1: entryRuleGroupHasChild returns [EObject current=null] : iv_ruleGroupHasChild= ruleGroupHasChild EOF ;
    public final EObject entryRuleGroupHasChild() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupHasChild = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1148:2: (iv_ruleGroupHasChild= ruleGroupHasChild EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1149:2: iv_ruleGroupHasChild= ruleGroupHasChild EOF
            {
             newCompositeNode(grammarAccess.getGroupHasChildRule()); 
            pushFollow(FOLLOW_ruleGroupHasChild_in_entryRuleGroupHasChild2511);
            iv_ruleGroupHasChild=ruleGroupHasChild();

            state._fsp--;

             current =iv_ruleGroupHasChild; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupHasChild2521); 

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
    // $ANTLR end "entryRuleGroupHasChild"


    // $ANTLR start "ruleGroupHasChild"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1156:1: ruleGroupHasChild returns [EObject current=null] : (otherlv_0= 'GroupHasChild' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'child' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleGroupHasChild() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1159:28: ( (otherlv_0= 'GroupHasChild' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'child' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1160:1: (otherlv_0= 'GroupHasChild' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'child' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1160:1: (otherlv_0= 'GroupHasChild' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'child' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1160:3: otherlv_0= 'GroupHasChild' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'child' ( ( ruleEString ) ) otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleGroupHasChild2558); 

                	newLeafNode(otherlv_0, grammarAccess.getGroupHasChildAccess().getGroupHasChildKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1164:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1165:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1165:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1166:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2579);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasChildRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleGroupHasChild2591); 

                	newLeafNode(otherlv_2, grammarAccess.getGroupHasChildAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleGroupHasChild2603); 

                	newLeafNode(otherlv_3, grammarAccess.getGroupHasChildAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1190:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1191:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1191:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1192:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2624);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasChildRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleGroupHasChild2636); 

                	newLeafNode(otherlv_5, grammarAccess.getGroupHasChildAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1212:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1213:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1213:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1214:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleGroupHasChild2657);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasChildRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1230:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==33) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1230:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleGroupHasChild2670); 

                        	newLeafNode(otherlv_7, grammarAccess.getGroupHasChildAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleGroupHasChild2682); 

                        	newLeafNode(otherlv_8, grammarAccess.getGroupHasChildAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1238:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1239:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1239:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1240:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGroupHasChildRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2705);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1253:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==15) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1253:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleGroupHasChild2718); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getGroupHasChildAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1257:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1258:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1258:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1259:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGroupHasChildRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2741);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleGroupHasChild2755); 

                        	newLeafNode(otherlv_12, grammarAccess.getGroupHasChildAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,37,FOLLOW_37_in_ruleGroupHasChild2769); 

                	newLeafNode(otherlv_13, grammarAccess.getGroupHasChildAccess().getChildKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1280:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1281:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1281:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1282:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasChildRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getChildFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2792);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,35,FOLLOW_35_in_ruleGroupHasChild2804); 

                	newLeafNode(otherlv_15, grammarAccess.getGroupHasChildAccess().getGroupKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1299:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1300:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1300:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1301:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasChildRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasChildAccess().getGroupFeatureGroupCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasChild2827);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleGroupHasChild2839); 

                	newLeafNode(otherlv_17, grammarAccess.getGroupHasChildAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleGroupHasChild"


    // $ANTLR start "entryRuleFeatureHasSubfeature_Impl"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1326:1: entryRuleFeatureHasSubfeature_Impl returns [EObject current=null] : iv_ruleFeatureHasSubfeature_Impl= ruleFeatureHasSubfeature_Impl EOF ;
    public final EObject entryRuleFeatureHasSubfeature_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureHasSubfeature_Impl = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1327:2: (iv_ruleFeatureHasSubfeature_Impl= ruleFeatureHasSubfeature_Impl EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1328:2: iv_ruleFeatureHasSubfeature_Impl= ruleFeatureHasSubfeature_Impl EOF
            {
             newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplRule()); 
            pushFollow(FOLLOW_ruleFeatureHasSubfeature_Impl_in_entryRuleFeatureHasSubfeature_Impl2875);
            iv_ruleFeatureHasSubfeature_Impl=ruleFeatureHasSubfeature_Impl();

            state._fsp--;

             current =iv_ruleFeatureHasSubfeature_Impl; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureHasSubfeature_Impl2885); 

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
    // $ANTLR end "entryRuleFeatureHasSubfeature_Impl"


    // $ANTLR start "ruleFeatureHasSubfeature_Impl"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1335:1: ruleFeatureHasSubfeature_Impl returns [EObject current=null] : (otherlv_0= 'FeatureHasSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleFeatureHasSubfeature_Impl() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1338:28: ( (otherlv_0= 'FeatureHasSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1339:1: (otherlv_0= 'FeatureHasSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1339:1: (otherlv_0= 'FeatureHasSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1339:3: otherlv_0= 'FeatureHasSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,38,FOLLOW_38_in_ruleFeatureHasSubfeature_Impl2922); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureHasSubfeature_ImplAccess().getFeatureHasSubfeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1343:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1344:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1344:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1345:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl2943);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasSubfeature_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeatureHasSubfeature_Impl2955); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureHasSubfeature_ImplAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeatureHasSubfeature_Impl2967); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureHasSubfeature_ImplAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1369:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1370:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1370:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1371:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl2988);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasSubfeature_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleFeatureHasSubfeature_Impl3000); 

                	newLeafNode(otherlv_5, grammarAccess.getFeatureHasSubfeature_ImplAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1391:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1392:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1392:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1393:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleFeatureHasSubfeature_Impl3021);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasSubfeature_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1409:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==33) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1409:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleFeatureHasSubfeature_Impl3034); 

                        	newLeafNode(otherlv_7, grammarAccess.getFeatureHasSubfeature_ImplAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleFeatureHasSubfeature_Impl3046); 

                        	newLeafNode(otherlv_8, grammarAccess.getFeatureHasSubfeature_ImplAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1417:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1418:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1418:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1419:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureHasSubfeature_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3069);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1432:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==15) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1432:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleFeatureHasSubfeature_Impl3082); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getFeatureHasSubfeature_ImplAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1436:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1437:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1437:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1438:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureHasSubfeature_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3105);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleFeatureHasSubfeature_Impl3119); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureHasSubfeature_ImplAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,34,FOLLOW_34_in_ruleFeatureHasSubfeature_Impl3133); 

                	newLeafNode(otherlv_13, grammarAccess.getFeatureHasSubfeature_ImplAccess().getParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1459:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1460:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1460:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1461:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasSubfeature_ImplRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getParentFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3156);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,39,FOLLOW_39_in_ruleFeatureHasSubfeature_Impl3168); 

                	newLeafNode(otherlv_15, grammarAccess.getFeatureHasSubfeature_ImplAccess().getSubfeatureKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1478:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1479:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1479:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1480:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasSubfeature_ImplRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasSubfeature_ImplAccess().getSubfeatureFeatureCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3191);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleFeatureHasSubfeature_Impl3203); 

                	newLeafNode(otherlv_17, grammarAccess.getFeatureHasSubfeature_ImplAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleFeatureHasSubfeature_Impl"


    // $ANTLR start "entryRuleSelectedFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1505:1: entryRuleSelectedFeature returns [EObject current=null] : iv_ruleSelectedFeature= ruleSelectedFeature EOF ;
    public final EObject entryRuleSelectedFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectedFeature = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1506:2: (iv_ruleSelectedFeature= ruleSelectedFeature EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1507:2: iv_ruleSelectedFeature= ruleSelectedFeature EOF
            {
             newCompositeNode(grammarAccess.getSelectedFeatureRule()); 
            pushFollow(FOLLOW_ruleSelectedFeature_in_entryRuleSelectedFeature3239);
            iv_ruleSelectedFeature=ruleSelectedFeature();

            state._fsp--;

             current =iv_ruleSelectedFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectedFeature3249); 

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
    // $ANTLR end "entryRuleSelectedFeature"


    // $ANTLR start "ruleSelectedFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1514:1: ruleSelectedFeature returns [EObject current=null] : (otherlv_0= 'SelectedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) ;
    public final EObject ruleSelectedFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1517:28: ( (otherlv_0= 'SelectedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1518:1: (otherlv_0= 'SelectedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1518:1: (otherlv_0= 'SelectedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1518:3: otherlv_0= 'SelectedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}'
            {
            otherlv_0=(Token)match(input,40,FOLLOW_40_in_ruleSelectedFeature3286); 

                	newLeafNode(otherlv_0, grammarAccess.getSelectedFeatureAccess().getSelectedFeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1522:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1523:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1523:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1524:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleSelectedFeature3307);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleSelectedFeature3319); 

                	newLeafNode(otherlv_2, grammarAccess.getSelectedFeatureAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleSelectedFeature3331); 

                	newLeafNode(otherlv_3, grammarAccess.getSelectedFeatureAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1548:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1549:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1549:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1550:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleSelectedFeature3352);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleSelectedFeature3364); 

                	newLeafNode(otherlv_5, grammarAccess.getSelectedFeatureAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1570:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1571:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1571:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1572:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleSelectedFeature3385);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1588:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==33) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1588:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleSelectedFeature3398); 

                        	newLeafNode(otherlv_7, grammarAccess.getSelectedFeatureAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleSelectedFeature3410); 

                        	newLeafNode(otherlv_8, grammarAccess.getSelectedFeatureAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1596:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1597:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1597:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1598:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelectedFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleSelectedFeature3433);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1611:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==15) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1611:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleSelectedFeature3446); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getSelectedFeatureAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1615:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1616:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1616:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1617:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getSelectedFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleSelectedFeature3469);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleSelectedFeature3483); 

                        	newLeafNode(otherlv_12, grammarAccess.getSelectedFeatureAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,41,FOLLOW_41_in_ruleSelectedFeature3497); 

                	newLeafNode(otherlv_13, grammarAccess.getSelectedFeatureAccess().getFeatureKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1638:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1639:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1639:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1640:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectedFeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getSelectedFeatureAccess().getFeatureFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleSelectedFeature3520);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,16,FOLLOW_16_in_ruleSelectedFeature3532); 

                	newLeafNode(otherlv_15, grammarAccess.getSelectedFeatureAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleSelectedFeature"


    // $ANTLR start "entryRuleEliminatedFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1665:1: entryRuleEliminatedFeature returns [EObject current=null] : iv_ruleEliminatedFeature= ruleEliminatedFeature EOF ;
    public final EObject entryRuleEliminatedFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEliminatedFeature = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1666:2: (iv_ruleEliminatedFeature= ruleEliminatedFeature EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1667:2: iv_ruleEliminatedFeature= ruleEliminatedFeature EOF
            {
             newCompositeNode(grammarAccess.getEliminatedFeatureRule()); 
            pushFollow(FOLLOW_ruleEliminatedFeature_in_entryRuleEliminatedFeature3568);
            iv_ruleEliminatedFeature=ruleEliminatedFeature();

            state._fsp--;

             current =iv_ruleEliminatedFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEliminatedFeature3578); 

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
    // $ANTLR end "entryRuleEliminatedFeature"


    // $ANTLR start "ruleEliminatedFeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1674:1: ruleEliminatedFeature returns [EObject current=null] : (otherlv_0= 'EliminatedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) ;
    public final EObject ruleEliminatedFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1677:28: ( (otherlv_0= 'EliminatedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1678:1: (otherlv_0= 'EliminatedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1678:1: (otherlv_0= 'EliminatedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1678:3: otherlv_0= 'EliminatedFeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}'
            {
            otherlv_0=(Token)match(input,42,FOLLOW_42_in_ruleEliminatedFeature3615); 

                	newLeafNode(otherlv_0, grammarAccess.getEliminatedFeatureAccess().getEliminatedFeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1682:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1683:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1683:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1684:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleEliminatedFeature3636);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEliminatedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleEliminatedFeature3648); 

                	newLeafNode(otherlv_2, grammarAccess.getEliminatedFeatureAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleEliminatedFeature3660); 

                	newLeafNode(otherlv_3, grammarAccess.getEliminatedFeatureAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1708:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1709:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1709:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1710:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleEliminatedFeature3681);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEliminatedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleEliminatedFeature3693); 

                	newLeafNode(otherlv_5, grammarAccess.getEliminatedFeatureAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1730:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1731:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1731:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1732:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleEliminatedFeature3714);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEliminatedFeatureRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1748:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==33) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1748:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleEliminatedFeature3727); 

                        	newLeafNode(otherlv_7, grammarAccess.getEliminatedFeatureAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleEliminatedFeature3739); 

                        	newLeafNode(otherlv_8, grammarAccess.getEliminatedFeatureAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1756:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1757:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1757:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1758:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getEliminatedFeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleEliminatedFeature3762);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1771:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==15) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1771:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleEliminatedFeature3775); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getEliminatedFeatureAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1775:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1776:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1776:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1777:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getEliminatedFeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleEliminatedFeature3798);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleEliminatedFeature3812); 

                        	newLeafNode(otherlv_12, grammarAccess.getEliminatedFeatureAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,41,FOLLOW_41_in_ruleEliminatedFeature3826); 

                	newLeafNode(otherlv_13, grammarAccess.getEliminatedFeatureAccess().getFeatureKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1798:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1799:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1799:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1800:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getEliminatedFeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getEliminatedFeatureAccess().getFeatureFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleEliminatedFeature3849);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,16,FOLLOW_16_in_ruleEliminatedFeature3861); 

                	newLeafNode(otherlv_15, grammarAccess.getEliminatedFeatureAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleEliminatedFeature"


    // $ANTLR start "entryRuleFeatureIsRoot"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1825:1: entryRuleFeatureIsRoot returns [EObject current=null] : iv_ruleFeatureIsRoot= ruleFeatureIsRoot EOF ;
    public final EObject entryRuleFeatureIsRoot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureIsRoot = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1826:2: (iv_ruleFeatureIsRoot= ruleFeatureIsRoot EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1827:2: iv_ruleFeatureIsRoot= ruleFeatureIsRoot EOF
            {
             newCompositeNode(grammarAccess.getFeatureIsRootRule()); 
            pushFollow(FOLLOW_ruleFeatureIsRoot_in_entryRuleFeatureIsRoot3897);
            iv_ruleFeatureIsRoot=ruleFeatureIsRoot();

            state._fsp--;

             current =iv_ruleFeatureIsRoot; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureIsRoot3907); 

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
    // $ANTLR end "entryRuleFeatureIsRoot"


    // $ANTLR start "ruleFeatureIsRoot"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1834:1: ruleFeatureIsRoot returns [EObject current=null] : (otherlv_0= 'FeatureIsRoot' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) ;
    public final EObject ruleFeatureIsRoot() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1837:28: ( (otherlv_0= 'FeatureIsRoot' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1838:1: (otherlv_0= 'FeatureIsRoot' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1838:1: (otherlv_0= 'FeatureIsRoot' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1838:3: otherlv_0= 'FeatureIsRoot' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'feature' ( ( ruleEString ) ) otherlv_15= '}'
            {
            otherlv_0=(Token)match(input,43,FOLLOW_43_in_ruleFeatureIsRoot3944); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureIsRootAccess().getFeatureIsRootKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1842:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1843:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1843:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1844:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureIsRoot3965);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureIsRootRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeatureIsRoot3977); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureIsRootAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeatureIsRoot3989); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureIsRootAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1868:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1869:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1869:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1870:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureIsRoot4010);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureIsRootRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleFeatureIsRoot4022); 

                	newLeafNode(otherlv_5, grammarAccess.getFeatureIsRootAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1890:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1891:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1891:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1892:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleFeatureIsRoot4043);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureIsRootRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1908:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==33) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1908:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleFeatureIsRoot4056); 

                        	newLeafNode(otherlv_7, grammarAccess.getFeatureIsRootAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleFeatureIsRoot4068); 

                        	newLeafNode(otherlv_8, grammarAccess.getFeatureIsRootAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1916:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1917:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1917:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1918:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureIsRootRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeatureIsRoot4091);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1931:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==15) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1931:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleFeatureIsRoot4104); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getFeatureIsRootAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1935:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1936:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1936:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1937:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureIsRootRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeatureIsRoot4127);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleFeatureIsRoot4141); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureIsRootAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,41,FOLLOW_41_in_ruleFeatureIsRoot4155); 

                	newLeafNode(otherlv_13, grammarAccess.getFeatureIsRootAccess().getFeatureKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1958:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1959:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1959:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1960:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureIsRootRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureIsRootAccess().getFeatureFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureIsRoot4178);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,16,FOLLOW_16_in_ruleFeatureIsRoot4190); 

                	newLeafNode(otherlv_15, grammarAccess.getFeatureIsRootAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleFeatureIsRoot"


    // $ANTLR start "entryRuleGroupHasMax"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1987:1: entryRuleGroupHasMax returns [EObject current=null] : iv_ruleGroupHasMax= ruleGroupHasMax EOF ;
    public final EObject entryRuleGroupHasMax() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupHasMax = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1988:2: (iv_ruleGroupHasMax= ruleGroupHasMax EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1989:2: iv_ruleGroupHasMax= ruleGroupHasMax EOF
            {
             newCompositeNode(grammarAccess.getGroupHasMaxRule()); 
            pushFollow(FOLLOW_ruleGroupHasMax_in_entryRuleGroupHasMax4228);
            iv_ruleGroupHasMax=ruleGroupHasMax();

            state._fsp--;

             current =iv_ruleGroupHasMax; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupHasMax4238); 

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
    // $ANTLR end "entryRuleGroupHasMax"


    // $ANTLR start "ruleGroupHasMax"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1996:1: ruleGroupHasMax returns [EObject current=null] : (otherlv_0= 'GroupHasMax' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'max' ( (lv_max_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleGroupHasMax() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;

        AntlrDatatypeRuleToken lv_max_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:1999:28: ( (otherlv_0= 'GroupHasMax' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'max' ( (lv_max_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2000:1: (otherlv_0= 'GroupHasMax' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'max' ( (lv_max_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2000:1: (otherlv_0= 'GroupHasMax' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'max' ( (lv_max_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2000:3: otherlv_0= 'GroupHasMax' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'max' ( (lv_max_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,44,FOLLOW_44_in_ruleGroupHasMax4275); 

                	newLeafNode(otherlv_0, grammarAccess.getGroupHasMaxAccess().getGroupHasMaxKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2004:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2005:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2005:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2006:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMax4296);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMaxRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleGroupHasMax4308); 

                	newLeafNode(otherlv_2, grammarAccess.getGroupHasMaxAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleGroupHasMax4320); 

                	newLeafNode(otherlv_3, grammarAccess.getGroupHasMaxAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2030:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2031:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2031:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2032:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMax4341);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMaxRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleGroupHasMax4353); 

                	newLeafNode(otherlv_5, grammarAccess.getGroupHasMaxAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2052:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2053:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2053:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2054:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleGroupHasMax4374);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMaxRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,45,FOLLOW_45_in_ruleGroupHasMax4386); 

                	newLeafNode(otherlv_7, grammarAccess.getGroupHasMaxAccess().getMaxKeyword_7());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2074:1: ( (lv_max_8_0= ruleEInt ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2075:1: (lv_max_8_0= ruleEInt )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2075:1: (lv_max_8_0= ruleEInt )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2076:3: lv_max_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getMaxEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleGroupHasMax4407);
            lv_max_8_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMaxRule());
            	        }
                   		set(
                   			current, 
                   			"max",
                    		lv_max_8_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2092:2: (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==33) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2092:4: otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')'
                    {
                    otherlv_9=(Token)match(input,33,FOLLOW_33_in_ruleGroupHasMax4420); 

                        	newLeafNode(otherlv_9, grammarAccess.getGroupHasMaxAccess().getExplanationsKeyword_9_0());
                        
                    otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleGroupHasMax4432); 

                        	newLeafNode(otherlv_10, grammarAccess.getGroupHasMaxAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2100:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2101:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2101:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2102:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGroupHasMaxRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getExplanationsExplanationCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMax4455);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2115:2: (otherlv_12= ',' ( ( ruleEString ) ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==15) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2115:4: otherlv_12= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FOLLOW_15_in_ruleGroupHasMax4468); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getGroupHasMaxAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2119:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2120:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2120:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2121:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGroupHasMaxRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getExplanationsExplanationCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMax4491);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleGroupHasMax4505); 

                        	newLeafNode(otherlv_14, grammarAccess.getGroupHasMaxAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_15=(Token)match(input,35,FOLLOW_35_in_ruleGroupHasMax4519); 

                	newLeafNode(otherlv_15, grammarAccess.getGroupHasMaxAccess().getGroupKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2142:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2143:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2143:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2144:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasMaxRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasMaxAccess().getGroupFeatureGroupCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMax4542);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleGroupHasMax4554); 

                	newLeafNode(otherlv_17, grammarAccess.getGroupHasMaxAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleGroupHasMax"


    // $ANTLR start "entryRuleGroupHasMin"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2169:1: entryRuleGroupHasMin returns [EObject current=null] : iv_ruleGroupHasMin= ruleGroupHasMin EOF ;
    public final EObject entryRuleGroupHasMin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroupHasMin = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2170:2: (iv_ruleGroupHasMin= ruleGroupHasMin EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2171:2: iv_ruleGroupHasMin= ruleGroupHasMin EOF
            {
             newCompositeNode(grammarAccess.getGroupHasMinRule()); 
            pushFollow(FOLLOW_ruleGroupHasMin_in_entryRuleGroupHasMin4590);
            iv_ruleGroupHasMin=ruleGroupHasMin();

            state._fsp--;

             current =iv_ruleGroupHasMin; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupHasMin4600); 

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
    // $ANTLR end "entryRuleGroupHasMin"


    // $ANTLR start "ruleGroupHasMin"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2178:1: ruleGroupHasMin returns [EObject current=null] : (otherlv_0= 'GroupHasMin' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'min' ( (lv_min_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleGroupHasMin() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;

        AntlrDatatypeRuleToken lv_min_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2181:28: ( (otherlv_0= 'GroupHasMin' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'min' ( (lv_min_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2182:1: (otherlv_0= 'GroupHasMin' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'min' ( (lv_min_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2182:1: (otherlv_0= 'GroupHasMin' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'min' ( (lv_min_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2182:3: otherlv_0= 'GroupHasMin' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'min' ( (lv_min_8_0= ruleEInt ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? otherlv_15= 'group' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_46_in_ruleGroupHasMin4637); 

                	newLeafNode(otherlv_0, grammarAccess.getGroupHasMinAccess().getGroupHasMinKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2186:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2187:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2187:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2188:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMin4658);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMinRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleGroupHasMin4670); 

                	newLeafNode(otherlv_2, grammarAccess.getGroupHasMinAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleGroupHasMin4682); 

                	newLeafNode(otherlv_3, grammarAccess.getGroupHasMinAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2212:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2213:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2213:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2214:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMin4703);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMinRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleGroupHasMin4715); 

                	newLeafNode(otherlv_5, grammarAccess.getGroupHasMinAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2234:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2235:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2235:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2236:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleGroupHasMin4736);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMinRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,47,FOLLOW_47_in_ruleGroupHasMin4748); 

                	newLeafNode(otherlv_7, grammarAccess.getGroupHasMinAccess().getMinKeyword_7());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2256:1: ( (lv_min_8_0= ruleEInt ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2257:1: (lv_min_8_0= ruleEInt )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2257:1: (lv_min_8_0= ruleEInt )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2258:3: lv_min_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getMinEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleGroupHasMin4769);
            lv_min_8_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGroupHasMinRule());
            	        }
                   		set(
                   			current, 
                   			"min",
                    		lv_min_8_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2274:2: (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==33) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2274:4: otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')'
                    {
                    otherlv_9=(Token)match(input,33,FOLLOW_33_in_ruleGroupHasMin4782); 

                        	newLeafNode(otherlv_9, grammarAccess.getGroupHasMinAccess().getExplanationsKeyword_9_0());
                        
                    otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleGroupHasMin4794); 

                        	newLeafNode(otherlv_10, grammarAccess.getGroupHasMinAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2282:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2283:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2283:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2284:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getGroupHasMinRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getExplanationsExplanationCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMin4817);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2297:2: (otherlv_12= ',' ( ( ruleEString ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==15) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2297:4: otherlv_12= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FOLLOW_15_in_ruleGroupHasMin4830); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getGroupHasMinAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2301:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2302:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2302:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2303:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGroupHasMinRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getExplanationsExplanationCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMin4853);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleGroupHasMin4867); 

                        	newLeafNode(otherlv_14, grammarAccess.getGroupHasMinAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_15=(Token)match(input,35,FOLLOW_35_in_ruleGroupHasMin4881); 

                	newLeafNode(otherlv_15, grammarAccess.getGroupHasMinAccess().getGroupKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2324:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2325:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2325:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2326:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getGroupHasMinRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getGroupHasMinAccess().getGroupFeatureGroupCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleGroupHasMin4904);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleGroupHasMin4916); 

                	newLeafNode(otherlv_17, grammarAccess.getGroupHasMinAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleGroupHasMin"


    // $ANTLR start "entryRuleAlternativeGroup"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2351:1: entryRuleAlternativeGroup returns [EObject current=null] : iv_ruleAlternativeGroup= ruleAlternativeGroup EOF ;
    public final EObject entryRuleAlternativeGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAlternativeGroup = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2352:2: (iv_ruleAlternativeGroup= ruleAlternativeGroup EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2353:2: iv_ruleAlternativeGroup= ruleAlternativeGroup EOF
            {
             newCompositeNode(grammarAccess.getAlternativeGroupRule()); 
            pushFollow(FOLLOW_ruleAlternativeGroup_in_entryRuleAlternativeGroup4952);
            iv_ruleAlternativeGroup=ruleAlternativeGroup();

            state._fsp--;

             current =iv_ruleAlternativeGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAlternativeGroup4962); 

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
    // $ANTLR end "entryRuleAlternativeGroup"


    // $ANTLR start "ruleAlternativeGroup"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2360:1: ruleAlternativeGroup returns [EObject current=null] : (otherlv_0= 'AlternativeGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' ) ;
    public final EObject ruleAlternativeGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2363:28: ( (otherlv_0= 'AlternativeGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2364:1: (otherlv_0= 'AlternativeGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2364:1: (otherlv_0= 'AlternativeGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2364:3: otherlv_0= 'AlternativeGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}'
            {
            otherlv_0=(Token)match(input,48,FOLLOW_48_in_ruleAlternativeGroup4999); 

                	newLeafNode(otherlv_0, grammarAccess.getAlternativeGroupAccess().getAlternativeGroupKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2368:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2369:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2369:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2370:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5020);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAlternativeGroupRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAlternativeGroup5032); 

                	newLeafNode(otherlv_2, grammarAccess.getAlternativeGroupAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAlternativeGroup5044); 

                	newLeafNode(otherlv_3, grammarAccess.getAlternativeGroupAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2394:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2395:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2395:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2396:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5065);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAlternativeGroupRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleAlternativeGroup5077); 

                	newLeafNode(otherlv_5, grammarAccess.getAlternativeGroupAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2416:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2417:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2417:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2418:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleAlternativeGroup5098);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAlternativeGroupRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2434:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==33) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2434:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleAlternativeGroup5111); 

                        	newLeafNode(otherlv_7, grammarAccess.getAlternativeGroupAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleAlternativeGroup5123); 

                        	newLeafNode(otherlv_8, grammarAccess.getAlternativeGroupAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2442:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2443:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2443:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2444:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5146);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2457:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==15) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2457:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleAlternativeGroup5159); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getAlternativeGroupAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2461:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2462:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2462:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2463:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5182);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleAlternativeGroup5196); 

                        	newLeafNode(otherlv_12, grammarAccess.getAlternativeGroupAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,19,FOLLOW_19_in_ruleAlternativeGroup5210); 

                	newLeafNode(otherlv_13, grammarAccess.getAlternativeGroupAccess().getGroupHasParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2484:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2485:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2485:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2486:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getGroupHasParentGroupHasParentCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5233);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2499:2: (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==22) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2499:4: otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,22,FOLLOW_22_in_ruleAlternativeGroup5246); 

                        	newLeafNode(otherlv_15, grammarAccess.getAlternativeGroupAccess().getGroupHasChildKeyword_10_0());
                        
                    otherlv_16=(Token)match(input,20,FOLLOW_20_in_ruleAlternativeGroup5258); 

                        	newLeafNode(otherlv_16, grammarAccess.getAlternativeGroupAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2507:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2508:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2508:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2509:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getGroupHasChildGroupHasChildCrossReference_10_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5281);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2522:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==15) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2522:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FOLLOW_15_in_ruleAlternativeGroup5294); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getAlternativeGroupAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2526:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2527:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2527:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2528:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getGroupHasChildGroupHasChildCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5317);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,21,FOLLOW_21_in_ruleAlternativeGroup5331); 

                        	newLeafNode(otherlv_20, grammarAccess.getAlternativeGroupAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2545:3: (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==49) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2545:5: otherlv_21= 'groupHasMax' ( ( ruleEString ) )
                    {
                    otherlv_21=(Token)match(input,49,FOLLOW_49_in_ruleAlternativeGroup5346); 

                        	newLeafNode(otherlv_21, grammarAccess.getAlternativeGroupAccess().getGroupHasMaxKeyword_11_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2549:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2550:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2550:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2551:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getGroupHasMaxGroupHasMaxCrossReference_11_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5369);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2564:4: (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==50) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2564:6: otherlv_23= 'groupHasMin' ( ( ruleEString ) )
                    {
                    otherlv_23=(Token)match(input,50,FOLLOW_50_in_ruleAlternativeGroup5384); 

                        	newLeafNode(otherlv_23, grammarAccess.getAlternativeGroupAccess().getGroupHasMinKeyword_12_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2568:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2569:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2569:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2570:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAlternativeGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAlternativeGroupAccess().getGroupHasMinGroupHasMinCrossReference_12_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleAlternativeGroup5407);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_25=(Token)match(input,16,FOLLOW_16_in_ruleAlternativeGroup5421); 

                	newLeafNode(otherlv_25, grammarAccess.getAlternativeGroupAccess().getRightCurlyBracketKeyword_13());
                

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
    // $ANTLR end "ruleAlternativeGroup"


    // $ANTLR start "entryRuleOrGroup"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2595:1: entryRuleOrGroup returns [EObject current=null] : iv_ruleOrGroup= ruleOrGroup EOF ;
    public final EObject entryRuleOrGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrGroup = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2596:2: (iv_ruleOrGroup= ruleOrGroup EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2597:2: iv_ruleOrGroup= ruleOrGroup EOF
            {
             newCompositeNode(grammarAccess.getOrGroupRule()); 
            pushFollow(FOLLOW_ruleOrGroup_in_entryRuleOrGroup5457);
            iv_ruleOrGroup=ruleOrGroup();

            state._fsp--;

             current =iv_ruleOrGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrGroup5467); 

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
    // $ANTLR end "entryRuleOrGroup"


    // $ANTLR start "ruleOrGroup"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2604:1: ruleOrGroup returns [EObject current=null] : (otherlv_0= 'OrGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' ) ;
    public final EObject ruleOrGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2607:28: ( (otherlv_0= 'OrGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2608:1: (otherlv_0= 'OrGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2608:1: (otherlv_0= 'OrGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2608:3: otherlv_0= 'OrGroup' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'groupHasParent' ( ( ruleEString ) ) (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )? (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )? otherlv_25= '}'
            {
            otherlv_0=(Token)match(input,51,FOLLOW_51_in_ruleOrGroup5504); 

                	newLeafNode(otherlv_0, grammarAccess.getOrGroupAccess().getOrGroupKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2612:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2613:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2613:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2614:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getOrGroupAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5525);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrGroupRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleOrGroup5537); 

                	newLeafNode(otherlv_2, grammarAccess.getOrGroupAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleOrGroup5549); 

                	newLeafNode(otherlv_3, grammarAccess.getOrGroupAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2638:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2639:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2639:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2640:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getOrGroupAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5570);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrGroupRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleOrGroup5582); 

                	newLeafNode(otherlv_5, grammarAccess.getOrGroupAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2660:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2661:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2661:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2662:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getOrGroupAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleOrGroup5603);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOrGroupRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2678:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==33) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2678:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleOrGroup5616); 

                        	newLeafNode(otherlv_7, grammarAccess.getOrGroupAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleOrGroup5628); 

                        	newLeafNode(otherlv_8, grammarAccess.getOrGroupAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2686:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2687:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2687:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2688:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getOrGroupAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5651);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2701:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==15) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2701:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleOrGroup5664); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getOrGroupAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2705:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2706:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2706:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2707:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrGroupAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5687);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleOrGroup5701); 

                        	newLeafNode(otherlv_12, grammarAccess.getOrGroupAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,19,FOLLOW_19_in_ruleOrGroup5715); 

                	newLeafNode(otherlv_13, grammarAccess.getOrGroupAccess().getGroupHasParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2728:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2729:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2729:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2730:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOrGroupRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getOrGroupAccess().getGroupHasParentGroupHasParentCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5738);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2743:2: (otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==22) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2743:4: otherlv_15= 'groupHasChild' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,22,FOLLOW_22_in_ruleOrGroup5751); 

                        	newLeafNode(otherlv_15, grammarAccess.getOrGroupAccess().getGroupHasChildKeyword_10_0());
                        
                    otherlv_16=(Token)match(input,20,FOLLOW_20_in_ruleOrGroup5763); 

                        	newLeafNode(otherlv_16, grammarAccess.getOrGroupAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2751:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2752:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2752:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2753:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getOrGroupAccess().getGroupHasChildGroupHasChildCrossReference_10_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5786);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2766:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==15) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2766:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FOLLOW_15_in_ruleOrGroup5799); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getOrGroupAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2770:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2771:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2771:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2772:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getOrGroupAccess().getGroupHasChildGroupHasChildCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5822);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,21,FOLLOW_21_in_ruleOrGroup5836); 

                        	newLeafNode(otherlv_20, grammarAccess.getOrGroupAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2789:3: (otherlv_21= 'groupHasMax' ( ( ruleEString ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==49) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2789:5: otherlv_21= 'groupHasMax' ( ( ruleEString ) )
                    {
                    otherlv_21=(Token)match(input,49,FOLLOW_49_in_ruleOrGroup5851); 

                        	newLeafNode(otherlv_21, grammarAccess.getOrGroupAccess().getGroupHasMaxKeyword_11_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2793:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2794:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2794:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2795:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getOrGroupAccess().getGroupHasMaxGroupHasMaxCrossReference_11_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5874);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2808:4: (otherlv_23= 'groupHasMin' ( ( ruleEString ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==50) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2808:6: otherlv_23= 'groupHasMin' ( ( ruleEString ) )
                    {
                    otherlv_23=(Token)match(input,50,FOLLOW_50_in_ruleOrGroup5889); 

                        	newLeafNode(otherlv_23, grammarAccess.getOrGroupAccess().getGroupHasMinKeyword_12_0());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2812:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2813:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2813:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2814:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getOrGroupRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getOrGroupAccess().getGroupHasMinGroupHasMinCrossReference_12_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOrGroup5912);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_25=(Token)match(input,16,FOLLOW_16_in_ruleOrGroup5926); 

                	newLeafNode(otherlv_25, grammarAccess.getOrGroupAccess().getRightCurlyBracketKeyword_13());
                

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
    // $ANTLR end "ruleOrGroup"


    // $ANTLR start "entryRuleEInt"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2839:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2840:2: (iv_ruleEInt= ruleEInt EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2841:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FOLLOW_ruleEInt_in_entryRuleEInt5963);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEInt5974); 

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
    // $ANTLR end "entryRuleEInt"


    // $ANTLR start "ruleEInt"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2848:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2851:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2852:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2852:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2852:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2852:2: (kw= '-' )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==52) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2853:2: kw= '-'
                    {
                    kw=(Token)match(input,52,FOLLOW_52_in_ruleEInt6013); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleEInt6030); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEIntAccess().getINTTerminalRuleCall_1()); 
                

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
    // $ANTLR end "ruleEInt"


    // $ANTLR start "entryRuleFeatureHasOptionalSubfeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2873:1: entryRuleFeatureHasOptionalSubfeature returns [EObject current=null] : iv_ruleFeatureHasOptionalSubfeature= ruleFeatureHasOptionalSubfeature EOF ;
    public final EObject entryRuleFeatureHasOptionalSubfeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureHasOptionalSubfeature = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2874:2: (iv_ruleFeatureHasOptionalSubfeature= ruleFeatureHasOptionalSubfeature EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2875:2: iv_ruleFeatureHasOptionalSubfeature= ruleFeatureHasOptionalSubfeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureRule()); 
            pushFollow(FOLLOW_ruleFeatureHasOptionalSubfeature_in_entryRuleFeatureHasOptionalSubfeature6075);
            iv_ruleFeatureHasOptionalSubfeature=ruleFeatureHasOptionalSubfeature();

            state._fsp--;

             current =iv_ruleFeatureHasOptionalSubfeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureHasOptionalSubfeature6085); 

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
    // $ANTLR end "entryRuleFeatureHasOptionalSubfeature"


    // $ANTLR start "ruleFeatureHasOptionalSubfeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2882:1: ruleFeatureHasOptionalSubfeature returns [EObject current=null] : (otherlv_0= 'FeatureHasOptionalSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleFeatureHasOptionalSubfeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2885:28: ( (otherlv_0= 'FeatureHasOptionalSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2886:1: (otherlv_0= 'FeatureHasOptionalSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2886:1: (otherlv_0= 'FeatureHasOptionalSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2886:3: otherlv_0= 'FeatureHasOptionalSubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,53,FOLLOW_53_in_ruleFeatureHasOptionalSubfeature6122); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getFeatureHasOptionalSubfeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2890:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2891:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2891:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2892:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6143);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasOptionalSubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeatureHasOptionalSubfeature6155); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeatureHasOptionalSubfeature6167); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2916:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2917:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2917:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2918:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6188);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasOptionalSubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleFeatureHasOptionalSubfeature6200); 

                	newLeafNode(otherlv_5, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2938:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2939:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2939:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2940:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleFeatureHasOptionalSubfeature6221);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasOptionalSubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2956:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==33) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2956:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleFeatureHasOptionalSubfeature6234); 

                        	newLeafNode(otherlv_7, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleFeatureHasOptionalSubfeature6246); 

                        	newLeafNode(otherlv_8, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2964:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2965:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2965:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2966:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureHasOptionalSubfeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6269);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2979:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==15) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2979:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleFeatureHasOptionalSubfeature6282); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2983:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2984:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2984:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:2985:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureHasOptionalSubfeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6305);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleFeatureHasOptionalSubfeature6319); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,34,FOLLOW_34_in_ruleFeatureHasOptionalSubfeature6333); 

                	newLeafNode(otherlv_13, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3006:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3007:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3007:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3008:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasOptionalSubfeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getParentFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6356);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,39,FOLLOW_39_in_ruleFeatureHasOptionalSubfeature6368); 

                	newLeafNode(otherlv_15, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getSubfeatureKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3025:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3026:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3026:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3027:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasOptionalSubfeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasOptionalSubfeatureAccess().getSubfeatureFeatureCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6391);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleFeatureHasOptionalSubfeature6403); 

                	newLeafNode(otherlv_17, grammarAccess.getFeatureHasOptionalSubfeatureAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleFeatureHasOptionalSubfeature"


    // $ANTLR start "entryRuleFeatureHasMandatorySubfeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3052:1: entryRuleFeatureHasMandatorySubfeature returns [EObject current=null] : iv_ruleFeatureHasMandatorySubfeature= ruleFeatureHasMandatorySubfeature EOF ;
    public final EObject entryRuleFeatureHasMandatorySubfeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureHasMandatorySubfeature = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3053:2: (iv_ruleFeatureHasMandatorySubfeature= ruleFeatureHasMandatorySubfeature EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3054:2: iv_ruleFeatureHasMandatorySubfeature= ruleFeatureHasMandatorySubfeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureRule()); 
            pushFollow(FOLLOW_ruleFeatureHasMandatorySubfeature_in_entryRuleFeatureHasMandatorySubfeature6439);
            iv_ruleFeatureHasMandatorySubfeature=ruleFeatureHasMandatorySubfeature();

            state._fsp--;

             current =iv_ruleFeatureHasMandatorySubfeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureHasMandatorySubfeature6449); 

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
    // $ANTLR end "entryRuleFeatureHasMandatorySubfeature"


    // $ANTLR start "ruleFeatureHasMandatorySubfeature"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3061:1: ruleFeatureHasMandatorySubfeature returns [EObject current=null] : (otherlv_0= 'FeatureHasMandatorySubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) ;
    public final EObject ruleFeatureHasMandatorySubfeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3064:28: ( (otherlv_0= 'FeatureHasMandatorySubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3065:1: (otherlv_0= 'FeatureHasMandatorySubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3065:1: (otherlv_0= 'FeatureHasMandatorySubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3065:3: otherlv_0= 'FeatureHasMandatorySubfeature' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= 'parent' ( ( ruleEString ) ) otherlv_15= 'subfeature' ( ( ruleEString ) ) otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,54,FOLLOW_54_in_ruleFeatureHasMandatorySubfeature6486); 

                	newLeafNode(otherlv_0, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getFeatureHasMandatorySubfeatureKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3069:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3070:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3070:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3071:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6507);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasMandatorySubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleFeatureHasMandatorySubfeature6519); 

                	newLeafNode(otherlv_2, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleFeatureHasMandatorySubfeature6531); 

                	newLeafNode(otherlv_3, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3095:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3096:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3096:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3097:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6552);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasMandatorySubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleFeatureHasMandatorySubfeature6564); 

                	newLeafNode(otherlv_5, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3117:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3118:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3118:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3119:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleFeatureHasMandatorySubfeature6585);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFeatureHasMandatorySubfeatureRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3135:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==33) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3135:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleFeatureHasMandatorySubfeature6598); 

                        	newLeafNode(otherlv_7, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleFeatureHasMandatorySubfeature6610); 

                        	newLeafNode(otherlv_8, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3143:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3144:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3144:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3145:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getFeatureHasMandatorySubfeatureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6633);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3158:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==15) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3158:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleFeatureHasMandatorySubfeature6646); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3162:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3163:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3163:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3164:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getFeatureHasMandatorySubfeatureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6669);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleFeatureHasMandatorySubfeature6683); 

                        	newLeafNode(otherlv_12, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,34,FOLLOW_34_in_ruleFeatureHasMandatorySubfeature6697); 

                	newLeafNode(otherlv_13, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getParentKeyword_8());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3185:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3186:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3186:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3187:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasMandatorySubfeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getParentFeatureCrossReference_9_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6720);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_15=(Token)match(input,39,FOLLOW_39_in_ruleFeatureHasMandatorySubfeature6732); 

                	newLeafNode(otherlv_15, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getSubfeatureKeyword_10());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3204:1: ( ( ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3205:1: ( ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3205:1: ( ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3206:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFeatureHasMandatorySubfeatureRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFeatureHasMandatorySubfeatureAccess().getSubfeatureFeatureCrossReference_11_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6755);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleFeatureHasMandatorySubfeature6767); 

                	newLeafNode(otherlv_17, grammarAccess.getFeatureHasMandatorySubfeatureAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleFeatureHasMandatorySubfeature"


    // $ANTLR start "entryRuleMutualExclusive"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3231:1: entryRuleMutualExclusive returns [EObject current=null] : iv_ruleMutualExclusive= ruleMutualExclusive EOF ;
    public final EObject entryRuleMutualExclusive() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMutualExclusive = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3232:2: (iv_ruleMutualExclusive= ruleMutualExclusive EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3233:2: iv_ruleMutualExclusive= ruleMutualExclusive EOF
            {
             newCompositeNode(grammarAccess.getMutualExclusiveRule()); 
            pushFollow(FOLLOW_ruleMutualExclusive_in_entryRuleMutualExclusive6803);
            iv_ruleMutualExclusive=ruleMutualExclusive();

            state._fsp--;

             current =iv_ruleMutualExclusive; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMutualExclusive6813); 

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
    // $ANTLR end "entryRuleMutualExclusive"


    // $ANTLR start "ruleMutualExclusive"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3240:1: ruleMutualExclusive returns [EObject current=null] : (otherlv_0= 'MutualExclusive' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? otherlv_19= '}' ) ;
    public final EObject ruleMutualExclusive() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3243:28: ( (otherlv_0= 'MutualExclusive' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? otherlv_19= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3244:1: (otherlv_0= 'MutualExclusive' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? otherlv_19= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3244:1: (otherlv_0= 'MutualExclusive' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? otherlv_19= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3244:3: otherlv_0= 'MutualExclusive' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? otherlv_19= '}'
            {
            otherlv_0=(Token)match(input,55,FOLLOW_55_in_ruleMutualExclusive6850); 

                	newLeafNode(otherlv_0, grammarAccess.getMutualExclusiveAccess().getMutualExclusiveKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3248:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3249:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3249:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3250:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive6871);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMutualExclusiveRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleMutualExclusive6883); 

                	newLeafNode(otherlv_2, grammarAccess.getMutualExclusiveAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleMutualExclusive6895); 

                	newLeafNode(otherlv_3, grammarAccess.getMutualExclusiveAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3274:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3275:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3275:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3276:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive6916);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMutualExclusiveRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleMutualExclusive6928); 

                	newLeafNode(otherlv_5, grammarAccess.getMutualExclusiveAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3296:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3297:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3297:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3298:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleMutualExclusive6949);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMutualExclusiveRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3314:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==33) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3314:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleMutualExclusive6962); 

                        	newLeafNode(otherlv_7, grammarAccess.getMutualExclusiveAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleMutualExclusive6974); 

                        	newLeafNode(otherlv_8, grammarAccess.getMutualExclusiveAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3322:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3323:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3323:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3324:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getMutualExclusiveRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive6997);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3337:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==15) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3337:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleMutualExclusive7010); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getMutualExclusiveAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3341:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3342:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3342:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3343:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getMutualExclusiveRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive7033);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleMutualExclusive7047); 

                        	newLeafNode(otherlv_12, grammarAccess.getMutualExclusiveAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3360:3: (otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==56) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3360:5: otherlv_13= 'relatedFeatures' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')'
                    {
                    otherlv_13=(Token)match(input,56,FOLLOW_56_in_ruleMutualExclusive7062); 

                        	newLeafNode(otherlv_13, grammarAccess.getMutualExclusiveAccess().getRelatedFeaturesKeyword_8_0());
                        
                    otherlv_14=(Token)match(input,20,FOLLOW_20_in_ruleMutualExclusive7074); 

                        	newLeafNode(otherlv_14, grammarAccess.getMutualExclusiveAccess().getLeftParenthesisKeyword_8_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3368:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3369:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3369:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3370:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getMutualExclusiveRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getRelatedFeaturesFeatureCrossReference_8_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive7097);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3383:2: (otherlv_16= ',' ( ( ruleEString ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==15) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3383:4: otherlv_16= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_16=(Token)match(input,15,FOLLOW_15_in_ruleMutualExclusive7110); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getMutualExclusiveAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3387:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3388:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3388:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3389:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getMutualExclusiveRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getMutualExclusiveAccess().getRelatedFeaturesFeatureCrossReference_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleMutualExclusive7133);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,21,FOLLOW_21_in_ruleMutualExclusive7147); 

                        	newLeafNode(otherlv_18, grammarAccess.getMutualExclusiveAccess().getRightParenthesisKeyword_8_4());
                        

                    }
                    break;

            }

            otherlv_19=(Token)match(input,16,FOLLOW_16_in_ruleMutualExclusive7161); 

                	newLeafNode(otherlv_19, grammarAccess.getMutualExclusiveAccess().getRightCurlyBracketKeyword_9());
                

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
    // $ANTLR end "ruleMutualExclusive"


    // $ANTLR start "entryRuleCustomUndirectedRelationship"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3418:1: entryRuleCustomUndirectedRelationship returns [EObject current=null] : iv_ruleCustomUndirectedRelationship= ruleCustomUndirectedRelationship EOF ;
    public final EObject entryRuleCustomUndirectedRelationship() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCustomUndirectedRelationship = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3419:2: (iv_ruleCustomUndirectedRelationship= ruleCustomUndirectedRelationship EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3420:2: iv_ruleCustomUndirectedRelationship= ruleCustomUndirectedRelationship EOF
            {
             newCompositeNode(grammarAccess.getCustomUndirectedRelationshipRule()); 
            pushFollow(FOLLOW_ruleCustomUndirectedRelationship_in_entryRuleCustomUndirectedRelationship7197);
            iv_ruleCustomUndirectedRelationship=ruleCustomUndirectedRelationship();

            state._fsp--;

             current =iv_ruleCustomUndirectedRelationship; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCustomUndirectedRelationship7207); 

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
    // $ANTLR end "entryRuleCustomUndirectedRelationship"


    // $ANTLR start "ruleCustomUndirectedRelationship"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3427:1: ruleCustomUndirectedRelationship returns [EObject current=null] : (otherlv_0= 'CustomUndirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= '}' ) ;
    public final EObject ruleCustomUndirectedRelationship() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;

        AntlrDatatypeRuleToken lv_stereotype_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3430:28: ( (otherlv_0= 'CustomUndirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3431:1: (otherlv_0= 'CustomUndirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3431:1: (otherlv_0= 'CustomUndirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3431:3: otherlv_0= 'CustomUndirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= '}'
            {
            otherlv_0=(Token)match(input,57,FOLLOW_57_in_ruleCustomUndirectedRelationship7244); 

                	newLeafNode(otherlv_0, grammarAccess.getCustomUndirectedRelationshipAccess().getCustomUndirectedRelationshipKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3435:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3436:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3436:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3437:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7265);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomUndirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleCustomUndirectedRelationship7277); 

                	newLeafNode(otherlv_2, grammarAccess.getCustomUndirectedRelationshipAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleCustomUndirectedRelationship7289); 

                	newLeafNode(otherlv_3, grammarAccess.getCustomUndirectedRelationshipAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3461:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3462:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3462:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3463:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7310);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomUndirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleCustomUndirectedRelationship7322); 

                	newLeafNode(otherlv_5, grammarAccess.getCustomUndirectedRelationshipAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3483:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3484:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3484:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3485:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleCustomUndirectedRelationship7343);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomUndirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,58,FOLLOW_58_in_ruleCustomUndirectedRelationship7355); 

                	newLeafNode(otherlv_7, grammarAccess.getCustomUndirectedRelationshipAccess().getStereotypeKeyword_7());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3505:1: ( (lv_stereotype_8_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3506:1: (lv_stereotype_8_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3506:1: (lv_stereotype_8_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3507:3: lv_stereotype_8_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getStereotypeEStringParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7376);
            lv_stereotype_8_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomUndirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"stereotype",
                    		lv_stereotype_8_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3523:2: (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==33) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3523:4: otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')'
                    {
                    otherlv_9=(Token)match(input,33,FOLLOW_33_in_ruleCustomUndirectedRelationship7389); 

                        	newLeafNode(otherlv_9, grammarAccess.getCustomUndirectedRelationshipAccess().getExplanationsKeyword_9_0());
                        
                    otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleCustomUndirectedRelationship7401); 

                        	newLeafNode(otherlv_10, grammarAccess.getCustomUndirectedRelationshipAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3531:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3532:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3532:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3533:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCustomUndirectedRelationshipRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getExplanationsExplanationCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7424);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3546:2: (otherlv_12= ',' ( ( ruleEString ) ) )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==15) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3546:4: otherlv_12= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FOLLOW_15_in_ruleCustomUndirectedRelationship7437); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getCustomUndirectedRelationshipAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3550:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3551:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3551:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3552:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCustomUndirectedRelationshipRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getExplanationsExplanationCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7460);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleCustomUndirectedRelationship7474); 

                        	newLeafNode(otherlv_14, grammarAccess.getCustomUndirectedRelationshipAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3569:3: (otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==56) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3569:5: otherlv_15= 'relatedFeatures' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,56,FOLLOW_56_in_ruleCustomUndirectedRelationship7489); 

                        	newLeafNode(otherlv_15, grammarAccess.getCustomUndirectedRelationshipAccess().getRelatedFeaturesKeyword_10_0());
                        
                    otherlv_16=(Token)match(input,20,FOLLOW_20_in_ruleCustomUndirectedRelationship7501); 

                        	newLeafNode(otherlv_16, grammarAccess.getCustomUndirectedRelationshipAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3577:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3578:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3578:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3579:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCustomUndirectedRelationshipRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getRelatedFeaturesFeatureCrossReference_10_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7524);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3592:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==15) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3592:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FOLLOW_15_in_ruleCustomUndirectedRelationship7537); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getCustomUndirectedRelationshipAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3596:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3597:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3597:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3598:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCustomUndirectedRelationshipRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCustomUndirectedRelationshipAccess().getRelatedFeaturesFeatureCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7560);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,21,FOLLOW_21_in_ruleCustomUndirectedRelationship7574); 

                        	newLeafNode(otherlv_20, grammarAccess.getCustomUndirectedRelationshipAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            otherlv_21=(Token)match(input,16,FOLLOW_16_in_ruleCustomUndirectedRelationship7588); 

                	newLeafNode(otherlv_21, grammarAccess.getCustomUndirectedRelationshipAccess().getRightCurlyBracketKeyword_11());
                

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
    // $ANTLR end "ruleCustomUndirectedRelationship"


    // $ANTLR start "entryRuleRequires"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3627:1: entryRuleRequires returns [EObject current=null] : iv_ruleRequires= ruleRequires EOF ;
    public final EObject entryRuleRequires() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequires = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3628:2: (iv_ruleRequires= ruleRequires EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3629:2: iv_ruleRequires= ruleRequires EOF
            {
             newCompositeNode(grammarAccess.getRequiresRule()); 
            pushFollow(FOLLOW_ruleRequires_in_entryRuleRequires7624);
            iv_ruleRequires=ruleRequires();

            state._fsp--;

             current =iv_ruleRequires; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRequires7634); 

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
    // $ANTLR end "entryRuleRequires"


    // $ANTLR start "ruleRequires"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3636:1: ruleRequires returns [EObject current=null] : (otherlv_0= 'Requires' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' ) ;
    public final EObject ruleRequires() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3639:28: ( (otherlv_0= 'Requires' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3640:1: (otherlv_0= 'Requires' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3640:1: (otherlv_0= 'Requires' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3640:3: otherlv_0= 'Requires' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}'
            {
            otherlv_0=(Token)match(input,59,FOLLOW_59_in_ruleRequires7671); 

                	newLeafNode(otherlv_0, grammarAccess.getRequiresAccess().getRequiresKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3644:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3645:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3645:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3646:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getRequiresAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleRequires7692);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRequiresRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleRequires7704); 

                	newLeafNode(otherlv_2, grammarAccess.getRequiresAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleRequires7716); 

                	newLeafNode(otherlv_3, grammarAccess.getRequiresAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3670:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3671:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3671:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3672:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getRequiresAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleRequires7737);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRequiresRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleRequires7749); 

                	newLeafNode(otherlv_5, grammarAccess.getRequiresAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3692:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3693:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3693:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3694:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getRequiresAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleRequires7770);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRequiresRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3710:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==33) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3710:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleRequires7783); 

                        	newLeafNode(otherlv_7, grammarAccess.getRequiresAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleRequires7795); 

                        	newLeafNode(otherlv_8, grammarAccess.getRequiresAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3718:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3719:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3719:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3720:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getRequiresAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleRequires7818);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3733:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==15) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3733:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleRequires7831); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getRequiresAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3737:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3738:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3738:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3739:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getRequiresAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleRequires7854);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleRequires7868); 

                        	newLeafNode(otherlv_12, grammarAccess.getRequiresAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3756:3: (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==60) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3756:5: otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')'
                    {
                    otherlv_13=(Token)match(input,60,FOLLOW_60_in_ruleRequires7883); 

                        	newLeafNode(otherlv_13, grammarAccess.getRequiresAccess().getSourcesKeyword_8_0());
                        
                    otherlv_14=(Token)match(input,20,FOLLOW_20_in_ruleRequires7895); 

                        	newLeafNode(otherlv_14, grammarAccess.getRequiresAccess().getLeftParenthesisKeyword_8_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3764:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3765:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3765:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3766:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getRequiresAccess().getSourcesFeatureCrossReference_8_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleRequires7918);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3779:2: (otherlv_16= ',' ( ( ruleEString ) ) )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==15) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3779:4: otherlv_16= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_16=(Token)match(input,15,FOLLOW_15_in_ruleRequires7931); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getRequiresAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3783:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3784:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3784:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3785:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getRequiresAccess().getSourcesFeatureCrossReference_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleRequires7954);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,21,FOLLOW_21_in_ruleRequires7968); 

                        	newLeafNode(otherlv_18, grammarAccess.getRequiresAccess().getRightParenthesisKeyword_8_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3802:3: (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==61) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3802:5: otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')'
                    {
                    otherlv_19=(Token)match(input,61,FOLLOW_61_in_ruleRequires7983); 

                        	newLeafNode(otherlv_19, grammarAccess.getRequiresAccess().getTargetsKeyword_9_0());
                        
                    otherlv_20=(Token)match(input,20,FOLLOW_20_in_ruleRequires7995); 

                        	newLeafNode(otherlv_20, grammarAccess.getRequiresAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3810:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3811:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3811:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3812:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getRequiresAccess().getTargetsFeatureCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleRequires8018);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3825:2: (otherlv_22= ',' ( ( ruleEString ) ) )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==15) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3825:4: otherlv_22= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_22=(Token)match(input,15,FOLLOW_15_in_ruleRequires8031); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getRequiresAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3829:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3830:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3830:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3831:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getRequiresRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getRequiresAccess().getTargetsFeatureCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleRequires8054);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,21,FOLLOW_21_in_ruleRequires8068); 

                        	newLeafNode(otherlv_24, grammarAccess.getRequiresAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_25=(Token)match(input,16,FOLLOW_16_in_ruleRequires8082); 

                	newLeafNode(otherlv_25, grammarAccess.getRequiresAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleRequires"


    // $ANTLR start "entryRuleTemporalOrderingSequential"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3860:1: entryRuleTemporalOrderingSequential returns [EObject current=null] : iv_ruleTemporalOrderingSequential= ruleTemporalOrderingSequential EOF ;
    public final EObject entryRuleTemporalOrderingSequential() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemporalOrderingSequential = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3861:2: (iv_ruleTemporalOrderingSequential= ruleTemporalOrderingSequential EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3862:2: iv_ruleTemporalOrderingSequential= ruleTemporalOrderingSequential EOF
            {
             newCompositeNode(grammarAccess.getTemporalOrderingSequentialRule()); 
            pushFollow(FOLLOW_ruleTemporalOrderingSequential_in_entryRuleTemporalOrderingSequential8118);
            iv_ruleTemporalOrderingSequential=ruleTemporalOrderingSequential();

            state._fsp--;

             current =iv_ruleTemporalOrderingSequential; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTemporalOrderingSequential8128); 

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
    // $ANTLR end "entryRuleTemporalOrderingSequential"


    // $ANTLR start "ruleTemporalOrderingSequential"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3869:1: ruleTemporalOrderingSequential returns [EObject current=null] : (otherlv_0= 'TemporalOrderingSequential' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' ) ;
    public final EObject ruleTemporalOrderingSequential() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3872:28: ( (otherlv_0= 'TemporalOrderingSequential' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3873:1: (otherlv_0= 'TemporalOrderingSequential' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3873:1: (otherlv_0= 'TemporalOrderingSequential' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3873:3: otherlv_0= 'TemporalOrderingSequential' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )? (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )? otherlv_25= '}'
            {
            otherlv_0=(Token)match(input,62,FOLLOW_62_in_ruleTemporalOrderingSequential8165); 

                	newLeafNode(otherlv_0, grammarAccess.getTemporalOrderingSequentialAccess().getTemporalOrderingSequentialKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3877:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3878:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3878:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3879:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8186);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTemporalOrderingSequentialRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleTemporalOrderingSequential8198); 

                	newLeafNode(otherlv_2, grammarAccess.getTemporalOrderingSequentialAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleTemporalOrderingSequential8210); 

                	newLeafNode(otherlv_3, grammarAccess.getTemporalOrderingSequentialAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3903:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3904:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3904:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3905:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8231);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTemporalOrderingSequentialRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleTemporalOrderingSequential8243); 

                	newLeafNode(otherlv_5, grammarAccess.getTemporalOrderingSequentialAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3925:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3926:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3926:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3927:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleTemporalOrderingSequential8264);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTemporalOrderingSequentialRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3943:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==33) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3943:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleTemporalOrderingSequential8277); 

                        	newLeafNode(otherlv_7, grammarAccess.getTemporalOrderingSequentialAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleTemporalOrderingSequential8289); 

                        	newLeafNode(otherlv_8, grammarAccess.getTemporalOrderingSequentialAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3951:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3952:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3952:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3953:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8312);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3966:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==15) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3966:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleTemporalOrderingSequential8325); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getTemporalOrderingSequentialAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3970:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3971:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3971:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3972:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8348);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleTemporalOrderingSequential8362); 

                        	newLeafNode(otherlv_12, grammarAccess.getTemporalOrderingSequentialAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3989:3: (otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==60) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3989:5: otherlv_13= 'sources' otherlv_14= '(' ( ( ruleEString ) ) (otherlv_16= ',' ( ( ruleEString ) ) )* otherlv_18= ')'
                    {
                    otherlv_13=(Token)match(input,60,FOLLOW_60_in_ruleTemporalOrderingSequential8377); 

                        	newLeafNode(otherlv_13, grammarAccess.getTemporalOrderingSequentialAccess().getSourcesKeyword_8_0());
                        
                    otherlv_14=(Token)match(input,20,FOLLOW_20_in_ruleTemporalOrderingSequential8389); 

                        	newLeafNode(otherlv_14, grammarAccess.getTemporalOrderingSequentialAccess().getLeftParenthesisKeyword_8_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3997:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3998:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3998:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:3999:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getSourcesFeatureCrossReference_8_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8412);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4012:2: (otherlv_16= ',' ( ( ruleEString ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==15) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4012:4: otherlv_16= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_16=(Token)match(input,15,FOLLOW_15_in_ruleTemporalOrderingSequential8425); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getTemporalOrderingSequentialAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4016:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4017:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4017:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4018:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getSourcesFeatureCrossReference_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8448);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,21,FOLLOW_21_in_ruleTemporalOrderingSequential8462); 

                        	newLeafNode(otherlv_18, grammarAccess.getTemporalOrderingSequentialAccess().getRightParenthesisKeyword_8_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4035:3: (otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')' )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==61) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4035:5: otherlv_19= 'targets' otherlv_20= '(' ( ( ruleEString ) ) (otherlv_22= ',' ( ( ruleEString ) ) )* otherlv_24= ')'
                    {
                    otherlv_19=(Token)match(input,61,FOLLOW_61_in_ruleTemporalOrderingSequential8477); 

                        	newLeafNode(otherlv_19, grammarAccess.getTemporalOrderingSequentialAccess().getTargetsKeyword_9_0());
                        
                    otherlv_20=(Token)match(input,20,FOLLOW_20_in_ruleTemporalOrderingSequential8489); 

                        	newLeafNode(otherlv_20, grammarAccess.getTemporalOrderingSequentialAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4043:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4044:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4044:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4045:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getTargetsFeatureCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8512);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4058:2: (otherlv_22= ',' ( ( ruleEString ) ) )*
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==15) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4058:4: otherlv_22= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_22=(Token)match(input,15,FOLLOW_15_in_ruleTemporalOrderingSequential8525); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getTemporalOrderingSequentialAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4062:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4063:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4063:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4064:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getTemporalOrderingSequentialRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getTemporalOrderingSequentialAccess().getTargetsFeatureCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8548);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop76;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,21,FOLLOW_21_in_ruleTemporalOrderingSequential8562); 

                        	newLeafNode(otherlv_24, grammarAccess.getTemporalOrderingSequentialAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_25=(Token)match(input,16,FOLLOW_16_in_ruleTemporalOrderingSequential8576); 

                	newLeafNode(otherlv_25, grammarAccess.getTemporalOrderingSequentialAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleTemporalOrderingSequential"


    // $ANTLR start "entryRuleCustomDirectedRelationship"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4093:1: entryRuleCustomDirectedRelationship returns [EObject current=null] : iv_ruleCustomDirectedRelationship= ruleCustomDirectedRelationship EOF ;
    public final EObject entryRuleCustomDirectedRelationship() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCustomDirectedRelationship = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4094:2: (iv_ruleCustomDirectedRelationship= ruleCustomDirectedRelationship EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4095:2: iv_ruleCustomDirectedRelationship= ruleCustomDirectedRelationship EOF
            {
             newCompositeNode(grammarAccess.getCustomDirectedRelationshipRule()); 
            pushFollow(FOLLOW_ruleCustomDirectedRelationship_in_entryRuleCustomDirectedRelationship8612);
            iv_ruleCustomDirectedRelationship=ruleCustomDirectedRelationship();

            state._fsp--;

             current =iv_ruleCustomDirectedRelationship; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCustomDirectedRelationship8622); 

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
    // $ANTLR end "entryRuleCustomDirectedRelationship"


    // $ANTLR start "ruleCustomDirectedRelationship"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4102:1: ruleCustomDirectedRelationship returns [EObject current=null] : (otherlv_0= 'CustomDirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )? otherlv_27= '}' ) ;
    public final EObject ruleCustomDirectedRelationship() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;

        AntlrDatatypeRuleToken lv_stereotype_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4105:28: ( (otherlv_0= 'CustomDirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )? otherlv_27= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4106:1: (otherlv_0= 'CustomDirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )? otherlv_27= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4106:1: (otherlv_0= 'CustomDirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )? otherlv_27= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4106:3: otherlv_0= 'CustomDirectedRelationship' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) otherlv_7= 'stereotype' ( (lv_stereotype_8_0= ruleEString ) ) (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )? (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )? otherlv_27= '}'
            {
            otherlv_0=(Token)match(input,63,FOLLOW_63_in_ruleCustomDirectedRelationship8659); 

                	newLeafNode(otherlv_0, grammarAccess.getCustomDirectedRelationshipAccess().getCustomDirectedRelationshipKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4110:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4111:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4111:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4112:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8680);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomDirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleCustomDirectedRelationship8692); 

                	newLeafNode(otherlv_2, grammarAccess.getCustomDirectedRelationshipAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleCustomDirectedRelationship8704); 

                	newLeafNode(otherlv_3, grammarAccess.getCustomDirectedRelationshipAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4136:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4137:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4137:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4138:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8725);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomDirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleCustomDirectedRelationship8737); 

                	newLeafNode(otherlv_5, grammarAccess.getCustomDirectedRelationshipAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4158:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4159:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4159:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4160:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleCustomDirectedRelationship8758);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomDirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,58,FOLLOW_58_in_ruleCustomDirectedRelationship8770); 

                	newLeafNode(otherlv_7, grammarAccess.getCustomDirectedRelationshipAccess().getStereotypeKeyword_7());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4180:1: ( (lv_stereotype_8_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4181:1: (lv_stereotype_8_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4181:1: (lv_stereotype_8_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4182:3: lv_stereotype_8_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getStereotypeEStringParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8791);
            lv_stereotype_8_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCustomDirectedRelationshipRule());
            	        }
                   		set(
                   			current, 
                   			"stereotype",
                    		lv_stereotype_8_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4198:2: (otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')' )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==33) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4198:4: otherlv_9= 'explanations' otherlv_10= '(' ( ( ruleEString ) ) (otherlv_12= ',' ( ( ruleEString ) ) )* otherlv_14= ')'
                    {
                    otherlv_9=(Token)match(input,33,FOLLOW_33_in_ruleCustomDirectedRelationship8804); 

                        	newLeafNode(otherlv_9, grammarAccess.getCustomDirectedRelationshipAccess().getExplanationsKeyword_9_0());
                        
                    otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleCustomDirectedRelationship8816); 

                        	newLeafNode(otherlv_10, grammarAccess.getCustomDirectedRelationshipAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4206:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4207:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4207:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4208:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getExplanationsExplanationCrossReference_9_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8839);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4221:2: (otherlv_12= ',' ( ( ruleEString ) ) )*
                    loop78:
                    do {
                        int alt78=2;
                        int LA78_0 = input.LA(1);

                        if ( (LA78_0==15) ) {
                            alt78=1;
                        }


                        switch (alt78) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4221:4: otherlv_12= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FOLLOW_15_in_ruleCustomDirectedRelationship8852); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getCustomDirectedRelationshipAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4225:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4226:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4226:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4227:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getExplanationsExplanationCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8875);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop78;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleCustomDirectedRelationship8889); 

                        	newLeafNode(otherlv_14, grammarAccess.getCustomDirectedRelationshipAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4244:3: (otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==60) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4244:5: otherlv_15= 'sources' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,60,FOLLOW_60_in_ruleCustomDirectedRelationship8904); 

                        	newLeafNode(otherlv_15, grammarAccess.getCustomDirectedRelationshipAccess().getSourcesKeyword_10_0());
                        
                    otherlv_16=(Token)match(input,20,FOLLOW_20_in_ruleCustomDirectedRelationship8916); 

                        	newLeafNode(otherlv_16, grammarAccess.getCustomDirectedRelationshipAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4252:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4253:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4253:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4254:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getSourcesFeatureCrossReference_10_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8939);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4267:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==15) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4267:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FOLLOW_15_in_ruleCustomDirectedRelationship8952); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getCustomDirectedRelationshipAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4271:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4272:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4272:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4273:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getSourcesFeatureCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8975);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,21,FOLLOW_21_in_ruleCustomDirectedRelationship8989); 

                        	newLeafNode(otherlv_20, grammarAccess.getCustomDirectedRelationshipAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4290:3: (otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')' )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==61) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4290:5: otherlv_21= 'targets' otherlv_22= '(' ( ( ruleEString ) ) (otherlv_24= ',' ( ( ruleEString ) ) )* otherlv_26= ')'
                    {
                    otherlv_21=(Token)match(input,61,FOLLOW_61_in_ruleCustomDirectedRelationship9004); 

                        	newLeafNode(otherlv_21, grammarAccess.getCustomDirectedRelationshipAccess().getTargetsKeyword_11_0());
                        
                    otherlv_22=(Token)match(input,20,FOLLOW_20_in_ruleCustomDirectedRelationship9016); 

                        	newLeafNode(otherlv_22, grammarAccess.getCustomDirectedRelationshipAccess().getLeftParenthesisKeyword_11_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4298:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4299:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4299:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4300:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getTargetsFeatureCrossReference_11_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship9039);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4313:2: (otherlv_24= ',' ( ( ruleEString ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==15) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4313:4: otherlv_24= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_24=(Token)match(input,15,FOLLOW_15_in_ruleCustomDirectedRelationship9052); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getCustomDirectedRelationshipAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4317:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4318:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4318:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4319:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCustomDirectedRelationshipRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCustomDirectedRelationshipAccess().getTargetsFeatureCrossReference_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleCustomDirectedRelationship9075);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,21,FOLLOW_21_in_ruleCustomDirectedRelationship9089); 

                        	newLeafNode(otherlv_26, grammarAccess.getCustomDirectedRelationshipAccess().getRightParenthesisKeyword_11_4());
                        

                    }
                    break;

            }

            otherlv_27=(Token)match(input,16,FOLLOW_16_in_ruleCustomDirectedRelationship9103); 

                	newLeafNode(otherlv_27, grammarAccess.getCustomDirectedRelationshipAccess().getRightCurlyBracketKeyword_12());
                

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
    // $ANTLR end "ruleCustomDirectedRelationship"


    // $ANTLR start "entryRuleAutoComplete"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4348:1: entryRuleAutoComplete returns [EObject current=null] : iv_ruleAutoComplete= ruleAutoComplete EOF ;
    public final EObject entryRuleAutoComplete() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAutoComplete = null;


        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4349:2: (iv_ruleAutoComplete= ruleAutoComplete EOF )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4350:2: iv_ruleAutoComplete= ruleAutoComplete EOF
            {
             newCompositeNode(grammarAccess.getAutoCompleteRule()); 
            pushFollow(FOLLOW_ruleAutoComplete_in_entryRuleAutoComplete9139);
            iv_ruleAutoComplete=ruleAutoComplete();

            state._fsp--;

             current =iv_ruleAutoComplete; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAutoComplete9149); 

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
    // $ANTLR end "entryRuleAutoComplete"


    // $ANTLR start "ruleAutoComplete"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4357:1: ruleAutoComplete returns [EObject current=null] : (otherlv_0= 'AutoComplete' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= '}' ) ;
    public final EObject ruleAutoComplete() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        Enumerator lv_configurationSource_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4360:28: ( (otherlv_0= 'AutoComplete' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= '}' ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4361:1: (otherlv_0= 'AutoComplete' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= '}' )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4361:1: (otherlv_0= 'AutoComplete' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= '}' )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4361:3: otherlv_0= 'AutoComplete' ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' otherlv_3= 'name' ( (lv_name_4_0= ruleEString ) ) otherlv_5= 'configurationSource' ( (lv_configurationSource_6_0= ruleConfigurationSource ) ) (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_64_in_ruleAutoComplete9186); 

                	newLeafNode(otherlv_0, grammarAccess.getAutoCompleteAccess().getAutoCompleteKeyword_0());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4365:1: ( (lv_id_1_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4366:1: (lv_id_1_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4366:1: (lv_id_1_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4367:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getAutoCompleteAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleAutoComplete9207);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAutoCompleteRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleAutoComplete9219); 

                	newLeafNode(otherlv_2, grammarAccess.getAutoCompleteAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAutoComplete9231); 

                	newLeafNode(otherlv_3, grammarAccess.getAutoCompleteAccess().getNameKeyword_3());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4391:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4392:1: (lv_name_4_0= ruleEString )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4392:1: (lv_name_4_0= ruleEString )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4393:3: lv_name_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getAutoCompleteAccess().getNameEStringParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleAutoComplete9252);
            lv_name_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAutoCompleteRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,32,FOLLOW_32_in_ruleAutoComplete9264); 

                	newLeafNode(otherlv_5, grammarAccess.getAutoCompleteAccess().getConfigurationSourceKeyword_5());
                
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4413:1: ( (lv_configurationSource_6_0= ruleConfigurationSource ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4414:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4414:1: (lv_configurationSource_6_0= ruleConfigurationSource )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4415:3: lv_configurationSource_6_0= ruleConfigurationSource
            {
             
            	        newCompositeNode(grammarAccess.getAutoCompleteAccess().getConfigurationSourceConfigurationSourceEnumRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleConfigurationSource_in_ruleAutoComplete9285);
            lv_configurationSource_6_0=ruleConfigurationSource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAutoCompleteRule());
            	        }
                   		set(
                   			current, 
                   			"configurationSource",
                    		lv_configurationSource_6_0, 
                    		"ConfigurationSource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4431:2: (otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==33) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4431:4: otherlv_7= 'explanations' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_33_in_ruleAutoComplete9298); 

                        	newLeafNode(otherlv_7, grammarAccess.getAutoCompleteAccess().getExplanationsKeyword_7_0());
                        
                    otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleAutoComplete9310); 

                        	newLeafNode(otherlv_8, grammarAccess.getAutoCompleteAccess().getLeftParenthesisKeyword_7_1());
                        
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4439:1: ( ( ruleEString ) )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4440:1: ( ruleEString )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4440:1: ( ruleEString )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4441:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAutoCompleteRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getAutoCompleteAccess().getExplanationsExplanationCrossReference_7_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleAutoComplete9333);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4454:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==15) ) {
                            alt84=1;
                        }


                        switch (alt84) {
                    	case 1 :
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4454:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleAutoComplete9346); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getAutoCompleteAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4458:1: ( ( ruleEString ) )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4459:1: ( ruleEString )
                    	    {
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4459:1: ( ruleEString )
                    	    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4460:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAutoCompleteRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getAutoCompleteAccess().getExplanationsExplanationCrossReference_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleEString_in_ruleAutoComplete9369);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop84;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleAutoComplete9383); 

                        	newLeafNode(otherlv_12, grammarAccess.getAutoCompleteAccess().getRightParenthesisKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,16,FOLLOW_16_in_ruleAutoComplete9397); 

                	newLeafNode(otherlv_13, grammarAccess.getAutoCompleteAccess().getRightCurlyBracketKeyword_8());
                

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
    // $ANTLR end "ruleAutoComplete"


    // $ANTLR start "ruleConfigurationSource"
    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4489:1: ruleConfigurationSource returns [Enumerator current=null] : ( (enumLiteral_0= 'MODEL' ) | (enumLiteral_1= 'MODELCONSEQUENCE' ) | (enumLiteral_2= 'USER' ) | (enumLiteral_3= 'USERCONSEQUENCE' ) ) ;
    public final Enumerator ruleConfigurationSource() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4491:28: ( ( (enumLiteral_0= 'MODEL' ) | (enumLiteral_1= 'MODELCONSEQUENCE' ) | (enumLiteral_2= 'USER' ) | (enumLiteral_3= 'USERCONSEQUENCE' ) ) )
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4492:1: ( (enumLiteral_0= 'MODEL' ) | (enumLiteral_1= 'MODELCONSEQUENCE' ) | (enumLiteral_2= 'USER' ) | (enumLiteral_3= 'USERCONSEQUENCE' ) )
            {
            // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4492:1: ( (enumLiteral_0= 'MODEL' ) | (enumLiteral_1= 'MODELCONSEQUENCE' ) | (enumLiteral_2= 'USER' ) | (enumLiteral_3= 'USERCONSEQUENCE' ) )
            int alt86=4;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt86=1;
                }
                break;
            case 66:
                {
                alt86=2;
                }
                break;
            case 67:
                {
                alt86=3;
                }
                break;
            case 68:
                {
                alt86=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4492:2: (enumLiteral_0= 'MODEL' )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4492:2: (enumLiteral_0= 'MODEL' )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4492:4: enumLiteral_0= 'MODEL'
                    {
                    enumLiteral_0=(Token)match(input,65,FOLLOW_65_in_ruleConfigurationSource9447); 

                            current = grammarAccess.getConfigurationSourceAccess().getMODELEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getConfigurationSourceAccess().getMODELEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4498:6: (enumLiteral_1= 'MODELCONSEQUENCE' )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4498:6: (enumLiteral_1= 'MODELCONSEQUENCE' )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4498:8: enumLiteral_1= 'MODELCONSEQUENCE'
                    {
                    enumLiteral_1=(Token)match(input,66,FOLLOW_66_in_ruleConfigurationSource9464); 

                            current = grammarAccess.getConfigurationSourceAccess().getMODELCONSEQUENCEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getConfigurationSourceAccess().getMODELCONSEQUENCEEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4504:6: (enumLiteral_2= 'USER' )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4504:6: (enumLiteral_2= 'USER' )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4504:8: enumLiteral_2= 'USER'
                    {
                    enumLiteral_2=(Token)match(input,67,FOLLOW_67_in_ruleConfigurationSource9481); 

                            current = grammarAccess.getConfigurationSourceAccess().getUSEREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getConfigurationSourceAccess().getUSEREnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4510:6: (enumLiteral_3= 'USERCONSEQUENCE' )
                    {
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4510:6: (enumLiteral_3= 'USERCONSEQUENCE' )
                    // ../org.xtext.example.fmlero/src-gen/org/xtext/example/mydsl/parser/antlr/internal/InternalFmlero.g:4510:8: enumLiteral_3= 'USERCONSEQUENCE'
                    {
                    enumLiteral_3=(Token)match(input,68,FOLLOW_68_in_ruleConfigurationSource9498); 

                            current = grammarAccess.getConfigurationSourceAccess().getUSERCONSEQUENCEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getConfigurationSourceAccess().getUSERCONSEQUENCEEnumLiteralDeclaration_3()); 
                        

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
    // $ANTLR end "ruleConfigurationSource"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleFeatureModel_in_entryRuleFeatureModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleFeatureModel122 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureModel143 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureModel155 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureModel167 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureModel188 = new BitSet(new long[]{0x0000000000034000L});
    public static final BitSet FOLLOW_14_in_ruleFeatureModel201 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureModel213 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ruleFeature_in_ruleFeatureModel234 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureModel247 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ruleFeature_in_ruleFeatureModel268 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureModel282 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_ruleFeatureModel297 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureModel309 = new BitSet(new long[]{0xCAE95D5080000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleFeatureModelPrimitive_in_ruleFeatureModel330 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureModel343 = new BitSet(new long[]{0xCAE95D5080000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleFeatureModelPrimitive_in_ruleFeatureModel364 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureModel378 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureModel392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureModelPrimitive_in_entryRuleFeatureModelPrimitive428 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureModelPrimitive438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasParent_in_ruleFeatureModelPrimitive485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasChild_in_ruleFeatureModelPrimitive512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasMax_in_ruleFeatureModelPrimitive539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasMin_in_ruleFeatureModelPrimitive566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasSubfeature_Impl_in_ruleFeatureModelPrimitive593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectedFeature_in_ruleFeatureModelPrimitive620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEliminatedFeature_in_ruleFeatureModelPrimitive647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureIsRoot_in_ruleFeatureModelPrimitive674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlternativeGroup_in_ruleFeatureModelPrimitive701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrGroup_in_ruleFeatureModelPrimitive728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasOptionalSubfeature_in_ruleFeatureModelPrimitive755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasMandatorySubfeature_in_ruleFeatureModelPrimitive782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMutualExclusive_in_ruleFeatureModelPrimitive809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRequires_in_ruleFeatureModelPrimitive836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAutoComplete_in_ruleFeatureModelPrimitive863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemporalOrderingSequential_in_ruleFeatureModelPrimitive890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCustomDirectedRelationship_in_ruleFeatureModelPrimitive917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCustomUndirectedRelationship_in_ruleFeatureModelPrimitive944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_entryRuleEString988 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEString999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEString1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEString1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeature_in_entryRuleFeature1110 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeature1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleFeature1157 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1178 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeature1190 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeature1202 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1223 = new BitSet(new long[]{0x000000007FC90000L});
    public static final BitSet FOLLOW_19_in_ruleFeature1236 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1248 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1271 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1284 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1307 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1321 = new BitSet(new long[]{0x000000007FC10000L});
    public static final BitSet FOLLOW_22_in_ruleFeature1336 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1348 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1371 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1384 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1407 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1421 = new BitSet(new long[]{0x000000007F810000L});
    public static final BitSet FOLLOW_23_in_ruleFeature1436 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1459 = new BitSet(new long[]{0x000000007F010000L});
    public static final BitSet FOLLOW_24_in_ruleFeature1474 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1486 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1509 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1522 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1545 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1559 = new BitSet(new long[]{0x000000007E010000L});
    public static final BitSet FOLLOW_25_in_ruleFeature1574 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1586 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1609 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1622 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1645 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1659 = new BitSet(new long[]{0x000000007C010000L});
    public static final BitSet FOLLOW_26_in_ruleFeature1674 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1686 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1709 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1722 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1745 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1759 = new BitSet(new long[]{0x0000000078010000L});
    public static final BitSet FOLLOW_27_in_ruleFeature1774 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1786 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1809 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1822 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1845 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1859 = new BitSet(new long[]{0x0000000070010000L});
    public static final BitSet FOLLOW_28_in_ruleFeature1874 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1886 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1909 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature1922 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature1945 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature1959 = new BitSet(new long[]{0x0000000060010000L});
    public static final BitSet FOLLOW_29_in_ruleFeature1974 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeature1986 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature2009 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeature2022 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature2045 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeature2059 = new BitSet(new long[]{0x0000000040010000L});
    public static final BitSet FOLLOW_30_in_ruleFeature2074 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeature2097 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeature2111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasParent_in_entryRuleGroupHasParent2147 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupHasParent2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleGroupHasParent2194 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2215 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGroupHasParent2227 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGroupHasParent2239 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2260 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleGroupHasParent2272 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleGroupHasParent2293 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleGroupHasParent2306 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleGroupHasParent2318 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2341 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleGroupHasParent2354 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2377 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleGroupHasParent2391 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleGroupHasParent2405 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2428 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleGroupHasParent2440 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasParent2463 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGroupHasParent2475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasChild_in_entryRuleGroupHasChild2511 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupHasChild2521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleGroupHasChild2558 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2579 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGroupHasChild2591 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGroupHasChild2603 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2624 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleGroupHasChild2636 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleGroupHasChild2657 = new BitSet(new long[]{0x0000002200000000L});
    public static final BitSet FOLLOW_33_in_ruleGroupHasChild2670 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleGroupHasChild2682 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2705 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleGroupHasChild2718 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2741 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleGroupHasChild2755 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleGroupHasChild2769 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2792 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleGroupHasChild2804 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasChild2827 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGroupHasChild2839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasSubfeature_Impl_in_entryRuleFeatureHasSubfeature_Impl2875 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureHasSubfeature_Impl2885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleFeatureHasSubfeature_Impl2922 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl2943 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureHasSubfeature_Impl2955 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureHasSubfeature_Impl2967 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl2988 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleFeatureHasSubfeature_Impl3000 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleFeatureHasSubfeature_Impl3021 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleFeatureHasSubfeature_Impl3034 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeatureHasSubfeature_Impl3046 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3069 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureHasSubfeature_Impl3082 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3105 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeatureHasSubfeature_Impl3119 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleFeatureHasSubfeature_Impl3133 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3156 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleFeatureHasSubfeature_Impl3168 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasSubfeature_Impl3191 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureHasSubfeature_Impl3203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectedFeature_in_entryRuleSelectedFeature3239 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectedFeature3249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleSelectedFeature3286 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSelectedFeature3307 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleSelectedFeature3319 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleSelectedFeature3331 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSelectedFeature3352 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleSelectedFeature3364 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleSelectedFeature3385 = new BitSet(new long[]{0x0000020200000000L});
    public static final BitSet FOLLOW_33_in_ruleSelectedFeature3398 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSelectedFeature3410 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSelectedFeature3433 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleSelectedFeature3446 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSelectedFeature3469 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleSelectedFeature3483 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleSelectedFeature3497 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSelectedFeature3520 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleSelectedFeature3532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEliminatedFeature_in_entryRuleEliminatedFeature3568 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEliminatedFeature3578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleEliminatedFeature3615 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleEliminatedFeature3636 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleEliminatedFeature3648 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEliminatedFeature3660 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleEliminatedFeature3681 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleEliminatedFeature3693 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleEliminatedFeature3714 = new BitSet(new long[]{0x0000020200000000L});
    public static final BitSet FOLLOW_33_in_ruleEliminatedFeature3727 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleEliminatedFeature3739 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleEliminatedFeature3762 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleEliminatedFeature3775 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleEliminatedFeature3798 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleEliminatedFeature3812 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleEliminatedFeature3826 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleEliminatedFeature3849 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleEliminatedFeature3861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureIsRoot_in_entryRuleFeatureIsRoot3897 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureIsRoot3907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleFeatureIsRoot3944 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureIsRoot3965 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureIsRoot3977 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureIsRoot3989 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureIsRoot4010 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleFeatureIsRoot4022 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleFeatureIsRoot4043 = new BitSet(new long[]{0x0000020200000000L});
    public static final BitSet FOLLOW_33_in_ruleFeatureIsRoot4056 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeatureIsRoot4068 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureIsRoot4091 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureIsRoot4104 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureIsRoot4127 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeatureIsRoot4141 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleFeatureIsRoot4155 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureIsRoot4178 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureIsRoot4190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasMax_in_entryRuleGroupHasMax4228 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupHasMax4238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleGroupHasMax4275 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMax4296 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGroupHasMax4308 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGroupHasMax4320 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMax4341 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleGroupHasMax4353 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleGroupHasMax4374 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleGroupHasMax4386 = new BitSet(new long[]{0x0010000000000040L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleGroupHasMax4407 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_33_in_ruleGroupHasMax4420 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleGroupHasMax4432 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMax4455 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleGroupHasMax4468 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMax4491 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleGroupHasMax4505 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleGroupHasMax4519 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMax4542 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGroupHasMax4554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupHasMin_in_entryRuleGroupHasMin4590 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupHasMin4600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleGroupHasMin4637 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMin4658 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGroupHasMin4670 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleGroupHasMin4682 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMin4703 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleGroupHasMin4715 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleGroupHasMin4736 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleGroupHasMin4748 = new BitSet(new long[]{0x0010000000000040L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleGroupHasMin4769 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_33_in_ruleGroupHasMin4782 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleGroupHasMin4794 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMin4817 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleGroupHasMin4830 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMin4853 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleGroupHasMin4867 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleGroupHasMin4881 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleGroupHasMin4904 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleGroupHasMin4916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlternativeGroup_in_entryRuleAlternativeGroup4952 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAlternativeGroup4962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleAlternativeGroup4999 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5020 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAlternativeGroup5032 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAlternativeGroup5044 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5065 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleAlternativeGroup5077 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleAlternativeGroup5098 = new BitSet(new long[]{0x0000000200080000L});
    public static final BitSet FOLLOW_33_in_ruleAlternativeGroup5111 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleAlternativeGroup5123 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5146 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleAlternativeGroup5159 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5182 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleAlternativeGroup5196 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleAlternativeGroup5210 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5233 = new BitSet(new long[]{0x0006000000410000L});
    public static final BitSet FOLLOW_22_in_ruleAlternativeGroup5246 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleAlternativeGroup5258 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5281 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleAlternativeGroup5294 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5317 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleAlternativeGroup5331 = new BitSet(new long[]{0x0006000000010000L});
    public static final BitSet FOLLOW_49_in_ruleAlternativeGroup5346 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5369 = new BitSet(new long[]{0x0004000000010000L});
    public static final BitSet FOLLOW_50_in_ruleAlternativeGroup5384 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAlternativeGroup5407 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAlternativeGroup5421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrGroup_in_entryRuleOrGroup5457 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrGroup5467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleOrGroup5504 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5525 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleOrGroup5537 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleOrGroup5549 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5570 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleOrGroup5582 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleOrGroup5603 = new BitSet(new long[]{0x0000000200080000L});
    public static final BitSet FOLLOW_33_in_ruleOrGroup5616 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleOrGroup5628 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5651 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleOrGroup5664 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5687 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleOrGroup5701 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleOrGroup5715 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5738 = new BitSet(new long[]{0x0006000000410000L});
    public static final BitSet FOLLOW_22_in_ruleOrGroup5751 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleOrGroup5763 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5786 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleOrGroup5799 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5822 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleOrGroup5836 = new BitSet(new long[]{0x0006000000010000L});
    public static final BitSet FOLLOW_49_in_ruleOrGroup5851 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5874 = new BitSet(new long[]{0x0004000000010000L});
    public static final BitSet FOLLOW_50_in_ruleOrGroup5889 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOrGroup5912 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleOrGroup5926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt5963 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEInt5974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleEInt6013 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleEInt6030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasOptionalSubfeature_in_entryRuleFeatureHasOptionalSubfeature6075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureHasOptionalSubfeature6085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleFeatureHasOptionalSubfeature6122 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6143 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureHasOptionalSubfeature6155 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureHasOptionalSubfeature6167 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6188 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleFeatureHasOptionalSubfeature6200 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleFeatureHasOptionalSubfeature6221 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleFeatureHasOptionalSubfeature6234 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeatureHasOptionalSubfeature6246 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6269 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureHasOptionalSubfeature6282 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6305 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeatureHasOptionalSubfeature6319 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleFeatureHasOptionalSubfeature6333 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6356 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleFeatureHasOptionalSubfeature6368 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasOptionalSubfeature6391 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureHasOptionalSubfeature6403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureHasMandatorySubfeature_in_entryRuleFeatureHasMandatorySubfeature6439 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureHasMandatorySubfeature6449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleFeatureHasMandatorySubfeature6486 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6507 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureHasMandatorySubfeature6519 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureHasMandatorySubfeature6531 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6552 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleFeatureHasMandatorySubfeature6564 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleFeatureHasMandatorySubfeature6585 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleFeatureHasMandatorySubfeature6598 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleFeatureHasMandatorySubfeature6610 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6633 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleFeatureHasMandatorySubfeature6646 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6669 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleFeatureHasMandatorySubfeature6683 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleFeatureHasMandatorySubfeature6697 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6720 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleFeatureHasMandatorySubfeature6732 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleFeatureHasMandatorySubfeature6755 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleFeatureHasMandatorySubfeature6767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMutualExclusive_in_entryRuleMutualExclusive6803 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMutualExclusive6813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleMutualExclusive6850 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive6871 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMutualExclusive6883 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMutualExclusive6895 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive6916 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleMutualExclusive6928 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleMutualExclusive6949 = new BitSet(new long[]{0x0100000200010000L});
    public static final BitSet FOLLOW_33_in_ruleMutualExclusive6962 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleMutualExclusive6974 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive6997 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleMutualExclusive7010 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive7033 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleMutualExclusive7047 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_56_in_ruleMutualExclusive7062 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleMutualExclusive7074 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive7097 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleMutualExclusive7110 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMutualExclusive7133 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleMutualExclusive7147 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleMutualExclusive7161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCustomUndirectedRelationship_in_entryRuleCustomUndirectedRelationship7197 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCustomUndirectedRelationship7207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleCustomUndirectedRelationship7244 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7265 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCustomUndirectedRelationship7277 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCustomUndirectedRelationship7289 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7310 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleCustomUndirectedRelationship7322 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleCustomUndirectedRelationship7343 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleCustomUndirectedRelationship7355 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7376 = new BitSet(new long[]{0x0100000200010000L});
    public static final BitSet FOLLOW_33_in_ruleCustomUndirectedRelationship7389 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCustomUndirectedRelationship7401 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7424 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleCustomUndirectedRelationship7437 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7460 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleCustomUndirectedRelationship7474 = new BitSet(new long[]{0x0100000000010000L});
    public static final BitSet FOLLOW_56_in_ruleCustomUndirectedRelationship7489 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCustomUndirectedRelationship7501 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7524 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleCustomUndirectedRelationship7537 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomUndirectedRelationship7560 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleCustomUndirectedRelationship7574 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleCustomUndirectedRelationship7588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRequires_in_entryRuleRequires7624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRequires7634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleRequires7671 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7692 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleRequires7704 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleRequires7716 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7737 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleRequires7749 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleRequires7770 = new BitSet(new long[]{0x3000000200010000L});
    public static final BitSet FOLLOW_33_in_ruleRequires7783 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleRequires7795 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7818 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleRequires7831 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7854 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleRequires7868 = new BitSet(new long[]{0x3000000000010000L});
    public static final BitSet FOLLOW_60_in_ruleRequires7883 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleRequires7895 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7918 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleRequires7931 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires7954 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleRequires7968 = new BitSet(new long[]{0x2000000000010000L});
    public static final BitSet FOLLOW_61_in_ruleRequires7983 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleRequires7995 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires8018 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleRequires8031 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleRequires8054 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleRequires8068 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleRequires8082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemporalOrderingSequential_in_entryRuleTemporalOrderingSequential8118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTemporalOrderingSequential8128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleTemporalOrderingSequential8165 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8186 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleTemporalOrderingSequential8198 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleTemporalOrderingSequential8210 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8231 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleTemporalOrderingSequential8243 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleTemporalOrderingSequential8264 = new BitSet(new long[]{0x3000000200010000L});
    public static final BitSet FOLLOW_33_in_ruleTemporalOrderingSequential8277 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTemporalOrderingSequential8289 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8312 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleTemporalOrderingSequential8325 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8348 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleTemporalOrderingSequential8362 = new BitSet(new long[]{0x3000000000010000L});
    public static final BitSet FOLLOW_60_in_ruleTemporalOrderingSequential8377 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTemporalOrderingSequential8389 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8412 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleTemporalOrderingSequential8425 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8448 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleTemporalOrderingSequential8462 = new BitSet(new long[]{0x2000000000010000L});
    public static final BitSet FOLLOW_61_in_ruleTemporalOrderingSequential8477 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTemporalOrderingSequential8489 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8512 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleTemporalOrderingSequential8525 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleTemporalOrderingSequential8548 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleTemporalOrderingSequential8562 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTemporalOrderingSequential8576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCustomDirectedRelationship_in_entryRuleCustomDirectedRelationship8612 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCustomDirectedRelationship8622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleCustomDirectedRelationship8659 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8680 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCustomDirectedRelationship8692 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCustomDirectedRelationship8704 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8725 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleCustomDirectedRelationship8737 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleCustomDirectedRelationship8758 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleCustomDirectedRelationship8770 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8791 = new BitSet(new long[]{0x3000000200010000L});
    public static final BitSet FOLLOW_33_in_ruleCustomDirectedRelationship8804 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCustomDirectedRelationship8816 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8839 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleCustomDirectedRelationship8852 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8875 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleCustomDirectedRelationship8889 = new BitSet(new long[]{0x3000000000010000L});
    public static final BitSet FOLLOW_60_in_ruleCustomDirectedRelationship8904 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCustomDirectedRelationship8916 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8939 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleCustomDirectedRelationship8952 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship8975 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleCustomDirectedRelationship8989 = new BitSet(new long[]{0x2000000000010000L});
    public static final BitSet FOLLOW_61_in_ruleCustomDirectedRelationship9004 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCustomDirectedRelationship9016 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship9039 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleCustomDirectedRelationship9052 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleCustomDirectedRelationship9075 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleCustomDirectedRelationship9089 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleCustomDirectedRelationship9103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAutoComplete_in_entryRuleAutoComplete9139 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAutoComplete9149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleAutoComplete9186 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAutoComplete9207 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAutoComplete9219 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAutoComplete9231 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAutoComplete9252 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleAutoComplete9264 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_ruleConfigurationSource_in_ruleAutoComplete9285 = new BitSet(new long[]{0x0000000200010000L});
    public static final BitSet FOLLOW_33_in_ruleAutoComplete9298 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleAutoComplete9310 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAutoComplete9333 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_15_in_ruleAutoComplete9346 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEString_in_ruleAutoComplete9369 = new BitSet(new long[]{0x0000000000208000L});
    public static final BitSet FOLLOW_21_in_ruleAutoComplete9383 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAutoComplete9397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleConfigurationSource9447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleConfigurationSource9464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleConfigurationSource9481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleConfigurationSource9498 = new BitSet(new long[]{0x0000000000000002L});

}