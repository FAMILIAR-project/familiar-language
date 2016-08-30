/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analysis Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.AnalysisOperation#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.AnalysisOperation#getFm <em>Fm</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getAnalysisOperation()
 * @model
 * @generated
 */
public interface AnalysisOperation extends Command
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getAnalysisOperation_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.AnalysisOperation#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fm</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fm</em>' containment reference.
   * @see #setFm(EObject)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getAnalysisOperation_Fm()
   * @model containment="true"
   * @generated
   */
  EObject getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.AnalysisOperation#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(EObject value);

} // AnalysisOperation