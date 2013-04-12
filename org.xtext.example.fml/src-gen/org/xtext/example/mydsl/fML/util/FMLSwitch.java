/**
 */
package org.xtext.example.mydsl.fML.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.xtext.example.mydsl.fML.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage
 * @generated
 */
public class FMLSwitch<T> 
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FMLPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = FMLPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case FMLPackage.FAMILIAR_SCRIPT:
      {
        FamiliarScript familiarScript = (FamiliarScript)theEObject;
        T result = caseFamiliarScript(familiarScript);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SCRIPT_COMMAND:
      {
        ScriptCommand scriptCommand = (ScriptCommand)theEObject;
        T result = caseScriptCommand(scriptCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPLEX_COMMAND:
      {
        ComplexCommand complexCommand = (ComplexCommand)theEObject;
        T result = caseComplexCommand(complexCommand);
        if (result == null) result = caseScriptCommand(complexCommand);
        if (result == null) result = caseCommand(complexCommand);
        if (result == null) result = caseFMLAbstractCommand(complexCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMMAND:
      {
        Command command = (Command)theEObject;
        T result = caseCommand(command);
        if (result == null) result = caseFMLAbstractCommand(command);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.INTEGER_EXPR:
      {
        IntegerExpr integerExpr = (IntegerExpr)theEObject;
        T result = caseIntegerExpr(integerExpr);
        if (result == null) result = caseCommand(integerExpr);
        if (result == null) result = caseIntegerCommand(integerExpr);
        if (result == null) result = caseFMLAbstractCommand(integerExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.BOOLEAN_EXPR:
      {
        BooleanExpr booleanExpr = (BooleanExpr)theEObject;
        T result = caseBooleanExpr(booleanExpr);
        if (result == null) result = caseCommand(booleanExpr);
        if (result == null) result = caseFMLAbstractCommand(booleanExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.IDENTIFIER_EXPR:
      {
        IdentifierExpr identifierExpr = (IdentifierExpr)theEObject;
        T result = caseIdentifierExpr(identifierExpr);
        if (result == null) result = caseCommand(identifierExpr);
        if (result == null) result = caseFMCommand(identifierExpr);
        if (result == null) result = caseFTCommand(identifierExpr);
        if (result == null) result = caseBCommand(identifierExpr);
        if (result == null) result = caseStrCommand(identifierExpr);
        if (result == null) result = caseConfigurationCommand(identifierExpr);
        if (result == null) result = caseSetCommand(identifierExpr);
        if (result == null) result = caseConstraintCommand(identifierExpr);
        if (result == null) result = caseIntegerCommand(identifierExpr);
        if (result == null) result = caseVariabilityOpCommand(identifierExpr);
        if (result == null) result = caseFMLAbstractCommand(identifierExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_EXPR:
      {
        StringExpr stringExpr = (StringExpr)theEObject;
        T result = caseStringExpr(stringExpr);
        if (result == null) result = caseCommand(stringExpr);
        if (result == null) result = caseStrCommand(stringExpr);
        if (result == null) result = caseFMLAbstractCommand(stringExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_EXPR:
      {
        SetExpr setExpr = (SetExpr)theEObject;
        T result = caseSetExpr(setExpr);
        if (result == null) result = caseCommand(setExpr);
        if (result == null) result = caseSetCommand(setExpr);
        if (result == null) result = caseFMLAbstractCommand(setExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ATOMIC_CONSTRAINT_EXPR:
      {
        AtomicConstraintExpr atomicConstraintExpr = (AtomicConstraintExpr)theEObject;
        T result = caseAtomicConstraintExpr(atomicConstraintExpr);
        if (result == null) result = caseCommand(atomicConstraintExpr);
        if (result == null) result = caseConstraintCommand(atomicConstraintExpr);
        if (result == null) result = caseFMLAbstractCommand(atomicConstraintExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONSTRAINT_EXPR:
      {
        ConstraintExpr constraintExpr = (ConstraintExpr)theEObject;
        T result = caseConstraintExpr(constraintExpr);
        if (result == null) result = caseCommand(constraintExpr);
        if (result == null) result = caseSetCommand(constraintExpr);
        if (result == null) result = caseFMLAbstractCommand(constraintExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_VARIABILITY_OPERATOR:
      {
        FeatureVariabilityOperator featureVariabilityOperator = (FeatureVariabilityOperator)theEObject;
        T result = caseFeatureVariabilityOperator(featureVariabilityOperator);
        if (result == null) result = caseCommand(featureVariabilityOperator);
        if (result == null) result = caseVariabilityOpCommand(featureVariabilityOperator);
        if (result == null) result = caseFMLAbstractCommand(featureVariabilityOperator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.IF_CONDITION:
      {
        IfCondition ifCondition = (IfCondition)theEObject;
        T result = caseIfCondition(ifCondition);
        if (result == null) result = caseCommand(ifCondition);
        if (result == null) result = caseFMLAbstractCommand(ifCondition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FOREACH_SET:
      {
        ForeachSet foreachSet = (ForeachSet)theEObject;
        T result = caseForeachSet(foreachSet);
        if (result == null) result = caseCommand(foreachSet);
        if (result == null) result = caseFMLAbstractCommand(foreachSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LTYPE:
      {
        lType lType = (lType)theEObject;
        T result = caselType(lType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FML_ABSTRACT_COMMAND:
      {
        FMLAbstractCommand fmlAbstractCommand = (FMLAbstractCommand)theEObject;
        T result = caseFMLAbstractCommand(fmlAbstractCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FM_COMMAND:
      {
        FMCommand fmCommand = (FMCommand)theEObject;
        T result = caseFMCommand(fmCommand);
        if (result == null) result = caseFMLAbstractCommand(fmCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FT_COMMAND:
      {
        FTCommand ftCommand = (FTCommand)theEObject;
        T result = caseFTCommand(ftCommand);
        if (result == null) result = caseFMLAbstractCommand(ftCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.BCOMMAND:
      {
        BCommand bCommand = (BCommand)theEObject;
        T result = caseBCommand(bCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STR_COMMAND:
      {
        StrCommand strCommand = (StrCommand)theEObject;
        T result = caseStrCommand(strCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONFIGURATION_COMMAND:
      {
        ConfigurationCommand configurationCommand = (ConfigurationCommand)theEObject;
        T result = caseConfigurationCommand(configurationCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_COMMAND:
      {
        SetCommand setCommand = (SetCommand)theEObject;
        T result = caseSetCommand(setCommand);
        if (result == null) result = caseFMLAbstractCommand(setCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LEAVES:
      {
        Leaves leaves = (Leaves)theEObject;
        T result = caseLeaves(leaves);
        if (result == null) result = caseCommand(leaves);
        if (result == null) result = caseSetCommand(leaves);
        if (result == null) result = caseFMLAbstractCommand(leaves);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONSTRAINT_COMMAND:
      {
        ConstraintCommand constraintCommand = (ConstraintCommand)theEObject;
        T result = caseConstraintCommand(constraintCommand);
        if (result == null) result = caseFMLAbstractCommand(constraintCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GET_CONSTRAINTS:
      {
        GetConstraints getConstraints = (GetConstraints)theEObject;
        T result = caseGetConstraints(getConstraints);
        if (result == null) result = caseCommand(getConstraints);
        if (result == null) result = caseSetCommand(getConstraints);
        if (result == null) result = caseFMLAbstractCommand(getConstraints);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPUTE_CONSTRAINTS:
      {
        ComputeConstraints computeConstraints = (ComputeConstraints)theEObject;
        T result = caseComputeConstraints(computeConstraints);
        if (result == null) result = caseCommand(computeConstraints);
        if (result == null) result = caseSetCommand(computeConstraints);
        if (result == null) result = caseFMLAbstractCommand(computeConstraints);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GET_FGROUPS:
      {
        GetFGroups getFGroups = (GetFGroups)theEObject;
        T result = caseGetFGroups(getFGroups);
        if (result == null) result = caseCommand(getFGroups);
        if (result == null) result = caseSetCommand(getFGroups);
        if (result == null) result = caseFMLAbstractCommand(getFGroups);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPUTE_FGROUPS:
      {
        ComputeFGroups computeFGroups = (ComputeFGroups)theEObject;
        T result = caseComputeFGroups(computeFGroups);
        if (result == null) result = caseCommand(computeFGroups);
        if (result == null) result = caseSetCommand(computeFGroups);
        if (result == null) result = caseFMLAbstractCommand(computeFGroups);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.PAIRWISE_COMMAND:
      {
        PairwiseCommand pairwiseCommand = (PairwiseCommand)theEObject;
        T result = casePairwiseCommand(pairwiseCommand);
        if (result == null) result = caseCommand(pairwiseCommand);
        if (result == null) result = caseSetCommand(pairwiseCommand);
        if (result == null) result = caseFMLAbstractCommand(pairwiseCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.INTEGER_COMMAND:
      {
        IntegerCommand integerCommand = (IntegerCommand)theEObject;
        T result = caseIntegerCommand(integerCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.DOUBLE_COMMAND:
      {
        DoubleCommand doubleCommand = (DoubleCommand)theEObject;
        T result = caseDoubleCommand(doubleCommand);
        if (result == null) result = caseIntegerCommand(doubleCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.VARIABILITY_OP_COMMAND:
      {
        VariabilityOpCommand variabilityOpCommand = (VariabilityOpCommand)theEObject;
        T result = caseVariabilityOpCommand(variabilityOpCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ANALYSIS_OPERATION:
      {
        AnalysisOperation analysisOperation = (AnalysisOperation)theEObject;
        T result = caseAnalysisOperation(analysisOperation);
        if (result == null) result = caseCommand(analysisOperation);
        if (result == null) result = caseFMLAbstractCommand(analysisOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_OPERATIONS:
      {
        SetOperations setOperations = (SetOperations)theEObject;
        T result = caseSetOperations(setOperations);
        if (result == null) result = caseCommand(setOperations);
        if (result == null) result = caseFMLAbstractCommand(setOperations);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_CARD:
      {
        SetCard setCard = (SetCard)theEObject;
        T result = caseSetCard(setCard);
        if (result == null) result = caseIntegerCommand(setCard);
        if (result == null) result = caseSetOperations(setCard);
        if (result == null) result = caseCommand(setCard);
        if (result == null) result = caseFMLAbstractCommand(setCard);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_BELONGS:
      {
        SetBelongs setBelongs = (SetBelongs)theEObject;
        T result = caseSetBelongs(setBelongs);
        if (result == null) result = caseBCommand(setBelongs);
        if (result == null) result = caseSetOperations(setBelongs);
        if (result == null) result = caseCommand(setBelongs);
        if (result == null) result = caseFMLAbstractCommand(setBelongs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_UNION_OR_INTERSECTION:
      {
        SetUnionOrIntersection setUnionOrIntersection = (SetUnionOrIntersection)theEObject;
        T result = caseSetUnionOrIntersection(setUnionOrIntersection);
        if (result == null) result = caseSetCommand(setUnionOrIntersection);
        if (result == null) result = caseSetOperations(setUnionOrIntersection);
        if (result == null) result = caseCommand(setUnionOrIntersection);
        if (result == null) result = caseFMLAbstractCommand(setUnionOrIntersection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_EMPTY:
      {
        SetEmpty setEmpty = (SetEmpty)theEObject;
        T result = caseSetEmpty(setEmpty);
        if (result == null) result = caseSetCommand(setEmpty);
        if (result == null) result = caseSetOperations(setEmpty);
        if (result == null) result = caseCommand(setEmpty);
        if (result == null) result = caseFMLAbstractCommand(setEmpty);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_ADD_OR_REMOVE:
      {
        SetAddOrRemove setAddOrRemove = (SetAddOrRemove)theEObject;
        T result = caseSetAddOrRemove(setAddOrRemove);
        if (result == null) result = caseSetOperations(setAddOrRemove);
        if (result == null) result = caseCommand(setAddOrRemove);
        if (result == null) result = caseFMLAbstractCommand(setAddOrRemove);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.IS_EMPTY_SET:
      {
        IsEmptySet isEmptySet = (IsEmptySet)theEObject;
        T result = caseIsEmptySet(isEmptySet);
        if (result == null) result = caseBCommand(isEmptySet);
        if (result == null) result = caseSetOperations(isEmptySet);
        if (result == null) result = caseCommand(isEmptySet);
        if (result == null) result = caseFMLAbstractCommand(isEmptySet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_TO_NAMES:
      {
        SetToNames setToNames = (SetToNames)theEObject;
        T result = caseSetToNames(setToNames);
        if (result == null) result = caseSetCommand(setToNames);
        if (result == null) result = caseSetOperations(setToNames);
        if (result == null) result = caseCommand(setToNames);
        if (result == null) result = caseFMLAbstractCommand(setToNames);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_OPERATION:
      {
        FeatureOperation featureOperation = (FeatureOperation)theEObject;
        T result = caseFeatureOperation(featureOperation);
        if (result == null) result = caseCommand(featureOperation);
        if (result == null) result = caseFTCommand(featureOperation);
        if (result == null) result = caseStrCommand(featureOperation);
        if (result == null) result = caseSetCommand(featureOperation);
        if (result == null) result = caseFMLAbstractCommand(featureOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ANCESTOR_FEATURE:
      {
        AncestorFeature ancestorFeature = (AncestorFeature)theEObject;
        T result = caseAncestorFeature(ancestorFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.DESCENDANT_FEATURE:
      {
        DescendantFeature descendantFeature = (DescendantFeature)theEObject;
        T result = caseDescendantFeature(descendantFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CHILDREN_FEATURE:
      {
        ChildrenFeature childrenFeature = (ChildrenFeature)theEObject;
        T result = caseChildrenFeature(childrenFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SIBLING_FEATURE:
      {
        SiblingFeature siblingFeature = (SiblingFeature)theEObject;
        T result = caseSiblingFeature(siblingFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.PARENT_FEATURE:
      {
        ParentFeature parentFeature = (ParentFeature)theEObject;
        T result = caseParentFeature(parentFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.NAME_FEATURE:
      {
        NameFeature nameFeature = (NameFeature)theEObject;
        T result = caseNameFeature(nameFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FM_FEATURE:
      {
        FMFeature fmFeature = (FMFeature)theEObject;
        T result = caseFMFeature(fmFeature);
        if (result == null) result = caseFMCommand(fmFeature);
        if (result == null) result = caseFMLAbstractCommand(fmFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_OPERATOR:
      {
        FeatureOperator featureOperator = (FeatureOperator)theEObject;
        T result = caseFeatureOperator(featureOperator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_OPERATION:
      {
        StringOperation stringOperation = (StringOperation)theEObject;
        T result = caseStringOperation(stringOperation);
        if (result == null) result = caseCommand(stringOperation);
        if (result == null) result = caseFMLAbstractCommand(stringOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_INIT:
      {
        StringInit stringInit = (StringInit)theEObject;
        T result = caseStringInit(stringInit);
        if (result == null) result = caseStrCommand(stringInit);
        if (result == null) result = caseStringOperation(stringInit);
        if (result == null) result = caseCommand(stringInit);
        if (result == null) result = caseFMLAbstractCommand(stringInit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_CONCAT:
      {
        StringConcat stringConcat = (StringConcat)theEObject;
        T result = caseStringConcat(stringConcat);
        if (result == null) result = caseStrCommand(stringConcat);
        if (result == null) result = caseStringOperation(stringConcat);
        if (result == null) result = caseCommand(stringConcat);
        if (result == null) result = caseFMLAbstractCommand(stringConcat);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_SUBSTRING:
      {
        StringSubstring stringSubstring = (StringSubstring)theEObject;
        T result = caseStringSubstring(stringSubstring);
        if (result == null) result = caseStrCommand(stringSubstring);
        if (result == null) result = caseStringOperation(stringSubstring);
        if (result == null) result = caseCommand(stringSubstring);
        if (result == null) result = caseFMLAbstractCommand(stringSubstring);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_INDEX_OF:
      {
        StringIndexOf stringIndexOf = (StringIndexOf)theEObject;
        T result = caseStringIndexOf(stringIndexOf);
        if (result == null) result = caseIntegerCommand(stringIndexOf);
        if (result == null) result = caseStringOperation(stringIndexOf);
        if (result == null) result = caseCommand(stringIndexOf);
        if (result == null) result = caseFMLAbstractCommand(stringIndexOf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STRING_LENGTH:
      {
        StringLength stringLength = (StringLength)theEObject;
        T result = caseStringLength(stringLength);
        if (result == null) result = caseIntegerCommand(stringLength);
        if (result == null) result = caseStringOperation(stringLength);
        if (result == null) result = caseCommand(stringLength);
        if (result == null) result = caseFMLAbstractCommand(stringLength);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPARE:
      {
        Compare compare = (Compare)theEObject;
        T result = caseCompare(compare);
        if (result == null) result = caseCommand(compare);
        if (result == null) result = caseFMLAbstractCommand(compare);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.PARAMETER:
      {
        Parameter parameter = (Parameter)theEObject;
        T result = caseParameter(parameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LOAD_GENERIC:
      {
        LoadGeneric loadGeneric = (LoadGeneric)theEObject;
        T result = caseLoadGeneric(loadGeneric);
        if (result == null) result = caseCommand(loadGeneric);
        if (result == null) result = caseFMLAbstractCommand(loadGeneric);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CTCR_COMMAND:
      {
        CTCRCommand ctcrCommand = (CTCRCommand)theEObject;
        T result = caseCTCRCommand(ctcrCommand);
        if (result == null) result = caseCommand(ctcrCommand);
        if (result == null) result = caseDoubleCommand(ctcrCommand);
        if (result == null) result = caseFMLAbstractCommand(ctcrCommand);
        if (result == null) result = caseIntegerCommand(ctcrCommand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MERGE:
      {
        Merge merge = (Merge)theEObject;
        T result = caseMerge(merge);
        if (result == null) result = caseCommand(merge);
        if (result == null) result = caseFMCommand(merge);
        if (result == null) result = caseFMLAbstractCommand(merge);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LFM_ARGS:
      {
        LFMArgs lfmArgs = (LFMArgs)theEObject;
        T result = caseLFMArgs(lfmArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.AGGREGATE_MERGE:
      {
        AggregateMerge aggregateMerge = (AggregateMerge)theEObject;
        T result = caseAggregateMerge(aggregateMerge);
        if (result == null) result = caseCommand(aggregateMerge);
        if (result == null) result = caseFMCommand(aggregateMerge);
        if (result == null) result = caseFMLAbstractCommand(aggregateMerge);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SYNTHESIS:
      {
        Synthesis synthesis = (Synthesis)theEObject;
        T result = caseSynthesis(synthesis);
        if (result == null) result = caseCommand(synthesis);
        if (result == null) result = caseFMCommand(synthesis);
        if (result == null) result = caseFMLAbstractCommand(synthesis);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.KNOWLEDGE_SPECIFICATION:
      {
        KnowledgeSpecification knowledgeSpecification = (KnowledgeSpecification)theEObject;
        T result = caseKnowledgeSpecification(knowledgeSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.HIERARCHY_SPECIFICATION:
      {
        HierarchySpecification hierarchySpecification = (HierarchySpecification)theEObject;
        T result = caseHierarchySpecification(hierarchySpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.HPRODUCTION:
      {
        HProduction hProduction = (HProduction)theEObject;
        T result = caseHProduction(hProduction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GROUPS_SPECIFICATION:
      {
        GroupsSpecification groupsSpecification = (GroupsSpecification)theEObject;
        T result = caseGroupsSpecification(groupsSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GROUP_SPEC:
      {
        GroupSpec groupSpec = (GroupSpec)theEObject;
        T result = caseGroupSpec(groupSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.XOR_GROUP_SPEC:
      {
        XorGroupSpec xorGroupSpec = (XorGroupSpec)theEObject;
        T result = caseXorGroupSpec(xorGroupSpec);
        if (result == null) result = caseGroupSpec(xorGroupSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MTX_GROUP_SPEC:
      {
        MtxGroupSpec mtxGroupSpec = (MtxGroupSpec)theEObject;
        T result = caseMtxGroupSpec(mtxGroupSpec);
        if (result == null) result = caseGroupSpec(mtxGroupSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.OR_GROUP_SPEC:
      {
        OrGroupSpec orGroupSpec = (OrGroupSpec)theEObject;
        T result = caseOrGroupSpec(orGroupSpec);
        if (result == null) result = caseGroupSpec(orGroupSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONSTRAINTS_SPECIFICATION:
      {
        ConstraintsSpecification constraintsSpecification = (ConstraintsSpecification)theEObject;
        T result = caseConstraintsSpecification(constraintsSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SLICE:
      {
        Slice slice = (Slice)theEObject;
        T result = caseSlice(slice);
        if (result == null) result = caseCommand(slice);
        if (result == null) result = caseFMCommand(slice);
        if (result == null) result = caseFMLAbstractCommand(slice);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.AGGREGATE:
      {
        Aggregate aggregate = (Aggregate)theEObject;
        T result = caseAggregate(aggregate);
        if (result == null) result = caseCommand(aggregate);
        if (result == null) result = caseFMCommand(aggregate);
        if (result == null) result = caseFMLAbstractCommand(aggregate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_MODEL_OPERATION:
      {
        FeatureModelOperation featureModelOperation = (FeatureModelOperation)theEObject;
        T result = caseFeatureModelOperation(featureModelOperation);
        if (result == null) result = caseCommand(featureModelOperation);
        if (result == null) result = caseFMLAbstractCommand(featureModelOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.EDIT_OPERATION:
      {
        EditOperation editOperation = (EditOperation)theEObject;
        T result = caseEditOperation(editOperation);
        if (result == null) result = caseFeatureModelOperation(editOperation);
        if (result == null) result = caseCommand(editOperation);
        if (result == null) result = caseFMLAbstractCommand(editOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.INSERT:
      {
        Insert insert = (Insert)theEObject;
        T result = caseInsert(insert);
        if (result == null) result = caseBCommand(insert);
        if (result == null) result = caseFeatureModelOperation(insert);
        if (result == null) result = caseCommand(insert);
        if (result == null) result = caseFMLAbstractCommand(insert);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.REMOVE_FEATURE:
      {
        RemoveFeature removeFeature = (RemoveFeature)theEObject;
        T result = caseRemoveFeature(removeFeature);
        if (result == null) result = caseBCommand(removeFeature);
        if (result == null) result = caseEditOperation(removeFeature);
        if (result == null) result = caseFeatureModelOperation(removeFeature);
        if (result == null) result = caseCommand(removeFeature);
        if (result == null) result = caseFMLAbstractCommand(removeFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.RENAME_FEATURE:
      {
        RenameFeature renameFeature = (RenameFeature)theEObject;
        T result = caseRenameFeature(renameFeature);
        if (result == null) result = caseBCommand(renameFeature);
        if (result == null) result = caseEditOperation(renameFeature);
        if (result == null) result = caseFeatureModelOperation(renameFeature);
        if (result == null) result = caseCommand(renameFeature);
        if (result == null) result = caseFMLAbstractCommand(renameFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.EXTRACT:
      {
        Extract extract = (Extract)theEObject;
        T result = caseExtract(extract);
        if (result == null) result = caseFMCommand(extract);
        if (result == null) result = caseFeatureModelOperation(extract);
        if (result == null) result = caseCommand(extract);
        if (result == null) result = caseFMLAbstractCommand(extract);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ASSERTION:
      {
        Assertion assertion = (Assertion)theEObject;
        T result = caseAssertion(assertion);
        if (result == null) result = caseCommand(assertion);
        if (result == null) result = caseFMLAbstractCommand(assertion);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.VARIABLE_NULL:
      {
        VariableNull variableNull = (VariableNull)theEObject;
        T result = caseVariableNull(variableNull);
        if (result == null) result = caseCommand(variableNull);
        if (result == null) result = caseFMLAbstractCommand(variableNull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.EXPORT:
      {
        Export export = (Export)theEObject;
        T result = caseExport(export);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.HIDDEN:
      {
        Hidden hidden = (Hidden)theEObject;
        T result = caseHidden(hidden);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LVIDENTIFIER:
      {
        LVidentifier lVidentifier = (LVidentifier)theEObject;
        T result = caseLVidentifier(lVidentifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.DEPENDENCY:
      {
        Dependency dependency = (Dependency)theEObject;
        T result = caseDependency(dependency);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONFIGURATION_CMD:
      {
        ConfigurationCmd configurationCmd = (ConfigurationCmd)theEObject;
        T result = caseConfigurationCmd(configurationCmd);
        if (result == null) result = caseCommand(configurationCmd);
        if (result == null) result = caseFMLAbstractCommand(configurationCmd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CREATE_CONFIGURATION:
      {
        CreateConfiguration createConfiguration = (CreateConfiguration)theEObject;
        T result = caseCreateConfiguration(createConfiguration);
        if (result == null) result = caseConfigurationCommand(createConfiguration);
        if (result == null) result = caseConfigurationCmd(createConfiguration);
        if (result == null) result = caseCommand(createConfiguration);
        if (result == null) result = caseFMLAbstractCommand(createConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPLETE_CONFIGURATION:
      {
        CompleteConfiguration completeConfiguration = (CompleteConfiguration)theEObject;
        T result = caseCompleteConfiguration(completeConfiguration);
        if (result == null) result = caseBCommand(completeConfiguration);
        if (result == null) result = caseConfigurationCmd(completeConfiguration);
        if (result == null) result = caseCommand(completeConfiguration);
        if (result == null) result = caseFMLAbstractCommand(completeConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SELECTION_FEATURE:
      {
        SelectionFeature selectionFeature = (SelectionFeature)theEObject;
        T result = caseSelectionFeature(selectionFeature);
        if (result == null) result = caseConfigurationCmd(selectionFeature);
        if (result == null) result = caseCommand(selectionFeature);
        if (result == null) result = caseFMLAbstractCommand(selectionFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_EXPRESSION:
      {
        FeatureExpression featureExpression = (FeatureExpression)theEObject;
        T result = caseFeatureExpression(featureExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.AUTO_CONFIGURATION:
      {
        AutoConfiguration autoConfiguration = (AutoConfiguration)theEObject;
        T result = caseAutoConfiguration(autoConfiguration);
        if (result == null) result = caseConfigurationCmd(autoConfiguration);
        if (result == null) result = caseCommand(autoConfiguration);
        if (result == null) result = caseFMLAbstractCommand(autoConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SELECTED_CONFIGURATION:
      {
        SelectedConfiguration selectedConfiguration = (SelectedConfiguration)theEObject;
        T result = caseSelectedConfiguration(selectedConfiguration);
        if (result == null) result = caseSetCommand(selectedConfiguration);
        if (result == null) result = caseConfigurationCmd(selectedConfiguration);
        if (result == null) result = caseCommand(selectedConfiguration);
        if (result == null) result = caseFMLAbstractCommand(selectedConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.DESELECTED_CONFIGURATION:
      {
        DeselectedConfiguration deselectedConfiguration = (DeselectedConfiguration)theEObject;
        T result = caseDeselectedConfiguration(deselectedConfiguration);
        if (result == null) result = caseSetCommand(deselectedConfiguration);
        if (result == null) result = caseConfigurationCmd(deselectedConfiguration);
        if (result == null) result = caseCommand(deselectedConfiguration);
        if (result == null) result = caseFMLAbstractCommand(deselectedConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.UNSELECTED_CONFIGURATION:
      {
        UnselectedConfiguration unselectedConfiguration = (UnselectedConfiguration)theEObject;
        T result = caseUnselectedConfiguration(unselectedConfiguration);
        if (result == null) result = caseConfigurationCmd(unselectedConfiguration);
        if (result == null) result = caseCommand(unselectedConfiguration);
        if (result == null) result = caseFMLAbstractCommand(unselectedConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.AS_FM:
      {
        AsFM asFM = (AsFM)theEObject;
        T result = caseAsFM(asFM);
        if (result == null) result = caseCommand(asFM);
        if (result == null) result = caseFMCommand(asFM);
        if (result == null) result = caseFMLAbstractCommand(asFM);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MAP:
      {
        Map map = (Map)theEObject;
        T result = caseMap(map);
        if (result == null) result = caseCommand(map);
        if (result == null) result = caseFMLAbstractCommand(map);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.UN_MAP:
      {
        UnMap unMap = (UnMap)theEObject;
        T result = caseUnMap(unMap);
        if (result == null) result = caseCommand(unMap);
        if (result == null) result = caseFMLAbstractCommand(unMap);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CLEAN_UP:
      {
        CleanUp cleanUp = (CleanUp)theEObject;
        T result = caseCleanUp(cleanUp);
        if (result == null) result = caseCommand(cleanUp);
        if (result == null) result = caseFMLAbstractCommand(cleanUp);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CORES:
      {
        Cores cores = (Cores)theEObject;
        T result = caseCores(cores);
        if (result == null) result = caseCommand(cores);
        if (result == null) result = caseSetCommand(cores);
        if (result == null) result = caseFMLAbstractCommand(cores);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.DEADS:
      {
        Deads deads = (Deads)theEObject;
        T result = caseDeads(deads);
        if (result == null) result = caseCommand(deads);
        if (result == null) result = caseSetCommand(deads);
        if (result == null) result = caseFMLAbstractCommand(deads);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FULL_MANDATORYS:
      {
        FullMandatorys fullMandatorys = (FullMandatorys)theEObject;
        T result = caseFullMandatorys(fullMandatorys);
        if (result == null) result = caseCommand(fullMandatorys);
        if (result == null) result = caseSetCommand(fullMandatorys);
        if (result == null) result = caseFMLAbstractCommand(fullMandatorys);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CLIQUES:
      {
        Cliques cliques = (Cliques)theEObject;
        T result = caseCliques(cliques);
        if (result == null) result = caseCommand(cliques);
        if (result == null) result = caseSetCommand(cliques);
        if (result == null) result = caseFMLAbstractCommand(cliques);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SCRIPT_DEFINITION:
      {
        ScriptDefinition scriptDefinition = (ScriptDefinition)theEObject;
        T result = caseScriptDefinition(scriptDefinition);
        if (result == null) result = caseCommand(scriptDefinition);
        if (result == null) result = caseFMLAbstractCommand(scriptDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SHELL:
      {
        Shell shell = (Shell)theEObject;
        T result = caseShell(shell);
        if (result == null) result = caseCommand(shell);
        if (result == null) result = caseFMLAbstractCommand(shell);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.EXIT:
      {
        Exit exit = (Exit)theEObject;
        T result = caseExit(exit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.EXIST:
      {
        Exist exist = (Exist)theEObject;
        T result = caseExist(exist);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.IS_CONFLICTING:
      {
        IsConflicting isConflicting = (IsConflicting)theEObject;
        T result = caseIsConflicting(isConflicting);
        if (result == null) result = caseBCommand(isConflicting);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LISTING:
      {
        Listing listing = (Listing)theEObject;
        T result = caseListing(listing);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.STATE:
      {
        State state = (State)theEObject;
        T result = caseState(state);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COPY_VARIABLE:
      {
        CopyVariable copyVariable = (CopyVariable)theEObject;
        T result = caseCopyVariable(copyVariable);
        if (result == null) result = caseCommand(copyVariable);
        if (result == null) result = caseFMCommand(copyVariable);
        if (result == null) result = caseFTCommand(copyVariable);
        if (result == null) result = caseStrCommand(copyVariable);
        if (result == null) result = caseVariabilityOpCommand(copyVariable);
        if (result == null) result = caseFMLAbstractCommand(copyVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.REMOVE_VARIABLE:
      {
        RemoveVariable removeVariable = (RemoveVariable)theEObject;
        T result = caseRemoveVariable(removeVariable);
        if (result == null) result = caseCommand(removeVariable);
        if (result == null) result = caseBCommand(removeVariable);
        if (result == null) result = caseFMLAbstractCommand(removeVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CONVERT:
      {
        Convert convert = (Convert)theEObject;
        T result = caseConvert(convert);
        if (result == null) result = caseCommand(convert);
        if (result == null) result = caseStrCommand(convert);
        if (result == null) result = caseFMLAbstractCommand(convert);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FML_SAVE:
      {
        FMLSave fmlSave = (FMLSave)theEObject;
        T result = caseFMLSave(fmlSave);
        if (result == null) result = caseCommand(fmlSave);
        if (result == null) result = caseFMLAbstractCommand(fmlSave);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.HIERARCHY:
      {
        Hierarchy hierarchy = (Hierarchy)theEObject;
        T result = caseHierarchy(hierarchy);
        if (result == null) result = caseCommand(hierarchy);
        if (result == null) result = caseFMCommand(hierarchy);
        if (result == null) result = caseFMLAbstractCommand(hierarchy);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.PRINTER_UTILITY:
      {
        PrinterUtility printerUtility = (PrinterUtility)theEObject;
        T result = casePrinterUtility(printerUtility);
        if (result == null) result = caseCommand(printerUtility);
        if (result == null) result = caseFMLAbstractCommand(printerUtility);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.LARGS:
      {
        LArgs lArgs = (LArgs)theEObject;
        T result = caseLArgs(lArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GDISPLAY:
      {
        GDisplay gDisplay = (GDisplay)theEObject;
        T result = caseGDisplay(gDisplay);
        if (result == null) result = caseCommand(gDisplay);
        if (result == null) result = caseFMLAbstractCommand(gDisplay);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.GLISTING:
      {
        GListing gListing = (GListing)theEObject;
        T result = caseGListing(gListing);
        if (result == null) result = caseCommand(gListing);
        if (result == null) result = caseFMLAbstractCommand(gListing);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MODIFY_VOPERATOR:
      {
        ModifyVOperator modifyVOperator = (ModifyVOperator)theEObject;
        T result = caseModifyVOperator(modifyVOperator);
        if (result == null) result = caseCommand(modifyVOperator);
        if (result == null) result = caseBCommand(modifyVOperator);
        if (result == null) result = caseFMLAbstractCommand(modifyVOperator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MANDATORY_EDIT:
      {
        MandatoryEdit mandatoryEdit = (MandatoryEdit)theEObject;
        T result = caseMandatoryEdit(mandatoryEdit);
        if (result == null) result = caseModifyVOperator(mandatoryEdit);
        if (result == null) result = caseCommand(mandatoryEdit);
        if (result == null) result = caseBCommand(mandatoryEdit);
        if (result == null) result = caseFMLAbstractCommand(mandatoryEdit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.OPTIONAL_EDIT:
      {
        OptionalEdit optionalEdit = (OptionalEdit)theEObject;
        T result = caseOptionalEdit(optionalEdit);
        if (result == null) result = caseModifyVOperator(optionalEdit);
        if (result == null) result = caseCommand(optionalEdit);
        if (result == null) result = caseBCommand(optionalEdit);
        if (result == null) result = caseFMLAbstractCommand(optionalEdit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ALTERNATIVE_EDIT:
      {
        AlternativeEdit alternativeEdit = (AlternativeEdit)theEObject;
        T result = caseAlternativeEdit(alternativeEdit);
        if (result == null) result = caseModifyVOperator(alternativeEdit);
        if (result == null) result = caseCommand(alternativeEdit);
        if (result == null) result = caseBCommand(alternativeEdit);
        if (result == null) result = caseFMLAbstractCommand(alternativeEdit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.OR_EDIT:
      {
        OrEdit orEdit = (OrEdit)theEObject;
        T result = caseOrEdit(orEdit);
        if (result == null) result = caseModifyVOperator(orEdit);
        if (result == null) result = caseCommand(orEdit);
        if (result == null) result = caseBCommand(orEdit);
        if (result == null) result = caseFMLAbstractCommand(orEdit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ADD_CONSTRAINT:
      {
        AddConstraint addConstraint = (AddConstraint)theEObject;
        T result = caseAddConstraint(addConstraint);
        if (result == null) result = caseCommand(addConstraint);
        if (result == null) result = caseFMLAbstractCommand(addConstraint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.REMOVE_CONSTRAINT:
      {
        RemoveConstraint removeConstraint = (RemoveConstraint)theEObject;
        T result = caseRemoveConstraint(removeConstraint);
        if (result == null) result = caseCommand(removeConstraint);
        if (result == null) result = caseFMLAbstractCommand(removeConstraint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CNF:
      {
        CNF cnf = (CNF)theEObject;
        T result = caseCNF(cnf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CNF_EXPRESSION:
      {
        CNFExpression cnfExpression = (CNFExpression)theEObject;
        T result = caseCNFExpression(cnfExpression);
        if (result == null) result = caseCNF(cnfExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.NEG_EXPR:
      {
        Neg_expr neg_expr = (Neg_expr)theEObject;
        T result = caseNeg_expr(neg_expr);
        if (result == null) result = caseCNFExpression(neg_expr);
        if (result == null) result = caseCNF(neg_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.FEATURE_MODEL:
      {
        FeatureModel featureModel = (FeatureModel)theEObject;
        T result = caseFeatureModel(featureModel);
        if (result == null) result = caseCommand(featureModel);
        if (result == null) result = caseFMCommand(featureModel);
        if (result == null) result = caseFMLAbstractCommand(featureModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.PRODUCTION:
      {
        Production production = (Production)theEObject;
        T result = caseProduction(production);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.CHILD:
      {
        Child child = (Child)theEObject;
        T result = caseChild(child);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MANDATORY:
      {
        Mandatory mandatory = (Mandatory)theEObject;
        T result = caseMandatory(mandatory);
        if (result == null) result = caseChild(mandatory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.OPTIONAL:
      {
        Optional optional = (Optional)theEObject;
        T result = caseOptional(optional);
        if (result == null) result = caseChild(optional);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.XORGROUP:
      {
        Xorgroup xorgroup = (Xorgroup)theEObject;
        T result = caseXorgroup(xorgroup);
        if (result == null) result = caseChild(xorgroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.ORGROUP:
      {
        Orgroup orgroup = (Orgroup)theEObject;
        T result = caseOrgroup(orgroup);
        if (result == null) result = caseChild(orgroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.MUTEXGROUP:
      {
        Mutexgroup mutexgroup = (Mutexgroup)theEObject;
        T result = caseMutexgroup(mutexgroup);
        if (result == null) result = caseChild(mutexgroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.INTEGER_OPERATION:
      {
        IntegerOperation integerOperation = (IntegerOperation)theEObject;
        T result = caseIntegerOperation(integerOperation);
        if (result == null) result = caseComplexCommand(integerOperation);
        if (result == null) result = caseScriptCommand(integerOperation);
        if (result == null) result = caseCommand(integerOperation);
        if (result == null) result = caseFMLAbstractCommand(integerOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.BOOL_OPERATION:
      {
        BoolOperation boolOperation = (BoolOperation)theEObject;
        T result = caseBoolOperation(boolOperation);
        if (result == null) result = caseComplexCommand(boolOperation);
        if (result == null) result = caseScriptCommand(boolOperation);
        if (result == null) result = caseCommand(boolOperation);
        if (result == null) result = caseFMLAbstractCommand(boolOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.COMPARISON_OPERATION:
      {
        ComparisonOperation comparisonOperation = (ComparisonOperation)theEObject;
        T result = caseComparisonOperation(comparisonOperation);
        if (result == null) result = caseComplexCommand(comparisonOperation);
        if (result == null) result = caseScriptCommand(comparisonOperation);
        if (result == null) result = caseCommand(comparisonOperation);
        if (result == null) result = caseFMLAbstractCommand(comparisonOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.SET_OPERATION:
      {
        SetOperation setOperation = (SetOperation)theEObject;
        T result = caseSetOperation(setOperation);
        if (result == null) result = caseComplexCommand(setOperation);
        if (result == null) result = caseScriptCommand(setOperation);
        if (result == null) result = caseCommand(setOperation);
        if (result == null) result = caseFMLAbstractCommand(setOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.INT_LITERAL:
      {
        IntLiteral intLiteral = (IntLiteral)theEObject;
        T result = caseIntLiteral(intLiteral);
        if (result == null) result = caseIntegerExpr(intLiteral);
        if (result == null) result = caseCommand(intLiteral);
        if (result == null) result = caseIntegerCommand(intLiteral);
        if (result == null) result = caseFMLAbstractCommand(intLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.OR_EXPR:
      {
        Or_expr or_expr = (Or_expr)theEObject;
        T result = caseOr_expr(or_expr);
        if (result == null) result = caseCNFExpression(or_expr);
        if (result == null) result = caseCNF(or_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.AND_EXPR:
      {
        And_expr and_expr = (And_expr)theEObject;
        T result = caseAnd_expr(and_expr);
        if (result == null) result = caseCNFExpression(and_expr);
        if (result == null) result = caseCNF(and_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.IMPL_EXPR:
      {
        Impl_expr impl_expr = (Impl_expr)theEObject;
        T result = caseImpl_expr(impl_expr);
        if (result == null) result = caseCNFExpression(impl_expr);
        if (result == null) result = caseCNF(impl_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FMLPackage.BIIMPL_EXPR:
      {
        Biimpl_expr biimpl_expr = (Biimpl_expr)theEObject;
        T result = caseBiimpl_expr(biimpl_expr);
        if (result == null) result = caseCNFExpression(biimpl_expr);
        if (result == null) result = caseCNF(biimpl_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Familiar Script</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Familiar Script</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFamiliarScript(FamiliarScript object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Script Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Script Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScriptCommand(ScriptCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Complex Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComplexCommand(ComplexCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCommand(Command object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerExpr(IntegerExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanExpr(BooleanExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Identifier Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Identifier Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIdentifierExpr(IdentifierExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringExpr(StringExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetExpr(SetExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Atomic Constraint Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Atomic Constraint Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAtomicConstraintExpr(AtomicConstraintExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraint Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstraintExpr(ConstraintExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Variability Operator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Variability Operator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureVariabilityOperator(FeatureVariabilityOperator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Condition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfCondition(IfCondition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Foreach Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Foreach Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForeachSet(ForeachSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>lType</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>lType</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselType(lType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFMLAbstractCommand(FMLAbstractCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>FM Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>FM Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFMCommand(FMCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>FT Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>FT Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFTCommand(FTCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>BCommand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>BCommand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBCommand(BCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Str Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Str Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStrCommand(StrCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configuration Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configuration Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfigurationCommand(ConfigurationCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetCommand(SetCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Leaves</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Leaves</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLeaves(Leaves object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraint Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstraintCommand(ConstraintCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Get Constraints</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Get Constraints</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGetConstraints(GetConstraints object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compute Constraints</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compute Constraints</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComputeConstraints(ComputeConstraints object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Get FGroups</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Get FGroups</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGetFGroups(GetFGroups object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compute FGroups</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compute FGroups</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComputeFGroups(ComputeFGroups object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pairwise Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pairwise Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePairwiseCommand(PairwiseCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerCommand(IntegerCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Double Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Double Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDoubleCommand(DoubleCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variability Op Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variability Op Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariabilityOpCommand(VariabilityOpCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Analysis Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Analysis Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnalysisOperation(AnalysisOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Operations</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Operations</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetOperations(SetOperations object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Card</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Card</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetCard(SetCard object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Belongs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Belongs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetBelongs(SetBelongs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Union Or Intersection</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Union Or Intersection</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetUnionOrIntersection(SetUnionOrIntersection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Empty</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Empty</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetEmpty(SetEmpty object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Add Or Remove</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Add Or Remove</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetAddOrRemove(SetAddOrRemove object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Is Empty Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Is Empty Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIsEmptySet(IsEmptySet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set To Names</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set To Names</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetToNames(SetToNames object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureOperation(FeatureOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ancestor Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ancestor Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAncestorFeature(AncestorFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Descendant Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Descendant Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDescendantFeature(DescendantFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Children Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Children Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseChildrenFeature(ChildrenFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Sibling Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sibling Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSiblingFeature(SiblingFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parent Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parent Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParentFeature(ParentFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Name Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Name Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNameFeature(NameFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>FM Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>FM Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFMFeature(FMFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Operator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Operator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureOperator(FeatureOperator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringOperation(StringOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Init</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Init</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringInit(StringInit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Concat</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Concat</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringConcat(StringConcat object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Substring</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Substring</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringSubstring(StringSubstring object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Index Of</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Index Of</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringIndexOf(StringIndexOf object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Length</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Length</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringLength(StringLength object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compare</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compare</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompare(Compare object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameter(Parameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Load Generic</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Load Generic</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLoadGeneric(LoadGeneric object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>CTCR Command</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>CTCR Command</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCTCRCommand(CTCRCommand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Merge</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Merge</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMerge(Merge object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>LFM Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>LFM Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLFMArgs(LFMArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Aggregate Merge</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Aggregate Merge</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAggregateMerge(AggregateMerge object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Synthesis</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Synthesis</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSynthesis(Synthesis object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Knowledge Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Knowledge Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKnowledgeSpecification(KnowledgeSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Hierarchy Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Hierarchy Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHierarchySpecification(HierarchySpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>HProduction</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>HProduction</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHProduction(HProduction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Groups Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Groups Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupsSpecification(GroupsSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupSpec(GroupSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Xor Group Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Xor Group Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXorGroupSpec(XorGroupSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mtx Group Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mtx Group Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMtxGroupSpec(MtxGroupSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Group Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Group Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrGroupSpec(OrGroupSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraints Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraints Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstraintsSpecification(ConstraintsSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Slice</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Slice</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSlice(Slice object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Aggregate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Aggregate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAggregate(Aggregate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Model Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Model Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureModelOperation(FeatureModelOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Edit Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Edit Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEditOperation(EditOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Insert</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Insert</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInsert(Insert object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Remove Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Remove Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRemoveFeature(RemoveFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Rename Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Rename Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRenameFeature(RenameFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Extract</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extract</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExtract(Extract object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assertion</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assertion</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssertion(Assertion object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable Null</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Null</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableNull(VariableNull object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Export</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Export</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExport(Export object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Hidden</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Hidden</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHidden(Hidden object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>LVidentifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>LVidentifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLVidentifier(LVidentifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Dependency</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Dependency</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDependency(Dependency object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configuration Cmd</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configuration Cmd</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfigurationCmd(ConfigurationCmd object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Create Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Create Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCreateConfiguration(CreateConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Complete Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complete Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompleteConfiguration(CompleteConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Selection Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Selection Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectionFeature(SelectionFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureExpression(FeatureExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Auto Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Auto Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAutoConfiguration(AutoConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Selected Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Selected Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectedConfiguration(SelectedConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Deselected Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Deselected Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDeselectedConfiguration(DeselectedConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unselected Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unselected Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnselectedConfiguration(UnselectedConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>As FM</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>As FM</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAsFM(AsFM object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Map</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Map</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMap(Map object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Un Map</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Un Map</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnMap(UnMap object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Clean Up</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Clean Up</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCleanUp(CleanUp object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Cores</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Cores</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCores(Cores object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Deads</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Deads</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDeads(Deads object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Full Mandatorys</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Full Mandatorys</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFullMandatorys(FullMandatorys object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Cliques</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Cliques</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCliques(Cliques object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Script Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Script Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScriptDefinition(ScriptDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Shell</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Shell</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseShell(Shell object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Exit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExit(Exit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Exist</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exist</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExist(Exist object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Is Conflicting</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Is Conflicting</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIsConflicting(IsConflicting object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Listing</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Listing</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseListing(Listing object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseState(State object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Copy Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Copy Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCopyVariable(CopyVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Remove Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Remove Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRemoveVariable(RemoveVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Convert</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Convert</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConvert(Convert object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Save</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Save</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFMLSave(FMLSave object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Hierarchy</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Hierarchy</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHierarchy(Hierarchy object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Printer Utility</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Printer Utility</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrinterUtility(PrinterUtility object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>LArgs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>LArgs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLArgs(LArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GDisplay</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GDisplay</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGDisplay(GDisplay object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GListing</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GListing</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGListing(GListing object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Modify VOperator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modify VOperator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModifyVOperator(ModifyVOperator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mandatory Edit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mandatory Edit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMandatoryEdit(MandatoryEdit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Optional Edit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Optional Edit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOptionalEdit(OptionalEdit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Alternative Edit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Alternative Edit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlternativeEdit(AlternativeEdit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Edit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Edit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrEdit(OrEdit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Add Constraint</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Add Constraint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAddConstraint(AddConstraint object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Remove Constraint</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Remove Constraint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRemoveConstraint(RemoveConstraint object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>CNF</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>CNF</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCNF(CNF object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>CNF Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>CNF Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCNFExpression(CNFExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Neg expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Neg expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNeg_expr(Neg_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureModel(FeatureModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Production</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Production</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProduction(Production object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Child</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Child</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseChild(Child object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mandatory</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mandatory</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMandatory(Mandatory object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Optional</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Optional</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOptional(Optional object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Xorgroup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Xorgroup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXorgroup(Xorgroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Orgroup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Orgroup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrgroup(Orgroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mutexgroup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mutexgroup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMutexgroup(Mutexgroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerOperation(IntegerOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bool Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bool Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBoolOperation(BoolOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Comparison Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Comparison Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComparisonOperation(ComparisonOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetOperation(SetOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntLiteral(IntLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOr_expr(Or_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>And expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>And expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnd_expr(And_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Impl expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Impl expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImpl_expr(Impl_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Biimpl expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Biimpl expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBiimpl_expr(Biimpl_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //FMLSwitch
