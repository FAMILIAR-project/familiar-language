/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Length</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.StringLength#getStr <em>Str</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getStringLength()
 * @model
 * @generated
 */
public interface StringLength extends IntegerCommand, StringOperation
{
  /**
   * Returns the value of the '<em><b>Str</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Str</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Str</em>' containment reference.
   * @see #setStr(StrCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringLength_Str()
   * @model containment="true"
   * @generated
   */
  StrCommand getStr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringLength#getStr <em>Str</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Str</em>' containment reference.
   * @see #getStr()
   * @generated
   */
  void setStr(StrCommand value);

} // StringLength
