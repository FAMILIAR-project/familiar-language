/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.xtext.example.mydsl.fML.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FMLFactoryImpl extends EFactoryImpl implements FMLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FMLFactory init()
  {
    try
    {
      FMLFactory theFMLFactory = (FMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.xtext.org/example/mydsl/FML"); 
      if (theFMLFactory != null)
      {
        return theFMLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FMLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case FMLPackage.FAMILIAR_SCRIPT: return createFamiliarScript();
      case FMLPackage.SCRIPT_COMMAND: return createScriptCommand();
      case FMLPackage.COMPLEX_COMMAND: return createComplexCommand();
      case FMLPackage.COMMAND: return createCommand();
      case FMLPackage.INTEGER_EXPR: return createIntegerExpr();
      case FMLPackage.BOOLEAN_EXPR: return createBooleanExpr();
      case FMLPackage.IDENTIFIER_EXPR: return createIdentifierExpr();
      case FMLPackage.STRING_EXPR: return createStringExpr();
      case FMLPackage.SET_EXPR: return createSetExpr();
      case FMLPackage.ATOMIC_CONSTRAINT_EXPR: return createAtomicConstraintExpr();
      case FMLPackage.CONSTRAINT_EXPR: return createConstraintExpr();
      case FMLPackage.FEATURE_VARIABILITY_OPERATOR: return createFeatureVariabilityOperator();
      case FMLPackage.IF_CONDITION: return createIfCondition();
      case FMLPackage.FOREACH_SET: return createForeachSet();
      case FMLPackage.LTYPE: return createlType();
      case FMLPackage.FML_ABSTRACT_COMMAND: return createFMLAbstractCommand();
      case FMLPackage.FM_COMMAND: return createFMCommand();
      case FMLPackage.FT_COMMAND: return createFTCommand();
      case FMLPackage.BCOMMAND: return createBCommand();
      case FMLPackage.STR_COMMAND: return createStrCommand();
      case FMLPackage.CONFIGURATION_COMMAND: return createConfigurationCommand();
      case FMLPackage.SET_COMMAND: return createSetCommand();
      case FMLPackage.CONSTRAINT_COMMAND: return createConstraintCommand();
      case FMLPackage.GET_CONSTRAINTS: return createGetConstraints();
      case FMLPackage.COMPUTE_CONSTRAINTS: return createComputeConstraints();
      case FMLPackage.GET_FGROUPS: return createGetFGroups();
      case FMLPackage.COMPUTE_FGROUPS: return createComputeFGroups();
      case FMLPackage.PAIRWISE_COMMAND: return createPairwiseCommand();
      case FMLPackage.INTEGER_COMMAND: return createIntegerCommand();
      case FMLPackage.DOUBLE_COMMAND: return createDoubleCommand();
      case FMLPackage.VARIABILITY_OP_COMMAND: return createVariabilityOpCommand();
      case FMLPackage.ANALYSIS_OPERATION: return createAnalysisOperation();
      case FMLPackage.SET_OPERATIONS: return createSetOperations();
      case FMLPackage.SET_CARD: return createSetCard();
      case FMLPackage.SET_BELONGS: return createSetBelongs();
      case FMLPackage.SET_UNION_OR_INTERSECTION: return createSetUnionOrIntersection();
      case FMLPackage.SET_EMPTY: return createSetEmpty();
      case FMLPackage.SET_ADD_OR_REMOVE: return createSetAddOrRemove();
      case FMLPackage.IS_EMPTY_SET: return createIsEmptySet();
      case FMLPackage.SET_TO_NAMES: return createSetToNames();
      case FMLPackage.FEATURE_OPERATION: return createFeatureOperation();
      case FMLPackage.ANCESTOR_FEATURE: return createAncestorFeature();
      case FMLPackage.DESCENDANT_FEATURE: return createDescendantFeature();
      case FMLPackage.CHILDREN_FEATURE: return createChildrenFeature();
      case FMLPackage.SIBLING_FEATURE: return createSiblingFeature();
      case FMLPackage.PARENT_FEATURE: return createParentFeature();
      case FMLPackage.NAME_FEATURE: return createNameFeature();
      case FMLPackage.FM_FEATURE: return createFMFeature();
      case FMLPackage.FEATURE_OPERATOR: return createFeatureOperator();
      case FMLPackage.STRING_OPERATION: return createStringOperation();
      case FMLPackage.STRING_INIT: return createStringInit();
      case FMLPackage.STRING_CONCAT: return createStringConcat();
      case FMLPackage.STRING_SUBSTRING: return createStringSubstring();
      case FMLPackage.STRING_INDEX_OF: return createStringIndexOf();
      case FMLPackage.STRING_LENGTH: return createStringLength();
      case FMLPackage.COMPARE: return createCompare();
      case FMLPackage.PARAMETER: return createParameter();
      case FMLPackage.LOAD_GENERIC: return createLoadGeneric();
      case FMLPackage.CTCR_COMMAND: return createCTCRCommand();
      case FMLPackage.MERGE: return createMerge();
      case FMLPackage.LFM_ARGS: return createLFMArgs();
      case FMLPackage.AGGREGATE_MERGE: return createAggregateMerge();
      case FMLPackage.SYNTHESIS: return createSynthesis();
      case FMLPackage.KNOWLEDGE_SPECIFICATION: return createKnowledgeSpecification();
      case FMLPackage.HIERARCHY_SPECIFICATION: return createHierarchySpecification();
      case FMLPackage.HPRODUCTION: return createHProduction();
      case FMLPackage.GROUPS_SPECIFICATION: return createGroupsSpecification();
      case FMLPackage.GROUP_SPEC: return createGroupSpec();
      case FMLPackage.XOR_GROUP_SPEC: return createXorGroupSpec();
      case FMLPackage.MTX_GROUP_SPEC: return createMtxGroupSpec();
      case FMLPackage.OR_GROUP_SPEC: return createOrGroupSpec();
      case FMLPackage.CONSTRAINTS_SPECIFICATION: return createConstraintsSpecification();
      case FMLPackage.SLICE: return createSlice();
      case FMLPackage.AGGREGATE: return createAggregate();
      case FMLPackage.FEATURE_MODEL_OPERATION: return createFeatureModelOperation();
      case FMLPackage.EDIT_OPERATION: return createEditOperation();
      case FMLPackage.INSERT: return createInsert();
      case FMLPackage.REMOVE_FEATURE: return createRemoveFeature();
      case FMLPackage.RENAME_FEATURE: return createRenameFeature();
      case FMLPackage.EXTRACT: return createExtract();
      case FMLPackage.ASSERTION: return createAssertion();
      case FMLPackage.VARIABLE_NULL: return createVariableNull();
      case FMLPackage.EXPORT: return createExport();
      case FMLPackage.HIDDEN: return createHidden();
      case FMLPackage.LVIDENTIFIER: return createLVidentifier();
      case FMLPackage.DEPENDENCY: return createDependency();
      case FMLPackage.CONFIGURATION_CMD: return createConfigurationCmd();
      case FMLPackage.CREATE_CONFIGURATION: return createCreateConfiguration();
      case FMLPackage.COMPLETE_CONFIGURATION: return createCompleteConfiguration();
      case FMLPackage.SELECTION_FEATURE: return createSelectionFeature();
      case FMLPackage.FEATURE_EXPRESSION: return createFeatureExpression();
      case FMLPackage.AUTO_CONFIGURATION: return createAutoConfiguration();
      case FMLPackage.SELECTED_CONFIGURATION: return createSelectedConfiguration();
      case FMLPackage.DESELECTED_CONFIGURATION: return createDeselectedConfiguration();
      case FMLPackage.UNSELECTED_CONFIGURATION: return createUnselectedConfiguration();
      case FMLPackage.AS_FM: return createAsFM();
      case FMLPackage.MAP: return createMap();
      case FMLPackage.UN_MAP: return createUnMap();
      case FMLPackage.CLEAN_UP: return createCleanUp();
      case FMLPackage.CORES: return createCores();
      case FMLPackage.DEADS: return createDeads();
      case FMLPackage.FULL_MANDATORYS: return createFullMandatorys();
      case FMLPackage.CLIQUES: return createCliques();
      case FMLPackage.SCRIPT_DEFINITION: return createScriptDefinition();
      case FMLPackage.SHELL: return createShell();
      case FMLPackage.EXIT: return createExit();
      case FMLPackage.EXIST: return createExist();
      case FMLPackage.IS_CONFLICTING: return createIsConflicting();
      case FMLPackage.LISTING: return createListing();
      case FMLPackage.STATE: return createState();
      case FMLPackage.COPY_VARIABLE: return createCopyVariable();
      case FMLPackage.REMOVE_VARIABLE: return createRemoveVariable();
      case FMLPackage.CONVERT: return createConvert();
      case FMLPackage.FML_SAVE: return createFMLSave();
      case FMLPackage.HIERARCHY: return createHierarchy();
      case FMLPackage.PRINTER_UTILITY: return createPrinterUtility();
      case FMLPackage.LARGS: return createLArgs();
      case FMLPackage.GDISPLAY: return createGDisplay();
      case FMLPackage.GLISTING: return createGListing();
      case FMLPackage.MODIFY_VOPERATOR: return createModifyVOperator();
      case FMLPackage.MANDATORY_EDIT: return createMandatoryEdit();
      case FMLPackage.OPTIONAL_EDIT: return createOptionalEdit();
      case FMLPackage.ALTERNATIVE_EDIT: return createAlternativeEdit();
      case FMLPackage.OR_EDIT: return createOrEdit();
      case FMLPackage.ADD_CONSTRAINT: return createAddConstraint();
      case FMLPackage.REMOVE_CONSTRAINT: return createRemoveConstraint();
      case FMLPackage.CNF: return createCNF();
      case FMLPackage.CNF_EXPRESSION: return createCNFExpression();
      case FMLPackage.NEG_EXPR: return createNeg_expr();
      case FMLPackage.FEATURE_MODEL: return createFeatureModel();
      case FMLPackage.PRODUCTION: return createProduction();
      case FMLPackage.CHILD: return createChild();
      case FMLPackage.MANDATORY: return createMandatory();
      case FMLPackage.OPTIONAL: return createOptional();
      case FMLPackage.XORGROUP: return createXorgroup();
      case FMLPackage.ORGROUP: return createOrgroup();
      case FMLPackage.MUTEXGROUP: return createMutexgroup();
      case FMLPackage.INTEGER_OPERATION: return createIntegerOperation();
      case FMLPackage.BOOL_OPERATION: return createBoolOperation();
      case FMLPackage.COMPARISON_OPERATION: return createComparisonOperation();
      case FMLPackage.SET_OPERATION: return createSetOperation();
      case FMLPackage.INT_LITERAL: return createIntLiteral();
      case FMLPackage.OR_EXPR: return createOr_expr();
      case FMLPackage.AND_EXPR: return createAnd_expr();
      case FMLPackage.IMPL_EXPR: return createImpl_expr();
      case FMLPackage.BIIMPL_EXPR: return createBiimpl_expr();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FMLPackage.EDIT_CONSTANT:
        return createEditConstantFromString(eDataType, initialValue);
      case FMLPackage.FEATURE_EDGE_KIND:
        return createFeatureEdgeKindFromString(eDataType, initialValue);
      case FMLPackage.KIND_OF_GET:
        return createKindOfGetFromString(eDataType, initialValue);
      case FMLPackage.KIND_OF_COMPUTE:
        return createKindOfComputeFromString(eDataType, initialValue);
      case FMLPackage.KIND_OF_GET_GROUPS:
        return createKindOfGetGroupsFromString(eDataType, initialValue);
      case FMLPackage.KIND_OF_COMPUTE_GROUPS:
        return createKindOfComputeGroupsFromString(eDataType, initialValue);
      case FMLPackage.BDD_BACKEND:
        return createBDDBackendFromString(eDataType, initialValue);
      case FMLPackage.MERGE_MODE:
        return createMergeModeFromString(eDataType, initialValue);
      case FMLPackage.SLICE_MODE:
        return createSliceModeFromString(eDataType, initialValue);
      case FMLPackage.COMPARISON_OPERATOR:
        return createComparisonOperatorFromString(eDataType, initialValue);
      case FMLPackage.SET_OPERATOR:
        return createSetOperatorFromString(eDataType, initialValue);
      case FMLPackage.OP_SELECTION:
        return createOpSelectionFromString(eDataType, initialValue);
      case FMLPackage.AUTO_CONF_MODE:
        return createAutoConfModeFromString(eDataType, initialValue);
      case FMLPackage.OPT_LISTING:
        return createOPT_LISTINGFromString(eDataType, initialValue);
      case FMLPackage.FM_FORMAT:
        return createFMFormatFromString(eDataType, initialValue);
      case FMLPackage.BOOL_OPERATOR:
        return createBOOL_OperatorFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FMLPackage.EDIT_CONSTANT:
        return convertEditConstantToString(eDataType, instanceValue);
      case FMLPackage.FEATURE_EDGE_KIND:
        return convertFeatureEdgeKindToString(eDataType, instanceValue);
      case FMLPackage.KIND_OF_GET:
        return convertKindOfGetToString(eDataType, instanceValue);
      case FMLPackage.KIND_OF_COMPUTE:
        return convertKindOfComputeToString(eDataType, instanceValue);
      case FMLPackage.KIND_OF_GET_GROUPS:
        return convertKindOfGetGroupsToString(eDataType, instanceValue);
      case FMLPackage.KIND_OF_COMPUTE_GROUPS:
        return convertKindOfComputeGroupsToString(eDataType, instanceValue);
      case FMLPackage.BDD_BACKEND:
        return convertBDDBackendToString(eDataType, instanceValue);
      case FMLPackage.MERGE_MODE:
        return convertMergeModeToString(eDataType, instanceValue);
      case FMLPackage.SLICE_MODE:
        return convertSliceModeToString(eDataType, instanceValue);
      case FMLPackage.COMPARISON_OPERATOR:
        return convertComparisonOperatorToString(eDataType, instanceValue);
      case FMLPackage.SET_OPERATOR:
        return convertSetOperatorToString(eDataType, instanceValue);
      case FMLPackage.OP_SELECTION:
        return convertOpSelectionToString(eDataType, instanceValue);
      case FMLPackage.AUTO_CONF_MODE:
        return convertAutoConfModeToString(eDataType, instanceValue);
      case FMLPackage.OPT_LISTING:
        return convertOPT_LISTINGToString(eDataType, instanceValue);
      case FMLPackage.FM_FORMAT:
        return convertFMFormatToString(eDataType, instanceValue);
      case FMLPackage.BOOL_OPERATOR:
        return convertBOOL_OperatorToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FamiliarScript createFamiliarScript()
  {
    FamiliarScriptImpl familiarScript = new FamiliarScriptImpl();
    return familiarScript;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScriptCommand createScriptCommand()
  {
    ScriptCommandImpl scriptCommand = new ScriptCommandImpl();
    return scriptCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComplexCommand createComplexCommand()
  {
    ComplexCommandImpl complexCommand = new ComplexCommandImpl();
    return complexCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Command createCommand()
  {
    CommandImpl command = new CommandImpl();
    return command;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerExpr createIntegerExpr()
  {
    IntegerExprImpl integerExpr = new IntegerExprImpl();
    return integerExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanExpr createBooleanExpr()
  {
    BooleanExprImpl booleanExpr = new BooleanExprImpl();
    return booleanExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IdentifierExpr createIdentifierExpr()
  {
    IdentifierExprImpl identifierExpr = new IdentifierExprImpl();
    return identifierExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringExpr createStringExpr()
  {
    StringExprImpl stringExpr = new StringExprImpl();
    return stringExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetExpr createSetExpr()
  {
    SetExprImpl setExpr = new SetExprImpl();
    return setExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AtomicConstraintExpr createAtomicConstraintExpr()
  {
    AtomicConstraintExprImpl atomicConstraintExpr = new AtomicConstraintExprImpl();
    return atomicConstraintExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintExpr createConstraintExpr()
  {
    ConstraintExprImpl constraintExpr = new ConstraintExprImpl();
    return constraintExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureVariabilityOperator createFeatureVariabilityOperator()
  {
    FeatureVariabilityOperatorImpl featureVariabilityOperator = new FeatureVariabilityOperatorImpl();
    return featureVariabilityOperator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfCondition createIfCondition()
  {
    IfConditionImpl ifCondition = new IfConditionImpl();
    return ifCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForeachSet createForeachSet()
  {
    ForeachSetImpl foreachSet = new ForeachSetImpl();
    return foreachSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public lType createlType()
  {
    lTypeImpl lType = new lTypeImpl();
    return lType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLAbstractCommand createFMLAbstractCommand()
  {
    FMLAbstractCommandImpl fmlAbstractCommand = new FMLAbstractCommandImpl();
    return fmlAbstractCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMCommand createFMCommand()
  {
    FMCommandImpl fmCommand = new FMCommandImpl();
    return fmCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FTCommand createFTCommand()
  {
    FTCommandImpl ftCommand = new FTCommandImpl();
    return ftCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BCommand createBCommand()
  {
    BCommandImpl bCommand = new BCommandImpl();
    return bCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StrCommand createStrCommand()
  {
    StrCommandImpl strCommand = new StrCommandImpl();
    return strCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigurationCommand createConfigurationCommand()
  {
    ConfigurationCommandImpl configurationCommand = new ConfigurationCommandImpl();
    return configurationCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCommand createSetCommand()
  {
    SetCommandImpl setCommand = new SetCommandImpl();
    return setCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintCommand createConstraintCommand()
  {
    ConstraintCommandImpl constraintCommand = new ConstraintCommandImpl();
    return constraintCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GetConstraints createGetConstraints()
  {
    GetConstraintsImpl getConstraints = new GetConstraintsImpl();
    return getConstraints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComputeConstraints createComputeConstraints()
  {
    ComputeConstraintsImpl computeConstraints = new ComputeConstraintsImpl();
    return computeConstraints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GetFGroups createGetFGroups()
  {
    GetFGroupsImpl getFGroups = new GetFGroupsImpl();
    return getFGroups;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComputeFGroups createComputeFGroups()
  {
    ComputeFGroupsImpl computeFGroups = new ComputeFGroupsImpl();
    return computeFGroups;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PairwiseCommand createPairwiseCommand()
  {
    PairwiseCommandImpl pairwiseCommand = new PairwiseCommandImpl();
    return pairwiseCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerCommand createIntegerCommand()
  {
    IntegerCommandImpl integerCommand = new IntegerCommandImpl();
    return integerCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoubleCommand createDoubleCommand()
  {
    DoubleCommandImpl doubleCommand = new DoubleCommandImpl();
    return doubleCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityOpCommand createVariabilityOpCommand()
  {
    VariabilityOpCommandImpl variabilityOpCommand = new VariabilityOpCommandImpl();
    return variabilityOpCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalysisOperation createAnalysisOperation()
  {
    AnalysisOperationImpl analysisOperation = new AnalysisOperationImpl();
    return analysisOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetOperations createSetOperations()
  {
    SetOperationsImpl setOperations = new SetOperationsImpl();
    return setOperations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetCard createSetCard()
  {
    SetCardImpl setCard = new SetCardImpl();
    return setCard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetBelongs createSetBelongs()
  {
    SetBelongsImpl setBelongs = new SetBelongsImpl();
    return setBelongs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetUnionOrIntersection createSetUnionOrIntersection()
  {
    SetUnionOrIntersectionImpl setUnionOrIntersection = new SetUnionOrIntersectionImpl();
    return setUnionOrIntersection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetEmpty createSetEmpty()
  {
    SetEmptyImpl setEmpty = new SetEmptyImpl();
    return setEmpty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetAddOrRemove createSetAddOrRemove()
  {
    SetAddOrRemoveImpl setAddOrRemove = new SetAddOrRemoveImpl();
    return setAddOrRemove;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IsEmptySet createIsEmptySet()
  {
    IsEmptySetImpl isEmptySet = new IsEmptySetImpl();
    return isEmptySet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetToNames createSetToNames()
  {
    SetToNamesImpl setToNames = new SetToNamesImpl();
    return setToNames;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureOperation createFeatureOperation()
  {
    FeatureOperationImpl featureOperation = new FeatureOperationImpl();
    return featureOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AncestorFeature createAncestorFeature()
  {
    AncestorFeatureImpl ancestorFeature = new AncestorFeatureImpl();
    return ancestorFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DescendantFeature createDescendantFeature()
  {
    DescendantFeatureImpl descendantFeature = new DescendantFeatureImpl();
    return descendantFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChildrenFeature createChildrenFeature()
  {
    ChildrenFeatureImpl childrenFeature = new ChildrenFeatureImpl();
    return childrenFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SiblingFeature createSiblingFeature()
  {
    SiblingFeatureImpl siblingFeature = new SiblingFeatureImpl();
    return siblingFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParentFeature createParentFeature()
  {
    ParentFeatureImpl parentFeature = new ParentFeatureImpl();
    return parentFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameFeature createNameFeature()
  {
    NameFeatureImpl nameFeature = new NameFeatureImpl();
    return nameFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMFeature createFMFeature()
  {
    FMFeatureImpl fmFeature = new FMFeatureImpl();
    return fmFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureOperator createFeatureOperator()
  {
    FeatureOperatorImpl featureOperator = new FeatureOperatorImpl();
    return featureOperator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringOperation createStringOperation()
  {
    StringOperationImpl stringOperation = new StringOperationImpl();
    return stringOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringInit createStringInit()
  {
    StringInitImpl stringInit = new StringInitImpl();
    return stringInit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringConcat createStringConcat()
  {
    StringConcatImpl stringConcat = new StringConcatImpl();
    return stringConcat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringSubstring createStringSubstring()
  {
    StringSubstringImpl stringSubstring = new StringSubstringImpl();
    return stringSubstring;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringIndexOf createStringIndexOf()
  {
    StringIndexOfImpl stringIndexOf = new StringIndexOfImpl();
    return stringIndexOf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringLength createStringLength()
  {
    StringLengthImpl stringLength = new StringLengthImpl();
    return stringLength;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Compare createCompare()
  {
    CompareImpl compare = new CompareImpl();
    return compare;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LoadGeneric createLoadGeneric()
  {
    LoadGenericImpl loadGeneric = new LoadGenericImpl();
    return loadGeneric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CTCRCommand createCTCRCommand()
  {
    CTCRCommandImpl ctcrCommand = new CTCRCommandImpl();
    return ctcrCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Merge createMerge()
  {
    MergeImpl merge = new MergeImpl();
    return merge;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LFMArgs createLFMArgs()
  {
    LFMArgsImpl lfmArgs = new LFMArgsImpl();
    return lfmArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AggregateMerge createAggregateMerge()
  {
    AggregateMergeImpl aggregateMerge = new AggregateMergeImpl();
    return aggregateMerge;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Synthesis createSynthesis()
  {
    SynthesisImpl synthesis = new SynthesisImpl();
    return synthesis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KnowledgeSpecification createKnowledgeSpecification()
  {
    KnowledgeSpecificationImpl knowledgeSpecification = new KnowledgeSpecificationImpl();
    return knowledgeSpecification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HierarchySpecification createHierarchySpecification()
  {
    HierarchySpecificationImpl hierarchySpecification = new HierarchySpecificationImpl();
    return hierarchySpecification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HProduction createHProduction()
  {
    HProductionImpl hProduction = new HProductionImpl();
    return hProduction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupsSpecification createGroupsSpecification()
  {
    GroupsSpecificationImpl groupsSpecification = new GroupsSpecificationImpl();
    return groupsSpecification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupSpec createGroupSpec()
  {
    GroupSpecImpl groupSpec = new GroupSpecImpl();
    return groupSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XorGroupSpec createXorGroupSpec()
  {
    XorGroupSpecImpl xorGroupSpec = new XorGroupSpecImpl();
    return xorGroupSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MtxGroupSpec createMtxGroupSpec()
  {
    MtxGroupSpecImpl mtxGroupSpec = new MtxGroupSpecImpl();
    return mtxGroupSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrGroupSpec createOrGroupSpec()
  {
    OrGroupSpecImpl orGroupSpec = new OrGroupSpecImpl();
    return orGroupSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintsSpecification createConstraintsSpecification()
  {
    ConstraintsSpecificationImpl constraintsSpecification = new ConstraintsSpecificationImpl();
    return constraintsSpecification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Slice createSlice()
  {
    SliceImpl slice = new SliceImpl();
    return slice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Aggregate createAggregate()
  {
    AggregateImpl aggregate = new AggregateImpl();
    return aggregate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureModelOperation createFeatureModelOperation()
  {
    FeatureModelOperationImpl featureModelOperation = new FeatureModelOperationImpl();
    return featureModelOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EditOperation createEditOperation()
  {
    EditOperationImpl editOperation = new EditOperationImpl();
    return editOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Insert createInsert()
  {
    InsertImpl insert = new InsertImpl();
    return insert;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RemoveFeature createRemoveFeature()
  {
    RemoveFeatureImpl removeFeature = new RemoveFeatureImpl();
    return removeFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RenameFeature createRenameFeature()
  {
    RenameFeatureImpl renameFeature = new RenameFeatureImpl();
    return renameFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Extract createExtract()
  {
    ExtractImpl extract = new ExtractImpl();
    return extract;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Assertion createAssertion()
  {
    AssertionImpl assertion = new AssertionImpl();
    return assertion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariableNull createVariableNull()
  {
    VariableNullImpl variableNull = new VariableNullImpl();
    return variableNull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Export createExport()
  {
    ExportImpl export = new ExportImpl();
    return export;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hidden createHidden()
  {
    HiddenImpl hidden = new HiddenImpl();
    return hidden;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LVidentifier createLVidentifier()
  {
    LVidentifierImpl lVidentifier = new LVidentifierImpl();
    return lVidentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Dependency createDependency()
  {
    DependencyImpl dependency = new DependencyImpl();
    return dependency;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigurationCmd createConfigurationCmd()
  {
    ConfigurationCmdImpl configurationCmd = new ConfigurationCmdImpl();
    return configurationCmd;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CreateConfiguration createCreateConfiguration()
  {
    CreateConfigurationImpl createConfiguration = new CreateConfigurationImpl();
    return createConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompleteConfiguration createCompleteConfiguration()
  {
    CompleteConfigurationImpl completeConfiguration = new CompleteConfigurationImpl();
    return completeConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelectionFeature createSelectionFeature()
  {
    SelectionFeatureImpl selectionFeature = new SelectionFeatureImpl();
    return selectionFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureExpression createFeatureExpression()
  {
    FeatureExpressionImpl featureExpression = new FeatureExpressionImpl();
    return featureExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AutoConfiguration createAutoConfiguration()
  {
    AutoConfigurationImpl autoConfiguration = new AutoConfigurationImpl();
    return autoConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelectedConfiguration createSelectedConfiguration()
  {
    SelectedConfigurationImpl selectedConfiguration = new SelectedConfigurationImpl();
    return selectedConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeselectedConfiguration createDeselectedConfiguration()
  {
    DeselectedConfigurationImpl deselectedConfiguration = new DeselectedConfigurationImpl();
    return deselectedConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnselectedConfiguration createUnselectedConfiguration()
  {
    UnselectedConfigurationImpl unselectedConfiguration = new UnselectedConfigurationImpl();
    return unselectedConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AsFM createAsFM()
  {
    AsFMImpl asFM = new AsFMImpl();
    return asFM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map createMap()
  {
    MapImpl map = new MapImpl();
    return map;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnMap createUnMap()
  {
    UnMapImpl unMap = new UnMapImpl();
    return unMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CleanUp createCleanUp()
  {
    CleanUpImpl cleanUp = new CleanUpImpl();
    return cleanUp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cores createCores()
  {
    CoresImpl cores = new CoresImpl();
    return cores;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Deads createDeads()
  {
    DeadsImpl deads = new DeadsImpl();
    return deads;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullMandatorys createFullMandatorys()
  {
    FullMandatorysImpl fullMandatorys = new FullMandatorysImpl();
    return fullMandatorys;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cliques createCliques()
  {
    CliquesImpl cliques = new CliquesImpl();
    return cliques;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScriptDefinition createScriptDefinition()
  {
    ScriptDefinitionImpl scriptDefinition = new ScriptDefinitionImpl();
    return scriptDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Shell createShell()
  {
    ShellImpl shell = new ShellImpl();
    return shell;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Exit createExit()
  {
    ExitImpl exit = new ExitImpl();
    return exit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Exist createExist()
  {
    ExistImpl exist = new ExistImpl();
    return exist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IsConflicting createIsConflicting()
  {
    IsConflictingImpl isConflicting = new IsConflictingImpl();
    return isConflicting;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Listing createListing()
  {
    ListingImpl listing = new ListingImpl();
    return listing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public State createState()
  {
    StateImpl state = new StateImpl();
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CopyVariable createCopyVariable()
  {
    CopyVariableImpl copyVariable = new CopyVariableImpl();
    return copyVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RemoveVariable createRemoveVariable()
  {
    RemoveVariableImpl removeVariable = new RemoveVariableImpl();
    return removeVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Convert createConvert()
  {
    ConvertImpl convert = new ConvertImpl();
    return convert;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLSave createFMLSave()
  {
    FMLSaveImpl fmlSave = new FMLSaveImpl();
    return fmlSave;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hierarchy createHierarchy()
  {
    HierarchyImpl hierarchy = new HierarchyImpl();
    return hierarchy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrinterUtility createPrinterUtility()
  {
    PrinterUtilityImpl printerUtility = new PrinterUtilityImpl();
    return printerUtility;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LArgs createLArgs()
  {
    LArgsImpl lArgs = new LArgsImpl();
    return lArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GDisplay createGDisplay()
  {
    GDisplayImpl gDisplay = new GDisplayImpl();
    return gDisplay;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GListing createGListing()
  {
    GListingImpl gListing = new GListingImpl();
    return gListing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModifyVOperator createModifyVOperator()
  {
    ModifyVOperatorImpl modifyVOperator = new ModifyVOperatorImpl();
    return modifyVOperator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MandatoryEdit createMandatoryEdit()
  {
    MandatoryEditImpl mandatoryEdit = new MandatoryEditImpl();
    return mandatoryEdit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OptionalEdit createOptionalEdit()
  {
    OptionalEditImpl optionalEdit = new OptionalEditImpl();
    return optionalEdit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlternativeEdit createAlternativeEdit()
  {
    AlternativeEditImpl alternativeEdit = new AlternativeEditImpl();
    return alternativeEdit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrEdit createOrEdit()
  {
    OrEditImpl orEdit = new OrEditImpl();
    return orEdit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AddConstraint createAddConstraint()
  {
    AddConstraintImpl addConstraint = new AddConstraintImpl();
    return addConstraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RemoveConstraint createRemoveConstraint()
  {
    RemoveConstraintImpl removeConstraint = new RemoveConstraintImpl();
    return removeConstraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CNF createCNF()
  {
    CNFImpl cnf = new CNFImpl();
    return cnf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CNFExpression createCNFExpression()
  {
    CNFExpressionImpl cnfExpression = new CNFExpressionImpl();
    return cnfExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Neg_expr createNeg_expr()
  {
    Neg_exprImpl neg_expr = new Neg_exprImpl();
    return neg_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureModel createFeatureModel()
  {
    FeatureModelImpl featureModel = new FeatureModelImpl();
    return featureModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Production createProduction()
  {
    ProductionImpl production = new ProductionImpl();
    return production;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Child createChild()
  {
    ChildImpl child = new ChildImpl();
    return child;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mandatory createMandatory()
  {
    MandatoryImpl mandatory = new MandatoryImpl();
    return mandatory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Optional createOptional()
  {
    OptionalImpl optional = new OptionalImpl();
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Xorgroup createXorgroup()
  {
    XorgroupImpl xorgroup = new XorgroupImpl();
    return xorgroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Orgroup createOrgroup()
  {
    OrgroupImpl orgroup = new OrgroupImpl();
    return orgroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mutexgroup createMutexgroup()
  {
    MutexgroupImpl mutexgroup = new MutexgroupImpl();
    return mutexgroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerOperation createIntegerOperation()
  {
    IntegerOperationImpl integerOperation = new IntegerOperationImpl();
    return integerOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoolOperation createBoolOperation()
  {
    BoolOperationImpl boolOperation = new BoolOperationImpl();
    return boolOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComparisonOperation createComparisonOperation()
  {
    ComparisonOperationImpl comparisonOperation = new ComparisonOperationImpl();
    return comparisonOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetOperation createSetOperation()
  {
    SetOperationImpl setOperation = new SetOperationImpl();
    return setOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntLiteral createIntLiteral()
  {
    IntLiteralImpl intLiteral = new IntLiteralImpl();
    return intLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Or_expr createOr_expr()
  {
    Or_exprImpl or_expr = new Or_exprImpl();
    return or_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public And_expr createAnd_expr()
  {
    And_exprImpl and_expr = new And_exprImpl();
    return and_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Impl_expr createImpl_expr()
  {
    Impl_exprImpl impl_expr = new Impl_exprImpl();
    return impl_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Biimpl_expr createBiimpl_expr()
  {
    Biimpl_exprImpl biimpl_expr = new Biimpl_exprImpl();
    return biimpl_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EditConstant createEditConstantFromString(EDataType eDataType, String initialValue)
  {
    EditConstant result = EditConstant.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertEditConstantToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureEdgeKind createFeatureEdgeKindFromString(EDataType eDataType, String initialValue)
  {
    FeatureEdgeKind result = FeatureEdgeKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertFeatureEdgeKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfGet createKindOfGetFromString(EDataType eDataType, String initialValue)
  {
    KindOfGet result = KindOfGet.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertKindOfGetToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfCompute createKindOfComputeFromString(EDataType eDataType, String initialValue)
  {
    KindOfCompute result = KindOfCompute.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertKindOfComputeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfGetGroups createKindOfGetGroupsFromString(EDataType eDataType, String initialValue)
  {
    KindOfGetGroups result = KindOfGetGroups.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertKindOfGetGroupsToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KindOfComputeGroups createKindOfComputeGroupsFromString(EDataType eDataType, String initialValue)
  {
    KindOfComputeGroups result = KindOfComputeGroups.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertKindOfComputeGroupsToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BDDBackend createBDDBackendFromString(EDataType eDataType, String initialValue)
  {
    BDDBackend result = BDDBackend.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertBDDBackendToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MergeMode createMergeModeFromString(EDataType eDataType, String initialValue)
  {
    MergeMode result = MergeMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMergeModeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SliceMode createSliceModeFromString(EDataType eDataType, String initialValue)
  {
    SliceMode result = SliceMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSliceModeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComparisonOperator createComparisonOperatorFromString(EDataType eDataType, String initialValue)
  {
    ComparisonOperator result = ComparisonOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertComparisonOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetOperator createSetOperatorFromString(EDataType eDataType, String initialValue)
  {
    SetOperator result = SetOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSetOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpSelection createOpSelectionFromString(EDataType eDataType, String initialValue)
  {
    OpSelection result = OpSelection.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertOpSelectionToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AutoConfMode createAutoConfModeFromString(EDataType eDataType, String initialValue)
  {
    AutoConfMode result = AutoConfMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertAutoConfModeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OPT_LISTING createOPT_LISTINGFromString(EDataType eDataType, String initialValue)
  {
    OPT_LISTING result = OPT_LISTING.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertOPT_LISTINGToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMFormat createFMFormatFromString(EDataType eDataType, String initialValue)
  {
    FMFormat result = FMFormat.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertFMFormatToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BOOL_Operator createBOOL_OperatorFromString(EDataType eDataType, String initialValue)
  {
    BOOL_Operator result = BOOL_Operator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertBOOL_OperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLPackage getFMLPackage()
  {
    return (FMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FMLPackage getPackage()
  {
    return FMLPackage.eINSTANCE;
  }

} //FMLFactoryImpl
