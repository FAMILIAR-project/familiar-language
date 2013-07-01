/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getId <em>Id</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getName <em>Name</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasParent <em>Group Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getGroupHasChild <em>Group Has Child</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasParent <em>Feature Has Parent</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasSubfeature <em>Feature Has Subfeature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getSelectedFeature <em>Selected Feature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getEliminatedFeature <em>Eliminated Feature</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getUndirectedRelationships <em>Undirected Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getIncomingDirectedRelationships <em>Incoming Directed Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getOutgoingDirectedRelationships <em>Outgoing Directed Relationships</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureIsRoot <em>Feature Is Root</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Group Has Parent</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.GroupHasParent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Parent</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Parent</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_GroupHasParent()
   * @model
   * @generated
   */
  EList<GroupHasParent> getGroupHasParent();

  /**
   * Returns the value of the '<em><b>Group Has Child</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.GroupHasChild}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Has Child</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group Has Child</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_GroupHasChild()
   * @model
   * @generated
   */
  EList<GroupHasChild> getGroupHasChild();

  /**
   * Returns the value of the '<em><b>Feature Has Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Has Parent</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Has Parent</em>' reference.
   * @see #setFeatureHasParent(FeatureHasSubfeature)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_FeatureHasParent()
   * @model
   * @generated
   */
  FeatureHasSubfeature getFeatureHasParent();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureHasParent <em>Feature Has Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Has Parent</em>' reference.
   * @see #getFeatureHasParent()
   * @generated
   */
  void setFeatureHasParent(FeatureHasSubfeature value);

  /**
   * Returns the value of the '<em><b>Feature Has Subfeature</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.FeatureHasSubfeature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Has Subfeature</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Has Subfeature</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_FeatureHasSubfeature()
   * @model
   * @generated
   */
  EList<FeatureHasSubfeature> getFeatureHasSubfeature();

  /**
   * Returns the value of the '<em><b>Selected Feature</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.SelectedFeature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selected Feature</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selected Feature</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_SelectedFeature()
   * @model
   * @generated
   */
  EList<SelectedFeature> getSelectedFeature();

  /**
   * Returns the value of the '<em><b>Eliminated Feature</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.EliminatedFeature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Eliminated Feature</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Eliminated Feature</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_EliminatedFeature()
   * @model
   * @generated
   */
  EList<EliminatedFeature> getEliminatedFeature();

  /**
   * Returns the value of the '<em><b>Undirected Relationships</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.UndirectedRelationship}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Undirected Relationships</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Undirected Relationships</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_UndirectedRelationships()
   * @model
   * @generated
   */
  EList<UndirectedRelationship> getUndirectedRelationships();

  /**
   * Returns the value of the '<em><b>Incoming Directed Relationships</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Incoming Directed Relationships</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Directed Relationships</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_IncomingDirectedRelationships()
   * @model
   * @generated
   */
  EList<DirectedRelationship> getIncomingDirectedRelationships();

  /**
   * Returns the value of the '<em><b>Outgoing Directed Relationships</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.DirectedRelationship}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Outgoing Directed Relationships</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Directed Relationships</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_OutgoingDirectedRelationships()
   * @model
   * @generated
   */
  EList<DirectedRelationship> getOutgoingDirectedRelationships();

  /**
   * Returns the value of the '<em><b>Feature Is Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Is Root</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Is Root</em>' reference.
   * @see #setFeatureIsRoot(FeatureIsRoot)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getFeature_FeatureIsRoot()
   * @model
   * @generated
   */
  FeatureIsRoot getFeatureIsRoot();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.Feature#getFeatureIsRoot <em>Feature Is Root</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Is Root</em>' reference.
   * @see #getFeatureIsRoot()
   * @generated
   */
  void setFeatureIsRoot(FeatureIsRoot value);

} // Feature
