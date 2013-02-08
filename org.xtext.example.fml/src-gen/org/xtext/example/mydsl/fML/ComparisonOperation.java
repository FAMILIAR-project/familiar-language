/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comparison Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.ComparisonOperation#getCmpOp <em>Cmp Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.ComparisonOperation#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getComparisonOperation()
 * @model
 * @generated
 */
public interface ComparisonOperation extends ComplexCommand
{
  /**
   * Returns the value of the '<em><b>Cmp Op</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fML.ComparisonOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cmp Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cmp Op</em>' attribute.
   * @see org.xtext.example.mydsl.fML.ComparisonOperator
   * @see #setCmpOp(ComparisonOperator)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComparisonOperation_CmpOp()
   * @model
   * @generated
   */
  ComparisonOperator getCmpOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComparisonOperation#getCmpOp <em>Cmp Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cmp Op</em>' attribute.
   * @see org.xtext.example.mydsl.fML.ComparisonOperator
   * @see #getCmpOp()
   * @generated
   */
  void setCmpOp(ComparisonOperator value);

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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getComparisonOperation_Right()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getRight();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.ComparisonOperation#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(ComplexCommand value);

} // ComparisonOperation
