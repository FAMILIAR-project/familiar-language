/**
 */
package org.xtext.example.mydsl.fmprimitives;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Undirected Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship#getRelatedFeatures <em>Related Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getCustomUndirectedRelationship()
 * @model
 * @generated
 */
public interface CustomUndirectedRelationship extends FeatureModelPrimitive, UndirectedRelationship
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
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getCustomUndirectedRelationship_Stereotype()
   * @model
   * @generated
   */
  String getStereotype();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.CustomUndirectedRelationship#getStereotype <em>Stereotype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stereotype</em>' attribute.
   * @see #getStereotype()
   * @generated
   */
  void setStereotype(String value);

  /**
   * Returns the value of the '<em><b>Related Features</b></em>' reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fmprimitives.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Related Features</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Related Features</em>' reference list.
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getCustomUndirectedRelationship_RelatedFeatures()
   * @model
   * @generated
   */
  EList<Feature> getRelatedFeatures();

} // CustomUndirectedRelationship
