/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bool Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.BoolOperation#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.BoolOperation#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getBoolOperation()
 * @model
 * @generated
 */
public interface BoolOperation extends ComplexCommand
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.BOOL_Operator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see org.xtext.example.mydsl.fML.BOOL_Operator
   * @see #setOp(BOOL_Operator)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getBoolOperation_Op()
   * @model
   * @generated
   */
  BOOL_Operator getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.BoolOperation#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see org.xtext.example.mydsl.fML.BOOL_Operator
   * @see #getOp()
   * @generated
   */
  void setOp(BOOL_Operator value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(ComplexCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getBoolOperation_Right()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getRight();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.BoolOperation#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(ComplexCommand value);

} // BoolOperation
