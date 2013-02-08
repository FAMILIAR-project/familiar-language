/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Concat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.StringConcat#getLstr <em>Lstr</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.StringConcat#getRstr <em>Rstr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getStringConcat()
 * @model
 * @generated
 */
public interface StringConcat extends StrCommand, StringOperation
{
  /**
   * Returns the value of the '<em><b>Lstr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lstr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lstr</em>' containment reference.
   * @see #setLstr(StrCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringConcat_Lstr()
   * @model containment="true"
   * @generated
   */
  StrCommand getLstr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringConcat#getLstr <em>Lstr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lstr</em>' containment reference.
   * @see #getLstr()
   * @generated
   */
  void setLstr(StrCommand value);

  /**
   * Returns the value of the '<em><b>Rstr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rstr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rstr</em>' containment reference.
   * @see #setRstr(StrCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getStringConcat_Rstr()
   * @model containment="true"
   * @generated
   */
  StrCommand getRstr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.StringConcat#getRstr <em>Rstr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rstr</em>' containment reference.
   * @see #getRstr()
   * @generated
   */
  void setRstr(StrCommand value);

} // StringConcat
