package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.xtext.example.mydsl.fML.AddConstraint;
import org.xtext.example.mydsl.fML.Aggregate;
import org.xtext.example.mydsl.fML.AggregateMerge;
import org.xtext.example.mydsl.fML.AlternativeEdit;
import org.xtext.example.mydsl.fML.AnalysisOperation;
import org.xtext.example.mydsl.fML.AncestorFeature;
import org.xtext.example.mydsl.fML.And_expr;
import org.xtext.example.mydsl.fML.AsFM;
import org.xtext.example.mydsl.fML.Assertion;
import org.xtext.example.mydsl.fML.AtomicConstraintExpr;
import org.xtext.example.mydsl.fML.AutoConfiguration;
import org.xtext.example.mydsl.fML.Biimpl_expr;
import org.xtext.example.mydsl.fML.BoolOperation;
import org.xtext.example.mydsl.fML.BooleanExpr;
import org.xtext.example.mydsl.fML.CNFExpression;
import org.xtext.example.mydsl.fML.CTCRCommand;
import org.xtext.example.mydsl.fML.ChildrenFeature;
import org.xtext.example.mydsl.fML.CleanUp;
import org.xtext.example.mydsl.fML.Cliques;
import org.xtext.example.mydsl.fML.Compare;
import org.xtext.example.mydsl.fML.ComparisonOperation;
import org.xtext.example.mydsl.fML.CompleteConfiguration;
import org.xtext.example.mydsl.fML.ComplexCommand;
import org.xtext.example.mydsl.fML.ComputeConstraints;
import org.xtext.example.mydsl.fML.ComputeFGroups;
import org.xtext.example.mydsl.fML.ConstraintExpr;
import org.xtext.example.mydsl.fML.ConstraintsSpecification;
import org.xtext.example.mydsl.fML.Convert;
import org.xtext.example.mydsl.fML.CopyVariable;
import org.xtext.example.mydsl.fML.Cores;
import org.xtext.example.mydsl.fML.CreateConfiguration;
import org.xtext.example.mydsl.fML.Deads;
import org.xtext.example.mydsl.fML.Dependency;
import org.xtext.example.mydsl.fML.DescendantFeature;
import org.xtext.example.mydsl.fML.DeselectedConfiguration;
import org.xtext.example.mydsl.fML.Exist;
import org.xtext.example.mydsl.fML.Exit;
import org.xtext.example.mydsl.fML.Export;
import org.xtext.example.mydsl.fML.Extract;
import org.xtext.example.mydsl.fML.FMFeature;
import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.FMLSave;
import org.xtext.example.mydsl.fML.FamiliarScript;
import org.xtext.example.mydsl.fML.FeatureExpression;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.FeatureOperation;
import org.xtext.example.mydsl.fML.FeatureOperator;
import org.xtext.example.mydsl.fML.FeatureVariabilityOperator;
import org.xtext.example.mydsl.fML.ForeachSet;
import org.xtext.example.mydsl.fML.FullMandatorys;
import org.xtext.example.mydsl.fML.GDisplay;
import org.xtext.example.mydsl.fML.GListing;
import org.xtext.example.mydsl.fML.GetConstraints;
import org.xtext.example.mydsl.fML.GetFGroups;
import org.xtext.example.mydsl.fML.GroupsSpecification;
import org.xtext.example.mydsl.fML.HProduction;
import org.xtext.example.mydsl.fML.Hidden;
import org.xtext.example.mydsl.fML.Hierarchy;
import org.xtext.example.mydsl.fML.HierarchySpecification;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.IfCondition;
import org.xtext.example.mydsl.fML.Impl_expr;
import org.xtext.example.mydsl.fML.Insert;
import org.xtext.example.mydsl.fML.IntLiteral;
import org.xtext.example.mydsl.fML.IntegerOperation;
import org.xtext.example.mydsl.fML.IsConflicting;
import org.xtext.example.mydsl.fML.IsEmptySet;
import org.xtext.example.mydsl.fML.KnowledgeSpecification;
import org.xtext.example.mydsl.fML.LArgs;
import org.xtext.example.mydsl.fML.LFMArgs;
import org.xtext.example.mydsl.fML.LVidentifier;
import org.xtext.example.mydsl.fML.Leaves;
import org.xtext.example.mydsl.fML.Listing;
import org.xtext.example.mydsl.fML.LoadGeneric;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.MandatoryEdit;
import org.xtext.example.mydsl.fML.Map;
import org.xtext.example.mydsl.fML.Merge;
import org.xtext.example.mydsl.fML.MtxGroupSpec;
import org.xtext.example.mydsl.fML.Mutexgroup;
import org.xtext.example.mydsl.fML.NameFeature;
import org.xtext.example.mydsl.fML.Neg_expr;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.OptionalEdit;
import org.xtext.example.mydsl.fML.OrEdit;
import org.xtext.example.mydsl.fML.OrGroupSpec;
import org.xtext.example.mydsl.fML.Or_expr;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.PairwiseCommand;
import org.xtext.example.mydsl.fML.Parameter;
import org.xtext.example.mydsl.fML.ParentFeature;
import org.xtext.example.mydsl.fML.PrinterUtility;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.RemoveConstraint;
import org.xtext.example.mydsl.fML.RemoveFeature;
import org.xtext.example.mydsl.fML.RemoveVariable;
import org.xtext.example.mydsl.fML.RenameFeature;
import org.xtext.example.mydsl.fML.ScriptCommand;
import org.xtext.example.mydsl.fML.ScriptDefinition;
import org.xtext.example.mydsl.fML.SelectedConfiguration;
import org.xtext.example.mydsl.fML.SelectionFeature;
import org.xtext.example.mydsl.fML.SetAddOrRemove;
import org.xtext.example.mydsl.fML.SetBelongs;
import org.xtext.example.mydsl.fML.SetCard;
import org.xtext.example.mydsl.fML.SetEmpty;
import org.xtext.example.mydsl.fML.SetExpr;
import org.xtext.example.mydsl.fML.SetOperation;
import org.xtext.example.mydsl.fML.SetToNames;
import org.xtext.example.mydsl.fML.SetUnionOrIntersection;
import org.xtext.example.mydsl.fML.Shell;
import org.xtext.example.mydsl.fML.SiblingFeature;
import org.xtext.example.mydsl.fML.Slice;
import org.xtext.example.mydsl.fML.State;
import org.xtext.example.mydsl.fML.StringConcat;
import org.xtext.example.mydsl.fML.StringExpr;
import org.xtext.example.mydsl.fML.StringIndexOf;
import org.xtext.example.mydsl.fML.StringInit;
import org.xtext.example.mydsl.fML.StringLength;
import org.xtext.example.mydsl.fML.StringSubstring;
import org.xtext.example.mydsl.fML.Synthesis;
import org.xtext.example.mydsl.fML.UnMap;
import org.xtext.example.mydsl.fML.UnselectedConfiguration;
import org.xtext.example.mydsl.fML.VariableNull;
import org.xtext.example.mydsl.fML.XorGroupSpec;
import org.xtext.example.mydsl.fML.Xorgroup;
import org.xtext.example.mydsl.fML.lType;
import org.xtext.example.mydsl.services.FMLGrammarAccess;

