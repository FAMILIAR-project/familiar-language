/**
 */
package fr.inria.familiar.fmlero.fmprimitives.impl;

import fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup;
import fr.inria.familiar.fmlero.fmprimitives.AutoComplete;
import fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource;
import fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship;
import fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship;
import fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship;
import fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature;
import fr.inria.familiar.fmlero.fmprimitives.Explanation;
import fr.inria.familiar.fmlero.fmprimitives.Feature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureGroup;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasMandatorySubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasOptionalSubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature;
import fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot;
import fr.inria.familiar.fmlero.fmprimitives.FeatureModel;
import fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesFactory;
import fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasChild;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasMax;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasMin;
import fr.inria.familiar.fmlero.fmprimitives.GroupHasParent;
import fr.inria.familiar.fmlero.fmprimitives.MutualExclusive;
import fr.inria.familiar.fmlero.fmprimitives.OrGroup;
import fr.inria.familiar.fmlero.fmprimitives.Requires;
import fr.inria.familiar.fmlero.fmprimitives.SelectedFeature;
import fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential;
import fr.inria.familiar.fmlero.fmprimitives.UndirectedRelationship;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FmprimitivesPackageImpl extends EPackageImpl implements FmprimitivesPackage
{
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
  private EClass featureModelPrimitiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureHasSubfeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass undirectedRelationshipEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass directedRelationshipEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupHasParentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupHasChildEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectedFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eliminatedFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureIsRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass explanationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupHasMaxEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupHasMinEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass alternativeGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureHasOptionalSubfeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureHasMandatorySubfeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mutualExclusiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass customUndirectedRelationshipEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass requiresEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass temporalOrderingSequentialEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass customDirectedRelationshipEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass autoCompleteEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum configurationSourceEEnum = null;

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
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private FmprimitivesPackageImpl()
  {
    super(eNS_URI, FmprimitivesFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link FmprimitivesPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static FmprimitivesPackage init()
  {
    if (isInited) return (FmprimitivesPackage)EPackage.Registry.INSTANCE.getEPackage(FmprimitivesPackage.eNS_URI);

    // Obtain or create and register package
    FmprimitivesPackageImpl theFmprimitivesPackage = (FmprimitivesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FmprimitivesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FmprimitivesPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theFmprimitivesPackage.createPackageContents();

    // Initialize created meta-data
    theFmprimitivesPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theFmprimitivesPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(FmprimitivesPackage.eNS_URI, theFmprimitivesPackage);
    return theFmprimitivesPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureModel()
  {
    return featureModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModel_Id()
  {
    return (EAttribute)featureModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModel_Name()
  {
    return (EAttribute)featureModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModel_Features()
  {
    return (EReference)featureModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModel_Primitives()
  {
    return (EReference)featureModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureModelPrimitive()
  {
    return featureModelPrimitiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModelPrimitive_Id()
  {
    return (EAttribute)featureModelPrimitiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModelPrimitive_Name()
  {
    return (EAttribute)featureModelPrimitiveEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureModelPrimitive_ConfigurationSource()
  {
    return (EAttribute)featureModelPrimitiveEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureModelPrimitive_Explanations()
  {
    return (EReference)featureModelPrimitiveEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureHasSubfeature()
  {
    return featureHasSubfeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureHasSubfeature_Parent()
  {
    return (EReference)featureHasSubfeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureHasSubfeature_Subfeature()
  {
    return (EReference)featureHasSubfeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUndirectedRelationship()
  {
    return undirectedRelationshipEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDirectedRelationship()
  {
    return directedRelationshipEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureGroup()
  {
    return featureGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeature()
  {
    return featureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeature_Id()
  {
    return (EAttribute)featureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeature_Name()
  {
    return (EAttribute)featureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_GroupHasParent()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_GroupHasChild()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_FeatureHasParent()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_FeatureHasSubfeature()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_SelectedFeature()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_EliminatedFeature()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_UndirectedRelationships()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_IncomingDirectedRelationships()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_OutgoingDirectedRelationships()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeature_FeatureIsRoot()
  {
    return (EReference)featureEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupHasParent()
  {
    return groupHasParentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasParent_Parent()
  {
    return (EReference)groupHasParentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasParent_Group()
  {
    return (EReference)groupHasParentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupHasChild()
  {
    return groupHasChildEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasChild_Child()
  {
    return (EReference)groupHasChildEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasChild_Group()
  {
    return (EReference)groupHasChildEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelectedFeature()
  {
    return selectedFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelectedFeature_Feature()
  {
    return (EReference)selectedFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEliminatedFeature()
  {
    return eliminatedFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEliminatedFeature_Feature()
  {
    return (EReference)eliminatedFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureIsRoot()
  {
    return featureIsRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureIsRoot_Feature()
  {
    return (EReference)featureIsRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExplanation()
  {
    return explanationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExplanation_Primitives()
  {
    return (EReference)explanationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupHasMax()
  {
    return groupHasMaxEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupHasMax_Max()
  {
    return (EAttribute)groupHasMaxEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasMax_Group()
  {
    return (EReference)groupHasMaxEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupHasMin()
  {
    return groupHasMinEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupHasMin_Min()
  {
    return (EAttribute)groupHasMinEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupHasMin_Group()
  {
    return (EReference)groupHasMinEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAlternativeGroup()
  {
    return alternativeGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAlternativeGroup_GroupHasParent()
  {
    return (EReference)alternativeGroupEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAlternativeGroup_GroupHasChild()
  {
    return (EReference)alternativeGroupEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAlternativeGroup_GroupHasMax()
  {
    return (EReference)alternativeGroupEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAlternativeGroup_GroupHasMin()
  {
    return (EReference)alternativeGroupEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrGroup()
  {
    return orGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrGroup_GroupHasParent()
  {
    return (EReference)orGroupEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrGroup_GroupHasChild()
  {
    return (EReference)orGroupEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrGroup_GroupHasMax()
  {
    return (EReference)orGroupEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrGroup_GroupHasMin()
  {
    return (EReference)orGroupEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureHasOptionalSubfeature()
  {
    return featureHasOptionalSubfeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureHasMandatorySubfeature()
  {
    return featureHasMandatorySubfeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMutualExclusive()
  {
    return mutualExclusiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMutualExclusive_RelatedFeatures()
  {
    return (EReference)mutualExclusiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCustomUndirectedRelationship()
  {
    return customUndirectedRelationshipEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomUndirectedRelationship_Stereotype()
  {
    return (EAttribute)customUndirectedRelationshipEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomUndirectedRelationship_RelatedFeatures()
  {
    return (EReference)customUndirectedRelationshipEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRequires()
  {
    return requiresEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRequires_Sources()
  {
    return (EReference)requiresEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRequires_Targets()
  {
    return (EReference)requiresEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTemporalOrderingSequential()
  {
    return temporalOrderingSequentialEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTemporalOrderingSequential_Sources()
  {
    return (EReference)temporalOrderingSequentialEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTemporalOrderingSequential_Targets()
  {
    return (EReference)temporalOrderingSequentialEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCustomDirectedRelationship()
  {
    return customDirectedRelationshipEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomDirectedRelationship_Stereotype()
  {
    return (EAttribute)customDirectedRelationshipEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomDirectedRelationship_Sources()
  {
    return (EReference)customDirectedRelationshipEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomDirectedRelationship_Targets()
  {
    return (EReference)customDirectedRelationshipEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAutoComplete()
  {
    return autoCompleteEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getConfigurationSource()
  {
    return configurationSourceEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FmprimitivesFactory getFmprimitivesFactory()
  {
    return (FmprimitivesFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    featureModelEClass = createEClass(FEATURE_MODEL);
    createEAttribute(featureModelEClass, FEATURE_MODEL__ID);
    createEAttribute(featureModelEClass, FEATURE_MODEL__NAME);
    createEReference(featureModelEClass, FEATURE_MODEL__FEATURES);
    createEReference(featureModelEClass, FEATURE_MODEL__PRIMITIVES);

    featureModelPrimitiveEClass = createEClass(FEATURE_MODEL_PRIMITIVE);
    createEAttribute(featureModelPrimitiveEClass, FEATURE_MODEL_PRIMITIVE__ID);
    createEAttribute(featureModelPrimitiveEClass, FEATURE_MODEL_PRIMITIVE__NAME);
    createEAttribute(featureModelPrimitiveEClass, FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE);
    createEReference(featureModelPrimitiveEClass, FEATURE_MODEL_PRIMITIVE__EXPLANATIONS);

    featureHasSubfeatureEClass = createEClass(FEATURE_HAS_SUBFEATURE);
    createEReference(featureHasSubfeatureEClass, FEATURE_HAS_SUBFEATURE__PARENT);
    createEReference(featureHasSubfeatureEClass, FEATURE_HAS_SUBFEATURE__SUBFEATURE);

    undirectedRelationshipEClass = createEClass(UNDIRECTED_RELATIONSHIP);

    directedRelationshipEClass = createEClass(DIRECTED_RELATIONSHIP);

    featureGroupEClass = createEClass(FEATURE_GROUP);

    featureEClass = createEClass(FEATURE);
    createEAttribute(featureEClass, FEATURE__ID);
    createEAttribute(featureEClass, FEATURE__NAME);
    createEReference(featureEClass, FEATURE__GROUP_HAS_PARENT);
    createEReference(featureEClass, FEATURE__GROUP_HAS_CHILD);
    createEReference(featureEClass, FEATURE__FEATURE_HAS_PARENT);
    createEReference(featureEClass, FEATURE__FEATURE_HAS_SUBFEATURE);
    createEReference(featureEClass, FEATURE__SELECTED_FEATURE);
    createEReference(featureEClass, FEATURE__ELIMINATED_FEATURE);
    createEReference(featureEClass, FEATURE__UNDIRECTED_RELATIONSHIPS);
    createEReference(featureEClass, FEATURE__INCOMING_DIRECTED_RELATIONSHIPS);
    createEReference(featureEClass, FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS);
    createEReference(featureEClass, FEATURE__FEATURE_IS_ROOT);

    groupHasParentEClass = createEClass(GROUP_HAS_PARENT);
    createEReference(groupHasParentEClass, GROUP_HAS_PARENT__PARENT);
    createEReference(groupHasParentEClass, GROUP_HAS_PARENT__GROUP);

    groupHasChildEClass = createEClass(GROUP_HAS_CHILD);
    createEReference(groupHasChildEClass, GROUP_HAS_CHILD__CHILD);
    createEReference(groupHasChildEClass, GROUP_HAS_CHILD__GROUP);

    selectedFeatureEClass = createEClass(SELECTED_FEATURE);
    createEReference(selectedFeatureEClass, SELECTED_FEATURE__FEATURE);

    eliminatedFeatureEClass = createEClass(ELIMINATED_FEATURE);
    createEReference(eliminatedFeatureEClass, ELIMINATED_FEATURE__FEATURE);

    featureIsRootEClass = createEClass(FEATURE_IS_ROOT);
    createEReference(featureIsRootEClass, FEATURE_IS_ROOT__FEATURE);

    explanationEClass = createEClass(EXPLANATION);
    createEReference(explanationEClass, EXPLANATION__PRIMITIVES);

    groupHasMaxEClass = createEClass(GROUP_HAS_MAX);
    createEAttribute(groupHasMaxEClass, GROUP_HAS_MAX__MAX);
    createEReference(groupHasMaxEClass, GROUP_HAS_MAX__GROUP);

    groupHasMinEClass = createEClass(GROUP_HAS_MIN);
    createEAttribute(groupHasMinEClass, GROUP_HAS_MIN__MIN);
    createEReference(groupHasMinEClass, GROUP_HAS_MIN__GROUP);

    alternativeGroupEClass = createEClass(ALTERNATIVE_GROUP);
    createEReference(alternativeGroupEClass, ALTERNATIVE_GROUP__GROUP_HAS_PARENT);
    createEReference(alternativeGroupEClass, ALTERNATIVE_GROUP__GROUP_HAS_CHILD);
    createEReference(alternativeGroupEClass, ALTERNATIVE_GROUP__GROUP_HAS_MAX);
    createEReference(alternativeGroupEClass, ALTERNATIVE_GROUP__GROUP_HAS_MIN);

    orGroupEClass = createEClass(OR_GROUP);
    createEReference(orGroupEClass, OR_GROUP__GROUP_HAS_PARENT);
    createEReference(orGroupEClass, OR_GROUP__GROUP_HAS_CHILD);
    createEReference(orGroupEClass, OR_GROUP__GROUP_HAS_MAX);
    createEReference(orGroupEClass, OR_GROUP__GROUP_HAS_MIN);

    featureHasOptionalSubfeatureEClass = createEClass(FEATURE_HAS_OPTIONAL_SUBFEATURE);

    featureHasMandatorySubfeatureEClass = createEClass(FEATURE_HAS_MANDATORY_SUBFEATURE);

    mutualExclusiveEClass = createEClass(MUTUAL_EXCLUSIVE);
    createEReference(mutualExclusiveEClass, MUTUAL_EXCLUSIVE__RELATED_FEATURES);

    customUndirectedRelationshipEClass = createEClass(CUSTOM_UNDIRECTED_RELATIONSHIP);
    createEAttribute(customUndirectedRelationshipEClass, CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE);
    createEReference(customUndirectedRelationshipEClass, CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES);

    requiresEClass = createEClass(REQUIRES);
    createEReference(requiresEClass, REQUIRES__SOURCES);
    createEReference(requiresEClass, REQUIRES__TARGETS);

    temporalOrderingSequentialEClass = createEClass(TEMPORAL_ORDERING_SEQUENTIAL);
    createEReference(temporalOrderingSequentialEClass, TEMPORAL_ORDERING_SEQUENTIAL__SOURCES);
    createEReference(temporalOrderingSequentialEClass, TEMPORAL_ORDERING_SEQUENTIAL__TARGETS);

    customDirectedRelationshipEClass = createEClass(CUSTOM_DIRECTED_RELATIONSHIP);
    createEAttribute(customDirectedRelationshipEClass, CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE);
    createEReference(customDirectedRelationshipEClass, CUSTOM_DIRECTED_RELATIONSHIP__SOURCES);
    createEReference(customDirectedRelationshipEClass, CUSTOM_DIRECTED_RELATIONSHIP__TARGETS);

    autoCompleteEClass = createEClass(AUTO_COMPLETE);

    // Create enums
    configurationSourceEEnum = createEEnum(CONFIGURATION_SOURCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    featureHasSubfeatureEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    groupHasParentEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    groupHasChildEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    selectedFeatureEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    eliminatedFeatureEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    featureIsRootEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    groupHasMaxEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    groupHasMinEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    alternativeGroupEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    alternativeGroupEClass.getESuperTypes().add(this.getFeatureGroup());
    orGroupEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    orGroupEClass.getESuperTypes().add(this.getFeatureGroup());
    featureHasOptionalSubfeatureEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    featureHasOptionalSubfeatureEClass.getESuperTypes().add(this.getFeatureHasSubfeature());
    featureHasMandatorySubfeatureEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    featureHasMandatorySubfeatureEClass.getESuperTypes().add(this.getFeatureHasSubfeature());
    mutualExclusiveEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    mutualExclusiveEClass.getESuperTypes().add(this.getUndirectedRelationship());
    customUndirectedRelationshipEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    customUndirectedRelationshipEClass.getESuperTypes().add(this.getUndirectedRelationship());
    requiresEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    requiresEClass.getESuperTypes().add(this.getDirectedRelationship());
    temporalOrderingSequentialEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    temporalOrderingSequentialEClass.getESuperTypes().add(this.getDirectedRelationship());
    customDirectedRelationshipEClass.getESuperTypes().add(this.getFeatureModelPrimitive());
    customDirectedRelationshipEClass.getESuperTypes().add(this.getDirectedRelationship());
    autoCompleteEClass.getESuperTypes().add(this.getFeatureModelPrimitive());

    // Initialize classes and features; add operations and parameters
    initEClass(featureModelEClass, FeatureModel.class, "FeatureModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFeatureModel_Id(), ecorePackage.getEString(), "id", null, 0, 1, FeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFeatureModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, FeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeatureModel_Features(), this.getFeature(), null, "features", null, 0, -1, FeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeatureModel_Primitives(), this.getFeatureModelPrimitive(), null, "primitives", null, 0, -1, FeatureModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureModelPrimitiveEClass, FeatureModelPrimitive.class, "FeatureModelPrimitive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFeatureModelPrimitive_Id(), ecorePackage.getEString(), "id", null, 0, 1, FeatureModelPrimitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFeatureModelPrimitive_Name(), ecorePackage.getEString(), "name", null, 0, 1, FeatureModelPrimitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFeatureModelPrimitive_ConfigurationSource(), this.getConfigurationSource(), "configurationSource", null, 0, 1, FeatureModelPrimitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeatureModelPrimitive_Explanations(), this.getExplanation(), null, "explanations", null, 0, -1, FeatureModelPrimitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureHasSubfeatureEClass, FeatureHasSubfeature.class, "FeatureHasSubfeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFeatureHasSubfeature_Parent(), this.getFeature(), null, "parent", null, 0, 1, FeatureHasSubfeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeatureHasSubfeature_Subfeature(), this.getFeature(), null, "subfeature", null, 0, 1, FeatureHasSubfeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(undirectedRelationshipEClass, UndirectedRelationship.class, "UndirectedRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(directedRelationshipEClass, DirectedRelationship.class, "DirectedRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(featureGroupEClass, FeatureGroup.class, "FeatureGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFeature_Id(), ecorePackage.getEString(), "id", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFeature_Name(), ecorePackage.getEString(), "name", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_GroupHasParent(), this.getGroupHasParent(), null, "groupHasParent", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_GroupHasChild(), this.getGroupHasChild(), null, "groupHasChild", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_FeatureHasParent(), this.getFeatureHasSubfeature(), null, "featureHasParent", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_FeatureHasSubfeature(), this.getFeatureHasSubfeature(), null, "featureHasSubfeature", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_SelectedFeature(), this.getSelectedFeature(), null, "selectedFeature", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_EliminatedFeature(), this.getEliminatedFeature(), null, "eliminatedFeature", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_UndirectedRelationships(), this.getUndirectedRelationship(), null, "undirectedRelationships", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_IncomingDirectedRelationships(), this.getDirectedRelationship(), null, "incomingDirectedRelationships", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_OutgoingDirectedRelationships(), this.getDirectedRelationship(), null, "outgoingDirectedRelationships", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFeature_FeatureIsRoot(), this.getFeatureIsRoot(), null, "featureIsRoot", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupHasParentEClass, GroupHasParent.class, "GroupHasParent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGroupHasParent_Parent(), this.getFeature(), null, "parent", null, 0, 1, GroupHasParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupHasParent_Group(), this.getFeatureGroup(), null, "group", null, 0, 1, GroupHasParent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupHasChildEClass, GroupHasChild.class, "GroupHasChild", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGroupHasChild_Child(), this.getFeature(), null, "child", null, 0, 1, GroupHasChild.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupHasChild_Group(), this.getFeatureGroup(), null, "group", null, 0, 1, GroupHasChild.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selectedFeatureEClass, SelectedFeature.class, "SelectedFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSelectedFeature_Feature(), this.getFeature(), null, "feature", null, 0, 1, SelectedFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eliminatedFeatureEClass, EliminatedFeature.class, "EliminatedFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEliminatedFeature_Feature(), this.getFeature(), null, "feature", null, 0, 1, EliminatedFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureIsRootEClass, FeatureIsRoot.class, "FeatureIsRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFeatureIsRoot_Feature(), this.getFeature(), null, "feature", null, 0, 1, FeatureIsRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(explanationEClass, Explanation.class, "Explanation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExplanation_Primitives(), this.getFeatureModelPrimitive(), null, "primitives", null, 0, -1, Explanation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupHasMaxEClass, GroupHasMax.class, "GroupHasMax", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGroupHasMax_Max(), ecorePackage.getEString(), "max", null, 0, 1, GroupHasMax.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupHasMax_Group(), this.getFeatureGroup(), null, "group", null, 0, 1, GroupHasMax.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupHasMinEClass, GroupHasMin.class, "GroupHasMin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGroupHasMin_Min(), ecorePackage.getEString(), "min", null, 0, 1, GroupHasMin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupHasMin_Group(), this.getFeatureGroup(), null, "group", null, 0, 1, GroupHasMin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(alternativeGroupEClass, AlternativeGroup.class, "AlternativeGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAlternativeGroup_GroupHasParent(), this.getGroupHasParent(), null, "groupHasParent", null, 0, 1, AlternativeGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAlternativeGroup_GroupHasChild(), this.getGroupHasChild(), null, "groupHasChild", null, 0, -1, AlternativeGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAlternativeGroup_GroupHasMax(), this.getGroupHasMax(), null, "groupHasMax", null, 0, 1, AlternativeGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAlternativeGroup_GroupHasMin(), this.getGroupHasMin(), null, "groupHasMin", null, 0, 1, AlternativeGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orGroupEClass, OrGroup.class, "OrGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrGroup_GroupHasParent(), this.getGroupHasParent(), null, "groupHasParent", null, 0, 1, OrGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrGroup_GroupHasChild(), this.getGroupHasChild(), null, "groupHasChild", null, 0, -1, OrGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrGroup_GroupHasMax(), this.getGroupHasMax(), null, "groupHasMax", null, 0, 1, OrGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrGroup_GroupHasMin(), this.getGroupHasMin(), null, "groupHasMin", null, 0, 1, OrGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureHasOptionalSubfeatureEClass, FeatureHasOptionalSubfeature.class, "FeatureHasOptionalSubfeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(featureHasMandatorySubfeatureEClass, FeatureHasMandatorySubfeature.class, "FeatureHasMandatorySubfeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(mutualExclusiveEClass, MutualExclusive.class, "MutualExclusive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMutualExclusive_RelatedFeatures(), this.getFeature(), null, "relatedFeatures", null, 0, -1, MutualExclusive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(customUndirectedRelationshipEClass, CustomUndirectedRelationship.class, "CustomUndirectedRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCustomUndirectedRelationship_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, CustomUndirectedRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomUndirectedRelationship_RelatedFeatures(), this.getFeature(), null, "relatedFeatures", null, 0, -1, CustomUndirectedRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(requiresEClass, Requires.class, "Requires", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRequires_Sources(), this.getFeature(), null, "sources", null, 0, -1, Requires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRequires_Targets(), this.getFeature(), null, "targets", null, 0, -1, Requires.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(temporalOrderingSequentialEClass, TemporalOrderingSequential.class, "TemporalOrderingSequential", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTemporalOrderingSequential_Sources(), this.getFeature(), null, "sources", null, 0, -1, TemporalOrderingSequential.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTemporalOrderingSequential_Targets(), this.getFeature(), null, "targets", null, 0, -1, TemporalOrderingSequential.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(customDirectedRelationshipEClass, CustomDirectedRelationship.class, "CustomDirectedRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCustomDirectedRelationship_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, CustomDirectedRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomDirectedRelationship_Sources(), this.getFeature(), null, "sources", null, 0, -1, CustomDirectedRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomDirectedRelationship_Targets(), this.getFeature(), null, "targets", null, 0, -1, CustomDirectedRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(autoCompleteEClass, AutoComplete.class, "AutoComplete", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Initialize enums and add enum literals
    initEEnum(configurationSourceEEnum, ConfigurationSource.class, "ConfigurationSource");
    addEEnumLiteral(configurationSourceEEnum, ConfigurationSource.MODEL);
    addEEnumLiteral(configurationSourceEEnum, ConfigurationSource.MODELCONSEQUENCE);
    addEEnumLiteral(configurationSourceEEnum, ConfigurationSource.USER);
    addEEnumLiteral(configurationSourceEEnum, ConfigurationSource.USERCONSEQUENCE);

    // Create resource
    createResource(eNS_URI);
  }

} //FmprimitivesPackageImpl
