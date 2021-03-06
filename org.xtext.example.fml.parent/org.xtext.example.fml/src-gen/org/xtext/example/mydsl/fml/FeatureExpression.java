/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.FeatureExpression#getFt <em>Ft</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getFeatureExpression()
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
   * @see org.xtext.example.mydsl.fml.FmlPackage#getFeatureExpression_Ft()
   * @model containment="true"
   * @generated
   */
  EObject getFt();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.FeatureExpression#getFt <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ft</em>' containment reference.
   * @see #getFt()
   * @generated
   */
  void setFt(EObject value);

} // FeatureExpression
