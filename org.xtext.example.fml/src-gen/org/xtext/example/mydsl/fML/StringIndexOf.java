/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Index Of</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.StringIndexOf#getStr <em>Str</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.StringIndexOf#getSchar <em>Schar</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getStringIndexOf()
 * @model
 * @generated
 */
public interface StringIndexOf extends IntegerCommand, StringOperation
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringIndexOf_Str()
   * @model containment="true"
   * @generated
   */
  StrCommand getStr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringIndexOf#getStr <em>Str</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Str</em>' containment reference.
   * @see #getStr()
   * @generated
   */
  void setStr(StrCommand value);

  /**
   * Returns the value of the '<em><b>Schar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Schar</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schar</em>' containment reference.
   * @see #setSchar(StrCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringIndexOf_Schar()
   * @model containment="true"
   * @generated
   */
  StrCommand getSchar();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringIndexOf#getSchar <em>Schar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schar</em>' containment reference.
   * @see #getSchar()
   * @generated
   */
  void setSchar(StrCommand value);

} // StringIndexOf
