/**
 */
package org.xtext.example.mydsl.fML.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.xtext.example.mydsl.fML.FMLFactory;
import org.xtext.example.mydsl.fML.FMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FMLPackageImpl extends EPackageImpl implements FMLPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected String packageFilename = "fML.ecore";

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass familiarScriptEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scriptCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass complexCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass booleanExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass identifierExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass atomicConstraintExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraintExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureVariabilityOperatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifConditionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass foreachSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fmlAbstractCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fmCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ftCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass strCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configurationCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraintCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass getConstraintsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass computeConstraintsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass getFGroupsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass computeFGroupsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pairwiseCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass doubleCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variabilityOpCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass analysisOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setOperationsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setCardEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setBelongsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setUnionOrIntersectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setEmptyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setAddOrRemoveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass isEmptySetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setToNamesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ancestorFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass descendantFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass childrenFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass siblingFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parentFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nameFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fmFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureOperatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringInitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringConcatEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringSubstringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringIndexOfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringLengthEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compareEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass loadGenericEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ctcrCommandEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mergeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lfmArgsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aggregateMergeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass synthesisEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass knowledgeSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hierarchySpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hProductionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupsSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xorGroupSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mtxGroupSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orGroupSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraintsSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sliceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aggregateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureModelOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass editOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass insertEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass removeFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass renameFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extractEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assertionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableNullEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exportEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hiddenEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lVidentifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dependencyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configurationCmdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass createConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass completeConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectionFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass autoConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectedConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass deselectedConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unselectedConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass asFMEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mapEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unMapEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cleanUpEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass coresEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass deadsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fullMandatorysEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cliquesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scriptDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass shellEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass existEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass isConflictingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass copyVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass removeVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass convertEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fmlSaveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass hierarchyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass printerUtilityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lArgsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gDisplayEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gListingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modifyVOperatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mandatoryEditEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass optionalEditEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass alternativeEditEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orEditEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass addConstraintEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass removeConstraintEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cnfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cnfExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass neg_exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass productionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass childEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mandatoryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass optionalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xorgroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orgroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mutexgroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass boolOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass comparisonOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass intLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass or_exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass and_exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass impl_exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass biimpl_exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum editConstantEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum featureEdgeKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum kindOfGetEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum kindOfComputeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum kindOfGetGroupsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum kindOfComputeGroupsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum bddBackendEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum mergeModeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum sliceModeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum comparisonOperatorEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum setOperatorEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum opSelectionEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum autoConfModeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum opT_LISTINGEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum fmFormatEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum booL_OperatorEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.xtext.example.mydsl.fML.FMLPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private FMLPackageImpl()
  {
    super(eNS_URI, FMLFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link FMLPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @generated
   */
  public static FMLPackage init()
  {
    if (isInited) return (FMLPackage)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI);

    // Obtain or create and register package
    FMLPackageImpl theFMLPackage = (FMLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FMLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FMLPackageImpl());

    isInited = true;

    // Load packages
    theFMLPackage.loadPackage();

    // Fix loaded packages
    theFMLPackage.fixPackageContents();

    // Mark meta-data to indicate it can't be changed
    theFMLPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(FMLPackage.eNS_URI, theFMLPackage);
    return theFMLPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFamiliarScript()
  {
    if (familiarScriptEClass == null)
    {
      familiarScriptEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(0);
    }
    return familiarScriptEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFamiliarScript_Params()
  {
        return (EReference)getFamiliarScript().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFamiliarScript_Cmds()
  {
        return (EReference)getFamiliarScript().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFamiliarScript_Exports()
  {
        return (EReference)getFamiliarScript().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScriptCommand()
  {
    if (scriptCommandEClass == null)
    {
      scriptCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(1);
    }
    return scriptCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getScriptCommand_Var()
  {
        return (EAttribute)getScriptCommand().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScriptCommand_MetaID()
  {
        return (EReference)getScriptCommand().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScriptCommand_Cmd()
  {
        return (EReference)getScriptCommand().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComplexCommand()
  {
    if (complexCommandEClass == null)
    {
      complexCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(2);
    }
    return complexCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComplexCommand_Left()
  {
        return (EReference)getComplexCommand().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComplexCommand_Not()
  {
        return (EAttribute)getComplexCommand().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComplexCommand_Batom()
  {
        return (EReference)getComplexCommand().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCommand()
  {
    if (commandEClass == null)
    {
      commandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(3);
    }
    return commandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerExpr()
  {
    if (integerExprEClass == null)
    {
      integerExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(4);
    }
    return integerExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBooleanExpr()
  {
    if (booleanExprEClass == null)
    {
      booleanExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(5);
    }
    return booleanExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBooleanExpr_Val()
  {
        return (EAttribute)getBooleanExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIdentifierExpr()
  {
    if (identifierExprEClass == null)
    {
      identifierExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(6);
    }
    return identifierExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifierExpr_Val()
  {
        return (EAttribute)getIdentifierExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIdentifierExpr_MetaID()
  {
        return (EReference)getIdentifierExpr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringExpr()
  {
    if (stringExprEClass == null)
    {
      stringExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(7);
    }
    return stringExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStringExpr_Val()
  {
        return (EAttribute)getStringExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetExpr()
  {
    if (setExprEClass == null)
    {
      setExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(8);
    }
    return setExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetExpr_E()
  {
        return (EReference)getSetExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAtomicConstraintExpr()
  {
    if (atomicConstraintExprEClass == null)
    {
      atomicConstraintExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(9);
    }
    return atomicConstraintExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAtomicConstraintExpr_Expr()
  {
        return (EReference)getAtomicConstraintExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstraintExpr()
  {
    if (constraintExprEClass == null)
    {
      constraintExprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(10);
    }
    return constraintExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstraintExpr_Constraints()
  {
        return (EReference)getConstraintExpr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstraintExpr_Fm()
  {
        return (EReference)getConstraintExpr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureVariabilityOperator()
  {
    if (featureVariabilityOperatorEClass == null)
    {
      featureVariabilityOperatorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(12);
    }
    return featureVariabilityOperatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureVariabilityOperator_Val()
  {
        return (EAttribute)getFeatureVariabilityOperator().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfCondition()
  {
    if (ifConditionEClass == null)
    {
      ifConditionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(14);
    }
    return ifConditionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfCondition_Bexpr()
  {
        return (EReference)getIfCondition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfCondition_Then()
  {
        return (EReference)getIfCondition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfCondition_Else()
  {
        return (EReference)getIfCondition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getForeachSet()
  {
    if (foreachSetEClass == null)
    {
      foreachSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(15);
    }
    return foreachSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForeachSet_Val()
  {
        return (EAttribute)getForeachSet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForeachSet_Var()
  {
        return (EAttribute)getForeachSet().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForeachSet_Exprs()
  {
        return (EReference)getForeachSet().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getlType()
  {
    if (lTypeEClass == null)
    {
      lTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(16);
    }
    return lTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlType_Val()
  {
        return (EAttribute)getlType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFMLAbstractCommand()
  {
    if (fmlAbstractCommandEClass == null)
    {
      fmlAbstractCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(17);
    }
    return fmlAbstractCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFMCommand()
  {
    if (fmCommandEClass == null)
    {
      fmCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(18);
    }
    return fmCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFTCommand()
  {
    if (ftCommandEClass == null)
    {
      ftCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(19);
    }
    return ftCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBCommand()
  {
    if (bCommandEClass == null)
    {
      bCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(20);
    }
    return bCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStrCommand()
  {
    if (strCommandEClass == null)
    {
      strCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(21);
    }
    return strCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigurationCommand()
  {
    if (configurationCommandEClass == null)
    {
      configurationCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(22);
    }
    return configurationCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetCommand()
  {
    if (setCommandEClass == null)
    {
      setCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(23);
    }
    return setCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstraintCommand()
  {
    if (constraintCommandEClass == null)
    {
      constraintCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(24);
    }
    return constraintCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGetConstraints()
  {
    if (getConstraintsEClass == null)
    {
      getConstraintsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(25);
    }
    return getConstraintsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGetConstraints_KindOfGet()
  {
        return (EAttribute)getGetConstraints().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGetConstraints_Fm()
  {
        return (EReference)getGetConstraints().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComputeConstraints()
  {
    if (computeConstraintsEClass == null)
    {
      computeConstraintsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(27);
    }
    return computeConstraintsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComputeConstraints_KindOfCompute()
  {
        return (EAttribute)getComputeConstraints().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComputeConstraints_Fm()
  {
        return (EReference)getComputeConstraints().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGetFGroups()
  {
    if (getFGroupsEClass == null)
    {
      getFGroupsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(29);
    }
    return getFGroupsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGetFGroups_KindOfGroups()
  {
        return (EAttribute)getGetFGroups().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGetFGroups_Fm()
  {
        return (EReference)getGetFGroups().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComputeFGroups()
  {
    if (computeFGroupsEClass == null)
    {
      computeFGroupsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(31);
    }
    return computeFGroupsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComputeFGroups_KindOfGroups()
  {
        return (EAttribute)getComputeFGroups().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComputeFGroups_Fm()
  {
        return (EReference)getComputeFGroups().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPairwiseCommand()
  {
    if (pairwiseCommandEClass == null)
    {
      pairwiseCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(33);
    }
    return pairwiseCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPairwiseCommand_Fm()
  {
        return (EReference)getPairwiseCommand().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPairwiseCommand_Minimization()
  {
        return (EReference)getPairwiseCommand().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPairwiseCommand_Partial()
  {
        return (EReference)getPairwiseCommand().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerCommand()
  {
    if (integerCommandEClass == null)
    {
      integerCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(34);
    }
    return integerCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDoubleCommand()
  {
    if (doubleCommandEClass == null)
    {
      doubleCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(35);
    }
    return doubleCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariabilityOpCommand()
  {
    if (variabilityOpCommandEClass == null)
    {
      variabilityOpCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(36);
    }
    return variabilityOpCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnalysisOperation()
  {
    if (analysisOperationEClass == null)
    {
      analysisOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(37);
    }
    return analysisOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnalysisOperation_Op()
  {
        return (EAttribute)getAnalysisOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnalysisOperation_Fm()
  {
        return (EReference)getAnalysisOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetOperations()
  {
    if (setOperationsEClass == null)
    {
      setOperationsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(38);
    }
    return setOperationsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetCard()
  {
    if (setCardEClass == null)
    {
      setCardEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(39);
    }
    return setCardEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetCard_Set()
  {
        return (EReference)getSetCard().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetBelongs()
  {
    if (setBelongsEClass == null)
    {
      setBelongsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(40);
    }
    return setBelongsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetBelongs_Setl()
  {
        return (EAttribute)getSetBelongs().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetBelongs_Setr()
  {
        return (EAttribute)getSetBelongs().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetUnionOrIntersection()
  {
    if (setUnionOrIntersectionEClass == null)
    {
      setUnionOrIntersectionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(41);
    }
    return setUnionOrIntersectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetUnionOrIntersection_Op()
  {
        return (EAttribute)getSetUnionOrIntersection().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetUnionOrIntersection_Setl()
  {
        return (EReference)getSetUnionOrIntersection().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetUnionOrIntersection_Setr()
  {
        return (EReference)getSetUnionOrIntersection().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetEmpty()
  {
    if (setEmptyEClass == null)
    {
      setEmptyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(42);
    }
    return setEmptyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetEmpty_Val()
  {
        return (EAttribute)getSetEmpty().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetAddOrRemove()
  {
    if (setAddOrRemoveEClass == null)
    {
      setAddOrRemoveEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(43);
    }
    return setAddOrRemoveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetAddOrRemove_Op()
  {
        return (EAttribute)getSetAddOrRemove().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetAddOrRemove_Setl()
  {
        return (EReference)getSetAddOrRemove().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetAddOrRemove_Var()
  {
        return (EReference)getSetAddOrRemove().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIsEmptySet()
  {
    if (isEmptySetEClass == null)
    {
      isEmptySetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(44);
    }
    return isEmptySetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIsEmptySet_Set()
  {
        return (EReference)getIsEmptySet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetToNames()
  {
    if (setToNamesEClass == null)
    {
      setToNamesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(45);
    }
    return setToNamesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetToNames_Set()
  {
        return (EReference)getSetToNames().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureOperation()
  {
    if (featureOperationEClass == null)
    {
      featureOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(46);
    }
    return featureOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureOperation_Op()
  {
        return (EReference)getFeatureOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureOperation_Feature()
  {
        return (EReference)getFeatureOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAncestorFeature()
  {
    if (ancestorFeatureEClass == null)
    {
      ancestorFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(47);
    }
    return ancestorFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAncestorFeature_Val()
  {
        return (EAttribute)getAncestorFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDescendantFeature()
  {
    if (descendantFeatureEClass == null)
    {
      descendantFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(48);
    }
    return descendantFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDescendantFeature_Val()
  {
        return (EAttribute)getDescendantFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getChildrenFeature()
  {
    if (childrenFeatureEClass == null)
    {
      childrenFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(49);
    }
    return childrenFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getChildrenFeature_Val()
  {
        return (EAttribute)getChildrenFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSiblingFeature()
  {
    if (siblingFeatureEClass == null)
    {
      siblingFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(50);
    }
    return siblingFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSiblingFeature_Val()
  {
        return (EAttribute)getSiblingFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParentFeature()
  {
    if (parentFeatureEClass == null)
    {
      parentFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(51);
    }
    return parentFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParentFeature_Val()
  {
        return (EAttribute)getParentFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNameFeature()
  {
    if (nameFeatureEClass == null)
    {
      nameFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(52);
    }
    return nameFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNameFeature_Val()
  {
        return (EAttribute)getNameFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFMFeature()
  {
    if (fmFeatureEClass == null)
    {
      fmFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(53);
    }
    return fmFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFMFeature_Val()
  {
        return (EAttribute)getFMFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureOperator()
  {
    if (featureOperatorEClass == null)
    {
      featureOperatorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(54);
    }
    return featureOperatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureOperator_Val()
  {
        return (EAttribute)getFeatureOperator().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringOperation()
  {
    if (stringOperationEClass == null)
    {
      stringOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(55);
    }
    return stringOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringInit()
  {
    if (stringInitEClass == null)
    {
      stringInitEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(56);
    }
    return stringInitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStringInit_Val()
  {
        return (EAttribute)getStringInit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringConcat()
  {
    if (stringConcatEClass == null)
    {
      stringConcatEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(57);
    }
    return stringConcatEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringConcat_Lstr()
  {
        return (EReference)getStringConcat().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringConcat_Rstr()
  {
        return (EReference)getStringConcat().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringSubstring()
  {
    if (stringSubstringEClass == null)
    {
      stringSubstringEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(58);
    }
    return stringSubstringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringSubstring_Str()
  {
        return (EReference)getStringSubstring().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringSubstring_Begin()
  {
        return (EReference)getStringSubstring().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringSubstring_End()
  {
        return (EReference)getStringSubstring().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringIndexOf()
  {
    if (stringIndexOfEClass == null)
    {
      stringIndexOfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(59);
    }
    return stringIndexOfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringIndexOf_Str()
  {
        return (EReference)getStringIndexOf().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringIndexOf_Schar()
  {
        return (EReference)getStringIndexOf().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringLength()
  {
    if (stringLengthEClass == null)
    {
      stringLengthEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(60);
    }
    return stringLengthEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStringLength_Str()
  {
        return (EReference)getStringLength().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompare()
  {
    if (compareEClass == null)
    {
      compareEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(61);
    }
    return compareEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompare_Fm_left()
  {
        return (EReference)getCompare().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompare_Fm_right()
  {
        return (EReference)getCompare().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameter()
  {
    if (parameterEClass == null)
    {
      parameterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(62);
    }
    return parameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameter_Param()
  {
        return (EAttribute)getParameter().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameter_Typed()
  {
        return (EAttribute)getParameter().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameter_Type()
  {
        return (EReference)getParameter().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLoadGeneric()
  {
    if (loadGenericEClass == null)
    {
      loadGenericEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(63);
    }
    return loadGenericEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLoadGeneric_Stream()
  {
        return (EAttribute)getLoadGeneric().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLoadGeneric_Params()
  {
        return (EAttribute)getLoadGeneric().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLoadGeneric_Ns()
  {
        return (EAttribute)getLoadGeneric().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCTCRCommand()
  {
    if (ctcrCommandEClass == null)
    {
      ctcrCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(64);
    }
    return ctcrCommandEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCTCRCommand_Fm()
  {
        return (EReference)getCTCRCommand().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMerge()
  {
    if (mergeEClass == null)
    {
      mergeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(65);
    }
    return mergeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMerge_Backend()
  {
        return (EAttribute)getMerge().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMerge_Mode()
  {
        return (EAttribute)getMerge().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMerge_Lfms()
  {
        return (EReference)getMerge().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMerge_Fms()
  {
        return (EReference)getMerge().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLFMArgs()
  {
    if (lfmArgsEClass == null)
    {
      lfmArgsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(68);
    }
    return lfmArgsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLFMArgs_Lfms()
  {
        return (EReference)getLFMArgs().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAggregateMerge()
  {
    if (aggregateMergeEClass == null)
    {
      aggregateMergeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(69);
    }
    return aggregateMergeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAggregateMerge_Mode()
  {
        return (EAttribute)getAggregateMerge().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAggregateMerge_Lfms()
  {
        return (EReference)getAggregateMerge().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAggregateMerge_Fms()
  {
        return (EReference)getAggregateMerge().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSynthesis()
  {
    if (synthesisEClass == null)
    {
      synthesisEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(70);
    }
    return synthesisEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSynthesis_Fm()
  {
        return (EReference)getSynthesis().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSynthesis_Kst()
  {
        return (EReference)getSynthesis().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getKnowledgeSpecification()
  {
    if (knowledgeSpecificationEClass == null)
    {
      knowledgeSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(71);
    }
    return knowledgeSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getKnowledgeSpecification_Hierarchy()
  {
        return (EReference)getKnowledgeSpecification().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getKnowledgeSpecification_Groups()
  {
        return (EReference)getKnowledgeSpecification().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getKnowledgeSpecification_Constraints()
  {
        return (EReference)getKnowledgeSpecification().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHierarchySpecification()
  {
    if (hierarchySpecificationEClass == null)
    {
      hierarchySpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(72);
    }
    return hierarchySpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHierarchySpecification_Hierarchy()
  {
        return (EReference)getHierarchySpecification().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHierarchySpecification_Features()
  {
        return (EReference)getHierarchySpecification().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHProduction()
  {
    if (hProductionEClass == null)
    {
      hProductionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(73);
    }
    return hProductionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHProduction_Name()
  {
        return (EAttribute)getHProduction().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHProduction_Features()
  {
        return (EAttribute)getHProduction().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupsSpecification()
  {
    if (groupsSpecificationEClass == null)
    {
      groupsSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(74);
    }
    return groupsSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupsSpecification_Groups()
  {
        return (EReference)getGroupsSpecification().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupSpec()
  {
    if (groupSpecEClass == null)
    {
      groupSpecEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(75);
    }
    return groupSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupSpec_Name()
  {
        return (EAttribute)getGroupSpec().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupSpec_Features()
  {
        return (EAttribute)getGroupSpec().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXorGroupSpec()
  {
    if (xorGroupSpecEClass == null)
    {
      xorGroupSpecEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(76);
    }
    return xorGroupSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMtxGroupSpec()
  {
    if (mtxGroupSpecEClass == null)
    {
      mtxGroupSpecEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(77);
    }
    return mtxGroupSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrGroupSpec()
  {
    if (orGroupSpecEClass == null)
    {
      orGroupSpecEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(78);
    }
    return orGroupSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstraintsSpecification()
  {
    if (constraintsSpecificationEClass == null)
    {
      constraintsSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(79);
    }
    return constraintsSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstraintsSpecification_Csts()
  {
        return (EReference)getConstraintsSpecification().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSlice()
  {
    if (sliceEClass == null)
    {
      sliceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(80);
    }
    return sliceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSlice_Fm()
  {
        return (EReference)getSlice().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSlice_Mode()
  {
        return (EAttribute)getSlice().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSlice_Fts()
  {
        return (EReference)getSlice().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAggregate()
  {
    if (aggregateEClass == null)
    {
      aggregateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(82);
    }
    return aggregateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAggregate_Fms()
  {
        return (EReference)getAggregate().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAggregate_Sfms()
  {
        return (EReference)getAggregate().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAggregate_Mapping()
  {
        return (EReference)getAggregate().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureModelOperation()
  {
    if (featureModelOperationEClass == null)
    {
      featureModelOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(83);
    }
    return featureModelOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEditOperation()
  {
    if (editOperationEClass == null)
    {
      editOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(84);
    }
    return editOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInsert()
  {
    if (insertEClass == null)
    {
      insertEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(85);
    }
    return insertEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInsert_Aspectfm()
  {
        return (EReference)getInsert().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInsert_Baseft()
  {
        return (EReference)getInsert().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInsert_Op()
  {
        return (EReference)getInsert().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRemoveFeature()
  {
    if (removeFeatureEClass == null)
    {
      removeFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(86);
    }
    return removeFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRemoveFeature_Feature()
  {
        return (EReference)getRemoveFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRenameFeature()
  {
    if (renameFeatureEClass == null)
    {
      renameFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(87);
    }
    return renameFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRenameFeature_Feature()
  {
        return (EReference)getRenameFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRenameFeature_FeatureNew()
  {
        return (EReference)getRenameFeature().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtract()
  {
    if (extractEClass == null)
    {
      extractEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(88);
    }
    return extractEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtract_Rootfeature()
  {
        return (EReference)getExtract().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAssertion()
  {
    if (assertionEClass == null)
    {
      assertionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(89);
    }
    return assertionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssertion_Assertion()
  {
        return (EReference)getAssertion().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableNull()
  {
    if (variableNullEClass == null)
    {
      variableNullEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(92);
    }
    return variableNullEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableNull_Var()
  {
        return (EAttribute)getVariableNull().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExport()
  {
    if (exportEClass == null)
    {
      exportEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(93);
    }
    return exportEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExport_Arg()
  {
        return (EReference)getExport().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHidden()
  {
    if (hiddenEClass == null)
    {
      hiddenEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(94);
    }
    return hiddenEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHidden_Arg()
  {
        return (EReference)getHidden().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLVidentifier()
  {
    if (lVidentifierEClass == null)
    {
      lVidentifierEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(95);
    }
    return lVidentifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLVidentifier_Args()
  {
        return (EAttribute)getLVidentifier().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDependency()
  {
    if (dependencyEClass == null)
    {
      dependencyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(96);
    }
    return dependencyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDependency_Var()
  {
        return (EAttribute)getDependency().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigurationCmd()
  {
    if (configurationCmdEClass == null)
    {
      configurationCmdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(97);
    }
    return configurationCmdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCreateConfiguration()
  {
    if (createConfigurationEClass == null)
    {
      createConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(98);
    }
    return createConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCreateConfiguration_Fm()
  {
        return (EReference)getCreateConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompleteConfiguration()
  {
    if (completeConfigurationEClass == null)
    {
      completeConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(99);
    }
    return completeConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompleteConfiguration_Config()
  {
        return (EReference)getCompleteConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelectionFeature()
  {
    if (selectionFeatureEClass == null)
    {
      selectionFeatureEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(100);
    }
    return selectionFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelectionFeature_Op()
  {
        return (EAttribute)getSelectionFeature().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelectionFeature_Fts()
  {
        return (EReference)getSelectionFeature().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelectionFeature_Config()
  {
        return (EReference)getSelectionFeature().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureExpression()
  {
    if (featureExpressionEClass == null)
    {
      featureExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(101);
    }
    return featureExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureExpression_Ft()
  {
        return (EReference)getFeatureExpression().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAutoConfiguration()
  {
    if (autoConfigurationEClass == null)
    {
      autoConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(103);
    }
    return autoConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAutoConfiguration_Config()
  {
        return (EReference)getAutoConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAutoConfiguration_Mode()
  {
        return (EAttribute)getAutoConfiguration().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelectedConfiguration()
  {
    if (selectedConfigurationEClass == null)
    {
      selectedConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(105);
    }
    return selectedConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelectedConfiguration_Config()
  {
        return (EReference)getSelectedConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeselectedConfiguration()
  {
    if (deselectedConfigurationEClass == null)
    {
      deselectedConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(106);
    }
    return deselectedConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeselectedConfiguration_Config()
  {
        return (EReference)getDeselectedConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnselectedConfiguration()
  {
    if (unselectedConfigurationEClass == null)
    {
      unselectedConfigurationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(107);
    }
    return unselectedConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnselectedConfiguration_Config()
  {
        return (EReference)getUnselectedConfiguration().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAsFM()
  {
    if (asFMEClass == null)
    {
      asFMEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(108);
    }
    return asFMEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAsFM_Conf()
  {
        return (EReference)getAsFM().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMap()
  {
    if (mapEClass == null)
    {
      mapEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(109);
    }
    return mapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMap_Fm()
  {
        return (EReference)getMap().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMap_Cst()
  {
        return (EReference)getMap().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnMap()
  {
    if (unMapEClass == null)
    {
      unMapEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(110);
    }
    return unMapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnMap_Fm()
  {
        return (EReference)getUnMap().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCleanUp()
  {
    if (cleanUpEClass == null)
    {
      cleanUpEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(111);
    }
    return cleanUpEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCleanUp_Fm()
  {
        return (EReference)getCleanUp().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCores()
  {
    if (coresEClass == null)
    {
      coresEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(112);
    }
    return coresEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCores_Fm()
  {
        return (EReference)getCores().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeads()
  {
    if (deadsEClass == null)
    {
      deadsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(113);
    }
    return deadsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeads_Fm()
  {
        return (EReference)getDeads().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFullMandatorys()
  {
    if (fullMandatorysEClass == null)
    {
      fullMandatorysEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(114);
    }
    return fullMandatorysEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFullMandatorys_Fm()
  {
        return (EReference)getFullMandatorys().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCliques()
  {
    if (cliquesEClass == null)
    {
      cliquesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(115);
    }
    return cliquesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCliques_Fm()
  {
        return (EReference)getCliques().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScriptDefinition()
  {
    if (scriptDefinitionEClass == null)
    {
      scriptDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(116);
    }
    return scriptDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScriptDefinition_Params()
  {
        return (EReference)getScriptDefinition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScriptDefinition_Cmds()
  {
        return (EReference)getScriptDefinition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScriptDefinition_Exports()
  {
        return (EReference)getScriptDefinition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getShell()
  {
    if (shellEClass == null)
    {
      shellEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(117);
    }
    return shellEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getShell_Cmd()
  {
        return (EReference)getShell().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExit()
  {
    if (exitEClass == null)
    {
      exitEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(118);
    }
    return exitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExit_Val()
  {
        return (EAttribute)getExit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExist()
  {
    if (existEClass == null)
    {
      existEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(119);
    }
    return existEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExist_Val()
  {
        return (EAttribute)getExist().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExist_Var()
  {
        return (EAttribute)getExist().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIsConflicting()
  {
    if (isConflictingEClass == null)
    {
      isConflictingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(120);
    }
    return isConflictingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIsConflicting_Val()
  {
        return (EAttribute)getIsConflicting().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIsConflicting_Var()
  {
        return (EAttribute)getIsConflicting().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getListing()
  {
    if (listingEClass == null)
    {
      listingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(121);
    }
    return listingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getListing_Val()
  {
        return (EAttribute)getListing().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getListing_Opt()
  {
        return (EAttribute)getListing().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getState()
  {
    if (stateEClass == null)
    {
      stateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(123);
    }
    return stateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getState_Val()
  {
        return (EAttribute)getState().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCopyVariable()
  {
    if (copyVariableEClass == null)
    {
      copyVariableEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(124);
    }
    return copyVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCopyVariable_Vid()
  {
        return (EAttribute)getCopyVariable().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRemoveVariable()
  {
    if (removeVariableEClass == null)
    {
      removeVariableEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(125);
    }
    return removeVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRemoveVariable_Vid()
  {
        return (EAttribute)getRemoveVariable().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConvert()
  {
    if (convertEClass == null)
    {
      convertEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(126);
    }
    return convertEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConvert_V()
  {
        return (EReference)getConvert().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConvert_Format()
  {
        return (EAttribute)getConvert().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFMLSave()
  {
    if (fmlSaveEClass == null)
    {
      fmlSaveEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(128);
    }
    return fmlSaveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFMLSave_V()
  {
        return (EReference)getFMLSave().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFMLSave_Format()
  {
        return (EAttribute)getFMLSave().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHierarchy()
  {
    if (hierarchyEClass == null)
    {
      hierarchyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(129);
    }
    return hierarchyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHierarchy_Fm()
  {
        return (EReference)getHierarchy().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrinterUtility()
  {
    if (printerUtilityEClass == null)
    {
      printerUtilityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(130);
    }
    return printerUtilityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPrinterUtility_Op()
  {
        return (EAttribute)getPrinterUtility().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrinterUtility_Arg()
  {
        return (EReference)getPrinterUtility().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLArgs()
  {
    if (lArgsEClass == null)
    {
      lArgsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(131);
    }
    return lArgsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLArgs_Args()
  {
        return (EReference)getLArgs().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGDisplay()
  {
    if (gDisplayEClass == null)
    {
      gDisplayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(132);
    }
    return gDisplayEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGDisplay_CmdDisplay()
  {
        return (EAttribute)getGDisplay().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGDisplay_Var()
  {
        return (EReference)getGDisplay().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGListing()
  {
    if (gListingEClass == null)
    {
      gListingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(133);
    }
    return gListingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGListing_Cmd()
  {
        return (EAttribute)getGListing().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModifyVOperator()
  {
    if (modifyVOperatorEClass == null)
    {
      modifyVOperatorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(134);
    }
    return modifyVOperatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMandatoryEdit()
  {
    if (mandatoryEditEClass == null)
    {
      mandatoryEditEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(135);
    }
    return mandatoryEditEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMandatoryEdit_Ft()
  {
        return (EReference)getMandatoryEdit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOptionalEdit()
  {
    if (optionalEditEClass == null)
    {
      optionalEditEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(136);
    }
    return optionalEditEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOptionalEdit_Ft()
  {
        return (EReference)getOptionalEdit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAlternativeEdit()
  {
    if (alternativeEditEClass == null)
    {
      alternativeEditEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(137);
    }
    return alternativeEditEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAlternativeEdit_Fts()
  {
        return (EReference)getAlternativeEdit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrEdit()
  {
    if (orEditEClass == null)
    {
      orEditEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(138);
    }
    return orEditEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrEdit_Fts()
  {
        return (EReference)getOrEdit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAddConstraint()
  {
    if (addConstraintEClass == null)
    {
      addConstraintEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(139);
    }
    return addConstraintEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAddConstraint_Cst()
  {
        return (EReference)getAddConstraint().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAddConstraint_Fm()
  {
        return (EReference)getAddConstraint().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRemoveConstraint()
  {
    if (removeConstraintEClass == null)
    {
      removeConstraintEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(140);
    }
    return removeConstraintEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRemoveConstraint_Cst()
  {
        return (EReference)getRemoveConstraint().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRemoveConstraint_Fm()
  {
        return (EReference)getRemoveConstraint().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCNF()
  {
    if (cnfEClass == null)
    {
      cnfEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(141);
    }
    return cnfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCNFExpression()
  {
    if (cnfExpressionEClass == null)
    {
      cnfExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(142);
    }
    return cnfExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCNFExpression_Name()
  {
        return (EAttribute)getCNFExpression().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNeg_expr()
  {
    if (neg_exprEClass == null)
    {
      neg_exprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(143);
    }
    return neg_exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNeg_expr_Expr()
  {
        return (EReference)getNeg_expr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureModel()
  {
    if (featureModelEClass == null)
    {
      featureModelEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(145);
    }
    return featureModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModel_Root()
  {
        return (EAttribute)getFeatureModel().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModel_Features()
  {
        return (EReference)getFeatureModel().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModel_Expr()
  {
        return (EReference)getFeatureModel().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModel_File()
  {
        return (EReference)getFeatureModel().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProduction()
  {
    if (productionEClass == null)
    {
      productionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(146);
    }
    return productionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProduction_Name()
  {
        return (EAttribute)getProduction().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProduction_Features()
  {
        return (EReference)getProduction().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getChild()
  {
    if (childEClass == null)
    {
      childEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(147);
    }
    return childEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMandatory()
  {
    if (mandatoryEClass == null)
    {
      mandatoryEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(148);
    }
    return mandatoryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMandatory_Name()
  {
        return (EAttribute)getMandatory().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOptional()
  {
    if (optionalEClass == null)
    {
      optionalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(149);
    }
    return optionalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOptional_Name()
  {
        return (EAttribute)getOptional().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXorgroup()
  {
    if (xorgroupEClass == null)
    {
      xorgroupEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(150);
    }
    return xorgroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXorgroup_Features()
  {
        return (EAttribute)getXorgroup().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrgroup()
  {
    if (orgroupEClass == null)
    {
      orgroupEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(151);
    }
    return orgroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOrgroup_Features()
  {
        return (EAttribute)getOrgroup().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMutexgroup()
  {
    if (mutexgroupEClass == null)
    {
      mutexgroupEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(152);
    }
    return mutexgroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMutexgroup_Features()
  {
        return (EAttribute)getMutexgroup().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerOperation()
  {
    if (integerOperationEClass == null)
    {
      integerOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(153);
    }
    return integerOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntegerOperation_Op()
  {
        return (EAttribute)getIntegerOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIntegerOperation_Right()
  {
        return (EReference)getIntegerOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBoolOperation()
  {
    if (boolOperationEClass == null)
    {
      boolOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(154);
    }
    return boolOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBoolOperation_Op()
  {
        return (EAttribute)getBoolOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBoolOperation_Right()
  {
        return (EReference)getBoolOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComparisonOperation()
  {
    if (comparisonOperationEClass == null)
    {
      comparisonOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(155);
    }
    return comparisonOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComparisonOperation_CmpOp()
  {
        return (EAttribute)getComparisonOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComparisonOperation_Right()
  {
        return (EReference)getComparisonOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetOperation()
  {
    if (setOperationEClass == null)
    {
      setOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(156);
    }
    return setOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetOperation_Sop()
  {
        return (EAttribute)getSetOperation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSetOperation_Right()
  {
        return (EReference)getSetOperation().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntLiteral()
  {
    if (intLiteralEClass == null)
    {
      intLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(157);
    }
    return intLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntLiteral_Value()
  {
        return (EAttribute)getIntLiteral().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOr_expr()
  {
    if (or_exprEClass == null)
    {
      or_exprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(158);
    }
    return or_exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOr_expr_Left()
  {
        return (EReference)getOr_expr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOr_expr_Right()
  {
        return (EReference)getOr_expr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnd_expr()
  {
    if (and_exprEClass == null)
    {
      and_exprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(159);
    }
    return and_exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnd_expr_Left()
  {
        return (EReference)getAnd_expr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnd_expr_Right()
  {
        return (EReference)getAnd_expr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImpl_expr()
  {
    if (impl_exprEClass == null)
    {
      impl_exprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(160);
    }
    return impl_exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImpl_expr_Left()
  {
        return (EReference)getImpl_expr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImpl_expr_Right()
  {
        return (EReference)getImpl_expr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBiimpl_expr()
  {
    if (biimpl_exprEClass == null)
    {
      biimpl_exprEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(161);
    }
    return biimpl_exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBiimpl_expr_Left()
  {
        return (EReference)getBiimpl_expr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBiimpl_expr_Right()
  {
        return (EReference)getBiimpl_expr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getEditConstant()
  {
    if (editConstantEEnum == null)
    {
      editConstantEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(11);
    }
    return editConstantEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getFeatureEdgeKind()
  {
    if (featureEdgeKindEEnum == null)
    {
      featureEdgeKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(13);
    }
    return featureEdgeKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getKindOfGet()
  {
    if (kindOfGetEEnum == null)
    {
      kindOfGetEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(26);
    }
    return kindOfGetEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getKindOfCompute()
  {
    if (kindOfComputeEEnum == null)
    {
      kindOfComputeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(28);
    }
    return kindOfComputeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getKindOfGetGroups()
  {
    if (kindOfGetGroupsEEnum == null)
    {
      kindOfGetGroupsEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(30);
    }
    return kindOfGetGroupsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getKindOfComputeGroups()
  {
    if (kindOfComputeGroupsEEnum == null)
    {
      kindOfComputeGroupsEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(32);
    }
    return kindOfComputeGroupsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getBDDBackend()
  {
    if (bddBackendEEnum == null)
    {
      bddBackendEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(66);
    }
    return bddBackendEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getMergeMode()
  {
    if (mergeModeEEnum == null)
    {
      mergeModeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(67);
    }
    return mergeModeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getSliceMode()
  {
    if (sliceModeEEnum == null)
    {
      sliceModeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(81);
    }
    return sliceModeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getComparisonOperator()
  {
    if (comparisonOperatorEEnum == null)
    {
      comparisonOperatorEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(90);
    }
    return comparisonOperatorEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getSetOperator()
  {
    if (setOperatorEEnum == null)
    {
      setOperatorEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(91);
    }
    return setOperatorEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getOpSelection()
  {
    if (opSelectionEEnum == null)
    {
      opSelectionEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(102);
    }
    return opSelectionEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getAutoConfMode()
  {
    if (autoConfModeEEnum == null)
    {
      autoConfModeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(104);
    }
    return autoConfModeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getOPT_LISTING()
  {
    if (opT_LISTINGEEnum == null)
    {
      opT_LISTINGEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(122);
    }
    return opT_LISTINGEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getFMFormat()
  {
    if (fmFormatEEnum == null)
    {
      fmFormatEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(127);
    }
    return fmFormatEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getBOOL_Operator()
  {
    if (booL_OperatorEEnum == null)
    {
      booL_OperatorEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(FMLPackage.eNS_URI).getEClassifiers().get(144);
    }
    return booL_OperatorEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FMLFactory getFMLFactory()
  {
    return (FMLFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isLoaded = false;

  /**
   * Laods the package and any sub-packages from their serialized form.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void loadPackage()
  {
    if (isLoaded) return;
    isLoaded = true;

    URL url = getClass().getResource(packageFilename);
    if (url == null)
    {
      throw new RuntimeException("Missing serialized package: " + packageFilename);
    }
    URI uri = URI.createURI(url.toString());
    Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
    try
    {
      resource.load(null);
    }
    catch (IOException exception)
    {
      throw new WrappedException(exception);
    }
    initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
    createResource(eNS_URI);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isFixed = false;

  /**
   * Fixes up the loaded package, to make it appear as if it had been programmatically built.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fixPackageContents()
  {
    if (isFixed) return;
    isFixed = true;
    fixEClassifiers();
  }

  /**
   * Sets the instance class on the given classifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void fixInstanceClass(EClassifier eClassifier)
  {
    if (eClassifier.getInstanceClassName() == null)
    {
      eClassifier.setInstanceClassName("org.xtext.example.mydsl.fML." + eClassifier.getName());
      setGeneratedClassName(eClassifier);
    }
  }

} //FMLPackageImpl
