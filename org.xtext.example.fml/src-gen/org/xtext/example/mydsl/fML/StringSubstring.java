/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Substring</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.StringSubstring#getStr <em>Str</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.StringSubstring#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.StringSubstring#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getStringSubstring()
 * @model
 * @generated
 */
public interface StringSubstring extends StrCommand, StringOperation
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringSubstring_Str()
   * @model containment="true"
   * @generated
   */
  StrCommand getStr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringSubstring#getStr <em>Str</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Str</em>' containment reference.
   * @see #getStr()
   * @generated
   */
  void setStr(StrCommand value);

  /**
   * Returns the value of the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Begin</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Begin</em>' containment reference.
   * @see #setBegin(IntegerCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringSubstring_Begin()
   * @model containment="true"
   * @generated
   */
  IntegerCommand getBegin();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringSubstring#getBegin <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Begin</em>' containment reference.
   * @see #getBegin()
   * @generated
   */
  void setBegin(IntegerCommand value);

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(IntegerCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringSubstring_End()
   * @model containment="true"
   * @generated
   */
  IntegerCommand getEnd();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringSubstring#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(IntegerCommand value);

} // StringSubstring
