/**
 */
package org.xtext.example.mydsl.fmprimitives;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Has Subfeature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature#getParent <em>Parent</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature#getSubfeature <em>Subfeature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureHasSubfeature()
 * @model
 * @generated
 */
public interface FeatureHasSubfeature extends FeatureModelPrimitive
{
  /**
   * Returns the value of the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' reference.
   * @see #setParent(Feature)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureHasSubfeature_Parent()
   * @model
   * @generated
   */
  Feature getParent();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature#getParent <em>Parent</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' reference.
   * @see #getParent()
   * @generated
   */
  void setParent(Feature value);

  /**
   * Returns the value of the '<em><b>Subfeature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subfeature</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subfeature</em>' reference.
   * @see #setSubfeature(Feature)
   * @see org.xtext.example.mydsl.fmprimitives.FmprimitivesPackage#getFeatureHasSubfeature_Subfeature()
   * @model
   * @generated
   */
  Feature getSubfeature();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fmprimitives.FeatureHasSubfeature#getSubfeature <em>Subfeature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subfeature</em>' reference.
   * @see #getSubfeature()
   * @generated
   */
  void setSubfeature(Feature value);

} // FeatureHasSubfeature
