package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.xtext.example.mydsl.services.FMLGrammarAccess;

@SuppressWarnings("all")
public class FMLSyntacticSequencer extends AbstractSyntacticSequencer {

	protected FMLGrammarAccess grammarAccess;
	protected AbstractElementAlias match_CopyVariable_CopyKeyword_0_0_or_CpKeyword_0_1;
	protected AbstractElementAlias match_FMLSave_SaveKeyword_0_0_or_SerializeKeyword_0_1;
	protected AbstractElementAlias match_FeatureModel_FMKeyword_0_0_or_FeaturemodelKeyword_0_1;
	protected AbstractElementAlias match_FullMandatorys_FalseOptionalsKeyword_0_1_or_FullMandatorysKeyword_0_0;
	protected AbstractElementAlias match_LoadGeneric___LEFT_BRACKETTerminalRuleCall_2_0_RIGHT_BRACKETTerminalRuleCall_2_2__q;
	protected AbstractElementAlias match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_a;
	protected AbstractElementAlias match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_p;
	protected AbstractElementAlias match_RemoveVariable_RemoveVariableKeyword_0_0_or_RmKeyword_0_1;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (FMLGrammarAccess) access;
		match_CopyVariable_CopyKeyword_0_0_or_CpKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getCopyVariableAccess().getCopyKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getCopyVariableAccess().getCpKeyword_0_1()));
		match_FMLSave_SaveKeyword_0_0_or_SerializeKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFMLSaveAccess().getSaveKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getFMLSaveAccess().getSerializeKeyword_0_1()));
		match_FeatureModel_FMKeyword_0_0_or_FeaturemodelKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFeatureModelAccess().getFMKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getFeatureModelAccess().getFeaturemodelKeyword_0_1()));
		match_FullMandatorys_FalseOptionalsKeyword_0_1_or_FullMandatorysKeyword_0_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFullMandatorysAccess().getFalseOptionalsKeyword_0_1()), new TokenAlias(false, false, grammarAccess.getFullMandatorysAccess().getFullMandatorysKeyword_0_0()));
		match_LoadGeneric___LEFT_BRACKETTerminalRuleCall_2_0_RIGHT_BRACKETTerminalRuleCall_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getLoadGenericAccess().getLEFT_BRACKETTerminalRuleCall_2_0()), new TokenAlias(false, false, grammarAccess.getLoadGenericAccess().getRIGHT_BRACKETTerminalRuleCall_2_2()));
		match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_a = new TokenAlias(true, true, grammarAccess.getPrimary_exprAccess().getLEFT_PARENTerminalRuleCall_1_0());
		match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_p = new TokenAlias(true, false, grammarAccess.getPrimary_exprAccess().getLEFT_PARENTerminalRuleCall_1_0());
		match_RemoveVariable_RemoveVariableKeyword_0_0_or_RmKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getRemoveVariableAccess().getRemoveVariableKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getRemoveVariableAccess().getRmKeyword_0_1()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getB_ANDRule())
			return getB_ANDToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getB_BIMPLYRule())
			return getB_BIMPLYToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getB_IMPLYRule())
			return getB_IMPLYToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getB_NOTRule())
			return getB_NOTToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getB_ORRule())
			return getB_ORToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getCOMMARule())
			return getCOMMAToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getLEFT_BRACKETRule())
			return getLEFT_BRACKETToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getLEFT_HOOKRule())
			return getLEFT_HOOKToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getLEFT_PARENRule())
			return getLEFT_PARENToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getMETA_ATTRIBUTE_SYMBOLRule())
			return getMETA_ATTRIBUTE_SYMBOLToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getPLUSRule())
			return getPLUSToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getRIGHT_BRACKETRule())
			return getRIGHT_BRACKETToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getRIGHT_HOOKRule())
			return getRIGHT_HOOKToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getRIGHT_PARENRule())
			return getRIGHT_PARENToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal B_AND :    '&' | 'and' ;
	 */
	protected String getB_ANDToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "&";
	}
	
	/**
	 * terminal B_BIMPLY :     '<->' | 'biimplies' ;
	 */
	protected String getB_BIMPLYToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "<->";
	}
	
	/**
	 * terminal B_IMPLY :   '->' | 'implies' | 'requires' ;
	 */
	protected String getB_IMPLYToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "->";
	}
	
	/**
	 * terminal B_NOT :    '!' | '~' ;
	 */
	protected String getB_NOTToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "!";
	}
	
	/**
	 * terminal B_OR :     '|' | 'or'  ;
	 */
	protected String getB_ORToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "|";
	}
	
	/**
	 * terminal COMMA : ',' ;
	 */
	protected String getCOMMAToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return ",";
	}
	
	/**
	 * terminal LEFT_BRACKET :     '{' ;
	 */
	protected String getLEFT_BRACKETToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "{";
	}
	
	/**
	 * terminal LEFT_HOOK : '[' ;
	 */
	protected String getLEFT_HOOKToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "[";
	}
	
	/**
	 * terminal LEFT_PAREN : '(' ;
	 */
	protected String getLEFT_PARENToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "(";
	}
	
	/**
	 * terminal META_ATTRIBUTE_SYMBOL : '@' ;
	 */
	protected String getMETA_ATTRIBUTE_SYMBOLToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "@";
	}
	
	/**
	 * terminal PLUS     :     '+' ;
	 */
	protected String getPLUSToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "+";
	}
	
	/**
	 * terminal RIGHT_BRACKET : '}' ;
	 */
	protected String getRIGHT_BRACKETToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "}";
	}
	
	/**
	 * terminal RIGHT_HOOK : ']' ;
	 */
	protected String getRIGHT_HOOKToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "]";
	}
	
	/**
	 * terminal RIGHT_PAREN : ')' ;
	 */
	protected String getRIGHT_PARENToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return ")";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_CopyVariable_CopyKeyword_0_0_or_CpKeyword_0_1.equals(syntax))
				emit_CopyVariable_CopyKeyword_0_0_or_CpKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_FMLSave_SaveKeyword_0_0_or_SerializeKeyword_0_1.equals(syntax))
				emit_FMLSave_SaveKeyword_0_0_or_SerializeKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_FeatureModel_FMKeyword_0_0_or_FeaturemodelKeyword_0_1.equals(syntax))
				emit_FeatureModel_FMKeyword_0_0_or_FeaturemodelKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_FullMandatorys_FalseOptionalsKeyword_0_1_or_FullMandatorysKeyword_0_0.equals(syntax))
				emit_FullMandatorys_FalseOptionalsKeyword_0_1_or_FullMandatorysKeyword_0_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LoadGeneric___LEFT_BRACKETTerminalRuleCall_2_0_RIGHT_BRACKETTerminalRuleCall_2_2__q.equals(syntax))
				emit_LoadGeneric___LEFT_BRACKETTerminalRuleCall_2_0_RIGHT_BRACKETTerminalRuleCall_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_a.equals(syntax))
				emit_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_p.equals(syntax))
				emit_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_RemoveVariable_RemoveVariableKeyword_0_0_or_RmKeyword_0_1.equals(syntax))
				emit_RemoveVariable_RemoveVariableKeyword_0_0_or_RmKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     'cp' | 'copy'
	 */
	protected void emit_CopyVariable_CopyKeyword_0_0_or_CpKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'serialize' | 'save'
	 */
	protected void emit_FMLSave_SaveKeyword_0_0_or_SerializeKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'featuremodel' | 'FM'
	 */
	protected void emit_FeatureModel_FMKeyword_0_0_or_FeaturemodelKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'fullMandatorys' | 'falseOptionals'
	 */
	protected void emit_FullMandatorys_FalseOptionalsKeyword_0_1_or_FullMandatorysKeyword_0_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (LEFT_BRACKET RIGHT_BRACKET)?
	 */
	protected void emit_LoadGeneric___LEFT_BRACKETTerminalRuleCall_2_0_RIGHT_BRACKETTerminalRuleCall_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     LEFT_PAREN*
	 */
	protected void emit_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     LEFT_PAREN+
	 */
	protected void emit_Primary_expr_LEFT_PARENTerminalRuleCall_1_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'rm' | 'removeVariable'
	 */
	protected void emit_RemoveVariable_RemoveVariableKeyword_0_0_or_RmKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
