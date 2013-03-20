/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage
 * @generated
 */
public interface FMLFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FMLFactory eINSTANCE = org.xtext.example.mydsl.fML.impl.FMLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Familiar Script</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Familiar Script</em>'.
   * @generated
   */
  FamiliarScript createFamiliarScript();

  /**
   * Returns a new object of class '<em>Script Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Script Command</em>'.
   * @generated
   */
  ScriptCommand createScriptCommand();

  /**
   * Returns a new object of class '<em>Complex Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Complex Command</em>'.
   * @generated
   */
  ComplexCommand createComplexCommand();

  /**
   * Returns a new object of class '<em>Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Command</em>'.
   * @generated
   */
  Command createCommand();

  /**
   * Returns a new object of class '<em>Integer Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Expr</em>'.
   * @generated
   */
  IntegerExpr createIntegerExpr();

  /**
   * Returns a new object of class '<em>Boolean Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Expr</em>'.
   * @generated
   */
  BooleanExpr createBooleanExpr();

  /**
   * Returns a new object of class '<em>Identifier Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Identifier Expr</em>'.
   * @generated
   */
  IdentifierExpr createIdentifierExpr();

  /**
   * Returns a new object of class '<em>String Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Expr</em>'.
   * @generated
   */
  StringExpr createStringExpr();

  /**
   * Returns a new object of class '<em>Set Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Expr</em>'.
   * @generated
   */
  SetExpr createSetExpr();

  /**
   * Returns a new object of class '<em>Atomic Constraint Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Atomic Constraint Expr</em>'.
   * @generated
   */
  AtomicConstraintExpr createAtomicConstraintExpr();

  /**
   * Returns a new object of class '<em>Constraint Expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraint Expr</em>'.
   * @generated
   */
  ConstraintExpr createConstraintExpr();

  /**
   * Returns a new object of class '<em>Feature Variability Operator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Variability Operator</em>'.
   * @generated
   */
  FeatureVariabilityOperator createFeatureVariabilityOperator();

  /**
   * Returns a new object of class '<em>If Condition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If Condition</em>'.
   * @generated
   */
  IfCondition createIfCondition();

  /**
   * Returns a new object of class '<em>Foreach Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Foreach Set</em>'.
   * @generated
   */
  ForeachSet createForeachSet();

  /**
   * Returns a new object of class '<em>lType</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>lType</em>'.
   * @generated
   */
  lType createlType();

  /**
   * Returns a new object of class '<em>Abstract Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Abstract Command</em>'.
   * @generated
   */
  FMLAbstractCommand createFMLAbstractCommand();

  /**
   * Returns a new object of class '<em>FM Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>FM Command</em>'.
   * @generated
   */
  FMCommand createFMCommand();

  /**
   * Returns a new object of class '<em>FT Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>FT Command</em>'.
   * @generated
   */
  FTCommand createFTCommand();

  /**
   * Returns a new object of class '<em>BCommand</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>BCommand</em>'.
   * @generated
   */
  BCommand createBCommand();

  /**
   * Returns a new object of class '<em>Str Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Str Command</em>'.
   * @generated
   */
  StrCommand createStrCommand();

  /**
   * Returns a new object of class '<em>Configuration Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Configuration Command</em>'.
   * @generated
   */
  ConfigurationCommand createConfigurationCommand();

  /**
   * Returns a new object of class '<em>Set Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Command</em>'.
   * @generated
   */
  SetCommand createSetCommand();

  /**
   * Returns a new object of class '<em>Leaves</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Leaves</em>'.
   * @generated
   */
  Leaves createLeaves();

  /**
   * Returns a new object of class '<em>Constraint Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraint Command</em>'.
   * @generated
   */
  ConstraintCommand createConstraintCommand();

  /**
   * Returns a new object of class '<em>Get Constraints</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Get Constraints</em>'.
   * @generated
   */
  GetConstraints createGetConstraints();

  /**
   * Returns a new object of class '<em>Compute Constraints</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compute Constraints</em>'.
   * @generated
   */
  ComputeConstraints createComputeConstraints();

  /**
   * Returns a new object of class '<em>Get FGroups</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Get FGroups</em>'.
   * @generated
   */
  GetFGroups createGetFGroups();

  /**
   * Returns a new object of class '<em>Compute FGroups</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compute FGroups</em>'.
   * @generated
   */
  ComputeFGroups createComputeFGroups();

  /**
   * Returns a new object of class '<em>Pairwise Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pairwise Command</em>'.
   * @generated
   */
  PairwiseCommand createPairwiseCommand();

  /**
   * Returns a new object of class '<em>Integer Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Command</em>'.
   * @generated
   */
  IntegerCommand createIntegerCommand();

  /**
   * Returns a new object of class '<em>Double Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Double Command</em>'.
   * @generated
   */
  DoubleCommand createDoubleCommand();

  /**
   * Returns a new object of class '<em>Variability Op Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variability Op Command</em>'.
   * @generated
   */
  VariabilityOpCommand createVariabilityOpCommand();

  /**
   * Returns a new object of class '<em>Analysis Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Analysis Operation</em>'.
   * @generated
   */
  AnalysisOperation createAnalysisOperation();

  /**
   * Returns a new object of class '<em>Set Operations</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Operations</em>'.
   * @generated
   */
  SetOperations createSetOperations();

  /**
   * Returns a new object of class '<em>Set Card</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Card</em>'.
   * @generated
   */
  SetCard createSetCard();

  /**
   * Returns a new object of class '<em>Set Belongs</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Belongs</em>'.
   * @generated
   */
  SetBelongs createSetBelongs();

  /**
   * Returns a new object of class '<em>Set Union Or Intersection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Union Or Intersection</em>'.
   * @generated
   */
  SetUnionOrIntersection createSetUnionOrIntersection();

  /**
   * Returns a new object of class '<em>Set Empty</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Empty</em>'.
   * @generated
   */
  SetEmpty createSetEmpty();

  /**
   * Returns a new object of class '<em>Set Add Or Remove</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Add Or Remove</em>'.
   * @generated
   */
  SetAddOrRemove createSetAddOrRemove();

  /**
   * Returns a new object of class '<em>Is Empty Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Is Empty Set</em>'.
   * @generated
   */
  IsEmptySet createIsEmptySet();

  /**
   * Returns a new object of class '<em>Set To Names</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set To Names</em>'.
   * @generated
   */
  SetToNames createSetToNames();

  /**
   * Returns a new object of class '<em>Feature Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Operation</em>'.
   * @generated
   */
  FeatureOperation createFeatureOperation();

  /**
   * Returns a new object of class '<em>Ancestor Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ancestor Feature</em>'.
   * @generated
   */
  AncestorFeature createAncestorFeature();

  /**
   * Returns a new object of class '<em>Descendant Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Descendant Feature</em>'.
   * @generated
   */
  DescendantFeature createDescendantFeature();

  /**
   * Returns a new object of class '<em>Children Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Children Feature</em>'.
   * @generated
   */
  ChildrenFeature createChildrenFeature();

  /**
   * Returns a new object of class '<em>Sibling Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Sibling Feature</em>'.
   * @generated
   */
  SiblingFeature createSiblingFeature();

  /**
   * Returns a new object of class '<em>Parent Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parent Feature</em>'.
   * @generated
   */
  ParentFeature createParentFeature();

  /**
   * Returns a new object of class '<em>Name Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Name Feature</em>'.
   * @generated
   */
  NameFeature createNameFeature();

  /**
   * Returns a new object of class '<em>FM Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>FM Feature</em>'.
   * @generated
   */
  FMFeature createFMFeature();

  /**
   * Returns a new object of class '<em>Feature Operator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Operator</em>'.
   * @generated
   */
  FeatureOperator createFeatureOperator();

  /**
   * Returns a new object of class '<em>String Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Operation</em>'.
   * @generated
   */
  StringOperation createStringOperation();

  /**
   * Returns a new object of class '<em>String Init</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Init</em>'.
   * @generated
   */
  StringInit createStringInit();

  /**
   * Returns a new object of class '<em>String Concat</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Concat</em>'.
   * @generated
   */
  StringConcat createStringConcat();

  /**
   * Returns a new object of class '<em>String Substring</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Substring</em>'.
   * @generated
   */
  StringSubstring createStringSubstring();

  /**
   * Returns a new object of class '<em>String Index Of</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Index Of</em>'.
   * @generated
   */
  StringIndexOf createStringIndexOf();

  /**
   * Returns a new object of class '<em>String Length</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Length</em>'.
   * @generated
   */
  StringLength createStringLength();

  /**
   * Returns a new object of class '<em>Compare</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compare</em>'.
   * @generated
   */
  Compare createCompare();

  /**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
  Parameter createParameter();

  /**
   * Returns a new object of class '<em>Load Generic</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Load Generic</em>'.
   * @generated
   */
  LoadGeneric createLoadGeneric();

  /**
   * Returns a new object of class '<em>CTCR Command</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>CTCR Command</em>'.
   * @generated
   */
  CTCRCommand createCTCRCommand();

  /**
   * Returns a new object of class '<em>Merge</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Merge</em>'.
   * @generated
   */
  Merge createMerge();

  /**
   * Returns a new object of class '<em>LFM Args</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>LFM Args</em>'.
   * @generated
   */
  LFMArgs createLFMArgs();

  /**
   * Returns a new object of class '<em>Aggregate Merge</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Aggregate Merge</em>'.
   * @generated
   */
  AggregateMerge createAggregateMerge();

  /**
   * Returns a new object of class '<em>Synthesis</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Synthesis</em>'.
   * @generated
   */
  Synthesis createSynthesis();

  /**
   * Returns a new object of class '<em>Knowledge Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Knowledge Specification</em>'.
   * @generated
   */
  KnowledgeSpecification createKnowledgeSpecification();

  /**
   * Returns a new object of class '<em>Hierarchy Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hierarchy Specification</em>'.
   * @generated
   */
  HierarchySpecification createHierarchySpecification();

  /**
   * Returns a new object of class '<em>HProduction</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>HProduction</em>'.
   * @generated
   */
  HProduction createHProduction();

  /**
   * Returns a new object of class '<em>Groups Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Groups Specification</em>'.
   * @generated
   */
  GroupsSpecification createGroupsSpecification();

  /**
   * Returns a new object of class '<em>Group Spec</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Spec</em>'.
   * @generated
   */
  GroupSpec createGroupSpec();

  /**
   * Returns a new object of class '<em>Xor Group Spec</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Xor Group Spec</em>'.
   * @generated
   */
  XorGroupSpec createXorGroupSpec();

  /**
   * Returns a new object of class '<em>Mtx Group Spec</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mtx Group Spec</em>'.
   * @generated
   */
  MtxGroupSpec createMtxGroupSpec();

  /**
   * Returns a new object of class '<em>Or Group Spec</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Group Spec</em>'.
   * @generated
   */
  OrGroupSpec createOrGroupSpec();

  /**
   * Returns a new object of class '<em>Constraints Specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraints Specification</em>'.
   * @generated
   */
  ConstraintsSpecification createConstraintsSpecification();

  /**
   * Returns a new object of class '<em>Slice</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Slice</em>'.
   * @generated
   */
  Slice createSlice();

  /**
   * Returns a new object of class '<em>Aggregate</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Aggregate</em>'.
   * @generated
   */
  Aggregate createAggregate();

  /**
   * Returns a new object of class '<em>Feature Model Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Model Operation</em>'.
   * @generated
   */
  FeatureModelOperation createFeatureModelOperation();

  /**
   * Returns a new object of class '<em>Edit Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Edit Operation</em>'.
   * @generated
   */
  EditOperation createEditOperation();

  /**
   * Returns a new object of class '<em>Insert</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Insert</em>'.
   * @generated
   */
  Insert createInsert();

  /**
   * Returns a new object of class '<em>Remove Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Remove Feature</em>'.
   * @generated
   */
  RemoveFeature createRemoveFeature();

  /**
   * Returns a new object of class '<em>Rename Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Rename Feature</em>'.
   * @generated
   */
  RenameFeature createRenameFeature();

  /**
   * Returns a new object of class '<em>Extract</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Extract</em>'.
   * @generated
   */
  Extract createExtract();

  /**
   * Returns a new object of class '<em>Assertion</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Assertion</em>'.
   * @generated
   */
  Assertion createAssertion();

  /**
   * Returns a new object of class '<em>Variable Null</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable Null</em>'.
   * @generated
   */
  VariableNull createVariableNull();

  /**
   * Returns a new object of class '<em>Export</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Export</em>'.
   * @generated
   */
  Export createExport();

  /**
   * Returns a new object of class '<em>Hidden</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hidden</em>'.
   * @generated
   */
  Hidden createHidden();

  /**
   * Returns a new object of class '<em>LVidentifier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>LVidentifier</em>'.
   * @generated
   */
  LVidentifier createLVidentifier();

  /**
   * Returns a new object of class '<em>Dependency</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Dependency</em>'.
   * @generated
   */
  Dependency createDependency();

  /**
   * Returns a new object of class '<em>Configuration Cmd</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Configuration Cmd</em>'.
   * @generated
   */
  ConfigurationCmd createConfigurationCmd();

  /**
   * Returns a new object of class '<em>Create Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Create Configuration</em>'.
   * @generated
   */
  CreateConfiguration createCreateConfiguration();

  /**
   * Returns a new object of class '<em>Complete Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Complete Configuration</em>'.
   * @generated
   */
  CompleteConfiguration createCompleteConfiguration();

  /**
   * Returns a new object of class '<em>Selection Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Selection Feature</em>'.
   * @generated
   */
  SelectionFeature createSelectionFeature();

  /**
   * Returns a new object of class '<em>Feature Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Expression</em>'.
   * @generated
   */
  FeatureExpression createFeatureExpression();

  /**
   * Returns a new object of class '<em>Auto Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Auto Configuration</em>'.
   * @generated
   */
  AutoConfiguration createAutoConfiguration();

  /**
   * Returns a new object of class '<em>Selected Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Selected Configuration</em>'.
   * @generated
   */
  SelectedConfiguration createSelectedConfiguration();

  /**
   * Returns a new object of class '<em>Deselected Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Deselected Configuration</em>'.
   * @generated
   */
  DeselectedConfiguration createDeselectedConfiguration();

  /**
   * Returns a new object of class '<em>Unselected Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unselected Configuration</em>'.
   * @generated
   */
  UnselectedConfiguration createUnselectedConfiguration();

  /**
   * Returns a new object of class '<em>As FM</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>As FM</em>'.
   * @generated
   */
  AsFM createAsFM();

  /**
   * Returns a new object of class '<em>Map</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Map</em>'.
   * @generated
   */
  Map createMap();

  /**
   * Returns a new object of class '<em>Un Map</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Un Map</em>'.
   * @generated
   */
  UnMap createUnMap();

  /**
   * Returns a new object of class '<em>Clean Up</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Clean Up</em>'.
   * @generated
   */
  CleanUp createCleanUp();

  /**
   * Returns a new object of class '<em>Cores</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Cores</em>'.
   * @generated
   */
  Cores createCores();

  /**
   * Returns a new object of class '<em>Deads</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Deads</em>'.
   * @generated
   */
  Deads createDeads();

  /**
   * Returns a new object of class '<em>Full Mandatorys</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Full Mandatorys</em>'.
   * @generated
   */
  FullMandatorys createFullMandatorys();

  /**
   * Returns a new object of class '<em>Cliques</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Cliques</em>'.
   * @generated
   */
  Cliques createCliques();

  /**
   * Returns a new object of class '<em>Script Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Script Definition</em>'.
   * @generated
   */
  ScriptDefinition createScriptDefinition();

  /**
   * Returns a new object of class '<em>Shell</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Shell</em>'.
   * @generated
   */
  Shell createShell();

  /**
   * Returns a new object of class '<em>Exit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Exit</em>'.
   * @generated
   */
  Exit createExit();

  /**
   * Returns a new object of class '<em>Exist</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Exist</em>'.
   * @generated
   */
  Exist createExist();

  /**
   * Returns a new object of class '<em>Is Conflicting</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Is Conflicting</em>'.
   * @generated
   */
  IsConflicting createIsConflicting();

  /**
   * Returns a new object of class '<em>Listing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Listing</em>'.
   * @generated
   */
  Listing createListing();

  /**
   * Returns a new object of class '<em>State</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>State</em>'.
   * @generated
   */
  State createState();

  /**
   * Returns a new object of class '<em>Copy Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Copy Variable</em>'.
   * @generated
   */
  CopyVariable createCopyVariable();

  /**
   * Returns a new object of class '<em>Remove Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Remove Variable</em>'.
   * @generated
   */
  RemoveVariable createRemoveVariable();

  /**
   * Returns a new object of class '<em>Convert</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Convert</em>'.
   * @generated
   */
  Convert createConvert();

  /**
   * Returns a new object of class '<em>Save</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Save</em>'.
   * @generated
   */
  FMLSave createFMLSave();

  /**
   * Returns a new object of class '<em>Hierarchy</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hierarchy</em>'.
   * @generated
   */
  Hierarchy createHierarchy();

  /**
   * Returns a new object of class '<em>Printer Utility</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Printer Utility</em>'.
   * @generated
   */
  PrinterUtility createPrinterUtility();

  /**
   * Returns a new object of class '<em>LArgs</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>LArgs</em>'.
   * @generated
   */
  LArgs createLArgs();

  /**
   * Returns a new object of class '<em>GDisplay</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>GDisplay</em>'.
   * @generated
   */
  GDisplay createGDisplay();

  /**
   * Returns a new object of class '<em>GListing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>GListing</em>'.
   * @generated
   */
  GListing createGListing();

  /**
   * Returns a new object of class '<em>Modify VOperator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Modify VOperator</em>'.
   * @generated
   */
  ModifyVOperator createModifyVOperator();

  /**
   * Returns a new object of class '<em>Mandatory Edit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mandatory Edit</em>'.
   * @generated
   */
  MandatoryEdit createMandatoryEdit();

  /**
   * Returns a new object of class '<em>Optional Edit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Optional Edit</em>'.
   * @generated
   */
  OptionalEdit createOptionalEdit();

  /**
   * Returns a new object of class '<em>Alternative Edit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Alternative Edit</em>'.
   * @generated
   */
  AlternativeEdit createAlternativeEdit();

  /**
   * Returns a new object of class '<em>Or Edit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Edit</em>'.
   * @generated
   */
  OrEdit createOrEdit();

  /**
   * Returns a new object of class '<em>Add Constraint</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Add Constraint</em>'.
   * @generated
   */
  AddConstraint createAddConstraint();

  /**
   * Returns a new object of class '<em>Remove Constraint</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Remove Constraint</em>'.
   * @generated
   */
  RemoveConstraint createRemoveConstraint();

  /**
   * Returns a new object of class '<em>CNF</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>CNF</em>'.
   * @generated
   */
  CNF createCNF();

  /**
   * Returns a new object of class '<em>CNF Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>CNF Expression</em>'.
   * @generated
   */
  CNFExpression createCNFExpression();

  /**
   * Returns a new object of class '<em>Neg expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Neg expr</em>'.
   * @generated
   */
  Neg_expr createNeg_expr();

  /**
   * Returns a new object of class '<em>Feature Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Model</em>'.
   * @generated
   */
  FeatureModel createFeatureModel();

  /**
   * Returns a new object of class '<em>Production</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Production</em>'.
   * @generated
   */
  Production createProduction();

  /**
   * Returns a new object of class '<em>Child</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Child</em>'.
   * @generated
   */
  Child createChild();

  /**
   * Returns a new object of class '<em>Mandatory</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mandatory</em>'.
   * @generated
   */
  Mandatory createMandatory();

  /**
   * Returns a new object of class '<em>Optional</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Optional</em>'.
   * @generated
   */
  Optional createOptional();

  /**
   * Returns a new object of class '<em>Xorgroup</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Xorgroup</em>'.
   * @generated
   */
  Xorgroup createXorgroup();

  /**
   * Returns a new object of class '<em>Orgroup</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Orgroup</em>'.
   * @generated
   */
  Orgroup createOrgroup();

  /**
   * Returns a new object of class '<em>Mutexgroup</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mutexgroup</em>'.
   * @generated
   */
  Mutexgroup createMutexgroup();

  /**
   * Returns a new object of class '<em>Integer Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Operation</em>'.
   * @generated
   */
  IntegerOperation createIntegerOperation();

  /**
   * Returns a new object of class '<em>Bool Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bool Operation</em>'.
   * @generated
   */
  BoolOperation createBoolOperation();

  /**
   * Returns a new object of class '<em>Comparison Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Comparison Operation</em>'.
   * @generated
   */
  ComparisonOperation createComparisonOperation();

  /**
   * Returns a new object of class '<em>Set Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Set Operation</em>'.
   * @generated
   */
  SetOperation createSetOperation();

  /**
   * Returns a new object of class '<em>Int Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Int Literal</em>'.
   * @generated
   */
  IntLiteral createIntLiteral();

  /**
   * Returns a new object of class '<em>Or expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or expr</em>'.
   * @generated
   */
  Or_expr createOr_expr();

  /**
   * Returns a new object of class '<em>And expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>And expr</em>'.
   * @generated
   */
  And_expr createAnd_expr();

  /**
   * Returns a new object of class '<em>Impl expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Impl expr</em>'.
   * @generated
   */
  Impl_expr createImpl_expr();

  /**
   * Returns a new object of class '<em>Biimpl expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Biimpl expr</em>'.
   * @generated
   */
  Biimpl_expr createBiimpl_expr();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  FMLPackage getFMLPackage();

} //FMLFactory
