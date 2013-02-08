/**
 */
package org.xtext.example.mydsl.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requires</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.Requires#getSources <em>Sources</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.Requires#getTargets <em>Targets</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getRequires()
 * @model
 * @generated
 */
public interface Requires extends FeatureModelPrimitive, DirectedRelationship
{
  /**
   * Returns the value of the '<em><b>Sources</b></em>' reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sources</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sources</em>' reference list.
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getRequires_Sources()
   * @model
   * @generated
   */
  EList<Feature> getSources();

  /**
   * Returns the value of the '<em><b>Targets</b></em>' reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Targets</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Targets</em>' reference list.
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getRequires_Targets()
   * @model
   * @generated
   */
  EList<Feature> getTargets();

} // Requires
