/**
 */
package org.xtext.example.mydsl.fML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.IfCondition#getBexpr <em>Bexpr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.IfCondition#getThen <em>Then</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.IfCondition#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getIfCondition()
 * @model
 * @generated
 */
public interface IfCondition extends Command
{
  /**
   * Returns the value of the '<em><b>Bexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bexpr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bexpr</em>' containment reference.
   * @see #setBexpr(ComplexCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIfCondition_Bexpr()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getBexpr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.IfCondition#getBexpr <em>Bexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bexpr</em>' containment reference.
   * @see #getBexpr()
   * @generated
   */
  void setBexpr(ComplexCommand value);

  /**
   * Returns the value of the '<em><b>Then</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.ScriptCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIfCondition_Then()
   * @model containment="true"
   * @generated
   */
  EList<ScriptCommand> getThen();

  /**
   * Returns the value of the '<em><b>Else</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fML.ScriptCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else</em>' containment reference list.
   * @see org.xtext.example.mydsl.fML.FMLPackage#getIfCondition_Else()
   * @model containment="true"
   * @generated
   */
  EList<ScriptCommand> getElse();

} // IfCondition
