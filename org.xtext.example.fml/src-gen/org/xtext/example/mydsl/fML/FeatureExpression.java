/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.FeatureExpression#getFt <em>Ft</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureExpression()
 * @model
 * @generated
 */
public interface FeatureExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Ft</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ft</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ft</em>' containment reference.
   * @see #setFt(EObject)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getFeatureExpression_Ft()
   * @model containment="true"
   * @generated
   */
  EObject getFt();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.FeatureExpression#getFt <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ft</em>' containment reference.
   * @see #getFt()
   * @generated
   */
  void setFt(EObject value);

} // FeatureExpression
