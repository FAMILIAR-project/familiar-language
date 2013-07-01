/**
 */
package fr.inria.familiar.fmlero.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Directed Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getSources <em>Sources</em>}</li>
 *   <li>{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getTargets <em>Targets</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getCustomDirectedRelationship()
 * @model
 * @generated
 */
public interface CustomDirectedRelationship extends FeatureModelPrimitive, DirectedRelationship
{
  /**
   * Returns the value of the '<em><b>Stereotype</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stereotype</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stereotype</em>' attribute.
   * @see #setStereotype(String)
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getCustomDirectedRelationship_Stereotype()
   * @model
   * @generated
   */
  String getStereotype();

  /**
   * Sets the value of the '{@link fr.inria.familiar.fmlero.fmprimitives.CustomDirectedRelationship#getStereotype <em>Stereotype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stereotype</em>' attribute.
   * @see #getStereotype()
   * @generated
   */
  void setStereotype(String value);

  /**
   * Returns the value of the '<em><b>Sources</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sources</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sources</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getCustomDirectedRelationship_Sources()
   * @model
   * @generated
   */
  EList<Feature> getSources();

  /**
   * Returns the value of the '<em><b>Targets</b></em>' reference list.
   * The list contents are of type {@link fr.inria.familiar.fmlero.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Targets</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Targets</em>' reference list.
   * @see fr.inria.familiar.fmlero.fmprimitives.FmprimitivesPackage#getCustomDirectedRelationship_Targets()
   * @model
   * @generated
   */
  EList<Feature> getTargets();

} // CustomDirectedRelationship
