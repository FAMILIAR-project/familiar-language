/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraints Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.ConstraintsSpecification#getCsts <em>Csts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getConstraintsSpecification()
 * @model
 * @generated
 */
public interface ConstraintsSpecification extends EObject
{
  /**
   * Returns the value of the '<em><b>Csts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Csts</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Csts</em>' containment reference.
   * @see #setCsts(ConstraintExpr)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getConstraintsSpecification_Csts()
   * @model containment="true"
   * @generated
   */
  ConstraintExpr getCsts();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ConstraintsSpecification#getCsts <em>Csts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Csts</em>' containment reference.
   * @see #getCsts()
   * @generated
   */
  void setCsts(ConstraintExpr value);

} // ConstraintsSpecification
