/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesFactory
 * @model kind="package"
 * @generated
 */
public interface FmprimitivesPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "fmprimitives";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://lero.ie/spl/fmprimitives.ecore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "fmprimitives";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FmprimitivesPackage eINSTANCE = fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl.init();

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelImpl <em>Feature Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureModel()
   * @generated
   */
  int FEATURE_MODEL = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__NAME = 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__FEATURES = 2;

  /**
   * The feature id for the '<em><b>Primitives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL__PRIMITIVES = 3;

  /**
   * The number of structural features of the '<em>Feature Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl <em>Feature Model Primitive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureModelPrimitive()
   * @generated
   */
  int FEATURE_MODEL_PRIMITIVE = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_PRIMITIVE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_PRIMITIVE__NAME = 1;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE = 2;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_PRIMITIVE__EXPLANATIONS = 3;

  /**
   * The number of structural features of the '<em>Feature Model Primitive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasSubfeatureImpl <em>Feature Has Subfeature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasSubfeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasSubfeature()
   * @generated
   */
  int FEATURE_HAS_SUBFEATURE = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Subfeature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE__SUBFEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature Has Subfeature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_SUBFEATURE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.UndirectedRelationshipImpl <em>Undirected Relationship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.UndirectedRelationshipImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getUndirectedRelationship()
   * @generated
   */
  int UNDIRECTED_RELATIONSHIP = 3;

  /**
   * The number of structural features of the '<em>Undirected Relationship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNDIRECTED_RELATIONSHIP_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.DirectedRelationshipImpl <em>Directed Relationship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.DirectedRelationshipImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getDirectedRelationship()
   * @generated
   */
  int DIRECTED_RELATIONSHIP = 4;

  /**
   * The number of structural features of the '<em>Directed Relationship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTED_RELATIONSHIP_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureGroupImpl <em>Feature Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureGroupImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureGroup()
   * @generated
   */
  int FEATURE_GROUP = 5;

  /**
   * The number of structural features of the '<em>Feature Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_GROUP_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeature()
   * @generated
   */
  int FEATURE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__NAME = 1;

  /**
   * The feature id for the '<em><b>Group Has Parent</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__GROUP_HAS_PARENT = 2;

  /**
   * The feature id for the '<em><b>Group Has Child</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__GROUP_HAS_CHILD = 3;

  /**
   * The feature id for the '<em><b>Feature Has Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__FEATURE_HAS_PARENT = 4;

  /**
   * The feature id for the '<em><b>Feature Has Subfeature</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__FEATURE_HAS_SUBFEATURE = 5;

  /**
   * The feature id for the '<em><b>Selected Feature</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__SELECTED_FEATURE = 6;

  /**
   * The feature id for the '<em><b>Eliminated Feature</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__ELIMINATED_FEATURE = 7;

  /**
   * The feature id for the '<em><b>Undirected Relationships</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__UNDIRECTED_RELATIONSHIPS = 8;

  /**
   * The feature id for the '<em><b>Incoming Directed Relationships</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__INCOMING_DIRECTED_RELATIONSHIPS = 9;

  /**
   * The feature id for the '<em><b>Outgoing Directed Relationships</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS = 10;

  /**
   * The feature id for the '<em><b>Feature Is Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__FEATURE_IS_ROOT = 11;

  /**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_FEATURE_COUNT = 12;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl <em>Group Has Parent</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasParent()
   * @generated
   */
  int GROUP_HAS_PARENT = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT__GROUP = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Group Has Parent</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_PARENT_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasChildImpl <em>Group Has Child</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasChildImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasChild()
   * @generated
   */
  int GROUP_HAS_CHILD = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Child</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__CHILD = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD__GROUP = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Group Has Child</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_CHILD_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.SelectedFeatureImpl <em>Selected Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.SelectedFeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getSelectedFeature()
   * @generated
   */
  int SELECTED_FEATURE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE__FEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Selected Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTED_FEATURE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.EliminatedFeatureImpl <em>Eliminated Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.EliminatedFeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getEliminatedFeature()
   * @generated
   */
  int ELIMINATED_FEATURE = 10;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE__FEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Eliminated Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELIMINATED_FEATURE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureIsRootImpl <em>Feature Is Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureIsRootImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureIsRoot()
   * @generated
   */
  int FEATURE_IS_ROOT = 11;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT__FEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Feature Is Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_IS_ROOT_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.ExplanationImpl <em>Explanation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.ExplanationImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getExplanation()
   * @generated
   */
  int EXPLANATION = 12;

  /**
   * The feature id for the '<em><b>Primitives</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPLANATION__PRIMITIVES = 0;

  /**
   * The number of structural features of the '<em>Explanation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPLANATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMaxImpl <em>Group Has Max</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMaxImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasMax()
   * @generated
   */
  int GROUP_HAS_MAX = 13;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__MAX = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX__GROUP = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Group Has Max</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MAX_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMinImpl <em>Group Has Min</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMinImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasMin()
   * @generated
   */
  int GROUP_HAS_MIN = 14;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__MIN = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN__GROUP = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Group Has Min</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_HAS_MIN_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.AlternativeGroupImpl <em>Alternative Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.AlternativeGroupImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getAlternativeGroup()
   * @generated
   */
  int ALTERNATIVE_GROUP = 15;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Group Has Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__GROUP_HAS_PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group Has Child</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__GROUP_HAS_CHILD = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Group Has Max</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__GROUP_HAS_MAX = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Group Has Min</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP__GROUP_HAS_MIN = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Alternative Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALTERNATIVE_GROUP_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl <em>Or Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getOrGroup()
   * @generated
   */
  int OR_GROUP = 16;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Group Has Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__GROUP_HAS_PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Group Has Child</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__GROUP_HAS_CHILD = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Group Has Max</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__GROUP_HAS_MAX = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Group Has Min</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP__GROUP_HAS_MIN = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Or Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_GROUP_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasOptionalSubfeatureImpl <em>Feature Has Optional Subfeature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasOptionalSubfeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasOptionalSubfeature()
   * @generated
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE = 17;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Subfeature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE__SUBFEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature Has Optional Subfeature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_OPTIONAL_SUBFEATURE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl <em>Feature Has Mandatory Subfeature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasMandatorySubfeature()
   * @generated
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE = 18;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__PARENT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Subfeature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE__SUBFEATURE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature Has Mandatory Subfeature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_HAS_MANDATORY_SUBFEATURE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.MutualExclusiveImpl <em>Mutual Exclusive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.MutualExclusiveImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getMutualExclusive()
   * @generated
   */
  int MUTUAL_EXCLUSIVE = 19;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Related Features</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE__RELATED_FEATURES = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Mutual Exclusive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MUTUAL_EXCLUSIVE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.CustomUndirectedRelationshipImpl <em>Custom Undirected Relationship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.CustomUndirectedRelationshipImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getCustomUndirectedRelationship()
   * @generated
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP = 20;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Stereotype</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Related Features</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Custom Undirected Relationship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_UNDIRECTED_RELATIONSHIP_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.RequiresImpl <em>Requires</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.RequiresImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getRequires()
   * @generated
   */
  int REQUIRES = 21;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__SOURCES = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Targets</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES__TARGETS = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Requires</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRES_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.TemporalOrderingSequentialImpl <em>Temporal Ordering Sequential</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.TemporalOrderingSequentialImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getTemporalOrderingSequential()
   * @generated
   */
  int TEMPORAL_ORDERING_SEQUENTIAL = 22;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__SOURCES = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Targets</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL__TARGETS = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Temporal Ordering Sequential</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEMPORAL_ORDERING_SEQUENTIAL_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.CustomDirectedRelationshipImpl <em>Custom Directed Relationship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.CustomDirectedRelationshipImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getCustomDirectedRelationship()
   * @generated
   */
  int CUSTOM_DIRECTED_RELATIONSHIP = 23;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The feature id for the '<em><b>Stereotype</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Sources</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__SOURCES = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Targets</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP__TARGETS = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Custom Directed Relationship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOM_DIRECTED_RELATIONSHIP_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.AutoCompleteImpl <em>Auto Complete</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.AutoCompleteImpl
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getAutoComplete()
   * @generated
   */
  int AUTO_COMPLETE = 24;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_COMPLETE__ID = FEATURE_MODEL_PRIMITIVE__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_COMPLETE__NAME = FEATURE_MODEL_PRIMITIVE__NAME;

  /**
   * The feature id for the '<em><b>Configuration Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_COMPLETE__CONFIGURATION_SOURCE = FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE;

  /**
   * The feature id for the '<em><b>Explanations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_COMPLETE__EXPLANATIONS = FEATURE_MODEL_PRIMITIVE__EXPLANATIONS;

  /**
   * The number of structural features of the '<em>Auto Complete</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUTO_COMPLETE_FEATURE_COUNT = FEATURE_MODEL_PRIMITIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource <em>Configuration Source</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource
   * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getConfigurationSource()
   * @generated
   */
  int CONFIGURATION_SOURCE = 25;


  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel <em>Feature Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Model</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModel
   * @generated
   */
  EClass getFeatureModel();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getId()
   * @see #getFeatureModel()
   * @generated
   */
  EAttribute getFeatureModel_Id();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getName()
   * @see #getFeatureModel()
   * @generated
   */
  EAttribute getFeatureModel_Name();

  /**
   * Returns the meta object for the containment reference list '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getFeatures()
   * @see #getFeatureModel()
   * @generated
   */
  EReference getFeatureModel_Features();

  /**
   * Returns the meta object for the containment reference list '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getPrimitives <em>Primitives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Primitives</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModel#getPrimitives()
   * @see #getFeatureModel()
   * @generated
   */
  EReference getFeatureModel_Primitives();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive <em>Feature Model Primitive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Model Primitive</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive
   * @generated
   */
  EClass getFeatureModelPrimitive();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getId()
   * @see #getFeatureModelPrimitive()
   * @generated
   */
  EAttribute getFeatureModelPrimitive_Id();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getName()
   * @see #getFeatureModelPrimitive()
   * @generated
   */
  EAttribute getFeatureModelPrimitive_Name();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getConfigurationSource <em>Configuration Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Configuration Source</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getConfigurationSource()
   * @see #getFeatureModelPrimitive()
   * @generated
   */
  EAttribute getFeatureModelPrimitive_ConfigurationSource();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getExplanations <em>Explanations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explanations</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureModelPrimitive#getExplanations()
   * @see #getFeatureModelPrimitive()
   * @generated
   */
  EReference getFeatureModelPrimitive_Explanations();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature <em>Feature Has Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Has Subfeature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature
   * @generated
   */
  EClass getFeatureHasSubfeature();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature#getParent()
   * @see #getFeatureHasSubfeature()
   * @generated
   */
  EReference getFeatureHasSubfeature_Parent();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature#getSubfeature <em>Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Subfeature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature#getSubfeature()
   * @see #getFeatureHasSubfeature()
   * @generated
   */
  EReference getFeatureHasSubfeature_Subfeature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.UndirectedRelationship <em>Undirected Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Undirected Relationship</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.UndirectedRelationship
   * @generated
   */
  EClass getUndirectedRelationship();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship <em>Directed Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Directed Relationship</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship
   * @generated
   */
  EClass getDirectedRelationship();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureGroup <em>Feature Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureGroup
   * @generated
   */
  EClass getFeatureGroup();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature
   * @generated
   */
  EClass getFeature();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getId()
   * @see #getFeature()
   * @generated
   */
  EAttribute getFeature_Id();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getName()
   * @see #getFeature()
   * @generated
   */
  EAttribute getFeature_Name();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasParent <em>Group Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Group Has Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasParent()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_GroupHasParent();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasChild <em>Group Has Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Group Has Child</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasChild()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_GroupHasChild();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasParent <em>Feature Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature Has Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasParent()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_FeatureHasParent();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasSubfeature <em>Feature Has Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Feature Has Subfeature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasSubfeature()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_FeatureHasSubfeature();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getSelectedFeature <em>Selected Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Selected Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getSelectedFeature()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_SelectedFeature();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getEliminatedFeature <em>Eliminated Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Eliminated Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getEliminatedFeature()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_EliminatedFeature();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getUndirectedRelationships <em>Undirected Relationships</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Undirected Relationships</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getUndirectedRelationships()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_UndirectedRelationships();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getIncomingDirectedRelationships <em>Incoming Directed Relationships</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Directed Relationships</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getIncomingDirectedRelationships()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_IncomingDirectedRelationships();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getOutgoingDirectedRelationships <em>Outgoing Directed Relationships</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Directed Relationships</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getOutgoingDirectedRelationships()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_OutgoingDirectedRelationships();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureIsRoot <em>Feature Is Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature Is Root</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureIsRoot()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_FeatureIsRoot();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasParent <em>Group Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Has Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasParent
   * @generated
   */
  EClass getGroupHasParent();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasParent#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasParent#getParent()
   * @see #getGroupHasParent()
   * @generated
   */
  EReference getGroupHasParent_Parent();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasParent#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasParent#getGroup()
   * @see #getGroupHasParent()
   * @generated
   */
  EReference getGroupHasParent_Group();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild <em>Group Has Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Has Child</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasChild
   * @generated
   */
  EClass getGroupHasChild();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getChild <em>Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Child</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getChild()
   * @see #getGroupHasChild()
   * @generated
   */
  EReference getGroupHasChild_Child();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasChild#getGroup()
   * @see #getGroupHasChild()
   * @generated
   */
  EReference getGroupHasChild_Group();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.SelectedFeature <em>Selected Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Selected Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.SelectedFeature
   * @generated
   */
  EClass getSelectedFeature();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.SelectedFeature#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.SelectedFeature#getFeature()
   * @see #getSelectedFeature()
   * @generated
   */
  EReference getSelectedFeature_Feature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature <em>Eliminated Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Eliminated Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature
   * @generated
   */
  EClass getEliminatedFeature();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature#getFeature()
   * @see #getEliminatedFeature()
   * @generated
   */
  EReference getEliminatedFeature_Feature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot <em>Feature Is Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Is Root</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot
   * @generated
   */
  EClass getFeatureIsRoot();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureIsRoot#getFeature()
   * @see #getFeatureIsRoot()
   * @generated
   */
  EReference getFeatureIsRoot_Feature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.Explanation <em>Explanation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Explanation</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Explanation
   * @generated
   */
  EClass getExplanation();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Explanation#getPrimitives <em>Primitives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Primitives</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Explanation#getPrimitives()
   * @see #getExplanation()
   * @generated
   */
  EReference getExplanation_Primitives();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMax <em>Group Has Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Has Max</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMax
   * @generated
   */
  EClass getGroupHasMax();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMax#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMax#getMax()
   * @see #getGroupHasMax()
   * @generated
   */
  EAttribute getGroupHasMax_Max();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMax#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMax#getGroup()
   * @see #getGroupHasMax()
   * @generated
   */
  EReference getGroupHasMax_Group();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMin <em>Group Has Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Has Min</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMin
   * @generated
   */
  EClass getGroupHasMin();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMin#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMin#getMin()
   * @see #getGroupHasMin()
   * @generated
   */
  EAttribute getGroupHasMin_Min();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.GroupHasMin#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.GroupHasMin#getGroup()
   * @see #getGroupHasMin()
   * @generated
   */
  EReference getGroupHasMin_Group();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup <em>Alternative Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Alternative Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup
   * @generated
   */
  EClass getAlternativeGroup();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasParent <em>Group Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasParent()
   * @see #getAlternativeGroup()
   * @generated
   */
  EReference getAlternativeGroup_GroupHasParent();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasChild <em>Group Has Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Group Has Child</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasChild()
   * @see #getAlternativeGroup()
   * @generated
   */
  EReference getAlternativeGroup_GroupHasChild();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMax <em>Group Has Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Max</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMax()
   * @see #getAlternativeGroup()
   * @generated
   */
  EReference getAlternativeGroup_GroupHasMax();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMin <em>Group Has Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Min</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AlternativeGroup#getGroupHasMin()
   * @see #getAlternativeGroup()
   * @generated
   */
  EReference getAlternativeGroup_GroupHasMin();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.OrGroup <em>Or Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Group</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.OrGroup
   * @generated
   */
  EClass getOrGroup();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasParent <em>Group Has Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Parent</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasParent()
   * @see #getOrGroup()
   * @generated
   */
  EReference getOrGroup_GroupHasParent();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasChild <em>Group Has Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Group Has Child</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasChild()
   * @see #getOrGroup()
   * @generated
   */
  EReference getOrGroup_GroupHasChild();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasMax <em>Group Has Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Max</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasMax()
   * @see #getOrGroup()
   * @generated
   */
  EReference getOrGroup_GroupHasMax();

  /**
   * Returns the meta object for the reference '{@link fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasMin <em>Group Has Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Group Has Min</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.OrGroup#getGroupHasMin()
   * @see #getOrGroup()
   * @generated
   */
  EReference getOrGroup_GroupHasMin();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasOptionalSubfeature <em>Feature Has Optional Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Has Optional Subfeature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureHasOptionalSubfeature
   * @generated
   */
  EClass getFeatureHasOptionalSubfeature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasMandatorySubfeature <em>Feature Has Mandatory Subfeature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Has Mandatory Subfeature</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.FeatureHasMandatorySubfeature
   * @generated
   */
  EClass getFeatureHasMandatorySubfeature();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.MutualExclusive <em>Mutual Exclusive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mutual Exclusive</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.MutualExclusive
   * @generated
   */
  EClass getMutualExclusive();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.MutualExclusive#getRelatedFeatures <em>Related Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Related Features</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.MutualExclusive#getRelatedFeatures()
   * @see #getMutualExclusive()
   * @generated
   */
  EReference getMutualExclusive_RelatedFeatures();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship <em>Custom Undirected Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Custom Undirected Relationship</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship
   * @generated
   */
  EClass getCustomUndirectedRelationship();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship#getStereotype <em>Stereotype</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Stereotype</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship#getStereotype()
   * @see #getCustomUndirectedRelationship()
   * @generated
   */
  EAttribute getCustomUndirectedRelationship_Stereotype();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship#getRelatedFeatures <em>Related Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Related Features</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomUndirectedRelationship#getRelatedFeatures()
   * @see #getCustomUndirectedRelationship()
   * @generated
   */
  EReference getCustomUndirectedRelationship_RelatedFeatures();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.Requires <em>Requires</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Requires</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Requires
   * @generated
   */
  EClass getRequires();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Requires#getSources <em>Sources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sources</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Requires#getSources()
   * @see #getRequires()
   * @generated
   */
  EReference getRequires_Sources();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.Requires#getTargets <em>Targets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Targets</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.Requires#getTargets()
   * @see #getRequires()
   * @generated
   */
  EReference getRequires_Targets();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential <em>Temporal Ordering Sequential</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Temporal Ordering Sequential</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential
   * @generated
   */
  EClass getTemporalOrderingSequential();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential#getSources <em>Sources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sources</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential#getSources()
   * @see #getTemporalOrderingSequential()
   * @generated
   */
  EReference getTemporalOrderingSequential_Sources();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential#getTargets <em>Targets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Targets</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.TemporalOrderingSequential#getTargets()
   * @see #getTemporalOrderingSequential()
   * @generated
   */
  EReference getTemporalOrderingSequential_Targets();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship <em>Custom Directed Relationship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Custom Directed Relationship</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship
   * @generated
   */
  EClass getCustomDirectedRelationship();

  /**
   * Returns the meta object for the attribute '{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getStereotype <em>Stereotype</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Stereotype</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getStereotype()
   * @see #getCustomDirectedRelationship()
   * @generated
   */
  EAttribute getCustomDirectedRelationship_Stereotype();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getSources <em>Sources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sources</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getSources()
   * @see #getCustomDirectedRelationship()
   * @generated
   */
  EReference getCustomDirectedRelationship_Sources();

  /**
   * Returns the meta object for the reference list '{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getTargets <em>Targets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Targets</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getTargets()
   * @see #getCustomDirectedRelationship()
   * @generated
   */
  EReference getCustomDirectedRelationship_Targets();

  /**
   * Returns the meta object for class '{@link fr.inria.familiar.fmlero.fmprimitives.AutoComplete <em>Auto Complete</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Auto Complete</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.AutoComplete
   * @generated
   */
  EClass getAutoComplete();

  /**
   * Returns the meta object for enum '{@link fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource <em>Configuration Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Configuration Source</em>'.
   * @see fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource
   * @generated
   */
  EEnum getConfigurationSource();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  FmprimitivesFactory getFmprimitivesFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelImpl <em>Feature Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureModel()
     * @generated
     */
    EClass FEATURE_MODEL = eINSTANCE.getFeatureModel();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MODEL__ID = eINSTANCE.getFeatureModel_Id();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MODEL__NAME = eINSTANCE.getFeatureModel_Name();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_MODEL__FEATURES = eINSTANCE.getFeatureModel_Features();

    /**
     * The meta object literal for the '<em><b>Primitives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_MODEL__PRIMITIVES = eINSTANCE.getFeatureModel_Primitives();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl <em>Feature Model Primitive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureModelPrimitiveImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureModelPrimitive()
     * @generated
     */
    EClass FEATURE_MODEL_PRIMITIVE = eINSTANCE.getFeatureModelPrimitive();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MODEL_PRIMITIVE__ID = eINSTANCE.getFeatureModelPrimitive_Id();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MODEL_PRIMITIVE__NAME = eINSTANCE.getFeatureModelPrimitive_Name();

    /**
     * The meta object literal for the '<em><b>Configuration Source</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_MODEL_PRIMITIVE__CONFIGURATION_SOURCE = eINSTANCE.getFeatureModelPrimitive_ConfigurationSource();

    /**
     * The meta object literal for the '<em><b>Explanations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_MODEL_PRIMITIVE__EXPLANATIONS = eINSTANCE.getFeatureModelPrimitive_Explanations();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasSubfeatureImpl <em>Feature Has Subfeature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasSubfeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasSubfeature()
     * @generated
     */
    EClass FEATURE_HAS_SUBFEATURE = eINSTANCE.getFeatureHasSubfeature();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_HAS_SUBFEATURE__PARENT = eINSTANCE.getFeatureHasSubfeature_Parent();

    /**
     * The meta object literal for the '<em><b>Subfeature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_HAS_SUBFEATURE__SUBFEATURE = eINSTANCE.getFeatureHasSubfeature_Subfeature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.UndirectedRelationshipImpl <em>Undirected Relationship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.UndirectedRelationshipImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getUndirectedRelationship()
     * @generated
     */
    EClass UNDIRECTED_RELATIONSHIP = eINSTANCE.getUndirectedRelationship();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.DirectedRelationshipImpl <em>Directed Relationship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.DirectedRelationshipImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getDirectedRelationship()
     * @generated
     */
    EClass DIRECTED_RELATIONSHIP = eINSTANCE.getDirectedRelationship();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureGroupImpl <em>Feature Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureGroupImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureGroup()
     * @generated
     */
    EClass FEATURE_GROUP = eINSTANCE.getFeatureGroup();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeature()
     * @generated
     */
    EClass FEATURE = eINSTANCE.getFeature();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE__ID = eINSTANCE.getFeature_Id();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

    /**
     * The meta object literal for the '<em><b>Group Has Parent</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__GROUP_HAS_PARENT = eINSTANCE.getFeature_GroupHasParent();

    /**
     * The meta object literal for the '<em><b>Group Has Child</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__GROUP_HAS_CHILD = eINSTANCE.getFeature_GroupHasChild();

    /**
     * The meta object literal for the '<em><b>Feature Has Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__FEATURE_HAS_PARENT = eINSTANCE.getFeature_FeatureHasParent();

    /**
     * The meta object literal for the '<em><b>Feature Has Subfeature</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__FEATURE_HAS_SUBFEATURE = eINSTANCE.getFeature_FeatureHasSubfeature();

    /**
     * The meta object literal for the '<em><b>Selected Feature</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__SELECTED_FEATURE = eINSTANCE.getFeature_SelectedFeature();

    /**
     * The meta object literal for the '<em><b>Eliminated Feature</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__ELIMINATED_FEATURE = eINSTANCE.getFeature_EliminatedFeature();

    /**
     * The meta object literal for the '<em><b>Undirected Relationships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__UNDIRECTED_RELATIONSHIPS = eINSTANCE.getFeature_UndirectedRelationships();

    /**
     * The meta object literal for the '<em><b>Incoming Directed Relationships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__INCOMING_DIRECTED_RELATIONSHIPS = eINSTANCE.getFeature_IncomingDirectedRelationships();

    /**
     * The meta object literal for the '<em><b>Outgoing Directed Relationships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__OUTGOING_DIRECTED_RELATIONSHIPS = eINSTANCE.getFeature_OutgoingDirectedRelationships();

    /**
     * The meta object literal for the '<em><b>Feature Is Root</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__FEATURE_IS_ROOT = eINSTANCE.getFeature_FeatureIsRoot();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl <em>Group Has Parent</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasParentImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasParent()
     * @generated
     */
    EClass GROUP_HAS_PARENT = eINSTANCE.getGroupHasParent();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_PARENT__PARENT = eINSTANCE.getGroupHasParent_Parent();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_PARENT__GROUP = eINSTANCE.getGroupHasParent_Group();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasChildImpl <em>Group Has Child</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasChildImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasChild()
     * @generated
     */
    EClass GROUP_HAS_CHILD = eINSTANCE.getGroupHasChild();

    /**
     * The meta object literal for the '<em><b>Child</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_CHILD__CHILD = eINSTANCE.getGroupHasChild_Child();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_CHILD__GROUP = eINSTANCE.getGroupHasChild_Group();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.SelectedFeatureImpl <em>Selected Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.SelectedFeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getSelectedFeature()
     * @generated
     */
    EClass SELECTED_FEATURE = eINSTANCE.getSelectedFeature();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTED_FEATURE__FEATURE = eINSTANCE.getSelectedFeature_Feature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.EliminatedFeatureImpl <em>Eliminated Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.EliminatedFeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getEliminatedFeature()
     * @generated
     */
    EClass ELIMINATED_FEATURE = eINSTANCE.getEliminatedFeature();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELIMINATED_FEATURE__FEATURE = eINSTANCE.getEliminatedFeature_Feature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureIsRootImpl <em>Feature Is Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureIsRootImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureIsRoot()
     * @generated
     */
    EClass FEATURE_IS_ROOT = eINSTANCE.getFeatureIsRoot();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_IS_ROOT__FEATURE = eINSTANCE.getFeatureIsRoot_Feature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.ExplanationImpl <em>Explanation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.ExplanationImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getExplanation()
     * @generated
     */
    EClass EXPLANATION = eINSTANCE.getExplanation();

    /**
     * The meta object literal for the '<em><b>Primitives</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPLANATION__PRIMITIVES = eINSTANCE.getExplanation_Primitives();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMaxImpl <em>Group Has Max</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMaxImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasMax()
     * @generated
     */
    EClass GROUP_HAS_MAX = eINSTANCE.getGroupHasMax();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_HAS_MAX__MAX = eINSTANCE.getGroupHasMax_Max();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_MAX__GROUP = eINSTANCE.getGroupHasMax_Group();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMinImpl <em>Group Has Min</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.GroupHasMinImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getGroupHasMin()
     * @generated
     */
    EClass GROUP_HAS_MIN = eINSTANCE.getGroupHasMin();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_HAS_MIN__MIN = eINSTANCE.getGroupHasMin_Min();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_HAS_MIN__GROUP = eINSTANCE.getGroupHasMin_Group();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.AlternativeGroupImpl <em>Alternative Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.AlternativeGroupImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getAlternativeGroup()
     * @generated
     */
    EClass ALTERNATIVE_GROUP = eINSTANCE.getAlternativeGroup();

    /**
     * The meta object literal for the '<em><b>Group Has Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALTERNATIVE_GROUP__GROUP_HAS_PARENT = eINSTANCE.getAlternativeGroup_GroupHasParent();

    /**
     * The meta object literal for the '<em><b>Group Has Child</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALTERNATIVE_GROUP__GROUP_HAS_CHILD = eINSTANCE.getAlternativeGroup_GroupHasChild();

    /**
     * The meta object literal for the '<em><b>Group Has Max</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALTERNATIVE_GROUP__GROUP_HAS_MAX = eINSTANCE.getAlternativeGroup_GroupHasMax();

    /**
     * The meta object literal for the '<em><b>Group Has Min</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALTERNATIVE_GROUP__GROUP_HAS_MIN = eINSTANCE.getAlternativeGroup_GroupHasMin();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl <em>Or Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.OrGroupImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getOrGroup()
     * @generated
     */
    EClass OR_GROUP = eINSTANCE.getOrGroup();

    /**
     * The meta object literal for the '<em><b>Group Has Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_GROUP__GROUP_HAS_PARENT = eINSTANCE.getOrGroup_GroupHasParent();

    /**
     * The meta object literal for the '<em><b>Group Has Child</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_GROUP__GROUP_HAS_CHILD = eINSTANCE.getOrGroup_GroupHasChild();

    /**
     * The meta object literal for the '<em><b>Group Has Max</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_GROUP__GROUP_HAS_MAX = eINSTANCE.getOrGroup_GroupHasMax();

    /**
     * The meta object literal for the '<em><b>Group Has Min</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_GROUP__GROUP_HAS_MIN = eINSTANCE.getOrGroup_GroupHasMin();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasOptionalSubfeatureImpl <em>Feature Has Optional Subfeature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasOptionalSubfeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasOptionalSubfeature()
     * @generated
     */
    EClass FEATURE_HAS_OPTIONAL_SUBFEATURE = eINSTANCE.getFeatureHasOptionalSubfeature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl <em>Feature Has Mandatory Subfeature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FeatureHasMandatorySubfeatureImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getFeatureHasMandatorySubfeature()
     * @generated
     */
    EClass FEATURE_HAS_MANDATORY_SUBFEATURE = eINSTANCE.getFeatureHasMandatorySubfeature();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.MutualExclusiveImpl <em>Mutual Exclusive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.MutualExclusiveImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getMutualExclusive()
     * @generated
     */
    EClass MUTUAL_EXCLUSIVE = eINSTANCE.getMutualExclusive();

    /**
     * The meta object literal for the '<em><b>Related Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MUTUAL_EXCLUSIVE__RELATED_FEATURES = eINSTANCE.getMutualExclusive_RelatedFeatures();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.CustomUndirectedRelationshipImpl <em>Custom Undirected Relationship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.CustomUndirectedRelationshipImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getCustomUndirectedRelationship()
     * @generated
     */
    EClass CUSTOM_UNDIRECTED_RELATIONSHIP = eINSTANCE.getCustomUndirectedRelationship();

    /**
     * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CUSTOM_UNDIRECTED_RELATIONSHIP__STEREOTYPE = eINSTANCE.getCustomUndirectedRelationship_Stereotype();

    /**
     * The meta object literal for the '<em><b>Related Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CUSTOM_UNDIRECTED_RELATIONSHIP__RELATED_FEATURES = eINSTANCE.getCustomUndirectedRelationship_RelatedFeatures();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.RequiresImpl <em>Requires</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.RequiresImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getRequires()
     * @generated
     */
    EClass REQUIRES = eINSTANCE.getRequires();

    /**
     * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRES__SOURCES = eINSTANCE.getRequires_Sources();

    /**
     * The meta object literal for the '<em><b>Targets</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRES__TARGETS = eINSTANCE.getRequires_Targets();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.TemporalOrderingSequentialImpl <em>Temporal Ordering Sequential</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.TemporalOrderingSequentialImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getTemporalOrderingSequential()
     * @generated
     */
    EClass TEMPORAL_ORDERING_SEQUENTIAL = eINSTANCE.getTemporalOrderingSequential();

    /**
     * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEMPORAL_ORDERING_SEQUENTIAL__SOURCES = eINSTANCE.getTemporalOrderingSequential_Sources();

    /**
     * The meta object literal for the '<em><b>Targets</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEMPORAL_ORDERING_SEQUENTIAL__TARGETS = eINSTANCE.getTemporalOrderingSequential_Targets();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.CustomDirectedRelationshipImpl <em>Custom Directed Relationship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.CustomDirectedRelationshipImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getCustomDirectedRelationship()
     * @generated
     */
    EClass CUSTOM_DIRECTED_RELATIONSHIP = eINSTANCE.getCustomDirectedRelationship();

    /**
     * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CUSTOM_DIRECTED_RELATIONSHIP__STEREOTYPE = eINSTANCE.getCustomDirectedRelationship_Stereotype();

    /**
     * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CUSTOM_DIRECTED_RELATIONSHIP__SOURCES = eINSTANCE.getCustomDirectedRelationship_Sources();

    /**
     * The meta object literal for the '<em><b>Targets</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CUSTOM_DIRECTED_RELATIONSHIP__TARGETS = eINSTANCE.getCustomDirectedRelationship_Targets();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.impl.AutoCompleteImpl <em>Auto Complete</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.AutoCompleteImpl
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getAutoComplete()
     * @generated
     */
    EClass AUTO_COMPLETE = eINSTANCE.getAutoComplete();

    /**
     * The meta object literal for the '{@link fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource <em>Configuration Source</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see fr.inria.familiar.fmlero.fmprimitives.ConfigurationSource
     * @see fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesPackageImpl#getConfigurationSource()
     * @generated
     */
    EEnum CONFIGURATION_SOURCE = eINSTANCE.getConfigurationSource();

  }

} //FmprimitivesPackage