@SuppressWarnings("all")
public class FMLSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private FMLGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == FMLPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case FMLPackage.ADD_CONSTRAINT:
				if(context == grammarAccess.getAddConstraintRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AddConstraint(context, (AddConstraint) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.AGGREGATE:
				if(context == grammarAccess.getAggregateRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_Aggregate(context, (Aggregate) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.AGGREGATE_MERGE:
				if(context == grammarAccess.getAggregateMergeRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AggregateMerge(context, (AggregateMerge) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ALTERNATIVE_EDIT:
				if(context == grammarAccess.getAlternativeEditRule() ||
				   context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getModifyVOperatorRule()) {
					sequence_AlternativeEdit(context, (AlternativeEdit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ANALYSIS_OPERATION:
				if(context == grammarAccess.getAnalysisOperationRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AnalysisOperation(context, (AnalysisOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ANCESTOR_FEATURE:
				if(context == grammarAccess.getAncestorFeatureRule()) {
					sequence_AncestorFeature(context, (AncestorFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.AND_EXPR:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_And_expr(context, (And_expr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.AS_FM:
				if(context == grammarAccess.getAsFMRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AsFM(context, (AsFM) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ASSERTION:
				if(context == grammarAccess.getAssertionRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_Assertion(context, (Assertion) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ATOMIC_CONSTRAINT_EXPR:
				if(context == grammarAccess.getAtomicConstraintExprRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConstraintCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AtomicConstraintExpr(context, (AtomicConstraintExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.AUTO_CONFIGURATION:
				if(context == grammarAccess.getAutoConfigurationRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_AutoConfiguration(context, (AutoConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.BIIMPL_EXPR:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_Biimpl_expr(context, (Biimpl_expr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.BOOL_OPERATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComplexCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptCommandRule()) {
					sequence_ComplexCommand(context, (BoolOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.BOOLEAN_EXPR:
				if(context == grammarAccess.getBooleanExprRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_BooleanExpr(context, (BooleanExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CNF_EXPRESSION:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_Primary_expr(context, (CNFExpression) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CTCR_COMMAND:
				if(context == grammarAccess.getCTCRCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getDoubleCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIntegerCommandRule()) {
					sequence_CTCRCommand(context, (CTCRCommand) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CHILDREN_FEATURE:
				if(context == grammarAccess.getChildrenFeatureRule()) {
					sequence_ChildrenFeature(context, (ChildrenFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CLEAN_UP:
				if(context == grammarAccess.getCleanUpRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_CleanUp(context, (CleanUp) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CLIQUES:
				if(context == grammarAccess.getCliquesRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_Cliques(context, (Cliques) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPARE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getCompareRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_Compare(context, (Compare) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPARISON_OPERATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComplexCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptCommandRule()) {
					sequence_ComplexCommand(context, (ComparisonOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPLETE_CONFIGURATION:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getCompleteConfigurationRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_CompleteConfiguration(context, (CompleteConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPLEX_COMMAND:
				if(context == grammarAccess.getComplexCommandAccess().getBoolOperationLeftAction_0_1_1_0() ||
				   context == grammarAccess.getComplexCommandAccess().getComparisonOperationLeftAction_0_1_2_0() ||
				   context == grammarAccess.getComplexCommandAccess().getIntegerOperationLeftAction_0_1_0_0() ||
				   context == grammarAccess.getComplexCommandAccess().getSetOperationLeftAction_0_1_3_0()) {
					sequence_ComplexCommand_BoolOperation_0_1_1_0_ComparisonOperation_0_1_2_0_IntegerOperation_0_1_0_0_SetOperation_0_1_3_0(context, (ComplexCommand) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComplexCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptCommandRule()) {
					sequence_ComplexCommand(context, (ComplexCommand) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPUTE_CONSTRAINTS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComputeConstraintsRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_ComputeConstraints(context, (ComputeConstraints) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COMPUTE_FGROUPS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComputeFGroupsRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_ComputeFGroups(context, (ComputeFGroups) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CONSTRAINT_EXPR:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConstraintExprRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_ConstraintExpr(context, (ConstraintExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CONSTRAINTS_SPECIFICATION:
				if(context == grammarAccess.getConstraintsSpecificationRule()) {
					sequence_ConstraintsSpecification(context, (ConstraintsSpecification) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CONVERT:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConvertRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getStrCommandRule()) {
					sequence_Convert(context, (Convert) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.COPY_VARIABLE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getCopyVariableRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFTCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getVariabilityOpCommandRule()) {
					sequence_CopyVariable(context, (CopyVariable) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CORES:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getCoresRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_Cores(context, (Cores) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.CREATE_CONFIGURATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getConfigurationCommandRule() ||
				   context == grammarAccess.getCreateConfigurationRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_CreateConfiguration(context, (CreateConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.DEADS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getDeadsRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_Deads(context, (Deads) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.DEPENDENCY:
				if(context == grammarAccess.getDependencyRule()) {
					sequence_Dependency(context, (Dependency) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.DESCENDANT_FEATURE:
				if(context == grammarAccess.getDescendantFeatureRule()) {
					sequence_DescendantFeature(context, (DescendantFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.DESELECTED_CONFIGURATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getDeselectedConfigurationRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_DeselectedConfiguration(context, (DeselectedConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.EXIST:
				if(context == grammarAccess.getExistRule()) {
					sequence_Exist(context, (Exist) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.EXIT:
				if(context == grammarAccess.getExitRule()) {
					sequence_Exit(context, (Exit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.EXPORT:
				if(context == grammarAccess.getExportRule()) {
					sequence_Export(context, (Export) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.EXTRACT:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getExtractRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureModelOperationRule()) {
					sequence_Extract(context, (Extract) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FM_FEATURE:
				if(context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMFeatureRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule()) {
					sequence_FMFeature(context, (FMFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FML_SAVE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFMLSaveRule()) {
					sequence_FMLSave(context, (FMLSave) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FAMILIAR_SCRIPT:
				if(context == grammarAccess.getFamiliarScriptRule()) {
					sequence_FamiliarScript(context, (FamiliarScript) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FEATURE_EXPRESSION:
				if(context == grammarAccess.getFeatureExpressionRule()) {
					sequence_FeatureExpression(context, (FeatureExpression) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FEATURE_MODEL:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureModelRule()) {
					sequence_FeatureModel(context, (FeatureModel) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FEATURE_OPERATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFTCommandRule() ||
				   context == grammarAccess.getFeatureOperationRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getStrCommandRule()) {
					sequence_FeatureOperation(context, (FeatureOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FEATURE_OPERATOR:
				if(context == grammarAccess.getFeatureOperatorRule()) {
					sequence_FeatureOperator(context, (FeatureOperator) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FEATURE_VARIABILITY_OPERATOR:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureVariabilityOperatorRule() ||
				   context == grammarAccess.getVariabilityOpCommandRule()) {
					sequence_FeatureVariabilityOperator(context, (FeatureVariabilityOperator) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FOREACH_SET:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getForeachSetRule()) {
					sequence_ForeachSet(context, (ForeachSet) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.FULL_MANDATORYS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFullMandatorysRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_FullMandatorys(context, (FullMandatorys) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.GDISPLAY:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getGDisplayRule()) {
					sequence_GDisplay(context, (GDisplay) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.GLISTING:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getGListingRule()) {
					sequence_GListing(context, (GListing) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.GET_CONSTRAINTS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getGetConstraintsRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_GetConstraints(context, (GetConstraints) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.GET_FGROUPS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getGetFGroupsRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_GetFGroups(context, (GetFGroups) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.GROUPS_SPECIFICATION:
				if(context == grammarAccess.getGroupsSpecificationRule()) {
					sequence_GroupsSpecification(context, (GroupsSpecification) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.HPRODUCTION:
				if(context == grammarAccess.getHProductionRule()) {
					sequence_HProduction(context, (HProduction) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.HIDDEN:
				if(context == grammarAccess.getHiddenRule()) {
					sequence_Hidden(context, (Hidden) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.HIERARCHY:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getHierarchyRule()) {
					sequence_Hierarchy(context, (Hierarchy) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.HIERARCHY_SPECIFICATION:
				if(context == grammarAccess.getHierarchySpecificationRule()) {
					sequence_HierarchySpecification(context, (HierarchySpecification) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.IDENTIFIER_EXPR:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCommandRule() ||
				   context == grammarAccess.getConstraintCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFTCommandRule() ||
				   context == grammarAccess.getIdentifierExprRule() ||
				   context == grammarAccess.getIntegerCommandRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getVariabilityOpCommandRule()) {
					sequence_IdentifierExpr(context, (IdentifierExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.IF_CONDITION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIfConditionRule()) {
					sequence_IfCondition(context, (IfCondition) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.IMPL_EXPR:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_Impl_expr(context, (Impl_expr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.INSERT:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureModelOperationRule() ||
				   context == grammarAccess.getInsertRule()) {
					sequence_Insert(context, (Insert) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.INT_LITERAL:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIntegerCommandRule() ||
				   context == grammarAccess.getIntegerExprRule()) {
					sequence_IntegerExpr(context, (IntLiteral) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.INTEGER_OPERATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComplexCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptCommandRule()) {
					sequence_ComplexCommand(context, (IntegerOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.IS_CONFLICTING:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getIsConflictingRule()) {
					sequence_IsConflicting(context, (IsConflicting) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.IS_EMPTY_SET:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIsEmptySetRule() ||
				   context == grammarAccess.getSetOperationsRule()) {
					sequence_IsEmptySet(context, (IsEmptySet) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.KNOWLEDGE_SPECIFICATION:
				if(context == grammarAccess.getKnowledgeSpecificationRule()) {
					sequence_KnowledgeSpecification(context, (KnowledgeSpecification) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LARGS:
				if(context == grammarAccess.getLArgsRule()) {
					sequence_LArgs(context, (LArgs) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LFM_ARGS:
				if(context == grammarAccess.getLFMArgsRule()) {
					sequence_LFMArgs(context, (LFMArgs) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LVIDENTIFIER:
				if(context == grammarAccess.getLVidentifierRule()) {
					sequence_LVidentifier(context, (LVidentifier) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LEAVES:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getLeavesRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_Leaves(context, (Leaves) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LISTING:
				if(context == grammarAccess.getListingRule()) {
					sequence_Listing(context, (Listing) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LOAD_GENERIC:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getLoadGenericRule()) {
					sequence_LoadGeneric(context, (LoadGeneric) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MANDATORY:
				if(context == grammarAccess.getChildRule() ||
				   context == grammarAccess.getMandatoryRule()) {
					sequence_Mandatory(context, (Mandatory) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MANDATORY_EDIT:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getMandatoryEditRule() ||
				   context == grammarAccess.getModifyVOperatorRule()) {
					sequence_MandatoryEdit(context, (MandatoryEdit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MAP:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getMapRule()) {
					sequence_Map(context, (Map) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MERGE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getMergeRule()) {
					sequence_Merge(context, (Merge) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MTX_GROUP_SPEC:
				if(context == grammarAccess.getGroupSpecRule() ||
				   context == grammarAccess.getMtxGroupSpecRule()) {
					sequence_MtxGroupSpec(context, (MtxGroupSpec) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.MUTEXGROUP:
				if(context == grammarAccess.getChildRule() ||
				   context == grammarAccess.getMutexgroupRule()) {
					sequence_Mutexgroup(context, (Mutexgroup) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.NAME_FEATURE:
				if(context == grammarAccess.getNameFeatureRule()) {
					sequence_NameFeature(context, (NameFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.NEG_EXPR:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getNeg_exprRule() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_Neg_expr(context, (Neg_expr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.OPTIONAL:
				if(context == grammarAccess.getChildRule() ||
				   context == grammarAccess.getOptionalRule()) {
					sequence_Optional(context, (Optional) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.OPTIONAL_EDIT:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getModifyVOperatorRule() ||
				   context == grammarAccess.getOptionalEditRule()) {
					sequence_OptionalEdit(context, (OptionalEdit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.OR_EDIT:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getModifyVOperatorRule() ||
				   context == grammarAccess.getOrEditRule()) {
					sequence_OrEdit(context, (OrEdit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.OR_GROUP_SPEC:
				if(context == grammarAccess.getGroupSpecRule() ||
				   context == grammarAccess.getOrGroupSpecRule()) {
					sequence_OrGroupSpec(context, (OrGroupSpec) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.OR_EXPR:
				if(context == grammarAccess.getAnd_exprRule() ||
				   context == grammarAccess.getAnd_exprAccess().getAnd_exprLeftAction_1_0() ||
				   context == grammarAccess.getBiimpl_exprRule() ||
				   context == grammarAccess.getBiimpl_exprAccess().getBiimpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getCNFRule() ||
				   context == grammarAccess.getImpl_exprRule() ||
				   context == grammarAccess.getImpl_exprAccess().getImpl_exprLeftAction_1_0() ||
				   context == grammarAccess.getOr_exprRule() ||
				   context == grammarAccess.getOr_exprAccess().getOr_exprLeftAction_1_0() ||
				   context == grammarAccess.getPrimary_exprRule() ||
				   context == grammarAccess.getUnary_exprRule()) {
					sequence_Or_expr(context, (Or_expr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.ORGROUP:
				if(context == grammarAccess.getChildRule() ||
				   context == grammarAccess.getOrgroupRule()) {
					sequence_Orgroup(context, (Orgroup) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.PAIRWISE_COMMAND:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getPairwiseCommandRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_PairwiseCommand(context, (PairwiseCommand) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.PARAMETER:
				if(context == grammarAccess.getParameterRule()) {
					sequence_Parameter(context, (Parameter) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.PARENT_FEATURE:
				if(context == grammarAccess.getParentFeatureRule()) {
					sequence_ParentFeature(context, (ParentFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.PRINTER_UTILITY:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getPrinterUtilityRule()) {
					sequence_PrinterUtility(context, (PrinterUtility) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.PRODUCTION:
				if(context == grammarAccess.getProductionRule()) {
					sequence_Production(context, (Production) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.REMOVE_CONSTRAINT:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getRemoveConstraintRule()) {
					sequence_RemoveConstraint(context, (RemoveConstraint) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.REMOVE_FEATURE:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getEditOperationRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureModelOperationRule() ||
				   context == grammarAccess.getRemoveFeatureRule()) {
					sequence_RemoveFeature(context, (RemoveFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.REMOVE_VARIABLE:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getRemoveVariableRule()) {
					sequence_RemoveVariable(context, (RemoveVariable) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.RENAME_FEATURE:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getEditOperationRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getFeatureModelOperationRule() ||
				   context == grammarAccess.getRenameFeatureRule()) {
					sequence_RenameFeature(context, (RenameFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SCRIPT_COMMAND:
				if(context == grammarAccess.getScriptCommandRule()) {
					sequence_ScriptCommand(context, (ScriptCommand) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SCRIPT_DEFINITION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptDefinitionRule()) {
					sequence_ScriptDefinition(context, (ScriptDefinition) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SELECTED_CONFIGURATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSelectedConfigurationRule() ||
				   context == grammarAccess.getSetCommandRule()) {
					sequence_SelectedConfiguration(context, (SelectedConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SELECTION_FEATURE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSelectionFeatureRule()) {
					sequence_SelectionFeature(context, (SelectionFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_ADD_OR_REMOVE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetAddOrRemoveRule() ||
				   context == grammarAccess.getSetOperationsRule()) {
					sequence_SetAddOrRemove(context, (SetAddOrRemove) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_BELONGS:
				if(context == grammarAccess.getBCommandRule() ||
				   context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetBelongsRule() ||
				   context == grammarAccess.getSetOperationsRule()) {
					sequence_SetBelongs(context, (SetBelongs) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_CARD:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIntegerCommandRule() ||
				   context == grammarAccess.getSetCardRule() ||
				   context == grammarAccess.getSetOperationsRule()) {
					sequence_SetCard(context, (SetCard) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_EMPTY:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getSetEmptyRule() ||
				   context == grammarAccess.getSetOperationsRule()) {
					sequence_SetEmpty(context, (SetEmpty) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_EXPR:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getSetExprRule()) {
					sequence_SetExpr(context, (SetExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_OPERATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getComplexCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getScriptCommandRule()) {
					sequence_ComplexCommand(context, (SetOperation) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_TO_NAMES:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getSetOperationsRule() ||
				   context == grammarAccess.getSetToNamesRule()) {
					sequence_SetToNames(context, (SetToNames) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SET_UNION_OR_INTERSECTION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSetCommandRule() ||
				   context == grammarAccess.getSetOperationsRule() ||
				   context == grammarAccess.getSetUnionOrIntersectionRule()) {
					sequence_SetUnionOrIntersection(context, (SetUnionOrIntersection) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SHELL:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getShellRule()) {
					sequence_Shell(context, (Shell) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SIBLING_FEATURE:
				if(context == grammarAccess.getSiblingFeatureRule()) {
					sequence_SiblingFeature(context, (SiblingFeature) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SLICE:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSliceRule()) {
					sequence_Slice(context, (Slice) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STATE:
				if(context == grammarAccess.getStateRule()) {
					sequence_State(context, (State) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_CONCAT:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getStringConcatRule() ||
				   context == grammarAccess.getStringOperationRule()) {
					sequence_StringConcat(context, (StringConcat) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_EXPR:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getStringExprRule()) {
					sequence_StringExpr(context, (StringExpr) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_INDEX_OF:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIntegerCommandRule() ||
				   context == grammarAccess.getStringIndexOfRule() ||
				   context == grammarAccess.getStringOperationRule()) {
					sequence_StringIndexOf(context, (StringIndexOf) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_INIT:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getStringInitRule() ||
				   context == grammarAccess.getStringOperationRule()) {
					sequence_StringInit(context, (StringInit) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_LENGTH:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getIntegerCommandRule() ||
				   context == grammarAccess.getStringLengthRule() ||
				   context == grammarAccess.getStringOperationRule()) {
					sequence_StringLength(context, (StringLength) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.STRING_SUBSTRING:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getStrCommandRule() ||
				   context == grammarAccess.getStringOperationRule() ||
				   context == grammarAccess.getStringSubstringRule()) {
					sequence_StringSubstring(context, (StringSubstring) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.SYNTHESIS:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getSynthesisRule()) {
					sequence_Synthesis(context, (Synthesis) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.UN_MAP:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getUnMapRule()) {
					sequence_UnMap(context, (UnMap) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.UNSELECTED_CONFIGURATION:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getConfigurationCmdRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getUnselectedConfigurationRule()) {
					sequence_UnselectedConfiguration(context, (UnselectedConfiguration) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.VARIABLE_NULL:
				if(context == grammarAccess.getCommandRule() ||
				   context == grammarAccess.getFMLAbstractCommandRule() ||
				   context == grammarAccess.getVariableNullRule()) {
					sequence_VariableNull(context, (VariableNull) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.XOR_GROUP_SPEC:
				if(context == grammarAccess.getGroupSpecRule() ||
				   context == grammarAccess.getXorGroupSpecRule()) {
					sequence_XorGroupSpec(context, (XorGroupSpec) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.XORGROUP:
				if(context == grammarAccess.getChildRule() ||
				   context == grammarAccess.getXorgroupRule()) {
					sequence_Xorgroup(context, (Xorgroup) semanticObject); 
					return; 
				}
				else break;
			case FMLPackage.LTYPE:
				if(context == grammarAccess.getLTypeRule()) {
					sequence_lType(context, (lType) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (cst=ConstraintCommand fm=FMCommand)
	 */
	protected void sequence_AddConstraint(EObject context, AddConstraint semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAddConstraint_Cst()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAddConstraint_Cst()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAddConstraint_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAddConstraint_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAddConstraintAccess().getCstConstraintCommandParserRuleCall_1_0(), semanticObject.getCst());
		feeder.accept(grammarAccess.getAddConstraintAccess().getFmFMCommandParserRuleCall_3_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (mode=MergeMode (lfms+=FMCommand+ | fms=LFMArgs))
	 */
	protected void sequence_AggregateMerge(EObject context, AggregateMerge semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((fms+=FMCommand+ | sfms=IdentifierExpr) mapping=SetCommand?)
	 */
	protected void sequence_Aggregate(EObject context, Aggregate semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fts=SetCommand
	 */
	protected void sequence_AlternativeEdit(EObject context, AlternativeEdit semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAlternativeEdit_Fts()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAlternativeEdit_Fts()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAlternativeEditAccess().getFtsSetCommandParserRuleCall_1_0(), semanticObject.getFts());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             op='isValid' | 
	 *             op='counting' | 
	 *             op='configs' | 
	 *             op='nbFeatures' | 
	 *             op='root' | 
	 *             op='features'
	 *         ) 
	 *         (fm=FMCommand | fm=ConfigurationCommand)
	 *     )
	 */
	protected void sequence_AnalysisOperation(EObject context, AnalysisOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val='ancestors'
	 */
	protected void sequence_AncestorFeature(EObject context, AncestorFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAncestorFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAncestorFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAncestorFeatureAccess().getValAncestorsKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=And_expr_And_expr_1_0 right=Impl_expr)
	 */
	protected void sequence_And_expr(EObject context, And_expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     conf=ConfigurationCommand
	 */
	protected void sequence_AsFM(EObject context, AsFM semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAsFM_Conf()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAsFM_Conf()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAsFMAccess().getConfConfigurationCommandParserRuleCall_1_0(), semanticObject.getConf());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     assertion=ComplexCommand
	 */
	protected void sequence_Assertion(EObject context, Assertion semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAssertion_Assertion()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAssertion_Assertion()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAssertionAccess().getAssertionComplexCommandParserRuleCall_2_0(), semanticObject.getAssertion());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     expr=CNF
	 */
	protected void sequence_AtomicConstraintExpr(EObject context, AtomicConstraintExpr semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getAtomicConstraintExpr_Expr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getAtomicConstraintExpr_Expr()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAtomicConstraintExprAccess().getExprCNFParserRuleCall_2_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (config=ConfigurationCommand mode=AutoConfMode?)
	 */
	protected void sequence_AutoConfiguration(EObject context, AutoConfiguration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Biimpl_expr_Biimpl_expr_1_0 right=Unary_expr)
	 */
	protected void sequence_Biimpl_expr(EObject context, Biimpl_expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (val='true' | val='false')
	 */
	protected void sequence_BooleanExpr(EObject context, BooleanExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_CTCRCommand(EObject context, CTCRCommand semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCTCRCommand_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCTCRCommand_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCTCRCommandAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val='children'
	 */
	protected void sequence_ChildrenFeature(EObject context, ChildrenFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getChildrenFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getChildrenFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getChildrenFeatureAccess().getValChildrenKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_CleanUp(EObject context, CleanUp semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCleanUp_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCleanUp_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCleanUpAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_Cliques(EObject context, Cliques semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCliques_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCliques_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCliquesAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (fm_left=FMCommand fm_right=FMCommand)
	 */
	protected void sequence_Compare(EObject context, Compare semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCompare_Fm_left()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCompare_Fm_left()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCompare_Fm_right()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCompare_Fm_right()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCompareAccess().getFm_leftFMCommandParserRuleCall_1_0(), semanticObject.getFm_left());
		feeder.accept(grammarAccess.getCompareAccess().getFm_rightFMCommandParserRuleCall_2_0(), semanticObject.getFm_right());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     config=ConfigurationCommand
	 */
	protected void sequence_CompleteConfiguration(EObject context, CompleteConfiguration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCompleteConfiguration_Config()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCompleteConfiguration_Config()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCompleteConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0(), semanticObject.getConfig());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (left=ComplexCommand_BoolOperation_0_1_1_0 op=BOOL_Operator right=ComplexCommand)
	 */
	protected void sequence_ComplexCommand(EObject context, BoolOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     left=Command
	 */
	protected void sequence_ComplexCommand_BoolOperation_0_1_1_0_ComparisonOperation_0_1_2_0_IntegerOperation_0_1_0_0_SetOperation_0_1_3_0(EObject context, ComplexCommand semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=ComplexCommand_ComparisonOperation_0_1_2_0 cmpOp=ComparisonOperator right=ComplexCommand)
	 */
	protected void sequence_ComplexCommand(EObject context, ComparisonOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Command | (not?='not' batom=ComplexCommand))
	 */
	protected void sequence_ComplexCommand(EObject context, ComplexCommand semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=ComplexCommand_IntegerOperation_0_1_0_0 (op=PLUS | op=MINUS | op=MULT | op=DIV | op=EXP) right=IntegerCommand)
	 */
	protected void sequence_ComplexCommand(EObject context, IntegerOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=ComplexCommand_SetOperation_0_1_3_0 sop=SetOperator right=ComplexCommand)
	 */
	protected void sequence_ComplexCommand(EObject context, SetOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (kindOfCompute=KindOfCompute fm=FMCommand)
	 */
	protected void sequence_ComputeConstraints(EObject context, ComputeConstraints semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getComputeConstraints_KindOfCompute()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getComputeConstraints_KindOfCompute()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getComputeConstraints_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getComputeConstraints_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getComputeConstraintsAccess().getKindOfComputeKindOfComputeEnumRuleCall_0_0(), semanticObject.getKindOfCompute());
		feeder.accept(grammarAccess.getComputeConstraintsAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (kindOfGroups=KindOfComputeGroups fm=FMCommand)
	 */
	protected void sequence_ComputeFGroups(EObject context, ComputeFGroups semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getComputeFGroups_KindOfGroups()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getComputeFGroups_KindOfGroups()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getComputeFGroups_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getComputeFGroups_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getComputeFGroupsAccess().getKindOfGroupsKindOfComputeGroupsEnumRuleCall_0_0(), semanticObject.getKindOfGroups());
		feeder.accept(grammarAccess.getComputeFGroupsAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (constraints+=CNF+ | fm=FMCommand)
	 */
	protected void sequence_ConstraintExpr(EObject context, ConstraintExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     csts=ConstraintExpr
	 */
	protected void sequence_ConstraintsSpecification(EObject context, ConstraintsSpecification semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getConstraintsSpecification_Csts()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getConstraintsSpecification_Csts()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getConstraintsSpecificationAccess().getCstsConstraintExprParserRuleCall_1_0(), semanticObject.getCsts());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (v=FMCommand format=FMFormat)
	 */
	protected void sequence_Convert(EObject context, Convert semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getConvert_V()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getConvert_V()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getConvert_Format()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getConvert_Format()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getConvertAccess().getVFMCommandParserRuleCall_1_0(), semanticObject.getV());
		feeder.accept(grammarAccess.getConvertAccess().getFormatFMFormatEnumRuleCall_3_0(), semanticObject.getFormat());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     vid=FML_IDENTIFIER
	 */
	protected void sequence_CopyVariable(EObject context, CopyVariable semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCopyVariable_Vid()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCopyVariable_Vid()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCopyVariableAccess().getVidFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVid());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_Cores(EObject context, Cores semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCores_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCores_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCoresAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_CreateConfiguration(EObject context, CreateConfiguration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getCreateConfiguration_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getCreateConfiguration_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCreateConfigurationAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_Deads(EObject context, Deads semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getDeads_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getDeads_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDeadsAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     var=FML_IDENTIFIER
	 */
	protected void sequence_Dependency(EObject context, Dependency semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getDependency_Var()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getDependency_Var()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDependencyAccess().getVarFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val='descendants'
	 */
	protected void sequence_DescendantFeature(EObject context, DescendantFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getDescendantFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getDescendantFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDescendantFeatureAccess().getValDescendantsKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     config=ConfigurationCommand
	 */
	protected void sequence_DeselectedConfiguration(EObject context, DeselectedConfiguration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getDeselectedConfiguration_Config()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getDeselectedConfiguration_Config()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDeselectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0(), semanticObject.getConfig());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val='isExisting' var=FML_IDENTIFIER)
	 */
	protected void sequence_Exist(EObject context, Exist semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getExist_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getExist_Val()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getExist_Var()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getExist_Var()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExistAccess().getValIsExistingKeyword_0_0(), semanticObject.getVal());
		feeder.accept(grammarAccess.getExistAccess().getVarFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val='quit' | val='exit')
	 */
	protected void sequence_Exit(EObject context, Exit semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     arg=LVidentifier
	 */
	protected void sequence_Export(EObject context, Export semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getExport_Arg()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getExport_Arg()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExportAccess().getArgLVidentifierParserRuleCall_1_0(), semanticObject.getArg());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     rootfeature=FTCommand
	 */
	protected void sequence_Extract(EObject context, Extract semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getExtract_Rootfeature()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getExtract_Rootfeature()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExtractAccess().getRootfeatureFTCommandParserRuleCall_1_0(), semanticObject.getRootfeature());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val='whichfm'
	 */
	protected void sequence_FMFeature(EObject context, FMFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFMFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFMFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFMFeatureAccess().getValWhichfmKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (v=FMCommand format=FMFormat)
	 */
	protected void sequence_FMLSave(EObject context, FMLSave semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFMLSave_V()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFMLSave_V()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFMLSave_Format()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFMLSave_Format()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFMLSaveAccess().getVFMCommandParserRuleCall_1_0(), semanticObject.getV());
		feeder.accept(grammarAccess.getFMLSaveAccess().getFormatFMFormatEnumRuleCall_3_0(), semanticObject.getFormat());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (params+=Parameter* cmds+=ScriptCommand* (exports+=Export | exports+=Hidden)*)
	 */
	protected void sequence_FamiliarScript(EObject context, FamiliarScript semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ft=IdentifierExpr | ft=StringExpr)
	 */
	protected void sequence_FeatureExpression(EObject context, FeatureExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (root=ID | (features+=Production+ expr+=CNF*) | file=StringExpr)
	 */
	protected void sequence_FeatureModel(EObject context, FeatureModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             op=AncestorFeature | 
	 *             op=DescendantFeature | 
	 *             op=ChildrenFeature | 
	 *             op=SiblingFeature | 
	 *             op=ParentFeature | 
	 *             op=NameFeature | 
	 *             op=FMFeature | 
	 *             op=FeatureOperator
	 *         ) 
	 *         feature=FTCommand
	 *     )
	 */
	protected void sequence_FeatureOperation(EObject context, FeatureOperation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val='operator'
	 */
	protected void sequence_FeatureOperator(EObject context, FeatureOperator semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFeatureOperator_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFeatureOperator_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFeatureOperatorAccess().getValOperatorKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val=FeatureEdgeKind
	 */
	protected void sequence_FeatureVariabilityOperator(EObject context, FeatureVariabilityOperator semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFeatureVariabilityOperator_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFeatureVariabilityOperator_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFeatureVariabilityOperatorAccess().getValFeatureEdgeKindEnumRuleCall_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val=FML_IDENTIFIER var=FML_IDENTIFIER exprs+=ScriptCommand+)
	 */
	protected void sequence_ForeachSet(EObject context, ForeachSet semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_FullMandatorys(EObject context, FullMandatorys semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getFullMandatorys_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getFullMandatorys_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFullMandatorysAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (cmdDisplay='gdisplay' (var=FMCommand | var=ConfigurationCommand))
	 */
	protected void sequence_GDisplay(EObject context, GDisplay semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {GListing}
	 */
	protected void sequence_GListing(EObject context, GListing semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (kindOfGet=KindOfGet fm=FMCommand)
	 */
	protected void sequence_GetConstraints(EObject context, GetConstraints semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getGetConstraints_KindOfGet()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getGetConstraints_KindOfGet()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getGetConstraints_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getGetConstraints_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getGetConstraintsAccess().getKindOfGetKindOfGetEnumRuleCall_0_0(), semanticObject.getKindOfGet());
		feeder.accept(grammarAccess.getGetConstraintsAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (kindOfGroups=KindOfGetGroups fm=FMCommand)
	 */
	protected void sequence_GetFGroups(EObject context, GetFGroups semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getGetFGroups_KindOfGroups()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getGetFGroups_KindOfGroups()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getGetFGroups_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getGetFGroups_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getGetFGroupsAccess().getKindOfGroupsKindOfGetGroupsEnumRuleCall_0_0(), semanticObject.getKindOfGroups());
		feeder.accept(grammarAccess.getGetFGroupsAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     groups+=GroupSpec+
	 */
	protected void sequence_GroupsSpecification(EObject context, GroupsSpecification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=FT_ID+)
	 */
	protected void sequence_HProduction(EObject context, HProduction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     arg=LVidentifier
	 */
	protected void sequence_Hidden(EObject context, Hidden semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getHidden_Arg()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getHidden_Arg()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getHiddenAccess().getArgLVidentifierParserRuleCall_1_0(), semanticObject.getArg());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (hierarchy=Hierarchy | features+=HProduction+)
	 */
	protected void sequence_HierarchySpecification(EObject context, HierarchySpecification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_Hierarchy(EObject context, Hierarchy semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getHierarchy_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getHierarchy_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getHierarchyAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val=FML_IDENTIFIER metaID=StringExpr?)
	 */
	protected void sequence_IdentifierExpr(EObject context, IdentifierExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (bexpr=ComplexCommand then+=ScriptCommand+ else+=ScriptCommand*)
	 */
	protected void sequence_IfCondition(EObject context, IfCondition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Impl_expr_Impl_expr_1_0 right=Biimpl_expr)
	 */
	protected void sequence_Impl_expr(EObject context, Impl_expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (aspectfm=FMCommand baseft=FTCommand op=VariabilityOpCommand)
	 */
	protected void sequence_Insert(EObject context, Insert semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getInsert_Aspectfm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getInsert_Aspectfm()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getInsert_Baseft()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getInsert_Baseft()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getInsert_Op()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getInsert_Op()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getInsertAccess().getAspectfmFMCommandParserRuleCall_1_0(), semanticObject.getAspectfm());
		feeder.accept(grammarAccess.getInsertAccess().getBaseftFTCommandParserRuleCall_3_0(), semanticObject.getBaseft());
		feeder.accept(grammarAccess.getInsertAccess().getOpVariabilityOpCommandParserRuleCall_5_0(), semanticObject.getOp());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=INT
	 */
	protected void sequence_IntegerExpr(EObject context, IntLiteral semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getIntLiteral_Value()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getIntLiteral_Value()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIntegerExprAccess().getValueINTTerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val='isConflicting' var=FML_IDENTIFIER)
	 */
	protected void sequence_IsConflicting(EObject context, IsConflicting semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getIsConflicting_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getIsConflicting_Val()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getIsConflicting_Var()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getIsConflicting_Var()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIsConflictingAccess().getValIsConflictingKeyword_0_0(), semanticObject.getVal());
		feeder.accept(grammarAccess.getIsConflictingAccess().getVarFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     set=SetCommand
	 */
	protected void sequence_IsEmptySet(EObject context, IsEmptySet semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getIsEmptySet_Set()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getIsEmptySet_Set()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIsEmptySetAccess().getSetSetCommandParserRuleCall_1_0(), semanticObject.getSet());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (hierarchy=HierarchySpecification? groups=GroupsSpecification? constraints=ConstraintsSpecification?)
	 */
	protected void sequence_KnowledgeSpecification(EObject context, KnowledgeSpecification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (args+=Command args+=Command*)
	 */
	protected void sequence_LArgs(EObject context, LArgs semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (lfms+=FMCommand lfms+=FMCommand*)
	 */
	protected void sequence_LFMArgs(EObject context, LFMArgs semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (args+=FML_IDENTIFIER args+=FML_IDENTIFIER*)
	 */
	protected void sequence_LVidentifier(EObject context, LVidentifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_Leaves(EObject context, Leaves semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getLeaves_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getLeaves_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLeavesAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ((val='ls' | val='vars') opt=OPT_LISTING?)
	 */
	protected void sequence_Listing(EObject context, Listing semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((stream=STRING | stream=FML_IDENTIFIER) params+=FML_IDENTIFIER* ns=FML_IDENTIFIER?)
	 */
	protected void sequence_LoadGeneric(EObject context, LoadGeneric semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ft=FTCommand
	 */
	protected void sequence_MandatoryEdit(EObject context, MandatoryEdit semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getMandatoryEdit_Ft()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getMandatoryEdit_Ft()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMandatoryEditAccess().getFtFTCommandParserRuleCall_1_0(), semanticObject.getFt());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     name=FT_ID
	 */
	protected void sequence_Mandatory(EObject context, Mandatory semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getMandatory_Name()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getMandatory_Name()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMandatoryAccess().getNameFT_IDParserRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (fm=FMCommand cst=SetCommand)
	 */
	protected void sequence_Map(EObject context, Map semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getMap_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getMap_Fm()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getMap_Cst()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getMap_Cst()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMapAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.accept(grammarAccess.getMapAccess().getCstSetCommandParserRuleCall_3_0(), semanticObject.getCst());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (backend=BDDBackend? mode=MergeMode (lfms+=FMCommand+ | fms=LFMArgs))
	 */
	protected void sequence_Merge(EObject context, Merge semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=FT_ID+)
	 */
	protected void sequence_MtxGroupSpec(EObject context, MtxGroupSpec semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (features+=FT_ID features+=FT_ID+)
	 */
	protected void sequence_Mutexgroup(EObject context, Mutexgroup semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val='name'
	 */
	protected void sequence_NameFeature(EObject context, NameFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getNameFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getNameFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNameFeatureAccess().getValNameKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     expr=Primary_expr
	 */
	protected void sequence_Neg_expr(EObject context, Neg_expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ft=FTCommand
	 */
	protected void sequence_OptionalEdit(EObject context, OptionalEdit semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getOptionalEdit_Ft()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getOptionalEdit_Ft()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOptionalEditAccess().getFtFTCommandParserRuleCall_1_0(), semanticObject.getFt());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Optional(EObject context, Optional semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getOptional_Name()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getOptional_Name()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOptionalAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     fts=SetCommand
	 */
	protected void sequence_OrEdit(EObject context, OrEdit semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getOrEdit_Fts()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getOrEdit_Fts()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOrEditAccess().getFtsSetCommandParserRuleCall_1_0(), semanticObject.getFts());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=FT_ID+)
	 */
	protected void sequence_OrGroupSpec(EObject context, OrGroupSpec semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Or_expr_Or_expr_1_0 right=And_expr)
	 */
	protected void sequence_Or_expr(EObject context, Or_expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (features+=FT_ID features+=FT_ID+)
	 */
	protected void sequence_Orgroup(EObject context, Orgroup semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (fm=FMCommand minimization=IntegerCommand? partial=IntegerCommand?)
	 */
	protected void sequence_PairwiseCommand(EObject context, PairwiseCommand semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (param=FML_IDENTIFIER (typed=':' type=lType)?)
	 */
	protected void sequence_Parameter(EObject context, Parameter semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val='parent'
	 */
	protected void sequence_ParentFeature(EObject context, ParentFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getParentFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getParentFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParentFeatureAccess().getValParentKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID | name='true' | name='false')
	 */
	protected void sequence_Primary_expr(EObject context, CNFExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((op='print' | op='println') arg=LArgs)
	 */
	protected void sequence_PrinterUtility(EObject context, PrinterUtility semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=Child+)
	 */
	protected void sequence_Production(EObject context, Production semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (cst=ConstraintCommand fm=FMCommand)
	 */
	protected void sequence_RemoveConstraint(EObject context, RemoveConstraint semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRemoveConstraint_Cst()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRemoveConstraint_Cst()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRemoveConstraint_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRemoveConstraint_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRemoveConstraintAccess().getCstConstraintCommandParserRuleCall_1_0(), semanticObject.getCst());
		feeder.accept(grammarAccess.getRemoveConstraintAccess().getFmFMCommandParserRuleCall_3_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     feature=FTCommand
	 */
	protected void sequence_RemoveFeature(EObject context, RemoveFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRemoveFeature_Feature()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRemoveFeature_Feature()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRemoveFeatureAccess().getFeatureFTCommandParserRuleCall_1_0(), semanticObject.getFeature());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     vid=FML_IDENTIFIER
	 */
	protected void sequence_RemoveVariable(EObject context, RemoveVariable semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRemoveVariable_Vid()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRemoveVariable_Vid()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRemoveVariableAccess().getVidFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVid());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (feature=FTCommand featureNew=StrCommand)
	 */
	protected void sequence_RenameFeature(EObject context, RenameFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRenameFeature_Feature()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRenameFeature_Feature()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getRenameFeature_FeatureNew()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getRenameFeature_FeatureNew()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRenameFeatureAccess().getFeatureFTCommandParserRuleCall_1_0(), semanticObject.getFeature());
		feeder.accept(grammarAccess.getRenameFeatureAccess().getFeatureNewStrCommandParserRuleCall_3_0(), semanticObject.getFeatureNew());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (var=FML_IDENTIFIER metaID=StringExpr? cmd=ComplexCommand)
	 */
	protected void sequence_ScriptCommand(EObject context, ScriptCommand semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (params+=Parameter* cmds+=ScriptCommand+ (exports+=Export | exports+=Hidden)*)
	 */
	protected void sequence_ScriptDefinition(EObject context, ScriptDefinition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     config=ConfigurationCommand
	 */
	protected void sequence_SelectedConfiguration(EObject context, SelectedConfiguration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSelectedConfiguration_Config()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSelectedConfiguration_Config()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSelectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0(), semanticObject.getConfig());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ((op='select' | op='deselect' | op='unselect') fts+=FeatureExpression+ config=ConfigurationCommand)
	 */
	protected void sequence_SelectionFeature(EObject context, SelectionFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((op='setAdd' | op='setRemove') setl=SetCommand var=Command)
	 */
	protected void sequence_SetAddOrRemove(EObject context, SetAddOrRemove semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (setl=FML_IDENTIFIER setr=FML_IDENTIFIER)
	 */
	protected void sequence_SetBelongs(EObject context, SetBelongs semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSetBelongs_Setl()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSetBelongs_Setl()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSetBelongs_Setr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSetBelongs_Setr()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSetBelongsAccess().getSetlFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getSetl());
		feeder.accept(grammarAccess.getSetBelongsAccess().getSetrFML_IDENTIFIERParserRuleCall_2_0(), semanticObject.getSetr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     set=SetCommand
	 */
	protected void sequence_SetCard(EObject context, SetCard semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSetCard_Set()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSetCard_Set()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSetCardAccess().getSetSetCommandParserRuleCall_1_0(), semanticObject.getSet());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val='setEmpty'
	 */
	protected void sequence_SetEmpty(EObject context, SetEmpty semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSetEmpty_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSetEmpty_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSetEmptyAccess().getValSetEmptyKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     e+=ComplexCommand+
	 */
	protected void sequence_SetExpr(EObject context, SetExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     set=SetCommand
	 */
	protected void sequence_SetToNames(EObject context, SetToNames semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSetToNames_Set()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSetToNames_Set()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSetToNamesAccess().getSetSetCommandParserRuleCall_1_0(), semanticObject.getSet());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ((op='setUnion' | op='setIntersection' | op='setDiff') setl=SetCommand setr=SetCommand)
	 */
	protected void sequence_SetUnionOrIntersection(EObject context, SetUnionOrIntersection semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (cmd=Exit | cmd=Exist | cmd=Listing | cmd=IsConflicting | cmd=State)
	 */
	protected void sequence_Shell(EObject context, Shell semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val='sibling'
	 */
	protected void sequence_SiblingFeature(EObject context, SiblingFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSiblingFeature_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSiblingFeature_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSiblingFeatureAccess().getValSiblingKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (fm=FMCommand mode=SliceMode fts=SetCommand)
	 */
	protected void sequence_Slice(EObject context, Slice semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSlice_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSlice_Fm()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSlice_Mode()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSlice_Mode()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getSlice_Fts()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getSlice_Fts()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSliceAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.accept(grammarAccess.getSliceAccess().getModeSliceModeEnumRuleCall_2_0(), semanticObject.getMode());
		feeder.accept(grammarAccess.getSliceAccess().getFtsSetCommandParserRuleCall_3_0(), semanticObject.getFts());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (val='memory' | val='cpu')
	 */
	protected void sequence_State(EObject context, State semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (lstr=StrCommand rstr=StrCommand)
	 */
	protected void sequence_StringConcat(EObject context, StringConcat semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringConcat_Lstr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringConcat_Lstr()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringConcat_Rstr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringConcat_Rstr()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringConcatAccess().getLstrStrCommandParserRuleCall_1_0(), semanticObject.getLstr());
		feeder.accept(grammarAccess.getStringConcatAccess().getRstrStrCommandParserRuleCall_2_0(), semanticObject.getRstr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val=STRING
	 */
	protected void sequence_StringExpr(EObject context, StringExpr semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringExpr_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringExpr_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringExprAccess().getValSTRINGTerminalRuleCall_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (str=StrCommand schar=StrCommand)
	 */
	protected void sequence_StringIndexOf(EObject context, StringIndexOf semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringIndexOf_Str()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringIndexOf_Str()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringIndexOf_Schar()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringIndexOf_Schar()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringIndexOfAccess().getStrStrCommandParserRuleCall_1_0(), semanticObject.getStr());
		feeder.accept(grammarAccess.getStringIndexOfAccess().getScharStrCommandParserRuleCall_2_0(), semanticObject.getSchar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val='strInit'
	 */
	protected void sequence_StringInit(EObject context, StringInit semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringInit_Val()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringInit_Val()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringInitAccess().getValStrInitKeyword_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     str=StrCommand
	 */
	protected void sequence_StringLength(EObject context, StringLength semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringLength_Str()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringLength_Str()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringLengthAccess().getStrStrCommandParserRuleCall_1_0(), semanticObject.getStr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (str=StrCommand begin=IntegerCommand end=IntegerCommand)
	 */
	protected void sequence_StringSubstring(EObject context, StringSubstring semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_Str()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_Str()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_Begin()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_Begin()));
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_End()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getStringSubstring_End()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringSubstringAccess().getStrStrCommandParserRuleCall_1_0(), semanticObject.getStr());
		feeder.accept(grammarAccess.getStringSubstringAccess().getBeginIntegerCommandParserRuleCall_2_0(), semanticObject.getBegin());
		feeder.accept(grammarAccess.getStringSubstringAccess().getEndIntegerCommandParserRuleCall_3_0(), semanticObject.getEnd());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (interactive?='--interactive' fm=FMCommand kst=KnowledgeSpecification?)
	 */
	protected void sequence_Synthesis(EObject context, Synthesis semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     fm=FMCommand
	 */
	protected void sequence_UnMap(EObject context, UnMap semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getUnMap_Fm()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getUnMap_Fm()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getUnMapAccess().getFmFMCommandParserRuleCall_1_0(), semanticObject.getFm());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     config=ConfigurationCommand
	 */
	protected void sequence_UnselectedConfiguration(EObject context, UnselectedConfiguration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getUnselectedConfiguration_Config()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getUnselectedConfiguration_Config()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getUnselectedConfigurationAccess().getConfigConfigurationCommandParserRuleCall_1_0(), semanticObject.getConfig());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     var=FML_IDENTIFIER
	 */
	protected void sequence_VariableNull(EObject context, VariableNull semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, FMLPackage.eINSTANCE.getVariableNull_Var()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FMLPackage.eINSTANCE.getVariableNull_Var()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getVariableNullAccess().getVarFML_IDENTIFIERParserRuleCall_1_0(), semanticObject.getVar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=FT_ID+)
	 */
	protected void sequence_XorGroupSpec(EObject context, XorGroupSpec semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (features+=FT_ID features+=FT_ID+)
	 */
	protected void sequence_Xorgroup(EObject context, Xorgroup semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         val='FeatureModel' | 
	 *         val='Feature' | 
	 *         val='Boolean' | 
	 *         val='String' | 
	 *         val='Configuration' | 
	 *         val='Set' | 
	 *         val='Double' | 
	 *         val='Integer' | 
	 *         val='Constraint'
	 *     )
	 */
	protected void sequence_lType(EObject context, lType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
