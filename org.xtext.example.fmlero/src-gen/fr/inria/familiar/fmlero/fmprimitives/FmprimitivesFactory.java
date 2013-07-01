/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage
 * @generated
 */
public interface FmprimitivesFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FmprimitivesFactory eINSTANCE = fr.inria.familiar.fmlero.fmprimitives.impl.FmprimitivesFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Feature Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Model</em>'.
   * @generated
   */
  FeatureModel createFeatureModel();

  /**
   * Returns a new object of class '<em>Feature Model Primitive</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Model Primitive</em>'.
   * @generated
   */
  FeatureModelPrimitive createFeatureModelPrimitive();

  /**
   * Returns a new object of class '<em>Feature Has Subfeature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Has Subfeature</em>'.
   * @generated
   */
  FeatureHasSubfeature createFeatureHasSubfeature();

  /**
   * Returns a new object of class '<em>Undirected Relationship</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Undirected Relationship</em>'.
   * @generated
   */
  UndirectedRelationship createUndirectedRelationship();

  /**
   * Returns a new object of class '<em>Directed Relationship</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Directed Relationship</em>'.
   * @generated
   */
  DirectedRelationship createDirectedRelationship();

  /**
   * Returns a new object of class '<em>Feature Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Group</em>'.
   * @generated
   */
  FeatureGroup createFeatureGroup();

  /**
   * Returns a new object of class '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature</em>'.
   * @generated
   */
  Feature createFeature();

  /**
   * Returns a new object of class '<em>Group Has Parent</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Has Parent</em>'.
   * @generated
   */
  GroupHasParent createGroupHasParent();

  /**
   * Returns a new object of class '<em>Group Has Child</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Has Child</em>'.
   * @generated
   */
  GroupHasChild createGroupHasChild();

  /**
   * Returns a new object of class '<em>Selected Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Selected Feature</em>'.
   * @generated
   */
  SelectedFeature createSelectedFeature();

  /**
   * Returns a new object of class '<em>Eliminated Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Eliminated Feature</em>'.
   * @generated
   */
  EliminatedFeature createEliminatedFeature();

  /**
   * Returns a new object of class '<em>Feature Is Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Is Root</em>'.
   * @generated
   */
  FeatureIsRoot createFeatureIsRoot();

  /**
   * Returns a new object of class '<em>Explanation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Explanation</em>'.
   * @generated
   */
  Explanation createExplanation();

  /**
   * Returns a new object of class '<em>Group Has Max</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Has Max</em>'.
   * @generated
   */
  GroupHasMax createGroupHasMax();

  /**
   * Returns a new object of class '<em>Group Has Min</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Has Min</em>'.
   * @generated
   */
  GroupHasMin createGroupHasMin();

  /**
   * Returns a new object of class '<em>Alternative Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Alternative Group</em>'.
   * @generated
   */
  AlternativeGroup createAlternativeGroup();

  /**
   * Returns a new object of class '<em>Or Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Group</em>'.
   * @generated
   */
  OrGroup createOrGroup();

  /**
   * Returns a new object of class '<em>Feature Has Optional Subfeature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Has Optional Subfeature</em>'.
   * @generated
   */
  FeatureHasOptionalSubfeature createFeatureHasOptionalSubfeature();

  /**
   * Returns a new object of class '<em>Feature Has Mandatory Subfeature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Has Mandatory Subfeature</em>'.
   * @generated
   */
  FeatureHasMandatorySubfeature createFeatureHasMandatorySubfeature();

  /**
   * Returns a new object of class '<em>Mutual Exclusive</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mutual Exclusive</em>'.
   * @generated
   */
  MutualExclusive createMutualExclusive();

  /**
   * Returns a new object of class '<em>Custom Undirected Relationship</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Custom Undirected Relationship</em>'.
   * @generated
   */
  CustomUndirectedRelationship createCustomUndirectedRelationship();

  /**
   * Returns a new object of class '<em>Requires</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Requires</em>'.
   * @generated
   */
  Requires createRequires();

  /**
   * Returns a new object of class '<em>Temporal Ordering Sequential</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Temporal Ordering Sequential</em>'.
   * @generated
   */
  TemporalOrderingSequential createTemporalOrderingSequential();

  /**
   * Returns a new object of class '<em>Custom Directed Relationship</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Custom Directed Relationship</em>'.
   * @generated
   */
  CustomDirectedRelationship createCustomDirectedRelationship();

  /**
   * Returns a new object of class '<em>Auto Complete</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Auto Complete</em>'.
   * @generated
   */
  AutoComplete createAutoComplete();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  FmprimitivesPackage getFmprimitivesPackage();

} //FmprimitivesFactory
