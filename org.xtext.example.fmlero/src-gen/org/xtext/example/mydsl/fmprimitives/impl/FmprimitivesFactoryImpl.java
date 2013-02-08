/**
 */
package org.xtext.example.mydsl.fmprimitives.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.xtext.example.mydsl.fmprimitives.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FmprimitivesFactoryImpl extends EFactoryImpl implements FmprimitivesFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FmprimitivesFactory init()
  {
    try
    {
      FmprimitivesFactory theFmprimitivesFactory = (FmprimitivesFactory)EPackage.Registry.INSTANCE.getEFactory("http://lero.ie/spl/fmprimitives.ecore"); 
      if (theFmprimitivesFactory != null)
      {
        return theFmprimitivesFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FmprimitivesFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FmprimitivesFactoryImpl()
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
      case FmprimitivesPackage.FEATURE_MODEL: return createFeatureModel();
      case FmprimitivesPackage.FEATURE_MODEL_PRIMITIVE: return createFeatureModelPrimitive();
      case FmprimitivesPackage.FEATURE_HAS_SUBFEATURE: return createFeatureHasSubfeature();
      case FmprimitivesPackage.UNDIRECTED_RELATIONSHIP: return createUndirectedRelationship();
      case FmprimitivesPackage.DIRECTED_RELATIONSHIP: return createDirectedRelationship();
      case FmprimitivesPackage.FEATURE_GROUP: return createFeatureGroup();
      case FmprimitivesPackage.FEATURE: return createFeature();
      case FmprimitivesPackage.GROUP_HAS_PARENT: return createGroupHasParent();
      case FmprimitivesPackage.GROUP_HAS_CHILD: return createGroupHasChild();
      case FmprimitivesPackage.SELECTED_FEATURE: return createSelectedFeature();
      case FmprimitivesPackage.ELIMINATED_FEATURE: return createEliminatedFeature();
      case FmprimitivesPackage.FEATURE_IS_ROOT: return createFeatureIsRoot();
      case FmprimitivesPackage.EXPLANATION: return createExplanation();
      case FmprimitivesPackage.GROUP_HAS_MAX: return createGroupHasMax();
      case FmprimitivesPackage.GROUP_HAS_MIN: return createGroupHasMin();
      case FmprimitivesPackage.ALTERNATIVE_GROUP: return createAlternativeGroup();
      case FmprimitivesPackage.OR_GROUP: return createOrGroup();
      case FmprimitivesPackage.FEATURE_HAS_OPTIONAL_SUBFEATURE: return createFeatureHasOptionalSubfeature();
      case FmprimitivesPackage.FEATURE_HAS_MANDATORY_SUBFEATURE: return createFeatureHasMandatorySubfeature();
      case FmprimitivesPackage.MUTUAL_EXCLUSIVE: return createMutualExclusive();
      case FmprimitivesPackage.CUSTOM_UNDIRECTED_RELATIONSHIP: return createCustomUndirectedRelationship();
      case FmprimitivesPackage.REQUIRES: return createRequires();
      case FmprimitivesPackage.TEMPORAL_ORDERING_SEQUENTIAL: return createTemporalOrderingSequential();
      case FmprimitivesPackage.CUSTOM_DIRECTED_RELATIONSHIP: return createCustomDirectedRelationship();
      case FmprimitivesPackage.AUTO_COMPLETE: return createAutoComplete();
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
      case FmprimitivesPackage.CONFIGURATION_SOURCE:
        return createConfigurationSourceFromString(eDataType, initialValue);
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
      case FmprimitivesPackage.CONFIGURATION_SOURCE:
        return convertConfigurationSourceToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
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
  public FeatureModelPrimitive createFeatureModelPrimitive()
  {
    FeatureModelPrimitiveImpl featureModelPrimitive = new FeatureModelPrimitiveImpl();
    return featureModelPrimitive;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureHasSubfeature createFeatureHasSubfeature()
  {
    FeatureHasSubfeatureImpl featureHasSubfeature = new FeatureHasSubfeatureImpl();
    return featureHasSubfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UndirectedRelationship createUndirectedRelationship()
  {
    UndirectedRelationshipImpl undirectedRelationship = new UndirectedRelationshipImpl();
    return undirectedRelationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DirectedRelationship createDirectedRelationship()
  {
    DirectedRelationshipImpl directedRelationship = new DirectedRelationshipImpl();
    return directedRelationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureGroup createFeatureGroup()
  {
    FeatureGroupImpl featureGroup = new FeatureGroupImpl();
    return featureGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature createFeature()
  {
    FeatureImpl feature = new FeatureImpl();
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasParent createGroupHasParent()
  {
    GroupHasParentImpl groupHasParent = new GroupHasParentImpl();
    return groupHasParent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasChild createGroupHasChild()
  {
    GroupHasChildImpl groupHasChild = new GroupHasChildImpl();
    return groupHasChild;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelectedFeature createSelectedFeature()
  {
    SelectedFeatureImpl selectedFeature = new SelectedFeatureImpl();
    return selectedFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EliminatedFeature createEliminatedFeature()
  {
    EliminatedFeatureImpl eliminatedFeature = new EliminatedFeatureImpl();
    return eliminatedFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureIsRoot createFeatureIsRoot()
  {
    FeatureIsRootImpl featureIsRoot = new FeatureIsRootImpl();
    return featureIsRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Explanation createExplanation()
  {
    ExplanationImpl explanation = new ExplanationImpl();
    return explanation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMax createGroupHasMax()
  {
    GroupHasMaxImpl groupHasMax = new GroupHasMaxImpl();
    return groupHasMax;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupHasMin createGroupHasMin()
  {
    GroupHasMinImpl groupHasMin = new GroupHasMinImpl();
    return groupHasMin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlternativeGroup createAlternativeGroup()
  {
    AlternativeGroupImpl alternativeGroup = new AlternativeGroupImpl();
    return alternativeGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrGroup createOrGroup()
  {
    OrGroupImpl orGroup = new OrGroupImpl();
    return orGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureHasOptionalSubfeature createFeatureHasOptionalSubfeature()
  {
    FeatureHasOptionalSubfeatureImpl featureHasOptionalSubfeature = new FeatureHasOptionalSubfeatureImpl();
    return featureHasOptionalSubfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureHasMandatorySubfeature createFeatureHasMandatorySubfeature()
  {
    FeatureHasMandatorySubfeatureImpl featureHasMandatorySubfeature = new FeatureHasMandatorySubfeatureImpl();
    return featureHasMandatorySubfeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MutualExclusive createMutualExclusive()
  {
    MutualExclusiveImpl mutualExclusive = new MutualExclusiveImpl();
    return mutualExclusive;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomUndirectedRelationship createCustomUndirectedRelationship()
  {
    CustomUndirectedRelationshipImpl customUndirectedRelationship = new CustomUndirectedRelationshipImpl();
    return customUndirectedRelationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Requires createRequires()
  {
    RequiresImpl requires = new RequiresImpl();
    return requires;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TemporalOrderingSequential createTemporalOrderingSequential()
  {
    TemporalOrderingSequentialImpl temporalOrderingSequential = new TemporalOrderingSequentialImpl();
    return temporalOrderingSequential;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomDirectedRelationship createCustomDirectedRelationship()
  {
    CustomDirectedRelationshipImpl customDirectedRelationship = new CustomDirectedRelationshipImpl();
    return customDirectedRelationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AutoComplete createAutoComplete()
  {
    AutoCompleteImpl autoComplete = new AutoCompleteImpl();
    return autoComplete;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigurationSource createConfigurationSourceFromString(EDataType eDataType, String initialValue)
  {
    ConfigurationSource result = ConfigurationSource.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertConfigurationSourceToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FmprimitivesPackage getFmprimitivesPackage()
  {
    return (FmprimitivesPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FmprimitivesPackage getPackage()
  {
    return FmprimitivesPackage.eINSTANCE;
  }

} //FmprimitivesFactoryImpl
