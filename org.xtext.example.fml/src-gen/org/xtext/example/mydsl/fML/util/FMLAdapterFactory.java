/**
 */
package org.xtext.example.mydsl.fML.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.xtext.example.mydsl.fML.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage
 * @generated
 */
public class FMLAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FMLPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = FMLPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FMLSwitch<Adapter> modelSwitch =
    new FMLSwitch<Adapter>()
    {
      @Override
      public Adapter caseFamiliarScript(FamiliarScript object)
      {
        return createFamiliarScriptAdapter();
      }
      @Override
      public Adapter caseScriptCommand(ScriptCommand object)
      {
        return createScriptCommandAdapter();
      }
      @Override
      public Adapter caseComplexCommand(ComplexCommand object)
      {
        return createComplexCommandAdapter();
      }
      @Override
      public Adapter caseCommand(Command object)
      {
        return createCommandAdapter();
      }
      @Override
      public Adapter caseIntegerExpr(IntegerExpr object)
      {
        return createIntegerExprAdapter();
      }
      @Override
      public Adapter caseBooleanExpr(BooleanExpr object)
      {
        return createBooleanExprAdapter();
      }
      @Override
      public Adapter caseIdentifierExpr(IdentifierExpr object)
      {
        return createIdentifierExprAdapter();
      }
      @Override
      public Adapter caseStringExpr(StringExpr object)
      {
        return createStringExprAdapter();
      }
      @Override
      public Adapter caseSetExpr(SetExpr object)
      {
        return createSetExprAdapter();
      }
      @Override
      public Adapter caseAtomicConstraintExpr(AtomicConstraintExpr object)
      {
        return createAtomicConstraintExprAdapter();
      }
      @Override
      public Adapter caseConstraintExpr(ConstraintExpr object)
      {
        return createConstraintExprAdapter();
      }
      @Override
      public Adapter caseFeatureVariabilityOperator(FeatureVariabilityOperator object)
      {
        return createFeatureVariabilityOperatorAdapter();
      }
      @Override
      public Adapter caseIfCondition(IfCondition object)
      {
        return createIfConditionAdapter();
      }
      @Override
      public Adapter caseForeachSet(ForeachSet object)
      {
        return createForeachSetAdapter();
      }
      @Override
      public Adapter caselType(lType object)
      {
        return createlTypeAdapter();
      }
      @Override
      public Adapter caseFMLAbstractCommand(FMLAbstractCommand object)
      {
        return createFMLAbstractCommandAdapter();
      }
      @Override
      public Adapter caseFMCommand(FMCommand object)
      {
        return createFMCommandAdapter();
      }
      @Override
      public Adapter caseFTCommand(FTCommand object)
      {
        return createFTCommandAdapter();
      }
      @Override
      public Adapter caseBCommand(BCommand object)
      {
        return createBCommandAdapter();
      }
      @Override
      public Adapter caseStrCommand(StrCommand object)
      {
        return createStrCommandAdapter();
      }
      @Override
      public Adapter caseConfigurationCommand(ConfigurationCommand object)
      {
        return createConfigurationCommandAdapter();
      }
      @Override
      public Adapter caseSetCommand(SetCommand object)
      {
        return createSetCommandAdapter();
      }
      @Override
      public Adapter caseLeaves(Leaves object)
      {
        return createLeavesAdapter();
      }
      @Override
      public Adapter caseConstraintCommand(ConstraintCommand object)
      {
        return createConstraintCommandAdapter();
      }
      @Override
      public Adapter caseGetConstraints(GetConstraints object)
      {
        return createGetConstraintsAdapter();
      }
      @Override
      public Adapter caseComputeConstraints(ComputeConstraints object)
      {
        return createComputeConstraintsAdapter();
      }
      @Override
      public Adapter caseGetFGroups(GetFGroups object)
      {
        return createGetFGroupsAdapter();
      }
      @Override
      public Adapter caseComputeFGroups(ComputeFGroups object)
      {
        return createComputeFGroupsAdapter();
      }
      @Override
      public Adapter casePairwiseCommand(PairwiseCommand object)
      {
        return createPairwiseCommandAdapter();
      }
      @Override
      public Adapter caseIntegerCommand(IntegerCommand object)
      {
        return createIntegerCommandAdapter();
      }
      @Override
      public Adapter caseDoubleCommand(DoubleCommand object)
      {
        return createDoubleCommandAdapter();
      }
      @Override
      public Adapter caseVariabilityOpCommand(VariabilityOpCommand object)
      {
        return createVariabilityOpCommandAdapter();
      }
      @Override
      public Adapter caseAnalysisOperation(AnalysisOperation object)
      {
        return createAnalysisOperationAdapter();
      }
      @Override
      public Adapter caseSetOperations(SetOperations object)
      {
        return createSetOperationsAdapter();
      }
      @Override
      public Adapter caseSetCard(SetCard object)
      {
        return createSetCardAdapter();
      }
      @Override
      public Adapter caseSetBelongs(SetBelongs object)
      {
        return createSetBelongsAdapter();
      }
      @Override
      public Adapter caseSetUnionOrIntersection(SetUnionOrIntersection object)
      {
        return createSetUnionOrIntersectionAdapter();
      }
      @Override
      public Adapter caseSetEmpty(SetEmpty object)
      {
        return createSetEmptyAdapter();
      }
      @Override
      public Adapter caseSetAddOrRemove(SetAddOrRemove object)
      {
        return createSetAddOrRemoveAdapter();
      }
      @Override
      public Adapter caseIsEmptySet(IsEmptySet object)
      {
        return createIsEmptySetAdapter();
      }
      @Override
      public Adapter caseSetToNames(SetToNames object)
      {
        return createSetToNamesAdapter();
      }
      @Override
      public Adapter caseFeatureOperation(FeatureOperation object)
      {
        return createFeatureOperationAdapter();
      }
      @Override
      public Adapter caseAncestorFeature(AncestorFeature object)
      {
        return createAncestorFeatureAdapter();
      }
      @Override
      public Adapter caseDescendantFeature(DescendantFeature object)
      {
        return createDescendantFeatureAdapter();
      }
      @Override
      public Adapter caseChildrenFeature(ChildrenFeature object)
      {
        return createChildrenFeatureAdapter();
      }
      @Override
      public Adapter caseSiblingFeature(SiblingFeature object)
      {
        return createSiblingFeatureAdapter();
      }
      @Override
      public Adapter caseParentFeature(ParentFeature object)
      {
        return createParentFeatureAdapter();
      }
      @Override
      public Adapter caseNameFeature(NameFeature object)
      {
        return createNameFeatureAdapter();
      }
      @Override
      public Adapter caseFMFeature(FMFeature object)
      {
        return createFMFeatureAdapter();
      }
      @Override
      public Adapter caseFeatureOperator(FeatureOperator object)
      {
        return createFeatureOperatorAdapter();
      }
      @Override
      public Adapter caseStringOperation(StringOperation object)
      {
        return createStringOperationAdapter();
      }
      @Override
      public Adapter caseStringInit(StringInit object)
      {
        return createStringInitAdapter();
      }
      @Override
      public Adapter caseStringConcat(StringConcat object)
      {
        return createStringConcatAdapter();
      }
      @Override
      public Adapter caseStringSubstring(StringSubstring object)
      {
        return createStringSubstringAdapter();
      }
      @Override
      public Adapter caseStringIndexOf(StringIndexOf object)
      {
        return createStringIndexOfAdapter();
      }
      @Override
      public Adapter caseStringLength(StringLength object)
      {
        return createStringLengthAdapter();
      }
      @Override
      public Adapter caseCompare(Compare object)
      {
        return createCompareAdapter();
      }
      @Override
      public Adapter caseParameter(Parameter object)
      {
        return createParameterAdapter();
      }
      @Override
      public Adapter caseLoadGeneric(LoadGeneric object)
      {
        return createLoadGenericAdapter();
      }
      @Override
      public Adapter caseCTCRCommand(CTCRCommand object)
      {
        return createCTCRCommandAdapter();
      }
      @Override
      public Adapter caseMerge(Merge object)
      {
        return createMergeAdapter();
      }
      @Override
      public Adapter caseLFMArgs(LFMArgs object)
      {
        return createLFMArgsAdapter();
      }
      @Override
      public Adapter caseAggregateMerge(AggregateMerge object)
      {
        return createAggregateMergeAdapter();
      }
      @Override
      public Adapter caseSynthesis(Synthesis object)
      {
        return createSynthesisAdapter();
      }
      @Override
      public Adapter caseKnowledgeSpecification(KnowledgeSpecification object)
      {
        return createKnowledgeSpecificationAdapter();
      }
      @Override
      public Adapter caseHierarchySpecification(HierarchySpecification object)
      {
        return createHierarchySpecificationAdapter();
      }
      @Override
      public Adapter caseHProduction(HProduction object)
      {
        return createHProductionAdapter();
      }
      @Override
      public Adapter caseGroupsSpecification(GroupsSpecification object)
      {
        return createGroupsSpecificationAdapter();
      }
      @Override
      public Adapter caseGroupSpec(GroupSpec object)
      {
        return createGroupSpecAdapter();
      }
      @Override
      public Adapter caseXorGroupSpec(XorGroupSpec object)
      {
        return createXorGroupSpecAdapter();
      }
      @Override
      public Adapter caseMtxGroupSpec(MtxGroupSpec object)
      {
        return createMtxGroupSpecAdapter();
      }
      @Override
      public Adapter caseOrGroupSpec(OrGroupSpec object)
      {
        return createOrGroupSpecAdapter();
      }
      @Override
      public Adapter caseConstraintsSpecification(ConstraintsSpecification object)
      {
        return createConstraintsSpecificationAdapter();
      }
      @Override
      public Adapter caseSlice(Slice object)
      {
        return createSliceAdapter();
      }
      @Override
      public Adapter caseAggregate(Aggregate object)
      {
        return createAggregateAdapter();
      }
      @Override
      public Adapter caseFeatureModelOperation(FeatureModelOperation object)
      {
        return createFeatureModelOperationAdapter();
      }
      @Override
      public Adapter caseEditOperation(EditOperation object)
      {
        return createEditOperationAdapter();
      }
      @Override
      public Adapter caseInsert(Insert object)
      {
        return createInsertAdapter();
      }
      @Override
      public Adapter caseRemoveFeature(RemoveFeature object)
      {
        return createRemoveFeatureAdapter();
      }
      @Override
      public Adapter caseRenameFeature(RenameFeature object)
      {
        return createRenameFeatureAdapter();
      }
      @Override
      public Adapter caseExtract(Extract object)
      {
        return createExtractAdapter();
      }
      @Override
      public Adapter caseAssertion(Assertion object)
      {
        return createAssertionAdapter();
      }
      @Override
      public Adapter caseVariableNull(VariableNull object)
      {
        return createVariableNullAdapter();
      }
      @Override
      public Adapter caseExport(Export object)
      {
        return createExportAdapter();
      }
      @Override
      public Adapter caseHidden(Hidden object)
      {
        return createHiddenAdapter();
      }
      @Override
      public Adapter caseLVidentifier(LVidentifier object)
      {
        return createLVidentifierAdapter();
      }
      @Override
      public Adapter caseDependency(Dependency object)
      {
        return createDependencyAdapter();
      }
      @Override
      public Adapter caseConfigurationCmd(ConfigurationCmd object)
      {
        return createConfigurationCmdAdapter();
      }
      @Override
      public Adapter caseCreateConfiguration(CreateConfiguration object)
      {
        return createCreateConfigurationAdapter();
      }
      @Override
      public Adapter caseCompleteConfiguration(CompleteConfiguration object)
      {
        return createCompleteConfigurationAdapter();
      }
      @Override
      public Adapter caseSelectionFeature(SelectionFeature object)
      {
        return createSelectionFeatureAdapter();
      }
      @Override
      public Adapter caseFeatureExpression(FeatureExpression object)
      {
        return createFeatureExpressionAdapter();
      }
      @Override
      public Adapter caseAutoConfiguration(AutoConfiguration object)
      {
        return createAutoConfigurationAdapter();
      }
      @Override
      public Adapter caseSelectedConfiguration(SelectedConfiguration object)
      {
        return createSelectedConfigurationAdapter();
      }
      @Override
      public Adapter caseDeselectedConfiguration(DeselectedConfiguration object)
      {
        return createDeselectedConfigurationAdapter();
      }
      @Override
      public Adapter caseUnselectedConfiguration(UnselectedConfiguration object)
      {
        return createUnselectedConfigurationAdapter();
      }
      @Override
      public Adapter caseAsFM(AsFM object)
      {
        return createAsFMAdapter();
      }
      @Override
      public Adapter caseMap(Map object)
      {
        return createMapAdapter();
      }
      @Override
      public Adapter caseUnMap(UnMap object)
      {
        return createUnMapAdapter();
      }
      @Override
      public Adapter caseCleanUp(CleanUp object)
      {
        return createCleanUpAdapter();
      }
      @Override
      public Adapter caseCores(Cores object)
      {
        return createCoresAdapter();
      }
      @Override
      public Adapter caseDeads(Deads object)
      {
        return createDeadsAdapter();
      }
      @Override
      public Adapter caseFullMandatorys(FullMandatorys object)
      {
        return createFullMandatorysAdapter();
      }
      @Override
      public Adapter caseCliques(Cliques object)
      {
        return createCliquesAdapter();
      }
      @Override
      public Adapter caseScriptDefinition(ScriptDefinition object)
      {
        return createScriptDefinitionAdapter();
      }
      @Override
      public Adapter caseShell(Shell object)
      {
        return createShellAdapter();
      }
      @Override
      public Adapter caseExit(Exit object)
      {
        return createExitAdapter();
      }
      @Override
      public Adapter caseExist(Exist object)
      {
        return createExistAdapter();
      }
      @Override
      public Adapter caseIsConflicting(IsConflicting object)
      {
        return createIsConflictingAdapter();
      }
      @Override
      public Adapter caseListing(Listing object)
      {
        return createListingAdapter();
      }
      @Override
      public Adapter caseState(State object)
      {
        return createStateAdapter();
      }
      @Override
      public Adapter caseCopyVariable(CopyVariable object)
      {
        return createCopyVariableAdapter();
      }
      @Override
      public Adapter caseRemoveVariable(RemoveVariable object)
      {
        return createRemoveVariableAdapter();
      }
      @Override
      public Adapter caseConvert(Convert object)
      {
        return createConvertAdapter();
      }
      @Override
      public Adapter caseFMLSave(FMLSave object)
      {
        return createFMLSaveAdapter();
      }
      @Override
      public Adapter caseHierarchy(Hierarchy object)
      {
        return createHierarchyAdapter();
      }
      @Override
      public Adapter casePrinterUtility(PrinterUtility object)
      {
        return createPrinterUtilityAdapter();
      }
      @Override
      public Adapter caseLArgs(LArgs object)
      {
        return createLArgsAdapter();
      }
      @Override
      public Adapter caseGDisplay(GDisplay object)
      {
        return createGDisplayAdapter();
      }
      @Override
      public Adapter caseGListing(GListing object)
      {
        return createGListingAdapter();
      }
      @Override
      public Adapter caseModifyVOperator(ModifyVOperator object)
      {
        return createModifyVOperatorAdapter();
      }
      @Override
      public Adapter caseMandatoryEdit(MandatoryEdit object)
      {
        return createMandatoryEditAdapter();
      }
      @Override
      public Adapter caseOptionalEdit(OptionalEdit object)
      {
        return createOptionalEditAdapter();
      }
      @Override
      public Adapter caseAlternativeEdit(AlternativeEdit object)
      {
        return createAlternativeEditAdapter();
      }
      @Override
      public Adapter caseOrEdit(OrEdit object)
      {
        return createOrEditAdapter();
      }
      @Override
      public Adapter caseAddConstraint(AddConstraint object)
      {
        return createAddConstraintAdapter();
      }
      @Override
      public Adapter caseRemoveConstraint(RemoveConstraint object)
      {
        return createRemoveConstraintAdapter();
      }
      @Override
      public Adapter caseCNF(CNF object)
      {
        return createCNFAdapter();
      }
      @Override
      public Adapter caseCNFExpression(CNFExpression object)
      {
        return createCNFExpressionAdapter();
      }
      @Override
      public Adapter caseNeg_expr(Neg_expr object)
      {
        return createNeg_exprAdapter();
      }
      @Override
      public Adapter caseFeatureModel(FeatureModel object)
      {
        return createFeatureModelAdapter();
      }
      @Override
      public Adapter caseProduction(Production object)
      {
        return createProductionAdapter();
      }
      @Override
      public Adapter caseChild(Child object)
      {
        return createChildAdapter();
      }
      @Override
      public Adapter caseMandatory(Mandatory object)
      {
        return createMandatoryAdapter();
      }
      @Override
      public Adapter caseOptional(Optional object)
      {
        return createOptionalAdapter();
      }
      @Override
      public Adapter caseXorgroup(Xorgroup object)
      {
        return createXorgroupAdapter();
      }
      @Override
      public Adapter caseOrgroup(Orgroup object)
      {
        return createOrgroupAdapter();
      }
      @Override
      public Adapter caseMutexgroup(Mutexgroup object)
      {
        return createMutexgroupAdapter();
      }
      @Override
      public Adapter caseIntegerOperation(IntegerOperation object)
      {
        return createIntegerOperationAdapter();
      }
      @Override
      public Adapter caseBoolOperation(BoolOperation object)
      {
        return createBoolOperationAdapter();
      }
      @Override
      public Adapter caseComparisonOperation(ComparisonOperation object)
      {
        return createComparisonOperationAdapter();
      }
      @Override
      public Adapter caseSetOperation(SetOperation object)
      {
        return createSetOperationAdapter();
      }
      @Override
      public Adapter caseIntLiteral(IntLiteral object)
      {
        return createIntLiteralAdapter();
      }
      @Override
      public Adapter caseOr_expr(Or_expr object)
      {
        return createOr_exprAdapter();
      }
      @Override
      public Adapter caseAnd_expr(And_expr object)
      {
        return createAnd_exprAdapter();
      }
      @Override
      public Adapter caseImpl_expr(Impl_expr object)
      {
        return createImpl_exprAdapter();
      }
      @Override
      public Adapter caseBiimpl_expr(Biimpl_expr object)
      {
        return createBiimpl_exprAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
	  //TOCHANGE
    return modelSwitch.doSwitch(0,(EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FamiliarScript <em>Familiar Script</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FamiliarScript
   * @generated
   */
  public Adapter createFamiliarScriptAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ScriptCommand <em>Script Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ScriptCommand
   * @generated
   */
  public Adapter createScriptCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ComplexCommand <em>Complex Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ComplexCommand
   * @generated
   */
  public Adapter createComplexCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Command <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Command
   * @generated
   */
  public Adapter createCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IntegerExpr <em>Integer Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IntegerExpr
   * @generated
   */
  public Adapter createIntegerExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.BooleanExpr <em>Boolean Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.BooleanExpr
   * @generated
   */
  public Adapter createBooleanExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IdentifierExpr <em>Identifier Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IdentifierExpr
   * @generated
   */
  public Adapter createIdentifierExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringExpr <em>String Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringExpr
   * @generated
   */
  public Adapter createStringExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetExpr <em>Set Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetExpr
   * @generated
   */
  public Adapter createSetExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AtomicConstraintExpr <em>Atomic Constraint Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AtomicConstraintExpr
   * @generated
   */
  public Adapter createAtomicConstraintExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ConstraintExpr <em>Constraint Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ConstraintExpr
   * @generated
   */
  public Adapter createConstraintExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureVariabilityOperator <em>Feature Variability Operator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureVariabilityOperator
   * @generated
   */
  public Adapter createFeatureVariabilityOperatorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IfCondition <em>If Condition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IfCondition
   * @generated
   */
  public Adapter createIfConditionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ForeachSet <em>Foreach Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ForeachSet
   * @generated
   */
  public Adapter createForeachSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.lType <em>lType</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.lType
   * @generated
   */
  public Adapter createlTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FMLAbstractCommand <em>Abstract Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FMLAbstractCommand
   * @generated
   */
  public Adapter createFMLAbstractCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FMCommand <em>FM Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FMCommand
   * @generated
   */
  public Adapter createFMCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FTCommand <em>FT Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FTCommand
   * @generated
   */
  public Adapter createFTCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.BCommand <em>BCommand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.BCommand
   * @generated
   */
  public Adapter createBCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StrCommand <em>Str Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StrCommand
   * @generated
   */
  public Adapter createStrCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ConfigurationCommand <em>Configuration Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ConfigurationCommand
   * @generated
   */
  public Adapter createConfigurationCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetCommand <em>Set Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetCommand
   * @generated
   */
  public Adapter createSetCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Leaves <em>Leaves</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Leaves
   * @generated
   */
  public Adapter createLeavesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ConstraintCommand <em>Constraint Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ConstraintCommand
   * @generated
   */
  public Adapter createConstraintCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GetConstraints <em>Get Constraints</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GetConstraints
   * @generated
   */
  public Adapter createGetConstraintsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ComputeConstraints <em>Compute Constraints</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ComputeConstraints
   * @generated
   */
  public Adapter createComputeConstraintsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GetFGroups <em>Get FGroups</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GetFGroups
   * @generated
   */
  public Adapter createGetFGroupsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ComputeFGroups <em>Compute FGroups</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ComputeFGroups
   * @generated
   */
  public Adapter createComputeFGroupsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.PairwiseCommand <em>Pairwise Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.PairwiseCommand
   * @generated
   */
  public Adapter createPairwiseCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IntegerCommand <em>Integer Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IntegerCommand
   * @generated
   */
  public Adapter createIntegerCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.DoubleCommand <em>Double Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.DoubleCommand
   * @generated
   */
  public Adapter createDoubleCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.VariabilityOpCommand <em>Variability Op Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.VariabilityOpCommand
   * @generated
   */
  public Adapter createVariabilityOpCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AnalysisOperation <em>Analysis Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AnalysisOperation
   * @generated
   */
  public Adapter createAnalysisOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetOperations <em>Set Operations</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetOperations
   * @generated
   */
  public Adapter createSetOperationsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetCard <em>Set Card</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetCard
   * @generated
   */
  public Adapter createSetCardAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetBelongs <em>Set Belongs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetBelongs
   * @generated
   */
  public Adapter createSetBelongsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection <em>Set Union Or Intersection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetUnionOrIntersection
   * @generated
   */
  public Adapter createSetUnionOrIntersectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetEmpty <em>Set Empty</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetEmpty
   * @generated
   */
  public Adapter createSetEmptyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetAddOrRemove <em>Set Add Or Remove</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetAddOrRemove
   * @generated
   */
  public Adapter createSetAddOrRemoveAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IsEmptySet <em>Is Empty Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IsEmptySet
   * @generated
   */
  public Adapter createIsEmptySetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetToNames <em>Set To Names</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetToNames
   * @generated
   */
  public Adapter createSetToNamesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureOperation <em>Feature Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureOperation
   * @generated
   */
  public Adapter createFeatureOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AncestorFeature <em>Ancestor Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AncestorFeature
   * @generated
   */
  public Adapter createAncestorFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.DescendantFeature <em>Descendant Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.DescendantFeature
   * @generated
   */
  public Adapter createDescendantFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ChildrenFeature <em>Children Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ChildrenFeature
   * @generated
   */
  public Adapter createChildrenFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SiblingFeature <em>Sibling Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SiblingFeature
   * @generated
   */
  public Adapter createSiblingFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ParentFeature <em>Parent Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ParentFeature
   * @generated
   */
  public Adapter createParentFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.NameFeature <em>Name Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.NameFeature
   * @generated
   */
  public Adapter createNameFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FMFeature <em>FM Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FMFeature
   * @generated
   */
  public Adapter createFMFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureOperator <em>Feature Operator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureOperator
   * @generated
   */
  public Adapter createFeatureOperatorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringOperation <em>String Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringOperation
   * @generated
   */
  public Adapter createStringOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringInit <em>String Init</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringInit
   * @generated
   */
  public Adapter createStringInitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringConcat <em>String Concat</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringConcat
   * @generated
   */
  public Adapter createStringConcatAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringSubstring <em>String Substring</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringSubstring
   * @generated
   */
  public Adapter createStringSubstringAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringIndexOf <em>String Index Of</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringIndexOf
   * @generated
   */
  public Adapter createStringIndexOfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.StringLength <em>String Length</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.StringLength
   * @generated
   */
  public Adapter createStringLengthAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Compare <em>Compare</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Compare
   * @generated
   */
  public Adapter createCompareAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Parameter
   * @generated
   */
  public Adapter createParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.LoadGeneric <em>Load Generic</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.LoadGeneric
   * @generated
   */
  public Adapter createLoadGenericAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CTCRCommand <em>CTCR Command</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CTCRCommand
   * @generated
   */
  public Adapter createCTCRCommandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Merge <em>Merge</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Merge
   * @generated
   */
  public Adapter createMergeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.LFMArgs <em>LFM Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.LFMArgs
   * @generated
   */
  public Adapter createLFMArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AggregateMerge <em>Aggregate Merge</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AggregateMerge
   * @generated
   */
  public Adapter createAggregateMergeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Synthesis <em>Synthesis</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Synthesis
   * @generated
   */
  public Adapter createSynthesisAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.KnowledgeSpecification <em>Knowledge Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.KnowledgeSpecification
   * @generated
   */
  public Adapter createKnowledgeSpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.HierarchySpecification <em>Hierarchy Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.HierarchySpecification
   * @generated
   */
  public Adapter createHierarchySpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.HProduction <em>HProduction</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.HProduction
   * @generated
   */
  public Adapter createHProductionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GroupsSpecification <em>Groups Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GroupsSpecification
   * @generated
   */
  public Adapter createGroupsSpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GroupSpec <em>Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GroupSpec
   * @generated
   */
  public Adapter createGroupSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.XorGroupSpec <em>Xor Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.XorGroupSpec
   * @generated
   */
  public Adapter createXorGroupSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.MtxGroupSpec <em>Mtx Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.MtxGroupSpec
   * @generated
   */
  public Adapter createMtxGroupSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.OrGroupSpec <em>Or Group Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.OrGroupSpec
   * @generated
   */
  public Adapter createOrGroupSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ConstraintsSpecification <em>Constraints Specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ConstraintsSpecification
   * @generated
   */
  public Adapter createConstraintsSpecificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Slice <em>Slice</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Slice
   * @generated
   */
  public Adapter createSliceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Aggregate <em>Aggregate</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Aggregate
   * @generated
   */
  public Adapter createAggregateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureModelOperation <em>Feature Model Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureModelOperation
   * @generated
   */
  public Adapter createFeatureModelOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.EditOperation <em>Edit Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.EditOperation
   * @generated
   */
  public Adapter createEditOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Insert <em>Insert</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Insert
   * @generated
   */
  public Adapter createInsertAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.RemoveFeature <em>Remove Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.RemoveFeature
   * @generated
   */
  public Adapter createRemoveFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.RenameFeature <em>Rename Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.RenameFeature
   * @generated
   */
  public Adapter createRenameFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Extract <em>Extract</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Extract
   * @generated
   */
  public Adapter createExtractAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Assertion <em>Assertion</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Assertion
   * @generated
   */
  public Adapter createAssertionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.VariableNull <em>Variable Null</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.VariableNull
   * @generated
   */
  public Adapter createVariableNullAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Export <em>Export</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Export
   * @generated
   */
  public Adapter createExportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Hidden <em>Hidden</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Hidden
   * @generated
   */
  public Adapter createHiddenAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.LVidentifier <em>LVidentifier</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.LVidentifier
   * @generated
   */
  public Adapter createLVidentifierAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Dependency <em>Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Dependency
   * @generated
   */
  public Adapter createDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ConfigurationCmd <em>Configuration Cmd</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ConfigurationCmd
   * @generated
   */
  public Adapter createConfigurationCmdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CreateConfiguration <em>Create Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CreateConfiguration
   * @generated
   */
  public Adapter createCreateConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CompleteConfiguration <em>Complete Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CompleteConfiguration
   * @generated
   */
  public Adapter createCompleteConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SelectionFeature <em>Selection Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SelectionFeature
   * @generated
   */
  public Adapter createSelectionFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureExpression <em>Feature Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureExpression
   * @generated
   */
  public Adapter createFeatureExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AutoConfiguration <em>Auto Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AutoConfiguration
   * @generated
   */
  public Adapter createAutoConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SelectedConfiguration <em>Selected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SelectedConfiguration
   * @generated
   */
  public Adapter createSelectedConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.DeselectedConfiguration <em>Deselected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.DeselectedConfiguration
   * @generated
   */
  public Adapter createDeselectedConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.UnselectedConfiguration <em>Unselected Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.UnselectedConfiguration
   * @generated
   */
  public Adapter createUnselectedConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AsFM <em>As FM</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AsFM
   * @generated
   */
  public Adapter createAsFMAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Map <em>Map</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Map
   * @generated
   */
  public Adapter createMapAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.UnMap <em>Un Map</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.UnMap
   * @generated
   */
  public Adapter createUnMapAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CleanUp <em>Clean Up</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CleanUp
   * @generated
   */
  public Adapter createCleanUpAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Cores <em>Cores</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Cores
   * @generated
   */
  public Adapter createCoresAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Deads <em>Deads</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Deads
   * @generated
   */
  public Adapter createDeadsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FullMandatorys <em>Full Mandatorys</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FullMandatorys
   * @generated
   */
  public Adapter createFullMandatorysAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Cliques <em>Cliques</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Cliques
   * @generated
   */
  public Adapter createCliquesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ScriptDefinition <em>Script Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ScriptDefinition
   * @generated
   */
  public Adapter createScriptDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Shell <em>Shell</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Shell
   * @generated
   */
  public Adapter createShellAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Exit <em>Exit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Exit
   * @generated
   */
  public Adapter createExitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Exist <em>Exist</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Exist
   * @generated
   */
  public Adapter createExistAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IsConflicting <em>Is Conflicting</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IsConflicting
   * @generated
   */
  public Adapter createIsConflictingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Listing <em>Listing</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Listing
   * @generated
   */
  public Adapter createListingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.State <em>State</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.State
   * @generated
   */
  public Adapter createStateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CopyVariable <em>Copy Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CopyVariable
   * @generated
   */
  public Adapter createCopyVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.RemoveVariable <em>Remove Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.RemoveVariable
   * @generated
   */
  public Adapter createRemoveVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Convert <em>Convert</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Convert
   * @generated
   */
  public Adapter createConvertAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FMLSave <em>Save</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FMLSave
   * @generated
   */
  public Adapter createFMLSaveAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Hierarchy <em>Hierarchy</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Hierarchy
   * @generated
   */
  public Adapter createHierarchyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.PrinterUtility <em>Printer Utility</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.PrinterUtility
   * @generated
   */
  public Adapter createPrinterUtilityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.LArgs <em>LArgs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.LArgs
   * @generated
   */
  public Adapter createLArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GDisplay <em>GDisplay</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GDisplay
   * @generated
   */
  public Adapter createGDisplayAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.GListing <em>GListing</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.GListing
   * @generated
   */
  public Adapter createGListingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ModifyVOperator <em>Modify VOperator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ModifyVOperator
   * @generated
   */
  public Adapter createModifyVOperatorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.MandatoryEdit <em>Mandatory Edit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.MandatoryEdit
   * @generated
   */
  public Adapter createMandatoryEditAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.OptionalEdit <em>Optional Edit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.OptionalEdit
   * @generated
   */
  public Adapter createOptionalEditAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AlternativeEdit <em>Alternative Edit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AlternativeEdit
   * @generated
   */
  public Adapter createAlternativeEditAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.OrEdit <em>Or Edit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.OrEdit
   * @generated
   */
  public Adapter createOrEditAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.AddConstraint <em>Add Constraint</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.AddConstraint
   * @generated
   */
  public Adapter createAddConstraintAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.RemoveConstraint <em>Remove Constraint</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.RemoveConstraint
   * @generated
   */
  public Adapter createRemoveConstraintAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CNF <em>CNF</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CNF
   * @generated
   */
  public Adapter createCNFAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.CNFExpression <em>CNF Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.CNFExpression
   * @generated
   */
  public Adapter createCNFExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Neg_expr <em>Neg expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Neg_expr
   * @generated
   */
  public Adapter createNeg_exprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.FeatureModel <em>Feature Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.FeatureModel
   * @generated
   */
  public Adapter createFeatureModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Production <em>Production</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Production
   * @generated
   */
  public Adapter createProductionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Child <em>Child</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Child
   * @generated
   */
  public Adapter createChildAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Mandatory <em>Mandatory</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Mandatory
   * @generated
   */
  public Adapter createMandatoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Optional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Optional
   * @generated
   */
  public Adapter createOptionalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Xorgroup <em>Xorgroup</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Xorgroup
   * @generated
   */
  public Adapter createXorgroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Orgroup <em>Orgroup</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Orgroup
   * @generated
   */
  public Adapter createOrgroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Mutexgroup <em>Mutexgroup</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Mutexgroup
   * @generated
   */
  public Adapter createMutexgroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IntegerOperation <em>Integer Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IntegerOperation
   * @generated
   */
  public Adapter createIntegerOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.BoolOperation <em>Bool Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.BoolOperation
   * @generated
   */
  public Adapter createBoolOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.ComparisonOperation <em>Comparison Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.ComparisonOperation
   * @generated
   */
  public Adapter createComparisonOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.SetOperation <em>Set Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.SetOperation
   * @generated
   */
  public Adapter createSetOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.IntLiteral <em>Int Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.IntLiteral
   * @generated
   */
  public Adapter createIntLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Or_expr <em>Or expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Or_expr
   * @generated
   */
  public Adapter createOr_exprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.And_expr <em>And expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.And_expr
   * @generated
   */
  public Adapter createAnd_exprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Impl_expr <em>Impl expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Impl_expr
   * @generated
   */
  public Adapter createImpl_exprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.fML.Biimpl_expr <em>Biimpl expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.fML.Biimpl_expr
   * @generated
   */
  public Adapter createBiimpl_exprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //FMLAdapterFactory
