/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Union Or Intersection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetr <em>Setr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSetUnionOrIntersection()
 * @model
 * @generated
 */
public interface SetUnionOrIntersection extends SetCommand, SetOperations
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
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetUnionOrIntersection_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Setl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setl</em>' containment reference.
   * @see #setSetl(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetUnionOrIntersection_Setl()
   * @model containment="true"
   * @generated
   */
  SetCommand getSetl();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetl <em>Setl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setl</em>' containment reference.
   * @see #getSetl()
   * @generated
   */
  void setSetl(SetCommand value);

  /**
   * Returns the value of the '<em><b>Setr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setr</em>' containment reference.
   * @see #setSetr(SetCommand)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getSetUnionOrIntersection_Setr()
   * @model containment="true"
   * @generated
   */
  SetCommand getSetr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.SetUnionOrIntersection#getSetr <em>Setr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setr</em>' containment reference.
   * @see #getSetr()
   * @generated
   */
  void setSetr(SetCommand value);

} // SetUnionOrIntersection
